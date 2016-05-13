/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package elvira.potential.binaryprobabilitytree;

import elvira.Configuration;
import elvira.FiniteStates;
import elvira.Node;
import elvira.NodeList;
import elvira.SetVectorOperations;
import elvira.inference.elimination.ids.IDVEWithPotentialBPTree;
import elvira.parser.ParseException;
import elvira.potential.IPotentialInterval;
import elvira.potential.Potential;
import elvira.potential.PotentialIntervalTable;
import elvira.potential.PotentialTable;
import elvira.tools.idiagram.BinaryUtilityManipulator;
import elvira.tools.intervals.GenericIntervalProperties;
import java.io.IOException;
import java.util.Vector;

/**
 *
 * @author Rafael Cabañas rcabanas@decsai.ugr.es
 */
public class PotentialIBPTWT extends PotentialBPTree implements IPotentialInterval{

    public PotentialIBPTWT() {
    }
    
    public PotentialIBPTWT(Vector variables) {
        this.variables=variables;
    }
    

    /**
     * Creates an interval potential represented as a tree with
     * transparent variables from an interval table
     * @param itab 
     */
    public PotentialIBPTWT(PotentialIntervalTable itab, String transName) {
         
       PotentialTable tab = itab.getTableWithTransparentVariable(transName);
       FiniteStates T = (FiniteStates) tab.getListTransparents().elementAt(0);
       T.setTransparency(FiniteStates.TRANSPARENT);
        
        variables = tab.getVariables();
        if(!variables.contains(T))
            variables.add(T);
        
        
        Vector listT= this.getListTransparents();
        if(listT.size() != 1){
            System.out.println("ERROR in IPotentialBPTwithTransparent(PotentialIntervalTable itab, String transName)");
            System.out.println("\tInvalid number of transparent variables"+listT.size());
        }
        
        values = BinaryProbabilityTree.getTreeFromPotential(tab); 
    }
    
    
    /**
     * Creates an interval potential represented as a tree with
     * transparent variables from a precise table with one of the
     * variables set as transparent
     * @param itab 
     */
    public PotentialIBPTWT(PotentialTable tab, FiniteStates T) {
       
        T.setTransparency(FiniteStates.TRANSPARENT);
        
        variables = tab.getVariables();
        if(!variables.contains(T))
            variables.add(T);
        
        
        Vector listT= this.getListTransparents();
        if(listT.size() != 1){
            System.out.println("ERROR in PotentialBPTwithTransparent(PotentialTable tab, FiniteStates T)");
            System.out.println("\tInvalid number of transparent variables"+listT.size());
        }
        
        values = BinaryProbabilityTree.getTreeFromPotential(tab);
    }
    
    
    /**
     * Transforms this potential into a PotentialIntervalTable
     */
    public PotentialIntervalTable getIntervalTable() {

        Vector T = getListTransparents();
        if (T.size() != 1) {
            System.out.println("ERROR: creating interval table from a BPT with a wrong number of transparent variables (" + T.size() + ")");
            System.exit(1);
        }
        Vector variables = getListNonTransparents();
        PotentialIntervalTable itab = new PotentialIntervalTable(variables);
        Configuration conf = new Configuration(getVariables());

        for (int i = 0; i < conf.possibleValues(); i++) {
            if (conf.getValue((FiniteStates) T.elementAt(0)) == 0) {
                itab.setMinValue(conf, getValue(conf));
            } else {
                itab.setMaxValue(conf, getValue(conf));
            }
            conf.nextConfiguration();
        }
        return itab;
    }
    
    
  

    @Override
    public void setMaxValue(Configuration conf, double value) {
        
        Configuration conf1 = new Configuration(conf, getListTransparents());
        Configuration conf2 = new Configuration(getListTransparents());
        Configuration conf12 = new Configuration(conf1, conf2, getNodeList());
        conf12.putValue((FiniteStates) getListTransparents().elementAt(0), 1);
        this.setValue(conf12, value);
    }

