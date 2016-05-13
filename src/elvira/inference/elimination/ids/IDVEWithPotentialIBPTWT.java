package elvira.inference.elimination.ids;

import elvira.*;
import elvira.inference.elimination.ids.params.IDVEIBPTWTparams;
import elvira.inference.elimination.ids.params.IDVEITparams;
import elvira.inference.elimination.ids.params.IDVEPTparams;
import elvira.inference.elimination.ids.params.IDVEparams;
import elvira.parser.ParseException;
import elvira.potential.*;
import elvira.potential.PotentialIntervalTable.ChoiceFunction;
import elvira.potential.binaryprobabilitytree.PotentialBPTree;
import elvira.potential.binaryprobabilitytree.PotentialIBPTWT;
import elvira.tools.idiagram.EUComparator;
import java.io.*;
import java.util.Random;
import java.util.Vector;

/**
 * Class <code>IDVEWithPotentialIBPTWT</code>. Implements the variable
 * elimination method of propagation using potentials of class
 * <code>PotentialIBPTWT</code>. If the initial potentials are not
 * PotentialTrees then they are converted to PotentialTrees.
 *
 */
public class IDVEWithPotentialIBPTWT extends IDVEWithPotentialBPTree {

    public static int transpCount = 1;
    private double iutilPerturbation=0;
    private double iprobPerturbation=0;

    /**
     * Constructs a new propagation for a given Bayesian network and some
     * evidence.
     *
     * @param b a <code>Bnet</code>.
     * @param e the evidence.
     */
    public IDVEWithPotentialIBPTWT(Bnet b, Evidence e) {
        super(b, e);

        /*       if (generateDebugInfo) {
         System.out.println("IDVEWithPotentialIntervalTable:  class Constructor(Bnet,Evidence) ----- BEGIN");
         }*/
        /*       if (generateDebugInfo) {
         System.out.println("IDVEWithPotentialIntervalTable:  class Constructor ----- END");
         }*/
    }

    /**
     * Constructs a new propagation for a given Bayesian network
     *
     * @param b a <code>Bnet</code>.
     */
    public IDVEWithPotentialIBPTWT(Bnet b) {
        super(b);
        /*       if (generateDebugInfo) {
         System.out.println("IDVEWithPotentialIntervalTable:  class Constructor(Bnet) ----- BEGIN");
         }*/

        /*       if (generateDebugInfo) {
         System.out.println("IDVEWithPotentialIntervalTable:  class Constructor ----- END");
         }*/
    }

    /**
     * Constructs a new propagation for a given Bayesian network and some
     * evidence.
     *
     * @param b a <code>Bnet</code>.
     * @param e the evidence.
     */
    public IDVEWithPotentialIBPTWT(Bnet b, Evidence e, IDVEIBPTWTparams p) {
        super(b, e, p);

        /*      if (generateDebugInfo) {
         System.out.println("IDVEWithPotentialIntervalTable:  class Constructor(Bnet,Evidence) ----- BEGIN");
         }*/
        this.iutilPerturbation = p.getIutilPerturbation();
        this.iprobPerturbation = p.getIprobPerturbation();
        /*      if (generateDebugInfo) {
         System.out.println("IDVEWithPotentialIntervalTable:  class Constructor ----- END");
         }*/
    }

    /**
     * Constructs a new propagation for a given Bayesian network
     *
     * @param b a <code>Bnet</code>.
     */
    public IDVEWithPotentialIBPTWT(Bnet b, IDVEIBPTWTparams p) {
        super(b, p);
        /*       if (generateDebugInfo) {
         System.out.println("IDVEWithPotentialIntervalTable:  class Constructor(Bnet) ----- BEGIN");
         }*/

        this.iutilPerturbation = p.getIutilPerturbation();
        this.iprobPerturbation = p.getIprobPerturbation();
        /*      if (generateDebugInfo) {
         System.out.println("IDVEWithPotentialIntervalTable:  class Constructor ----- END");
         }*/
    }

    public void setPolChoice(ChoiceFunction f) {
        PotentialIntervalTable.setPolChoice(f);
    }

