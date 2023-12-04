public class Pal {

    private boolean isPalindrome(String s) {
        if (s == null || s.length() == 0) return true;
        int left = 0, right = s.length() - 1;
        while (left < right) {
            // 挨个比较左右指针指向的字符是否相等
            if (s.charAt(left) != s.charAt(right)) return false;
            left++; // 移动左指针
            right--; // 移动右指针
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println("Hello World!");
        Pal pal = new Pal();
        boolean result = pal.isPalindrome("abc");
        assert result == false;
        result = pal.isPalindrome("aba");
        assert result == true;
    }
}
