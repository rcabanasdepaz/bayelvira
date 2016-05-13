/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package elvira.inference.symbolic.ids;

import elvira.Bnet;
import elvira.Evidence;
import elvira.IDiagram;
import elvira.Relation;
import elvira.inference.elimination.ids.IDVEWithPotentialBPTree;
import elvira.inference.elimination.ids.IDVEWithPotentialBPTree.pruningMethods;
import elvira.parser.ParseException;
import elvira.potential.Potential;
import elvira.potential.PotentialTable;
import elvira.potential.binaryprobabilitytree.BinaryProbabilityTree;
import elvira.potential.binaryprobabilitytree.PotentialBPTree;
import elvira.tools.idiagram.EUComparator;
import elvira.tools.idiagram.pairtable.CombPairTable;
import java.io.IOException;

/**
 *
 * @author Rafael Cabañas rcabanas@decsai.ugr.es
 */
public class IDSPIWithPotentialBPTree extends IDSymbolicProbInf {

    /**
     * Indicates the pruning method used for probability trees. By default, it
     * is not used any method
     */
    private IDVEWithPotentialBPTree.pruningMethods pruningProbMethod = IDVEWithPotentialBPTree.pruningMethods.KULLBACK_LEIBLER_DISTANCE;
    /**
     * @param args the command line arguments
     */
    /**
     * Indicates the pruning method used for utility trees. By default, it is
     * not used any method
     */
    private IDVEWithPotentialBPTree.pruningMethods pruningUtilityMethod = IDVEWithPotentialBPTree.pruningMethods.EUCLIDEAN;
    /**
     * Limit to produce the identification of leaves in the tree This value can
     * be changed with setThresholdForPruning
     */
    private double thresholdForPruningProb = 0.0;
    /**
     * Limit to produce the identification of leaves in the tree This value can
     * be changed with setThresholdForPruningUtility
     */
    private double thresholdForPruningUtility = 0.0;
    /**
     * Indicate if variables are sorted after each operation
     */
    private boolean sortProbTrees = true;
    /**
     * Indicate if variables are sorted after each operation
     */
    private boolean sortUtilityTrees = true;
    
    
    private boolean initialConstraints = false;
    private boolean combinationConstraints = false;
    private boolean removalConstraints = false;

    /**
     * Constructs a new propagation for a given Bayesian network and some
     * evidence.
     *
     * <code>Bnet</code>.
     *
     * @param e the evidence.
     */
    public IDSPIWithPotentialBPTree(Bnet b, Evidence e) {
        super(b, e);

        if (generateDebugInfo) {
            System.out.println("IDSPIWithPotentialBPTree:  class Constructor(Bnet,Evidence) ----- BEGIN");
        }

        BinaryProbabilityTree.setImprovedOps(true); 
        boolean evaluable = initialConditions();
        if (evaluable == false) {
            System.out.println("Non evaluable diagram......");
            System.exit(0);
        }


        if (generateDebugInfo) {
            System.out.println("IDSPIWithPotentialBPTree:  class Constructor ----- END");
        }


    }

    /**
     * Constructs a new propagation for a given Bayesian network
     *
     * @param b a
     * <code>Bnet</code>.
     */
    public IDSPIWithPotentialBPTree(Bnet b) {
        super(b, new Evidence());

        
        

        if (generateDebugInfo) {
            System.out.println("IDVEWithPotentialBPTree:  class Constructor(Bnet) ----- BEGIN");
        }

        BinaryProbabilityTree.setImprovedOps(true); 
        evaluable = IDSymbolicProbInf.EvaluableStates.NOCHECKED;


        if (generateDebugInfo) {
            System.out.println("IDVEWithPotentialBPTree:  class Constructor(Bnet) ----- END");
        }


    }
    
   /**
     * Transform the expected utility: applies the constraints
     *
     * @param <code>Potential</code> the potential to transform
     */
    @Override
    public Potential transformExpectedUtility(Potential pot) {
        PotentialBPTree potTree;



        // Try to prune, joining identical values
        if (pot.getClassName().equals("PotentialTable")) {
            potTree = new PotentialBPTree(pot);
        } else {
            potTree = (PotentialBPTree) pot;
        }

        
        
        if (generateDebugInfo) {
            System.out.println("IDVEWithPotentialBPTree:  transformExpectedUtility ----- BEGIN");

            System.out.println("\t - Potencial a transformar: " + potTree);
        }

 

           // Now, check the possible presence of constraints on this relation
           boolean constraints = (((IDSPIWithPotentialBPTree)this).isInitialConstraints() || ((IDSPIWithPotentialBPTree)this).isCombinationConstraints() || ((IDSPIWithPotentialBPTree)this).isRemovalConstraints());

            if (constraints) {
                PotentialBPTree constraintTree = (PotentialBPTree) ((IDiagram) network).applyConstraintsOnPotential(potTree, true);            
                if(constraintTree.getSize()<potTree.getSize())
                    potTree = (PotentialBPTree) constraintTree;
            }

        

        if (generateDebugInfo) {
            System.out.println("\t - Potencial transformado: " + potTree);
            System.out.println("IDVEWithPotentialBPTree:  transformExpectedUtility ----- END");
        }


        // Return the modified pot
        return potTree;


    }

    
    

