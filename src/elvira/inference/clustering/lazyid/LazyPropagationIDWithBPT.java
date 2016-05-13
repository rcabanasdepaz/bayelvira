package elvira.inference.clustering.lazyid;

import elvira.*;
import elvira.inference.elimination.ids.IDVEWithPotentialBPTree;
import elvira.inference.elimination.ids.IDVEWithPotentialBPTree.pruningMethods;
import elvira.potential.PotentialTable;
import elvira.potential.PotentialTree;
import elvira.parser.ParseException;
import elvira.potential.Potential;
import elvira.potential.binaryprobabilitytree.BinaryProbabilityTree;
import elvira.potential.binaryprobabilitytree.PotentialBPTree;
import elvira.tools.idiagram.EUComparator;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

public class LazyPropagationIDWithBPT extends LazyPropagationID {

    /**
     * Values for storing max and min values for utility
     */
    protected double maximum;
    protected double minimum;
    /**
     * Value for storing the threshold for prunning probability trees
     */
    protected double thresholdForPrunningProbability = 0;
    /**
     * Value for storing the threshold for prunning utility trees
     */
    protected double thresholdForPrunningUtility = 0;
    /**
     * Indicates if sort is made when building the probability tree
     */
    final protected boolean sortProbTrees = true;
    /**
     * Indicates if sort is made when building the util tree
     */
    final protected boolean sortUtilTrees = true;
    protected boolean pruneAfterOperation = false;

    /**
     * Class constructor: it receives the ID to solve
     *
     * @param diag
     * @param threshold
     * @param triangulationCriteria used for triangulation
     * @param propagationCriteria used for removing variables: with
     * triangulation order or with message passing schema
     * @param eliminationCriteria used for removing variables in the cliques:
     * offline-online
     * @param debug flag
     * @param statistics flag
     */
    public LazyPropagationIDWithBPT(IDiagram diag, double threshold, int triangulationCriteria, int propagationCriteria,
            int eliminationCriteria, boolean debugFlag, boolean statisticsFlag) {
        
        
        this(diag, threshold, threshold, triangulationCriteria, propagationCriteria, eliminationCriteria, debugFlag, statisticsFlag);
        
        
    }

    /**
     * Class constructor: it receives the ID to solve
     *
     * @param diag
     * @param thProb threshold for pruning Probability trees
     * @param thUtility threshold for pruning Utility trees
     * @param triangulationCriteria used for triangulation
     * @param propagationCriteria used for removing variables: with
     * triangulation order or with message passing schema
     * @param eliminationCriteria used for removing variables in the cliques:
     * offline-online
     * @param debug flag
     * @param statistics flag
     *
     */
    public LazyPropagationIDWithBPT(IDiagram diag, double thProb, double thUtility, int triangulationCriteria, int propagationCriteria,
            int eliminationCriteria, boolean debugFlag, boolean statisticsFlag) {
        this(diag, thProb, thUtility, triangulationCriteria, propagationCriteria, eliminationCriteria, debugFlag, statisticsFlag, false);
    }

    /**
     * Class constructor: it receives the ID to solve
     *
     * @param diag
     * @param thProb threshold for pruning Probability trees
     * @param thUtility threshold for pruning Utility trees
     * @param triangulationCriteria used for triangulation
     * @param propagationCriteria used for removing variables: with
     * triangulation order or with message passing schema
     * @param eliminationCriteria used for removing variables in the cliques:
     * offline-online
     * @param debug flag
     * @param statistics flag
     * @param transformAtTree flag
     */
    public LazyPropagationIDWithBPT(IDiagram diag, double thProb, double thUtility, int triangulationCriteria, int propagationCriteria,
            int eliminationCriteria, boolean debugFlag, boolean statisticsFlag, boolean transformAtTree) {
        
        super(diag, triangulationCriteria, propagationCriteria, eliminationCriteria, debugFlag, statisticsFlag, false, transformAtTree);
        
        
        if (generateDebugInfo) {
            System.out.println("LazyPropagationIDWithBPT:  class Constructor ----- BEGIN");
        }

        // Store the threshold for prunning
        thresholdForPrunningProbability = thProb;
        thresholdForPrunningUtility = thUtility;


        // Sets evaluable to NOCHECKED
        evaluable = LazyPropagationID.EvaluableStates.NOCHECKED;
        
        boolean state = initialConditions();
        
        if (state == false) {
            evaluable = LazyPropagationID.EvaluableStates.NOEVALUABLE;
            System.out.println("The IDiagram is not evaluable");
            System.exit(0);
        } else {
            evaluable = LazyPropagationID.EvaluableStates.EVALUABLE;
        }





        // Set method name
        setMethod("LayPropagationIDWithBPT");
        
        if (generateDebugInfo) {
            System.out.println("LazyPropagationIDWithBPT:  class Constructor ----- END");
        }
    }

