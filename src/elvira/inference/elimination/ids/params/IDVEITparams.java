/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package elvira.inference.elimination.ids.params;

import elvira.Node;
import java.util.Vector;

/**
 * Class for setting the parameters of the evaluation during the initialitiation
 *
 * @author Rafael Cabañas rcabanas@decsai.ugr.es
 */
/**
 * Class for setting the parameters of the evaluation during the initialitiation
 */
public class IDVEITparams extends IDVEparams {

    protected double iutilPerturbation = 0;
    protected double iprobPerturbation = 0;
    private Vector<Node> subsetOfPertNodes = new Vector<Node>();


    public Vector<Node> getSubsetOfPertNodes() {
        return subsetOfPertNodes;
    }

    public void setSubsetOfPertNodes(Vector<Node> subsetOfPertNodes) {
        this.subsetOfPertNodes = subsetOfPertNodes;
    }
    
    public void addPertNode(Node n) {
        this.subsetOfPertNodes.add(n);  
    }
    

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