    @Override
    public void setMinValue(Configuration conf, double value) {
        Configuration conf1 = new Configuration(conf, getListTransparents());
        Configuration conf2 = new Configuration(getListTransparents());
        Configuration conf12 = new Configuration(conf1, conf2, getNodeList());
        conf12.putValue((FiniteStates) getListTransparents().elementAt(0), 0);
        this.setValue(conf12, value);
    }
    @Override
    public double getMaxValue(Configuration conf) {
        Configuration conf1 = new Configuration(conf, getListTransparents());
        Configuration conf2 = new Configuration(getListTransparents());
        Configuration conf12 = new Configuration(conf1, conf2, getNodeList());
        conf12.putValue((FiniteStates) getListTransparents().elementAt(0), 1);
        return this.getValue(conf12);
    }

    @Override
    public double getMinValue(Configuration conf) {
        Configuration conf1 = new Configuration(conf, getListTransparents());
        Configuration conf2 = new Configuration(getListTransparents());
        Configuration conf12 = new Configuration(conf1, conf2, getNodeList());
        conf12.putValue((FiniteStates) getListTransparents().elementAt(0), 0);
        return this.getValue(conf12);    
    }

    @Override
    public boolean isProper() {
        return GenericIntervalProperties.isProper(this);
    }

    @Override
    public boolean isReachable() {
        return GenericIntervalProperties.isReachable(this);
    }

    @Override
    public void makeReachable() {
        System.out.print("makeReachable ~~ "+this.getNumberOfNodes());
        GenericIntervalProperties.makeReachable(this);
        System.out.println(" --> "+this.getNumberOfNodes());
    }

    @Override
    public double getHigherMax() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getLowerMin() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isIncluded(IPotentialInterval ipot) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isIncluded(IPotentialInterval ipot, double error) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

      /**
    * This method sorts and prune an utility tree
    * @param sort boolean variable that indicates if tree is sorted
    * @param threshold limit value for prunning, if it's 0, it is never prunned
    */
   
    @Override
   public void sortAndPruneUtility(boolean sort,   double threshold, boolean sortStates){
        
       
       long oldSize = this.getNumConfigurations();
 
   
       if(sort)
        super.sortAndPruneUtility(true, 0.0, sortStates);
       

       //Prune the tree
       setTree(BinaryUtilityManipulator.getPrunedIntervalUtilityTree(getTree(), threshold, oldSize));
 
   }
   
   public void limitBound(double limit) {
        long maxSize;
        double globalSum;
        maxSize = (long) FiniteStates.getSize(getVariables());
        globalSum = values.sum(maxSize);
        
        values.insertVariableInLeaves((FiniteStates) getListTransparents().elementAt(0));
        values.pruneIntervals(limit, maxSize, globalSum, false, false);
    }
    
      /**
    * Combines this potential with the argument. The argument <code>pot</code>
    * can be any class of <code>Potential</code> .
    * @returns a new <code>PotentialBPTree</code> consisting of the combination
    * of <code>pot</code> and this <code>PotentialBPTree</code>.
    */
   public Potential combine(Potential pot) {
        
       boolean changeOrder = false;
       if(!pot.isNonNegative()) {
           if(this.isNonNegative()){
               changeOrder=true;
           }else {
                System.out.println("ERROR: both potentials contain negative values");
                System.exit(1);
           }    
        }
       

       
      PotentialIBPTWT newPot = new PotentialIBPTWT();

             if (changeOrder) {
           newPot.setVariables(SetVectorOperations.union(getListNonTransparents(),
                   pot.getVariables()));
           newPot.values = (((PotentialIBPTWT) pot).values).combine(values, true, true);
       } else {
           newPot.setVariables(SetVectorOperations.union(getVariables(),
                   pot.getListNonTransparents()));
           newPot.values = values.combine(((PotentialIBPTWT) pot).values, true, true);
       }

     if(head != null && pot.getHead() != null) {
        newPot.setHead(Potential.getCombinationHead(this, pot));
        newPot.setTail(Potential.getCombinationTail(this, pot));
    }

      return newPot;
   }
   
   
    @Override
    public Potential divide(Potential p) {

        Vector v, v1, v2;
        FiniteStates aux;
        int i, nv;
        PotentialIBPTWT pot;
        double x;
        BinaryProbabilityTree tree, tree1, tree2;

        v1 = getVariables();   // Variables of this potential.
        v2 = p.getListNonTransparents(); // Variables of the argument.
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
        pot = new PotentialIBPTWT();
        pot.setVariables(v);
        
        tree1 = getTree();                          // Tree of this potential.
        tree2 = ((PotentialBPTree)p).getTree();       // Tree of the argument.

        
        if(tree2.isNonNegative()) {
            tree = tree1.divide(tree2, true, true);
            pot.setTree(tree);
        } else {
            System.out.println("ERROR: denominator must be non-negative");
            System.exit(1);
        }

        return pot;
    }

    
        /**
     * Removes a list of variables by applying marginalization by maximum.
     * @param vars a <code>Vector</code> of <code>FiniteStates</code> variables.
     * @return a new <code>PotentialIBPTWT</code> with the marginal.
     */
    
