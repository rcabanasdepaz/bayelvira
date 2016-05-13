/* VariableElimination.java */
package elvira.inference.elimination.ids;

import elvira.tools.OperationsList;
import java.util.Vector;
import java.io.*;
import elvira.*;
import elvira.inference.elimination.VEWithPotentialTree;
import elvira.inference.elimination.VariableElimination;
import elvira.inference.elimination.ids.params.IDVEparams;
import elvira.parser.ParseException;
import elvira.potential.*;
import elvira.potential.binaryprobabilitytree.PotentialBPTree;
import elvira.potential.binaryprobabilitytree.PotentialIBPTWT;
import elvira.tools.idiagram.EUComparator;
import elvira.tools.idiagram.pairtable.IDPairTable;
import elvira.tools.idiagram.IDPotentialAnalyzer;
import elvira.tools.idiagram.pairtable.CombPair;
import elvira.tools.idiagram.pairtable.CombPairTable;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

/**
 * This class implements a generic variable elimination method of propagation.
 * The initial potentials can be of any kind, but they must define the methods:
 * <ul> <li>
 * <code>Potential combine(Pontential combine)</code> <li>
 * <code>Potential addVariable(FiniteStates var)</code> </ul> This class can be
 * extended for special requirements. The methods that can be overloaded are:
 * <ul> <li>
 * <code><a href="#transformInitialRelation(Relation)">Relation transformInitialRelation(Relation r)</a></code>
 * <li>
 * <code><a href="#transformAfterAdding(Potential)">Potential transformAfterAdding(Potential pot)</a></code>
 * <li>
 * <code><a href="#transformAfterEliminating(Potential)">Potential transformAfterEliminating(Potential pot)</a></code>
 * <li>
 * <code><a href="#transformAfterOperation(Potential)">Potential transformAfterOperation(Potential pot, boolean flag)</a></code>
 * <li>
 * <code><a href="#combine(Potential, Potential)">Potential combine(Potential pot1,Potential pot2)</a></code>
 * <li>
 * <code><a href="#addVariable(Potential, FiniteStates)">Potential addVariable(Potential pot,FiniteStates var)</a></code>
 * </ul>
 *
 * @author mgomez (megomez@decsai.ugr.es)
 * @author Andres Cano (acu@decsai.ugr.es)
 * @see VEWithPotentialTree
 * @since 14/3/2001
 */
public class IDVariableElimination extends VariableElimination {

    private boolean simpleDecisionRemoval = false;

    /**
     * Constants to indicate the possible states of evaluable
     */
    protected enum EvaluableStates {

        NOCHECKED, EVALUABLE, NOEVALUABLE
    };
    /**
     * Maximum and minimum values of utility reached during IDs evaluation
     */
    protected double maximum;
    protected double minimum;
    /**
     * Maximum and minimum values of the initial utilities
     */
    protected double maximumIU = Double.NEGATIVE_INFINITY;
    protected double minimumIU = Double.POSITIVE_INFINITY;
    /**
     * Data member to indicate if the diagram is evaluable: it is prepared for
     * evaluation. Initially this value is NOCHECKED and its value is determined
     * after a call to initialConditions
     */
    protected EvaluableStates evaluable;
    /**
     * To show if we want to use statistics about the evaluation It is required
     * to change this flag to use statistics
     */
    public boolean generateStatistics = true;
    /**
     * To show if we want to use debug information about the evaluation. The
     * information is related to the evaluation for Influence diagrams
     */
    public boolean generateDebugInfo = true;
    /**
     * Operations list for storing information about the elimination process
     */
    OperationsList operations;
    /**
     * Keeps the size of the whole set of relations
     */
    double totalSize;
    /**
     * Stores the size of the largest potential
     */
    protected long maxSize;
    private int idCriteria = IDPairTable.MINSIZE;
    protected boolean saveAsTable = true;
    Vector<Node> removalOrdering = null;
    protected boolean greedyCombination = false;
    protected int combinationHeuristic = CombPairTable.MIN_SIZE_ONLY_PAIR;
    protected boolean checkUnity = true;
    protected boolean simetric = true;
    protected boolean conditioning = true;
    protected Vector strategy;
    //to measure the overhead for choosing the operations
    protected boolean measureOverHead = false;
    protected long overHeadTime = 0;
    private long initTime, endTime;
    public static boolean normUtilities = false;
    private boolean qualitativeEval = false;

    /**
     * Constructs a new propagation for a given Bayesian network and some
     * evidence.
     *
     * @param b a
     * <code>Bnet</code>.
     * @param e the evidence.
     */
    public IDVariableElimination(Bnet b, Evidence e) {
        super(b, e);

        /*
         * if (generateDebugInfo) { System.out.println("IDVariableElimination:
         * class Constructor(Bnet,Evidence) ----- BEGIN"); }
         */
        evaluable = EvaluableStates.NOCHECKED;
        maxSize = IDPotentialAnalyzer.getMaxSize(((IDiagram) b).getRelationList());
        strategy = new Vector();

        /*
         * if (generateDebugInfo) { System.out.println("IDVariableElimination:
         * class Constructor ----- END"); }
         */
    }

    /**
     * Constructs a new propagation for a given Bayesian network
     *
     * @param b a
     * <code>Bnet</code>.
     */
    public IDVariableElimination(Bnet b) {
        super(b);
        /*
         * if (generateDebugInfo) { System.out.println("IDVariableElimination:
         * class Constructor(Bnet) ----- BEGIN"); }
         */
        evaluable = EvaluableStates.NOCHECKED;
        strategy = new Vector();
        /*
         * if (generateDebugInfo) { System.out.println("IDVariableElimination:
         * class Constructor ----- END"); }
         */
    }

    /**
     * Constructs a new propagation for a given Bayesian network and some
     * evidence.
     *
     * @param b a
     * <code>Bnet</code>.
     * @param e the evidence.
     */
    public IDVariableElimination(Bnet b, Evidence e, IDVEparams p) {
        super(b, e);

        /*
         * if (generateDebugInfo) { System.out.println("IDVariableElimination:
         * class Constructor(Bnet,Evidence) ----- BEGIN"); }
         */

        //Set parameters of evaluation
        maximum = p.getMaximum();
        minimum = p.getMinimum();
        generateDebugInfo = p.isGenerateDebugInfo();
        generateStatistics = p.isGenerateStatistics();
        checkUnity = p.checkUnity;
        ((IDiagram) b).setGenerateDebugInfo(generateDebugInfo);

        evaluable = EvaluableStates.NOCHECKED;
        maxSize = IDPotentialAnalyzer.getMaxSize(((IDiagram) b).getRelationList());
        strategy = new Vector();

        /*
         * if (generateDebugInfo) { System.out.println("IDVariableElimination:
         * class Constructor ----- END"); }
         */
    }

    /**
     * Constructs a new propagation for a given Bayesian network
     *
     * @param b a
     * <code>Bnet</code>.
     */
    public IDVariableElimination(Bnet b, IDVEparams p) {
        super(b);
        /*
         * if (generateDebugInfo) { System.out.println("IDVariableElimination:
         * class Constructor(Bnet) ----- BEGIN"); }
         */

        //Set parameters of evaluation
        maximum = p.getMaximum();
        minimum = p.getMinimum();
        generateDebugInfo = p.isGenerateDebugInfo();
        generateStatistics = p.isGenerateStatistics();
        ((IDiagram) b).setGenerateDebugInfo(generateDebugInfo);
        strategy = new Vector();

        evaluable = EvaluableStates.NOCHECKED;


        /*
         * if (generateDebugInfo) { System.out.println("IDVariableElimination:
         * class Constructor ----- END"); }
         */
    }

    /**
     * Checks if an influence diagram has the properties required for being
     * evaluated
     *
     * @return true or false
     */
    public boolean initialConditions() {
        String errorMessage = null;
        boolean state = true;

        if (generateDebugInfo) {
            System.out.println("IDVariableElimination:  initialConditions ----- BEGIN");
        }

        // Gets the network
        IDiagram diag = (IDiagram) network;

        // Makes the checks if avaluable is false
        if (evaluable == EvaluableStates.NOCHECKED) {
            // Check if all the links are directed
            state = diag.directedLinks();

            // Error if evaluable is false and return false
            if (state == false) {
                errorMessage = "Influence Diagram with no directed links\n\n";
            } else {
                // Check the presence of cycles
                state = diag.hasCycles();
                if (state == true) {
                    errorMessage = "Influence Diagram with cycles\n\n";
                } else {
                    // Add non forgetting arcs
                    diag.addNonForgettingArcs();

                    // Check if there is a path linking all the decisions
                    state = diag.pathBetweenDecisions();
                    if (state == false) {
                        errorMessage = "Influence Diagram with non ordered decisions\n\n";
                    } else {
                        // Remove redundancy and barren nodes
                        diag.eliminateRedundancy();

                        // Transform the set of initial relations
                        maximum = 0;
                        minimum = 10000;
                        updateInitialUtilityMaxMin();

                        ((IDiagram) network).setProp(this);
                        currentRelations = getInitialRelations();
                    }
                }
            }
        }



        // Prints the error message if needed
        if (!state) {
            evaluable = EvaluableStates.NOEVALUABLE;
            System.out.println(errorMessage);
        } else {
            evaluable = EvaluableStates.EVALUABLE;
        }

        if (generateDebugInfo) {
            System.out.println("IDVariableElimination:  initialConditions ----- END");
        }

        // Return evaluable
        return state;
    }

    /**
     * Method to return the value of maximum
     */
    public double getMaximum() {
        if (generateDebugInfo) {
            System.out.println("IDVariableElimination:  getMaximum ----- BEGIN");
            System.out.println("IDVariableElimination:  getMaximum ----- END");
        }
        return maximum;
    }

