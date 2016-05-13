/* NodePairTable.java */

package elvira;

import java.util.Vector;
import elvira.Relation;
import elvira.FiniteStates;
import elvira.potential.Potential;
import elvira.potential.PotentialContinuousPT;
import elvira.potential.PotentialTree;
import elvira.potential.binaryprobabilitytree.PotentialBPTree;

/**
 * This class implements a pair of values
 * (<code>FiniteStates,Vector</code>), where the <code>Vector</code> contains
 * <code>Relations</code> where the <code>FiniteStates</code> variable
 * appears. It can be used in propagation algorithms to control
 * the relations in which a variable takes part, for instance, when the
 * variable is going to be deleted.
 *
 * @since 22/9/2000
 */

public class NodePairTable {

/**
 * A variable.
 */
protected Node variable;

/**
 * Is a vector that contains all the relations where <code>variable</code>
 * appears.
 */
protected Vector relations;


/**
 * Constructs an <code>NodePairTable</code>.
 * @param var a <code>Node</code> variable to put in this object.
 */

public NodePairTable(Node var) {
  
  variable = var;
  relations = new Vector();
}


/**
 * Gets the variable in this pair.
 * @return the variable.
 */

public Node getVariable() {
  
  return variable;
}

/**
 * Computes the size of the potential resulting from the
 * combination of all the relations in the list,
 * considering fully expanded potentials and
 * <code>FiniteStates</code> variables. It takes into account only
 * finite-states variables to calculate the size.
 * @return the obtained size.
 */

public double totalSize() {
  
  Relation r;
  double s = 1.0;
  int i;
  
  NodeList nl = new NodeList(); 
  
  for (i=0 ; i<relations.size() ; i++) {
    r = (Relation)relations.elementAt(i);
    nl.join(r.getVariables());
  }
  for(i=0;i<nl.size();i++){ // Remove non finite-states nodes
    if(nl.elementAt(i).getTypeOfVariable()!=Node.FINITE_STATES){
      nl.removeNode(nl.elementAt(i));
    }
  }
  s = FiniteStates.getSize(nl);
  
  return s;
}

public double meanCompressionRatio() {
  
  Relation r;
  double ratio = 0;
  Potential p;
  
  System.out.println("meanCompressionRatio");
  long treeSize=0, potentialSize;
  
  for(int i=0; i<relations.size(); i++) {
     p = ((Relation) relations.get(i)).getValues();
     
     if(p==null)
         System.out.println();
     
     if(p instanceof PotentialTree)
        treeSize = ((PotentialTree)p).getSize();
     else
        treeSize = ((PotentialBPTree)p).getSize();

     
     potentialSize = p.getNumConfigurations();
     
     ratio += ((double)treeSize)/((double)potentialSize);
     
  }
  
  return ratio/relations.size();
  

  
  
}





/**
 * Computes the size of the potential resulting from the
 * combination of all the relations in the list,

 * @return the obtained size.
 */

public double totalTreeSize() {
  
  Relation r;
  double s = 1.0;
  int i;
  
  NodeList nl = new NodeList(); 
  
  
  //Generates a list with all the variables in
  //the resulting potential
  for (i=0 ; i<relations.size() ; i++) {
    r = (Relation)relations.elementAt(i);
    nl.join(r.getVariables());
  }
  for(i=0;i<nl.size();i++){ // Remove non finite-states nodes
    if(nl.elementAt(i).getTypeOfVariable()!=Node.FINITE_STATES){
      nl.removeNode(nl.elementAt(i));
    }
  }
  
  
  for(i=0;i<nl.size();i++){
      FiniteStates v = (FiniteStates) nl.elementAt(i);
      int totalNumStates = v.getNumStates();
      double maxNumStates = 1;
      
      for(int j=0; j<relations.size(); j++){ 
          
         if(((Relation)relations.elementAt(j)).getKind() != Relation.CONSTRAINT){ 
            Potential tree =  ((Relation)relations.elementAt(j)).getValues();
            double prnDegree = ((double) tree.getNumPrunedConf(v)/ (double) tree.getNumConfigurations());
            double meanNumStates = (1- prnDegree) * totalNumStates;
            
           // tree.print();
            
            if(meanNumStates > maxNumStates)
                maxNumStates = meanNumStates;
         }
         
      }
      
      s *= maxNumStates;
      
  }
  
  
  
  return s;
}


/**
 * Computes the size of the potential resulting from the
 * combination of all the relations in the list,
    @return the obtained size.
 */

public double totalTreeSize2() {
  
  Relation r;
  double s = 0;
  int i;
  
  NodeList nl = new NodeList(); 
  
  
  //Generates a list with all the variables in
  //the resulting potential
  for (i=0 ; i<relations.size() ; i++) {
    r = (Relation)relations.elementAt(i);
    nl.join(r.getVariables());
  }
  for(i=0;i<nl.size();i++){ // Remove non finite-states nodes
    if(nl.elementAt(i).getTypeOfVariable()!=Node.FINITE_STATES){
      nl.removeNode(nl.elementAt(i));
    }
  }
  
  

      
      for(int j=0; j<relations.size(); j++){ 
          
         if(((Relation)relations.elementAt(j)).getKind() != Relation.CONSTRAINT){ 
            Potential tree =  ((Relation)relations.elementAt(j)).getValues();
            long treeSize = tree.getSize();
            NodeList varsInPot = new NodeList(tree.getVariables());
            
            NodeList varsNotInPot = nl.difference(varsInPot);
            
            for(i=0; i<varsNotInPot.size(); i++) {
                treeSize *= ((FiniteStates)(varsNotInPot.getNodes().get(i))).getNumStates();
            }
            
            s += treeSize;
            
         }
         
      }
      


  
  return s/relations.size();
}



/**
 * Computes the size of the potential resulting from the
 * combination of all the relations in the list,
    @return the obtained size.
 */

public double totalNodesInTree() {
       return totalNodesInTree(false);
    
}


public double totalNodesInTree(boolean onlyFirstTree) {
  
  Relation r;
  double s = 0;
  int i;
  int numPots=0;
  
  NodeList nl = new NodeList(); 
  
  
  //Generates a list with all the variables in
  //the resulting potential
  for (i=0 ; i<relations.size() ; i++) {
    r = (Relation)relations.elementAt(i);
    nl.join(r.getVariables());
  }
  for(i=0;i<nl.size();i++){ // Remove non finite-states nodes
    if(nl.elementAt(i).getTypeOfVariable()!=Node.FINITE_STATES){
      nl.removeNode(nl.elementAt(i));
    }
  }
  
  

      
      for(int j=0; j<relations.size(); j++){          
         if(((Relation)relations.elementAt(j)).getKind() != Relation.CONSTRAINT){ 
             
            Potential tree =  ((Relation)relations.elementAt(j)).getValues();
            long numLeaves = tree.getSize();
            long numNodes = tree.getNumberOfNodes();
            
            NodeList varsInPot = new NodeList(tree.getVariables());
            NodeList varsNotInPot = nl.difference(varsInPot);
            
            long numNodesAdded = 1;
            for(i=0; i<varsNotInPot.size(); i++) {
                numNodesAdded *= ((FiniteStates)(varsNotInPot.getNodes().get(i))).getNumStates();
            }
            numNodesAdded = numLeaves*(2* numNodesAdded - 2);
            
            
            s += numNodes + numNodesAdded;
            numPots++;
            
         }
         
      }
      


  if(numPots>0)
    return s/numPots;
  return 0;
}

public double totalNodesInProbTree() {
  
  Relation r;
  double s = 0;
  int i;
  int numPots=0;
  
  NodeList nl = new NodeList(); 
  
  
  //Generates a list with all the variables in
  //the resulting potential
  for (i=0 ; i<relations.size() ; i++) {
    r = (Relation)relations.elementAt(i);
    nl.join(r.getVariables());
  }
  for(i=0;i<nl.size();i++){ // Remove non finite-states nodes
    if(nl.elementAt(i).getTypeOfVariable()!=Node.FINITE_STATES){
      nl.removeNode(nl.elementAt(i));
    }
  }
  
  

      
      for(int j=0; j<relations.size(); j++){          
         if(((Relation)relations.elementAt(j)).getKind() != Relation.CONSTRAINT &&
              ((Relation)relations.elementAt(j)).getKind() != Relation.UTILITY   ){ 
             
            Potential tree =  ((Relation)relations.elementAt(j)).getValues();
            long numLeaves = tree.getSize();
            long numNodes = tree.getNumberOfNodes();
            
            NodeList varsInPot = new NodeList(tree.getVariables());
            NodeList varsNotInPot = nl.difference(varsInPot);
            
            long numNodesAdded = 1;
            for(i=0; i<varsNotInPot.size(); i++) {
                numNodesAdded *= ((FiniteStates)(varsNotInPot.getNodes().get(i))).getNumStates();
            }
            numNodesAdded = numLeaves*(2* numNodesAdded - 2);
            
            
            s += numNodes + numNodesAdded;
            numPots++;
            
         }
         
      }
      


  if(numPots>0)
    return s/numPots;
  return 0;
}


public double meanDiffPrune() {
  
  Relation r;
  double d = 0.0;
  int i;
  
  NodeList nl = new NodeList(); 
  
  
  //Generates a list with all the variables in
  //the resulting potential
  for (i=0 ; i<relations.size() ; i++) {
    r = (Relation)relations.elementAt(i);
    nl.join(r.getVariables());
  }
  for(i=0;i<nl.size();i++){ // Remove non finite-states nodes
    if(nl.elementAt(i).getTypeOfVariable()!=Node.FINITE_STATES){
      nl.removeNode(nl.elementAt(i));
    }
  }
  
  
  for(i=0;i<nl.size();i++){
      FiniteStates v = (FiniteStates) nl.elementAt(i);
      int totalNumStates = v.getNumStates();
      double maxDegree = 0;
      double minDegree = 1;
      
      for(int j=0; j<relations.size(); j++){ 
          
         if(((Relation)relations.elementAt(j)).getKind() != Relation.CONSTRAINT){ 
            Potential tree =  ((Relation)relations.elementAt(j)).getValues();
            double prnDegree = ((double) tree.getNumPrunedConf(v)/ (double) tree.getNumConfigurations());
        
            if(prnDegree > maxDegree)
                maxDegree = prnDegree;
            if(prnDegree < minDegree)
                minDegree = prnDegree;
            
         }
         
      }
      
      d += (maxDegree - minDegree);
      
  }
  
  d = d/nl.size();
  
  
  
  return d;
}


/**
 * Computes the size of the potential resulting from the
 * combination of all the relations in the list,
 * considering fully expanded potentials (in this case, ContinuousProbabilityTrees) and
 * <code>FiniteStates</code> variables.
 * @return the obtained size.
 */

public double continuousTotalSize() {
  
  Relation r;
  PotentialContinuousPT pot;
  double s = 1.0;
  int numSplits = 0; //This vble will contain the splits of the continuous variables
  int split;
  int numTerms = 1;
  int terms;
  double tamDiscrete;
  int i,j;

  NodeList varsInPot;
   //System.out.println("****Empiezo con totalSize****");

  NodeList discreteNl = new NodeList();
  NodeList nl = new NodeList(); 
  //System.out.println("La variable que estamos mirando es: ");
  //variable.print();
  //System.out.println("Hay "+relations.size()+" potenciales para esta variable");
  
  for (i=0 ; i<relations.size() ; i++) {
    r = (Relation)relations.elementAt(i);
    pot = (PotentialContinuousPT)r.getValues();
    //System.out.println("Primero este potencial:");
    //pot.getTree().print();
    //pot.setNumTerms(3);
    //pot.setNumSplits(2);
    split = pot.getNumSplits();
    //System.out.println("splits es "+split);
    terms = pot.getNumTerms();
    //System.out.println("terms es "+terms);
    //System.out.println("numTerms antes de multiplicar por terms "+numTerms);
    numTerms = numTerms * terms;
    //System.out.println("numTerms despues de multiplicar por terms "+numTerms);
    //numSplits += split;

    // Now I want to get splits^(number of cont var in pot). I will acumulate in s;
    varsInPot = new NodeList();
    varsInPot = r.getVariables();

    for(j=0 ; j<varsInPot.size() ; j++){

	if(varsInPot.elementAt(j).getTypeOfVariable()== Node.CONTINUOUS){

	    //System.out.println("La variables es continua, luego hago s = s * split, con s= "+s+" y split= "+split);
	    s = s*split;
	}
    }

    nl.join(r.getVariables());
  }
  //System.out.println("Este es el nodelist de todas las vbles que estan en los potenciales");
  //nl.print();
  for(i=0;i<nl.size();i++){ 
    if(nl.elementAt(i).getTypeOfVariable()== Node.FINITE_STATES){
      //System.out.println("Inserto en las discretas la variable");
      //(nl.elementAt(i)).print();
      discreteNl.insertNode(nl.elementAt(i));  
    }
    //else{ 
    //  s *= numSplits;
    //  System.out.println("La vble es cont, luego multiplico s por el numSplits y sale "+s);
    //}
  }
   

  if (discreteNl.size() > 0){
    tamDiscrete = FiniteStates.getSize(discreteNl);
    //System.out.println("Este es el tamdiscrete que obtengo: "+tamDiscrete);
    //System.out.println("Hay alguna vble discreta.");
    s *= tamDiscrete;
  }
  
  //System.out.println("Esto es lo q vale numTerms: "+numTerms);
  s *= numTerms;
  
  //System.out.println("Y el tamaño seria "+s);
  //System.out.println("****Acabo con totalSize****");
  return s;
}

/**
 * Computes the number of links to add during the creation
 * of the potential resulting from the
 * combination of all the relations in the list,
 * considering fully expanded potentials and
 * <code>FiniteStates</code> variables.
 * @return the number of links calculated.
 */

public double numberOfLinksToAdd() {
  
  Relation r;
  int i, j, k, s;
  double newLinks = 0;
  boolean found;
  Node a, b;
  NodeList v, nl = new NodeList(); 
  
  for (i=0 ; i<relations.size() ; i++) {
    r = (Relation)relations.elementAt(i);
    nl.join(r.getVariables());
  }
  
  // as variable is contained in all the relations, 
  // no link is added containing variable, so we can remove it

  nl.removeNode(variable);
  
  // now we are going to build 
  
  s = nl.size(); 
  
  for (i=0 ; i<(s-1) ; i++) {
    a = nl.elementAt(i);
    for (j=i+1 ; j<s ; j++) {
      b = nl.elementAt(j);
      // check that the link between the nodes in positions i and j exists
      found = false;
      for (k=0 ; k<relations.size() ; k++) {
	r = (Relation)relations.elementAt(k);
	v = r.getVariables();
	if ( (v.getId(a)!=-1) && (v.getId(b)!=-1) ){
	  found = true;
	  break;
	}      
      }
      if (!found)
	newLinks++;
    }
  }    
  
  return newLinks;
}


/**
 * Computes the number of links to add among the nodes of the explanation
 * set and the rest of nodes, when the considered node is deleted
 * during the triangulation.
 * @param expSet an explanation set.
 * @return the number of links calculated.
 */

public double numberOfLinksToAddBetweenExplanationAndRest(NodeList expSet) {
  
  Relation r;
  int i, j, k, s;
  double newLinks = 0;
  boolean found;
  Node a, b;
  NodeList v, nl = new NodeList(); 
  
  for (i=0 ; i<relations.size() ; i++) {
    r = (Relation)relations.elementAt(i);
    nl.join(r.getVariables());
  }
  
  // as variable is contained in all the relations, 
  // no link is added containing variable, so we can remove it
  
  nl.removeNode(variable);
  
  // now we are going to build 
  
  s = nl.size(); 
  
  for (i=0 ; i<(s-1) ; i++) {
    a = nl.elementAt(i);
    for (j=i+1 ; j<s ; j++) {
      b = nl.elementAt(j);
      if (((expSet.getId(a)==-1) && (expSet.getId(b)!=-1)) ||
	  ((expSet.getId(a)!=-1) && (expSet.getId(b)==-1)) ) {
	// Check that the link between the nodes in positions i and j exists
	found = false;
	for (k=0 ; k<relations.size() ; k++) {
	  r = (Relation)relations.elementAt(k);
	  v = r.getVariables();
	  if ( (v.getId(a)!=-1) && (v.getId(b)!=-1) ) {
	    found = true;
	    break;
	  }      
	}
	if (!found)
	  newLinks++;
      }
    }
  }    
  
  return newLinks;
}


/**
 * Computes the number of links to add when the considered node is deleted 
 * during the triangulation, but excluding the links among the nodes in
 * the explanation set.
 * @param expSet an explanation set.
 * @return the number calculated.
 */

public double numberOfLinksToAddExceptAmongExplanationNodes(NodeList expSet) {
  
  Relation r;
  int i, j, k, s;
  double newLinks = 0;
  boolean found;
  Node a, b;
  NodeList v, nl = new NodeList(); 
        
  for (i=0 ; i<relations.size() ; i++) {
    r = (Relation)relations.elementAt(i);
    nl.join(r.getVariables());
  }
  
  // as variable is contained in all the relations, 
  // no link is add containing variable, so we can remove it
  
  nl.removeNode(variable);
  
  // now we are going to build 
  
  s = nl.size(); 
  
  for (i=0 ; i<(s-1) ; i++) {
    a = nl.elementAt(i);
    for (j=i+1 ; j<s ; j++) {
      b = nl.elementAt(j);
      if (!(expSet.getId(a)!=-1) && !(expSet.getId(b)!=-1) ) {
	// Check that the link between the nodes in positions i and j exists
	found = false;
	for (k=0 ; k<relations.size() ; k++) {
	  r = (Relation)relations.elementAt(k);
	  v = r.getVariables();
	  if ( (v.getId(a)!=-1) && (v.getId(b)!=-1) ) {
	    found = true;
	    break;
	  }      
	}
	if (!found)
	  newLinks++;
      }
    }
  }    
  
  return newLinks;
}



    @Override
    public String toString(){
        return variable.toString();

    }

} // End of class.
