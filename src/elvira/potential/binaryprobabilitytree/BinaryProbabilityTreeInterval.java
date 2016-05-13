/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package elvira.potential.binaryprobabilitytree;

/**
 *
 * @author rcabanas
 */
public class BinaryProbabilityTreeInterval extends BinaryProbabilityTree{
    
    private double lowerBound, upperBound;

    public BinaryProbabilityTreeInterval() {
    }

    public BinaryProbabilityTreeInterval(double lowerBound, double upperBound) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }
    
    
    
        /**
     * Gets the number of nodes of the tree starting in this node.
     * @return the number of leaves beneath this tree node.
     */
    @Override
    public long getNumberOfNodes() {
        if (var == null) { // Probability node
            return 2;
        } else {
            return (1 + leftChild.child.getNumberOfNodes()
                    + rightChild.child.getNumberOfNodes());
        }
    }

      /**
     * Gets the number of nodes of the tree starting in this node.
     * @return the number of leaves beneath this tree node.
     */
    public long getNumberOfLeaves() {
        if (var == null) { // Probability node
            return 1;
        } else {
            return (leftChild.child.getNumberOfLeaves()
                    + rightChild.child.getNumberOfLeaves());
        }
    }  
    
    
    
}
