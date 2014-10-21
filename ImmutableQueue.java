package jp.co.worksap.global;

import java.util.NoSuchElementException;

public class ImmutableQueue<E> {
	// ����ͷָ��
	private Node<E> head;
	// ����βָ��
	private Node<E> tail;

	/**
	 * Ĭ���޲ι��캯��
	 */
	public ImmutableQueue() {
	}

	/**
	 * ��Ԫ�ع��캯����ֻ��һ��Ԫ��ʱ��ͷָ�����βָ��
	 * 
	 * @param e
	 */
	public ImmutableQueue(E e) {
		if (e == null) {
			throw new IllegalArgumentException();
		}
		// �½��ڵ㣬ͷָ�롢βָ�붼ָ����
		head = new Node<E>(e, null, null);
		tail = head;
	}

	/**
	 * �Զ���ʵ��Ϊ�����Ĺ��캯��������һ��Ԫ����ȫ��ͬ�Ķ���
	 * 
	 * @param queue
	 */
	public ImmutableQueue(ImmutableQueue<E> queue) {
		if (queue == null) {
			throw new IllegalArgumentException();
		}
		Node<E> tmp = queue.head;
		// ������������queue�������½��ڵ㣬����������
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
	 * ����У����ز�������¶��У�ԭ���в���
	 * 
	 * @param e
	 * @return
	 */
	public ImmutableQueue<E> enqueue(E e) {
		if (e == null)
			throw new IllegalArgumentException();
		ImmutableQueue<E> queue = new ImmutableQueue<E>(this);
		//�ն������½�ͷ���
		if (queue.head == null) {
			Node<E> node = new Node<E>(e, null, null);
			queue.head = node;
			queue.tail = node;
		}//������β������ڵ�
		else {
			Node<E> node = new Node<E>(e, queue.tail, null);
			queue.tail.next = node;
			queue.tail = node;
		}
		return queue;
	}

	/**
	 * �����У����ز�������¶��У�ԭ���в���
	 * 
	 * @return
	 */
	public ImmutableQueue<E> dequeue() {
		if (head == null)
			throw new NoSuchElementException();
		ImmutableQueue<E> queue = new ImmutableQueue<E>(this);
		Node<E> tmp = queue.head;
		// ��ֻ��һ���ڵ㣬���ͷָ�롢βָ������Ϊ��
		if (tmp.next == null) {
			queue.head = null;
			queue.tail = null;
		} // ����ɾ��ͷ���
		else {
			tmp.next.pre = null;
			queue.head = tmp.next;
			tmp = null;
		}
		return queue;
	}

	/**
	 * �鿴���ж���Ԫ��
	 * @return
	 */
	public E peek() {
		if (head == null)
			throw new NoSuchElementException();
		else
			return head.element;
	}

	/**
	 * ���ض��г���
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
 * ����ڵ��࣬����Ԫ��element��ǰָ��ͺ�ָ��
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
