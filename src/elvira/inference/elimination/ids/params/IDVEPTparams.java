package elvira.inference.elimination.ids.params;


   /**
    * Class for setting the parameters of the evaluation during the
    * initialitiation
    * 
    * 
    * @author Rafael Cabañas rcabanas@decsai.ugr.es
    */
   public class IDVEPTparams extends IDVEparams{

        public IDVEPTparams() {
        }
       

        /*
            * Limit to produce the identification of leaves in the tree This value can
            * be changed with setThresholdForPrunning
            */
        protected double thresholdForPruningProb = 0.0;
        /**
            * Limit to produce the identification of leaves in the tree This value can
            * be changed with setThresholdForPruningUtility
            */
        private double thresholdForPruningUtility = 0.0;
        /**
            * Data members to store the max and min values for the potentials
            */
        //protected double maximum, minimum;
        /**
            * Indicates if transformation for utility trees are only performed at the
            * begining or after each operation
            */
        private boolean onlyInitialTransformation = false;
        
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
    
    protected double lowValueThreshold = 0.0;
       
        

        public void setOnlyInitialTransformation(boolean onlyInitialTransformation) {
            this.onlyInitialTransformation = onlyInitialTransformation;
        }

        public void setThresholdForPruningUtility(double thresholdForPruningUtility) {
            this.thresholdForPruningUtility = thresholdForPruningUtility;
        }

        public void setThresholdForPrunning(double thresholdForPrunning) {
            this.thresholdForPruningProb = thresholdForPrunning;
        }

        public boolean isOnlyInitialTransformation() {
            return onlyInitialTransformation;
        }

        public double getThresholdForPruningUtility() {
            return thresholdForPruningUtility;
        }

        public double getThresholdForPrunning() {
            return thresholdForPruningProb;
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
        return !onlyInitialTransformation;
    }

    public void setRemovalConstraints(boolean initialConstraints) {
        this.onlyInitialTransformation = !initialConstraints;
    }

    public double getLowValueThreshold() {
        return lowValueThreshold;
    }

    public void setLowValueThreshold(double lowValueThreshold) {
        this.lowValueThreshold = lowValueThreshold;
    }
    
    

       
        
   }     
       