package IOStream;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class IOStream {
    final static int charPerPage = 1800;
    public static void main(String[] args) {
        try {
            readFileInByteArrayAndShowInConsole();
            glue10Files();
            consoleAppOnePageAtTime();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readFileInByteArrayAndShowInConsole() throws IOException {
        BufferedInputStream in = new BufferedInputStream(new FileInputStream("50byte.txt"));
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try{
            int item;
            while((item = in.read()) != -1) {
                out.write(item);
            }
            byte[] byteArray = out.toByteArray();
            System.out.println(Arrays.toString(byteArray));
        }
        finally {
            in.close();
            out.close();
        }
    }

    public static void glue10Files() throws IOException {
        ArrayList<InputStream> al = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            al.add(new FileInputStream(i + ".txt"));
        }
        BufferedInputStream in = new BufferedInputStream(new SequenceInputStream(Collections.enumeration(al)));

        FileOutputStream finalFile=new FileOutputStream("filesInOne.txt");
        BufferedOutputStream out = new BufferedOutputStream(finalFile);

        try{
            int item;
            while((item = in.read()) != -1) {
                System.out.print((char)item);
                out.write(item);
            }
        }
        finally {
            in.close();
            out.close();
        }
    }

    public static void consoleAppOnePageAtTime() throws IOException {
        RandomAccessFile file = new RandomAccessFile("fileToRead.txt", "rw");
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter page number:");
        int position = sc.nextInt() - 1;
        try{
            file.seek(position * charPerPage);
            for (int i = 0; i < charPerPage; i++) {
                System.out.print((char)file.read());
            }
        }
        finally {
            file.close();
        }
    }
}
