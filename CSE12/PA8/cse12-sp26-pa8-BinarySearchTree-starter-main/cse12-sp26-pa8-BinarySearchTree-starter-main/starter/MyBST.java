/*
 * Name: Connor Gibson
 * Email: cogibosn@ucsd.edu
 * PID: A18677169
 *
 * IMPORTANT: Do not change the method headers. Your tests will be run against
 * good and bad implementations of Student/Course/Sanctuary. You will only
 * receive points if your test passes when run on a good implementation and
 * fails for the corresponding bad implementation.
 */
import java.util.ArrayList;

/**
* This class makes a binary search tree object,
* extending compareable with a key and value
*/
public class MyBST<K extends Comparable<K>, V> {
    MyBSTNode<K, V> root = null;
    int size = 0;
    /**
    * This method returns the size of the BST
    * 
    * @return An integer representing the amount 
    * of elements in the BST
    */  
    public int size() {
        return size;
    }
    /**
    * This method inserts a key,value pair into 
    * the bst
    * 
    * @param key The key to search the bst for 
    * @param value The respective value to each key
    * @return If the key already exists, return the 
    * original value of that key, else return null
    */
    public V insert(K key, V value) {
        // TODO
        if (key == null){
            throw new NullPointerException();
        }
        if (root == null){
            root = new MyBSTNode<K, V>(key, value, null);
            size++;
            return null;
        }
        MyBSTNode<K, V> current = root;
        MyBSTNode<K, V> parent = null;
        while (current != null){
            parent = current;
            int keyInteger = key.compareTo(current.getKey());

            if (keyInteger < 0) {
                current = current.getLeft();
            } else if (keyInteger > 0) {
                current = current.getRight();
            } else {
                V originalValue = current.getValue();
                current.setValue(value);
                return originalValue;
            }
        }
        MyBSTNode<K, V> newNode = new MyBSTNode<>(key, value, parent);
        if (key.compareTo(parent.getKey()) < 0) {
            parent.setLeft(newNode);
        } else {
            parent.setRight(newNode);
        }

        size++;
        return null;
    }
    /**
    * Searches the bst for the given key and returns its value
    *
    * @param key The key to search for in the bst
    * @return The value associated with the key, or null if not found
    */
    public V search(K key) {
        // TODO
        if (key == null){
            return null;
        }
        MyBSTNode<K, V> current = root;
        while (current != null){
            int equal = key.compareTo(current.getKey());
            if (equal == 0){
                return current.getValue();
            } else if (equal < 0){
                current = current.getLeft();
            } else {
                current = current.getRight();
            }
        }
        return null;
    }
    /**
     * Removes the node with the given key from the bst
     *
     * @param key The key of the node to remove
     * @return The value previously associated with the key, or null if not found
     */
    public V remove(K key) {
        if (key == null) return null;

        MyBSTNode<K, V> curr = root;
        MyBSTNode<K, V> parent = null;

        while (curr != null) {
            int cmp = key.compareTo(curr.getKey());
            if (cmp < 0) {
                parent = curr;
                curr = curr.getLeft();
            } else if (cmp > 0) {
                parent = curr;
                curr = curr.getRight();
            } else {
                break;
            }
        }

        if (curr == null) return null;

        V oldValue = curr.getValue();

        if (curr.getLeft() != null && curr.getRight() != null) {
            MyBSTNode<K, V> succ = curr.successor();

            curr.setKey(succ.getKey());
            curr.setValue(succ.getValue());

            MyBSTNode<K, V> succParent = succ.getParent();
            MyBSTNode<K, V> succChild =
                (succ.getLeft() != null) ? succ.getLeft() : succ.getRight();

            if (succChild != null) {
                succChild.setParent(succParent);
            }

            if (succParent.getLeft() == succ) {
                succParent.setLeft(succChild);
            } else {
                succParent.setRight(succChild);
            }

            size--;
            return oldValue;
        }

        MyBSTNode<K, V> child =
            (curr.getLeft() != null) ? curr.getLeft() : curr.getRight();

        if (child != null) child.setParent(parent);

        if (parent == null) {
            root = child;
        } else if (curr == parent.getLeft()) {
            parent.setLeft(child);
        } else {
            parent.setRight(child);
        }

        size--;
        return oldValue;
    }
    /**
     * Returns all nodes whose keys fall within the given range
     *
     * @param low The lower bound of the key range
     * @param high The upper bound of the key range
     * @return An ArrayList of nodes with keys in the specified range
     */
    public ArrayList<MyBSTNode<K, V>> rangeQuery(K low, K high) {
        ArrayList<MyBSTNode<K, V>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        if (low.compareTo(root.getKey()) <= 0) {
            MyBST<K, V> leftTree = new MyBST<>();
            leftTree.root = root.getLeft();
            result.addAll(leftTree.rangeQuery(low, high));
        }

        if (low.compareTo(root.getKey()) <= 0 &&
            high.compareTo(root.getKey()) >= 0) {
            result.add(root);
        }

        if (high.compareTo(root.getKey()) >= 0) {
            MyBST<K, V> rightTree = new MyBST<>();
            rightTree.root = root.getRight();
            result.addAll(rightTree.rangeQuery(low, high));
        }
        return result;
    }
    /**
     * Creates and returns a deep copy of this bst
     *
     * @return A new bst containing the same key,value pairs
     */
    public MyBST<K, V> copy() {
        MyBST<K, V> newTree = new MyBST<>();
        newTree.root = copyNode(this.root, null);
        newTree.size = this.size;
        return newTree;
    }
    /**
     * Recursively copies the given node and its children
     *
     * @param node The node to copy
     * @param parent The parent of the newly copied node
     * @return The root of the copied subtree
     */
    private MyBSTNode<K, V> copyNode(MyBSTNode<K, V> node, 
        MyBSTNode<K, V> parent) {
        if (node == null) return null;

        MyBSTNode<K, V> newNode =
            new MyBSTNode<>(node.getKey(), node.getValue(), parent);

        newNode.setLeft(copyNode(node.getLeft(), newNode));
        newNode.setRight(copyNode(node.getRight(), newNode));

        return newNode;
    }




