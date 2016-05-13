package elvira.inference.elimination.ids;

import elvira.*;
import elvira.inference.elimination.ids.params.IDVEBPTparams;
import elvira.inference.elimination.ids.params.IDVEIBPTWTparams;
import elvira.potential.*;
import elvira.potential.binaryprobabilitytree.BinaryProbabilityTree;
import elvira.potential.binaryprobabilitytree.PotentialBPTree;
import elvira.tools.idiagram.EUComparator;
import java.util.Vector;

/**
 * Class
 * <code>VEWithPotentialBPTree</code>. Implements the variable elimination
 * method of propagation using potentials of class
 * <code>PotentialBPTree</code>. If the initial potentials are not
 * PotentialBPTrees then they are converted to PotentialBPTrees.
 *
 * @author Rafael Cabañas de Paz (rcabanas@decsai.ugr.es)
 */
public class IDVEWithPotentialBPTree extends IDVariableElimination {

    /**
     * A very low limit for prunning, allowing almost exact calculations.
     */
    public static final double limitForPruning = 0.0000001;

    /**
     * All posible methods for pruning the tree
     */
    public static enum pruningMethods {

        NO_PRUNING, KULLBACK_LEIBLER_DISTANCE, EUCLIDEAN, EUCLIDEAN_NORM, EUCLIDEAN_EXP, COSINE, EXT_JACCARD, RELATIVE2, CINDEX, PERCENT_ERROR
    };
    //    0               1                       2           3               4          5            6           7      8           9                      
    /**
     * Indicates the pruning method used for probability trees. By default, it
     * is not used any method
     */
    private IDVEWithPotentialBPTree.pruningMethods pruningProbMethod = IDVEWithPotentialBPTree.pruningMethods.NO_PRUNING;
    /**
     * Indicates the pruning method used for utility trees. By default, it is
     * not used any method
     */
    private IDVEWithPotentialBPTree.pruningMethods pruningUtilityMethod = IDVEWithPotentialBPTree.pruningMethods.NO_PRUNING;
    /**
     * Indicates if the c-index is used for making the partitions when pruning
     * utility trees (pruning method is not affected)
     */
    private boolean Cindex = false;
    /**
     * Limit to produce the identification of leaves in the tree This value can
     * be changed with setThresholdForPruning
     */
    protected double thresholdForPruningProb = 0.0;
    /**
     * Limit to produce the identification of leaves in the tree This value can
     * be changed with setThresholdForPruningUtility
     */
    protected double thresholdForPruningUtility = 0.0;
    /**
     * Indicate if variables are sorted after each operation
     */
    protected boolean sortProbTrees = true;
    /**
     * Indicate if variables are sorted after each operation
     */
    protected boolean sortUtilityTrees = true;
    /**
     * Data members to store the max and min values for the potentials
     */
    //protected double maximum, minimum;
    /**
     * Indicate if utility trees are normalized
     */
    boolean normalize = false;
    /**
     * Indicates if transformation for trees are only performed at the begining
     * or after each operation
     */
    protected boolean onlyInitialTransformation = false;
    /**
     * Adapted thresholds are used for pruning trees. If it is set to true, the
     * threshold will depend on the size of the tree
     */
    private boolean adaptativeThresholds = false;
    /**
     * Flag that indicates if trees are pruned during the initial
     * transformation. The default value is true
     */
    protected boolean pruneInitial = true;
    /**
     * Flag that indicates if trees are pruned during the transformations after
     * each operation. The default value is true
     */
    protected boolean pruneAfterOperation = true;
    protected double lowValuesThreshold = 0;
    
    protected boolean lowPruningAfterOperation = true; //NOT changed by the moment
    
    protected  boolean sortStates = false;

    /**
     * Constructs a new propagation for a given Bayesian network and some
     * evidence.
     *
     * @param b a
     * <code>Bnet</code>.
     * @param e the evidence.
     */
    public IDVEWithPotentialBPTree(Bnet b, Evidence e) {
        super(b, e);

        if (generateDebugInfo) {
            System.out.println("IDVEWithPotentialBPTree:  class Constructor(Bnet,Evidence) ----- BEGIN");
        }


        boolean evaluable = initialConditions();
        if (evaluable == false) {
            System.out.println("Non evaluable diagram......");
            System.exit(0);
        }


        if (generateDebugInfo) {
            System.out.println("IDVEWithPotentialBPTree:  class Constructor ----- END");
        }


    }

