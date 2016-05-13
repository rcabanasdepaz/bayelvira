package elvira.inference.clustering.lazyid;

import elvira.Node;
import elvira.NodeList;
import elvira.potential.Potential;

import java.util.Vector;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class JunctionTreeSeparator {

   /**
    * Data member to contain the variables on which the separator is defined
    */
   NodeList variables;
   /**
    * Data member to contain the upper neighbour of the separator
    */
   JunctionTreeNode superiorNeighbour;
   /**
    * Data member to store the down neighbour of the separator
    */
   JunctionTreeNode inferiorNeighbour;
   /**
    * Data member to store the prob and util messages
    */
   HashMap<Node, ArrayList<Potential>> probMessages;
   HashMap<Node, ArrayList<Potential>> utilMessages;
   HashMap<Node, ArrayList<Potential>> probMessagesAux;
   HashMap<Node, ArrayList<Potential>> utilMessagesAux;
   /**
    * Data member to numerate the separators
    */
   private int index = 0;
   /**
    * Data member to count the number of separators used
    */
   private static int separatorCounters = 0;
   /**
    * Data member to store the debug flag
    */
   private boolean generateDebugInfo;

   /**
    * Constructor of the class
    *
    * @param superior neighbour
    * @param inferior neighbour
    * @param variables in the domain of the separator
    */
   public JunctionTreeSeparator(JunctionTreeNode superior, JunctionTreeNode inferior,
           NodeList variables, boolean debugFlag) {
      if (generateDebugInfo) {
         System.out.println("JunctionTreeSeparator: class Constructor ----- BEGIN");
      }
      this.generateDebugInfo = debugFlag;
      this.superiorNeighbour = superior;
      this.inferiorNeighbour = inferior;
      this.variables = variables;
      index = separatorCounters;
      separatorCounters++;

      // Create the arrays for potentials
      probMessages = new HashMap<Node, ArrayList<Potential>>();
      utilMessages = new HashMap<Node, ArrayList<Potential>>();

      
      
      if (generateDebugInfo) {
         System.out.println("JunctionTreeSeparator: class Constructor ----- END");
      }
   }

   /**
    * Method to print the data aboout the separator
    */
   public void print() {
      ArrayList<Potential> pots;
      Potential pot;
      Node node;

      System.out.println("SEPARATOR -----------------------------------");
      System.out.println("Indice: " + index);
      System.out.println("--------------- Separator variables ---------");
      for (int i = 0; i < variables.size(); i++) {
         node = variables.elementAt(i);
         System.out.println(node.getName());
      }

      // Print the probability potentials related to every variable
      System.out.println("Mensajes de probabilidad: ");
      for (int i = 0; i < variables.size(); i++) {
         node = variables.elementAt(i);
         System.out.println("..... Relacionados con variable: " + node.getName());
         pots = probMessages.get(node);
         // Print if needed
         if (pots != null) {
            for (int j = 0; j < pots.size(); j++) {
               System.out.println("-----------------" + j + "------------------");
               pot = pots.get(j);
               pot.print();
               System.out.println("-----------------------------------------");
            }
         }
      }

      // The same with utility potentials
      System.out.println("Mensajes de utilidad: ");
      for (int i = 0; i < variables.size(); i++) {
         node = variables.elementAt(i);
         System.out.println("..... Relacionados con variable: " + node.getName());
         pots = utilMessages.get(node);
         // Print if needed
         if (pots != null) {
            for (int j = 0; j < pots.size(); j++) {
               System.out.println("-----------------" + j + "------------------");
               pot = pots.get(j);
               pot.print();
               System.out.println("-----------------------------------------");
            }
         }
      }
   }

   /**
    * Method for printing the structure of a separator
    *
    * @param spaces
    */
   public void printStructure(int spaces) {
      // Print the spaces and the index
      for (int i = 0; i < spaces; i++) {
         System.out.print(" ");
      }
      System.out.print("S" + index + "-");

      // Go with the information about the inferior neighbour
      inferiorNeighbour.printStructure(spaces + 2);
   }
   /**
    * Method for printing the structure of a separator
    *
    * @param spaces
    */
   public void printStructureNumPots(int spaces) {
      // Print the spaces and the index
      for (int i = 0; i < spaces; i++) {
         System.out.print(" ");
      }
      System.out.print("S("+probMessages.size()+","+utilMessages.size()+")-");

      // Go with the information about the inferior neighbour
      inferiorNeighbour.printStructureNumPots(spaces + 2);
   }
   
   
   /**
    * Method to return the superior junction tree node related to this separator
    *
    * @return superior
    */
   public JunctionTreeNode getSuperiorNeighbour() {
      return superiorNeighbour;
   }

   /**
    * Method to return the inferior junction tree node related to this separator
    *
    * @return superior
    */
   public JunctionTreeNode getInferiorNeighbour() {
      return inferiorNeighbour;
   }

   
   public void initAuxiliarySets() {
       
       this.probMessagesAux = JunctionTreeNode.copyPotentialMap(probMessages);
       this.utilMessagesAux = JunctionTreeNode.copyPotentialMap(utilMessages);
       

   }
   
   
   
   /**
    * Public method for testing the prob message looking if the variable passed
    * as argument it is in the domain of the potential
    *
    * @param node
    * @param liberate to show if the reference to the potential must be deleted
    * @return prob potentials with node belonging to their domain null if there
    * are no potentials with this condition
    */
   public ArrayList<Potential> getProbMessages(Node node, boolean liberate) {
      ArrayList<Potential> cliqueNodePotentials;
      ArrayList<Potential> varPotentials;
      Node nodeInSeparator;
      
      if (generateDebugInfo) {
         System.out.println("JunctionTreeSeparator: getProbMessages ----- BEGIN");
      }

      HashMap<Node, ArrayList<Potential>> map = null;
      
      if(liberate)
          map = probMessages;
      else
          map = probMessagesAux;
      
      
      // Get the potentials related with a node. If liberate is true, remove the
      // references to this potential

      cliqueNodePotentials = map.remove(node);


      // If there are not potentials related to node, return null
      if (cliqueNodePotentials != null) {
         // Consider the rest of variables of the clique and liberate the references
         // to the potentials contained in cliqueNodePotentials
         for (int i = 0; i < variables.size(); i++) {
            nodeInSeparator = variables.elementAt(i);

            // Get the potentials for this variable 
            varPotentials = map.get(nodeInSeparator);

            // Consider the potentials for this node, and remove the ones contained
            // in cliqueNodePotentials
            if ((varPotentials != null)) {
               removeRepetitions(varPotentials, cliqueNodePotentials);
            }
         }
      }
          
      
      if (generateDebugInfo) {
          
         
         int numMsg = 0;
         if(cliqueNodePotentials != null)
             numMsg = cliqueNodePotentials.size();
         if(numMsg != 1) 
            System.out.println("\n\tRecibidos "+numMsg+" mensajes del separador "+this.index+" relativos a "+node.getName());
         else
            System.out.println("\n\tRecibido 1 mensaje del separador "+this.index+" relativo a "+node.getName());

             
         System.out.println("\nJunctionTreeSeparator: getProbMessages ----- END");
      }

      // return cliquenodePotentials
      return cliqueNodePotentials;
   }

   /**
    * Public method for testing the prob message looking if the variable passed
    * as argument it is in the domain of the potential
    *
    * @param node
    * @param liberate to show if the reference to the potential must be deleted
    * @return util potentials with node belonging to their domain null if there
    * are no potentials with this condition
    */
   public ArrayList<Potential> getUtilMessages(Node node, boolean liberate) {
      ArrayList<Potential> cliqueNodePotentials;
      ArrayList<Potential> varPotentials;
      Node nodeInSeparator;
      
      if (generateDebugInfo) {
         System.out.println("JunctionTreeSeparator: getUtilMessages ----- BEGIN");
      }
      
      
      HashMap<Node, ArrayList<Potential>> map = null;
      
      if(liberate)
          map = utilMessages;
      else
          map = utilMessagesAux;
      
      
      


         cliqueNodePotentials = map.remove(node);


      // If there are not potentials related to node, return null
      if (cliqueNodePotentials != null) {
         // Consider the rest of variables of the clique and liberate the references
         // to the potentials contained in cliqueNodePotentials
         for (int i = 0; i < variables.size(); i++) {
            nodeInSeparator = variables.elementAt(i);

            // Get the potentials for this variable 
            varPotentials = map.get(nodeInSeparator);

            // Consider the potentials for this node, and remove the ones contained
            // in cliqueNodePotentials
            if ((varPotentials != null)) {
               removeRepetitions(varPotentials, cliqueNodePotentials);
            }
         }
      }
      
      if (generateDebugInfo) {
          
         int numMsg = 0;
         if(cliqueNodePotentials != null)
             numMsg = cliqueNodePotentials.size();
         if(numMsg != 1) 
            System.out.println("\n\tRecibidos "+numMsg+" mensajes del separador "+this.index+" relativos a "+node.getName());
         else
            System.out.println("\n\tRecibido 1 mensaje del separador "+this.index+" relativo a "+node.getName());
 
          
         System.out.println("\nJunctionTreeSeparator: getUtilMessages ----- END");
      }

      // return cliquenodePotentials
      return cliqueNodePotentials;
   }

   /**
    * Public method for setting a prob message for the separator
    *
    * @param potential
    */
   public void setProbMessage(Potential potential) {
      Vector variables = potential.getVariables();
      Node node;

      if (generateDebugInfo) {
         System.out.println("JunctionTreeSeparator: setProbMessage ----- BEGIN");
      }
      
      // To get the variables related to the potentials where they
      // appear, consider the variables one by one
      for (int i = 0; i < variables.size(); i++) {
         node = (Node) variables.elementAt(i);

         // See if it is included in probPotentials
         if (probMessages.get(node) == null) {
            // Create a new ArrayList to include the potential
            ArrayList<Potential> potentials = new ArrayList<Potential>();
            potentials.add(potential);

            // Include the pair into probPotentials
            probMessages.put(node, potentials);
         } else {
            // Get the array list
            ArrayList<Potential> potentials = probMessages.get(node);
            // Include the potential
            potentials.add(potential);
         }
      }
      
      if (generateDebugInfo) {
         System.out.println("JunctionTreeSeparator: setProbMessage ----- END");
      }
   }

   /**
    * Public method for setting a util message for the separator
    *
    * @param potential
    */
   public void setUtilMessage(Potential potential) {
      Vector variables = potential.getVariables();
      Node node;

      if (generateDebugInfo) {
         System.out.println("JunctionTreeSeparator: setUtilMessage ----- BEGIN");
      } 
      
      // To get the variables related to the potentials where they
      // appear, consider the variables one by one
      for (int i = 0; i < variables.size(); i++) {
         node = (Node) variables.elementAt(i);

         // See if it is included in utilPotentials
         if (utilMessages.get(node) == null) {
            // Create a nuew ArrayList to include the potential
            ArrayList<Potential> potentials = new ArrayList<Potential>();
            potentials.add(potential);

            // Include the pair into probPotentials
            utilMessages.put(node, potentials);
         } else {
            // Get the array list
            ArrayList<Potential> potentials = utilMessages.get(node);

            // Include the potential
            potentials.add(potential);
         }
      }
      
      if (generateDebugInfo) {
         System.out.println("JunctionTreeSeparator: setUtilMessage ----- END");
      }
   }

   /**
    * Method to check if a given node belongs to the set of variables
    *
    * @param node
    * @return boolean value
    */
   public boolean isVariablePresent(Node node) {
      if (variables.getId(node.getName()) == -1) {
         return false;
      } else {
         return true;
      }
   }

   /**
    * Method to get access to index data member
    */
   public int getIndex() {
      return index;
   }

   
   /**
    * Method to get the variables associated to the separator
    * @return 
    */
    public NodeList getVariables() {
        return variables;
    }
   
   
   
   
   

   /**
    * Public method for getting the size of the probability potentials related
    * to the separator
    *
    * @return potentials
    */
   public ArrayList<Potential> getProbMessages() {
      HashSet<Potential> allProbabilityPotentials = new HashSet();
      ArrayList<Potential> potentials = new ArrayList<Potential>();
      ArrayList<Potential> potentialsToReturn = new ArrayList<Potential>();
      Iterator<ArrayList<Potential>> iterator = probMessages.values().iterator();
      Potential potential;
      
      if (generateDebugInfo) {
         System.out.println("JunctionTreeSeparator: getProbMessages ----- BEGIN");
      }

      // Check if there are probMessages
      if (probMessages.size() == 0) {
         return null;
      }

      // Every position will contain an arraylist
      while (iterator.hasNext()) {
         potentials = iterator.next();
         // Consider the potentials one by one
         for (int i = 0; i < potentials.size(); i++) {
            potential = potentials.get(i);

            // Add it if it is not already contained
            if (allProbabilityPotentials.add(potential)) {
               // Add it to potentials 
               potentialsToReturn.add(potential);
            }
         }
      }
      
      if (generateDebugInfo) {
         System.out.println("JunctionTreeSeparator: getProbMessages ----- END");
      }

      // Return potentials
      return potentialsToReturn;
   }

   /**
    * Public method for getting the size of the utility potentials related to
    * the separator
    *
    * @return potentials
    */
   public ArrayList<Potential> getUtilMessages() {
      HashSet<Potential> allUtilityPotentials = new HashSet();
      ArrayList<Potential> potentials = new ArrayList<Potential>();
      ArrayList<Potential> potentialsToReturn = new ArrayList<Potential>();
      Iterator<ArrayList<Potential>> iterator = utilMessages.values().iterator();
      Potential potential;
      
      if (generateDebugInfo) {
         System.out.println("JunctionTreeSeparator: getUtilMessages ----- BEGIN");
      }

      // Check if there are utilMessages
      if (utilMessages.size() == 0) {
         return null;
      }

      // Every position will contain an arraylist
      while (iterator.hasNext()) {
         potentials = iterator.next();

         // Consider the potentials one by one
         for (int i = 0; i < potentials.size(); i++) {
            potential = potentials.get(i);
            // Add it if it is not already contained
            if (allUtilityPotentials.add(potential)) {
               // It is not contained yet, add it to potentials
               potentialsToReturn.add(potential);
            }
         }
      }
      
      if (generateDebugInfo) {
         System.out.println("JunctionTreeSeparator: getUtilMessages ----- END");
      }

      // Return potentials
      return potentialsToReturn;
   }

   /**
    * Public method for getting the the probability potentials related to the
    * separator and removing then from the list of potentials of the separator
    *
    * @return potentials
    */
   public ArrayList<Potential> removeProbMessages() {
      HashSet<Potential> allProbabilityPotentials = new HashSet();
      ArrayList<Potential> potentials = new ArrayList<Potential>();
      ArrayList<Potential> potentialsToReturn = new ArrayList<Potential>();
      Iterator<ArrayList<Potential>> iterator = probMessages.values().iterator();
      Potential potential;

      if (generateDebugInfo) {
         System.out.println("JunctionTreeSeparator: removeProbMessages ----- BEGIN");
      }
      
      // Check if there are probMessages
      if (probMessages.size() == 0) {
         return null;
      }

      // Every position will contain an arraylist
      while (iterator.hasNext()) {
         potentials = iterator.next();
         // Consider the potentials one by one
         for (int i = 0; i < potentials.size(); i++) {
            potential = potentials.remove(i);

            // Add it if it is not already contained
            if (allProbabilityPotentials.add(potential)) {
               // Add it to potentials 
               potentialsToReturn.add(potential);
            }
         }
      }
      
       if (generateDebugInfo) {
         System.out.println("JunctionTreeSeparator: removeProbMessages ----- END");
      }

      // Return potentials
      return potentialsToReturn;
   }

   /**
    * Public method for getting the size of the utility potentials related to
    * the separator
    *
    * @return potentials
    */
   public ArrayList<Potential> removeUtilMessages() {
      HashSet<Potential> allUtilityPotentials = new HashSet();
      ArrayList<Potential> potentials = new ArrayList<Potential>();
      ArrayList<Potential> potentialsToReturn = new ArrayList<Potential>();
      Iterator<ArrayList<Potential>> iterator = utilMessages.values().iterator();
      Potential potential;
      
      if (generateDebugInfo) {
         System.out.println("JunctionTreeSeparator: removeUtilMessages ----- BEGIN");
      }

      // Check if there are utilMessages
      if (utilMessages.size() == 0) {
         return null;
      }

      // Every position will contain an arraylist
      while (iterator.hasNext()) {
         potentials = iterator.next();

         // Consider the potentials one by one
         for (int i = 0; i < potentials.size(); i++) {
            potential = potentials.remove(i);
            // Add it if it is not already contained
            if (allUtilityPotentials.add(potential)) {
               // It is not contained yet, add it to potentials
               potentialsToReturn.add(potential);
            }
         }
      }
      
      if (generateDebugInfo) {
         System.out.println("JunctionTreeSeparator: removeUtilMessages ----- END");
      }

      // Return potentials
      return potentialsToReturn;
   }

   /**
    * Private method for removing the references to potentials already used
    *
    * @param first list where the references must be cleaned
    * @param second list containing the references to remove
    */
   private void removeRepetitions(ArrayList<Potential> first, ArrayList<Potential> second) {
      if (generateDebugInfo) {
         System.out.println("JunctionTreeSeparator: removeRepetitions ----- BEGIN");
      }
      
      // Consider the potentials in second
      for (Potential potential : second) {

         // Look if it is referenced in first
         if (first.contains(potential)) {
            first.remove(potential);
         }
      }
      
      if (generateDebugInfo) {
         System.out.println("JunctionTreeSeparator: removeRepetitions ----- END");
      }
   }
}
