/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package elvira.inference.symbolic.ids;

import com.sun.java_cup.internal.runtime.Symbol;
import elvira.*;
import elvira.inference.Propagation;
import elvira.inference.elimination.ids.IDVariableElimination;
import elvira.inference.symbolic.SymbolicProbInf;
import elvira.parser.ParseException;
import elvira.potential.Potential;
import elvira.potential.PotentialTable;
import elvira.potential.binaryprobabilitytree.BinaryProbabilityTree;
import elvira.potential.binaryprobabilitytree.PotentialBPTree;
import elvira.tools.OperationsList;
import elvira.tools.VectorManipulator;
import elvira.tools.idiagram.IDPotentialAnalyzer;
import elvira.tools.idiagram.pairtable.CombPair;
import elvira.tools.idiagram.pairtable.CombPairTable;
import elvira.tools.idiagram.pairtable.IDPairTable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

/**
 *
 * @author Rafael Cabañas (rcabanas@decsai.ugr.es)
 */
public class IDSymbolicProbInf extends SymbolicProbInf {
    protected double minimum;
    protected double maximum;

    /**
     * Constants to indicate the possible states of evaluable
     */
    protected enum EvaluableStates {

        NOCHECKED, EVALUABLE, NOEVALUABLE
    };
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
    protected boolean saveAsTable = true;
    
    private int heuristic = CombPairTable.MIN_SIZE_ONLY_PAIR;
    boolean allowSingletonProbs = true;

    protected boolean simetric = true; //si true, Da igual el orden (a,b)=(b,a)
    
    //to measure the overhead for choosing the operations
    protected boolean measureOverHead = false;
    protected long overHeadTime = 0;
    private  long initTime, endTime;
    
        /**
     * HashMap to contain the decision tables produces as a result
     * of the evaluation
     */
    protected HashMap<Node, Potential> resultsMap = null;
    
    
    protected boolean checkUnity=true;
    
    

    
    //Local variables to store the storage reqirements
    private long numNodesNotInvolved = 0;
    private long numNodesProb = 0;
    private long numNodesUtil = 0;

    private long sizesNotInvolved = 0;
    private long sizesProb = 0;
    private long sizesUtil = 0;
    
    private boolean qualitativeEval = false;
    
       
    public IDSymbolicProbInf() {
    
    }
    
    
    /**
     * Constructs a new propagation for a given ID and some evidence.
     *
     * @param b a <code>Bnet</code>.
     * @param e the evidence.
     */
    public IDSymbolicProbInf(Bnet b, Evidence e) {
        super(b, e);

        if (generateDebugInfo) {
            System.out.println("IDSymbolicProbInf:  class Constructor(Bnet,Evidence) ----- BEGIN");
        }
        evaluable = EvaluableStates.NOCHECKED;

        if (generateDebugInfo) {
            System.out.println("IDSymbolicProbInf:  class Constructor ----- END");
        }
    }

    /**
     * Constructs a new propagation for a given Bayesian network
     *
     * @param b a <code>Bnet</code>.
     */
    public IDSymbolicProbInf(Bnet b) {
        super(b);
        if (generateDebugInfo) {
            System.out.println("IDSymbolicProbInf:  class Constructor(Bnet) ----- BEGIN");
        }
        evaluable = EvaluableStates.NOCHECKED;
        if (generateDebugInfo) {
            System.out.println("IDSymbolicProbInf:  class Constructor ----- END");
        }
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
            System.out.println("IDSymbolicProbInf:  initialConditions ----- BEGIN");
        }