    /**
     * Constructs a new propagation for a given Bayesian network
     *
     * @param b a
     * <code>Bnet</code>.
     */
    public IDVEWithPotentialBPTree(Bnet b) {
        super(b, new Evidence());


        if (generateDebugInfo) {
            System.out.println("IDVEWithPotentialBPTree:  class Constructor(Bnet) ----- BEGIN");
        }


        // Call to initial conditions in order to get measures 
        // about potential sizes, etc        
        boolean evaluable = initialConditions();
        if (evaluable == false) {
            System.out.println("Non evaluable diagram......");
            System.exit(0);
        }



        if (generateDebugInfo) {
            System.out.println("IDVEWithPotentialBPTree:  class Constructor(Bnet) ----- END");
        }


    }

    /**
     * Constructs a new propagation for a given Bayesian network and some
     * evidence.
     *
     * @param b a
     * <code>Bnet</code>.
     * @param e the evidence.
     * @param p evaluation parameters
     */
    public IDVEWithPotentialBPTree(Bnet b, Evidence e, IDVEBPTparams p) {
        super(b, e, p);


        if (generateDebugInfo) {
            System.out.println("IDVEWithPotentialBPTree:  class Constructor(Bnet,Evidence,IDVEBPTparams) ----- BEGIN");
        }

        // Set the parameters 
        pruningProbMethod = p.getPruningProbMethod();
        pruningUtilityMethod = p.getPruningUtilityMethod();
        thresholdForPruningProb = p.getThresholdForPruningProb();
        thresholdForPruningUtility = p.getThresholdForPruningUtility();
        adaptativeThresholds = p.isAdaptativeThresholds();
        Cindex = p.isCindex();
        normalize = p.isNormalize();
        onlyInitialTransformation = p.isOnlyInitialTransformation();
        sortProbTrees = p.isSortProbTrees();
        sortUtilityTrees = p.isSortUtilityTrees();
        pruneInitial = p.isPruneInitial();
        pruneAfterOperation = p.isPruneAfterOperation();
        this.setImprovedOps(p.isImprovedOps());
        lowValuesThreshold = p.getLowValueThreshold();
        sortStates = p.isSortStates();
        LogicalExpression.setSortStates(sortStates);
        
        
       if (this instanceof IDVEWBPTAndConstraints) {
            ((IDVEWBPTAndConstraints) this).setInitialConstraints(p.isInitialConstraints());
            ((IDVEWBPTAndConstraints) this).setCombinationConstraints(p.isCombinationConstraints());
            ((IDVEWBPTAndConstraints) this).setRemovalConstraints(p.isRemovalConstraints());

        }

        if (this instanceof IDVEWithPotentialIBPTWT) {
            ((IDVEWithPotentialIBPTWT) this).setIprobPerturbation(((IDVEIBPTWTparams) p).getIprobPerturbation());
            ((IDVEWithPotentialIBPTWT) this).setIutilPerturbation(((IDVEIBPTWTparams) p).getIutilPerturbation());

        }


        // Call to initial conditions in order to get measures 
        // about potential sizes, etc
        boolean evaluable = initialConditions();
        if (evaluable == false) {
            System.out.println("Non evaluable diagram......");
            System.exit(0);
        }


        if (generateDebugInfo) {
            System.out.println("IDVEWithPotentialBPTree:  class Constructor(Bnet,Evidence,IDVEBPTparams) ----- END");
        }



    }

