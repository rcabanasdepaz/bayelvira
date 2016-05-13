/*
 */
package elvira.potential.binaryprobabilitytree;

import java.util.Vector;
import java.io.*;
import elvira.*;
import elvira.inference.elimination.ids.IDVEWithPotentialBPTree.pruningMethods;
import elvira.potential.Potential;
import elvira.parser.ParseException;
import elvira.potential.PotentialTable;
import elvira.potential.PotentialTree;
import elvira.potential.ProbabilityTree;
import elvira.tools.CmdLineArguments;
import elvira.tools.CmdLineArguments.CmdLineArgumentsException;
import elvira.tools.CmdLineArguments.argumentType;
import elvira.tools.VectorManipulator;
import elvira.tools.idiagram.BinaryUtilityManipulator;
import elvira.tools.idiagram.EUComparator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This is a potential whose values are represented by a binary probability tree.
 * A binary probability tree is a compact representation of a probability distribution,
 * alternative to a probability table. 
 * Each internal node represents a variable and each leaf
 * node represents a probability value. Each variable node (internal node)
 * has two children. Each child of an internal node is labeled with a set of 
 * states of the variable of that internal node.
 * The value stored in a leaf node corresponds to the probability of the
 * configurations of variables consistent with the labeling from the root to that leaf.
 * 
 * @author Andrés Cano Utrera (acu@decsai.ugr.es)
 * @author Rafael Cabañas de Paz (rcabanas@decsai.ugr.es)
 */
public class PotentialBPTree extends Potential {

   /**
    * A <code>BinaryProbabilityTree</code> with the values of this
    * <code>PotentialBPTree</code>.
    */
   protected BinaryProbabilityTree values;
   


   public PotentialBPTree() {

   }

   /**
    * Constructs a <code>PotentialBPTree</code> from another
    * <code>Potential</code>.
    *
    * @param <code>pot</code> the <code>Potential</code> to be transformed to
    * <code>PotentialBPTree</code>.
    */
   public PotentialBPTree(Potential pot) {
      Vector vars;

      vars = (Vector) pot.getVariables().clone();
      setVariables(vars);
      if(pot instanceof elvira.potential.PTreeCredalSet)
          values=BinaryProbabilityTree.getTreeFromPTreeCredalSet((elvira.potential.PTreeCredalSet)pot);
      else
          values = BinaryProbabilityTree.getTreeFromPotential(pot);
   }

       /**
     * Creates a new <code>PotentialBPTree</code> for a given list of variables
     * and a tree with a single value equal to 0.
     * @param vars a <code>Vector</code> of variables (<code>FiniteStates</code>)
     * that the potential will contain.
     */

    public PotentialBPTree(Vector vars) {

        setVariables((Vector)vars.clone());
        values = new BinaryProbabilityTree();
        values.assignProb(0);

    }

    public PotentialBPTree(NodeList vars) {
        setVariables((Vector)vars.getNodes().clone());
        values = new BinaryProbabilityTree(0);
    }


   /**
    * Combines this potential with the argument. The argument <code>pot</code>
    * can be any class of <code>Potential</code> .
    * @returns a new <code>PotentialBPTree</code> consisting of the combination
    * of <code>pot</code> and this <code>PotentialBPTree</code>.
    */
   public Potential combine(Potential pot) {
      PotentialBPTree newPot = new PotentialBPTree();
      newPot.setVariables(SetVectorOperations.union(getVariables(),
              pot.getVariables()));
      newPot.values = values.combine(((PotentialBPTree) pot).values);
      
     if(head != null && pot.getHead() != null) {
        newPot.setHead(Potential.getCombinationHead(this, pot));
        newPot.setTail(Potential.getCombinationTail(this, pot));
    }

     
     
      return newPot;
   }
   
   
   

   public Potential combineIntervals(Potential pot) {
      PotentialBPTree newPot = new PotentialBPTree();
      Vector vars = SetVectorOperations.union(getVariables(),
              pot.getVariables());
      vars.remove(pot.getListTransparents().elementAt(0));
      
      newPot.setVariables(vars);
      newPot.values = values.combine(((PotentialBPTree) pot).values,true,true);
      
      
      
     if(head != null && pot.getHead() != null) {
        newPot.setHead(Potential.getCombinationHead(this, pot));
        newPot.setTail(Potential.getCombinationTail(this, pot));
    }

      return newPot;
   }
   

   
   
