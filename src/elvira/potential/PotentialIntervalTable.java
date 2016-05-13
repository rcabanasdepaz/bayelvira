/* _________________________________________________________________________
 
 PotentialIntervalTable
 
 Elvira Project
 
 File: PotentialIntervalTable.java
 Description: Implements a potential which elements are intervals
 Author: Manuel Gomez, Andrés Cano, Rafael Cabañas
 
 
 
 _________________________________________________________________________
 
 Note:
 
 ________________________________________________________________________ */
package elvira.potential;

import elvira.*;
import elvira.tools.VectorManipulator;
import elvira.tools.intervals.GenericIntervalProperties;
import java.io.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Random;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import lpsolve.LpSolve;
import lpsolve.LpSolveException;

/**
 * Class : PotentialIntervalTable Description: Potential to represent a
 * probability distribution defined by intervals Implements a potential as two
 * set of values: one for min and another for max values
 *
 * @author Manuel Gómez Olmedo (mgomez@decsai.ugr.es)
 * @author Andrés Cano Utrera (acu@decsai.ugr.es)
 * @author Rafael Cabañas rcabanas@decsai.ugr.es
 */
public class PotentialIntervalTable extends PotentialInterval {

    private double minValues[];
    private double maxValues[];


 public String toString2D(int varsX, int varsY, String sepCell, String sepLine, String pattern) {

    String str = "";
    
    //Check if vars of each axis sums the total number of variables
    if(varsX+varsY != this.variables.size()) {
        System.out.println("ERROR printing table");
        return "";
    }
    
    //Computes num. of elements per line
    int lineSize = 1;
    for(int i=0; i<varsX; i++)
        lineSize *= ((FiniteStates)this.variables.elementAt(variables.size()-1-i)).getNumStates();
    
    
   
    
    for(int i=0; i<this.maxValues.length; i++){
    
        
        Locale locale  = new Locale("en", "US");
        //String pattern = "0.0##";

        DecimalFormat decimalFormat = (DecimalFormat)NumberFormat.getNumberInstance(locale);
        decimalFormat.applyPattern(pattern);
        String valueStr;

        valueStr =  decimalFormat.format(minValues[i]);
        str += "\\left["+valueStr+',';
        
        valueStr =  decimalFormat.format(maxValues[i]);
        str += valueStr+"\\right]";
        
        if((i % lineSize)==lineSize-1)
            str += sepLine;
        else
            str += sepCell;
    
    }

    return str;
    }

    public static enum ChoiceFunction {

        MAXMIN, MAXMAX, IMAXIMALITY
    };
    public static ChoiceFunction polChoice = ChoiceFunction.MAXMAX;
    
    

    public PotentialIntervalTable() {
        minValues = null;
        maxValues = null;
    }

    /**
     * Constructs a new PotentialIntervalTable
     *
     * @param min PotentialTable with min values
     * @param max PotentialTable with max values
     */
    public PotentialIntervalTable(PotentialTable min, PotentialTable max) {
        double newValues[];
        // Set the variables
        variables = (Vector) (min.getVariables()).clone();
        // The values for min will be stored in the field minValues 
        minValues = min.getValues();
        // Set space for this new vector
        newValues = new double[minValues.length];
        // Copy all of these values
        System.arraycopy(minValues, 0, newValues, 0, minValues.length);
        minValues = newValues;
        // The same for max
        maxValues = max.getValues();
        // Set space for the new vector
        newValues = new double[maxValues.length];
        // Copy both arrays
        System.arraycopy(maxValues, 0, newValues, 0, maxValues.length);
        maxValues = newValues;
    }

    /**
     * Constructs a new PotentialIntervalTable from a precise potential. New
     * intervals are [p(x), p(x)+epsilon], where p is the value of the precise
     * potential
     *
     * @param pot precise potential
     * @param epsilon size of intervals
     */
    public PotentialIntervalTable(PotentialTable pot, double epsilon) {
        this(pot, epsilon, false);
    }

    /**
     * Constructs a new PotentialIntervalTable from a precise potential. New
     * intervals are [p(x), p(x)+epsilon], where p is the value of the precise
     * potential
     *
     * @param pot precise potential
     * @param epsilon size of intervals
     * @param isProb indicates if it is a probability potential with values in
     * [0,1]
     */
    public PotentialIntervalTable(PotentialTable pot, double epsilon, boolean isProb) {
        double newValues[];
        // Set the variables
        variables = (Vector) (pot.getVariables()).clone();

        minValues = new double[pot.getValues().length];
        maxValues = new double[pot.getValues().length];

        if (isProb) {
            this.setHead(pot.getHead());
            this.setTail(pot.getTail());
        }

        for (int i = 0; i < minValues.length; i++) {
            if (epsilon == 0) {
                minValues[i] = pot.getValue(i);
                maxValues[i] = minValues[i];
            } else if (!isProb) {
                minValues[i] = pot.getValue(i) - epsilon;
                maxValues[i] = pot.getValue(i) + epsilon;
                /*
                 * } else if(pot.getValue(i)==0){ minValues[i] = 0; maxValues[i]
                 * = 0;
                 */
            } else {
                minValues[i] = pot.getValue(i) * (1 - epsilon);
                maxValues[i] = minValues[i] + epsilon;
            }

        }

    }

    /**
     * Constructs a new <code>PotentialIntervalTable</code> for a list of
     * variables and creates an array to store the values.
     *
     * @param vars a <code>Vector</code> of variables (
     * <code>FiniteStates</code>).
     */
    public PotentialIntervalTable(Vector vars) {
        int nv;

        // Compute the size of the array.
        nv = (int) FiniteStates.getSize(vars);

        variables = (Vector) vars.clone();
        minValues = new double[nv];
        maxValues = new double[nv];
    }

    /**
     * Constructs a new <code>PotentialIntervalTable</code> from a CredalSet
     */
    public PotentialIntervalTable(CredalSet pot) {
        variables = pot.getListNonTransparents();
        Configuration conf = new Configuration(variables);
        int ncases = (int) FiniteStates.getSize(variables);

        minValues = new double[ncases];
        maxValues = new double[ncases];
        for (int i = 0; i < ncases; i++) {
            setMinValue(conf, pot.getMinimum(conf));
            setMaxValue(conf, pot.getMaximum(conf));
            conf.nextConfiguration();
        }
    }

    /**
     * Constructs a PotentialIntervalTable given a PotentialIntervalTree
     */
    public PotentialIntervalTable(PotentialIntervalTree pot) {
        variables = (Vector) pot.getVariables().clone();
        Configuration conf = new Configuration(variables);
        int ncases = (int) FiniteStates.getSize(variables);

        // Allocate space for minValues and maxValues
        minValues = new double[ncases];
        maxValues = new double[ncases];

        // Loop to get all the values
        for (int i = 0; i < ncases; i++) {
            setMinValue(conf, pot.getMinValue(conf));
            setMaxValue(conf, pot.getMaxValue(conf));
            conf.nextConfiguration();
        }
    }

    public PotentialIntervalTable(Vector vars, Vector<Potential> pots) {
        this(vars);

        for (int i = 0; i < maxValues.length; i++) {
            maxValues[i] = Double.NEGATIVE_INFINITY;
            minValues[i] = Double.POSITIVE_INFINITY;
        }

        Potential p;

        for (int j = 0; j < pots.size(); j++) {
            p = pots.elementAt(j);
            Configuration c = new Configuration(vars);
            for (int i = 0; i < c.possibleValues(); i++) {
                this.setMaxValue(c, Math.max(getMaxValue(c), p.getValue(c)));
                this.setMinValue(c, Math.min(getMinValue(c), p.getValue(c)));
                c.nextConfiguration();
            }
        }

    }

    /**
     * Method to actualize the values of the potential given a
     * PotentialConvexSet with a set of extreme points
     *
     * @param pot PotentialConvexSet with the set of extreme points to convert
     * into intervals.The update is done if the values in the potential convex
     * set are bigger than or lesser than the values already stored in the
     * intervals
     */
    public void actualizeValues(PotentialConvexSet pot) {
        // Make a configuration with the non transparent variables in pot
        Configuration nonTrans = new Configuration(pot.getListNonTransparents());

        // Consider all the cases
        for (long i = 0; i < FiniteStates.getSize(nonTrans.getVariables()); i++) {
            // Get max value for this configuration in the potential convex set
            double max = pot.getMaximum(nonTrans);
            double min = pot.getMinimum(nonTrans);

            // Actualize max and min values if required. For that is required to
            // get tge max and min values stored in this potential
            double actualMin = getMinValue(nonTrans);
            double actualMax = getMaxValue(nonTrans);

            // Update if requiered
            if (max > actualMax && max != Double.NaN) {
                setMaxValue(nonTrans, max);
            }
            if (min < actualMin && min != -Double.NaN) {
                setMinValue(nonTrans, min);
            }

            // Go to the next configuration
            nonTrans.nextConfiguration();
        }
    }

    /**
     * Get a copy of the array of min values
     */
    double[] getArrayCopyMinValues() {
        double mValues[] = new double[minValues.length];
        System.arraycopy(minValues, 0, mValues, 0, minValues.length);
        return mValues;
    }

    /**
     * Get a copy of the array of max values
     */
    double[] getArrayCopyMaxValues() {
        double mValues[] = new double[maxValues.length];
        System.arraycopy(maxValues, 0, mValues, 0, maxValues.length);
        return mValues;
    }

    /**
     * Copies this potential.
     *
     * @return a copy of this <code>PotentialTable</code>.
     */
    public Potential copy() {
        PotentialIntervalTable pot;
        int n;

        pot = new PotentialIntervalTable();
        pot.variables = (Vector) variables.clone();
        pot.minValues = getArrayCopyMinValues();
        pot.maxValues = getArrayCopyMaxValues();
        return pot;
    }

    /**
     * Print both min and max values
     */
    public void print() {
        int i, total;
        Configuration conf;

        super.print();
        System.out.print("values = table-interval ( \n");
        total = (int) FiniteStates.getSize(variables);
        conf = new Configuration(variables);
        for (i = 0; i < total; i++) {
            System.out.print("                ");
            conf.print();
            System.out.print(" [" + minValues[i] + ",   " + maxValues[i] + "],\n");
            conf.nextConfiguration();
        }
        System.out.print("                );\n");
    }

    public String getClassName() {
        return new String("PotentialIntervalTable");
    }

    /**
     * Marginalizes over a set of variables. It is equivalent to
     * <code>addVariable</code> over the other variables.
     *
     * @param vars a <code>Vector</code> of variables (
     * <code>FiniteStates</code>).
     * @return a <code>PotentialIntervalTable</code> with the marginalization of
     * this potential over <code>vars</code>.
     */
    @Override
    public Potential marginalizePotential(Vector vars) {

        Vector vars2;
        int i;
        FiniteStates temp;
        PotentialIntervalTable pot;

        vars2 = new Vector();
        for (i = 0; i < variables.size(); i++) {
            temp = (FiniteStates) variables.elementAt(i);
            if (vars.indexOf(temp) == -1) {
                vars2.addElement(temp);
            }
        }

        pot = addVariable(vars2);

        if (head != null && tail != null) {
            pot.setHead(Potential.getMargHead(this, vars2));
            pot.setTail(Potential.getMargTail(this, vars2));
        }

        return pot;
    }