    /**
     * Constructs a new propagation for a given Bayesian network
     *
     * @param b a
     * <code>Bnet</code>.
     * @param p evaluation parameters
     */
    public IDVEWithPotentialBPTree(Bnet b, IDVEBPTparams p) {

        super(b, new Evidence(), p);

        if (generateDebugInfo) {
            System.out.println("IDVEWithPotentialBPTree:  class Constructor(Bnet,IDVEBPTparams) ----- BEGIN");
        }
        // Set the parameters
        pruningProbMethod = p.getPruningProbMethod();
        pruningUtilityMethod = p.getPruningUtilityMethod();
        thresholdForPruningProb = p.getThresholdForPruningProb();
        thresholdForPruningUtility = p.getThresholdForPruningUtility();
        adaptativeThresholds = p.isAdaptativeThresholds();
        Cindex = p.isCindex();
        normalize = p.isNormalize();
        onlyInitialTransformation = p.isOnlyInitialTransformation();
        sortProbTrees = p.isSortProbTrees();
        sortUtilityTrees = p.isSortUtilityTrees();
        pruneInitial = p.isPruneInitial();
        pruneAfterOperation = p.isPruneAfterOperation();
        this.setImprovedOps(p.isImprovedOps());
        saveAsTable = p.isSaveAsTable();
        lowValuesThreshold = p.getLowValueThreshold();
        sortStates = p.isSortStates();
        LogicalExpression.setSortStates(sortStates);

        if (this instanceof IDVEWBPTAndConstraints) {
            ((IDVEWBPTAndConstraints) this).setInitialConstraints(p.isInitialConstraints());
            ((IDVEWBPTAndConstraints) this).setCombinationConstraints(p.isCombinationConstraints());
            ((IDVEWBPTAndConstraints) this).setRemovalConstraints(p.isRemovalConstraints());

        }

        if (this instanceof IDVEWithPotentialIBPTWT) {
            ((IDVEWithPotentialIBPTWT) this).setIprobPerturbation(((IDVEIBPTWTparams) p).getIprobPerturbation());
            ((IDVEWithPotentialIBPTWT) this).setIutilPerturbation(((IDVEIBPTWTparams) p).getIutilPerturbation());

        }


        // Call to initial conditions in order to get measures 
        // about potential sizes, etc
        boolean evaluable = initialConditions();
        if (evaluable == false) {
            System.out.println("Non evaluable diagram......");
            System.exit(0);
        }


        if (generateDebugInfo) {
            System.out.println("IDVEWithPotentialBPTree:  class Constructor(Bnet,IDVEBPTparams) ----- END");
        }



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

        r = super.transformInitialRelation(r);

        if (generateDebugInfo) {
            System.out.println("IDVEWithPotentialBPTree:  transformInitialRelation ----- BEGIN");
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
            if (this instanceof IDVEWBPTAndConstraints) {
                if (((IDVEWBPTAndConstraints) this).initialConstraints) {
                    ((IDiagram) network).applyConstraintsOnRelation(rNew);
                    potTree = (PotentialBPTree) rNew.getValues();
                }
            }
            // Now, prune the tree. If it is a utility, sort its
            // variables. Initially only exact prune operations
            if (pruneInitial) {
                if (r.getKind() == Relation.UTILITY) { //It is an utility tree

                    double threshold = thresholdForPruningUtility;
                    //Uses an individual threshodl
                    if (adaptativeThresholds) {
                        threshold = thresholdForPruningUtility * Math.exp(1 + (double) (potTree.getSize()) / maxSize);
                    }

                    if (pruningUtilityMethod != IDVEWithPotentialBPTree.pruningMethods.NO_PRUNING) {
                        threshold = thresholdForPruningUtility;
                    }
                    Potential tinit = new PotentialTable(potTree);
                    
                    
                    double maxmin[] = {maximumIU, minimumIU};
                    potTree.sortAndPruneUtility(sortUtilityTrees, normalize, pruningUtilityMethod, threshold, Cindex, sortStates,maxmin);
                    if (lowValuesThreshold > 0) {
                        double limit = (maxmin[0] - maxmin[1]) * lowValuesThreshold;
                        potTree.replaceLowValues(limit, true);
                        potTree.limitBound(0L);
                    }

                    //Saves the statistics of the initial prune
                    if (generateStatistics) {
                        this.statistics.setInitalUtilityError(EUComparator.compareUtilities(tinit, potTree));
                        this.statistics.setInitialUtilitySize(potTree.getSize());
                        this.statistics.setInitialUtilityNodes(potTree.getNumberOfNodes());

                    }

                } else {

                    //It is a probability potential

                    double threshold = thresholdForPruningProb;
                    //Uses an individual threshodl
                    if (adaptativeThresholds) {
                        threshold = (thresholdForPruningProb) * Math.exp(1 + (double) (potTree.getSize()) / maxSize);
                    }
                    


                    

                    //Sort the tree
                    if (sortProbTrees && pruningProbMethod == IDVEWithPotentialBPTree.pruningMethods.NO_PRUNING) {
                        potTree.sort(sortStates);
                    } else if (sortProbTrees) {
                        potTree.sortAndBound(0L,sortStates);
                    }
                   
                    
                    //Prune the tree
                    if (pruningProbMethod == IDVEWithPotentialBPTree.pruningMethods.KULLBACK_LEIBLER_DISTANCE) {
                        potTree.limitBound(threshold);
                    }

                    if (lowValuesThreshold > 0) {
                        potTree.replaceLowValues(lowValuesThreshold, false);
                        potTree.limitBound(0L);
                    }

                    

                }
            }



            /*
             * // Store the final potential rNew.setValues(potTree); // Now,
             * check the possible presence of constraints on this relation if
             * (this instanceof IDVEWBPTAndConstraints) { ((IDiagram)
             * network).applyConstraintsOnRelation(rNew); // potTree =
             * (PotentialBPTree) rNew.getValues(); PotentialBPTree
             * potTreeConstraint = (PotentialBPTree) rNew.getValues();
             *
             * if(potTreeConstraint.getSize() < potTree.getSize()) potTree =
             * potTreeConstraint;
             *
             *
             * }
             *
             */

            if (generateDebugInfo) {
                System.out.println("\t - Potencial transformado: " + potTree);
            }


            // Store the final potential
            rNew.setValues(potTree);
            
      //      potTree.printTikz(0, 0, -1);


            if (generateDebugInfo) {
                System.out.println("IDVEWithPotentialBPTree:  transformInitialRelation ----- END");
            }


            // Return the new relation
            return rNew;
        } else {



            if (generateDebugInfo) {
                System.out.println("IDVEWithPotentialBPTree:  transformInitialRelation ----- END");
            }

            // For constraints, do not change it
            return r;
        }
    }

