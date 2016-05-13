/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package elvira.tools.idiagram;

import elvira.FiniteStates;
import elvira.Node;
import elvira.inference.elimination.ids.IDVEWithPotentialBPTree;
import elvira.inference.elimination.ids.IDVEWithPotentialTree;
import elvira.inference.elimination.ids.IDVariableElimination;
import elvira.potential.binaryprobabilitytree.BinaryProbabilityTree;
import elvira.potential.binaryprobabilitytree.PotentialBPTree;
import elvira.tools.Distances;
import elvira.tools.SortVectors;
import elvira.tools.VectorManipulator;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Vector;

/**
 *
 * @author rcabanas
 */
public class BinaryUtilityManipulator {
    
    
 
            /**
     * Constructs a <code>BinaryProbabilityTree</code> from another one.
     * The new tree is build so that the more informative variables are
     * in the upper levels of the tree and variables are splitted also trying to
     * maximaze such measure of information.
     * @param binaryPT the <code>BinaryProbabilityTree</code> to convert to a
     * sorted BinaryProbabilityTree.
     * @param variables a Vector with the variables of the Potential
    * @param sort boolean variable that indicates if tree is sorted
    * @param threshold limit value for prunning, if it's 0, it is never prunned
    * @param maximum value in the whole tree
    * @param minimum value in the whole tree
     * @return
     */
    public static BinaryProbabilityTree getSortedAndPrunedUtilityTree(BinaryProbabilityTree binaryPT,
            java.util.Vector variables, boolean sort, boolean normalize, IDVEWithPotentialBPTree.pruningMethods method, 
            double threshold, boolean Cindex, double maximum, double minimum, long oldSize, boolean sortStates) {
        
        return getSortedAndPrunedUtilityTree(binaryPT, variables, sort, normalize, method, threshold, Cindex, maximum, minimum, null, oldSize, sortStates);

        
    }
    
    
         /**
     * Constructs a <code>BinaryProbabilityTree</code> from another one.
     * The new tree is build so that the more informative variables are
     * in the upper levels of the tree and variables are splitted also trying to
     * maximaze such measure of information.
     * @param binaryPT the <code>BinaryProbabilityTree</code> to convert to a
     * sorted BinaryProbabilityTree.
     * @param variables a Vector with the variables of the Potential
    * @param sort boolean variable that indicates if tree is sorted
    * @param threshold limit value for prunning, if it's 0, it is never prunned
     * @return
     */
    

    public static BinaryProbabilityTree getSortedAndPrunedUtilityTree(BinaryProbabilityTree binaryPT,
            Vector variables, boolean sort, boolean normalize, IDVEWithPotentialBPTree.pruningMethods method, double threshold, boolean Cindex, long oldSize, boolean sortStates) {
        return getSortedAndPrunedUtilityTree(binaryPT, variables, sort, normalize, method, threshold, Cindex, null, oldSize, sortStates);
    
    }  
    
    
    /**
     * Constructs a <code>BinaryProbabilityTree</code> from another one.
     * The new tree is build so that the more informative variables are
     * in the upper levels of the tree and variables are splitted also trying to
     * maximaze such measure of information.
     * @param binaryPT the <code>BinaryProbabilityTree</code> to convert to a
     * sorted BinaryProbabilityTree.
     * @param variables a Vector with the variables of the Potential
    * @param sort boolean variable that indicates if tree is sorted
    * @param threshold limit value for prunning, if it's 0, it is never prunned
     * @return
     */
    public static BinaryProbabilityTree getSortedAndPrunedUtilityTree(BinaryProbabilityTree binaryPT,
            java.util.Vector variables, boolean sort, boolean normalize, IDVEWithPotentialBPTree.pruningMethods method,
            double threshold, boolean Cindex, Vector<Node> importantVars, long oldSize, boolean sortStates){

       double maxmin[] = binaryPT.getMaxMin();
       double maximum = maxmin[0];
       double minimum = maxmin[1];
       
       return getSortedAndPrunedUtilityTree(binaryPT, variables, sort, normalize, method, threshold, Cindex, maximum, minimum, importantVars, oldSize, sortStates);

    }   
    
    
    
    public static BinaryProbabilityTree getPrunedUtilityTree(BinaryProbabilityTree binaryPT, double threshold, long totalSize, IDVEWithPotentialBPTree.pruningMethods method, double maxmin[]) {

       BinaryProbabilityTree outTree = binaryPT.copy();
       
       
       
       double maximum = maxmin[0];
       double minimum = maxmin[1];
       
       double limitForPruning=0;
       if(method == IDVEWithPotentialBPTree.pruningMethods.EUCLIDEAN)
            limitForPruning = (maximum - minimum)*threshold;
       else
            limitForPruning = threshold;
      // prune(outTree, limitForPruning, totalSize);
       prune(outTree, method, limitForPruning, totalSize);
       // prune(outTree, limitForPruning, totalSize, Integer.MAX_VALUE); old method
       
       return outTree;
    }   
        
    
    
