package elvira.inference.clustering.lazyid;

import elvira.*;
import elvira.tools.idiagram.pairtable.IDPairTable;
import elvira.potential.Potential;
import elvira.potential.PotentialTree;
import elvira.potential.LogicalExpression;
import elvira.tools.OperationsList;
import elvira.tools.Crono;
import elvira.tools.PropagationStatistics;
import elvira.tools.idiagram.pairtable.CombPairTable;
import java.io.*;
import java.util.ArrayList;
import java.util.Vector;
import java.util.HashMap;
import java.util.Set;
import java.util.Collection;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StrongJunctionTree {

    /**
     * Constants for the criteria used for removing the variables of
     * the cliques during recursive message passing procedure
     */
    public static final int OFFLINE_TRIANGULATION = 1;
    public static final int ONLINE_TRIANGULATION = 2;
    public static final int SYMBOLIC_PROB_INFERENCE= 3;
    
    /**
     * Constants for the method used for removing variables: using the
     * elimination order used for triangulation directly or using
     * message passing algorithm
     */
    public static final int DIRECT_ELIMINATION = 1;
    public static final int MESSAGE_PASSING = 2;
    
    
    
    /**
     * Root node of the junction tree
     */
    protected JunctionTreeNode root;
    
    /**
     * Data member to contain the pair list used for
     * guiding the triangulation
     */
    private IDPairTable pairTable;
    
    /**
     * NodeList to contain the orden of elimination used for
     * triangulation. The last position corresponds to the
     * first node to remove
     */
    private ArrayList<String> numeration;
    
    /**
     * HashMap to contain the cliques. The key of a clique it
     * will be its index related to the junction tree construction
     */
    private HashMap<Integer, NodeList> indexedCliques;
    
    /**
     * HashMap to contain the decision tables produces as a result
     * of the evaluation
     */
    protected HashMap<Node, Potential> results;
    
    /**
     * Diagram to evaluate
     */
    protected IDiagram diag;
    
    /**
     * Data member to show if propagation statistics will be used
     */
    protected boolean generateStatistics=true;
    /**
     * Data member to show if the propagation will generate debug
     * info
     */
    protected boolean generateDebugInfo=true;
    /**
     * Data member to store the triangulation criteria
     */
    protected int triangulationCriteria;
    /**
     * Data member for storing the kind of propagation: with direct
     * elimination or using message passing
     */
    protected int propagationCriteria;
    /**
     * Data member used for fixing a criteria for removing the
     * variables of a clique
     */
    protected int variableEliminationCriteria;
    /**
     * Data member to control the time of execution
     */
    protected Crono crono;
    /**
     * Data member to store the statistics
     */
    protected PropagationStatistics statistics;
    
    /**
     * Operations list for improving the data collection about
     * the evaluation process
     */
    private OperationsList operations;
    
    /**
     * Controls the total size of potentials
     */
    private double totalSize;


    protected boolean saveTriangulationProcess = true;
    protected PrintWriter triangulationOutput;
   
   protected LazyPropagationID propagationID;
   

   
      
      
    /**
     * Constructor receiving an ID as argument
     * @param diag IDiagram to triangulate
     * @param triangulationCriteria for triangulation
     * @param propagationCriteria kind of propagation
     * @param eliminationCritera criteria used for removing variables: offline triangulation,
     *                        online triangulation
     * @param debug flag for debug information
     * @param statistics flag for statistics information
     */
    public StrongJunctionTree(IDiagram diag, int triangulationCriteria, 
            int propagationCriteria, int eliminationCriteria, 
            boolean debug, boolean statistics, LazyPropagationID propagationID) {
        Graph graphCopy;
        
        if (debug) {
           System.out.println("   StrongJunctionTree:  class Constructor ----- BEGIN");
           System.out.println("   Triangulation criteria: "+triangulationCriteria);
           System.out.println("   Propagation criteria: "+propagationCriteria);
           System.out.println("   EliminationCriteria: "+eliminationCriteria);
        }

        numeration = new ArrayList<String>();
        indexedCliques = new HashMap<Integer, NodeList>();
        results = new HashMap<Node, Potential>();
        this.diag = diag;
        this.triangulationCriteria = triangulationCriteria;
        this.propagationCriteria = propagationCriteria;
        this.variableEliminationCriteria = eliminationCriteria;
        generateDebugInfo = debug;
        generateStatistics = statistics;
        crono = new Crono();
        this.propagationID = propagationID;
        
        saveTriangulationProcess=false;
        
        if(saveTriangulationProcess)
            try {
            triangulationOutput = new PrintWriter(new File("stats/triangulationProcess_"+this.triangulationCriteria+"_"+this.diag.getName()+".m"));
        //    triangulationOutput = new PrintWriter(System.out, true);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(StrongJunctionTree.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        //    triangulationOutput = new PrintWriter(System.out, true);

        // Show debug information if needed
        if (generateDebugInfo) {
            System.out.println("\tConstruccion del arbol de cliques..........");
        }

        // Get a pair table with the elimination order
        pairTable = new IDPairTable(diag, new Evidence());

        // Set the criteria for the triangulation
        pairTable.setIDCriteria(triangulationCriteria);

        
        
        
        // Process the diagram
        graphCopy = processDiagram(diag);

        // Decide the set of cliques
        // Before that, generate debug information if needed
        if (generateDebugInfo) {
            System.out.println("\tComienza procedimiento de determinacion de cliques....");
        }
        determineCliques(graphCopy);

        // Arrange the cliques
        arrangeCliques();

        // Print the structure
        if (generateDebugInfo) {
            System.out.println("\n\nEstructura final del arbol de cliques generado");
            root.print();
            System.out.println("----------------------------------------------\n\n\n");
        }

        
        
        
        // Assign potentials to cliques
        if (generateDebugInfo) {
            System.out.println("\tComienza asignacion de potenciales....");
        }
        assignPotentials();
        
        if (generateDebugInfo) {
           System.out.println("StrongJunctionTree:  class Constructor ----- END");
        }
    }

    /**
     * Private method for making the process of the diagram: moralization,
     * triangulation and so on, as a previous step for building the cliques
     * @param diag to process
     * @return processed graph
     */
    protected Graph processDiagram(IDiagram diag) {
        Graph graph;
        Graph graphCopy;
        
        if (generateDebugInfo) {
           System.out.println("StrongJunctionTree:  processDiagram ----- BEGIN");
        }

        // Triangulate it. Before that, copy the IDiagram to remove
        // the utility nodes and the informative arcs
        IDiagram diagCopy = diag.copy();

        // Remove informativeArcs
        diagCopy.removeInformativeArcs();

        // Generate debug information if needed
        if (generateDebugInfo) {
            System.out.println("Eliminados los arcos informativos.........");
        }

        
        
        // Now, it is time to moralize the IDiagram
        graph = diagCopy.moral();

        


        
        // Add undirected links for variables linked through constraints.
        // For this class this method does nothing
        addConstraintLinks(graph);

        // Now remove utility nodes in the moralized graph
        graph.removeUtilityNodes();
        
        if(saveTriangulationProcess){
            graph.saveGraphMatlab(triangulationOutput, "Moral graph");
            triangulationOutput.println("moralGraph={NODES LINKS KIND};\n\n");
        }
        
        // Before triangulate, make a copy of the graph. This is required:
        // the triangulation method is based on removing nodes from the graph
        graphCopy = graph.duplicate();

        // Make the triangulation
        LinkList fillIns = triangulateGraph(graph);
        
        if(saveTriangulationProcess)
            triangulationOutput.flush();

        // Complete the graph with the fillIns links
        completeGraph(graphCopy, fillIns);

        // Print the reduced graph
        try {
            graphCopy.save("triangulado");
        } catch (IOException e) {
        }
        ;
        
        if (generateDebugInfo) {
           System.out.println("StrongJunctionTree:  processDiagram ----- END");
        }

        // Return graphCopy
        return graphCopy;
    }
    
    /**
     * Private method for adding constraint links between the variables
     * related to the constraints
     * @param graph to modify
     */
    public void addConstraintLinks(Graph graph) {
    }

    /**
     * Method for setting the statistics object to keep
     * information about the evaluation process
     * @param statistics
     */
    public void setStatistics(PropagationStatistics statistics){
       this.statistics=statistics;
    }

    
    
        /**
     * Method for getting the statistics object to keep
     * information about the evaluation process
     * @return statistics
     */
    public PropagationStatistics getStatistics() {
        return statistics;
    }
    
    
    
    
    
    
    /**
     * Method for getting the value of triangulationCriteria data member
     * @return triangulationCriteria
     */
    public int getTriangulationCriteria() {
        return triangulationCriteria;
    }

    /**
     * Method for getting the elimination criteria
     * @return eliminationCriteria
     */
    public int getVariableEliminationCriteria() {
        return variableEliminationCriteria;
    }


    /**
     * Method for getting the statistics flag
     * @return generateStatistics
     */
    public boolean getStatisticsFlag() {
        return generateStatistics;
    }
    
    /**
     * Method for getting the debug flag
     * @return generateDebugInfo
     */
    public boolean getDebugFlag(){
        return generateDebugInfo;
    }

    public int getSpiCriteria() {
        return propagationID.getSpiCriteria();
    }

    public void setSpiCriteria(int spiCriteria) {
        propagationID.setSpiCriteria(spiCriteria);
    }
    
    
    
    
    
    

    
    /**
     * Method for getting the root of the clique tree
     * @return 
     */
    public JunctionTreeNode getRoot() {
        return root;
    }

    

    
    /**
     * Method for propagating, depending on the criteria
     * used
     */
    public void propagate(){
        if (generateDebugInfo) {
           System.out.println("StrongJunctionTree:  propagate ----- BEGIN");
        }
        
        // Initialize the crono
        crono.start();

        // Set the statistics if required
        if (generateStatistics) {
           
            // Creates the list of operations
            operations=new OperationsList();
            
            // Add initial information about the diagram
            statistics.addOperation("Start: ");
            totalSize=getSize();
            statistics.addSize(totalSize);
            statistics.addTime(crono.getTime());
        }

        // As a previous step, apply constraints if needed
        applyConstraints();

        switch(propagationCriteria){
            case StrongJunctionTree.MESSAGE_PASSING:
                propagateWithMessagePassing();
                break;
            case StrongJunctionTree.DIRECT_ELIMINATION:
                propagateWithDirectElimination();
                break;
            default:
                System.out.println("Wrong criteria for propagation.....");
                System.exit(0);
        }
        
        if (generateDebugInfo) {
           System.out.println("StrongJunctionTree:  propagate ----- END");
        }
        
        if (generateStatistics) {
           try{
            statistics.printOperationsAndSizes();
           }catch(IOException e){
              System.out.println("Problem printing statistics information");
              System.exit(0);
           }
        }
    }

    /**
     * Method for applying constraints
     */
    protected void applyConstraints(){
        if (generateDebugInfo) {
           System.out.println("StrongJunctionTree:  applyConstraints ----- BEGIN");
           System.out.println("StrongJunctionTree:  applyConstraints ----- END");
        }
    }

    /**
     * Method for propagating on the strong junction tree
     */
    public void propagateWithMessagePassing() {
        JunctionTreeNode inferiorNeighbour;

        if (generateDebugInfo) {
           System.out.println("StrongJunctionTree:  propagateWithMessagePassing ----- BEGIN");
           System.out.println("Root is clique "+root.getIndex());
        }

        // The process begin on the root node, calling to collectEvidence
        // on its inferior neighbours
        for (int i = 0; i < root.down.size(); i++) {
            inferiorNeighbour = root.down.get(i).getInferiorNeighbour();

            // Call collectEvidence on it and after that, absorbEvidence
            if (generateDebugInfo) {
                System.out.println("Comienza fase collect.......");
            }
            inferiorNeighbour.collectEvidence();

            if (generateDebugInfo) {
                System.out.println("Comienza fase absorb.......");
            }
            inferiorNeighbour.absorbEvidence();
        }
        
        if (generateDebugInfo) {
           System.out.println("StrongJunctionTree:  propagateWithMessagePassing ----- END");
        }
    }

    /**
     * Method for making the propagation following the order used for
     * triangulation
     */
    public void propagateWithDirectElimination() {
        ArrayList<JunctionTreeNode> cliques = new ArrayList<JunctionTreeNode>();
        ArrayList<Node> nodes = new ArrayList<Node>();
        JunctionTreeNode clique;
        Node node;
        boolean[] lastOperationsOnCliques;
        String nodeName;
        
        if (generateDebugInfo) {
           System.out.println("StrongJunctionTree:  propagateWithDirectElimination ----- BEGIN");
        }

        for (int i = numeration.size() - 1; i >= 0; i--) {
            nodeName = numeration.get(i);

            // For every node, locate the clique where the variable must be
            // deleted
            clique = getNearestClique(nodeName);
            System.out.println("CLIQUE ASOCIADO A VARIABLE "+nodeName+": "+clique.index);
            node = clique.variables.getNode(nodeName);
            cliques.add(clique);
            nodes.add(node);
        }

        // Now create the array of flags marking the last operations on cliques
        lastOperationsOnCliques = markLastOperationsOnCliques(cliques);

        // Print the cliques indexes according to the order obtained
        for (int i = 0; i < cliques.size(); i++) {
            clique = cliques.get(i);
        }

        // Now it is time for removing the variables in the order stablished by
        // numerations and taking into account the operations that require passing
        // messages to the separators
        for (int i = 0; i < nodes.size(); i++) {
            node = nodes.get(i);
            clique = cliques.get(i);
            switch (node.getKindOfNode()) {
                case Node.CHANCE:
                    clique.removeChanceVariable(node, lastOperationsOnCliques[i]);
                    break;
                case Node.DECISION:
                    clique.removeDecisionVariable(node, lastOperationsOnCliques[i]);
                    break;
                default:
                    System.out.println("Invalid node kind: " + node.getKindOfNode());
                    System.out.println("Method: propagateWithTrinagulationorder; Class: StrongJunctionTree");
                    System.exit(0);
            }

            // If it is the last operation, set potentials to the upper separator
            if (lastOperationsOnCliques[i] == true && clique.up != null) {
                clique.setPotentialsToUpSeparator();
            }
System.out.println("Eliminando: "+node.getName());

            // If it is needed to print statistics, do it
            if (generateStatistics == true) {
                if (lastOperationsOnCliques[i] == true) {
                    statistics.addSize(getSize());
                    statistics.addTime(crono.getTime());
                }
            }
        }
        if (generateDebugInfo) {
           System.out.println("StrongJunctionTree:  propagateWithDirectElimination ----- END");
        }
    }

    /**
     * Public method to order the variables according to the deletion
     * sequence
     * @param variablesList the list of variables to order
     */
    public ArrayList<Node> orderVariablesWithOfflineTriangulation(ArrayList<Node> variablesList) {
        ArrayList<Node> finalOrder = new ArrayList<Node>();
        Node node;
        int biggestIndex;
        int order = numeration.size();
        
        if (generateDebugInfo) {
           System.out.println("StrongJunctionTree:  orderVariablesWithOfflineTriangulation ----- BEGIN");
        }

        // Consider the variables one by one
        for (int i = 0; i < variablesList.size(); i++) {

            // Check the elimination order to detect the biggest number
            // related to the variablesList variables, from i position
            // till the end
            node = firstToRemove(variablesList, order);

            // Update the value of order
            order = getOrderOfDeletion(node);

            // Insert the first node into finalOrder
            finalOrder.add(node);
        }
        
        if (generateDebugInfo) {
           System.out.println("StrongJunctionTree:  orderVariablesWithOfflineTriangulation ----- END");
        }

        // return finalOrder
        return finalOrder;
    }

    /**
     * Public method for setting a given elimination order
     * @param fileName with the order of elimination
     */
    public void setEliminationOrder(String fileName) {
        numeration = new ArrayList<String>();
        FileReader file = null;
        
        if (generateDebugInfo) {
           System.out.println("StrongJunctionTree:  setEliminationOrder ----- BEGIN");
        }
        
        try {
            file = new FileReader(fileName);
        } catch (FileNotFoundException e) {
            System.out.println("Elimination order description file " + fileName + " nor found");
            System.exit(0);
        }
        BufferedReader reader = new BufferedReader(file);

        // Read the names of the variables
        String varName;
        try {
            while ((varName = reader.readLine()) != null) {
                numeration.add(varName);
            }
        } catch (IOException e) {
        }
        ;

        // Close the descriptors
        try {
            reader.close();
            file.close();
        } catch (IOException e) {
        }
        ;
        
        if (generateDebugInfo) {
           System.out.println("StrongJunctionTree:  setEliminationOrder ----- END");
        }
    }
    
    /**
     * Adds a new operation to the list of operations
     * @param node 
     */
    public void addOperation(Node node){
       operations.addOperation(node);
    }

    /**
     * Sets a new size for the current operation
     * @param size 
     */
    public void addSize(long size){
       operations.addSize(totalSize+size);
    }
    
    /**
     * Defines the statistics measures about a new operation
     */
    public void defineStatisticsMeasures(){
       // Store the size of the diagram at this moment
       totalSize = getSize();
            
       // Gets the max size for the operation
       double maxSize=operations.getMaxSize();

       if (maxSize < totalSize){
          maxSize=totalSize;
       }
       
       // Stores the max size for the operation
       statistics.addSize(maxSize);
       statistics.addTime(crono.getTime());
    }
    
    /**
     * Computes the total size
     */
    public void setTotalSize(){
       totalSize=this.getSize();
    }

    
    /**
     * Indicates if potentials are transformed after each operation
     * @param onlyInitialTransformation 
     */
    /**
     * Indicates if potentials are transformed after each operation
     */
    public boolean isOnlyInitialTransformation() {
        return this.propagationID.isOnlyInitialTransformation();
    }

    
    
    public LazyPropagationID getPropagationID() {
        return propagationID;
    }
    
    
    
    
    
    
    
    /**
     * Private method to get the index of the variable that must be
     * deleted before
     * @param variablesList list of variables to check
     * @param max max value for the order of elimination
     * @return first index of the variable to delete in the sequence:
     *         the one with max index under max argument
     */
    private Node firstToRemove(ArrayList<Node> variablesList, int max) {
        Node node;
        int order = 0;
        int maxOrder = -1;
        int maxIndex = 0;
        if (generateDebugInfo) {
           System.out.println("StrongJunctionTree:  firstToRemove ----- BEGIN");
        }

        for (int i = 0; i < variablesList.size(); i++) {
            node = variablesList.get(i);

            // Check for the order of elimination of this variable
            order = getOrderOfDeletion(node);

            // Update the max if needed
            if (order > maxOrder && order < max) {
                maxOrder = order;
                maxIndex = i;
            }
        }
        
        if (generateDebugInfo) {
           System.out.println("StrongJunctionTree:  firstToRemove ----- END");
        }

        // Ath the end return maxIndex
        return variablesList.get(maxIndex);
    }

    /**
     * Private method to get the order of deletion for a given node
     * @param node to check
     * @return order of deletion, according to numeration order
     */
    private int getOrderOfDeletion(Node node) {
        String name;

        return numeration.indexOf(node.getName());
    }

    /**
     * Private method to determine the cliques for a given
     * graph passed as argument
     * @param graph to consider
     */
    private void determineCliques(Graph graph) {
        NodeList candidate;
        NodeList nodes = graph.getNodeList();
        NodeList neigbours;
        Node node;
        String nodeName;
        int numNeighbours;
        int index;
        boolean contained;

        if (generateDebugInfo) {
           System.out.println("StrongJunctionTree:  determineCliques ----- BEGIN");
        }
        
        // Consider the nodes as they are in the hashMap, one
        // by one. For every one, consider a clique with the
        // node itself and its neighbours. We begin with the
        // last node to remove
        for (int i = numeration.size() - 1; i >= 0; i--) {
            // Get the name of the node
            nodeName = numeration.get(i);

            if (generateDebugInfo) {
                System.out.println("Considerando nodo para generar cliques: " + nodeName);
            }

            // Initialize candidate
            candidate = new NodeList();

            // Get the node in the graph
            node = nodes.getNode(nodeName);

            // Check node is not null
            if (node != null) {
                // Insert the node into candidate clique
                candidate.insertNode(node);

                // Get its neighbours
                numNeighbours = node.getNumNeighbours();
                for (int j = 0; j < numNeighbours; j++) {
                    candidate.insertNode(node.getNeighbourAt(j));
                    if (generateDebugInfo) {
                        System.out.println("      Agregando : " + node.getNeighbourAt(j).getName());
                    }
                }

                // Finally, check if this clique is already in cliques
                contained = isCliqueContained(candidate);

                // If is not included, add it
                if (contained == false) {
                    // Determine the index of the clique
                    index = determineIndex(candidate);
                    if (generateDebugInfo) {
                        System.out.println("Constituido clique de indice: " + index +" ("+candidate.size()+" variables)");
                        candidate.printNames();
                    }
                    indexedCliques.put(index, candidate);
                }

                // Finally, remove the node from the graph
                try {
                    graph.removeNode(node);
                } catch (InvalidEditException e) {
                    System.out.println("Error removing node from graph");
                    System.out.println("Method DetermineCliques");
                    System.out.println("Class StrongJunctionTree");
                    System.exit(0);
                }
            } else {
                System.out.println("Error retrieving node");
                System.out.println("Method: determineCliques");
                System.out.println("Class: StrongJunctionTree");
                System.exit(0);
            }
        }
        
        if (generateDebugInfo) {
           System.out.println("StrongJunctionTree:  determineCliques ----- END");
        }
    }

    /**
     * Private method to complete the graph with a set of fillins
     * links, passed as argument
     * @param graph to complete
     * @param fillIns, list of fillins arcs
     */
    protected void completeGraph(Graph graph, LinkList fillIns) {
        NodeList nodes = graph.getNodeList();
        Node head;
        Node tail;
        Link link;

        if (generateDebugInfo) {
           System.out.println("StrongJunctionTree:  completeGraph ----- BEGIN");
        }
        
        for (int i = 0; i < fillIns.size(); i++) {
            link = fillIns.elementAt(i);
            tail = nodes.getNode(link.getTail().getName());
            head = nodes.getNode(link.getHead().getName());

            // Add it to graph
            try {
                graph.createLink(tail, head, false);
            } catch (InvalidEditException e) {
                System.out.println("Error when adding filling arc");
                System.out.println("Method: completeGraph");
                System.out.println("Class: StrongJunctionTree");
                System.exit(0);
            }
        }
        
        if (generateDebugInfo) {
           System.out.println("StrongJunctionTree:  completeGraph ----- END");
        }
    }

    /**
     * Method to produce the triangulation of the graph
     * @return the list of links to add to tge graph in order
     * to triangulate it
     */
    protected LinkList triangulateGraph(Graph graph) {
        Node node;
        Node nodeID;
        NodeList nodesInGraph;
        NodeList simplicials;
        LinkList partialAdded;
        LinkList globalAdded = new LinkList();
        Link link;
        int order = 1;

        if (generateDebugInfo) {
           System.out.println("StrongJunctionTree:  triangulateGraph ----- BEGIN");
        }
        
        if(saveTriangulationProcess)
            triangulationOutput.println("ORDER={};\nSET_GRAPHS={};");
        
        
        // Now consider the elimination order stored in pairTable
        pairTable.setIDCriteria(triangulationCriteria);
        while (pairTable.size() != 0) {
            node = pairTable.nextToRemoveIDWithCriteriaRemoving();
            node = diag.getNode(node.getName());

            // Add the node to the arrayList
            numeration.add(0, node.getName());
            if (generateDebugInfo) {
                System.out.println("Eliminacion de: " + node.getName());
            }
            order++;

            // Deal with the node if it was not reduced
            nodesInGraph = graph.getNodeList();
            if (nodesInGraph.getId(node.getName()) != -1) {
                nodeID = nodesInGraph.getNode(node.getName());

                // Complete the links between its neighbours
                partialAdded = completeLinks(nodeID, graph);

                // Add the links to globalAdded
                for (int i = 0; i < partialAdded.size(); i++) {
                    link = partialAdded.elementAt(i);
                    globalAdded.insertLink(link);
                }
                
                //Saves the information about the intermediate graph
                // in matlab format and about the fill-in arcs
                if(saveTriangulationProcess)
                    saveIntermediateGraph(graph, node, partialAdded);
                
                

                // Delete the node from the graph
                try {
                    graph.removeNode(node);
                } catch (InvalidEditException e) {
                    System.out.println("Error removing node to graph");
                    System.out.println("Method triangulateGraph");
                    System.out.println("Class StrongJunctionTree");
                    System.exit(0);
                }
            } else {
                System.out.println("    simplicial..... nothing to do");
            }
        }
        
        if (generateDebugInfo) {
           System.out.println("StrongJunctionTree:  triangulatedGraph ----- END");
        }

        // Finally, return globalAdded
        return globalAdded;
    }
    
    
    public void saveIntermediateGraph(Graph graph, Node node, LinkList fillins){

        triangulationOutput.println("%%%%%%%%%%%%%%%%\n%Removal of "+node.getName()+"\n%%%%%%%%%%%%%%%%");
        graph.saveLinkListMatlab(triangulationOutput);
        
        Link l = null;
        triangulationOutput.println("FILLINS={};");
        for (int i = 0; i < fillins.size(); i++) {
            l = fillins.elementAt(i);
            triangulationOutput.println("FILLINS={FILLINS{:} {'"+l.getTail().getName()+"', '"+l.getHead().getName()+"', "+l.getDirected()+"}};");

        }
        triangulationOutput.println("ORDER = {ORDER{:} '"+node.getName()+"'};");
        triangulationOutput.println("GRAPH = {NODES LINKS KIND FILLINS '"+node.getName()+"'};");
        triangulationOutput.println("SET_GRAPHS = {SET_GRAPHS{:} GRAPH};\n\n");
        
    
    
    }
    
    
    

    /**
     * Method to complete the links when one node is removed,
     * making its neighbours be completely connected
     * @param node to remove
     * @param diag to modify removing the node
     */
    private LinkList completeLinks(Node node, Graph diag) {
        Node neighbour;
        Node other;
        Link link;
        LinkList fillIns = new LinkList();
        int numNeighbours = node.getNumNeighbours();

        if (generateDebugInfo) {
            System.out.println("StrongJunctionTree:  completeLinks ----- BEGIN");
            for (int i = 0; i < numNeighbours; i++) {
                System.out.println("     Vecino (" + i + ") = " + node.getNeighbourAt(i).getName());
            }
        }

        // Get the neigbours of the node
        for (int i = 0; i < numNeighbours; i++) {
            neighbour = node.getNeighbourAt(i);

            // Consider every neighbour respect to the rest of
            // them
            for (int j = 0; j < numNeighbours; j++) {
                // Consider the rest of neighbours
                if (j != i) {
                    other = node.getNeighbourAt(j);

                    // Look if there is a link between them
                    // neighbour and other
                    if (neighbour.isNeighbour(other) == false) {
                        try {
                            // Create the link in the diagram
                            diag.createLink(neighbour, other, false);
                        } catch (InvalidEditException e) {
                            System.out.println("Error when trying to add a link");
                            System.out.println("Method: completeLinks");
                            System.out.println("Class: StrongJunctionTree");
                            System.exit(0);
                        }

                        // Create the link to insert it into fillIns link list
                        link = new Link(neighbour, other, "fill-in", false);
                        if (generateDebugInfo) {
                            System.out.println("Agregando enlace entre: " + neighbour.getName() + " y " + other.getName());
                        }
                        fillIns.insertLink(link);

                        // Finally, to avoid problems, add other as neighbour
                        // of neighbour node
                        neighbour.addNeighbour(other);
                    }
                }
            }
        }
        
        if (generateDebugInfo) {
           System.out.println("StrongJunctionTree:  completeLinks ----- END");
        }

        // Finally, return fillIns
        return fillIns;
    }

    /**
     * Private method to print the cliques
     */
    public void printCliques() {
        NodeList clique;
        Set keys = indexedCliques.keySet();
        Iterator iterator = keys.iterator();
        int key;
        while (iterator.hasNext()) {
            key = (Integer) iterator.next();
            clique = indexedCliques.get(key);
            System.out.println("\n--------------------------------------");
            clique.print();
            System.out.println("--------------------------------------\n\n");
        }
    }

    /**
     *  method to print the structure of the strong junction tree
     */
    public void printStructure() {
        // Begin with the structure of the root node and this call
        // will produce the print all over the tree
        root.printStructure(0);
    }
    
        /**
     * public method to print the structure of the strong junction tree
     */
    public void printStructureNumPots() {
        // Begin with the structure of the root node and this call
        // will produce the print all over the tree
        root.printStructureNumPots(0);
    }

    /**
     * Private method for printing the whole tree
     */
    public void printComplete() {
        root.print();
    }

    /**
     * Method to determine the index of a clique
     * @param clique
     * @return index
     */
    private int determineIndex(NodeList clique) {
        Node node = null;
        Node other = null;
        Node nodeBiggest = null;
        NodeList neighboursNotInClique;
        NodeList commonNeighboursNotInClique;
        int index = 0;
        int biggest;
        int limit = 100000;
        int cliqueIndex = -1;
        int otherIndex;
        int trial = 0;
        
        if (generateDebugInfo) {
           System.out.println("StrongJunctionTree:  determineIndex ----- BEGIN");
        }

        // There will be as much trials as the number of nodes in the clique
        while (trial < clique.size()) {
            // First at all, look for the biggest numeration related to the nodes
            // and inferior to limit
            biggest = 0;
            for (int i = 0; i < clique.size(); i++) {
                node = clique.elementAt(i);

                // Get its index
                index = numeration.indexOf(node.getName()) + 1;
                if (index > biggest && index < limit) {
                    biggest = index;
                    nodeBiggest = node;
                }
            }

            // Change limit for the next time
            limit = biggest;

            // Once determined the biggest, check if the nodes
            // with smaller index presents a common neighbour
            // such that this neighbour has small index than
            // this node
            commonNeighboursNotInClique = new NodeList();
            for (int i = 0; i < clique.size(); i++) {
                other = clique.elementAt(i);

                // Get the index of other
                otherIndex = getIndexForNode(other);

                // Deal with it if it is not node and it has a numeration
                // unde node
                if (other != nodeBiggest && otherIndex < biggest) {
                    // Consider its neighbours not in the clique and with a
                    // numeration under biggest
                    neighboursNotInClique = getNeighboursNotInSet(other, clique, biggest);

                    // Now, compute the intersection between neighboursNotInClique and
                    // commonNeighboursNotInClique. This mus be done when the second
                    // is not null
                    if (commonNeighboursNotInClique.size() == 0) {
                        // Join with neighbours not in clique
                        commonNeighboursNotInClique.join(neighboursNotInClique);
                    } else {
                        // Compute the intersection. Once the intersection be empty,
                        // go down to consider another index
                        commonNeighboursNotInClique = commonNeighboursNotInClique.intersection(neighboursNotInClique);
                    }

                    // If it is empty, consider another index
                    if (commonNeighboursNotInClique.size() == 0) {
                        break;
                    }
                }
            }

            // If this point is reached with commonNeighboursNotInClique with
            // some element, then the index of the clique will be index
            if (commonNeighboursNotInClique.size() != 0) {
                cliqueIndex = biggest;
                break;
            } else {
                cliqueIndex = 1;
                trial++;
            }
        }
        
        if (generateDebugInfo) {
           System.out.println("StrongJunctionTree:  determineIndex ----- END");
        }

        // Return cliqueIndex
        return cliqueIndex;
    }

    /**
     * Method to get the neighbour of a node that do not belong to a
     * clique, and with a numeration under a given value
     * @param node to get its neighbours
     * @param clique to exlude the neighbours in this nodelist
     * @param index limit
     */
    private NodeList getNeighboursNotInSet(Node node, NodeList clique, int limit) {
        NodeList neighbours = new NodeList();
        Node neighbour;
        int index = 0;

        if (generateDebugInfo) {
           System.out.println("StrongJunctionTree:  getNeighboursNotInSet ----- BEGIN");
        }
        
        for (int i = 0; i < node.getNumNeighbours(); i++) {
            neighbour = node.getNeighbourAt(i);

            // Get its index
            index = getIndexForNode(neighbour);

            // Check if it belongs to clique
            if (clique.getId(neighbour) == -1 && index < limit) {
                neighbours.insertNode(neighbour);
            }
        }
        
        if (generateDebugInfo) {
           System.out.println("StrongJunctionTree:  getNeighboursNotInSet ----- END");
        }
        
        // Finally, return neigbours
        return neighbours;
    }

    /**
     * Private method to get the index related to a node, respect
     * to numeration
     * @param node
     * @return index
     */
    private int getIndexForNode(Node node) {
        int index = -1;
        for (int j = 0; j < numeration.size(); j++) {
            if (node.getName().equals(numeration.get(j))) {
                index = j;
                break;
            }
        }

        // Finally, return index
        return index + 1;
    }

    /**
     * Method to check if a given clique is contained in the hash map
     * of cliques
     * @param clique to check
     * @return boolean value
     */
    private boolean isCliqueContained(NodeList clique) {
        Collection cliques = indexedCliques.values();
        NodeList cliqueDefined;
        Iterator iterator = cliques.iterator();
        boolean contained = false;

        while (iterator.hasNext()) {
            cliqueDefined = (NodeList) iterator.next();
            if (clique.isIncluded(cliqueDefined)) {
                contained = true;
                break;
            }
        }

        // Return the value of contained
        return contained;
    }

    /**
     * Method to arrange the cliques in a strong junction tree
     */
    private void arrangeCliques() {
        NodeList unionVariables = new NodeList();
        NodeList intersection;
        int index = 2;
        int considered = 1;
        
        if (generateDebugInfo) {
           System.out.println("StrongJunctionTree:  arrangeCliques ----- BEGIN");
        }

        // Get the clique with 1 as index
        NodeList clique = indexedCliques.get(1);

        // Add its variables to union
        unionVariables.join(clique);

        // This node it will be the root of the strong junction
        // tree
        //JunctionTreeNode rootNode=new JunctionTreeNode(this,clique,1);
        JunctionTreeNode rootNode = buildTreeNode(this, clique, 1);

        // Assign it to root data member
        root = rootNode;

        // Now consider the nodes one by one, according to their numbering
        while (considered < indexedCliques.size()) {
            // Jump till the next index
            while (!indexedCliques.containsKey(index)) {
                index++;
            }

            // Consider this index and add 1 to considered
            considered++;

            // Get the clique
            clique = indexedCliques.get(index);

            // Get the intersection between the variables of this
            // clique and the union of variables belonging to previous
            // cliques
            intersection = clique.intersection(unionVariables);

            // Therefore, give the root the order to accomodate this clique
            root.accomodate(clique, intersection, index);

            // Once the clique is added to the tree, add its variables to
            // the union
            unionVariables.join(clique);

            // Add one to index
            index++;
        }
        
        if (generateDebugInfo) {
           System.out.println("StrongJunctionTree:  arrangeCliques ----- END");
        }
    }

    /**
     * Private method for building a tree node
     * @param tree
     * @param clique to include in the node
     * @param index
     */
    JunctionTreeNode buildTreeNode(StrongJunctionTree tree, NodeList clique, int index) {
        if (generateDebugInfo) {
           System.out.println("StrongJunctionTree:  buildTreeNode ----- BEGIN");
           System.out.println("StrongJunctionTree:  buildTreeNode ----- END");
        }
        return new JunctionTreeNode(tree, clique, index, generateDebugInfo);
    }

    /**
     * Method to assign the potentials to the cliques of the tree
     */
    private void assignPotentials() {
        //Vector relations=diag.getRelationList();
        Vector relations = diag.getRelationList();
        Relation relation;
        Potential potential;

        if (generateDebugInfo){
           System.out.println("StrongJunctionTree:  assignPotentials ----- BEGIN");
        }
        
        // Consider the relations one by one
        for (int i = 0; i < relations.size(); i++) {
            relation = (Relation) relations.elementAt(i);
            potential = relation.getValues();
            if (generateDebugInfo) {
                System.out.println("\tAsignando potencial: " + potential.getClass().getName());
                System.out.println("\t  "+potential);
                System.out.println("\t.................................................");
            }

            if (relation.getKind() == Relation.CONSTRAINT){
              LogicalExpression logExp=(LogicalExpression)(relation.getValues());
              potential=logExp.getResult();
            }

            // Begin the assignment process on the root node
            root.assignPotential(potential, relation.getKind());
        }
        
        if (generateDebugInfo){
           System.out.println("StrongJunctionTree:  assignPotentials ----- END");
        }
    }

    
    
    /** Returns the result of the propagation
     * 
     * @return HashMap<Node, Potential>
     */
    public HashMap<Node, Potential> getResults() {
        return results;
    }

    
    
    /**
     * Method to get the size of probability potentials contained in
     * a strong junction tree
     * @return size
     */
    public double getSize() {
        if (generateDebugInfo){
           System.out.println("StrongJunctionTree:  getSize ----- BEGIN");
        }
        
        // Call the method getSProbize and getUtilSize on the root of the tree
        // This will produce a traverse over the tree
        double probSize = root.getProbSize();
        double utilSize = root.getUtilSize();
        double constraintSize = root.getConstraintSize();
        if (generateDebugInfo) {
            System.out.println("\tProbs: " + probSize);
            System.out.println("\tUtils: " + utilSize);
            System.out.println("\tConstraints: " + constraintSize);
        }
        
        if (generateDebugInfo){
           System.out.println("StrongJunctionTree:  getSize ----- END");
        }
        return probSize + utilSize + constraintSize;
    }
    
        /**
     * Method to get the size of probability potentials contained in
     * a strong junction tree
     * @return size
     */
    public double getNodesSize() {
        if (generateDebugInfo){
           System.out.println("StrongJunctionTree:  getSize ----- BEGIN");
        }
        
        // Call the method getSProbize and getUtilSize on the root of the tree
        // This will produce a traverse over the tree
        double probSize = root.getProbNodesSize();
        double utilSize = root.getUtilNodesSize();
        double constraintSize = root.getConstraintNodesSize();
        if (generateDebugInfo) {
            System.out.println("\tProbs: " + probSize);
            System.out.println("\tUtils: " + utilSize);
            System.out.println("\tConstraints: " + constraintSize);
        }
        
        if (generateDebugInfo){
           System.out.println("StrongJunctionTree:  getSize ----- END");
        }
        return probSize + utilSize + constraintSize;
    }
    
    
    public int getTreeSize() {
    
        int n = 1;
        
        ArrayList<JunctionTreeSeparator> S =  new ArrayList<JunctionTreeSeparator>();
        S.addAll(root.down);
        System.out.println(S.size());
        
        
        while(!S.isEmpty()) {
            
            n++;
            JunctionTreeSeparator s = S.remove(0);
            S.addAll(s.getInferiorNeighbour().down);
            
        
        }
        
        return n;
    
    } 
    
    
    
    
    
    
    
     public Vector<Integer> getCliqueSizes() {
    
        Vector<Integer> sizes = new Vector<Integer>();
        
        sizes.add(root.variables.size());
 
        ArrayList<JunctionTreeSeparator> S =  new ArrayList<JunctionTreeSeparator>();
        S.addAll(root.down);
        
        System.out.println(S.size());
        
        while(!S.isEmpty()) {
            JunctionTreeSeparator s = S.remove(0);
            sizes.add(s.inferiorNeighbour.getVariables().size());
            S.addAll(s.getInferiorNeighbour().down);

        }
        
        return sizes;
    
    } 
    
    public Vector<Long> getCliqueWeights() {

        Vector<Long> weights = new Vector<Long>();

        NodeList variables = root.getVariables();
        long w = 1;
        for (int i = 0; i < variables.size(); i++) {
            Node n = variables.getNodes().elementAt(i);
            if (n instanceof FiniteStates) {
                w *= ((FiniteStates) n).getNumStates();
            }
        }

        weights.add(w);

        ArrayList<JunctionTreeSeparator> S = new ArrayList<JunctionTreeSeparator>();
        S.addAll(root.down);



        while (!S.isEmpty()) {
            JunctionTreeSeparator s = S.remove(0);

            variables = s.inferiorNeighbour.getVariables();
            w = 1;
            for (int i = 0; i < variables.size(); i++) {
                Node n = variables.getNodes().elementAt(i);
                if (n instanceof FiniteStates) {
                    w *= ((FiniteStates) n).getNumStates();
                }
            }

            weights.add(w);

            S.addAll(s.getInferiorNeighbour().down);

        }

        return weights;

    }

    /**
     * Method to print the results of the poropagation
     */
    public void printResults() {
        NodeList decisions = diag.getDecisionList();
        Potential table;
        Node node;

        for (int i = 0; i < decisions.size(); i++) {
            node = decisions.elementAt(i);
            // Get the pair decision-table
            table = results.get(node);

            // Print the table for the decision
            System.out.println(" .......... Table for " + node.getName() + " .............");
            if (table != null) {
                table.print();
            }
            System.out.println("......................................................");
        }
    }

    /**
     * Public method for getting the nearest clique containing a given
     * variable passed as argument (nearest to the root)
     * @param node to look for
     * @return nearest clique containg node
     */
    public JunctionTreeNode getNearestClique(Node node) {
        int min = Integer.MAX_VALUE;
        int candidate;

        if (generateDebugInfo){
           System.out.println("StrongJunctionTree:  getNearestClique(Node) ----- BEGIN");
        }
        
        // Consider the list of cliques and look for the variable of
        // interest
        for (NodeList list : indexedCliques.values()) {
            if (list.getId(node.getName()) != -1) {
                // Get the index of this clique
                candidate = getIndex(list);

                // Look if it is needed to store this index
                if (candidate < min) {
                    min = candidate;
                }
            }
        }

        // At the end look for the min value. If it is not MAX_VALUE, then
        // it is detected the clique to consider
        if (min != Integer.MAX_VALUE) {
            // Look for the junction tree node for this clique
            return root.lookForNode(min);
        }
        
        if (generateDebugInfo){
           System.out.println("StrongJunctionTree:  getNearestClique(Node) ----- END");
        }

        // If this point is reached, return null
        return null;
    }

    /**
     * Public method for getting the nearest clique containing a given
     * variable which name is passed as argument (nearest to the root)
     * @param variableName to look for
     * @return nearest clique containg node
     */
    public JunctionTreeNode getNearestClique(String variableName) {
        int min = Integer.MAX_VALUE;
        int candidate;

        if (generateDebugInfo){
           System.out.println("StrongJunctionTree:  getNearestClique(String) ----- BEGIN");
           System.out.println("Variable: "+variableName);
        }
        
        // Consider the list of cliques and look for the variable of
        // interest
        for (NodeList list : indexedCliques.values()) {
            if (list.getId(variableName) != -1) {
                // Get the index of this clique
                candidate = getIndex(list);

                // Look if it is needed to store this index
                if (candidate < min) {
                    min = candidate;
                }
            }
        }

        // At the end look for the min value. If it is not MAX_VALUE, then
        // it is detected the clique to consider
        if (min != Integer.MAX_VALUE) {
            // Look for the junction tree node for this clique
            return root.lookForNode(min);
        }
        
        if (generateDebugInfo){
           System.out.println("StrongJunctionTree:  getNearestClique(String) ----- END");
        }

        // If this point is reached, return null
        return null;
    }

    /**
     * Private mthod for getting the index of a clique respect to
     * the data member indexedCliques: it returns the key for the
     * clique
     * @param clique to look for
     * @return index for the clique
     */
    public int getIndex(NodeList clique) {
        for (int i : indexedCliques.keySet()) {
            if (clique == indexedCliques.get(i)) {
                return i;
            }
        }

        // If this point is reached return -1
        return -1;
    }

    /**
     * Method for printing the statistics data at the end of the
     * propagation
     */
    public void printFinalStatisticsData() {
        statistics.setTime(crono.getTime());
        try {
            statistics.printOperationsAndSizes();
        } catch (IOException e) {
        }
        ;
    }

    /**
     * Private method for marking the last operations on the cliques. When
     * an operation is the one to be done on a clique, then the array position
     * for that operation is marked as true
     * @param cliques array containing the indexes of the cliques operated
     * at each stage
     * @return flags array of boolean flags
     */
    private boolean[] markLastOperationsOnCliques(ArrayList<JunctionTreeNode> cliques) {
        JunctionTreeNode clique;
        JunctionTreeNode cliqueRepeated;
        boolean[] flags = new boolean[cliques.size()];
        boolean repeated;
        
        if (generateDebugInfo){
           System.out.println("StrongJunctionTree:  markLastOperationsOnCliques ----- BEGIN");
        }

        for (int i = 0; i < cliques.size(); i++) {
            clique = cliques.get(i);

            // Look if this occurrence of the clique is the last one
            repeated = false;
            for (int j = i + 1; j < cliques.size() && repeated == false; j++) {
                cliqueRepeated = cliques.get(j);

                // Check if are the same
                if (clique.index == cliqueRepeated.index) {
                    // There is no need to look more. This does not correspond to
                    // the last operation on the clique
                    repeated = true;
                }
            }

            // If this point is reached with repeated == false, this was the last
            // operation on the clique
            if (repeated == false) {
                flags[i] = true;
            }
        }
        
        if (generateDebugInfo){
           System.out.println("StrongJunctionTree:  markLastOperationsOnCliques ----- END");
        }

        // Return the vector of flags
        return flags;
    }

    public boolean isSaveTriangulationProcess() {
        return saveTriangulationProcess;
    }

    public void setSaveTriangulationProcess(boolean saveTriangulationProcess) {
        this.saveTriangulationProcess = saveTriangulationProcess;
    }
    
    
    
    
    
    
    
    
}