       /**
     * Sums this potential with <code>Potential</code> pot
     * @param p the <code>Potential</code> to sum with this
     * <code>Potential</code>.
     * @return a new <code>PotentialTree</code> consisting of the sum
     * of <code>p</code> and this <code>Potential</code>.
     */
    
    @Override
    public Potential addition(Potential p) {
        Vector v, v1, v2;
        FiniteStates aux;
        int i, nv;
        PotentialBPTree pot;
        double x;
        BinaryProbabilityTree tree, tree1, tree2;
        
        if (p.getClass().getName().equals("elvira.potential.binaryprobabilitytree.PotentialBPTree")) {
            v1 = getVariables();   // Variables of this potential.
            v2 = p.getVariables(); // Variables of the argument.
            v = new Vector(); // Variables of the new potential.
            for (i=0 ; i<v1.size() ; i++) {
                aux = (FiniteStates)v1.elementAt(i);
                v.addElement(aux);
            }
            for (i=0 ; i<v2.size() ; i++) {
                aux = (FiniteStates)v2.elementAt(i);
                if (aux.indexOf(v1) == -1)
                    v.addElement(aux);
            }
            // The new Potential.
            pot = new PotentialBPTree(v);
            tree1 = getTree();                           // Tree of this potential.
            tree2 = ((PotentialBPTree)p).getTree();        // Tree of the argument.
           
            tree = tree1.sum(tree2);
            //tree = BinaryProbabilityTree.sum(tree1,tree2); // The new tree.
            pot.setTree(tree);
        }
        else {
            System.out.println("Error in Potential PotentialTree.combine(Potential p): argument p was not a PotentialTree nor a PotentialTable nor a PotentialConvexSet");
            System.exit(1);
            pot = this;
        }
        
        return pot;
    }
   
    
        /**
     * Sums this potential with the <code>PotentialTable</code>
     * of the argument.
     * @param p a <code>PotentialTable</code>.
     * @returns a new <code>PotentialTree</code> consisting of the combination
     * of <code>p (PotentialTable)</code> and this <code>PotentialTree</code>.
     */
    
    public PotentialTree sum(PotentialTable p) {
        PotentialTree pt;
        pt = p.toTree();
        return (PotentialTree)addition((Potential)pt);
    }

   /**
    * Removes the variable var from this PotentialBPTree summing over all
    * its values.
    * @param var a <code>FiniteStates</code> variable.
    * @return a new <code>BinaryPotentialTree</code> with the result of the 
    * operation.
    */
   public Potential addVariable(Node var) {
      PotentialBPTree newPot = new PotentialBPTree();
      Vector potVariables;

      potVariables = (Vector) getVariables().clone();
      potVariables.removeElement(var);
      newPot.setVariables(potVariables);
      //newPot.values = values.addVariable((FiniteStates) var, (int)values.getSize());
      newPot.values = values.addVariable((FiniteStates) var, ((FiniteStates)var).getNumStates());

      
      
      return newPot;
   }

   /**
    * Restricts this potential to a configuration of variables.
    *
    * @param conf the <code>Configuration</code>.
    * @return a new PotentialBPTree with the result of the operation.
    */
   public Potential restrictVariable(Configuration conf) {
      PotentialBPTree newPot = new PotentialBPTree();
      BinaryProbabilityTree newTree;
      Vector potVariables = new Vector();
      int position;
      boolean found = false;
      int nVars = getVariables().size();
      FiniteStates finiteStatesVar;

      newTree = values;
      for (int i = 0; i < nVars; i++) {
         finiteStatesVar = (FiniteStates) getVariables().elementAt(i);
         position = conf.indexOf(finiteStatesVar);
         if (position == -1) { // If finiteStatesVar is not in conf, add it to the new list.
            potVariables.addElement(finiteStatesVar);
         } else {  // Otherwise, restrict the tree to it.
            newTree = newTree.restrict(finiteStatesVar, conf.getValue(position));
            found = true;
         }
      }
      if (!found) { // If none variable was deleted
         newTree = values.copy(); // we make a copy of the tree        
      }
      newPot.setVariables(potVariables);
      newPot.values = newTree;
      return newPot;
   }

