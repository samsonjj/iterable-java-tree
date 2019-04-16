
import java.util.*;
import java.io.*;


public class Main {
    
    public static void main(String[] args) {
        Tree<Integer> tree = new Tree<>();
        tree.setRoot(1);
        Tree.Node<Integer> node = tree.root.addChild(2);
        tree.root.addChild(4);
        tree.root.addChild(5);
        tree.root.addChild(6);
        node.addChild(3);
        

        System.out.println("root is " + tree.root.getData());
        System.out.println("child is " + tree.root.getChildren().get(0).getData());
        System.out.println("next is " + tree.root.getChildren().get(0).getChildren().get(0).getData());

        Iterator<Integer> it = tree.iterator();
        System.out.println("has Next " + it.hasNext());

        System.out.println("About to print out tree values.");
        for(Integer data : tree) {
            System.out.println(data);
        }
        System.out.println("Done printing out tree values.");
    }
}
