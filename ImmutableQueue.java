package jp.co.worksap.global;

import java.util.NoSuchElementException;

public class ImmutableQueue<E> {
	// 队列头指针
	private Node<E> head;
	// 队列尾指针
	private Node<E> tail;

	/**
	 * 默认无参构造函数
	 */
	public ImmutableQueue() {
	}

	/**
	 * 单元素构造函数，只有一个元素时，头指针等于尾指针
	 * 
	 * @param e
	 */
	public ImmutableQueue(E e) {
		if (e == null) {
			throw new IllegalArgumentException();
		}
		// 新建节点，头指针、尾指针都指向它
		head = new Node<E>(e, null, null);
		tail = head;
	}

	/**
	 * 以队列实例为参数的构造函数，复制一个元素完全相同的队列
	 * 
	 * @param queue
	 */
	public ImmutableQueue(ImmutableQueue<E> queue) {
		if (queue == null) {
			throw new IllegalArgumentException();
		}
		Node<E> tmp = queue.head;
		// 遍历参数队列queue，依次新建节点，并连接起来
		for (; tmp != null; tmp = tmp.next) {
			if (head == null) {
				head = new Node<E>(tmp.element, null, null);
				tail = head;
			} else {
				Node<E> node = new Node<E>(tmp.element, tail, null);
				tail.next = node;
				tail = node;
			}
		}
	}

	/**
	 * 入队列，返回操作后的新队列，原队列不变
	 * 
	 * @param e
	 * @return
	 */
	public ImmutableQueue<E> enqueue(E e) {
		if (e == null)
			throw new IllegalArgumentException();
		ImmutableQueue<E> queue = new ImmutableQueue<E>(this);
		//空队列则新建头结点
		if (queue.head == null) {
			Node<E> node = new Node<E>(e, null, null);
			queue.head = node;
			queue.tail = node;
		}//否则在尾部加入节点
		else {
			Node<E> node = new Node<E>(e, queue.tail, null);
			queue.tail.next = node;
			queue.tail = node;
		}
		return queue;
	}

	/**
	 * 出队列，返回操作后的新队列，原队列不变
	 * 
	 * @return
	 */
	public ImmutableQueue<E> dequeue() {
		if (head == null)
			throw new NoSuchElementException();
		ImmutableQueue<E> queue = new ImmutableQueue<E>(this);
		Node<E> tmp = queue.head;
		// 若只有一个节点，则把头指针、尾指针设置为空
		if (tmp.next == null) {
			queue.head = null;
			queue.tail = null;
		} // 否则删除头结点
		else {
			tmp.next.pre = null;
			queue.head = tmp.next;
			tmp = null;
		}
		return queue;
	}

	/**
	 * 查看队列顶部元素
	 * @return
	 */
	public E peek() {
		if (head == null)
			throw new NoSuchElementException();
		else
			return head.element;
	}

	/**
	 * 返回队列长度
	 * @return
	 */
	public int size() {
		int size = 0;
		Node<E> tmp = head;
		for (; tmp != null; tmp = tmp.next) {
			size++;
		}
		return size;
	}

}

/**
 * 定义节点类，包含元素element、前指针和后指针
 * 
 * @author hangyang
 * 
 * @param <E>
 */
class Node<E> {
	E element;
	Node<E> pre;
	Node<E> next;

	public Node() {
	};

	public Node(E element, Node<E> pre, Node<E> next) {
		this.element = element;
		this.pre = pre;
		this.next = next;
	};
}
