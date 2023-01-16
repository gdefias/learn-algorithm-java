package Algorithm.String;
import java.util.regex.*;
/**
 * Created by Defias on 2016/4/28.

 有效回文串

 https://leetcode-cn.com/problems/valid-palindrome/

 给定一个字符串，判断其是否为一个回文串。只包含字母和数字，忽略大小写。
 说明：本题中，将空字符串定义为有效的回文串。

 输入: "A man, a plan, a canal: Panama"
 输出: true

 输入: "race a car"
 输出: false
 */

public class IsPalindrome {
	public static void main(String[] args) {
//		String s = "A man, a plan, a canal: Panamas";
		String s= ".,";
		System.out.println(isPalindrome(s));

        //System.out.println("Is moon a palindrome? " + isPalindrome2("moon"));
        //System.out.println("Is noon a palindrome? " + isPalindrome2("noon"));
        //System.out.println("Is a a palindrome? " + isPalindrome2("a"));
        //System.out.println("Is aba a palindrome? " + isPalindrome2("aba"));
        //System.out.println("Is ab a palindrome? " + isPalindrome2("ab"));
	}

	//方法1： 首尾双指针
	public static boolean isPalindrome(String s) {
		if(s==null || s.length() == 0) {
			return true;
		}

		int start = 0;
		int end = s.length()-1;

		while(start < end) {
			while(start<s.length() && !Character.isLetterOrDigit(s.charAt(start)))
				start++;

			if(start == s.length()) {
				return true;
			}

			while(end >= 0 && !Character.isLetterOrDigit(s.charAt(end)))
				end--;

			if(Character.toLowerCase(s.charAt(start)) != Character.toLowerCase(s.charAt(end))) {
				return start>end;
			} else {
				start++;
				end--;
			}
		}
		return true;
	}


	//方法2： 筛选 + 判断
	public static boolean isPalindrome2(String s) {
		StringBuffer sgood = new StringBuffer();
		int length = s.length();
		for (int i = 0; i < length; i++) {
			char ch = s.charAt(i);
			if (Character.isLetterOrDigit(ch)) {
				sgood.append(Character.toLowerCase(ch));
			}
		}
		StringBuffer sgood_rev = new StringBuffer(sgood).reverse();  //反转字符串
		return sgood.toString().equals(sgood_rev.toString());
	}



	//方法3：笨方法，先过滤，然后首尾比较
	public static boolean isPalindrome3(String s) {
		int slen = s.length();
		StringBuffer sal = new StringBuffer();
		String regEx = "[0-9A-Za-z]";
		Pattern p = Pattern.compile(regEx);

		for(int j=0; j<s.length(); j++) {
			String ssub = s.substring(j,j+1);
			if(p.matcher(ssub).matches()) {
				sal.append(s.charAt(j));
			}
		}

		char befor, after;
		int len = sal.length();
		for(int i=0; i<len/2; i++) {
			befor = sal.charAt(i);
			after = sal.charAt(len-i-1);
			if((befor != after) && (Math.abs(befor-after) != 32))
				return false;
		}
		return true;
	}

}
