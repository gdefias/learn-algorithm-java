package Algorithm.LinkList;

/**
 *
 * @author Defias
 * @date 2017/9/15
 *
 * 整数列表接口
 */
public interface IIntegerList extends IIntegerCollection {
    void add(int location, int val);
    int get(int location);
    void set(int location, int newVal);
    int remove(int location);
    void print();
}