   /**
    * Prints this <code>PotentialBPTree</code> to the standard output.
    */
   @Override
   public void print() {
      super.print();
      System.out.println("Number of leaves: "+getNumberOfLeaves());
      values.print(10);
   }

   
   public void printTikz(double posX, double posY,int initState) {
       System.out.println(values.printTikz(posX, posY, 1, initState)+";");
   }
   
  
   public String toStringTikz(double posX, double posY,int initState) {
       return values.printTikz(posX, posY, 1, initState)+";";
   }
   
   
   /**
    * Normalizes this potential to sum up to one.
    */
   @Override
   public void normalize() {
      long totalSize;

      totalSize = (long) FiniteStates.getSize(getVariables());
      values.normalize(totalSize);
   }

   /**
    * Gets the value for a configuration.
    * @param conf a <code>Configuration</code> of FiniteState variables.
    * @return the value corresponding to <code>Configuration conf</code>.
    */
   @Override
   public double getValue(Configuration conf) {
       

      return values.getProb(conf);
   }

   
   
   
   
   /**
    * Returns the instance of the class BinaryProbabilityTree
    * @return 
    */
    public BinaryProbabilityTree getValues() {
        
        
        
        return values;
    }

    /**
     * Computes the addition of all the values in this tree, starting
     * at this node.
     * @param treeSize size of the completely expanded tree, that is, the number
     * of possible configurations of the variables in this potential.
     * @return the  computed addition of values in this tree.
     */
    @Override
    public double sum(long treeSize) {
        return values.sum(treeSize);
    
    }
  
    /**
     * Computes the addition of all the values in this tree, starting
     * at this node.
     * @return the  computed addition of values in this tree.
     */
    @Override
    public double sum() {
        //return values.sum(getSize());
        return values.sum(getNumConfigurations());
    
    }
   
   
    public void limitBound(double limit) {
        long maxSize;
        double globalSum;
        maxSize = (long) FiniteStates.getSize(getVariables());
        globalSum = values.sum(maxSize);
        values.prune(limit, maxSize, globalSum);
    }
   
    

   
    
   
   /**
    * Bounds the tree associated with this potential by removing
    * nodes whose information value is lower than  threshold "limit".
    * 
    * @param limit the information limit.
    * @see BinaryProbabilityTree.prune(double limit)
    */
   public void limitBound(double limit, Vector<Node> importantVariables) {
         long maxSize;
            double globalSum;

            maxSize = (long) FiniteStates.getSize(getVariables());
            globalSum = values.sum(maxSize);          
            values.prune(limit, maxSize, globalSum, importantVariables);

   }
   
   public void sort(){
      values = BinaryProbabilityTree.getSortedTreeFromBinaryPT(values, 
              getVariables(), false);  
   }
   public void sort(boolean sortStates){
      values = BinaryProbabilityTree.getSortedTreeFromBinaryPT(values, 
              getVariables(), sortStates);  
   }

   
   public void sortAndBound(double threshold){
      values = BinaryProbabilityTree.getSortedAndPrunedTreeFromBinaryPT(values, variables, threshold, false);
   }
   
   
   public void sortAndBound(double threshold, boolean sortStates){
      values = BinaryProbabilityTree.getSortedAndPrunedTreeFromBinaryPT(values, variables, threshold, sortStates);
   }
 
      /**
    * This method sorts and prune a probability tree
    * @param sort boolean variable that indicates if tree is sorted
    * @param threshold limit value for prunning, if it's 0, it is never prunned
    */
   
   public void sortAndPruneProbability(boolean sort, double threshold, Vector<Node> importatVariables, boolean sortStates){

       BinaryProbabilityTree newTree =  BinaryUtilityManipulator.getSortedAndPrunedUtilityTree(values,
               getVariables(), sort, false, pruningMethods.KULLBACK_LEIBLER_DISTANCE, threshold, false, importatVariables, this.getNumConfigurations(),  sortStates);
       
       values = newTree;

      
   }  
   
    
   /**
    * This method sorts and prune an utility tree
    * @param sort boolean variable that indicates if tree is sorted
    * @param threshold limit value for prunning, if it's 0, it is never prunned
    */
   
