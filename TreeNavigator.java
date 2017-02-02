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

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TreeNavigator {

    private TreeNode root;
    private TreeNode cursor;
    private String cursorPath;
    /**
     * Build a tree by read in a file
     * @param treeFile
     * the tree file name
     * @return 
     * return the tree that builded
     */
    public static TreeNavigator buildTree(String treeFile) {
        TreeNavigator treeB = new TreeNavigator();
        File file = new File(treeFile);
        String fileInp = "";
        int firstIn = 0;
        boolean isBulid = false;
        try {
            Scanner fileInput = new Scanner(file);
            while (fileInput.hasNextLine()) {
                isBulid = false;
                firstIn++;
                fileInp = fileInput.nextLine();
                if (treeB.getRoot() != null) {
                    treeB.setCursor(treeB.getRoot());
                }
                String[] reqSplit = fileInp.split(";");
                String[] runSplit = reqSplit[0].split("-");
                String[] nodeSplit;
                if (reqSplit[2].equalsIgnoreCase("nonleaf")) {
                    nodeSplit = reqSplit[1].split(",");
                } else {
                    nodeSplit = new String[1];
                    nodeSplit[0] = reqSplit[1];
                }
                if(firstIn==1&&runSplit.length!=1){
                    System.out.println("There is no parent.");
                    break;
                }
                for (int j = 0; j < runSplit.length; j++) {
                    isBulid = true;
                    if (runSplit.length == 1) {
                        if (treeB.getRoot() == null) {
                            TreeNode newNode = new TreeNode();
                            newNode.setKeywords(nodeSplit);
                            treeB.setRoot(newNode);
                            treeB.setCursor(treeB.getRoot());
                        } else {
                            treeB.getRoot().setKeywords(nodeSplit);
                        }
                    } else {
                        if (runSplit[j].equalsIgnoreCase("0")) {
                            if (j == 0 && treeB.getRoot() == null) {
                                TreeNode newNode = new TreeNode();
                                treeB.setRoot(newNode);
                                treeB.setCursor(treeB.getRoot());
                            } else if (j != 0 && treeB.getCursor().getLeft() == null) {
                                TreeNode newNode = new TreeNode();
                                treeB.getCursor().setLeft(newNode);
                                treeB.setCursor(treeB.getCursor().getLeft());
                            } else if (j != 0) {
                                treeB.setCursor(treeB.getCursor().getLeft());
                            }

                        } else if (runSplit[j].equalsIgnoreCase("1")) {
                            if (treeB.getCursor().getRight() == null) {
                                TreeNode newNode = new TreeNode();
                                treeB.getCursor().setRight(newNode);
                            }
                            treeB.setCursor(treeB.getCursor().getRight());
                        }
                        if (j == runSplit.length - 1) {
                            if(treeB.getCursor().getKeywords()!=null){
                                System.out.println("'"+treeB.getCursor().toString()+"' node is already exist. This extra node is skipped.");
                            }
                            else{
                                treeB.getCursor().setKeywords(nodeSplit);
                            }
                        }
                    }
                }
            }
            if(isBulid){
                System.out.println("Tree Loaded.");
            }
            return treeB;
        } catch (FileNotFoundException ex) {
            System.out.println("File is not exist.");
            return treeB;
        }
    }
    /**
     * Classify the final answer by a string
     * @param text
     * the string the be classify
     * @return
     * the final answer
     */
    public String classify(String text) {
        text = text.toLowerCase();
        cursorPath = "";
        resetCursor();
        while (!cursor.isLeaf()) {
            boolean isGoLeft = true;
            String[] temp = cursor.getKeywords();
            for (int j = 0; j < temp.length; j++) {
                String s = temp[j].toLowerCase();
                if (text.contains(s)) {
                    isGoLeft = false;
                    break;
                }
                else{
                }
            }
            
            if (!isGoLeft) {
                cursorPath += "IS " + cursor.toString()+", ";
                cursor = cursor.getRight();
            } else {
                cursorPath += "NOT " + cursor.toString()+", ";
                cursor = cursor.getLeft();
            }
        }
        cursorPath += "DECISION: " + cursor.toString();
        return cursor.toString();
    }
    /**
     * Gets the current path of the cursor. For example, if cursor referred to a TreeNode at position “Garfield” in the example below, this method should return “NOT red, NOT coyote,wolf, IS cat, IS orange, DECISION: Garfield”
     * @return
     * the path information
     */
    public String getPath() {
        return cursorPath;
    }
    /**
     * reset cursor to root
     */
    public void resetCursor() {
        cursor = root;
    }
    /**
     * move cursor to cursor left node
     */
    public void cursorLeft() {
        cursor = cursor.getLeft();
    }
    /**
     * move cursor to cursor right node
     */
    public void cursorRight() {
        cursor = cursor.getRight();
    }
    /**
     * Get the left node of cursor
     * @return 
     * the left of cursor
     */
    public TreeNode getCursorLeft() {
        return cursor.getLeft();
    }
    /**
     * Get the right node of cursor
     * @return 
     * the right of cursor
     */
    public TreeNode getCursorRight() {
        return cursor.getRight();
    }
    /**
     * Get cursor
     * @return 
     * the cursor node
     */
    public TreeNode getCursor() {
        return cursor;
    }
    /**
     * Set root
     * @param r 
     * the node to be set
     */
    public void setRoot(TreeNode r) {
        root = r;
    }
    /**
     * Get root
     * @return
     * return root node
     */
    public TreeNode getRoot() {
        return root;
    }
    /**
     * Set cursor to node
     * @param c
     * node to set
     */
    public void setCursor(TreeNode c) {
        cursor = c;
    }
    /**
     * Edit cursor keywords
     * @param text 
     * the keywords to be set
     */
    public void editCursor(String text) {
        String[] editTemp;
        if (cursor.isLeaf()) {
            editTemp = new String[1];
            editTemp[0] = text;
            cursor.setKeywords(editTemp);
        } else {
            editTemp = text.split(",");
            cursor.setKeywords(editTemp);
        }
    }
    /**
     * Edit node keywords
     * @param text
     * the text to be set
     * @param tn 
     * the node of text to be change
     */
    public void editText(String text, TreeNode tn) {
        String[] editTemp;
        editTemp = text.split(",");
        tn.setKeywords(editTemp);
    }
}
