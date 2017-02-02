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

import java.util.Scanner;

public class DecisionTreeClassifier {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        TreeNavigator treeCreate = new TreeNavigator();
        System.out.println("Welcome to the DecisionTree Classifier");
        System.out.println("");
        System.out.println("Menu:");
        System.out.println("\tI)Import a tree from a file");
        System.out.println("\tE)Edit current tree");
        System.out.println("\tC)Classify a Description");
        System.out.println("\tP)Show decision path for a Description");
        System.out.println("\tQ) Quit");
        System.out.print("Please select an option: ");
        String s = input.nextLine();
        
        while (!s.equalsIgnoreCase("Q")) {
            if (s.equalsIgnoreCase("I")) {
                System.out.print("Please enter a filename: ");
                String fileName = input.nextLine();
                treeCreate=TreeNavigator.buildTree(fileName);
            } else if (s.equalsIgnoreCase("E")) {
                treeCreate.resetCursor();
                System.out.println("Cursor is at root.");
                System.out.print("Current node keywords: ");
                String keywords = input.nextLine();
                TreeNode newNode = new TreeNode();
                treeCreate.setRoot(newNode);
                treeCreate.setCursor(treeCreate.getRoot());
                treeCreate.editText(keywords,treeCreate.getRoot());
                System.out.println("Please select an option:");
                System.out.println("\tE)Edit Keywords");
                System.out.println("\tC)Add Children");
                System.out.println("\tD)Delete Children, and Make Leaf");
                System.out.println("\tN)Cursor to No Child");
                System.out.println("\tY)Cursor to Yes Child");
                System.out.println("\tR)Cursor to Root");
                System.out.println("\tP)Cursor to Parent");
                System.out.println("\tM)Main Menu");
                System.out.print("Please select an Edit option: ");
                String eOption = input.nextLine();
                while(!eOption.equalsIgnoreCase("M")){
                    if(eOption.equalsIgnoreCase("E")){
                        System.out.print("Please enter keywords for this node, separated by a comma: ");
                        String kw = input.nextLine();
                        treeCreate.editCursor(kw);
                        System.out.println("Keywords updated to: "+treeCreate.getCursor().toString());
                    }
                    else if(eOption.equalsIgnoreCase("C")){
                        System.out.print("Please enter terminal text for the no leaf: ");
                        String noLeaf = input.nextLine();
                        System.out.print("Please enter terminal text for the yes leaf: ");
                        String yesLeaf = input.nextLine();
                        if(treeCreate.getCursor().isLeaf()){
                            TreeNode newNodeL = new TreeNode();
                            TreeNode newNodeR = new TreeNode();
                            treeCreate.getCursor().setLeft(newNodeL);
                            treeCreate.getCursor().setRight(newNodeR);
                            treeCreate.editText(noLeaf, treeCreate.getCursor().getLeft());
                            treeCreate.editText(yesLeaf, treeCreate.getCursor().getRight());
                            System.out.println("Children are: yes - '"+treeCreate.getCursor().getRight().toString()+"' and no - '"
                                    +treeCreate.getCursor().getLeft().toString()+"'");
                        }
                        else{
                            System.out.println("Cursor is not leaf.");
                        }
                    }
                    else if(eOption.equalsIgnoreCase("D")){
                        System.out.print("Please enter a terminal text for this node: ");
                        String textTemp = input.nextLine();
                        if(treeCreate.getCursor().getLeft()!=null||treeCreate.getCursor().getRight()!=null){
                            if(treeCreate.getCursor().getLeft()!=null){
                                treeCreate.getCursor().setLeft(null);
                            }
                            if(treeCreate.getCursor().getRight()!=null){
                                treeCreate.getCursor().setRight(null);
                            }
                            treeCreate.editText(textTemp, treeCreate.getCursor());
                            System.out.println("Current node is leaf. Text is: '"+treeCreate.getCursor().toString()+"'");
                        }
                        else{
                            System.out.println("The cursor is a leaf node.");
                        }
                    }
                    else if(eOption.equalsIgnoreCase("N")){
                        if(!treeCreate.getCursor().isLeaf()){
                            treeCreate.cursorLeft();
                            if(treeCreate.getCursor().isLeaf()){
                                System.out.println("Cursor moved. Cursor is at leaf, message is '"+treeCreate.getCursor().toString()+"'");
                            }
                            else{
                                System.out.println("Cursor moved. Message is '"+treeCreate.getCursor().toString()+"'");
                            }
                        }
                        else{
                            System.out.println("Cursor is already leaf.");
                        }
                        
                    }
                    else if(eOption.equalsIgnoreCase("Y")){
                        if(!treeCreate.getCursor().isLeaf()){
                            treeCreate.cursorRight();
                            if(treeCreate.getCursor().isLeaf()){
                                System.out.println("Cursor moved. Cursor is at leaf, message is '"+treeCreate.getCursor().toString()+"'");
                            }
                            else{
                                System.out.println("Cursor moved. Message is '"+treeCreate.getCursor().toString()+"'");
                            }
                        }
                        else{
                            System.out.println("Cursor is already leaf.");
                        }
                    }
                    else if(eOption.equalsIgnoreCase("R")){
                        if(treeCreate.getRoot()!=null){
                            treeCreate.resetCursor();
                            System.out.println("Cursor moved. Cursor is at root position.");
                        }
                        else{
                            System.out.println("The root is null.");
                        }
                    }
                    else if(eOption.equalsIgnoreCase("P")){
                        
                    }
                    System.out.print("Please select an Edit option: ");
                    eOption = input.nextLine();
                }
            } else if (s.equalsIgnoreCase("C")) {
                if(treeCreate.getRoot()!=null){
                System.out.print("Please enter some text: ");
                String text = input.nextLine();
                System.out.println("Your request is classified as: "+treeCreate.classify(text));
                }
                else{
                    System.out.println("There is no tree.");
                }
            } else if (s.equalsIgnoreCase("P")) {
                if(treeCreate.getRoot()!=null){
                System.out.print("Please enter some text: ");
                String text = input.nextLine();
                treeCreate.classify(text);
                System.out.println("Decision path: "+treeCreate.getPath());
                }
                else{
                    System.out.println("There is no tree.");
                }
            }
            System.out.print("Please select an option: ");
            s = input.nextLine();
        }
        System.out.println("GoodBye!");

    }
}
