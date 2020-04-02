package demo.lombok.Bean;

import lombok.Cleanup;

import java.io.*;

/**
 * @Clearnup将自动释放数据流
 */
public class User6 {
    public void userCleanUp() throws IOException {
        @Cleanup  InputStream in = new FileInputStream("src/main/resources/tmp.txt");
        @Cleanup  OutputStream out = new FileOutputStream("src/main/resources/dist.txt");
        byte[] b = new byte[10000];
        while (true) {
            int r = in.read(b);
            if (r == -1) break;
            out.write(b, 0, r);
        }
        out.write('\r');
        out.write('\n');
    }

    public boolean deleteFile(){
        File file = new File("src/main/resources/dist.txt");
        if(file.exists())
            return file.delete();
        return false;
    }

}
