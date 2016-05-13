package elvira.inference.elimination.ids;

import elvira.*;
import elvira.inference.elimination.ids.params.IDVEITparams;
import elvira.inference.elimination.ids.params.IDVEPTparams;
import elvira.inference.elimination.ids.params.IDVEparams;
import elvira.parser.ParseException;
import elvira.potential.*;
import elvira.potential.PotentialIntervalTable.ChoiceFunction;
import elvira.tools.idiagram.EUComparator;
import java.io.*;
import java.util.Random;
import java.util.Vector;

/**
 * Class
 * <code>VEWithPotentialTree</code>. Implements the variable elimination method
 * of propagation using potentials of class
 * <code>PotentialTree</code>. If the initial potentials are not PotentialTrees
 * then they are converted to PotentialTrees.
 *
 * @since 11/9/2000
 */
public class IDVEWithPotentialIntervalTable extends IDVariableElimination {

    protected double iutilPerturbation = 0;
    protected double iprobPerturbation = 0;
    protected boolean makeReachableComb = true;
    protected boolean makeReachableBinComb = false;
    protected boolean makeReachableMarg = true;

    public enum evalMethod {

        STANDARD, EXT_POINTS_UTIL, LINEAR_PROG_UTIL, LINEAR_PROG
    };
    protected evalMethod eMethod = evalMethod.STANDARD;
    private Vector<Node> subsetOfPertNodes;

    /**
     * Constructs a new propagation for a given Bayesian network and some
     * evidence.
     *
     * @param b a
     * <code>Bnet</code>.
     * @param e the evidence.
     */
    public IDVEWithPotentialIntervalTable(Bnet b, Evidence e) {
        super(b, e);

        /*
         * if (generateDebugInfo) {
         * System.out.println("IDVEWithPotentialIntervalTable: class
         * Constructor(Bnet,Evidence) ----- BEGIN");
         }
         */
        /*
         * if (generateDebugInfo) {
         * System.out.println("IDVEWithPotentialIntervalTable: class Constructor
         * ----- END");
         }
         */
    }

    /**
     * Constructs a new propagation for a given Bayesian network
     *
     * @param b a
     * <code>Bnet</code>.
     */
    public IDVEWithPotentialIntervalTable(Bnet b) {
        super(b);
        /*
         * if (generateDebugInfo) {
         * System.out.println("IDVEWithPotentialIntervalTable: class
         * Constructor(Bnet) ----- BEGIN");
         }
         */

        /*
         * if (generateDebugInfo) {
         * System.out.println("IDVEWithPotentialIntervalTable: class Constructor
         * ----- END");
         }
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
    public IDVEWithPotentialIntervalTable(Bnet b, Evidence e, IDVEITparams p) {
        super(b, e, p);

        /*
         * if (generateDebugInfo) {
         * System.out.println("IDVEWithPotentialIntervalTable: class
         * Constructor(Bnet,Evidence) ----- BEGIN");
         }
         */
        this.iutilPerturbation = p.getIutilPerturbation();
        this.iprobPerturbation = p.getIprobPerturbation();
        this.subsetOfPertNodes = p.getSubsetOfPertNodes();
        /*
         * if (generateDebugInfo) {
         * System.out.println("IDVEWithPotentialIntervalTable: class Constructor
         * ----- END");
         }
         */
    }

    /**
     * Constructs a new propagation for a given Bayesian network
     *
     * @param b a
     * <code>Bnet</code>.
     */
    public IDVEWithPotentialIntervalTable(Bnet b, IDVEITparams p) {
        super(b, p);
        /*
         * if (generateDebugInfo) {
         * System.out.println("IDVEWithPotentialIntervalTable: class
         * Constructor(Bnet) ----- BEGIN");
         }
         */

        this.iutilPerturbation = p.getIutilPerturbation();
        this.iprobPerturbation = p.getIprobPerturbation();
        this.subsetOfPertNodes = p.getSubsetOfPertNodes();
        /*
         * if (generateDebugInfo) {
         * System.out.println("IDVEWithPotentialIntervalTable: class Constructor
         * ----- END");
         }
         */
    }

