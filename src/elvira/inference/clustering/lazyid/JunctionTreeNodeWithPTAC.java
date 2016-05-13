package elvira.inference.clustering.lazyid;

import elvira.Node;
import elvira.NodeList;
import elvira.potential.Potential;
import elvira.potential.PotentialTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Vector;

public class JunctionTreeNodeWithPTAC extends JunctionTreeNodeWithPT {

   /**
    * Constructor
    *
    * @param tree
    * @param variables
    * @param index
    * @param debugFlag
    */
   public JunctionTreeNodeWithPTAC(StrongJunctionTree tree,
           NodeList variables, int index, boolean debugFlag) {
      super(tree, variables, index, debugFlag);
      if (generateDebugInfo) {
         System.out.println("JunctionTreeNodeWithPTAC: class Constructor ----- BEGIN");
      }
      constraintPotentials = new HashMap<Node, ArrayList<Potential>>();
      if (generateDebugInfo) {
         System.out.println("JunctionTreeNodeWithPTAC: class Constructor ----- END");
      }
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
         System.out.println("JunctionTreeNodeWithPTAC: buildTreeNode ----- BEGIN");
      }
      JunctionTreeNodeWithPTAC node = new JunctionTreeNodeWithPTAC(tree, clique, index, generateDebugInfo);
      if (generateDebugInfo) {
         System.out.println("JunctionTreeNodeWithPTAC: buildTreeNode ----- END");
      }
      return node;
   }

   /**
    * Private method for post processing the utility potentials. For
    * PotentialTable this method does nothing, but with probability potentials
    * the variables will be sorted and a prunning will be done if required
    *
    * @param potential
    */
   protected Potential postProcessUtility(Potential potential) {
      if (generateDebugInfo) {
         System.out.println("JunctionTreeNodeWithPTAC: postProcessUtility ----- BEGIN");
      }
      
      PotentialTree finalPot = (PotentialTree) super.postProcessUtility(potential);
      
      if (generateDebugInfo) {
         System.out.println("JunctionTreeNodeWithPTAC: postProcessUtility ----- END");
      }
      // Tratamiento de las restricciones
      return finalPot;
   }

   /**
    * Private method for post processing the probability potentials. For
    * PotentialTable this method does nothing, but with probability potentials
    * the variables will be sorted and a prunning will be done if required
    *
    * @param potential
    */
   protected Potential postProcessProbability(Potential potential) {
      if (generateDebugInfo) {
         System.out.println("JunctionTreeNodeWithPTAC: postProcessProbability ----- BEGIN");
      }
      PotentialTree finalPot = (PotentialTree) super.postProcessProbability(potential);
      if (generateDebugInfo) {
         System.out.println("JunctionTreeNodeWithPTAC: postProcessProbability ----- END");
      }
      return finalPot;
   }




     /**
    * Private method for post processing the utility potentials. For
    * PotentialTable this method does nothing, but with probability potentials
    * the variables will be sorted and a prunning will be done if required
    *
    * @param potential
    */
   protected Potential postProcessConstrainedUtility(Potential potential) {

    double threshold;

    if(tree.isOnlyInitialTransformation())
     threshold=0;
    else
     threshold = ((StrongJunctionTreeWithPT) tree).getThresholdForPrunning();


      double minimum = ((StrongJunctionTreeWithPT) tree).getMinimum();
      double maximum = ((StrongJunctionTreeWithPT) tree).getMaximum();


      if (generateDebugInfo) {
         System.out.println("JunctionTreeNodeWithPT: postProcessUtility ----- BEGIN");
      }

      PotentialTree finalPot = ((PotentialTree) potential).sortUtilityAndPrune(minimum,
              maximum, threshold);
      //PotentialTree finalPot=((PotentialTree)potential).sortUtilityAndPruneWithoutRestrict(minimum,
      //                           maximum,threshold,PotentialTree.DISTANCE_WITH_CEROS);
      if (generateDebugInfo) {
         System.out.println("JunctionTreeNodeWithPT: postProcessUtility ----- END");
      }
      return finalPot;
   }

   /**
    * Private method for post processing the probability potentials. For
    * PotentialTable this method does nothing, but with probability potentials
    * the variables will be sorted and a prunning will be done if required
    *
    * @param potential
    */
   protected Potential postProcessConstrainedProbability(Potential potential) {
      double threshold;

     if(tree.isOnlyInitialTransformation())
         threshold=0;
     else
         threshold = ((StrongJunctionTreeWithPT) tree).getThresholdForPrunning();

      if (generateDebugInfo) {
         System.out.println("JunctionTreeNodeWithPT: postProcessProbability ----- BEGIN");
      }
      PotentialTree finalPot = ((PotentialTree) potential).sortAndBound(threshold);
      System.gc();

      //PotentialTree finalPot=((PotentialTree)potential).sortAndBoundWithoutRestrict(threshold);
      if (generateDebugInfo) {
         System.out.println("JunctionTreeNodeWithPT: postProcessProbability ----- END");
      }
      return finalPot;
   }





   /**
    * Method to add a constraint potential to this junction tree node
    *
    * @param potential
    */
   public void addConstraintPotential(Potential potential) {
      Vector variables = potential.getVariables();
      Node node;
      
      if (generateDebugInfo) {
         System.out.println("JunctionTreeNodeWithPTAC: addConstraintPotential ----- BEGIN");
      }

      // Show debug information
      if (tree.getDebugFlag()) {
         System.out.println("Agregar potencial de restriccion a nodo: " + index);
         potential.print();
         System.out.println("-------------------------------------------------");
      }

      // To get the variables related to the potentials where they
      // appear, consider the variables one by one
      for (int i = 0; i < variables.size(); i++) {
         node = (Node) variables.elementAt(i);

         // See if it is included in constraintPotentials
         if (constraintPotentials.get(node) == null) {
            // Create a new ArrayList to include the potential
            ArrayList<Potential> potentials = new ArrayList<Potential>();
            potentials.add(potential);

            // Include the pair into probPotentials
            constraintPotentials.put(node, potentials);
         } else {
            // Get the array list
            ArrayList<Potential> potentials = constraintPotentials.get(node);

            // Include the potential
            potentials.add(potential);
         }
      }
      if (generateDebugInfo) {
         System.out.println("JunctionTreeNodeWithPTAC: addConstraintPotential ----- END");
      }
   }

   /**
    * Method for combining constraints
    *
    * @param boolean flag: propagate on neighbourhs
    */
   public void applyConstraints(boolean goDown) {
      HashSet<Potential> constraints = new HashSet<Potential>();
      ArrayList<Potential> probConstrained;
      ArrayList<Potential> utilConstrained;
      Iterator<ArrayList<Potential>> iteratorConsPotentials = constraintPotentials.values().iterator();
      ArrayList<Potential> potentials;
      Potential potential, constraint;
      boolean applied;
      
      if (generateDebugInfo) {
         System.out.println("JunctionTreeNodeWithPTAC: applyConstraints ----- BEGIN");
      }

      // Get constraint potentials
      while (iteratorConsPotentials.hasNext()) {
         potentials = iteratorConsPotentials.next();
         for (int i = 0; i < potentials.size(); i++) {
            // As constraints is a set, there will ne no repetitions
            constraints.add(potentials.get(i));
         }
      }

      // Consider every constraint
      Iterator<Potential> iteratorConstraints = constraints.iterator();
      while (iteratorConstraints.hasNext()) {
         constraint = iteratorConstraints.next();
         applied = false;
         // Get the probability potentials constrained by this constraint
         probConstrained = getProbabilityPotentialsConstrained(constraint.getVariables());

         // Combine the potentials
         if (probConstrained != null && probConstrained.size() != 0) {

             combineWithProbabilityPotentials(constraint, probConstrained);
             
            applied = true;
         }

         // Get the utility potentials constrained by this constraint
         utilConstrained = getUtilityPotentialsConstrained(constraint.getVariables());

         // Combine the potentials
         if (utilConstrained != null && utilConstrained.size() != 0) {
            combineWithUtilityPotentials(constraint, utilConstrained);
            applied = true;
         }

         // APPLIED CONSTRAINTS SHOULD BE REMOVED: are already USED
   //      if (applied == true) {
   //         removeConstraintPotential(constraint);
   //      }
      }

      // Make the same with the inferior neigbourghs
      if (goDown) {
         JunctionTreeNode inferiorNeighbour;
         for (int i = 0; i < down.size(); i++) {
            inferiorNeighbour = down.get(i).getInferiorNeighbour();
            inferiorNeighbour.applyConstraints(goDown);
         }
      }
      
      if (generateDebugInfo) {
         System.out.println("JunctionTreeNodeWithPTAC: applyConstraints ----- END");
      }
   }

   /**
    * Method for getting the probability potentials with the variables contained
    * in the first argument. The potentials returned are removed from the list
    * of probability potentials
    *
    * @return ArrayList<Potential>
    */
   private ArrayList<Potential> getProbabilityPotentialsConstrained(Vector varsInConst) {
      ArrayList<Potential> constrained = new ArrayList<Potential>();
      Iterator<ArrayList<Potential>> iterator = probPotentials.values().iterator();
      ArrayList<Potential> potentials = null;
      NodeList listVarsInConst = new NodeList(varsInConst);
      NodeList listVarsInPotential;
      Node varInClique;
      Potential potential;
      
      if (generateDebugInfo) {
         System.out.println("JunctionTreeNodeWithPTAC: getProbabilityPotentialsConstrained ----- BEGIN");
      }

      // Consider the potentials one by one
      while (iterator.hasNext()) {
         potentials = iterator.next();
         for (int i = 0; i < potentials.size(); i++) {
            potential = potentials.get(i);
            listVarsInPotential = new NodeList(potential.getVariables());

            // listVarsInPotential should be included in listVarsInConf
            if (listVarsInConst.isIncluded(listVarsInPotential)) {
               if (!constrained.contains(potential)) {
                  // Include the potential in constrained
                  constrained.add(potential);
               }
            }
         }
      }

      // Now the potentials must be removed from the lists of potentials
      // for every variable
      if (!constrained.isEmpty()) {
         // Consider the variables of the clique one by one
         for (int i = 0; i < variables.size(); i++) {
            varInClique = variables.elementAt(i);

            // Get the potentials for this variable
            potentials = probPotentials.get(varInClique);

            // Remove them
            if (potentials != null) {
               removeRepetitions(potentials, constrained);
            }
         }
      }
      
      if (generateDebugInfo) {
         System.out.println("JunctionTreeNodeWithPTAC: getProbabilityPotentialsConstrained ----- END");
      }

      // Return constrained
      return constrained;
   }

   /**
    * Method for getting the utility potentials with the variables contained in
    * the first argument. The potentials returned are removed from the list of
    * utility potentials
    *
    * @return ArrayList<Potential>
    */
   private ArrayList<Potential> getUtilityPotentialsConstrained(Vector varsInConst) {
      ArrayList<Potential> constrained = new ArrayList<Potential>();
      Iterator<ArrayList<Potential>> iterator = utilPotentials.values().iterator();
      ArrayList<Potential> potentials = null;
      NodeList listVarsInConst = new NodeList(varsInConst);
      NodeList listVarsInPotential;
      Node varInClique;
      Potential potential;
      
      if (generateDebugInfo) {
         System.out.println("JunctionTreeNodeWithPTAC: getUtilityPotentialsConstrained ----- BEGIN");
      }

      // Consider the potentials one by one
      while (iterator.hasNext()) {
         potentials = iterator.next();
         for (int i = 0; i < potentials.size(); i++) {
            potential = potentials.get(i);
            listVarsInPotential = new NodeList(potential.getVariables());

            // listVarsInPotential should be included in listVarsInConf
            if (listVarsInConst.isIncluded(listVarsInPotential)) {
               if (!constrained.contains(potential)) {
                  // Include the potential in constrained
                  constrained.add(potential);
               }
            }
         }
      }

      // Now the potentials must be removed from the lists of potentials
      // for every variable
      if (!constrained.isEmpty()) {
         // Consider the variables of the clique one by one
         for (int i = 0; i < variables.size(); i++) {
            varInClique = variables.elementAt(i);

            // Get the potentials for this variable
            potentials = utilPotentials.get(varInClique);

            // Remove them
            if (potentials != null) {
               removeRepetitions(potentials, constrained);
            }
         }
      }
      
      if (generateDebugInfo) {
         System.out.println("JunctionTreeNodeWithPTAC: getUtilityPotentialsConstrained ----- END");
      }

      // Return constrained
      return constrained;
   }

   /**
    * Private mthod for combining the probability potentials for a given
    * variable
    *
    * @param constraint potential with the constraint
    * @param probPots probability potentials constrained by the constraint
    */
   private void combineWithProbabilityPotentials(Potential constraint,
           ArrayList<Potential> probPots) {
      Potential pot;
      
      if (generateDebugInfo) {
         System.out.println("JunctionTreeNodeWithPTAC: combineWithProbabilityPotentials ----- BEGIN");
      }

      // Consider the probability potentials, if it is not null
      if (probPots != null) {
          
          long s1, s2, s3;

          for (int i = 0; i < probPots.size(); i++) {
              
            pot = probPots.get(i);
            s1 = pot.getSize();            
            
           if(generateDebugInfo){
                System.out.println("\n\tReestricción: "+constraint+"");
                System.out.println("\tPotencial de probabilidad: "+pot);
           }
                
 //pot.print();    
              

            // Combine
            pot = pot.combine(constraint);
            s2 = pot.getSize();
            
 //pot.print();            
            
            // Reorder the potential
            pot = postProcessConstrainedProbability(pot);
            s3 = pot.getSize();
            
 // pot.print();           
            
            if(generateDebugInfo){
                System.out.println("\tTamaño inicial: "+s1);
                System.out.println("\tTamaño tras combinar: "+s2);
                System.out.println("\tTamaño tras podar: "+s3);
            }
            
            // Add the new potential to the set of probability potentials
            if (pot != null) {
                addProbabilityPotential(pot);
            }

            
            
         }
      }
      
      if (generateDebugInfo) {
         System.out.println("JunctionTreeNodeWithPTAC: combineWithProbabilityPotentials ----- END");
      }

   }

   /**
    * Private mthod for combining the utility potentials for a given variable
    *
    * @param constraint potential with the constraint
    * @param probPots utility potentials constrained by the constraint
    */
   private void combineWithUtilityPotentials(Potential constraint,
           ArrayList<Potential> utilPots) {
      Potential pot=null;

      if (generateDebugInfo) {
         System.out.println("JunctionTreeNodeWithPTAC: combineWithUtilityPotentials ----- BEGIN");
      }

      // Consider the utility potentials, if it is not null
      if (utilPots != null) {
         
          long s1, s2, s3;

          for (int i = 0; i < utilPots.size(); i++) {
              
            pot = utilPots.get(i);
            s1 = pot.getSize();            
            
           if(generateDebugInfo){
                System.out.println("\n\tReestricción: "+constraint+"");
                System.out.println("\tPotencial de utilidad: "+pot);
           }
                
              
              

            // Combine
            pot = pot.combine(constraint);
            s2 = pot.getSize();
            // Reorder the potential
            pot = postProcessConstrainedUtility(pot);
            s3 = pot.getSize();
            
            if(generateDebugInfo){
                System.out.println("\tTamaño inicial: "+s1);
                System.out.println("\tTamaño tras combinar: "+s2);
                System.out.println("\tTamaño tras podar: "+s3);
            }

            
            
         }
      }

      // Add the new potential to the set of utility potentials
      if (pot != null) {
         addUtilityPotential(pot);
      }
      
      if (generateDebugInfo) {
         System.out.println("JunctionTreeNodeWithPTAC: combineWithUtilityPotentials ----- END");
      }
   }
}
