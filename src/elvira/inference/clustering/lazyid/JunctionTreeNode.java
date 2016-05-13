package elvira.inference.clustering.lazyid;

import com.sun.corba.se.spi.monitoring.StatisticMonitoredAttribute;
import elvira.*;
import elvira.inference.symbolic.ids.IDSymbolicProbInf;
import elvira.tools.idiagram.pairtable.IDPairTable;
import elvira.potential.Potential;
import elvira.potential.PotentialTree;
import elvira.potential.binaryprobabilitytree.PotentialBPTree;
import elvira.tools.VectorManipulator;
import elvira.tools.idiagram.pairtable.CombPair;
import elvira.tools.idiagram.pairtable.CombPairTable;

import java.util.*;

public class JunctionTreeNode {

   /**
    * Data member to store the list of variables defined on this clique
    */
   NodeList variables;
   /**
    * Data member to store the upward neighbour of the node
    */
   JunctionTreeSeparator up;
   /**
    * Set of separators to store the downward neighbours of the node
    */
   ArrayList<JunctionTreeSeparator> down;
   /**
    * Set of probability potentials related to the clique represented with this
    * node
    */
   HashMap<Node, ArrayList<Potential>> probPotentials;
   
      /**
    * Set of probability potentials related to the clique represented with this
    * node. It is used to avoid removing potentials from clique
    */
   HashMap<Node, ArrayList<Potential>> probPotentialsAux;
   /**
    * Set of utility potentials related to the clique represented with this node
    */
   
   HashMap<Node, ArrayList<Potential>> utilPotentials;
      /**
    * Set of utility potentials related to the clique represented with this node
    * It is used to avoid removing potentials from clique
    */
   
   HashMap<Node, ArrayList<Potential>> utilPotentialsAux;
   
   
   
   /**
    * Set of constraint potentials related to the collectEvidenceclique
    * represented with this node
    */
   HashMap<Node, ArrayList<Potential>> constraintPotentials;
   /**
    * Data member to store the index of the clique
    */
   int index;
   /**
    * Data member to store a reference to the StrongJunctionTree where the node
    * is inserted
    */
   protected StrongJunctionTree tree;
   /**
    * Data member to store the debug flag
    */
   protected boolean generateDebugInfo;
   
   
   protected boolean liberate = true;
   
   //Operation heuristic overhead
   protected long initTime, endTime;


   /**
    * Constructor
    *
    * @param tree
    * @param variables
    * @param index
    */
   public JunctionTreeNode(StrongJunctionTree tree, NodeList variables, int index, boolean debflag) {
      generateDebugInfo = debflag;

      if (generateDebugInfo) {
         System.out.println("JunctionTreeNode:  class Constructor of Clique "+index+" ----- BEGIN");
      }

      this.tree = tree;
      this.variables = variables;

      // Assign the index
      this.index = index;

      //Create the arraylist for downward neighbours
      down = new ArrayList<JunctionTreeSeparator>();

      // Create the map to store the probability potentials
      probPotentials = new HashMap<Node, ArrayList<Potential>>();
      // The same for utility potentials
      utilPotentials = new HashMap<Node, ArrayList<Potential>>();

      if (generateDebugInfo) {
         System.out.println("JunctionTreeNode:  class Constructor Clique "+index+" ----- END");
      }
   }

   /**
    * Method to print the data of the junction tree node
    */
   public void print() {
      JunctionTreeSeparator separator;
      JunctionTreeNode neighbour;
      Node node;

      System.out.println("-------- CLIQUE WITH INDEX: " + index + " ------------");
      // Print the variables of the clique
      System.out.println("Index: " + index);
      System.out.println("\n\n ----------------- Variables -------------------");
      for (int i = 0; i < variables.size(); i++) {
         node = variables.elementAt(i);
         System.out.println(node.getName());
      }

      // Print the potentials related to the clique, variable to
      // variable
      System.out.println(" ........ PROBABILITY POTENTIALS ..........");
      ArrayList<Potential> pots;
      Potential pot;
      for (int i = 0; i < variables.size(); i++) {
         node = variables.elementAt(i);
         System.out.println("... Para variable: " + node.getName());
         pots = probPotentials.get(node);
         if (pots != null) {
            for (int j = 0; j < pots.size(); j++) {
               System.out.println("-----------------" + j + "------------------");
               pot = pots.get(j);
               //pot.print();
               System.out.println("-----------------------------------------");
            }
         }
      }
      System.out.println(" ........ UTILITY POTENTIALS ..........");
      for (int i = 0; i < variables.size(); i++) {
         node = variables.elementAt(i);
         System.out.println("... Para variable: " + node.getName());
         pots = utilPotentials.get(node);
         if (pots != null) {
            for (int j = 0; j < pots.size(); j++) {
               System.out.println("-----------------" + j + "------------------");
               pot = pots.get(j);
               pot.print();
               System.out.println("-----------------------------------------");
            }
         }
      }

      for (int i = 0; i < down.size(); i++) {
         separator = down.get(i);

         // Call the print method on it
         System.out.println("    Down separator number: " + i);
         separator.print();
      }

      // Print now the inferior neighbours
      for (int i = 0; i < down.size(); i++) {
         separator = down.get(i);
         neighbour = separator.getInferiorNeighbour();

         // Call the method to print the clique
         System.out.println("        Inferior neighbour: " + i);
         neighbour.print();
      }
   }

   /**
    * Method to print the data of the junction tree node without traversing the
    * tree: only the node it is printed
    */
   public void printAlone() {
      JunctionTreeSeparator separator;
      JunctionTreeNode neighbour;
      Node node;

      // Print the variables of the clique
      System.out.println("Indice: " + index);
      System.out.println("\n\n ----------------- Variables -------------------");
      for (int i = 0; i < variables.size(); i++) {
         node = variables.elementAt(i);
         System.out.println(node.getName());
      }

      // Print the potentials related to the clique, variable to
      // variable
      System.out.println(" ........ PROBABILITY POTENTIALS ..........");
      ArrayList<Potential> pots;
      Potential pot;
      for (int i = 0; i < variables.size(); i++) {
         node = variables.elementAt(i);
         System.out.println("... Para variable: " + node.getName());
         pots = probPotentials.get(node);
         if (pots != null) {
            for (int j = 0; j < pots.size(); j++) {
               System.out.println("-----------------" + j + "------------------");
               pot = pots.get(j);
               pot.print();
               System.out.println("-----------------------------------------");
            }
         }
      }
      System.out.println(" ........ UTILITY POTENTIALS ..........");
      for (int i = 0; i < variables.size(); i++) {
         node = variables.elementAt(i);
         System.out.println("... Para variable: " + node.getName());
         pots = utilPotentials.get(node);
         if (pots != null) {
            for (int j = 0; j < pots.size(); j++) {
               System.out.println("-----------------" + j + "------------------");
               pot = pots.get(j);
               pot.print();
               System.out.println("-----------------------------------------");
            }
         }
      }

      for (int i = 0; i < down.size(); i++) {
         separator = down.get(i);

         // Call the print method on it
         System.out.println("    Down separator number: " + i);
         separator.print();
      }
   }

   /**
    * Public method for printing the structure of the tree
    *
    * @param white spaces to print
    */
   public void printStructure(int spaces) {
      int newSpaces;
      JunctionTreeNode inferiorNeigbour;

      // Print the index
      System.out.println(index);
      // Print the white spaces
      for (int i = 0; i < spaces; i++) {
         System.out.print(" ");
      }
      System.out.println(" |");

      // Consider the inferior separators and print them
      for (JunctionTreeSeparator separator : down) {
         // Print the structure of the separator and follow with the
         // node related to it
         newSpaces = spaces + 2;
         separator.printStructure(newSpaces);
      }
   }
   
      /**
    * Public method for printing the structure of the tree
    *
    * @param white spaces to print
    */
   public void printStructureNumPots(int spaces) {
      int newSpaces;
      JunctionTreeNode inferiorNeigbour;

      // Print the index
      System.out.println(index+"("+probPotentials.size()+","+utilPotentials.size()+")");
      // Print the white spaces
      for (int i = 0; i < spaces; i++) {
         System.out.print(" ");
      }
      System.out.println("           |");

      // Consider the inferior separators and print them
      for (JunctionTreeSeparator separator : down) {
         // Print the structure of the separator and follow with the
         // node related to it
         newSpaces = spaces + 2;
         separator.printStructureNumPots(newSpaces);
      }
   }
   
   

   /**
    * Public method to accomodate a clique into a node
    *
    * @param clique to connect
    * @param intersection separator of the clique
    * @param index of the clique
    * @return boolean condition
    */
   public boolean accomodate(NodeList clique, NodeList separator, int index) {
      JunctionTreeSeparator separatorNode;
      int trials = 0;
      boolean accomodated = false;

      if (generateDebugInfo) {
         System.out.println("JunctionTreeNode:  accomodate Clique "+index+" ----- BEGIN");
      }

      // Check if the variables in separator are contained in
      // the set of variables of this clique
      if (separator.size() != 0 && separator.isIncluded(variables)) {
         // If the separator is contained in the variables of
         // this node, add it as down neighbour. First at all,
         // create a new node
         JunctionTreeNode node = buildTreeNode(tree, clique, index);

         // Add it as down neighbour
         addDownwardNeighbour(node, separator);

         // Return true
         accomodated = true;
      } else {
         // Recursively call to this method on the downward
         // neighbours untill we receive a true as answer
         while (!accomodated && trials < down.size()) {
            // Select a down neighbour
            separatorNode = down.get(trials);

            // Call accomodate on node
            accomodated = separatorNode.getInferiorNeighbour().accomodate(clique, separator, index);

            // Add one to trials
            trials++;
         }
      }

      if (generateDebugInfo) {
         System.out.println("JunctionTreeNode:  accomodate Clique "+index+" ----- END");
      }

      // Return the value of accomodated
      return accomodated;
   }

   /**
    * Method for building a new tree node
    *
    * @param tree
    * @param clique nodelist to include in the node
    * @param index of the node
    */
   protected JunctionTreeNode buildTreeNode(StrongJunctionTree tree,
           NodeList clique, int index) {

      if (generateDebugInfo) {
         System.out.println("JunctionTreeNode:  buildTreeNode en clique "+index+" ----- BEGIN");
      }
      JunctionTreeNode node = new JunctionTreeNode(tree, clique, index, generateDebugInfo);
      if (generateDebugInfo) {
         System.out.println("JunctionTreeNode:  buildTreeNode en clique "+index+" ----- END");
      }
      return node;
   }

   /**
    * Method for applying constraints
    *
    * @param boolean flag for showing if inferior neigbourghs must be considered
    */
   protected void applyConstraints(boolean goDown) {
   }

   /**
    * Method for collectEvidence. This method will be allways called from the
    * superior neighbour
    */
   public void collectEvidence() {
      JunctionTreeNode inferiorNeighbour;

      if (generateDebugInfo) {
         System.out.println("JunctionTreeNode:  collectEvidence en clique "+index+" ----- BEGIN");
      }

      // The method check if the inferior neighbour is a leaf node. In
      // such case, there is no more invocations to collect: only to
      // absorb
      for (int i = 0; i < down.size(); i++) {
         inferiorNeighbour = down.get(i).getInferiorNeighbour();

         // Check if it is a leaf node
         if (inferiorNeighbour.isLeafNode()) {
            // Call to absorbEvidence on it. This will make the marginalizations
            // required at it will charge the potentials in the separator
            inferiorNeighbour.absorbEvidence();
         } else {
            // It is needed a par of calls: collect and after that absorb
            inferiorNeighbour.collectEvidence();
            inferiorNeighbour.absorbEvidence();
         }
      }

      if (generateDebugInfo) {
         System.out.println("JunctionTreeNode:  collectEvidence en clique "+index+" ----- END");
      }
   }

   /**
    * Method for absorbing the evidence from inferior neighbours
    */
   public void absorbEvidence() {
      ArrayList<Node> varsToRemove = new ArrayList<Node>();
      Node node;
      int variableEliminationCriteria = tree.getVariableEliminationCriteria();

      if (generateDebugInfo) {
         System.out.println("JunctionTreeNode:  absorbEvidence en clique "+index+" ----- BEGIN");
      }
      
      ArrayList<Potential> arrayListPot = getArrayListPot(probPotentials, true);
      createPotentialMap(arrayListPot);

      // Determine the variables to remove
      for (int i = 0; i < variables.size(); i++) {
         node = variables.elementAt(i);

         // Check if the node it is in the separator list of variables
         if (!up.isVariablePresent(node)) {
            // This variable must be deleted
            varsToRemove.add(node);
         }
      }


      // If the array list contains more than two variables,  then
      // they must be ordered respect to the criteria used:
      // (a) elimination order used for triangulation
      // (b) on-line triangulation with the same criteria employed
      // for triangulation of the whole graph
      if (varsToRemove.size() > 1) {
         switch (variableEliminationCriteria) {
            case StrongJunctionTree.OFFLINE_TRIANGULATION:
            case StrongJunctionTree.SYMBOLIC_PROB_INFERENCE:    
               varsToRemove = tree.orderVariablesWithOfflineTriangulation(varsToRemove);
               break;
            case StrongJunctionTree.ONLINE_TRIANGULATION:
               startMeasureOverHead();
               varsToRemove = orderVariablesWithOnlineTriangulation(varsToRemove);
               stopMeasureOverHead();
               break;
            default:
               System.out.println("Invalid criteria for ordering variables to remove");
               System.out.println("Method: absorbEvidence     Class: JunctionTreeNode");
               System.exit(0);
         }
      }

      // Finally, call the method for removing the variables
      removeVariables(varsToRemove);
      if (generateDebugInfo) {
         System.out.println("JunctionTreeNode:  absorbEvidence en clique "+index+" ----- END");
      }
   }