    /**
     * Transform an utility potential, prunning the lower values if possible
     *
     * @param <code>Potential</code> the potential to transform
     * @param <code>boolean</code> is a utility?
     */
    @Override
    public Potential transformAfterOperation(Potential pot, boolean utility) {
        PotentialBPTree potTree;




        // Try to prune, joining identical values

        if (pot.getClassName().equals("PotentialTable")) {
            potTree = new PotentialBPTree(pot);
        } else {
            potTree = (PotentialBPTree) pot;
        }
        //Transformation after operations is not performed
        if (!onlyInitialTransformation) {


            //  Potential initTree = potTree.copy();


            if (generateDebugInfo) {
                System.out.println("IDVEWithPotentialBPTree:  transformAfterOperation ----- BEGIN");

                System.out.println("\t - Potencial a transformar: " + potTree);
            }


            // Now, check the possible presence of constraints on this relation
            if (this instanceof IDVEWBPTAndConstraints) {

                if (((IDVEWBPTAndConstraints) this).isRemovalConstraints()) {
                    PotentialBPTree potTreeConstraint = (PotentialBPTree) ((IDiagram) network).applyConstraintsOnPotential(potTree, utility);

                    //Dirscard the resulting tree if it is larger
                    if (potTreeConstraint.getSize() < potTree.getSize()) {
                        potTree = potTreeConstraint;
                    }
                }
            }



            // Prune the tree. If it is a utility tree, sort its variables
            // Now prune operation is done using thresholdForPrunning
            if (pruneAfterOperation) {
                if (utility) { // It is an utility potential



                    double threshold = thresholdForPruningUtility;
                    //Uses an individual threshodl
                    if (adaptativeThresholds) {
                        threshold = (thresholdForPruningUtility) * Math.exp(1 + (double) (potTree.getSize()) / maxSize);
                    }
                    if (pruningUtilityMethod != IDVEWithPotentialBPTree.pruningMethods.NO_PRUNING) {
                        threshold = thresholdForPruningUtility;
                    }
                    
                    double maxmin[] ={maximumIU, minimumIU};
                    potTree.sortAndPruneUtility(sortUtilityTrees, normalize, pruningUtilityMethod, threshold, Cindex, maxmin);
                    // potTree.sortAndPruneUtility(false, normalize, pruningUtilityMethod, threshold, Cindex);
                } else {    //It is a probability potential



                    double threshold = thresholdForPruningProb;
                    //Uses an individual threshodl
                    if (adaptativeThresholds) {
                        threshold = (thresholdForPruningProb) * Math.exp(1 + (double) (potTree.getSize()) / maxSize);
                    }

                    //                potTree.sortAndBound(threshold);


                    if (sortProbTrees) {
                        potTree.sort();
                    }


                    if (pruningProbMethod == IDVEWithPotentialBPTree.pruningMethods.KULLBACK_LEIBLER_DISTANCE) {
                        potTree.limitBound(threshold);

                    }

                }
            }
            
            //Low pruning
            if(lowPruningAfterOperation) {
                 //Low values pruning 
                if (lowValuesThreshold > 0) {
                    
                    double limit = lowValuesThreshold;
                    if(utility) {
                        double[] maxMin = potTree.getTree().getMaxMin();
                        limit *= (maxMin[0] - maxMin[1]);
                    }
                    potTree.replaceLowValues(limit, utility);
                    potTree.limitBound(0L);
                }
            }
            
            
        }




        //System.out.println("\terror "+EUComparator.compareUtilities(potTree, initTree));


        if (generateDebugInfo) {
            System.out.println("\t - Potencial transformado: " + potTree);
            System.out.println("IDVEWithPotentialBPTree:  transformAfterOperation ----- END");
        }


        // Return the modified pot
        return potTree;


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
        if (this instanceof IDVEWBPTAndConstraints) {

            boolean constraints =  (((IDVEWBPTAndConstraints) this).isInitialConstraints() ||
                                    ((IDVEWBPTAndConstraints) this).isCombinationConstraints() ||
                                    ((IDVEWBPTAndConstraints) this).isRemovalConstraints()); 
            if (constraints) {
                potTree = (PotentialBPTree) ((IDiagram) network).applyConstraintsOnPotential(potTree, true);
            }

        }




        if (generateDebugInfo) {
            System.out.println("\t - Potencial transformado: " + potTree);
            System.out.println("IDVEWithPotentialBPTree:  transformExpectedUtility ----- END");
        }


        // Return the modified pot
        return potTree;


    }

