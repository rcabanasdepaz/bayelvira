/*
 */
package elvira.potential.binaryprobabilitytree;

import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import elvira.*;
import elvira.potential.Potential;
import elvira.Configuration;
import elvira.inference.elimination.ids.IDVEWithPotentialBPTree;
import elvira.inference.elimination.ids.IDVEWithPotentialBPTree.pruningMethods;
import elvira.inference.elimination.ids.IDVEWithPotentialTree;
import elvira.potential.PotentialTable;
import elvira.potential.ProbabilityTree;
import elvira.tools.Distances;
import elvira.tools.SortVectors;
import elvira.tools.VectorManipulator;
import elvira.tools.idiagram.InfoGainManipulator;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A binary probability tree is a compact representation of a probability distribution,
 * alternative to a probability table. 
 * Each internal node represents a variable and each leaf
 * node represents a probability value. Each variable node (internal node)
 * has two children. Each child of an internal node is labeled with a set of 
 * states of the variable of that internal node.
 * The value stored in a leaf node corresponds to the probability of the
 * configurations of variables consistent with the labeling from the root to that leaf.
 *
 * An object of this class is a node of the tree, that can point
 * to other nodes, forming a tree in this way.
 *
 * @author Andrés Cano Utrera (acu@decsai.ugr.es)
 * @author Rafael Cabañas de Paz (rcabanas@decsai.ugr.es)
 */
public class BinaryProbabilityTree{
    
    protected static FiniteStates FiniteStates;
    
    static float it = 0;

    /**
     * The variable associated with this node of the tree, if the node is
     * internal. When var == null, it means that this node is a probability node
     */
    protected FiniteStates var = null;
    /**
     * The probability value, if this node is a leaf.
     */
    private double value;
    /**
     * A reference to the left child
     */
    protected BinaryProbabilityTree.BinaryProbabilityTreeInfoChild leftChild = null;
    /**
     * A reference to the right child
     */
    protected BinaryProbabilityTree.BinaryProbabilityTreeInfoChild rightChild = null;


    private static boolean improvedOps = false;
    

    private int factor = 1;
    
      /**
     * Creates a probability node with value p.
     * @param p a double value.
     */

    public BinaryProbabilityTree(double p) {
        value=p;
    }

      /**
     * Creates an empty tree.
     * @param p a double value.
     */

    public BinaryProbabilityTree() {

    }


    public static BinaryProbabilityTree createTerminalTree(FiniteStates n, int leftState, double leftValue, int rightState, double rightValue){
        
        BinaryProbabilityTree tree = new BinaryProbabilityTree();
        tree.setVar(n);
        
        BinaryProbabilityTree leftTree = new BinaryProbabilityTree(leftValue);
        BinaryProbabilityTree rightTree = new BinaryProbabilityTree(rightValue);
        
        //Set left state
        ListStates statesLeft = new BinaryProbabilityTree.ListStates(n);        
        for(int i=0; i<statesLeft.getNumStates(); i++){
            statesLeft.setElementAt(i, i==leftState);
        }
        BinaryProbabilityTreeInfoChild infoLeft = new BinaryProbabilityTree.BinaryProbabilityTreeInfoChild();
        infoLeft.setInfo(leftTree, statesLeft);
        tree.setLeftChild(infoLeft);
        
        //Set left state
        ListStates statesRight = new BinaryProbabilityTree.ListStates(n);        
        for(int i=0; i<statesRight.getNumStates(); i++){
            statesRight.setElementAt(i, i==rightState);
        }
        BinaryProbabilityTreeInfoChild infoRight = new BinaryProbabilityTree.BinaryProbabilityTreeInfoChild();
        infoRight.setInfo(rightTree, statesRight);
        tree.setRightChild(infoRight);
        
        return tree;
    }
    
    
    
    /**
     * Gets the variable associated with this node.
     * @return the <code>FiniteStates</code> variable stored in this tree node.
     */
    public FiniteStates getVar() {
        return var;
    }
    
    
     /**
     * Gets the value associated with this node. If it is not a leave, it will return null
     * @return the <code>double</code> value of the node
     */
    public double getValue() {
        return this.value;
    }

    public int getFactor() {
        return factor;
    }

    public void setFactor(int factor) {
        this.factor = factor;
    }
       
    
    
    

    /**
     * Assigns a value to this node.
     * @param p a <code>double</code> value.
     */
    public void assignProb(double p) {
        /* if (var != null) {
        System.out.println("Error in BinaryProbabilityTree.assignProb(double): var!=null");
        try {
        throw (new Exception());
        } catch (Exception e) {
        e.printStackTrace();
        }
        System.exit(1);
        }*/
        var = null;
        value = p;
        this.leftChild = null;
        this.rightChild = null;
        
        
        
    }
    
    
    
    /**
     * This method constructs a tree from an existing tree. This variable will
     * reference to the same object than tree.
     * @param tree 
     */
    
    public void setTree(BinaryProbabilityTree tree) {
         this.value = tree.value;
         this.var = tree.var;
         this.leftChild = tree.leftChild;
         this.rightChild = tree.rightChild;
    }

    
    
    
    /**
     * Sets the value of the node
     * @param value 
     */
    public void setValue(double value) {
        this.value = value;
    }

    /**
     * Sets the variable of the node
     * @param var 
     */
    public void setVar(FiniteStates var) {
        this.var = var;
    }

    
    
    
    
    /**
     * Gets the information about leftChild
     * @return 
     */
    public BinaryProbabilityTree.BinaryProbabilityTreeInfoChild getLeftChild() {
        return leftChild;
    }

    
    /**
     * Sets the information about the left child
     * @param leftChild 
     */
    public void setLeftChild(BinaryProbabilityTree.BinaryProbabilityTreeInfoChild leftChild) {
        this.leftChild = leftChild;
    }

    
    /**
     * Gets the information about the right child
     * @return 
     */
    public BinaryProbabilityTree.BinaryProbabilityTreeInfoChild getRightChild() {
        return rightChild;
    }

    /**
     * Sets the information about the right child
     * @param rightChild 
     */
    public void setRightChild(BinaryProbabilityTree.BinaryProbabilityTreeInfoChild rightChild) {
        this.rightChild = rightChild;
    }
    
    

    /**
     * Combines two trees. This operation is analogous to the pointwise
     * product of two probability tables.
     * @param binaryPT the <code>BinaryProbabilityTree</code> to be multiplied with
     * this tree
     * @return a new <code>BinaryProbabilityTree</code> resulting from combining
     * this tree with <code>binaryPT</code>.
     */
    public BinaryProbabilityTree combine(BinaryProbabilityTree binaryPT) {
        
        BinaryProbabilityTree tree;        
        tree = new BinaryProbabilityTree();
        //System.out.println("improvedOps="+improvedOps);
        if(improvedOps) {
            
            //If one of the trees is a leaf with the value 0,
            //the result will be always 0
            if((isProbab() && value==0)){
                tree.value=0;
                tree.setFactor((int)binaryPT.getSizeExpanded());
                return tree;
            }
            
            if ((binaryPT.isProbab() && binaryPT.getValue() == 0)) {
                tree.value = 0;
                tree.setFactor((int)getSizeExpanded());
                return tree;
            }
            //If one of the trees is a leaf with the value 1,
            //the result will be the other tree
            if(isProbab() && value==1) {
                tree = binaryPT.copy();
                return tree;
            }
            if(binaryPT.isProbab() && binaryPT.getValue()==1) {
                tree = this.copy();
                return tree;
            }
        }
      
        
        
        if (var == null) {// If we are at a probability node
            if (binaryPT.var == null) {  // If binaryPT is a probability node
              
                tree.value = value*binaryPT.value;
            //    tree.value = BigDecimal.valueOf(value).multiply(BigDecimal.valueOf(binaryPT.value)).doubleValue();
                //System.out.println(value+"*"+binaryPT.value+"="+tree.value);
                
                
                if(Potential.getStatistics() != null)
                    Potential.getStatistics().addNumMultiplications(1);
                
                
            } else { // If binaryPT is not a simple probability node
                tree.var = binaryPT.var;

                tree.leftChild = new BinaryProbabilityTree.BinaryProbabilityTreeInfoChild();
                // Left child will be the combination of the left child  of
                // binaryPT and the value stored in this node tree
                tree.leftChild.setInfo(combine(binaryPT.leftChild.child),
                        new BinaryProbabilityTree.ListStates(binaryPT.leftChild.listStates));
                tree.rightChild = new BinaryProbabilityTree.BinaryProbabilityTreeInfoChild();
                // Right child will be the combination of the right child  of
                // binaryPT and the value stored in this node tree
                tree.rightChild.setInfo(combine(binaryPT.rightChild.child),
                        new BinaryProbabilityTree.ListStates(binaryPT.rightChild.listStates));
            }

        } else { // If we are not at a simple probability node
            tree.var = var;
            tree.leftChild = new BinaryProbabilityTree.BinaryProbabilityTreeInfoChild();

            // Left child will be the combination of the left child  of this node
            // with the restriction of binaryPT to var=leftChild.listStates
            tree.leftChild.setInfo(leftChild.child.combine(
                    binaryPT.restrict(var, leftChild.listStates)),
                    new BinaryProbabilityTree.ListStates(leftChild.listStates));
            tree.rightChild = new BinaryProbabilityTree.BinaryProbabilityTreeInfoChild();
            // Right child will be the combination of the ritht child  of this node
            // with the restriction of binaryPT to var=rightChild.listStates
            tree.rightChild.setInfo(rightChild.child.combine(
                    binaryPT.restrict(var, rightChild.listStates)),
                    new BinaryProbabilityTree.ListStates(rightChild.listStates));
        }

        return tree;
    }

    /**
     * Combines two trees. This operation is analogous to the pointwise
     * product of two probability tables.
     * @param binaryPT the <code>BinaryProbabilityTree</code> to be multiplied with
     * this tree
     * @return a new <code>BinaryProbabilityTree</code> resulting from combining
     * this tree with <code>binaryPT</code>.
     */
    public BinaryProbabilityTree combine(BinaryProbabilityTree binaryPT, boolean lowerBounds, boolean upperBounds) {
        
        BinaryProbabilityTree tree;        
        tree = new BinaryProbabilityTree();
        
        boolean leftLowerBounds = lowerBounds, leftUpperBounds=upperBounds;
        boolean rightLowerBounds = lowerBounds, rightUpperBounds=upperBounds;
        
        if(hasTransparent()){
            leftLowerBounds = true;
            leftUpperBounds = false;
            rightLowerBounds = false;
            rightUpperBounds = true;
        }
        
        
        //System.out.println("improvedOps="+improvedOps);
        if(improvedOps) {
            
            //If one of the trees is a leaf with the value 0,
            //the result will be always 0
            if((isProbab() && value==0)){
                tree.value=0;
                tree.setFactor((int)binaryPT.getSizeExpanded());
                return tree;
            }
            
            if ((binaryPT.isProbab() && binaryPT.getValue() == 0)) {
                tree.value = 0;
                tree.setFactor((int)getSizeExpanded());
                return tree;
            }
            //If one of the trees is a leaf with the value 1,
            //the result will be the other tree
            if(isProbab() && value==1) {
                tree = binaryPT.copy();
                return tree;
            }
            if(binaryPT.isProbab() && binaryPT.getValue()==1) {
                tree = this.copy();
                return tree;
            }
        }
      
        
        
        if (var == null) {// If we are at a probability node
            
                            
            FiniteStates T2 = binaryPT.getFirstTransparentVar();
            if (T2 != null) {
                if (lowerBounds) {
                    if (value > 0) {
                        binaryPT = binaryPT.restrict(T2, 0);
                    } else {
                        binaryPT = binaryPT.restrict(T2, 1);
                    }
                } else {
                    if (value > 0) {
                        binaryPT = binaryPT.restrict(T2, 1);
                    } else {
                        binaryPT = binaryPT.restrict(T2, 0);
                    }
                }

            }


            
            if (binaryPT.var == null) {  // If binaryPT is a probability node
              
                tree.value = value*binaryPT.value;
            //    tree.value = BigDecimal.valueOf(value).multiply(BigDecimal.valueOf(binaryPT.value)).doubleValue();
                //System.out.println(value+"*"+binaryPT.value+"="+tree.value);
                
                
                if(Potential.getStatistics() != null)
                    Potential.getStatistics().addNumMultiplications(1);
                
                
            } else { // If binaryPT is not a simple probability node

                tree.var = binaryPT.var;

                tree.leftChild = new BinaryProbabilityTree.BinaryProbabilityTreeInfoChild();
                // Left child will be the combination of the left child  of
                // binaryPT and the value stored in this node tree
                tree.leftChild.setInfo(combine(binaryPT.leftChild.child, leftLowerBounds, leftUpperBounds),
                        new BinaryProbabilityTree.ListStates(binaryPT.leftChild.listStates));
                tree.rightChild = new BinaryProbabilityTree.BinaryProbabilityTreeInfoChild();
                // Right child will be the combination of the right child  of
                // binaryPT and the value stored in this node tree
                tree.rightChild.setInfo(combine(binaryPT.rightChild.child, rightLowerBounds, rightUpperBounds),
                        new BinaryProbabilityTree.ListStates(binaryPT.rightChild.listStates));
            }

        } else { // If we are not at a simple probability node
            tree.var = var;
            tree.leftChild = new BinaryProbabilityTree.BinaryProbabilityTreeInfoChild();

            // Left child will be the combination of the left child  of this node
            // with the restriction of binaryPT to var=leftChild.listStates
            tree.leftChild.setInfo(leftChild.child.combine(
                    binaryPT.restrict(var, leftChild.listStates), leftLowerBounds, leftUpperBounds),
                    new BinaryProbabilityTree.ListStates(leftChild.listStates));
            tree.rightChild = new BinaryProbabilityTree.BinaryProbabilityTreeInfoChild();
            // Right child will be the combination of the ritht child  of this node
            // with the restriction of binaryPT to var=rightChild.listStates
            tree.rightChild.setInfo(rightChild.child.combine(
                    binaryPT.restrict(var, rightChild.listStates), rightLowerBounds, rightUpperBounds),
                    new BinaryProbabilityTree.ListStates(rightChild.listStates));
        }

        return tree;
    }
   
    
    
        /**
     * Sums two trees. This operation is analogous to the pointwise
     * product of two probability tables.
     * @param binaryPT the <code>BinaryProbabilityTree</code> to be multiplied with
     * this tree
     * @return a new <code>BinaryProbabilityTree</code> resulting from combining
     * this tree with <code>binaryPT</code>.
     */
    public BinaryProbabilityTree sum(BinaryProbabilityTree binaryPT) {
        
        BinaryProbabilityTree tree;        
        tree = new BinaryProbabilityTree();
        //System.out.println("improvedOps="+improvedOps);
  /*      if(improvedOps) {
            
            
            //If one of the trees is a leaf with the value 0,
            //the result will be the other tree
            if(isProbab() && value==0) {
        //        System.out.println("improvedOps sum");
                tree = binaryPT.copy();
                return tree;
            }
            if(binaryPT.isProbab() && binaryPT.getValue()==0) {
         //       System.out.println("improvedOps sum");
                tree = this.copy();
                return tree;
            }
        }
      
*/
        
        if (var == null) {// If we are at a probability node
            if (binaryPT.var == null) {  // If binaryPT is a probability node
              
                tree.value = value+binaryPT.value;
            //    tree.value = BigDecimal.valueOf(value).multiply(BigDecimal.valueOf(binaryPT.value)).doubleValue();
                //System.out.println(value+"*"+binaryPT.value+"="+tree.value);
                if(Potential.getStatistics() != null)
                    Potential.getStatistics().addNumSums(1);
                
            } else { // If binaryPT is not a simple probability node
                tree.var = binaryPT.var;

                tree.leftChild = new BinaryProbabilityTree.BinaryProbabilityTreeInfoChild();
                // Left child will be the combination of the left child  of
                // binaryPT and the value stored in this node tree
                tree.leftChild.setInfo(sum(binaryPT.leftChild.child),
                        new BinaryProbabilityTree.ListStates(binaryPT.leftChild.listStates));
                tree.rightChild = new BinaryProbabilityTree.BinaryProbabilityTreeInfoChild();
                // Right child will be the combination of the right child  of
                // binaryPT and the value stored in this node tree
                tree.rightChild.setInfo(sum(binaryPT.rightChild.child),
                        new BinaryProbabilityTree.ListStates(binaryPT.rightChild.listStates));
            }

        } else { // If we are not at a simple probability node
            tree.var = var;
            tree.leftChild = new BinaryProbabilityTree.BinaryProbabilityTreeInfoChild();

            // Left child will be the combination of the left child  of this node
            // with the restriction of binaryPT to var=leftChild.listStates
            tree.leftChild.setInfo(leftChild.child.sum(
                    binaryPT.restrict(var, leftChild.listStates)),
                    new BinaryProbabilityTree.ListStates(leftChild.listStates));
            tree.rightChild = new BinaryProbabilityTree.BinaryProbabilityTreeInfoChild();
            // Right child will be the combination of the ritht child  of this node
            // with the restriction of binaryPT to var=rightChild.listStates
            tree.rightChild.setInfo(rightChild.child.sum(
                    binaryPT.restrict(var, rightChild.listStates)),
                    new BinaryProbabilityTree.ListStates(rightChild.listStates));
        }

        return tree;
    }

    
    

    
    
        /**
     * subtract two trees. This operation is analogous to the pointwise
     * product of two probability tables.
     * @param binaryPT the <code>BinaryProbabilityTree</code> to be multiplied with
     * this tree
     * @return a new <code>BinaryProbabilityTree</code> resulting from combining
     * this tree with <code>binaryPT</code>.
     */
    public BinaryProbabilityTree subtract(BinaryProbabilityTree binaryPT) {
        
        BinaryProbabilityTree tree;        
        tree = new BinaryProbabilityTree();
        //System.out.println("improvedOps="+improvedOps);
    /*    if(improvedOps) {
            
            //If one of the trees is a leaf with the value 0,
            //the result will be always 0
            if((isProbab() && value==0)){
                tree.value=0;
                tree.setFactor((int)binaryPT.getSizeExpanded());
                return tree;
            }
            
            if ((binaryPT.isProbab() && binaryPT.getValue() == 0)) {
                tree.value = 0;
                tree.setFactor((int)getSizeExpanded());
                return tree;
            }
            //If one of the trees is a leaf with the value 1,
            //the result will be the other tree
            if(isProbab() && value==1) {
                tree = binaryPT.copy();
                return tree;
            }
            if(binaryPT.isProbab() && binaryPT.getValue()==1) {
                tree = this.copy();
                return tree;
            }
        }
      */

        
        if (var == null) {// If we are at a probability node
            if (binaryPT.var == null) {  // If binaryPT is a probability node
              
                tree.value = value-binaryPT.value;
    /*            if(Potential.getStatistics() != null)
                    Potential.getStatistics().addNumSums(1);
     */           
            } else { // If binaryPT is not a simple probability node
                tree.var = binaryPT.var;

                tree.leftChild = new BinaryProbabilityTree.BinaryProbabilityTreeInfoChild();
                // Left child will be the combination of the left child  of
                // binaryPT and the value stored in this node tree
                tree.leftChild.setInfo(subtract(binaryPT.leftChild.child),
                        new BinaryProbabilityTree.ListStates(binaryPT.leftChild.listStates));
                tree.rightChild = new BinaryProbabilityTree.BinaryProbabilityTreeInfoChild();
                // Right child will be the combination of the right child  of
                // binaryPT and the value stored in this node tree
                tree.rightChild.setInfo(subtract(binaryPT.rightChild.child),
                        new BinaryProbabilityTree.ListStates(binaryPT.rightChild.listStates));
            }

        } else { // If we are not at a simple probability node
            tree.var = var;
            tree.leftChild = new BinaryProbabilityTree.BinaryProbabilityTreeInfoChild();

            // Left child will be the combination of the left child  of this node
            // with the restriction of binaryPT to var=leftChild.listStates
            tree.leftChild.setInfo(leftChild.child.subtract(
                    binaryPT.restrict(var, leftChild.listStates)),
                    new BinaryProbabilityTree.ListStates(leftChild.listStates));
            tree.rightChild = new BinaryProbabilityTree.BinaryProbabilityTreeInfoChild();
            // Right child will be the combination of the ritht child  of this node
            // with the restriction of binaryPT to var=rightChild.listStates
            tree.rightChild.setInfo(rightChild.child.subtract(
                    binaryPT.restrict(var, rightChild.listStates)),
                    new BinaryProbabilityTree.ListStates(rightChild.listStates));
        }

        return tree;
    }

    
    

    
    
    
    
    /**
     * Removes a variable from this tree by summing over all its values. This is
     * used by the marginalization operation.
     * @param variable a <code>FiniteStates</code> variable to remove from
     * the tree.
     * @see addVariable(FiniteStates variable, int factor)
     * @return a new <code>BinaryProbabilityTree</code> with the result of the
     * operation.
     */
  /*  public BinaryProbabilityTree addVariable(FiniteStates variable) {
        //return addVariable(variable, variable.getNumStates());
        return addVariable(variable);
    }*/

    /**
     * Restricts this tree to the value <code>val</code> of <code>variable</code>.
     * @param variable a <code>FiniteStates</code> variable to which the tree
     * will be restricted.
     * @param val the <code>int</code> value of the variable to instantiate
     * (first value = 0).
     * @return a new <code>BinaryProbabilityTree</code> consisting of the
     * restriction of this tree to the value number <code>val</code> of
     * <code>variable</code>
     */
    public BinaryProbabilityTree restrict(FiniteStates variable, int val) {
        BinaryProbabilityTree.ListStates setToRestrict = new BinaryProbabilityTree.ListStates(variable.getNumStates());
        setToRestrict.activateStateAt(val);

        return restrict(variable, setToRestrict);

    }

    /**
     * Makes a recursive copy of this binary probability tree.
     * @return a <code>BinaryProbabilityTree</code> copy of this one.
     */
    public BinaryProbabilityTree copy() {
        BinaryProbabilityTree newTree;

        newTree = new BinaryProbabilityTree();

        if (var == null) {
            newTree.value = value;
        } else {
            newTree.var = var;
            newTree.leftChild = leftChild.copy();
            newTree.rightChild = rightChild.copy();
        }

        return newTree;
    }

    /**
     * Normalizes this tree starting in this node to sum up to 1.
     * This object is modified with the method.
     * @param totalSize size of the completely expanded tre, that is the number
     * of possible configurations of the variables in this potential.
     */
    public void normalize(long totalSize) {
        double total;

        total = sum(totalSize);

        if (var == null) { // probability node
            if (total > 0.0) {
                value /= total;
                if (Double.isNaN(value) || Double.isInfinite(value)) {
                    value = 0.0;
                }

            } else {
                value = 0.0;
            }

        } else {
            leftChild.child.normalizeAux(total);
            rightChild.child.normalizeAux(total);
        }

    }

    /**
     * Auxiliary recursive method for <code>normalize(long)</code>. It avoids to unnecessary
     * compute again the addition of the values in this tree.
     * This object is modified with the method.
     * @param total the addition of the values in the leaves of the tree
     * being normalized.
     */
    private void normalizeAux(double total) {
        if (var == null) { // probability node
            if (total > 0.0) {
                value /= total;
                if (Double.isNaN(value) || Double.isInfinite(value)) {
                    value = 0.0;
                }

            } else {
                value = 0.0;
            }

        } else {
            leftChild.child.normalizeAux(total);
            rightChild.child.normalizeAux(total);
        }

    }