    /**
     * Transforms one of the original relations into another one whose values
     * are of class <code>PotentialTree</code>. @ param r the
     * <code>Relation</code> to be transformed.
     */
    @Override
    public Relation transformInitialRelation(Relation r) {
        PotentialIBPTWT ipotTree;
        PotentialIntervalTable ipotTable;
        Relation rNew = r;
        String transName;

        if (generateDebugInfo) {
            System.out.println("IDVEWithPotentialIBPTWT:  transformInitialRelation ----- BEGIN");
        }

        if (r.getKind() == Relation.CONSTRAINT) {
            return r;
        }

        if (r.getKind() == Relation.UTILITY) {
       //     if (iutilPerturbation != 0) {
                rNew = new Relation();
                rNew.setVariables(r.getVariables().copy());
                rNew.setKind(r.getKind());

                //Creates the interval table
                ipotTable = new PotentialIntervalTable((PotentialTable) r.getValues(), iutilPerturbation);
                //Transforms it into a tree
                transpCount++;
                transName = "T" + Integer.toString(transpCount);
                ipotTree = new PotentialIBPTWT(ipotTable, transName);
                FiniteStates T = (FiniteStates) ipotTree.getListTransparents().elementAt(0);
                ipotTree.sortAndPruneUtility(sortUtilityTrees, thresholdForPruningUtility, false);
                

                //Inserts the transparent variables in all the paths from root to leaves
                ipotTree.getTree().insertVariableInLeaves(T);
       
                
                boolean included = ipotTable.isIncluded(ipotTree.getIntervalTable());

     
                rNew.setValues(ipotTree);

                this.maximumIU = Math.max(maximumIU, ipotTable.getHigherMax());
                this.minimumIU = Math.min(minimumIU, ipotTable.getLowerMin());

       //     }
        } else if (r.getKind() == Relation.POTENTIAL) {
          //  if (iprobPerturbation != 0) {
                rNew = new Relation();
                rNew.setVariables(r.getVariables().copy());
                rNew.setKind(r.getKind());
 
               ipotTable = new PotentialIntervalTable((PotentialTable) r.getValues(), iprobPerturbation, true);
          //      makeReachable(ipotTable);

                //Transforms it into a tree
                transpCount++;
                transName = "T" + Integer.toString(transpCount);
                ipotTree = new PotentialIBPTWT(ipotTable, transName);

                FiniteStates T = (FiniteStates) ipotTree.getListTransparents().elementAt(0);
                
                if(sortProbTrees)
                    ipotTree.sortAndBound(0.0);
                
                //Inserts the transparent variables in all the paths from root to leaves
                ipotTree.getTree().insertVariableInLeaves(T);
                       
               
               
              
                System.out.print(ipotTree.getSize());
                ipotTree.limitBound(thresholdForPruningProb);
                System.out.println("~~>"+ipotTree.getSize());
                
                boolean included = ipotTable.isIncluded(ipotTree.getIntervalTable());
                System.out.println("~~included: "+included);
                
                rNew.setValues(ipotTree);

            }
       // }

        if (generateDebugInfo) {

            System.out.println("IDVEWithPotentialIBPTWT:  transformInitialRelation ----- END");
        }

        // Return the new relation
        return rNew;
    }
    
        /**
     * Transform an utility potential, prunning the lower values if possible
     *
     * @param <code>Potential</code> the potential to transform
     * @param <code>boolean</code> is a utility?
     */
    @Override
    public Potential transformAfterOperation(Potential pot, boolean utility) {
        return pot;
    }
    

    public void samplingPropagation(Random rand, int iterations) throws Throwable {

        Vector<Vector<Potential>> resultsSet = new Vector<Vector<Potential>>();
        resultsSet = new Vector<Vector<Potential>>();

        for (int i = 0; i < iterations; i++) {

            IDiagram idp = getPreciseID(rand);

            IDVariableElimination ve = new IDVariableElimination(idp);
            ve.setCheckUnity(checkUnity);
            ve.setCombinationHeuristic(combinationHeuristic);
            ve.setCriteria(getCriteria());
            ve.setGreedyCombination(greedyCombination);
            ve.setIdCriteria(getIdCriteria());
            ve.generateStatistics = generateStatistics;
            ve.generateDebugInfo = generateDebugInfo;

            ve.propagate();

            Vector results_i = ve.getResults();

            for (int j = 0; j < results_i.size(); j++) {
                if (i == 0) {
                    resultsSet.add(new Vector<Potential>());
                }
                resultsSet.elementAt(j).add((Potential) results_i.elementAt(j));
            }

        }

        Vector<PotentialIntervalTable> resInterval = new Vector<PotentialIntervalTable>();
        for (int i = 0; i < resultsSet.size(); i++) {
            resInterval.add(new PotentialIntervalTable(resultsSet.elementAt(i).elementAt(0).getVariables(), resultsSet.elementAt(i)));
        }

        this.results = resInterval;

    }

    public IDiagram getPreciseID(Random rand) throws Throwable {

        IDiagram id = ((IDiagram) network).copy();

        RelationList rel = this.getInitialRelations();
        Vector newRel = new Vector();

        Relation r;
        for (int i = 0; i < rel.size(); i++) {

            r = (Relation) rel.elementAt(i);
            PotentialIntervalTable ipot = (PotentialIntervalTable) r.getValues();

            if (r.getKind() != Relation.CONSTRAINT) {
                if (r.getKind() != Relation.UTILITY) {
                  //  PotentialIntervalTable ipot = new PotentialIntervalTable((PotentialTable) r.getValues(), iutilPerturbation, false);

                    r.setValues(ipot.getRandomPreciseProbability(rand));
                } else {
                    //     PotentialIntervalTable ipot = new PotentialIntervalTable((PotentialTable) r.getValues(), iprobPerturbation, true);
                    r.setValues(ipot.getRandomPreciseUtility(rand));
                }
            }

            newRel.add(r);

        }

        id.setRelationList(newRel);

        return id;

    }

    
    
    
    /**
     * Check if the ID is evaluable. It performs initial transformations
     */
    public void checkEvaluable() {
        // Call to initial conditions in order to get measures 
        // about potential sizes, etc
        boolean evaluable = initialConditions();
        if (evaluable == false) {
            System.out.println("Non evaluable diagram......");
            System.exit(0);
        }

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