    /**
     * Method to set the value for maximum
     *
     * @param value to set
     */
    public void setMaximum(double value) {
        if (generateDebugInfo) {
            System.out.println("IDVariableElimination:  setMaximum ----- BEGIN");
        }
        if (value > maximum) {
            maximum = value;
        }
        if (generateDebugInfo) {
            System.out.println("IDVariableElimination:  setMaximum ----- END");
        }
    }

    /**
     * Method to return the value of minimum
     */
    public double getMinimum() {
        if (generateDebugInfo) {
            System.out.println("IDVariableElimination:  getMinimum ----- BEGIN");
            System.out.println("IDVariableElimination:  getMinimum ----- END");
        }
        return minimum;
    }

    /**
     * Method to set the value for minimum
     *
     * @param value to set
     */
    public void setMinimum(double value) {
        if (generateDebugInfo) {
            System.out.println("IDVariableElimination:  setMinimum ----- BEGIN");
        }
        if (value < minimum) {
            minimum = value;
        }
        if (generateDebugInfo) {
            System.out.println("IDVariableElimination:  setMinimum ----- END");
        }
    }

    /**
     * Program for performing experiments from the command line. The command
     * line arguments are as follows: <ol> <li> Input file: the network. <li>
     * Output file. <li> Evidence file. </ol> If the evidence file is omitted,
     * then no evidences are considered.
     */
    public static void main(String args[]) throws ParseException, IOException {

        Network b;
        Evidence e;
        FileInputStream evidenceFile;
        IDVariableElimination ve;
        String base;
        int i;

        if (args.length < 2) {
            System.out.println("Too few arguments. Arguments are: ElviraFile OutputFile EvidenceFile");
        } else {
            // networkFile = new FileInputStream(args[0]);
            // b = new Bnet(networkFile);
            b = Network.read(args[0]);

            if (args.length == 3) {
                evidenceFile = new FileInputStream(args[2]);
                e = new Evidence(evidenceFile, b.getNodeList());
            } else {
                e = new Evidence();
            }

            ve = new IDVariableElimination((Bnet) b, e);
            ve.obtainInterest();

            // Compose the name for the file with the statistics

            if (ve.generateStatistics == true) {
                base = args[0].substring(0, args[0].lastIndexOf('.'));
                base = base.concat("_VariableElimination_data");
                ve.statistics.setFileName(base);
            }
            ve.propagate(args[1]);

        }
    }

    public int getCombinationHeuristic() {
        return combinationHeuristic;
    }

    public void setCombinationHeuristic(int combinationHeuristic) {
        this.combinationHeuristic = combinationHeuristic;
    }

    /**
     * Gets the expected utility tables for the decision nodes in an influence
     * diagram
     */
    private void getPosteriorDistributionsID() {
        NodeList notRemoved;
        Node x;
        RelationList rLtemp;
        Relation valueRel;
        IDPairTable pt = null;
        int i, j, s;
        Vector vars = null;
        String operation;
        String base;
        boolean combined;
        totalSize = 0;

        if (generateDebugInfo) {
            System.out.println("IDVariableElimination:  getPosteriorDistributions ----- BEGIN");
        }

        // Initializes the crono
        crono.start();

        // First at all, remove all results stored in results vector
        results.removeAllElements();
        strategy.removeAllElements();

        // Insert in notRemoved all not observed nodes
        notRemoved = getNotObservedNodes();

        startMeasureOverHead();
        // Make a pair table
        pt = new IDPairTable((IDiagram) network, observations, currentRelations);
        pt.setIDCriteria(idCriteria);
        stopMeasureOverHead();

        // Consider the impact of observations on relations
        restrictCurrentRelationsToObservations();

        if (generateDebugInfo == true) {
            currentRelations.printDomainsAndSizes();
        }


        if (generateStatistics == true) {
            // Creates the operations list
            operations = new OperationsList();
            // Note down the data about the beginning of the evaluation
            statistics.addOperation("Start of evaluation: ");
            totalSize = currentRelations.sumSizes();
            statistics.addSize(totalSize);
            statistics.addTime(crono.getTime());
            statistics.addNumberNodes(getCurrentRelations().sumNumNodes());
            statistics.addTotalSize(getCurrentRelations().sumSizes());




        }
//int vi = 1;
        // Loop to eliminate the variables
        for (i = notRemoved.size(); i > 0; i--) {

            // Select next variable to remove
            startMeasureOverHead();
            if (removalOrdering != null) {
                x = removalOrdering.get(0);
                removalOrdering.remove(0);
            } else {
                x = pt.nextToRemoveID();
            }
            stopMeasureOverHead();
            if (generateStatistics == true) {
                // Store the operation
                statistics.addOperation("Variable elimination: " + x.getName());
                operations.addOperation(x);
            }

            // Delete this node of notRemoved and pairTable
            startMeasureOverHead();
            notRemoved.removeNode(x);
            pt.removeVariable((FiniteStates) x);
            stopMeasureOverHead();

            // Combine the potentials of this node

            if (generateDebugInfo == true) {
                System.out.println("\nVariable a eliminar: " + x.getName());
            }


            if (x.getKindOfNode() == Node.CHANCE) {
                if (generateDebugInfo == true) {
                    System.out.println("A eliminar nodo de azar");
                }

                // Combine potentials for removing the chance node

                combinePotentialsToRemoveChanceNode((FiniteStates) x, pt);

            } else {
                if (x.getKindOfNode() == Node.DECISION) {


                    if (generateDebugInfo == true) {
                        System.out.println("A eliminar nodo de decision");
                    }

                    combinePotentialsToRemoveDecisionNode((FiniteStates) x, pt);
                }
            }

            if (generateStatistics == true) {
                // Store the size of the diagram at this moment
                totalSize = currentRelations.sumSizes();

                // Gets the max size for the operation
                double maxSize = operations.getMaxSize();

                if (maxSize < totalSize) {
                    maxSize = totalSize;
                }

                if (generateDebugInfo == true) {
                    System.out.println("Total size: " + maxSize);
                }
                statistics.addSize(maxSize);
                statistics.addTime(crono.getTime());
            }
        } // end for

        // Finally prints the data about the evaluation
        if (generateStatistics == true) {
            // Sets the final ammount of stored numbers
            totalSize = currentRelations.sumSizes();
            statistics.addSize(totalSize);

            // Sets the number of milliseconds needed to evaluate
            statistics.setTime(crono.getTime());

            // Generate the file with the statistics measures
            try {
                if (generateStatistics) {
                    statistics.printOperationsAndSizes();
                }
            } catch (IOException e) {
            }
            ;
        }

        //Althoug generateStatistics can be false, the final expected utility must be
        //stored in order to be showed in the inference panel.
        for (i = 0; i < currentRelations.size(); i++) {
            valueRel = (Relation) currentRelations.elementAt(i);
            if (valueRel.getKind() == Relation.UTILITY) {
                Potential meu = transformMEU(valueRel.getValues());
                statistics.setFinalExpectedUtility(meu);
                break;
            }
        }

        // View the time needed to solve the IDiagram
        crono.viewTime();

        if (generateDebugInfo) {
            System.out.println("IDVariableElimination:  getPosteriorDistributionsID ----- END");
        }

    } // end method

    /**
     * Carries out a propagation storing the results in
     * <code>results</code>.
     */
    @Override
    public void propagate() {


        if (generateDebugInfo) {
            System.out.println("IDVariableElimination:  propagate ----- BEGIN");
        }

        if (network.getClass() == IDiagram.class) {
            // First at all, check if the diagram is evaluable. If this condition
            // is not checked, call to initialConditions       
            if (evaluable == EvaluableStates.NOCHECKED) {
                // It is needed a call to initialConditions
                initialConditions();

                // If the result is negative, print the message and return
                if (evaluable == EvaluableStates.NOEVALUABLE) {
                    System.out.println("The influence diagram can not be solved with this method");
                    return;
                }
            }

            currentRelations.changeKind(Relation.CONDITIONAL_PROB, Relation.POTENTIAL);
            currentRelations.setHeadsAndTails(Relation.POTENTIAL);

            Potential.setStatistics(statistics);
            Potential.setQualitativeOps(qualitativeEval);

            // If this point is reached, call the method for computing the
            // posterior distributions
            getPosteriorDistributionsID();
        } else {
            System.out.print("Error in VariableElimination.propagate(): ");
            System.out.println("this propagation method is not implemented for " + network.getClass());
            System.exit(1);
        }

        if (generateDebugInfo) {
            System.out.println("IDVariableElimination:  propagate ----- END");
        }
    }

    /**
     * Carries out a propagation saving the results in
     * <code>OutputFile</code>.
     *
     * @param outputFile the file where the exact results will be stored.
     */
    public void propagate(String outputFile) throws ParseException, IOException {
        if (generateDebugInfo) {
            System.out.println("IDVariableElimination:  propagate(String) ----- BEGIN");
        }
        propagate();
        saveResults(outputFile);
        if (generateDebugInfo) {
            System.out.println("IDVariableElimination:  propagate ----- END");
        }
    }

    /**
     * Makes a propagation on an ID, and gets a configuration as the evidence
     * for the propagation.
     *
     * @param <code>Configuration</code> Evidence for the propagation
     *
     */
    public void propagate(Configuration configuration) {
        if (generateDebugInfo) {
            System.out.println("IDVariableElimination:  propagate(Configuration) ----- BEGIN");
        }

        if (network.getClass() != IDiagram.class) {
            System.out.println("Error in VariableElimination.propagate(Configuration): This method is only for influence diagrams");
            System.exit(0);
        }

        // Make an evidence object from configuration, and store it
        // as observations

        observations = new Evidence(configuration);

        // If everything is OK

        obtainInterest();
        getPosteriorDistributionsID();
        if (generateDebugInfo) {
            System.out.println("IDVariableElimination:  propagate ----- END");
        }
    }