      /**
     * Constructs a <code>BinaryProbabilityTree</code> from another one.
     * The new tree is build so that the more informative variables are
     * in the upper levels of the tree and variables are splitted also trying to
     * maximaze such measure of information.
     * @param binaryPT the <code>BinaryProbabilityTree</code> to convert to a
     * sorted BinaryProbabilityTree.
     * @param variables a Vector with the variables of the Potential
    * @param sort boolean variable that indicates if tree is sorted
    * @param threshold limit value for prunning, if it's 0, it is never prunned
     * @return
     */
    

    public static BinaryProbabilityTree getSortedAndPrunedProbabilityTree(BinaryProbabilityTree binaryPT,
            Vector variables, boolean sort, boolean normalize, IDVEWithPotentialBPTree.pruningMethods method, 
            double threshold, boolean Cindex, long oldSize, boolean sortStates)  {
        return getSortedAndPrunedUtilityTree(binaryPT, variables, sort, normalize, method, threshold, Cindex, null, oldSize, sortStates);
    
    }  
    
    
    /**
     * Constructs a <code>BinaryProbabilityTree</code> from another one.
     * The new tree is build so that the more informative variables are
     * in the upper levels of the tree and variables are splitted also trying to
     * maximaze such measure of information.
     * @param binaryPT the <code>BinaryProbabilityTree</code> to convert to a
     * sorted BinaryProbabilityTree.
     * @param variables a Vector with the variables of the Potential
    * @param sort boolean variable that indicates if tree is sorted
    * @param threshold limit value for prunning, if it's 0, it is never prunned
     * @return
     */
    public static BinaryProbabilityTree getSortedAndPrunedProbabilityTree(BinaryProbabilityTree binaryPT,
            java.util.Vector variables, boolean sort, boolean normalize, IDVEWithPotentialBPTree.pruningMethods method,
            double threshold, boolean Cindex, Vector<Node> importantVars, long oldSize, boolean sortStates)  {


       double maximum = 1;
       double minimum = 0;
       
       return getSortedAndPrunedUtilityTree(binaryPT, variables, sort, normalize, method, threshold, Cindex, maximum, minimum, importantVars, oldSize, sortStates);

    }   
   

    
        /**
     * Constructs a <code>BinaryProbabilityTree</code> from another one.
     * The new tree is build so that the more informative variables are
     * in the upper levels of the tree and variables are splitted also trying to
     * maximaze such measure of information.
     * @param binaryPT the <code>BinaryProbabilityTree</code> to convert to a
     * sorted BinaryProbabilityTree.
     * @param variables a Vector with the variables of the Potential
    * @param sort boolean variable that indicates if tree is sorted
    * @param threshold limit value for prunning, if it's 0, it is never prunned
    * @param maximum value in the whole tree
    * @param minimum value in the whole tree
     * @return
     */
    public static BinaryProbabilityTree getSortedAndPrunedUtilityTree(BinaryProbabilityTree binaryPT,
            java.util.Vector variables, boolean sort, boolean normalize, IDVEWithPotentialBPTree.pruningMethods method, 
            double threshold, boolean Cindex, double maximum, double minimum, Vector<Node> importantVars, long oldSize, boolean sortStates)  {

        
        
        //Normalizes the tree
        double total = 0;            
        total = binaryPT.sum(binaryPT.getSizeExpanded());        
        if(normalize)
            binaryPT.normalize(binaryPT.getSizeExpanded());

        

        
        int nVars = variables.size();

        Vector<FiniteStates> vars = new Vector<FiniteStates>();
        Vector<BinaryProbabilityTree.ListStates> listStatesVector = new Vector<BinaryProbabilityTree.ListStates>();
        BinaryProbabilityTree tree = new BinaryProbabilityTree();
        for (int i = 0; i < nVars; i++) {
            vars.add((FiniteStates)variables.get(i));
            listStatesVector.add(new BinaryProbabilityTree.ListStates((FiniteStates)variables.get(i)));
        }

        

        //Computes the maximun distance for prunning
        //For Euclidean Distance or KLB, it is necessary to compute the maximun distance between
        // the maximun and the minumun of the tree
        double maxDistance=0;
        if(method == IDVEWithPotentialBPTree.pruningMethods.EUCLIDEAN) {

            maxDistance = threshold*(maximum - minimum);

        } else {
            maxDistance = threshold;
        }
        
        //Gets the pruned tree
        tree = getSortedAndPrunedUtilityTree(binaryPT, vars, listStatesVector,sort, method, maxDistance, Cindex, importantVars, oldSize, Double.NaN, Double.NaN, sortStates);
        
        
        //If it has been normalized, it multiplies the tree by the total value
        if(normalize)
            tree.multiply(total);

        return tree;
    }   


    
    
