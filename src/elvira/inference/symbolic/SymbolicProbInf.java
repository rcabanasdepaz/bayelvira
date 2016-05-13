/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package elvira.inference.symbolic;

import elvira.Bnet;
import elvira.Evidence;
import elvira.RelationList;
import elvira.inference.Propagation;
import elvira.tools.Crono;
import java.util.Vector;

/**
 *
 * @author Rafael Cabañas (rcabanas@decsai.ugr.es)
 */
public class SymbolicProbInf extends Propagation{
  
   /**
    * The relations available in a given moment.
    */
   
   protected RelationList currentRelations;
   /**
    * To show if we want to use statistics about the evaluation
    * It is required to change this flag to use statistics
    */

   public boolean generateStatistics=true;
   
   public SymbolicProbInf() {
   }
   
   
     /**
    * Constructs a new propagation for a given Bayesian network and
    * some evidence.
    *
    * @param b a <code>Bnet</code>.
    * @param e the evidence.
    */
   
   public SymbolicProbInf(Bnet b, Evidence e) {
      
      observations = e;
      network = b;
      results = new Vector();

      
   } 
   
   
      /**
    * Constructs a new propagation for a given Bayesian network
    *
    * @param b a <code>Bnet</code>.
    */
   
   public SymbolicProbInf(Bnet b) {
      
      network = b;
      observations = new Evidence();
      results = new Vector();
      
   }
   
      /**
    * Computes the posterior distributions.
    * There will be a posterior distribution for each
    * variable of interest.
    * Posterior distributions are stored in <code>results</code>.
    * Note that observed variables are not included in the deletion
    * sequence.
    */
   
   public void getPosteriorDistributions() {
         System.out.println("this propagation method is not implemented for " + network.getClass());
         System.exit(1);
   }
   
      
   /**
    * Carries out a propagation storing the results in <code>results</code>.
    */
   
   public void propagate() {
         System.out.println("this propagation method is not implemented for " + network.getClass());
         System.exit(1);
   
   }
   
   

}