    /**
     * Private method for building a tree node
     *
     * @param diag influence diagram
     */
    @Override
    public void buildStrongJunctionTree() {
        if (generateDebugInfo) {
            System.out.println("LazyPropagationIDWithBPT:  buildStringJunctionTree ----- BEGIN");
        }
        
        
        tree = new StrongJunctionTreeWithBPT((IDiagram) network, thresholdForPrunningProbability, thresholdForPrunningUtility,
                triangulationCriteria, propagationCriteria, variableEliminationCriteria,
                generateDebugInfo, generateStatistics, this);

        //this.transformAtTree = false;

        // If it is needed, set the reference to the statistics objetc
        // for the tree
        if (generateStatistics) {
            tree.setStatistics(statistics);
        }
        
        
        if (generateDebugInfo) {
            System.out.println("LazyPropagationIDWithBPT:  buildStringJunctionTree ----- END");
        }        
    }

    /**
     * Public method for making the propagation @Override propagate method of
     * super clase
     */
    public void propagate() {
        
        if (generateDebugInfo) {
            System.out.println("LazyPropagationIDWithBPT:  propagate ----- BEGIN");
        }

        Potential.setStatistics(statistics);
        
        // Make the strong junction tree
        buildStrongJunctionTree();

        // Set the properties for the evaluation
        ((StrongJunctionTreeWithBPT) tree).setMinimum(minimum);
        ((StrongJunctionTreeWithBPT) tree).setMaximum(maximum);
        ((StrongJunctionTreeWithBPT) tree).propagate();


        //Computes de decision tables
        checkDecisionTables();
        
        if (generateDebugInfo) {
            System.out.println("LazyPropagationIDWithPT:  propagate ----- BEGIN");
        }
    }
    
    public boolean isPruneAfterOperation() {
        return pruneAfterOperation;
    }
    
    public void setPruneAfterOperation(boolean pruneAfterOperation) {
        this.pruneAfterOperation = pruneAfterOperation;
    }
    
    public void setImprovedOps(boolean improvedOps) {
        BinaryProbabilityTree.setImprovedOps(improvedOps);
    }

    /**
     * Method to set the value for maximum
     *
     * @param value to set
     */
    public void setMaximum(double value) {
        if (generateDebugInfo) {
            System.out.println("LazyPropagationIDWithBPT:  setMaximum ----- BEGIN");
        }
        
        if (value > maximum) {
            maximum = value;
        }
        
        if (generateDebugInfo) {
            System.out.println("LazyPropagationIDWithBPT:  setMaximum ----- END");
        }
    }

    /**
     * Method to set the value for minimum
     *
     * @param value to set
     */
    public void setMinimum(double value) {
        if (generateDebugInfo) {
            System.out.println("LazyPropagationIDWithBPT:  setMinimum ----- BEGIN");
        }
        
        if (value < minimum) {
            minimum = value;
        }
        
        if (generateDebugInfo) {
            System.out.println("LazyPropagationIDWithBPT:  setMinimum ----- END");
        }
    }

    /**
     * Transforms one of the original relations into another one whose values
     * are of class
     * <code>PotentialTree</code>, and adding the effect of the possible
     * constraints. This is done for normal relations, but not for constraints
     * relations @ param r the
     * <code>Relation</code> to be transformed. @Override method in Propagation
     * class
     */
    @Override
    public Relation transformInitialRelation(Relation r) {
        if (transformAtTree) {
            return r;
        }
        return transformInitialRelationWithoutCheck(r);
        
    }

