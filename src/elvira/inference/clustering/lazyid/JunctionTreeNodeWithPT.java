package elvira.inference.clustering.lazyid;

import elvira.NodeList;
import elvira.potential.Potential;
import elvira.potential.PotentialTree;

public class JunctionTreeNodeWithPT extends JunctionTreeNode {

   /**
    * Constructor
    *
    * @param tree
    * @param variables
    * @param index
    * @param debugFlag
    */
   public JunctionTreeNodeWithPT(StrongJunctionTree tree, NodeList variables, int index, boolean debugFlag) {
      super(tree, variables, index, debugFlag);
      if (generateDebugInfo) {
         System.out.println("JunctionTreeNodeWithPT: class Constructor ----- BEGIN");
         System.out.println("JunctionTreeNodeWithPT: class Constructor ----- END");
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
         System.out.println("JunctionTreeNodeWithPT: buildTreeNode ----- BEGIN");
      }
      JunctionTreeNodeWithPT node = new JunctionTreeNodeWithPT(tree, clique, index, generateDebugInfo);
      if (generateDebugInfo) {
         System.out.println("JunctionTreeNodeWithPT: buildTreeNode ----- END");
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
    @Override
   protected Potential postProcessUtility(Potential potential) {
      
      if(tree.isOnlyInitialTransformation())
        return potential; 
       
       
      double minimum = ((StrongJunctionTreeWithPT) tree).getMinimum();
      double maximum = ((StrongJunctionTreeWithPT) tree).getMaximum();
      double threshold = ((StrongJunctionTreeWithPT) tree).getThresholdForPrunning();
      
      if (generateDebugInfo) {
         System.out.println("JunctionTreeNodeWithPT: postProcessUtility ----- BEGIN");
      }
      int s1 = potential.getVariables().size(); 
      PotentialTree finalPot = ((PotentialTree) potential).sortUtilityAndPrune(minimum,
              maximum, threshold);
       int s2 = finalPot.getVariables().size();           
       if(s1!=s2){
                System.out.println("diff");
            finalPot = ((PotentialTree) potential).sortUtilityAndPrune(minimum,
              maximum, threshold);
       
       
       }
      
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
   @Override
   protected Potential postProcessProbability(Potential potential) {
      double threshold = ((StrongJunctionTreeWithPT) tree).getThresholdForPrunning();
      
     if(tree.isOnlyInitialTransformation())
         return potential;
     
      
      if (generateDebugInfo) {
         System.out.println("JunctionTreeNodeWithPT: postProcessProbability ----- BEGIN");
      }
      
      int s1 = potential.getVariables().size();

      PotentialTree finalPot = ((PotentialTree) potential).sortAndBound(threshold);
      System.gc();
       int s2 = finalPot.getVariables().size();           
       if(s1!=s2)
                System.out.println("diff");
      //PotentialTree finalPot=((PotentialTree)potential).sortAndBoundWithoutRestrict(threshold);
      if (generateDebugInfo) {
         System.out.println("JunctionTreeNodeWithPT: postProcessProbability ----- END");
      }
      return finalPot;
   }
}