    /**
     *
     */
    @Override
    public Hashtable<Configuration, Vector<Integer>> argMax(FiniteStates n) {

        FiniteStates temp;
        int i, pos;
        Configuration auxConf1, auxConf2;
        PotentialTable potPast;

        Hashtable<Configuration, Vector<Integer>> arg = new Hashtable<Configuration, Vector<Integer>>();

// List of variables without n
        Vector past = new Vector();
        for (i = 0; i < variables.size(); i++) {
            past.addElement(variables.elementAt(i));
        }
        past.remove(n);

        Vector listD = new Vector();
        listD.add(n);

        // creates the new potential and sets the values to 0.0
        potPast = new PotentialTable(past);

        for (i = 0; i < potPast.getValues().length; i++) {
            potPast.setValue(i, Double.NEGATIVE_INFINITY);
        }

        Configuration confAll = new Configuration(variables);
        Configuration confD;

        for (i = 0; i < confAll.possibleValues(); i++) {
            Configuration confPast = new Configuration(confAll, listD);
            confD = new Configuration(confAll, past);

            if (polChoice == ChoiceFunction.MAXMIN || (polChoice == ChoiceFunction.IMAXIMALITY)) {
                if (getMinValue(confAll) >= potPast.getValue(confPast)) {
                    potPast.setValue(confPast, getMinValue(confAll));
                    arg.remove(confPast);
                    Vector v = new Vector();
                    v.add(confAll.getValue(n));
                    arg.put(confPast, v);

                }

            } else if (polChoice == ChoiceFunction.MAXMAX) {
                if (getMaxValue(confAll) >= potPast.getValue(confPast)) {
                    potPast.setValue(confPast, getMinValue(confAll));
                    arg.remove(confPast);
                    Vector v = new Vector();
                    v.add(confAll.getValue(n));
                    arg.put(confPast, v);

                }

            }

            confAll.nextConfiguration();

        }

        /*          *
         * for (i = 0; i < minValues.length; i++) { confD = new
         * Configuration(confAll, listD); pos = confD.getIndexInTable();
         *
         * if (polChoice == ChoiceFunction.MAXMIN || (polChoice ==
         * ChoiceFunction.IMAXIMALITY)) { if (minValues[i] >=
         * potPast.getValue(pos)) { potPast.setValue(pos, minValues[i]);
         * arg.remove(confD); Vector v = new Vector();
         * v.add(confAll.getValue(n)); arg.put(confD, v); } } else if (polChoice
         * == ChoiceFunction.MAXMAX) { if (maxValues[i] > potPast.getValue(pos))
         * { potPast.setValue(pos, maxValues[i]); arg.remove(confD); Vector v =
         * new Vector(); v.add(confAll.getValue(n)); arg.put(confD, v);
         *
         *
         *
         * }
         * }
         *
         * confAll.nextConfiguration(); }
         */
        //Second round for IMAXIMALITY
        confAll = new Configuration(variables);
        


        if (polChoice == ChoiceFunction.IMAXIMALITY) {
           // arg.clear();

            for (i = 0; i < confAll.possibleValues(); i++) {

                Configuration confPast = new Configuration(confAll, listD);
                confD = new Configuration(confAll, past);
                
       /*         confAll.print();
                
                System.out.println(this.getMaxValue(confAll)+" >= "+potPast.getValue(confPast));
*/
                if (this.getMaxValue(confAll) >= potPast.getValue(confPast)){
                    //|| Math.abs(this.getMaxValue(confAll)-potPast.getValue(confPast))<0.000001) {
                    

                    Vector v = arg.get(confPast);
                    if (v == null) {
                        v = new Vector();
                    }
                    
                    if(!v.contains(confAll.getValue(n)))
                        v.add(confAll.getValue(n));
                    arg.put(confPast, v);
                    


                }

                confAll.nextConfiguration();
            }

            /*
             * for (i = 0; i < minValues.length; i++) { confD = new
             * Configuration(confAll, listD); pos = confD.getIndexInTable();
             *
             * if (maxValues[i] > potPast.getValue(pos)) { Vector v =
             * arg.get(confD); if (v == null) { v = new Vector(); }
             * v.add(confAll.getValue(n)); arg.put(confD, v);
             * System.out.println("added"); } System.out.println("~~conf
             * ="+confD.toString().replaceAll("\n", " "));
             * confAll.nextConfiguration(); }
             */
        }

        return arg;
    }

    @Override
    public Potential sendVarToEnd(Node x) {
        NodeList finalOrderOfVariables = new NodeList();
        PotentialIntervalTable newPotential;
        Configuration configuration;
        Node aux;
        double value;
        int i;
        long j, size;
        // Copy the variables from the potential to finalOrderOfVariables,
        // changing the order of x
        for (i = 0; i < variables.size(); i++) {
            aux = (Node) variables.elementAt(i);
            if (!aux.getName().equals(x.getName())) {
                finalOrderOfVariables.insertNode(aux);
            }
        }
        // Add the last one
        finalOrderOfVariables.insertNode(x);
        // Now, make a potential with the reordered set of variables
        newPotential = new PotentialIntervalTable(finalOrderOfVariables.toVector());
        size = (long) FiniteStates.getSize(variables);
        // Now, copy the values
        configuration = new Configuration(finalOrderOfVariables);
        // Loop to copy the values
        for (j = 0; j < size; j++) {
            value = getMaxValue(configuration);
            // This value will be copied into newPotential
            newPotential.setMaxValue(configuration, value);

            value = getMinValue(configuration);
            // This value will be copied into newPotential
            newPotential.setMinValue(configuration, value);
            // Move to next configuration
            configuration.nextConfiguration();
        }

        // At the end return the new Potential
        return ((Potential) newPotential);
    }
    
    
    
    public Potential setVariablesOrder(Node[] order){
       PotentialIntervalTable pot = this;
       
       for (int i=0; i<order.length; i++)
           pot = (PotentialIntervalTable) pot.sendVarToEnd(order[i]);
       
       return pot;
   }
    

    /**
     * Marginalizes over a set of variables using maximun as marginalization
     * operator.
     *
     * @param vars a <code>Vector</code> of variables (
     * <code>FiniteStates</code>).
     * @return a <code>PotentialIntervalTable</code> with the
     * max-marginalization of this potential over <code>vars</code>.
     */
    public Potential maxMarginalizePotential(Vector vars) {

        int i, pos;
        PotentialIntervalTable pot;
        Vector vars2;
        Configuration conf, subConf;
        SetVectorOperations svo = new SetVectorOperations();

        // creates the new potential and sets the values to 0.0
        pot = new PotentialIntervalTable(vars);

        for (i = 0; i < pot.maxValues.length; i++) {
            pot.minValues[i] = Double.NEGATIVE_INFINITY;
            pot.maxValues[i] = Double.NEGATIVE_INFINITY;
        }

        // Store in vars2 the variables present in "variables" but
        // not included in vars
        vars2 = svo.notIn(variables, vars);

        // Now for each configuration of the old potential, take
        // its value and see with which subconfiguration of the new
        // one it corresponds. If the new value is greater than
        // the value stored until this moment for the subconfiguration
        // then set the value as new value for the potential.
        conf = new Configuration(variables);

        for (i = 0; i < maxValues.length; i++) {
            subConf = new Configuration(conf, vars2);
            pos = subConf.getIndexInTable();
            if (maxValues[i] > pot.maxValues[pos]) {
                pot.maxValues[pos] = maxValues[i];
            }
            if (minValues[i] > pot.minValues[pos]) {
                pot.minValues[pos] = minValues[i];
            }
            conf.nextConfiguration();
        }

        return pot;
    }

    public double entropyPotential() {
        System.out.println("Error in PotentialIntervalTable.entropyPotential(): Method not implemented for PotentialIntervalTable");
        System.exit(1);
        return 0;
    }

    public double entropyPotential(Configuration conf) {
        System.out.println("Error in PotentialIntervalTable.entropyPotential(Configuration): Method not implemented for PotentialIntervalTable");
        System.exit(1);
        return 0;
    }

    public double totalPotential() {
        System.out.println("Error in PotentialIntervalTable.totalPotential(): Method not implemented for PotentialIntervalTable");
        System.exit(1);
        return 0;
    }

    public double totalPotential(Configuration conf) {
        System.out.println("Error in PotentialIntervalTable.totalPotential(Configuration): Method not implemented for PotentialIntervalTable");
        System.exit(1);
        return 0;
    }

    /**
     * Method that returns the size of the potential, the number of values it
     * could be store on it
     */
    public long getSize() {
        return minValues.length;
    }

    /**
     * Sets the value in a position in the array of values.
     *
     * @param index the position in the array to modify.
     * @param value the value to store in that position.
     */
    public void setValue(int index, double value) {
        minValues[index] = value;
        maxValues[index] = value;
    }

    /**
     * Sets the min value in a position in the array of min values.
     *
     * @param index the position in the array to modify.
     * @param value the value to store in that position.
     */
    public void setMinValue(int index, double value) {
        // Esto no esta bien, pero para que tire

        minValues[index] = value;
    }

    /**
     * Sets the max value in a position in the array of max values.
     *
     * @param index the position in the array to modify.
     * @param value the value to store in that position.
     */
    public void setMaxValue(int index, double value) {
        // Esto no esta bien, pero para que tire

        maxValues[index] = value;
    }

    /**
     * Sets the value for a configuration of variables.
     *
     * @param conf a <code>Configuration</code> of variables.
     * @param value a the new value for <code>Configuration conf</code>.
     */
    public void setValue(Configuration conf, double value) {
        int index;
        Configuration aux;

        aux = new Configuration(variables, conf);
        index = aux.getIndexInTable();
        maxValues[index] = value;
        minValues[index] = value;

    }

    /**
     * Sets the value for a configuration of variables.
     *
     * @param conf a <code>Configuration</code> of variables.
     * @param value a the new value for <code>Configuration conf</code>.
     */
    @Override
    public void setMinValue(Configuration conf, double value) {
        int index;
        Configuration aux;

        aux = new Configuration(variables, conf);
        index = aux.getIndexInTable();
        minValues[index] = value;
    }

    /**
     * Sets the value for a configuration of variables.
     *
     * @param conf a <code>Configuration</code> of variables.
     * @param value a the new value for <code>Configuration conf</code>.
     */
    @Override
    public void setMaxValue(Configuration conf, double value) {
        int index;
        Configuration aux;

        aux = new Configuration(variables, conf);
        index = aux.getIndexInTable();
        maxValues[index] = value;
    }

    /**
     * Method to actualize the values for max, if the values passed as argument
     * is greater than the
     *
     * @param value
     */
    /**
     * Sets all the values in a potential to a given value.
     *
     * @param value a real value.
     */
    public void setValue(double value) {
        System.out.println("Error in PotentialIntervalTable.setValue(double): Method not implemented for PotentialIntervalTable");
        System.exit(-1);
        return;
    }

    /**
     * Sets all the min values in a potential to a given value.
     *
     * @param value a real value.
     */
    public void setMinValue(double value) {
        int index;

        for (index = 0; index < minValues.length; index++) {
            minValues[index] = value;
        }
    }

    /**
     * Sets all the max values in a potential to a given value.
     *
     * @param value a real value.
     */
    public void setMaxValue(double value) {
        int index;

        for (index = 0; index < minValues.length; index++) {
            maxValues[index] = value;
        }
    }

    /**
     * Method to set the whole set of min values for the intervals
     *
     * @param minVals array with the min values
     */
    public void setMinValues(double[] minVals) {
        minValues = minVals;
    }

    /**
     * Method to set the whole set of max values for the intervals
     *
     * @param maxVals array with the min values
     */
    public void setMaxValues(double[] maxVals) {
        maxValues = maxVals;
    }

    /**
     * Gets the value for a configuration of variables.
     *
     * @param conf a <code>Configuration</code>.
     * @return the value of the potential for <code>Configuration conf</code>.
     */
    public double getValue(Configuration conf) {
        System.out.println("Error in PotentialIntervalTable.getValue(Configuration): Method not implemented for PotentialIntervalTable");
        System.exit(1);
        return 0;
    }

    /**
     * Gets the min value for a configuration of variables.
     *
     * @param conf a <code>Configuration</code>.
     * @return the value of the potential for <code>Configuration conf</code>.
     */
    public double getMinValue(Configuration conf) {
        int pos;
        Configuration aux;

        // Take a configuration from conf just for variables
        // in the potential.
        aux = new Configuration(variables, conf);
        pos = aux.getIndexInTable();

        return minValues[pos];
    }

