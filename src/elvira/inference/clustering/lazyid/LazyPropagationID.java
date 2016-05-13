package elvira.inference.clustering.lazyid;

import elvira.RelationList;
import elvira.IDiagram;
import elvira.Bnet;
import elvira.Network;
import elvira.Node;
import elvira.NodeList;
import elvira.tools.idiagram.pairtable.IDPairTable;
import elvira.Relation;
import elvira.potential.Potential;
import elvira.potential.PotentialTree;
import elvira.parser.ParseException;
import elvira.inference.Propagation;
import elvira.potential.PotentialTable;
import elvira.tools.idiagram.pairtable.CombPairTable;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;
import javax.tools.Diagnostic;

public class LazyPropagationID extends Propagation {

   /**
    * Constants to indicate the possible states of evaluable
    */
   protected enum EvaluableStates {

      NOCHECKED, EVALUABLE, NOEVALUABLE
   };
   /**
    * Data member to indicate if the diagram is evaluable: it is prepared for
    * evaluation. Initially this value is NOCHECKED and its value is determined
    * after a call to initialConditions
    */
   protected EvaluableStates evaluable;
   /**
    * The relations related to the IDiagram. This is needed to convert the
    * relation potential to trees if they are not in this form
    */
   protected RelationList currentRelations;
   /**
    * The propagation will be done using a strong junction tree
    */
   protected StrongJunctionTree tree;
   /**
    * Data member to store the criteria used for generation the deletion
    * sequence when triangulating
    */
   protected int triangulationCriteria = IDPairTable.WEIGHTFILL;
   /**
    * Criteria used for removing variables, with online or offline triangulation
    */
   protected int variableEliminationCriteria = StrongJunctionTree.OFFLINE_TRIANGULATION;
   
   
   /** The heuristic could be different to the heuristic used for triangulation*/
   
   protected int eliminationHeuristic = -1;
   
   /**
    * Criteria used for propagation: using the triangulation order directly or
    * using the message passing schema
    */
   protected int propagationCriteria = StrongJunctionTree.DIRECT_ELIMINATION;
   /**
    * Data member to store if the evaluation will generate statistics
    */
   protected boolean generateStatistics = true;
   /**
    * Data member to store if the evaluation will generate debug information
    */
   protected boolean generateDebugInfo = true;
   
   
   protected boolean onlyInitialTransformation = false;
   
   
   protected boolean checkUnity = true;
   
   protected boolean allowSingletons = true;
   
      /** 
    * Indicates if potentials are transformed when the
    * StrongJunctionTree is built. The default value is false, so
    * they are transformed before building the tree. 
    */
   protected boolean transformAtTree = false;
   

   protected int spiCriteria = CombPairTable.MIN_SIZE_ONLY_PAIR;
   
   
   protected  int combinationHeuristic = CombPairTable.MIN_SIZE_ONLY_PAIR;
   
   protected boolean greedyCombination = false;
   
   protected boolean saveTrianglation = false;
  
   
   protected boolean computeMEU = false;
   
    //Selecting operation overhead
    protected boolean measureOverHead = false;
    protected long overHeadTime = 0;
    
    private boolean qualitativeEval = false;


     

