/**
 * Created by renstange on 11/1/16.
 */
public class BinarySearchTreeNode <E extends Comparable<E>> {
    private E elt;
    private BinarySearchTreeNode<E> left;
    private BinarySearchTreeNode<E> right;

    public void add(E elt){
        if (elt.compareTo(this.elt) < 0) {
            if (this.left != null) {
                this.left.add(elt);
            } else {
                this.left = new BinarySearchTreeNode<>(elt);
            }
        } else if (elt.compareTo(this.elt) > 0) {
            if (this.right != null) {
                this.right.add(elt);
            } else {
                this.right = new BinarySearchTreeNode<>(elt);
            }
        } else {
            System.out.println("Duplicates are not allowed!");
        }
    }
    public boolean contains(E elt) {
        if (elt.compareTo(this.elt) == 0) {
            return true;
        } else if ((elt.compareTo(this.elt) < 0) && (this.left != null)) {
            return this.left.contains(elt);
        } else if ((elt.compareTo(this.elt) > 0) && (this.right != null)){
            return this.right.contains(elt);
        }
        return false;
    }
    public void display(){
        if (this.left != null) {
            this.left.display();
        }
        System.out.println(this.elt);
        if (this.right != null) {
            this.right.display();
        }
    }
    public void delete(E elt) {
        BinarySearchTreeNode<E> oldNode = this.getNode(elt);
        BinarySearchTreeNode<E> parNode = this.getParent(elt);
        BinarySearchTreeNode<E> travNode;
        BinarySearchTreeNode<E> repNode;
        if ((oldNode.getLeft() == null) && (oldNode.getRight() == null)) {
            if (parNode == null) {
                this.elt = null;
            } else if (parNode.getLeft().getElt().compareTo(oldNode.getElt()) == 0) {
                parNode.setLeft(null);
            } else {
                parNode.setRight(null);
            }
        } else {
            if (oldNode.getLeft() != null) {
                travNode = oldNode.getLeft();
                if (travNode.getRight() != null) {
                    repNode = travNode.getRight();
                } else {
                    repNode = travNode;
                }
                while (repNode.getRight() != null) {
                    travNode = repNode;
                    repNode = repNode.getRight();
                }
                if (travNode.equals(repNode)) {
                    oldNode.setLeft(repNode.getLeft());
                } else {
                    travNode.setRight(null);
                }
            } else {
                travNode = oldNode.getRight();
                if (travNode.getLeft() != null) {
                    repNode = travNode.getLeft();
                } else {
                    repNode = travNode;
                }
                while (repNode.getLeft() != null) {
                    travNode = repNode;
                    repNode = repNode.getLeft();
                }
                if (travNode.equals(repNode)) {
                    oldNode.setRight(repNode.getRight());
                } else {
                    travNode.setLeft(null);
                }
            }
            oldNode.setElt(repNode.getElt());
        }

    }
    private BinarySearchTreeNode<E> getNode (E elt) {
        if (elt.compareTo(this.elt) == 0) {
            return this;
        } else if ((elt.compareTo(this.elt) < 0) && (this.left != null)) {
            return this.left.getNode(elt);
        } else if ((elt.compareTo(this.elt) > 0) && (this.right != null)){
            return this.right.getNode(elt);
        }
        return null;
    }

    //Added these for my deletion method, cannot call variables without them.
    public BinarySearchTreeNode<E> getLeft() {
        return this.left;
    }
    public BinarySearchTreeNode<E> getRight() {
        return this.right;
    }
    public BinarySearchTreeNode<E> getParent(E elt) {
        BinarySearchTreeNode<E> tempParent = null;
        BinarySearchTreeNode<E> tempChild = this;
        while ((tempChild.getElt() != elt) && !((tempChild.getLeft() == null) && (tempChild.getRight() == null))) {
            tempParent = tempChild;
            if ((tempChild.getLeft() != null) && (tempChild.getLeft().contains(elt))) {
                tempChild = tempChild.getLeft();
            } else {
                tempChild = tempChild.getRight();
            }
        }
        return tempParent;
    }
    public E getElt() {
        return this.elt;
    }
    public void setLeft(BinarySearchTreeNode<E> elt) {
        this.left = elt;
    }
    public void setRight(BinarySearchTreeNode<E> elt) {
        this.right = elt;
    }
    public void setElt(E elt) {
        this.elt = elt;
    }

    public void preOrder(Visitor V) {
        V.visit(this);
        if (this.left != null) {
            this.left.preOrder(V);
        }
        if (this.right != null) {
            this.right.preOrder(V);
        }
    }
    public void inOrder(Visitor V) {
        if (this.left != null) {
            this.left.inOrder(V);
        }
        V.visit(this);
        if (this.right != null) {
            this.right.inOrder(V);
        }
    }
    public void postOrder(Visitor V) {
        if (this.left != null) {
            this.left.postOrder(V);
        }
        if (this.right != null) {
            this.right.postOrder(V);
        }
        V.visit(this);
    }
    public BinarySearchTreeNode(E elt){
        this.elt = elt;
    }
}
