package Algorithm.Math;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Defias on 2016/4/28.
 *
 * 分解质因数：
 *(1)如果这个质数恰等于n，则说明分解质因数的过程已经结束，打印出即可。
 *(2)如果n>k，但n能被k整除，则应打印出k的值，并用n除以k的商,作为新的正整数你n,重复执行第一步。
 *(3)如果n不能被k整除，则用k+1作为k的值,重复执行第一步。
 */

public class PrimeFactorization {
	public static void main(String[] args) {
		int num = 90;
		Solution2(num);
		System.out.println(result);

	}

	private static List<Integer> result = new ArrayList<>();

	public static void Solution(int num) {
		for (int i=2;i<=num;i++) {
			if (num == i) {
				result.add(i);
				return;
			}
			if (num > i && (num % i == 0)) {
				result.add(i);
				Solution(num / i);
				break;
			}
		}
	}

	public static void Solution2(int num) {
		for (int i=2; i*i<=num; i++) {       //固定找因子的写法
			while (num % i == 0) {
				num = num / i;
				result.add(i);
			}
		}
		if (num != 1) {
			result.add(num);
		}
	}


}
