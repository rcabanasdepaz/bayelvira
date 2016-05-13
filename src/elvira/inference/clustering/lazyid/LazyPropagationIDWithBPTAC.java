package elvira.inference.clustering.lazyid;

import elvira.IDiagram;
import elvira.Network;
import elvira.Relation;
import elvira.potential.PotentialTree;
import elvira.potential.LogicalExpression;
import elvira.parser.ParseException;
import elvira.potential.Potential;
import elvira.potential.binaryprobabilitytree.PotentialBPTree;
import java.io.IOException;

public class LazyPropagationIDWithBPTAC extends LazyPropagationIDWithBPT {

    
    
    protected boolean combinationConstraints = false;
    protected boolean initialConstraints = true;
    protected boolean removalConstraints = false;
    
    
    /**
     * Class constructor: it receives the ID to solve
     * @param diag
     * @param threshold
     * @param triangulationCriteria used for triangulation
     * @param  propagationCriteria used for removing variables: with triangulation order or
     *                 with message passing schema
     * @param eliminationCriteria used for removing variables in the cliques: offline-online
     * @param debug flag
     * @param statistics flag
     */
    public LazyPropagationIDWithBPTAC(IDiagram diag, double threshold, int triangulationCriteria, int propagationCriteria,
                                   int eliminationCriteria,boolean debugFlag, boolean statisticsFlag) {
        super(diag,threshold,triangulationCriteria,propagationCriteria,eliminationCriteria,debugFlag,statisticsFlag);

        if (generateDebugInfo) {
           System.out.println("LazyPropagationIDWithBPTAC:  class Constructor ----- BEGIN");
        }
        
        Potential.setStatistics(statistics);
        
        // Set method name
        setMethod("LayPropagationIDWithPTAC");
        
        if (generateDebugInfo) {
           System.out.println("LazyPropagationIDWithBPTAC:  class Constructor ----- END");
        }
    }
    
    /**
     * Private method for building a tree node
     * @Override method in LazyPropagationID 
     */
    @Override
    public void buildStrongJunctionTree() {
        if (generateDebugInfo) {
           System.out.println("LazyPropagationIDWithPTAC:  buildStrongJunctionTree ----- BEGIN");
        }

        tree = new StrongJunctionTreeWithBPTAC((IDiagram)network, thresholdForPrunningProbability,
                triangulationCriteria, 
                propagationCriteria,variableEliminationCriteria, generateDebugInfo, 
                generateStatistics, this);
        
        // If it is needed, set the reference to the statistics objetc
        // for the tree
        if (generateStatistics){
           tree.setStatistics(statistics);
        }
        
        if (generateDebugInfo) {
           System.out.println("LazyPropagationIDWithPTAC:  buildStrongJunctionTree ----- END");
        }
    }

    /**
     * Public method for making the propagation
     */
    public void propagate() {
        if (generateDebugInfo) {
           System.out.println("LazyPropagationIDWithBPTAC:  propagate ----- BEGIN");
        }
        
        // Make the tree for the propagation
        buildStrongJunctionTree();
       
        tree.printStructure();
        
        // Set the properties for the evaluation
        ((StrongJunctionTreeWithBPT) tree).setMinimum(minimum);
        ((StrongJunctionTreeWithBPT) tree).setMaximum(maximum);
        ((StrongJunctionTreeWithBPT) tree).propagate();

       

        //Computes the decision tables
        this.checkDecisionTables();
        
        if (generateDebugInfo) {
           System.out.println("LazyPropagationIDWithBPTAC:  propagate ----- END");
        }
    }

    /**
     * Transforms one of the original relations into another one whose values
     * are of class <code>PotentialTree</code>, and adding the effect of the
     * possible constraints. This is done for normal relations, but not for
     * constraints relations
     * @ param r the <code>Relation</code> to be transformed.
     */
    public Relation transformInitialRelation(Relation r) {
        PotentialBPTree potTree;
        LogicalExpression logExp;
        Relation rNew;
        double minimum;
        double maximum;

        if (generateDebugInfo) {
           System.out.println("LazyPropagationIDWithBPTAC:  transformInitialRelation ----- BEGIN");
        }
        
        // Call the transformInitialRelation of LazyPropagationIDWithPT
        if (r.getKind() != Relation.CONSTRAINT){
           r = super.transformInitialRelation(r);
        }
        else{
            // Now consider the use of constraints
            // May be the constraint is not evaluated
            logExp = (LogicalExpression) (r.getValues());
            potTree = (PotentialBPTree) logExp.getResult();
            if (potTree == null) {
                logExp.setPotentialType("elvira.potential.binaryprobabilitytree.PotentialBPTree");
                logExp.evaluate();
                potTree = (PotentialBPTree) logExp.getResult();
            }

            // Anyway, work with it to compact its contents
            potTree.sort();
            potTree.limitBound(0L);
            //potTree = potTree.sortAndBoundWithoutRestrict(0L);
            //r.setValues(potTree);
            logExp.setResult(potTree);
        }
        
        if (generateDebugInfo) {
           System.out.println("LazyPropagationIDWithBPTAC:  transformInitialRelation ----- END");
        }

        // Return r
        return r;
    }

    public boolean isCombinationConstraints() {
        return combinationConstraints;
    }

    public void setCombinationConstraints(boolean combinationConstraints) {
        this.combinationConstraints = combinationConstraints;
    }

    public boolean isInitialConstraints() {
        return initialConstraints;
    }

    public void setInitialConstraints(boolean initialConstraints) {
        this.initialConstraints = initialConstraints;
    }
    
    
    

}