   /**
    * Method for ordering the variables to remove with an on-line triangulation.
    * It will be used the same criteria employed for the triangulation of the
    * whole graph
    *
    * @param varsToRemove array with the variables to order
    * @param orderedVars
    */
   private ArrayList<Node> orderVariablesWithOnlineTriangulation(ArrayList<Node> varsToRemove) {
      IDPairTable pairTable = new IDPairTable();
      RelationList relationsForVar;
      Relation relation;
      FiniteStates variable;
      ArrayList<RelationList> relationsForVars;
      ArrayList<Node> orderedVars = new ArrayList<Node>();
      Node varToRemove;

      if (generateDebugInfo) {
         System.out.println("JunctionTreeNode:  orderVariablesWithOnlineTriangulation ----- BEGIN");
      }

      // Fix the criteria for the triangulation
      pairTable.setIDCriteria(tree.getTriangulationCriteria());

      // It is needed to get the relations containing the variables of the potentials for
      // every variable to remove
      for (int i = 0; i < varsToRemove.size(); i++) {
         variable = (FiniteStates) varsToRemove.get(i);

         // Make relations from the potentials where var appears
         relationsForVar = makeRelationsFromVarPotentials(variable);

         // Add the variable and the relations to the pairTable
         pairTable.addElement(variable);

         // Add the relations
         for (int j = 0; j < relationsForVar.size(); j++) {
            relation = relationsForVar.elementAt(j);
            pairTable.addRelation(variable, relation);
         }
      }

      // Now the variables must be ordered according to the selected criteria

      for (int i = 0; i < varsToRemove.size(); i++) {
         varToRemove = pairTable.nextToRemoveIDWithCriteriaRemoving();

         // Print debug information if needed
         if (generateDebugInfo) {
            System.out.println("Variable " + i + " a eliminar : " + varToRemove.getName());
         }
         orderedVars.add(varToRemove);
      }

      if (generateDebugInfo) {
         System.out.println("JunctionTreeNode:  orderVariablesWithOnlineTriangulation ----- END");
      }

      // Return orderedVars
      return orderedVars;
   }

   /**
    * Method for getting a RelationList containing relations linking the
    * variables of the potentials where the variable passed as argument appears
    *
    * @param varToRemove
    * @return relations
    */
   private RelationList makeRelationsFromVarPotentials(Node varToRemove) {
      RelationList relations = new RelationList();
      Potential pot;
      Relation rel;

      if (generateDebugInfo) {
         System.out.println("JunctionTreeNode:  makeRelationsFromVarPotentials ----- BEGIN");
      }

      // Get the potentials where varToRemove appears
      ArrayList<Potential> probPotentialsVar = getProbabilityPotentials(varToRemove, true);
      ArrayList<Potential> utilPotentialsVar = getUtilityPotentials(varToRemove, true);
      ArrayList<Potential> sepProbPotentials = getInferiorSeparatorsProbPotentials(varToRemove, true);
      ArrayList<Potential> sepUtilPotentials = getInferiorSeparatorsUtilPotentials(varToRemove, true);

      // Now make a new "artificial" relation for every potential
      if (probPotentialsVar != null) {
         for (int i = 0; i < probPotentialsVar.size(); i++) {
            pot = probPotentialsVar.get(i);
            rel = new Relation(pot);

            // Add the relation to relations
            relations.insertRelation(rel);
         }
      }

      if (utilPotentialsVar != null) {
         for (int i = 0; i < utilPotentialsVar.size(); i++) {
            pot = utilPotentialsVar.get(i);
            rel = new Relation(pot);

            // Add it to relations
            relations.insertRelation(rel);
         }
      }

      if (sepProbPotentials != null) {
         for (int i = 0; i < sepProbPotentials.size(); i++) {
            pot = sepProbPotentials.get(i);
            rel = new Relation(pot);

            // Add it to relations
            relations.insertRelation(rel);
         }
      }

      if (sepUtilPotentials != null) {
         for (int i = 0; i < sepUtilPotentials.size(); i++) {
            pot = sepUtilPotentials.get(i);
            rel = new Relation(pot);

            // Add it to relations
            relations.insertRelation(rel);
         }
      }

      if (generateDebugInfo) {
         System.out.println("JunctionTreeNode:  makeRelationsFromVarPotentials ----- END");
      }

      // Finally, return the relationList
      return relations;
   }

   /**
    * Method to compute the decision table for a clique containing one of the
    * decisions
    *
    * @param decision to compute its table
    */
   public void computeDecisionTable(Node decision) {
      // Get the relevant past for decision
      NodeList past = tree.diag.getRelevantPast(decision);
      ArrayList<Node> toRemoveArray = new ArrayList<Node>();
      ArrayList<Node> toRemoveArrayOrdered = null;
      Node node;

      if (generateDebugInfo) {
         System.out.println("JunctionTreeNode: computeDecisionTable en clique "+index+" ----- BEGIN");
         System.out.println("\tPara decision: " + decision.getName());
         System.out.println("\tVariables en clique: "+this);
         
         
      }

      // Add information for statistics in order to identify operations
      // required for computing decision tables
      if (tree.getStatisticsFlag()) {
         tree.statistics.addOperation("------");
         tree.statistics.addSize(0);
         tree.statistics.addTime(tree.crono.getTime());
      }


      // The rest of variables must be deleted
      NodeList toRemove = variables.differenceNames(past);
      

      // Pass the variables to an arrayList
      for (int i = 0; i < toRemove.size(); i++) {
 //        if(toRemove.elementAt(i)!=decision) 
            toRemoveArray.add(toRemove.elementAt(i));
      }

      // Now, remove the variables; before that impose an order between
      // them, according to the criteria used for removing. The removal must
      // be done with the same order as the variables belonging to the past
      toRemoveArrayOrdered = tree.orderVariablesWithOfflineTriangulation(toRemoveArray);

      if(generateDebugInfo) {
          System.out.println("\tOrden de borrado: "+toRemoveArrayOrdered);
      }
      
      
      // Now, remove these variables
      removeVariables(toRemoveArrayOrdered);
      
      
      
      
      

      if (generateDebugInfo) {
         System.out.println("JunctionTreeNode: computeDecisionTable en clique "+index+" ----- END");
      }
   }
   
      /**
    * Method to compute the decision table for a clique containing one of the
    * decisions
    *
    * @param decision to compute its table
    */
   public void removeAllVariables() {

      ArrayList<Node> toRemoveArray = new ArrayList<Node>();
      ArrayList<Node> toRemoveArrayOrdered = null;
      Node node;

      if (generateDebugInfo) {
         System.out.println("JunctionTreeNode: removeAllVariables en clique "+index+" ----- BEGIN");

  
      }

      // Add information for statistics in order to identify operations
      // required for computing decision tables
      if (tree.getStatisticsFlag()) {
         tree.statistics.addOperation("------");
         tree.statistics.addSize(0);
         tree.statistics.addTime(tree.crono.getTime());
      }


      // The rest of variables must be deleted
      NodeList toRemove = variables;
      

      // Pass the variables to an arrayList
      for (int i = 0; i < toRemove.size(); i++) {
 //        if(toRemove.elementAt(i)!=decision) 
            toRemoveArray.add(toRemove.elementAt(i));
      }

      // Now, remove the variables; before that impose an order between
      // them, according to the criteria used for removing. The removal must
      // be done with the same order as the variables belonging to the past
      
      if(tree.getVariableEliminationCriteria() != StrongJunctionTree.SYMBOLIC_PROB_INFERENCE) {
        startMeasureOverHead();
        toRemoveArrayOrdered = tree.orderVariablesWithOfflineTriangulation(toRemoveArray);
        stopMeasureOverHead();
      }else{
          toRemoveArrayOrdered = toRemoveArray;
      }
      
      if(generateDebugInfo) {
          System.out.println("\tOrden de borrado: "+toRemoveArrayOrdered);
      }
      
      
      // Now, remove these variables
      removeVariables(toRemoveArrayOrdered);
      


      if (generateDebugInfo) {
         System.out.println("removeAllVariables: computeDecisionTable en clique "+index+" ----- END");
      }
   }
   

      /**
    * Private method for removing the variables of the clique
    *
    * @param varsToRemove arraylist with the variables to be removed
    */
   private void removeVariables(ArrayList<Node> varsToRemove) {
       
       
//       System.out.println("~~"+ varsToRemove.toString());
       
       int criteria = tree.getVariableEliminationCriteria();
        switch (criteria) {
            case StrongJunctionTree.OFFLINE_TRIANGULATION:
            case StrongJunctionTree.ONLINE_TRIANGULATION:    
               removeVariablesVE(varsToRemove);
               break;
           case StrongJunctionTree.SYMBOLIC_PROB_INFERENCE:   
               removeVariablesSPI(varsToRemove);
               
               break;
            default:
               System.out.println("Invalid criteria for ordering variables to remove");
               System.out.println("Method: removeVariables     Class: JunctionTreeNode");
               System.exit(0);
         }
       
       
   }
   
      /**
    * Private method for removing the variables of the clique
    * using SPI algorithm
    *
    * @param varsToRemove arraylist with the variables to be removed
    */
   private void removeVariablesSPI(ArrayList<Node> varsToRemove) {
       if(generateDebugInfo)
        System.out.println("Remove Variables SPI");
       
       ArrayList<NodeList> setsToRemove = getSetsToRemove(varsToRemove);
       
       IDSymbolicProbInf spi = new IDSymbolicProbInf();
       spi.setHeuristic(tree.getSpiCriteria());
       spi.setAllowSingletonProbs(tree.getPropagationID().isAllowSingletons());
       spi.setCheckUnity(tree.getPropagationID().isCheckUnity());
       
       
       
       spi.setResultsMap(tree.getResults());
       spi.setStatistics(tree.getStatistics());
       spi.setGenerateDebugInfo(generateDebugInfo);
       spi.setMeasureOverHead(tree.propagationID.isMeasureOverHead());
       
       
       for(int i=0; i<setsToRemove.size(); i++){
       //for(NodeList set : setsToRemove) {
           NodeList set = setsToRemove.get(i); 
           
           //4.a Get Potentials
           ArrayList<Potential> prob_xk = getProbabilityPotentials(set, liberate);
           ArrayList<Potential> util_xk = getUtilityPotentials(set, liberate);
           
           prob_xk.addAll(getInferiorSeparatorsProbPotentials(set, liberate));
           util_xk.addAll(getInferiorSeparatorsUtilPotentials(set, liberate));
           
           //4.b Remove Variables in the set
           if(set.elementAt(0).getKindOfNode() == Node.CHANCE) {
               spi.removeChanceSet(set, prob_xk, util_xk);
              
               
               
           }else if(set.elementAt(0).getKindOfNode() == Node.DECISION) {
                spi.removeDecision(set.elementAt(0), prob_xk, util_xk);      
           }else{
               System.out.println("Invalid node kind: " + set.elementAt(0).getKindOfNode());
               System.exit(0);
           
           }
           
      
           //4.c Update
           if (prob_xk != null) {
               
             
               
               
                if(liberate)
                addProbabilityPotential(prob_xk);
                else
                addPotentialToHashMap(prob_xk, probPotentialsAux); 
            }

            // The same for the utility
            if (util_xk != null) {
                if(liberate)
                addUtilityPotential(util_xk);
                else
                addPotentialToHashMap(util_xk, utilPotentialsAux);

            }
            
            // 5. Associate potentials to the parent separator
           if (up != null && i==setsToRemove.size()-1) {
               if (generateDebugInfo) {
                   System.out.println();
                   System.out.println("\tAsigna potenciales al separador superior........");
                   System.out.println("\tUltima operacion sobre el clique: " + index);
                   System.out.println();
               }
               setPotentialsToUpSeparator();
           }
     
       }
       
       if(spi.isMeasureOverHead()) {
           this.tree.propagationID.addOverHeadTime(spi.getOverHeadTime());
       }
   
   }
   
   

   private void removeDecisionSetSPI(Node n, ArrayList<Potential> probSet, ArrayList<Potential> utilSet) {
   
   
   }    
   
   private ArrayList<NodeList> getSetsToRemove(ArrayList<Node> vars) {
       ArrayList<NodeList> sets = new ArrayList<NodeList>();
       
       int lastKind = -1;
       int currentSet = -1;

       
       
       for (Node node : vars) {
           if(node.getKindOfNode() != lastKind || node.getKindOfNode() == Node.DECISION) {
               currentSet++;
               lastKind=node.getKindOfNode();
               sets.add(new NodeList());            
           }
           
           sets.get(currentSet).insertNode(node);
           
       }
       
       return sets;
   
   }

   /**
    * Private method for removing the variables of the clique
    * using VE algorithm
    *
    * @param varsToRemove arraylist with the variables to be removed
    */
   private void removeVariablesVE(ArrayList<Node> varsToRemove) {
      int last = varsToRemove.size();
      boolean lastFlag = false;
      int i = 0;

      if (generateDebugInfo) {
         System.out.println("JunctionTreeNode: removeVariables en clique "+index+" ----- BEGIN");
      }

      
      if(!liberate) {
          
          this.utilPotentialsAux = copyPotentialMap(utilPotentials);
          this.probPotentialsAux = copyPotentialMap(probPotentials);
          for(JunctionTreeSeparator s : down) {
              s.initAuxiliarySets();
          
          }
      }
          
      
      

      // Iterate on the arraylist to remove the variables stored
      // on it
      
      for (Node node : varsToRemove) {
         if (generateDebugInfo) {
            System.out.print("\n\n\tSe va a eliminar: " + node.getName() + " ");
         }

         // Add one to i
         i++;

         // If it is the last, pass true as second argument
         if (i == last) {
            lastFlag = true;
         }

         switch (node.getKindOfNode()) {
            case Node.CHANCE:
               if (generateDebugInfo) System.out.println("(azar)\n\n");
               removeChanceVariable(node, lastFlag);
               break;
            case Node.DECISION:
               if (generateDebugInfo) System.out.println("(decision)\n\n");
               removeDecisionVariable(node, lastFlag);
               break;
            default:
               System.out.println("Invalid node kind: " + node.getKindOfNode());
               System.exit(0);
         }
         if (generateDebugInfo) {
            System.out.println("\tEliminado: " + node.getName());
            System.out.println("\t.....................................");
         }
         
      }

      // Set the potentials to the up separator
      
      if (up != null) {          
         if (generateDebugInfo) {
            System.out.println();
            System.out.println("\tAsigna potenciales al separador superior........");
            System.out.println("\tUltima operacion sobre el clique: " + index);
            System.out.println();
        }
         setPotentialsToUpSeparator();
      }

      // Print statistics information
      if (tree.getStatisticsFlag()) {
         if (lastFlag) {
            tree.statistics.addSize(tree.getSize());
            tree.statistics.addTime(tree.crono.getTime());
         }
      }

      if (generateDebugInfo) {
         System.out.println("JunctionTreeNode: removeVariables en clique "+index+" ----- END");
      }
      
      
      
   }