    /**
     * Method to get the minimum value related to a given position The position
     * shows the configuration of the leaf to reach
     *
     * @param index
     * @return minimum value for this configuration
     */
    public double getMinValue(long index) {
        // Creta e aconfiguration with the variables of this potential
        Configuration conf = new Configuration(variables);

        // Jump to the wished set of values
        conf.goToConfiguration(index);

        // Get the minValue for this configuration
        return (getMinValue(conf));
    }

    /**
     * Gets the max value for a configuration of variables.
     *
     * @param conf a <code>Configuration</code>.
     * @return the value of the potential for <code>Configuration conf</code>.
     */
    public double getMaxValue(Configuration conf) {
        int pos;
        Configuration aux;

        // Take a configuration from conf just for variables
        // in the potential.
        aux = new Configuration(variables, conf);
        pos = aux.getIndexInTable();

        return maxValues[pos];
    }

    /**
     * Method to get the minimum value related to a given position The position
     * shows the configuration of the leaf to reach
     *
     * @param index
     * @return minimum value for this configuration
     */
    public double getMaxValue(long index) {
        // Creta e aconfiguration with the variables of this potential
        Configuration conf = new Configuration(variables);

        // Jump to the wished set of values
        conf.goToConfiguration(index);

        // Get the minValue for this configuration
        return (getMaxValue(conf));
    }

    /**
     * Restricts this potential to the configuration of variables conf.
     *
     * @param conf a <code>Configuration</code>.
     * @return A new <code>PotentialInterval</code> resulting from the
     * restriction of this PotentialInterval to <code>conf</code>.
     */
    public Potential restrictVariable(Configuration conf) {

        Configuration auxConf;
        Vector aux;
        FiniteStates temp;
        PotentialIntervalTable pot;
        int i;

        // Creates a configuration preserving the values in conf.
        auxConf = new Configuration(variables, conf);

        // Computes the list of variables of the new Potential.
        aux = new Vector();
        for (i = 0; i < variables.size(); i++) {
            temp = (FiniteStates) variables.elementAt(i);
            if (conf.indexOf(temp) == -1) {
                aux.addElement(temp);
            }
        }

        pot = new PotentialIntervalTable(aux);

        for (i = 0; i < pot.minValues.length; i++) {
            pot.minValues[i] = getMinValue(auxConf);
            pot.maxValues[i] = getMaxValue(auxConf);
            auxConf.nextConfiguration(conf);
        }

        return pot;
    }

    /**
     * Normalizes the values of this potential. The object is modified.
     */
    public void normalize() {
        int i;
        double s;

        s = totalPotential();

        for (i = 0; i < minValues.length; i++) {
            minValues[i] /= s;
            maxValues[i] /= s;
        }
    }

    /**
     * Normalizes the values of this potential over a specified variable. If the
     * specified variable is not in the potential variables list, does not do
     * anything.
     *
     * @param v a <code>FiniteStates</code> variable.
     */
    public void normalizeOver(FiniteStates v) {

        int i, j, vvalue;
        String vname;
        double kmin, kmax;
        Configuration conf;
        PotentialIntervalTable pot;

        if (!variables.contains(v)) {
            System.out.print("");
        }

        /*
         * if(getHead() != null) {
         *
         * for(i=getHead().size()-1; i<=0; i--){
         * if(!getHead().elementAt(i).equals(v)) {
         * getTail().add(getHead().elementAt(i)); getHead().remove(i); } } }
         */
        if (v.indexOf(variables) != -1) {
            //Obtain the normalization constants for each configuration
            pot = new PotentialIntervalTable(variables);
            conf = new Configuration(variables);

            vname = v.getName();
            for (i = 0; i < maxValues.length; i++) {
                kmin = getMinValue(conf);
                kmax = getMaxValue(conf);
                vvalue = conf.getValue(vname);

                for (j = 0; j < v.getNumStates(); j++) {
                    if (j != vvalue) {
                        conf.putValue(vname, j);
                        kmax = BigDecimal.valueOf(kmax).add(BigDecimal.valueOf(getMinValue(conf))).doubleValue();
                        kmin = BigDecimal.valueOf(kmin).add(BigDecimal.valueOf(getMaxValue(conf))).doubleValue();

                        /*
                         * kmax += getMinValue(conf); kmin += getMaxValue(conf);
                         */
                    }
                }

                conf.putValue(vname, vvalue);
                pot.setMaxValue(conf, kmax);
                pot.setMinValue(conf, kmin);
                conf.nextConfiguration();
            }

            // Normalize
            conf = new Configuration(variables);
            for (i = 0; i < maxValues.length; i++) {

                kmax = pot.getMaxValue(conf);
                kmin = pot.getMinValue(conf);

                double vmax = 0;
                double vmin = 0;

                if (kmax != 0) {
                    //vmax = BigDecimal.valueOf(getMaxValue(conf)).divide(BigDecimal.valueOf(kmax),10,RoundingMode.HALF_UP).doubleValue();
                    vmax = getMaxValue(conf) / kmax;
                }

                if (kmin != 0) {
                    // vmin = BigDecimal.valueOf(getMinValue(conf)).divide(BigDecimal.valueOf(kmin),10,RoundingMode.HALF_UP).doubleValue();
                    vmin = getMinValue(conf) / kmin;
                }

                double aux;
                if (vmin > vmax) {
                    aux = vmin;
                    vmin = vmax;
                    vmin = aux;
                }

                if (vmin < 0.0) {
                    vmin = 0.0;
                } else if (vmin > 1.0) {
                    vmin = 1.0;
                }

                if (vmax < 0.0) {
                    vmax = 0;
                } else if (vmax > 1.0) {
                    vmax = 1.0;
                }

                setMinValue(conf, vmin);
                setMaxValue(conf, vmax);
                conf.nextConfiguration();
            }
        }

    }

    /**
     * Saves a potential to a file. This one must be used to save the results of
     * a propagation, not to save a network. It includes the name of the
     * variables.
     *
     * @param p the <code>PrintWriter</code> where the potential will be
     * written.
     */
    public void saveResult(PrintWriter p) {

        int i, total;
        Configuration conf;

        for (i = 0; i < variables.size(); i++) {
            p.println("node " + ((FiniteStates) variables.elementAt(i)).getName());
        }

        saveAsConfig(p);
    }

    /**
     * Saves a potential to a file. This one must be used when saving a network.
     *
     * @param p the <code>PrintWriter</code> where the potential will be
     * written.
     */
    public void saveAsConfig(PrintWriter p) {
        int i, total;
        Configuration conf;

        p.print("values = table-interval ( \n");

        total = (int) FiniteStates.getSize(variables);

        conf = new Configuration(variables);

        for (i = 0; i < total; i++) {
            p.print("                ");
            conf.save(p);
            p.print(" = [" + minValues[i] + "," + maxValues[i] + "] ,\n");
            conf.nextConfiguration();
        }
        p.print("                );\n");
    }

    /**
     * Saves a potential to a file. This one must be used when saving a network.
     * The values are written as a table.
     *
     * @param p the <code>PrintWriter</code> where the potential will be
     * written.
     */
    public void save(PrintWriter p) {

        int i, total;

        p.print("values= table-interval (");

        total = (int) FiniteStates.getSize(variables);

        for (i = 0; i < total; i++) {
            p.println("(" + minValues[i] + ", " + maxValues[i] + ")");
        }
        p.print(");\n");
    }

    /**
     * Removes the argument variable summing over all its values.
     *
     * @param var a <code>Node</code> variable to be removed.
     * @return a new <code>PotentialIntervalTable</code> with the result of the
     * deletion.
     */
    @Override
    public Potential addVariable(Node var) {
        Vector v;

        v = new Vector();
        v.addElement(var);
        return (addVariable(v));
    }

    /**
     * Sums over all the values of the variables in a list.
     *
     * @param vars a <code>Vector</code> containing variables (
     * <code>FiniteStates</code>).
     * @return a new <code>PotentialIntervalTable</code> with the variables in
     * <code>vars</code> removed.
     */
    public PotentialIntervalTable addVariable(Vector vars) {
        Vector aux;
        FiniteStates temp;
        int i, pos;
        Configuration auxConf1, auxConf2;
        PotentialIntervalTable pot;

        aux = new Vector();

        // Creates the list of variables of the new potential.
        for (i = 0; i < variables.size(); i++) {
            temp = (FiniteStates) variables.elementAt(i);
            if (vars.indexOf(temp) == -1) {
                aux.addElement(temp);
            }
        }

        // Creates the new potential and sets the values to [0.0, 0.0]
        pot = new PotentialIntervalTable(aux);

        if (getHead() != null) {
            pot.setHead(Potential.getMargHead(this, vars));
            pot.setTail(Potential.getMargTail(this, vars));
        }

        for (i = 0; i < pot.maxValues.length; i++) {
            pot.minValues[i] = 0.0;
            pot.maxValues[i] = 0.0;
        }

        // Now for each configuration of the old potential, take
        // its value and see with which configuration of the new
        // one it corresponds. Then increment the value of the
        // new potential for that configuration.
        auxConf1 = new Configuration(variables);

        long numSum = 0;

        for (i = 0; i < maxValues.length; i++) {
            auxConf2 = new Configuration(auxConf1, vars);
            pos = auxConf2.getIndexInTable();

            if (!Double.isInfinite(minValues[i]) && !Double.isInfinite(pot.minValues[pos])) {
                pot.minValues[pos] = BigDecimal.valueOf(pot.minValues[pos]).add(BigDecimal.valueOf(minValues[i])).doubleValue();
            } else {
                pot.minValues[pos] = Double.NEGATIVE_INFINITY;
            }

            if (!Double.isInfinite(maxValues[i]) && !Double.isInfinite(pot.maxValues[pos])) {
                pot.maxValues[pos] = BigDecimal.valueOf(pot.maxValues[pos]).add(BigDecimal.valueOf(maxValues[i])).doubleValue();
            } else {
                pot.maxValues[pos] = Double.POSITIVE_INFINITY;
            }

            /*
             * pot.minValues[pos] += minValues[i]; pot.maxValues[pos] += maxValues[i];
             */
            numSum++;
            auxConf1.nextConfiguration();

        }

        return pot;
    }

