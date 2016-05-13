package elvira.inference.elimination.ids.params;

import elvira.inference.elimination.ids.IDVEWithPotentialBPTree;
import elvira.inference.elimination.ids.IDVEWithPotentialBPTree.pruningMethods;

/**
 * Class for setting the parameters of the evaluation during the
 * initialitiation
 * @author rcabanas
 */
public class IDVEBPTparams extends IDVEparams {
    
   /**
     * Indicates the pruning method used for probability trees. By default, it
     * is not used any method
     */
    private IDVEWithPotentialBPTree.pruningMethods pruningProbMethod = IDVEWithPotentialBPTree.pruningMethods.NO_PRUNING;
    /**
     * Indicates the pruning method used for utility trees. By default, it is
     * not used any method
     */
    private IDVEWithPotentialBPTree.pruningMethods pruningUtilityMethod = IDVEWithPotentialBPTree.pruningMethods.NO_PRUNING;
    /**
     * Indicates if the c-index is used for making the partitions when pruning
     * utility trees (pruning method is not affected)
     */
    private boolean Cindex = false;
    /**
     * Limit to produce the identification of leaves in the tree This value can
     * be changed with setThresholdForPruning
     */
    private double thresholdForPruningProb = 0.0;
    /**
     * Limit to produce the identification of leaves in the tree This value can
     * be changed with setThresholdForPruningUtility
     */
    private double thresholdForPruningUtility = 0.0;
    /**
     * Indicate if variables are sorted after each operation
     */
    private boolean sortProbTrees = true;
    /**
     * Indicate if variables are sorted after each operation
     */
    private boolean sortUtilityTrees = true;

    /**
     * Indicate if utility trees are normalized
     */
    boolean normalize = false;
    /**
     * Indicates if transformation for utility trees are only performed at the
     * begining or after each operation
     */
    private boolean onlyInitialTransformation = false;
    

    
    /**
     * Adapted thresholds are used for pruning trees.
     * If it is set to true, the threshold will depend on the size of the tree
     */
    
    private boolean adaptativeThresholds = false;
    
      /**
     * Flag that indicates if trees are pruned during 
     * the initial transformation.
     * The default value is true
     */
    protected boolean pruneInitial = true;

     /**
     * Flag that indicates if trees are pruned during 
     * the transformations after each operation. 
     * The default value is true
     */
    protected boolean pruneAfterOperation = true;
    
    
    protected boolean improvedOps = false;

     protected boolean combinationConstraints = false;

    protected boolean initialConstraints = false;
    protected boolean removalConstraints = false;
    
    protected boolean simetric = false;
    
    protected double lowValueThreshold = 0.0;
    
    protected boolean sortStates = false;

    public boolean isSimetric() {
        return simetric;
    }

    public void setSimetric(boolean simetric) {
        this.simetric = simetric;
    }
    
    
    
    
    public IDVEBPTparams() {
    }
    
    
    public boolean isCindex() {
        return Cindex;
    }

    public void setCindex(boolean Cindex) {
        this.Cindex = Cindex;
    }

    public boolean isAdaptativeThresholds() {
        return adaptativeThresholds;
    }

    public void setAdaptativeThresholds(boolean adaptativeThresholds) {
        this.adaptativeThresholds = adaptativeThresholds;
    }

    public boolean isNormalize() {
        return normalize;
    }

    public void setNormalize(boolean normalize) {
        this.normalize = normalize;
    }

    public boolean isOnlyInitialTransformation() {
        return onlyInitialTransformation;
    }

    public void setOnlyInitialTransformation(boolean onlyInitialTransformation) {
        this.onlyInitialTransformation = onlyInitialTransformation;
    }

    public pruningMethods getPruningProbMethod() {
        return pruningProbMethod;
    }

    public void setPruningProbMethod(pruningMethods pruningProbMethod) {
        this.pruningProbMethod = pruningProbMethod;
    }

    public pruningMethods getPruningUtilityMethod() {
        return pruningUtilityMethod;
    }

    public void setPruningUtilityMethod(pruningMethods pruningUtilityMethod) {
        this.pruningUtilityMethod = pruningUtilityMethod;
    }

    public boolean isSortProbTrees() {
        return sortProbTrees;
    }

    public void setSortProbTrees(boolean sortProbTrees) {
        this.sortProbTrees = sortProbTrees;
    }

    public boolean isSortUtilityTrees() {
        return sortUtilityTrees;
    }

    public void setSortUtilityTrees(boolean sortUtilityTrees) {
        this.sortUtilityTrees = sortUtilityTrees;
    }

    public double getThresholdForPruningProb() {
        return thresholdForPruningProb;
    }

    public void setThresholdForPruningProb(double thresholdForPruningProb) {
        this.thresholdForPruningProb = thresholdForPruningProb;
    }

    public double getThresholdForPruningUtility() {
        return thresholdForPruningUtility;
    }

    public void setThresholdForPruningUtility(double thresholdForPruningUtility) {
        this.thresholdForPruningUtility = thresholdForPruningUtility;
    }

    public void setPruneAfterOperation(boolean pruneAfterOperation) {
        this.pruneAfterOperation = pruneAfterOperation;
    }

    public void setPruneInitial(boolean pruneInitial) {
        this.pruneInitial = pruneInitial;
    }

    public boolean isPruneAfterOperation() {
        return pruneAfterOperation;
    }

    public boolean isPruneInitial() {
        return pruneInitial;
    }

    public boolean isImprovedOps() {
        return improvedOps;
    }

    public void setImprovedOps(boolean improvedOps) {
        this.improvedOps = improvedOps;
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

    
    public boolean isRemovalConstraints() {
        return removalConstraints;
    }

    public void setRemovalConstraints(boolean removalConstraints) {
        this.removalConstraints = removalConstraints;
    }

    public double getLowValueThreshold() {
        return lowValueThreshold;
    }

    public void setLowValueThreshold(double lowValueThreshold) {
        this.lowValueThreshold = lowValueThreshold;
    }

    public boolean isSortStates() {
        return sortStates;
    }

    public void setSortStates(boolean sortStates) {
        this.sortStates = sortStates;
    }

    
    
    
    
       
    
    
    
    
}
