/* PropagationStatistics */

package elvira.tools;

import elvira.Node;
import java.io.*;
import java.util.Vector;
import elvira.potential.Potential;
import elvira.potential.PotentialTree;
import elvira.potential.PotentialTable;
import elvira.potential.binaryprobabilitytree.PotentialBPTree;

/**
 * Class <code>PropagationStatistics</code>.
 * Contains some interesting information in order to evaluate
 * the propagation carried out.
 *
 * @since 11/10/2000
 */

public class PropagationStatistics {

/**
 * Time spent in the propagation.
 */
private double time;

/**
 * Statistics about the join tree.
 */
private JoinTreeStatistics JTStat;

/**
 * These two values are necesary in exact partial abductive inference,
 * because the join tree is modified.
 */
private double JTExtraSize;
private double JTInitialSize;

/**
 * Vector to add the operations needed to evaluate a network
 * with elimination methods
 */

private Vector operations;

/**
 * Vector to store the sizes of the network during the
 * evaluation of the network with the elimination methods
 */

private Vector sizes;
private Vector numberNodes;

/**
 * Vector to store the times of computation until completion
 * of each operation
 */

private Vector times;

/**
 * Vector with a measure of importance for the variables of
 * each of the decisions
 */

private Vector explanations;

/**
 * Potential to store the final expected utility for
 * influence diagrams evaluation
 */

private Potential finalExpectedUtility = null;

/**
 * To store the name of the elvira file used in this
 * propagation
 */

protected String fileName;

/**
 * Vector with the sizes of decision trees
 */
protected Vector decSizes;

/**
 * Error of the initial Utitliy
 */
protected double initalUtilityError;


/**
 * Size of the initial utility potential
 */

protected long initialUtilitySize;
protected long initialUtilityNodes;


/**
 * Vector to store the utility potentials
 */
protected Vector utilityHistory;


protected Vector totalSizeHistory;

private Vector<Node> elimOrder;

private Vector operationSize;

private long initialPotentialsNodes;

private long numMultiplications;
private long numDivisions;
private long numSums;
private long numSumMarg;
private long numMaxMarg;

private long numProbBarren;

Vector sumMargTime;


Vector initialProbError;

private long numConstraints = 0;
private long numAppliedConstraints = 0;

private double LPcalls = 0;
private double LPsizes = 0;
private double maxLPsize = 0;


private long probBarren = 0;
private long singletons = 0;


/**
 * Constructor.
 */

public PropagationStatistics() {

  time = 0.0;
  JTStat = new JoinTreeStatistics();
  JTExtraSize = 0.0;
  JTInitialSize = 0.0;

  // Initialize the vector of operations and sizes

  operations=new Vector();
  sizes=new Vector();
  numberNodes=new Vector();
  times=new Vector();
  explanations=new Vector();
  decSizes= new Vector();
  
  utilityHistory = new Vector();
  totalSizeHistory = new Vector();
  elimOrder = new Vector<Node>();
  operationSize = new Vector();
  
  initialPotentialsNodes = 0;
  numMultiplications = 0;
  numDivisions = 0;
  numSums = 0;
  numMaxMarg=0;
  numSumMarg=0;
  numProbBarren=0;
  initialProbError = new Vector();
  
  sumMargTime = new Vector();
  
  
}


/**
 * Access methods
 */


/**
 * Sets the time spent in the propagation.
 * @param t the time.
 */

public void setTime(double t) {

  time = t;
}


public void addCurrentTime() {
    this.addTime(System.currentTimeMillis());
}


/**
 * Sets the statistics about the join tree.
 * @param s the statistics.
 */

public void setJTStat(JoinTreeStatistics s) {

  JTStat = s;
}


/**
 * Sets the initial size.
 * @param s new size.
 */

public void setJTInitialSize(double s) {

  JTInitialSize = s;
}


/**
 * Sets the extra size.
 * @param s new size.
 */

public void setJTExtraSize(double s) {

  JTExtraSize = s;
}


/**
 * Gets the time spent in the propagation.
 * @return the time.
 */

public double getTime( ) {

  return time;
}


/**
 * Gets the statistics about the join tree.
 * @return the statistics.
 */

public JoinTreeStatistics getJTStat(){
  return JTStat;
}


/**
 * Gets the final expected Utility.
 */

public Potential getFinalExpectedUtility() {
  return finalExpectedUtility;
}

public double getMEUvalue() {
    double meu = Double.NaN;
    if(finalExpectedUtility != null) {
        meu = (new PotentialTable(finalExpectedUtility)).getValue(0);
    }
    return meu;
}

/**
 * Gets the initial size.
 * @return the initial size.
 */

public double getJTInitialSize( ){

  return JTInitialSize;
}


/**
 * Gets the extra size.
 * @return the extra size.
 */

public double getJTExtraSize( ) {

  return JTExtraSize;
}

/**
 * Gets the vector of times
 * @return times
 */

public Vector getTimes(){
  return times;
}

/**
 * Gets the vector of sizes
 * @return sizes
 */

public Vector getSizes(){
  return sizes;
}

/**
 * Gets the vector of sizes
 * @return sizes
 */

public Vector getNumberNodes(){
  return numberNodes;
}

/**
 * Gets the fileName
 * @return fileName
 */

public String getFileName(){
  return fileName;
}

/**
 * Prints the object to the standard output.
 */

public void print( ) {

  this.print(false);
}


/**
 * Prints the object to the standard output.
 * @param partial indicates whether the propagation is an exact
 * partial abductive inference (<code>true</code>).
 */

public void print(boolean partial) {

  System.out.println("Printing statistics about the propagation");
  System.out.println("\tTime: " + time);
  System.out.println();

  if (partial) {
    System.out.println("\tSize of the initial jt: " + JTInitialSize);
    System.out.println("\tSize of the whole jt: " +
		       (JTInitialSize+JTExtraSize));
    System.out.println("\tData about the jt used in abductive inference");
  }

  JTStat.print();
}

/**
 * Method to store a new operation in the vector of operations
 */

public void addOperation(String operation){
  operations.addElement((Object)operation);
}


public void printOperations() {
    for(int i=0; i<operations.size(); i++)
        System.out.println(operations.elementAt(i));
}

/**
 * Method to add a new size if the vector of sizes
 */

public void addSize(double size){
  sizes.addElement(new Double(size));
}


/**
 * Method to add a new decision size if the vector of sizes
 */

public void addDecSize(double size){
  decSizes.addElement(new Double(size));
}



/**
 * Get the sizes of decision trees
 * @return 
 */
public Vector getDecSizes() {
    return decSizes;
}

/**
 * Method to add a new nodes value if the vector of sizes
 */

//int nn = 0;
public void addNumberNodes(double size){

 //       nn++;
//        System.out.println(nn+" addNumberNodes: "+size);
    numberNodes.addElement(new Double(size));
}

/**
 * Method to add a new time if the vector of times
 */

public void addTime(double time){
  times.addElement(new Double(time));
}

/**
 * Method to store the final expected utility
 * @param <code>Potential</code> the final expected utility
 */

public void setFinalExpectedUtility(Potential pot){
  finalExpectedUtility=pot;
}

/**
 * Method to store the name of the file used for this
 * propagation
 * @param <code>String</code> the name of the elvira file
 */

public void setFileName(String name){
  fileName=name;
}

/**
 * Method to store a vector, with the measure of importance for
 * the sets of variables of each of the decision tables
 */

public void setExplanation(String varName,Potential pot){
  PotentialTree potTree;

	// Deals with the potential to get a measure for the variables
   // belonging to it. Anyway, do it as potentialTree
  //
   if (pot instanceof PotentialTable){
		//Transform it to potentialTree
		potTree=((PotentialTable)pot).toTree();
	}
   else if(pot instanceof PotentialBPTree)
                potTree = new PotentialTree(pot);
   else if(pot instanceof PotentialTree)

		potTree=(PotentialTree)pot;

	// Now, measure the relevance for the variables

//	explanations.addElement(potTree.measureRelevance(varName));
}

/**
 * Method to save to a file the vectors of operations and sizes
 * @param <code>String</code> the name of file where the data will
 *                            ve stored
 */

public void printOperationsAndSizes() throws IOException{
	File file;
  FileWriter f;
  PrintWriter p;
  Vector explanation;
	String directory;
  int i,j;

	 // Create a directory to store the data about the
	 // evaluation

   directory=fileName+"_dir";
	 file=new File(directory);
	 System.out.println("Creacion directorio: "+file.mkdir());

   // Create a new FileWriter object

   f=new FileWriter(directory+"/op_util_exp");

   // Create a new object PrintWriter object

   p=new PrintWriter(f);

   // Now, go on operation to operation showing the
   // list of operations and the final utility

   for(i=0; i < operations.size(); i++){
     p.println((String)operations.elementAt(i));
   }

   // Finally shows the final expectedUtility

   if (finalExpectedUtility != null) {
    finalExpectedUtility.saveResult(p);
   }

	 // Shows the explanation

	 for(i=0; i < explanations.size() ; i++){
		 explanation=(Vector)explanations.elementAt(i);

		 // Prints the measure for this table

		 p.println();
		 p.println("++++++++++++++++++++++++++++++++++++++++++");

		 for(j=0; j < explanation.size(); j++){
		 	p.println((String)explanation.elementAt(j));
	   }

		 p.println();
		 p.println("++++++++++++++++++++++++++++++++++++++++++");
	}

	// Shows the time needed to evaluate

	p.println();
	p.println("Computation time: "+time);

   // Close the descriptor for file writting

   f.close();

	 // Create a new file for sizes of operations

	 f=new FileWriter(directory+"/sizes");
	 p=new PrintWriter(f);

	 for(i=0; i < sizes.size(); i++){
			p.println((i+1)+" "+sizes.elementAt(i).toString());
	 }

	 // Close the descriptor

	 f.close();

	 // Create a new file for times of computation

	 f=new FileWriter(directory+"/times");
	 p=new PrintWriter(f);

	 for(i=0; i < times.size(); i++){
			p.println((i+1)+" "+times.elementAt(i).toString());
	 }

	 // Close the descriptor

	 f.close();
}

/**
 * Method to get the total computation time from
 * vector of times
 * @return the last time stored
 */

public double getMaximumTime(){
  return(((Double)times.elementAt(times.size()-1)).doubleValue());
}

/**
 * Method to return the maximum size reached during the
 * evaluation
 * @return maximum size
 */

public double getMaximumSize(){
  int i;
  double val,max;

  for(i=0,max=0; i < sizes.size(); i++){
    val=((Double)sizes.elementAt(i)).doubleValue();
    if (val > max)
      max=val;
  }

  // Return max

  return max;
}

/**
 * Method for computing the average size during the computation
 * @return getAverageSize
 */
public double getAverageSize(){
    double average=0,value;

    // Consider the values
    for(int i=0; i < sizes.size(); i++){
       value=(Double)sizes.elementAt(i);
       average+=value;
    }

    // Compute the average
    average=average/sizes.size();

    // Return average
    return average;
}

/**
 * Method to return the maximum size reached during the
 * evaluation
 * @return maximum size
 */

public double getMaximumNumberNodes(){
  int i;
  double val,max;

  for(i=0,max=0; i < numberNodes.size(); i++){
    val=((Double)numberNodes.elementAt(i)).doubleValue();
    if (val > max)
      max=val;
  }

  // Return max

  return max;
}

/**
 * Method for computing the average size during the computation
 * @return getAverageSize
 */
public double getAverageNumberNodes(){
    double average=0,value;

    // Consider the values
    for(int i=0; i < numberNodes.size(); i++){
       value=(Double)numberNodes.elementAt(i);
       average+=value;
    }

    // Compute the average
    average=average/numberNodes.size();

    // Return average
    return average;
}

