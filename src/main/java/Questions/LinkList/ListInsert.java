package Questions.LinkList;
import Lib.Base.*;
import Lib.Util;

/**
 * Created by Defias on 2016/4/28.
 *
 * 在升序链表中插入一个节点
 */

public class ListInsert {
	public static void main(String[] args) {
		ListNode root1 = Util.makeLinkedList();

		ListNode root = insertNode(root1, 6);
		Util.printLinkedList(root);
	}


	public static ListNode insertNode(ListNode head, int val) {
		ListNode node = new ListNode(val);
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		head = dummy;

		// find the last element <= val
		while (head.next != null && head.next.val <= val) {
			head = head.next;
		}
		node.next = head.next;
		head.next = node;

		return dummy.next;
	}
}


