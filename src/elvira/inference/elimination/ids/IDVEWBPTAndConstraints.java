/**
 *
 * @author Rafael Cabañas rcabanas@decsai.ugr.es
 */
package elvira.inference.elimination.ids;

import java.io.*;
import java.util.*;
import elvira.*;
import elvira.inference.elimination.ids.params.IDVEBPTparams;
import elvira.inference.elimination.ids.params.IDVEPTparams;
import elvira.parser.ParseException;
import elvira.potential.*;
import elvira.potential.binaryprobabilitytree.PotentialBPTree;

/**
 * Class
 * <code>VEWPTAndCOnstraints</code>. Implements the variable elimination method
 * of propagation using potentials of class
 * <code>PotentialTree</code>. If the initial potentials are not PotentialTrees
 * then they are converted to PotentialTrees. This class uses the constraints to
 * improve the evaluation of the influence diagram
 *
 * @since 27/06/2002
 */
public class IDVEWBPTAndConstraints extends IDVEWithPotentialBPTree {

    protected boolean combinationConstraints = false;
    protected boolean initialConstraints = true;
    protected boolean removalConstraints = false;

    /**
     * Constructs a new propagation for a given Bayesian network and some
     * evidence.
     *
     * @param b a
     * <code>Bnet</code>.
     * @param e the evidence.
     */
    public IDVEWBPTAndConstraints(Bnet b, Evidence e) {
        super(b, e);

        if (generateDebugInfo) {
            System.out.println("IDVEWBPTAndConstraints:  class Constructor(Bnet,Evidence) ----- BEGIN");
        }

        // Call to initial conditions in order to get measures 
        // about potential sizes, etc      
        boolean evaluable = initialConditions();
        if (evaluable == false) {
            System.out.println("Non evaluable diagram......");
            System.exit(0);
        }

        if (generateDebugInfo) {
            System.out.println("IDVEWBPTAndConstraints:  class Constructor ----- END");
        }
    }

    /**
     * Constructs a new propagation for a given Bayesian network
     *
     * @param b a
     * <code>Bnet</code>.
     */
    public IDVEWBPTAndConstraints(Bnet b) {
        super(b);

        if (generateDebugInfo) {
            System.out.println("IDVEWBPTAndConstraints:  class Constructor(Bnet,Evidence) ----- BEGIN");
        }

        // Call to initial conditions in order to get measures 
        // about potential sizes, etc
        boolean evaluable = initialConditions();
        if (evaluable == false) {
            System.out.println("Non evaluable diagram......");
            System.exit(0);
        }

        if (generateDebugInfo) {
            System.out.println("IDVEWBPTAndConstraints:  class Constructor ----- END");
        }
    }

    /**
     * Constructs a new propagation for a given Bayesian network and some
     * evidence.
     *
     * @param b a
     * <code>Bnet</code>.
     * @param e the evidence.
     * @param p evaluation parameters
     */
    public IDVEWBPTAndConstraints(Bnet b, Evidence e, IDVEBPTparams p) {
        super(b, e, p);

        if (generateDebugInfo) {
            System.out.println("IDVEWBPTAndConstraints:  class Constructor(Bnet,Evidence) ----- BEGIN");
        }

        // Call to initial conditions in order to get measures 
        // about potential sizes, etc      
        boolean evaluable = initialConditions();
        if (evaluable == false) {
            System.out.println("Non evaluable diagram......");
            System.exit(0);
        }



        if (generateDebugInfo) {
            System.out.println("IDVEWBPTAndConstraints:  class Constructor ----- END");
        }
    }

    /**
     * Constructs a new propagation for a given Bayesian network
     *
     * @param b a
     * @param p evaluation parameters
     * <code>Bnet</code>.
     */
    public IDVEWBPTAndConstraints(Bnet b, IDVEBPTparams p) {
        super(b, p);

        if (generateDebugInfo) {
            System.out.println("IDVEWBPTAndConstraints:  class Constructor(Bnet,Evidence) ----- BEGIN");
        }


        // Call to initial conditions in order to get measures 
        // about potential sizes, etc
        boolean evaluable = initialConditions();
        if (evaluable == false) {
            System.out.println("Non evaluable diagram......");
            System.exit(0);
        }

        if (generateDebugInfo) {
            System.out.println("IDVEWBPTAndConstraints:  class Constructor ----- END");
        }
    }