    /**
     * Computes the addition of all the values in this tree, starting
     * at this node.
     * @param treeSize size of the completely expanded tree, that is, the number
     * of possible configurations of the variables in this potential.
     * @return the  computed addition of values in this tree.
     */
    public double sum(long treeSize) {
        double s = 0.0;

        if (var == null) { // Probability node
            s = (double) treeSize * value;
        } else {
            int nLeftStates = leftChild.listStates.numberValidStates();
            int nRightStates = rightChild.listStates.numberValidStates();
            int nStates = nLeftStates + nRightStates;

            
            
            s += leftChild.child.sum((treeSize / nStates) * nLeftStates);
            s += rightChild.child.sum((treeSize / nStates) * nRightStates);
        }

        return s;
    }
    
    
        /**
     * Computes the addition of all squares of the values in this tree, starting
     * at this node.
     * @param treeSize size of the completely expanded tree, that is, the number
     * of possible configurations of the variables in this potential.
     * @return the  computed addition of the squares of the values in this tree.
     */
    public double sumSqr(long treeSize) {
        double s = 0.0;

        if (var == null) { // Probability node
            s = (double) treeSize * Math.pow(value, 2);
        } else {
            int nLeftStates = leftChild.listStates.numberValidStates();
            int nRightStates = rightChild.listStates.numberValidStates();
            int nStates = nLeftStates + nRightStates;

            
            
            s += leftChild.child.sumSqr((treeSize / nStates) * nLeftStates);
            s += rightChild.child.sumSqr((treeSize / nStates) * nRightStates);
        }

        return s;
    }
    
    
    
    
    /**
     * Multiplies all leaves of a tree by constant value
     * @param k 
     */
    public void multiply(double k) {
        if(isProbab())
            value = value * k;
        else{
            leftChild.child.multiply(k);
            rightChild.child.multiply(k);
        }
            
    
    }
    
    
        /**
         * Get a vector conaining all the leaves of the tree
     * @return Vector
     */
    public Vector getLeaves() {

        
      Vector leaves = new Vector();
        this.getLeaves(leaves);

        return leaves;
        


    }
    
    


    
        /**
         * Get a vector conaining all the leaves of the tree.
         * It is a private auxiliare method used by the public version.
     * @return Vector
     */
    private void getLeaves(Vector leaves) {

        
        
        if (isProbab()) { // Probability node

                leaves.add(value);
            
        } else {
            leftChild.child.getLeaves(leaves);
            rightChild.child.getLeaves(leaves);
        }

    }
    
        /**
         * Get a vector conaining all the leaves of the tree
     * @return Vector
     */
    public Vector getLeavesExpanded() {

        
      Vector leaves = new Vector();
        this.getLeavesExpanded(leaves);

        return leaves;
        


    }
    
    


    
        /**
         * Get a vector conaining all the leaves of the tree.
         * It is a private auxiliare method used by the public version.
     * @return Vector
     */
    private void getLeavesExpanded(Vector leaves) {

        
        
        if (isProbab()) { // Probability node
            for(int i=0; i<factor; i++)
                leaves.add(value);
            
        } else {
            leftChild.child.getLeaves(leaves);
            rightChild.child.getLeaves(leaves);
        }

    }
    
    
          /**
         * Get a vector conaining all leaves factors
     * @return Vector
     */
    public Vector getFactors(long oldSize) {

        
      Vector factors = new Vector();
        this.getFactors(factors, oldSize);

        return factors;
        


    }
    
    


    
        /**
         * Get a vector conaining all leaves factors
         * It is a private auxiliare method used by the public version.
     * @return Vector
     */
    private void getFactors(Vector factors, long oldSize) {

        
        
        if (isProbab()) { // Probability node

                factors.add((int)oldSize);
            
        } else {
            
            int nValidStatesLeft = this.getLeftChild().getListStates().getNumValidStates();
            int nValidStatesRight = this.getRightChild().getListStates().getNumValidStates();
            int nValidStates = nValidStatesLeft + nValidStatesRight;
            
            long leftSize = (oldSize / nValidStates) * nValidStatesLeft;
            long rightSize = (oldSize / nValidStates) * nValidStatesRight;

            
            leftChild.child.getFactors(factors, leftSize);
            rightChild.child.getFactors(factors, rightSize);
        }

    }
      
    
    
        /**
     * Computes the sum of squared distances whithin a reference value
     * @param reference, the value to compare with the tree
     * @param ceroSumms, vector to store the number of situations
     * where the difference is 0
     * @return the estimation of distance
     */
    
    private double sumUtilitySquaredDistances(double reference, Vector ceroSumms){
        double s = 0.0, dif = 0.0;
        int i, nv;
        long newSize;
        double ceros=((Double)ceroSumms.elementAt(0)).doubleValue();
        
        if (isProbab()){
            // May be the tree was truncated and a single node represents
            // multiple values (all of them will be the same)
            s = value-reference;

            if (s == 0)
                ceros++;
            ceroSumms.setElementAt(new Double(ceros),0);
            s = Math.pow(s,2);
        }
        else {
            
            if(leftChild != null)
                s+=leftChild.child.sumUtilitySquaredDistances(reference, ceroSumms);
             if(rightChild != null)
                s+=rightChild.child.sumUtilitySquaredDistances(reference, ceroSumms);               
            
        }
        
        // Return the value
        
        return (s);
    }
    
       
    /**
     * Computes the euclidiean distance between a tree expande once, with the completed
     * expanded
     * @param variable splitting variable
     * @param cutPoint splitting cut point, if the variable has n states, it can be from 1 to n-1
     * @param states list of states of the variable
     * @param potentialSize
     * @param leftTree reference variable for returing the left tree which results from the partition
     * @param rightTree  reference variable for returing the right tree which results from the partition
     * @return euclidean utility distance
     */

    public double utilityInfoGain(FiniteStates variable, int firstState, int cutPoint, BinaryProbabilityTree.ListStates states, long oldSize,
            BinaryProbabilityTree leftTree, BinaryProbabilityTree rightTree, pruningMethods method) {
    
        
        double totalD = 0.0;
        Vector meanLeft, meanRight, leavesLeft, leavesRight, leaves, mean;
        Vector factors, factorsLeft, factorsRight;
        
        double d1, d2, info;
        
        
        //Make the partitions
        BinaryProbabilityTree.ListStates leftPartition = states.getPartition(firstState, cutPoint+1);
        BinaryProbabilityTree.ListStates rightPartition = states.getPartition(cutPoint+1, states.getNumStates());
      
        //Search the states of the variable present in the tree: if it
        //has been prunned previously, it is possible that one of
        //the states had been deleted.
//        BinaryProbabilityTree.ListStates statesInTree = this.getStatesInTree(variable);
        
        

        /* Expands the tree once and computes the 
         * distance with the completed expanded tree.
         */
        
        
        
        //Information of the left tree
        leftTree.setTree(restrict(variable, leftPartition));
        //Information of the right tree
        rightTree.setTree(restrict(variable, rightPartition));

        
        int nValidStatesLeft = leftPartition.getNumValidStates();
        int nValidStatesRight = rightPartition.getNumValidStates();
        int nValidStates = nValidStatesLeft + nValidStatesRight;

        long leftSize = (oldSize / nValidStates) * nValidStatesLeft;
        long rightSize = (oldSize / nValidStates) * nValidStatesRight;


        //MÉTODO 1
        
     

        leavesLeft = leftTree.getLeaves();
        factorsLeft = leftTree.getFactors(leftSize);
        meanLeft = VectorManipulator.vectorMean(leavesLeft, factorsLeft);
        
        
        
        leavesRight = rightTree.getLeaves();
        
        factorsRight = rightTree.getFactors(rightSize);
        meanRight = VectorManipulator.vectorMean(leavesRight, factorsRight);
        
        leaves = VectorManipulator.concat(leavesLeft, leavesRight);
        mean = VectorManipulator.concat(meanLeft, meanRight);
        factors = VectorManipulator.concat(factorsLeft, factorsRight);
 /*
        VectorManipulator.print(factors);
       System.out.println(variable);
        System.out.println("leftTree");
        leftTree.print(1);
        System.out.println("rightTree");
        rightTree.print(1);
       */ 
        
        /* Computes the distance between the leaves and the mean of each subtree.
         By default it uses euclidean distance*/
        try {

            if(method == pruningMethods.EUCLIDEAN_EXP)
                totalD = 1 - Distances.euclidean_exp(mean, leaves);
            else if(method == pruningMethods.EUCLIDEAN_NORM)
                totalD = 1 - Distances.euclidean_norm(mean, leaves);
            else if(method == pruningMethods.COSINE)
                totalD = 1 - Distances.cosine(mean, leaves);
            else if(method == pruningMethods.EXT_JACCARD)
                totalD = 1 - Distances.ext_jaccard(mean, leaves);
            else if(method == pruningMethods.KULLBACK_LEIBLER_DISTANCE)
                totalD = Distances.kullbackLeibler(mean, leaves);
            else if(method == pruningMethods.RELATIVE2)
                totalD = Distances.distanciaRelativa2(mean, leaves);
            else if(method == pruningMethods.PERCENT_ERROR)
                totalD = Distances.percentError(mean, leaves);
            else
                totalD = Distances.euclidean(mean, leaves, factors);
            
        } catch (Exception ex) {
            System.out.println(ex);
            ex.printStackTrace();
        }

       
        d2 = totalD;
        
        
        
        

        mean = VectorManipulator.vectorMean(leaves, factors);

        try {

            if(method == pruningMethods.EUCLIDEAN_EXP)
                totalD = 1 - Distances.euclidean_exp(mean, leaves);
            else if(method == pruningMethods.EUCLIDEAN_NORM)
                totalD = 1 - Distances.euclidean_norm(mean, leaves);
            else if(method == pruningMethods.COSINE)
                totalD = 1 - Distances.cosine(mean, leaves);
            else if(method == pruningMethods.EXT_JACCARD)
                totalD = 1 - Distances.ext_jaccard(mean, leaves);
            else if(method == pruningMethods.KULLBACK_LEIBLER_DISTANCE)
                totalD = Distances.kullbackLeibler(mean, leaves);
            else if(method == pruningMethods.RELATIVE2)
                totalD = Distances.distanciaRelativa2(mean, leaves);
            else if(method == pruningMethods.PERCENT_ERROR)
                totalD = Distances.percentError(mean, leaves);
            else
                totalD = Distances.euclidean(mean, leaves, factors);
            
        } catch (Exception ex) {
            System.out.println(ex);
            ex.printStackTrace();
        }

        d1 = totalD;
        
        info = d1 - d2;

        return info;
        

        
    }
    /**
     * Computes the euclidiean distance between a tree expande once, with the completed
     * expanded
     * @param variable splitting variable
     * @param cutPoint splitting cut point, if the variable has n states, it can be from 1 to n-1
     * @param states list of states of the variable
     * @param potentialSize
     * @param leftTree reference variable for returing the left tree which results from the partition
     * @param rightTree  reference variable for returing the right tree which results from the partition
     * @return euclidean utility distance
     */

    public double utilityInfoGain(FiniteStates variable, int firstState, int cutPoint, BinaryProbabilityTree.ListStates states, long oldSize,
            BinaryProbabilityTree leftTree, BinaryProbabilityTree rightTree, pruningMethods method, double sum, double sumSqr,
            double sumChildren[], int index[]) {
    

        double d1=0, d2=0, info=0;
        double stop = 0;
        
        
        //Make the partitions
        BinaryProbabilityTree.ListStates leftPartition = states.getPartition(firstState, cutPoint+1, index);
        BinaryProbabilityTree.ListStates rightPartition = states.getPartition(cutPoint+1, index.length, index);


        /* Expands the tree once and computes the 
         * distance with the completed expanded tree.
         */
        
        
        
        //Information of the left tree
        leftTree.setTree(restrict(variable, leftPartition));
        //Information of the right tree
        rightTree.setTree(restrict(variable, rightPartition));

   /*     System.out.println("split point:");
        this.print(0);
        leftTree.print(0);
        rightTree.print(0);*/
        

        
        
        int nValidStatesLeft = leftPartition.getNumValidStates();
        int nValidStatesRight = rightPartition.getNumValidStates();
        int nValidStates = nValidStatesLeft + nValidStatesRight;

        long leftSize = (oldSize / nValidStates) * nValidStatesLeft;
        long rightSize = (oldSize / nValidStates) * nValidStatesRight;

        double sumLeaves;
        
        if(Double.isNaN(sum))
            sumLeaves = this.sum(oldSize);
        else
            sumLeaves = sum;
        
        double sumLeavesSqr;
        if(Double.isNaN(sumSqr))
            sumLeavesSqr = this.sumSqr(oldSize);
        else
            sumLeavesSqr = sumSqr;
        
        
        

        
        double sumLeft = leftTree.sum(leftSize);
        double sumLeftSqr = leftTree.sumSqr(leftSize);
        
        double sumRight = rightTree.sum(rightSize);
        double sumRightSqr = rightTree.sumSqr(rightSize);
        
        switch (method) {
            case EUCLIDEAN:
                d1 = Math.sqrt(sumLeavesSqr - Math.pow(sumLeaves, 2) / oldSize);
                d2 = Math.sqrt(sumLeftSqr - Math.pow(sumLeft, 2) / leftSize + sumRightSqr - Math.pow(sumRight, 2) / rightSize);

                //All the leaves are equal to 0, so the distance is set to 0 (are equal)
                if (Double.isNaN(d1)) {
                    d1 = 0;
                }
                if (Double.isNaN(d2)) {
                    d2 = 0;
                }

                info = d1 - d2;
                
                if(d1==0)
                    stop=1;


                break;
            case KULLBACK_LEIBLER_DISTANCE:
                
                info = (sumLeaves * probLogProb(1.0, nValidStates) - probLogProb(sumLeaves, sumLeaves))
                     - (sumLeft * probLogProb(1.0, nValidStatesLeft) + sumRight * probLogProb(1.0, nValidStatesRight)
                            - probLogProb(sumLeft, sumLeft) - probLogProb(sumRight, sumRight));
                


         /*       if(d1==0)
                    stop=1;*/
                
                break;

            case EUCLIDEAN_NORM:

                d1 = Math.sqrt(sumLeavesSqr - Math.pow(sumLeaves, 2) / oldSize);
                d2 = Math.sqrt(sumLeftSqr - Math.pow(sumLeft, 2) / leftSize + sumRightSqr - Math.pow(sumRight, 2) / rightSize);

                //All the leaves are equal to 0, so the distance is set to 0 (are equal)
                if (Double.isNaN(d1)) {
                    d1 = 0;
                }
                if (Double.isNaN(d2)) {
                    d2 = 0;
                }

                //normalizes
                d1 = 1 / (1 + d1);
                d2 = 1 / (1 + d2);

                //decreasing
                info = d2 - d1;

                if (info <= 0 && sumLeaves / oldSize != Math.sqrt(sumLeavesSqr / oldSize)) {
                    info = Double.MIN_VALUE;
                }
                
                
                if(d1==1)
                    stop=1;
                break;

            case EUCLIDEAN_EXP:

                d1 = Math.sqrt(sumLeavesSqr - Math.pow(sumLeaves, 2) / oldSize);
                d2 = Math.sqrt(sumLeftSqr - Math.pow(sumLeft, 2) / leftSize + sumRightSqr - Math.pow(sumRight, 2) / rightSize);

                //All the leaves are equal to 0, so the distance is set to 0 (are equal)
                if (Double.isNaN(d1)) {
                    d1 = 0;
                }
                if (Double.isNaN(d2)) {
                    d2 = 0;
                }

                //normalizes
                d1 = Math.exp(-Math.pow(d1, 2));
                d2 = Math.exp(-Math.pow(d2, 2));

                //decreasing
                info = d2 - d1;

                if (info <= 0 && sumLeaves / oldSize != Math.sqrt(sumLeavesSqr / oldSize)) {
                    info = Double.MIN_VALUE;
                }
                if(d1==1)
                    stop=1;
                
                break;
            case COSINE:
                d1 = (Math.pow(sumLeaves, 2) / oldSize) / (Math.sqrt(sumLeavesSqr) * Math.sqrt(Math.pow(sumLeaves, 2) / oldSize));
                d2 = (Math.pow(sumLeft, 2) / leftSize + Math.pow(sumRight, 2) / rightSize) / (Math.sqrt(sumLeftSqr + sumRightSqr) * Math.sqrt(Math.pow(sumLeft, 2) / leftSize + Math.pow(sumRight, 2) / rightSize));

                //All the leaves are equal to 0, so the distance is set to 1 (are equal)
                if (Double.isNaN(d1)) {
                    d1 = 1;
                }
                if (Double.isNaN(d2)) {
                    d2 = 1;
                }

                info = d2 - d1;


                if (info <= 0 && sumLeaves / oldSize != Math.sqrt(sumLeavesSqr / oldSize)) {
                    info = Double.MIN_VALUE;
                }
                
                if(d1==1)
                    stop=1; 
                
                break;

            case EXT_JACCARD:

                /*d1 = 1 / (sumLeavesSqr - 1);
                d2 = 1 / (sumLeftSqr + sumRightSqr - 1);
               
                
                
                */

                /*d1 = (Math.pow(sumLeaves, 2)/oldSize)/sumLeavesSqr;
                d2 = (Math.pow(sumLeft, 2)/leftSize + Math.pow(sumRight, 2)/rightSize)/sumLeavesSqr;
*/
                
                d1 = (Math.pow(sumLeaves, 2) / oldSize) / ((sumLeavesSqr));
                d2 = (Math.pow(sumLeft, 2) / leftSize + Math.pow(sumRight, 2) / rightSize) / ((sumLeftSqr + sumRightSqr));
                
                
                
                //All the leaves are equal to 0, so the distance is set to 1 (are equal)
                if (Double.isNaN(d1)) {
                    d1 = 1;
                }
                if (Double.isNaN(d2)) {
                    d2 = 1;
                }

                //decreasing 
                info = d2 - d1;
                
 /*               Vector leftLeaves = leftTree.getLeavesExpanded();
                Vector rightLeaves = rightTree.getLeavesExpanded();
                Vector leaves = VectorManipulator.concat(leftLeaves, rightLeaves);
                
                Vector mean = VectorManipulator.vectorMean(leaves);
                Vector meanL = VectorManipulator.vectorMean(leftLeaves);
                Vector meanR = VectorManipulator.vectorMean(rightLeaves);
                Vector meanLR = VectorManipulator.concat(meanL, meanR);
            
                
        double d1p = -1, d2p=-1;        
        try {
            d1p = Distances.ext_jaccard(mean, leaves);
            d2p = Distances.ext_jaccard(meanLR, leaves);
        } catch (Exception ex) {
            Logger.getLogger(BinaryProbabilityTree.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        double infop = d2p-d1p;
        if(Math.abs(infop-info)>0.01) {
            System.out.println(d2+"-"+d1+"="+info+"\t"+d2p+"-"+d1p+"="+infop+"\t"+(Math.abs(infop-info)));
        }

*/


            /*    if (info <= 0 && sumLeaves / oldSize != Math.sqrt(sumLeavesSqr / oldSize)) {
                    info = Double.MIN_VALUE;
                }*/
                
                if(d1==1)
                    stop=1;
                
                break;
            default:
                System.err.print("ERROR: wrong pruning method");
                System.exit(1);


        }





        sumChildren[0] = sumLeft;
        sumChildren[1] = sumRight;
        sumChildren[2] = sumLeftSqr;
        sumChildren[3] = sumRightSqr;
        sumChildren[4] = stop;


        return info;


    }

    /**
     * Computes the euclidiean distance between a tree expande once, with the completed
     * expanded
     * @param variable splitting variable
     * @param cutPoint splitting cut point, if the variable has n states, it can be from 1 to n-1
     * @param states list of states of the variable
     * @param potentialSize
     * @param leftTree reference variable for returing the left tree which results from the partition
     * @param rightTree  reference variable for returing the right tree which results from the partition
     * @return euclidean utility distance
     */

    public double utilityInfoGain(FiniteStates variable, int firstState, int cutPoint, BinaryProbabilityTree.ListStates states, long oldSize,
            BinaryProbabilityTree leftTree, BinaryProbabilityTree rightTree, pruningMethods method, double sum, double sumSqr,
            double sumChildren[]) {
    

        double d1, d2, info;
        
        
        //Make the partitions
        BinaryProbabilityTree.ListStates leftPartition = states.getPartition(firstState, cutPoint+1);
        BinaryProbabilityTree.ListStates rightPartition = states.getPartition(cutPoint+1, states.getNumStates());


        /* Expands the tree once and computes the 
         * distance with the completed expanded tree.
         */
        
        
        
        //Information of the left tree
        leftTree.setTree(restrict(variable, leftPartition));
        //Information of the right tree
        rightTree.setTree(restrict(variable, rightPartition));

        
        int nValidStatesLeft = leftPartition.getNumValidStates();
        int nValidStatesRight = rightPartition.getNumValidStates();
        int nValidStates = nValidStatesLeft + nValidStatesRight;

        long leftSize = (oldSize / nValidStates) * nValidStatesLeft;
        long rightSize = (oldSize / nValidStates) * nValidStatesRight;

        double sumLeaves;
        
        if(Double.isNaN(sum))
            sumLeaves = this.sum(oldSize);
        else
            sumLeaves = sum;
        
        double sumLeavesSqr;
        if(Double.isNaN(sumSqr))
            sumLeavesSqr = this.sumSqr(oldSize);
        else
            sumLeavesSqr = sumSqr;
        
        

        double sumLeft = leftTree.sum(leftSize);
        double sumLeftSqr = leftTree.sumSqr(leftSize);
        
        double sumRight = rightTree.sum(rightSize);
        double sumRightSqr = rightTree.sumSqr(rightSize);
        
        
        d1 = Math.sqrt(sumLeavesSqr - Math.pow(sumLeaves, 2)/oldSize);
        d2 = Math.sqrt(sumLeftSqr - Math.pow(sumLeft,2)/leftSize + sumRightSqr - Math.pow(sumRight,2)/rightSize);

        if(Double.isNaN(d1)) 
           d1 = 0;
        if(Double.isNaN(d2)) 
            d2 = 0;               

        
        
        info = d1-d2;
        
        

        sumChildren[0] = sumLeft;
        sumChildren[1] = sumRight;
        sumChildren[2] = sumLeftSqr;
        sumChildren[3] = sumRightSqr;
        
        return info;
        
        
    }
    
    


