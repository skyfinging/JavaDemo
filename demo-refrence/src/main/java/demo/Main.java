package demo;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * 验证强引用、软引用、弱引用、虚引用
 * 加入启动参数 -XX:+PrintGC 以查看gc信息
 * -Xms10m -Xmx10m -XX:SurvivorRatio=8 -XX:NewRatio=4
 * 设置堆大小是10M，新生代是2M，老年代是8M，Eden区是1.6M
 *
 * 当发生full gc的时候，弱引用肯定会被回收
 * 当相隔一段时间后，内存占用一定量之后，进行full gc的时候，软引用会被回收
 * 如果内存设置为10M，软引用的释放时间为4M+6.5S , 4.6M+6S , 5M+5.5S , 5.6M+5S
 * 如果内存设置为20M，软引用释放时间变为10M+10S
 *
 */
public class Main {

    private static ReferenceQueue<Iid> rq = new ReferenceQueue();

    public static void checkQueue() {
        List<MyObject> myObjectList = new ArrayList();
        Reference<? extends Iid> ref = null;
        while ((ref = rq.poll()) != null) {
            if (ref != null) {
                System.out.println("In queue: "+ref.get().getId());
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {

            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        List<MyObject> myObjectList = new ArrayList();
        List<WeakReference<MyWeakObject>> weakObjectWeakReference = new ArrayList();
        List<SoftReference<MySoftObject>> softReferences = new ArrayList();
        for(int i=0;i<3;i++) {
            MyObject myObject = new MyObject(""+i);
            MySoftObject mySoftObject = new MySoftObject(""+i);
            MyWeakObject myWeakObject = new MyWeakObject(""+i);

            myObjectList.add(myObject);
            weakObjectWeakReference.add(new WeakReference(myWeakObject));
            softReferences.add(new SoftReference(mySoftObject));
        }

//        try { // 下面休息几分钟，让上面的垃圾回收线程运行完成
//            Thread.currentThread().sleep(8000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        checkQueue();

        int i=3;
        while(true){
            MyObject myObject = new MyObject(""+i++);
            myObjectList.add(myObject);
            Thread.sleep(10);
            if(i%50==0) {
                //System.out.println(System.currentTimeMillis());
                System.gc();//手动调用full gc，软引用不一定会被回收
            }
        }
    }
}
