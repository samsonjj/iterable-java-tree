
import java.util.*;
import java.io.*;

/*
 * our final result will be a Tree class.
 * this tree can be traversed, by interacting with node objects.
 * tree gives an iterator that will traverse the tree in depth first search.
 * also breadth first search.
 * able to grab nodes
 * but also able to 
 */

public class Tree<T> implements Iterable<T> {

    public Node<T> root;

    public Tree() {
        this.root = new Node<T>(null, null);
    }

    public Tree(T data) {
        this.root = new Node<T>(data, null);
    }

    public Node<T> setRoot(T data) {
        this.root = new Node<T>(data, null);
        return this.root;
    }

    public Iterator<T> iterator() {
        return new DfsIterator<T>(this);
    }

    class DfsIterator<T> implements Iterator<T> {

        ArrayList<NodeRecord<T>> nodeStack;

        class NodeRecord<T> {
            Node<T> node;
            int nextChild = 0;
            public NodeRecord(Node<T> node, int nextChild) {
                this.node = node;
                this.nextChild = nextChild;
            }
        }

        // We create a new node which contains only the root as a child, for proper ordering
        public DfsIterator(Tree<T> tree) {
            this.nodeStack = new ArrayList<>();
            NodeRecord<T> topRecord = new NodeRecord<T>(new Node<T>(null, null), 0);
            topRecord.node.getChildren().add(tree.root);
            this.nodeStack.add(topRecord);
        }

        public boolean hasNext() {
            while( nodeStack.size() > 0 && nodeStack.get(nodeStack.size()-1).nextChild >= 
                    nodeStack.get(nodeStack.size()-1).node.getChildren().size()) {
                nodeStack.remove(nodeStack.size()-1);
            } 
            if(nodeStack.isEmpty()) {
                return false;
            }
            else {
                return true;
            }
        }

        public T next() {
            while( nodeStack.size() > 0 && nodeStack.get(nodeStack.size()-1).nextChild >= 
                    nodeStack.get(nodeStack.size()-1).node.getChildren().size()) {
                nodeStack.remove(nodeStack.size()-1);
            }
            NodeRecord<T> currentRecord = nodeStack.get(nodeStack.size()-1);
            nodeStack.add(new NodeRecord<T>(
                        currentRecord.node.getChildren().get(currentRecord.nextChild),
                        0 ));
            currentRecord.nextChild++;
            return nodeStack.get(nodeStack.size()-1).node.getData();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
    
    

    public static class Node<T> {
        private T data;
        private ArrayList<Node<T>> children;
        private Node<T> parent;

        public Node(T data, Node<T> parent) {
            this.data = data;
            this.parent = parent;
            this.children = new ArrayList<>();
        }
        public Node<T> getParent() {
            return this.parent;
        }
        public ArrayList<Node<T>> getChildren() {
            return this.children;
        }
        public T getData() {
            return this.data;
        }
        public Node<T> addChild(T data) {
            children.add(new Node<T>(data, this));
            return this.children.get(this.children.size()-1);
        }
        public Node<T> changeParent(Node<T> newParent) {
            this.parent.getChildren().remove(this);
            this.parent = newParent;
            newParent.getChildren().add(this);
            return newParent;
        }
    }
}
