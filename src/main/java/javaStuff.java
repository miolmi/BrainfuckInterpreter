public class javaStuff {
    public static void main(String[] args) {

        var code = "+-><>ABC";
        var sinCount = 0;
        var chars = code.toCharArray();
        for(int i = 0;i<chars.length;i++) {
            if (sinCount>=0) {
                if (chars[i] == '[') {
                    sinCount++;
                }
                if (chars[i] == ']') {
                    sinCount--;
                }
            }
        }
    }
}
