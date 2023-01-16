package Algorithm.HashTable;
import static Lib.Base.*;
/**
 * Created by Defias on 2016/4/25.

 自定义hash表（哈希集合）

 数组+单链表  链地址法
 */


public class MyHashTable {
	private ListNode[] st;
	private int n=0;   //键的数量
	private int size;  //链表条数/数组长度
	public static final int DEFAULT_SIZE=769;   //链表的默认条数

	public static void main(String[] args) {
		MyHashTable hashtable = new MyHashTable();

		int[] vals = {1,770,34,56,54,66,779,18,9,10};
		for(int i=0; i<vals.length; i++)
			hashtable.add(vals[i]);  //向哈希表中添加元素

		System.out.println("size: " + hashtable.size());
		System.out.println("n: " + hashtable.count());

		System.out.println(hashtable.contains(56));
		System.out.println(hashtable.contains(54));

		hashtable.remove(56);   //从哈希表中删除一个元素
		System.out.println("n: " + hashtable.count());

		System.out.println(hashtable.contains(56));
		System.out.println(hashtable.contains(54));
	}


	public MyHashTable() {
		this.size = DEFAULT_SIZE;
		st = new ListNode[size];
		initTable();
	}

	public MyHashTable(int size) {
		this.size = size;
		st = new ListNode[size];
		initTable();

	}

	private void initTable() {
		for (int i=0; i<size; ++i) {
			st[i] = null;
		}
	}

	//获取元素数量
	public int count() {
		return n;
	}

	//获取链表数量
	public int size() {
		return size;
	}

	//哈希函数：获得对象的索引位置
	public int Hash(int val){
		return val%size;
	}

	//哈希函数
	public int Hash2(int val) {
		return getHashCode((Object) val) & (size-1);
	}

	//获得一个对象的哈希码
	public int getHashCode(Object o){
		int h = o.hashCode();
		h += ~(h << 9);
		h ^=  (h >>> 14);
		h +=  (h << 4);
		h ^=  (h >>> 10);
		return h;
	}



	//添加元素
	public void add(int val){
		int index = Hash2(val);
		//System.out.println("index:"+index+" value:"+val);

		ListNode newNode = new ListNode(val);
		ListNode p = st[index];

		if(p==null){
			st[index]=newNode;
		} else {  //解决哈希冲突
			ListNode nextp;
			while((p.val != val) && (nextp=p.next)!=null) {
				p = nextp;
			}

			//不允许加入重复元素
			if(p.val == val) {
				return;
			}
			p.next = newNode;
		}
		n++;
	}

	//哈希表中是否包含指定的元素
	public boolean contains(int val){
		int index = Hash2(val);

		ListNode p = st[index];
		while(p!=null) {
			if(p.val == val) {
				return true;
			} else if(p.next!=null) {
				p = p.next;
			} else {
				return false;
			}
		}
		return false;
	}

	//删除哈希表中的指定元素
	public void remove(int val){
		int index = Hash2(val);

		ListNode p = st[index];

		if (p!=null && p.val==val) {
			st[index] = p.next;
			n--;
			return;
		} else if(p==null || p.next==null) {
			return;
		}

		ListNode prefixp = p;
		p = p.next;
		while (p!=null) {
			if(p.val == val) {
				prefixp.next = p.next;
				n--;
				return;
			} else if (p.next!=null) {
				prefixp = p;
				p = p.next;
			} else {
				return;
			}
		}
	}
}