    /**
     * Method to make the operations requiered to remove a chance node
     *
     * @param node Node to remove
     * @param pt PairTable to store the relations of the nodes
     * @return
     * <code>boolean</code> to show if the operation was made
     */
    protected void combinePotentialsToRemoveChanceNode(FiniteStates node, PairTable pt) {


        if (conditioning) {
            combinePotentialsToRemoveChanceNodeConditioning(node, pt);
        } else {
            combinePotentialsToRemoveChanceNodeStandard(node, pt);
        }
    }

    /**
     * Method to make the operations requiered to remove a chance node
     *
     * @param node Node to remove
     * @param pt PairTable to store the relations of the nodes
     * @return
     * <code>boolean</code> to show if the operation was made
     */
    protected void combinePotentialsToRemoveChanceNodeStandard(FiniteStates node, PairTable pt) {
        Potential potC;
        Potential potU;
        double potCSize = 0, potUSize = 0;
        Flag unaryOperation = new Flag();


        if (generateDebugInfo) {
            System.out.println("IDVariableElimination:  combinePotentialsToRemoveChanceNode ----- BEGIN");
        }

        // First at all, combine the probability potentials related to this
        // node


        if (greedyCombination) {
            potC = combineProbabilityPotentialsGreedy(node, pt, unaryOperation);
        } else {
            potC = combineProbabilityPotentials(node, pt, unaryOperation);
        }



        boolean unity = false;
        if (potC != null && checkUnity) {
            unity = Potential.generatesUnityPot(potC, node);


        }



        if (potC != null && generateStatistics) {



            if (generateDebugInfo == true) {
                System.out.println("Potencial resultante de combinacion de probs");
                potC.printDomain();
                System.out.println("--------------------------------------------");
                if (unaryOperation.getValue() == false) {
                    potCSize = potC.getSize();
                }
                operations.addSize(totalSize + potCSize);


            }
        }

        // Combine the utility potentials related to node
        unaryOperation.setValue(false);

        if (greedyCombination) {
            potU = combineUtilityPotentialsGreedy(node, pt, unaryOperation);
        } else {
            potU = combineUtilityPotentials(node, pt, unaryOperation);

        }

        if (potU != null && generateStatistics) {
            if (unaryOperation.getValue() == false) {
                potUSize = potU.getSize();
            }
            operations.addSize(totalSize + potUSize + potCSize);



        }


        // Combine both of them
        if (potC != null && potU != null) {
            //potU = combine(potC, potU);
            potU = combine(potU, potC);

            if (generateStatistics) {
                statistics.addNumMultiplications(binaryOperationCost(potU));
            }



            if (generateDebugInfo == true) {
                System.out.println("Combinados ambos potenciales......");
                potU.printDomain();
                System.out.println("----------------------------------------");
            }
        }





        if (generateStatistics) {
            long sizeU = 0, numNodesU = 0;
            if (potU != null) {
                sizeU = potU.getSize();
                numNodesU = potU.getNumberOfNodes();
            }
            long sizeC = 0, numNodesC = 0;
            if (potC != null) {
                sizeC = potC.getSize();
                numNodesC = potC.getNumberOfNodes();
            }
            statistics.addNumberNodes(getCurrentRelations().sumNumNodes() + numNodesC + numNodesU);
            statistics.addTotalSize(getCurrentRelations().sumSizes() + sizeC + sizeU);
            statistics.addCurrentTime();
            statistics.addVariableRemoved(node);
            statistics.addOpSize(sizeC + sizeU);

        }





        if (unity) {
            potC = null;
            if (generateStatistics) {
                statistics.addNumProbBarren(1);
            }
            if (generateDebugInfo) {
                System.out.println("Se va a generar un potencial unidad... no se realiza la marginalización");
            }
        }
        // Remove from them the node itself
        if (potC != null) {

            // If the potential is marginal or conditional on the variable to
            // remove the result of the operation will be an unity potential
            // and is not needed to apply such operation

            if (generateDebugInfo == true) {
                System.out.println("Needed to remove variable with sum-marginalization");
                potC.printDomain();
                System.out.println("--------------------------------------------------");
            }

            if (generateStatistics) {
                statistics.addSumMarg(unaryOperationCost(potC, node));
            }


            potC = addVariable(potC, node);
            transformAfterEliminating(potC);



            if (generateDebugInfo == true) {
                if (potC == null) {
                    System.out.println("Se trata de un potencial unidad y se descarta....");
                } else {
                    potC.printDomain();
                    System.out.println("----------------------------------------");
                }
            }
        }

        if (potU != null) {

            if (generateStatistics) {
                statistics.addSumMarg(unaryOperationCost(potU, node));
            }


            potU = addVariable(potU, node);

            if (generateDebugInfo == true) {
                System.out.println("Se elimina en suma la variable a eliminar de potU " + node.getName());
                potU.printDomain();
                System.out.println("----------------------------------------");
            }




        }







        // Finally, divide them to get the final utility
        if (potC != null && potU != null) {

            potU = divide(potU, potC);
            if (generateStatistics) {
                statistics.addNumDivisions(binaryOperationCost(potU));
            }


            if (generateDebugInfo == true) {
                System.out.println("Se han dividido los potenciales: ");
                potU.printDomain();
                System.out.println("----------------------------------------");
            }
        }

        /*
         * if(generateStatistics) { long sizeU = 0; if(potU != null) sizeU =
         * potU.getSize(); long sizeC = 0; if(potC != null) sizeC =
         * potC.getSize();
         * statistics.addTotalSize(getCurrentRelations().sumSizes()+sizeC+sizeU);
         * // statistics.addTotalSize(sizeC+sizeU); statistics.addCurrentTime();
         *
         * }
         */




        //Prune the potentials
        if (potC != null) {
            potC = transformAfterOperation(potC, false);
        }
        if (potU != null) {
            potU = transformAfterOperation(potU, true);
        }

        //Statistics: size of all potentials
        if (generateStatistics) {
            long sizeU = 0, numNodesU = 0;
            if (potU != null) {
                sizeU = potU.getSize();
                numNodesU = potU.getNumberOfNodes();
            }
            long sizeC = 0, numNodesC = 0;
            if (potC != null) {
                sizeC = potC.getSize();
                numNodesC = potC.getNumberOfNodes();
            }
            statistics.addNumberNodes(getCurrentRelations().sumNumNodes() + numNodesC + numNodesU);
            statistics.addTotalSize(getCurrentRelations().sumSizes() + sizeC + sizeU);
            statistics.addCurrentTime();
            statistics.addOpSize(sizeC + sizeU);

        }




        // We have to make relations for the new potentials
        if (potC != null) {
            makeRelationFromPotential(potC, pt, Relation.POTENTIAL);
        }

        if (potU != null) {
            makeRelationFromPotential(potU, pt, Relation.UTILITY);
        }

        if (generateDebugInfo) {
            System.out.println("IDVariableElimination:  combinePotentialsToRemoveChanceNode ----- END");
        }
    }

    /**
     * Method to make the operations requiered to remove a chance node
     *
     * @param node Node to remove
     * @param pt PairTable to store the relations of the nodes
     * @return
     * <code>boolean</code> to show if the operation was made
     */
    protected void combinePotentialsToRemoveChanceNodeConditioning(FiniteStates node, PairTable pt) {
        Potential potC;
        Potential potU;
        double potCSize = 0, potUSize = 0;
        Flag unaryOperation = new Flag();


        /*
         * Combine relevant Probability potentials
         */
        if (generateDebugInfo) {
            System.out.println("IDVariableElimination:  combinePotentialsToRemoveChanceNode ----- BEGIN");
        }
        // First at all, combine the probability potentials related to this
        // node
        if (greedyCombination) {
            potC = combineProbabilityPotentialsGreedy(node, pt, unaryOperation);

        } else {

            potC = combineProbabilityPotentials(node, pt, unaryOperation);
        }
        
        
        
        boolean unity = false;
        if (potC != null && checkUnity) {
            unity = Potential.generatesUnityPot(potC, node);
        }
        if (potC != null && generateStatistics) {
            if (generateDebugInfo == true) {
                System.out.println("Potencial resultante de combinacion de probs");
                potC.printDomain();
                System.out.println("--------------------------------------------");
                if (unaryOperation.getValue() == false) {
                    potCSize = potC.getSize();
                }
                operations.addSize(totalSize + potCSize);
            }
        }

        /*
         * Conditioning on the rest of variables
         */

        Potential potCond = null;
        if (potC != null) {


            potCond = potC.copy();
            potCond.normalizeOver(node);

        }


        // Combine the utility potentials related to node
        unaryOperation.setValue(false);


        /*
         * Combine relevant Probability potentials
         */
        if (greedyCombination) {
            potU = combineUtilityPotentialsGreedy(node, pt, unaryOperation);
        } else {
            potU = combineUtilityPotentials(node, pt, unaryOperation);
        }
        if (potU != null && generateStatistics) {
            if (unaryOperation.getValue() == false) {
                potUSize = potU.getSize();
            }
            operations.addSize(totalSize + potUSize + potCSize);
        }


        /* potCond.print();
         * Removal from the probabilities
         */
        if (unity) {
            potC = null;
            if (generateStatistics) {
                statistics.addNumProbBarren(1);
            }
            if (generateDebugInfo) {
                System.out.println("Se va a generar un potencial unidad... no se realiza la marginalización");
            }
        }
        // Remove from them the node itself
        if (potC != null) {

            if (generateDebugInfo == true) {
                System.out.println("Needed to remove variable with sum-marginalization");
                potC.printDomain();
                System.out.println("--------------------------------------------------");
            }

            if (generateStatistics) {
                statistics.addSumMarg(unaryOperationCost(potC, node));
            }


            potC = addVariable(potC, node);
            transformAfterEliminating(potC);




            if (generateDebugInfo == true) {
                if (potC == null) {
                    System.out.println("Se trata de un potencial unidad y se descarta....");
                } else {
                    potC.printDomain();
                    System.out.println("----------------------------------------");
                }
            }
        }




        /*
         * Removal from the utilities
         */
        if (potU != null) {

            if (generateStatistics) {
                statistics.addSumMarg(unaryOperationCost(potU, node));
            }

            if (potCond != null) {
                
                if(! (this instanceof IDVEWithPotentialIntervalTable)) {
                    potU = potCond.combine(potU);
         
                }else {
                    
                    potU = ((PotentialIntervalTable)potCond).combineNoCheckSign(potU);
                }
                
                
                

            } // potU.print();

            potU = addVariable(potU, node);



            if (generateDebugInfo == true) {
                System.out.println("Se elimina en suma la variable a eliminar de potU " + node.getName());
                potU.printDomain();
                System.out.println("----------------------------------------");
            }




        }



//potU.print();

        //Prune the potentials
        if (potC != null) {
            potC = transformAfterOperation(potC, false);

        }
        if (potU != null) {
            potU = transformAfterOperation(potU, true);
        }

        //Statistics: size of all potentials
        if (generateStatistics) {
            long sizeU = 0, numNodesU = 0;
            if (potU != null) {
                sizeU = potU.getSize();
                numNodesU = potU.getNumberOfNodes();
            }
            long sizeC = 0, numNodesC = 0;
            if (potC != null) {
                sizeC = potC.getSize();
                numNodesC = potC.getNumberOfNodes();
            }
            statistics.addNumberNodes(getCurrentRelations().sumNumNodes() + numNodesC + numNodesU);
            statistics.addTotalSize(getCurrentRelations().sumSizes() + sizeC + sizeU);
            statistics.addCurrentTime();
            statistics.addOpSize(sizeC + sizeU);

        }




        // We have to make relations for the new potentials
        if (potC != null) {

            makeRelationFromPotential(potC, pt, Relation.POTENTIAL);
        }

        if (potU != null) {
            makeRelationFromPotential(potU, pt, Relation.UTILITY);

        }

        if (generateDebugInfo) {
            System.out.println("IDVariableElimination:  combinePotentialsToRemoveChanceNode ----- END");
        }
    }

