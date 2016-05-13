/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package elvira.tools.idiagram;

import elvira.*;
import elvira.potential.PotentialTable;
import elvira.tools.VectorManipulator;
import java.util.Random;
import java.util.Vector;

/**
 *
 * @author rcabanas
 */
public class IDStatistics {
    
    /**
     * Influence Diagram analyzed
     */
    private IDiagram id;
   
    
    public IDStatistics(IDiagram id) {
        this.id = id;
    }
    
    
    public int getNumNodes() {
        return id.getNodeList().size();
    }
    
    public int getNumChance() {
        return id.numberOfChanceNodes();
    }
    
    public int getNumValue() {
        return id.numberOfValueNodes();
    }
    
    public int getNumDecisions() {
        return id.numberOfDecisions();
    }
    
    public int getNumLinks() {
        return id.numberOfLinks();
    }
    
    public int getNumUtilityParents() {
        return id.getValueNode().getNumNeighbours();        
    }
    
    public int getUtilitySize() {
        return (int)id.getRelation(id.getValueNode()).getValues().getSize();      
    }
    
    
    public float getMeanNumberOfParents() {
        int sum = 0;
        Vector<Node> nodes = id.getNodeList().toVector();
        for(int i=0; i<nodes.size(); i++) {
            sum += nodes.get(i).getParentNodes().size();
        }
        
        return sum/nodes.size();
        
    }
    
    
    public double getMeanNumberOfStates() {
        int sum = 0;
        Vector<Node> nodes = id.getNodeList().toVector();
        for(int i=0; i<nodes.size(); i++) {
            if(nodes.get(i).getKindOfNode() != Node.UTILITY)
                sum += ((FiniteStates)nodes.get(i)).getNumStates();
        }
        
        return ((double)sum)/nodes.size();

    }
    
    public double getMaxNumberOfStates() {
        int max = 0;
        Vector<Node> nodes = id.getNodeList().toVector();
        for(int i=0; i<nodes.size(); i++) {
            if(nodes.get(i).getKindOfNode() != Node.UTILITY){
                if(((FiniteStates)nodes.get(i)).getNumStates() > max)
                    max = ((FiniteStates)nodes.get(i)).getNumStates();
            }
        }
        
        return max;

    }
        public double getMaxChancefStates() {
        int max = 0;
        Vector<Node> nodes = id.getNodeList().toVector();
        for(int i=0; i<nodes.size(); i++) {
            if(nodes.get(i).getKindOfNode() == Node.CHANCE){
                if(((FiniteStates)nodes.get(i)).getNumStates() > max)
                    max = ((FiniteStates)nodes.get(i)).getNumStates();
            }
        }
        
        return max;

    }
    
    public long getStorageSize() {
        long sum = 0;
        Vector<Relation> relations = id.getRelationList();
        for(int i=0; i<relations.size(); i++){
            Relation r = relations.get(i);
            sum += r.getValues().getSize();
        }
        
        return sum;

    }
    
    
    public long getTotalTableSize() {
        
        NodeList nodes = id.getNodeList();
        long ts = 1;
        for(int i=0; i<nodes.size(); i++) {
            Node n = (nodes.getNodes().elementAt(i));
            if(n instanceof FiniteStates){
                ts*=((FiniteStates) n).getNumStates();
            }
        
        }
        
        return ts;
    
    }
    
    public double getMeanCSIratio() {
        double ratio = 0;
        int n = 0;
         Vector<Relation> relations = id.getRelationList();
        for(int i=0; i<relations.size(); i++){
            Relation r = relations.get(i);
                
                if(r.getKind() != Relation.CONSTRAINT) {
                    n++;
                    PotentialTable p =new PotentialTable(r.getValues());
                    Vector values = p.getVectorValues();
                    VectorManipulator.print(values);
                    Vector set = VectorManipulator.removeRepeted(values);

                    ratio += ((double) set.size())/((double)values.size());
                }
        }
        
        return ratio/n;
        
    }
    
    public double getMeanPotSize() {
            int n= 0;
        double size = 0;
         Vector<Relation> relations = id.getRelationList();
        for(int i=0; i<relations.size(); i++){
            Relation r = relations.get(i);
                
                if(r.getKind() != Relation.CONSTRAINT) {
                    PotentialTable p =new PotentialTable(r.getValues());
                    size += p.getSize();
                    n++;
                }
        }
        
        return size/n;
        
    }
    
    public Vector<Integer> getNumPerChancePartition(){
        Vector<Integer> distr = new Vector<Integer>();
        IDiagram networkAux = id.copy();
        
         NodeList decisions = ((IDiagram) networkAux).getDecisionList();

        // Now, as first step, get all nodes without decisions as sucessors
        NodeList nodeSet = ((IDiagram) networkAux).getChanceNodesWithoutDecisionsAsSucessors();
        NodeList newNodeSet;

        distr.add(nodeSet.size());
   

        
        //


        for (int i = decisions.size() - 1; i >= 0; i--) {
            Node decisionConsidered = decisions.elementAt(i);
            nodeSet.insertNode(decisionConsidered);

            //remove Di

            

    
            // Now, get all chance nodes with sucessors in phase
            newNodeSet = ((IDiagram) networkAux).getChanceNodesWithSucessorsInSet(nodeSet);
      

            distr.add(newNodeSet.size());


            for (int j = 0; j < newNodeSet.size(); j++) {
                nodeSet.insertNode(newNodeSet.elementAt(j));
            }


        }


        
        return distr;
        
    }
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        
        Random ranGen = new Random(1234);


        int width = 3;
        int height = 4;               
        double percentage = 70;
        int numberDecisionNodes = 2;
        int numberValueNodes = 1;
        int maxNumberParents = ranGen.nextInt(5)+2;
        int maxNumberStates = ranGen.nextInt(5)+2;;
        double percConstraint = 0;    



        //Generates a random ID
        IDRandomGenerator idgen = new IDRandomGenerator(width, height, percentage, numberDecisionNodes, numberValueNodes, maxNumberParents, maxNumberStates, percConstraint);
        idgen.generateID("idRand");
        idgen.randomizeID("idRand");
        
        IDiagram idRand = idgen.getResult();
        
        //Statistics
        IDStatistics stats = new IDStatistics(idRand);
        System.out.println("Number of nodes: "+stats.getNumNodes());
        System.out.println("Number of chance nodes: "+stats.getNumChance());
        System.out.println("Number of decision nodes: "+stats.getNumDecisions());
        System.out.println("Number of utility nodes: "+stats.getNumValue());
        System.out.println("Number of links: "+stats.getNumLinks());
        System.out.println("Number of utility parents: "+stats.getNumUtilityParents());
        System.out.println("Mean of parents: "+stats.getMeanNumberOfParents());
        System.out.println("Mean of states: "+stats.getMeanNumberOfStates());
        System.out.println("Mean of CSI ratio: "+stats.getMeanCSIratio());
        System.out.println("TotalTableSize: "+stats.getTotalTableSize());
        
        VectorManipulator.print(stats.getNumPerChancePartition());
        
    }
}
