/**
 * Xia Lin
 * 110732381
 * xia.lin@stonybrook.edu
 * Assignment 5
 * CSE214-01
 * Charles Chen
 * Shilpi Bhattacharyya
 */
package treenode;

public class TreeNode {

    private String[] keywords;
    private TreeNode left;
    private TreeNode right;
    /**
     * default constructor
     */
    public TreeNode(){
        left=null;
        right=null;
    }
    /**
     * Set keywords
     * @param kw 
     * String array to be add
     */
    public void setKeywords(String[] kw) {
        keywords = kw;
    }
    /**
     * Get keywords
     * @return 
     * the string array contend keywords
     */
    public String[] getKeywords() {
        return keywords;
    }
    /**
     * Set left
     * @param l 
     * the node set to left
     */
    public void setLeft(TreeNode l) {
        left = l;
    }
    /**
     * Get left
     * @return 
     * the left node
     */
    public TreeNode getLeft() {
        return left;
    }
    /**
     * Set right
     * @param r 
     * the node to be set
     */
    public void setRight(TreeNode r) {
        right = r;
    }
    /**
     * Get right
     * @return 
     * the right node
     */
    public TreeNode getRight() {
        return right;
    }
    /**
     * Check if the node is leaf
     * @return 
     * true if left and right node do not exist, false otherwise
     */
    public boolean isLeaf() {
        if (left == null && right == null) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * Print the current node keywords
     * @return 
     * the keywords information
     */
    public String toString() {
        String temp = "";
        for (int i = 0; i < keywords.length; i++) {
            if (i == keywords.length - 1) {
                temp += keywords[i];
            } else {
                temp += keywords[i] + ", ";
            }
        }
        return temp;
    }
}
