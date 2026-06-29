
class Node {
    int data;
    int height;
    Node left;
    Node right;

    Node(int data) {
        this.data = data;
        this.height = 1;
        left = null;
        right = null;
    }
}

public class Avl {
    Node root;
    Avl() {
        root = null;
    }
    int height(Node node) {
        if (node == null)
            return 0;
        return node.height;
    }

    int balFact(Node node) {
        if (node == null)
            return 0;
        return height(node.left) - height(node.right);
    }

    void insert(int data) {
        root = insert(root, data);
    }

    Node insert(Node curr, int data) {
        if (curr == null) {
            return new Node(data);
        }

        if (data < curr.data) {
            curr.left = insert(curr.left, data);
        }

        else if (data > curr.data) {
            curr.right = insert(curr.right, data);
        }

        else {
            return curr;
        }

        curr.height = 1 + Math.max(height(curr.left),height(curr.right));
        int bal = balFact(curr);

        //LL Rotation
        if (bal > 1 &&  data < curr.left.data) {
            return rightRotate(curr);
        }

        //RR Rotation
        if (bal < -1 &&  data > curr.right.data) {
            return leftRotate(curr);
        }

        //LR Rotation
        if (bal > 1 &&
                data > curr.left.data) {
            curr.left = leftRotate(curr.left);
            return rightRotate(curr);
        }

        //RL Rotation
        if (bal < -1 &&  data < curr.right.data) {
                curr.right = rightRotate(curr.right);
            return leftRotate(curr);
        }
        return curr;
    }

    Node leftRotate(Node oldRoot) {
        Node newRoot = oldRoot.right;
        Node subTree = newRoot.left;
        newRoot.left = oldRoot;
        oldRoot.right = subTree;
        oldRoot.height =1 + Math.max(height(oldRoot.left), height(oldRoot.right));
        newRoot.height = 1 + Math.max(height(newRoot.left),height(newRoot.right));
        return newRoot;
    }

    Node rightRotate(Node oldRoot) {
        Node newRoot = oldRoot.left;
        Node subTree = newRoot.right;
        newRoot.right = oldRoot;
        oldRoot.left = subTree;
        oldRoot.height = 1 + Math.max(height(oldRoot.left), height(oldRoot.right));
        newRoot.height = 1 + Math.max( height(newRoot.left), height(newRoot.right));
        return newRoot;
    }

    void delete(int data) {
        root = delete(root, data);
    }

    Node minValueNode(Node node) {
        Node curr = node;
        while (curr.left != null) {
            curr = curr.left;
        }
        return curr;
    }

    Node delete(Node curr, int data) {
        if (curr == null) {
            return curr;
        }
        if (data < curr.data) {
            curr.left = delete(curr.left, data);
        }

        else if (data > curr.data) {
            curr.right = delete(curr.right, data);
        }
        else {
            if (curr.left == null || curr.right == null) {
                Node temp;
                if (curr.left != null)
                    temp = curr.left;
                else
                    temp = curr.right;
                if (temp == null) {
                    curr = null;
                }
                else {
                    curr = temp;
                }
            }

            else {
                Node temp = minValueNode(curr.right);
                curr.data = temp.data;
                curr.right = delete( curr.right, temp.data);
            }
        }

        if (curr == null) {
            return curr;
        }

        curr.height = 1 + Math.max( height(curr.left), height(curr.right));

        int bal = balFact(curr);

        // LL
        if (bal > 1 &&  balFact(curr.left) >= 0) {
            return rightRotate(curr);
        }

        //RR
        if (bal < -1 && balFact(curr.right) <= 0) {
            return leftRotate(curr);
        }

        //LR
        if (bal > 1 && balFact(curr.left) < 0) {
            curr.left = leftRotate(curr.left);
            return rightRotate(curr);
        }

        //RL
        if (bal < -1 &&  balFact(curr.right) > 0) {
            curr.right = rightRotate(curr.right);
            return leftRotate(curr);
        }
        return curr;
    }

    void display() {
        inorder(root);
        System.out.println();
    }

    void inorder(Node root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        System.out.print(root.data + " ");
        inorder(root.right);
    }

    public static void main(String[] args) {
        Avl tree = new Avl();

        tree.insert(10);
        tree.insert(20);
        tree.insert(30);
        tree.insert(40);
        tree.insert(50);
        tree.insert(25);

        System.out.println( "AVL Tree After Insertion:");

        tree.display();

        tree.delete(40);

        System.out.println("AVL Tree After Deleting 40:");
        tree.display();

        tree.delete(30);
        System.out.println("AVL Tree After Deleting 30:");
        tree.display();
    }
}