    /**
     * Combines two potentials. The argument <code>p</code> can be a
     * <code>PotentialTable</code>, a <code>PotentialTree</code>, a
     *
     * @param p the <code>Potential</code> to combine with this.
     * @return a new <code>PotentialIntervalTable</code> with the result of the
     * combination.
     */
    public Potential combine(Potential p) {

        Vector v, v1, v2;
        Configuration conf, conf1, conf2;
        FiniteStates aux;
        int i;
        PotentialIntervalTable pot;

        if (!(p instanceof PotentialIntervalTable) && !(p instanceof PotentialTable) && !(p instanceof PotentialTree)) {
            System.out.println("Error in PotentialIntervalTable.combine(Potential p): invalid type of potential");
            System.exit(1);
            return null;
        }

        v1 = variables;
        v2 = p.variables;
        v = new Vector(); // Variables of the new potential.

        for (i = 0; i < v1.size(); i++) {
            aux = (FiniteStates) v1.elementAt(i);
            v.addElement(aux);
        }

        for (i = 0; i < v2.size(); i++) {
            aux = (FiniteStates) v2.elementAt(i);
            if (aux.indexOf(v1) == -1) {
                v.addElement(aux);
            }
        }

        // Creates the new potential.
        pot = new PotentialIntervalTable(v);

        if (head != null && p.head != null) {
            pot.setHead(Potential.getCombinationHead(this, p));
            pot.setTail(Potential.getCombinationTail(this, p));
        }

        // Now explore all the configurations in the new potential,
        // evaluate the two operands according to this configuration,
        // and multiply the two values.
        conf = new Configuration(v);

        double alpha1, beta1, alpha2, beta2;

        for (i = 0; i < pot.maxValues.length; i++) {
            conf1 = new Configuration(v1, conf);
            conf2 = new Configuration(v2, conf);

            alpha1 = getMinValue(conf1);
            beta1 = getMaxValue(conf1);

            if (p instanceof PotentialIntervalTable) {
                alpha2 = ((PotentialIntervalTable) p).getMinValue(conf);
                beta2 = ((PotentialIntervalTable) p).getMaxValue(conf);
            } else {
                alpha2 = p.getValue(conf2);
                beta2 = alpha2;

            }

            /*
             * if(Math.abs(beta1-alpha1)<0.001) { beta1 = Math.max(alpha1,
             * beta1); alpha1=beta1; }
             *
             * if(Math.abs(beta2-alpha2)<0.001) { beta2 = Math.max(alpha2,
             * beta2); alpha2=beta2;
             }
             */
            double max = Double.POSITIVE_INFINITY, min = Double.NEGATIVE_INFINITY;

            if (alpha1 >= 0) {
                if (alpha2 >= 0 && beta2 >= 0) {
                    if (!Double.isInfinite(alpha1) && !Double.isInfinite(beta2)) {
                        min = BigDecimal.valueOf(alpha1).multiply(BigDecimal.valueOf(alpha2)).doubleValue();
                    }

                    if (!Double.isInfinite(beta1) && !Double.isInfinite(beta2)) {
                        max = BigDecimal.valueOf(beta1).multiply(BigDecimal.valueOf(beta2)).doubleValue();
                    }

                } else if (alpha2 < 0 && beta2 >= 0) {

                    if (!Double.isInfinite(beta1) && !Double.isInfinite(alpha2)) {
                        min = BigDecimal.valueOf(beta1).multiply(BigDecimal.valueOf(alpha2)).doubleValue();
                    }

                    if (!Double.isInfinite(beta1) && !Double.isInfinite(beta2)) {
                        max = BigDecimal.valueOf(beta1).multiply(BigDecimal.valueOf(beta2)).doubleValue();
                    }

                    /*
                     * min = beta1 * alpha2; max = beta1 * beta2;
                     */
                } else if (alpha2 < 0 && beta2 < 0) {

                    if (!Double.isInfinite(beta1) && !Double.isInfinite(alpha2)) {
                        min = BigDecimal.valueOf(beta1).multiply(BigDecimal.valueOf(alpha2)).doubleValue();
                    }

                    if (!Double.isInfinite(alpha1) && !Double.isInfinite(beta2)) {
                        max = BigDecimal.valueOf(alpha1).multiply(BigDecimal.valueOf(beta2)).doubleValue();
                    }

                    /*
                     * min = beta1 * alpha2; max = alpha1 * beta2;
                     */
                }
            } else if (beta1 >= 0 && alpha2 >= 0) {

                if (!Double.isInfinite(alpha1) && !Double.isInfinite(beta2)) {
                    min = BigDecimal.valueOf(alpha1).multiply(BigDecimal.valueOf(beta2)).doubleValue();
                }
                if (!Double.isInfinite(beta1) && !Double.isInfinite(beta2)) {
                    max = BigDecimal.valueOf(beta1).multiply(BigDecimal.valueOf(beta2)).doubleValue();
                }


                /*
                 * min = alpha1 * beta2; max = beta1 * beta2;
                 */
            } else if (beta1 <= 0 && alpha2 >= 0) {
                if (!Double.isInfinite(alpha1) && !Double.isInfinite(beta2)) {
                    min = BigDecimal.valueOf(alpha1).multiply(BigDecimal.valueOf(beta2)).doubleValue();
                }
                if (!Double.isInfinite(alpha1) && !Double.isInfinite(alpha2)) {
                    max = BigDecimal.valueOf(beta1).multiply(BigDecimal.valueOf(alpha2)).doubleValue();
                }


                /*
                 * min = alpha1 * beta2; max = beta1 * alpha2;
                 */
            } else {

                //            System.out.println("WARNING: using default case for combining interval...");
                //default case
                min = Math.min(Math.min(alpha1 * alpha2, alpha1 * beta2), Math.min(beta1 * alpha2, beta1 * beta2));
                max = Math.max(Math.max(alpha1 * alpha2, alpha1 * beta2), Math.max(beta1 * alpha2, beta1 * beta2));
            }

            pot.setMinValue(conf, min);
            pot.setMaxValue(conf, max);

            conf.nextConfiguration();
        }

        return pot;
    }
    /**
     * Combines two potentials. The argument <code>p</code> can be a
     * <code>PotentialTable</code>, a <code>PotentialTree</code>, a
     *
     * @param p the <code>Potential</code> to combine with this.
     * @return a new <code>PotentialIntervalTable</code> with the result of the
     * combination.
     */
    public Potential combineNoCheckSign(Potential p) {

        Vector v, v1, v2;
        Configuration conf, conf1, conf2;
        FiniteStates aux;
        int i;
        PotentialIntervalTable pot;

        if (!(p instanceof PotentialIntervalTable) && !(p instanceof PotentialTable) && !(p instanceof PotentialTree)) {
            System.out.println("Error in PotentialIntervalTable.combine(Potential p): invalid type of potential");
            System.exit(1);
            return null;
        }

        v1 = variables;
        v2 = p.variables;
        v = new Vector(); // Variables of the new potential.

        for (i = 0; i < v1.size(); i++) {
            aux = (FiniteStates) v1.elementAt(i);
            v.addElement(aux);
        }

        for (i = 0; i < v2.size(); i++) {
            aux = (FiniteStates) v2.elementAt(i);
            if (aux.indexOf(v1) == -1) {
                v.addElement(aux);
            }
        }

        // Creates the new potential.
        pot = new PotentialIntervalTable(v);

        if (head != null && p.head != null) {
            pot.setHead(Potential.getCombinationHead(this, p));
            pot.setTail(Potential.getCombinationTail(this, p));
        }

        // Now explore all the configurations in the new potential,
        // evaluate the two operands according to this configuration,
        // and multiply the two values.
        conf = new Configuration(v);

        double alpha1, beta1, alpha2, beta2;

        for (i = 0; i < pot.maxValues.length; i++) {
            conf1 = new Configuration(v1, conf);
            conf2 = new Configuration(v2, conf);

            alpha1 = getMinValue(conf1);
            beta1 = getMaxValue(conf1);

            if (p instanceof PotentialIntervalTable) {
                alpha2 = ((PotentialIntervalTable) p).getMinValue(conf);
                beta2 = ((PotentialIntervalTable) p).getMaxValue(conf);
            } else {
                alpha2 = p.getValue(conf2);
                beta2 = alpha2;

            }

            /*
             * if(Math.abs(beta1-alpha1)<0.001) { beta1 = Math.max(alpha1,
             * beta1); alpha1=beta1; }
             *
             * if(Math.abs(beta2-alpha2)<0.001) { beta2 = Math.max(alpha2,
             * beta2); alpha2=beta2;
             }
             */
            double max = Double.POSITIVE_INFINITY, min = Double.NEGATIVE_INFINITY;

            min = BigDecimal.valueOf(alpha1).multiply(BigDecimal.valueOf(alpha2)).doubleValue();
            max = BigDecimal.valueOf(beta1).multiply(BigDecimal.valueOf(beta2)).doubleValue();


            pot.setMinValue(conf, min);
            pot.setMaxValue(conf, max);

            conf.nextConfiguration();
        }

        return pot;
    }

    /**
     * Divides two potentials. The argument <code>p</code> can be a
     * <code>PotentialTable</code>, a <code>PotentialTree</code>, a
     *
     * @param p the <code>Potential</code> to combine with this.
     * @return a new <code>PotentialIntervalTable</code> with the result of the
     * combination: this/p
     */
    public Potential divide(Potential p) {

        Vector v, v1, v2;
        Configuration conf, conf1, conf2;
        FiniteStates aux;
        int i;
        PotentialIntervalTable pot;

        if (!(p instanceof PotentialIntervalTable) && !(p instanceof PotentialTable) && !(p instanceof PotentialTree)) {
            System.out.println("Error in PotentialIntervalTable.divide(Potential p): invalid type of potential");
            System.exit(1);
            return null;
        }

        v1 = variables;
        v2 = p.variables;
        v = new Vector(); // Variables of the new potential.

        for (i = 0; i < v1.size(); i++) {
            aux = (FiniteStates) v1.elementAt(i);
            v.addElement(aux);
        }

        for (i = 0; i < v2.size(); i++) {
            aux = (FiniteStates) v2.elementAt(i);
            if (aux.indexOf(v1) == -1) {
                v.addElement(aux);
            }
        }

        // Creates the new potential.
        pot = new PotentialIntervalTable(v);

        if (head != null && p.head != null) {
            pot.setHead(Potential.getCombinationHead(this, p));
            pot.setTail(Potential.getCombinationTail(this, p));
        }

        // Now explore all the configurations in the new potential,
        // evaluate the two operands according to this configuration,
        // and multiply the two values.
        conf = new Configuration(v);

        double alpha1, beta1, prob;

        for (i = 0; i < pot.maxValues.length; i++) {
            conf1 = new Configuration(v1, conf);
            conf2 = new Configuration(v2, conf);

            alpha1 = getMinValue(conf1);
            beta1 = getMaxValue(conf1);

            double alpha2, beta2;
            if (p instanceof PotentialIntervalTable) {
                alpha2 = ((PotentialIntervalTable) p).getMinValue(conf);
                beta2 = ((PotentialIntervalTable) p).getMaxValue(conf);

            } else {
                alpha2 = p.getValue(conf2);
                beta2 = alpha2;
            }

            double minValue = 0, maxValue = 0;

            //Supose intervals are coherent
            if (alpha2 > 0) {
                if (alpha1 >= 0) {
                    minValue = alpha1 / beta2;
                    maxValue = beta1 / alpha2;
                } else if (beta1 <= 0) {
                    minValue = alpha1 / alpha2;
                    maxValue = beta1 / beta2;
                } else {
                    minValue = alpha1 / alpha2;
                    maxValue = beta1 / alpha2;

                }

            } else if (alpha2 == 0 && beta2 == 0) {
                minValue = 0;
                maxValue = 0;
            } else {
                //              System.out.println("WARNING: using default case in division of Intervales");
                //default case
                double alpha1alpha2 = Double.POSITIVE_INFINITY;
                double beta1alpha2 = Double.POSITIVE_INFINITY;
                if (alpha2 != 0) {
                    alpha1alpha2 = alpha1 / alpha2;
                    beta1alpha2 = beta1 / alpha2;
                }
                double alpha1beta2 = Double.POSITIVE_INFINITY;
                double beta1beta2 = Double.POSITIVE_INFINITY;
                if (beta2 != 0) {
                    alpha1beta2 = alpha1 / beta2;
                    beta1beta2 = alpha1 / beta2;
                }

                minValue = Math.min(Math.min(alpha1alpha2, alpha1beta2), Math.min(beta1alpha2, beta1beta2));

                maxValue = Math.max(Math.max(alpha1alpha2, alpha1beta2), Math.max(beta1alpha2, beta1beta2));
            }

            /*
             * double maxValue = Double.POSITIVE_INFINITY, minValue =
             * Double.NEGATIVE_INFINITY; if(beta1 !=0 ){ minValue = alpha1 /
             * beta2; }
             *
             * if(alpha2 != 0) { maxValue = beta1 / alpha2; }
             *
             */
            pot.setMinValue(conf, minValue);
            pot.setMaxValue(conf, maxValue);
            conf.nextConfiguration();
        }

        return pot;
    }

