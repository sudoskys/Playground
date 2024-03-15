package week03;

import java.io.*;
import java.util.Scanner;

class FileStream {

    public static void main(String[] args) {
        File file = new File("newfile.txt");

        // 使用 FileOutputStream 进行写入
        try (FileOutputStream fos = new FileOutputStream(file)) {
            String str = "Hello, world!";
            fos.write(str.getBytes());
            System.out.println("Successfully wrote to the file using FileOutputStream.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 使用 FileInputStream 进行读取
        try (FileInputStream fis = new FileInputStream(file)) {
            int content;
            while ((content = fis.read()) != -1) {
                // convert to char and display it
                System.out.print((char) content);
            }
            System.out.println("\nSuccessfully read from the file using FileInputStream.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 使用 BufferedOutputStream 进行写入
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file))) {
            String str = "Hello, world!";
            bos.write(str.getBytes());
            bos.flush();
            System.out.println("Successfully wrote to the file using BufferedOutputStream.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 使用 BufferedInputStream 进行读取
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file))) {
            int content;
            while ((content = bis.read()) != -1) {
                // convert to char and display it
                System.out.print((char) content);
            }
            System.out.println("\nSuccessfully read from the file using BufferedInputStream.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


class FileDir {

    public static void main(String[] args) {
        // Specify the directory path
        File directory = new File("/directory");

        // Create the directory
        boolean isCreated = directory.mkdirs();

        if (isCreated) {
            System.out.println("Directory created successfully");
        } else {
            System.out.println("Failed to create directory");
        }
    }
}


class FileExist {

    public static void main(String[] args) {
        // Specify the directory path
        File directory = new File("/directory");

        // Check if the directory exists
        if (directory.exists() && directory.isDirectory()) {
            System.out.println("Directory exists");
        } else {
            System.out.println("Directory does not exist");
        }
    }
}


class CurrentPath {

    public static void main(String[] args) {
        // 获取当前Java程序的路径
        String currentPath = System.getProperty("user.dir");
        System.out.println("Current path: " + currentPath);

        // 创建一个File对象
        File file = new File(currentPath + "/newfile.txt");

        // 使用 FileWriter 进行追加写入
        try (FileWriter fw = new FileWriter(file, true)) {
            String str = "Hello, world!";
            fw.write(str);
            System.out.println("Successfully appended to the file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


public class FileOpt {

    public static void main(String[] args) {
        // Specify the file path
        File file = new File("newfile.txt");

        // Using BufferedReader
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Using Scanner
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