   /**
    * Class constructor: it receives the ID to solve
    *
    * @param diag
    * @param triangulationCriteria used for triangulation
    * @param propagationCriteria used for removing variables: with triangulation
    * order or with message passing schema
    * @param eliminationCriteria used for removing variables in the cliques:
    * offline-online
    * @param debug flag
    * @param statistics flag
    * @param checkInitials flag for checking initials or not
    */
   public LazyPropagationID(IDiagram diag, int triangulationCriteria, int propagationCriteria,
           int eliminationCriteria, boolean debug, boolean statistics, boolean checkInitials, boolean transformAtTree) {

      if (debug) {
         System.out.println("LazyPropagationID:  class Constructor ----- BEGIN");
      }


      // Sets evaluable to NOCHECKED
        evaluable = EvaluableStates.NOCHECKED;
      
       this.transformAtTree = transformAtTree;

      // Create results vector
      results = new Vector();

      // Set method name
      setMethod("LayPropagationID");

      // Print the network in Hugin format
      //try {
      //diag.saveWithHuginFormat();
      //System.exit(0);
      //} catch (IOException e) {
      //}
      //;

      if (triangulationCriteria != -1) {
         this.triangulationCriteria = triangulationCriteria;
      }

      // Assign the value for the propagation criteria
      if (propagationCriteria != -1) {
         this.propagationCriteria = propagationCriteria;
      }

      // Assign the value for removing criteria
      if (eliminationCriteria != -1) {
         this.variableEliminationCriteria = eliminationCriteria;
      }

      // Set the flags for debug and statistics
      generateDebugInfo = debug;
      generateStatistics = statistics;
      diag.setGenerateDebugInfo(generateDebugInfo);
      
      // Fix the network
      network = (Bnet) diag;
      

      // Remove constraint relations if required
      if (getClass().getName().equals("elvira.inference.clustering.lazyid.LazyPropagationIDWithPTAC") == false && 
          getClass().getName().equals("elvira.inference.clustering.lazyid.LazyPropagationIDWithBPTAC") == false) {
         diag.removeConstraintRelations();
      }

      // Now, deal with the IDiagram to avoid redundant links,
      // and to prepare it for evaluation. All this is done
      // in method initialConditions
      boolean state = true; 
      if(checkInitials) 
          state = initialConditions();

      if (state == false) {
         evaluable = EvaluableStates.NOEVALUABLE;
         System.out.println("The IDiagram is not evaluable");
         System.exit(0);
      } else {
         evaluable = EvaluableStates.EVALUABLE;
      }

      if (generateDebugInfo) {
         System.out.println("FIN LazyPropagationID:  class Constructor ----- END");
      }
   }
   
   
      /**
    * Class constructor: it receives the ID to solve
    *
    * @param diag
    * @param triangulationCriteria used for triangulation
    * @param propagationCriteria used for removing variables: with triangulation
    * order or with message passing schema
    * @param eliminationCriteria used for removing variables in the cliques:
    * offline-online
    * @param debug flag
    * @param statistics flag
    */
   public LazyPropagationID(IDiagram diag, int triangulationCriteria, int propagationCriteria,
           int eliminationCriteria, boolean debug, boolean statistics) {
        this(diag, triangulationCriteria, propagationCriteria, eliminationCriteria, debug, statistics, true, false);


   }
   
   
   
   /**
    * Returns the Strong Junction Tree
    * @return  
    */ 
    public StrongJunctionTree getStrongJunctionTree() {
        return tree;
    }

    public int getSpiCriteria() {
        return spiCriteria;
    }

    public void setSpiCriteria(int spiCriteria) {
        this.spiCriteria = spiCriteria;
    }

    public boolean isCheckUnity() {
        return checkUnity;
    }

    public void setCheckUnity(boolean checkUnity) {
        this.checkUnity = checkUnity;
    }

    public boolean isAllowSingletons() {
        return allowSingletons;
    }

    public void setAllowSingletons(boolean allowSingletons) {
        this.allowSingletons = allowSingletons;
    }

    public boolean isGreedyCombination() {
        return greedyCombination;
    }

    public void setGreedyCombination(boolean greedyCombination) {
        this.greedyCombination = greedyCombination;
    }
    
 

    public int getCombinationHeuristic() {
        return combinationHeuristic;
    }

    public void setCombinationHeuristic(int combinationHeuristic) {
        this.combinationHeuristic = combinationHeuristic;
    }

    public boolean isOnlyInitialTransformation() {
        return onlyInitialTransformation;
    }

    
    
    
   
   public void setOnlyInitialTransformation(boolean onlyInitialTransformation) {   
        this.onlyInitialTransformation = onlyInitialTransformation;
   }

    public int getEliminationHeuristic() {
        return eliminationHeuristic;
    }

    public void setEliminationHeuristic(int eliminationHeuristic) {
        this.eliminationHeuristic = eliminationHeuristic;
    }

    public boolean isComputeMEU() {
        return computeMEU;
    }

    public void setComputeMEU(boolean computeMEU) {
        this.computeMEU = computeMEU;
    }

    public boolean isQualitativeEval() {
        return qualitativeEval;
    }

    public void setQualitativeEval(boolean qualitativeEval) {
        this.qualitativeEval = qualitativeEval;
    }
   
   
   
   
   
    public void addOverHeadTime(long overHeadTime) {
        this.overHeadTime += overHeadTime;
    }

    public boolean isMeasureOverHead() {
        return measureOverHead;
    }

    public void setMeasureOverHead(boolean measureOverHead) {
        this.measureOverHead = measureOverHead;
    }

