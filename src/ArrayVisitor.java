import java.util.ArrayList;
/**
 * Created by renstange on 11/2/16.
 */
public class ArrayVisitor implements Visitor {

    private ArrayList list;

    public void visit(BinarySearchTreeNode node) {
        list.add(node.getElt());
    }

    public ArrayVisitor(ArrayList tempList){
        list = tempList;
    }
}