    /**
     * Adds two potentials. The argument <code>p</code> can be a
     * <code>PotentialIntervalTable</code>
     *
     * @param p the <code>Potential</code> to add with this.
     * @return a new <code>PotentialIntervalTable</code> with the result of the
     * addition.
     */
    public Potential addition(Potential p) {
        Vector v, v1, v2;
        Configuration conf, conf1, conf2;
        FiniteStates aux;
        int i;
        PotentialIntervalTable pot;

        if (!(p instanceof PotentialIntervalTable)) {
            System.out.println("Error in PotentialIntervalTable.addition(Potential p): invalid type of potential");
            System.exit(1);
            return null;
        }

        v1 = variables;
        v2 = p.variables;
        v = new Vector(); // Variables of the new potential.

        for (i = 0; i < v1.size(); i++) {
            aux = (FiniteStates) v1.elementAt(i);
            v.addElement(aux);
        }

        for (i = 0; i < v2.size(); i++) {
            aux = (FiniteStates) v2.elementAt(i);
            if (aux.indexOf(v1) == -1) {
                v.addElement(aux);
            }
        }

        // Creates the new potential.
        pot = new PotentialIntervalTable(v);

        if (head != null && p.head != null) {
            pot.setHead(Potential.getCombinationHead(this, p));
            pot.setTail(Potential.getCombinationTail(this, p));
        }

        // Now explore all the configurations in the new potential,
        // evaluate the two operands according to this configuration,
        // and multiply the two values.
        conf = new Configuration(v);

        double alpha1, beta1;
        double alpha2, beta2;

        for (i = 0; i < pot.maxValues.length; i++) {
            conf1 = new Configuration(v1, conf);
            conf2 = new Configuration(v2, conf);

            alpha1 = getMinValue(conf1);
            beta1 = getMaxValue(conf1);

            alpha2 = ((PotentialIntervalTable) p).getMinValue(conf2);
            beta2 = ((PotentialIntervalTable) p).getMaxValue(conf2);

            pot.setMinValue(conf, alpha1 + alpha2);
            pot.setMaxValue(conf, beta1 + beta2);
            conf.nextConfiguration();
        }

        return pot;
    }

    public static ChoiceFunction getPolChoice() {
        return polChoice;
    }

    public static void setPolChoice(ChoiceFunction polChoice) {
        PotentialIntervalTable.polChoice = polChoice;
    }

    /**
     * Method to assign teh values of a default interval
     *
     * @param defaults Vector with the default interval
     */
    public void setDefaultValues(Vector defaults) {
        double min = ((Double) defaults.elementAt(0)).doubleValue();
        double max = ((Double) defaults.elementAt(1)).doubleValue();
        int i, total;

        total = (int) FiniteStates.getSize(variables);

        for (i = 0; i < total; i++) {
            if (minValues[i] == -1) {
                if (maxValues[i] == -1) {
                    minValues[i] = min;
                    maxValues[i] = max;
                } else {
                    System.out.println("Error in PotentialIntervalTable.setDefaultValues: min value fixed and max value not fixed");
                    System.exit(0);
                }
            }
        }
    }

    public boolean isProper() {

        return GenericIntervalProperties.isProper(this);
    }

    public boolean isReachable() {
        return GenericIntervalProperties.isReachable(this);

    }

    public void makeReachable() {
        GenericIntervalProperties.makeReachable(this);
    }

    public PotentialIntervalTable getReachable() {
        PotentialIntervalTable pot = (PotentialIntervalTable) this.copy();
        pot.setHead(getHead());
        pot.setTail(getTail());
        GenericIntervalProperties.makeReachable(pot);
        return pot;
    }

    public boolean consistentLimits() {
        return GenericIntervalProperties.consistentLimits(this);
    }

    public double getHigherMax() {
        double max = Double.NEGATIVE_INFINITY;
        for (int i = 0; i < maxValues.length; i++) {
            if (maxValues[i] > max) {
                max = maxValues[i];
            }
        }

        return max;
    }

    public double getLowerMin() {
        double min = Double.POSITIVE_INFINITY;
        for (int i = 0; i < minValues.length; i++) {
            if (minValues[i] < min) {
                min = minValues[i];
            }
        }

        return min;
    }

    public double getSumMax() {
        double sum = 0;
        for (int i = 0; i < maxValues.length; i++) {
            sum += maxValues[i];
        }
        return sum;
    }

    public double getSumMin() {
        double sum = 0;
        for (int i = 0; i < minValues.length; i++) {
            sum += minValues[i];
        }
        return sum;
    }

    public PotentialTable getRandomPreciseUtility(Random r) {
        PotentialTable t = new PotentialTable(this.getVariables());

        for (int i = 0; i < this.maxValues.length; i++) {

            double alpha = minValues[i];
            double beta = maxValues[i];

            double v = r.nextDouble() * (beta - alpha) + alpha;
            t.setValue(i, v);

        }

        return t;
    }

    public double getMeanISize() {
        double isize = 0;
        for (int i = 0; i < maxValues.length; i++) {
            isize += (maxValues[i] - minValues[i]);
        }

        isize = isize / maxValues.length;

        return isize;
    }

    public PotentialTable getRandomPreciseProbability(Random r) {
        PotentialTable t = new PotentialTable(this.getVariables());
        t.setHead(head);
        t.setTail(tail);

        Configuration confTail = new Configuration(tail);
        Configuration confHead;

        for (int y = 0; y < confTail.possibleValues(); y++) {
            confHead = new Configuration(head);

            double sum = 0;
            for (int x = 0; x < confHead.possibleValues() - 1; x++) {

                Configuration conf = new Configuration(confHead, confTail, new NodeList(this.getVariables()));
                int i = conf.getIndexInTable();
                double alpha = minValues[i];
                double beta = maxValues[i];
                double v = r.nextDouble() * (beta - alpha) + alpha;
                t.setValue(i, v);
                sum += v;
                confHead.nextConfiguration();
            }

            //Last value should sum 1
            Configuration conf = new Configuration(confHead, confTail, new NodeList(this.getVariables()));
            int i = conf.getIndexInTable();
            double v = 1 - sum;
            t.setValue(i, v);

            confHead.nextConfiguration();

            if (v < minValues[i] || v > maxValues[i]) {    //The potential is not included
                y--;
            } else {
                confTail.nextConfiguration();
            }

        }

        return t;
    }

    /**
     * Performs the sum-marginalization by considering only a subset of the
     * extreme points. That is, those were each of the weights is either
     * marginalized or minimized. NOTE It has been proved with an
     * counter-example that it is NOT ALWAYS RIGHT
     *
     * @param prob
     * @param util
     * @param n
     * @return
     */
    public static PotentialIntervalTable margWithSubsetOfExtremePoints(PotentialIntervalTable prob, PotentialIntervalTable util, FiniteStates n) {

        PotentialIntervalTable p = null;

        Vector v1 = prob.getVariables();
        Vector v2 = util.getVariables();
        Vector resVars = new Vector();
        Vector toRemove = new Vector();
        toRemove.add(n);

        for (int i = 0; i < v1.size(); i++) {
            resVars.add(v1.elementAt(i));
        }

        for (int i = 0; i < v2.size(); i++) {
            if (resVars.indexOf(v2.elementAt(i)) == -1) {
                resVars.add(v2.elementAt(i));
            }
        }

        NodeList combVars = new NodeList(resVars);

        resVars.remove(n);
        p = new PotentialIntervalTable(resVars);

        Configuration confX = new Configuration(resVars);

        //Determine upper bounds for each conf. in the final potential
        for (int x = 0; x < confX.possibleValues(); x++) {

            //Compute extreme points
            Vector<double[]> ext = new Vector<double[]>();

            //Points maximizing or minimizing w_i
            Configuration confI = new Configuration(toRemove);
            for (int i = 0; i < confI.possibleValues(); i++) {
                Configuration confIX = new Configuration(confX, confI, combVars);

                double pmax[] = new double[n.getNumStates()];
                double pmin[] = new double[n.getNumStates()];

                //compute normalization constant
                double kmax = 0;
                double kmin = 0;

                Configuration confJ = new Configuration(toRemove);
                for (int j = 0; j < confJ.possibleValues(); j++) {
                    Configuration confJX = new Configuration(confX, confJ, combVars);

                    System.out.println("prob " + prob.getMaxValue(confJX) + " " + prob.getMinValue(confJX));

                    if (i == j) {
                        kmax += prob.getMaxValue(confJX);
                        kmin += prob.getMinValue(confJX);

                    } else {
                        kmax += prob.getMinValue(confJX);
                        kmin += prob.getMaxValue(confJX);

                    }
                    confJ.nextConfiguration();
                }

                System.out.println("sumprob = " + kmax + " " + kmin);

                if (kmax != 0) {
                    kmax = 1 / kmax;
                }

                if (kmin != 0) {
                    kmin = 1 / kmin;
                }

                double sumMax = 0, sumMin = 0;
                //extreme points
                for (int j = 0; j < confJ.possibleValues(); j++) {
                    Configuration confJX = new Configuration(confX, confJ, combVars);

                    if (i == j) {
                        pmax[j] = prob.getMaxValue(confJX) * kmax;
                        pmin[j] = prob.getMinValue(confJX) * kmin;

                    } else {
                        pmin[j] = prob.getMaxValue(confJX) * kmin;
                        pmax[j] = prob.getMinValue(confJX) * kmax;

                    }

                    System.out.println(pmax[j] + " " + pmin[j]);
                    sumMax = sumMax + pmax[j];
                    sumMin = sumMin + pmin[j];

                    confJ.nextConfiguration();
                }

                System.out.println("sum weights: " + sumMax + " " + sumMin);

                ext.add(pmin);
                ext.add(pmax);
                System.out.println("\n");

                confI.nextConfiguration();
            }

            //Compute the upper and lower utility iterating over the extreme points
            double maxBeta = Double.NEGATIVE_INFINITY;
            double minAlpha = Double.POSITIVE_INFINITY;

            for (int k = 0; k < ext.size(); k++) {
                confI = new Configuration(toRemove);
                System.out.println("");
                double alpha = 0, beta = 0;
                double[] pk = ext.elementAt(k);
                for (int i = 0; i < confI.possibleValues(); i++) {
                    Configuration confIX = new Configuration(confX, confI, combVars);

                    System.out.print(pk[i] + " ");

                    alpha += util.getMinValue(confIX) * pk[i];
                    beta += util.getMaxValue(confIX) * pk[i];

                    confI.nextConfiguration();

                }

                if (alpha < minAlpha) {
                    minAlpha = alpha;
                }

                if (beta > maxBeta) {
                    maxBeta = beta;
                }

            }

            p.setMaxValue(confX, maxBeta);
            p.setMinValue(confX, minAlpha);
            System.out.println("maxminUtil=" + maxBeta + " " + minAlpha + "----");

            confX.nextConfiguration();
        }

        return p;
    }

