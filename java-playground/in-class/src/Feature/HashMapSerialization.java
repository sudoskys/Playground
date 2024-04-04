package Feature;

import java.io.*;
import java.util.HashMap;

public class HashMapSerialization {
    public static void main(String[] args) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("Alice", 25);
        map.put("Bob", 30);
        map.put("Charlie", 35);

        try {
            // 序列化
            FileOutputStream fos = new FileOutputStream("hashmap.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(map);
            oos.close();
            fos.close();
            System.out.println("Serialized HashMap data is saved in hashmap.ser");

            // 反序列化
            FileInputStream fis = new FileInputStream("hashmap.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            HashMap deserializedMap = (HashMap) ois.readObject();
            ois.close();
            fis.close();

            System.out.println("Deserialized HashMap: " + deserializedMap);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Class not found");
            c.printStackTrace();
        }
    }
}