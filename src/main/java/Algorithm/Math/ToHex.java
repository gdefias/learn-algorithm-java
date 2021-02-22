package Questions.Math;

/**
 * Created by Defias on 2020/07.
 * Description: 数字转换为十六进制数
 *
 给定一个整数，编写一个算法将这个数转换为十六进制数。对于负整数，我们通常使用 补码运算 方法。

 注意:
 十六进制中所有字母(a-f)都必须是小写。
 十六进制字符串中不能包含多余的前导零。如果要转化的数为0，那么以单个字符'0'来表示；对于其他情况，十六进制字符串中的第一个字符将不会是0字符。 
 给定的数确保在32位有符号整数范围内。
 不能使用任何由库提供的将数字直接转换或格式化为十六进制的方法。

 输入:
 26
 输出:
 "1a"


 输入:
 -1
 输出:
 "ffffffff"
 */

public class ToHex {
    public static void main(String[] args) {
        ToHex O = new ToHex();
        System.out.println(O.toHex(-1));
    }

    public String toHex(int num) {
        String bases = "0123456789abcdef";
        StringBuilder res = new StringBuilder();

        while(num!=0) {
            int item = num & 15;
            res.append(bases.charAt(item));
            num = num >>> 4;
        }

        if(res.length()==0) {
            return "0";
        }

        return res.reverse().toString();
    }
}