    public double getInitalUtilityError() {
        return initalUtilityError;
    }

    public void setInitalUtilityError(double ininitalUtilityError) {
        this.initalUtilityError = ininitalUtilityError;
    }

    public long getInitialUtilitySize() {
        return initialUtilitySize;
    }

    public void setInitialUtilitySize(long initialUtilitySize) {
        this.initialUtilitySize = initialUtilitySize;
    }
    
    
   /** Returns a vector with the history of all utility potentials
    * 
    * @return 
    */
    public Vector getUtilityHistory() {
        return utilityHistory;
    }

    
    
    public void setUtilityHistory(Vector utilityHistory) {
        this.utilityHistory = utilityHistory;
    }
    
    
    
    
    
    
    /**
     * Add an utility potential to the history
     * @param p 
     */
    public void addUtilityHistory(Potential p) {
        utilityHistory.add(p);
    }
    
        /**
     * Add an utility potential to the history
     * @param p 
     */
    public void addUtilityHistory(int pos, Potential p) {
        utilityHistory.add(pos, p);
    }
    
    public Potential removeUtilityHistory(int pos) {
        return (Potential) utilityHistory.remove(pos);
    }
  

    
    public Vector getUtilHstSizes() {
        Vector v = new Vector();
        for(int i=0; i<utilityHistory.size(); i++) {
            if(utilityHistory.get(i)!=null)
                v.add((double)((Potential)utilityHistory.get(i)).getSize());
            else
                v.add(0);
        }
        
        return v;
    }
   
   
    public Vector getUtilHstNodes() {
        Vector v = new Vector();
        for(int i=0; i<utilityHistory.size(); i++) {
            if(utilityHistory.get(i)!=null)
                v.add((double)((Potential)utilityHistory.get(i)).getNumberOfNodes());
            else
                v.add(0);
        }
        
        return v;
    }