   public void sortAndPruneUtility(boolean sort, double threshold, Vector<Node> importatVariables, boolean sortStates){

       BinaryProbabilityTree newTree =  BinaryUtilityManipulator.getSortedAndPrunedUtilityTree(values,
               getVariables(), sort, false, pruningMethods.EUCLIDEAN, threshold, false, importatVariables, this.getNumConfigurations(), sortStates);
       
       values = newTree;

      
   }  
   
   
   /**
    * This method sorts and prune an utility tree
    * @param sort boolean variable that indicates if tree is sorted
    * @param threshold limit value for prunning, if it's 0, it is never prunned
    */
   
   public void sortAndPruneUtility(boolean sort, double threshold, boolean sortStates){

       BinaryProbabilityTree newTree =  BinaryUtilityManipulator.getSortedAndPrunedUtilityTree(values,
               getVariables(), sort, false, pruningMethods.EUCLIDEAN, threshold, false, this.getNumConfigurations(), sortStates);
       
       values = newTree;

      
   }
    
      public void sortUtility(){

          
        long oldSize = this.getNumConfigurations();  
          
       BinaryProbabilityTree newTree =  BinaryUtilityManipulator.getSortedAndPrunedUtilityTree(values,
               getVariables(), false, false, pruningMethods.EUCLIDEAN, 0L, false, oldSize, false);
       
       values = newTree;

      
   }
   
   
   
      /**
    * This method sorts and prune an utility tree
    * @param sort boolean variable that indicates if tree is sorted
    * @param threshold limit value for prunning, if it's 0, it is never prunned
    * @param maximum value of the tree
    * @param minimum  value of the tree
    */
   
   public void sortAndPruneUtility(boolean sort, double threshold, double maximum, double minimum, boolean sortStates){

       BinaryProbabilityTree newTree =  BinaryUtilityManipulator.getSortedAndPrunedUtilityTree(values,
               getVariables(), sort, false, pruningMethods.EUCLIDEAN, threshold, false, maximum, minimum, this.getNumConfigurations(), sortStates);
       
       values = newTree;

      
   }
   
   
   
      public void sortAndPruneUtility(boolean sort, boolean normalize, pruningMethods method, double threshold, boolean Cindex, double globalMaxmin[]) {
          sortAndPruneUtility(sort, normalize, method, threshold, Cindex, false, globalMaxmin);
      }
   
   
   /**
    * This method sorts and prune an utility tree
    * @param sort boolean variable that indicates if tree is sorted
    * @param threshold limit value for prunning, if it's 0, it is never prunned
    */
   
   public void sortAndPruneUtility(boolean sort, boolean normalize, pruningMethods method, double threshold, boolean Cindex, boolean sortStates, double globalMaxmin[]){
        
       
       long oldSize = this.getNumConfigurations();
 
       BinaryProbabilityTree newTree = values;
       
      
       
       if(sort)
       newTree =  BinaryUtilityManipulator.getSortedAndPrunedUtilityTree(values, 
              getVariables(), sort, normalize, method, 0L, Cindex, oldSize, sortStates);

      newTree = BinaryUtilityManipulator.getPrunedUtilityTree(newTree, threshold, oldSize, method,globalMaxmin);
       

       values = newTree;

      
   }
   
   

      /**
    * This method sorts and prune an utility tree using the euclidean distance
    * @param threshold limit value for prunning, if it's 0, it is never prunned
    */
   
   public PotentialBPTree sortAndPruneUtility(double threshold, double globalMaxmin[]){

       PotentialBPTree pt = new PotentialBPTree(this);
       pt.sortAndPruneUtility(true, false, pruningMethods.EUCLIDEAN, threshold, false, false,globalMaxmin);
       
       pt.print();
       
       return pt;      
   }
      
   
   
      /**
    * This method sorts and prune an utility tree using the euclidean distance
    * @param threshold limit value for prunning, if it's 0, it is never prunned
     * @param sortStates sorts the states when selecting the splitting point
    */
   
   public PotentialBPTree sortAndPruneUtility(double threshold, boolean sortStates, double globalMaxmin[]){

       PotentialBPTree pt = new PotentialBPTree(this);
       pt.sortAndPruneUtility(true, false, pruningMethods.EUCLIDEAN, threshold, false, sortStates,globalMaxmin);
       
       pt.print();
       
       return pt;      
   }
   

