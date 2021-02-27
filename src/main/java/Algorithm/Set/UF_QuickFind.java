package Algorithm.Set;

import static Lib.Base.printArray;

/**
 * Created by Defias on 2017/10/15.
 *
 * 并查集（不相交集合数据结构）  Disjoint Set / Union-find Set
 *
 * quick-find算法
 */


public class UF_QuickFind {

    private int[] ufid;   //存放触点所在连通分量的标识
    private int count;    //连通分量的数量

    public static void main(String[] args) {
        UF_QuickFind uf = new UF_QuickFind(10);

        printArray(uf.ufid);

        System.out.println(uf.connected(0, 6));
        System.out.println(uf.find(6));

        uf.union(0,9);
        uf.union(0,3);
        uf.union(0,6);
        printArray(uf.ufid);
        System.out.println(uf.connected(0, 6));
        System.out.println(uf.find(6));
        System.out.println(uf.count());
    }

    //创建n个元素的并查集（初始时每个元素对应一个连通分量，自身元素作为该分量的标识）
    public UF_QuickFind(int n) {
        if (n<0)
            throw new IllegalArgumentException();
        count = n;
        ufid = new int[n];

        for (int i=0; i<n; i++) {
            ufid[i] = i;
        }
    }


    //查找指定元素所在的连通分量的标识
    public int find(int p) {
        validate(p);
        return ufid[p];
    }


    //连通分量的数量
    public int count() {
        return count;
    }


    //元素p和q是否在同一个连通分量中
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }


    //合并元素p和q所在的两个连通分量为一个
    public void union(int p, int q) {
        int ufidp = find(p);
        int ufidq = find(q);

        if (ufidp == ufidq)  //本就是同一个连通分量无需再合并
            return;

        //遍历这个数组
        for(int i=0; i<ufid.length; ++i) {
            if(ufid[i] == ufidq) {
                ufid[i] = ufidp;
            }
        }

        count--;   //每合并一次连通分量的数量就较少1
    }


    // validate that p is a valid index
    private void validate(int p) {
        int n = ufid.length;
        if (p<0 || p >= n) {
            throw new IndexOutOfBoundsException("index " + p + " is not between 0 and " + (n-1));
        }
    }

}
