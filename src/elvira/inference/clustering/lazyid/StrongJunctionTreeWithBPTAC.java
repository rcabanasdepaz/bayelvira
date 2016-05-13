package elvira.inference.clustering.lazyid;

import elvira.IDiagram;
import elvira.Graph;
import elvira.Link;
import elvira.InvalidEditException;
import elvira.NodeList;
import elvira.Relation;
import elvira.Node;
import elvira.potential.LogicalExpression;
import elvira.LogicalNode;

import java.util.Vector;

public class StrongJunctionTreeWithBPTAC extends StrongJunctionTreeWithBPT {

   /**
    * Constructor receiving an ID as argument
    * @param IDiagram to triangulate
    * @param threshold
    * @param triangulationCriteria used for triangulation
    * @param propagationCriteria message passing or direct elimination
    *        of variables
    * @param variableEliminationCriteria using on-line triangulation
    *        or off-line triangulation
    * @param generateDebugInfo debug information flag
    * @param generateStatistics statistics information flag
    */
   public StrongJunctionTreeWithBPTAC(IDiagram diag, double threshold, int triangulationCriteria, 
                                     int propagationCriteria, int variableEliminationCriteria, 
                                     boolean generateDebugInfo, boolean generateStatistics, LazyPropagationIDWithBPTAC propagationID) {

   
      super(diag, threshold, threshold, triangulationCriteria, propagationCriteria, variableEliminationCriteria, generateDebugInfo, generateStatistics, propagationID); 

   }

   /**
    * Private method for building a tree node
    * @param tree
    * @param clique to include in the node
    * @param index
    * @Override method in super class
    */
   public JunctionTreeNode buildTreeNode(StrongJunctionTree tree, NodeList clique, int index) {
      if (generateDebugInfo) {
         System.out.println("StrongJunctionTreeWithBPACT: buildTreeNode ----- BEGIN");
      }
      JunctionTreeNode node=new JunctionTreeNode(tree, clique, index, generateDebugInfo);
      if (generateDebugInfo) {
         System.out.println("StrongJunctionTreeWithBPTAC: buildTreeNode ----- END");
      }
      return node;
   }

   /**
    * Private method for adding constraint links between the variables
    * related to the constraints
    * @param graph to modify
    */
   public void addConstraintLinks(Graph graph) {
      LogicalExpression logExp;
      LogicalNode antecedent;
      LogicalNode consecuent;
      Node varAnt;
      Node varCons;
      NodeList nodes = graph.getNodeList();
      Vector antecedentVariables;
      Vector consecuentVariables;
      Relation relation;
      Link link;

      if (generateDebugInfo) {
         System.out.println("StrongJunctionTreeWithBPACT: addConstraintLinks ----- BEGIN");
      }
      
      // Consider the relations one by one, looking for constraints
      Vector relations = diag.getRelationList();
      for (int i = 0; i < relations.size(); i++) {
         relation = (Relation) relations.elementAt(i);

         // Consider if it is a constraint
         if (relation.getKind() == Relation.CONSTRAINT) {
            logExp = (LogicalExpression) (relation.getValues());
            antecedent = logExp.getAntecedent();
            consecuent = logExp.getConsecuent();
            antecedentVariables = antecedent.getVariables();
            consecuentVariables = consecuent.getVariables();

            // Make links from antecedents to consecuents, if needed
            for (int j = 0; j < antecedentVariables.size(); j++) {
               varAnt = nodes.getNode(((Node) antecedentVariables.elementAt(j)).getName());
               for (int k = 0; k < consecuentVariables.size(); k++) {
                  varCons = nodes.getNode(((Node) consecuentVariables.elementAt(k)).getName());

                  // Check if the link from varAnt to varCons exists
                  link = graph.getLink(varAnt, varCons);

                  // If it does not exist, add it
                  if (link == null) {
                     try {
                        graph.createLink(varAnt, varCons, false);
                     } catch (InvalidEditException e) {
                        System.out.println("Error when adding constraint arc");
                        System.out.println("Method: addConstraintLinks");
                        System.out.println("Class: StrongJunctionTreeWithPTAC");
                        System.exit(0);
                     }
                  }
               }
            }
         }
      }
      
      if (generateDebugInfo) {
         System.out.println("StrongJunctionTreeWithBPACT: addConstraintLinks ----- END");
      }
   }

   /**
    * Method for applying constraints
    */
   protected void applyConstraints(){
      // Begin the process with the root node
    //  root.applyConstraints(true);
   }   
}
