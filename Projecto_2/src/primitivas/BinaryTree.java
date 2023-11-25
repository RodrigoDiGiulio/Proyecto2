/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package primitivas;

/**
 *
 * @author RDG
 * @param <T>
 */
public class BinaryTree{
    //Atributos
    private TreeNode root;
    
    public BinaryTree(){
        this.root = null;
    }

    public BinaryTree(TreeNode root) {
        this.root = root;
    }

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }
    
    public boolean isEmpty(TreeNode node){
        return node == null;
    }

    public void preOrder(TreeNode tempRoot){
        if(tempRoot != null){
            System.out.print(tempRoot.getData()+" ");
            preOrder(tempRoot.getLeftSon());
            preOrder(tempRoot.getRightSon());
        }
    }
    
    public void inOrder(TreeNode tempRoot){
        if(tempRoot != null){
            inOrder(tempRoot.getLeftSon());
            System.out.print(tempRoot.getData()+" ");
            inOrder(tempRoot.getRightSon());
        }
    }
    
    public List postOrder(TreeNode tempRoot, List finalList){
        if(tempRoot != null){
            postOrder(tempRoot.getLeftSon(),finalList);
            postOrder(tempRoot.getRightSon(),finalList);
            finalList.addEnd(tempRoot.getData());
        }
        return finalList;
    }
    
    //NO FUNCIONA
//    public List postOrderFather(TreeNode node, List fatherList) {
//        if (node != null) {
//            if (node.getFather() != null) {
//                fatherList.addEnd(node.getFather().getData());
//            } else {
//                fatherList.addEnd(-1);
//            }
//            fatherList = postOrderFather(node.getLeftSon(), fatherList);
//            fatherList = postOrderFather(node.getRightSon(), fatherList);
//        }
//        return fatherList;
//    }
    
    //FUNCIONA MENOS, PERO NO DA ERRORES con 3, con el resto si
    public List postOrderFather(TreeNode node, List fatherList) {
        if (node != null) {
            postOrderFather(node.getLeftSon(), fatherList);
            postOrderFather(node.getRightSon(), fatherList);
            if (node.getFather() != null) {
                fatherList.addEnd(node.getFather().getData());
            } else {
                fatherList.addEnd(node.getData()-100);
            }
        }
        return fatherList;
    }
    
    public void print(TreeNode tempRoot){
        if (tempRoot != null) {
            System.out.println(tempRoot.getData() + " ");
            print(tempRoot.getLeftSon());
            print(tempRoot.getRightSon());
        }
    }
    
    int level(TreeNode N) {
        if (N == null)
            return 0;
        return N.getLevel();
    }
    
    TreeNode rightRotate(TreeNode y) {
        TreeNode x = y.getLeftSon();
        TreeNode T2 = x.getRightSon();
        x.setRightSon(y);
        y.setLeftSon(T2);
        y.setLevel(Math.max(level(y.getLeftSon()), level(y.getRightSon())) + 1);
        x.setLevel(Math.max(level(x.getLeftSon()), level(x.getRightSon())) + 1);
        return x;
    }
    
    TreeNode leftRotate(TreeNode x) {
        TreeNode y = x.getRightSon();
        TreeNode T2 = y.getLeftSon();
        y.setLeftSon(x);
        x.setRightSon(T2);
        x.setLevel(Math.max(level(x.getLeftSon()), level(x.getRightSon())) + 1);
        y.setLevel(Math.max(level(y.getLeftSon()), level(y.getRightSon())) + 1);
        return y;
    }
    
    int getBalance(TreeNode N) {
        if (N == null)
            return 0;
        return level(N.getLeftSon()) - level(N.getRightSon());
    }
    
    public TreeNode add(TreeNode treeNode, TreeNode father, int data){
        if(treeNode == null){
            TreeNode newNode = new TreeNode(data);
            newNode.setFather(father);
            return newNode;
        }else{
            if(treeNode.getData() > data){
                treeNode.setLeftSon(add(treeNode.getLeftSon(), treeNode, data));
            }else{
                treeNode.setRightSon(add(treeNode.getRightSon(), treeNode, data));
            }
        }
        treeNode.setLevel(1 + Math.max(level(treeNode.getLeftSon()), level(treeNode.getRightSon())));
        int balance = getBalance(treeNode);
        if (balance > 1 && data < treeNode.getLeftSon().getData())
            return rightRotate(treeNode);
        if (balance < -1 && data > treeNode.getRightSon().getData())
            return leftRotate(treeNode);
        if (balance > 1 && data > treeNode.getLeftSon().getData()) {
            treeNode.setLeftSon(leftRotate(treeNode.getLeftSon()));
            return rightRotate(treeNode);
        }
        if (balance < -1 && data < treeNode.getRightSon().getData()) {
            treeNode.setRightSon(rightRotate(treeNode.getRightSon()));
            return leftRotate(treeNode);
        }
        return treeNode;
    }
    
}
