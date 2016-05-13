/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package elvira.tools.idiagram;

import elvira.Node;
import elvira.NodeList;
import elvira.potential.ProbabilityTree;
import elvira.potential.binaryprobabilitytree.BinaryProbabilityTree;
import elvira.tools.VectorManipulator;
import java.util.Vector;

/**
 *
 * @author Rafael Cabañas rcabanas@decsai.ugr.es
 */
public class InfoGainManipulator {
    
    
    public static double ponderateInfo(double info, BinaryProbabilityTree tree, Vector<Node> vars) {

        //if(true)return info;
        
        if(vars==null)
            return info;
        if(vars.size()==0)
            return info;
        
        double numConfAfected = VectorManipulator.sumIntValues(tree.getNumConfigurationsWithVariables(vars));
        double maxPossibleAfected = (double) (tree.getSize() * vars.size());
        
        System.out.println(numConfAfected+" "+maxPossibleAfected);
        

        
        return (1 + numConfAfected/maxPossibleAfected)*info;
        
        
    }
    
    
    public static double ponderateInfo(double info, ProbabilityTree tree, Vector<Node> vars) {
    
        int numConfAfected = VectorManipulator.sumIntValues(tree.getNumConfigurationsWithVariables(vars));
        int maxPossibleAfected = (int) (tree.getSize() * vars.size());
        
        System.out.println(numConfAfected+" "+maxPossibleAfected);
        
        return (1 + numConfAfected/maxPossibleAfected)*info;
        
        
        
    } 
    
    
    public static double ponderateWithNumVars(double info, BinaryProbabilityTree tree, Vector<Node> vars) {

        //if(true)return info;
        
        if(vars==null)
            return info;
        if(vars.size()==0)
            return info;
        
        if(tree.isProbab())
            return info;
        
        Vector<Node> treeVars = tree.getVarList().getNodes();
        Vector<Node> intersection = VectorManipulator.intersection(vars, treeVars);

        System.out.println(intersection.size()+" "+treeVars.size());
        
        
     
        
        return (1 + Math.exp(intersection.size()/treeVars.size()))*info;
        
        
    }
    
    
    
    public static double ponderateWithSizesVars(double info, BinaryProbabilityTree tree, Vector<Node> vars) {

        //if(true)return info;
        
        if(vars==null)
            return info;
        if(vars.size()==0)
            return info;
        
        if(tree.isProbab())
            return info;
        
        Vector<Node> treeVars = tree.getVarList().getNodes();
        Vector<Node> intersection = VectorManipulator.intersection(vars, treeVars);
 
        NodeList intersectionList = new NodeList(intersection);
        
        return (1 + intersectionList.getSize()/tree.getSize())*info;
        
        
    }
    
    
}
