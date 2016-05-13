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
   public class IDVEIBPTWTparams extends IDVEBPTparams{
   
       protected double iutilPerturbation = 0;
       protected double iprobPerturbation = 0;



    public double getIutilPerturbation() {
        return iutilPerturbation;
    }

    public void setIutilPerturbation(double iutilPerturbation) {
        this.iutilPerturbation = iutilPerturbation;
    }

    public double getIprobPerturbation() {
        return iprobPerturbation;
    }

    public void setIprobPerturbation(double iprobPerturbation) {
        this.iprobPerturbation = iprobPerturbation;
    }
       
       
       


   }
   