   /**
    * Public method for removing a chance node
    *
    * @param node to remove
    * @param lastFlag to show if potentials must be sent to the upper separator
    */
   public void removeChanceVariable(Node node, boolean lastFlag) {
      Flag onePotentialInvolved = new Flag();

      

      
      
      // Look for the potentials related to this variable. Part of them
      // will be on the same clique
      ArrayList<Potential> cliqueNodeProbPotentials = getProbabilityPotentials(node, liberate);
      ArrayList<Potential> infSeparatorsNodeProbPotentials = getInferiorSeparatorsProbPotentials(node, liberate);
      ArrayList<Potential> cliqueNodeUtilPotentials = getUtilityPotentials(node, liberate);
      ArrayList<Potential> infSeparatorsNodeUtilPotentials = getInferiorSeparatorsUtilPotentials(node, liberate);

      if (generateDebugInfo) {
         // Shows infomation about potentials involved in the operation
         System.out.println("JunctionTreeNode: removeChanceVariable ----- BEGIN");
         System.out.println("\n\tClique: " + index);
         System.out.println("\tVariable : " + node.getName());
         if (cliqueNodeProbPotentials != null) {
            System.out.println("\tP. de prob. en clique: "
                    + cliqueNodeProbPotentials.size());
         }
         if (infSeparatorsNodeProbPotentials != null) {
            System.out.println("\tP. de prob. en sep. : "
                    + infSeparatorsNodeProbPotentials.size());
         }
         if (cliqueNodeUtilPotentials != null) {
            System.out.println("\tP. de util en clique: "
                    + cliqueNodeUtilPotentials.size());
         }
         if (infSeparatorsNodeUtilPotentials != null) {
            System.out.println("\tP. de util en sep. :"
                    + infSeparatorsNodeUtilPotentials.size());
         }
      
         
      
      }

      
      // Combine the probability potentials
      
      Potential probPots = null;
      

      probPots = combineProbabilityPotentials(cliqueNodeProbPotentials,
              infSeparatorsNodeProbPotentials);
      

      // Gathers statistical information if required
      if (tree.getStatisticsFlag()) {
         if(generateDebugInfo) 
            System.out.println("\n\tAgregada informacion de operacion de borrado de nodo de azar...");
         tree.statistics.addOperation(node.getName());
         tree.addOperation(node);
         
         
         
         //Get the number of potentials in the clique and in the 
         //inferior separator
         int numPotsClique  = 0;
         if(cliqueNodeProbPotentials != null)
             numPotsClique = cliqueNodeProbPotentials.size();      
         
         int numPotsInfSep  = 0;
         if(infSeparatorsNodeProbPotentials != null)
             numPotsInfSep = infSeparatorsNodeProbPotentials.size();

         if (numPotsClique + numPotsInfSep > 1) {
            onePotentialInvolved.setValue(false);
            tree.addSize(probPots.getSize());
         }
         
        
      
      }


      // Combine the utility potentials
      Potential utilPots = combineUtilityPotentials(cliqueNodeUtilPotentials,
              infSeparatorsNodeUtilPotentials);
      

      
      


      // Gathers statistical information if required
      if (tree.getStatisticsFlag()) {
         
          
         //Get the number of potentials in the clique and in the 
         //inferior separator
         int numPotsClique  = 0;
         if(cliqueNodeUtilPotentials != null)
             numPotsClique = cliqueNodeUtilPotentials.size();    
         int numPotsInfSep  = 0;
         if(infSeparatorsNodeUtilPotentials != null)
             numPotsInfSep = infSeparatorsNodeUtilPotentials.size(); 
          
          
         if (numPotsClique + numPotsInfSep > 1) {
            onePotentialInvolved.setValue(false);
            tree.addSize(utilPots.getSize());
         }
      }

      // Combine probability and utility potentials
      if (probPots != null && utilPots != null) {
         utilPots = utilPots.combine(probPots);

          if (tree.getStatisticsFlag()) 
              tree.statistics.addNumMultiplications(binaryOperationCost(utilPots));
          
         if (generateDebugInfo) {
            System.out.println("\tObtenida combinacion de probs y utils......");
            System.out.println("\t"+utilPots);
            System.out.println("\t-------------------------------------------");
            System.out.println("\tTam. inicial del arbol de utilidad antes del tratamiento del arbol: " + utilPots.getSize());
         }

         // Gathers statistical information if required
         if (tree.getStatisticsFlag()) {
             tree.addSize(utilPots.getSize());
         }

         // Postprocess utility tree
         //utilPots=postProcessUtility(utilPots);

         if (generateDebugInfo) {
            System.out.println("\tTam. final del arbol de utilidad despues del tratamiento del arbol: " + utilPots.getSize());
         }
      }
      
      
      //Stores the total size of potentials int the strong junction tree
      // and the potentials in use
      if(tree.getStatisticsFlag()){
          long sizeP=0, sizeU=0;
          long numNodesP=0, numNodesU = 0;
          
          if(probPots != null){
              sizeP=probPots.getSize();
              numNodesP = probPots.getNumberOfNodes();
          }
          if(utilPots != null){
              sizeU=utilPots.getSize();
              numNodesU = utilPots.getNumberOfNodes();
          
          }
          
          tree.getStatistics().addTotalSize(sizeP+sizeU+tree.getSize());
          tree.getStatistics().addNumberNodes(numNodesP+numNodesU+tree.getNodesSize());

      }
      
      
      

      // Check if the probability potential can lead to a unity potential
      if (probPots != null) {
          
        boolean unity = false;
        
        if(tree.getPropagationID().isCheckUnity()) {
            unity = tree.diag.isConditionalOrMarginalPotential(node, probPots);  
            if(!unity && probPots.getHead() != null) {
                unity = probPots.generatesUnityPot(probPots, node);
            }
            if(unity && tree.generateStatistics) {
                tree.getStatistics().addNumProbBarren(1);
            }
        
        } 
          
        
         if (!unity ) {
            
            if(tree.generateStatistics) {
                tree.getStatistics().addSumMarg(unaryOperationCost(probPots, node));
                
            }
             
             Vector newHead = null, newTail = null;
             if (probPots.getHead() != null) {
                 newHead = Potential.getMargHead(probPots, node);
                 newTail = Potential.getMargTail(probPots, node);
             }
             
             // This is not an unity potential and is required to remove the variable
            probPots = probPots.addVariable(node);
            
            probPots.setHead(newHead);
            probPots.setTail(newTail);
            

            // Show the potential
            if (generateDebugInfo) {
               System.out.println("\tPotencial prob. resultante de la marginalizacion: ");
               System.out.println("\t"+probPots);
               System.out.println("\t--------------------------------------------------------");
               System.out.println("\tTam. inicial del arbol de prob. antes de tratamiento del arbol: " + probPots.getSize());
           

            
            }

            // Postprocess probability tree
            //probPots=postProcessProbability(probPots);

            if (generateDebugInfo) {
               System.out.println("\tTam. final del arbol de prob. despues del tratamiento del arbol: " + probPots.getSize());
            }
         } else {
            if (generateDebugInfo) {
               System.out.println("\tAl marginalizar seria un potencial unidad.......");
               System.out.println("\t"+probPots);
               System.out.println("\t...................................");
            }
            probPots = null;
         }
      }

      // Remove the variable from the utility potentials
      if (utilPots != null) {
          
          
         if(tree.generateStatistics) {
             tree.getStatistics().addSumMarg(unaryOperationCost(utilPots, node));
         }    
          
         
         
         
         utilPots = utilPots.addVariable(node);
         
     
         
         if (generateDebugInfo) {
            System.out.println("\tCombinacion de probs y utils marginalizada en ......" + node.getName());
            System.out.println("\t"+utilPots);
            System.out.println("\n-------------------------------------------");
            System.out.println("\tTam. inicial antes del arbol de util. del tratamiento del arbol: " + utilPots.getSize());
         }

         // Postprocessing utility tree
         //utilPots=postProcessUtility(utilPots);

         if (generateDebugInfo) {
            System.out.println("\tTam. final del arbol de util. despues del tratamiento del arbol: " + utilPots.getSize());
         }
      }

      // If it is needed, make the division between utilPots and probPots
      if (probPots != null && utilPots != null) {
         if (generateDebugInfo) {
            System.out.println("\tSe procede a realizar la division");
         }

         // Perform the division
         utilPots = utilPots.divide(probPots);
         
         if(tree.generateStatistics) {
             binaryOperationCost(utilPots);
         }

         if (generateDebugInfo) {
            System.out.println("\tArbol de utilidad normalizado tras la division");
            System.out.println("\t-------------------------------------------");
            System.out.println("\t"+utilPots);
            System.out.println("\t-------------------------------------------");
         }
      }

      /**
       * Rearrange probability and utility potential, as results, to minimize
       * space memory usage
       */
      if (probPots != null) {
         if (generateDebugInfo) {
            System.out.println("\tTam. del potential de prob. antes del tratamiento del arbol: " + probPots.getSize());
         }

         // Postprocess probability tree
         probPots = postProcessProbability(probPots);

         if (generateDebugInfo) {
            System.out.println("\tTam. del potential de prob. despues del tratamiento del arbol: " + probPots.getSize());
         }
      }

      if (utilPots != null) {
         if (generateDebugInfo) {
            System.out.println("\tTam. del potential de util. antes del tratamiento del arbol: " + utilPots.getSize());
         }

         utilPots = postProcessUtility(utilPots);

         if (generateDebugInfo) {
            System.out.println("\tTam. del potential de util. despues del tratamiento del arbol: " + utilPots.getSize());
         }
      }

      

       
      //Stores the total size of potentials int the strong junction tree
      // and the potentials in use
      if(tree.getStatisticsFlag()){
          long sizeP=0, sizeU=0;
          long numNodesP=0, numNodesU = 0;
          
          if(probPots != null){
              sizeP=probPots.getSize();
              numNodesP = probPots.getNumberOfNodes();
          }
          if(utilPots != null){
              sizeU=utilPots.getSize();
              numNodesU = utilPots.getNumberOfNodes();
          
          }
          
          tree.getStatistics().addTotalSize(sizeP+sizeU+tree.getSize());
          tree.getStatistics().addNumberNodes(numNodesP+numNodesU+tree.getNodesSize());

      }
        
       
       
      
      // Add the probability potential, if needed
      if (probPots != null) {
         
         if(liberate)
            addProbabilityPotential(probPots);
         else
            addPotentialToHashMap(probPots, probPotentialsAux); 
      }

      // The same for the utility
      if (utilPots != null) {
          if(liberate)
            addUtilityPotential(utilPots);
          else
            addPotentialToHashMap(utilPots, utilPotentialsAux);

      }

      // May be constraints can be applied now
//      applyConstraints(false);

      // Get the size of the potentials and print it
      if (generateDebugInfo) {
         System.out.println("\n\tTOTAL SIZE: " + tree.getSize());
      }

      if (tree.getStatisticsFlag()) {
         if (lastFlag == false) {
            
            // Defines the size and anotates the operation
            tree.defineStatisticsMeasures();
         }
      }

      if (generateDebugInfo) {
         System.out.println("\nJunctionTreeNode: removeChanceVariable ----- END");
      }
   }