    public long getOverHeadTime() {
        return overHeadTime;
    }

    public void setOverHeadTime(long overHeadTime) {
        this.overHeadTime = overHeadTime;
    }

   
   
   
   
   
  /**
    * Transforms one of the original relations into another one whose values are
    * of class
    * <code>PotentialTree</code>, and adding the effect of the possible
    * constraints. This is done for normal relations, but not for constraints
    * relations @ param r the
    * <code>Potential</code> to be transformed. @Override method in Propagation
    * class
    */

   public Potential transformInitialPotential(Potential p, int kind, Vector<Node> importatVariables) {
      if (generateDebugInfo) 
         System.out.println("LazyPropagationIDWithBPT:  transformInitialPotential ----- BEGIN");
         
      
      Relation r = new Relation(p);
      r.setKind(kind);
      Potential potNew = transformInitialRelationWithoutCheck(r, importatVariables).getValues();
         
         
      if (generateDebugInfo)   
         System.out.println("LazyPropagationIDWithBPT:  transformInitialPotential ----- END");
      
      // It is a constraint and there is nothing to do with it
      return potNew;
   }

   
   
   /**
    * Transforms one of the original relations into another one whose values are
    * of class
    * <code>PotentialTree</code>, and adding the effect of the possible
    * constraints. This is done for normal relations, but not for constraints
    * relations @ param r the
    * <code>Relation</code> to be transformed. @Override method in Propagation
    * class
    */
    @Override
   public Relation transformInitialRelation(Relation r) {
      if (generateDebugInfo) {
         System.out.println("LazyPropagationID:  transformInitialRelation ----- BEGIN");
         System.out.println("LazyPropagationID:  transformInitialRelation ----- END");

      }
      
      // It is a constraint and there is nothing to do with it
      return r;
   }
     /**
    * Transforms one of the original relations into another one whose values are
    * of class
    * <code>PotentialTree</code>, and adding the effect of the possible
    * constraints. This is done for normal relations, but not for constraints
    * relations @ param r the
    * <code>Relation</code> to be transformed. @Override method in Propagation
    * class
    */
 
   protected Relation transformInitialRelationWithoutCheck(Relation r, Vector<Node> importatVariables) {
      if (generateDebugInfo) {
         System.out.println("LazyPropagationID:  transformInitialRelationWithoutCheck ----- BEGIN");
         System.out.println("LazyPropagationID:  transformInitialRelationWithoutCheck ----- END");
      }
      // It is a constraint and there is nothing to do with it
      return r;
   }
  
    
     /**
    * Transforms one of the original relations into another one whose values are
    * of class
    * <code>PotentialTree</code>, and adding the effect of the possible
    * constraints. This is done for normal relations, but not for constraints
    * relations @ param r the
    * <code>Relation</code> to be transformed. @Override method in Propagation
    * class
    */
 
   protected Relation transformInitialRelationWithoutCheck(Relation r) {
      if (generateDebugInfo) {
         System.out.println("LazyPropagationID:  transformInitialRelationWithoutCheck ----- BEGIN");
         System.out.println("LazyPropagationID:  transformInitialRelationWithoutCheck ----- END");
      }
      // It is a constraint and there is nothing to do with it
      return r;
   }
  

   /**
    * Private method for building a tree node
    */
   public void buildStrongJunctionTree() {
      if (generateDebugInfo) {
         System.out.println("LazyPropagationID:  buildStronJunctionTree ----- BEGIN");
      }

      tree = new StrongJunctionTree((IDiagram) network, triangulationCriteria, propagationCriteria,
              variableEliminationCriteria, generateDebugInfo, generateStatistics, this);
   
      // If it is needed, set the reference to the statistics objetc
      // for the tree
      if (generateStatistics) {
         tree.setStatistics(statistics);
      }  
      if (generateDebugInfo) {
         System.out.println("LazyPropagationID:  buildStronJunctionTree ----- END");
      }
   }

   /**
    * Method for getting the current relations
    *
    * @return currentRelations
    */
   public RelationList getCurrentRelations() {
      if (generateDebugInfo) {
         System.out.println("LazyPropagationID:  getCurrentRelations ----- BEGIN");
         System.out.println("LazyPropagationID:  getCurrentRelations ----- END");
      }
      return currentRelations;
   }

   
   