        /**
     * Recursive method that constructs a sorted <code>BinaryProbabilityTree</code>
     * (tree where more informative variables are in the upper levels, and variables
     * are splitted also trying to maximize an information measure)
     * from another BinaryProbabilityTree. This is an auxiliary method for
     * <code>getSortedTreeFromBinaryPT(BinaryProbabilityTree binaryPT,
     *       java.util.Vector variables,long treeSize)</code>.
     * @param originalTree
     * @param vars vector for storing the list of variables
     * @param states vector for storing the states of each variable
     * @param sort indicates if the variables are sorted
     * @param maxDistance maxDistance for prunning
     * @return
     */


    static private BinaryProbabilityTree getSortedAndPrunedUtilityTree(BinaryProbabilityTree originalTree, 
            Vector<FiniteStates> vars, Vector<BinaryProbabilityTree.ListStates> states, boolean sort, IDVEWithPotentialBPTree.pruningMethods method, double maxDistance, boolean Cindex, long oldSize, double sum, double sumSqr, boolean sortStates)  {
        return getSortedAndPrunedUtilityTree(originalTree, vars, states, sort, method, maxDistance, Cindex, null, oldSize,  sum,  sumSqr, sortStates);
    }
    
    
        /**
     * Recursive method that constructs a sorted <code>BinaryProbabilityTree</code>
     * (tree where more informative variables are in the upper levels, and variables
     * are splitted also trying to maximize an information measure)
     * from another BinaryProbabilityTree. This is an auxiliary method for
     * <code>getSortedTreeFromBinaryPT(BinaryProbabilityTree binaryPT,
     *       java.util.Vector variables,long treeSize)</code>.
     * @param originalTree
     * @param vars vector for storing the list of variables
     * @param states vector for storing the states of each variable
     * @param sort indicates if the variables are sorted
     * @param maxDistance maxDistance for prunning
     * @return
     */



    
    /**
     * Recursive method that constructs a sorted <code>BinaryProbabilityTree</code>
     * (tree where more informative variables are in the upper levels, and variables
     * are splitted also trying to maximize an information measure)
     * from another BinaryProbabilityTree. This is an auxiliary method for
     * <code>getSortedTreeFromBinaryPT(BinaryProbabilityTree binaryPT,
     *       java.util.Vector variables,long treeSize)</code>.
     * @param originalTree
     * @param vars vector for storing the list of variables
     * @param states vector for storing the states of each variable
     * @param sort indicates if the variables are sorted
     * @param maxDistance maxDistance for prunning
     * @return
     */

    
  /*      static private BinaryProbabilityTree getSortedAndPrunedUtilityTree(BinaryProbabilityTree originalTree, 
            Vector<FiniteStates> vars, Vector<BinaryProbabilityTree.ListStates> states, boolean sort, IDVEWithPotentialBPTree.pruningMethods method, 
            double maxDistance, boolean Cindex, Vector<Node> importantVars, long oldSize, double sum, double sumSqr) {
        
        }
    
*/
    