   /**
    * Public method for removing a decision variable
    *
    * @param node to remove
    * @param lastFlag to show if the variable to be removed is the last
    * operation over the clique
    */
   public void removeDecisionVariable(Node node, boolean lastFlag) {

      // Look for the potentials related to this variable. Part of them
      // will be on the same clique
      ArrayList<Potential> cliqueNodeProbPotentials = getProbabilityPotentials(node, liberate);
      ArrayList<Potential> infSeparatorsNodeProbPotentials = getInferiorSeparatorsProbPotentials(node, liberate);
      ArrayList<Potential> cliqueNodeUtilPotentials = getUtilityPotentials(node, liberate);
      ArrayList<Potential> infSeparatorsNodeUtilPotentials = getInferiorSeparatorsUtilPotentials(node, liberate);

      if (generateDebugInfo) {
         System.out.println("JunctionTreeNode: removeDecisionVariable ----- BEGIN\n");
         System.out.println("\t----------------- removeDecisionVariable ---------------");
         System.out.println("\tClique: " + index);
         System.out.println("\t--------------- Nodo : " + node.getName());
         if (cliqueNodeProbPotentials != null) {
            System.out.println("\tP. de prob. en clique: "
                    + cliqueNodeProbPotentials.size());
         }
         if (infSeparatorsNodeProbPotentials != null) {
            System.out.println("\tP. de prob. en sep. : "
                    + infSeparatorsNodeProbPotentials.size());
         }
         if (cliqueNodeUtilPotentials != null) {
            System.out.println("\tP. de util en clique: "
                    + cliqueNodeUtilPotentials.size());
         }
         if (infSeparatorsNodeUtilPotentials != null) {
            System.out.println("\tP. de util en sep. :"
                    + infSeparatorsNodeUtilPotentials.size());
         }
      }

      if (tree.getStatisticsFlag()) {
         tree.statistics.addOperation(node.getName());
      }

      // Combine the probability potentials
      Potential probPots = combineProbabilityPotentials(cliqueNodeProbPotentials,
              infSeparatorsNodeProbPotentials);

      // Combine the utility potentials
      Potential utilPots = combineUtilityPotentials(cliqueNodeUtilPotentials,
              infSeparatorsNodeUtilPotentials);

      // Combine probability and utility potentials
      if (probPots != null && utilPots != null) {
         utilPots = utilPots.combine(probPots);
         
         if(tree.generateStatistics)
             tree.statistics.addNumMultiplications(binaryOperationCost(utilPots));
 
         if (generateDebugInfo) {
            System.out.println("\tTam. inicial de arbol de utilidad antes de tratarlo: " + utilPots.getSize());
         }

         // Reorder the tree
         //utilPots=postProcessUtility(utilPots);

         if (generateDebugInfo) {
            System.out.println("\tTam. final de arbol de utilidad antes de tratarlo: " + utilPots.getSize());
         }
      }
      
      //Stores the total size of potentials int the strong junction tree
      // and the potentials in use
      if(tree.getStatisticsFlag()){
          long sizeP=0, sizeU=0;
          long numNodesP=0, numNodesU = 0;
          
          if(probPots != null){
              sizeP=probPots.getSize();
              numNodesP = probPots.getNumberOfNodes();
          }
          if(utilPots != null){
              sizeU=utilPots.getSize();
              numNodesU = utilPots.getNumberOfNodes();
          
          }
          
          tree.getStatistics().addTotalSize(sizeP+sizeU+tree.getSize());
          tree.getStatistics().addNumberNodes(numNodesP+numNodesU+tree.getNodesSize());

      }
      
      
      
      
      
      
      

      // Check if the probability potential can lead to a unity potential
      if (probPots != null) {
         Vector vars = new Vector(probPots.getVariables());
         vars.removeElement(node);

         if(tree.generateStatistics) {
             tree.statistics.addMaxMarg(unaryOperationCost(probPots, node));
         }
         
          Vector newHead = null, newTail = null;
          if (probPots.getHead() != null) {
              newHead = Potential.getMargHead(probPots, node);
              newTail = Potential.getMargTail(probPots, node);
          }
       
         
         // This is not an unity potential and is required to remove the variable
         probPots = probPots.maxMarginalizePotential(vars);
         
         
         probPots.setHead(newHead);
         probPots.setTail(newTail);

         // Show the potential
         if (generateDebugInfo) {
            System.out.println("\tPotencial prob. resultante de la marginalizacion: ");
            System.out.println("\t  "+probPots);
            System.out.println("\t--------------------------------------------------------");
            System.out.println("\tTam. del arbol de prob. antes de tratarlo: " + probPots.getSize());
         }

         // Reorder the tree
         //probPots=postProcessProbability(probPots);

         if (generateDebugInfo) {
            System.out.println("Tam. del arbol de prob. despues de tratarlo: " + probPots.getSize());
         }
      }

      // If it is needed, make the division between utilPots and probPots
      if (probPots != null && utilPots != null) {
         utilPots = utilPots.divide(probPots);
         
         if(tree.generateStatistics)
             tree.statistics.addNumDivisions(binaryOperationCost(utilPots));

         if (generateDebugInfo) {
            System.out.println("\tArbol de utilidad tras normalizar.....");
            System.out.println("\t-----------------------------------------------");
            System.out.println("\t  "+utilPots);
            System.out.println("-----------------------------------------------");
            System.out.println("\tTam. de arbol de util. antes de tratarlo: " + utilPots.getSize());
         }

         // Reorder the tree
         //utilPots=postProcessUtility(utilPots);

         if (generateDebugInfo) {
            System.out.println("\tTam. de arbol de util. despues de tratarlo: " + utilPots.getSize());
         }
      }

      // Remove the variable from the utility potentials
      if (utilPots != null) {
         
          
          
          // Before removing the variable, store the decision table as result
          

            tree.results.put(node, utilPots);
         
        //Saves the expected utility in the statistics
  //       if (tree.getStatisticsFlag())
  //          tree.getStatistics().addUtilityHistory(utilPots);
         
          if(tree.generateStatistics) {

             tree.statistics.addMaxMarg(binaryOperationCost(utilPots));
         }
         
         Vector vars = new Vector(utilPots.getVariables());
         vars.removeElement(node);
         utilPots = utilPots.maxMarginalizePotential(vars);

         // Show the marginalization
         if (generateDebugInfo) {
            System.out.println("\tPotencial de util. tras marginalizar:....................");
            System.out.println("\t  "+utilPots);
            System.out.println("\t..........................................................");
            System.out.println("\tTam. del arbol de util. antes de tratarlo: " + utilPots.getSize());
         }

         // Reorder the tree
         utilPots = postProcessUtility(utilPots);

         if (generateDebugInfo) {
            System.out.println("\tTam. del arbol de util. antes de tratarlo: " + utilPots.getSize());
         }
      }

      if (probPots != null) {
         if (generateDebugInfo) {
            System.out.println("\tTam. del arbol de prob. antes de tratarlo: " + probPots.getSize());
         }
         probPots = postProcessProbability(probPots);
         if (generateDebugInfo) {
            System.out.println("\tTam. del arbol de prob. despues de tratarlo: " + probPots.getSize());
         }
      }
      
        //Stores the total size of potentials int the strong junction tree
        // and the potentials in use
        if(tree.getStatisticsFlag()){
            long sizeP=0, sizeU=0;
            long numNodesP=0, numNodesU = 0;

            if(probPots != null){
                sizeP=probPots.getSize();
                numNodesP = probPots.getNumberOfNodes();
            }
            if(utilPots != null){
                sizeU=utilPots.getSize();
                numNodesU = utilPots.getNumberOfNodes();

            }

            tree.getStatistics().addTotalSize(sizeP+sizeU+tree.getSize());
            tree.getStatistics().addNumberNodes(numNodesP+numNodesU+tree.getNodesSize());

        }
        
      
      
      
      

      // Add the probability potential, if needed
      if (probPots != null) {
         
         if(liberate)
            addProbabilityPotential(probPots);
         else
            addPotentialToHashMap(probPots, probPotentialsAux); 
      }

      // The same for the utility
      if (utilPots != null) {
          if(liberate)
            addUtilityPotential(utilPots);
          else
            addPotentialToHashMap(utilPots, utilPotentialsAux);

      }
      // May be constraints can be applied now
  //    applyConstraints(false);

      // Get the size of the potentials and print it
      if (generateDebugInfo) {
         System.out.println("\tTOTAL SIZE: " + tree.getSize());
      }

      if (tree.getStatisticsFlag()) {
         if (lastFlag == false) {
            tree.statistics.addSize(tree.getSize());
            tree.statistics.addTime(tree.crono.getTime());
         }
      }

      if (generateDebugInfo) {
         System.out.println("\nJunctionTreeNode: removeDecisionVariable ----- END");
      }
   }

   /**
    * Private method for post processing the utility potentials. For
    * PotentialTable this method does nothing, but with probability potentials
    * the variables will be sorted and a prunning will be done if required
    *
    * @param potential
     * @return 
    */
   protected Potential postProcessUtility(Potential potential) {
      return potential;
   }

   /**
    * Private method for post processing the probability potentials. For
    * PotentialTable this method does nothing, but with probability potentials
    * the variables will be sorted and a prunning will be done if required
    *
    * @param potential
    */
   protected Potential postProcessProbability(Potential potential) {
      return potential;
   }

   /**
    * Public method for setting the potentials of the clique to the upper
    * separator
    */
   public void setPotentialsToUpSeparator() {
      HashSet<Potential> probPots = new HashSet<Potential>();
      HashSet<Potential> utilPots = new HashSet<Potential>();
      ArrayList<Potential> potentials;
      ArrayList<Potential> inferiorSepProbPotentials;
      ArrayList<Potential> inferiorSepUtilPotentials;

      if (generateDebugInfo) {
         System.out.println("JunctionTreeNode: setPotentialsToUpSeparator en clique "+index+" ----- BEGIN");
      }
      
      
      //Sets the hashmap used to compute the messages
      HashMap<Node, ArrayList<Potential>> map;
      if(liberate)
          map = probPotentials;
      else
          map = probPotentialsAux;
      
      
      

      // Get an iterator for the probaility potentials
      Iterator<ArrayList<Potential>> iterator = map.values().iterator();

      // Consider them: every arraylist will be related to a var
      while (iterator.hasNext()) {
         potentials = iterator.next();

         // Consider the potentials one by one
         for (Potential potential : potentials) {
            // If it is not already in probPotentials, send it to the
            // separator
             
             
            if (probPots.add(potential)) {
               if (generateDebugInfo) {
                  System.out.println("\tAsignando potencial de probabilidad a separador superior de: " + index + "  separador: " + up.getIndex());
                  System.out.println("\t  "+potential);
                  System.out.println("...................................................................");
               }
               up.setProbMessage(potential);
            }
         }
      }

      // Add prob potentials in the separators
      for (JunctionTreeSeparator infSeparator : down) {
         inferiorSepProbPotentials = infSeparator.removeProbMessages();
         if (inferiorSepProbPotentials != null) {
            for (Potential potential : inferiorSepProbPotentials) {
               up.setProbMessage(potential);
            }
         }
      }

      
      //Sets the hashmap used to compute the messages
      if(liberate)
          map = utilPotentials;
      else
          map = utilPotentialsAux;
      
      // The same for utility potentials
      iterator = utilPotentials.values().iterator();

      // Consider them: every arraylist will be related to a var
      while (iterator.hasNext()) {
         potentials = iterator.next();

         // Consider the potentials one by one
         for (Potential potential : potentials) {
            // If it is not already in probPotentials, send it to the
            // separator
            if (utilPots.add(potential)) {
               if (generateDebugInfo) {
                  System.out.println("\tAsignando potencial de utilidad a separador superior de: " + index);
                  System.out.println("\t  "+potential);
                  System.out.println("\t...................................................................");
               }
               up.setUtilMessage(potential);
            }
         }
      }

      // Add util potentials in the separators
      for (JunctionTreeSeparator infSeparator : down) {
         inferiorSepUtilPotentials = infSeparator.removeUtilMessages();
         if (inferiorSepUtilPotentials != null) {
            for (Potential potential : inferiorSepUtilPotentials) {
               up.setUtilMessage(potential);
            }
         }
      }

      // The data members for probPotentials and utilPotentials are
      // cleaned
      if(liberate){
        probPotentials = new HashMap<Node, ArrayList<Potential>>();
        utilPotentials = new HashMap<Node, ArrayList<Potential>>();
      }else{
        probPotentialsAux = new HashMap<Node, ArrayList<Potential>>();
        utilPotentialsAux = new HashMap<Node, ArrayList<Potential>>();
      }
      
      
      if (generateDebugInfo) {
         System.out.println("JunctionTreeNode: setPotentialsToUpSeparator en clique "+index+" ----- END");
      }
   }

     /**
    * Private mthod for combining the probability potentials for a given
    * variable
    *
    * @param cliquePots clique potentials related to node
    * @param sepPots separator potentials related to node
    * @return potential
    */
   private Potential combineProbabilityPotentials(ArrayList<Potential> cliquePots,
           ArrayList<Potential> sepPots) {
       
       if(tree.getPropagationID().isGreedyCombination())
           return combineProbabilityPotentialsGreedy(cliquePots, sepPots);
       return combineProbabilityPotentialsOld(cliquePots, sepPots);
           
   
   }
   
   
   /**
    * Private mthod for combining the probability potentials for a given
    * variable
    *
    * @param cliquePots clique potentials related to node
    * @param sepPots separator potentials related to node
    * @return potential
    */
   private Potential combineProbabilityPotentialsOld(ArrayList<Potential> cliquePots,
           ArrayList<Potential> sepPots) {
      Potential finalPotential = null;

      if (generateDebugInfo) {
         System.out.println("\nJunctionTreeNode: combineProbabilityPotentials en clique "+index+" ----- BEGIN\n");
      }


      
      // Consider the clique potentials, if it is not null
      if (cliquePots != null) {
         for (Potential pot : cliquePots) {
            if (finalPotential == null) {
               finalPotential = pot;

               if (generateDebugInfo) {
                  System.out.println("\tComienzo con potencial:");
                  System.out.println("\t"+pot);
                  System.out.println("\t---------------------------------------------------");
               }
            } else {
               // Print debug information
               if (generateDebugInfo) {
                  System.out.println("\tContinuo con potencial: ");
                  System.out.println("\t"+pot);
                  System.out.println("\t---------------------------------------------------");
               }

               finalPotential = finalPotential.combine(pot);
                if (tree.getStatisticsFlag()) {
                    tree.statistics.addNumMultiplications(binaryOperationCost(finalPotential));
                }

               // Print debug information
               if (generateDebugInfo) {
                  System.out.println("\tResultado parcial:");
                  System.out.println("\t"+finalPotential);
                  System.out.println("\t---------------------------------------------------");
               }
            }
         }
      }

      // Add debug information
      if (generateDebugInfo) {
         System.out.println();
         System.out.println("\tSe procede ahora a combinar con potenciales de separadores");
         System.out.println();
      }

      // The same for separator potentials
      if (sepPots != null) {
         for (Potential pot : sepPots) {
            if (finalPotential == null) {
               finalPotential = pot;

               // Add debug information
               if (generateDebugInfo) {
                  System.out.println("\tComienzo con potencial:");
                  System.out.println("\t"+pot);
                  System.out.println("\t---------------------------------------------------");
               }
            } else {
               // Add debug information
               if (generateDebugInfo) {
                  System.out.println("\tContinuo con potencial:");
                  System.out.println("\t"+pot);
                  System.out.println("\t---------------------------------------------------");
               }

               finalPotential = finalPotential.combine(pot);
                if (tree.getStatisticsFlag()) {
                    tree.statistics.addNumMultiplications(binaryOperationCost(finalPotential));
                }
               // Add debug information
               if (generateDebugInfo) {
                  System.out.println("\tResultado parcial:");
                  System.out.println("\t"+pot);
                  System.out.println("\t---------------------------------------------------");
               }

               // Sort and bound this potential if it is a PotentialTree
               if (generateDebugInfo) {
                  System.out.println("Tam. inicial: " + finalPotential.getSize());
               }
            }
         }
      }

      // Show the exit from the method
      if (generateDebugInfo) {
         System.out.println("\t(Fin)----------------- combineProbabilityPotentials ---------------");
         System.out.println("\tPotencial calculado:");
         if (finalPotential != null) {
            System.out.println("\t  "+finalPotential);
         } else {
            System.out.println("\t  Potencial nulo");
         }
         System.out.println("\t-------------------------------------------------------------------");
         System.out.println();
      }

      
      
      
      if (generateDebugInfo) {
         System.out.println("\nJunctionTreeNode: combineProbabilityPotentials en clique "+index+" ----- END");
      }

      // Return the combination
      return finalPotential;
   }

   
   
   
   