  /**
   * Gets the number of nodes of the potential.
   * @return the number of values (size) of the potential.
   */ 
    @Override
   public long getNumberOfNodes() {
      return values.getNumberOfNodes();
   }

     /**
   * Gets the number of leaves of the potential.
   * @return the number of values (size) of the potential.
   */
   public long getNumberOfLeaves() {
      return values.getNumberOfLeaves();
   }

   /**
    * Method for getting the values
    */
   public BinaryProbabilityTree getTree(){
      return values;
   }

   /**
    * Sets a new BinaryProbabilityTree in this Potential
    * @param newTree the new tree
    */
   public void setTree(BinaryProbabilityTree newTree){
       values=newTree;
   }

   private static final String argBnetFile = "-bnetFile";

   public static void main(String args[]) throws ParseException, IOException {
      String bnetFileString = null;

      CmdLineArguments params = new CmdLineArguments();
      try {
         params.addArgument(argBnetFile, argumentType.s, "",
                 "The filename of the Bnet (.elv format). No default value, must be provided.");
         params.parseArguments(args);
         params.print();
         bnetFileString = params.getString(argBnetFile);
      } catch (CmdLineArgumentsException ex) {
         params.printHelp();
         System.exit(1);
      }
      if (bnetFileString.equalsIgnoreCase("")) {
         System.out.println(argBnetFile + " argument not found, you must specify one!!!");
         params.printHelp();
         System.exit(1);
      }
      Network b;
      Potential pot;
      PotentialBPTree potbptree;

      b = Network.read(bnetFileString);
      Vector relList = b.getRelationList();
      for (int i = 0; i < relList.size(); i++) {
         pot = ((Relation) (relList.elementAt(i))).getValues();
         potbptree = new PotentialBPTree(pot);
         potbptree.print();
      }
   }
   


   /**
     * Marginalizes a <code>PotentialTree</code> to a list of variables.
     * It is equivalent to remove (add) the other variables.
     * @param vars a <code>Vector</code> of <code>FiniteStates</code> variables.
     * @return a new <code>PotentialTree</code> with the marginal.
     * @see addVariable(Vector vars)
     */

    @Override
    public Potential marginalizePotential(Vector vars) {

        Vector v;
        int i, j;
        boolean found;
        FiniteStates var1, var2;
        PotentialBPTree pot;
        Vector variables = getVariables();

        v = new Vector(); // List of variables to remove
        // (those not in vars).

        for (i=0 ; i<variables.size() ; i++) {
            var1 = (FiniteStates)variables.elementAt(i);
            found = false;

            for (j=0 ; j<vars.size(); j++) {
                var2 = (FiniteStates)vars.elementAt(j);
                if (var1 == var2) {
                    found = true;
                    break;
                }
            }

            if (!found)
            {v.addElement(var1);}
        }

        //eliminate each variable in v
        
      
        pot = this;
        for(i=0; i<v.size(); i++) {
             pot = (PotentialBPTree) pot.addVariable((Node)(v.get(i)));
        }
        
       if(head !=null && tail!=null){
            pot.setHead(Potential.getMargHead(this, v));
            pot.setTail(Potential.getMargTail(this, v));
        }

        return pot;
    }
    
    
        /**
     * Removes a list of variables by applying marginalization by maximum.
     * @param vars a <code>Vector</code> of <code>FiniteStates</code> variables.
     * @return a new <code>PotentialBPTree</code> with the marginal.
     */
    
    @Override
    public PotentialBPTree maxMarginalizePotential(Vector vars) {
        
        Vector aux;
        FiniteStates var1, var2;
        int i, j;
        boolean found;
        PotentialBPTree pot;
        BinaryProbabilityTree tree;
        Vector variables = getVariables();




        aux = new Vector(); // New list of variables.
        for (i=0 ; i<variables.size() ; i++) {
            var1 = (FiniteStates)variables.elementAt(i);
            found = false;
            
            for (j=0 ; j<vars.size() ; j++) {
                var2 = (FiniteStates)vars.elementAt(j);
                if (var1 == var2) {
                    found = true;
                    break;
                }
            }
            
            if (!found)
                aux.addElement(var1);
        }
        
        
        
        tree = values;
        
        for (i=0 ; i<aux.size() ; i++) {
            var1 = (FiniteStates)aux.elementAt(i);
            tree = tree.maximizeOverVariable(var1);

        }

        pot = new PotentialBPTree(vars); // The new tree.

        pot.setTree(tree);
        
        
        return pot;
    }
    
