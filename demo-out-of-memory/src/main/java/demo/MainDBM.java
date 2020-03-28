package demo;

import java.nio.ByteBuffer;

public class MainDBM {
    // -Xmx10m -Xms10m -XX:MaxDirectMemorySize=50m
    public static void main(String[] args) throws InterruptedException {
        System.out.println("maxDirectMemory:"+(sun.misc.VM.maxDirectMemory()/(double)1024/1204)+"MB");
        Thread.sleep(300);
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(10*1024*1024);
    }
}
