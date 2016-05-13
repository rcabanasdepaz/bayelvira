/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package elvira.inference.symbolic.ids;

import elvira.Bnet;
import elvira.Evidence;
import elvira.IDiagram;
import elvira.Relation;
import elvira.inference.elimination.ids.IDVEWBPTAndConstraints;
import elvira.inference.elimination.ids.IDVEWPTAndConstraints;
import elvira.inference.elimination.ids.IDVEWithPotentialBPTree;
import elvira.inference.elimination.ids.IDVEWithPotentialBPTree.pruningMethods;
import elvira.inference.elimination.ids.IDVEWithPotentialTree;
import elvira.parser.ParseException;
import elvira.potential.CanonicalPotential;
import elvira.potential.Potential;
import elvira.potential.PotentialTable;
import elvira.potential.PotentialTree;
import elvira.potential.ProbabilityTree;
import elvira.potential.binaryprobabilitytree.BinaryProbabilityTree;
import elvira.potential.binaryprobabilitytree.PotentialBPTree;
import elvira.tools.idiagram.EUComparator;
import elvira.tools.idiagram.pairtable.CombPairTable;
import java.io.IOException;

/**
 *
 * @author Rafael Cabañas rcabanas@decsai.ugr.es
 */
public class IDSPIWithPotentialTree extends IDSymbolicProbInf {

   /**
     * A very low limit for prunning, allowing almost exact calculations.
     */
    public static final double limitForPruning =  0.00000000001;

    /*
     * Limit to produce the identification of leaves in the tree This value can
     * be changed with setThresholdForPruningProb
     */
    protected double thresholdForPruningProb = 0.0;
    /**
     * Limit to produce the identification of leaves in the tree This value can
     * be changed with setThresholdForPruningUtility
     */
    private double thresholdForPruningUtility = 0.0;
    /**
     * Data members to store the max and min values for the potentials
     */
    //protected double maximum, minimum;

    /**
     * Flag that indicates if trees are pruned during 
     * the initial transformation.
     * The default value is true
     */
    protected boolean pruneInitial = true;    
 

    /**
     * Constructs a new propagation for a given Bayesian network and some
     * evidence.
     *
     * <code>Bnet</code>.
     *
     * @param e the evidence.
     */
    public IDSPIWithPotentialTree(Bnet b, Evidence e) {
        super(b, e);

        if (generateDebugInfo) {
            System.out.println("IDSPIWithPotentialTree:  class Constructor(Bnet,Evidence) ----- BEGIN");
        }

        BinaryProbabilityTree.setImprovedOps(true); 
        boolean evaluable = initialConditions();
        if (evaluable == false) {
            System.out.println("Non evaluable diagram......");
            System.exit(0);
        }


        if (generateDebugInfo) {
            System.out.println("IDSPIWithPotentialTree:  class Constructor ----- END");
        }


    }

    /**
     * Constructs a new propagation for a given Bayesian network
     *
     * @param b a
     * <code>Bnet</code>.
     */
    public IDSPIWithPotentialTree(Bnet b) {
        super(b, new Evidence());

        
        

        if (generateDebugInfo) {
            System.out.println("IDSPIWithPotentialTree:  class Constructor(Bnet) ----- BEGIN");
        }

        BinaryProbabilityTree.setImprovedOps(true); 
        evaluable = IDSymbolicProbInf.EvaluableStates.NOCHECKED;


        if (generateDebugInfo) {
            System.out.println("IDSPIWithPotentialTree:  class Constructor(Bnet) ----- END");
        }


    }
    

    
    

