package zh_convert;

import com.spreada.utils.chinese.ZHConverter;

public class Main {
    public static void main(String[] args) {
        ZHConverter converter = ZHConverter.getInstance(ZHConverter.SIMPLIFIED);
        System.out.println(converter.convert("簡體簡體"));
        converter = ZHConverter.getInstance(ZHConverter.TRADITIONAL);
        System.out.println(converter.convert("简体字"));
    }
}
