class Fruits {
    String name = "水果";
    String color = "颜色";
}


class Apple extends Fruits {
    String name = "苹果";
    String color = "红色";
}

class Pear extends Fruits {
    String name = "bigPear";
    String color = "green";
}

public class Peer {

    public static void main(String[] args) {
        Fruits f = new Fruits();
        Apple a = new Apple();
        Pear p = new Pear();
        // 判断
        System.out.println(f instanceof Fruits);
        System.out.println(a instanceof Fruits);
        System.out.println(p instanceof Fruits);

        Fruits f1 = new Pear();
        f1.name = "bigPear";
        f1.color = "green";
        System.out.println(f1.name);
        System.out.println(f1 instanceof Fruits);
        System.out.println(f1 instanceof Pear);
        Pear x = (Pear) f1;
        System.out.println(x.color);
    }
}
