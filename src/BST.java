public class BST {
    Node root;

    public void insert(int key){
        root = insertRec(root, key);
    }

    private Node insertRec(Node node, int key) {
        if(node == null){
            return new Node(key);
        }

        if(key<node.key){
            node.left = insertRec(node.left, key);
        }
        else if(key>node.key){
            node.right = insertRec(node.right, key);
        }
        return node;
    }

    public boolean search(int key){
        return searchRec(root, key);
    }

    private boolean searchRec(Node root, int key) {
        if(root == null){
            return false;
        }
        if (root.key == key){
            return true;
        }

        if(key<root.key){
            return searchRec(root.left, key);
        }
        else{
            return searchRec(root.right, key);
        }
    }

    public void delete(int key){
        root= deleteRec(root,key);
    }

    private Node deleteRec(Node node, int key) {
        if(node == null){
            return null;
        }

        if(key<node.key){
            node.left = deleteRec(node.left, key);
        }
        else if(key>node.key){
            node.right = deleteRec(node.right, key);
        }
        else {
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }

            Node successor = findMin(node.right);
            node.key = successor.key;
            node.right = deleteRec(successor.right, node.key);
        }

        return node;
    }

    Node findMin(Node node){
        while(node.left != null){
            node = node.left;
        }
        return node;
    }


}