    /**
     * Method to make the operations requiered to remove a decision node
     *
     * @param node Node to remove
     * @param pt PairTable to store the relations of the nodes
     * @return
     * <code>boolean</code> to show if the operation was made
     */
    private void combinePotentialsToRemoveDecisionNode(FiniteStates node, PairTable pt) {
        Potential potC = null;
        Potential potU = null;
        Potential eU;
        Vector vars = null;
        double potCSize = 0, potUSize = 0;
        Flag unaryOperation = new Flag();






        if (generateDebugInfo) {
            System.out.println("IDVariableElimination:  combinePotentialsToRemoveDecisionNode ----- BEGIN");
        }

        // First at all, combine the probability potentials related to this
        // node


        if (simpleDecisionRemoval) {
            directRemovalFromProbabilities(node, pt, unaryOperation);
        }

        if (greedyCombination) {
            potC = combineProbabilityPotentialsGreedy(node, pt, unaryOperation);
        } else {
            potC = combineProbabilityPotentials(node, pt, unaryOperation);
        }


        boolean unity = false;
        if (potC != null && checkUnity) {
            unity = Potential.generatesUnityPot(potC, node);
        }





        if (potC != null && generateStatistics) {
            if (unaryOperation.getValue() == false) {
                potCSize = potC.getSize();
            }
            operations.addSize(totalSize + potCSize);
        }

        // Combine the utility potentials related to node
        unaryOperation.setValue(false);
        if (greedyCombination) {
            potU = combineUtilityPotentialsGreedy(node, pt, unaryOperation);
        } else {
            potU = combineUtilityPotentials(node, pt, unaryOperation);
        }
        if (potU != null && generateStatistics) {
            if (unaryOperation.getValue() == false) {
                potUSize = potU.getSize();
            }
            operations.addSize(totalSize + potUSize + potCSize);
        }



        // Combine both of them

        if (potC != null && potU != null) {
            //   potU = combine(potC, potU);
            potU = combine(potU, potC);
            if (generateStatistics) {
                potUSize = potU.getSize();
                operations.addSize(totalSize + potUSize + potCSize);
                statistics.addNumMultiplications(binaryOperationCost(potU));

            }

        }



        if (generateStatistics) {
            long sizeU = 0, numNodesU = 0;
            if (potU != null) {
                sizeU = potU.getSize();
                numNodesU = potU.getNumberOfNodes();
            }
            long sizeC = 0, numNodesC = 0;
            if (potC != null) {
                sizeC = potC.getSize();
                numNodesC = potC.getNumberOfNodes();
            }
            statistics.addNumberNodes(getCurrentRelations().sumNumNodes() + numNodesC + numNodesU);
            statistics.addTotalSize(getCurrentRelations().sumSizes() + sizeC + sizeU);
            statistics.addCurrentTime();
            statistics.addVariableRemoved(node);
            statistics.addOpSize(sizeC + sizeU);

        }



        if (unity) {
            potC = null;
            if (generateDebugInfo) {
                System.out.println("Se va a generar un potencial unidad");
            }
        }
        // Remove from them the node itself, maxMarginalizing
        if (potC != null) {


            if (generateStatistics) {
                statistics.addMaxMarg(unaryOperationCost(potC, node));

            }


            potC = maxVariable(potC, node);
        }





        // divide them to get the final utility
        if (potC != null && potU != null) {


            potU = divide(potU, potC);
            if (generateStatistics) {
                statistics.addNumDivisions(binaryOperationCost(potU));
            }

            if (generateDebugInfo == true) {
                System.out.println("Se han dividido los potenciales: ");
                potU.printDomain();
                System.out.println("----------------------------------------");
            }
        }



        if (potU != null) {


            if (potU instanceof PotentialIntervalTable) {
                ((PotentialIntervalTable) potU).fixBounds(false);
            }

            // Before removing the node itself, store the decision
            // table, making the requiered operations for that
            eU = getExpectedUtility(node, potU);
            //   eU.print();

            if (saveAsTable) {
                if ((eU instanceof PotentialIntervalTable)) {
                    PotentialIntervalTable eUTable = (PotentialIntervalTable) eU.sendVarToEnd(node);
                    results.add(eUTable);
                } else if ((eU instanceof PotentialIBPTWT)) {
                    PotentialIntervalTable eUTable = ((PotentialIBPTWT) eU).getIntervalTable();
                    eUTable = (PotentialIntervalTable) eUTable.sendVarToEnd(node);
                    results.add(eUTable);


                } else {
                    PotentialTable eUTable = new PotentialTable(eU);
                    eUTable = (PotentialTable) eUTable.sendVarToEnd(node);
                    results.addElement(eUTable);


                }
            } else {
                results.addElement(eU);
            }



            if (generateStatistics == true) {
                //For this table, display the statistics about the relative
                //importance of the variables
                //  statistics.setExplanation(node.getName(), eU);
                this.statistics.addUtilityHistory(eU);


                if (eU instanceof PotentialTree) {
                    statistics.addDecSize(((PotentialTree) eU).getSize());
                }

                if (eU instanceof PotentialBPTree) {
                    statistics.addDecSize(((PotentialBPTree) eU).getSize());
                }

            }




            vars = new Vector(potU.getVariables());
            vars.removeElement(node);

            if (generateStatistics) {
                statistics.addMaxMarg(unaryOperationCost(potU, node));
            }
            Hashtable<Configuration, Vector<Integer>> polDi = potU.argMax(node);
            strategy.add(polDi);
            potU = potU.maxMarginalizePotential(vars);

        }




        //Prune the potentials
        if (potC != null) {
            potC = transformAfterOperation(potC, false);
        }
        if (potU != null) {
            potU = transformAfterOperation(potU, true);
        }

        if (generateStatistics) {
            long sizeU = 0, numNodesU = 0;
            if (potU != null) {
                sizeU = potU.getSize();
                numNodesU = potU.getNumberOfNodes();
            }
            long sizeC = 0, numNodesC = 0;
            if (potC != null) {
                sizeC = potC.getSize();
                numNodesC = potC.getNumberOfNodes();
            }
            statistics.addNumberNodes(getCurrentRelations().sumNumNodes() + numNodesC + numNodesU);
            statistics.addTotalSize(getCurrentRelations().sumSizes() + sizeC + sizeU);
            statistics.addCurrentTime();
            statistics.addOpSize(sizeC + sizeU);

        }

        // We have to make relations for the new potentials
        if (potC != null) {


            // May be there is only a variable on it. In this case
            // do not include a relation for this case
            if (potC.getVariables().size() > 1) {
                makeRelationFromPotential(potC, pt, Relation.POTENTIAL);
            }
        }

        if (potU != null) {
            makeRelationFromPotential(potU, pt, Relation.UTILITY);
        }

        if (generateDebugInfo) {
            System.out.println("IDVariableElimination:  combinePotentialsToRemoveDecisionNode ----- END");
        }
    }