    /**
     * Method to set the value for maximum
     *
     * @param value to set
     */
    public void setMaximum(double value) {
        if (value > maximum) {
            maximum = value;
        }
    }

    /**
     * Method to set the value for minimum
     *
     * @param value to set
     */
    public void setMinimum(double value) {
        if (value < minimum) {
            minimum = value;
        }
    }

    /**
     * Method for setting the pruning method with probability trees
     *
     * @param pruningMethod
     */
    public void setPruningProbMethod(IDVEWithPotentialBPTree.pruningMethods pruningMethod) {
        this.pruningProbMethod = pruningMethod;
    }

    /**
     * Method to indicate if the variables in probability trees must be sort
     *
     * @param sortVariablesInTrees
     */
    public void setSortProbTrees(boolean sortVariablesInTrees) {
        this.sortProbTrees = sortVariablesInTrees;
    }

    /**
     * Sets the treshold for pruning probability trees
     *
     * @param thresholdForPruning
     */
    public void setThresholdForPruningProb(double thresholdForPruning) {
        this.thresholdForPruningProb = thresholdForPruning;
    }

    /**
     * Method for setting the pruning method with Utility trees
     *
     * @param pruningMethod
     */
    public void setPruningUtilityMethod(IDVEWithPotentialBPTree.pruningMethods pruningMethod) {
        this.pruningUtilityMethod = pruningMethod;
    }

    /**
     * Method for setting the pruning method with probability trees
     *
     * @param pruningMethod
     */
    /**
     * Method to indicate if the variables in Utility trees must be sort
     *
     * @param sortVariablesInTrees
     */
    public void setSortUtilityTrees(boolean sortVariablesInTrees) {
        this.sortUtilityTrees = sortVariablesInTrees;
    }

