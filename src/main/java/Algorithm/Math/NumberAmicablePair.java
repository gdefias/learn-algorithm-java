package Questions.Math;
import java.util.*;
/**
 * Created by Defias on 2016/4/28.
 *
 *
 * 相亲数
 * 一对整数是相亲数是说他们各自的所有有效因子（除了自己以外的因子）之和等于另外一个数。比如(220, 284)就是一对相亲数。因为：
 * 220的所有因子：1+2+4+5+10+11+20+22+44+55+110 = 284
 * 284的所有因子：1+2+4+71+142 = 220
 *
 * 给出整数k，求1~k之间的所有相亲数对
 */

public class NumberAmicablePair {
	public static void main(String[] args) {
		int k = 1001;
		System.out.println(Solution2(k));
	}

	public static List<List<Integer>> Solution(int k) {
		List<List<Integer>> results = new ArrayList<>();

		for(int i=1; i<=k; i++) {
			int other = DivisorSum(i);
			if(other!=i && other>1 && other<=k) {
				if(DivisorSum(other) == i) {
					List<Integer> result = new ArrayList<>();
					List<Integer> testresult = new ArrayList<>();
					result.add(i);
					result.add(other);
					testresult.add(other);
					testresult.add(i);
					if(!results.contains(testresult))
						results.add(result);
				}
			}
		}
		return results;
	}

	//因子和
	public static int DivisorSum(int num) {
		int sum = 0;
		for(int i=1; i<=num/2; i++) {
			if(num%i == 0) {
				sum = sum + i;  //低效  循环次数高  一次找一个因子
			}
		}
		return sum;
	}

	//有效的方法
	public static List<List<Integer>> Solution2(int k) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		for (int i = 2; i <= k; ++i) {
			int amicable = factorSum(i);
			if (amicable <= i || amicable > k) {
				continue;
			}
			if (factorSum(amicable) == i) {
				ArrayList<Integer> pair = new ArrayList<Integer>();
				pair.add(i);
				pair.add(amicable);
				result.add(pair);
			}
		}
		return result;

	}

	//因子和
	public static int factorSum(int n) {
		int sum = 1, i;
		for (i=2; i*i < n; ++i) {
			if (n % i == 0) {
				sum += i + n/i;  //高效  循环的次数减少了  一次找两个因子
			}
		}
		if (i*i == n) {  //两个因子是同一个数的情况
			sum += i;
		}
		return sum;
	}


}