    /**
     * Private method to combine the potentials of probability related to a
     * given node
     *
     * @param node Node to consider
     * @param pt PairTable containing the relations for every node
     * @param flag to show if there is only a potential involved
     * @return the final potential after combination
     */
    protected Potential combineProbabilityPotentialsGreedy(FiniteStates node, PairTable pt, Flag flag) {
        RelationList relations = new RelationList();
        Potential pot;
        Relation relation;
        int i;

        if (generateDebugInfo) {
            System.out.println("IDVariableElimination:  combineProbabilityPotentials ----- BEGIN");
        }

        // Get the relations related to node
        if (currentRelations != null) {
            relations = currentRelations.getRelationsOfAndRemove(node, Relation.POTENTIAL);
            if (relations.size() == 1) {
                flag.setValue(true);
            }
        }




        ArrayList<Potential> pots = new ArrayList<Potential>();

        // Consider all the probability potentials where node
        // takes part 
        for (i = 0; i < relations.size(); i++) {
            // Get the relation
            relation = relations.elementAt(i);
            // Consider it if it is not an utility potential
            if (relation.getKind() != Relation.UTILITY) {
                // Combine the potential

                pots.add(relation.getValues());
                // Remove this relation from pt
                pt.removeRelation(relation);
            }
        }


        CombPairTable B = new CombPairTable();
        B.setAllowSingletons(false);
        B.setWithoutRemovable();
        B.setSimetric(simetric);



        while (pots.size() > 1) {

            startMeasureOverHead();
            B.addCombinations(pots);
            CombPair p = B.netxToCombine(combinationHeuristic);
            stopMeasureOverHead();

            Potential prob_ij = p.getPot1().combine(p.getPot2());


            if (generateStatistics) {
                statistics.addNumMultiplications(binaryOperationCost(prob_ij));
            }

            if (p.getPot1().getHead() != null) {
                prob_ij.setHead(Potential.getCombinationHead(p.getPot1(), p.getPot2()));
                prob_ij.setTail(Potential.getCombinationTail(p.getPot1(), p.getPot2()));


            }



            transformAfterBinaryComb(prob_ij);




            // Now, check the possible presence of constraints on this relation
            if (this instanceof IDVEWBPTAndConstraints) {

                if (((IDVEWBPTAndConstraints) this).isCombinationConstraints()) {
                    PotentialBPTree potTreeConstraint = (PotentialBPTree) ((IDiagram) network).applyConstraintsOnPotential(prob_ij, false);

                    //Discards the resulting tree if it is larger
                    if (potTreeConstraint.getSize() < prob_ij.getSize()) {
                        prob_ij = potTreeConstraint;
                    }
                }
            } else if (this instanceof IDVEWPTAndConstraints) {

                if (((IDVEWPTAndConstraints) this).isCombinationConstraints()) {
                    PotentialTree potTreeConstraint = (PotentialTree) ((IDiagram) network).applyConstraintsOnPotential(prob_ij, false);

                    //Discards the resulting tree if it is larger
                    if (potTreeConstraint.getSize() < prob_ij.getSize()) {
                        prob_ij = potTreeConstraint;
                    }
                }
            }


            //Update
            pots.remove(p.getPot1());
            pots.remove(p.getPot2());
            pots.add(prob_ij);
            startMeasureOverHead();
            B.removeContainig(p.getPot1());
            B.removeContainig(p.getPot2());
            stopMeasureOverHead();

        }


        if (generateDebugInfo) {
            System.out.println("IDVariableElimination:  combineProbabilitypotentials ----- END");
        }

        pot = null;
        if (pots.size() > 0) {
            pot = pots.get(0);
        }

        transformAfterCombining(pot);

        // Return the potential
        return pot;
    }

    /**
     * Private method to combine the potentials of probability related to a
     * given node
     *
     * @param node Node to consider
     * @param pt PairTable containing the relations for every node
     * @param flag to show if there is only a potential involved
     * @return the final potential after combination
     */
    protected Potential combineProbabilityPotentials(FiniteStates node, PairTable pt, Flag flag) {
        RelationList relations = new RelationList();
        Potential pot = null;
        Relation relation;
        int i;

        if (generateDebugInfo) {
            System.out.println("IDVariableElimination:  combineProbabilityPotentials ----- BEGIN");
        }

        // Get the relations related to node
        if (currentRelations != null) {
            relations = currentRelations.getRelationsOfAndRemove(node, Relation.POTENTIAL);
            if (relations.size() == 1) {
                flag.setValue(true);
            }
        }

        // Consider all the probability potentials where node
        // takes part and combine them
        //       for (i = 0; i < relations.size(); i++) {
        for (i = relations.size() - 1; i >= 0; i--) {
            // Get the relation
            relation = relations.elementAt(i);
            // Consider it if it is not an utility potential
            if (relation.getKind() != Relation.UTILITY) {


                Vector combinationHead = null, combinationTail = null;

                if (relation.getValues().getHead() != null) {
                    combinationHead = Potential.getCombinationHead(pot, relation.getValues());
                    combinationTail = Potential.getCombinationTail(pot, relation.getValues());
                }



                // Combine the potential
                pot = combinePotentials(pot, relation);



                //Updates the head and the tail
                // 
                if (combinationHead != null) {
                    pot.setHead(combinationHead);
                    pot.setTail(combinationTail);
                }



                // Now, check the possible presence of constraints on this relation
                if (this instanceof IDVEWBPTAndConstraints) {

                    if (((IDVEWBPTAndConstraints) this).isCombinationConstraints()) {
                        PotentialBPTree potTreeConstraint = (PotentialBPTree) ((IDiagram) network).applyConstraintsOnPotential(pot, false);

                        //Discards the resulting tree if it is larger
                        if (potTreeConstraint.getSize() < pot.getSize()) {
                            pot = potTreeConstraint;
                        }
                    }
                } else if (this instanceof IDVEWPTAndConstraints) {

                    if (((IDVEWPTAndConstraints) this).isCombinationConstraints()) {
                        PotentialTree potTreeConstraint = (PotentialTree) ((IDiagram) network).applyConstraintsOnPotential(pot, false);

                        //Discards the resulting tree if it is larger
                        if (potTreeConstraint.getSize() < pot.getSize()) {
                            pot = potTreeConstraint;
                        }
                    }
                }





                if (generateStatistics) {
                    statistics.addNumMultiplications(binaryOperationCost(pot));
                }

                // Remove this relation from pt
                pt.removeRelation(relation);
            }
        }

        if (generateDebugInfo) {
            System.out.println("IDVariableElimination:  combineProbabilitypotentials ----- END");
        }

        // Return the potential
        return pot;
    }

    /**
     *
     * @param node Node to consider
     * @param pt PairTable containing the relations for every node
     * @param flag to show if there is only a potential involved
     * @return the final potential after combination
     */
    private void directRemovalFromProbabilities(FiniteStates node, PairTable pt, Flag flag) {
        RelationList relations = new RelationList();
        Relation relation;
        int i;

        if (generateDebugInfo) {
            System.out.println("IDVariableElimination:  directRemovalFromProbabilities ----- BEGIN");
        }

        // Get the relations related to node
        if (currentRelations != null) {
            relations = currentRelations.getRelationsOfAndRemove(node, Relation.POTENTIAL);
            if (relations.size() == 1) {
                flag.setValue(true);
            }
        }

        // Consider all the probability potentials where node
        // takes part and combine them
        for (i = 0; i < relations.size(); i++) {
            // Get the relation
            relation = relations.elementAt(i);
            // Consider it if it is not an utility potential
            if (relation.getKind() != Relation.UTILITY) {



                // Remove this relation from pt
                pt.removeRelation(relation);
                Potential pot = relation.getValues();


                if (generateDebugInfo) {
                    System.out.println("Probability potential with decision " + node + ": " + pot.toString());
                }

                //Reestrict to any value of the decision                
                NodeList vars = pot.getNodeList();
                vars.removeNode(node);
                Configuration conf = new Configuration();
                conf.insert((FiniteStates) node, 0);
                pot = pot.restrictVariable(conf);


                if (relation.getValues().getHead() != null) {
                    pot.setHead(Potential.getMargHead(relation.getValues(), node));
                    pot.setTail(Potential.getMargTail(relation.getValues(), node));
                }

                boolean savePot = true;

                //Updates the head and the tail
                if (pot.getHead() != null) {
                    pot.setHead(Potential.getMargHead(pot, node));
                    pot.setTail(Potential.getMargTail(pot, node));
                    if (relation.getValues().getHead().size() == 0) {
                        savePot = false;
                    }

                }

                if (savePot) {
                    transformAfterEliminating(pot);

                    //Save into the relation list
                    makeRelationFromPotential(pot, pt, Relation.POTENTIAL);
                }




            }
        }

        if (generateDebugInfo) {
            System.out.println("IDVariableElimination:  directRemovalFromProbabilities ----- END");
        }


    }

    /**
     * Private method to combine the potentials of probability related to a
     * given node
     *
     * @param node Node to consider
     * @param pt PairTable containing the relations for every node
     * @param flag to show if there is only a potential involved
     * @return the final potential after combination
     */
    protected Potential combineUtilityPotentialsGreedy(FiniteStates node, PairTable pt, Flag flag) {
        RelationList relations = new RelationList();
        Potential pot = null;
        Relation relation;
        int i;

        if (generateDebugInfo) {
            System.out.println("IDVariableElimination:  combineUtilityPotentialsGreedy ----- BEGIN");
        }

        // Get the relations related to node
        if (currentRelations != null) {
            relations = currentRelations.getRelationsOfAndRemove(node, Relation.UTILITY);
            if (relations.size() == 1) {
                flag.setValue(true);
            }
        }

        ArrayList<Potential> pots = new ArrayList<Potential>();

        // Consider all the probability potentials where node
        // takes part 
        for (i = 0; i < relations.size(); i++) {
            // Get the relation
            relation = relations.elementAt(i);
            // Consider it if it is not an utility potential
            if (relation.getKind() == Relation.UTILITY) {
                // Combine the potential

                pots.add(relation.getValues());
                // Remove this relation from pt
                pt.removeRelation(relation);
            }
        }



        CombPairTable B = new CombPairTable();
        B.setAllowSingletons(false);
        B.setWithoutRemovable();



        while (pots.size() > 1) {
            startMeasureOverHead();
            B.addCombinations(pots);
            CombPair p = B.netxToCombine(combinationHeuristic);
            Potential prob_ij = p.getPot1().addition(p.getPot2());
            startMeasureOverHead();

            if (generateStatistics) {
                statistics.addNumSums(prob_ij.getSize());
            }


            // Now, check the possible presence of constraints on this relation
            if (this instanceof IDVEWBPTAndConstraints) {

                if (((IDVEWBPTAndConstraints) this).isCombinationConstraints()) {
                    PotentialBPTree potTreeConstraint = (PotentialBPTree) ((IDiagram) network).applyConstraintsOnPotential(pot, true);

                    //Discards the resulting tree if it is larger
                    if (potTreeConstraint.getSize() < pot.getSize()) {
                        pot = potTreeConstraint;
                    }
                }
            } else if (this instanceof IDVEWPTAndConstraints) {

                if (((IDVEWPTAndConstraints) this).isCombinationConstraints()) {
                    PotentialTree potTreeConstraint = (PotentialTree) ((IDiagram) network).applyConstraintsOnPotential(pot, true);

                    //Discards the resulting tree if it is larger
                    if (potTreeConstraint.getSize() < pot.getSize()) {
                        pot = potTreeConstraint;
                    }
                }
            }

            //Update
            pots.remove(p.getPot1());
            pots.remove(p.getPot2());
            pots.add(prob_ij);
            startMeasureOverHead();
            B.removeContainig(p.getPot1());
            B.removeContainig(p.getPot2());
            stopMeasureOverHead();

        }


        if (generateDebugInfo) {
            System.out.println("IDVariableElimination:  combineUtilityPotentialsGreedy ----- END");
        }


        pot = null;
        if (pots.size() > 0) {
            pot = pots.get(0);
        }

        // Return the potential
        return pot;
    }