    /**
     * Sets the treshold for pruning probability trees
     *
     * @param thresholdForPruning
     */
    public void setThresholdForPruningUtility(double thresholdForPruning) {
        this.thresholdForPruningUtility = thresholdForPruning;
    }

    public void setNormalize(boolean normalize) {
        this.normalize = normalize;


    }

    /**
     * Method which indicates if transformations are made only with the initial
     * potentials
     *
     * @return
     */
    public boolean isOnlyInitialTransformation() {
        return onlyInitialTransformation;
    }

    /**
     * Method to set the transformation only at the begining or after each
     * operation
     *
     * @param onlyInitialTransformation
     */
    public void setOnlyInitialTransformation(boolean onlyInitialTransformation) {
        this.onlyInitialTransformation = onlyInitialTransformation;
    }

    public boolean isCindex() {
        return Cindex;
    }

    public void setCindex(boolean Cindex) {
        this.Cindex = Cindex;
    }

    /**
     * Indicates it adapted thresholds are used. The default value is false
     *
     * @param adaptativeThresholds
     */
    public void setAdaptativeThresholds(boolean adaptativeThresholds) {
        this.adaptativeThresholds = adaptativeThresholds;
    }

    /**
     * Flag that indicates if trees are pruned during the initial
     * transformation. The default value is true
     */
    public void setPruneAfterOperation(boolean pruneAfterOperation) {
        this.pruneAfterOperation = pruneAfterOperation;
    }

    /**
     * Flag that indicates if trees are pruned during the transformations after
     * each operation The default value is true
     */
    public void setPruneInitial(boolean pruneInitial) {
        this.pruneInitial = pruneInitial;
    }

    public boolean isImprovedOps() {
        return BinaryProbabilityTree.isImprovedOps();
    }

    public void setImprovedOps(boolean improvedOps) {
        BinaryProbabilityTree.setImprovedOps(improvedOps);
    }

    public double getLowValuesThreshold() {
        return lowValuesThreshold;
    }

    public void setLowValuesThreshold(double lowValuesThreshold) {
        this.lowValuesThreshold = lowValuesThreshold;
    }
    
    
    
    public static pruningMethods getPruningMethod(String name){
        
  /*              NO_PRUNING, KULLBACK_LEIBLER_DISTANCE, EUCLIDEAN, EUCLIDEAN_NORM, EUCLIDEAN_EXP, COSINE, EXT_JACCARD, RELATIVE2, CINDEX, PERCENT_ERROR
    };
    //    0    
    */    
        pruningMethods method = null;
    
        if(name.equals("KULLBACK_LEIBLER_DISTANCE"))
            method = IDVEWithPotentialBPTree.pruningMethods.KULLBACK_LEIBLER_DISTANCE;
        else if(name.equals("EUCLIDEAN"))
            method = IDVEWithPotentialBPTree.pruningMethods.EUCLIDEAN;
        else if(name.equals("EUCLIDEAN_NORM"))
            method = IDVEWithPotentialBPTree.pruningMethods.EUCLIDEAN_NORM;
        else if(name.equals("EUCLIDEAN_EXP"))
            method = IDVEWithPotentialBPTree.pruningMethods.EUCLIDEAN_EXP;
        else if(name.equals("COSINE"))
            method = IDVEWithPotentialBPTree.pruningMethods.COSINE;
        else if(name.equals("EXT_JACCARD"))
            method = IDVEWithPotentialBPTree.pruningMethods.EXT_JACCARD;
        else if(name.equals("RELATIVE2"))
            method = IDVEWithPotentialBPTree.pruningMethods.RELATIVE2;
        else if(name.equals("CINDEX"))
            method = IDVEWithPotentialBPTree.pruningMethods.CINDEX;
        else if(name.equals("PERCENT_ERROR"))
            method = IDVEWithPotentialBPTree.pruningMethods.PERCENT_ERROR;
        else {
            System.out.println("ERROR: invalid prunning method "+name);
            System.exit(-1);
        }
        
        return method;
        
    }
    
}
