/**
 * Created by renstange on 11/1/16.
 */
public class PrintVisitor implements Visitor {
    public void visit(BinarySearchTreeNode node) {
        System.out.println(node.getElt());
    }
}