   /**
    * returns hashmap where the keys are the decision nodes
    * and the values the decision tables
    * @return 
    */
   public HashMap<Node, Potential> getResultsHashMap(){
       return tree.getResults();
   }

   
   
   /**
    * Method for getting the flag that indicates if the potentials
    * are transformed when the strong junction tree is built
    * @return 
    */
    public boolean isTransformAtTree() {
        return transformAtTree;
    }
   
   
   
   
   /**
    * Public method for making the propagation
    */
   public void propagate() {
      if (generateDebugInfo) {
         System.out.println("LazyPropagationID:  propagate ----- BEGIN");
      }

       currentRelations.changeKind(Relation.CONDITIONAL_PROB, Relation.POTENTIAL);
       currentRelations.setHeadsAndTails(Relation.POTENTIAL);
      
      
      // Make the strong junction tree
       
      if(tree == null) 
        buildStrongJunctionTree();
      
     if(eliminationHeuristic != -1)
         tree.triangulationCriteria = eliminationHeuristic;
      
     
     //Sets if the evaluation is qualitative or quantitative
     Potential.setQualitativeOps(qualitativeEval);
     
     
      // Call the proper method for propagation
      tree.propagate();
      
      //Checks if the decision tables have been computed
      checkDecisionTables();
      


      if (generateDebugInfo) {
         System.out.println("LazyPropagationID:  propagate ----- END");
      }
   }

   /**
    * Checks if an influence diagram has the properties required for being
    * evaluated
    *
    * @return true or false
    */
   public boolean initialConditions() {
      boolean state = false;
      String errorMessage = null;
      IDiagram diag = (IDiagram) network;

      if (generateDebugInfo) {
         System.out.println("LazyPropagationID:  initialConditions ----- BEGIN");
      }

      // Makes the checks if avaluable is false
      if (evaluable == EvaluableStates.NOCHECKED) {

         // Check if all the links are directed
         state = diag.directedLinks();

         // Error if evaluable is false and return false
         if (state == false) {
            errorMessage = "Influence Diagram with no directed links\n\n";
         } else {
            // Check the presence of cycles
            state = diag.hasCycles();
            if (state == true) {
               errorMessage = "Influence Diagram with cycles\n\n";
            } else {
               // Add non forgetting arcs

               diag.addNonForgettingArcs();
               diag.eliminateRedundancy();

               
               ((IDiagram)diag).setPreprocessed(true);
               

               // Check if there is a path linking all the decisions
               state = diag.pathBetweenDecisions();
               if (state == false) {
                  errorMessage = "Influence Diagram with non ordered decisions\n\n";
               } else {
                  // Transform the set of initial relations
                  currentRelations = getInitialRelations();
                  diag.setRelationList(currentRelations.getRelations());
                  //Save the initial utilites in the statistics
                  // saveInitialUtilities();
                  
                   currentRelations.changeKind(Relation.CONDITIONAL_PROB, Relation.POTENTIAL);
                   currentRelations.setHeadsAndTails(Relation.POTENTIAL);

                   
                  
                  
               }
            }
         }
      }

      // Prints the error message if needed
      if (!state) {
         evaluable = EvaluableStates.NOEVALUABLE;
         System.out.println(errorMessage);
      } else {
         evaluable = EvaluableStates.EVALUABLE;
      }

      if (generateDebugInfo) {
         System.out.println("LazyPropagationID:  initialConditions ----- END");
      }

      // Return true
      return state;
   }

   /**
    * Method to check if all the decision tables are computed
    */
   public void checkDecisionTables() {
      NodeList decisions = tree.diag.getDecisionList();
      JunctionTreeNode clique;
      Potential table;
      Node decision;

      if (generateDebugInfo) {
         System.out.println("LazyPropagationID:  checkDecisionTables ----- BEGIN");
      }

      // Consider the decisions one by one
      for (int i = decisions.size() - 1; i >= 0; i--) {
         decision = decisions.elementAt(i);

         //Check if the table for this decision is computed
         table = tree.results.get(decision);

         if (table == null) {
            if (generateDebugInfo) {
               System.out.println("No computada tabla para " + decision.getName());
            }

            // Get the clique where to compute the policy for this variable
            clique = tree.getNearestClique(decision);

            // Now, remove the decision variable
            clique.computeDecisionTable(decision);
            
            
         }
      }
      
        //Checks if the MEU have been computed
      if(isComputeMEU() && statistics.getFinalExpectedUtility()==null) {
          tree.getRoot().removeAllVariables();
      }    


      // At the end, force the generation of the file with the
      // statistics data, if needed
      if (generateStatistics) {
         tree.printFinalStatisticsData();
      }
      
      updateResults();

      if (generateDebugInfo) {
         System.out.println("LazyPropagationID:  checkDecisionTables ----- END");
      }
   }