    /**
     * Performs the sum-marginalization by considering only a subset of the
     * extreme points. That is, those were each of the weights is either
     * marginalized or minimized. NOTE It has been proved with an
     * counter-example that it is NOT ALWAYS RIGHT
     *
     * @param prob
     * @param util
     * @param n
     * @return
     */
    public static PotentialIntervalTable margWithExtremePoints(PotentialIntervalTable prob, PotentialIntervalTable util, FiniteStates n) {

        PotentialIntervalTable p = null;

        if (prob == null) {
            return (PotentialIntervalTable) util.addVariable(n);
        }

        Vector v1 = prob.getVariables();
        Vector v2 = util.getVariables();
        Vector resVars = new Vector();
        Vector toRemove = new Vector();
        toRemove.add(n);

        for (int i = 0; i < v1.size(); i++) {
            resVars.add(v1.elementAt(i));
        }

        for (int i = 0; i < v2.size(); i++) {
            if (resVars.indexOf(v2.elementAt(i)) == -1) {
                resVars.add(v2.elementAt(i));
            }
        }

        NodeList combVars = new NodeList(resVars);

        resVars.remove(n);
        p = new PotentialIntervalTable(resVars);

        Configuration confX = new Configuration(resVars);

        //Determine upper bounds for each conf. in the final potential
        for (int x = 0; x < confX.possibleValues(); x++) {

            //Compute extreme points
            Vector L = new Vector(), U = new Vector();
            //Vectors with lower un upper bounds
            Configuration confI = new Configuration(toRemove);
            for (int i = 0; i < confI.possibleValues(); i++) {
                Configuration confIX = new Configuration(confX, confI, combVars);
                U.add(prob.getMaxValue(confIX));
                L.add(prob.getMinValue(confIX));
                confI.nextConfiguration();
            }

            double[][] comb = VectorManipulator.getAllCombinations2(L, U);
            double[][] ext = new double[comb.length][comb[0].length];

            double sum[] = new double[comb.length];
            for (int i = 0; i < comb.length; i++) {
                sum[i] = 0;
                for (int k = 0; k < comb[i].length; k++) {
                    sum[i] += comb[i][k];
                }
                for (int k = 0; k < comb[i].length; k++) {
                    ext[i][k] = 0;
                    if (sum[i] != 0) {
                        ext[i][k] = comb[i][k] / sum[i];
                    }

                }
            }

            //Compute the upper and lower utility iterating over the extreme points
            double maxBeta = Double.NEGATIVE_INFINITY;
            double minAlpha = Double.POSITIVE_INFINITY;

            for (int k = 0; k < ext.length; k++) {

                confI = new Configuration(toRemove);

                double alpha = 0, beta = 0;
                double[] pk = ext[k];
                for (int i = 0; i < confI.possibleValues(); i++) {
                    Configuration confIX = new Configuration(confX, confI, combVars);

                    alpha += util.getMinValue(confIX) * pk[i];
                    beta += util.getMaxValue(confIX) * pk[i];

                    confI.nextConfiguration();

                }

                //       System.out.println("\nutil="+beta);
                if (alpha < minAlpha) {
                    minAlpha = alpha;
                }

                if (beta > maxBeta) {
                    maxBeta = beta;
                    //          System.out.println("\nSet new maxbeta"+maxBeta);
                }

                //       System.out.println("");
            }

            p.setMaxValue(confX, maxBeta);
            p.setMinValue(confX, minAlpha);
            //    System.out.println("maxminUtil="+maxBeta+" "+minAlpha+"\n\n----"); 

            confX.nextConfiguration();
        }

        return p;
    }

    public static PotentialIntervalTable margWithLinearProg(PotentialIntervalTable prob, PotentialIntervalTable util, FiniteStates n) {

        PotentialIntervalTable p = null;

      //  System.out.println("removal of " + n.getName() + " " + n.getNumStates());
        if (prob == null) {
            return (PotentialIntervalTable) util.addVariable(n);
        }

        Vector v1 = prob.getVariables();
        Vector v2 = util.getVariables();
        Vector resVars = new Vector();
        Vector toRemove = new Vector();
        toRemove.add(n);

        for (int i = 0; i < v1.size(); i++) {
            resVars.add(v1.elementAt(i));
        }

        for (int i = 0; i < v2.size(); i++) {
            if (resVars.indexOf(v2.elementAt(i)) == -1) {
                resVars.add(v2.elementAt(i));
            }
        }

        NodeList combVars = new NodeList(resVars);

        resVars.remove(n);
        p = new PotentialIntervalTable(resVars);
        int nStates = ((FiniteStates) n).getNumStates();

        Configuration confX = new Configuration(resVars);

        //Determine upper bounds for each conf. in the final potential
        for (int x = 0; x < confX.possibleValues(); x++) {
            try {

                double L[] = new double[nStates];
                double U[] = new double[nStates];

                double Lc[] = new double[nStates];
                double Uc[] = new double[nStates];

                //Vectors with lower un upper bounds
                Configuration confI = new Configuration(toRemove);
                for (int i = 0; i < confI.possibleValues(); i++) {
                    Configuration confIX = new Configuration(confX, confI, combVars);
                    U[i] = (prob.getMaxValue(confIX));
                    L[i] = (prob.getMinValue(confIX));
                    Uc[i] = (util.getMaxValue(confIX));
                    Lc[i] = (util.getMinValue(confIX));
                    confI.nextConfiguration();
                }

                double[] newInterval = solveRLP(L, U, Lc, Uc, 0, 1);

                p.setMaxValue(confX, newInterval[1]);
                p.setMinValue(confX, newInterval[0]);

                confX.nextConfiguration();
            } catch (LpSolveException ex) {
                Logger.getLogger(PotentialIntervalTable.class.getName()).log(Level.SEVERE, null, ex);
                System.exit(1);
            }

        }

        return p;
    }

    public static double[] solveRLP(double[] L, double[] U, double[] Lc, double[] Uc, double Smin, double Smax) throws LpSolveException {
        double[] out = new double[2];

        if ((L.length != U.length) || (Lc.length != Uc.length) || (L.length != Uc.length)) {
            System.out.println("ERROR: arrays of different sizes in solveLP");
            System.exit(1);
        }
        
        
        //Updates Smax and Smin if needed
        double Smax2 = 0, Smin2 = 0;
        for(int i=0; i<L.length; i++) {
            Smax2 += U[i];
            Smin2 += L[i];
        }
        Smax = Math.min(Smax, Smax2);
        Smin = Math.max(Smin, Smin2);
        

        /*      boolean zeroU = true;
         for (int i = 0; i < L.length; i++) {
         zeroU = zeroU && (L[i] == 0);
         }
         if (zeroU) {
         out[0] = 0;
         out[1] = 0;
         return out;
         }

         */
        int nStates = L.length;
        LpSolve solver = LpSolve.makeLp(0, nStates + 1);

        if (generateStatistics) {
            getStatistics().addLPcall(nStates + 1);

        }
        solver.setVerbose(2); //Output messages: only errors (if any)
//
// solver.setVerbose(4);

        //Constraints due to the bounds
        for (int i = 0; i < nStates; i++) {
            double ctr[] = new double[nStates + 2];

            //Upper constraint
            for (int j = 0; j < nStates + 2; j++) {
                ctr[j] = 0;
            }

            ctr[i + 1] = 1;
            ctr[nStates + 1] = -U[i];
            solver.addConstraint(ctr, LpSolve.LE, 0.0);

            //Lower constraint
            for (int j = 0; j < nStates + 2; j++) {
                ctr[j] = 0;
            }

            ctr[i + 1] = -1;
            ctr[nStates + 1] = L[i];
            solver.addConstraint(ctr, LpSolve.LE, 0.0);

        }

        //Constraints due to the sum 1
        double ctr[] = new double[nStates + 2];
        for (int j = 1; j < nStates + 1; j++) {
            ctr[j] = 1;
        }
        ctr[0] = 0;
        ctr[nStates + 1] = 0;
        solver.addConstraint(ctr, LpSolve.EQ, 1.0);

        ctr = new double[nStates + 2];

        //Constraint Smax
        for (int j = 0; j < nStates + 1; j++) {
            ctr[j] = 1;
        }

        ctr[nStates + 1] = -Smax;
        solver.addConstraint(ctr, LpSolve.LE, 0.0);

        //Constraint Smin
        for (int j = 0; j < nStates + 1; j++) {
            ctr[j] = -1;
        }

        ctr[nStates + 1] = Smin;
        solver.addConstraint(ctr, LpSolve.LE, 0.0);

        //solver.setUpbo(nStates, nStates);
        solver.setLowbo(nStates, 0);
        for (int i = 1; i < nStates + 1; i++) {
            solver.setUpbo(i, 1);
            solver.setLowbo(i, 0);
        }

        double objMax[] = new double[nStates + 2];
        double objMin[] = new double[nStates + 2];

        //objective functions
        for (int i = 0; i < nStates; i++) {

            objMax[0] = 0;
            objMax[nStates] = 0;

            objMin[0] = 0;
            objMin[nStates] = 0;

            for (int j = 1; j < nStates + 1; j++) {
                objMax[j] = Uc[j - 1];
                objMin[j] = Lc[j - 1];
            }

        }

        //solver.printLp();
        // solve the problem
        solver.setMaxim();
        solver.setObjFn(objMax);

    //    solver.printLp();
        solver.solve();

        double maxValue = solver.getObjective();

        solver.setMinim();
        solver.setObjFn(objMin);
        //      solver.printLp();
        solver.solve();

        double minValue = solver.getObjective();

        out[0] = minValue;
        out[1] = maxValue;

   //     solver.deleteLp();
        return out;

    }

    
    public static double[] solveRLP(double[] L, double[] U, double[] Lc, double[] Uc, double[] Ldc, double[] Udc, double Smin, double Smax) throws LpSolveException {
        double[] out = new double[2];

        if ((L.length != U.length) || (Lc.length != Uc.length) || (L.length != Uc.length)) {
            System.out.println("ERROR: arrays of different sizes in solveLP");
            System.exit(1);
        }
        
        if ((Ldc.length != Udc.length) || (L.length != Udc.length)) {
            System.out.println("ERROR: arrays of different sizes in solveLP");
            System.exit(1);
        }
        
        
        
        //Updates Smax and Smin if needed
        double Smax2 = 0, Smin2 = 0;
        for(int i=0; i<L.length; i++) {
            Smax2 += U[i];
            Smin2 += L[i];
        }
        Smax = Math.min(Smax, Smax2);
        Smin = Math.max(Smin, Smin2);
        

        /*      boolean zeroU = true;
         for (int i = 0; i < L.length; i++) {
         zeroU = zeroU && (L[i] == 0);
         }
         if (zeroU) {
         out[0] = 0;
         out[1] = 0;
         return out;
         }

         */
        int nStates = L.length;
        LpSolve solver = LpSolve.makeLp(0, nStates + 1);

        if (generateStatistics) {
            getStatistics().addLPcall(nStates + 1);

        }
        solver.setVerbose(2); //Output messages: only errors (if any)
//
 //solver.setVerbose(6);

        //Constraints due to the bounds
        for (int i = 0; i < nStates; i++) {
            double ctr[] = new double[nStates + 2];

            //Upper constraint
            for (int j = 0; j < nStates + 2; j++) {
                ctr[j] = 0;
            }

            ctr[i + 1] = 1;
            ctr[nStates + 1] = -U[i];
            solver.addConstraint(ctr, LpSolve.LE, 0.0);

            //Lower constraint
            for (int j = 0; j < nStates + 2; j++) {
                ctr[j] = 0;
            }

            ctr[i + 1] = -1;
            ctr[nStates + 1] = L[i];
            solver.addConstraint(ctr, LpSolve.LE, 0.0);

        }

        double ctr[] = null;
        

        ctr = new double[nStates + 2];
        //Constraint Smax
        for (int j = 0; j < nStates + 1; j++) {
            ctr[j] = 1;
        }

        ctr[nStates + 1] = -Smax;
        solver.addConstraint(ctr, LpSolve.LE, 0.0);

        //Constraint Smin
        for (int j = 0; j < nStates + 1; j++) {
            ctr[j] = -1;
        }

        ctr[nStates + 1] = Smin;
        solver.addConstraint(ctr, LpSolve.LE, 0.0);

     
        
        //solver.setUpbo(nStates, nStates);
        solver.setLowbo(nStates, 0);
        for (int i = 1; i < nStates + 1; i++) {
       //     solver.setUpbo(i, 1);
            solver.setLowbo(i, 0);
        }

        double objMax[] = new double[nStates + 2];
        double objMin[] = new double[nStates + 2];

        //objective functions
        for (int i = 0; i < nStates; i++) {

            objMax[0] = 0;
            objMax[nStates] = 0;

            objMin[0] = 0;
            objMin[nStates] = 0;

            for (int j = 1; j < nStates + 1; j++) {
                objMax[j] = Uc[j - 1];
                objMin[j] = Lc[j - 1];
            }

        }



        
        //Constraints due to the sum 1 (with denom. coeficients)
        // maximizing (take the lower)
        ctr = new double[nStates + 2];
        for (int j = 1; j < nStates + 1; j++) {
            ctr[j] = Ldc[j-1];
        }
        ctr[0] = 0;
        ctr[nStates + 1] = 0;
        solver.addConstraint(ctr, LpSolve.EQ, 1.0);

        
        
        solver.setMaxim();
        solver.setObjFn(objMax);
      //  solver.printLp();
        solver.solve();       
        
        

        double maxValue = solver.getObjective();
        
         //////////////// Minimize 
        
        solver.delConstraint(solver.getNrows()); //Deletes previous constraint
        
             
        //Constraints due to the sum 1 (with denom. coeficients)
        // minimizing (take the upper)
        ctr = new double[nStates + 2];
        for (int j = 1; j < nStates + 1; j++) {
            ctr[j] = Udc[j-1];
        }
        ctr[0] = 0;
        ctr[nStates + 1] = 0;
        solver.addConstraint(ctr, LpSolve.EQ, 1.0);

        
        
        
        
        
        
        

        solver.setMinim();
        solver.setObjFn(objMin);
     //   solver.printLp();
        solver.solve();

        double minValue = solver.getObjective();

        out[0] = minValue;
        out[1] = maxValue;

   //     solver.deleteLp();
        return out;

    }

    
    
    
    
    
    
    
    