     /**
     * Transforms one of the original relations into another one whose values
     * are of class
     * <code>PotentialTree</code>. @ param r the
     * <code>Relation</code> to be transformed.
     */
    @Override
    public Relation transformInitialRelation(Relation r) {
        PotentialTree potTree;
        Relation rNew = r;

        if (generateDebugInfo) {
            System.out.println("IDSPIWithPotentialTree:  transformInitialRelation ----- BEGIN");
        }

        // Transform the relation, but only for non constraints relations
        if (r.getKind() != Relation.CONSTRAINT) {
            rNew = new Relation();
            rNew.setVariables(r.getVariables().copy());
            rNew.setKind(r.getKind());
            if (r.getValues().getClassName().equals("PotentialTable")) {
                potTree = ((PotentialTable) r.getValues()).toTree();
            } else if (r.getValues().getClassName().equals("CanonicalPotential")) {
                potTree = ((CanonicalPotential) r.getValues()).toTree();
            } else {
                potTree = (PotentialTree) (r.getValues());
            }

              //Shows the information
            if (generateDebugInfo) {
                System.out.println("\t - Potencial a transformar: " + potTree);
            }

            // Store the new potential
            rNew.setValues(potTree);
                  
            // Now, prune the tree. If it is a utility, sort its
            // variables. Initially only exact prune operations
            if (pruneInitial) {
                if (r.getKind() == Relation.UTILITY) {
                    potTree.updateSize();
                    // First at all look for minimum and maximum
                    setMaximum(potTree.getTree().maximumValue());
                    setMinimum(potTree.getTree().minimumValue());
                    Potential tinit = new PotentialTable(potTree.copy());
                    potTree = potTree.sortUtilityAndPrune(minimum, maximum, thresholdForPruningUtility);

                    if (generateStatistics) {
                        double error = EUComparator.compareUtilities(tinit, potTree);
                        this.statistics.setInitalUtilityError(error);
                        this.statistics.setInitialUtilitySize(potTree.getSize());
                        this.statistics.setInitialUtilityNodes(potTree.getNumberOfNodes());
                    }
                } else {
                    //potTree = potTree.sortAndBound(thresholdForPruningProb);
                    potTree = potTree.sortAndBound(0L);
                    potTree.limitBound(thresholdForPruningProb);
                }
            }

    
            if (generateDebugInfo) {
                System.out.println("\t - Potencial transformado: " + potTree);
            }        

            // Store the final potential
            rNew.setValues(potTree);
            
         
        }

        if (generateDebugInfo) {

            System.out.println("IDSPIWithPotentialTree:  transformInitialRelation ----- END");
        }

        // Return the new relation
        return rNew;
    }

    public double getThresholdForPruningProb() {
        return thresholdForPruningProb;
    }

    public void setThresholdForPruningProb(double thresholdForPruningProb) {
        this.thresholdForPruningProb = thresholdForPruningProb;
    }

    public double getThresholdForPruningUtility() {
        return thresholdForPruningUtility;
    }

    public void setThresholdForPruningUtility(double thresholdForPruningUtility) {
        this.thresholdForPruningUtility = thresholdForPruningUtility;
    }

    public boolean isPruneInitial() {
        return pruneInitial;
    }

    public void setPruneInitial(boolean pruneInitial) {
        this.pruneInitial = pruneInitial;
    }
    
    public void setImprovedOps(boolean b) {
        ProbabilityTree.setImprovedOps(b);
    }  
    
    

    public static void main(String[] args) throws ParseException, IOException {
        // TODO code application logic here
            String idFile;
            idFile = "ids/lazyspi/test3.elv";
            idFile = "ids/spi/motivation_not_binary_trees4.elv";
            
          //  idFile = "ids/lazyspi/test3.elv";
        
            IDiagram id = new IDiagram(idFile);
            IDSPIWithPotentialTree spi = new IDSPIWithPotentialTree(id);
            spi.generateStatistics=true;
            spi.generateDebugInfo=true;
            
            
            spi.setSimetric(true);
            spi.setSimetric(false);
            spi.setHeuristic(CombPairTable.MIN_WEIGHT_ONLY_PAIR);
           // spi.setHeuristic(CombPairTable.MIN_TREESIZE_ONLY_PAIR);
            
            spi.setThresholdForPruningProb(0.05);
            spi.setThresholdForPruningUtility(0.05);
            
            spi.propagate();


            System.out.println("Results:");
          //  spi.printResults();

                 long mult=spi.getStatistics().getNumMultiplications();
            long div = spi.getStatistics().getNumDivisions();
            long sums = spi.getStatistics().getNumSums();
            long margs = spi.getStatistics().getNumSumMarg();
            long max = spi.getStatistics().getNumMaxMarg();
            
            spi.printResults();
            
            System.out.println(mult+" multiplications\n"+div+" divisions\n"+sums+" sums\n"+margs+"  sumMargs\n"+max+"  max\n"); 
            System.out.println(mult+div+sums+margs+max+"  ops\n"); 

        
    }


}
