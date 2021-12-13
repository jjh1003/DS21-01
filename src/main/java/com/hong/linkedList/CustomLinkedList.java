package com.hong.linkedList;

import lombok.Data;

/**
 * 自定义链表
 */
@Data
public class CustomLinkedList {

    private Node top;

    @Data
    class Node{
        public Node(int data) {
            this.data = data;
        }
        private int data;
        private Node next;
    }

    /**
     * 将节点插入到链表的头部
     * @param data 数据
     * @return 返回值
     */
    public boolean insertHead(int data) {
        Node node = new Node(data);
        node.next = top;
        top = node;
        return true;
    }

    /**
     * 将节点插入到链表的尾部
     * @param data 数据
     * @return 返回值
     */
    public boolean insertLast(int data) {
        if (top==null) {
            return false;
        }
        Node item = top;
        while (item.next!=null){
            item = item.next;
        }
        item.next = new Node(data);
        return true;
    }

    /**
     * 根据index插入节点到链表
     * @param index 节点索引
     * @param data 数据
     * @return 返回值
     */
    public boolean insert(int index, int data) {
        Node node = new Node(data);
        if (index==0) {
            node.next = top;
            top = node;
            return true;
        }
        if (top==null) {
            return false;
        }
        Node item = top;
        for (int i = 1; i < index; i++) {
            item = item.next;
            if (item==null) {
                return false;
            }
        }
        node.next = item.next;
        item.next = node;
        return true;
    }

    /**
     * 删除头部节点
     * @return 返回值
     */
    public int deleteHead() {
        if (top==null){
            return -1;
        }
        top = top.next;
        return 1;
    }

    /**
     * 删除尾部节点
     * @return 返回值
     */
    public int deleteLast() {
        if (top==null){
            return -1;
        }
        if (top.next==null){
            top=null;
        }
        Node lastNode = top;
        Node item = lastNode;
        while (lastNode.next!=null) {
            item = lastNode;
            lastNode = lastNode.next;
        }
        item.next = null;
        return 1;
    }

    /**
     * 根据索引删除节点
     * @param index 索引
     * @return 返回值
     */
    public int deleteByIndex(int index) {
        if (top==null) {
            return -1;
        }
        if (index==0) {
            top = top.next;
            return 1;
        }
        Node currentNode = top;
        Node item = currentNode;
        for (int i = 1; i <= index; i++) {
            item = currentNode;
            currentNode = currentNode.next;
            if (currentNode==null) {
                return -1;
            }
        }
        item.next = currentNode.next;
        return 1;
    }

    /**
     * 删除node节点
     * @param data 数据
     * @return 返回值
     */
    public int delete(int data) {
        if (top==null) {
            return -1;
        }
        int flag = -1;
        Node item = top;
        Node parent = null;
        while (item != null) {
            if (item.data==data) {
                flag = 1;
                if (parent != null) {
                    parent.next = item.next;
                } else {
                    top = item.next;
                }
            } else {
                parent = item;
            }
            item = item.next;
        }
        return flag;
    }

    /**
     * 获取链表头部数据
     * @return 返回值
     */
    public Integer getHead() {
        if (top==null) {
            return null;
        }
        return top.data;
    }

    /**
     * 获取链表尾部数据
     * @return 返回值
     */
    public Integer getLast() {
        if (top==null) {
            return null;
        }
        Node item = top;
        while (item.next!=null){
            item = item.next;
        }
        return item.data;
    }

    /**
     * 根据索引查找数据
     * @param index 索引
     * @return 返回值
     */
    public Integer get(int index) {
        if (top==null){
            return null;
        }
        Node item = top;
        for (int i = 0; i < index; i++) {
            if (item.next==null) {
                return null;
            }
            item = item.next;
        }
        return item.data;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        Node item = top;
        while (item != null) {
            str.append(item.data).append("  ");
            item = item.next;
        }
        return str.toString();
    }

    public static void main(String[] args) {
        CustomLinkedList linkedList = new CustomLinkedList();
        System.out.println("-------初始化-------");
        linkedList.insertHead(1);
        linkedList.insertHead(2);
        linkedList.insertHead(3);
        System.out.println(linkedList);
        System.out.println("-------插入头部-------");
        System.out.println(linkedList.insertHead(5));
        System.out.println(linkedList);
        System.out.println("-------插入尾部-------");
        System.out.println(linkedList.insertLast(6));
        System.out.println(linkedList);
        int index = 2;
        System.out.println("-------插入中间-------index="+index);
        System.out.println(linkedList.insert(index, 7));
        System.out.println(linkedList);
        System.out.println("-------获取头部-------");
        System.out.println(linkedList.getHead());
        System.out.println("-------获取尾部-------");
        System.out.println(linkedList.getLast());
        System.out.println("-------获取中间-------index="+index);
        System.out.println(linkedList.get(index));
        System.out.println("-------初始化-------");
        linkedList.insertHead(6);
        linkedList.insertHead(2);
        linkedList.insertHead(7);
        linkedList.insertHead(1);
        linkedList.insertHead(4);
        linkedList.insertHead(9);
        linkedList.insertHead(6);
        System.out.println(linkedList);
        System.out.println("-------删除头部-------");
        System.out.println(linkedList.deleteHead());
        System.out.println(linkedList);
        System.out.println("-------删除尾部-------");
        System.out.println(linkedList.deleteLast());
        System.out.println(linkedList);
        System.out.println("-------删除中间-------index="+index);
        System.out.println(linkedList.deleteByIndex(index));
        System.out.println(linkedList);
        int data = 2;
        System.out.println("-------删除中间-------data=" + data);
        System.out.println(linkedList.delete(data));
        System.out.println(linkedList);
        System.out.println("-------异常测试-------");
        int eIndex = 10;
        System.out.println("-------插入数据-------index="+eIndex);
        System.out.println(linkedList.insert(eIndex,10));
        System.out.println("-------获取数据-------index="+eIndex);
        System.out.println(linkedList.get(eIndex));
        System.out.println("-------删除数据-------index="+eIndex);
        System.out.println(linkedList.deleteByIndex(eIndex));
        int eData = 12;
        System.out.println("-------删除数据-------data="+eData);
        System.out.println(linkedList.delete(eData));

    }

}