    @Override
    public PotentialIBPTWT maxMarginalizePotential(Vector vars) {
        
        Vector aux;
        FiniteStates var1, var2;
        int i, j;
        boolean found;
        PotentialIBPTWT pot;
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

        pot = new PotentialIBPTWT(vars); // The new tree.

        pot.setTree(tree);
        
        return pot;
    }
   
    
   
       /**
    * Removes the variable var from this PotentialIBPTWT summing over all
    * its values.
    * @param var a <code>FiniteStates</code> variable.
    * @return a new <code>PotentialIBPTWT</code> with the result of the 
    * operation.
    */
   public Potential addVariable(Node var) {
      PotentialIBPTWT newPot = new PotentialIBPTWT();
      Vector potVariables;

      potVariables = (Vector) getVariables().clone();
      potVariables.removeElement(var);
      newPot.setVariables(potVariables);
      newPot.values = values.addVariable((FiniteStates) var, (int)values.getSize());


      
      
      return newPot;
   }
    
    
     @Override
    public Potential addition(Potential p) {
        Vector v, v1, v2;
        FiniteStates aux;
        int i, nv;
        PotentialIBPTWT pot;
        double x;
        BinaryProbabilityTree tree, tree1, tree2;
        
        if (p.getClass().getName().equals("elvira.potential.binaryprobabilitytree.PotentialBPTree")
                || p.getClass().getName().equals("elvira.potential.binaryprobabilitytree.PotentialIBPTWT")) {
            
            //Replaces the transparent variable in the second potential
            FiniteStates T1 = (FiniteStates) ((Potential)this).getListTransparents().elementAt(0);
            FiniteStates T2 = (FiniteStates) p.getListTransparents().elementAt(0);
            
            ((PotentialBPTree)p).replaceVariable(T2, T1);
            
            
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
            pot = new PotentialIBPTWT();
            pot.setVariables(v);
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
     * Copies this potential.
     * @return a copy of this <code>PotentialTree</code>.
     */
    
    @Override
    public Potential copy() {
        
        PotentialIBPTWT pot;
        
        
        pot = new PotentialIBPTWT(variables);
        pot.values = values.copy();

        
        return pot;
    }
    

        /**
 * Normalizes the values of this potential over a specified variable.
 * If the specified variable is not in the potential variables list,
 * does not do anything.
 * @param v a <code>FiniteStates</code> variable.
 */

public void normalizeOver (FiniteStates v) {
    
   
    FiniteStates T = (FiniteStates) getListTransparents().elementAt(0);
    long numConf = getNumConfigurations();
            
    BinaryProbabilityTree sizes = getIntervalSizes().getTree();
    
     
   
    BinaryProbabilityTree kmin = values.restrict(T,0).addVariable(v, (int) (numConf/2));
    BinaryProbabilityTree kmax = values.restrict(T,1).addVariable(v, (int) (numConf/2));
    
    kmin = kmin.sum(sizes);
    kmax = kmax.subtract(sizes);

    BinaryProbabilityTree k = new BinaryProbabilityTree();
    k = BinaryProbabilityTree.createTerminalTree(T,0, -1, 1, -1);
    k.getLeftChild().setInfo(kmax);
    k.getRightChild().setInfo(kmin);
    
    setTree(values.divide(k));
    
    
}

public PotentialBPTree getIntervalSizes() {
    PotentialBPTree sizes = new PotentialBPTree(getListNonTransparents());

    FiniteStates T = (FiniteStates) getListTransparents().elementAt(0);
    
    Configuration conf = new Configuration(getListTransparents());
    PotentialBPTree lowTree = (PotentialBPTree) restrictVariable(conf);
    conf.nextConfiguration();
    PotentialBPTree upTree = (PotentialBPTree) restrictVariable(conf);
    
    sizes.setTree(upTree.getTree().subtract(lowTree.getTree()));
    
    
    return sizes;
}
   
   public boolean isNonNegative() {
       return values.isNonNegative();
   }
    
         /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, ParseException {
        // TODO code application logic here

        FiniteStates A = new FiniteStates("A", 3);
        FiniteStates B = new FiniteStates("B", 2);

        FiniteStates T1 = new FiniteStates("T1", 2);
        T1.setTransparency(FiniteStates.TRANSPARENT);
        FiniteStates T2 = new FiniteStates("T2", 2);
        T2.setTransparency(FiniteStates.TRANSPARENT);
        FiniteStates T3 = new FiniteStates("T3", 2);
        T3.setTransparency(FiniteStates.TRANSPARENT);
        
        
        
        NodeList nodes1 = new NodeList();
        nodes1.insertNode(B);
        nodes1.insertNode(A);
        nodes1.insertNode(T1);

        PotentialTable tab_util = new PotentialTable(nodes1);
        double vUtil[] = {0, 0, -1, -1, 90, 95, 2, 20, 5, 30, 100, 105};   ///all pruning scenarios
        tab_util.setValues(vUtil);
        PotentialIBPTWT ibt_util = new PotentialIBPTWT(tab_util, T1);
        
        ibt_util.print();        
        ibt_util.sortAndPruneUtility(true, 0.5, false);
        ibt_util.print();        
     //   tab_util.print();
        ibt_util.getIntervalTable().print();
        

        NodeList nodes3 = new NodeList();
        nodes3.insertNode(B);
        nodes3.insertNode(A);
        nodes3.insertNode(T3);

        PotentialTable tab_util3 = new PotentialTable(nodes3);
        double vUtil3[] = {0, 0, -1, -1, 10, 95, 2, 20, 5, 30, 100, 105};   ///all pruning scenarios
        tab_util3.setValues(vUtil3);
        PotentialIBPTWT ibt_util3 = new PotentialIBPTWT(tab_util3, T3);
        
        ibt_util3.print();        
        ibt_util3.sortAndPruneUtility(true, 0.05, false);
        ibt_util3.print();            
        
        
        
        //Probabilities
        Vector nodes2 = new Vector();
        nodes2.add(A);
        nodes2.add(B);

        Vector tail2 = new Vector();
        tail2.add(nodes2.elementAt(1));

        double minProb2[] = {0.3, 0.4, 0.0,
            0.3, 0.1, 0.1};

        double maxProb2[] = {0.7, 0.6, 0.0,
            0.6, 0.4, 0.3};

        PotentialIntervalTable itab2 = new PotentialIntervalTable(nodes2);

        itab2.setHead((Node) nodes2.elementAt(0));
        itab2.setTail(tail2);
        itab2.setMaxValues(maxProb2);
        itab2.setMinValues(minProb2);

        itab2.print();
        itab2.makeReachable();
        itab2.print();
        
        PotentialIBPTWT ibt_prob = new PotentialIBPTWT(itab2, "T2");
        ibt_prob.sort();
        ibt_prob.sortAndBound(0.0);
        ibt_prob.print();
        ibt_prob.values.insertVariableInLeaves((FiniteStates) ibt_prob.getListTransparents().elementAt(0));
        ibt_prob.limitBound(0.00);
        
   
        
        
        ibt_prob.print();
        
        
        System.out.println(ibt_util3.values.isNonNegative());
        
        
      /*  ibt_util.combine(ibt_prob).print();
        ibt_prob.combine(ibt_util).print();
              
              */
        
        ibt_util3.divide(ibt_prob).print();
        //        ibt_prob.divide(ibt_util).print();

    //    ibt_util.addition(ibt_util3).print();
  //     ibt_util3.replaceVariable(T3, T1);
       PotentialIBPTWT addtree = (PotentialIBPTWT)(ibt_util).addition(ibt_util3);
  
       
   //    ibt_util3.print();
       //     addtree.renameVariable("A","X");
        
        addtree.print();
        
    //    addtree.getIntervalSizes().print();
        
        itab2.print();
        ibt_prob.normalizeOver(B);
        
        
        itab2.normalizeOver(B);
        ibt_prob.getIntervalTable().print();
        itab2.print();
        
        
    } 
    
    
    

}