        // Gets the network
        IDiagram diag = (IDiagram) network;
        ((IDiagram)network).setProp(this);
        
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
            System.out.println("IDSymbolicProbInf:  initialConditions ----- END");
        }

        // Return evaluable
        return state;
    }

    /**
     * Carries out a propagation storing the results in
     * <code>results</code>.
     */
    public void propagate() {
        if (generateDebugInfo) {
            System.out.println("IDSymbolicProbInf:  propagate ----- BEGIN");
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
          //  getPosteriorDistributionsID_ipmu();
           getPosteriorDistributionsID(); //ECAI and PGM
            
            
        } else {
            System.out.print("Error in IDSymbolicProbInf.propagate(): ");
            System.out.println("this propagation method is not implemented for " + network.getClass());
            System.exit(1);
        }

        if (generateDebugInfo) {
            System.out.println("IDSymbolicProbInf:  propagate ----- END");
        }
    }
    
    
    
 
    

    /**
     * Gets the expected utility tables for the decision nodes in an influence
     * diagram
     */
    private void getPosteriorDistributionsID_ipmu() {
        if (generateDebugInfo) {
            System.out.println("IDSymbolicProbInf:  getPosteriorDistributionsID ----- BEGIN");

        
        
        }
        
        IDiagram networkAux = ((IDiagram) network).copy();

        // First at all, remove all results stored in results vector
        results.removeAllElements();

        NodeList decisions = ((IDiagram) networkAux).getDecisionList();

        // Now, as first step, get all nodes without decisions as sucessors
        NodeList nodeSet = ((IDiagram) networkAux).getChanceNodesWithoutDecisionsAsSucessors();
        NodeList newNodeSet;

        if(generateDebugInfo)
            printAllRelations(false);
        
        
        //Remove set of chance nodes In
        removeChanceSet(nodeSet);

        if(generateDebugInfo)
            printAllRelations(false);
        
        //


        for (int i = decisions.size() - 1; i >= 0; i--) {
            Node decisionConsidered = decisions.elementAt(i);
            System.out.println(decisionConsidered.getName());
            nodeSet.insertNode(decisionConsidered);

            //remove Di
            removeDecision(decisionConsidered);
            //
            //

    
            // Now, get all chance nodes with sucessors in phase
            newNodeSet = ((IDiagram) networkAux).getChanceNodesWithSucessorsInSet(nodeSet);
            newNodeSet.printNames();

            //remove Ii
            removeChanceSet(newNodeSet);
            //



            for (int j = 0; j < newNodeSet.size(); j++) {
                nodeSet.insertNode(newNodeSet.elementAt(j));
            }


        }



        if (generateDebugInfo) {
            System.out.println("IDSymbolicProbInf:  getPosteriorDistributionsID ----- END");
        }

    } // end method

    
    
      /**
     * Gets the expected utility tables for the decision nodes in an influence
     * diagram
     */
    private void getPosteriorDistributionsID() {
        if (generateDebugInfo) {
            System.out.println("IDSymbolicProbInf:  getPosteriorDistributionsID ----- BEGIN");
        }
        
        IDiagram networkAux = ((IDiagram) network).copy();

        // First at all, remove all results stored in results vector
        results.removeAllElements();

        NodeList decisions = ((IDiagram) networkAux).getDecisionList();

        // Now, as first step, get all nodes without decisions as sucessors
        NodeList nodeSet = ((IDiagram) networkAux).getChanceNodesWithoutDecisionsAsSucessors();
        NodeList newNodeSet;

        if(generateDebugInfo)
            printAllRelations(false);
   
   
        if(generateStatistics) {
            numNodesNotInvolved = (long) currentRelations.sumNumNodes();
            sizesNotInvolved = (long) currentRelations.sumSizes();
            saveStorageStats();
        }
            

        //Remove set of chance nodes In
        //removeChanceSet(nodeSet);
        
        ArrayList<Potential> prob_xk = getCurrentProbWithAndRemove(nodeSet);
        ArrayList<Potential> util_xk = getCurrentUtilWithAndRemove(nodeSet);
        
        removeChanceSet(nodeSet, prob_xk, util_xk);  
        
        insertInCurrentRelations(prob_xk, Relation.POTENTIAL);
        insertInCurrentRelations(util_xk, Relation.UTILITY);

        if(generateDebugInfo)
            printAllRelations(false);
        
        //


        for (int i = decisions.size() - 1; i >= 0; i--) {
            Node decisionConsidered = decisions.elementAt(i);
            nodeSet.insertNode(decisionConsidered);

            //remove Di
                
            prob_xk = getCurrentProbWithAndRemove(decisionConsidered);
            util_xk = getCurrentUtilWithAndRemove(decisionConsidered);
            removeDecision(decisionConsidered, prob_xk, util_xk);
            insertInCurrentRelations(prob_xk, Relation.POTENTIAL);
            insertInCurrentRelations(util_xk, Relation.UTILITY);

            //
            //
            
                    // Now, get all chance nodes with sucessors in phase
                newNodeSet = ((IDiagram) networkAux).getChanceNodesWithSucessorsInSet(nodeSet);
   

    
            // Now, get all chance nodes with sucessors in phase
            newNodeSet = ((IDiagram) networkAux).getChanceNodesWithSucessorsInSet(nodeSet);
      


            //remove Ii
            prob_xk = getCurrentProbWithAndRemove(newNodeSet);
            util_xk = getCurrentUtilWithAndRemove(newNodeSet);
            removeChanceSet(newNodeSet, prob_xk, util_xk);
            insertInCurrentRelations(prob_xk, Relation.POTENTIAL);
            insertInCurrentRelations(util_xk, Relation.UTILITY);


            for (int j = 0; j < newNodeSet.size(); j++) {
                nodeSet.insertNode(newNodeSet.elementAt(j));
            }


        }

        
        //Saves the MEU
        for(int i=0; i<currentRelations.size(); i++) {
            Relation r = currentRelations.elementAt(i);
            if(r.getKind() == Relation.UTILITY){
                this.statistics.setFinalExpectedUtility(r.getValues());
            }
        }

        if (generateDebugInfo) {
            System.out.println("IDSymbolicProbInf:  getPosteriorDistributionsID ----- END");
        }

    } // end method
    
    
    public void removeChanceSet(NodeList chanceSet) {

        printAllRelations(false);
        
        
        if(chanceSet.size()==0)
            return;
        
        
        if (generateDebugInfo) {
            System.out.println("IDSymbolicProbInf:  removeChanceSet ----- BEGIN");
            System.out.println("Chance variables to remove: " + chanceSet.getNodes());
            System.out.println("1. Relevant potentials: ");
        }

        Potential potComb = null;
        NodeList removableVars = null;

        // 1. Set relevant potentials
        RelationList relevant = currentRelations.getRelationsOf(chanceSet.getNodes(), Relation.POTENTIAL);    
        RelationList relevantUtil = currentRelations.getRelationsOf(chanceSet.getNodes(), Relation.UTILITY);    
        
    //    Potential util = getUtility();
   //     removeUtility();

        
        
        // 2. Update potential Set
        for (int i = 0; i < relevant.size(); i++) {
            Relation r = ((Relation) relevant.getRelations().elementAt(i));
            currentRelations.removeRelation(r);
            if (generateDebugInfo) {
                r.printDomain();
            }
        }

        for (int i = 0; i < relevantUtil.size(); i++) {
            Relation r = ((Relation) relevantUtil.getRelations().elementAt(i));
            currentRelations.removeRelation(r);
            if (generateDebugInfo) {
                r.printDomain();
            }
        }

        CombPairTable pairTable = new CombPairTable();
        pairTable.setSetDebugInfo(generateDebugInfo);
        pairTable.setSimetric(simetric);
        pairTable.setRemovable(chanceSet);
        pairTable.setCheckMarginalChild(false);
        pairTable.setNotInvolvedRel(currentRelations);

        // 4. Repeat 
        do {
            
//            NodeList head, tail;
            
            
            if (relevant.size() > 1) {

                //Check if any variable is contained in a single
                //potential and remove it
                
                pairTable.setOtherInvolvedPots(relevantUtil);
                //4.a.i Generate pairwise combinations
                pairTable.addCombinations(relevant);
                if (generateDebugInfo) {
                    System.out.println("4.a.i Generate pairwise combinations:");
                    pairTable.printPairs();
                    System.out.println();
                
                }
                


                //4.a.ii Select next to combine
                CombPair p  = pairTable.netxToCombine(heuristic);
                potComb = combinePair(p);
                if(generateStatistics)
                    statistics.addNumMultiplications(binaryOperationCost(potComb));
        
                //Update the head and the tail
//                head = p.getHeadCombined();
//                tail = p.getTailCombined();
                
                //potComb.print();

                if (generateDebugInfo) {
                    System.out.println("4.a.ii Select next to combine: " + p);
                }




                //4.a.iii Determine variables that can be removed
                removableVars = p.getRemovableVars();
                if (generateDebugInfo) {
                    System.out.println("4.a.iii Determine variables that can be removed: " + removableVars.toString2());
                }

                //4.a.iv Update B
                pairTable.removeContainig(p.getR1());
                pairTable.removeContainig(p.getR2());
                if (generateDebugInfo) {
                    System.out.println("4.a.iv Update B");
                }


                //4.a.v Delete from relevant potential set
                relevant.removeRelation(p.getR1());
                relevant.removeRelation(p.getR2());
                if (generateDebugInfo) {
                    System.out.println("4.a.v Delete from relevant potential set");
                }

            } else {
                potComb = relevant.elementAt(0).getValues();
//                head = relevant.elementAt(0).getHead();
//                tail = relevant.elementAt(0).getTail();
                
                removableVars = potComb.getNodeList().intersection(chanceSet);
                relevant.removeRelationAt(0);
                
                if(generateDebugInfo) {
                    System.out.println("4.i Single probability potential: ");
                    potComb.printDomain();
                    System.out.println("4.ii  Determine variables that can be removed: ["+removableVars.toString2()+"]");
                }
                
            }
            //4.b Sum-marginalize
            Object output[] = sumMarginalizePots(potComb, relevantUtil, removableVars);
            Potential newProb = (Potential) output[0];
            relevantUtil = (RelationList) output[1];
            boolean unityPot = (Boolean) output[2];

            
            //4.c Update the set of variables to remove
            chanceSet = chanceSet.difference(removableVars);
            if(generateDebugInfo) {
                System.out.println("4.c Update the set of variables to remove: ["+chanceSet.toString2()+"]");
            }
            
            //Update the head and the tail
//            head = head.difference(removableVars);
 //           tail = tail.difference(removableVars);
            //4.d Update the set of relevant potentials
            if(!unityPot) {
                Relation newRel = new Relation(newProb);
   //             newRel.setHead(head);
   //             newRel.setTail(tail);
  // System.out.print("~~~~Resulting of marg");
  // newRel.printHeadAndTail();
                
                relevant.insertRelation(newRel);
            }
        } while (chanceSet.size() > 0);
        
        
        
        //d. Update the set of all potentials
//        Relation utilRel = new Relation(util);
 //       utilRel.setKind(Relation.UTILITY);
 //       currentRelations.insertRelation(utilRel);
        for(int i=0; i<relevant.size(); i++) {
            currentRelations.insertRelation(relevant.elementAt(i));
        }
        
        for(int i=0; i<relevantUtil.size(); i++) {
            currentRelations.insertRelation(relevantUtil.elementAt(i));
        }
        
       // System.out.println("Current relations: ");
       // currentRelations.print();

        if (generateDebugInfo) {
            System.out.println("IDSymbolicProbInf:  removeChanceSet ----- END");
        }
    }

    public Potential combinePair(CombPair p) {

        Potential pot1 = p.getR1().getValues();
        Potential pot2 = p.getR2().getValues();

       Potential newPot = pot1.combine(pot2);
/*        
        p.getR1().printHeadAndTail();
        p.getR2().printHeadAndTail();
          System.out.println("~~");
 */       
/*        NodeList head1 = new NodeList(pot1.getHead());
        NodeList head2 = new NodeList(pot2.getHead());
        
        NodeList tail1 = new NodeList(pot1.getTail());
        NodeList tail2 = new NodeList(pot2.getTail());
        
        head1.join(head2);
        NodeList newHead = head1;
        
        tail1.join(tail2);
        NodeList newTail = tail1.difference(newHead);
        
        
        newPot.setHead(newHead.toVector());
        newPot.setTail(newTail.toVector());
       */

        
        return newPot;



    }
    
    public void removeDecision(Node dec) {
    
        if (generateDebugInfo) {
            System.out.println("IDSymbolicProbInf:  removeDecision ----- BEGIN");
            System.out.println("Decision to remove: " + dec.getName());
            System.out.println("1. Relevant potentials: ");
        
        }

        
        // 1. Set relevant potentials and update the potential set
        RelationList relevant = currentRelations.getRelationsOfAndRemove(dec, Relation.POTENTIAL);
        RelationList relevantUtil = currentRelations.getRelationsOfAndRemove(dec, Relation.UTILITY);

        
        //Potential util = getUtility();
        //removeUtility();

        if (generateDebugInfo) {
            for (int i = 0; i < relevant.size(); i++) {
                Relation r = ((Relation) relevant.getRelations().elementAt(i));
                r.printDomain();
              //  r.print();
            }
            for (int i = 0; i < relevantUtil.size(); i++) {
                Relation r = ((Relation) relevantUtil.getRelations().elementAt(i));
                r.printDomain();
              //  r.print();
            }
        }
 
 
        //2. Reestrict to the rest of variables
        if(generateDebugInfo)
            System.out.println("2. Reestrict to the rest of variables. Resulting potentials:");
        for (int i = relevant.size()-1; i >=0; i--) {
                
                Relation r = ((Relation) relevant.getRelations().elementAt(i));
                relevant.removeElementAt(i);
                NodeList vars = r.getVariables();
                vars.removeNode(dec);

/* System.out.println("~~start reestrict");
 r.printHeadAndTail();
NodeList head = r.getHead();
NodeList tail = r.getTail();
*/
     
                Configuration conf = new Configuration();
                conf.insert((FiniteStates) dec, 0);
                r = r.restrict(conf);
 
/*                head.removeNode(dec);
                tail.removeNode(dec);
                r.setHead(head);
                r.setTail(tail);
                
    r.printHeadAndTail();  
     System.out.println("~~end reestrict");
 */               
                
                relevant.insertRelation(r);
                
                
                
                
                if(generateDebugInfo){
                    r.printDomain();
                 //   r.print();
                }
   
        }
        
        //3. Max-marginalize the utility potential              
        Potential util = maxMarginalizePots(relevantUtil, dec);

       // util.print();
        
        //4. Update the set of all potentials
        Relation utilRel = new Relation(util);
        utilRel.setKind(Relation.UTILITY);
        currentRelations.insertRelation(utilRel);
        for(int i=0; i<relevant.size(); i++) {
            currentRelations.insertRelation(relevant.elementAt(i));
            relevant.printDomainsAndSizes();
        }
        
     //   System.out.println("Current relations: ");
     //   currentRelations.print();

        if (generateDebugInfo) {
            System.out.println("IDSymbolicProbInf:  removeDecision ----- END");
        }
        
       
        
    }
    
    
    public Object[] sumMarginalizePots(Potential potComb, RelationList utilSet, NodeList removableVars) {
    
            Object[] output = new Object[3];
            if(removableVars.size()==0){
                output[0] = potComb;
                output[1] = utilSet;
                output[2] = false;
                
                return output;
            }
            
            if (generateDebugInfo) {
                System.out.println("\nIDSymbolicProbInf:  sumMarginalizePots ----- BEGIN");
                System.out.println("\t1.Relevant utility potentials:");
            }   
            
            boolean unityPot;
            
            // 1.Set the relevant potentials
            RelationList relevantUtil = utilSet.getRelationsOf(removableVars.getNodes(), Relation.UTILITY);
            
            for(int i=0; i<relevantUtil.size(); i++) {
                utilSet.removeRelation(relevantUtil.elementAt(i));
                if(generateDebugInfo) {
                    System.out.println("\t"+relevantUtil.elementAt(i).toString());
                }
                
            }
            
            if(relevantUtil.size()==0) {
                
                Potential newProb = sumMarginalize(potComb, removableVars);
               unityPot = newProb.isUnity();
//                unityPot=false;
                
                output[0] = newProb;
                output[1] = utilSet;
                output[2] = unityPot;
                
                return output;
                
            }
            
            //2. Initialize the combination candidate set
            CombPairTable pairTable = new CombPairTable();
            pairTable.setRemovable(removableVars);
            pairTable.setSetDebugInfo(generateDebugInfo);
            pairTable.setAllowSingletons(true);
            pairTable.setOffset("\t");
            pairTable.setOtherInvolvedPots(potComb);
            
            do{
            
                //3.a 3.b Generate pairwise combinations
                pairTable.addCombinations(relevantUtil);
                

                
                
                if(generateDebugInfo){
                    System.out.println("\t3.a 3.b Generate pairwise combinations:"+pairTable);

                }

                //3.c Select a pair
                CombPair p = pairTable.netxToCombine(heuristic);
                Potential util = p.addition();
                
                System.out.println("~~Addition:");
                util.print();
                
                
                if (generateStatistics) {
                    if(!p.isSinglePot())
                        statistics.addNumSums(binaryOperationCost(util));
                }

                if(generateDebugInfo){
                    System.out.println("\t3.c Select next pair to sum "+p);

                }
                

                //3.d Determine the subset of variables to remove
                NodeList V = p.getRemovableVars();
                if(generateDebugInfo)
                    System.out.println("\t3.d Determine the subset of variables to remove V={"+V.toString2()+"}");
                
                
                //3.e Sum-marginalize
                Potential newProb = sumMarginalize(potComb, V);
 
                
                unityPot=false;
             //   unityPot = newProb.isUnity();
                util = util.combine(potComb);
                
                if(generateStatistics)
                    statistics.addNumMultiplications(binaryOperationCost(util));
                util = sumMarginalize(util, V);

                if(!unityPot) {
                    util = util.divide(newProb);
                    if(generateStatistics)
                        statistics.addNumDivisions(binaryOperationCost(util));
                }
                

              //  util.print();

                if(generateDebugInfo) {
                    System.out.println("\t4.e Resulting potentials of sum-marginalization:");
                    System.out.println("\t"+newProb);
                    System.out.println("\t"+util);
                }
                
                //3.f Remove the original potentials from relevant utility
                // potential set and from candidate combination set
                relevantUtil.removeRelation(p.getR1());
                pairTable.removeContainig(p.getR1());
                if(!p.isSinglePot()) {
                    relevantUtil.removeRelation(p.getR2());
                    pairTable.removeContainig(p.getR2());
                }
                
                //3.g Update potential sets
                potComb = newProb;
                Relation r = new Relation(util);
                r.setKind(Relation.UTILITY);
                relevantUtil.insertRelation(r);
                
                //3.h Update the variables to remove
                removableVars = removableVars.difference(V);
                if(generateDebugInfo) {
                    System.out.println("\t3.h Update the variables to remove from utilities {"+removableVars.toString2()+"}");
                }
                
            }while(removableVars.size()>0);
            
   
            for(int i=0; i<relevantUtil.size(); i++) {
                utilSet.insertRelation(relevantUtil.elementAt(i));
            }
            
            output[0] = potComb;
            output[1] = utilSet;
            output[2] = unityPot;
            
            if (generateDebugInfo) {
                System.out.println("IDSymbolicProbInf:  sumMarginalizePots ----- END");
            } 
            
            
            
            return output;
            
    
    }
    
    
    

    public Potential sumMarginalize(Potential pot, NodeList removableVars) {
        if (removableVars.size() == 0) {
            return pot;
        }

        if(generateStatistics) {
            long numConf = pot.getNumConfigurations();
            for(int i=0; i<removableVars.size(); i++) {
                FiniteStates v = (FiniteStates) removableVars.elementAt(i);
                numConf = numConf/v.getNumStates();
                statistics.addSumMarg(numConf*(v.getNumStates()-1));
            }       
        }
        
        NodeList vars = pot.getNodeList().difference(removableVars);
        Potential newPot = pot.marginalizePotential(vars.toVector());


        return newPot;

    }
    
    
    public Relation sumMarginalizeUtility(Relation util, Relation prob, NodeList removableVars) {
        if (removableVars.size() == 0) {
            return util;
        }

        Potential utilPot = util.getValues();
        Potential probPot = prob.getValues();
        
                
        NodeList vars = util.getVariables().difference(removableVars);
        Potential newPot = util.getValues().marginalizePotential(vars.toVector());
        Relation newRel = new Relation(newPot); 
        newRel.setKind(util.getKind());

        return newRel;

    }
 
 
    public Potential maxMarginalizePots(RelationList relevantUtil, Node dec) {
    
            //1. Initialize the combination candidate set
            CombPairTable pairTable = new CombPairTable();
            pairTable.setRemovable(dec);
            pairTable.setSetDebugInfo(generateDebugInfo);
            pairTable.setAllowSingletons(false);
            pairTable.setOffset("\t"); 
        
            while(relevantUtil.size()>1) {
                
                
                
                //2.a add all pairwise of combinations
                pairTable.addCombinations(relevantUtil);
                
                
                if(pairTable.getSize()==0)
                    System.out.println();
                
                
                //2.b Select next pair to sum
                CombPair p = pairTable.netxToCombine(heuristic);
                Potential util_ij = p.addition();
                if(generateStatistics){
                    if(!p.isSinglePot())
                    statistics.addNumSums(util_ij.getSize());
                }
                
                //2.c Update the candidate combination set
                pairTable.removeContainig(p.getR1());
                pairTable.removeContainig(p.getR2());
                
                //2.d Update the potential set
                relevantUtil.removeRelation(p.getR1());
                relevantUtil.removeRelation(p.getR2());
                
                Relation r = new Relation();
                r.setKind(Relation.UTILITY);
                r.setValues(util_ij);
                relevantUtil.insertRelation(r);
                
                
            
            }
            
            //
            
            //3. Maxmarginalize D
            Potential util_D = ((Relation) relevantUtil.getRelations().elementAt(0)).getValues();
            saveExpectedUtility(util_D);
            return maxMarginalize(util_D, dec);
        
        
    }
    
    
    
    public Potential maxMarginalize(Potential pot, Node n){
        
        //System.out.println("maxMarginalize");
         if(generateStatistics) {
            long numConf = pot.getNumConfigurations();

                FiniteStates v = (FiniteStates) n;
                numConf = numConf/v.getNumStates();
                statistics.addMaxMarg(numConf*(v.getNumStates()-1));
     
        }
        
        NodeList vars = pot.getNodeList();
        vars.removeNode(n);
        Potential newPot = pot.maxMarginalizePotential(vars.toVector());
        
       
        return newPot;
    
    }

    public HashMap<Node, Potential> getResultsMap() {
        return resultsMap;
    }

    public void setResultsMap(HashMap<Node, Potential> resultsMap) {
        this.resultsMap = resultsMap;
    }

    public boolean isSimetric() {
        return simetric;
    }

    public void setSimetric(boolean simetric) {
        this.simetric = simetric;
    }
    

    
    private long unaryOperationCost(Potential pot, NodeList removableVars) {
            
        long size = 0;
        
        if(pot instanceof PotentialBPTree)
            return 0;
        
        
        long numConf = pot.getNumConfigurations();
            for(int i=0; i<removableVars.size(); i++) {
                FiniteStates v = (FiniteStates) removableVars.elementAt(i);
                numConf = numConf/v.getNumStates();
                size += numConf*(v.getNumStates()-1);
            
            
            }  
            return size;
    }

    private long unaryOperationCost(Potential pot, Node n) {
        
        if(pot instanceof PotentialBPTree)
                return 0;        

        long numConf = pot.getNumConfigurations();

        FiniteStates v = (FiniteStates) n;
        numConf = numConf/v.getNumStates();
        return numConf*(v.getNumStates()-1);

    }
    
    
    private long binaryOperationCost(Potential pot) {
    

        if(pot instanceof PotentialBPTree)
                return 0;  
        

        return pot.getSize();
        
        
    }
    

    private Potential getUtility() {
        Vector relations = currentRelations.getRelations();
        for (int i = 0; i < relations.size(); i++) {
            Relation r = (Relation) relations.get(i);
            if (r.getKind() == Relation.UTILITY) {
                return r.getValues();
            }
        }

        return null;
    }
    
    
  

    private void removeUtility() {
        Vector relations = currentRelations.getRelations();
        for (int i = 0; i < relations.size(); i++) {
            Relation r = (Relation) relations.get(i);
            if (r.getKind() == Relation.UTILITY) {
                currentRelations.removeRelation(r);
                break;
            }
        }

    }

    private void saveExpectedUtility(Potential util) {
        Potential utilCopy = util.copy();
        this.statistics.addUtilityHistory(utilCopy);
        results.add(utilCopy);
        
    }


    private void startMeasureOverHead() {
        if(measureOverHead)
            initTime = System.nanoTime();
    
    }
    
    
    private void stopMeasureOverHead() {
        
        if(measureOverHead) {
            endTime = System.nanoTime();
            addOverHeadTime(endTime-initTime);
        }
    }

    
    public void printAllRelations(boolean onlyDomain) {
        
        
        if(!generateDebugInfo)
            return;
        
        System.out.println("Current Relations domains:");
        System.out.println("--------------------------");
        for(int i=0; i<currentRelations.size(); i++) {
            currentRelations.elementAt(i).printDomain();
        }
        
        
        
        if(!onlyDomain) {
            System.out.println("Current Relations potentials:");
            System.out.println("--------------------------");
            for(int i=0; i<currentRelations.size(); i++) {
                currentRelations.elementAt(i).print();
            }
        }
        
    
    }

    /**
     * GET and SET methods
     */
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

    
    
    
    
    public void setAllowSingletonProbs(boolean allowSingletonProbs) {
        this.allowSingletonProbs = allowSingletonProbs;
    }

    public void setGenerateDebugInfo(boolean generateDebugInfo) {
        this.generateDebugInfo = generateDebugInfo;
    }
    
    
      public int getHeuristic() {
        return heuristic;
    }

    public void setHeuristic(int heuristic) {
        this.heuristic = heuristic;
    }

    public boolean isQualitativeEval() {
        return qualitativeEval;
    }

    public void setQualitativeEval(boolean qualitativeEval) {
        this.qualitativeEval = qualitativeEval;
    }
    
      
    
    
    
