package demo;


import org.apache.commons.lang.ArrayUtils;

import java.util.Arrays;
import java.util.List;

/**
 * 测试ArrayUtils工具类
 * 自定义实现数组深拷贝，支持多维数组深拷贝
 */
public class Main {
    public static void main(String[] args) {
        int[] arr1 = new int[]{1,3,5,7,9};
        int[] arr2 = new int[]{2,4,6,8};
        int[] arr3 = ArrayUtils.addAll(arr1,arr2);
        Integer[] arr4 = ArrayUtils.toObject(arr3);
        List<Integer> list = Arrays.asList(arr4);
        list.forEach(System.out::print);
        System.out.println();

        ArrayUtils.clone(arr3);
        Integer[] arr5 = cloneArray(arr4);
        arr5[0] = 0;
        Arrays.asList(arr4).forEach(System.out::print);
        System.out.println();
        Arrays.asList(arr5).forEach(System.out::print);

        System.out.println();
        //添加元素
        Object[] arr6 = ArrayUtils.add(arr5, 0, -1);
        Arrays.asList(arr6).forEach(System.out::print);

        System.out.println();
        //截取数组
        Object[] arr7 = ArrayUtils.subarray(arr5, 1, 8);
        Arrays.asList(arr7).forEach(System.out::print);

        //ArrayUtils.clone()克隆数组，数组是新的，但是数组中的元素还是原来的对象
        //重写的clone可以进行深拷贝
        MyObject[] myObjects = new MyObject[3];
        myObjects[0] = MyObject.of("a");
        MyObject[] myObjects1 = cloneArray(myObjects);
        System.out.println();
        System.out.println(ArrayUtils.isEquals(myObjects,myObjects1));
        myObjects1[0].setName("1");
        Arrays.asList(myObjects).forEach(System.out::println);
        Arrays.asList(myObjects1).forEach(System.out::println);


        MyObject[][] myObjects2 = new MyObject[2][2];
        myObjects2[0] = new MyObject[2];
        myObjects2[0][1] = MyObject.of("01");
        MyObject[][] myObjects3 = cloneArray(myObjects2);
        System.out.println();
        System.out.println(ArrayUtils.isEquals(myObjects2,myObjects3));
        myObjects3[0][1] = MyObject.of("aa");
        System.out.println(myObjects2[0][1]);
        System.out.println(myObjects3[0][1]);

    }

    public static <T> T[] cloneArray(T[] array) {
        if (array == null) {
            return null;
        }
        T[] newArr = array.clone();

        //找到数组中一个非空元素的下标
        int nonNullIndex = findNonNullIndex(newArr);
        if(nonNullIndex<0)
            return newArr;

        if(newArr[nonNullIndex].getClass().isArray()){  //如果元素是数组，那对每个元素进行数组拷贝
            if (newArr.length > 0) {
                for (int i = 0; i < newArr.length; i++) {
                    newArr[i] = (T) cloneArray((Object[]) newArr[i]);
                }
            }
        }else if(newArr[nonNullIndex] instanceof MyCloneable) { //如果元素实现了克隆接口，则对每个元素进行手动克隆，实现深克隆
            if (newArr.length > 0) {
                for (int i = 0; i < newArr.length; i++) {
                    if(newArr[i]!=null)
                        newArr[i] = ((MyCloneable)array[i]).cloneObject();
                }
            }
        }
        return newArr;
    }

    private static <T> int findNonNullIndex(T[] array){
        if(array==null)
            return -1;
        for(int i=0;i<array.length;i++){
            if(array[i]!=null)
                return i;
        }
        return -1;
    }

}