 //   int n = 0;
    public void addTotalSize(double s) {
 //       n++;
 //       System.out.println(n+" addTotalSize: "+s);
        this.totalSizeHistory.add(s);
    }

    public Vector getTotalSizeHistory() {
        return totalSizeHistory;
    }
    
    public void addOpSize(double op) {

        
        this.operationSize.add(op);
    }

    public Vector getOpSizes() {
        return operationSize;
    }
    
    public Vector getTimeDiff() {
        Vector v = new Vector();

        for(int i=0; i<times.size(); i++)
            v.add((Double)times.get(i) - (Double)times.get(0));
        
        return v;
        
    }

    public long getInitialUtilityNodes() {
        return initialUtilityNodes;
    }

    public void setInitialUtilityNodes(long initialUtilityNodes) {
        this.initialUtilityNodes = initialUtilityNodes;
    }
    
    
        public Vector<Node> getElimOrder() {
        return elimOrder;
    }
    
    public Vector<String> getElimOrderStr() {
        Vector<String> elimOrderStr = new Vector<String>();
        for(Node v : elimOrder) {
            elimOrderStr.add(v.getName());
        
        }
        return elimOrderStr;
    }
  
  
    public void addVariableRemoved(Node var) {
        elimOrder.add(var);
    }

    public long getInitialPotentialsNodes() {
        return initialPotentialsNodes;
    }