    public static PotentialIntervalTable margUtilWithLP(PotentialIntervalTable probYAB, PotentialIntervalTable probCEY, PotentialIntervalTable utilYG, FiniteStates Y, boolean sumConstr, boolean unity) {

        PotentialIntervalTable p = null;
        int yStates = Y.getNumStates();
        Vector varsACBE, varsAC, varsACBEG;

        //Preliminary checks
        if (probYAB == null && probCEY == null) {
            return (PotentialIntervalTable) utilYG.addVariable(Y);
        } else if (probYAB == null && probCEY != null) {
            System.err.println("ERROR in margUtilWithLP: if there is more than probabilities, Y should be in the head in at least one potential");
            System.exit(1);
        }

        //Combines both utilities
        PotentialIntervalTable probYACBE = null;
        if (probCEY != null) {
            probYACBE = (PotentialIntervalTable) probYAB.combine(probCEY);
            probYACBE.makeReachable();
        } else {
            probYACBE = probYAB;

        }

        //
        varsACBE = new Vector();
        for (int i = 0; i < probYACBE.getVariables().size(); i++) {
            FiniteStates n = (FiniteStates) probYACBE.getVariables().elementAt(i);
            if (!n.equals(Y)) {
                varsACBE.add(n);
            }
        }

        varsAC = new Vector();
        for (int i = 0; i < probYACBE.getHead().size(); i++) {
            FiniteStates n = (FiniteStates) probYACBE.getHead().elementAt(i);
            if (!n.equals(Y)) {
                varsAC.add(n);
            }
        }

        varsACBEG = VectorManipulator.union(varsACBE, utilYG.getVariables());
        varsACBEG.remove(Y);

        Vector<Double> Smax = null;
        Vector<Double> Smin = null;
        Configuration confACBE = null;

        //Constraints with Smin Smax
        if (sumConstr && !unity) {
            Smax = new Vector<Double>();
            Smin = new Vector<Double>();

            confACBE = new Configuration(varsACBE);

            for (int i = 0; i < confACBE.possibleValues(); i++) {

                Smax.add(1.0);
                Smin.add(1.0);

                double realUpSum = 0, realLowSum = 0;

                Configuration confAC = new Configuration(varsAC);
                Configuration confY = new Configuration();
                confY.insert(Y, 0);

                for (int j = 0; j < confAC.possibleValues(); j++) {
                    for (int y = 0; y < Y.getNumStates(); y++) {
                        if (!confAC.isCompatibleWeak(confACBE)) {

                            Configuration confYACBE = new Configuration(confY, confAC);
                            confYACBE = new Configuration(confYACBE, confACBE);

                            Smax.set(i, Smax.elementAt(i) - probYACBE.getMinValue(confYACBE));
                            Smin.set(i, Smin.elementAt(i) - probYACBE.getMaxValue(confYACBE));

                        } 
                        confY.nextConfiguration();
                    }
                    confAC.nextConfiguration();
                }

     //      System.out.println(Smin.elementAt(i)+"---"+Smax.elementAt(i)+"~~"+realLowSum+", "+realUpSum); //~~
                confACBE.nextConfiguration();
            }
        }

        //Call to LP solver
        p = new PotentialIntervalTable(varsACBEG);

        Configuration confACBEG = new Configuration(varsACBEG);

        for (int j = 0; j < confACBEG.possibleValues(); j++) {

            try {

                double L[] = new double[yStates];
                double U[] = new double[yStates];

                double Lc[] = new double[yStates];
                double Uc[] = new double[yStates];

                //Vectors with lower un upper bounds
                Configuration confY = new Configuration();
                confY.insert(Y, 0);

                for (int i = 0; i < confY.possibleValues(); i++) {

                    Configuration confYAB = new Configuration(confY, confACBEG, new NodeList(probYACBE.getVariables()));
                    Configuration confCEY = new Configuration(confY, confACBEG, new NodeList(utilYG.getVariables()));

                    U[i] = (probYACBE.getMaxValue(confYAB));
                    L[i] = (probYACBE.getMinValue(confYAB));
                    Uc[i] = (utilYG.getMaxValue(confCEY));
                    Lc[i] = (utilYG.getMinValue(confCEY));

                    confY.nextConfiguration();
                }

                confACBE = new Configuration(confACBEG, new NodeList(varsACBE));

                double Smin_acbe = 0;
                double Smax_acbe = 1;

                if (sumConstr && !unity) {
                    Smin_acbe = Smin.elementAt(confACBE.getIndexInTable());
                    Smax_acbe = Smax.elementAt(confACBE.getIndexInTable());
                }

                double[] newInterval = null;

                if (!unity) {
                    newInterval = PotentialIntervalTable.solveRLP(L, U, Lc, Uc, Smin_acbe, Smax_acbe);
                } else {
                    newInterval = PotentialIntervalTable.solveLP(L, U, Lc, Uc, 1, 1);

                }

                p.setMaxValue(confACBEG, newInterval[1]);
                p.setMinValue(confACBEG, newInterval[0]);

                confACBEG.nextConfiguration();

            } catch (LpSolveException ex) {
                Logger.getLogger(PotentialIntervalTable.class.getName()).log(Level.SEVERE, null, ex);
                System.exit(1);
            }

        }

        //p=   (PotentialIntervalTable) probYAB.combine(probCEY).combine(utilYG).addVariable(Y).divide(probYAB.combine(probCEY).addVariable(Y));
        return p;

    }

    public static PotentialIntervalTable margProbWithLP(PotentialIntervalTable probYAB, PotentialIntervalTable probCEY, FiniteStates Y, boolean sumConstr) {

        PotentialIntervalTable p = null;

        int yStates = Y.getNumStates();

        //Computes the reestriction  of sum_Y P(Y, a | b) \in [minSum, maxSum]       
        Vector varsAB = new Vector();
        Vector varYAB = probYAB.getVariables();
        for (int i = 0; i < varYAB.size(); i++) {
            FiniteStates n = (FiniteStates) varYAB.elementAt(i);
            if (!n.equals(Y)) {
                varsAB.add(n);
            }
        }

        Vector varsA = new Vector();
        for (int i = 0; i < probYAB.getHead().size(); i++) {
            FiniteStates n = (FiniteStates) probYAB.getHead().elementAt(i);
            if (!n.equals(Y)) {
                varsA.add(n);
            }
        }

        Vector varsCEY = null;
        if (probCEY != null) {
            varsCEY = probCEY.getVariables();
        } else {
            varsCEY = new Vector();
        }

        Vector varsABCE = VectorManipulator.union(varsAB, varsCEY);
        varsABCE.remove(Y);

        Configuration confAB = new Configuration(varsAB);

        Vector<Double> Smin = new Vector();   // lower limit of the sum for a give AB
        Vector<Double> Smax = new Vector();   // upper limit of the sum for a give AB

        for (int i = 0; i < confAB.possibleValues(); i++) {

            Configuration confY = new Configuration();
            confY.insert(Y, 0);
            Configuration confA = new Configuration(varsA);
            Configuration confB = new Configuration(confAB, new NodeList(probYAB.getTail()));

            //Sets limits to 1
            Smin.add(1.0);
            Smax.add(1.0);

            if (probYAB.getHead().size() > 1) { // An optimization

                for (int j = 0; j < confY.possibleValues(); j++) {
                    for (int k = 0; k < confA.possibleValues(); k++) {

                        if (!confA.isCompatibleWeak(confAB)) {
                            Configuration confYAB = new Configuration(confY, confA);
                            confYAB = new Configuration(confYAB, confB);

                            Smin.set(i, Smin.elementAt(i) - probYAB.getMaxValue(confYAB));
                            Smax.set(i, Smax.elementAt(i) - probYAB.getMinValue(confYAB));

                        }
                        confA.nextConfiguration();
                    }
                    confY.nextConfiguration();
                }

                confAB.nextConfiguration();
            }

        }

        p = new PotentialIntervalTable(varsABCE);

        //Write call to LP problem
        Configuration confABCE = new Configuration(varsABCE);

        for (int j = 0; j < confABCE.possibleValues(); j++) {
            try {

                double L[] = new double[yStates];
                double U[] = new double[yStates];

                double Lc[] = new double[yStates];
                double Uc[] = new double[yStates];

                //Vectors with lower un upper bounds
                Configuration confY = new Configuration();
                confY.insert(Y, 0);

                for (int i = 0; i < confY.possibleValues(); i++) {

                    Configuration confYAB = new Configuration(confY, confABCE, new NodeList(probYAB.getVariables()));
                    Configuration confCEY = new Configuration(confY, confABCE, new NodeList(varsCEY));

                    U[i] = (probYAB.getMaxValue(confYAB));
                    L[i] = (probYAB.getMinValue(confYAB));

                    if (!varsCEY.isEmpty()) {
                        Uc[i] = (probCEY.getMaxValue(confCEY));
                        Lc[i] = (probCEY.getMinValue(confCEY));
                    } else {
                        Uc[i] = 1;
                        Lc[i] = 1;

                    }
                    confY.nextConfiguration();
                }

                confAB = new Configuration(confABCE, new NodeList(varsAB));

                double Smin_ab = Double.NEGATIVE_INFINITY;
                double Smax_ab = Double.POSITIVE_INFINITY;

                if (sumConstr) {
                    Smin_ab = Smin.elementAt(confAB.getIndexInTable());
                    Smax_ab = Smax.elementAt(confAB.getIndexInTable());
                }

                double[] newInterval = PotentialIntervalTable.solveLP(L, U, Lc, Uc, Smin_ab, Smax_ab);

                p.setMaxValue(confABCE, newInterval[1]);
                p.setMinValue(confABCE, newInterval[0]);

                confABCE.nextConfiguration();

            } catch (LpSolveException ex) {
                Logger.getLogger(PotentialIntervalTable.class.getName()).log(Level.SEVERE, null, ex);
                System.exit(1);
            }
        }

        Vector newHead = Potential.getCombinationHead(probYAB, probCEY);
        newHead.remove(Y);
        p.setHead(newHead);

        Vector newTail = Potential.getCombinationTail(probYAB, probCEY);
        newTail.remove(Y);
        p.setTail(newTail);

//        p = (PotentialIntervalTable) probYAB.combine(probCEY).addVariable(Y);
        return p;
    }