    /**
     * Transforms one of the original relations into another one whose values
     * are of class
     * <code>PotentialTree</code>, and adding the effect of the possible
     * constraints. This is done for normal relations, but not for constraints
     * relations @ param r the
     * <code>Relation</code> to be transformed.
     */
    @Override
    public Relation transformInitialRelation(Relation r) {
        PotentialBPTree potTree;
        LogicalExpression logExp;
        Relation rNew;

        if (generateDebugInfo) {
            System.out.println("IDVEWBPTAndConstraints:  transformInitialRelation ----- BEGIN");
        }

        if (r.getKind() != Relation.CONSTRAINT) {

            rNew = super.transformInitialRelation(r);

        } else {
            // May be the constraint is not evaluated
            logExp = (LogicalExpression) (r.getValues());
            logExp.setPotentialType("elvira.potential.binaryprobabilitytree.PotentialBPTree");
            potTree = (PotentialBPTree) logExp.getResult();
            if (potTree == null) {
                logExp.evaluate();
                potTree = (PotentialBPTree) logExp.getResult();
            }

            // Anyway, work with it to compact its contents

            potTree.sort();
            potTree.limitBound(0L);
            logExp.setResult(potTree);

            // Do not change the relation, and return the same
            rNew = r;
        }

        if (generateDebugInfo) {
            System.out.println("IDVEWBPTAndConstraints:  transformInitialRelation ----- END");
        }

        // return rNew
        return rNew;
    }

    /**
     * To tranform a potential after an operation on it
     *
     * @param <code>Potential</code> potential to transform
     * @param <code>boolean</code> flag to show if the potential is an utility
     * @return
     * <code>Potential</code> the modified potential
     */
    @Override
    public Potential transformAfterOperation(Potential pot, boolean utility) {
        PotentialBPTree potResult = (PotentialBPTree) pot;


        //Transformation after operations is not performed
        if (!onlyInitialTransformation) {




            if (generateDebugInfo) {
                System.out.println("IDVEWBPTAndConstraints:  transformAfterOperation ----- BEGIN");
            }

            // First at all examine the set of relations contained in currentRelations.
            // Remove the constraint relations related to variables already deleted      
            removeConstraintRelations();

            potResult = (PotentialBPTree) super.transformAfterOperation(pot, utility);

            if (generateDebugInfo) {
                System.out.println("IDVEWBPTAndConstraints:  transformAfterOperation ----- END");
            }
        }
        // Return potResult
        return potResult;



    }

    /**
     * Examine constraint relations trying to remove that constraints relating
     * variables already removed
     */
    private void removeConstraintRelations() {
        Relation rel;
        Relation relNonConst;
        NodeList varsInConstraint, varsInRel, intersection;
        boolean emptyIntersection = true;

        if (generateDebugInfo) {
            System.out.println("IDVEWBPTAndConstraints:  removeConstraintRelations ----- BEGIN");
        }

        for (int i = 0; i < currentRelations.size(); i++) {
            rel = currentRelations.elementAt(i);

            // Considere the relation if it is a contraint relation
            if (rel.getKind() == Relation.CONSTRAINT) {
                // Get the set of variables for this relation
                varsInConstraint = rel.getVariables();

                // Check if this set of variables has non empty intersection
                // with the other relations (not constraint relations)
                for (int j = 0; j < currentRelations.size(); j++) {
                    if (j != i) {
                        // Consider only non constraint relations
                        relNonConst = currentRelations.elementAt(j);

                        // Analyze the variables if it is not a constraint
                        // relation
                        if (relNonConst.getKind() != Relation.CONSTRAINT) {
                            varsInRel = relNonConst.getVariables();

                            // Check if there is a non empty intersection between
                            // both sets of variables
                            intersection = varsInConstraint.intersection(varsInRel);
                            emptyIntersection = (intersection.size() == 0);

                            // If the result is false, the constraint will not be deleted
                            if (emptyIntersection == false) {
                                break;
                            }
                        }
                    }
                }

                // If emptyIntersection is true, the relation must be deleted
                if (emptyIntersection == true) {
                    currentRelations.removeRelationAt(i);

                    // Decrement one to i so that the next relation be considered
                    i--;
                }
            }
        }

        if (generateDebugInfo) {
            System.out.println("IDVEWBPTAndConstraints:  removeConstraintRelations ----- END");
        }
    }

    public boolean isCombinationConstraints() {
        return combinationConstraints;
    }

    public void setCombinationConstraints(boolean applyConstraintsWhenCombining) {
        this.combinationConstraints = applyConstraintsWhenCombining;
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
    
    
}