    /**
     * Private method to combine the potentials of utility related to a given
     * node
     *
     * @param node Node to consider
     * @param pt PairTable containing the relations for every node
     * @param flag to show if there is only a potential involved
     * @return the final potential after combination
     */
    protected Potential combineUtilityPotentials(Node node, PairTable pt, Flag flag) {
        RelationList relations = new RelationList();
        Potential pot = null;
        Relation relation;
        int i;

        if (generateDebugInfo) {
            System.out.println("IDVariableElimination:  combineUtilityPotentials ----- BEGIN");
        }

        // Get the relations related to node
        if (currentRelations != null) {
            relations = currentRelations.getRelationsOfAndRemove(node, Relation.UTILITY);
            if (relations.size() == 1) {
                flag.setValue(true);
            }
        }

        // Consider all the probability potentials where node
        // takes part and combine them
        for (i = 0; i < relations.size(); i++) {



            // Get the relation
            relation = relations.elementAt(i);



            // Consider it if it is not an utility potential
            if (relation.getKind() == Relation.UTILITY) {


                if (generateDebugInfo == true) {
                    System.out.println("Potential de utilidad: ");
                    relation.printDomain();
                    System.out.println("---------------------------------------");
                }

                // Combine the potential
                pot = addPotentials(pot, relation);


                // Now, check the possible presence of constraints on this relation
                if (this instanceof IDVEWBPTAndConstraints) {

                    if (((IDVEWBPTAndConstraints) this).isCombinationConstraints()) {
                        PotentialBPTree potTreeConstraint = (PotentialBPTree) ((IDiagram) network).applyConstraintsOnPotential(pot, true);

                        //Discards the resulting tree if it is larger
                        if (potTreeConstraint.getSize() < pot.getSize()) {
                            pot = potTreeConstraint;
                        }
                    }
                } else if (this instanceof IDVEWPTAndConstraints) {

                    if (((IDVEWPTAndConstraints) this).isCombinationConstraints()) {
                        PotentialTree potTreeConstraint = (PotentialTree) ((IDiagram) network).applyConstraintsOnPotential(pot, true);

                        //Discards the resulting tree if it is larger
                        if (potTreeConstraint.getSize() < pot.getSize()) {
                            pot = potTreeConstraint;
                        }
                    }
                }



                if (generateDebugInfo == true) {
                    if (pot != null) {
                        System.out.println("Combinacion de potenciales hasta el momento:");
                        pot.printDomain();
                        System.out.println("-------------------------------------");
                    } else {
                        System.out.println("Potencial nulo");
                    }
                }

                // Remove this relation from pt
                pt.removeRelation(relation);
            }
        }

        if (generateDebugInfo == true) {
            if (pot != null) {
                System.out.println("El potential final a devolver es:");
                pot.printDomain();
                System.out.println("-------------------------------------");
            } else {
                System.out.println("Potencial nulo");
            }
            System.out.println("IDVariableElimination:  combineUtilitypotentials ----- END");
        }

        // Return the potential
        return pot;
    }

    /**
     * Method to add two potentials: once passed as argument and other from a
     * relation. If any of them is null will be returned the other
     *
     * @param <code>Potential</code> potential
     * @param <code>Relation</code> relation, which potential wish to add to
     * potential
     * @return
     * <code>Potential</code> final potential
     */
    private Potential addPotentials(Potential pot, Relation rel) {
        Potential finalPotential;

        if (generateDebugInfo) {
            System.out.println("IDVariableElimination:  addPotentials ----- BEGIN");
        }

        if (pot == null) {
            finalPotential = rel.getValues();
        } else {
            finalPotential = addition(pot, rel.getValues());
            if (generateStatistics) {
                statistics.addNumSums(finalPotential.getSize());
            }
        }

        if (generateDebugInfo) {
            System.out.println("IDVariableElimination:  addPotentials ----- END");
        }
        return (finalPotential);
    }

    /**
     * Method to preproccess two potential, looking if both of them share some
     * variable. If not build a new unity potential over the new variables and
     * combine both potentials. When there are variables in common then add them
     *
     * @param <code>Potential</code> potential
     * @param <code>Relation</code> relation, which potential wish to add to
     * potential
     * @return
     * <code>Potential</code> final potential
     */
    private Potential preproccess(Potential pot, Relation rel) {
        NodeList varsInRel = rel.getVariables();
        NodeList varsInPot = new NodeList(pot.getVariables());
        Node node;
        boolean shared = false;
        Potential potNew;
        Potential res;
        Configuration conf;
        int i;

        if (generateDebugInfo) {
            System.out.println("IDVariableElimination:  preprocess ----- BEGIN");
        }

        // Get if there is at least one variable in common
        for (i = 0; i < varsInRel.size(); i++) {
            node = varsInRel.elementAt(i);
            if (varsInPot.getId(node.getName()) != -1) {
                shared = true;
                break;
            }
        }

        // If shared == false, build a new PotentialTree
        potNew = rel.getValues().copy();
        conf = new Configuration(potNew.getVariables());
        for (i = 0; i < conf.possibleValues(); i++) {
            potNew.setValue(conf, 1.0);
            conf.nextConfiguration();
        }

        // Combine both potentials
        res = pot.combine(potNew);

        if (generateDebugInfo) {
            System.out.println("IDVariableElimination:  preprocess ----- END");
        }

        // Return res
        return res;
    }

    /**
     * Method to combine two potentials: one passed as argument and other from a
     * relation. If any of them is null will be returned the other
     *
     * @param <code>Potential</code> potential
     * @param <code>Relation</code> relation, which potential wish to add to
     * potential
     * @return
     * <code>Potential</code> final potential
     */
    private Potential combinePotentials(Potential pot, Relation rel) {
        Potential finalPotential;

        if (generateDebugInfo) {
            System.out.println("IDVariableElimination:  combinePotentials ----- BEGIN");
        }

        if (pot == null) {
            finalPotential = rel.getValues();
            if (generateDebugInfo == true) {
                System.out.println("Unico potencial en relacion........... ");
                finalPotential.printDomain();
                System.out.println("---------------------------------------");
            }
        } else {
            if (generateDebugInfo == true) {
                System.out.println("Combinacion de potenciales............ ");
                pot.printDomain();
                System.out.println("---------------------------------------");
                rel.printDomain();
                System.out.println("---------------------------------------");
            }


            //   if(pot.getSize() < rel.getValues().getSize())
            finalPotential = combine(pot, rel.getValues());
            //   else
            //     finalPotential = combine(rel.getValues(), pot);


            if (generateDebugInfo == true) {
                System.out.println("Potencial producido .................. ");
                finalPotential.printDomain();
                System.out.println("---------------------------------------");
            }
        }

        if (generateDebugInfo) {
            System.out.println("IDVariableElimination:  combinePotentials ----- END");
        }

        return (finalPotential);
    }

    /**
     * Method to create a new relation with the variables of the chance
     * potential. The potential is transformed after the addition
     *
     * @param <code>Potential</code> chance potential
     * @param <code>PairTable</code> pairTable where the relation is added
     * @param kind of relation to create (POTENTIAL or UTILITY)
     * @return
     * <code>Relation</code> relation made from variables in potential
     */
    protected void makeRelationFromPotential(Potential pot, PairTable pair,
            int kind) {
        Relation r = null;
        boolean utility = false;




        if (generateDebugInfo) {
            System.out.println("IDVariableElimination:  makeRelationFromPotential ----- BEGIN");
        }

        // Check the kind of the relation

        if (kind != Relation.POTENTIAL && kind != Relation.UTILITY) {
            System.out.println("Error in VariableElimination.makeRelationFromPotential(Potential, PairTable, int):");
            System.out.println("Invalid kind of relation to be created");
            System.exit(1);
        }

        // Works only for potentials not null

        if (pot != null) {
            if (kind == Relation.UTILITY) {
                utility = true;
            }


            if (!utility && pot.getHead() == null) {
                System.out.println("");
            }

            //Trying to save a unity potential
            if (!utility && pot.getHead() != null && pot.getHead().size() == 0) {
                return;
            }

//((PotentialIntervalTable)pot).fixBounds(!utility);       

            // Transform it
            //pot = transformAfterOperation(pot, utility);

            // Creates the new relation
            r = new Relation();
            r.setKind(kind);
            r.getVariables().setNodes((Vector) pot.getVariables().clone());
            r.setValues(pot);

            currentRelations.insertRelation(r);

            // Add the relation to the pairtable
            pair.addRelation(r);

            if (generateDebugInfo == true) {
                System.out.println("New relation generated..............");
                r.printDomain();
                if (r.getVariables().size() == 0) {
                    System.out.println("Potential without variables ...............");
                    r.getValues().print();
                    System.out.println("-------------------------------------------");
                }
            }
        }

        if (generateDebugInfo) {
            System.out.println("IDVariableElimination:  makeRelationFromPotential ----- END");
        }
    }

