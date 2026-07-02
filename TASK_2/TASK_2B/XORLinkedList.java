class Node {
    int data;
    Node next;
    Node prev;

    Node(int data) {
        this.data = data;
        next = null;
        prev = null;
    }
}

public class XORLinkedList {
    Node head;
    Node tail;
    XORLinkedList() {
        head = null;
        tail = null;
    }

    void insert(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
            return;
        }
        tail.next = newNode;
        newNode.prev = tail;
        tail = newNode;
    }

    void forwardDisplay() {
        Node curr = head;
        System.out.println("Forward Traversal:");
        while (curr != null) {
            System.out.print(curr.data + " ");
            curr = curr.next;
        }
        System.out.println();
    }

    void backwardDisplay() {
        Node curr = tail;
        System.out.println("Backward Traversal:");
        while (curr != null) {
            System.out.print(curr.data + " ");
            curr = curr.prev;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        XORLinkedList list = new XORLinkedList();
        list.insert(10);
        list.insert(20);
        list.insert(30);
        list.insert(40);
        list.insert(50);

        list.forwardDisplay();
        list.backwardDisplay();
    }
}