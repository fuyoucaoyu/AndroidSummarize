package com.justnow.androidsummarize.ds;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class DSTest {

    public DSTest() {
        init();
    }

    private void init() {

    }

    private void testArray() {
        int[] arr = {1, 2, 3, 4, 5};
        int length = arr.length;
    }

    private void testStack() {
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(0); // 添加到栈顶
        Integer top = stack.pop(); // 删除栈顶并返回元素
        int t = stack.peek(); // 获取栈顶元素，但是不删除
        boolean isEmpty = stack.isEmpty(); // 检查是不是空栈
        int index = stack.search(0); // 栈顶到元素的位置
    }

    private void testPriorityQueue() {
        // PriorityQueue 二叉小顶堆实现,优先队列的作用是能保证每次取出的元素都是队列中权值最小的
        // 树中任一非叶结点的关键字均不大于（或不小于）其左右孩子（若存在）结点的关键字。
        // PriorityQueue是非同步的，要实现同步需要调用java.util.concurrent包下的PriorityBlockingQueue类来实现同步
        Queue<Integer> queue = new PriorityQueue<Integer>();
        boolean suc = queue.offer(0); // 队列尾部入列一个元素
        boolean suc1 = queue.add(1); // 队列尾部入列一个元素，会抛异常 NoSuchElementException
        Integer i = queue.poll(); // 把队列的最后一个元素出列
        Integer i1 = queue.remove(); // 把队列的最后一个元素出列 NoSuchElementException
        Integer j = queue.peek(); // 查看队列的第一个元素，但是不出列
        Integer j1 = queue.element(); // 查看队列的第一个元素，NoSuchElementException

        Queue<Integer> priorityQueue = new PriorityQueue<Integer>(16, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
//                return o1 - o2; // 小根堆，也是 priorityQueue 默认的小顶堆
                return o2 - o1; // 重载优先级使其变为大根堆
            }
        });
    }

    private void testDeque() {
        // Deque: double ended queue（双端队列），继承了队列接口Queue
        // 1. 在将双端队列用作队列时，将得到 FIFO（先进先出）行为
        // 2. 用作 LIFO（后进先出）堆栈。应优先使用此接口而不是遗留 Stack 类

        // ArrayDeque 它的内部使用一个数组来保存具体的元素，然后分别使用head, tail来指示队列的头和尾。
        Deque<Integer> deque1 = new ArrayDeque<Integer>();
        // Node的结构。它本身包含元素值，prev、next两个引用。不存在要考虑下标的问题，也不需要扩展数组空间
        Deque<Integer> deque2 = new LinkedList<Integer>();
//        Deque<Integer> deque3 = new ConcurrentLinkedDeque<Integer>();

        deque1.offerFirst(0); // 在头部添加元素，第一个元素
        deque1.addFirst(1); // 第一个原始，抛异常
        int i1 = deque1.pollFirst(); // 删除并抛出第一个值
        int i2 = deque1.removeFirst();
        deque1.peekFirst(); // 查看队列的第一个元素
        deque1.getFirst();
        deque1.offerLast(1); // 在尾部添加元素
        deque1.addLast(1);
        int j1 = deque1.pollLast(); // 删除并抛出队尾元素，最后一个值
        int j2 = deque1.removeLast();
        deque1.peekLast(); // 查看队列的最后一个元素
        deque1.getLast();
    }

    private void testBlockingQueue() {

        /**
              可能报异常 返回布尔值 可能阻塞  设定等待时间
         入队  add(e)  offer(e)  put(e)  offer(e, timeout, unit)
         出队  remove()  poll()  take()  poll(timeout, unit)
         查看  element() peek()  无       无
         */
        BlockingQueue<Integer> arrayBlockingQueue = new ArrayBlockingQueue<Integer>(16, true);
        BlockingQueue<Integer> linkedBlockingQueue = new LinkedBlockingDeque<>(16);
    }

    private void testTree() {
        TreeMap<Integer, String> treeMap = new TreeMap<Integer, String>();
        treeMap.put(1, "test");
        treeMap.putAll(new HashMap<Integer, String>());
        treeMap.get(1);
        treeMap.containsKey(1);
        treeMap.containsValue("test");
        treeMap.isEmpty(); // size == 0
        treeMap.size();
        treeMap.remove(1);
        treeMap.clear();
        // key 在 [0, 1] 之前， true 表示包含
        treeMap.subMap(0, true, 10, true);
    }

    private void testHashMap() {
        HashMap<Integer, String> hashMap = new HashMap<Integer, String>();
        hashMap.put(0, "test");
        hashMap.putAll(new HashMap<Integer, String>());
        hashMap.get(0);
        hashMap.containsKey(0);
        hashMap.containsValue("test");
        hashMap.isEmpty(); // size == 0
        hashMap.size();
        hashMap.remove(0);
        hashMap.clear();

    }
}
