package Algorithm.Set;

import static Lib.Base.printArray;

/**
 * Created by Defias on 2020/06.
 * Description:  并差集
 *
 * 加权quick-union算法 / 路径压缩的加权quick-union算法
 */

public class UF_WeightedQuickUnion {
    private int[] parent;  //存放触点的父触点，根触点的父触点是自身，同一连通分量中的触点有同一个根触点（用根触点标识该分量）
    private int[] rank;   //秩：各个根结点对应的分量树高度的上界
    private int count;     //连通分量的数量



    public static void main(String[] args) {
        UF_WeightedQuickUnion uf = new UF_WeightedQuickUnion(10);

        printArray(uf.parent);
        printArray(uf.rank);

        System.out.println(uf.connected(8, 9));
        System.out.println(uf.find(9));

        uf.union(0,9);
        uf.union(1,8);
        uf.union(1,0);
        printArray(uf.parent);
        printArray(uf.rank);
        System.out.println(uf.connected(8, 9));
        System.out.println(uf.find(9));
        System.out.println(uf.count());
    }

    //创建n个元素的并查集（初始时每个元素对应一个连通分量，自身元素作为该分量的标识）
    public UF_WeightedQuickUnion(int n) {
        if (n<0)
            throw new IllegalArgumentException();
        count = n;
        parent = new int[n];
        rank = new int[n];

        for (int i=0; i<n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }


    //查询指定元素所在的连通分量的标识（根元素）
    public int find(int p) {
        validate(p);

        int idp = parent[p];
        while (idp != parent[idp]) {
            idp = parent[idp];
        }
        return idp;
    }


    //查询指定元素所在的连通分量的标识（根元素）并进行路径压缩
    public int find2(int p) {
        validate(p);
        while (p != parent[p]) {
            parent[p] = parent[parent[p]];    // path compression by halving
            p = parent[p];
        }
        return p;
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
        int rootP = find(p);
        int rootQ = find(q);

        if (rootP == rootQ)  //本就是同一个连通分量无需再合并
            return;

        //使较矮的连通分量指向较高的连通分量 （也可按连通分量包含的结点数量多少来区分大小连通分量）
        if (rank[rootP] < rank[rootQ]) {
            parent[rootP] = rootQ;
        } else if(rank[rootP] > rank[rootQ]) {
            parent[rootQ] = rootP;
        } else {
            parent[rootQ] = rootP;
            rank[rootP]++;
        }
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