    /**
     * This class creates individual node objects 
     * to add to the BST
     */
    static class MyBSTNode<K, V> {
        private static final String TEMPLATE = "Key: %s, Value: %s";
        private static final String NULL_STR = "null";

        private K key;
        private V value;
        private MyBSTNode<K, V> parent;
        private MyBSTNode<K, V> left = null;
        private MyBSTNode<K, V> right = null;

        /**
         * Creates a MyBSTNode<K,V> storing specified data
         *
         * @param key    the key the MyBSTNode<K,V> will
         * @param value  the data the MyBSTNode<K,V> will store
         * @param parent the parent of this node
         */
        public MyBSTNode(K key, V value, MyBSTNode<K, V> parent) {
            this.key = key;
            this.value = value;
            this.parent = parent;
        }

        /**
         * Return the key stored in the the MyBSTNode<K,V>
         *
         * @return the key stored in the MyBSTNode<K,V>
         */
        public K getKey() {
            return key;
        }

        /**
         * Set the key stored in the MyBSTNode<K,V>
         *
         * @param newKey the key to be stored
         */
        public void setKey(K newKey) {
            this.key = newKey;
        }

        /**
         * Return data stored in the MyBSTNode<K,V>
         *
         * @return the data stored in the MyBSTNode<K,V>
         */
        public V getValue() {
            return value;
        }

        /**
         * Set the data stored in the MyBSTNode<K,V>
         *
         * @param newValue the data to be stored
         */
        public void setValue(V newValue) {
            this.value = newValue;
        }

        /**
         * Return the parent
         *
         * @return the parent
         */
        public MyBSTNode<K, V> getParent() {
            return parent;
        }

        /**
         * Set the parent
         *
         * @param newParent the parent
         */
        public void setParent(MyBSTNode<K, V> newParent) {
            this.parent = newParent;
        }

        /**
         * Return the left child
         *
         * @return left child
         */
        public MyBSTNode<K, V> getLeft() {
            return left;
        }

        /**
         * Set the left child
         *
         * @param newLeft the new left child
         */
        public void setLeft(MyBSTNode<K, V> newLeft) {
            this.left = newLeft;
        }

        /**
         * Return the right child
         *
         * @return right child
         */
        public MyBSTNode<K, V> getRight() {
            return right;
        }

        /**
         * Set the right child
         *
         * @param newRight the new right child
         */
        public void setRight(MyBSTNode<K, V> newRight) {
            this.right = newRight;
        }
        /**
        * Returns the successor of the current node
        * 
        * @return The node (key, value) of the successor
        */
        public MyBSTNode<K, V> successor() {
            MyBSTNode<K, V> current = this;

            if (current.right != null) {
                current = current.right;
                while (current.left != null) {
                    current = current.left;
                }
                return current;
            }

            MyBSTNode<K, V> parent = current.parent;
            while (parent != null && current == parent.right) {
                current = parent;
                parent = parent.parent;
            }
            return parent;
        }


        /**
         * This method compares if two node objects are equal.
         *
         * @param obj The target object that the currect object compares to.
         * @return Boolean value indicates if two node objects are equal
         */
        public boolean equals(Object obj) {
            if (!(obj instanceof MyBSTNode))
                return false;

            MyBSTNode<K, V> comp = (MyBSTNode<K, V>) obj;

            return ((this.getKey() == null ? comp.getKey() == null :
                    this.getKey().equals(comp.getKey()))
                    && (this.getValue() == null ? comp.getValue() == null :
                    this.getValue().equals(comp.getValue())));
        }

        /**
         * This method gives a string representation of node object.
         *
         * @return "Key:Value" that represents the node object
         */
        public String toString() {
            return String.format(
                    TEMPLATE,
                    this.getKey() == null ? NULL_STR : this.getKey(),
                    this.getValue() == null ? NULL_STR : this.getValue());
        }
    }

}
