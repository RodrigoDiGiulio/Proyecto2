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
public class TreeNode{
    //Atributos
    private int data;
    private int level;
    private TreeNode leftSon;
    private TreeNode rightSon;
    private TreeNode father;
    
    public TreeNode(int data){
        this.data = data;
        this.leftSon = null;
        this.rightSon = null;
        this.father = null;
        this.level = 1;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
    
    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public TreeNode getLeftSon() {
        return leftSon;
    }

    public void setLeftSon(TreeNode leftSon) {
        this.leftSon = leftSon;
    }

    public TreeNode getRightSon() {
        return rightSon;
    }

    public void setRightSon(TreeNode rightSon) {
        this.rightSon = rightSon;
    }

    public TreeNode getFather() {
        return father;
    }

    public void setFather(TreeNode father) {
        this.father = father;
    }
    
}
