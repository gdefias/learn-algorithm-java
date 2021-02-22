package Questions.Graph;

/**
 * Created by Defias on 2020/06.
 * Description:  加权边
 */

public class Edge implements Comparable<Edge> {

    private final int v;  //边的一个端点
    private final int w;  //边的另一个端点
    private final double weight;  //权重


    public static void main(String[] args) {
        Edge e = new Edge(12, 34, 5.67);
        System.out.println(e);
    }

    public Edge(int v, int w, double weight) {
        if (v < 0)
            throw new IndexOutOfBoundsException("Vertex name must be a nonnegative integer");
        if (w < 0)
            throw new IndexOutOfBoundsException("Vertex name must be a nonnegative integer");
        if (Double.isNaN(weight))
            throw new IllegalArgumentException("Weight is NaN");
        this.v = v;
        this.w = w;
        this.weight = weight;
    }


    //边的权重
    public double weight() {
        return weight;
    }


    //边的一个端点
    public int either() {
        return v;
    }


    //返回指定端点的边的另一个端点
    public int other(int vertex) {
        if(vertex == v)
            return w;
        else if (vertex == w)
            return v;
        else
            throw new IllegalArgumentException("Illegal endpoint");
    }


    //边的权重比较
    @Override
    public int compareTo(Edge that) {
        return Double.compare(this.weight, that.weight);
    }


    //边的字符串表示
    public String toString() {
        return String.format("%d-%d %.5f", v, w, weight);
    }
}