    /**
     * This method divides two potentials.
     * For the exception 0/0, the method computes the result as 0.
     * The exception ?/0: the method aborts with a message in the standar output.
     * @param p the <code>PotentialBPTree</code> to divide with this.
     * @return a new <code>PotentialBPTree</code> with the result of
     * dividing this potential by <code>p</code>.
     */

    @Override
    public Potential divide(Potential p) {

        Vector v, v1, v2;
        FiniteStates aux;
        int i, nv;
        PotentialBPTree pot;
        double x;
        BinaryProbabilityTree tree, tree1, tree2;

        v1 = getVariables();   // Variables of this potential.
        v2 = p.getVariables(); // Variables of the argument.
        v = new Vector(); // Variables of the new potential.

        for (i=0 ; i<v1.size() ; i++) {
            aux = (FiniteStates)v1.elementAt(i);
            v.addElement(aux);
        }

        for (i=0 ; i<v2.size() ; i++) {
            aux = (FiniteStates)v2.elementAt(i);
            if (aux.indexOf(v1) == -1)
                v.addElement(aux);
        }

        // The new Potential.
        pot = new PotentialBPTree(v);

        tree1 = getTree();                          // Tree of this potential.
        tree2 = ((PotentialBPTree)p).getTree();       // Tree of the argument.

        tree = BinaryProbabilityTree.divide(tree1,tree2); // The new tree.

        pot.setTree(tree);

        return pot;
    }


    /**
     * This method calcules the size of the tree (number of leaves).
     * @return size of the tree
     */
    @Override
    public long getSize() {
        return values.getSize();
    }
    
    
    
    /**
     * Computes a measure of the influence of each state of each variable in
     * the utility.
     * @return 
     */
    public double averageVariance() {
    
        double avg = 0;
        Vector vars = values.getVariables();
        Vector variances = new Vector();
        
        for(int i=0; i<vars.size(); i++) {
            FiniteStates v = (FiniteStates) vars.get(i);
            Vector meanLeaves = new Vector();
            for(int k=0; k<v.getNumStates(); k++) {
                BinaryProbabilityTree restrTree = values.copy().restrict(v, k);
                meanLeaves.add(VectorManipulator.mean(restrTree.getLeaves()));
            
            }
            
            variances.add(VectorManipulator.variance(meanLeaves));
            
        
        }
        
        
        return VectorManipulator.mean(variances);
        
    
    }
    
    
    
        /**
     * Copies this potential.
     * @return a copy of this <code>PotentialTree</code>.
     */
    
    @Override
    public Potential copy() {
        
        PotentialBPTree pot;
        
        
        pot = new PotentialBPTree(variables);
        pot.values = values.copy();

        
        return pot;
    }
    
        /**
     * Sets the value for a configuration.
     * @param conf a <code>Configuration</code>.
     * @param x a <code>double</code>, the new value for <code>conf</code>.
     */
    
    public void setValue(Configuration conf, double x) {
    
        PotentialTable t = new PotentialTable(this);
        t.setValue(conf, x);
        
        PotentialBPTree bpt = new PotentialBPTree(t);
        this.values = bpt.values.copy();
        
    
    }
    
    
        /**
     * Method to repair this distribution to be a probability distribution
     * @param <code>NodeList</code> nodes conditioning the distribution
     * @return <code>PotentialBPTree</code> Potential already normalized
     */
    
