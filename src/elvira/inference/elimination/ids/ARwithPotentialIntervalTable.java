/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package elvira.inference.elimination.ids;

import elvira.*;
import elvira.inference.elimination.ids.params.ARITparams;
import elvira.inference.elimination.ids.params.IDVEITparams;
import elvira.parser.ParseException;
import elvira.potential.*;
import elvira.tools.VectorManipulator;
import elvira.tools.intervals.GenericIntervalProperties;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import lpsolve.LpSolveException;

/**
 *
 * @author Rafael Cabañas rcabanas@decsai.ugr.es
 */
public class ARwithPotentialIntervalTable extends ArcReversal {

    protected double iutilPerturbation = 0;
    protected double iprobPerturbation = 0;
    
    boolean checkChanceLP = false, checkReversalLP = false;
    
    protected Vector<Node> subsetOfPertNodes = new Vector<Node>();
    
    

    public enum RemovalMethod {

        BREESE_FERTIG, LINEAR_PROG
    };
    protected RemovalMethod removal = RemovalMethod.BREESE_FERTIG;

    /**
     * Creates a new propagation for a given diagram.
     *
     * @param id the influence diagram to evaluate.
     */
    public ARwithPotentialIntervalTable(IDiagram id) {
        super(id);
    }

    public ARwithPotentialIntervalTable(IDiagram id, ARITparams p) {
        super(id);

        this.iutilPerturbation = p.getIutilPerturbation();
        this.iprobPerturbation = p.getIprobPerturbation();
        this.removal = p.getRemoval();
        this.subsetOfPertNodes = p.getSubsetOfPertNodes();
        PotentialIntervalTable.setPolChoice(PotentialIntervalTable.ChoiceFunction.IMAXIMALITY);

    }