    public void setPolChoice(ChoiceFunction f) {
        PotentialIntervalTable.setPolChoice(f);
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

        boolean precisePert = false;

        if (generateDebugInfo) {
            System.out.println("IDVEWithPotentialIntervalTable:  transformInitialRelation ----- BEGIN");
        }






        if (r.getKind() == Relation.CONSTRAINT) {
            return r;
        }


        //Perturbate only a subset
        if (subsetOfPertNodes.size() > 0 && !subsetOfPertNodes.contains(r.getChild())) {
            precisePert = true;
        }


        if (r.getKind() == Relation.UTILITY) {
            //if (iutilPerturbation != 0) {
            rNew = new Relation();
            rNew.setVariables(r.getVariables().copy());
            rNew.setKind(r.getKind());



            PotentialIntervalTable pot = null;

            if (precisePert) {
                pot = new PotentialIntervalTable((PotentialTable) r.getValues(), 0.0);
            } else {
                pot = new PotentialIntervalTable((PotentialTable) r.getValues(), iutilPerturbation);
            }
            
            //pot.print();

            rNew.setValues(pot);

            this.maximumIU = Math.max(maximumIU, pot.getHigherMax());
            this.minimumIU = Math.min(minimumIU, pot.getLowerMin());



            //}
        } else if (r.getKind() == Relation.POTENTIAL) {



            //        if (iprobPerturbation != 0) {
            rNew = new Relation();
            rNew.setVariables(r.getVariables().copy());
            rNew.setKind(r.getKind());

            PotentialIntervalTable pot = null;
            if (precisePert) {
                pot = new PotentialIntervalTable((PotentialTable) r.getValues(), 0.0, true);
            } else {
                pot = new PotentialIntervalTable((PotentialTable) r.getValues(), iprobPerturbation, true);
            }

            makeReachable(pot);
            rNew.setValues(pot);

            //       }
        }

//~~
        rNew.getValues().print();

        if (generateDebugInfo) {

            System.out.println("IDVEWithPotentialIntervalTable:  transformInitialRelation ----- END");
        }

        
        
        
        // Return the new relation
        return rNew;
    }

    public void samplingPropagation(Random rand, int iterations) throws Throwable {

        Vector<Vector<Potential>> resultsSet = new Vector<Vector<Potential>>();
        resultsSet = new Vector<Vector<Potential>>();


        for (int i = 0; i < iterations; i++) {

            IDiagram idp = getPreciseID(rand);


            IDVariableElimination ve = new IDVariableElimination(idp);
            ve.setCheckUnity(checkUnity);
            ve.setCombinationHeuristic(combinationHeuristic);
            ve.setCriteria(getCriteria());
            ve.setGreedyCombination(greedyCombination);
            ve.setIdCriteria(getIdCriteria());
            ve.generateStatistics = generateStatistics;
            ve.generateDebugInfo = generateDebugInfo;

            ve.propagate();


            Vector results_i = ve.getResults();

            for (int j = 0; j < results_i.size(); j++) {
                if (i == 0) {
                    resultsSet.add(new Vector<Potential>());
                }
                resultsSet.elementAt(j).add((Potential) results_i.elementAt(j));
            }

        }

        Vector<PotentialIntervalTable> resInterval = new Vector<PotentialIntervalTable>();
        for (int i = 0; i < resultsSet.size(); i++) {
            resInterval.add(new PotentialIntervalTable(resultsSet.elementAt(i).elementAt(0).getVariables(), resultsSet.elementAt(i)));
        }

        this.results = resInterval;

    }