    /**
     * Transforms one of the original relations into another one whose values
     * are of class
     * <code>PotentialBPTree</code>. @ param r the
     * <code>Relation</code> to be transformed.
     */
    @Override
    public Relation transformInitialRelation(Relation r) {
        PotentialBPTree potTree;
        Relation rNew;



        if (generateDebugInfo) {
            System.out.println("IDSPIWithPotentialBPTree:  transformInitialRelation ----- BEGIN");
        }


        // Transform the relation, but only for non constraints relations

        if (r.getKind() != Relation.CONSTRAINT) {
            rNew = new Relation();
            rNew.setVariables(r.getVariables().copy());
            rNew.setKind(r.getKind());

            //Transforms the tree
            if (!r.getValues().getClassName().equals("PotentialBPTree")) {
                potTree = new PotentialBPTree(r.getValues());
            } else {
                potTree = (PotentialBPTree) r.getValues();
            }


            // Store the new potential
            rNew.setValues(potTree);

            //Shows the information
            if (generateDebugInfo) {
                System.out.println("\t - Potencial a transformar: " + potTree);
            }


           
            // Now, check the possible presence of constraints on this relation
 
            if (initialConstraints) {
                PotentialBPTree constraintTree = (PotentialBPTree) ((IDiagram) network).applyConstraintsOnPotential(potTree, r.getKind() == Relation.UTILITY);            
                if(constraintTree.getSize()<potTree.getSize())
                    potTree = (PotentialBPTree) constraintTree;
            }
            
            // Now, prune the tree. If it is a utility, sort its
            // variables. Initially only exact prune operations

            if (r.getKind() == Relation.UTILITY) { //It is an utility tree

                double threshold = thresholdForPruningUtility;

                if (pruningUtilityMethod != IDVEWithPotentialBPTree.pruningMethods.NO_PRUNING) {
                    threshold = thresholdForPruningUtility;
                }
                Potential tinit = new PotentialTable(potTree);
                double[] maxMin = ((PotentialTable)tinit).getMaxMin();
                potTree.sortAndPruneUtility(sortProbTrees, false, pruningProbMethod, threshold, false,maxMin);


                //Saves the statistics of the initial prune
                if (generateStatistics) {
                    this.statistics.setInitalUtilityError(EUComparator.compareUtilities(tinit, potTree));
                    this.statistics.setInitialUtilitySize(potTree.getSize());
                    this.statistics.setInitialUtilityNodes(potTree.getNumberOfNodes());

                }

            } else {

                //It is a probability potential
                double threshold = thresholdForPruningProb;

                if (sortProbTrees) {
                    potTree.sort();
               //     potTree.print();
                }
                if (pruningProbMethod == IDVEWithPotentialBPTree.pruningMethods.KULLBACK_LEIBLER_DISTANCE) {
                    potTree.limitBound(threshold);
              //      potTree.print();
                }


            }




            if (generateDebugInfo) {
                System.out.println("\t - Potencial transformado: " + potTree);
            }


            // Store the final potential
            rNew.setValues(potTree);



            if (generateDebugInfo) {
                System.out.println("IDSPIWithPotentialBPTree:  transformInitialRelation ----- END");
            }


            // Return the new relation
            return rNew;
        } else {



            if (generateDebugInfo) {
                System.out.println("IDSPIWithPotentialBPTree:  transformInitialRelation ----- END");
            }

            // For constraints, do not change it
            return r;
        }
    }

    public pruningMethods getPruningProbMethod() {
        return pruningProbMethod;
    }

    public void setPruningProbMethod(pruningMethods pruningProbMethod) {
        this.pruningProbMethod = pruningProbMethod;
    }

    public pruningMethods getPruningUtilityMethod() {
        return pruningUtilityMethod;
    }

    public void setPruningUtilityMethod(pruningMethods pruningUtilityMethod) {
        this.pruningUtilityMethod = pruningUtilityMethod;
    }

    public boolean isSortProbTrees() {
        return sortProbTrees;
    }

    public void setSortProbTrees(boolean sortProbTrees) {
        this.sortProbTrees = sortProbTrees;
    }

    public boolean isSortUtilityTrees() {
        return sortUtilityTrees;
    }

    public void setSortUtilityTrees(boolean sortUtilityTrees) {
        this.sortUtilityTrees = sortUtilityTrees;
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

    public boolean isInitialConstraints() {
        return initialConstraints;
    }

    public void setInitialConstraints(boolean initialConstraints) {
        this.initialConstraints = initialConstraints;
    }

    public boolean isCombinationConstraints() {
        return combinationConstraints;
    }

    public void setCombinationConstraints(boolean combinationConstraints) {
        this.combinationConstraints = combinationConstraints;
    }

    public boolean isRemovalConstraints() {
        return removalConstraints;
    }

    public void setRemovalConstraints(boolean removalConstraints) {
        this.removalConstraints = removalConstraints;
    }
    
    
    public void setImprovedOps(boolean improvedOps) {
        BinaryProbabilityTree.setImprovedOps(improvedOps);
    } 
    
    
    
    
    
    
    
    

    public static void main(String[] args) throws ParseException, IOException {
        // TODO code application logic here
            String idFile;
            idFile = "ids/lazyspi/test3.elv";
            idFile = "ids/spi/motivation_not_binary_trees4.elv";
            idFile = "ids/spi/nhl.elv";
          //  idFile = "ids/lazyspi/test3.elv";
        
            IDiagram id = new IDiagram(idFile);
            IDSPIWithPotentialBPTree spi = new IDSPIWithPotentialBPTree(id);
            spi.generateStatistics=true;
            spi.generateDebugInfo=false;
            
            
            spi.setSimetric(true);
            spi.setSimetric(false);
            spi.setHeuristic(CombPairTable.MIN_WEIGHT_ONLY_PAIR);
           // spi.setHeuristic(CombPairTable.MIN_TREESIZE_ONLY_PAIR);
            
            spi.setThresholdForPruningProb(0.00);
            spi.setThresholdForPruningUtility(0.00);
            
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