    public void setInitialPotentialsNodes(long initialPotentialsNodes) {
        this.initialPotentialsNodes = initialPotentialsNodes;
    }

    public long getNumMultiplications() {
        return numMultiplications;
    }

    public long getNumDivisions() {
        return numDivisions;
    }
  
    

    public void addNumMultiplications(long n) {
        
        if(n==0)
            return;
        
        
 //       StackTraceElement[] stack = Thread.currentThread().getStackTrace();
 //       System.out.println(stack[2].toString());
        
//        System.out.println("\t\t ~~Combs += "+n);
        
        

        numMultiplications += n;
       // System.out.println("Comb"+nComb+" + "+n +" = " + numMultiplications);
        
    }
  
    public void addNumDivisions(long n) {
                if(n==0)
            return;
  //      StackTraceElement[] stack = Thread.currentThread().getStackTrace();
 //       System.out.println(stack[2].toString());
 //       System.out.println("\t\t ~~Divs += "+n);
        numDivisions += n;
    }

    public long getNumSums() {
        
        return numSums;
    }

    public void addNumSums(long numSums) {
                if(numSums==0)
            return;
 //       StackTraceElement[] stack = Thread.currentThread().getStackTrace();
 //       System.out.println(stack[2].toString());
//System.out.println("\t\t ~~Sums += "+numSums);
        this.numSums += numSums;
    }

    
    public void addSumMarg(long n) {
                if(n==0)
            return;
  //      StackTraceElement[] stack = Thread.currentThread().getStackTrace();
  //      System.out.println(stack[2].toString());
  //      System.out.println("SumMarg "+n);
        numSumMarg += n;
 //       System.out.println("\t\t ~~SumMargs += "+n);
    }

    public long getNumSumMarg() {
        return numSumMarg;
    }
    
    public void addMaxMarg(long n) {
                if(n==0)
            return;
 //       StackTraceElement[] stack = Thread.currentThread().getStackTrace();
 //       System.out.println(stack[2].toString());
 //               System.out.println("MaxMarg "+n);
//System.out.println("\t\t ~~MaxMargs += "+n);
        numMaxMarg += n;
    }

    public long getNumMaxMarg() {
        return numMaxMarg;
    }
    
    
    public long getTotalOperations() {
        return numMaxMarg+numDivisions+numMultiplications+numSumMarg+numSums;
    }
    
    public Vector getInitialProbError() {
        return initialProbError;
    }

    public void addInitialProbError(double e) {
        initialProbError.add(e);
    }

    public long getNumProbBarren() {
        return numProbBarren;
    }

    public void addNumProbBarren(long numProbBarren) {
        this.numProbBarren += numProbBarren;
    }
    
    
   public long getNumConstraints() {
        return numConstraints;
    }

    public void addNumConstraints(long numConstraints) {
        this.numConstraints += numConstraints;
    }

    public long getNumAppliedConstraints() {
        return numAppliedConstraints;
    }



    public void addNumAppliedConstraints(int numConstraints) {
        this.numAppliedConstraints += numConstraints;
    }


    public  void addSumMargTime(long t) {
        sumMargTime.add(t);
    }

    public Vector getSumMargTime() {
        return sumMargTime;
    }

    public void setSumMargTime(Vector sumMargTime) {
        this.sumMargTime = sumMargTime;
    }

    public double getLPcalls() {
        return LPcalls;
    }

    public double getLPsizes() {
        return LPsizes;
    }

    public double getLPmean() {
        if(LPcalls==0)
            return 0;
        return LPsizes/LPcalls;
    }
    
    public double getMaxLPsize() {
        return maxLPsize;
    }
    
    public void addLPcall(double size) {
        LPcalls++;
        LPsizes += size;
        if(maxLPsize<size)
            maxLPsize = size;
    }



    public long getSingletons() {
        return singletons;
    }

    public void setSingletons(long singletons) {
        this.singletons = singletons;
    }
    

    public void addSingletons(long n) {
        this.singletons +=n;
    }
    
    
    

} // End of class