    static private BinaryProbabilityTree getSortedAndPrunedUtilityTree(BinaryProbabilityTree originalTree, 
            Vector<FiniteStates> vars, Vector<BinaryProbabilityTree.ListStates> states, boolean sort, IDVEWithPotentialBPTree.pruningMethods method, 
            double maxDistance, boolean Cindex, Vector<Node> importantVars, long oldSize, double sum, double sumSqr, boolean sortStates) {

        
        //It is a probability node
        if(originalTree.isProbab()) {
            return new BinaryProbabilityTree(originalTree.getValue());
        }
        
        

        
        //Gets the best variable and cutpoint
        Hashtable<String, Object>  splittingInfo = selectSplittingUtilityInfo( originalTree, vars, states, sort, method, Cindex, importantVars, oldSize, sum, sumSqr, sortStates);
        
        double maxInfo = (Double)splittingInfo.get("maxInfo");
        double minCindex = (Double)splittingInfo.get("minCindex");
        int bestCutPoint = (Integer)splittingInfo.get("bestCutPoint");
        FiniteStates bestVar = (FiniteStates) splittingInfo.get("bestVar");
        int best_i = ((Integer)splittingInfo.get("best_i"));
        BinaryProbabilityTree bestLeftTree = (BinaryProbabilityTree)splittingInfo.get("bestLeftTree");
        BinaryProbabilityTree bestRightTree = (BinaryProbabilityTree)splittingInfo.get("bestRightTree");
        
        double sumLeft = (Double)splittingInfo.get("sumLeft");
        double sumRight = (Double)splittingInfo.get("sumRight");
        double sumLeftSqr = (Double)splittingInfo.get("sumLeftSqr");
        double sumRightSqr = (Double)splittingInfo.get("sumRightSqr");
        int index[] = (int[])splittingInfo.get("bestIndex");
       // boolean prune = (Boolean)splittingInfo.get("stop");
        
        //Output tree
        BinaryProbabilityTree tree = new BinaryProbabilityTree();
        

        boolean prune = originalTree.equalLeaves();
        

        if(prune) {
                  
            tree.setValue(originalTree.sum(oldSize)/oldSize);
            

        }else{ // The tree is not prunned. It analyzes the children trees
            
            //Puts the best variable in the node
            tree.setVar((FiniteStates) splittingInfo.get("bestVar"));
           
            //Constructs the left branch
            Vector<FiniteStates> leftVarsVector = new Vector<FiniteStates>();
            Vector<BinaryProbabilityTree.ListStates> leftStatesVector = new Vector<BinaryProbabilityTree.ListStates>();
            leftVarsVector.addAll(vars);
            leftStatesVector.addAll(states);
            BinaryProbabilityTree.ListStates leftPartition = leftStatesVector.get(best_i).getPartition(0, bestCutPoint+1, index);
            int nValidStatesLeft = leftPartition.getNumValidStates();
            
            //Updates the sets of variables and states
            if(nValidStatesLeft<2) {
                leftVarsVector.remove(best_i);
                leftStatesVector.remove(best_i);
            }else{
                leftStatesVector.set(best_i, leftPartition);
            }

            
            
            

            //Constructs the right branch        
            Vector<FiniteStates> rightVarsVector = new Vector<FiniteStates>();
            Vector<BinaryProbabilityTree.ListStates> rightStatesVector = new Vector<BinaryProbabilityTree.ListStates>();
            rightVarsVector.addAll(vars);
            rightStatesVector.addAll(states);
            BinaryProbabilityTree.ListStates rightPartition = rightStatesVector.get(best_i).getPartition(bestCutPoint+1, index.length,index);
            int nValidStatesRight = rightPartition.getNumValidStates();
            
            //Updates the sets of variables and states
            if(nValidStatesRight<2) {
                rightVarsVector.remove(best_i);
                rightStatesVector.remove(best_i);
            }else{
                rightStatesVector.set(best_i, rightPartition);
            }
            
            

            int nValidStates = nValidStatesLeft + nValidStatesRight;            
            long leftSize = (oldSize / nValidStates) * nValidStatesLeft;
            long rightSize = (oldSize / nValidStates) * nValidStatesRight;

            BinaryProbabilityTree leftTree = getSortedAndPrunedUtilityTree(bestLeftTree, leftVarsVector, leftStatesVector, sort, method,  maxDistance, Cindex, importantVars, leftSize, sumLeft, sumLeftSqr, sortStates);
            BinaryProbabilityTree rightTree = getSortedAndPrunedUtilityTree(bestRightTree, rightVarsVector, rightStatesVector, sort, method, maxDistance, Cindex, importantVars, rightSize, sumRight, sumRightSqr, sortStates);

            //Builds the links with the children trees
            
            BinaryProbabilityTree.BinaryProbabilityTreeInfoChild leftChild = new BinaryProbabilityTree.BinaryProbabilityTreeInfoChild();
            BinaryProbabilityTree.BinaryProbabilityTreeInfoChild rightChild = new BinaryProbabilityTree.BinaryProbabilityTreeInfoChild();
            leftChild.setInfo(leftTree, leftPartition);
            rightChild.setInfo(rightTree, rightPartition);
            tree.setLeftChild(leftChild);
            tree.setRightChild(rightChild);

        }
        
        return tree;        
        
    }
   
         /**
     * Select the best cutting point and variable for splitting
     * @param originalTree
     * @param vars vector for storing the list of variables
     * @param states vector for storing the states of each variable
     * @param sort indicates if the variables are sorted
     * @return It returns a hashtable with all the information
     */

    public static Hashtable<String, Object> selectSplittingUtilityInfo(BinaryProbabilityTree originalTree, 
            Vector<FiniteStates> vars, Vector<BinaryProbabilityTree.ListStates> states, 
            boolean sort, IDVEWithPotentialBPTree.pruningMethods method, boolean useCindex, long oldSize, double sum, double sumSqr, boolean sortStates)  {
    
    
        return selectSplittingUtilityInfo(originalTree, vars, states, sort, method, useCindex, null, oldSize, sum, sumSqr, sortStates);
        
    }   
    
        /**
     * Select the best cutting point and variable for splitting
     * @param originalTree
     * @param vars vector for storing the list of variables
     * @param states vector for storing the states of each variable
     * @param sort indicates if the variables are sorted
     * @return It returns a hashtable with all the information
     */

