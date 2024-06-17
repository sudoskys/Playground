package dao;

public class User {
    private int id;
    private String username;
    private String password;
    private int score;

    // Constructors, Getters, and Setters

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.score = 0; // 默认积分为0
    }

    public User(int id, String username, String password, int score) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.score = score;
    }

    public User(String username, String password, int score) {
        this.username = username;
        this.password = password;
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getScore() {
        return score;
    }

    /**
     * 设置用户ID
     *
     * @param id 用户ID
     */
    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setScore(int score) {
        this.score = score;
    }
}