/**
 *  Methods as described in paper for PGM 14
 */    


    

    
    
    
     public void removeChanceSet(NodeList X, ArrayList<Potential> probSet, ArrayList<Potential> utilSet) {
   
        if(probSet.size()==0)
             return;
    
          if (generateDebugInfo) {
        System.out.println("Remove chance set: "+X.toString2());
          }
       NodeList W=null;
       
       // 1. Initialize the combination candidate set
       
       
       CombPairTable B = new CombPairTable();
       B.setAllowSingletons(allowSingletonProbs);
       B.setRemovable(X);
       B.setSetDebugInfo(generateDebugInfo);
       B.setCheckUnityPots(checkUnity);
       B.setSimetric(simetric);
       B.setCheckMarginalChild(true);
       B.setOtherInvolvedPots(utilSet);
       B.setNotInvolvedRel(currentRelations);
    
       
       if(generateStatistics) {
           if(currentRelations != null) {
            numNodesNotInvolved = (long) currentRelations.sumNumNodes();
            sizesNotInvolved = (long) currentRelations.sumSizes();
           }else{
               numNodesNotInvolved = 0;
               sizesNotInvolved = 0;
           }
       }
       

       // 2. Repeat
       do {
           
    
           // 2.a 2.b add all pairwise combinations and singletons
           startMeasureOverHead();
           B.addCombinations(probSet);
           stopMeasureOverHead();
           
           
           if (generateDebugInfo) {
               System.out.println("2.a 2.b Generate pairwise combinations:");
               B.printPairs();
               System.out.println();
            }
           
            //2.c Select next to combine
           startMeasureOverHead();
           CombPair p = B.netxToCombine(heuristic);
           stopMeasureOverHead();

           
            //2.d Combine potentials
            Potential prob_ij = null;
            

            
            if(p.isPair()){
                prob_ij = p.getPot1().combine(p.getPot2());
                prob_ij = applyCombConstraints(prob_ij);

                if (generateStatistics) 
                    statistics.addNumMultiplications(binaryOperationCost(prob_ij));
                
            }else{
                prob_ij = p.getPot1();
                if (generateStatistics) 
                    statistics.addSingletons(1);
            }
            
            if(generateStatistics) {
                numNodesProb = (long) (B.getAllRelationsWithout(p).sumNumNodes() + prob_ij.getNumberOfNodes());
                sizesProb = (long) (B.getAllRelationsWithout(p).sumSizes() + prob_ij.getSize());

            }
            if (generateDebugInfo) {
               System.out.println("2.c Select next to combine: " +p+" "+prob_ij.getSize());
           }

            
            
            if(generateDebugInfo)
                System.out.println("2.d Combine potentials: "+prob_ij);
            
           //2.f Determine variables that can be removed
           startMeasureOverHead();
           W = p.getRemovableVars();
           stopMeasureOverHead();
           if (generateDebugInfo) {
               System.out.println("2.e Determine variables that can be removed: {" + W.toString2()+"}");
           }
         
           //2.g Sum-marginalize
           ArrayList<Potential> util_W = getPotentialsWithAny(utilSet, W, true); //removes from util_X
           if(W.size()>0) {
                Object[] ret = sumMarginalize(W, prob_ij, util_W);
                prob_ij = (Potential) ret[0];
                util_W =  (ArrayList<Potential>) ret[1];
                
           }
           


           //2.h Update
           
           X = X.difference(W);
           probSet.remove(p.getPot1());
           startMeasureOverHead();
           B.removeContainig(p.getPot1());
           if(p.isPair()) {
               probSet.remove(p.getPot2());
               B.removeContainig(p.getPot2());
           }
           stopMeasureOverHead();
           

           
           if(prob_ij != null)
            probSet.add(prob_ij);
           utilSet.addAll(util_W);
           

           
           

       } while (X.size() > 0);


   }
   
 
  private Object[] sumMarginalize(NodeList W, Potential prob, ArrayList<Potential> utilSet) {

      
             //2. There is not utilities
       if(utilSet.size()==0){
            NodeList vars = prob.getNodeList().difference(W);
            
            if(generateStatistics)
                statistics.addSumMarg(unaryOperationCost(prob, W));
            
            prob = prob.marginalizePotential(vars.toVector());
            Object[] ret = new Object[2];
            ret[0] = prob;
            ret[1] = utilSet;
            return ret;
       } 
      

       // 1. Initialize the combination candidate set
       CombPairTable B = new CombPairTable();
       B.setAllowSingletons(true);
       B.setRemovable(W);
       B.setSetDebugInfo(generateDebugInfo);
       B.setOffset("\t");
       B.setSimetric(simetric);
       
       
       long numNodesU = 0;
       
       
        NodeList V=null;
       
       

       
         // 2. Repeat
       do {
           
           // 2.a 2.b add all pairwise combinations and singletons
           startMeasureOverHead();
           B.addCombinations(utilSet);
           stopMeasureOverHead();
           
           if (generateDebugInfo) {
               System.out.println("2.a 2.b Generate pairwise combinations:");
               B.printPairs();
               System.out.println();
            }
           
            //2.c Select next to combine
           CombPair q = B.netxToCombine(heuristic);
           if (generateDebugInfo) {
               System.out.println("2.c Select next to combine: " + q);
           }
           
           
           //2.d Sum the potentials
            Potential util_ij = null;
            if(q.isPair()){
                util_ij = q.getPot1().addition(q.getPot2());
                if(generateStatistics)
                    statistics.addNumSums(binaryOperationCost(util_ij));
            }else{
                util_ij = q.getPot1();
            } 
            

            
            
           //2.e Determine variables that can be removed
           V = q.getRemovableVars();
           if (generateDebugInfo) {
               System.out.println("2.e Determine variables that can be removed: {" + V.toString2()+"}");
           }
           
           
           //2.f Sum-marginalize
           if (V.size() > 0) {
               
               
               //Combines utility and probability
               if (prob != null) {
                   util_ij = util_ij.combine(prob);
                   if (generateStatistics) {
                       statistics.addNumMultiplications(binaryOperationCost(util_ij));
                   }
               }

               
                if(generateStatistics) {
                    
                    //Statistics of num. nodes before marg.
                    numNodesUtil = (long) (B.getAllRelationsWithout(q).sumNumNodes() + util_ij.getNumberOfNodes());
                    sizesUtil = (long) (B.getAllRelationsWithout(q).sumSizes() + util_ij.getSize());

                    saveStorageStats();
                    
                    //Substracts nodes nodes of potentials that are going to be marg.
                    numNodesProb -= prob.getNumberOfNodes();
                    numNodesUtil -= util_ij.getNumberOfNodes();
                    
                    sizesProb -= prob.getSize();
                    sizesUtil -= util_ij.getSize();
                    
                }
                   
                   

               
               
               
               //Marginalizes from utility
               NodeList vars = util_ij.getNodeList().difference(V);               
               if(generateStatistics){
                   statistics.addSumMarg(unaryOperationCost(util_ij, V));
                   numNodesU -= util_ij.getNumberOfNodes();
               }               
               
               util_ij = util_ij.marginalizePotential(vars.toVector());
               

               //Marginalizes from probability and divides         
               boolean isUnity = Potential.generatesUnityPot(prob, V);
               if (!isUnity || !checkUnity) {
                   vars = prob.getNodeList().difference(V);
                   if (generateStatistics) {
                       statistics.addSumMarg(unaryOperationCost(prob, V));
                       
                       
                   }
                   
                   prob = prob.marginalizePotential(vars.toVector());
                   util_ij = util_ij.divide(prob);
                   if(generateStatistics) 
                        statistics.addNumDivisions(binaryOperationCost(util_ij));
 
               } else {
                   prob = null;
                   
                   if(generateStatistics)
                       statistics.addNumProbBarren(V.size());
                   if(generateDebugInfo)
                       System.out.println("No se realiza la marginalización ni la división... potencial unidad");
               }
               
               

               
               
               
               //Comprobación a posteriori
               if(isUnity)
                   prob = null;

               
               if(generateStatistics) {
                   numNodesUtil += util_ij.getNumberOfNodes();
                   sizesUtil += util_ij.getSize(); 
                   
                   if(prob != null) {
                        numNodesProb += prob.getNumberOfNodes();
                        sizesProb += prob.getSize();
                   }
                   
                   saveStorageStats();
                    
               }
               
               
               prob = applyRemConstraints(prob);
               util_ij = applyRemConstraints(util_ij);
                   
               
               if(generateDebugInfo) {
                   System.out.println("Resulting potentials of sum-marginalization");
                   if(prob != null)
                        System.out.println(prob.toString());
                   System.out.println(util_ij.toString());
               }
               
               
           }
           
           
           //2.h Update
           startMeasureOverHead();
           W = W.difference(V);
           utilSet.remove(q.getPot1());
           B.removeContainig(q.getPot1());
           if(q.isPair()) {
               utilSet.remove(q.getPot2());
               B.removeContainig(q.getPot2());
           }
           utilSet.add(util_ij);
           stopMeasureOverHead();
           
           
       }while(W.size()>0);
       
       
        Object[] ret = new Object[2];
        ret[0] = prob;
        ret[1] = utilSet;
        return ret;
       
       
   }
    
   
   
   public void removeDecision(Node D, ArrayList<Potential> probSet, ArrayList<Potential> utilSet) {
       
       
       if(generateStatistics) {
           
           numNodesNotInvolved = 0;
           sizesNotInvolved = 0;
           numNodesProb = 0; 
           sizesProb = 0;
           if(currentRelations !=null) {
            numNodesNotInvolved = (long) currentRelations.sumNumNodes();                      
            sizesNotInvolved = (long) currentRelations.sumSizes();
            
           }
       }
       
       
        if(generateDebugInfo)
            System.out.println("1. Reestrict to the rest of variables. Resulting potentials:");
        
        for(Potential pot : probSet) {
            
            if (generateDebugInfo) 
                pot.printDomain();
            
            probSet.remove(pot);
            Potential newPot = null;
            
            NodeList vars = pot.getNodeList();
            vars.removeNode(D);
            Configuration conf = new Configuration();
            conf.insert((FiniteStates) D, 0);
            newPot = pot.restrictVariable(conf);
            
            if(pot.getHead()!=null){
                newPot.setHead(Potential.getMargHead(pot, D));
                newPot.setTail(Potential.getMargTail(pot, D));
            }
            
            probSet.add(newPot);
            
            if(generateStatistics) {
                numNodesProb += pot.getNumberOfNodes();
                sizesProb += pot.getSize();
            }
            
            if(generateDebugInfo)
                pot.printDomain();
           
            
        }
        
        

        
        if(utilSet.size()==0)
            return;
        
        Potential newUtil = maxMarginalize(D, utilSet);
        utilSet.remove(0);
        utilSet.add(newUtil);
   
   }
   
   
   private Potential maxMarginalize(Node D, ArrayList<Potential> utilSet) {
       
       

       
       
       // 1. Initialize the combination candidate set
       CombPairTable B = new CombPairTable();
       B.setAllowSingletons(false);
       B.setRemovable(D);
       B.setSetDebugInfo(generateDebugInfo);
       B.setOffset("\t"); 
       B.setSimetric(simetric);
       
       while (utilSet.size()>1) {           
           
           //2.a Add al pairwise of combinations
           startMeasureOverHead();
           B.addCombinations(utilSet);
           stopMeasureOverHead();
           
           if (generateDebugInfo) {
               System.out.println("2.a Generate pairwise combinations:");
               B.printPairs();
            }
           
           //2.b Select next to combine: 
           CombPair q = B.netxToCombine(heuristic);
           if (generateDebugInfo) {
               System.out.println("2.b Select next to combine: " + q);
           }
           
           // Sum both potentials
           Potential util_ij = q.getPot1().addition(q.getPot2());
           util_ij = applyCombConstraints(util_ij);
           
           
           if(generateStatistics)
               statistics.addNumSums(binaryOperationCost(util_ij));
           
           startMeasureOverHead();
           B.removeContainig(util_ij);
           utilSet.remove(q.getPot1());
           utilSet.remove(q.getPot2());
           utilSet.add(util_ij);
           stopMeasureOverHead();
  
       }
       
        Potential util = utilSet.get(0);
        
      //  util = transformExpectedUtility(util);
        if(resultsMap != null)
            resultsMap.put(D, util);
        
        if(results!=null)
            saveExpectedUtility(util);
        
        
        
        NodeList vars = util.getNodeList();
        vars.removeNode(D);
        
        if(generateStatistics) {
            statistics.addMaxMarg(unaryOperationCost(util, D));
            numNodesUtil = util.getNumberOfNodes();
            sizesUtil = util.getSize();
            saveStorageStats();
        }
        Potential newUtil = util.maxMarginalizePotential(vars.toVector());
        
        
        if(generateStatistics) {
            numNodesUtil = newUtil.getNumberOfNodes();
            sizesUtil = newUtil.getSize();
            saveStorageStats();
        }
        
        
        newUtil = transformExpectedUtility(newUtil);
        
       
       return newUtil;
       
   
   }
   
   
    private ArrayList<Potential> getPotentialsWithAny(ArrayList<Potential> pots, NodeList vars, boolean removeFlag) {
    
        ArrayList<Potential> pots_vars = new ArrayList<Potential>();
        if(vars == null)
            return pots_vars;
        
        
        for(int i=pots.size()-1; i>=0; i--){
            Potential p = pots.get(i);
            
            
            if(p == null || vars==null) {
                System.out.print("");
            }
            
            if(!VectorManipulator.intersection(p.getVariables(), vars.getNodes()).isEmpty()) {
                pots_vars.add(p);
                if(removeFlag)
                    pots.remove(p);
            }
        }
        
        return pots_vars;
        
    }
     
    
    private ArrayList<Potential> getCurrentProbWithAndRemove(NodeList chanceSet) {
            
            
        // 1. Set relevant potentials
        RelationList relevant = currentRelations.getRelationsOf(chanceSet.getNodes(), Relation.POTENTIAL);    
        ArrayList<Potential> relevantArray = new ArrayList<Potential>();
        
        
        // 2. Update potential Set
        for (int i = 0; i < relevant.size(); i++) {
            Relation r = ((Relation) relevant.getRelations().elementAt(i));
            currentRelations.removeRelation(r);
            relevantArray.add(r.getValues());
            if (generateDebugInfo) {
                r.printDomain();
            }
        }
        
        
        return relevantArray;


    }
    
    
    private ArrayList<Potential> getCurrentUtilWithAndRemove(NodeList chanceSet) {
            
            
        // 1. Set relevant potentials
        RelationList relevant = currentRelations.getRelationsOf(chanceSet.getNodes(), Relation.UTILITY);    
        ArrayList<Potential> relevantArray = new ArrayList<Potential>();
        
        
        // 2. Update potential Set
        for (int i = 0; i < relevant.size(); i++) {
            Relation r = ((Relation) relevant.getRelations().elementAt(i));
            currentRelations.removeRelation(r);
            relevantArray.add(r.getValues());
            if (generateDebugInfo) {
                r.printDomain();
            }
        }
        
        
        return relevantArray;


    }
     
    private ArrayList<Potential> getCurrentProbWithAndRemove(Node n) {
            
            
        NodeList nodeSet = new NodeList();
        nodeSet.insertNode(n);
        
        return getCurrentProbWithAndRemove(nodeSet);


    }
    
    
    private ArrayList<Potential> getCurrentUtilWithAndRemove(Node n) {
            
        NodeList nodeSet = new NodeList();
        nodeSet.insertNode(n);
        
        return getCurrentUtilWithAndRemove(nodeSet);

    }
    
    
    private void insertInCurrentRelations(ArrayList<Potential> pots, int kind) {
    
        for(int i=0; i<pots.size(); i++) {
            Relation r = new Relation(pots.get(i));
            r.setKind(kind);
            currentRelations.insertRelation(r);
        }
    
    }

    
    Potential applyCombConstraints(Potential prob_ij) {
      
        if(prob_ij==null)
            return null;
        
        // Now, check the possible presence of constraints on this relation
        if (this instanceof IDSPIWithPotentialBPTree) {

            if (((IDSPIWithPotentialBPTree) this).isCombinationConstraints()) {
                PotentialBPTree potTreeConstraint = (PotentialBPTree) ((IDiagram) network).applyConstraintsOnPotential(prob_ij, false);

                //Discards the resulting tree if it is larger
                if (potTreeConstraint.getSize() < prob_ij.getSize()) {
                    prob_ij = potTreeConstraint;
                }
            }
        }

        return prob_ij;



    }
    
    
    Potential applyRemConstraints(Potential prob_ij) {
            
        if(prob_ij==null)
            return null;
            
        // Now, check the possible presence of constraints on this relation
        if (this instanceof IDSPIWithPotentialBPTree) {

            if (((IDSPIWithPotentialBPTree) this).isRemovalConstraints()) {
                PotentialBPTree potTreeConstraint = (PotentialBPTree) ((IDiagram) network).applyConstraintsOnPotential(prob_ij, false);

                //Discards the resulting tree if it is larger
                if (potTreeConstraint.getSize() < prob_ij.getSize()) {
                    prob_ij = potTreeConstraint;
                }
            }
        }

        return prob_ij;



    }

    private Long sumNodes(ArrayList<Potential> pots) {
        long sum = 0;
        for (int i = 0; i < pots.size(); i++) {
            sum += pots.get(i).getNumberOfNodes();
                   
        }
        return sum;
                    
    }

    private void saveStorageStats() {
   //     System.out.print(numNodesNotInvolved+" "+numNodesProb+" "+numNodesUtil+" = "+(numNodesNotInvolved+numNodesProb+numNodesUtil));
   //     System.out.println("\t - \t"+sizesNotInvolved+" "+sizesProb+" "+sizesUtil+" = "+(sizesNotInvolved+sizesProb+sizesUtil));

        
        statistics.addNumberNodes(numNodesNotInvolved+numNodesProb+numNodesUtil);
        statistics.addTotalSize(sizesNotInvolved+sizesProb+sizesUtil);
    }
    
    
    public boolean isCheckUnity() {
        return checkUnity;
    }

    public void setCheckUnity(boolean checkUnity) {
        this.checkUnity = checkUnity;
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
     * Test method 
     ****
     */
    
    

    
    
    public static void main(String[] args) throws ParseException, IOException {
        // TODO code application logic here

        String idFile;
        
      //  idFile = "ids/jensen1util.elv";
      
        
        idFile = "src/elvira/samples/ids/nhl.elv";
               idFile = "ids/anders/dsepDec.elv"; 
               
        idFile = "ids/jensen2.elv"; 
        idFile = "ids/spi/hospi_red3.elv";
                idFile = "ids/spi/motivation_not_binary.elv";
idFile = "ids/jensen2.elv";
idFile = "ids/lazyspi/test3.elv";
        
        //int H[] = {CombPairTable.MIN_SIZE, CombPairTable.MIN_SIZE_MARG, CombPairTable.DAMBROSIO};
        int H[] = {CombPairTable.MIN_WEIGHT_ONLY_PAIR};
        
        for(int i=0; i<H.length; i++ ){
            IDiagram id = new IDiagram(idFile);
            IDSymbolicProbInf spi = new IDSymbolicProbInf(id);
            spi.generateStatistics=true;
            spi.generateDebugInfo=true;
            spi.setHeuristic(H[i]);
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
}