    public static Hashtable<String, Object> selectSplittingUtilityInfo(BinaryProbabilityTree originalTree, 
            Vector<FiniteStates> vars, Vector<BinaryProbabilityTree.ListStates> states, boolean sort, 
            IDVEWithPotentialBPTree.pruningMethods method, boolean useCindex, Vector<Node> importantVars, long oldSize, double sum, double sumSqr, boolean sortStates) {
      

        int best_i=0;        
        double d1, d2;
        int bestCutPoint = 0;
        double maxInfo = Double.NEGATIVE_INFINITY;
        double info=0;
        double Cindex = 0;
        double minCindex = Double.POSITIVE_INFINITY;
        FiniteStates bestVar=null;
        boolean stop=false;
        
        double sumChildren[] = {0,0,0,0,0};
        double bestSumChildren[] = {0,0,0,0,0};
        int bestIndex[] = null;
        
        
        
        Hashtable<String, Object>  splittingInfo = new Hashtable<String, Object>();
        
        BinaryProbabilityTree bestLeftTree=new BinaryProbabilityTree(), bestRightTree=new BinaryProbabilityTree();
        BinaryProbabilityTree leftTree=new BinaryProbabilityTree(), rightTree=new BinaryProbabilityTree();
      
            
           //if the tree is not sorted, it is not necessary checks the others variables
           int nVars= vars.size();
           if(!sort)
               nVars = 1;
            
            //Checks each variable
            for(int i=0; i<nVars; i++) {
                   
                int index[];
                int nValidStates = states.get(i).getNumValidStates();
                
                //Sorts the available states of the variable
                if(sortStates) {
                    //Computes the sum for each bra
                    double potentialSum;
                    int state = -1;                    
                    double branchSum[] = new double[nValidStates];
                    BinaryProbabilityTree restrictedOriginalTree;
                    long newTreeSize = oldSize / nValidStates;

                    potentialSum = 0.0;
                    /* Calculate sum for each valid state j of variable i */
                    for (int j = 0; j < nValidStates; j++) {
                        state = states.get(i).getFirstValidState(state + 1);
                        //validStates[j] = state;
                        restrictedOriginalTree = originalTree.restrict(vars.elementAt(i), state);
                        branchSum[j] = restrictedOriginalTree.sum(newTreeSize);
                        potentialSum += branchSum[j];
                    }


                    ArrayList sortInfo = SortVectors.quicksort(branchSum);
                    index = states.get(i).getGlobalIndex((int[]) sortInfo.get(1));
                }else{
                    index = new int[nValidStates];
                    for (int j = 0; j < nValidStates; j++) {
                       index[j]=j;
                    }
                    index = states.get(i).getGlobalIndex(index);
                }
                
            //Checks each possible cut point    
           for(int j=0; j<nValidStates-1; j++) {      
                    info = originalTree.utilityInfoGain(vars.get(i), 0, j, states.get(i), oldSize, leftTree, rightTree, method, sum, sumSqr, sumChildren, index);

                    
                    if(importantVars != null)
                        info = InfoGainManipulator.ponderateInfo(info, originalTree, importantVars);                   
                    if(useCindex)
                        Cindex = Distances.Cindex(leftTree.getLeaves(), rightTree.getLeaves());                  
                    //Replaces the best configuration wheter using gain of information or cindex
                    if(useCindex==false) {
                        if(info>=maxInfo || (Double.isNaN(info) && bestVar == null)) {
                            maxInfo = info;
                            bestVar = vars.get(i);
                            bestCutPoint = j;
                            best_i = i;
                            bestLeftTree = leftTree.copy();
                            bestRightTree = rightTree.copy();
                            
                            bestSumChildren[0] = sumChildren[0];
                            bestSumChildren[1] = sumChildren[1];
                            bestSumChildren[2] = sumChildren[2];
                            bestSumChildren[3] = sumChildren[3];
                            stop = (sumChildren[4]==1);
                            
                            bestIndex = index;
                            

                        }
                    }else{
                        
                       
                        if(minCindex>Cindex || (Double.isNaN(Cindex) && bestVar == null)) {

                            maxInfo = info;
                            minCindex = Cindex;
                            bestVar = vars.get(i);
                            bestCutPoint = j;
                            best_i = i;
                            bestLeftTree = leftTree.copy();
                            bestRightTree = rightTree.copy();
                            bestIndex = index;
                        }    
                    
                    
                    }

                }
                
            }
        
        
        

        //To avoid problems with very small values
        //if information gain is very low, it is approximated
   /*     if(info != 0.0 && Math.abs(info)<IDVEWithPotentialBPTree.limitForPruning){
            info=0;
        }
*/

        
        /*
         * Stores the splitting information in the hashtable
         */
        splittingInfo.put("minCindex", minCindex);
        splittingInfo.put("bestVar", bestVar);
        splittingInfo.put("bestCutPoint", bestCutPoint);
        splittingInfo.put("best_i", best_i);
        splittingInfo.put("bestRightTree", bestRightTree);
        splittingInfo.put("bestLeftTree", bestLeftTree);
        splittingInfo.put("maxInfo", maxInfo);
        splittingInfo.put("sumLeft", bestSumChildren[0]);
        splittingInfo.put("sumRight", bestSumChildren[1]);
        splittingInfo.put("sumLeftSqr", bestSumChildren[2]);
        splittingInfo.put("sumRightSqr", bestSumChildren[3]);
        splittingInfo.put("bestIndex", bestIndex);
        splittingInfo.put("stop", stop);
        return splittingInfo;

    }
    
    
    