    /*Getter and setters*/
    public void setPolChoice(PotentialIntervalTable.ChoiceFunction f) {
        PotentialIntervalTable.setPolChoice(f);
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
    
    

    /**
     * Transforms one of the original relations into another one whose values
     * are of class
     * <code>PotentialTree</code>. @ param r the
     * <code>Relation</code> to be transformed.
     */
    @Override
    public Relation transformInitialRelation(Relation r) {
        PotentialTree potTree;
        Relation rNew = r;
        
        boolean precisePert = false;

        
    //    System.out.println("kind="+r.getKind());

        if (r.getKind() == Relation.CONSTRAINT) {
            return r;
        }


        //Perturbate only a subset
        if(subsetOfPertNodes.size()>0 && !subsetOfPertNodes.contains(r.getChild()))
            precisePert = true;
        else
            precisePert = false;
        
        
        
        
        if (r.getKind() == Relation.UTILITY) {
            //if (iutilPerturbation != 0) {
            rNew = new Relation();
            rNew.setVariables(r.getVariables().copy());
            rNew.setKind(r.getKind());



            PotentialIntervalTable pot = null;
            if(precisePert)
                pot = new PotentialIntervalTable((PotentialTable) r.getValues(), 0.0);
            else
                pot = new PotentialIntervalTable((PotentialTable) r.getValues(), iutilPerturbation);
            

            
            
            rNew.setValues(pot);



            //}
        } else if (r.getKind() == Relation.POTENTIAL) {



            //if (iprobPerturbation != 0) {
            rNew = new Relation();
            rNew.setVariables(r.getVariables().copy());
            rNew.setKind(r.getKind());

            PotentialIntervalTable pot = null;
            
            if(precisePert)
                pot = new PotentialIntervalTable((PotentialTable) r.getValues(), 0.0, true);
            else
                pot = new PotentialIntervalTable((PotentialTable) r.getValues(), iprobPerturbation, true);
    
            
            makeReachable(pot);
            
            if(removal == RemovalMethod.BREESE_FERTIG)
                setUpperBounds(pot);
            
            rNew.setValues(pot);

            //}
        }

//~~
 //       rNew.getValues().print();


        // Return the new relation
        return rNew;
    }

       /**
     * Transforms one of the original relations into another one whose values
     * are of class
     * <code>PotentialTree</code>. @ param r the
     * <code>Relation</code> to be transformed.
     */
    public Relation transformRelation(Relation r) {
        PotentialTree potTree;
        Relation rNew = r;

        
    //    System.out.println("kind="+r.getKind());

        if (r.getKind() == Relation.CONSTRAINT) {
            return r;
        }

       
        
        
        if (r.getKind() == Relation.UTILITY) {
            //if (iutilPerturbation != 0) {
            rNew = new Relation();
            rNew.setVariables(r.getVariables().copy());
            rNew.setKind(r.getKind());



            PotentialIntervalTable pot = new PotentialIntervalTable((PotentialTable) r.getValues(), iutilPerturbation);
            rNew.setValues(pot);



            //}
        } else if (r.getKind() == Relation.POTENTIAL) {



            //if (iprobPerturbation != 0) {
            rNew = new Relation();
            rNew.setVariables(r.getVariables().copy());
            rNew.setKind(r.getKind());

            PotentialIntervalTable pot = new PotentialIntervalTable((PotentialTable) r.getValues(), iprobPerturbation, true);
            
            
            makeReachable(pot);
            
            if(removal == RemovalMethod.BREESE_FERTIG)
                setUpperBounds(pot);
            


            //}
        }

//~~
 //       rNew.getValues().print();


        // Return the new relation
        return rNew;
    }

    
    
    
    
    
    
    
    
    
    public void makeReachable(Potential p) {
        if (p instanceof IPotentialInterval) {
            ((IPotentialInterval) p).makeReachable();
        }
    }

    /**
     * To get the expected utility after a node deletion
     */
    @Override
    public void getExpectedUtility(Node util, Node toRemove) {
        
        
        boolean imprecise = false;
        
        //Pre-process
        if(diag.getRelation(util).getValues() instanceof PotentialIntervalTable) {
            imprecise = true;
        }
        
        
        
        if(imprecise) {
            if (this.removal == RemovalMethod.BREESE_FERTIG) {
                getExpectedUtility_Breese_Fertig(util, toRemove);
            }else if(this.removal == RemovalMethod.LINEAR_PROG) {
                getExpectedUtility_LP(util, toRemove);        
            } else {
                System.out.println("ERROR: wrong removal method for intervals");
                System.exit(-1);
            }
        }else{
            super.getExpectedUtility(util, toRemove);       
        }


    }

    /**
     * To get the posterior distributions as result of an arc reversal.
     *
     * @param origin the origin node.
     * @param dest the destination node.
     */
    @Override
    public void getPosteriorDistributions(Node origin, Node dest) {
        
        
        
        
        boolean imprecise = false;
        boolean impreciseO = false, impreciseD = false;
        
        //Pre-process
        if(diag.getRelation(origin).getValues() instanceof PotentialIntervalTable) {
            impreciseO = true;
        }
        
         //Pre-process
        if(diag.getRelation(dest).getValues() instanceof PotentialIntervalTable) {
            impreciseD = true;
        }
        
        
        imprecise = impreciseO || impreciseD;
        
        
        iutilPerturbation = 0;
        iprobPerturbation = 0;
        
        if(imprecise && !impreciseO) {
            Relation r = diag.getRelation(origin);
            diag.removeRelation(origin);
            Relation rNew = transformRelation(r);
            diag.addRelation(rNew);  
        }
        
        if(imprecise && !impreciseD) {
            Relation r = diag.getRelation(dest);
            diag.removeRelation(dest);
            Relation rNew = transformRelation(r);
            diag.addRelation(rNew);  
        }
        
        
        
        if(imprecise) {
            if (this.removal == RemovalMethod.BREESE_FERTIG) {
                getPosteriorDistributions_Breese_Fertig(origin, dest);
            }else if(this.removal == RemovalMethod.LINEAR_PROG) {
                getPosteriorDistributions_LP(origin, dest);
            } else {
                System.out.println("ERROR: wrong removal method for intervals");
                System.exit(-1);
            }
        }else{
            super.getPosteriorDistributions(origin, dest);       
        }
    }

    /**
     * Function to actualize the utility when decision node removal.
     */
    public void maximizeUtility(Node util, Node toRemove, RelationList tables) {
        
        boolean imprecise = false;
        
        //Pre-process
        if(diag.getRelation(util).getValues() instanceof PotentialIntervalTable) {
            imprecise = true;
        }
        
        if(imprecise) {
            PotentialIntervalTable utilPotCopy = ((PotentialIntervalTable) diag.getRelation(util).getValues());
            Hashtable<Configuration, Vector<Integer>> polDi = utilPotCopy.argMax((FiniteStates)toRemove);
            strategy.add(polDi);
            
            
        }
        
        if(imprecise) {    
            if (this.removal == RemovalMethod.BREESE_FERTIG) {
                maximizeUtility_Breese_Fertig(util,toRemove,tables);
            }else if(this.removal == RemovalMethod.LINEAR_PROG) {
                maximizeUtility_LP(util, toRemove, tables);   
            } else {
                System.out.println("ERROR: wrong removal method for intervals");
                System.exit(-1);
            }
        }else{
            super.maximizeUtility(util, toRemove, tables);
        }
    }

    /**
     * To get the expected utility after deletion of a chance node Y using
     * Breese_Fertig method
     *
     */
    public void getExpectedUtility_Breese_Fertig(Node util, Node toRemove) {


        FiniteStates Y = (FiniteStates) toRemove;

        PotentialIntervalTable oldUtil = (PotentialIntervalTable) diag.getRelation(util).getValues();
        PotentialIntervalTable oldProb = (PotentialIntervalTable) diag.getRelation(Y).getValues();
        PotentialIntervalTable newUtil, newUtilLP = null;




        Vector varOldUtil = oldUtil.getVariables();
        Vector varOldProb = oldProb.getVariables();


        //Variables in the final utility
        Vector X = new Vector();
        for (int i = 0; i < varOldUtil.size(); i++) {
            Node n = (Node) varOldUtil.elementAt(i);
            if (!n.equals(Y)) {
                X.add(n);
            }
        }

        for (int i = 0; i < varOldProb.size(); i++) {
            Node n = (Node) varOldProb.elementAt(i);
            if (!n.equals(Y) && !X.contains(n)) {
                X.add(n);
            }
        }

        //New utility
        newUtil = new PotentialIntervalTable(X);


        Configuration confX = new Configuration(X);

        for (int j = 0; j < confX.possibleValues(); j++) {

            //Determines ys and yr for each state of X
            // lowest lower bound and highest high bound
            int ys = -1, yr = -1;
            double maxValue = Double.NEGATIVE_INFINITY;
            double minValue = Double.POSITIVE_INFINITY;


            Configuration confY = new Configuration();
            confY.insert(Y, 0);

            //muchos valores confY mal inicializada

            for (int yi = 0; yi < confY.possibleValues(); yi++) {

                Configuration confXY = new Configuration(confX, confY);

                double Ui = oldUtil.getMaxValue(confXY);
                double Li = oldUtil.getMinValue(confXY);

                if (Ui > maxValue) {
                    maxValue = Ui;
                    yr = yi;
                }

                if (Li < minValue) {
                    minValue = Li;
                    ys = yi;
                }


                confY.nextConfiguration();
            }

            confY = new Configuration();
            confY.insert(Y, 0);
            //Computes the bounds of the new utility
            double L = 0, U = 0;

            
            double vL[] = new double[Y.getNumStates()];
            double vU[] = new double[Y.getNumStates()];
            double vLC[] = new double[Y.getNumStates()];
            double vUC[] = new double[Y.getNumStates()];
            
            
            for (int yi = 0; yi < confY.possibleValues(); yi++) {

                Configuration confXY = new Configuration(confX, confY);
                
                vL[yi] = oldProb.getMinValue(confXY);
                vU[yi] = oldProb.getMaxValue(confXY);

                vLC[yi] = oldUtil.getMinValue(confXY);
                vUC[yi] = oldUtil.getMaxValue(confXY);

                if (yi == yr) {
                    U += oldUtil.getMaxValue(confXY) * oldProb.getMaxValue(confXY);
                } else {
                    U += oldUtil.getMaxValue(confXY) * oldProb.getMinValue(confXY);
                }


                if (yi == ys) {
                    L += oldUtil.getMinValue(confXY) * oldProb.getMaxValue(confXY);
                } else {
//                    L += oldUtil.getMaxValue(confXY) * oldProb.getMinValue(confXY);
                    L += oldUtil.getMinValue(confXY) * oldProb.getMinValue(confXY);
                }


                

                
                

                confY.nextConfiguration();
            }
            
            
            if(checkChanceLP) {
                    try {
                        double[] intervalLP = PotentialIntervalTable.solveRLP(vL, vU, vLC, vUC, 1, 1);
                        
                        if(Math.abs(intervalLP[0] - L)>0.00001 || Math.abs(intervalLP[1] - U)>0.00001) {
                            System.err.println("WARNING: LP interval is larger that BF method");
                        }
                        
                        
                        
                    } catch (LpSolveException ex) {
                        Logger.getLogger(ARwithPotentialIntervalTable.class.getName()).log(Level.SEVERE, null, ex);
                    }
                
                }
            
            

            newUtil.setMaxValue(confX, U);
            newUtil.setMinValue(confX, L);

            confX.nextConfiguration();
        }



        //Guardar util
        diag.getRelation(util).setValues(newUtil);
        this.addGenPotential(newUtil, "Remove chance "+toRemove.getName()+": "+newUtil.toString());

     //   newUtil.print();
     //   System.out.println();



    }
    
    
    
  

    /**
     * To get the posterior distributions as result of an arc reversal.
     *
     * @param origin the origin node.
     * @param dest the destination node.
     */
    public void getPosteriorDistributions_Breese_Fertig(Node origin, Node dest) {
        
        
        PotentialIntervalTable initialOrigin, initialDest, finalOrigin, finalDest, finalOriginLP=null, finalDestLP=null;
        
        
        System.out.println("REVERSE ARC");

        // To get the initial distributions

        initialOrigin = (PotentialIntervalTable) diag.getRelation(origin).getValues();
        initialDest = (PotentialIntervalTable) diag.getRelation(dest).getValues();
        
        //~~
 /*       initialOrigin.print();
        initialDest.print();
*/

        //Compute vars in final potentials (x,y,v1,v2,v3)
        Vector varsW = VectorManipulator.union(initialOrigin.getVariables(), initialDest.getVariables());


        //Creates the new empty potentials (without the values)
        finalDest = new PotentialIntervalTable(varsW);
        finalDest.setHead(origin);
        Vector newDestTail = VectorManipulator.subtractElement(varsW, origin);
        finalDest.setTail(newDestTail);


        finalOrigin = new PotentialIntervalTable(VectorManipulator.subtractElement(varsW, origin));
        finalOriginLP = new PotentialIntervalTable(VectorManipulator.subtractElement(varsW, origin));
        finalOrigin.setHead(dest);
        Vector newOriTail = VectorManipulator.subtractElement(finalOrigin.getVariables(), dest);
        finalOrigin.setTail(newOriTail);



        //////////
        // Computes the lower bounds of finalDest
        //////////
        Configuration confW = new Configuration(varsW);
        for (int i = 0; i < confW.possibleValues(); i++) {

            //New lower values
            double lowFinalDest = 0, upFinalDest=0;
            Configuration confID = new Configuration(initialDest.getVariables(), confW);
            Configuration confIO = new Configuration(initialOrigin.getVariables(), confW);
            double sumIO = 0, sumID = 0;
            
            double W = initialDest.getMinValue(confID) * initialOrigin.getMinValue(confIO);
            double W2 = initialDest.getMaxValue(confID) * initialOrigin.getMaxValue(confIO);
            
            sumIO += initialOrigin.getMinValue(confIO); 
            


            /*
             * initialOrigin.print(); initialDest.print(); confW.print();
             * System.out.println("\nW = " + initialDest.getMinValue(confID) +
             * "*" + initialOrigin.getMinValue(confIO));
             */
            //Finds the ys with a minimal bc(x|ys,...)
            Configuration confVarOri = new Configuration(initialOrigin.getHead());

            double s = -1, maxUpperBound = Double.NEGATIVE_INFINITY;
       //     double r = -1, minLowerBound = Double.POSITIVE_INFINITY;

            for (int j = 0; j < confVarOri.possibleValues(); j++) {

                Configuration conf_noVarOri = new Configuration(VectorManipulator.subtractElement(varsW, (FiniteStates) origin), confW);
                Configuration confID_j = new Configuration(confVarOri, conf_noVarOri, new NodeList(initialDest.getVariables()));


                double u_j = initialDest.getMaxValue(confID_j);
                if (u_j > maxUpperBound && j != confW.getValue((FiniteStates) origin)) {
                    maxUpperBound = u_j;
                    s = j;
                }
                
                
        /*        double l_j = initialDest.getMinValue(confID_j);
                if (l_j < minLowerBound && j != confW.getValue((FiniteStates) origin)) {
                    minLowerBound = l_j;
                    r = j;
                }

*/
                confVarOri.nextConfiguration();
            }
            
            


            //Computes the rest of the terms of W
            confVarOri = new Configuration(initialOrigin.getHead());
            for (int j = 0; j < confVarOri.possibleValues(); j++) {
                
                

                Configuration conf_noVarOri = new Configuration(VectorManipulator.subtractElement(varsW, (FiniteStates) origin), confW);
                Configuration confID_j = new Configuration(confVarOri, conf_noVarOri, new NodeList(initialDest.getVariables()));
                Configuration confIO_j = new Configuration(confVarOri, conf_noVarOri, new NodeList(initialOrigin.getVariables()));

                if (j == s) {
                    W += initialDest.getMaxValue(confID_j) * initialOrigin.getMaxValue(confIO_j);
           //         System.out.println("+ " + initialDest.getMaxValue(confID_j) + "*" + initialOrigin.getMaxValue(confIO_j) + "  <-- ys");
                    sumIO += initialOrigin.getMaxValue(confIO_j);


                } else if (j != confW.getValue((FiniteStates) origin)) {
                    W += initialDest.getMinValue(confID_j) * initialOrigin.getMinValue(confIO_j);
           //         System.out.println("\t+ " + initialDest.getMaxValue(confID_j) + "*" + initialOrigin.getMinValue(confIO_j));
                    sumIO += initialOrigin.getMinValue(confIO_j);
                }
                
                
                W2 += initialDest.getMinValue(confID_j) * initialOrigin.getMinValue(confIO_j);
                
                
                
                confVarOri.nextConfiguration();
            }




        //    System.out.println("=" + W);


            if (W > 0) {
                lowFinalDest = (initialDest.getMinValue(confID) * initialOrigin.getMinValue(confIO)) / W;
        //        System.out.println("lowprob=" + (initialDest.getMinValue(confID)) + " * " + (initialOrigin.getMinValue(confIO)) + " // " + W);
            } else if (W == 0) {
                lowFinalDest = 0;
            } else {
                System.out.println("ERROR: W<0");
                System.exit(-1);
            }
            
            if(W2>0){
                upFinalDest = (initialDest.getMaxValue(confID) * initialOrigin.getMaxValue(confIO)) / W2;
        //        System.out.println("lowprob=" + (initialDest.getMinValue(confID)) + " * " + (initialOrigin.getMinValue(confIO)) + " // " + W);
            } else if (W2 == 0) {
                upFinalDest = 0;
            } else {
                System.out.println("ERROR: W2<0");
                System.exit(-1);
            }


            finalDest.setMinValue(confW, lowFinalDest);
            finalDest.setMaxValue(confW, upFinalDest);
      //      System.out.println(lowFinalDest);
            confW.nextConfiguration();
        }


        /////////////
        /// Computes the lower bounds of finalOri
        ////////////

        Configuration confFinalOri = new Configuration(finalOrigin.getVariables());
        for (int i = 0; i < confFinalOri.possibleValues(); i++) {

            /*
             * initialOrigin.print(); initialDest.print(); confFinalOri.print();
             */


            // Selects the ys that minimizes the lower bound given the values of the rest of the variables
            // Y is the variable origin

            int s = -1;
            double minLowerBound = Double.POSITIVE_INFINITY;
            Configuration confY = new Configuration(initialOrigin.getHead());


            for (int j = 0; j < confY.possibleValues(); j++) {


                Configuration confID_withoutY = new Configuration(confFinalOri, initialOrigin.getHead()); //drops Y
                Configuration confID_j = new Configuration(confID_withoutY, confY, new NodeList(initialDest.getVariables())); // adds the confY


                double low_j = initialDest.getMinValue(confID_j);
                if (low_j < minLowerBound) {
                    s = j;
                    minLowerBound = low_j;
                }
                confY.nextConfiguration();
            }

            
            
             double vL[] = new double[confY.possibleValues()];
            double vU[] = new double[confY.possibleValues()];
            double vLC[] = new double[confY.possibleValues()];
            double vUC[] = new double[confY.possibleValues()];
            
            
            

            // Computes the lower bound for the given configuration


            confY = new Configuration(initialOrigin.getHead());
            double lowFinalOri = 0;
            double sumIO = 0;

            for (int j = 0; j < confY.possibleValues(); j++) {


                Configuration confID_withoutY = new Configuration(confFinalOri, initialOrigin.getHead()); //drops Y
                Configuration confID_j = new Configuration(confID_withoutY, confY, new NodeList(initialDest.getVariables())); // adds the confY

                Configuration confIO_withoutY = new Configuration(confFinalOri, initialOrigin.getHead()); //drops Y
                Configuration confIO_j = new Configuration(confIO_withoutY, confY, new NodeList(initialOrigin.getVariables())); // conf. only with the variables in initialOrigin



                if (j == s) {
                    lowFinalOri += initialDest.getMinValue(confID_j) * initialOrigin.getMaxValue(confIO_j);
                    sumIO += initialOrigin.getMaxValue(confIO_j);
                    
                //    System.out.println(initialDest.getMinValue(confID_j) + " * " + initialOrigin.getMaxValue(confIO_j) + "  <-- ys");

                } else {
                    lowFinalOri += initialDest.getMinValue(confID_j) * initialOrigin.getMinValue(confIO_j);
                    sumIO += initialOrigin.getMinValue(confIO_j);
               //     System.out.println(initialDest.getMinValue(confID_j) + " * " + initialOrigin.getMinValue(confIO_j) + "");

                }
                
                
                
                vL[j] = initialOrigin.getMinValue(confIO_j);
                vU[j] = initialOrigin.getMaxValue(confIO_j);

                vLC[j] = initialDest.getMinValue(confID_j);
                vUC[j] = initialDest.getMaxValue(confID_j);
                
                

                confY.nextConfiguration();
            }


            
            
           if(checkReversalLP) {
                    try {
                        
                        if(initialOrigin.getVariables().size()==1)
                            System.out.println("~~");
                        
                        double[] intervalLP = PotentialIntervalTable.solveRLP(vL, vU, vLC, vUC, 1, 1);
 
                        
                        finalOriginLP.setMinValue(confFinalOri, intervalLP[0]);
                        finalOriginLP.setMaxValue(confFinalOri, intervalLP[1]);
                        
                    } catch (LpSolveException ex) {
                        Logger.getLogger(ARwithPotentialIntervalTable.class.getName()).log(Level.SEVERE, null, ex);
                    }
                
                }
            
            
            
            

            //           System.out.println(" = " + lowFinalOri);
            finalOrigin.setMinValue(confFinalOri, lowFinalOri);
            finalOrigin.setMaxValue(confFinalOri, -1);



            confFinalOri.nextConfiguration();
        }





        ////// Computes the upper bonds
        setUpperBounds(finalDest);
        setUpperBounds(finalOrigin);
      
        


        diag.getRelation(dest).setValues(finalOrigin);
        diag.getRelation(origin).setValues(finalDest);

        this.addGenPotential(finalDest, "Arc Reversal final destination "+finalDest.toString());
        this.addGenPotential(finalOrigin, "Arc Reversal final origin "+finalOrigin.toString());


        
    //      makeReachable(finalDest);
    //      makeReachable(finalOrigin);

        //       finalDest.print();
        //      finalOrigin.print();
        //       System.out.println();
    }

    
        /**
     * Function to actualize the utility when decision node removal
     * using Breese_Fertig method.
     */
    public void maximizeUtility_Breese_Fertig(Node util, Node toRemove, RelationList tables) {
        FiniteStates D = (FiniteStates) toRemove;

        PotentialIntervalTable oldUtil = (PotentialIntervalTable) diag.getRelation(util).getValues();
        PotentialIntervalTable newUtil;
        

        Vector varOldUtil = oldUtil.getVariables();


        //Variables in the final utility
        Vector X = new Vector();
        for (int i = 0; i < varOldUtil.size(); i++) {
            Node n = (Node) varOldUtil.elementAt(i);
            if (!n.equals(D)) {
                X.add(n);
            }
        }
        
        
               //New utility
        newUtil = new PotentialIntervalTable(X);

        Configuration confX = new Configuration(X);

        for (int j = 0; j < confX.possibleValues(); j++) {

            
            //Determine the undominated alternatives
            Configuration confD = new Configuration();
            confD.insert(D, 0);

            double L[] = new double[confD.possibleValues()];
            double U[] = new double[confD.possibleValues()];
            for (int di = 0; di < confD.possibleValues(); di++) {
                
                Configuration confXD = new Configuration(confX, confD);
                
                double Ui = oldUtil.getMaxValue(confXD);
                double Li = oldUtil.getMinValue(confXD);
                
                L[di] = Li;
                U[di] = Ui;
                
                confD.nextConfiguration();
                
            }
            
            boolean UNDOM[] = PotentialIntervalTable.areUndominated(L, U);
            
            
            
            
            
            
            // Determines the
            // lowest lower bound and highest high bound

            double newU = Double.NEGATIVE_INFINITY;
            double newL = Double.POSITIVE_INFINITY;


            confD = new Configuration();
            confD.insert(D, 0);


            for (int di = 0; di < confD.possibleValues(); di++) {

                Configuration confXD = new Configuration(confX, confD);

                if(UNDOM[di]) {
                    double Ui = oldUtil.getMaxValue(confXD);
                    double Li = oldUtil.getMinValue(confXD);

                    if (Ui > newU) {
                        newU = Ui;
                    }

                    if (Li < newL) {
                        newL = Li;
                    }
                }
                confD.nextConfiguration();
            }

            newUtil.setMaxValue(confX, newU);
            newUtil.setMinValue(confX, newL);

            confX.nextConfiguration();
        }



        //Guardar util
        diag.getRelation(util).setValues(newUtil);
        this.addGenPotential(newUtil, "Remove Decision "+toRemove.getName()+": "+newUtil.toString());
        

//        newUtil.print();
        System.out.println();

       
        
    }
    
    /* Methods using LP*/
    
    public void getPosteriorDistributions_LP(Node origin, Node dest){
        
        
        PotentialIntervalTable initialOrigin, initialDest;
        

        // To get the initial distributions

        initialOrigin = (PotentialIntervalTable) diag.getRelation(origin).getValues();
        initialDest = (PotentialIntervalTable) diag.getRelation(dest).getValues();
        
       
        PotentialIntervalTable  finalOrigin, finalDest;
       

        //Compute vars in final potentials (x,y,v1,v2,v3)
        Vector varsW = VectorManipulator.union(initialOrigin.getVariables(), initialDest.getVariables());


        //Creates the new empty potentials (without the values)
        finalDest = new PotentialIntervalTable(varsW);
        finalDest.setHead(origin);
        Vector newDestTail = VectorManipulator.subtractElement(varsW, origin);
        finalDest.setTail(newDestTail);


        finalOrigin = new PotentialIntervalTable(VectorManipulator.subtractElement(varsW, origin));
        finalOrigin.setHead(dest);
        Vector newOriTail = VectorManipulator.subtractElement(finalOrigin.getVariables(), dest);
        finalOrigin.setTail(newOriTail);



        //////////
        // Computes the lower bounds of finalDest
        //////////
        Configuration confW = new Configuration(varsW);
        for (int i = 0; i < confW.possibleValues(); i++) {

            //New lower values
            double lowFinalDest = 0, upFinalDest=0;
            Configuration confID = new Configuration(initialDest.getVariables(), confW);
            Configuration confIO = new Configuration(initialOrigin.getVariables(), confW);

            
            double W = initialDest.getMinValue(confID) * initialOrigin.getMinValue(confIO);

            Configuration confVarOri = new Configuration(initialOrigin.getHead());
            
            
            double U[] = new double[confVarOri.possibleValues()];
            double L[] = new double[confVarOri.possibleValues()];
            
            double Uc[] = new double[confVarOri.possibleValues()];
            double Lc[] = new double[confVarOri.possibleValues()];
            
            double Udc[] = new double[confVarOri.possibleValues()];
            double Ldc[] = new double[confVarOri.possibleValues()];
            
            

            for (int j = 0; j < confVarOri.possibleValues(); j++) {
                
                

                Configuration conf_noVarOri = new Configuration(VectorManipulator.subtractElement(varsW, (FiniteStates) origin), confW);
                Configuration confID_j = new Configuration(confVarOri, conf_noVarOri, new NodeList(initialDest.getVariables()));
                Configuration confIO_j = new Configuration(confVarOri, conf_noVarOri, new NodeList(initialOrigin.getVariables()));


                U[j] = initialOrigin.getMaxValue(confIO_j);
                L[j] = initialOrigin.getMinValue(confIO_j);
                
                if(j==confW.getValue((FiniteStates) origin)) {
                    Uc[j] = 0;
                    Lc[j] = 0;
                    Udc[j] = initialDest.getMaxValue(confID_j);
                    Ldc[j] = initialDest.getMinValue(confID_j);
                }else{
                    Uc[j] = initialDest.getMaxValue(confID_j);
                    Lc[j] = initialDest.getMinValue(confID_j);;
                    Udc[j] = 0;
                    Ldc[j] = 0;                                  
                }

                confVarOri.nextConfiguration();
            }


            
             //Call the LP solver
            double[] newInterval = null;
            try {
                newInterval = PotentialIntervalTable.solveRLP(L, U, Lc, Uc, Ldc, Udc, 1, 1);
            } catch (LpSolveException ex) {
                Logger.getLogger(ARwithPotentialIntervalTable.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
                System.exit(-1);
            }

             lowFinalDest = 1 /(1 + newInterval[1]); // with max
             upFinalDest = 1 /(1 + newInterval[0]); // with min

            finalDest.setMinValue(confW, lowFinalDest);
            finalDest.setMaxValue(confW, upFinalDest);
      //      System.out.println(lowFinalDest);
            confW.nextConfiguration();
        }

        
     //   finalDest.print();
        

        /////////////
        /// Computes the lower bounds of finalOri
        ////////////

        Configuration confFinalOri = new Configuration(finalOrigin.getVariables());
        for (int i = 0; i < confFinalOri.possibleValues(); i++) {

            /*
             * initialOrigin.print(); initialDest.print(); confFinalOri.print();
             */


            // Selects the ys that minimizes the lower bound given the values of the rest of the variables
            // Y is the variable origin
confFinalOri.print();
            
            int s = -1;
            double minLowerBound = Double.POSITIVE_INFINITY;
            Configuration confY = new Configuration(initialOrigin.getHead());


            for (int j = 0; j < confY.possibleValues(); j++) {


                Configuration confID_withoutY = new Configuration(confFinalOri, initialOrigin.getHead()); //drops Y
                Configuration confID_j = new Configuration(confID_withoutY, confY, new NodeList(initialDest.getVariables())); // adds the confY


                double low_j = initialDest.getMinValue(confID_j);
                if (low_j < minLowerBound) {
                    s = j;
                    minLowerBound = low_j;
                }
                confY.nextConfiguration();
            }

            
            
             double L[] = new double[confY.possibleValues()];
            double U[] = new double[confY.possibleValues()];
            double Lc[] = new double[confY.possibleValues()];
            double Uc[] = new double[confY.possibleValues()];
            
            
            

            // Computes the lower bound for the given configuration


            confY = new Configuration(initialOrigin.getHead());
            double lowFinalOri = 0;

            for (int j = 0; j < confY.possibleValues(); j++) {


                Configuration confID_withoutY = new Configuration(confFinalOri, initialOrigin.getHead()); //drops Y
                Configuration confID_j = new Configuration(confID_withoutY, confY, new NodeList(initialDest.getVariables())); // adds the confY

                Configuration confIO_withoutY = new Configuration(confFinalOri, initialOrigin.getHead()); //drops Y
                Configuration confIO_j = new Configuration(confIO_withoutY, confY, new NodeList(initialOrigin.getVariables())); // conf. only with the variables in initialOrigin

                
                
                L[j] = initialOrigin.getMinValue(confIO_j);
                U[j] = initialOrigin.getMaxValue(confIO_j);

                Lc[j] = initialDest.getMinValue(confID_j);
                Uc[j] = initialDest.getMaxValue(confID_j);
                


                confY.nextConfiguration();
            }


            

            
            double[] newInterval = null;
            try {
                newInterval = PotentialIntervalTable.solveRLP(L, U, Lc, Uc, 1, 1);
            } catch (LpSolveException ex) {
                Logger.getLogger(ARwithPotentialIntervalTable.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
                System.exit(-1);
            }
            
            

            //           System.out.println(" = " + lowFinalOri);
            finalOrigin.setMinValue(confFinalOri, newInterval[0]);
            finalOrigin.setMaxValue(confFinalOri, newInterval[1]);



            confFinalOri.nextConfiguration();
        }


        
       // finalOrigin.print();
        

        
        
        GenericIntervalProperties.makeReachable(finalDest);
        GenericIntervalProperties.makeReachable(finalOrigin);
        


        diag.getRelation(dest).setValues(finalOrigin);
        diag.getRelation(origin).setValues(finalDest);

        this.addGenPotential(finalDest, "Arc Reversal final destination "+finalDest.toString());
        this.addGenPotential(finalOrigin, "Arc Reversal final origin "+finalOrigin.toString());

        

    }
    

   
   public void getExpectedUtility_LP(Node util, Node toRemove) {


        FiniteStates Y = (FiniteStates) toRemove;

        PotentialIntervalTable oldUtil = (PotentialIntervalTable) diag.getRelation(util).getValues();
        PotentialIntervalTable oldProb = (PotentialIntervalTable) diag.getRelation(Y).getValues();
        
        
        PotentialIntervalTable newUtil;

        Vector varOldUtil = oldUtil.getVariables();
        Vector varOldProb = oldProb.getVariables();


        //Variables in the final utility
        Vector X = new Vector();
        for (int i = 0; i < varOldUtil.size(); i++) {
            Node n = (Node) varOldUtil.elementAt(i);
            if (!n.equals(Y)) {
                X.add(n);
            }
        }

        for (int i = 0; i < varOldProb.size(); i++) {
            Node n = (Node) varOldProb.elementAt(i);
            if (!n.equals(Y) && !X.contains(n)) {
                X.add(n);
            }
        }

        //New utility
        newUtil = new PotentialIntervalTable(X);


        Configuration confX = new Configuration(X);

        for (int j = 0; j < confX.possibleValues(); j++) {


            Configuration confY = new Configuration();
            confY.insert(Y, 0);

            
            double vL[] = new double[Y.getNumStates()];
            double vU[] = new double[Y.getNumStates()];
            double vLC[] = new double[Y.getNumStates()];
            double vUC[] = new double[Y.getNumStates()];
            
            
            for (int yi = 0; yi < confY.possibleValues(); yi++) {

                Configuration confXY = new Configuration(confX, confY);
                
                vL[yi] = oldProb.getMinValue(confXY);
                vU[yi] = oldProb.getMaxValue(confXY);
                vLC[yi] = oldUtil.getMinValue(confXY);
                vUC[yi] = oldUtil.getMaxValue(confXY);

                confY.nextConfiguration();
            }
            
            
            
            
            double[] newInterval = null;
            
            try {
                newInterval = PotentialIntervalTable.solveRLP(vL, vU, vLC, vUC, 1, 1);
            } catch (LpSolveException ex) {
                Logger.getLogger(ARwithPotentialIntervalTable.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
                System.exit(-1);
            }
            
            

            newUtil.setMaxValue(confX, newInterval[1]);
            newUtil.setMinValue(confX, newInterval[0]);

            confX.nextConfiguration();
        }

        
     //   newUtil.print();


        //Guardar util
        diag.getRelation(util).setValues(newUtil);
        this.addGenPotential(newUtil, "Remove chance "+toRemove.getName()+": "+newUtil.toString());

     //   newUtil.print();
     //   System.out.println();


    }
       
    
      public void maximizeUtility_LP(Node util, Node toRemove, RelationList tables) {
        FiniteStates D = (FiniteStates) toRemove;

        PotentialIntervalTable oldUtil = (PotentialIntervalTable) diag.getRelation(util).getValues();
        PotentialIntervalTable newUtil;
        

        Vector varOldUtil = oldUtil.getVariables();


        //Variables in the final utility
        Vector X = new Vector();
        for (int i = 0; i < varOldUtil.size(); i++) {
            Node n = (Node) varOldUtil.elementAt(i);
            if (!n.equals(D)) {
                X.add(n);
            }
        }
        
        
               //New utility
        newUtil = new PotentialIntervalTable(X);

        Configuration confX = new Configuration(X);

        for (int j = 0; j < confX.possibleValues(); j++) {

            
            //Determine the undominated alternatives
            Configuration confD = new Configuration();
            confD.insert(D, 0);

            double L[] = new double[confD.possibleValues()];
            double U[] = new double[confD.possibleValues()];
            for (int di = 0; di < confD.possibleValues(); di++) {
                
                Configuration confXD = new Configuration(confX, confD);
                
                double Ui = oldUtil.getMaxValue(confXD);
                double Li = oldUtil.getMinValue(confXD);
                
                L[di] = Li;
                U[di] = Ui;
                
                confD.nextConfiguration();
                
            }
            
            boolean UNDOM[] = PotentialIntervalTable.areUndominated(L, U);
            
            
            
            
            
            
            // Determines the
            // lowest lower bound and highest high bound

            double newU = Double.NEGATIVE_INFINITY;
            double newL = Double.NEGATIVE_INFINITY;


            confD = new Configuration();
            confD.insert(D, 0);


            for (int di = 0; di < confD.possibleValues(); di++) {

                Configuration confXD = new Configuration(confX, confD);

                if(UNDOM[di]) {
                    double Ui = oldUtil.getMaxValue(confXD);
                    double Li = oldUtil.getMinValue(confXD);

                    if (Ui > newU) {
                        newU = Ui;
                    }

                    if (Li > newL) {
                        newL = Li;
                    }
                }
                confD.nextConfiguration();
            }

            newUtil.setMaxValue(confX, newU);
            newUtil.setMinValue(confX, newL);

            confX.nextConfiguration();
        }



        //Guardar util
        diag.getRelation(util).setValues(newUtil);
        this.addGenPotential(newUtil, "Remove Decision "+toRemove.getName()+": "+newUtil.toString());
        

//        newUtil.print();
        System.out.println();

       
        
    } 
    
    
    
    /* Updating potential methods */
    
    public static void setUpperBounds(PotentialIntervalTable pot) {


        FiniteStates head = (FiniteStates) pot.getHead().elementAt(0);

        Configuration conf = new Configuration(pot.getVariables());


        for (int i = 0; i < conf.possibleValues(); i++) {

            double sumLow = 0;

            Configuration confHead = new Configuration(pot.getHead());


            for (int j = 0; j < confHead.possibleValues(); j++) {
                if (conf.getValue(head) != confHead.getValue(head)) {
                    Configuration confTail = new Configuration(pot.getTail(), conf);
                    Configuration conf2 = new Configuration(confHead, confTail, new NodeList(pot.getVariables()));

                    sumLow += pot.getMinValue(conf2);
                }


                confHead.nextConfiguration();
            }

            pot.setMaxValue(conf, Math.max(1 - sumLow, pot.getMaxValue(conf)));
//pot.setMaxValue(conf, Mat1 - sumLow);
            conf.nextConfiguration();
        }





    }
    
    
   public Vector computePrecisionFitness() {

        Vector S = new Vector();
        for (int i = 0; i < strategy.size(); i++) {

            Hashtable<Configuration, Vector<Integer>> pol = (Hashtable<Configuration, Vector<Integer>>) strategy.get(i);

            Iterator<Configuration> iterator = pol.keySet().iterator();

            //Cambiar para que muestre
            Vector vars = ((Potential) results.get(i)).getVariables();
            Vector vars2 = new Vector();
            FiniteStates dec = null;
            for (int j = 0; j < vars.size(); j++) {
                if (j != vars.size() - 1) {
                    vars2.add(vars.elementAt(j));
                } else {
                    dec = (FiniteStates) vars.elementAt(j);
                }
            }

            int numAlternatives = 0;

            Configuration conf = new Configuration(vars2);

            int min = conf.possibleValues();
            int max = min * dec.getNumStates();
            for (int j = 0; j < conf.possibleValues(); j++) {
                try {
                    numAlternatives += ((Vector) pol.get(conf)).size();
                } catch (Exception e) {
                    System.out.println();
                }
                conf.nextConfiguration();
            }

            double score = (double) (max - numAlternatives) / (max - min);
            S.add(score);
            
            

        }
        return S;
    }    
    
    
    
    
    
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException, IOException {
        // TODO code application logic here

        String idFile = "ids/spi/oil.elv";
     //   idFile = "ids/spi/car_asim.elv";
     //   idFile = "ids/spi/nhl.elv";
     //   idFile = "ids/simple_nodec.elv";
      //  idFile = "ids/simpleID6.elv";
     //   idFile = "ids/asym/reactor.elv";

        double ppert = 1;
        double upert = 0;

        IDiagram id = new IDiagram(idFile);
        //ArcReversal 
        ArcReversal AR = new ArcReversal(id);
        AR.setSaveGenPots(false);
      
        AR.evaluateDiagram();
        
        System.out.println("MEU AR = " + ((PotentialTable) AR.getStatistics().getFinalExpectedUtility()).getValue(0));

        
        
        
        
        

        id = new IDiagram(idFile);
        ARITparams p = new ARITparams();
        p.setIprobPerturbation(ppert);
        p.setIutilPerturbation(upert);
        p.setRemoval(RemovalMethod.LINEAR_PROG);
        Node pertNode = id.getNodeList().getNode("Oil");
        pertNode = id.getNodeList().getNode("Seismic");
        System.out.println(pertNode.getName());
        p.addPertNode(pertNode);

        ARwithPotentialIntervalTable ARIT = new ARwithPotentialIntervalTable(id, p);

        ARIT.setSaveGenPots(false);
        ARIT.evaluateDiagram();
        
        double upmeu =  ((PotentialIntervalTable) ARIT.getStatistics().getFinalExpectedUtility()).getMaxValue(0);
        double lowmeu =  ((PotentialIntervalTable) ARIT.getStatistics().getFinalExpectedUtility()).getMinValue(0);


        System.out.println("MEU ARIT = " + lowmeu + ", " + upmeu +"\t size = "+(upmeu-lowmeu));
        
        ARIT.printStrategy();
        
        
        
//
        

        
        id = new IDiagram(idFile);
        IDVEITparams p2 = new IDVEITparams();
        p2.setIprobPerturbation(ppert);
        p2.setIutilPerturbation(upert);
        
        pertNode = id.getNodeList().getNode("Seismic");
        p2.addPertNode(pertNode);
        
        IDVEWithPotentialIntervalTable VEIT = new IDVEWithPotentialIntervalTable(id, p2);
        
        VEIT.setMakeReachableComb(true);
        VEIT.setMakeReachableMarg(true);
        VEIT.setMakeReachableBinComb(true);
        VEIT.setSimpleDecisionRemoval(true);
        
        //TODO:  standard cond, LINEAR_PROG_UTIL da error
        //      revisar tiempos (quitar mierda)

        VEIT.setConditioning(true);
        VEIT.setEvalMethod(IDVEWithPotentialIntervalTable.evalMethod.LINEAR_PROG);
        VEIT.generateDebugInfo=false;
        VEIT.obtainInterest();
        VEIT.propagate();
        
        VEIT.printStrategy();
        
        
        //Cambiar para que describa qué se añade... vector de strings
        
        
        
//        System.out.println("MEU AR = " + ((PotentialTable) AR.getStatistics().getFinalExpectedUtility()).getValue(0));
        System.out.println("MEU ARIT = " + ((PotentialIntervalTable) ARIT.getStatistics().getFinalExpectedUtility()).getMinValue(0) + ", " + ((PotentialIntervalTable) ARIT.getStatistics().getFinalExpectedUtility()).getMaxValue(0));
VectorManipulator.print(ARIT.computePrecisionFitness());
           System.out.println("MEU VEIT = " + ((PotentialIntervalTable) VEIT.getStatistics().getFinalExpectedUtility()).getMinValue(0) + ", " + ((PotentialIntervalTable) VEIT.getStatistics().getFinalExpectedUtility()).getMaxValue(0));
 
           VectorManipulator.print(VEIT.computePrecisionFitness());
           
           /*       Vector<Potential> generatedPotsARIT = ARIT.getGeneratedPots();
         Vector<Potential> generatedPotsAR = AR.getGeneratedPots();
        
         System.out.println("  ");
         
         for(int i=0; i<generatedPotsAR.size(); i++) {
             System.out.println(AR.getStrGenPot().elementAt(i));         
         }
         
         System.out.println("  ");
         
         
         for(int i=0; i<generatedPotsARIT.size(); i++) {
             System.out.println(ARIT.getStrGenPot().elementAt(i));         
         }         
    */     
         System.out.println("  ");
     //   PotentialInterval.areIncluded(generatedPotsAR, generatedPotsARIT);
         //Parece que no se han guardado en el mismo orden
         
        System.out.println();
        

        
        
        
        

    }
}