    public IDiagram getPreciseID(Random rand) throws Throwable {




        IDiagram id = ((IDiagram) network).copy();

        RelationList rel = this.getInitialRelations();
        Vector newRel = new Vector();




        Relation r;
        for (int i = 0; i < rel.size(); i++) {

            r = (Relation) rel.elementAt(i);
            PotentialIntervalTable ipot = (PotentialIntervalTable) r.getValues();

            if (r.getKind() != Relation.CONSTRAINT) {
                if (r.getKind() != Relation.UTILITY) {
                    //  PotentialIntervalTable ipot = new PotentialIntervalTable((PotentialTable) r.getValues(), iutilPerturbation, false);

                    r.setValues(ipot.getRandomPreciseProbability(rand));
                } else {
                    //     PotentialIntervalTable ipot = new PotentialIntervalTable((PotentialTable) r.getValues(), iprobPerturbation, true);
                    r.setValues(ipot.getRandomPreciseUtility(rand));
                }
            }


            newRel.add(r);


        }

        id.setRelationList(newRel);



        return id;

    }

    /**
     * Transforms the
     * <code>Potential</code> obtained asode>Potential</code> obtained
     * asvariable (
     * <code>FiniteStates</code>). This method can be overloaded in subclases
     * for special requirements. Right now, this method returns the argument
     * itself.
     *
     * @param pot the
     * <code>Potential</code> to transform.
     * @return
     */
    public Potential transformAfterCombining(Potential pot) {

        if (makeReachableComb && !makeReachableBinComb && pot != null) {
            makeReachable(pot);
        }
        return pot;
    }

    /**
     * Transforms the
     * <code>Potential</code> obtained asode>Potential</code> obtained
     * asvariable (
     * <code>FiniteStates</code>). This method can be overloaded in subclases
     * for special requirements. Right now, this method returns the argument
     * itself.
     *
     * @param pot the
     * <code>Potential</code> to transform.
     * @return
     */
    public Potential transformAfterBinaryComb(Potential pot) {


        if (makeReachableBinComb && pot != null) {
            makeReachable(pot);
        }


        return pot;
    }

    /**
     * Transforms the
     * <code>Potential</code> obtained asode>Potential</code> obtained
     * asvariable (
     * <code>FiniteStates</code>). This method can be overloaded in subclases
     * for special requirements. Right now, this method returns the argument
     * itself.
     *
     * @param pot the
     * <code>Potential</code> to transform.
     * @return
     */
    public Potential transformAfterEliminating(Potential pot) {



        if (makeReachableMarg && pot != null) {
            makeReachable(pot);
        }
        return pot;
    }

    public void makeReachable(Potential p) {
        if (p instanceof IPotentialInterval) {

            //       if(!((IPotentialInterval)p).isReachable()){

            ((IPotentialInterval) p).makeReachable();

            //          }
        }
    }

    /**
     * Check if the ID is evaluable. It performs initial transformations
     */
    public void checkEvaluable() {
        // Call to initial conditions in order to get measures 
        // about potential sizes, etc
        boolean evaluable = initialConditions();
        if (evaluable == false) {
            System.out.println("Non evaluable diagram......");
            System.exit(0);
        }

    }

    public boolean isMakeReachableBinComb() {
        return makeReachableBinComb;
    }

    public void setMakeReachableBinComb(boolean makeReachableBinComb) {
        this.makeReachableBinComb = makeReachableBinComb;
    }

    public boolean isMakeReachableComb() {
        return makeReachableComb;
    }

    public void setMakeReachableComb(boolean makeReachableComb) {
        this.makeReachableComb = makeReachableComb;
    }

    public boolean isMakeReachableMarg() {
        return makeReachableMarg;
    }

    public void setMakeReachableMarg(boolean makeReachableMarg) {
        this.makeReachableMarg = makeReachableMarg;
    }

    public evalMethod getEvalMethod() {
        return eMethod;
    }

    public void setEvalMethod(evalMethod eMethod) {
        this.eMethod = eMethod;
    }