    public static double prune(BinaryProbabilityTree originalTree, double limit, long oldSize) {
        return prune(originalTree, limit, oldSize, Integer.MAX_VALUE);
        
    }
    

    //OLD version
    public static double prune(BinaryProbabilityTree originalTree, double limit, long oldSize, int maxDepth) {
        
        BinaryProbabilityTree tree = originalTree;
        
        boolean bounded = true; // tell if the tree can be reduced to a probab. node
        long leftSize = 1;
        long rightSize = 1;
        double outDist = Double.NaN;
        double leftDistance=0, rightDistance=0;
        double sumLeaves = tree.sum(oldSize);
        double sumLeavesSqr = tree.sumSqr(oldSize);
        

        if (!tree.isProbab()) {// If we are not at a probability node
            int nValidStatesLeft = tree.getLeftChild().getListStates().getNumValidStates();
            int nValidStatesRight = tree.getRightChild().getListStates().getNumValidStates();
            int nValidStates = nValidStatesLeft + nValidStatesRight;

            if (!tree.getLeftChild().getChild().isProbab()) { // If left child is not a leaf

                leftSize = (oldSize / nValidStates) * nValidStatesLeft;
                leftDistance = prune(tree.getLeftChild().getChild(), limit, leftSize, maxDepth-1);
                if (Double.isNaN(leftDistance)) {
                    bounded = false;
                }

            }


            if (!tree.getRightChild().getChild().isProbab()) { // If left child is not a leaf
                rightSize = (oldSize / nValidStates) * nValidStatesRight;
                rightDistance = prune(tree.getRightChild().getChild(), limit, rightSize, maxDepth-1);
                if (Double.isNaN(rightDistance)) {
                    bounded = false;
                }
            }




            if (bounded) { // if we are situated at a terminal tree?
                double info;
                

                double leftValue = tree.getLeftChild().getChild().getValue();
                double rightValue = tree.getRightChild().getChild().getValue();
                double mean = (leftSize*leftValue + rightSize*rightValue)/(leftSize+rightSize);
                
                
                double d1 = Math.sqrt(sumLeavesSqr - Math.pow(sumLeaves, 2)/oldSize);
                double d2 = Math.sqrt(Math.pow(leftDistance, 2) + Math.pow(rightDistance,2));
                
                if(Double.isNaN(d1)) 
                    d1 = 0;
                if(Double.isNaN(d2)) 
                    d2 = 0;               
                
                
                info = d1 - d2;
                
                //if information gain is very low, it is approximated
                if(info !=0 && Math.abs(info)<IDVEWithPotentialBPTree.limitForPruning) {
                    info=0;
                }
                
                // Prune this tree if info <= limit 
                if (info <= limit || maxDepth <=0) {     
                    
                  tree.assignProb(mean);    
                  outDist = d1;
                }
            }
        }
        
        originalTree = tree;
        return outDist;
  }    