    /**
     * Method to compare the policies obtained as a consequence of two
     * evalautions on the same IDiagram
     *
     * @param <code>resultsToCompare</code> evaluation to compare with @result
     * <code>double</code> the distance between policies
     */
    public double comparePolicies(Vector resultsToCompare) {
        Potential result;
        Potential resultToCompare;
        Vector vars;
        Vector varsForConf = new Vector();
        FiniteStates decision;
        Configuration partial, total, totalToCompare;
        double utility, utilityToCompare, max, diff = 0, diffLocal;
        long k;
        long cases;
        int i, j, indMax;

        if (generateDebugInfo) {
            System.out.println("IDVariableElimination:  comparePolicies ----- BEGIN");
        }

        // To compare both policies we must see the expected
        // utility of the proposed strategy for the evaluation
        // respect to the evaluation passed as an argument
        // We will only consider the last table with the global
        // strategy, ANYWAY, ITS IN A LOOP........
        for (i = 0; i < results.size(); i++) {

            // Select the potential related with this own object
            result = (Potential) results.elementAt(i);

            // Select the potential related with the results to be compared
            // with this
            resultToCompare = (Potential) resultsToCompare.elementAt(i);

            // Make a configuration over the whole set of
            // variables except the last one. The last one is related
            // with the decision variable and it must be kept appart
            // to loop over it
            vars = result.getVariables();
            for (j = 0; j < vars.size() - 1; j++) {
                varsForConf.addElement(vars.elementAt(j));
            }

            // Build the configuration
            partial = new Configuration(varsForConf);

            // Build a new configuration with the whole set of variables
            // This configuration will be used to access to the table with
            // the optimal strategy, given a certain configuration
            total = new Configuration(vars);

            // Retrieve the decision to consider
            decision = (FiniteStates) vars.elementAt(j);

            // The decision is used to determine the number od states
            // of the related variable. So is computed the final size
            // of the potential with the optimal strategy, without taking
            // into account the number of states of the decision
            cases = (long) FiniteStates.getSize(vars) / decision.getNumStates();

            // Built a configuration with the variables of the potential
            // passed as argument and that will be compared
            totalToCompare = new Configuration(resultToCompare.getVariables());

            // Once the configuration is done, we must go over
            // all of its values, to retrieve the optimal strategy
            // for it
            diff = 0;
            for (k = 0; k < cases; k++) {

                // Copy the values from partial configuration
                total.resetConfiguration(partial);

                // Get the optimal strategy for each case, in the table
                // related to this object
                max = 0;
                utility = 0;
                for (j = 0, indMax = 0; j < decision.getNumStates(); j++) {
                    total.putValue(decision, j);
                    utility = result.getValue(total);

                    if (j == 0) {
                        max = utility;
                    } else {
                        if (max < utility) {
                            max = utility;
                            indMax = j;
                        }
                    }
                }

                // Set the value for the maximum value in the total
                // configuration

                total.putValue(decision, indMax);

                // Once obtained the maximum for the base table,
                // get this value for the second one

                for (j = 0; j < totalToCompare.size(); j++) {
                    totalToCompare.putValue(total.getVariable(j).getName(), total.getValue(j));
                }

                // Get the expected utility related to this strategy, but in the potential
                // passed as argument

                utilityToCompare = resultToCompare.getValue(totalToCompare);

                // Compute the difference

                diffLocal = max - utilityToCompare;
                ;
                diff += Math.pow(diffLocal, 2);

                // Go to the next configuration

                partial.nextConfiguration();
            }

            // Once finished, divide by the number of cases
            diff = diff / cases;

            // Get square root, as the global diff
            diff = Math.sqrt(diff);

            // As by now we are only interested inf the first
            // table, break the loop
            break;
        }

        if (generateDebugInfo) {
            System.out.println("IDVariableElimination:  comparePolicies ----- END");
        }
        return (diff);
    }

    /**
     * Method to get the expected utility for a decision table
     *
     * @param Node decision node related to the decision table
     * @param Potential utility directly related to the decision
     */
    private Potential getExpectedUtility(Node node, Potential util) {
        Potential aux = null;
        Potential potC = null;
        Relation rel;
        Vector vars;
        Vector newVars;
        Node decision;
        String name;
        int kindOfRel;
        boolean added = false;
        int i;

        //  ((PotentialIntervalTable)util).getMeanISize();

        if (generateDebugInfo) {
            System.out.println("IDVariableElimination:  getExpectedUtility ----- BEGIN");
            System.out.println("Argumentos: ");
            System.out.println("Node: " + node.getName());
            System.out.println("Util: ");
            util.printDomain();
        }

        // Assign aux to util
        aux = util;

        // Consider the utility relations
        for (i = 0; i < currentRelations.size(); i++) {
            rel = currentRelations.elementAt(i);
            if (rel.isInRelation(node) == false && rel.getKind() == Relation.UTILITY) {
                // First at all check if both potentials have shared variables
                // If not, combine first with an unity potential to add these
                // new variables
                aux = preproccess(aux, rel);
                aux = addPotentials(aux, rel);
                added = true;
                if (generateDebugInfo == true) {
                    System.out.println("Integrando utilidades :");
                    System.out.println("Potencial auxiliar: ");
                    aux.printDomain();
                }
            }
        }

        //Get the set of variables present in aux

        vars = aux.getVariables();

        // Once the utility relations are integrated, we must consider the
        // probability relations to avoid utilty related to non possible
        // events. This is required if really were added new relations

        if (added == true) {

            RelationList relations = currentRelations.getRelationsOf(aux.getVariables());

            for (i = 0; i < relations.size(); i++) {
                // Get every relation

                rel = (Relation) relations.elementAt(i);

                // Consider if it is not an utility

                kindOfRel = rel.getKind();
                if (kindOfRel != Relation.UTILITY && kindOfRel != Relation.CONSTRAINT) {

                    if (generateDebugInfo == true) {
                        System.out.println("Combinando con distr. de probabilidad");
                        System.out.println("Relacion correspondiente");
                        rel.printDomain();
                    }

                    // Combine on potC

                    potC = combinePotentials(potC, rel);

                    if (generateDebugInfo == true) {
                        System.out.println("Tras combinacion: resultado parcial");
                        potC.printDomain();
                    }
                }
            }

            // If potC is non null, multiply the utility and potC to avoid
            // utility related to impossible events. The divide to get the
            // same values

            if (potC != null) {
                aux = combine(aux, potC);
                aux = divide(aux, potC);
            }

            //Finally, remove adding all the variables that were not present
            //in aux: that variables are stored in aux

            newVars = aux.getVariables();

            for (i = 0; i < newVars.size(); i++) {
                Node var = (Node) newVars.elementAt(i);

                // Look if this variable is in vars

                if (vars.contains(var) == false) {
                    aux = addVariable(aux, var);
                    if (generateDebugInfo == true) {
                        System.out.println("Eliminada variable: " + var.getName());
                        aux.printDomain();
                    }
                }
            }
        }

        // Apply constraints on the final decision table
        aux = transformExpectedUtility(aux);

        // Get sure node is the last variable in the potential

        //aux = (Potential) aux.sendVarToEnd(node);
        if (generateDebugInfo == true) {
            System.out.println("Saliendo de getExpecteUtility");
            System.out.println("A devolver: ");
            aux.printDomain();
            System.out.println("IDVariableElimination:  getExpectedUtility ----- END");
        }



        // Return aux
        return aux;
    }

    /**
     * Class to show if a given operation on the ID involves one or several
     * potentials (relations)
     */
    protected class Flag {

        private boolean flag;

        Flag() {
            flag = false;
        }

        void setValue(boolean value) {
            flag = value;
        }

        boolean getValue() {
            return flag;
        }
    }

    public void setIdCriteria(int idCriteria) {
        this.idCriteria = idCriteria;
    }

    public int getIdCriteria() {
        return idCriteria;
    }

    public void setSaveAsTable(boolean saveAsTable) {
        this.saveAsTable = saveAsTable;
    }

    public void setRemovalOrder(String[] names) {

        removalOrdering = new Vector<Node>();

        for (int i = 0; i < names.length; i++) {
            removalOrdering.add(network.getNode(names[i]));
        }


    }

    public Vector<Node> getRemovalOrdering() {
        return removalOrdering;
    }

    public void setRemovalOrdering(Vector<Node> ord) {
        removalOrdering = new Vector<Node>();
        for (int i = 0; i < ord.size(); i++) {
            removalOrdering.add(ord.elementAt(i));
        }
    }

    public boolean isGreedyCombination() {
        return greedyCombination;
    }

    public void setGreedyCombination(boolean greedyCombination) {
        this.greedyCombination = greedyCombination;
    }

    public void setCheckUnity(boolean checkUnity) {
        this.checkUnity = checkUnity;
    }

    public boolean isSimetric() {
        return simetric;
    }

    public void setSimetric(boolean simetric) {
        this.simetric = simetric;
    }

    public boolean isConditioning() {
        return conditioning;
    }

    public void setConditioning(boolean conditioning) {
        this.conditioning = conditioning;
    }

    public boolean isSimpleDecisionRemoval() {
        return simpleDecisionRemoval;
    }

    public void setSimpleDecisionRemoval(boolean simpleDecisionRemoval) {
        this.simpleDecisionRemoval = simpleDecisionRemoval;
    }

    public double getMaximumIU() {
        return maximumIU;
    }

    public void setMaximumIU(double maximumIU) {
        this.maximumIU = maximumIU;
    }