    /**
     * Transforms one of the original relations into another one whose values
     * are of class
     * <code>PotentialTree</code>, and adding the effect of the possible
     * constraints. This is done for normal relations, but not for constraints
     * relations @ param r the
     * <code>Relation</code> to be transformed. @Override method in Propagation
     * class
     */
    @Override
    protected Relation transformInitialRelationWithoutCheck(Relation r) {
        PotentialBPTree potTree;
        Relation rNew;
        double minimumValue;
        double maximumValue;
        
        if (generateDebugInfo) {
            System.out.println("LazyPropagationIDWithBPT:  transformInitialRelation ----- BEGIN");
            System.out.println("Ántes: " + r.getValues().getSize());
            
        }
        
        
        if (r.getKind() != Relation.CONSTRAINT) {
            // We do the conversion
            rNew = new Relation();
            rNew.setVariables(r.getVariables().copy());
            rNew.setKind(r.getKind());
            if (r.getValues().getClassName().equals("PotentialTable")) {                
                potTree = new PotentialBPTree(r.getValues());
            } else {
                potTree = (PotentialBPTree) (r.getValues());
            }
            
            
            if (generateDebugInfo) {
                System.out.println("\t - Potencial a transformar: " + potTree);
            }


            // Store the new potential
            rNew.setValues(potTree);



            // Now, check the possible presence of constraints on this relation
            if (this instanceof LazyPropagationIDWithBPTAC) {
                if (((LazyPropagationIDWithBPTAC) this).initialConstraints) {
                    ((IDiagram) network).applyConstraintsOnRelation(rNew);
                    potTree = (PotentialBPTree) rNew.getValues();
                }
            }

            // Prune operation, initially exact
            if (r.getKind() == Relation.UTILITY) {
                // First at all look for minimum and maximum
                
                double maxmin[] = potTree.getTree().getMaxMin();
                minimumValue = maxmin[1];
                maximumValue = maxmin[0];
                setMinimum(minimumValue);
                setMaximum(maximumValue);
                
                Potential tinit = new PotentialTable(potTree);
                
                //Utiliza el mínimo maximo local
                potTree.sortAndPruneUtility(sortUtilTrees, false, pruningMethods.EUCLIDEAN, thresholdForPrunningUtility, false, false, maxmin);


                //Saves the statistics of the initial prune
                if (generateStatistics) {
                    this.statistics.setInitalUtilityError(EUComparator.compareUtilities(tinit, potTree));
                    this.statistics.setInitialUtilitySize(potTree.getSize());
                    //    this.statistics.addUtilityHistory(potTree);
                }
                
            } else {

                //It is a probability potential

                if (sortProbTrees) {
                    potTree.sort();
                }                
                potTree.limitBound(thresholdForPrunningProbability);
                
                
            }
            
            
            if (generateDebugInfo) {
                System.out.println("\t - Potencial transformado: " + potTree);
                System.out.println("LazyPropagationIDWithPT:  transformInitialRelation ----- END");
            }


            // Store the new potential
            rNew.setValues(potTree);
            
            if (generateDebugInfo) {
                System.out.println("\t - Potencial transformado: " + potTree);
                System.out.println("LazyPropagationIDWithBPT:  transformInitialRelation ----- END");
            }


            // Return the new relation
            return rNew;
        } else {
            if (generateDebugInfo) {
                System.out.println("LazyPropagationIDWithBPT:  transformInitialRelation ----- END");
            }


            // It is a constraint and there is nothing to do with it
            return r;
        }
    }

    /**
     * Transforms one of the original relations into another one whose values
     * are of class
     * <code>PotentialTree</code>, and adding the effect of the possible
     * constraints. This is done for normal relations, but not for constraints
     * relations @ param r the
     * <code>Relation</code> to be transformed. @Override method in Propagation
     * class
     */
    @Override
    protected Relation transformInitialRelationWithoutCheck(Relation r, Vector<Node> importatVariables) {
        PotentialBPTree potTree;
        Relation rNew;
        double minimumValue;
        double maximumValue;
        
        if (generateDebugInfo) {
            System.out.println("LazyPropagationIDWithBPT:  transformInitialRelation ----- BEGIN");
            System.out.println("Ántes: " + r.getValues().getSize());
            
        }
        
        
        if (r.getKind() != Relation.CONSTRAINT) {
            // We do the conversion
            rNew = new Relation();
            rNew.setVariables(r.getVariables().copy());
            rNew.setKind(r.getKind());
            if (r.getValues().getClassName().equals("PotentialTable")) {                
                potTree = new PotentialBPTree(r.getValues());
            } else {
                potTree = (PotentialBPTree) (r.getValues());
            }
            
            
            if (generateDebugInfo) {
                System.out.println("\t - Potencial a transformar: " + potTree);
            }


            // Store the new potential
            rNew.setValues(potTree);



            // Now, check the possible presence of constraints on this relation
            if (this.getClass().getName().equals("elvira.inference.clustering.lazyid.LazyPropagationIDWithBPTAC")) {
                ((IDiagram) network).applyConstraintsOnRelation(rNew);
                potTree = (PotentialBPTree) rNew.getValues();
            }

            // Prune operation, initially exact
            if (r.getKind() == Relation.UTILITY) {
                // First at all look for minimum and maximum
                
                double maxmin[] = potTree.getTree().getMaxMin();
                minimumValue = maxmin[1];
                maximumValue = maxmin[0];
                setMinimum(minimumValue);
                setMaximum(maximumValue);
                
                Potential tinit = new PotentialTable(potTree);
                
                potTree.sortAndPruneUtility(sortUtilTrees, thresholdForPrunningUtility, importatVariables, false);


                //Saves the statistics of the initial prune
                if (generateStatistics) {
                    this.statistics.setInitalUtilityError(EUComparator.compareUtilities(tinit, potTree));
                    this.statistics.setInitialUtilitySize(potTree.getSize());
                }

                //tinit.print();//
                //potTree.print();//;

                //potTree = potTree.sortUtilityAndPruneWithoutRestrict(minimumValue, maximumValue, 0L, PotentialTree.DISTANCE_WITH_CEROS);
            } else {

                //It is a probability potential
                if (sortProbTrees) {
                    potTree.sort();
                }
                
                potTree.limitBound(thresholdForPrunningProbability, importatVariables);


                /*
                 * potTree.print();
                 *
                 * potTree.sortAndPruneProbability(sortUtilTrees,
                 * thresholdForPrunningProbability, importatVariables);
                 *
                 * potTree.print();
                 */
                
                
            }
            
            
            if (generateDebugInfo) {
                System.out.println("\t - Potencial transformado: " + potTree);
                System.out.println("LazyPropagationIDWithPT:  transformInitialRelation ----- END");
            }


            // Store the new potential
            rNew.setValues(potTree);
            
            if (generateDebugInfo) {
                System.out.println("\t - Potencial transformado: " + potTree);
                System.out.println("LazyPropagationIDWithBPT:  transformInitialRelation ----- END");
            }


            // Return the new relation
            return rNew;
        } else {
            if (generateDebugInfo) {
                System.out.println("LazyPropagationIDWithBPT:  transformInitialRelation ----- END");
            }


            // It is a constraint and there is nothing to do with it
            return r;
        }
    }
    