       public static double[] prune(BinaryProbabilityTree originalTree, IDVEWithPotentialBPTree.pruningMethods method,  double limit, long oldSize) {
        
        BinaryProbabilityTree tree = originalTree;
        

        long leftSize = 1;
        long rightSize = 1;
        
        
        double sumLeaves = 0;
        double sumLeavesSqr = 0;
        
        double sumLeft=0, sumRight=0;
        double sumLeftSqr=0, sumRightSqr;
        
        double leftPruneInfo[] = null, rightPruneInfo[] = null;
        

        if (!tree.isProbab()) {// If we are not at a probability node
            int nValidStatesLeft = tree.getLeftChild().getListStates().getNumValidStates();
            int nValidStatesRight = tree.getRightChild().getListStates().getNumValidStates();
            int nValidStates = nValidStatesLeft + nValidStatesRight;

            if (!tree.getLeftChild().getChild().isProbab()) { // If left child is not a leaf
                leftSize = (oldSize / nValidStates) * nValidStatesLeft;
                leftPruneInfo = prune(tree.getLeftChild().getChild(), method, limit, leftSize);
            }else{
                leftPruneInfo = new double[2];
                leftPruneInfo[0] = tree.getLeftChild().getChild().getValue();
                leftPruneInfo[1] = Math.pow(leftPruneInfo[0],2);
            
            }

            if (!tree.getRightChild().getChild().isProbab()) { // If left child is not a leaf
                rightSize = (oldSize / nValidStates) * nValidStatesRight;
                rightPruneInfo = prune(tree.getRightChild().getChild(), method, limit, rightSize);
            }else{
                rightPruneInfo = new double[2];
                rightPruneInfo[0] = tree.getRightChild().getChild().getValue();
                rightPruneInfo[1] = Math.pow(rightPruneInfo[0],2);
            
            }




            if (tree.isTerminal()) { // if we are situated at a terminal tree?
                double info=0;

                double leftValue = tree.getLeftChild().getChild().getValue();
                double rightValue = tree.getRightChild().getChild().getValue();
                double mean = (leftSize*leftValue + rightSize*rightValue)/(leftSize+rightSize);
               
                sumLeft = leftPruneInfo[0];
                sumRight = rightPruneInfo[0];
                sumLeftSqr = leftPruneInfo[1];
                sumRightSqr = rightPruneInfo[1];
                
                sumLeaves = sumLeft+sumRight;
                sumLeavesSqr = sumLeftSqr + sumRightSqr;
                
                double d1, d2;
                
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

                        break;
                    case KULLBACK_LEIBLER_DISTANCE:

                        info = sumLeaves * BinaryProbabilityTree.probLogProb(1.0, nValidStates) - BinaryProbabilityTree.probLogProb(sumLeaves, sumLeaves)
                         - (sumLeft * BinaryProbabilityTree.probLogProb(1.0, nValidStatesLeft) + sumRight * BinaryProbabilityTree.probLogProb(1.0, nValidStatesRight)
                                - BinaryProbabilityTree.probLogProb(sumLeft, sumLeft) - BinaryProbabilityTree.probLogProb(sumRight, sumRight));


                       // info = info/sumLeaves;
                        
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
                        break;

                    case EXT_JACCARD:

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


                        if (info <= 0 && sumLeaves / oldSize != Math.sqrt(sumLeavesSqr / oldSize)) {
                            info = Double.MIN_VALUE;
                        }

                        break;
                    default:
                        System.err.print("ERROR: wrong pruning method");
                        System.exit(1);


                }
                
                //if information gain is very low, it is approximated
                if(info !=0 && Math.abs(info)<IDVEWithPotentialBPTree.limitForPruning) {
                    info=0;
                }
                
                // Prune this tree if info <= limit
             //   System.out.println(info+" <= "+limit+" "+(info <= limit));
                if (info <= limit) {   
                  tree.assignProb(mean);    
                }
            }
        }
        
        originalTree = tree;
        
