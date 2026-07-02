import java.util.HashMap;
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
class Solution {
    public Node copyRandomList(Node head) {
        if(head==null) return null;
        Node curr=head;
        HashMap<Node, Node> map = new HashMap<>();

        // STEP1: create new list with same values
        while(curr!=null){    
            Node newNode=new Node(curr.val);
            newNode.next=curr.next;
            curr.next=newNode;
            curr=newNode.next;
        }

        // STEP2: copy the random pointer
        curr=head;
        while(curr!=null){
            if(curr.random!=null){
                curr.next.random=curr.random.next;
            }
            curr=curr.next.next;
        }

        // STEP3: separate two lists
        curr=head;
        Node newHead=head.next;
        Node newCurr=newHead;
        while(curr!=null){
            curr.next=newCurr.next;
            curr=curr.next;
            if(curr!=null){
                newCurr.next=curr.next;
                newCurr=newCurr.next;
            }
        }
        return newHead;
    }
}
public class CopyRandomList {
    static void printList(Node head) {
        Node curr = head;
        while (curr != null) {
            int randomVal;
            if (curr.random != null) {
                randomVal = curr.random.val;
            }
            else {
                randomVal = -1;
            }
            System.out.println("Node Value : " + curr.val +"  Random Points To : " + randomVal);
            curr = curr.next;
        }
    }

public static void main(String[] args) {
    // Creating nodes
    Node n1 = new Node(4);
    Node n2 = new Node(8);
    Node n3 = new Node(15);
    Node n4 = new Node(16);

    // Next connections
    n1.next = n2;
    n2.next = n3;
    n3.next = n4;

    // Random connections based on image
    n1.random = n4;
    n2.random = n3;
    n3.random = null;
    n4.random = n1;

    System.out.println("Original List:");
    printList(n1);

    Solution obj = new Solution();
    Node copiedHead = obj.copyRandomList(n1);
    System.out.println();

    System.out.println("Copied List:");
    printList(copiedHead);
    }
}