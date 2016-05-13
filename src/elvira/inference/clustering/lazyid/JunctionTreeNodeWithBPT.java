
package elvira.inference.clustering.lazyid;

import elvira.NodeList;
import elvira.inference.elimination.ids.IDVEWithPotentialBPTree;
import elvira.potential.Potential;
import elvira.potential.PotentialTree;
import elvira.potential.binaryprobabilitytree.BinaryProbabilityTree;
import elvira.potential.binaryprobabilitytree.PotentialBPTree;
import elvira.tools.idiagram.EUComparator;

public class JunctionTreeNodeWithBPT extends JunctionTreeNode{

  /**
   * Constructor
   * @param tree 
   * @param variables
   * @param index
   */
  public JunctionTreeNodeWithBPT(StrongJunctionTree tree,NodeList variables,int index, boolean debflag){
    super(tree,variables,index, debflag);
    if (generateDebugInfo) {
        System.out.println("JunctionTreeNodeWithBPT: class Constructor ----- BEGIN");
        System.out.println("JunctionTreeNodeWithBPT: class Constructor ----- END");
    }
    
  }

  /**
   * Method for building a new tree node
   * @param tree
   * @param clique nodelist to include in the node
   * @param index of the node
   */
  protected JunctionTreeNode buildTreeNode(StrongJunctionTree tree, 
                                         NodeList clique, int index){
      
      
    if (generateDebugInfo) {
        System.out.println("JunctionTreeNodeWithBPT: buildTreeNode ----- BEGIN");
    }

    JunctionTreeNodeWithBPT node=new JunctionTreeNodeWithBPT(tree,clique,index, generateDebugInfo);
    
    if (generateDebugInfo) {
        System.out.println("JunctionTreeNodeWithBPT: buildTreeNode ----- END");
    }  
      
    
    return node;
  }

  /**
   * Private method for post processing the utility potentials. For
   * PotentialTable this method does nothing, but with binary probability
   * potentials the variables will be sorted and a prunning will
   * be done if required. It is only used for DIRECT_ELIMINATION
   * @param potential
   */
    @Override
  protected Potential postProcessUtility(Potential potential){

    if(tree.isOnlyInitialTransformation())
        return potential;        
        
    double minimum=((StrongJunctionTreeWithBPT)tree).getMinimum();
    double maximum=((StrongJunctionTreeWithBPT)tree).getMaximum();
    double threshold=((StrongJunctionTreeWithBPT)tree).getThresholdForPrunningUtility();    
    
    PotentialBPTree newTree = new PotentialBPTree(potential.getVariables());
    newTree.setTree(((PotentialBPTree)potential).getTree().copy());
    
    double maxmin[] = {maximum, minimum};
    newTree.sortAndPruneUtility(true, false, IDVEWithPotentialBPTree.pruningMethods.EUCLIDEAN, threshold, false, false,maxmin);


    return newTree;
      
  }

  /**
   * Private method for post processing the probability potentials. For
   * PotentialTable this method does nothing, but with probability
   * potentials the variables will be sorted and a prunning will
   * be done if required
   * @param potential
     * @return 
   */
    @Override
  protected Potential postProcessProbability(Potential potential){  
   
    Potential pot = potential;
      
    if(tree.isOnlyInitialTransformation())
        return pot; 
    
    
    
    
    
    if(((LazyPropagationIDWithBPT)tree.getPropagationID()).isPruneAfterOperation()) {
        double threshold=((StrongJunctionTreeWithBPT)tree).getThresholdForPrunningProbability();    
        PotentialBPTree newTree = new PotentialBPTree(potential.getVariables());
        newTree.setTree(((PotentialBPTree)potential).getTree().copy());
        newTree.sort();
        newTree.limitBound(threshold);
        pot = newTree;
    }


    return pot;
      
  }
}
