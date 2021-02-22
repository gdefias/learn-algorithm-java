package Questions.Graph.SP;

/**
 * Created by Defias on 2020/06.
 * Description:  加权有向边
 */

public class DirectedEdge {
    private final int v;
    private final int w;
    private final double weight;   //权重


    public static void main(String[] args) {
        DirectedEdge e = new DirectedEdge(12, 34, 5.67);
        System.out.println(e);
    }


    public DirectedEdge(int v, int w, double weight) {
        if (v < 0)
            throw new IndexOutOfBoundsException("Vertex names must be nonnegative integers");
        if (w < 0)
            throw new IndexOutOfBoundsException("Vertex names must be nonnegative integers");
        if (Double.isNaN(weight))
            throw new IllegalArgumentException("Weight is NaN");
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

   //这条边的顶点
    public int from() {
        return v;
    }


    //这条边指向的顶点
    public int to() {
        return w;
    }


    //这条边的权重
    public double weight() {
        return weight;
    }


    public String toString() {
        return v + "->" + w + " " + String.format("%5.2f", weight);
    }
}
