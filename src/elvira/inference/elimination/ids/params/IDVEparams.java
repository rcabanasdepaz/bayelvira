/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package elvira.inference.elimination.ids.params;

/**
 * Class for setting the parameters of the evaluation during the
 * initialitiation
 * @author Rafael Cabañas rcabanas@decsai.ugr.es
 */
    /**
    * Class for setting the parameters of the evaluation during the
    * initialitiation
    */ 
   public class IDVEparams{
   
       
        protected double maximum;
        protected double minimum;
        public boolean generateStatistics = true;
        public boolean generateDebugInfo = true;
        public boolean checkUnity = true;
        
        protected boolean saveAsTable = true;

        public IDVEparams() {
        }

        public boolean isGenerateDebugInfo() {
            return generateDebugInfo;
        }

        public void setGenerateDebugInfo(boolean generateDebugInfo) {
            this.generateDebugInfo = generateDebugInfo;
        }

        public boolean isGenerateStatistics() {
            return generateStatistics;
        }

        public void setGenerateStatistics(boolean generateStatistics) {
            this.generateStatistics = generateStatistics;
        }

        public double getMaximum() {
            return maximum;
        }

        public void setMaximum(double maximum) {
            this.maximum = maximum;
        }

        public double getMinimum() {
            return minimum;
        }

        public void setMinimum(double minimum) {
            this.minimum = minimum;
        }

    public boolean isSaveAsTable() {
        return saveAsTable;
    }

    public void setSaveAsTable(boolean saveAsTable) {
        this.saveAsTable = saveAsTable;
    }

    public boolean isCheckUnity() {
        return checkUnity;
    }

    public void setCheckUnity(boolean checkUnity) {
        this.checkUnity = checkUnity;
    }
        
        
        

   }
   