    /**
     * Method to make the operations requiered to remove a chance node
     *
     * @param node Node to remove
     * @param pt PairTable to store the relations of the nodes
     * @return
     * <code>boolean</code> to show if the operation was made
     */
    @Override
    protected void combinePotentialsToRemoveChanceNode(FiniteStates node, PairTable pt) {


        if (!conditioning && eMethod == evalMethod.STANDARD) {
            super.combinePotentialsToRemoveChanceNodeStandard(node, pt);
        } else if (conditioning && eMethod == evalMethod.STANDARD) {
            super.combinePotentialsToRemoveChanceNodeConditioning(node, pt);
        } else if (eMethod == evalMethod.EXT_POINTS_UTIL || eMethod == evalMethod.LINEAR_PROG_UTIL) {
            combinePotentialsToRemoveChanceNodeExtLinearProgUtil(node, pt);
        } else if (eMethod == evalMethod.LINEAR_PROG) {
            combinePotentialsToRemoveChanceLinearProg(node, pt);

        } else {

            System.err.println("ERROR: combinePotentialsToRemoveChanceNode, wrong chance removal method");
            System.exit(1);

        }

    }

    /*
     * if (conditioning) { combinePotentialsToRemoveChanceNodeConditioning(node,
     * pt); } else { combinePotentialsToRemoveChanceNodeStandard(node, pt); } }
     *
     */
    /**
     * Method to make the operations requiered to remove a chance node
     *
     * @param node Node to remove
     * @param pt PairTable to store the relations of the nodes
     * @return
     * <code>boolean</code> to show if the operation was made
     */
    protected void combinePotentialsToRemoveChanceNodeExtLinearProgUtil(FiniteStates node, PairTable pt) {
        Potential potC, potCopy = null;
        Potential potU;
        double potCSize = 0, potUSize = 0;
        IDVariableElimination.Flag unaryOperation = new IDVariableElimination.Flag();



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

        potCopy = potC;

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





        /*
         * Checks if the removal will lead to a unity potential
         */
        boolean unity = false;
        if (potC != null && checkUnity) {
            unity = Potential.generatesUnityPot(potC, node);
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



        /*
         * Removal from the utilities
         */
        if (potU != null) {
            if (generateStatistics) {
                statistics.addSumMarg(unaryOperationCost(potU, node));
            }

            if (eMethod == evalMethod.EXT_POINTS_UTIL) {
                potU = PotentialIntervalTable.margWithExtremePoints((PotentialIntervalTable) potCopy, (PotentialIntervalTable) potU, node);
            } else if (eMethod == evalMethod.LINEAR_PROG_UTIL) {
                potU = PotentialIntervalTable.margWithLinearProg((PotentialIntervalTable) potCopy, (PotentialIntervalTable) potU, node);
            } else {
                System.out.println("ERROR: wrong evalMethod");
                System.exit(1);

            }

            if (generateDebugInfo == true) {
                System.out.println("Se elimina en suma la variable a eliminar de potU " + node.getName());
                potU.printDomain();
                System.out.println("----------------------------------------");
            }
        }




        /*
         * Removal from the probabilities
         */
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

    //TODO: Revisar que va bien cada uno de los métodos, depurar   
    /**
     * Method to make the operations requiered to remove a chance node
     *
     * @param Y Node to remove
     * @param pt PairTable to store the relations of the nodes
     * @return
     * <code>boolean</code> to show if the operation was made
     */
    protected void combinePotentialsToRemoveChanceLinearProg(FiniteStates Y, PairTable pt) {
        PotentialIntervalTable probYAB, probCEY, utilYG, probYABcopy;
        Potential potU = null, potC = null;
        double potCSize = 0, potUSize = 0;
        IDVariableElimination.Flag unaryOperation = new IDVariableElimination.Flag();

        if (generateDebugInfo) {
            System.out.println("IDVariableElimination:  combinePotentialsToRemoveChanceNode ----- BEGIN");
        }

        
System.out.println("~~Removal of "+Y.getName());
        
        /*
         * if(Y.getName().equals("OH")) System.out.println();
         *
         * System.out.println("~~ Removal of "+Y.getName());
         * currentRelations.printDomainsAndSizes(); System.out.println("~~~");
         */

        if (currentRelations.getRelationsWithVarInTheHead(Y).size() == 0) {
            System.out.println("~~");
        }

        /*
         * Get the probability with Y in the head
         */
        Relation r = (Relation) currentRelations.getRelationsWithVarInTheHead(Y).elementAt(0);
        currentRelations.removeRelation(r);
        probYAB = (PotentialIntervalTable) r.getValues();


        /*
         * Combine the rest of relevant Probability potentials
         */

        // First at all, combine the probability potentials related to this
        // node
        if (greedyCombination) {
            probCEY = (PotentialIntervalTable) combineProbabilityPotentialsGreedy(Y, pt, unaryOperation);
        } else {
            probCEY = (PotentialIntervalTable) combineProbabilityPotentials(Y, pt, unaryOperation);
        }


        probYABcopy = probYAB;

        
        
        
        /*
         * if (potC != null && generateStatistics) { if (generateDebugInfo ==
         * true) { System.out.println("Potencial resultante de combinacion de
         * probs"); potC.printDomain();
         * System.out.println("--------------------------------------------");
         * if (unaryOperation.getValue() == false) { potCSize = potC.getSize();
         * } operations.addSize(totalSize + potCSize); } }
         */


        // Combine the utility potentials related to node
        unaryOperation.setValue(false);


        /*
         * Combine relevant Probability potentials
         */
        if (greedyCombination) {
            utilYG = (PotentialIntervalTable) combineUtilityPotentialsGreedy(Y, pt, unaryOperation);
        } else {
            utilYG = (PotentialIntervalTable) combineUtilityPotentials(Y, pt, unaryOperation);
        }
        /*
         * if (potU != null && generateStatistics) { if
         * (unaryOperation.getValue() == false) { potUSize = potU.getSize(); }
         * operations.addSize(totalSize + potUSize + potCSize);
        }
         */



        //     checkUnity = false;
        /*
         * Checks if the removal will lead to a unity potential
         */
        boolean unity = false;
        if (probYAB != null && checkUnity) {
            unity = (probCEY == null && probYAB.getHead().size() == 1);
        }

        if (unity) {

            potC = null;
            probYAB = null;

            if (generateStatistics) {
                statistics.addNumProbBarren(1);
            }
            if (generateDebugInfo) {
                System.out.println("Se va a generar un potencial unidad... no se realiza la marginalización");
            }
        }



        /*
         * Removal from the utilities
         */
        if (utilYG != null) {
            if (generateStatistics) {
                statistics.addSumMarg(unaryOperationCost(utilYG, Y));
            }

            if (eMethod == evalMethod.LINEAR_PROG) {
                potU = PotentialIntervalTable.margUtilWithLP(probYABcopy, probCEY, utilYG, Y, false, unity);
                //  potU.print();
            } else {
                System.out.println("ERROR: wrong evalMethod");
                System.exit(1);

            }

            /*
             * if (generateDebugInfo == true) { System.out.println("Se elimina
             * en suma la variable a eliminar de potU " + Y.getName());
             * potU.printDomain();
             * System.out.println("----------------------------------------");
            }
             */
        }




        /*
         * Removal from the probabilities
         */
        if (probYAB != null) {

            potC = PotentialIntervalTable.margProbWithLP(probYABcopy, probCEY, Y, true);
            transformAfterEliminating(potC);


            /*
             * if (generateDebugInfo == true) { if (potC == null) {
             * System.out.println("Se trata de un potencial unidad y se
             * descarta...."); } else { potC.printDomain();
             * System.out.println("----------------------------------------"); }
            }
             */
        }






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

// potC.print();

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
        //  currentRelations.printDomainsAndSizes();

    }
}