      public double utilityDistance(Vector leavesLeft, Vector leavesRight, Vector factorsLeft, Vector factorsRight, pruningMethods method) {

        long newSize;
        double s = 0.0, totalD = 0.0;
        Vector ceroSumms=new Vector();
        ceroSumms.addElement(new Double(0));
        Vector meanLeft, meanRight, leaves, mean;
        Vector factors;




        meanLeft = VectorManipulator.vectorMean(leavesLeft, factorsLeft);
        meanRight = VectorManipulator.vectorMean(leavesRight, factorsRight);

        leaves = VectorManipulator.concat(leavesLeft, leavesRight);
        mean = VectorManipulator.concat(meanLeft, meanRight);
        factors = VectorManipulator.concat(factorsLeft, factorsRight);

        /* Computes the distance between the leaves and the mean of each subtree.
         By default it uses euclidean distance*/
        try {

            if(method == pruningMethods.EUCLIDEAN_EXP)
                totalD = 1 - Distances.euclidean_exp(mean, leaves);
            else if(method == pruningMethods.EUCLIDEAN_NORM)
                totalD = 1 - Distances.euclidean_norm(mean, leaves);
            else if(method == pruningMethods.COSINE)
                totalD = 1 - Distances.cosine(mean, leaves);
            else if(method == pruningMethods.EXT_JACCARD)
                totalD = 1 - Distances.ext_jaccard(mean, leaves);
            else if(method == pruningMethods.KULLBACK_LEIBLER_DISTANCE)
                totalD = Distances.kullbackLeibler(mean, leaves);
            else if(method == pruningMethods.RELATIVE2)
                totalD = Distances.distanciaRelativa2(mean, leaves);
            else if(method == pruningMethods.PERCENT_ERROR)
                totalD = Distances.percentError(mean, leaves);
            else
                totalD = Distances.euclidean(mean, leaves, factors);

        } catch (Exception ex) {
            System.out.println(ex);
            ex.printStackTrace();
        }


        double d2 = totalD;





        mean = VectorManipulator.vectorMean(leaves, factors);

        try {

            if(method == pruningMethods.EUCLIDEAN_EXP)
                totalD = 1 - Distances.euclidean_exp(mean, leaves);
            else if(method == pruningMethods.EUCLIDEAN_NORM)
                totalD = 1 - Distances.euclidean_norm(mean, leaves);
            else if(method == pruningMethods.COSINE)
                totalD = 1 - Distances.cosine(mean, leaves);
            else if(method == pruningMethods.EXT_JACCARD)
                totalD = 1 - Distances.ext_jaccard(mean, leaves);
            else if(method == pruningMethods.KULLBACK_LEIBLER_DISTANCE)
                totalD = Distances.kullbackLeibler(mean, leaves);
            else if(method == pruningMethods.RELATIVE2)
                totalD = Distances.distanciaRelativa2(mean, leaves);
            else if(method == pruningMethods.PERCENT_ERROR)
                totalD = Distances.percentError(mean, leaves);
            else
                totalD = Distances.euclidean(mean, leaves, factors);

        } catch (Exception ex) {
            System.out.println(ex);
            ex.printStackTrace();
        }

        double d1 = totalD;

        return d1 -d2;


    }

    
    
    

    
        /**
     * Computes the euclidiean distance of a variable within a tree, but
     * the potential represents an utility function.
     * @param potentialSize, the maximun size of the potential
     * containing the tree (i.e. the size the tree would have if
     * were completely extended).
     * @return the value of information variable
     */
    
    public double utilityDistance(long potentialSize, pruningMethods method) {
        BinaryProbabilityTree tree;
        int i, nv;
        long newSize;
        double s = 0.0, totalD = 0.0;
        Vector ceroSumms=new Vector();
        ceroSumms.addElement(new Double(0));
        
        
        //s = this.sum(potentialSize)/potentialSize;
        //totalD = this.sumUtilitySquaredDistances(s,ceroSumms);
        
        
   //     Vector factors = this.getFactors();
        Vector leaves = this.getLeaves();
        Vector mean = VectorManipulator.vectorMean(leaves);


          /* Computes the distance between the leaves and the mean.
         By default it uses euclidean distance*/
        try {

            if(method == pruningMethods.EUCLIDEAN_EXP)
                totalD = 1 - Distances.euclidean_exp(mean, leaves);
            else if(method == pruningMethods.EUCLIDEAN_NORM)
                totalD = 1 - Distances.euclidean_norm(mean, leaves);
            else if(method == pruningMethods.COSINE)
                totalD = 1 - Distances.cosine(mean, leaves);
            else if(method == pruningMethods.EXT_JACCARD)
                totalD = 1 - Distances.ext_jaccard(mean, leaves);
            else if(method == pruningMethods.KULLBACK_LEIBLER_DISTANCE)
                totalD = Distances.kullbackLeibler(mean, leaves);
            else if(method == pruningMethods.RELATIVE2)
                totalD = Distances.distanciaRelativa2(mean, leaves);
            else if(method == pruningMethods.PERCENT_ERROR)
                totalD = Distances.percentError(mean, leaves);
            else
                totalD = Distances.euclidean(mean, leaves);
            
        } catch (Exception ex) {
            System.out.println(ex);
        }

        
        
        //double ceros=((Double)ceroSumms.elementAt(0)).doubleValue();
       // totalD=totalD-totalD*(ceros/potentialSize);
        
        // Get the square root
       // totalD=Math.sqrt(totalD);
        
        // Return the final distance
        
        return (totalD)/potentialSize;
    }
        
    
    
    

    /**
     * Constructs a <code>BinaryProbabilityTree</code> from a Potential of
     * any class.
     * @param pot the <code>Potential</code> to convert to a BinaryProbabilityTree.
     * @return a <code>BinaryProbabilityTree</code> that represents the same
     * Potential that <code>pot</code>
     */
    public static BinaryProbabilityTree getTreeFromPotential(Potential pot) {
        int nVars = pot.getVariables().size();
        BinaryProbabilityTree tree = new BinaryProbabilityTree();
        BinaryProbabilityTree.ListStates listStatesVector[] = new BinaryProbabilityTree.ListStates[nVars];
        for (int i = 0; i < nVars; i++) {
            listStatesVector[i] = new BinaryProbabilityTree.ListStates((FiniteStates) (pot.getVariables().get(i)));
        }

        getTreeFromPotential(pot, tree, 0, listStatesVector);
        return tree;
    }

