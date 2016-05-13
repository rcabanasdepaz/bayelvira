package elvira.inference.clustering.lazyid;

import elvira.IDiagram;
import elvira.NodeList;
import elvira.Graph;

public class StrongJunctionTreeWithBPT extends StrongJunctionTree {

   /**
    * Value for storing the threshold for prunning probability trees
    */
   protected double thresholdForPrunningProbability = 0;

   /**
    * Value for storing the threshold for prunning utility trees
    */
   protected double thresholdForPrunningUtility = 0;
   
    /**
     * Data member to store the minimum and maximum values for utility
     */
    protected double minimum;
    protected double maximum;

    /**
     * Constructor receiving an ID as argument
     * @param IDiagram to triangulate
     * @param threshold
     * @param triangulationCriteria used for triangulation
     * @param propagationCriteria message passing or direct elimination
     *        of variables
     * @param variableEliminationCriteria using on-line triangulation
     *        or off-line triangulation
     * @param generateDebugInfo debug information flag
     * @param generateStatistics statistics information flag
     */
    public StrongJunctionTreeWithBPT(IDiagram diag, double thProb, double thUtility, int triangulationCriteria,
                             int propagationCriteria, int variableEliminationCriteria,
                             boolean generateDebugInfo, boolean generateStatistics, LazyPropagationIDWithBPT propagationID) {
        super(diag, triangulationCriteria, propagationCriteria,
              variableEliminationCriteria, generateDebugInfo, 
              generateStatistics, propagationID);
        
        if (generateDebugInfo) {
            System.out.println("StrongJunctionTreeWithBPT: class Constructor ----- BEGIN");
        }
        
        this.thresholdForPrunningProbability = thProb;
        this.thresholdForPrunningUtility = thUtility;
        
        if (generateDebugInfo) {
            System.out.println("StrongJunctionTreeWithBPT: class Constructor ----- END");
        }
        
        
    }

    /**
     * Private method for building a tree node
     * @param tree
     * @param clique to include in the node
     * @param index
     * @Override buildTreeNode defined on StringJunctionTree
     */
    public JunctionTreeNode buildTreeNode(StrongJunctionTree tree, NodeList clique, int index) {
        if (generateDebugInfo) {
            System.out.println("StrongJunctionTreeWithBPT: buildTreeNode ----- BEGIN");
        }   
        JunctionTreeNode node = new JunctionTreeNodeWithBPT(tree, clique, index, generateDebugInfo);
        
        if (generateDebugInfo) {
            System.out.println("StrongJunctionTreeWithBPT: buildTreeNode ----- BEGIN");
        }   
        
        return node;
        
        
    }

    /**
     * Private method for adding constraint links between the variables
     * related to the constraints
     * @param graph to modify
     */
    @Override
    public void addConstraintLinks(Graph graph) {
    }

    
    
    
    /**
    * Public method for getting the value of thresholdForPrunning
    */
    public double getThresholdForPrunningProbability() {
        return thresholdForPrunningProbability;
    }
    
    /**
    * Public method for getting the value of thresholdForPrunning
    */
    public double getThresholdForPrunningUtility() {
        return thresholdForPrunningUtility;
    }



    
    
    
    
    /**
     * Method for getting maximum
     */
    public double getMaximum(){
       return maximum;
    }
    
    /**
     * Method for getting the minimum
     */
    public double getMinimum(){
       return minimum;
    }
    
    /**
     * Method for setting the maximum
     */
    public void setMaximum(double maximum){
       this.maximum=maximum;
    }
    
    /**
     * Method for setting the minimum
     */
    public void setMinimum(double minimum){
       this.minimum=minimum;
    }
}
