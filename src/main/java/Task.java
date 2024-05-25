import java.io.*;
import java.util.concurrent.Callable;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Task implements Callable<String> {
    File file = new File("final_file.jpg");
    FileOutputStream fos;
    FileWriter logFile;
    String information;
    byte[] byteArray;
    int k;
    int d;
    int p;
    public Task(String name, FileWriter logFile, FileOutputStream fos) throws IOException {
        this.fos = fos;
        this.logFile = logFile;

        File file = new File(name);
        DataInputStream dis = new DataInputStream(new FileInputStream(file));
        int k = dis.readInt();
        byteArray = new byte[k];
        dis.readFully(byteArray);
        int d = dis.readInt();
        int p = dis.readInt();

        information = "прочитали файл " + name +
                "\nразмер блока данных:" + k + "\nконтрольное число четности: " + d + "\nномер фрагмента: " + p + "\nподсчитанная четность: " + ifEven() + "\n";
    }

    public synchronized void information() throws IOException {
        logFile.write(information);
        logFile.flush();
    }
    public synchronized void write() throws IOException {
        fos.write(byteArray);
        fos.flush();
    }

    public int ifEven() {
        int ans = 0;
        for (int i = 0; i < byteArray.length; i++) {
            ans += Integer.bitCount((int) byteArray[i]);
        }
        return ans%2;
    }

    @Override
    public String call() {
        try {
            write();
            information();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