    @Override
    public void repair(NodeList nodes){
        Configuration conf;
        double size=nodes.getSize();
        int valuesToConsider;
        double sum;
        double lost;
        double add;
        double i;
        
        // First at all, do a configuration with all variables
        // in nodes




        conf=new Configuration(nodes);
        
        // Calculate the number of possible values for this configuration
        
        for(i=0; i < size; i++){
            // Now, go on the possible values for this configuration
            
            sum=sumConsistent(conf);
            
            // We need to change the values for this values to be a distribution
            // Get the numbers different to 0
            
            valuesToConsider=nonCeroValues(conf);
            
            // Compute the lost probability (due to 0s)
            
            lost=1.0-sum;
            
            if (lost > 0.0) {
                // This probability will be assigned to the rest of values
                
                add=lost/valuesToConsider;
                
                // Finally this value must be added to non cero values consistent
                // with the given configuration
                
                addValue(conf,add);
            }
            
            // Go on with the next configuration
            
            conf.nextConfiguration();
        }
    }
    
    
    
        /**
     * Gets the sum of the values consistent with a given configuration
     * of variables: consider only that values matching the configuration
     * Equivalence: remove adding variables not in conf
     */
    
    
    public double sumConsistent(Configuration conf){
        Configuration auxConf,total;
        int i,nv;
        double sum;
        
        // Create a new configuration with the variables not in conf
        
        auxConf=new Configuration(conf,variables,0);
        
        // Make a configuration for the whole set of variables
        
        total=new Configuration(variables);
        
        // Initialize sum
        
        sum=0.0;
        
        // Get the number of possible values for a configuration
        
        nv=auxConf.possibleValues();
        
        for(i=0; i < nv; i++){
            
            // Give total the values in conf and auxConf
            
            total.setValues(conf,auxConf);
            
            // Get the value and add it
            
            sum+=getValue(total);
            auxConf.nextConfiguration();
        }
        
        // return sum
        
        return sum;
    }
    
    
    
    
   /**
     * Method to get the values (non cero) consistent with a
     * given configuration
     */
    
    public int nonCeroValues(Configuration conf){
        Configuration auxConf,total;
        int i,nv;
        int count=0;
        
        // Create a new configuration with the variables not in conf
        
        auxConf=new Configuration(conf,variables,0);
        
        // Make a configuration for the whole set of variables
        
        total=new Configuration(variables);
        
        // Get the number of possible values for a configuration
        
        nv=auxConf.possibleValues();
        
        // Go on possible values and consider those different to 0
        //
        for(i=0; i < nv; i++){
            
            // Give total the values in conf and auxConf
            
            total.setValues(conf,auxConf);
            
            // Get the value and add it
            
            if (getValue(total) != 0.0)
                count++;
            
            auxConf.nextConfiguration();
        }
        
        // return count
        
        return count;
    }
    
    /**
     * Method to add a value to non cero values consistent with the
     * given configuration
     */
    
    public void addValue(Configuration conf,double toAdd){
        Configuration auxConf,total;
        int i,nv;
        int count=0;
        double value;
        
        // Create a new configuration with the variables not in conf
        
        auxConf=new Configuration(conf,variables,0);
        
        // Make a configuration for the whole set of variables
        
        total=new Configuration(variables);
        
        // Get the number of possible values for a configuration
        
        nv=auxConf.possibleValues();
        
        // Go on possible values and consider those different to 0
        
        for(i=0; i < nv; i++){
            
            // Give total the values in conf and auxConf
            
            total.setValues(conf,auxConf);
            
            // Get the value and add it
            
            if ((value=getValue(total)) != 0.0){
                value+=toAdd;
                setValue(total,value);
            }
            
            auxConf.nextConfiguration();
        }
        
    }


    
    public long getNumPrunedConf(Node n) {
        return this.values.getNumPrunedConf(getNumConfigurations(), n);
    }
    
    
    /**
     * Determines if all values are equal or greater than zero
     */
    public boolean isNonNegative() {
        return values.isNonNegative();
    }

    
public boolean replaceVariable(FiniteStates oldVar, FiniteStates newVar) {
    
    if(!variables.contains(oldVar))
        return false;
    
    Vector vars = new Vector();
    for(int i=0; i<variables.size(); i++) {
        if(variables.elementAt(i).equals(oldVar))
            vars.add(newVar);
        else
            vars.add(variables.elementAt(i));
    }
    
    variables = vars;
    
    values.replaceVariable(oldVar, newVar);
    
    return true;
}    

    public void replaceLowValues(double threshold, boolean absValue) {
        values.replaceLowValues(threshold, absValue);   
    }


    
    

}