    public double getMinimumIU() {
        return minimumIU;
    }

    public void setMinimumIU(double minimumIU) {
        this.minimumIU = minimumIU;
    }

    public boolean isQualitativeEval() {
        return qualitativeEval;
    }

    public void setQualitativeEval(boolean qualitativeEval) {
        this.qualitativeEval = qualitativeEval;
    }

    public static boolean isNormUtilities() {
        return normUtilities;
    }

    public static void setNormUtilities(boolean normUtilities) {
        IDVariableElimination.normUtilities = normUtilities;
    }

    public boolean isMeasureOverHead() {
        return measureOverHead;
    }

    public void setMeasureOverHead(boolean measureOverHead) {
        this.measureOverHead = measureOverHead;
    }

    public long getOverHeadTime() {
        return overHeadTime;
    }

    public void setOverHeadTime(long overHeadTime) {
        this.overHeadTime = overHeadTime;
    }

    public void addOverHeadTime(long overHeadTime) {
        this.overHeadTime += overHeadTime;
    }

    private void startMeasureOverHead() {
        if (measureOverHead) {
            initTime = System.nanoTime();
        }

    }

    private void stopMeasureOverHead() {

        if (measureOverHead) {
            endTime = System.nanoTime();
            addOverHeadTime(endTime - initTime);
        }
    }

    private long computeMargSize(Potential pot, NodeList removableVars) {

        long size = 0;

        if (pot instanceof PotentialBPTree) {
            return 0;
        }


        long numConf = pot.getNumConfigurations();
        for (int i = 0; i < removableVars.size(); i++) {
            FiniteStates v = (FiniteStates) removableVars.elementAt(i);
            numConf = numConf / v.getNumStates();
            size += numConf * (v.getNumStates() - 1);


        }
        return size;
    }

    private long binaryOperationCost(Potential resultingPot) {
        if (resultingPot instanceof PotentialBPTree) {
            return 0;
        }

        return resultingPot.getSize();
    }

    protected long unaryOperationCost(Potential pot, Node n) {

        if (pot instanceof PotentialBPTree) {
            return 0;
        }

        long numConf = pot.getNumConfigurations();

        FiniteStates v = (FiniteStates) n;
        numConf = numConf / v.getNumStates();
        return numConf * (v.getNumStates() - 1);

    }

    public Vector computePrecisionFitness() {

        Vector S = new Vector();
        for (int i = 0; i < strategy.size(); i++) {

            Hashtable<Configuration, Vector<Integer>> pol = (Hashtable<Configuration, Vector<Integer>>) strategy.get(i);

            Iterator<Configuration> iterator = pol.keySet().iterator();

            //Cambiar para que muestre
            Vector vars = ((Potential) results.get(i)).getVariables();
            Vector vars2 = new Vector();
            FiniteStates dec = null;
            for (int j = 0; j < vars.size(); j++) {
                if (j != vars.size() - 1) {
                    vars2.add(vars.elementAt(j));
                } else {
                    dec = (FiniteStates) vars.elementAt(j);
                }
            }

            int numAlternatives = 0;

            Configuration conf = new Configuration(vars2);

            int min = conf.possibleValues();
            int max = min * dec.getNumStates();
            for (int j = 0; j < conf.possibleValues(); j++) {
                try {
                    numAlternatives += ((Vector) pol.get(conf)).size();
                } catch (Exception e) {
                    System.out.println();
                }
                conf.nextConfiguration();
            }

            double score = (double) (max - numAlternatives) / (max - min);
            S.add(score);

        }
        return S;
    }

    public Potential getGlobalStrategy() {

        Potential globalStr = null;

        Vector<Potential> localStr = getPolicyBinaryPotentials();
        for (int i = 0; i < localStr.size(); i++) {
            if (globalStr == null) {
                globalStr = localStr.elementAt(i);
            } else {
                globalStr = globalStr.combine(localStr.elementAt(i));
            }

        }




        return globalStr;

    }

    public Vector<Potential> getPolicyBinaryPotentials() {

        Vector S = new Vector();
        for (int i = 0; i < strategy.size(); i++) {

            Hashtable<Configuration, Vector<Integer>> pol = (Hashtable<Configuration, Vector<Integer>>) strategy.get(i);

            if (pol != null) {
                Iterator<Configuration> iterator = pol.keySet().iterator();

                //Cambiar para que muestre
                Vector vars = ((Potential) results.get(i)).getVariables();
                Vector vars2 = new Vector();

                FiniteStates dec = null;
                for (int j = 0; j < vars.size(); j++) {
                    if (j != vars.size() - 1) {
                        vars2.add(vars.elementAt(j));
                    } else {
                        dec = (FiniteStates) vars.elementAt(j);
                    }
                }


                PotentialTable polPotential = new PotentialTable(vars);


                Vector decList = new Vector();
                decList.add(dec);

                int numAlternatives = 0;

                Configuration conf2 = new Configuration(vars2);


                int min = conf2.possibleValues();
                int max = min * dec.getNumStates();
                for (int j = 0; j < conf2.possibleValues(); j++) { //iterate over the variables in the past


                    Configuration confDec = new Configuration(decList);

                    for (int k = 0; k < confDec.possibleValues(); k++) {

                        Configuration conf = new Configuration(confDec, conf2, new NodeList(vars));



                        try {
                            if (((Vector) pol.get(conf2)).contains(k)) {
                                polPotential.setValue(conf, 1);
                            } else {
                                polPotential.setValue(conf, 0);
                            }
                        } catch (Exception e) {
                            System.out.println();
                            System.exit(1);

                        }

                        confDec.nextConfiguration();
                    }
                    conf2.nextConfiguration();
                }


                S.add(polPotential);
            }

        }
        return S;
    }

    public void printStrategy() {

        for (int i = 0; i < strategy.size(); i++) {

            Hashtable<Configuration, Vector<Integer>> pol = (Hashtable<Configuration, Vector<Integer>>) strategy.get(i);

            Iterator<Configuration> iterator = pol.keySet().iterator();

            //Cambiar para que muestre
            Vector vars = ((Potential) results.get(i)).getVariables();
            Vector vars2 = new Vector();
            FiniteStates dec = null;
            for (int j = 0; j < vars.size(); j++) {
                if (j != vars.size() - 1) {
                    vars2.add(vars.elementAt(j));
                } else {
                    dec = (FiniteStates) vars.elementAt(j);
                }
            }

            System.out.println("\n\nPolicy for decision " + dec.getName() + " (" + dec.getNumStates() + " states),\nrelevant past = " + vars2.toString().replaceAll(" ", "") + ":");
            int numAlternatives = 0;


            Configuration conf = new Configuration(vars2);

            int min = conf.possibleValues();
            int max = min * dec.getNumStates();
            for (int j = 0; j < conf.possibleValues(); j++) {

                System.out.print("\t\t");
                conf.print();
                System.out.println(" " + pol.get(conf));
                numAlternatives += ((Vector) pol.get(conf)).size();

                conf.nextConfiguration();
            }

            float score = (float) (max - numAlternatives) / (max - min);

            System.out.println("Optimal solution: " + min + " alternatives");
            System.out.println("Worst solution: " + max + " alternatives");
            System.out.println("Solution obtained: " + numAlternatives + " alternatives (score=" + score + ")");



        }


    }

    private void updateInitialUtilityMaxMin() {

        RelationList rlist = this.network.getInitialRelations();
        double max = Double.NEGATIVE_INFINITY;
        double min = Double.POSITIVE_INFINITY;


        for (int i = 0; i < rlist.size(); i++) {
            Relation r = rlist.elementAt(i);
            if (r.getKind() == Relation.UTILITY && r.getValues() instanceof PotentialTable) {

                double maxmin[] = ((PotentialTable) r.getValues()).getMaxMin();
                if (maxmin[1] < min) {
                    min = maxmin[1];
                }
                if (maxmin[0] > max) {
                    max = maxmin[0];
                }


            }
        }

        setMaximumIU(max);
        setMinimumIU(min);


    }

    public Relation transformInitialRelation(Relation r) {
        PotentialTable potTable;
        Relation rNew;



        if (generateDebugInfo) {
            System.out.println("IDVariableElimination:  transformInitialRelation ----- BEGIN");
        }


        if (r.getKind() == Relation.UTILITY && normUtilities) {
            rNew = new Relation();
            rNew.setVariables(r.getVariables().copy());
            rNew.setKind(r.getKind());
            potTable = new PotentialTable(r.getValues());

            //  System.out.println(maximumIU+", "+minimumIU);
            //   potTable.print();

            potTable.normalize(maximumIU, minimumIU);

            //   potTable.print();



            if (generateDebugInfo) {
                System.out.println("\t - Potencial transformado: " + potTable);
            }
            // Store the final potential
            rNew.setValues(potTable);


            if (generateDebugInfo) {
                System.out.println("IDVariableElimination:  transformInitialRelation ----- END");
            }


            // Return the new relation
            return rNew;
        } else {



            if (generateDebugInfo) {
                System.out.println("IDVariableElimination:  transformInitialRelation ----- END");
            }

            // For constraints, do not change it
            return r;
        }
    }

    protected Potential transformMEU(Potential pot) {

        Potential pot2 = pot;

        if (generateDebugInfo) {
            System.out.println("IDVariableElimination:  transformMEU ----- BEGIN");
        }


        if (normUtilities) {

            pot2 = new PotentialTable(pot);


            double meu = ((PotentialTable) pot2).getValue(0);
            int nUtil = this.network.getNodesOfKind(Node.UTILITY).size();
            meu = meu * (maximumIU - minimumIU) + nUtil * minimumIU;

            ((PotentialTable) pot2).setValue(0, meu);
        }

        if (generateDebugInfo) {
            System.out.println("IDVariableElimination:  transformMEU ----- END");
        }

        return pot2;

    }
} // End of class

