/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package elvira.inference.elimination.ids.params;

import elvira.Node;
import elvira.inference.elimination.ids.ARwithPotentialIntervalTable;
import elvira.inference.elimination.ids.ARwithPotentialIntervalTable.RemovalMethod;
import java.util.Vector;

/**
 * Class for setting the parameters of the evaluation during the
 * initialitiation
 * @author Rafael Cabañas rcabanas@decsai.ugr.es
 */
    /**
    * Class for setting the parameters of the evaluation during the
    * initialitiation
    */ 
   public class ARITparams{
   
       protected double iutilPerturbation = 0;
       protected double iprobPerturbation = 0;
       
       protected RemovalMethod removal = RemovalMethod.BREESE_FERTIG;
       
       protected Vector<Node> subsetOfPertNodes = new Vector<Node>();



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

    public RemovalMethod getRemoval() {
        return removal;
    }

    public void setRemoval(RemovalMethod removal) {
        this.removal = removal;
    }
       

    public Vector<Node> getSubsetOfPertNodes() {
        return subsetOfPertNodes;
    }

    public void setSubsetOfPertNodes(Vector<Node> subsetOfPertNodes) {
        this.subsetOfPertNodes = subsetOfPertNodes;
    }
    
    public void addPertNode(Node n) {
        this.subsetOfPertNodes.add(n);  
    }
    
      


   }
   