    /**
     * Recursive method that constructs a <code>BinaryProbabilityTree</code> from
     * a Potential of any class. This is an auxiliary method for
     * <code>getTreeFromPotential(Potential)</code>. The new tree is returned with
     * the parameter <code>tree</code>
     * @param pot the <code>Potential</code> to convert to a BinaryProbabilityTree.
     * @param tree the <code>BinaryProbabilityTree</code> we are constructing.
     *  In the first call to the method it must be a new BinaryProbalityTree
     * @param varToExplore the number of variable in the Potential pot to explore.
     * It must be 0 in the first call to the method.
     * @param listStatesVector[] an array of ListStates. Although, it is modified
     * during the running of this method, when it finishes, the list will be the
     * original one. In the first call to the method it must contains a ListStates
     * for each variable in Potential pot (with all states set to active)
     */
    static private void getTreeFromPotential(Potential pot, BinaryProbabilityTree tree,
            int varToExplore, BinaryProbabilityTree.ListStates listStatesVector[]) {
        if (varToExplore == pot.getVariables().size()) // if all the variables have been explored
        {
            Configuration config = new Configuration();
            /*  make a Configuration with the selected states in the current
            listStatesVector */
            for (int i = 0; i < listStatesVector.length; i++) {
                config.insert((FiniteStates) pot.getVariables().elementAt(i),
                        listStatesVector[i].getFirstValidState(0));
            }

            
            tree.assignProb(pot.getValue(config));
        } else {
            int state = 0;
            BinaryProbabilityTree.ListStates listStates;

            FiniteStates firstVar = (FiniteStates) pot.getVariables().elementAt(varToExplore);

            BinaryProbabilityTree.ListStates firstListStates = listStatesVector[varToExplore];
            int nvalidstates = firstListStates.numberValidStates();
            int lenLeftListStates = (nvalidstates + 1) / 2;
            int lenRightListStates = nvalidstates - lenLeftListStates;

            tree.var = firstVar;

            /* Proccess left branch */
            if (lenLeftListStates == 1) {
                listStates = new BinaryProbabilityTree.ListStates(firstVar.getNumStates());
                state = firstListStates.getFirstValidState(0);
                listStates.activateStateAt(state);

                tree.leftChild = new BinaryProbabilityTree.BinaryProbabilityTreeInfoChild();
                tree.leftChild.setInfo(new BinaryProbabilityTree(), listStates);
                listStatesVector[varToExplore] = listStates;
                getTreeFromPotential(pot, tree.leftChild.child, varToExplore + 1, listStatesVector);
            } else if (lenLeftListStates > 1) {
                listStates = new BinaryProbabilityTree.ListStates(firstVar.getNumStates());
                //Activate the lenLeftListStates first valid states
                state = -1;
                for (int i = 0; i < lenLeftListStates; i++) {
                    state = firstListStates.getFirstValidState(state + 1);
                    listStates.activateStateAt(state);
                }
                listStatesVector[varToExplore] = listStates;

                tree.leftChild = new BinaryProbabilityTree.BinaryProbabilityTreeInfoChild();
                tree.leftChild.setInfo(new BinaryProbabilityTree(), listStates);
                getTreeFromPotential(pot, tree.leftChild.child, varToExplore, listStatesVector);
            } else {
                System.out.println("Error in BinaryProbabilityTree.getTreeFromPotential():"
                        + "lenLeftListStates=" + lenLeftListStates);
                System.exit(1);
            }

            /* Proccess right branch */
            state++;
            if (lenRightListStates == 1) {
                listStates = new BinaryProbabilityTree.ListStates(firstVar.getNumStates());
                state = firstListStates.getFirstValidState(state);
                listStates.activateStateAt(state);

                tree.rightChild = new BinaryProbabilityTree.BinaryProbabilityTreeInfoChild();
                tree.rightChild.setInfo(new BinaryProbabilityTree(), listStates);

                listStatesVector[varToExplore] = listStates;
                getTreeFromPotential(pot, tree.rightChild.child, varToExplore + 1, listStatesVector);
            } else if (lenRightListStates > 1) {
                //Activate the following lenRightfListStates valid states
                listStates = new BinaryProbabilityTree.ListStates(firstVar.getNumStates());

                state--;
                for (int i = 0; i < lenRightListStates; i++) {
                    state = firstListStates.getFirstValidState(state + 1);
                    listStates.activateStateAt(state);
                }
                listStatesVector[varToExplore] = listStates;

                tree.rightChild = new BinaryProbabilityTree.BinaryProbabilityTreeInfoChild();
                tree.rightChild.setInfo(new BinaryProbabilityTree(), listStates);
                getTreeFromPotential(pot, tree.rightChild.child, varToExplore, listStatesVector);
            } else {
                System.out.println("Error in BinaryProbabilityTree.getTreeFromPotential():"
                        + "lenRightListStates=" + lenRightListStates);
                try {
                    throw new Exception();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                pot.print();
                System.exit(1);
            }
            /*In listStatesVector, restore  the old listStates at position varToExplore*/
            listStatesVector[varToExplore] = firstListStates;
        }
    }

    /**
     * Constructs a <code>BinaryProbabilityTree</code> from a PTreeCredalSet.
     * The PTreeCredalSet must represent a conditional distribution. In this three
     * parent variables should be at the top levels of the tree, then we have
     * a transparent variable for each configuration of the parents variables,
     * and finally we have the conditional variable in the botton level of the
     * tree.
     * @param pot the <code>PTreeCredalSet</code> to be converted to
     * a BinaryProbabilityTree.
     * @return a <code>BinaryProbabilityTree</code> that represents the same
     * Potential that <code>pot</code>
     */
    static BinaryProbabilityTree getTreeFromPTreeCredalSet(
            elvira.potential.PTreeCredalSet pot) {
        ArrayList<FiniteStates> variablesOrder=new ArrayList<FiniteStates>();       
        FiniteStates transparentVar=null;
        int varToExplore=0;
        int typeOfSplitting=-1;

        elvira.potential.ProbabilityTree ptree=pot.getTree();
        while(ptree.isVariable()){ //Make the list of non-transparent variables
            if(ptree.getVar().getTransparency()!=FiniteStates.TRANSPARENT)
                variablesOrder.add(ptree.getVar());
            ptree=ptree.getChild(0);
        }

        BinaryProbabilityTree tree = new BinaryProbabilityTree();
        BinaryProbabilityTree.ListStates listStatesVector[] = new BinaryProbabilityTree.ListStates[variablesOrder.size()+1];

        for (int i = 0; i < variablesOrder.size(); i++) {
            listStatesVector[i] = new BinaryProbabilityTree.ListStates(variablesOrder.get(i));
        }
      
        if(variablesOrder.size()==1){ // pot with only one non-transparent var
            typeOfSplitting=2; // Begin splitting a transparent variable
            varToExplore=1;
            transparentVar=getTransparentFromPTreeCredalSet(
                                pot.getTree(),variablesOrder,listStatesVector);
            listStatesVector[variablesOrder.size()] = new BinaryProbabilityTree.ListStates(transparentVar);
        }
        else if(variablesOrder.size()>1){
            typeOfSplitting=0; // Begin splitting a parent variable in pot
        }
        else{
            System.out.println("Error in BinaryProbabilityTree.getTreeFromPTreeCredalSet(): "+
                    "variablesOrder.size<=0");
            System.exit(0);
        }

        getTreeFromPTreeCredalSet(pot, variablesOrder, typeOfSplitting, tree, 
                transparentVar, varToExplore, listStatesVector);
        return tree;
    }

    /**
     * Recursive method that constructs a <code>BinaryProbabilityTree</code> from
     * a PTreeCredalSet. This is an auxiliary method for
     * <code>getTreeFromPTreeCredalSet(Potential)</code>. The new tree is returned with
     * the parameter <code>tree</code>
     * @param pot the <code>PTreeCredalSet</code> to be converted to a
     * BinaryProbabilityTree.
     * @param variablesOrder a list with the non-transparent variables
     * in pot. The variables will be splitted in the new BinaryProbabilityTree
     * according to the order in variablesOrder.
     * @param typeOfSplitting the type of variable to be splitted (
     * 0: a parent variable of pot. 1: child variable of pot.
     * 2: a transparent variable. 3: all the variables have been explored
     * @param tree the <code>BinaryProbabilityTree</code> we are constructing.
     *  In the first call to the method it must be a new BinaryProbalityTree
     * @param transparentVar A transparent variable if a transparent is to be
     * splitted.
     * @param varToExplore the number of variable in the Potential pot to explore.
     * It must be 0 in the first call to the method or 1 (to split toe transparent
     * variable of a potential with only one non-transparent variable)
     * @param listStatesVector[] an array of ListStates. Although, it is modified
     * during the running of this method, when it finishes, the list will be the
     * original one. In the first call to the method it must contains a ListStates
     * for each non-transparent variable in Potential pot
     * (with all states set to active), according to the order specified by
     * variablesOrder. An extra position is used at the end of this array, to be
     * used with a transparent variable.
     */
    static private void getTreeFromPTreeCredalSet(
            elvira.potential.PTreeCredalSet pot,
            ArrayList<FiniteStates> variablesOrder,
            int typeOfSplitting,
            BinaryProbabilityTree tree,
            FiniteStates transparentVar,
            int varToExplore, BinaryProbabilityTree.ListStates listStatesVector[]) {
        if (typeOfSplitting == 3) // if all the variables have been explored
        {
            Configuration config = new Configuration();
            /*  make a Configuration with the selected states in the current
            listStatesVector */
            for (int i = 0; i < (listStatesVector.length - 1); i++) {
                config.insert(variablesOrder.get(i),
                        listStatesVector[i].getFirstValidState(0));
            }
            config.insert(transparentVar,
                    listStatesVector[listStatesVector.length - 1].getFirstValidState(0));

            tree.assignProb(pot.getValue(config));
        } else {
            FiniteStates firstVar = null;

            if (typeOfSplitting == 0) { //Splitting a parent variable of pot
                firstVar = variablesOrder.get(varToExplore);              
            } else if (typeOfSplitting == 1) { // Splitting child variable of pot
                firstVar = variablesOrder.get(varToExplore);              
            } else if (typeOfSplitting == 2) { // Splitting a transparent variable
                firstVar = transparentVar;     
                if(firstVar.getNumStates()==1){ // If the transparent has only 1 state
                    getTreeFromPTreeCredalSet(pot, variablesOrder, 1,
                            tree, transparentVar,
                            variablesOrder.size() - 1, listStatesVector);
                    return;
                }
            }

            
            BinaryProbabilityTree.ListStates firstListStates = listStatesVector[varToExplore];
            int nvalidstates = firstListStates.numberValidStates();
            int lenLeftListStates = (nvalidstates + 1) / 2;
            int lenRightListStates = nvalidstates - lenLeftListStates;
            int state = 0;
            BinaryProbabilityTree.ListStates listStates;
            
            tree.var = firstVar;

            /* Proccess left branch */
            if (lenLeftListStates == 1) {
                listStates = new BinaryProbabilityTree.ListStates(firstVar.getNumStates());
                state = firstListStates.getFirstValidState(0);
                listStates.activateStateAt(state);

                tree.leftChild = new BinaryProbabilityTree.BinaryProbabilityTreeInfoChild();
                tree.leftChild.setInfo(new BinaryProbabilityTree(), listStates);
                listStatesVector[varToExplore] = listStates;

                if (typeOfSplitting == 0) { //Splitting a parent variable of pot
                    if (varToExplore == variablesOrder.size() - 2) {//Splitting the last parent variable of pot
                        transparentVar=getTransparentFromPTreeCredalSet(
                                pot.getTree(),variablesOrder,listStatesVector);
                        listStatesVector[variablesOrder.size()] = new BinaryProbabilityTree.ListStates(transparentVar);                        
                        getTreeFromPTreeCredalSet(pot, variablesOrder, 2, 
                                tree.leftChild.child,
                                transparentVar, listStatesVector.length - 1,
                                listStatesVector);
                    } else {
                        getTreeFromPTreeCredalSet(pot, variablesOrder, 0, 
                                tree.leftChild.child,
                                null, varToExplore + 1, listStatesVector);
                    }
                } else if (typeOfSplitting == 1) { // Splitting the child variable of pot
                    getTreeFromPTreeCredalSet(pot, variablesOrder, 3, 
                            tree.leftChild.child, transparentVar,
                            -1, listStatesVector);
                } else if (typeOfSplitting == 2) { // Splitting a transparent variable
                    getTreeFromPTreeCredalSet(pot, variablesOrder, 1, 
                            tree.leftChild.child, transparentVar,
                            variablesOrder.size() - 1, listStatesVector);
                }
            } else if (lenLeftListStates > 1) {
                listStates = new BinaryProbabilityTree.ListStates(firstVar.getNumStates());
                //Activate the lenLeftListStates first valid states
                state = -1;
                for (int i = 0; i < lenLeftListStates; i++) {
                    state = firstListStates.getFirstValidState(state + 1);
                    listStates.activateStateAt(state);
                }
                listStatesVector[varToExplore] = listStates;

                tree.leftChild = new BinaryProbabilityTree.BinaryProbabilityTreeInfoChild();
                tree.leftChild.setInfo(new BinaryProbabilityTree(), listStates);
                getTreeFromPTreeCredalSet(pot, variablesOrder, typeOfSplitting,
                        tree.leftChild.child, transparentVar,
                        varToExplore, listStatesVector);
            } else {
                System.out.println("Error in BinaryProbabilityTree.getTreeFromPTreeCredalSet():"
                        + "lenLeftListStates=" + lenLeftListStates);
                System.exit(1);
            }

            /* Proccess right branch */
            state++;
            if (lenRightListStates == 1) {
                listStates = new BinaryProbabilityTree.ListStates(firstVar.getNumStates());
                state = firstListStates.getFirstValidState(state);
                listStates.activateStateAt(state);

                tree.rightChild = new BinaryProbabilityTree.BinaryProbabilityTreeInfoChild();
                tree.rightChild.setInfo(new BinaryProbabilityTree(), listStates);
                listStatesVector[varToExplore] = listStates;

                if (typeOfSplitting == 0) { //Splitting a parent variable of pot
                    if (varToExplore == variablesOrder.size() - 2) {//Splitting the last parent variable of pot                       
                        transparentVar=getTransparentFromPTreeCredalSet(
                                pot.getTree(),variablesOrder,listStatesVector);
                        listStatesVector[variablesOrder.size()] = new BinaryProbabilityTree.ListStates(transparentVar);
                        getTreeFromPTreeCredalSet(pot, variablesOrder, 2, 
                                tree.rightChild.child,
                                transparentVar, listStatesVector.length - 1,
                                listStatesVector);
                    } else {
                        getTreeFromPTreeCredalSet(pot, variablesOrder, 0, 
                                tree.rightChild.child,
                                null, varToExplore + 1, listStatesVector);
                    }
                } else if (typeOfSplitting == 1) { // Splitting the child variable of pot
                    getTreeFromPTreeCredalSet(pot, variablesOrder, 3, 
                            tree.rightChild.child, transparentVar,
                            -1, listStatesVector);
                } else if (typeOfSplitting == 2) { // Splitting a transparent variable
                    getTreeFromPTreeCredalSet(pot, variablesOrder, 1, 
                            tree.rightChild.child, transparentVar,
                            variablesOrder.size() - 1, listStatesVector);
                }
            } else if (lenRightListStates > 1) {
                listStates = new BinaryProbabilityTree.ListStates(firstVar.getNumStates());

                //Activate the lenRightListStates first valid states
                state--;
                for (int i = 0; i < lenRightListStates; i++) {
                    state = firstListStates.getFirstValidState(state + 1);
                    listStates.activateStateAt(state);
                }
                listStatesVector[varToExplore] = listStates;

                tree.rightChild = new BinaryProbabilityTree.BinaryProbabilityTreeInfoChild();
                tree.rightChild.setInfo(new BinaryProbabilityTree(), listStates);
                getTreeFromPTreeCredalSet(pot, variablesOrder, typeOfSplitting,
                        tree.rightChild.child, transparentVar,
                        varToExplore, listStatesVector);
            } else {
                System.out.println("Error in BinaryProbabilityTree.getTreeFromPTreeCredalSet():"
                        + "lenRightListStates=" + lenRightListStates);
                try {
                    throw new Exception();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                pot.print();
                System.exit(1);
            }
            /*In listStatesVector, restore  the old listStates at position varToExplore*/
            listStatesVector[varToExplore] = firstListStates;
        }
    }
    
    
    public FiniteStates getFirstTransparentVar() {
    
        FiniteStates T = null; 
        
        if(isProbab()) {
            T=null;
        }else if(var.isTransparent()){
            T = var;
        }else {
            if(leftChild != null) {
                T = leftChild.getChild().getFirstTransparentVar();
            }else if(T == null && rightChild != null){
                T = rightChild.getChild().getFirstTransparentVar();
            
            }
        }
        
        return T;
    
    }
    

    /**
     * Gets from a ProbabilityTree the transparent variable consistent with
     * the states defined by variablesOrder and listStatesVector. This is
     * an auxiliary method for getTreeFromPTreeCredalSet()
     * @param pTree
     * @param variablesOrder
     * @param listStatesVector
     * @return The transparent variable consistent with
     * the states defined by variablesOrder and listStatesVector. This is
     * an auxiliary method for getTreeFromPTreeCredalSet()
     */
    private static FiniteStates getTransparentFromPTreeCredalSet(
            elvira.potential.ProbabilityTree pTree,
            ArrayList<FiniteStates> variablesOrder,
            BinaryProbabilityTree.ListStates listStatesVector[]) {
        FiniteStates transparentVar;

        // Find in pTree the transparent variable consistent with the
        // configuration defined by listStatesVector
        for (int i = 0; i < (variablesOrder.size() - 1); i++) {
            if (pTree.getVar() != variablesOrder.get(i)) {
                System.out.println("Error in getTransparentFromPTreeCredalSet(): "
                        + "found " + pTree.getVar().getName() + " and should be "
                        + variablesOrder.get(i).getName());
                System.exit(0);
            }
            pTree = pTree.getChild(listStatesVector[i].getFirstValidState(0));
        }
        transparentVar = pTree.getVar();
        if (transparentVar.getTransparency() != FiniteStates.TRANSPARENT) {
            System.out.println("Error in getTransparentFromPTreeCredalSet(): "
                    + "firstVar: " + transparentVar.getName() + " should be transparent");
            System.exit(0);
        }
        return transparentVar;
    }

    /**
     * Constructs a <code>BinaryProbabilityTree</code> from another one.
     * The new tree is build so that the more informative variables are
     * in the upper levels of the tree and variables are splitted also trying to
     * maximaze such measure of information.
     * @see getSortedTreeFromBinaryPT(BinaryProbabilityTree originalTree,
    long treeSizrt,
    FiniteStates varsToExploreVector[], ListStates listStatesVector[],
    BinaryProbabilityTree tree)
     * @param binaryPT the <code>BinaryProbabilityTree</code> to convert to a
     * sorted BinaryProbabilityTree.
     * @param variables a Vector with the variables of the Potential
     * @return a <code>BinaryProbabilityTree</code> that represents the same
     * Potential that <code>binaryPT</code>
     */
    static BinaryProbabilityTree getSortedAndPrunedTreeFromBinaryPT(BinaryProbabilityTree binaryPT,
            java.util.Vector variables, double threshold, boolean sortStates) {
        
        int nVars = variables.size();
        FiniteStates varArray[] = new FiniteStates[nVars];
        BinaryProbabilityTree.ListStates listStatesVector[] = new BinaryProbabilityTree.ListStates[nVars];
        BinaryProbabilityTree tree = new BinaryProbabilityTree();
        for (int i = 0; i < nVars; i++) {
            varArray[i] = (FiniteStates) (variables.get(i));
            listStatesVector[i] = new BinaryProbabilityTree.ListStates(varArray[i]);
        }

        
        getSortedAndPrunedTreeFromBinaryPT(binaryPT, (long) FiniteStates.getSize(variables), varArray, listStatesVector, tree, threshold, sortStates);

        return tree;
    }
    

       /**
     * Constructs a <code>BinaryProbabilityTree</code> from another one.
     * The new tree is build so that the more informative variables are
     * in the upper levels of the tree and variables are splitted also trying to
     * maximaze such measure of information.
     * @see getSortedTreeFromBinaryPT(BinaryProbabilityTree originalTree,
    long treeSize,
    FiniteStates varsToExploreVector[], ListStates listStatesVector[],
    BinaryProbabilityTree tree)
     * @param binaryPT the <code>BinaryProbabilityTree</code> to convert to a
     * sorted BinaryProbabilityTree.
     * @param variables a Vector with the variables of the Potential
     * @return a <code>BinaryProbabilityTree</code> that represents the same
     * Potential that <code>binaryPT</code>
     */
    static BinaryProbabilityTree getSortedTreeFromBinaryPT(BinaryProbabilityTree binaryPT,
            java.util.Vector variables, boolean sortStates) {
        
        int nVars = variables.size();
        FiniteStates varArray[] = new FiniteStates[nVars];
        BinaryProbabilityTree.ListStates listStatesVector[] = new BinaryProbabilityTree.ListStates[nVars];
        BinaryProbabilityTree tree = new BinaryProbabilityTree();
        for (int i = 0; i < nVars; i++) {
            varArray[i] = (FiniteStates) (variables.get(i));
            listStatesVector[i] = new BinaryProbabilityTree.ListStates(varArray[i]);
        }

        getSortedTreeFromBinaryPT(binaryPT, (long) FiniteStates.getSize(variables),
                varArray, listStatesVector, tree, sortStates);
        return tree;
    }
     

    
    /**
     * Recursive method that constructs a sorted <code>BinaryProbabilityTree</code>
     * (tree where more informative variables are in the upper levels, and variables
     * are splitted also trying to maximize an information measure)
     * from another BinaryProbabilityTree. This is an auxiliary method for
     * <code>getSortedTreeFromBinaryPT(BinaryProbabilityTree binaryPT,
     *       java.util.Vector variables,long treeSize)</code>.
     * The new tree is returned with
     * the parameter <code>tree</code>
     * @param originalTree the BinaryProbabilityTree to be transformed into a new
     * sorted BinaryProbalityTree. In the successive recursive calls new originalTrees
     * are obtained by restricting to a given ListStates.
     * @param treeSize size of the completely expanded tree, that is, the number
     * of possible configurations of the variables in this potential. This parameter
     * could be obtained traversing the array listStatesVector (another parameter
     * of this method) by multiplying the number of valid states at each position,
     * but it is appended as a parameter in order to avoid recalculating it at
     * every recursive call.
     * @param varToExploreVector[] an array with all the variables in the Potential.
     * Each position i contains variable i of the Potential.
     * In the first call to the method, it must contains a variable of the Potential
     * at every position.
     * The array is modified during the running of this method: null is set at position i
     * if variable i has been yet completly explored at the current branch. At the
     * end of the method the array will be restored to the original one.
     * @param listStatesVector[] an array of ListStates. Although, it is modified
     * during the running of this method, when it finishes, the list will be the
     * original one. In the first call to the method, it must contains a ListStates
     * for each variable in the Potential(with all states set to active)
     * @param tree the <code>BinaryProbabilityTree</code> we are constructing.
     *  In the first call to the method it must be a new BinaryProbalityTree
     */
    static private void getSortedTreeFromBinaryPT(BinaryProbabilityTree originalTree,
            long treeSize,
            FiniteStates varsToExploreVector[], BinaryProbabilityTree.ListStates listStatesVector[],
            BinaryProbabilityTree tree, boolean sortStates) {
        
        

        if (originalTree.var == null) { // if originalTree is a probability node
            tree.assignProb(originalTree.value);
        } else {
            // Find the best variable and cut point for splitting 
            
            ArrayList returnedArray = selectBestVariableAndCutPointForSplitting(originalTree,
                    treeSize, varsToExploreVector, listStatesVector, sortStates);
            int maxVariable = ((Integer)returnedArray.get(0)).intValue(); // will contain the variable that maximaze info
            int maxCutPointGlobal = ((Integer)returnedArray.get(1)).intValue(); // will contain the cut point for maxVariable that maximaxe info
            double info = ((Double)returnedArray.get(2)).doubleValue();
            int index[] = (int[]) returnedArray.get(3);
            
            if (maxVariable < 0 || maxCutPointGlobal < 0) {
                try {
                    throw (new Exception());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.exit(1);
            }

            BinaryProbabilityTree.ListStates listStates;
            int state = 0, lenListStates;
            BinaryProbabilityTree newOriginalTree;
            BinaryProbabilityTree.ListStates firstListStates = listStatesVector[maxVariable];
            tree.var = varsToExploreVector[maxVariable];
            
            
            //Update index vector
            index = firstListStates.getGlobalIndex(index);

            // Proccess left branch 
            lenListStates = maxCutPointGlobal + 1;
            if (lenListStates == 1) { // if left branch will contain only one state
                listStates = new BinaryProbabilityTree.ListStates(varsToExploreVector[maxVariable].getNumStates());
                state = firstListStates.getFirstValidState(0,index);
                listStates.activateStateAt(index[state]);

                tree.leftChild = new BinaryProbabilityTree.BinaryProbabilityTreeInfoChild();
                tree.leftChild.setInfo(new BinaryProbabilityTree(), listStates);
                listStatesVector[maxVariable] = listStates;
                newOriginalTree =
                        originalTree.restrict(varsToExploreVector[maxVariable],
                        listStates);
                varsToExploreVector[maxVariable] = null;
                getSortedTreeFromBinaryPT(newOriginalTree,
                        treeSize / listStatesVector[maxVariable].numberValidStates(),
                        varsToExploreVector, listStatesVector, tree.leftChild.child, sortStates);
                varsToExploreVector[maxVariable] = tree.var;
            } else if (lenListStates > 1) {
                //Activate the maxCutPointGlobal+1 first valid states
                listStates = new BinaryProbabilityTree.ListStates(varsToExploreVector[maxVariable].getNumStates());

                state = -1;
                for (int i = 0; i < lenListStates; i++) {
                    state = firstListStates.getFirstValidState(state + 1, index);
                    listStates.activateStateAt(index[state]);
                }
                listStatesVector[maxVariable] = listStates;
                newOriginalTree =
                        originalTree.restrict(varsToExploreVector[maxVariable],
                        listStates);
                tree.leftChild = new BinaryProbabilityTree.BinaryProbabilityTreeInfoChild();
                tree.leftChild.setInfo(new BinaryProbabilityTree(), listStates);
                getSortedTreeFromBinaryPT(newOriginalTree,
                        (treeSize / listStatesVector[maxVariable].numberValidStates()) * lenListStates,
                        varsToExploreVector,
                        listStatesVector, tree.leftChild.child, sortStates);
            } else {
                System.out.println("Error in BinaryProbabilityTree.getSortedTreeFromBinaryPT():"
                        + "lenLeftListStates=" + lenListStates);
                System.exit(1);
            }

            // Proccess right branch
            //lenListStates=listStatesVector[maxVariable].numberValidStates()
            lenListStates = firstListStates.numberValidStates()
                    - lenListStates;
            state++;
            if (lenListStates == 1) { // if right branch will contain only one state
                listStates = new BinaryProbabilityTree.ListStates(varsToExploreVector[maxVariable].getNumStates());
                state = firstListStates.getFirstValidState(state,index);
                listStates.activateStateAt(index[state]);

                tree.rightChild = new BinaryProbabilityTree.BinaryProbabilityTreeInfoChild();
                tree.rightChild.setInfo(new BinaryProbabilityTree(), listStates);
                listStatesVector[maxVariable] = listStates;
                newOriginalTree =
                        originalTree.restrict(varsToExploreVector[maxVariable],
                        listStates);
                varsToExploreVector[maxVariable] = null;
                getSortedTreeFromBinaryPT(newOriginalTree,
                        treeSize / listStatesVector[maxVariable].numberValidStates(),
                        varsToExploreVector,
                        listStatesVector, tree.rightChild.child, sortStates);
                varsToExploreVector[maxVariable] = tree.var;
            } else if (lenListStates > 1) {
                //Activate the following lenListStates valid states
                listStates = new BinaryProbabilityTree.ListStates(varsToExploreVector[maxVariable].getNumStates());

                state--;
                for (int i = 0; i < lenListStates; i++) {
                    state = firstListStates.getFirstValidState(state + 1,index);
                    listStates.activateStateAt(index[state]);

                }
                
                listStatesVector[maxVariable] = listStates;
                newOriginalTree =
                        originalTree.restrict(varsToExploreVector[maxVariable],
                        listStates);
                tree.rightChild = new BinaryProbabilityTree.BinaryProbabilityTreeInfoChild();
                tree.rightChild.setInfo(new BinaryProbabilityTree(), listStates);
                getSortedTreeFromBinaryPT(newOriginalTree,
                        (treeSize / listStatesVector[maxVariable].numberValidStates()) * lenListStates,
                        varsToExploreVector,
                        listStatesVector, tree.rightChild.child, sortStates);
            } else {
                System.out.println("Error in BinaryProbabilityTree.getSortedTreeFromBinaryPT():"
                        + "lenRightListStates=" + lenListStates);
                System.exit(1);
            }
            //In listStatesVector, restore  the old listStates at position maxVariable
            listStatesVector[maxVariable] = firstListStates;
        } // end else (if (originalTree.var == null) )
    }

    
    //OLD VERSION
  /*  static private void getSortedTreeFromBinaryPT(BinaryProbabilityTree originalTree,
            long treeSize,
            FiniteStates varsToExploreVector[], BinaryProbabilityTree.ListStates listStatesVector[],
            BinaryProbabilityTree tree, boolean sortStates) {
        
        

        if (originalTree.var == null) { // if originalTree is a probability node
            tree.assignProb(originalTree.value);
        } else {
            // Find the best variable and cut point for splitting
            double returnedArray[] = selectBestVariableAndCutPointForSplitting(originalTree,
                    treeSize, varsToExploreVector, listStatesVector);
            int maxVariable = (int)returnedArray[0]; // will contain the variable that maximaze info
            int maxCutPointGlobal = (int)returnedArray[1]; // will contain the cut point for maxVariable that maximaxe info

            if (maxVariable < 0 || maxCutPointGlobal < 0) {
                try {
                    throw (new Exception());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.exit(1);
            }

            BinaryProbabilityTree.ListStates listStates;
            int state = 0, lenListStates;
            BinaryProbabilityTree newOriginalTree;
            BinaryProbabilityTree.ListStates firstListStates = listStatesVector[maxVariable];
            tree.var = varsToExploreVector[maxVariable];

            // Proccess left branch 
            lenListStates = maxCutPointGlobal + 1;
            if (lenListStates == 1) { // if left branch will contain only one state
                listStates = new BinaryProbabilityTree.ListStates(varsToExploreVector[maxVariable].getNumStates());
                state = firstListStates.getFirstValidState(0);
                listStates.activateStateAt(state);

                tree.leftChild = new BinaryProbabilityTree.BinaryProbabilityTreeInfoChild();
                tree.leftChild.setInfo(new BinaryProbabilityTree(), listStates);
                listStatesVector[maxVariable] = listStates;
                newOriginalTree =
                        originalTree.restrict(varsToExploreVector[maxVariable],
                        listStates);
                varsToExploreVector[maxVariable] = null;
                getSortedTreeFromBinaryPT(newOriginalTree,
                        treeSize / listStatesVector[maxVariable].numberValidStates(),
                        varsToExploreVector, listStatesVector, tree.leftChild.child, false);
                varsToExploreVector[maxVariable] = tree.var;
            } else if (lenListStates > 1) {
                //Activate the maxCutPointGlobal+1 first valid states
                listStates = new BinaryProbabilityTree.ListStates(varsToExploreVector[maxVariable].getNumStates());

                state = -1;
                for (int i = 0; i < lenListStates; i++) {
                    state = firstListStates.getFirstValidState(state + 1);
                    listStates.activateStateAt(state);
                }
                listStatesVector[maxVariable] = listStates;
                newOriginalTree =
                        originalTree.restrict(varsToExploreVector[maxVariable],
                        listStates);
                tree.leftChild = new BinaryProbabilityTree.BinaryProbabilityTreeInfoChild();
                tree.leftChild.setInfo(new BinaryProbabilityTree(), listStates);
                getSortedTreeFromBinaryPT(newOriginalTree,
                        (treeSize / listStatesVector[maxVariable].numberValidStates()) * lenListStates,
                        varsToExploreVector,
                        listStatesVector, tree.leftChild.child, false);
            } else {
                System.out.println("Error in BinaryProbabilityTree.getSortedTreeFromBinaryPT():"
                        + "lenLeftListStates=" + lenListStates);
                System.exit(1);
            }

            // Proccess right branch
            //lenListStates=listStatesVector[maxVariable].numberValidStates()
            lenListStates = firstListStates.numberValidStates()
                    - lenListStates;
            state++;
            if (lenListStates == 1) { // if right branch will contain only one state
                listStates = new BinaryProbabilityTree.ListStates(varsToExploreVector[maxVariable].getNumStates());
                state = firstListStates.getFirstValidState(state);
                listStates.activateStateAt(state);

                tree.rightChild = new BinaryProbabilityTree.BinaryProbabilityTreeInfoChild();
                tree.rightChild.setInfo(new BinaryProbabilityTree(), listStates);
                listStatesVector[maxVariable] = listStates;
                newOriginalTree =
                        originalTree.restrict(varsToExploreVector[maxVariable],
                        listStates);
                varsToExploreVector[maxVariable] = null;
                getSortedTreeFromBinaryPT(newOriginalTree,
                        treeSize / listStatesVector[maxVariable].numberValidStates(),
                        varsToExploreVector,
                        listStatesVector, tree.rightChild.child, false);
                varsToExploreVector[maxVariable] = tree.var;
            } else if (lenListStates > 1) {
                //Activate the following lenListStates valid states
                listStates = new BinaryProbabilityTree.ListStates(varsToExploreVector[maxVariable].getNumStates());

                state--;
                for (int i = 0; i < lenListStates; i++) {
                    state = firstListStates.getFirstValidState(state + 1);
                    listStates.activateStateAt(state);
                }
                listStatesVector[maxVariable] = listStates;
                newOriginalTree =
                        originalTree.restrict(varsToExploreVector[maxVariable],
                        listStates);
                tree.rightChild = new BinaryProbabilityTree.BinaryProbabilityTreeInfoChild();
                tree.rightChild.setInfo(new BinaryProbabilityTree(), listStates);
                getSortedTreeFromBinaryPT(newOriginalTree,
                        (treeSize / listStatesVector[maxVariable].numberValidStates()) * lenListStates,
                        varsToExploreVector,
                        listStatesVector, tree.rightChild.child, false);
            } else {
                System.out.println("Error in BinaryProbabilityTree.getSortedTreeFromBinaryPT():"
                        + "lenRightListStates=" + lenListStates);
                System.exit(1);
            }
            //In listStatesVector, restore  the old listStates at position maxVariable
            listStatesVector[maxVariable] = firstListStates;
        } // end else (if (originalTree.var == null) )
    }
*/
    
   /**
     * Recursive method that constructs a sorted <code>BinaryProbabilityTree</code>
     * (tree where more informative variables are in the upper levels, and variables
     * are splitted also trying to maximize an information measure)
     * from another BinaryProbabilityTree. This is an auxiliary method for
     * <code>getSortedTreeFromBinaryPT(BinaryProbabilityTree binaryPT,
     *       java.util.Vector variables,long treeSize)</code>.
     * The new tree is returned with
     * the parameter <code>tree</code>
     * @param originalTree the BinaryProbabilityTree to be transformed into a new
     * sorted BinaryProbalityTree. In the successive recursive calls new originalTrees
     * are obtained by restricting to a given ListStates.
     * @param treeSize size of the completely expanded tree, that is, the number
     * of possible configurations of the variables in this potential. This parameter
     * could be obtained traversing the array listStatesVector (another parameter
     * of this method) by multiplying the number of valid states at each position,
     * but it is appended as a parameter in order to avoid recalculating it at
     * every recursive call.
     * @param varToExploreVector[] an array with all the variables in the Potential.
     * Each position i contains variable i of the Potential.
     * In the first call to the method, it must contains a variable of the Potential
     * at every position.
     * The array is modified during the running of this method: null is set at position i
     * if variable i has been yet completly explored at the current branch. At the
     * end of the method the array will be restored to the original one.
     * @param listStatesVector[] an array of ListStates. Although, it is modified
     * during the running of this method, when it finishes, the list will be the
     * original one. In the first call to the method, it must contains a ListStates
     * for each variable in the Potential(with all states set to active)
     * @param tree the <code>BinaryProbabilityTree</code> we are constructing.
     *  In the first call to the method it must be a new BinaryProbalityTree
     */

    
    static private void getSortedAndPrunedTreeFromBinaryPT(BinaryProbabilityTree originalTree,
            long treeSize,
            FiniteStates varsToExploreVector[], BinaryProbabilityTree.ListStates listStatesVector[],
            BinaryProbabilityTree tree, double maxInfo, boolean sortStates) {
        
     //  System.out.println("getSortedAndPrunedTreeFromBinaryPT 2");
        if (originalTree.var == null) { // if originalTree is a probability node
            tree.assignProb(originalTree.value);
        } else {
            /* Find the best variable and cut point for splitting */
            
            
            ArrayList returnedArray = selectBestVariableAndCutPointForSplitting(originalTree,
                    treeSize, varsToExploreVector, listStatesVector, sortStates);
            int maxVariable = ((Integer)returnedArray.get(0)).intValue(); // will contain the variable that maximaze info
            int maxCutPointGlobal = ((Integer)returnedArray.get(1)).intValue(); // will contain the cut point for maxVariable that maximaxe info
            double info = ((Double)returnedArray.get(2)).doubleValue();
            int index[] = (int[]) returnedArray.get(3);
            
            if (maxVariable < 0 || maxCutPointGlobal < 0) {
                try {
                    throw (new Exception());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.exit(1);
            }
            
            
    /*        //To avoid problems with very small values
            if (Math.abs(info) < 0.0000001) {
                info = 0;
            }
           
    */
            //Checks if the tree can be pruned
            
           boolean equalLeaves = originalTree.equalLeaves();        
           if(equalLeaves) {
                Vector leaves = originalTree.getLeaves();
                tree.var = null;
                tree.value = VectorManipulator.mean(leaves);
                tree.leftChild = null;
                tree.rightChild = null;
            
                return;
            }
                

            BinaryProbabilityTree.ListStates listStates;
            int state = 0, lenListStates;
            BinaryProbabilityTree newOriginalTree;
            BinaryProbabilityTree.ListStates firstListStates = listStatesVector[maxVariable];
            tree.var = varsToExploreVector[maxVariable];
            
            
            //Update index vector
            index = firstListStates.getGlobalIndex(index);

            /* Proccess left branch */
            lenListStates = maxCutPointGlobal + 1;
            if (lenListStates == 1) { // if left branch will contain only one state
                listStates = new BinaryProbabilityTree.ListStates(varsToExploreVector[maxVariable].getNumStates());
                

                
                state = firstListStates.getFirstValidState(0,index);
                listStates.activateStateAt(index[state]);

                tree.leftChild = new BinaryProbabilityTree.BinaryProbabilityTreeInfoChild();
                tree.leftChild.setInfo(new BinaryProbabilityTree(), listStates);
                listStatesVector[maxVariable] = listStates;
                newOriginalTree =
                        originalTree.restrict(varsToExploreVector[maxVariable],
                        listStates);
                varsToExploreVector[maxVariable] = null;
                getSortedAndPrunedTreeFromBinaryPT(newOriginalTree,
                        treeSize / listStatesVector[maxVariable].numberValidStates(),
                        varsToExploreVector, listStatesVector, tree.leftChild.child, maxInfo, sortStates);
                varsToExploreVector[maxVariable] = tree.var;
            } else if (lenListStates > 1) {
                /*Activate the maxCutPointGlobal+1 first valid states*/
                listStates = new BinaryProbabilityTree.ListStates(varsToExploreVector[maxVariable].getNumStates());

 
                
                state = -1;
                for (int i = 0; i < lenListStates; i++) {
                    state = firstListStates.getFirstValidState(state + 1, index);
                    listStates.activateStateAt(index[state]);
                }
                listStatesVector[maxVariable] = listStates;
                newOriginalTree =
                        originalTree.restrict(varsToExploreVector[maxVariable],
                        listStates);
                tree.leftChild = new BinaryProbabilityTree.BinaryProbabilityTreeInfoChild();
                tree.leftChild.setInfo(new BinaryProbabilityTree(), listStates);
                getSortedAndPrunedTreeFromBinaryPT(newOriginalTree,
                        (treeSize / listStatesVector[maxVariable].numberValidStates()) * lenListStates,
                        varsToExploreVector,
                        listStatesVector, tree.leftChild.child, maxInfo, sortStates);
            } else {
                System.out.println("Error in BinaryProbabilityTree.getSortedTreeFromBinaryPT():"
                        + "lenLeftListStates=" + lenListStates);
                System.exit(1);
            }

            /* Proccess right branch */
            //lenListStates=listStatesVector[maxVariable].numberValidStates()
            lenListStates = firstListStates.numberValidStates()
                    - lenListStates;
            state++;
            if (lenListStates == 1) { // if right branch will contain only one state
                listStates = new BinaryProbabilityTree.ListStates(varsToExploreVector[maxVariable].getNumStates());
                
                state = firstListStates.getFirstValidState(state,index);
                listStates.activateStateAt(index[state]);

                tree.rightChild = new BinaryProbabilityTree.BinaryProbabilityTreeInfoChild();
                tree.rightChild.setInfo(new BinaryProbabilityTree(), listStates);
                listStatesVector[maxVariable] = listStates;
                newOriginalTree =
                        originalTree.restrict(varsToExploreVector[maxVariable],
                        listStates);
                varsToExploreVector[maxVariable] = null;
                getSortedAndPrunedTreeFromBinaryPT(newOriginalTree,
                        treeSize / listStatesVector[maxVariable].numberValidStates(),
                        varsToExploreVector,
                        listStatesVector, tree.rightChild.child, maxInfo, sortStates);
                varsToExploreVector[maxVariable] = tree.var;
            } else if (lenListStates > 1) {
                //Activate the following lenListStates valid states
                listStates = new BinaryProbabilityTree.ListStates(varsToExploreVector[maxVariable].getNumStates());

                
                
                state--;
                for (int i = 0; i < lenListStates; i++) {
                    state = firstListStates.getFirstValidState(state + 1,index);
                    listStates.activateStateAt(index[state]);

                }
                
                
                listStatesVector[maxVariable] = listStates;
                newOriginalTree =
                        originalTree.restrict(varsToExploreVector[maxVariable],
                        listStates);
                tree.rightChild = new BinaryProbabilityTree.BinaryProbabilityTreeInfoChild();
                tree.rightChild.setInfo(new BinaryProbabilityTree(), listStates);
                getSortedAndPrunedTreeFromBinaryPT(newOriginalTree,
                        (treeSize / listStatesVector[maxVariable].numberValidStates()) * lenListStates,
                        varsToExploreVector,
                        listStatesVector, tree.rightChild.child, maxInfo, sortStates);
            } else {
                System.out.println("Error in BinaryProbabilityTree.getSortedTreeFromBinaryPT():"
                        + "lenRightListStates=" + lenListStates);
                System.exit(1);
            }
            /*In listStatesVector, restore  the old listStates at position maxVariable*/
            listStatesVector[maxVariable] = firstListStates;
        } // end else (if (originalTree.var == null) )
    }
    
    /*
                
     //OLD VERSION           
     static private void getSortedAndPrunedTreeFromBinaryPT(BinaryProbabilityTree originalTree,
            long treeSize,
            FiniteStates varsToExploreVector[], BinaryProbabilityTree.ListStates listStatesVector[],
            BinaryProbabilityTree tree, double maxInfo, boolean sortStates) {
        
       
        if (originalTree.var == null) { // if originalTree is a probability node
            tree.assignProb(originalTree.value);
        } else {
            // Find the best variable and cut point for splitting 
            double returnedArray[] = selectBestVariableAndCutPointForSplitting(originalTree,
                    treeSize, varsToExploreVector, listStatesVector);
            int maxVariable = (int)returnedArray[0]; // will contain the variable that maximaze info
            int maxCutPointGlobal = (int)returnedArray[1]; // will contain the cut point for maxVariable that maximaxe info
            double info = returnedArray[2];
            
            if (maxVariable < 0 || maxCutPointGlobal < 0) {
                try {
                    throw (new Exception());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.exit(1);
            }
            
            
            //To avoid problems with very small values
            if (Math.abs(maxInfo) < 0.00001) {
                maxInfo = 0;
            }
            
            
            //Checks if the tree can be pruned
            if(info <= maxInfo) {
                Vector leaves = originalTree.getLeaves();
                tree.var = null;
                tree.value = VectorManipulator.mean(leaves);
                tree.leftChild = null;
                tree.rightChild = null;
            
                return;
            }
                

            BinaryProbabilityTree.ListStates listStates;
            int state = 0, lenListStates;
            BinaryProbabilityTree newOriginalTree;
            BinaryProbabilityTree.ListStates firstListStates = listStatesVector[maxVariable];
            tree.var = varsToExploreVector[maxVariable];

            // Proccess left branch 
            lenListStates = maxCutPointGlobal + 1;
            if (lenListStates == 1) { // if left branch will contain only one state
                listStates = new BinaryProbabilityTree.ListStates(varsToExploreVector[maxVariable].getNumStates());
                state = firstListStates.getFirstValidState(0);
                listStates.activateStateAt(state);

                tree.leftChild = new BinaryProbabilityTree.BinaryProbabilityTreeInfoChild();
                tree.leftChild.setInfo(new BinaryProbabilityTree(), listStates);
                listStatesVector[maxVariable] = listStates;
                newOriginalTree =
                        originalTree.restrict(varsToExploreVector[maxVariable],
                        listStates);
                varsToExploreVector[maxVariable] = null;
                getSortedAndPrunedTreeFromBinaryPT(newOriginalTree,
                        treeSize / listStatesVector[maxVariable].numberValidStates(),
                        varsToExploreVector, listStatesVector, tree.leftChild.child, maxInfo,false);
                varsToExploreVector[maxVariable] = tree.var;
            } else if (lenListStates > 1) {
                //Activate the maxCutPointGlobal+1 first valid states
                listStates = new BinaryProbabilityTree.ListStates(varsToExploreVector[maxVariable].getNumStates());

                state = -1;
                for (int i = 0; i < lenListStates; i++) {
                    state = firstListStates.getFirstValidState(state + 1);
                    listStates.activateStateAt(state);
                }
                listStatesVector[maxVariable] = listStates;
                newOriginalTree =
                        originalTree.restrict(varsToExploreVector[maxVariable],
                        listStates);
                tree.leftChild = new BinaryProbabilityTree.BinaryProbabilityTreeInfoChild();
                tree.leftChild.setInfo(new BinaryProbabilityTree(), listStates);
                getSortedAndPrunedTreeFromBinaryPT(newOriginalTree,
                        (treeSize / listStatesVector[maxVariable].numberValidStates()) * lenListStates,
                        varsToExploreVector,
                        listStatesVector, tree.leftChild.child, maxInfo,false);
            } else {
                System.out.println("Error in BinaryProbabilityTree.getSortedTreeFromBinaryPT():"
                        + "lenLeftListStates=" + lenListStates);
                System.exit(1);
            }

            // Proccess right branch 
            //lenListStates=listStatesVector[maxVariable].numberValidStates()
            lenListStates = firstListStates.numberValidStates()
                    - lenListStates;
            state++;
            if (lenListStates == 1) { // if right branch will contain only one state
                listStates = new BinaryProbabilityTree.ListStates(varsToExploreVector[maxVariable].getNumStates());
                state = firstListStates.getFirstValidState(state);
                listStates.activateStateAt(state);

                tree.rightChild = new BinaryProbabilityTree.BinaryProbabilityTreeInfoChild();
                tree.rightChild.setInfo(new BinaryProbabilityTree(), listStates);
                listStatesVector[maxVariable] = listStates;
                newOriginalTree =
                        originalTree.restrict(varsToExploreVector[maxVariable],
                        listStates);
                varsToExploreVector[maxVariable] = null;
                getSortedAndPrunedTreeFromBinaryPT(newOriginalTree,
                        treeSize / listStatesVector[maxVariable].numberValidStates(),
                        varsToExploreVector,
                        listStatesVector, tree.rightChild.child, maxInfo, false);
                varsToExploreVector[maxVariable] = tree.var;
            } else if (lenListStates > 1) {
                //Activate the following lenListStates valid states
                listStates = new BinaryProbabilityTree.ListStates(varsToExploreVector[maxVariable].getNumStates());

                state--;
                for (int i = 0; i < lenListStates; i++) {
                    state = firstListStates.getFirstValidState(state + 1);
                    listStates.activateStateAt(state);
                }
                listStatesVector[maxVariable] = listStates;
                newOriginalTree =
                        originalTree.restrict(varsToExploreVector[maxVariable],
                        listStates);
                tree.rightChild = new BinaryProbabilityTree.BinaryProbabilityTreeInfoChild();
                tree.rightChild.setInfo(new BinaryProbabilityTree(), listStates);
                getSortedAndPrunedTreeFromBinaryPT(newOriginalTree,
                        (treeSize / listStatesVector[maxVariable].numberValidStates()) * lenListStates,
                        varsToExploreVector,
                        listStatesVector, tree.rightChild.child, maxInfo, false);
            } else {
                System.out.println("Error in BinaryProbabilityTree.getSortedTreeFromBinaryPT():"
                        + "lenRightListStates=" + lenListStates);
                System.exit(1);
            }
            //In listStatesVector, restore  the old listStates at position maxVariable
            listStatesVector[maxVariable] = firstListStates;
        } // end else (if (originalTree.var == null) )
    }
    
  */  
    
    
    /**
     * Find the best variable and the best cut point to put at the root of a new
     * BinaryProbabilityTree, considering a given measure of information. It is an
     * auxiliary method for <code>getSortedTreeFromBinaryPT(BinaryProbabilityTree,
    long,FiniteStates, ListStates, BinaryProbabilityTree)</code>
     * @param originalTree the BinaryProbabilityTree to be transformed into a new
     * sorted BinaryProbalityTree.
     * @param treeSize size of the completely expanded tree, that is, the number
     * of possible configurations of the variables in originalTree. This parameter
     * could be obtained traversing the array listStatesVector (another parameter
     * of this method) by multiplying the number of valid states at each position,
     * but it is appended as a parameter in order to avoid recalculating it at
     * every recursive call.
     * @param varsToExploreVector an array with all the variables in originalTree.
     * Each position i contains variable i of originalTree.
     * @param listStatesVector an array with the active ListStates for each
     * variable.
     * @return an array with two integers. Position 0 contains the index of
     * variable (in the array varsToExploreVector) corresponding to the best variable.
     * Position 1 contain the index of the best
     * valid state to split in two parts (from 0 to this states, and from the next one to
     * the last state). Note that only valid states are considered, so if index 0 is
     * returned, it means that the first valid state is the best, if index 1 is
     * returned, it means that the second valid state is the best, and so on.
     */
  
     
     private static ArrayList selectBestVariableAndCutPointForSplitting(
            BinaryProbabilityTree originalTree, long treeSize,
            FiniteStates varsToExploreVector[], BinaryProbabilityTree.ListStates listStatesVector[], boolean sortStates) {
        
        double maxGlobalInfo = Double.NEGATIVE_INFINITY;
        int maxVariable = -1; // will contain the variable that maximaze info
        int maxCutPointGlobal = -1; // will contain the cut point for maxVariable that maximaxe info
        double maxGlobalInfoCutPoint = Double.NEGATIVE_INFINITY;

        
        int index[] = null;
        int maxIndex[] = null;
        
        // Find the best variable and cut point for splitting 
        for (int i = 0; i < varsToExploreVector.length; i++) {
            if (varsToExploreVector[i] != null) { // if variable i has not been explored yet
                double potentialSum;
                int state = -1;
                int nValidStates = listStatesVector[i].numberValidStates();
                double branchSum[] = new double[nValidStates];
                //int validStates[] = new int[nValidStates];
                BinaryProbabilityTree restrictedOriginalTree;
                long newTreeSize = treeSize / nValidStates;

                potentialSum = 0.0;
                // Calculate sum for each valid state j of variable i 
                for (int j = 0; j < nValidStates; j++) {
                    state = listStatesVector[i].getFirstValidState(state + 1);
                    //validStates[j] = state;
                    restrictedOriginalTree = originalTree.restrict(varsToExploreVector[i], state);
                    branchSum[j] = restrictedOriginalTree.sum(newTreeSize);
                    potentialSum += branchSum[j];
                }
                

                
                index = new int[branchSum.length];
                for(int j=0;j<branchSum.length;j++)
                    index[j] = j;
                
                if(sortStates){
                    ArrayList sortInfo = SortVectors.quicksort(branchSum);
                    index = (int[]) sortInfo.get(1);
                }
                
                
                
                // Find the best cut point for variable i 
                double leftBranchSum = branchSum[index[0]];
                double rightBranchSum = potentialSum - leftBranchSum;
                int maxCutPointLocal = 0;
                double maxInfoCutPoint = -probLogProb(rightBranchSum, nValidStates - 1)
                        + probLogProb(leftBranchSum, leftBranchSum)
                        + probLogProb(rightBranchSum, rightBranchSum);
                
       

                // Try all possible cut points for variable i 
                for (int j = 1; j < (nValidStates - 1); j++) {
                    leftBranchSum += branchSum[index[j]];
                    rightBranchSum = potentialSum - leftBranchSum;
                    double infoCutPoint = -probLogProb(leftBranchSum, j + 1)
                            - probLogProb(rightBranchSum, nValidStates - j - 1)
                            + probLogProb(leftBranchSum, leftBranchSum)
                            + probLogProb(rightBranchSum, rightBranchSum);
                    

                    
                    
                    if (infoCutPoint > maxInfoCutPoint) {
                        maxCutPointLocal = j;
                        maxInfoCutPoint = infoCutPoint;
                    }
                }


     
                // Check if we have a new best variable 
                double globalInfo = probLogProb(potentialSum, nValidStates)
                        - probLogProb(potentialSum, potentialSum) + maxInfoCutPoint;
                if (globalInfo > maxGlobalInfo) {
                    maxVariable = i;
                    maxCutPointGlobal = maxCutPointLocal;
                    maxGlobalInfo = globalInfo;
                    maxGlobalInfoCutPoint = -maxInfoCutPoint;
                    maxIndex = index;
                }
            }        
        } // end for(int i=0;i<varsToExploreVector.length;i++)
        ArrayList returnedArray = new ArrayList();
        returnedArray.add(maxVariable);
        returnedArray.add(maxCutPointGlobal);
        returnedArray.add(maxGlobalInfo);
        returnedArray.add(maxIndex);
        
        
        return returnedArray;
    }

    
   
     //OLD VERSION
  /*      private static double[] selectBestVariableAndCutPointForSplitting(
            BinaryProbabilityTree originalTree, long treeSize,
            FiniteStates varsToExploreVector[], BinaryProbabilityTree.ListStates listStatesVector[]) {
        double maxGlobalInfo = Double.NEGATIVE_INFINITY;
        int maxVariable = -1; // will contain the variable that maximaze info
        int maxCutPointGlobal = -1; // will contain the cut point for maxVariable that maximaxe info

        // Find the best variable and cut point for splitting 
        for (int i = 0; i < varsToExploreVector.length; i++) {
            if (varsToExploreVector[i] != null) { // if variable i has not been explored yet
                double potentialSum;
                int state = -1;
                int nValidStates = listStatesVector[i].numberValidStates();
                double branchSum[] = new double[nValidStates];
                //int validStates[] = new int[nValidStates];
                BinaryProbabilityTree restrictedOriginalTree;
                long newTreeSize = treeSize / nValidStates;

                potentialSum = 0.0;
                // Calculate sum for each state j of variable i 
                for (int j = 0; j < nValidStates; j++) {
                    state = listStatesVector[i].getFirstValidState(state + 1);
                    //validStates[j] = state;
                    restrictedOriginalTree = originalTree.restrict(varsToExploreVector[i], state);
                    branchSum[j] = restrictedOriginalTree.sum(newTreeSize);
                    potentialSum += branchSum[j];
                }

                // Find the best cut point for variable i
                double leftBranchSum = branchSum[0];
                double rightBranchSum = potentialSum - leftBranchSum;
                int maxCutPointLocal = 0;
                double maxInfoCutPoint = -probLogProb(rightBranchSum, nValidStates - 1)
                        + probLogProb(leftBranchSum, leftBranchSum)
                        + probLogProb(rightBranchSum, rightBranchSum);
                // Try all possible cut points for variable i
                for (int j = 1; j < (nValidStates - 1); j++) {
                    leftBranchSum += branchSum[j];
                    rightBranchSum = potentialSum - leftBranchSum;
                    double infoCutPoint = -probLogProb(leftBranchSum, j + 1)
                            - probLogProb(rightBranchSum, nValidStates - j - 1)
                            + probLogProb(leftBranchSum, leftBranchSum)
                            + probLogProb(rightBranchSum, rightBranchSum);
                    if (infoCutPoint > maxInfoCutPoint) {
                        maxCutPointLocal = j;
                        maxInfoCutPoint = infoCutPoint;
                    }
                }

                // Check if we have a new best variable 
                double globalInfo = probLogProb(potentialSum, nValidStates)
                        - probLogProb(potentialSum, potentialSum) + maxInfoCutPoint;
                if (globalInfo > maxGlobalInfo) {
                    maxVariable = i;
                    maxCutPointGlobal = maxCutPointLocal;
                    maxGlobalInfo = globalInfo;
                }
            }  // end if(varsToExploreVector[i]!=null
        } // end for(int i=0;i<varsToExploreVector.length;i++)
        double returnedArray[] = new double[3];
        returnedArray[0] = maxVariable;
        returnedArray[1] = maxCutPointGlobal;
        returnedArray[2] = maxGlobalInfo;
        return returnedArray;
    }

    */
    
    
    
    public static double probLogProb(double p1, double p2)  {
        if (p1 == 0.0) {
            return 0.0;
        } else {
            if (p2 == 0.0) {
                System.out.println("Error in BinaryProbabilityTree.probLogProb():"
                        + " p1= " + p1 + ", p2=" + p2);
                
                    return Double.NEGATIVE_INFINITY;

            }
            return p1 * Math.log(p2);
        }
    }

    /**
     * Prints this binary tree to the standard output.
     * @param offset a tab factor (number of blank spaces before a child
     * is written).
     */
    public void print(int offset) {
        int l, k;

        if (var == null) // probability node
        {
            System.out.print(value + ";\n");
        } else {
            System.out.print("case " + var.getName() + " {\n");

            for (l = 1; l <= offset; l++) {
                System.out.print(" ");
            }

            System.out.print("(");
            leftChild.listStates.print();
            System.out.print(")" + " = ");
            leftChild.child.print(offset + 10);

            for (l = 1; l <= offset; l++) {
                System.out.print(" ");
            }

            System.out.print("(");
            rightChild.listStates.print();
            System.out.print(")" + " = ");
            rightChild.child.print(offset + 10);

            for (l = 1; l <= offset; l++) {
                System.out.print(" ");
            }

            System.out.print("          } \n");
        }
    }
    
    public String printTikz(double posX, double posY, int level, int initState) {
    
        boolean printLbl = false;
        
        if(initState == -1)
            printLbl = true;
        
        
        String s = "";
        
        String offset = " ";
        for (int i = 0; i < level; i++) {
            offset += "   ";
        }
                
        if(level==1)
            s += offset+"\\node at ("+posX+", "+posY+") ";
        else
            s += offset+"node ";
        

        
        if(isProbab())
            s += "{"+value+"}\n";
        else{
            s += "{$"+var.getName()+"$}\n";
            
            
            //leftChild
            s += offset+"child{\n";
            s += leftChild.getChild().printTikz(0, 0, level+1, initState);
            s += offset+"edge from parent node [left] {$";
            
            boolean[] states = leftChild.listStates.getStates();
            for(int i=0; i<states.length; i++) {
                if(states[i]) {
                    
                    if(printLbl)
                        s+=var.getState(i)+",";
                    else
                        s+=var.getName().toLowerCase()+"_"+(initState+i)+",";
                }
            }
            s = s.substring(0, s.length()-1)+"$}}\n";
            
            
            //rightChild
            s += offset+"child{\n";
            s += rightChild.getChild().printTikz(0, 0, level+1, initState);
            s += offset+"edge from parent node [right] {$";
            
            states = rightChild.listStates.getStates();
            for(int i=0; i<states.length; i++) {
                if(states[i]) {
                    
                    if(printLbl)
                        s+=var.getState(i)+",";
                    else
                        s+=var.getName().toLowerCase()+"_"+(initState+i)+",";
                }
            }
            s = s.substring(0, s.length()-1)+"$}}\n";
            
            
            
            
        }
            
        
        return s;
    
    }
    
    
    
    
    

    /**
     * Prints this binary tree into a file.
     * @param offset a tab factor (number of blank spaces before a child
     * is written).
     *@param p the <code>PrintWriter</code> where the potential will be written.
     */
    public void save(PrintWriter p, int offset) {
        int l, k;

        if (var == null) // probability node
        {
            p.print(value + ";\n");
        } else {
            p.print("case " + var.getName() + " {\n");

            for (l = 1; l <= offset; l++) {
                p.print(" ");
            }

            p.print("(");
            leftChild.listStates.print(p);
            p.print(")" + " = ");
            leftChild.child.save(p, offset + 10);

            for (l = 1; l <= offset; l++) {
                p.print(" ");
            }

            p.print("(");
            rightChild.listStates.print(p);
            p.print(")" + " = ");
            rightChild.child.save(p, offset + 10);

            for (l = 1; l <= offset; l++) {
                p.print(" ");
            }

            p.print("          } \n");
        }
    }



    /**
     * Gets the probability of a given configuration of variables.
     * @param conf a <code>Configuration</code> of FiniteState variables.
     * @return the probability value of the tree following the path indicated by
     * <code>Configuration conf</code>.
     */
    public double getProb(Configuration conf) {
        if (var != null) { // If the node is a variable
            int val;
            val = conf.getValue(var);           
            if (leftChild.listStates.contains(val)) {
                return leftChild.child.getProb(conf);
            } else if (rightChild.listStates.contains(val)) {
                return rightChild.child.getProb(conf);
            } else {
                System.out.println("Error in BinaryProbabilityTree.getProb(Configuration)"
                        + ": return value for variable "+var+" at configuration "+conf+") is -1");
                
                this.print(2);
                System.exit(1);
            }
        } else {
            return value;
        }
        return -1;
    }

    /**
     * This is a private and recursive method used by <code>addChildren(FiniteStates)</code>
     * It obtains a new BinaryProbabilityTree by suming over this tree with
     * <code>binaryPT</code>. The implementation of this method is very similar to
     * <code>combine(BinaryProbabilityTree binaryPT)</code> changing products by sums.
     * @param binaryPT a <code>BinaryProbabilityTree</code> to sum over with this
     * tree
     * @see BinaryProbabilityTree addChildren(FiniteStates variable)
     * @return a new <code>BinaryProbabilityTree</code> with the result of the
     * operation.
     */
    private BinaryProbabilityTree add(BinaryProbabilityTree binaryPT) {
        BinaryProbabilityTree tree;
        
 /*       if(improvedOps){
        
            //If one of the trees is a leaf with the value 0,
            //the result will be the other tree
            if(isProbab() && value==0) {
       //         System.out.println("improvedOps add");
                tree = binaryPT.copy();
                return tree;
            }
            if(binaryPT.isProbab() && binaryPT.getValue()==0) {
       //         System.out.println("improvedOps add");
                tree = this.copy();
                return tree;
            }
        }
        
 */       

        tree = new BinaryProbabilityTree();
        if (var == null) {// If we are at a probability node
            if (binaryPT.var == null) {  // If binaryPT is a probability node
                tree.value = value + binaryPT.value;
                //tree.value = BigDecimal.valueOf(value).add(BigDecimal.valueOf(binaryPT.value)).doubleValue();
                if(Potential.getStatistics() != null)
                    Potential.getStatistics().addSumMarg(1);
                
                
              //  System.out.println(value+"+"+binaryPT.value+"="+tree.value+" (add)");
            } else { // If binaryPT is not a simple probability node
                tree.var = binaryPT.var;

                tree.leftChild = new BinaryProbabilityTree.BinaryProbabilityTreeInfoChild();
                // Left child will be the result of adding the value stored in this
                // node tree and the left child  of binaryPT
                tree.leftChild.setInfo(add(binaryPT.leftChild.child),
                        new BinaryProbabilityTree.ListStates(binaryPT.leftChild.listStates));
                tree.rightChild = new BinaryProbabilityTree.BinaryProbabilityTreeInfoChild();
                // Right child will be the result of adding the value stored in this
                // node tree and the right child  of binaryPT
                tree.rightChild.setInfo(add(binaryPT.rightChild.child),
                        new BinaryProbabilityTree.ListStates(binaryPT.rightChild.listStates));
            }
        } else { // If we are not at a simple probability node
            tree.var = var;
            tree.leftChild = new BinaryProbabilityTree.BinaryProbabilityTreeInfoChild();

            // Left child will be the result of adding the left child  of this node
            // with the restriction of binaryPT to var=leftChild.listStates
            tree.leftChild.setInfo(leftChild.child.add(
                    binaryPT.restrict(var, leftChild.listStates)),
                    new BinaryProbabilityTree.ListStates(leftChild.listStates));
            tree.rightChild = new BinaryProbabilityTree.BinaryProbabilityTreeInfoChild();
            // Right child will be the result of adding the right child  of this node
            // with the restriction of binaryPT to var=rightChild.listStates
            tree.rightChild.setInfo(rightChild.child.add(
                    binaryPT.restrict(var, rightChild.listStates)),
                    new BinaryProbabilityTree.ListStates(rightChild.listStates));
        }
        return tree;
    }

    /**
     * This is a private method used by <code>addVariable(FiniteStates,int)</code>
     * It obtains a new BinaryProbabilityTree by suming over the two children of
     * this node tree. It is supossed that <code>variable</code> is the variable stored
     * in this node tree. That is,  <code>variable</code> is at the root of the tree
     * @param variable a <code>FiniteStates</code> variable to remove from
     * the tree.
     * @see BinaryProbabilityTree addVariable(FiniteStates variable,int factor)
     * @return a new <code>BinaryProbabilityTree</code> with the result of the
     * operation.
     */
    private BinaryProbabilityTree addChildren(FiniteStates variable) {
        BinaryProbabilityTree leftTree, rightTree, newTree;

        leftTree = leftChild.child;
        rightTree = rightChild.child;
        
        
        if(leftChild.listStates.numberValidStates()>1)
            leftTree = leftChild.child.addVariable(variable,
                leftChild.listStates.numberValidStates());
        
        if(rightChild.listStates.numberValidStates()>1)
        rightTree = rightChild.child.addVariable(variable,
                rightChild.listStates.numberValidStates());
        
/*        for(int i=0; i<leftChild.listStates.states.length; i++)
            System.out.print(leftChild.listStates.states[i]+" ");
        System.out.println("");
        for(int i=0; i<rightChild.listStates.states.length; i++)
            System.out.print(rightChild.listStates.states[i]+" ");
        
        System.out.println("\n-----");
  */      
        

        newTree = leftTree.add(rightTree);
 /*       
        System.out.println("\n----");
        System.out.println("leftTree:");
        leftTree.print(1);
        System.out.println("rightTree:");
        rightTree.print(1);
        System.out.println("newTree:");
        newTree.print(1);
        System.out.println("----");
        */
        
 /*       if(Potential.getStatistics() != null)
                    Potential.getStatistics().addSumMarg(newTree.getSize());
  */      

        return newTree;
    }

    /**
     * This is a private and recursive method used by <code>addVariable(FiniteStates)</code>
     * It obtains a new BinaryProbabilityRee removing <code>variable</code> from this tree
     * by summing over all its values.
     * @param variable a <code>FiniteStates</code> variable to remove from
     * this tree.
     * @param factor is an integer used to multiply the value stored in a leaf
     * node when we reach it by a branch where variable is not found (this means
     * that the probability value in the leaf appears factor times in a equivalent
     * probability table, and then the probability value must be multiply by factor
     * in the new tree)
     * @see BinaryProbabilityTree addVariable(FiniteStates variable)
     * @return a new <code>BinaryProbabilityTree</code> with the result of the
     * operation.
     */
    public BinaryProbabilityTree addVariable(FiniteStates variable, int factor) {
        BinaryProbabilityTree tree;

        
//System.out.println("f="+factor);
        
        if (var == null) {// If we are at a probability node
            tree = new BinaryProbabilityTree();
          //  tree.value = BigDecimal.valueOf(value).multiply(BigDecimal.valueOf(factor)).doubleValue();
            tree.value = value*factor;
//            System.out.println(value+" * "+factor+" = "+tree.value);
           if(Potential.getStatistics() != null)
                Potential.getStatistics().addNumMultiplications(1);
            
            
            //System.out.println(value+"*"+factor+"="+tree.value+" (marg)");

        } else { // If we are not at a simple probability node
            if (var.equals(variable)) { // if variable to sum over is var
                tree = addChildren(variable);
            } else {
                tree = new BinaryProbabilityTree();
                tree.var = var;
                tree.leftChild = new BinaryProbabilityTreeInfoChild();
                // Left child will be the result of suming over variable in the
                // left child of this node tree
                tree.leftChild.setInfo(leftChild.child.addVariable(variable, factor),
                        new ListStates(leftChild.listStates));
                tree.rightChild = new BinaryProbabilityTreeInfoChild();
                // Right child will be the result of suming over variable in the
                // right child of this node tree
                tree.rightChild.setInfo(rightChild.child.addVariable(variable, factor),
                        new ListStates(rightChild.listStates));
            }

        }
        
        
        
        return tree;
    }

    /**
     * Restricts this binary probability tree to a list of states (setToRestrict)
     * of the variable varToRestrict
     * @param varToRestrict the variable to eliminate from this tree
     * @param setToRestrict the set of states of varToRestrict used for the
     * restriction operation
     * @return a new BinaryProbabilityTree with the result of the operation
     */
    private BinaryProbabilityTree restrict(FiniteStates varToRestrict,
            BinaryProbabilityTree.ListStates setToRestrict) {
        BinaryProbabilityTree tree = null;
        
        

        if (var == null) {// If we are at a probability node
            tree = new BinaryProbabilityTree();
            tree.assignProb(value);
            tree.setFactor(1);
        } else {
            if (var == varToRestrict) { // if varToRestrict is var ?????  if (var.equals(varToRestrict))
               
                BinaryProbabilityTree.ListStates leftSet = getLeftStates().intersectionStates(setToRestrict);
                BinaryProbabilityTree.ListStates rightSet = getRightStates().intersectionStates(setToRestrict);
               
                boolean leftEmpty = leftSet.isEmpty();
                boolean rightEmpty = rightSet.isEmpty();
                if (leftEmpty || rightEmpty) { // if new left or right states would be empty
                    if (leftEmpty && rightEmpty) {
                        System.out.println("Error in BinaryProbabilityTree.restrict(): left and right children will be empty");
                        System.exit(1);
                    } else {
                        if (leftEmpty) {
                            if(rightChild.listStates.getNumValidStates() > 1)
                                tree = rightChild.child.restrict(varToRestrict, rightSet);
                            else
                                tree = rightChild.child.copy();
                            
                        } else { // if rightEmpty
                            if(leftChild.listStates.getNumValidStates()>1)
                                tree = leftChild.child.restrict(varToRestrict, leftSet);
                            else 
                                tree = leftChild.child.copy();
                        }
                    }
                } else { // if neither new left nor right states would be empty
                    tree = new BinaryProbabilityTree();
                    tree.var = var;
                    tree.leftChild = new BinaryProbabilityTree.BinaryProbabilityTreeInfoChild();
                    
                    
                    //If this branch contains more than 1 state, it must reestrict the subtree
                    if(leftChild.listStates.numberValidStates() > 1)
                        tree.leftChild.setInfo(leftChild.child.restrict(varToRestrict, leftSet),leftSet);
                    else
                        tree.leftChild.setInfo(leftChild.child.copy(),leftSet);
                    
                    
                    tree.rightChild = new BinaryProbabilityTree.BinaryProbabilityTreeInfoChild();
                    //If this branch contains more than 1 state, it must reestrict the subtree
                    if(rightChild.listStates.numberValidStates() > 1)
                        tree.rightChild.setInfo(rightChild.child.restrict(varToRestrict, rightSet),rightSet);
                    else
                        tree.rightChild.setInfo(rightChild.child.copy(),rightSet);
                    
                }

            } else { // if varToRestrict is not var
                tree = new BinaryProbabilityTree();
                tree.var = var;

                tree.leftChild = new BinaryProbabilityTree.BinaryProbabilityTreeInfoChild();
                tree.leftChild.setInfo(leftChild.child.restrict(varToRestrict, setToRestrict),
                        new BinaryProbabilityTree.ListStates(leftChild.listStates));
                tree.rightChild = new BinaryProbabilityTree.BinaryProbabilityTreeInfoChild();
                tree.rightChild.setInfo(rightChild.child.restrict(varToRestrict, setToRestrict),
                        new BinaryProbabilityTree.ListStates(rightChild.listStates));
            }

        }
        return tree;
    }

    /**
     * Obtains the list of states associated to the left child of this node tree
     * @return the list of states associated to the left child of this node tree
     */
    private BinaryProbabilityTree.ListStates getLeftStates() {
        if (leftChild == null) {
            return null;
        } else if (leftChild.listStates == null) {
            return null;
        } else {
            return leftChild.listStates;
        }

    }

    /**
     * Obtains the list of states associated to the right child of this node tree
     * @return the list of states associated to the right child of this node tree
     */
    private BinaryProbabilityTree.ListStates getRightStates() {
        if (rightChild == null) {
            return null;
        } else if (rightChild.listStates == null) {
            return null;
        } else {
            return rightChild.listStates;
        }

    }

   
    
    public void prune(double limit) {
        
        Vector leftLeaves, rightLeaves, leaves, meanNotExpanded, meanExpanded;


        if(!this.isProbab()) {
            try {
                leftLeaves = this.getLeftChild().getChild().getLeaves();
                rightLeaves = this.getRightChild().getChild().getLeaves();
                leaves = this.getLeaves();


                meanNotExpanded = VectorManipulator.vectorMean(leaves);
                meanExpanded = VectorManipulator.concat(VectorManipulator.vectorMean(leftLeaves), VectorManipulator.vectorMean(rightLeaves));

                double d1 = Distances.kullbackLeibler(meanNotExpanded, leaves);
                double d2 = Distances.kullbackLeibler(meanExpanded, leaves);

                double info = d1 - d2;
                
                //if information gain is very low, it is approximated
                if(Math.abs(info)<IDVEWithPotentialBPTree.limitForPruning)
                    info=0;
                

                
                
                if(info<=limit) { //tree is not expanded
                    this.leftChild=null;
                    this.rightChild=null;
                    this.var=null;
                    this.value = (Double)(meanNotExpanded.get(0));
                    this.setFactor((int)getSizeExpanded());
                   

                }else {     
                    this.getLeftChild().getChild().prune(limit);
                    this.getRightChild().getChild().prune(limit);    
                }
            } catch (Exception ex) {
                Logger.getLogger(BinaryProbabilityTree.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    
    }
    
    
       /**
     * Prunes this tree by substituing each node whose children are
     * leaves (terminal node) by the average of them. This is done for terminal
     * nodes with an information value lower than a given threshold.
     * This method do not have any effect for a probability tree consisting of
     * only one probability node.
     * This method uses the same measure of information as ProbabilityTree.prune(),
     * @param limit the information threshold for pruning.
     * @param oldSize size (number of leaves) of this tree if it were complete.
     * The value of this parameter is modified in the succesive recursive calls
     * @param globalSum the addition of the original potential. This parameter
     * remains constant in the succesive recursive calls.
     * @return <code>true</code> if the tree has been reduced to a probability
     * node; <code>false</code> otherwise.
     * @see ProbabilityTree.prune(double limit, long oldSize, double globalSum,
     *      long numberDeleted[])
     */
    public boolean prune(double limit, long oldSize, double globalSum) {
        return prune(limit, oldSize, globalSum, null);
    } 
    /**
     * Prunes this tree by substituing each node whose children are
     * leaves (terminal node) by the average of them. This is done for terminal
     * nodes with an information value lower than a given threshold.
     * This method do not have any effect for a probability tree consisting of
     * only one probability node.
     * This method uses the same measure of information as ProbabilityTree.prune(),
     * @param limit the information threshold for pruning.
     * @param oldSize size (number of leaves) of this tree if it were complete.
     * The value of this parameter is modified in the succesive recursive calls
     * @param globalSum the addition of the original potential. This parameter
     * remains constant in the succesive recursive calls.
     * @return <code>true</code> if the tree has been reduced to a probability
     * node; <code>false</code> otherwise.
     * @see ProbabilityTree.prune(double limit, long oldSize, double globalSum,
     *      long numberDeleted[])
     */
    public boolean prune(double limit, long oldSize, double globalSum, Vector<Node> importantVariables) {
        
        
        double pr, sumLeft = 0.0, sumRight = 0.0; //, entropy = 0.0;
        boolean bounded = true; // tell if the tree can be reduced to a probab. node

        if (var != null) {// If we are not at a probability node
            int nValidStatesLeft = leftChild.listStates.numberValidStates();
            int nValidStatesRight = rightChild.listStates.numberValidStates();
            int nValidStates = nValidStatesLeft + nValidStatesRight;
            

            

            if (leftChild.child.var == null) { // If left child is a probability node
                pr = leftChild.child.value;
                sumLeft += pr * nValidStatesLeft;
                // entropy += ( -probLogProb(pr,pr)  ) * nValidStatesLeft;
            } else {
                long newSize = (oldSize / nValidStates) * nValidStatesLeft;
                boolean childBounded = leftChild.child.prune(limit, newSize, globalSum, importantVariables);
                if (!childBounded) {
                    bounded = false;
                }
                if (bounded) { // if child has been pruned
                    pr = leftChild.child.value;
                    sumLeft += pr * nValidStatesLeft;
                    //entropy += (-probLogProb(pr,pr)) * nValidStatesLeft;
                }
            }


            if (rightChild.child.var == null) { // If right child is a probability node
                pr = rightChild.child.value;
                sumRight += pr * nValidStatesRight;
                //entropy += (-probLogProb(pr,pr)) * nValidStatesRight;
            } else {
                long newSize = (oldSize / nValidStates) * nValidStatesRight;
                boolean childBounded = rightChild.child.prune(limit, newSize, globalSum, importantVariables);
                if (!childBounded) {
                    bounded = false;
                }
                if (bounded) { // if child has been pruned
                    pr = rightChild.child.value;
                    sumRight += pr * nValidStatesRight;
                    //entropy += (-probLogProb(pr,pr)) * nValidStatesRight;
                }
            }



            if (bounded) { // if we are situated at a terminal tree?
                double info;
                double sum = sumLeft + sumRight;
                
              
//print(20);
                if (sum <= 0.0) {
                    info = 0.0;
                } else {

                    double SLeft = (oldSize / nValidStates) * sumLeft;
                    double SRight = (oldSize / nValidStates) * sumRight;
                    double S = SLeft + SRight; // (oldSize /nValidStates) * sum;
              /* info = (S / globalSum) *
                    (probLogProb(1.0,nValidStates)  - probLogProb(1.0,S)
                    - entropy / sum );
                    // (S / globalSum) *(Math.log(var.getNumStates()) - Math.log(S) - entropy / sum);  */
                    info = S * probLogProb(1.0, nValidStates) - probLogProb(S, S)
                            - SLeft * probLogProb(1.0, nValidStatesLeft) - SRight * probLogProb(1.0, nValidStatesRight)
                            + probLogProb(SLeft, SLeft) + probLogProb(SRight, SRight);

                    // Is is needed to divide the computed info by globalSum
                    info = info / globalSum;
                    if(importantVariables != null)
                     info = InfoGainManipulator.ponderateWithSizesVars(info, this, importantVariables);

                }
                
                
                
                //if information gain is very low, it is approximated
                if(info !=0 && Math.abs(info)<IDVEWithPotentialBPTree.limitForPruning) {
                    info=0;
                }
                
              //  System.out.println(info+" <= "+limit+" "+(info <= limit));
                if (info <= limit) { // Prune this tree if info <= limit       
                    pr = sum / nValidStates;
                    assignProb(pr);
                    setFactor(nValidStates);
                    
                } else {
                    bounded = false;
                }
            }
        }
        return bounded;
  }

      /**
     * Prunes this tree by substituing each node whose children are
     * leaves (terminal node) by the average of them. This is done for terminal
     * nodes with an information value lower than a given threshold.
     * This method do not have any effect for a probability tree consisting of
     * only one probability node.
     * This method uses the same measure of information as ProbabilityTree.prune(),
     * @param limit the information threshold for pruning.
     * @param oldSize size (number of leaves) of this tree if it were complete.
     * The value of this parameter is modified in the succesive recursive calls
     * @param globalSum the addition of the original potential. This parameter
     * remains constant in the succesive recursive calls.
     * @return <code>true</code> if the tree has been reduced to a probability
     * node; <code>false</code> otherwise.
     * @see ProbabilityTree.prune(double limit, long oldSize, double globalSum,
     *      long numberDeleted[])
     */
    public boolean pruneIntervals(double limit, long oldSize, double globalSum, boolean lowerBounds, boolean upperBounds) {
        
        
        double pr, sumLeft = 0.0, sumRight = 0.0; //, entropy = 0.0;
        boolean bounded = true; // tell if the tree can be reduced to a probab. node
        double sumLLow = 0.0, sumLUp = 0.0; 
        double sumRLow = 0.0, sumRUp = 0.0;
        
        int nValidStatesLeftLeft = 0, nValidStatesLeftRight = 0; 
        int nValidStatesRightLeft = 0, nValidStatesRightRight = 0;
        
        long sizeUp=0, sizeLow=0, sizeLLow=0, sizeLUp=0, sizeRLow=0, sizeRUp=0; 
        
        if (var != null) {// If we are not at a probability node
            int nValidStatesLeft = leftChild.listStates.numberValidStates();
            int nValidStatesRight = rightChild.listStates.numberValidStates();
            int nValidStates = nValidStatesLeft + nValidStatesRight;
            boolean childrenLowerBounds, childrenUpperBounds;
            
           
            

            double sumLow = 0, sumUp = 0;
            FiniteStates T = getFirstTransparentVar();
            if (T != null) { 
                BinaryProbabilityTree lowTree = restrict(T, 0);
                BinaryProbabilityTree upTree = restrict(T, 1);
                
                BinaryProbabilityTree leftLowTree=null, leftUpTree=null;
                BinaryProbabilityTree rightLowTree=null, rightUpTree=null;

                sizeLow = lowTree.getSizeExpanded();
                sizeUp = upTree.getSizeExpanded();                
                sumLow = lowTree.sum(sizeLow);
                sumUp = upTree.sum(sizeUp);
                
                if(sumUp == 0) {
                    System.out.println("");
                    upTree.getSizeExpanded();
                upTree.sum(sizeUp);
                }
            if(!leftChild.child.isProbab()){
            /*    nValidStatesLeftLeft = leftChild.child.leftChild.listStates.numberValidStates();
                nValidStatesLeftRight = leftChild.child.rightChild.listStates.numberValidStates();
                sizeLLow = oldSize * (nValidStatesLeft) * (nValidStatesLeftLeft);
                sizeLLow = sizeLLow/(nValidStates *((nValidStatesLeftLeft+nValidStatesLeftRight)));                
                sizeLUp = oldSize * (nValidStatesLeft) * (nValidStatesLeftRight);
                sizeLUp = sizeLUp/(nValidStates *((nValidStatesLeftLeft+nValidStatesLeftRight)));
                sumLLow = leftChild.child.leftChild.child.sum(sizeLLow);
                sumLUp = leftChild.child.rightChild.child.sum(sizeLUp);*/
                
                
                leftLowTree = leftChild.child.restrict(T, 0);
                sizeLLow = leftLowTree.getSizeExpanded();
                sumLLow = leftLowTree.sum(sizeLLow);
                
                leftUpTree = leftChild.child.restrict(T, 1);
                sizeLUp = leftUpTree.getSizeExpanded();
                sumLUp = leftUpTree.sum(sizeLUp);
            
            }
            if(!rightChild.child.isProbab()){
            /*    nValidStatesRightLeft = rightChild.child.leftChild.listStates.numberValidStates();
                nValidStatesRightRight = rightChild.child.rightChild.listStates.numberValidStates();
                sizeRLow = oldSize * (nValidStatesRight) * (nValidStatesRightLeft);
                sizeRLow = sizeRLow/(nValidStates *((nValidStatesRightLeft+nValidStatesRightRight)));
                sizeRUp = oldSize * (nValidStatesRight) * (nValidStatesRightRight);
                sizeRUp = sizeRUp/(nValidStates *((nValidStatesRightLeft+nValidStatesRightRight)));
                sumRLow = rightChild.child.leftChild.child.sum(sizeRLow);
                sumRUp = rightChild.child.rightChild.child.sum(sizeRUp);                   
           */
                
                rightLowTree = rightChild.child.restrict(T, 0);
                sizeRLow = rightLowTree.getSizeExpanded();
                sumRLow = rightLowTree.sum(sizeRLow);
                
                rightUpTree = rightChild.child.restrict(T, 1);
                sizeRUp = rightUpTree.getSizeExpanded();
                sumRUp = rightUpTree.sum(sizeRUp);
            }


                
                
             

                
       
                


             
            }
            
            

            if (leftChild.child.var == null) { // If left child is a probability node
                pr = leftChild.child.value;
                sumLeft += pr * nValidStatesLeft;
                // entropy += ( -probLogProb(pr,pr)  ) * nValidStatesLeft;
            } else {
                
                if (getVar().isTransparent()) {
                    childrenUpperBounds = false;
                    childrenLowerBounds = true;
                } else {
                    childrenUpperBounds = upperBounds;
                    childrenLowerBounds = lowerBounds;
                }
                
                
                long newSize = (oldSize / nValidStates) * nValidStatesLeft;
                boolean childBounded = leftChild.child.pruneIntervals(limit, newSize, globalSum,childrenLowerBounds, childrenUpperBounds);
                if (!childBounded) {
                    bounded = false;
                }
                if (bounded) { // if child has been pruned
                    pr = leftChild.child.value;
                    sumLeft += pr * nValidStatesLeft;
                    //entropy += (-probLogProb(pr,pr)) * nValidStatesLeft;
                }
            }


            if (rightChild.child.var == null) { // If right child is a probability node
                pr = rightChild.child.value;
                sumRight += pr * nValidStatesRight;
                //entropy += (-probLogProb(pr,pr)) * nValidStatesRight;
            } else {
                
                
                if (getVar().isTransparent()) {
                    childrenUpperBounds = true;
                    childrenLowerBounds = false;
                } else {
                    childrenUpperBounds = upperBounds;
                    childrenLowerBounds = lowerBounds;
                }
                
                long newSize = (oldSize / nValidStates) * nValidStatesRight;
                boolean childBounded = rightChild.child.pruneIntervals(limit, newSize, globalSum, childrenLowerBounds, childrenUpperBounds);
                if (!childBounded) {
                    bounded = false;
                }
                if (bounded) { // if child has been pruned
                    pr = rightChild.child.value;
                    sumRight += pr * nValidStatesRight;
                    //entropy += (-probLogProb(pr,pr)) * nValidStatesRight;
                }
            }


            /* General case: terminal tree without a transparent variable */
            if (isTerminal() && !getVar().isTransparent()) {

                double info;
                double sum = sumLeft + sumRight;               
              
                if (sum <= 0.0) {
                    info = 0.0;
                } else {

                    double SLeft = (oldSize / nValidStates) * sumLeft;
                    double SRight = (oldSize / nValidStates) * sumRight;
                    double S = SLeft + SRight; // (oldSize /nValidStates) * sum;
              /* info = (S / globalSum) *
                    (probLogProb(1.0,nValidStates)  - probLogProb(1.0,S)
                    - entropy / sum );
                    // (S / globalSum) *(Math.log(var.getNumStates()) - Math.log(S) - entropy / sum);  */
                    info = S * probLogProb(1.0, nValidStates) - probLogProb(S, S)
                            - SLeft * probLogProb(1.0, nValidStatesLeft) - SRight * probLogProb(1.0, nValidStatesRight)
                            + probLogProb(SLeft, SLeft) + probLogProb(SRight, SRight);

                    // Is is needed to divide the computed info by globalSum
                    info = info / globalSum;

                }
                
                
                
                //if information gain is very low, it is approximated
                if(info !=0 && Math.abs(info)<IDVEWithPotentialBPTree.limitForPruning) {
                    info=0;
                }
                
                

                if (info <= limit) { // Prune this tree if info <= limit
                    
                // Determine if the new value is the maximum or the minimum
                if (lowerBounds && !upperBounds) {
                    pr = Math.min(leftChild.child.value, rightChild.child.value);
                } else if (!lowerBounds && upperBounds) {
                    pr = Math.max(leftChild.child.value, rightChild.child.value);
                }else {
                    pr = sum / nValidStates;
                }

                    assignProb(pr);
                    setFactor(nValidStates);
                    
                } else {
                    bounded = false;
                }
            } else if (childrenAreTerminal() && childrenHaveTransparent()) {
                
                double info;

    
                   
   /*             info = - sumUp * (probLogProb(1.0, sumUp) - probLogProb(1.0, sizeUp)) 
                       - sumLow * (probLogProb(1.0, sumLow) - probLogProb(1.0, sizeLow)) 
                       + sumLLow * (probLogProb(1.0, sumLLow) - probLogProb(1.0, sizeLLow)) 
                       + sumLUp * (probLogProb(1.0, sumLUp) - probLogProb(1.0, sizeLUp))  
                       + sumRLow * (probLogProb(1.0, sumRLow) - probLogProb(1.0, sizeRLow)) 
                       + sumRUp * (probLogProb(1.0, sumRUp) - probLogProb(1.0, sizeRUp)) ;
     */
                
                if(sumLow+sumUp == 0) {
                    info = 0;
                
                }if(sumLow==0) {
                      info = - sumUp * (probLogProb(1.0, sumUp) - probLogProb(1.0, sizeUp)) 
                       + sumLUp * (probLogProb(1.0, sumLUp) - probLogProb(1.0, sizeLUp))  
                       + sumRUp * (probLogProb(1.0, sumRUp) - probLogProb(1.0, sizeRUp)) ;

                
                }if(sumUp==0) {
                        info =
                       - sumLow * (probLogProb(1.0, sumLow) - probLogProb(1.0, sizeLow)) 
                       + sumLLow * (probLogProb(1.0, sumLLow) - probLogProb(1.0, sizeLLow)) 
                        + sumRLow * (probLogProb(1.0, sumRLow) - probLogProb(1.0, sizeRLow));
                
                }else{
                
                
                 info = - sumUp * (probLogProb(1.0, sumUp) - probLogProb(1.0, sizeUp)) 
                       - sumLow * (probLogProb(1.0, sumLow) - probLogProb(1.0, sizeLow)) 
                       + sumLLow * (probLogProb(1.0, sumLLow) - probLogProb(1.0, sizeLLow)) 
                       + sumLUp * (probLogProb(1.0, sumLUp) - probLogProb(1.0, sizeLUp))  
                       + sumRLow * (probLogProb(1.0, sumRLow) - probLogProb(1.0, sizeRLow)) 
                       + sumRUp * (probLogProb(1.0, sumRUp) - probLogProb(1.0, sizeRUp)) ;

                
                }

                                    //if information gain is very low, it is approximated
                if(info !=0 && Math.abs(info)<IDVEWithPotentialBPTree.limitForPruning) {
                    info=0;
                }
                
                if (info <= limit) { // Prune this tree if info <= limit
                //Create new terminal subTrees
                
                    double maxmin[] = getMaxMin(); 
                    BinaryProbabilityTree newTree = BinaryProbabilityTree.createTerminalTree(T, 0, maxmin[1], 1, maxmin[0]);   
                    setTree(newTree);
                }
                    
                    

                
                
 
                
                
            }

        }
        return bounded;
  } 
    
    
    
    /**
     * This method allows to get a list of the variables in the tree
     * @return Vector with all the variables
     */

    public Vector getVariables() {
        Vector v = new Vector();
        getVariables(v);
        return v;
    
    }

    /**
     * This private method is used by the public method which returns the list
     * of variables. It searches recursively new variables in the tree and adds them
     * to the vector v
     * @param v
     */
    private void getVariables(Vector v) {

        if(isProbab())
            return;
        if(!v.contains(var))
            v.add(var);

        leftChild.child.getVariables(v);
        rightChild.child.getVariables(v);

    }

    /**
     * This method determines if the node is an internal node or a leaf (probability node)
     * @return boolean variable
     */
    public boolean isProbab() {
        return (leftChild == null && rightChild==null);

    }



        /**
     * Removes a variable by maximizing over it.
     * @param variable a <code>FiniteStates</code> variable to remove.
     * @return a new <code>ProbabilityTree</code> with the result of
     * the operation.
     */

    public BinaryProbabilityTree maximizeOverVariable(FiniteStates variable) {

        BinaryProbabilityTree maxTree, t1, t2;

        


        if (isProbab())
            maxTree = new BinaryProbabilityTree(value); // the value to return is the same.
        else {
                        
            if(var == variable) {
                t1 = this.leftChild.child.maximizeOverVariable(variable);
                t2 = this.rightChild.child.maximizeOverVariable(variable);
                maxTree = t1.max(t2);
            }else {
                maxTree = new BinaryProbabilityTree();
                maxTree.var = var;
                maxTree.leftChild = new BinaryProbabilityTree.BinaryProbabilityTreeInfoChild();
                maxTree.rightChild = new BinaryProbabilityTree.BinaryProbabilityTreeInfoChild();

                t1 = this.leftChild.child.maximizeOverVariable(variable);
                t2 = this.rightChild.child.maximizeOverVariable(variable);
                maxTree.leftChild.setInfo(t1, new BinaryProbabilityTree.ListStates(getLeftStates()));
                maxTree.rightChild.setInfo(t2, new BinaryProbabilityTree.ListStates(getRightStates()));

                
            }
         
        }

        return maxTree;
    }




        /**
     * Integrate the argument tree to this by applying maximization.
     * @param tree a <code>BinaryProbabilityTree</code>.
     * @return a new <code>BinaryProbabilityTree</code> with the maximization of
     * <code>tree</code> and the tree starting in this node.
     */

    public BinaryProbabilityTree max(BinaryProbabilityTree tree) {

        BinaryProbabilityTree maxTree, t1, t2;

        maxTree = new BinaryProbabilityTree();
        t1 = this;
        t2 = tree;
        
        
  /*      System.out.println("Intervalos");
        double maxmin1[] = t1.getMaxMin();
        double maxmin2[] = t2.getMaxMin();
        
        System.out.println("\t["+maxmin1[1]+", "+maxmin1[0]+"]");
        System.out.println("\t["+maxmin2[1]+", "+maxmin2[0]+"]");
        
        
        if(maxmin1[1]>maxmin2[0])
            System.out.println("\tt1 es mayor:"+t1.getSize()+", "+t2.getSize());
        else if(maxmin2[1]>maxmin1[0])
            System.out.println("\tt2 es mayor"+t1.getSize()+", "+t2.getSize());
        else
            System.out.println("\tNo se sabe"+t1.getSize()+", "+t2.getSize());
    */    
        if(t1.isProbab()){
                if(t2.isProbab()){ //Both are probability Nodes: the comparaison is direct
                        maxTree.assignProb(Math.max(t1.value, t2.value));
                        if(Potential.getStatistics() != null)
                            Potential.getStatistics().addMaxMarg(1);
                        
                }else { //Only t1 is a leaf
                    maxTree.var = t2.var;
                    maxTree.leftChild = new BinaryProbabilityTree.BinaryProbabilityTreeInfoChild();
                    maxTree.rightChild = new BinaryProbabilityTree.BinaryProbabilityTreeInfoChild();
                    maxTree.leftChild.setInfo(t2.leftChild.child.max(t1), new BinaryProbabilityTree.ListStates(t2.getLeftStates()));
                    maxTree.rightChild.setInfo(t2.rightChild.child.max(t1), new BinaryProbabilityTree.ListStates(t2.getRightStates()));

                }
        }else{
            
            //None of the trees is a leaf
            maxTree.var = t1.var;
            maxTree.leftChild = new BinaryProbabilityTree.BinaryProbabilityTreeInfoChild();
            maxTree.rightChild = new BinaryProbabilityTree.BinaryProbabilityTreeInfoChild();
            maxTree.leftChild.setInfo(t1.leftChild.child.max(t2.restrict(var, t1.getLeftStates())), new BinaryProbabilityTree.ListStates(t1.getLeftStates()));
            maxTree.rightChild.setInfo(t1.rightChild.child.max(t2.restrict(var, t1.getRightStates())), new BinaryProbabilityTree.ListStates(t1.getRightStates()));

        
        
        }      

        return maxTree;
    }


        /**
     * Divides two trees.
     *
     * For the exception 0/0, the method computes the result as 0.
     * The exception ?/0: the method reorts an error message to the
     * standard output.
     *
     * @param tree a <code>BinaryProbabilityTree</code>.
     * @return a new <code>BinaryProbabilityTree</code> resulting from dividing the
         * tree class with the input tree
     */



        public BinaryProbabilityTree divide(BinaryProbabilityTree tree) {

            BinaryProbabilityTree outTree, t1, t2;

            outTree = new BinaryProbabilityTree();
            t1 = this;
            t2 = tree;

            if(improvedOps){
                if(t1.isProbab() && t1.value == 0) {
                    outTree.assignProb(0);
                    outTree.setFactor((int)t2.getSizeExpanded());
                    return outTree;
                }

                if(t2.isProbab()){
                    if(t2.value==0){
                        outTree.assignProb(0);
                        outTree.setFactor((int)t1.getSizeExpanded());
                        return outTree;
                    }

                    if(t2.value == 1) {
                        outTree = t1.copy();
                        return outTree;
                    }
                }
            }



            if(t1.isProbab()){
                    if(t2.isProbab()){ //Both are probability Nodes: the comparaison is direct
                            if(t2.value==0)
                                outTree.assignProb(0);
                            else{                           
                                //System.out.println("~~"+t1.value+"/"+t2.value+"="+t1.value/t2.value);
                                //outTree.assignProb(BigDecimal.valueOf(t1.value).divide(BigDecimal.valueOf(t2.value),  10, RoundingMode.HALF_UP).doubleValue());
                                outTree.assignProb(t1.value/t2.value);
                                if(Potential.getStatistics() != null)
                                    Potential.getStatistics().addNumDivisions(1);

                            }
                    }else { //Only t1 is a leaf
                        outTree.var = t2.var;
                        outTree.leftChild = new BinaryProbabilityTree.BinaryProbabilityTreeInfoChild();
                        outTree.rightChild = new BinaryProbabilityTree.BinaryProbabilityTreeInfoChild();
                        outTree.leftChild.setInfo(divide(t1,t2.leftChild.child), new BinaryProbabilityTree.ListStates(t2.getLeftStates()));
                        outTree.rightChild.setInfo(divide(t1, t2.rightChild.child), new BinaryProbabilityTree.ListStates(t2.getRightStates()));

                    }
            }else{

                //None of the trees is a leaf
                outTree.var = t1.var;
                outTree.leftChild = new BinaryProbabilityTree.BinaryProbabilityTreeInfoChild();
                outTree.rightChild = new BinaryProbabilityTree.BinaryProbabilityTreeInfoChild();
                outTree.leftChild.setInfo(divide(t1.leftChild.child, t2.restrict(var, t1.getLeftStates())), new BinaryProbabilityTree.ListStates(t1.getLeftStates()));
                outTree.rightChild.setInfo(divide(t1.rightChild.child, t2.restrict(var, t1.getRightStates())), new BinaryProbabilityTree.ListStates(t1.getRightStates()));



            }

            return outTree;
        }



        public BinaryProbabilityTree divide(BinaryProbabilityTree tree, boolean lowerBounds, boolean upperBounds) {
            BinaryProbabilityTree outTree, t1, t2;

            outTree = new BinaryProbabilityTree();
            t1 = this;
            t2 = tree;

            
        boolean leftLowerBounds = lowerBounds, leftUpperBounds=upperBounds;
        boolean rightLowerBounds = lowerBounds, rightUpperBounds=upperBounds;
        
        if(hasTransparent()){
            leftLowerBounds = true;
            leftUpperBounds = false;
            rightLowerBounds = false;
            rightUpperBounds = true;
        }
        
            
           /*
        TODO: check improved operations with intervals
            */ 
        if (improvedOps) {
            if (t1.isProbab() && t1.value == 0) {
                outTree.assignProb(0);
                outTree.setFactor((int) t2.getSizeExpanded());
                return outTree;
            }

            if (t2.isProbab()) {
                if (t2.value == 0) {
                    outTree.assignProb(0);
                    outTree.setFactor((int) t1.getSizeExpanded());
                    return outTree;
                }

                if (t2.value == 1) {
                    outTree = t1.copy();
                    return outTree;
                }
            }
        }

        if (t1.isProbab()) {

            FiniteStates T2 = t2.getFirstTransparentVar();
            if (T2 != null) {
                if (lowerBounds) {
                    if (value > 0) {
                        t2 = t2.restrict(T2, 1);
                    } else {
                        t2 = t2.restrict(T2, 0);
                    }
                } else {
                    if (value > 0) {
                        t2 = t2.restrict(T2, 0);
                    } else {
                        t2 = t2.restrict(T2, 1);
                    }
                }

            }

            if (t2.isProbab()) { //Both are probability Nodes: the comparaison is direct
                if (t2.value == 0) {
                    if(t1.value==0)
                        outTree.assignProb(0);
                    else if(t1.value<0)
                        outTree.assignProb(Double.NEGATIVE_INFINITY);
                    else 
                        outTree.assignProb(Double.POSITIVE_INFINITY);
                } else {

                    //outTree.assignProb(BigDecimal.valueOf(t1.value).divide(BigDecimal.valueOf(t2.value),  10, RoundingMode.HALF_UP).doubleValue());
                    outTree.assignProb(t1.value / t2.value);
                    if (Potential.getStatistics() != null) {
                        Potential.getStatistics().addNumDivisions(1);
                    }

                }
            } else { //Only t1 is a leaf
                outTree.var = t2.var;
                outTree.leftChild = new BinaryProbabilityTree.BinaryProbabilityTreeInfoChild();
                outTree.rightChild = new BinaryProbabilityTree.BinaryProbabilityTreeInfoChild();
                outTree.leftChild.setInfo(t1.divide(t2.leftChild.child, leftLowerBounds, leftUpperBounds), new BinaryProbabilityTree.ListStates(t2.getLeftStates()));
                outTree.rightChild.setInfo(t1.divide(t2.rightChild.child, rightLowerBounds, rightUpperBounds), new BinaryProbabilityTree.ListStates(t2.getRightStates()));

            }
        } else {

            //None of the trees is a leaf
            outTree.var = t1.var;
            outTree.leftChild = new BinaryProbabilityTree.BinaryProbabilityTreeInfoChild();
            outTree.rightChild = new BinaryProbabilityTree.BinaryProbabilityTreeInfoChild();
            outTree.leftChild.setInfo(t1.leftChild.child.divide(t2.restrict(var, t1.getLeftStates()), leftLowerBounds, leftUpperBounds), new BinaryProbabilityTree.ListStates(t1.getLeftStates()));
            outTree.rightChild.setInfo(t1.rightChild.child.divide(t2.restrict(var, t1.getRightStates()), rightLowerBounds, rightUpperBounds), new BinaryProbabilityTree.ListStates(t1.getRightStates()));

        }

        return outTree;
    }

    /**
     * Divides two trees. To be used as a static function.
     *
     * For the exception 0/0, the method computes the result as 0. The exception
     * ?/0: the method reorts an error message to the standard output.
     *
     * @param tree1 a <code>BinaryProbabilityTree</code>.
     * @param tree2 a <code>BinaryProbabilityTree</code>.
     * @return a new <code>BinaryProbabilityTree</code> resulting from combining
     * <code>tree1</code> and <code>tree2</code>.
     */
    public static BinaryProbabilityTree divide(BinaryProbabilityTree tree1,
            BinaryProbabilityTree tree2) {
        return tree1.divide(tree2);
    }

    /**
     * This method allows to get a vector with all the subtrees of a variable <code>var</code>: it will
     * return a tree for each state in the variable. The node with the variable <code>var</code> is not
     * included.
     * @param var variable whose children are returned
     * @param copy boolean variable that indicates if a copy of the trees is made
     * @return Vector with the subtrees
     */

    public Vector getAllSubtrees(FiniteStates var, boolean copy) {
        return getAllSubtrees(var.getName(), copy);
    }

     /**
     * This method allows to get a vector with all the subtrees of a variable <code>var</code>: it will
     * return a tree for each state in the variable. The node with the variable <code>var</code> is not
     * included.
     * @param nameVar name of the variable whose children are returned
     * @param copy boolean variable that indicates if a copy of the trees is made
     * @return Vector with the subtrees
     */


    public Vector getAllSubtrees(String nameVar, boolean copy) {
        Vector v = new Vector();
        if(!isProbab()) {
            if(var.getName().equals(nameVar)) {

                //It checks if the left child variable is the same
                if(!leftChild.child.isProbab() && leftChild.child.var.getName().equals(nameVar))
                   v.addAll(leftChild.child.getAllSubtrees(nameVar, copy));
                else {

                   if(copy)
                    v.add(leftChild.child.copy());
                   else
                    v.add(leftChild.child);
                }

                //It checks if the right child variable is the same
                if(!rightChild.child.isProbab() && rightChild.child.var.getName().equals(nameVar))
                   v.addAll(rightChild.child.getAllSubtrees(nameVar, copy));
                else{

                    if(copy)
                        v.add(rightChild.child.copy());
                    else
                        v.add(rightChild.child);


                }
            }else{

                //Search on the children
                v.addAll(leftChild.child.getAllSubtrees(nameVar, copy));
                v.addAll(rightChild.child.getAllSubtrees(nameVar, copy));
            }
        }
        return v;
    }


    /**
     * Deletes (set a null) the children of the node, if any.
     */

   public void deleteChildren() {
        this.leftChild = null;
        this.rightChild = null;
   }

   /**
    * This method allows to get a tree with only the root variable. Children nodes
    * of a different variable are set as probability nodes with the <code>defValue</code>
    * @param t input tree (is not modified)
    * @param defValue default probability values
    */

   public static void getFirstVariableTree(BinaryProbabilityTree t, double defValue) {

      //Checks if the leftChild is a node of the same variable
       if(!t.leftChild.child.var.getName().equals(t.var.getName())) {
          t.leftChild.child.assignProb(defValue);
          t.leftChild.child.deleteChildren();
      }
      else
          getFirstVariableTree(t.leftChild.child, defValue);


      //Checks if the rightChild is a node of the same variable
      if(!t.rightChild.child.var.getName().equals(t.var.getName())) {
          t.rightChild.child.assignProb(defValue);
          t.rightChild.child.deleteChildren();
      }
      else
          getFirstVariableTree(t.rightChild.child, defValue);

   }

   /**
    * This method add the tree <code>tr</code> at each leaf. The probability
    * of the old leaves are put in one of the new leaves indicated by <code>posProb</code>
    *
    * @param tr Tree added to the leaves (a copy is made)
    * @param posProb position of the old probabilities in the new leaves.
    */

    public void putTreeAtTheEnd(BinaryProbabilityTree tr, int posProb) {


        // Inserts the tree in the left branch if it is a probability node
        if(!leftChild.child.isProbab()) {
            leftChild.child.putTreeAtTheEnd(tr, posProb);
        } else {
            BinaryProbabilityTree tr_copy = tr.copy();
            tr_copy.assignChildProb(leftChild.child.value, posProb);  //Keeps the probability
            leftChild.child = tr_copy;
        }

        // Inserts the tree in the right branch if it is a probability node
        if(!rightChild.child.isProbab()) {
            rightChild.child.putTreeAtTheEnd(tr, posProb);
        } else {
            BinaryProbabilityTree tr_copy = tr.copy();
            tr_copy.assignChildProb(rightChild.child.value, posProb);  //Keeps the probability
            rightChild.child = tr_copy;
        }





    }


         /**
     * Assigns a value to the ith configuration of the variable
     * @param p a <code>double</code> value.
     */
    public void assignChildProb(double p, int i) {

        int leftStates = leftChild.listStates.numberValidStates();
        int rightStates = rightChild.listStates.numberValidStates();


        if(leftStates > i) {    //The ith configuration is in the left side
            if(leftStates == 1)
                leftChild.child.assignProb(p);
            else
                leftChild.child.assignChildProb(p, i);

        }else if(leftStates + rightStates > i){     //The ith configuration is in the right side
            if(rightStates == 1)
                rightChild.child.assignProb(p);
            else
                rightChild.child.assignChildProb(p, i-leftStates);

        }


    }


    /**
     * This method allows to generate a new tree with the root variable at the end. For
     * example, if variables are shown in the order A,B,C,D it will generate an equivalent
     * tree showing the variables in the order B,C,D,A
     * @param tr
     * @return
     */
    public static BinaryProbabilityTree putRootAtTheEnd(BinaryProbabilityTree tr) {

        String rootName = tr.var.getName();

        //Gets a copy of the tree and gets the structure of the root variable
        BinaryProbabilityTree tr_R = tr.copy();
        BinaryProbabilityTree.getFirstVariableTree(tr_R, 1);


        //Get all subtrees of the root variable
        Vector subtrees_R = tr.getAllSubtrees(rootName, true);

        //It adds the root tree at the leaves
        BinaryProbabilityTree subtr;
        for(int i=0; i<subtrees_R.size(); i++) {
            subtr = ((BinaryProbabilityTree)subtrees_R.get(i));
            subtr.putTreeAtTheEnd(tr_R, i);

        }


        //Combines all the subtrees
        tr = ((BinaryProbabilityTree)subtrees_R.get(0));
        for(int i=1; i<subtrees_R.size(); i++) {
            subtr = ((BinaryProbabilityTree)subtrees_R.get(i));
            tr = tr.combine(subtr);
        }


        return tr;
    }




    /**
     * This method calcules the size of the tree (number of leaves).
     * @return size of the tree
     */

    public long getSize() {
        
        
        
        
        long size = 0;
        if(isProbab()) {
            size = 1;
        }else {
            

            
            if(leftChild != null)
                size += leftChild.child.getSize();
            if(rightChild != null)
                size += rightChild.child.getSize();
        }

        return size;

    }

    
    
        /**
     * This method calcules the size of the tree (number of leaves) when the tree
     * is completed expanded.
     * @return size of the tree
     */

    public long getSizeExpanded() {
        long size = 0;
        long sizeLeft=0, sizeRight=0;
  
        
        Vector v = new Vector();
        Vector nstates = new Vector();
        
        getAvailableStatesOfVars(v, nstates);
        
        long res = 1;
        for(int i=0; i<nstates.size();i++){
            res *= ((Integer)nstates.elementAt(i));
        }
        
  /*      if(isProbab()) {
            size = 1;
        }else {
            if(leftChild != null) {
                sizeLeft = leftChild.child.getSizeExpanded();
                if(leftChild.child.isProbab())
                    sizeLeft *= leftChild.listStates.getNumValidStates(); 
            }
            if(rightChild != null)
                sizeRight += rightChild.child.getSizeExpanded();
                if(rightChild.child.isProbab())
                    sizeRight *= rightChild.listStates.getNumValidStates(); 
                
            size = sizeLeft+sizeRight;
        }
*/
        return res;

    }
    
    
    private void getAvailableStatesOfVars(Vector nodes, Vector nstates) {

        if (!isProbab()) {
            if (!nodes.contains(var)) {
                nodes.add(var);
                nstates.add(leftChild.getListStates().getNumValidStates() + rightChild.getListStates().getNumValidStates());
            }
            leftChild.getChild().getAvailableStatesOfVars(nodes, nstates);
            rightChild.getChild().getAvailableStatesOfVars(nodes, nstates);

        }

    }
    
    
    /**
     * Get a list with all the states of a variables in the tree
     * @param v
     * @return
     */
    
    public BinaryProbabilityTree.ListStates getStatesInTree(FiniteStates v) {
    
        BinaryProbabilityTree.ListStates leftStates = new BinaryProbabilityTree.ListStates(v.getNumStates());
        BinaryProbabilityTree.ListStates rightStates = new BinaryProbabilityTree.ListStates(v.getNumStates());
        
        BinaryProbabilityTree.ListStates states = null;
        
        if(isProbab())
            states = new BinaryProbabilityTree.ListStates(v.getNumStates());
        else if(var != v) {
            
            if(leftChild != null) {
                leftStates = leftChild.child.getStatesInTree(v).unionStates(leftStates);
            }
        
            
            if(rightChild != null) {
                rightStates = rightChild.child.getStatesInTree(v).unionStates(rightStates);
            }
            
             states = leftStates.unionStates(rightStates);
        }
        else {

            
            if(leftChild != null) {
                leftStates = getLeftStates().unionStates(leftStates);
                leftStates = leftChild.child.getStatesInTree(v).unionStates(leftStates);
            }
        
            
            if(rightChild != null) {
                rightStates = getRightStates().unionStates(rightStates);
                rightStates = rightChild.child.getStatesInTree(v).unionStates(rightStates);
            }
            
            states = leftStates.unionStates(rightStates);
            
        }
        
        
        
        
        
        
        return states;
    
    }
    
    

    public NodeList getVarList() {
        NodeList n = new NodeList();
        getVarList(n);
       
        return n;
    }
    
    
    private void getVarList(NodeList n) {
        if(isProbab())
            return;
        
        if(n.getId(var)==-1)
            n.insertNode(var);
        
        leftChild.getChild().getVarList(n);
        rightChild.getChild().getVarList(n);
    }

    public double information(FiniteStates y, long potentialSize) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
    /**
     * Calculates the maximun and the minimun value in the tree.
     * @return double array of size 2 where the first value is the maximun
     * and the second is the minimun.
     */
    
    public double[] getMaxMin() {
        
        double maxmin[] = new double[2];
        
        if(isProbab()) {
            maxmin[0] = this.value; //maximum
            maxmin[1] = this.value; //minimum
            
        }else {
            
            maxmin[0] = Double.NEGATIVE_INFINITY;
            maxmin[1] = Double.POSITIVE_INFINITY;
            
            if(leftChild != null && leftChild.child != null) {
                maxmin = leftChild.child.getMaxMin();
            }
            
            double maxminRight[] = new double[2];
            if(rightChild != null && rightChild.child != null) {
                maxminRight = rightChild.child.getMaxMin();
                maxmin[0] = Math.max(maxmin[0], maxminRight[0]);
                maxmin[1] = Math.min(maxmin[1], maxminRight[1]);
                
            }
            
        
        }
        
        
        
        return maxmin;
    } 
    
    
    
    public Vector<Integer> getNumConfigurationsWithVariables(Vector<Node> nodes){
        Vector<Integer> v = new Vector<Integer>();
        
        for(int i=0; i< nodes.size(); i++)
            v.add(getNumConfigurationsWithVariable(nodes.get(i)));
        
        return v;
        
        
    }

    public int getNumConfigurationsWithVariable(Node n) {
    
        int num = 0;
        
        if(isProbab())
            return num;
        
        if(n.equals(var))
            num = (int) getSize();
        else {
            num += leftChild.getChild().getNumConfigurationsWithVariable(n);
            num += rightChild.getChild().getNumConfigurationsWithVariable(n);
        }
        
        return num;
    }
    
        public long getNumPrunedConf(long totalSize, Node n) {
    
        long num = 0;
        
        if(isProbab())
            return totalSize;
        
        
        int leftStates = leftChild.listStates.getNumValidStates();
        int rightStates = rightChild.listStates.getNumValidStates();
        int numStates = leftStates + rightStates;
        
        if(!var.equals(n)){
            
            num += leftChild.getChild().getNumPrunedConf(totalSize * leftStates/numStates, n);
            num += rightChild.getChild().getNumPrunedConf(totalSize * rightStates/numStates, n);

        }else {
            if(leftStates>1)
                num += leftChild.getChild().getNumPrunedConf(totalSize * (leftStates/ numStates), n);
            if(rightStates>1)
                num += rightChild.getChild().getNumPrunedConf(totalSize * (rightStates/numStates), n);

            
        }
            

      
        
        return num;
        
        
    
    }
    

    public static boolean isImprovedOps() {
        return improvedOps;
    }

    public static void setImprovedOps(boolean improvedOps) {
        BinaryProbabilityTree.improvedOps = improvedOps;
    }

    BinaryProbabilityTree addVariable(FiniteStates finiteStates) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    
    public boolean isTerminal() {
        boolean ret = false;

        if (leftChild != null && rightChild != null) {
            if (leftChild.getChild().isProbab() && rightChild.getChild().isProbab()) {
                ret = true;
            }
        }

        return ret;

    }
    

public void replaceVariable(FiniteStates oldVar, FiniteStates newVar) {

    if(!isProbab()) {
        
        boolean replaced = false;
        if(var.equals(oldVar)) {
            var = newVar;
            replaced = true;
        }
        
        if(!isTerminal()) {
            if(!replaced || leftChild.listStates.getNumValidStates()>1)
                leftChild.child.replaceVariable(oldVar, newVar);
            if(!replaced || rightChild.listStates.getNumValidStates()>1)
                rightChild.child.replaceVariable(oldVar, newVar);    
        }
    }
    


}

    public void replaceLowValues(double threshold, boolean absValue) {
      if(isProbab()){
          if(((!absValue) && this.value<=threshold) || ((absValue)&& Math.abs(this.value)<=threshold)) {
              this.value=0;
          }
      }else{
          leftChild.child.replaceLowValues(threshold, absValue);
          rightChild.child.replaceLowValues(threshold, absValue);
      }                  
            
    }

    
    /**
     * It contains the information used to label the children of a
     * <code>BinaryProbabilityTree</code> node
     */
    public static class BinaryProbabilityTreeInfoChild {

        /**
         * A reference to a child of given BinaryProbabilityTree node
         */
        BinaryProbabilityTree child = null;
        /**
         * The list of states associated with this child of a given
         * BinaryProbabilityTree node
         */
        BinaryProbabilityTree.ListStates listStates = null;

        /**
         * Set the fields for this object
         * @param tree the new value for <code>child</code> field
         * @param newListStates the new value for the <code>listStates</code>
         * field
         */
        public void setInfo(BinaryProbabilityTree tree, BinaryProbabilityTree.ListStates newListStates) {
            child = tree;
            listStates = newListStates;

        }
        /**
         * Set the fields for this object
         * @param tree the new value for <code>child</code> field
         * @param newListStates the new value for the <code>listStates</code>
         * field
         */
        public void setInfo(BinaryProbabilityTree tree) {
            child = tree;
        }

        
        /**
         * Gets the tree child
         * @return 
         */
        public BinaryProbabilityTree getChild() {
            return child;
        }

        public ListStates getListStates() {
            return listStates;
        }
        
        
        
        
        

        /**
         * Gets a copy of this BinaryProbabilityTree
         * @return a copy of this BinaryProbabilityTree
         */
        public BinaryProbabilityTree.BinaryProbabilityTreeInfoChild copy() {
            BinaryProbabilityTree.BinaryProbabilityTreeInfoChild infoChild =
                    new BinaryProbabilityTree.BinaryProbabilityTreeInfoChild();
            infoChild.listStates = new BinaryProbabilityTree.ListStates(listStates); //listStates.copy();
            infoChild.child = child.copy();
            return infoChild;
        }






    }




    /**
     * Represents a list of cases of a given FiniteStates variable
     */
    public static class ListStates {

        /**
         * An array with as many elements as states has the variable. Contains
         * true at position i if the state i is included in this ListStates, and
         * false otherwise
         */
        private boolean[] states = null;

        /**
         * Constructs a new ListStates with an empty list of cases (all states are
         * set to false)
         * @param nStates the number of states of the variable of this
         * ListStates
         */
        public ListStates(int nStates) {
            states = new boolean[nStates];
            for (int i = 0; i < nStates; i++) {
                states[i] = false;
            }
        }

        /**
         * Constructs a new ListStates for variable <code>var</code> with a full
         * list of cases (all states are set to true)
         * @param var the variable of this ListStates
         */
        public ListStates(FiniteStates var) {
            int nStates = var.getNumStates();
            states = new boolean[nStates];
            for (int i = 0; i < nStates; i++) {
                states[i] = true;
            }
        }

        /**
         * Constructs a copy of the ListStates <code>listStates</code>
         * @param listStates the ListStates to copy
         */
        public ListStates(BinaryProbabilityTree.ListStates listStates) {
            states = listStates.states.clone();
        }

        /**
         * Returns a vector of booleans with the active states
         * @return 
         */
        public boolean[] getStates() {
            return states;
        }
        
        
        
        

        /**
         * Activate the state <code>i</code> of this ListStates
         * @param i
         */
        private void activateStateAt(int i) {
            if(i>=0)
                setElementAt(i, true);
        }
        
        
        /**
         * Return a new list with the states between <code>i</code> and <code>i-1</code> activated (both included)
         * 
         * @param i first state activated
         * @param j first state not activated
         * 
         */
        public BinaryProbabilityTree.ListStates getPartition(int i, int j) {
                    //Make the partitions
            BinaryProbabilityTree.ListStates partition = new BinaryProbabilityTree.ListStates(this.getNumStates());

            for(int s=i; s<j;s++)
                if(this.states[s]==true)
                partition.activateStateAt(s);
            
            return partition;
            
        }
          /**
         * Return a new list with the states between <code>i</code> and <code>i-1</code> activated (both included)
         * 
         * @param i first state activated
         * @param j first state not activated
         * 
         */
        public BinaryProbabilityTree.ListStates getPartition(int i, int j, int index[]) {
                    //Make the partitions
            BinaryProbabilityTree.ListStates partition = new BinaryProbabilityTree.ListStates(this.getNumStates());

            for(int s=i; s<j;s++)
                if(this.states[index[s]]==true)
                partition.activateStateAt(index[s]);
           
            return partition;
            
        }      

        /**
         * Gets the number of states of the variable of this ListStates
         * @return the number of states of the variable of this ListStates
         */
        private int getNumStates() {
            return states.length;
        }
        
        /**
         * Gets the number of valid states of the variable of this ListStates
         * @return the number of states of the variable of this ListStates
         */
        public int getNumValidStates() {
            int n = 0;
            for(int i=0; i<states.length; i++)
                if(states[i] == true)
                    n++;
            return n;
        }


        /**
         * Gets true if the state at position <code>i</code> is active in this
         * ListState, and false otherwise
         * @param i the position to check
         * @return true if the state at position <code>i</code> is active in this
         * ListState, and false otherwise
         */
        private boolean elementAt(int i) {
            return states[i];
        }

        /**
         * Set the state <code>i</code> of this ListStates at true or false
         * @param i the position to set
         * @param newValue the new value for position <code>i</code>
         */
        public void setElementAt(int i, boolean newValue) {
            states[i] = newValue;
        }

        /**
         * Gets a new ListStates with the intersection of states of this ListStates
         * and the states of <code>setStates2</code>
         * @param setStates2 the ListStates used to get the intersection with this
         * one.
         * @return a new ListStates with the intersection of states of this
         * ListStates and the states of <code>setStates2</code>
         */
        private BinaryProbabilityTree.ListStates intersectionStates(BinaryProbabilityTree.ListStates setStates2) {
            
            
            
            int nStates = getNumStates();
            
            
            BinaryProbabilityTree.ListStates newSetStates = new BinaryProbabilityTree.ListStates(nStates);
            for (int i = 0; i < nStates; i++) {
                if (states[i] && setStates2.states[i]) {
                    newSetStates.states[i] = true;
                } else {
                    newSetStates.states[i] = false;
                }
            }
            return newSetStates;
        }

        /**
         * Gets a new ListStates with the union of states of this ListStates
         * and the states of <code>setStates2</code>
         * @param setStates2 the ListStates used to get the union with this
         * one.
         * @return a new ListStates with the union of states of this
         * ListStates and the states of <code>setStates2</code>
         */
        private BinaryProbabilityTree.ListStates unionStates(BinaryProbabilityTree.ListStates setStates2) {
            int nStates = getNumStates();
            BinaryProbabilityTree.ListStates newSetStates = new BinaryProbabilityTree.ListStates(nStates);
            for (int i = 0; i < nStates; i++) {
                if (states[i] || setStates2.states[i]) {
                    newSetStates.states[i] = true;
                } else {
                    newSetStates.states[i] = false;
                }
            }
            return newSetStates;
        }
        
        
        
        
        /**
         * Returns true if this ListStates do not contains any state, that is
         * all the positions in the array <code>states</code> are false.
         * @return true if this ListStates do not contains any state, that is
         * all the positions in the array <code>states</code> are false.
         */
        private boolean isEmpty() {
            for (int i = 0; i < states.length; i++) {
                if (states[i]) {
                    return false;
                }
            }
            return true;
        }
        
        
        

        

        /**
         * Returns the number of active (valid) states this ListStates represents,
         * that is, the number of positions equals to true.
         * @return the number of valid states this ListStates represents
         */
        private int numberValidStates() {
            int n = 0;
            for (int i = 0; i < states.length; i++) {
                if (states[i]) {
                    n++;
                }
            }
            return n;
        }

        /**
         * Returns the first active (valid) state from position pos, this
         * ListStates represents
         * @return the first valid state from position pos, this ListStates
         * represents or -1 if it does not contains any valid state
         */
        public int getFirstValidState(int pos) {
            for (int i = pos; i < states.length; i++) {
                if (states[i]) {
                    return i;
                }
            }
            return -1;
        }
        
        
                public int getIndexOfNthValid(int n) {
            for (int i = 0; i < this.states.length; i++) {
                if (states[i]) {
                    if (n == 0) {
                        return i;
                    }
                    n--;

                }
            }
            return -1;
        }
        
        
        public int[] getGlobalIndex(int v[]) {
            int index[] = new int[v.length];
            for(int i=0; i<v.length; i++)
                index[i] = getIndexOfNthValid(v[i]);
            
            
            return index;
        }

        
        
        
        
                /**
         * Returns the first active (valid) state from position pos, this
         * ListStates represents. Checks the states following a given order.
         * @return the first valid state from position pos, this ListStates
         * represents or -1 if it does not contains any valid state
         */
        public int getFirstValidState(int pos, int checkOrder[]) {
            
            
            for (int i = pos; i < checkOrder.length; i++) {
            try{    
                if (states[checkOrder[i]]) {
                    return i;
                }
            
            }catch(Exception e) {
                System.out.println();
            }
            
            }
            return -1;
        }
        

        /**
         * Returns true if the state <code>state</code> is active (set to true) in
         * this ListStates
         * @param state the number of state to check if is active
         * @return true if the state <code>state</code> is active (set to true) in
         * this ListStates
         */
        private boolean contains(int state) {
   
            if(state>=states.length || state<0)
                return false;
            return (states[state]);
        }

        /**
         *  Prints the list of active (valid) states to the standard output,
         */
        public void print() {
            boolean firstOnePrinted = false;
            for (int i = 0; i < states.length; i++) {
                if (states[i]) {
                    if (firstOnePrinted) {
                        System.out.print(", " + i);
                    } else {
                        System.out.print(i);
                        firstOnePrinted = true;
                    }
                }
            }
        }
    



            /**
         *  Prints the list of active (valid) states to a filet,
         */
        public void print(PrintWriter p) {
            boolean firstOnePrinted = false;
            for (int i = 0; i < states.length; i++) {
                if (states[i]) {
                    if (firstOnePrinted) {
                        p.print(", " + i);
                    } else {
                        p.print(i);
                        firstOnePrinted = true;
                    }
                }
            }
        }
    }
    /**
     * Gets the number of nodes of the tree starting in this node.
     * @return the number of leaves beneath this tree node.
     */
    public long getNumberOfNodes() {
        if (var == null) { // Probability node
            return 1;
        } else {
            return (1 + leftChild.child.getNumberOfNodes()
                    + rightChild.child.getNumberOfNodes());
        }
    }

    /**
     * Gets the number of nodes of the tree starting in this node.
     * @return the number of leaves beneath this tree node.
     */
    public long getNumberOfLeaves() {
        if (var == null) { // Leaf node
            return 1;
        } else {
            return (leftChild.child.getNumberOfLeaves()
                    + rightChild.child.getNumberOfLeaves());
        }
    }
    
    

    /**
     * Determines if all values are equal or greater than zero
     */
    public boolean isNonNegative() {
        if (isProbab()) { // Leaf node
            return value >= 0;
        } else {
            return leftChild.child.isNonNegative()
                    && rightChild.child.isNonNegative();
        }
    }

    

    /**
     * Determines whether a variable is in the tree starting in this node
     * or not.
     * @param variable a <code>FiniteStates</code> variable.
     * @return <code>true</code> if variable is in some node in the tree,
     *         and <code>false</code> otherwise.
     */
    public boolean isIn(FiniteStates variable) {

        boolean found = false;
        int i;

        if (var == null) {
            found = false;
        } else {
            if (var == variable) {
                found = true;
            } else {
                found = leftChild.child.isIn(variable);
                if (found == false) {
                    found = rightChild.child.isIn(variable);
                }
            }
        }
        return found;
    }

    /**
     * Returns the transparent variables contained in this tree
     * @return A Set<FiniteStates>  with the list of transparent variables
     */
    public Set<FiniteStates> getListTransparents() {
        if (var == null) // If we are at a probability node
        {
            return null;
        }

        HashSet<FiniteStates> transVars = new HashSet<FiniteStates>();
        // If not, check if the root variable is transparent
        if (var.getTransparency() == FiniteStates.TRANSPARENT) {
            // Add it
            transVars.add(var);
        }

        leftChild.child.getListTransparents(transVars);
        rightChild.child.getListTransparents(transVars);

        return transVars;
    }
    
    /**
     * Gets the transparent variables of this tree. It stores
     * the transparent variables in the Set<FiniteStates> argument
     * @param transVars Set<FiniteStates>  where  the transparent variables
     * will be inserted
     */
    private void getListTransparents(Set<FiniteStates> transVars) {
        if (var == null) // If we are at a probability node
        {
            return;
        }

        // If the root var is transparent and is not included in transVars,
        // include it
        if (var.getTransparency() == FiniteStates.TRANSPARENT) {
            transVars.add(var);
        }
        leftChild.child.getListTransparents(transVars);
        rightChild.child.getListTransparents(transVars);
    }


    public void insertVariableInLeaves(FiniteStates newVar) {
        
        if(isProbab()) {
            var = newVar;
            this.leftChild = new BinaryProbabilityTreeInfoChild();
            ListStates states = new ListStates(newVar);
            states.setElementAt(0, true);
            states.setElementAt(1, false);
            this.leftChild = new BinaryProbabilityTree.BinaryProbabilityTreeInfoChild();
            this.leftChild.setInfo(new BinaryProbabilityTree(value), states);
            
            this.rightChild = new BinaryProbabilityTreeInfoChild();
            states = new ListStates(newVar);
            states.setElementAt(0, false);
            states.setElementAt(1, true);
            this.rightChild = new BinaryProbabilityTree.BinaryProbabilityTreeInfoChild();
            this.rightChild.setInfo(new BinaryProbabilityTree(value), states);
        
        }else{        
            if(var != newVar) {
                this.leftChild.child.insertVariableInLeaves(newVar);
                this.rightChild.child.insertVariableInLeaves(newVar);

            }        
        }
  
    }
    
    
        
    public boolean childrenAreTerminal(){
        boolean ret = false;        
        if (getLeftChild() != null && getRightChild() != null
                    && getLeftChild().getChild().isTerminal()
                    && getRightChild().getChild().isTerminal()) {
            ret = true;
        }        
        return ret;
        
    }
    
    public boolean childrenHaveTransparent(){
        boolean ret = false;        
        if (getLeftChild() != null && getRightChild() != null
                    && getLeftChild().getChild().hasTransparent()
                    && getRightChild().getChild().hasTransparent()) {
            ret = true;
        }        
        return ret;
        
    }
    
    
    public boolean hasTransparent() {
        boolean ret = false;
        if(!isProbab() && var.isTransparent()) {
            ret = true;
        }
        
        return ret;
    
    }

    
    public boolean equalLeaves() {
        
        double refValue[] = {Double.NaN};
        return equalLeaves(refValue);
    }
    
    private boolean equalLeaves(double refValue[]){ 

        
       if(isProbab()) {
           if(Double.isNaN(refValue[0])) {
               refValue[0] = value;
               return true;
           
           } else {
               return value==refValue[0];
           
           }
       } 
       
       if(!leftChild.child.equalLeaves(refValue))
           return false;
       
      return rightChild.child.equalLeaves(refValue);
        

    }


}