     /**
    * Private mthod for combining the probability potentials for a given
    * variable
    *
    * @param cliquePots clique potentials related to node
    * @param sepPots separator potentials related to node
    * @return potential
    */
   private Potential combineProbabilityPotentialsGreedy(ArrayList<Potential> cliquePots,
           ArrayList<Potential> sepPots) {
       Potential finalPotential = null;

       if (generateDebugInfo) {
           System.out.println("\nJunctionTreeNode: combineProbabilityPotentials en clique " + index + " ----- BEGIN\n");
       }

       Potential pot = null;
       int combinationHeuristic = this.tree.getPropagationID().getCombinationHeuristic();

       ArrayList<Potential> pots = new ArrayList<Potential>();
       
       if(cliquePots != null)
            pots.addAll(cliquePots);
       if(sepPots != null)
            pots.addAll(sepPots);


        CombPairTable B = new CombPairTable();
        B.setAllowSingletons(false);
        B.setWithoutRemovable();


        while (pots.size() > 1) {
            startMeasureOverHead();
            B.addCombinations(pots);
            CombPair p = B.netxToCombine(combinationHeuristic);
            startMeasureOverHead();
            
            Potential prob_ij = p.getPot1().combine(p.getPot2());
            if (tree.getStatisticsFlag()) {
                tree.getStatistics().addNumMultiplications(binaryOperationCost(prob_ij));
            }

            if (p.getPot1().getHead() != null) {
                prob_ij.setHead(Potential.getCombinationHead(p.getPot1(), p.getPot2()));
                prob_ij.setTail(Potential.getCombinationTail(p.getPot1(), p.getPot2()));


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


       if (generateDebugInfo) {
           System.out.println("\nJunctionTreeNode: combineProbabilityPotentials en clique " + index + " ----- END");
       }
        // Return the potential
        return pot;


   }
   
      /**
    * Private method for combining the utility potentials for a given variable
    *
    * @param cliquePots clique potentials related to node
    * @param sepPots separator potentials related to node
    * @return potential
    */
   private Potential combineUtilityPotentials(ArrayList<Potential> cliquePots,
           ArrayList<Potential> sepPots) {
           if(tree.getPropagationID().isGreedyCombination())
               return combineUtilityPotentialsGreedy(cliquePots, sepPots);
           return combineUtilityPotentialsOld(cliquePots, sepPots);
           
   
   }
   
   
   
   /**
    * Private method for combining the utility potentials for a given variable
    *
    * @param cliquePots clique potentials related to node
    * @param sepPots separator potentials related to node
    * @return potential
    */
   private Potential combineUtilityPotentialsOld(ArrayList<Potential> cliquePots,
           ArrayList<Potential> sepPots) {
      Potential finalPotential = null;

      if (generateDebugInfo) {
         System.out.println("\nJunctionTreeNode: combineUtilityPotentials en clique "+index+" ----- BEGIN\n");
      }

 
      // Consider the clique potentials, if it is not null
      if (cliquePots != null) {
         for (Potential pot : cliquePots) {
            if (finalPotential == null) {
               finalPotential = pot;

               if (generateDebugInfo) {
                  System.out.println("\tComienzo con potencial:");
                  System.out.println("\t"+pot);
                  System.out.println("\t---------------------------------------------------");
               }
            } else {
               // Print debug information
               if (generateDebugInfo) {
                  System.out.println("\tContinuo con potencial: ");
                  System.out.println("\t"+pot);
                  System.out.println("\t---------------------------------------------------");
               }


                finalPotential = finalPotential.addition(pot);
                if (tree.getStatisticsFlag()) {
                    tree.statistics.addNumSums(binaryOperationCost(finalPotential));
                }
               // Print debug information
               if (generateDebugInfo) {
                  System.out.println("\tResultado parcial:");
                  System.out.println("\t"+finalPotential);
                  System.out.println("\t---------------------------------------------------");
               }
            }
         }
      }

      // Add debug information
      if (generateDebugInfo) {
         System.out.println("\tSe procede ahora a combinar con potenciales de separadores");
      }

      // The same for separator potentials
      if (sepPots != null) {
         for (Potential pot : sepPots) {
            if (finalPotential == null) {
               finalPotential = pot;

               // Add debug information
               if (generateDebugInfo) {
                  System.out.println("\tComienzo con potencial:");
                  System.out.println("\t"+pot);
                  System.out.println("\t---------------------------------------------------");
               }
            } else {
               // Add debug information
               if (generateDebugInfo) {
                  System.out.println("\tContinuo con potencial:");
                  System.out.println("\t"+pot);
                  System.out.println("\t---------------------------------------------------");
               }

               //finalPotential = finalPotential.combine(pot);
            finalPotential = finalPotential.addition(pot);
            if (tree.getStatisticsFlag()) {
                tree.statistics.addNumSums(binaryOperationCost(finalPotential));
            }
               // Add debug information
               if (generateDebugInfo) {
                  System.out.println("\tResultado parcial:");
                  System.out.println("\t"+pot);
                  System.out.println("\t---------------------------------------------------");
               }

               // Sort and bound this potential if it is a PotentialTree
               if (generateDebugInfo) {
                  System.out.println("Tam. inicial: " + finalPotential.getSize());
               }
            }
         }
      }
      

      // Show the exit from the method
      if (generateDebugInfo) {
         System.out.println("\t(Fin)----------------- combineUtilityPotentials ---------------");
         System.out.println("\tPotencial calculado:");
         if (finalPotential != null) {
            System.out.println("\t  "+finalPotential);
         } else {
            System.out.println("\t  Potencial nulo");
         }
         System.out.println("\t-------------------------------------------------------------------");
         System.out.println();
      }

      if (generateDebugInfo) {
         System.out.println("JunctionTreeNode: combineUtilityPotentials en clique "+index+" ----- END\t");
      }

      // Return the combination
      return finalPotential;
   }
 /**
    * Private method for combining the utility potentials for a given variable
    *
    * @param cliquePots clique potentials related to node
    * @param sepPots separator potentials related to node
    * @return potential
    */
   private Potential combineUtilityPotentialsGreedy(ArrayList<Potential> cliquePots,
           ArrayList<Potential> sepPots) {
      Potential pot = null;

      if (generateDebugInfo) {
         System.out.println("\nJunctionTreeNode: combineUtilityPotentials en clique "+index+" ----- BEGIN\n");
      }
      
      ArrayList<Potential> pots = new ArrayList<Potential>();
      
      if(cliquePots!=null)
        pots.addAll(cliquePots);
      if(sepPots != null)
        pots.addAll(sepPots);
      
          CombPairTable B = new CombPairTable();
        B.setAllowSingletons(false);
        B.setWithoutRemovable();
        int combinationHeuristic = tree.getPropagationID().getCombinationHeuristic();

        while (pots.size() > 1) {
            startMeasureOverHead();
            B.addCombinations(pots);
            CombPair p = B.netxToCombine(combinationHeuristic);
            stopMeasureOverHead();
            
            Potential prob_ij = p.getPot1().addition(p.getPot2());
            if (tree.getDebugFlag()) {
                tree.getStatistics().addNumSums(binaryOperationCost(prob_ij));
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



 


      if (generateDebugInfo) {
         System.out.println("JunctionTreeNode: combineUtilityPotentials en clique "+index+" ----- END\t");
      }

        // Return the potential
        return pot;
   }

   /**
    * Private method for getting the set of prob potentials related to a given
    * variable in the inferior separators of a given clique
    *
    * @param node to consider
    * @param liberate to show if the references to the potentials must be
    * liberated
    * @return potentials related to this node return null if there is no
    * potentials related to node
    */
   private ArrayList<Potential> getInferiorSeparatorsProbPotentials(Node node, boolean liberate) {
      ArrayList<Potential> separatorNodePotentials = null;
      ArrayList<Potential> separatorPotentials;

      if (generateDebugInfo) {
         System.out.println("\nJunctionTreeNode: getInferiorSeparatorsProbPotentials ----- BEGIN");
      }

      // Get the messages related to this node in the down separator
      if (down.size() != 0) {
         separatorNodePotentials = new ArrayList<Potential>();

         // Loop over them to get the potentials. When the potential
         // returned is not null, store it
         for (JunctionTreeSeparator separator : down) {
            separatorPotentials = separator.getProbMessages(node, liberate);
            // Add the potentials to separatorNodePotentials
            if (separatorPotentials != null) {
               separatorNodePotentials.addAll(separatorPotentials);
            }
         }
      }

      if (generateDebugInfo) {
          
        System.out.println("\n\tPotenciales de probabilidad con la variable "+node.getName()+" en los separadores inferiores del clique "+this.index);

 
         if(separatorNodePotentials!=null && separatorNodePotentials.isEmpty()==false)
            for(Potential p : separatorNodePotentials) {
                if(p != null)
                    System.out.println("\t  - "+p);
            } 
         else{
                System.out.println("\t    No hay potentciales");
         }  
          
          
         System.out.println("\nJunctionTreeNode: getInferiorSeparatorsProbPotentials ----- END");
      }

      // return the arraylist
      return separatorNodePotentials;
   }

   /**
    * Private method for getting the set of util potentials related to a given
    * variable in the inferior separators of a given clique
    *
    * @param node to consider
    * @param liberate to show if the references to the potentials must be
    * liberated
    * @return potentials related to this node return null if there is no
    * potentials related to node
    */
   private ArrayList<Potential> getInferiorSeparatorsUtilPotentials(Node node, boolean liberate) {
      ArrayList<Potential> separatorNodePotentials = null;
      ArrayList<Potential> separatorPotentials;

      if (generateDebugInfo) {
         System.out.println("JunctionTreeNode: getInferiorSeparatorsUtilPotentials ----- BEGIN");
      }

      // Get the messages related to this node in the down separator
      if (down.size() != 0) {
         separatorNodePotentials = new ArrayList<Potential>();

         // Loop over them to get the potentials. When the potential
         // returned is not null, store it
         for (JunctionTreeSeparator separator : down) {
            // Print debug information
            if (generateDebugInfo) {
               System.out.println("");
               System.out.println("\tBuscando potenciales de utilidad en separador: " + separator.getIndex()+"\n");
            }

            separatorPotentials = separator.getUtilMessages(node, liberate);
            
            if (generateDebugInfo) System.out.println("");
            
            
            // Add the potentials to separatorNodePotentials
            if (separatorPotentials != null) {
               separatorNodePotentials.addAll(separatorPotentials);
               if (generateDebugInfo) {
                  System.out.println("\tHay potenciales de utilidad: " + separatorPotentials.size());
                  System.out.println("\tAcumulados: " + separatorNodePotentials.size());
               }
            }
         }
      }

      if (generateDebugInfo) {
          
          
System.out.println("\tPotenciales de utilidad con la variable "+node.getName()+" en los separadores inferiores del clique "+this.index);         if(separatorNodePotentials!=null && separatorNodePotentials.isEmpty()==false)
             for(Potential p : separatorNodePotentials) {
                if(p != null)
                    System.out.println("\t  - "+p);
            } 
         else{
                System.out.println("\t    No hay potentciales");
         }  
         
          
         System.out.println("\nJunctionTreeNode: getInferiorSeparatorsUtilPotentials ----- END");
      }

      // return the arraylist
      return separatorNodePotentials;
   }

   /**
    * Private method to check if a given clique is leaf node or not
    *
    * @return boolean value
    */
   private boolean isLeafNode() {
      if (down.size() == 0) {
         return true;
      } else {
         return false;
      }
   }

   /**
    * Method to add a downward neighbour to this node
    *
    * @param node junction tree node to add as neighbour
    * @param separator
    */
   private void addDownwardNeighbour(JunctionTreeNode node, NodeList separator) {
      // Create a new JunctionTreeSeparator

      if (generateDebugInfo) {
         System.out.println("JunctionTreeNode: addDownwardNeighbour ----- BEGIN");
      }

      JunctionTreeSeparator nodeSeparator = new JunctionTreeSeparator(this, node,
              separator, generateDebugInfo);

      // Add the nodeSeparator to down
      down.add(nodeSeparator);

      // But now, the node passed as argument will have this separator
      // as upward neighbour
      node.setUpwardNeighbour(nodeSeparator);

      if (generateDebugInfo) {
         System.out.println("JunctionTreeNode: addDownwardNeighbour ----- END");
      }
   }

   /**
    * Private method to set the upward neighbour to this node
    *
    * @param node juntion tree node to add as neighbour
    * @param separator
    */
   private void setUpwardNeighbour(JunctionTreeSeparator separator) {

      if (generateDebugInfo) {
         System.out.println("JunctionTreeNode: setUpwardNeighbour ----- BEGIN");
      }

      // Set the nodeSeparator to up
      up = separator;

      if (generateDebugInfo) {
         System.out.println("JunctionTreeNode: setUpwardNeighbour ----- END");
      }
   }

   /**
    * Public method to assign a potential to the clique. This method will be
    * called on the root node
    *
    * @param potential
    * @param kind of potential (utility, probability, constraint)
    */
   public void assignPotential(Potential potential, int kind) {
      JunctionTreeSeparator separator;
      JunctionTreeNode jTreeNode;
      HashMap<Integer, Double> candidates;
      double size;

      if (generateDebugInfo) {
         System.out.println("JunctionTreeNode: assignPotential to Clique "+index+" ----- BEGIN");
      }

      // Two rounds are needed: the first one computes the cost of assigning
      // this potential, and the second one to make the assignment
      candidates = new HashMap<Integer, Double>();

      // Consider if it can be assigned to the root
      if (containsPotentialVars(potential)) {
         size = totalSize() + potential.getSize();
         candidates.put(index, size);
      }

      // Anyway, consider the rest of cliques
      for (int i = 0; i < down.size(); i++) {
         separator = down.get(i);
         jTreeNode = separator.getInferiorNeighbour();

         // Test it
         jTreeNode.assignPotential(potential, candidates);
      }

      // When this is finished, loop over candidates to determine
      // the better clique for the assignment
      double min = Double.MAX_VALUE;
      double value;
      int candidateIndex = 0, finalIndex = 0;

      Iterator<Integer> indexes = candidates.keySet().iterator();
      while (indexes.hasNext()) {
         candidateIndex = indexes.next();
         value = candidates.get(candidateIndex);
         if (value < min) {
            finalIndex = candidateIndex;
            min = value;
         }
      }

      // Assign the potential to the selected clique. If it is a 
      // constraint will be assignet to each candidate clique
      if (kind != Relation.CONSTRAINT) {
         assignPotential(potential, finalIndex, kind);
      } else {
         int j = 1;
         //Assign the potential to every candidate
         indexes = candidates.keySet().iterator();
         while (indexes.hasNext()) {
            candidateIndex = indexes.next();
            assignPotential(potential, candidateIndex, kind);
         }
      }

      if (generateDebugInfo) {
         System.out.println("JunctionTreeNode: assignPotential to Clique "+index+" ----- END");
      }
   }

   /**
    * Public method for getting the size of the probability trees related to a
    * clique and the inferior neighbours or if
    *
    * @return size
    */
   public double getProbSize() {
      HashSet<Potential> probPots = new HashSet();
      ArrayList<Potential> potentials;
      JunctionTreeNode infNeighbour;
      double size = 0;
      double partialSize = 0;

      if (generateDebugInfo) {
         System.out.println("JunctionTreeNode: getProbSize ----- BEGIN");
      }

      // Compute the size for the probability potentials related to the
      // clique
      Iterator<ArrayList<Potential>> iterator = probPotentials.values().iterator();

      // Every position will return an array list 
      while (iterator.hasNext()) {
         potentials = iterator.next();
         for (Potential potential : potentials) {
            if (probPots.add(potential)) {
               partialSize += potential.getSize();
            }
         }
      }

      if (generateDebugInfo) {
         System.out.println("\tProb size (" + index + ") = " + partialSize);
      }

      // Now consider the inferior separators
      for (JunctionTreeSeparator downSep : down) {
         potentials = downSep.getProbMessages();
         if (potentials != null) {
            // Add the potentials to probPots

            for (Potential potential : potentials) {
               if (probPots.add(potential)) {
                  if (generateDebugInfo) {
                     System.out.println("\tProb. separador (" + downSep.getIndex() + ") = " + potential.getSize());
                  }
               }
            }
         }

         // Anyyway, consider the related inferior neighbour
         infNeighbour = downSep.getInferiorNeighbour();

         // Compute the size of potentials from it
         infNeighbour.getProbSize(probPots);
      }

      size = computeSizes(probPots);

      if (generateDebugInfo) {
         System.out.println("JunctionTreeNode: getProbSize ----- END");
      }

      // Return size
      return size;
   }

   /**
    * Private auxiliar method for getting the size of the probability trees
    * related to a clique
    */
   public void getProbSize(HashSet<Potential> probPots) {
      ArrayList<Potential> potentials;
      JunctionTreeNode infNeighbour;
      double partialSize = 0;

      if (generateDebugInfo) {
         System.out.println("JunctionTreeNode: getProbSize(HashSet) ----- BEGIN");
      }

      // Compute the size for the probability potentials related to the
      // clique
      Iterator<ArrayList<Potential>> iterator = probPotentials.values().iterator();

      // Every position will return an array list 
      while (iterator.hasNext()) {
         potentials = iterator.next();
         for (Potential potential : potentials) {
            if (probPots.add(potential)) {
               partialSize += potential.getSize();
            }
         }
      }

      if (generateDebugInfo) {
         System.out.println("\tProb size (" + index + ") = " + partialSize);
      }

      // Now consider the inferior separators
      for (JunctionTreeSeparator downSep : down) {
         potentials = downSep.getProbMessages();
         if (potentials != null) {
            // Add the potentials to probPots
            for (Potential potential : potentials) {
               if (probPots.add(potential)) {
                  if (generateDebugInfo) {
                     System.out.println("\tProb. separador (" + downSep.getIndex() + ") = " + potential.getSize());
                  }
               }
            }
         }

         // Anyyway, consider the related inferior neighbour
         infNeighbour = downSep.getInferiorNeighbour();

         // Compute the size of potentials from it
         infNeighbour.getProbSize(probPots);
      }

      if (generateDebugInfo) {
         System.out.println("JunctionTreeNode: getProbSize(HashSet) ----- END");
      }
   }

   
   
   
      /**
    * Public method for getting the size of the probability trees (in number of nodes) related to a
    * clique and the inferior neighbours or if
    *
    * @return size
    */
   public double getProbNodesSize() {
      HashSet<Potential> probPots = new HashSet();
      ArrayList<Potential> potentials;
      JunctionTreeNode infNeighbour;
      double size = 0;
      double partialSize = 0;

      if (generateDebugInfo) {
         System.out.println("JunctionTreeNode: getProbNodesSize ----- BEGIN");
      }

      // Compute the size for the probability potentials related to the
      // clique
      Iterator<ArrayList<Potential>> iterator = probPotentials.values().iterator();

      // Every position will return an array list 
      while (iterator.hasNext()) {
         potentials = iterator.next();
         for (Potential potential : potentials) {
            if (probPots.add(potential)) {
               partialSize += potential.getNumberOfNodes();
            }
         }
      }

      if (generateDebugInfo) {
         System.out.println("\tProb size (" + index + ") = " + partialSize);
      }

      // Now consider the inferior separators
      for (JunctionTreeSeparator downSep : down) {
         potentials = downSep.getProbMessages();
         if (potentials != null) {
            // Add the potentials to probPots

            for (Potential potential : potentials) {
               if (probPots.add(potential)) {
                  if (generateDebugInfo) {
                     System.out.println("\tProb. separador (" + downSep.getIndex() + ") = " + potential.getSize());
                  }
               }
            }
         }

         // Anyyway, consider the related inferior neighbour
         infNeighbour = downSep.getInferiorNeighbour();

         // Compute the size of potentials from it
         infNeighbour.getProbSize(probPots);
      }

      size = computeSizes(probPots);

      if (generateDebugInfo) {
         System.out.println("JunctionTreeNode: getProbNodesSize ----- END");
      }

      // Return size
      return size;
   }

   /**
    * Private auxiliar method for getting the size of the probability trees
    * (in number of nodes) related to a clique
    */
   public void getProbNodesSize(HashSet<Potential> probPots) {
      ArrayList<Potential> potentials;
      JunctionTreeNode infNeighbour;
      double partialSize = 0;

      if (generateDebugInfo) {
         System.out.println("JunctionTreeNode: getProbNodesSize(HashSet) ----- BEGIN");
      }

      // Compute the size for the probability potentials related to the
      // clique
      Iterator<ArrayList<Potential>> iterator = probPotentials.values().iterator();

      // Every position will return an array list 
      while (iterator.hasNext()) {
         potentials = iterator.next();
         for (Potential potential : potentials) {
            if (probPots.add(potential)) {
               partialSize += potential.getNumberOfNodes();
            }
         }
      }

      if (generateDebugInfo) {
         System.out.println("\tProb size (" + index + ") = " + partialSize);
      }

      // Now consider the inferior separators
      for (JunctionTreeSeparator downSep : down) {
         potentials = downSep.getProbMessages();
         if (potentials != null) {
            // Add the potentials to probPots
            for (Potential potential : potentials) {
               if (probPots.add(potential)) {
                  if (generateDebugInfo) {
                     System.out.println("\tProb. separador (" + downSep.getIndex() + ") = " + potential.getSize());
                  }
               }
            }
         }

         // Anyyway, consider the related inferior neighbour
         infNeighbour = downSep.getInferiorNeighbour();

         // Compute the size of potentials from it
         infNeighbour.getProbNodesSize(probPots);
      }

      if (generateDebugInfo) {
         System.out.println("JunctionTreeNode: getProbNodesSize(HashSet) ----- END");
      }
   }
   
   
   
   
   
   /**
    * Gets the variables associated to the clique
    * @return 
    */
    public NodeList getVariables() {
        return variables;
    }

   
   
   
   
   
   
   
   
   
   
   
   
   /**
    * Public method for getting the size of the utility trees related to a
    * clique and the inferior neighbours
    *
    * @return size
    */
   public double getUtilSize() {
      HashSet<Potential> utilPots = new HashSet();
      ArrayList<Potential> potentials;
      JunctionTreeNode infNeighbour;
      double size = 0;
      double partialSize = 0;

      if (generateDebugInfo) {
         System.out.println("JunctionTreeNode: getUtilSize ----- BEGIN");
      }

      // Compute the size for the probability potentials related to the
      // clique
      Iterator<ArrayList<Potential>> iterator = utilPotentials.values().iterator();

      // Every position will return an array list 
      while (iterator.hasNext()) {
         potentials = iterator.next();
         for (Potential potential : potentials) {
            // Add the potential
            if (utilPots.add(potential)) {
               partialSize += potential.getSize();
            }
         }
      }

      if (generateDebugInfo) {
         System.out.println("\tUtil size (" + index + ") = " + partialSize);
      }

      // Now consider the inferior separators
      for (JunctionTreeSeparator downSep : down) {
         potentials = downSep.getUtilMessages();
         if (potentials != null) {
            for (Potential potential : potentials) {
               if (utilPots.add(potential)) {
                  if (generateDebugInfo) {
                     System.out.println("\tUtil. separador (" + downSep.getIndex() + ") = " + potential.getSize());
                  }
               }
            }
         }

         // Anyyway, consider the related inferior neighbour
         infNeighbour = downSep.getInferiorNeighbour();

         // Compute the size of potentials from it
         infNeighbour.getUtilSize(utilPots);
      }

      // When this is done, compute the sizes for the potentials in
      // utilPots
      size = computeSizes(utilPots);

      if (generateDebugInfo) {
         System.out.println("JunctionTreeNode: getUtilSize ----- END");
      }

      // Return size
      return size;
   }

   /**
    * Private auxiliar method for computing the sizes of the utility potentials
    *
    * @param set of utility potentials already considered
    */
   private void getUtilSize(HashSet<Potential> utilPots) {
      ArrayList<Potential> potentials;
      JunctionTreeNode infNeighbour;
      double partialSize = 0;

      if (generateDebugInfo) {
         System.out.println("JunctionTreeNode: getUtilSize(HashSet) ----- BEGIN");
      }

      // Get the utility potentials for the clique
      Iterator<ArrayList<Potential>> iterator = utilPotentials.values().iterator();

      // Every position will return an array list 
      while (iterator.hasNext()) {
         potentials = iterator.next();
         for (Potential potential : potentials) {
            // Add the potential
            if (utilPots.add(potential)) {
               partialSize += potential.getSize();
            }
         }
      }

      if (generateDebugInfo) {
         System.out.println("\tUtil size (" + index + ") = " + partialSize);
      }

      // Now consider the inferior separators
      for (JunctionTreeSeparator downSep : down) {
         potentials = downSep.getUtilMessages();
         if (potentials != null) {
            for (Potential potential : potentials) {
               if (utilPots.add(potential)) {
                  if (generateDebugInfo) {
                     System.out.println("\tUtil. separador (" + downSep.getIndex() + ") = " + potential.getSize());
                  }
               }
            }
         }

         // Anyyway, consider the related inferior neighbour
         infNeighbour = downSep.getInferiorNeighbour();

         // Compute the size of potentials from it
         infNeighbour.getUtilSize(utilPots);
      }

      if (generateDebugInfo) {
         System.out.println("JunctionTreeNode: getUtilSize(HashSet) ----- END");
      }
   }
 /**
    * Public method for getting the size of the utility trees related to a
    * clique and the inferior neighbours
    *
    * @return size
    */
   public double getUtilNodesSize() {
      HashSet<Potential> utilPots = new HashSet();
      ArrayList<Potential> potentials;
      JunctionTreeNode infNeighbour;
      double size = 0;
      double partialSize = 0;

      if (generateDebugInfo) {
         System.out.println("JunctionTreeNode: getUtilSize ----- BEGIN");
      }

      // Compute the size for the probability potentials related to the
      // clique
      Iterator<ArrayList<Potential>> iterator = utilPotentials.values().iterator();

      // Every position will return an array list 
      while (iterator.hasNext()) {
         potentials = iterator.next();
         for (Potential potential : potentials) {
            // Add the potential
            if (utilPots.add(potential)) {
               partialSize += potential.getNumberOfNodes();
            }
         }
      }

      if (generateDebugInfo) {
         System.out.println("\tUtil size (" + index + ") = " + partialSize);
      }

      // Now consider the inferior separators
      for (JunctionTreeSeparator downSep : down) {
         potentials = downSep.getUtilMessages();
         if (potentials != null) {
            for (Potential potential : potentials) {
               if (utilPots.add(potential)) {
                  if (generateDebugInfo) {
                     System.out.println("\tUtil. separador (" + downSep.getIndex() + ") = " + potential.getNumberOfNodes());
                  }
               }
            }
         }

         // Anyyway, consider the related inferior neighbour
         infNeighbour = downSep.getInferiorNeighbour();

         // Compute the size of potentials from it
         infNeighbour.getUtilNodesSize(utilPots);
      }

      // When this is done, compute the sizes for the potentials in
      // utilPots
      size = computeNodesSizes(utilPots);

      if (generateDebugInfo) {
         System.out.println("JunctionTreeNode: getUtilSize ----- END");
      }

      // Return size
      return size;
   }

   /**
    * Private auxiliar method for computing the sizes of the utility potentials
    *
    * @param set of utility potentials already considered
    */
   private void getUtilNodesSize(HashSet<Potential> utilPots) {
      ArrayList<Potential> potentials;
      JunctionTreeNode infNeighbour;
      double partialSize = 0;

      if (generateDebugInfo) {
         System.out.println("JunctionTreeNode: getUtilSize(HashSet) ----- BEGIN");
      }

      // Get the utility potentials for the clique
      Iterator<ArrayList<Potential>> iterator = utilPotentials.values().iterator();

      // Every position will return an array list 
      while (iterator.hasNext()) {
         potentials = iterator.next();
         for (Potential potential : potentials) {
            // Add the potential
            if (utilPots.add(potential)) {
               partialSize += potential.getNumberOfNodes();
            }
         }
      }

      if (generateDebugInfo) {
         System.out.println("\tUtil size (" + index + ") = " + partialSize);
      }

      // Now consider the inferior separators
      for (JunctionTreeSeparator downSep : down) {
         potentials = downSep.getUtilMessages();
         if (potentials != null) {
            for (Potential potential : potentials) {
               if (utilPots.add(potential)) {
                  if (generateDebugInfo) {
                     System.out.println("\tUtil. separador (" + downSep.getIndex() + ") = " + potential.getNumberOfNodes());
                  }
               }
            }
         }

         // Anyyway, consider the related inferior neighbour
         infNeighbour = downSep.getInferiorNeighbour();

         // Compute the size of potentials from it
         infNeighbour.getUtilNodesSize(utilPots);
      }

      if (generateDebugInfo) {
         System.out.println("JunctionTreeNode: getUtilSize(HashSet) ----- END");
      }
   }
   
    /**
     * Return the index of the clique
     * @return 
     */
    public int getIndex() {
        return index;
    }
   
   
   
   
   

   /**
    * Public method for getting the size of the constraint trees related to a
    * clique and the inferior neighbours
    *
    * @return size
    */
   public double getConstraintSize() {
      HashSet<Potential> constraintPots = new HashSet();
      ArrayList<Potential> potentials;
      JunctionTreeNode infNeighbour;
      double size = 0;
      double partialSize = 0;

      if (generateDebugInfo) {
         System.out.println("JunctionTreeNode: getConstraintSize ----- BEGIN");
      }

      // If there are not constraints, return 0
      if (constraintPotentials == null) {
         return 0;
      }

      // Compute the size for the constraint potentials related to the
      // clique
      Iterator<ArrayList<Potential>> iterator = constraintPotentials.values().iterator();

      // Every position will return an array list 
      while (iterator.hasNext()) {
         potentials = iterator.next();
         for (Potential potential : potentials) {
            // Add the potential
            if (constraintPots.add(potential)) {
               partialSize += potential.getSize();
            }
         }
      }

      if (generateDebugInfo) {
         System.out.println("\tConstraints size (" + index + ") = " + partialSize);
      }

      for (JunctionTreeSeparator downSep : down) {
         // Consider the related inferior neighbour
         infNeighbour = downSep.getInferiorNeighbour();

         // Compute the size of potentials from it
         infNeighbour.getConstraintSize(constraintPots);
      }

      // When this is done, compute the sizes for the potentials in
      // constraintPots
      size = computeSizes(constraintPots);

      if (generateDebugInfo) {
         System.out.println("JunctionTreeNode: getConstraintSize ----- END");
      }

      // Return size
      return size;
   }

   /**
    * Private auxiliar method for computing the sizes of the constraint
    * potentials
    *
    * @param set of constraint potentials already considered
    */
   private void getConstraintSize(HashSet<Potential> constraintPots) {
      ArrayList<Potential> potentials;
      JunctionTreeNode infNeighbour;
      double partialSize = 0;

      if (generateDebugInfo) {
         System.out.println("JunctionTreeNode: getConstraintSize(HashSet) ----- BEGIN");
      }

      // Get the constraint potentials for the clique
      Iterator<ArrayList<Potential>> iterator = constraintPotentials.values().iterator();

      // Every position will return an array list 
      while (iterator.hasNext()) {
         potentials = iterator.next();
         for (Potential potential : potentials) {
            // Add the potential
            if (constraintPots.add(potential)) {
               partialSize += potential.getSize();
            }
         }
      }

      if (generateDebugInfo) {
         System.out.println("\tConstraints size (" + index + ") = " + partialSize);
      }

      // Now consider the inferior separators
      for (JunctionTreeSeparator downSep : down) {
         // Anyyway, consider the related inferior neighbour
         infNeighbour = downSep.getInferiorNeighbour();

         // Compute the size of potentials from it
         infNeighbour.getConstraintSize(constraintPots);
      }

      if (generateDebugInfo) {
         System.out.println("JunctionTreeNode: getConstraintSize(HashSet) ----- END");
      }
   }
   
   
      /**
    * Public method for getting the size of the constraint trees related to a
    * clique and the inferior neighbours
    *
    * @return size
    */
   public double getConstraintNodesSize() {
      HashSet<Potential> constraintPots = new HashSet();
      ArrayList<Potential> potentials;
      JunctionTreeNode infNeighbour;
      double size = 0;
      double partialSize = 0;

      if (generateDebugInfo) {
         System.out.println("JunctionTreeNode: getConstraintSize ----- BEGIN");
      }

      // If there are not constraints, return 0
      if (constraintPotentials == null) {
         return 0;
      }

      System.out.println("getConstraintNodesSize no implementado");
      System.exit(1);
      
      // Compute the size for the constraint potentials related to the
      // clique
      Iterator<ArrayList<Potential>> iterator = constraintPotentials.values().iterator();

      // Every position will return an array list 
      while (iterator.hasNext()) {
         potentials = iterator.next();
         for (Potential potential : potentials) {
            // Add the potential
            if (constraintPots.add(potential)) {
               partialSize += potential.getSize();
            }
         }
      }

      if (generateDebugInfo) {
         System.out.println("\tConstraints size (" + index + ") = " + partialSize);
      }

      for (JunctionTreeSeparator downSep : down) {
         // Consider the related inferior neighbour
         infNeighbour = downSep.getInferiorNeighbour();

         // Compute the size of potentials from it
         infNeighbour.getConstraintSize(constraintPots);
      }

      // When this is done, compute the sizes for the potentials in
      // constraintPots
      size = computeSizes(constraintPots);

      if (generateDebugInfo) {
         System.out.println("JunctionTreeNode: getConstraintSize ----- END");
      }

      // Return size
      return size;
   }
   

   /**
    * Method for computing the sizes of a set of potentials
    *
    * @param set of potentials
    */
   private double computeSizes(HashSet<Potential> pots) {
      Potential potential;
      double size = 0;

      Iterator<Potential> iterator = pots.iterator();
      while (iterator.hasNext()) {
         potential = iterator.next();
         if (potential.getClassName().equals("PotentialTree")) {
            PotentialTree pot = (PotentialTree) potential;
            if (pot.checkSize() == false) {
               pot.updateSize();
            }
            size += pot.getSize();
         } else {
            size += potential.getSize();
         }
      }

      // return size
      return size;
   }
   
   
   
      /**
    * Method for computing the sizes of a set of potentials
    * (number of nodes of the potential tree)
    * @param set of potentials
    */
   private double computeNodesSizes(HashSet<Potential> pots) {
      Potential potential;
      double size = 0;

      Iterator<Potential> iterator = pots.iterator();
      while (iterator.hasNext()) {
         potential = iterator.next();

            size += potential.getNumberOfNodes();
   
      }

      // return size
      return size;
   }

   /**
    * Method for looking for a node with a given index
    *
    * @param index to search for
    * @return node with this index
    */
   public JunctionTreeNode lookForNode(int cliqueIndex) {
      JunctionTreeNode treeNode;
      JunctionTreeNode found = null;

      if (generateDebugInfo) {
         System.out.println("JunctionTreeNode: lookForNode ----- BEGIN");
      }

      if (this.index == cliqueIndex) {
         found = this;
      }

      for (Iterator<JunctionTreeSeparator> it = down.iterator(); it.hasNext() && found == null;) {
         JunctionTreeSeparator separator = it.next();
         treeNode = separator.getInferiorNeighbour();
         found = treeNode.lookForNode(cliqueIndex);
      }

      if (generateDebugInfo) {
         System.out.println("JunctionTreeNode: lookForNode ----- END");
      }

      // If this point is reached, return null
      return found;
   }

   /**
    * Public method to assign a potential to a clique. This is an auxiliar
    * method that will never be used directly with the root clique
    *
    * @param potential
    * @param candidates hashmap with the costs of assignments
    */
   private void assignPotential(Potential potential, HashMap candidates) {
      JunctionTreeSeparator separator;
      JunctionTreeNode jTreeNode;
      double size;

      if (generateDebugInfo) {
         System.out.println("JunctionTreeNode: assignPotential(Potential, HashMap) to Clique "+index+" ----- BEGIN");
      }

      // Consider if it can be assigned to this clique
      if (containsPotentialVars(potential)) {
         size = totalSize() + potential.getSize();
         candidates.put(index, size);
      }

      // Anyway, consider the rest of the cliques
      for (int i = 0; i < down.size(); i++) {
         separator = down.get(i);
         jTreeNode = separator.getInferiorNeighbour();

         // Assign it
         jTreeNode.assignPotential(potential, candidates);
      }

      if (generateDebugInfo) {
         System.out.println("JunctionTreeNode: assignPotential(Potential, HashMap) to Clique "+index+" ----- END");
      }
   }

   /**
    * Private method to assign the potential to a node with a given index
    *
    * @param potential to assign
    * @param index of the junction tree node
    * @param kind of potential
    */
   private void assignPotential(Potential potential, int index, int kind) {
      // First at all, treverse the tree until reaching the junction
      // tree node with the given index
      JunctionTreeNode jTreeNode = null;
      JunctionTreeSeparator separator;

      if (generateDebugInfo) {
         System.out.println("JunctionTreeNode: assignPotential(Potential, int, int) ----- BEGIN");
      }


      
      if (this.index == index) {
          

          
         addPotential(potential, kind);
         if (generateDebugInfo) {
            System.out.println("\tIndice: " + index);
            System.out.println("\t...................................................");
         }
      } else {
         // Consider the rest of nodes
         for (int i = 0; i < down.size(); i++) {
            separator = down.get(i);

            // Consider the juntion tree node
            jTreeNode = separator.getInferiorNeighbour();
            if (jTreeNode.index == index) {
               if (generateDebugInfo) {
                  System.out.println("\tIndice: " + index);
                  System.out.println("\t...................................................");
               }
               jTreeNode.addPotential(potential, kind);
            } else {
               // Look from this clique down
               jTreeNode.assignPotential(potential, index, kind);
            }
         }
      }
      if (generateDebugInfo) {
         System.out.println("JunctionTreeNode: assignPotential(Potential, int, int) ----- END");
      }
   }

   /**
    * Method for adding a potential to a junction tree node
    *
    * @param potential to include
    * @param kind of potential to assign
    */
   private void addPotential(Potential potential, int kind) {

      if (generateDebugInfo) {
         System.out.println("JunctionTreeNode: addPotential ----- BEGIN");
      }
      
      
      
      //If initial transformations are not performed before
      //building the tree, they are made now
       if(tree.getPropagationID().isTransformAtTree()) {
            
            
            Vector<Node> sepVars = new Vector<Node>();
            if(up != null)
                sepVars = up.getVariables().getNodes();
            potential = tree.getPropagationID().transformInitialPotential(potential, kind, sepVars);
       }
      
      
      
      switch (kind) {
         case Relation.UTILITY:
            addUtilityPotential(potential);
            break;
         case Relation.CONSTRAINT:
            addConstraintPotential(potential);
            break;
         default:
            addProbabilityPotential(potential);
            break;
      }
      if (generateDebugInfo) {
         System.out.println("JunctionTreeNode: addPotential ----- END");
      }
   }

   
      /**
    * Method to add a set of  potentials to a hashmap
    *
    * @param potential
    */
   protected void addPotentialToHashMap(ArrayList<Potential> pot, HashMap<Node, ArrayList<Potential>> map) {
       for(Potential p : pot) {
           addPotentialToHashMap(p, map);
       }
   
   }
   
   
   
   
   
      /**
    * Method to add a  potential to a hashmap
    *
    * @param potential
    */
   protected void addPotentialToHashMap(Potential potential, HashMap<Node, ArrayList<Potential>> map) {
      Vector variables = potential.getVariables();
      Node node;

      if (generateDebugInfo) {
         System.out.println("JunctionTreeNode: addPotentialToHashMap ----- BEGIN\n");
      }

      // Show debug information
      if (generateDebugInfo) {
         System.out.println("\tAgregar potencial  a hashmap en clique: " + index);
         System.out.println("\t"+potential);
         System.out.println("\t-------------------------------------------------");
      }

      // To get the variables related to the potentials where they
      // appear, consider the variables one by one
      for (int i = 0; i < variables.size(); i++) {
         node = (Node) variables.elementAt(i);

         // See if it is included in probPotentials
         if (map.get(node) == null) {
            // Create a new ArrayList to include the potential
            ArrayList<Potential> potentials = new ArrayList<Potential>();
            potentials.add(potential);

            // Include the pair into probPotentials
            map.put(node, potentials);
         } else {
            // Get the array list
            ArrayList<Potential> potentials = map.get(node);

            // Include the potential
            potentials.add(potential);
         }
      }

      if (generateDebugInfo) {
         System.out.println("\nJunctionTreeNode: addPotentialToHashMap ----- END");
      }
   }
   
   
    /**
    * Method to add a set of probability potential to this junction tree node
    *
    * @param potential
    */
   protected void addProbabilityPotential(ArrayList<Potential> pots) {
       for(Potential p : pots) {
           addProbabilityPotential(p);
       }
       
   }
   
   
   
   /**
    * Method to add a probability potential to this junction tree node
    *
    * @param potential
    */
   protected void addProbabilityPotential(Potential potential) {
      Vector variables = potential.getVariables();
      Node node;

      if (generateDebugInfo) {
         System.out.println("JunctionTreeNode: addProbabilityPotential ----- BEGIN\n");
      }


      
      // Show debug information
      if (generateDebugInfo) {
         System.out.println("\tAgregar potencial de probabilidad a nodo: " + index);
         System.out.println("\t"+potential);
         System.out.println("\t-------------------------------------------------");
      }

      // To get the variables related to the potentials where they
      // appear, consider the variables one by one
      for (int i = 0; i < variables.size(); i++) {
         node = (Node) variables.elementAt(i);

         // See if it is included in probPotentials
         if (probPotentials.get(node) == null) {
            // Create a new ArrayList to include the potential
            ArrayList<Potential> potentials = new ArrayList<Potential>();
            potentials.add(potential);

            // Include the pair into probPotentials
            probPotentials.put(node, potentials);
         } else {
            // Get the array list
            ArrayList<Potential> potentials = probPotentials.get(node);

            // Include the potential
            potentials.add(potential);
         }
      }

      if (generateDebugInfo) {
         System.out.println("\nJunctionTreeNode: addProbabilityPotential ----- END");
      }
   }

   /**
    * Method to add a set of utility potentials to this junction tree node
    *
    * @param potential
    */
   
    protected void addUtilityPotential(ArrayList<Potential> pots) {
       for(Potential p : pots) {
           addUtilityPotential(p);
       }
       
   }
   
      /**
    * Method to add an utility potential to this junction tree node
    *
    * @param potential
    */
   protected void addUtilityPotential(Potential potential) {
      Vector variables = potential.getVariables();
      Node node;

      if (generateDebugInfo) {
         System.out.println("JunctionTreeNode: addUtilityPotential ----- BEGIN\n");
      }

      // Show debug information
      if (generateDebugInfo) {
         System.out.println("\tAgregar potencial de utilidad a nodo: " + index);
         System.out.println("\t"+potential);
         System.out.println("\t-------------------------------------------------");
      }

      // To get the variables related to the potentials where they
      // appear, consider the variables one by one
      for (int i = 0; i < variables.size(); i++) {
         node = (Node) variables.elementAt(i);

         // See if it is included in utilPotentials
         if (utilPotentials.get(node) == null) {
            // Create a nuew ArrayList to include the potential
            ArrayList<Potential> potentials = new ArrayList<Potential>();
            potentials.add(potential);

            // Include the pair into probPotentials
            utilPotentials.put(node, potentials);
         } else {
            // Get the array list
            ArrayList<Potential> potentials = utilPotentials.get(node);

            // Include the potential
            potentials.add(potential);
         }
      }
      
      //It is the MEU
      if(this.tree.getPropagationID().isComputeMEU() && variables.size()==0){
          this.tree.getPropagationID().getStatistics().setFinalExpectedUtility(potential); 
      }

      if (generateDebugInfo) {
         System.out.println("\nJunctionTreeNode: addUtilityPotential ----- END");
      }
   }


   /**
    * Method to add a constraint potential to this junction tree node
    *
    * @param potential
    */
   protected void addConstraintPotential(Potential potential ) {
   }

   /**
    * Private method to determine the total size of the potentials assigned to
    * the clique
    *
    * @return size
    */
   public double totalSize() {
      ArrayList potentials;
      Potential potential;
      double size = 0;

      if (generateDebugInfo) {
         System.out.println("JunctionTreeNode: totalSize ----- BEGIN");
      }

      // Begin with prob potentials
      Iterator<ArrayList<Potential>> iterator = probPotentials.values().iterator();

      // Every position will return an array list 
      while (iterator.hasNext()) {
         potentials = iterator.next();
         for (int i = 0; i < potentials.size(); i++) {
            potential = (Potential) potentials.get(i);
            size += potential.getSize();
         }
      }

      // The same for util potentials
      iterator = utilPotentials.values().iterator();
      while (iterator.hasNext()) {
         potentials = iterator.next();
         for (int i = 0; i < potentials.size(); i++) {
            potential = (Potential) potentials.get(i);
            size += potential.getSize();
         }
      }

      // The same for constraint potentials
      if (constraintPotentials != null) {
         iterator = constraintPotentials.values().iterator();
         while (iterator.hasNext()) {
            potentials = iterator.next();
            for (int i = 0; i < potentials.size(); i++) {
               potential = (Potential) potentials.get(i);
               size += potential.getSize();
            }
         }
      }

      if (generateDebugInfo) {
         System.out.println("JunctionTreeNode: totalSize ----- END");
      }

      // Return the size
      return size;
   }

   /**
    * Private method to check if the variables of a potential are included in
    * the list of variables of the junction tree node
    *
    * @param potential
    * @return boolean value
    */
   private boolean containsPotentialVars(Potential potential) {
      // Get the variables of the potential
      Vector varsPotential = potential.getVariables();
      Node node;

      if (generateDebugInfo) {
         System.out.println("JunctionTreeNode: containsPotentialVars ----- BEGIN");
      }

      // Consider them one by one
      for (int i = 0; i < varsPotential.size(); i++) {
         node = (Node) varsPotential.elementAt(i);

         // Look if it is included into variables (the variables of
         // this juntion tree node)
         if (variables.getId(node.getName()) == -1) {
            // There is one variable not include in the list of variables
            // of the junction tree node, and return false
            return false;
         }
      }

      if (generateDebugInfo) {
         System.out.println("JunctionTreeNode: containsPotentialVars ----- END");
      }

      // If this point is reached, return true
      return true;
   }

   
   
   static public HashMap<Node, ArrayList<Potential>> copyPotentialMap(HashMap<Node, ArrayList<Potential>> map) {
       return createPotentialMap(getArrayListPot(map, true));
   
   }
   
   
   
   static public ArrayList<Potential> getArrayListPot(HashMap<Node, ArrayList<Potential>> map, boolean copy) {
       
        Set<Node> keys = map.keySet();
        
        
        ArrayList<Potential> allPots = new ArrayList<Potential>();
        
        Iterator<Node> iterator = keys.iterator();
        ArrayList<Potential> potsNode = null;
        Potential pot = null;
        while(iterator.hasNext()) {
            potsNode = map.get(iterator.next());
            for(int i=0; i<potsNode.size(); i++) {
                pot =potsNode.get(i);
                if(!allPots.contains(pot))
                    allPots.add(pot);
            }
        
        }
        

        if(copy) {
            for(int i=0; i<allPots.size(); i++) {
                allPots.add(allPots.remove(i).copy());
            }
        
        }
        

        
        return allPots;
   
   
   }
   
   
   static public HashMap<Node, ArrayList<Potential>> createPotentialMap(ArrayList<Potential> list) {
   
       HashMap<Node, ArrayList<Potential>> map = new HashMap<Node, ArrayList<Potential>>();
       
       Potential pot = null;
       Vector<Node> vars = null;
       ArrayList<Potential> listNode = null;
       for(int i=0; i<list.size(); i++) {
           pot = list.get(i);
           vars = pot.getVariables();
           for(Node n : vars) {
               if(!map.containsKey(n)) {
                   listNode = new ArrayList<Potential>();
                   listNode.add(pot);
               }
               else{
                   listNode = map.remove(n);
                   listNode.add(pot);
               }
               
               map.put(n, listNode);
           
           }
       }
       
       return map;
   }
   
   
   
   public void computeRelevantPotentials() {
      if (generateDebugInfo) {
         System.out.println("JunctionTreeNode: computeRelevantPotentials ----- BEGIN");
      }
      
      
      
      
      
      
      
      if (generateDebugInfo) {
         System.out.println("JunctionTreeNode: computeRelevantPotentials ----- END");
      }
       
       
   
   }
   
   
   
      /**
    * Method to get and optionally liberate the references for the potentials
    * related to a node set
    *
    * @param node
    * @param liberate
    * @return list of probability potentials related to node
    */
   private ArrayList<Potential> getProbabilityPotentials(NodeList setNodes, boolean liberate) {
      ArrayList<Potential> relevantPots = new ArrayList<Potential>();

      for(int i=0; i<setNodes.size(); i++){
          Node node = setNodes.getNodes().elementAt(i);
          ArrayList<Potential> nodePots = getProbabilityPotentials(node, liberate);
          if(nodePots != null)
              relevantPots.addAll(nodePots);
      }
      

      return relevantPots;
   }
   
   
   
    /**
    * Method to get and optionally liberate the references for the potentials
    * related to a node Set
    *
    * @param node
    * @param liberate
    * @return list of probability potentials related to node
    */
   private ArrayList<Potential> getUtilityPotentials(NodeList setNodes, boolean liberate) {
      ArrayList<Potential> relevantPots = new ArrayList<Potential>();

      for(int i=0; i<setNodes.size(); i++){
          Node node = setNodes.getNodes().elementAt(i);
          ArrayList<Potential> nodePots = getUtilityPotentials(node, liberate);
          if(nodePots != null)
              relevantPots.addAll(nodePots);
      }      
      return relevantPots;
   }
   
   
         /**
    * Method to get and optionally liberate the references for the potentials
    * related to a node set
    *
    * @param setNodes
    * @param liberate
    * @return list of probability potentials related to node
    */
   private ArrayList<Potential> getInferiorSeparatorsProbPotentials(NodeList setNodes, boolean liberate) {
      ArrayList<Potential> relevantPots = new ArrayList<Potential>();

      for(int i=0; i<setNodes.size(); i++){
          Node node = setNodes.getNodes().elementAt(i);
          ArrayList<Potential> nodePots = getInferiorSeparatorsProbPotentials(node, liberate);
          if(nodePots != null)
              relevantPots.addAll(nodePots);
      }
      

      return relevantPots;
   }
   
   
   
    /**
    * Method to get and optionally liberate the references for the potentials
    * related to a node Set
    *
    * @param setNodes
    * @param liberate
    * @return list of probability potentials related to node
    */
   private ArrayList<Potential> getInferiorSeparatorsUtilPotentials(NodeList setNodes, boolean liberate) {
      ArrayList<Potential> relevantPots = new ArrayList<Potential>();

      for(int i=0; i<setNodes.size(); i++){
          Node node = setNodes.getNodes().elementAt(i);
          ArrayList<Potential> nodePots = getInferiorSeparatorsUtilPotentials(node, liberate);
          if(nodePots != null)
              relevantPots.addAll(nodePots);
      }
      

      return relevantPots;
   }
   
   
   
   
   
   
   /**
    * Method to get and optionally liberate the references for the potentials
    * related to a node
    *
    * @param node
    * @param liberate
    * @return list of probability potentials related to node
    */
   private ArrayList<Potential> getProbabilityPotentials(Node node, boolean liberate) {
      ArrayList<Potential> cliqueNodePotentials;
      ArrayList<Potential> varPotentials;
      Node nodeInClique;

      
      
      
      
      if (generateDebugInfo) {
         System.out.println("JunctionTreeNode: getProbabilityPotentials ----- BEGIN");
      }
      
      //Sets the hashmap used to compute the messages
      HashMap<Node, ArrayList<Potential>> map;
      if(liberate)
          map = probPotentials;
      else
          map = probPotentialsAux;
              
      
      

      // Get the potentials related with a node. If liberate is true, remove the
      // references to this potential
      // If liberate is true, it will operate directly with potentials in clique
      // and modify them.
      // If not, it will operate with an auxiliary structure

      cliqueNodePotentials = map.remove(node);


      // If there are not potentials related to node, return null
      if (cliqueNodePotentials != null) {
         // Consider the rest of variables of the clique and liberate the references
         // to the potentials contained in cliqueNodePotentials
         for (int i = 0; i < variables.size(); i++) {
            nodeInClique = variables.elementAt(i);

            // Get the potentials for this variable 
            varPotentials = map.get(nodeInClique);

            // Consider the potentials for this node, and remove the ones contained
            // in cliqueNodeProbPotentials
            //if (liberate && varPotentials != null) {
            if (varPotentials != null) {
               removeRepetitions(varPotentials, cliqueNodePotentials);
            }
         }
      }

      if (generateDebugInfo) {
         

         System.out.println("\n\tPotenciales de probabilidad con la variable "+node.getName()+" en el clique "+this.index+":");

 
        if(cliqueNodePotentials!=null && cliqueNodePotentials.isEmpty()==false)
            for(Potential p : cliqueNodePotentials) {
                if(p != null)
                    System.out.println("\t  - "+p);
            } 
         else{
                System.out.println("\t    No hay potentciales");
         } 
         
         System.out.println("\nJunctionTreeNode: getProbabilityPotentials ----- END");
      }

      // return cliquenodePotentials
      return cliqueNodePotentials;
   }

   /**
    * Method to get and optionally liberate the references for the potentials
    * related to a node
    *
    * @param node
    * @param liberate
    * @return list of utility potentials related to node
    */
   private ArrayList<Potential> getUtilityPotentials(Node node, boolean liberate) {
      ArrayList<Potential> cliqueNodePotentials;
      ArrayList<Potential> varPotentials;
      Node nodeInClique;

      if (generateDebugInfo) {
         System.out.println("JunctionTreeNode: getUtilityPotentials ----- BEGIN");
      }
      
      
      //Sets the hashmap used to compute the messages
      HashMap<Node, ArrayList<Potential>> map;
      if(liberate)
          map = utilPotentials;
      else
          map = utilPotentialsAux;
              
      

      // Get the potentials related with a node. If liberate is true, remove the
      // references to this potential
         cliqueNodePotentials = map.remove(node);

      // If there are no cliques related to node, return null
      if (cliqueNodePotentials != null) {
         // Consider the rest of variables of the clique and liberate the references
         // to the potentials contained in cliqueNodePotentials
         for (int i = 0; i < variables.size(); i++) {
            nodeInClique = variables.elementAt(i);

            // Get the potentials for this variable 
            varPotentials = map.get(nodeInClique);

            // Consider the potentials for this node, and remove the ones contained
            // in cliqueNodePotentials
            if (varPotentials != null) {
               removeRepetitions(varPotentials, cliqueNodePotentials);
            }
         }
      }

      if (generateDebugInfo) {
          
          
         System.out.println("\n\tPotenciales de utilidad con la variable "+node.getName()+" en el clique "+this.index+":");

         if(cliqueNodePotentials!=null && cliqueNodePotentials.isEmpty()==false)
            for(Potential p : cliqueNodePotentials) {
                if(p != null)
                    System.out.println("\t  - "+p);
            }
         else{
                System.out.println("\t    No hay potentciales");
         } 
           
          
         System.out.println("\nJunctionTreeNode: getUtilityPotentials ----- END");
      }

      // return cliquenodePotentials
      return cliqueNodePotentials;
   }

   /**
    * Private method for removing the references to potentials already used
    *
    * @param first list where the references must be cleaned
    * @param second list containing the references to remove
    */
   protected void removeRepetitions(ArrayList<Potential> first, ArrayList<Potential> second) {

      if (generateDebugInfo) {
         System.out.println("JunctionTreeNode: removeRepetitions ----- BEGIN");
      }

      // Consider the potentials in second
      for (Potential potential : second) {

         // Look if it is referenced in first
         if (first.contains(potential)) {
            first.remove(potential);
         }
      }

      if (generateDebugInfo) {
         System.out.println("JunctionTreeNode: removeRepetitions ----- END");
      }
   }

   /**
    * Method for removing a potential from the list of constraint potentials
    *
    * @param potential
    * @param list of potentials to consider
    */
   protected void removeConstraintPotential(Potential potential) {
      ArrayList<Potential> varPotentials;
      Node nodeInClique;

      if (generateDebugInfo) {
         System.out.println("JunctionTreeNode: removeConstraintPotential ----- BEGIN");
      }

      // Consider the variables of the clique one by one
      for (int i = 0; i < variables.size(); i++) {
         nodeInClique = variables.elementAt(i);

         // Get the potentials for this variable 
         varPotentials = constraintPotentials.get(nodeInClique);

         // Consider the potentials for this node, and remove the ones contained
         // in cliqueNodePotentials
         if (varPotentials != null && varPotentials.contains(potential)) {
            varPotentials.remove(potential);
         }
      }

      if (generateDebugInfo) {
         System.out.println("JunctionTreeNode: removeConstraintPotential ----- END");
      }
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
    
   
   
     
  public void printCliquePotentials() {
        
      System.out.println("\n\n Clique "+this.index);
      
      Iterator<Node> iterator = this.probPotentials.keySet().iterator();
        while(iterator.hasNext()) {
            Node var = iterator.next();
            ArrayList<Potential> pots = this.probPotentials.get(var);
            
            System.out.println("\nVariable "+var.getName());
            for(int i=0; i<pots.size(); i++){
                System.out.print("  ");
                pots.get(i).printDomain();
                System.out.println();
            }
            
            
        }
        
        
        iterator = this.utilPotentials.keySet().iterator();
        while(iterator.hasNext()) {
            Node var = iterator.next();
            ArrayList<Potential> pots = this.utilPotentials.get(var);
            
            System.out.println("\nVariable "+var.getName());
            for(int i=0; i<pots.size(); i++){
                System.out.print("  ");
                pots.get(i).printDomain();
                System.out.println();
            }
            
            
        }
        

        
  }
  
  
  private void startMeasureOverHead() {
        if(tree.propagationID.measureOverHead)
            initTime = System.nanoTime();
    
    }
    
    
    private void stopMeasureOverHead() {
        
        if(tree.propagationID.measureOverHead){
            endTime = System.nanoTime();
            tree.propagationID.addOverHeadTime(endTime-initTime);
        }
    }

  
  
      @Override
    public String toString(){
      
      String s = "{";
      Vector<Node> vars = this.getVariables().getNodes();
      
      if(vars.size()==0)
          return s+"}";
      
      for(int i=0; i<vars.size()-1; i++) {
          s += vars.get(i).getName()+", ";
      }
      
      s+= vars.get(vars.size()-1).getName()+"}";
        
      return s;

    } 

   /**
    * Class to show if a given operation on the ID involves one or several
    * potentials (relations)
    */
   private class Flag {

      private boolean flag;

      private Flag() {
         flag = false;
      }

      private void setValue(boolean value) {
         flag = value;
      }

      private boolean getValue() {
         return flag;
      }
   }
}
