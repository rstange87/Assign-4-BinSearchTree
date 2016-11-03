import java.lang.Integer;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * Created by renstange on 10/19/16.
 */
public class BinarySearchTree<E extends Comparable<E>> {

    private int size;

    private BinarySearchTreeNode<E> root;

    public BinarySearchTree(){
        this.size = 0;
    }

    public void add(E elt){
        if (size != 0){
            this.root.add(elt);
        } else {
            //Create new root.
            this.root = new BinarySearchTreeNode<>(elt);
            this.size = 1;
        }
    }

    public boolean contains(E elt) {
        return(this.root.contains(elt));
    }

    public void delete(E elt) {
        if (this.root.contains(elt)) {
            this.root.delete(elt);
            if (this.root.getElt() == null) {
                this.root = null;
            }
        } else {
            System.out.print("That element does not exist!");
        }
    }
    public void display(){
        this.root.display();
    }

    public void preOrder(Visitor V) {
        this.root.preOrder(V);
    }
    public void inOrder(Visitor V) {
        this.root.inOrder(V);
    }
    public void postOrder(Visitor V) {
        this.root.postOrder(V);
    }

    public ArrayList<E> treeSort() {
        ArrayList returnList = new ArrayList();
        this.inOrder(new ArrayVisitor(returnList));
        return returnList;
    }

    /* As far as the Big-O case goes for this sort should be N,
        as it is dependent on the values auto generated and placed into the tree.
        These sort methods require to visit every element of the binary tree size N.
     */

    public static void main(String[] args){
        BinarySearchTree<Integer> myTestTree = new BinarySearchTree<>();
        for (int i=0; i<40; i++) {
            int tempNumber = (int)(Math.random()*100);
            if ((i==0) || !(myTestTree.root.contains(tempNumber))) {
                myTestTree.add(tempNumber);
            }
        }
        int choice = 0;
        do {
            Scanner myScanner = new Scanner(System.in);
            System.out.println("A tree has been created.\nWhat would you like to do?");
            System.out.println("1 - Display tree (pre-order)");
            System.out.println("2 - Display tree (in-order)");
            System.out.println("3 - Display tree (post-order)");
            System.out.println("4 - Display tree (array-sort)");
            System.out.println("5 - Add new element");
            System.out.println("6 - Delete element");
            System.out.println("10 - Exit");
            if (myScanner.hasNextInt()) {
                choice = myScanner.nextInt();
            }
            myScanner.reset();
            if (choice == 1) {
                myTestTree.preOrder(new PrintVisitor());
            } else if (choice == 2) {
                myTestTree.inOrder(new PrintVisitor());
            } else if (choice == 3) {
                myTestTree.postOrder(new PrintVisitor());
            } else if (choice == 4) {
                System.out.println(myTestTree.treeSort());
            } else if (choice == 5) {
                System.out.print("Please enter an int to add: ");
                if (myScanner.hasNextInt()) {
                    myTestTree.add(myScanner.nextInt());
                }
                myScanner.reset();
            } else if (choice == 6) {
                System.out.println("Please enter an int to remove: ");
                if (myScanner.hasNextInt()) {
                    myTestTree.delete(myScanner.nextInt());
                }
                myScanner.reset();
            } else if (choice == 10) {
                System.out.println("Goodbye!");
            }

        } while (choice != 10);

    }
}