    public static double[] solveLP(double[] L, double[] U, double[] Lc, double[] Uc, double minSum, double maxSum) throws LpSolveException {
        double[] out = new double[2];

        if ((L.length != U.length) || (Lc.length != Uc.length) || (L.length != Uc.length)) {
            System.out.println("ERROR: arrays of different sizes in solveLP");
            System.exit(1);
        }
        
        //Updates Smax and Smin if needed
        double maxSum2 = 0, minSum2 = 0;
        for(int i=0; i<L.length; i++) {
            maxSum2 += U[i];
            minSum2 += L[i];
        }
        maxSum = Math.min(maxSum, maxSum2);
        minSum = Math.max(minSum, minSum2);
        

        int nStates = L.length;
        LpSolve solver = LpSolve.makeLp(0, nStates);
        solver.setVerbose(2);
        solver.setVerbose(6);

        //Constraints due to bounds
        for (int i = 0; i < nStates; i++) {
            double ctr[] = new double[nStates + 1];
            //Upper constraint
            for (int j = 0; j < nStates + 1; j++) {
                ctr[j] = 0;
            }
            ctr[i + 1] = 1;
            solver.addConstraint(ctr, LpSolve.LE, U[i]);

            //Lower constraint
            for (int j = 0; j < nStates + 1; j++) {
                ctr[j] = 0;
            }
            ctr[i + 1] = 1;
            solver.addConstraint(ctr, LpSolve.GE, L[i]);
        }

        //Sum constraints
        if (maxSum != Double.POSITIVE_INFINITY && minSum != Double.NEGATIVE_INFINITY) {
            double ctr[] = new double[nStates + 1];
            //Upper constraint
            for (int j = 0; j < nStates + 1; j++) {
                ctr[j] = 1;
            }
            solver.addConstraint(ctr, LpSolve.LE, maxSum);
            solver.addConstraint(ctr, LpSolve.GE, minSum);
        }

        //Bounds [0,1]
        //   solver.setLowbo(nStates, 0);
        for (int i = 1; i < nStates + 1; i++) {
            solver.setUpbo(i, 1);
            solver.setLowbo(i, 0);
        }
   //  solver.printLp();   

        double objMax[] = new double[nStates + 1];
        double objMin[] = new double[nStates + 1];

        objMax[0] = 0;
        objMin[0] = 0;

        for (int j = 1; j < nStates + 1; j++) {
            objMax[j] = Uc[j - 1];
            objMin[j] = Lc[j - 1];
        }

        solver.setMinim();
        solver.setObjFn(objMin);
        //     solver.printLp();
        solver.solve();
        double minValue = solver.getObjective();
//        System.out.println("minvalue="+minValue);

        solver.setMaxim();
        solver.setObjFn(objMax);
 //            solver.printLp();
        solver.solve();
        double maxValue = solver.getObjective();
  //      System.out.println("minvalue="+maxValue);
        
   //     solver.printSolution(0);

        out[0] = minValue;
        out[1] = maxValue;

        return out;

    }

    public static PotentialIntervalTable margWithExtremePoints_slow(PotentialIntervalTable prob, PotentialIntervalTable util, FiniteStates n) {

        PotentialIntervalTable p = null;

        if (prob == null) {
            return (PotentialIntervalTable) util.addVariable(n);
        }

        Vector v1 = prob.getVariables();
        Vector v2 = util.getVariables();
        Vector resVars = new Vector();
        Vector toRemove = new Vector();
        toRemove.add(n);

        for (int i = 0; i < v1.size(); i++) {
            resVars.add(v1.elementAt(i));
        }

        for (int i = 0; i < v2.size(); i++) {
            if (resVars.indexOf(v2.elementAt(i)) == -1) {
                resVars.add(v2.elementAt(i));
            }
        }

        NodeList combVars = new NodeList(resVars);

        resVars.remove(n);
        p = new PotentialIntervalTable(resVars);

        Configuration confX = new Configuration(resVars);

        //Determine upper bounds for each conf. in the final potential
        for (int x = 0; x < confX.possibleValues(); x++) {

            //Compute extreme points
            Vector<double[]> ext = new Vector<double[]>();

            Vector L = new Vector(), U = new Vector();
            //Vectors with lower un upper bounds
            Configuration confI = new Configuration(toRemove);
            for (int i = 0; i < confI.possibleValues(); i++) {
                Configuration confIX = new Configuration(confX, confI, combVars);
                U.add(prob.getMaxValue(confIX));
                L.add(prob.getMinValue(confIX));
                confI.nextConfiguration();
            }

            Vector comb = VectorManipulator.getAllCombinations(U, L);

            //loop of each combination
            for (int i = 0; i < comb.size(); i++) {
                Vector Ci = ((Vector) comb.elementAt(i));

                //    VectorManipulator.print(Ci);
                double k = VectorManipulator.sumValues(Ci);

                double e[] = new double[Ci.size()];
                for (int j = 0; j < Ci.size(); j++) {
                    if (k != 0) {
                        e[j] = ((Double) Ci.elementAt(j)).doubleValue() / k;
                    } else {
                        e[j] = 0;
                    }

                }
                ext.add(e);

            }

            //Compute the upper and lower utility iterating over the extreme points
            double maxBeta = Double.NEGATIVE_INFINITY;
            double minAlpha = Double.POSITIVE_INFINITY;

            for (int k = 0; k < ext.size(); k++) {

                //         System.out.println("\n"+(k+1));
                //       VectorManipulator.print((Vector) comb.elementAt(k));
                confI = new Configuration(toRemove);

                double alpha = 0, beta = 0;
                double[] pk = ext.elementAt(k);
                for (int i = 0; i < confI.possibleValues(); i++) {
                    Configuration confIX = new Configuration(confX, confI, combVars);

                    //           System.out.print(pk[i]+" ");
                    alpha += util.getMinValue(confIX) * pk[i];
                    beta += util.getMaxValue(confIX) * pk[i];

                    confI.nextConfiguration();

                }

                //       System.out.println("\nutil="+beta);
                if (alpha < minAlpha) {
                    minAlpha = alpha;
                }

                if (beta > maxBeta) {
                    maxBeta = beta;
                    //          System.out.println("\nSet new maxbeta"+maxBeta);
                }

                //       System.out.println("");
            }

            p.setMaxValue(confX, maxBeta);
            p.setMinValue(confX, minAlpha);
            //    System.out.println("maxminUtil="+maxBeta+" "+minAlpha+"\n\n----"); 

            confX.nextConfiguration();
        }

        return p;
    }

    /**
     * Performs the sum-marginalization by considering only one of the extreme
     * points. That is, that extreme point with a higher prob*util It has been
     * proved with an counter-example that it is NOT ALWAYS RIGHT
     *
     * @param prob
     * @param util
     * @param n
     * @return
     */
    public static PotentialIntervalTable margWithOneExtremePoint(PotentialIntervalTable prob, PotentialIntervalTable util, FiniteStates n) {

        PotentialIntervalTable p = null;

        Vector v1 = prob.getVariables();
        Vector v2 = util.getVariables();
        Vector resVars = new Vector();
        Vector toRemove = new Vector();
        toRemove.add(n);

        for (int i = 0; i < v1.size(); i++) {
            resVars.add(v1.elementAt(i));
        }

        for (int i = 0; i < v2.size(); i++) {
            if (resVars.indexOf(v2.elementAt(i)) == -1) {
                resVars.add(v2.elementAt(i));
            }
        }

        NodeList combVars = new NodeList(resVars);

        resVars.remove(n);
        p = new PotentialIntervalTable(resVars);

        Configuration confX = new Configuration(resVars);

        //Determine upper bounds for each conf. in the final potential
        for (int x = 0; x < confX.possibleValues(); x++) {

            //Determine j
            double max_prod = Double.NEGATIVE_INFINITY;
            int j = -1;

            Configuration confY = new Configuration(toRemove);
            for (int y = 0; y < confY.possibleValues(); y++) {
                Configuration confXY = new Configuration(confX, confY, combVars);

                //    System.out.println("  " + prob.getMaxValue(confXY) + "*" + util.getMaxValue(confXY));
                double prod = prob.getMaxValue(confXY) * util.getMaxValue(confXY);
                if (prod > max_prod) {
                    max_prod = prod;
                    j = y;
                }
                confY.nextConfiguration();
            }

            //Determine normalization constant  and weights           
            double k = 0;
            confY = new Configuration(toRemove);
            double[] w = new double[confY.possibleValues()];
            for (int y = 0; y < confY.possibleValues(); y++) {
                Configuration confXY = new Configuration(confX, confY, combVars);

                if (y == j) {
                    w[y] = prob.getMaxValue(confXY);

                } else {
                    w[y] += prob.getMinValue(confXY);
                }
                k += w[y];
                confY.nextConfiguration();
            }
            k = 1 / k;

    //        System.out.println(w[0] + " " + w[1] + " " + w[2] + " " + k);
            //Compute the upper boud          
            double beta = 0;
            confY = new Configuration(toRemove);
            for (int y = 0; y < confY.possibleValues(); y++) {
                Configuration confXY = new Configuration(confX, confY, combVars);

                beta += w[y] * util.getMaxValue(confXY);
                confY.nextConfiguration();
            }

            beta = beta * k;

            p.setMaxValue(confX, beta);
            confX.nextConfiguration();

        }

        //Determine the lower bounds
        for (int x = 0; x < confX.possibleValues(); x++) {

            //Determine j
            double min_prod = Double.POSITIVE_INFINITY;
            int j = -1;

            Configuration confY = new Configuration(toRemove);
            for (int y = 0; y < confY.possibleValues(); y++) {
                Configuration confXY = new Configuration(confX, confY, combVars);

                //           System.out.println("  " + prob.getMaxValue(confXY) + "*" + util.getMaxValue(confXY));
                double prod = prob.getMaxValue(confXY) * util.getMinValue(confXY);
                if (prod < min_prod) {
                    min_prod = prod;
                    j = y;
                }
                confY.nextConfiguration();
            }

            //Determine normalization constant  and weights           
            double k = 0;
            confY = new Configuration(toRemove);
            double[] w = new double[confY.possibleValues()];
            for (int y = 0; y < confY.possibleValues(); y++) {
                Configuration confXY = new Configuration(confX, confY, combVars);

                if (y == j) {
                    w[y] = prob.getMaxValue(confXY);

                } else {
                    w[y] += prob.getMinValue(confXY);
                }
                k += w[y];
                confY.nextConfiguration();
            }
            k = 1 / k;

  //          System.out.println(w[0] + " " + w[1] + " " + w[2] + " " + k);
            //Compute the lower bound          
            double alpha = 0;
            confY = new Configuration(toRemove);
            for (int y = 0; y < confY.possibleValues(); y++) {
                Configuration confXY = new Configuration(confX, confY, combVars);
                alpha += w[y] * util.getMinValue(confXY);
                confY.nextConfiguration();
            }

            alpha = alpha * k;

            p.setMinValue(confX, alpha);
            confX.nextConfiguration();

        }

        return p;

    }

    public PotentialTable getTableWithTransparentVariable(String transparentName) {

        PotentialTable tab;

        Vector vars = new Vector();
        for (int i = 0; i < variables.size(); i++) {
            vars.addElement(variables.elementAt(i));
        }

        FiniteStates T = new FiniteStates();
        T.setName(transparentName);
        T.setNumStates(2);
        T.setTransparency(FiniteStates.TRANSPARENT);
        vars.add(T);

        tab = new PotentialTable(vars);

        Configuration conf = new Configuration(vars);
        for (int i = 0; i < conf.possibleValues(); i++) {

            double v;
            if (conf.getValue(T) == 0) {
                v = getMinValue(conf);
            } else {
                v = getMaxValue(conf);
            }

            tab.setValue(conf, v);

            conf.nextConfiguration();
        }

        return tab;

    }

    public void fixBounds(boolean isProb) {

        for (int i = 0; i < maxValues.length; i++) {

            if (Math.abs(minValues[i] - maxValues[i]) < 0.001) {
                maxValues[i] = Math.max(minValues[i], maxValues[i]);
                minValues[i] = maxValues[i];
            } else if (minValues[i] > maxValues[i]) {
                double aux = minValues[i];
                minValues[i] = maxValues[i];
                maxValues[i] = aux;

                /*
                 * if(isProb && minValues[i]<0.0)
                 minValues[i]=0.0;
                 */
            }
        }
    }

    public static boolean[] areUndominated(double[] L, double[] U) {

        if (L.length != U.length) {
            System.out.println("ERROR checking undominated alternatives");
            System.exit(-1);
        }

        boolean UD[] = new boolean[L.length];

        for (int i = 0; i < UD.length; i++) {
            boolean undom = true;
            for (int j = 0; j < UD.length; j++) {
                if (i != j && U[i] < L[j]) {
                    undom = false;
                }
            }
            UD[i] = undom;
        }

        return UD;

    }

}