   public static void main(String[] args) throws ParseException, IOException {
      String networkName = null;
      boolean debug = false;
      boolean statistics = false;
      int triangulationCriteria = -1;
      int propagationCriteria = -1;
      int eliminationCriteria = -1;
      int i;

      // Check the arguments passed to the program
      if (args.length < 2) {
         // Show how the program must be called
         usage();
      } else {
         // Consider the arguments one by one
         i = 0;
         while (i < args.length) {
            if (args[i].equals("-net")) {
               networkName = args[i + 1];
               i = i + 2;
            } else {
               if (args[i].equals("-g")) {
                  debug = true;
                  i++;
               } else {
                  if (args[i].equals("-s")) {
                     statistics = true;
                     i++;
                  } else {
                     if (args[i].equals("-t")) {
                        triangulationCriteria = Integer.parseInt(args[i + 1]);
                        i = i + 2;
                     } else {
                        if (args[i].equals("-e")) {
                           eliminationCriteria = Integer.parseInt(args[i + 1]);
                           i = i + 2;
                        } else {
                           if (args[i].equals("-p")) {
                              propagationCriteria = Integer.parseInt(args[i + 1]);
                              i = i + 2;
                           } else {
                              usage();
                           }
                        }
                     }
                  }
               }
            }
         }
      }

      // If everything is ok makes the evaluator
      Network net = Network.read(networkName);
      LazyPropagationID evaluator = new LazyPropagationID((IDiagram) net, triangulationCriteria,
              propagationCriteria, eliminationCriteria, debug, statistics);

      // Set the name for the statistics file
      String base = networkName.substring(0, networkName.lastIndexOf('.'));
      base = base.concat("_LazyPropagationID_data");
      evaluator.statistics.setFileName(base);

      // Make the propagation
      evaluator.propagate();

      // Now check if all the decision tables are computed
      evaluator.checkDecisionTables();

      // Print the results
      //evaluator.tree.printResults();
   }
   
     /**
    * Gets a vector with the results
    * @return 
    */

   public void updateResults(){
       
       
       if(getStrongJunctionTree() != null) {
            
            HashMap<Node, Potential> resultsHash = getStrongJunctionTree().getResults();
            
            
            
            
        //    Iterator<Potential> iterator = resultsHash.values().iterator();
            
            Iterator<Node> iterator = resultsHash.keySet().iterator();
            while(iterator.hasNext()) {
          

                Node node = iterator.next();
                Potential eU = resultsHash.get(node);
                
                results.add(0, eU.sendVarToEnd(node));
                if(generateStatistics)
                    this.getStatistics().addUtilityHistory(0, eU);
                
                
                
            }
   
            
     /*       //Puts the initial utility at the first position
            int size = this.getStatistics().getUtilityHistory().size();
            Potential initUtil = this.getStatistics().removeUtilityHistory(size-1);
            this.getStatistics().addUtilityHistory(0, initUtil);
*/
            
       }
      
   }
     
   /**
    * private method for saving the initial utilites in the statistics
    */
   
   private void saveInitialUtilities() {
       
       if(!generateStatistics)
           return;
       
       RelationList relations = this.getCurrentRelations();
       Relation r;
       for(int i=0; i<relations.size(); i++) {
           r = relations.elementAt(i);
           if(r.getKind()==Relation.UTILITY)
               statistics.addUtilityHistory(r.getValues());
          
       }
   }
   
   

   /**
    * Method to show how the program must be called
    */
   private static void usage() {
      System.out.println("Use: LazyPropagationID -net iDiagramFile");
      System.out.println("       [-g] (generate debug information) ");
      System.out.println("       [-s] (generate statistics)");
      System.out.println("       [-t] (triangulation criteria)");
      System.out.println("       [-p] (propagation criteria: direct elimination (1), message passing (2))");
      System.out.println("       [-e] (elimination criteria: online triangulation (2), offline triangulation (1))");
      System.exit(0);
   }
}
