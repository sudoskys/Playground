


class Circle3 {
    int r;

    // build
    public Circle3(int r) {
        this.r = r;
    }

    // get area
    public double getArea() {
        return Math.PI * Math.pow(r, 2);
    }
}


class Sector {
    int r;
    int l; // 母线

    // build

    // get area
    public double getArea() {
        return Math.PI * r * l;
    }

    public Sector(int r, int l) {
        this.r = r;
        this.l = l;
    }
}


public class Cone {
    // 母线
    Circle3 bottom;
    // 底面半径
    Sector sector;

    public Cone(Circle3 bottom, Sector sector) {
        this.bottom = bottom;
        this.sector = sector;
    }

    public double getArea() {
        return bottom.getArea() + sector.getArea();
    }

    public static void main(String[] args) {
        Circle3 c = new Circle3(8);
        Sector s = new Sector(8, 15);
        Cone cone = new Cone(c, s);
        // 保留两位小数
        String area=String.format("%.2f", cone.getArea());
        System.out.println(area);
    }
}
