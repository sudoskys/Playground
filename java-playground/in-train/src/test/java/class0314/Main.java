package class0314;

import java.io.*;
import java.util.*;

class User implements Serializable, Comparable<User> {
    private static final long serialVersionUID = 1L;
    int userid;
    String username;
    String password; // removed transient
    Date regDate;
    String sex;
    int score;

    public User(int userid, String username, String password, Date regDate, String sex, int score) {
        this.userid = userid;
        this.username = username;
        this.password = password;
        this.regDate = regDate;
        this.sex = sex;
        this.score = score;
    }

    // constructor, getters and setters

    @Override
    public String toString() {
        return "User [userid=" + userid + ", username=" + username + ", regDate=" + regDate + ", sex=" + sex + ", score=" + score + "]";
    }

    @Override
    public int compareTo(User o) {
        int sexComp = this.sex.compareTo(o.sex);
        if (sexComp != 0) {
            return sexComp;
        } else {
            int dateComp = this.regDate.compareTo(o.regDate);
            if (dateComp != 0) {
                return dateComp;
            } else {
                int nameComp = this.username.compareTo(o.username);
                if (nameComp != 0) {
                    return nameComp;
                } else {
                    return Integer.compare(this.userid, o.userid);
                }
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        User[] userArray = {
                new User(1, "zhangsan", "12346", new Date(), "男", 100),
                new User(2, "lisi", "123456", new Date(), "女", 90),
                new User(3, "wangwu", "123", new Date(), "男", 80),
        };
        // sort userArray
        Arrays.sort(userArray);
        TreeSet<User> users = new TreeSet<>(Arrays.asList(userArray));
        for (User user : users) {
            System.out.println(user);
        }
        System.out.println("------------");
        // serialize users
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("user.dat"))) {
            oos.writeObject(users);
        } catch (IOException e) {
            e.printStackTrace();
        }
        TreeSet<User> deserializedUsers = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("user.dat"))) {
            deserializedUsers = (TreeSet<User>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (deserializedUsers != null) {
            for (User user : deserializedUsers) {
                System.out.println(user);
            }
        }
    }
}