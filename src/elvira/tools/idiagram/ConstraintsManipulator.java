/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package elvira.tools.idiagram;

import elvira.NodeList;
import elvira.potential.LogicalExpression;
import elvira.potential.Potential;
import java.util.Vector;

/**
 *
 * @author Rafael Cabañas rcabanas@decsai.ugr.es
 */
public class ConstraintsManipulator {

    public static Potential applyConstraint(LogicalExpression logexp, Potential potTree, NodeList common, boolean generateDebugInfo) {

        // Test if the logical expression is evaluated
        if (logexp.getResult() == null) {
            // Evaluate the constraint
            logexp.evaluate();
        }

        // Anyway, combine the potentials
        logexp.setPotentialType(potTree.getClassName());
        Potential constraint = logexp.getResult();
        constraint = constraint.maxMarginalizePotential(common.toVector());


        if (generateDebugInfo) {
            System.out.println("\tAplicando reestricción:");
            System.out.println("\t - Potencial: " + potTree);
            System.out.println("\t - Reestricción: " + constraint);
        }

        //potTree.limitBound(0L);
        // potTree = potTree.combine(constraint);
        potTree = constraint.combine(potTree);


        if (generateDebugInfo) {
            System.out.println("\t - Resultado tras combinar: " + potTree);
        }

        return potTree;

    }

    public static Potential applyConstraint(LogicalExpression logexp, Potential potTree, boolean generateDebugInfo) {
        NodeList varsInPot = new NodeList(potTree.getVariables());
        NodeList varsInConst = new NodeList(logexp.getVariables());
        NodeList common = varsInPot.intersection(varsInConst);


        return applyConstraint(logexp, potTree, common, generateDebugInfo);

    }

    public static boolean isAplicable(LogicalExpression logexp, Potential potTree) {
        NodeList varsInPot = new NodeList(potTree.getVariables());
        NodeList varsInConst = new NodeList(logexp.getVariables());
        NodeList common = varsInPot.intersection(varsInConst);

        if (common.size() > 0) {
            return true;
        }
        return false;

    }
}