        double pruneInfo[] = {sumLeaves, sumLeavesSqr};
        return pruneInfo;
  }    
 
    
    
    public static BinaryProbabilityTree getPrunedIntervalUtilityTree(BinaryProbabilityTree binaryPT, double threshold, long totalSize) {

       BinaryProbabilityTree outTree = binaryPT.copy();
       double maxmin[] = binaryPT.getMaxMin();
       double maximum = maxmin[0];
       double minimum = maxmin[1];
       double limitForPruning = (maximum - minimum)*threshold;

       pruneIntervals(outTree, limitForPruning, totalSize,true,true);
       
       return outTree;
    }  
    
    
    public static BinaryProbabilityTree getPrunedUtilityTree(BinaryProbabilityTree binaryPT, double threshold, long totalSize, int maxDepth) {

       BinaryProbabilityTree outTree = binaryPT.copy();
       double maxmin[] = binaryPT.getMaxMin();
       double maximum = maxmin[0];
       double minimum = maxmin[1];
       double limitForPruning = (maximum - minimum)*threshold;

       prune(outTree, limitForPruning, totalSize,maxDepth);
       
       return outTree;
    }  
    

    public static double pruneIntervals(BinaryProbabilityTree originalTree, double limit, long oldSize, boolean lowerBounds, boolean upperBounds) {
        
        BinaryProbabilityTree tree = originalTree;
        long leftSize = 1;
        long rightSize = 1;
        double outDist = Double.NaN;
        double leftDistance = 0, rightDistance = 0;
        
        // If we are not in leaf node, it could be pruned
        if (!tree.isProbab()) {

            /*All the date needed to compute information gain should
             be obtained from the original sub tree */
            double sumLeaves = tree.sum(oldSize);
            double sumLeavesSqr = tree.sumSqr(oldSize);
            boolean childrenLowerBounds, childrenUpperBounds;
            double sumLowLeaves = 0, sumLowLeavesSqr = 0;
            double sumUpLeaves = 0, sumUpLeavesSqr = 0;
            FiniteStates T = tree.getFirstTransparentVar();
            if (T != null) {
                BinaryProbabilityTree lowTree = tree.restrict(T, 0);
                BinaryProbabilityTree upTree = tree.restrict(T, 1);

                sumLowLeaves = lowTree.sum(oldSize / 2);
                sumLowLeavesSqr = lowTree.sumSqr(oldSize / 2);
                sumUpLeaves = upTree.sum(oldSize / 2);
                sumUpLeavesSqr = upTree.sumSqr(oldSize / 2);
            }

            /* If children are not leaves, try to prune them */
            int nValidStatesLeft = tree.getLeftChild().getListStates().getNumValidStates();
            int nValidStatesRight = tree.getRightChild().getListStates().getNumValidStates();
            int nValidStates = nValidStatesLeft + nValidStatesRight;
            
            if (!tree.getLeftChild().getChild().isProbab()) {

                if (tree.getVar().isTransparent()) {
                    childrenUpperBounds = false;
                    childrenLowerBounds = true;
                } else {
                    childrenUpperBounds = upperBounds;
                    childrenLowerBounds = lowerBounds;

                }

                leftSize = (oldSize / nValidStates) * nValidStatesLeft;
                leftDistance = pruneIntervals(tree.getLeftChild().getChild(), limit, leftSize, childrenLowerBounds, childrenUpperBounds);

            }

            if (!tree.getRightChild().getChild().isProbab()) { // If left child is not a leaf

                if (tree.getVar().isTransparent()) {
                    childrenUpperBounds = true;
                    childrenLowerBounds = false;
                } else {
                    childrenUpperBounds = upperBounds;
                    childrenLowerBounds = lowerBounds;

                }

                rightSize = (oldSize / nValidStates) * nValidStatesRight;
                rightDistance = pruneIntervals(tree.getRightChild().getChild(), limit, rightSize, childrenLowerBounds, childrenUpperBounds);
            }
 
            /* General case: terminal tree without a transparent variable */
            if ((tree.isTerminal() || tree.isProbab()) && !tree.getVar().isTransparent()) {

                double info;
                double leftValue = tree.getLeftChild().getChild().getValue();
                double rightValue = tree.getRightChild().getChild().getValue();
                double newValue = (leftSize * leftValue + rightSize * rightValue) / (leftSize + rightSize);

                // Determine if the new value is the maximum or the minimum
                if (lowerBounds && !upperBounds) {
                    newValue = Math.min(leftValue, rightValue);
                } else if (!lowerBounds && upperBounds) {
                    newValue = Math.max(leftValue, rightValue);
                }

                //Compute the info gain
                double d1 = Math.sqrt(sumLeavesSqr - Math.pow(sumLeaves, 2) / oldSize);
                double d2 = Math.sqrt(Math.pow(leftDistance, 2) + Math.pow(rightDistance, 2));
                if (Double.isNaN(d1)) d1 = 0;
                if (Double.isNaN(d2)) d2 = 0;
                info = d1 - d2;

                //if information gain is very low, it is approximated
                if (info != 0 && Math.abs(info) < IDVEWithPotentialBPTree.limitForPruning) {
                    info = 0;
                }

                // Prune this tree if info <= limit 
                if (info <= limit) {
                    tree.assignProb(newValue);
                    outDist = d1;
                }
            
            /* Variable is transparent: just send up the distance*/
            } else if (tree.getVar().isTransparent()) {
                outDist = Math.sqrt(Math.pow(leftDistance, 2) + Math.pow(rightDistance, 2));
                
            /* Both children are terminal trees with the transparent variables */    
            } else if (tree.childrenAreTerminal() && tree.childrenHaveTransparent()) {

                //Compute the info gain
                double d1 = Math.sqrt(sumLowLeavesSqr - Math.pow(sumLowLeaves, 2) / (oldSize / 2) + sumUpLeavesSqr - Math.pow(sumUpLeaves, 2) / (oldSize / 2));
                double d2 = Math.sqrt(Math.pow(leftDistance, 2) + Math.pow(rightDistance, 2));

                if (Double.isNaN(d1)) d1 = 0;
                if (Double.isNaN(d2)) d2 = 0;

                double info = d1 - d2;

                //if information gain is very low, it is approximated
                if (info != 0 && Math.abs(info) < IDVEWithPotentialBPTree.limitForPruning) {
                    info = 0;
                }
                // Prune this tree if info <= limit 
                if (info <= limit) {

                    double maxmin[] = tree.getMaxMin();
                    
                    BinaryProbabilityTree newTree = BinaryProbabilityTree.createTerminalTree(T, 0, maxmin[1], 1, maxmin[0]);
                    
                    tree.setTree(newTree);
                    
                    outDist = d1;
                }



            }
        }           //                      
        originalTree = tree;
        return outDist;
    }





}
