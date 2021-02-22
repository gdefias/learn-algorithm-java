package Questions.Set;

/**
 * Created by Defias on 2020/06.
 * Description: 并查集
 *
 * quick-union算法
 */

import static Lib.Base.*;

public class UF_QuickUnion {
    private int[] parent;  //存放触点的父触点，根触点的父触点是自身，同一连通分量中的触点有同一个根触点（用根触点标识该分量）
    private int count;    //连通分量的数量

    public static void main(String[] args) {
        UF_QuickUnion uf = new UF_QuickUnion(10);

        printArray(uf.parent);

        System.out.println(uf.connected(0, 6));
        System.out.println(uf.find(6));

        uf.union(9,0);
        uf.union(3,9);
        uf.union(6,3);
        printArray(uf.parent);
        System.out.println(uf.connected(0, 6));
        System.out.println(uf.find(6));
        System.out.println(uf.count());
    }

    //创建n个元素的并查集（初始时每个元素对应一个连通分量，自身元素作为该分量的标识）
    public UF_QuickUnion(int n) {
        if (n<0)
            throw new IllegalArgumentException();
        count = n;
        parent = new int[n];

        for (int i=0; i<n; i++) {
            parent[i] = i;
        }
    }


    //指定元素所在的连通分量的标识
    public int find(int p) {
        validate(p);

        int idp = parent[p];
        while (idp != parent[idp]) {
            idp = parent[idp];
        }
        return idp;
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

        parent[ufidq] = ufidp;
        count--;   //每合并一次连通分量的数量就较少1
    }


    // validate that p is a valid index
    private void validate(int p) {
        int n = parent.length;
        if (p<0 || p >= n) {
            throw new IndexOutOfBoundsException("index " + p + " is not between 0 and " + (n-1));
        }
    }


}