    public static void main(String[] args) throws ParseException, IOException {
        String networkName = null;
        boolean debug = false;
        boolean statistics = false;
        int triangulationCriteria = -1;
        int propagationCriteria = -1;
        int eliminationCriteria = -1;
        double threshold = 0.0;
        int i;

        // Show the arguments used when calling the program
        for (i = 0; i < args.length; i++) {
            System.out.print(args[i] + " ");
        }
        System.out.println();

        // Check the arguments passed to the program
        if (args.length < 2) {
            // Show how the program must be called
            usage();
        } else {
            // Consider the arguments one by one
            i = 0;
            while (i < args.length) {
                if (args[i].equals("-net")) {
                    networkName = args[i + 1];
                    i = i + 2;
                } else {
                    if (args[i].equals("-g")) {
                        debug = true;
                        i++;
                    } else {
                        if (args[i].equals("-s")) {
                            statistics = true;
                            i++;
                        } else {
                            if (args[i].equals("-t")) {
                                triangulationCriteria = Integer.parseInt(args[i + 1]);
                                i = i + 2;
                            } else {
                                if (args[i].equals("-e")) {
                                    eliminationCriteria = Integer.parseInt(args[i + 1]);
                                    i = i + 2;
                                } else {
                                    if (args[i].equals("-p")) {
                                        propagationCriteria = Integer.parseInt(args[i + 1]);
                                        i = i + 2;
                                    } else {
                                        if (args[i].equals("-tp")) {
                                            threshold = Double.parseDouble(args[i + 1]);
                                            i = i + 2;
                                        } else {
                                            usage();
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        // If everything is ok makes the evaluator
        Network net = Network.read(networkName);
        LazyPropagationIDWithBPT evaluator = new LazyPropagationIDWithBPT((IDiagram) net, threshold, triangulationCriteria, propagationCriteria, eliminationCriteria, debug, statistics);

        // Set the name for the statistics file
        String base = networkName.substring(0, networkName.lastIndexOf('.'));
        base = base.concat("_LazyPropagationIDWithBPT_data");
        evaluator.statistics.setFileName(base);

        // Make the propagation
        evaluator.propagate();

        // Now check if all the decision tables are computed
        evaluator.checkDecisionTables();

        // Print the results
        // evaluator.tree.printResults();
    }

    /**
     * Method to show how the program must be called
     */
    private static void usage() {
        System.out.println("Use: LazyPropagationIDWithBPT -net iDiagramFile");
        System.out.println("       [-g] (generate debug information) ");
        System.out.println("       [-s] (generate statistics)");
        System.out.println("       [-t] (triangulation criteria)");
        System.out.println("       -tp (threshold for prunning)");
        System.out.println("       [-p] (propagation criteria: direct elimination (1), message passing (2))");
        System.out.println("       [-e] (elimination criteria: online triangulation (2), offline triangulation (1))");
        System.exit(0);
    }
}
