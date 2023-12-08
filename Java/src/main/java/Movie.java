/**
 * 任务：实现一个电影类
 * 类名为：Movie
 */

// 请在下面的Begin-End之间按照注释中给出的提示编写正确的代码

public class Movie {
    // 定义6个变量
    // 电影id（id int） 电影名称（movieName String）  电影类型（movieType String）
    // 电影评分（double score）  电影时长（totalTime int）  电影简介（content String）
    int id;
    String movieName;
    String movieType;
    double score;
    int totalTime;
    String content;

    public Movie() {
    }

    public Movie(int id, String movieName, String movieType, double score, int totalTime, String content) {
        this.id = id;
        this.movieName = movieName;
        this.movieType = movieType;
        this.score = score;
        this.totalTime = totalTime;
        this.content = content;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieName() {
        return this.movieName;
    }

    public void setMovieType(String movieType) {
        this.movieType = movieType;
    }

    public String getMovieType() {
        return this.movieType;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public double getScore() {
        return this.score;
    }

    public void setTotalTime(int totalTime) {
        this.totalTime = totalTime;
    }

    public int getTotalTime() {
        return this.totalTime;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return this.content;
    }


    // 定义设置和获取6个变量值的方法

    public void print_moive() {
        System.out.printf("电影id：%d\n", this.id);
        System.out.printf("电影名称：%s\n", this.movieName);
        System.out.printf("电影类型：%s\n", this.movieType);
        System.out.printf("电影评分：%.2f\n", this.score);
        System.out.printf("电影时长：%d\n", this.totalTime);
        System.out.printf("电影简介：%s\n", this.content);
    }

    public static void main(String[] arg) {
        Movie movie = new Movie(1, "流浪地球", "科幻", 7.90, 125, "近未来，科学家们发现太阳急速衰老膨胀，短时间内包括地球在内...");
        movie.print_moive();
    }
}