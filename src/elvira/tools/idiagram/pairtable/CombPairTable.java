/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package elvira.tools.idiagram.pairtable;

import elvira.*;
import elvira.potential.Potential;
import elvira.tools.VectorManipulator;
import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * @author Rafael Cabañas de Paz (rcabanas@decsai.ugr.es)
 */
public class CombPairTable {
    
    public static final int MIN_SIZE_ONLY_PAIR = 0;
    public static final int MIN_SIZE_MARG_ONLY_PAIR = 1;
    public static final int MIN_WEIGHT_ONLY_PAIR = 2;
    public static final int CANO_MORAL_ONLY_PAIR=  3;
    public static final int MIN_SIZE = 4;
    public static final int MIN_SIZE_MARG = 5;
    public static final int MIN_WEIGHT = 6;
    public static final int CANO_MORAL=  7;
    public static final int MAX_POT_ONLY_PAIR=  8;
    public static final int MAX_POT=  9;
    public static final int DAMBROSIO_ONLY_PAIR = 10;
    public static final int DAMBROSIO = 11;
    
    
    public static final int MIN_TREESIZE_ONLY_PAIR = 12;
    public static final int SIZE_MARG_WEIGHT_UTIL = 13;
    public static final int MIN_TREESIZE= 14;
    
    private Vector<CombPair> pairSet;
    
    private NodeList allVars;
    private NodeList removable;
    
    private boolean setDebugInfo =false;
    
    private Vector<Potential> otherInvolvedPots;
    
    private RelationList notInvolvedRel; 
    
    private boolean allowSingletons = false;
    
    private String offset="";
    
    private boolean checkMarginalChild = false;
    
    private boolean checkUnityPots = false;
    
    private int neighborhoodDegree = 1;
    
    
    private boolean simetric = true; //si true, Da igual el orden (a,b)=(b,a)
    
    
    public CombPairTable() {
        pairSet = new Vector<CombPair>();
    }
    
    public void addCombinations(RelationList relations) {
        
        Relation ri, rj;
        
        for(int i=0; i<relations.size()-1; i++){
            for(int j=i+1; j<relations.size(); j++){
                
                ri = relations.elementAt(i);
                rj = relations.elementAt(j);
                
                if(!contains(ri, rj)) {
                    pairSet.add(new CombPair(ri, rj));
                }
                
                if(!simetric && !contains(rj, ri)) {
                    pairSet.add(new CombPair(rj, ri));
                }
                
        
            }      
        }
        
        //When there is a single potential in the set, singletons are always allowed
        if(allowSingletons || relations.size()==1){
            removeAllSingletons();
            addSinglePotPairs(relations);
        }
        
        
   /*     if(neighborhoodDegree>1) {
            addPosteriorCombinations();
        
        }
        */
        
        updateAllVars();
    }
    
    public void addCombinations(ArrayList<Potential> pots) {
        RelationList relations = new RelationList();
        for(Potential p : pots) {
            Relation r = new Relation(p);
            relations.insertRelation(r);
        }
        
        this.addCombinations(relations);
    
    }
    
    
    private void addPosteriorCombinations() {
    
        
    /*    Vector<CombPair> newPairSet = new Vector<CombPair>();
        for(int i=2; i<=neighborhoodDegree; i++) {
            
            boolean int
            for(int j=0; j<)
        
        
        }
        
   */     
    
    
    }
    
    
    private void removeAllSingletons() {
        
        if(pairSet.size()==0)
            return;
        
        for(int i=pairSet.size()-1; i>=0; i--){
            if(pairSet.elementAt(i).isSinglePot())
                pairSet.remove(i);
        }
    
    }
    private void addSinglePotPairs(RelationList relations) {
        Vector<Relation> rel = relations.getRelations();
        
        if(rel.size()==1) {
            pairSet.add(new CombPair(rel.elementAt(0)));
        }else{        
            for(int i=0; i<rel.size(); i++) {
                Relation ri = rel.elementAt(i);
                NodeList vars_i = ri.getVariables().copy();
                
           
                
                vars_i = vars_i.intersection(removable);
                
                //Checks if the variables in the relation
                // are in any other potential
                for(int j=0; j<rel.size(); j++) {
                    if(i!=j && vars_i.size()>0){
                        vars_i = vars_i.difference(rel.elementAt(j).getVariables());
                    }
                }
                
                
                
                
                //if it has removable varariables, it can be added
                if(vars_i.size()>0)
                 //   pairSet.add(new CombPair(ri));
                    pairSet.insertElementAt(new CombPair(ri), 0);
                
                
                
            }
        }
    
    }
    
    
     public RelationList getAllRelationsWithout(CombPair p){
    
        RelationList list = new RelationList();
        Relation r1 = null;
        Relation r2 = null;
        
        for(int i=0; i<pairSet.size(); i++){
            r1 = pairSet.get(i).getR1();
            if(!list.contains(r1)) {
                list.insertRelation(r1);
            }
            if(!pairSet.get(i).isSinglePot()){
                r2 = pairSet.get(i).getR2();
                if(!list.contains(r2))
                    list.insertRelation(r2);
            }
        }
        
        return list;
    }   
    
    
    public RelationList getAllRelations(){
    
        RelationList list = new RelationList();
        Relation r1 = null;
        Relation r2 = null;
        
        for(int i=0; i<pairSet.size(); i++){
            r1 = pairSet.get(i).getR1();
            if(!list.contains(r1))
                list.insertRelation(r1);
            
            if(!pairSet.get(i).isSinglePot()){
                r2 = pairSet.get(i).getR2();
                if(!list.contains(r2))
                    list.insertRelation(r2);
            }
        }
        
        return list;
    }
    
    
    
    public boolean contains(Relation r1, Relation r2) {
    
        for(int i=0; i<pairSet.size(); i++) {
            CombPair p = pairSet.elementAt(i);
            //if(p.contains(r1) && p.contains(r2))
            if(p.isEquivalent(r1, r2, simetric))
                return true;
        }
        
        return false;
        
    }
    
    public int getIndex(Relation r1, Relation r2) {
    
        for(int i=0; i<pairSet.size(); i++) {
            CombPair p = pairSet.elementAt(i);
            if(p.contains(r1) && p.contains(r2))
                return i;
        }
        
        return -1;
        
    }
    
    
 public Vector<Integer> getPairsLeadingUnity() {
     
     
        
        Vector<Integer> pairs = new Vector<Integer>();
        
        for(int i=0; i<pairSet.size(); i++) {
            CombPair p = pairSet.elementAt(i);
            NodeList removableInPair = getRemovableVarsOfPair(i);
            if(p.generatesUnityPot(removableInPair))
                pairs.add(i);
            
        }
        
        
        
        if (setDebugInfo) { 
            System.out.println("Pairs that generate a unity potentials:");
            VectorManipulator.print(pairs);
        }
        
        return pairs;

 }
   
    public double computeScore(int i, int heuristic) {
        CombPair p = pairSet.elementAt(i);
        
        
        if (setDebugInfo) 
            System.out.println(offset+"Computing score of pair " + p);

        NodeList vars = p.getVars().copy();
        vars.removeNodesOfVariableType(Node.CONTINUOUS);
        NodeList removableInPair = getRemovableVarsOfPair(i);
       p.setRemovableVars(removableInPair);
        
        Vector<Potential> potsInvolved;
        NodeList otherInvolvedVars;
        
        double removableWeight;
        

        
 /*       boolean unity = false;
        if(checkUnityPots)
            unity = p.generatesUnityPot(removableInPair);
 */       
        
        double score = 0;
        
        switch(heuristic) {
            case MIN_SIZE_ONLY_PAIR:
                
                score = vars.size();

                if (setDebugInfo) {
                    System.out.println(offset+" - Variables in pair: " + vars.toString2());
                    System.out.println(offset+" - Removable variables: " + removableInPair.toString2());
                    System.out.println(offset+" - Score: " + score);
             }
                
             break; 
            case MIN_WEIGHT:
                
                potsInvolved = getOtherInvolvedPots(removableInPair);
                otherInvolvedVars = getVariablesInPots(potsInvolved);
                vars.join(otherInvolvedVars);
                
                
                score = 1;
                for(int k=0; k<vars.size(); k++)
                    score *= ((FiniteStates)vars.elementAt(k)).getNumStates();

                if (setDebugInfo) {
                    System.out.println(offset+" - Variables in pair: " + vars.toString2());
                    System.out.println(offset+" - Other involved vars " + otherInvolvedVars.toString2());
                    System.out.println(offset+" - Removable variables: " + removableInPair.toString2());
         
                    System.out.println(offset+" - Score: " + score);
                }


                break;
            case MIN_WEIGHT_ONLY_PAIR:
                
                score = 1;
                for(int k=0; k<vars.size(); k++)
                    score *= ((FiniteStates)vars.elementAt(k)).getNumStates();

                if (setDebugInfo) {
                    System.out.println(offset+" - Variables in pair: " + vars.toString2());
                    System.out.println(offset+" - Removable variables: " + removableInPair.toString2());

         
                    System.out.println(offset+" - Score: " + score);
                }
                break;

            case MIN_TREESIZE_ONLY_PAIR:

              
              // score = p.getPot1().getNumberOfNodes(); //IMPLEMENTAR HEURÍSTICA 
               
               //Variables present in the 2nd pot not present in the first
              NodeList vars2 = new NodeList();
              score = 1;
              long numNodes1 = p.getPot1().getNumberOfNodes();
              long numLeaves1 =  p.getPot1().getSize();
               
               if(p.isPair()) {                   
                   vars2 =vars.difference(p.getR1().getVariables());
                   for(int k=0; k<vars2.size(); k++) {
                       score *= ((FiniteStates)vars2.elementAt(k)).getNumStates();
                   }
               }

               score = numNodes1 + numLeaves1 *(2*score - 2);
                
                
                if (setDebugInfo) {
                    System.out.println(offset + " - Variables in pair: " + vars.toString2());
                    System.out.println(offset + " - Removable variables: " + removableInPair.toString2());
                    System.out.println(offset + " - Variables only in second potential: " + vars2.toString2());
                    System.out.println(offset + " - Number of nodes in BT1 : " + numNodes1);
                    System.out.println(offset + " - Number of leaves in BT1 : " + numLeaves1);
                    System.out.println(offset + " - Score: " + score);


                    System.out.println(offset + " - Score: " + score);
                }


                break;
            case CANO_MORAL:
                
                potsInvolved = getOtherInvolvedPots(removableInPair);
                otherInvolvedVars = getVariablesInPots(potsInvolved);
                vars.join(otherInvolvedVars);
                
                
                score = 1;
                for(int k=0; k<vars.size(); k++)
                    score *= ((FiniteStates)vars.elementAt(k)).getNumStates();

                removableWeight = 1;
                for(int k=0; k<removableInPair.size(); k++)
                    removableWeight *= ((FiniteStates)removableInPair.elementAt(k)).getNumStates();

                
                score = score/removableWeight;
                
                
                if (setDebugInfo) {
                    System.out.println(offset+" - Variables in pair: " + vars.toString2());
                    System.out.println(offset+" - Removable variables: " + removableInPair.toString2());

                    System.out.println(offset+" - Score: " + score);
                }


                break;
            case CANO_MORAL_ONLY_PAIR:
                
                score = 1;
                for(int k=0; k<vars.size(); k++)
                    score *= ((FiniteStates)vars.elementAt(k)).getNumStates();

                removableWeight = 1;
                for(int k=0; k<removableInPair.size(); k++)
                    removableWeight *= ((FiniteStates)removableInPair.elementAt(k)).getNumStates();
                
                
                
                if (setDebugInfo) {
                    System.out.println(offset+" - Variables in pair: " + vars.toString2());
                    System.out.println(offset+" - Removable variables: " + removableInPair.toString2());

                    System.out.println(offset+" - Score: " + score);
                }


                break;
                
                
            case MAX_POT_ONLY_PAIR:
                
              if(!p.isSinglePot())
                    score = p.getR1().getVariables().size() + p.getR2().getVariables().size();
                else
                    score = p.getR1().getVariables().size();
              
              score = 1/score;
                
                if (setDebugInfo) {
                    System.out.println(offset+" - Variables in pair: " + vars.toString2());

                    System.out.println(offset+" - Score: " + score);
             }

                break;                
            case MAX_POT:
                
                potsInvolved = getOtherInvolvedPots(removableInPair);

                
                if(!p.isSinglePot())
                    score = p.getR1().getVariables().size()+p.getR2().getVariables().size();
                else
                    score = p.getR1().getVariables().size();
                
                for(int k=0; k<potsInvolved.size(); k++)
                    score +=otherInvolvedPots.elementAt(k).getVariables().size();
                
                
                score = 1/score;
                
                if (setDebugInfo) {
                    System.out.println(offset+" - Variables in pair: " + vars.toString2());

                    System.out.println(offset+" - Score: " + score);
             }

                break;

            case MIN_SIZE:
                
                potsInvolved = getOtherInvolvedPots(removableInPair);
                otherInvolvedVars = getVariablesInPots(potsInvolved);
                vars.join(otherInvolvedVars);
                score = vars.size();
                
                if (setDebugInfo) {
                    System.out.println(offset+" - Variables in pair: " + vars.toString2());
                    System.out.println(offset+" - Variables in utilities: " + otherInvolvedVars.toString2());

                    System.out.println(offset+" - Score: " + score);
                }

                break;
                  
                
                
            case MIN_SIZE_MARG_ONLY_PAIR:

                score = vars.size() - removableInPair.size();

                if (setDebugInfo) {
                    System.out.println(offset+" - Variables in pair: " + vars.toString2());
                    System.out.println(offset+" - Removable variables: " + removableInPair.toString2());

                    System.out.println(offset+" - Score: " + score);
                }

                break;
                
            case MIN_SIZE_MARG:
                
               
                 potsInvolved = getOtherInvolvedPots(removableInPair);
                otherInvolvedVars = getVariablesInPots(potsInvolved);
                vars.join(otherInvolvedVars);
                score = vars.size() - removableInPair.size();
                
                
                
                
                if (setDebugInfo) {
                    System.out.println(offset+" - Variables in pair: " + vars.toString2());
                    System.out.println(offset+" - Variables in utilities: " + otherInvolvedVars.toString2());

                    System.out.println(offset+" - Score: " + score);
                }
                break;   
            default:
                System.out.println("Invalid Heuristic or not implemented yet: "+heuristic);
                System.exit(1);
                break;                
                
                
        }
        

 /*       if (unity) {
            score = 0;
            if (setDebugInfo) {
                System.out.println(offset + " - Score changed to " + score);

            }
        }*/
        return score;
    }


   
   
   public CombPair netxToCombine(int heuristic) {
   
       if(heuristic==DAMBROSIO_ONLY_PAIR)
           return netxToCombine2scores(MIN_SIZE_MARG_ONLY_PAIR, MAX_POT_ONLY_PAIR);
       else if(heuristic==DAMBROSIO)
           return netxToCombine2scores(MIN_SIZE_MARG, MAX_POT);
       else if(heuristic==SIZE_MARG_WEIGHT_UTIL) {
           return netxToCombine2scores(MIN_SIZE_ONLY_PAIR, MIN_WEIGHT);
       }
       
       
       
       CombPair bestPair=null;
       double bestScore = Double.POSITIVE_INFINITY;
       
       CombPair bestPairWithMarg = null;
       double bestScoreWithMarg = Double.POSITIVE_INFINITY;
       
       
       
      Vector<Integer> index = new Vector<Integer>();
 /*       if(checkUnityPots)
           index = getPairsLeadingUnity();
  */
       
       if(index.size()==0) {
           for(int i=0; i<pairSet.size(); i++)
               index.add(i);
       }
       
       
       
       for(int j=0; j<index.size(); j++) {
           
           int i = index.elementAt(j);
           double score = computeScore(i, heuristic);
           
           if(checkMarginalChild && !marginalWithoutCommonChild(pairSet.elementAt(i))){
               if(score<bestScoreWithMarg) {
                    bestPairWithMarg = pairSet.elementAt(i);
                    bestScoreWithMarg = score;
                   
               }                   
           }
           else if(score< bestScore) {
               bestPair = pairSet.elementAt(i);
               bestScore = score;
           }

       }

       
       if(bestPair==null)
           return bestPairWithMarg;
       
       
       return bestPair;
   }
   
   
      private CombPair netxToCombine2scores(int h1, int h2) {
   
       CombPair bestPair=null;
       double bestScore1 = Double.POSITIVE_INFINITY;
       double bestScore2 = Double.POSITIVE_INFINITY;
       
       
       
       Vector<Integer> index = new Vector<Integer>();
       if(checkUnityPots)
           index = getPairsLeadingUnity();
       
       if(index.size()==0) {
           for(int i=0; i<pairSet.size(); i++)
               index.add(i);
       }
       
    
       for(int i=0; i<index.size(); i++) {
           double score1 = computeScore(index.elementAt(i), h1);
           if(score1<= bestScore1) {
               bestScore1 = score1;
           }else{
               index.remove(i);
           }

       }
       for(int i=0; i<index.size(); i++) {
           double score1 = computeScore(index.elementAt(i), h1);
           if(score1<= bestScore1) {
               bestScore1 = score1;
           }else{
               index.remove(i);
           }

       }    
       
       for(int i=0; i<index.size(); i++) {
           double score2 = computeScore(index.elementAt(i), h2);
           if(score2<= bestScore2) {
               bestScore2 = score2;
           }else{
               index.remove(i);
           }

       }   
       
            for(int i=0; i<index.size(); i++) {
           double score2 = computeScore(index.elementAt(i), h2);
           if(score2<= bestScore2) {
               bestScore2 = score2;
           }else{
               index.remove(i);
           }

       }  
       
      bestPair = pairSet.elementAt(index.elementAt(0));
       
       
       
 /*      for(int i=0; i<index.size(); i++) {
           double score1 = computeScore(index.elementAt(i), h1);
           double score2 = computeScore(index.elementAt(i), h2);
           if(score1<= bestScore1 && score2<bestScore2) {
               bestPair = pairSet.elementAt(index.elementAt(i));
               bestScore1 = score1;
               bestScore2 = score2;
           }

       }
   
  */
       
       return bestPair;
   }
    
    
   
    public Vector<Potential> getOtherInvolvedPots(NodeList vars){
    
        Vector<Potential> pots = new Vector<Potential>();
        
        if(otherInvolvedPots==null)
            return pots;
        
        for(int i=0; i<otherInvolvedPots.size(); i++){
            if(otherInvolvedPots.elementAt(i).getNodeList().intersection(vars).size()>0)
                pots.add(otherInvolvedPots.elementAt(i));
        }
   
        return pots;
    }
    
   
    private NodeList getVariablesInPots(Vector<Potential> pots){
    
        NodeList vars = new NodeList();
        for(int i=0; i<pots.size(); i++){
            vars.join(pots.elementAt(i).getNodeList());
        }
        
        return vars;
        
    
    }
  
    
    private void removeRel(RelationList list, Relation r) {
    
        for(int i=list.size()-1; i>=0; i--) {
            if(list.elementAt(i).getVariables().equals(r.getVariables()))
                list.removeElementAt(i);
                    
        }
        
        
    }
    

    public NodeList getRemovableVarsOfPair(int i) {
        CombPair pi = pairSet.get(i);
        NodeList vars_i = pi.getVars().intersection(removable);
        

        
        
        //Get the rest of relations
        RelationList relations = getAllRelations();
           

    removeRel(relations, pi.getR1());
    //    relations.removeRelation(pi.getR1());
        if(!pi.isSinglePot())
            removeRel(relations, pi.getR2());
    //        relations.removeRelation(pi.getR2());
        
        //Get the variables in the rest of relations
        NodeList varsInOtherRelations = relations.getVariables();
        
        //Removable variables in pair
        vars_i = vars_i.difference(varsInOtherRelations);
        pi.setRemovableVars(vars_i);
                return vars_i;
    }
    

    public NodeList getVarsInOtherPots(int i) {
    
        NodeList list = new NodeList();
        Relation ri1, ri2=null, rj1, rj2=null;
        
        CombPair pi, pj;
        
        pi = pairSet.get(i);
        ri1 = pi.getR1();
        if(!pi.isSinglePot())
            ri2 = pairSet.get(i).getR2();
        
        
        
        for(int j=0; j<pairSet.size(); j++) {
            pj = pairSet.elementAt(j);
            
            if(true){
            if(pi.isSinglePot() && pj.isSinglePot()) {
                if(ri1 != pj.getR1()) 
                    list.join(pj.getR1().getVariables());
            
            }else if(pi.isSinglePot() && !pj.isSinglePot()) {
                if(ri1 != pj.getR1()) 
                    list.join(pj.getR1().getVariables());
                if( ri1 != pj.getR2()) 
                    list.join(pj.getR2().getVariables()); 
            
            }else if(!pi.isSinglePot() && pj.isSinglePot()) {
                if(ri1 != pj.getR1()  && ri2 != pi.getR1()) 
                    list.join(pj.getR1().getVariables());
                
            } else {
                if(ri1 != pj.getR1()  && ri2 != pj.getR1()) 
                    list.join(pj.getR1().getVariables());
                if( ri1 != pj.getR2() && ri2 != pj.getR2()) 
                    list.join(pj.getR2().getVariables());  
                
            }
            }
            
    
        
        }
        


        return list;
    }

    public Vector<Potential> getOtherInvolvedPots() {
        return otherInvolvedPots;
    }

    public void setOtherInvolvedPots(Vector<Potential> potSet) {
        this.otherInvolvedPots = potSet;
    }
    
   public void setOtherInvolvedPots(RelationList potList) {
        Vector<Potential> set = new Vector<Potential>();
        for(int i=0; i<potList.size(); i++) {
            set.add(potList.elementAt(i).getValues());
        }
        this.otherInvolvedPots=set;
        
    }
   
   
   public void setOtherInvolvedPots(ArrayList<Potential> potList) {
       
       
        Vector<Potential> set = new Vector<Potential>();
        for(int i=0; i<potList.size(); i++) {
            set.add((potList.get(i)));
        }
        this.otherInvolvedPots=set;
        
    }
    
    public void setOtherInvolvedPots(Potential pot) {
        Vector<Potential> utilSet = new Vector<Potential>();
        utilSet.add(pot);
        this.otherInvolvedPots=utilSet;
    }
        
    
    
    private void updateAllVars() {
       allVars = new NodeList();
       for(int i=0; i<pairSet.size(); i++) {
            CombPair p = pairSet.elementAt(i);
            allVars.join(p.getVars());
        }
        
    }
    
    
        
    public void removeContainig(Relation r) {
        for(int i=pairSet.size()-1; i>=0; i--)
            if(pairSet.elementAt(i).contains(r)) {
                pairSet.remove(i);
            }
    }
    
    
    
    public void removeContainig(Potential pot) {
        for(int i=pairSet.size()-1; i>=0; i--)
            if(pairSet.elementAt(i).contains(pot)) {
                pairSet.remove(i);
            }
    }
    
    
    
    public String toString() {
        
        String str = "{";
        for(int i=0; i<pairSet.size(); i++) {
            str += pairSet.get(i);
            if(i != pairSet.size()-1)
                str +=", ";
        }
        
        str += "}";
        return str;
    }
    
        public void printPairs() {
        
        String str = "";
        for(int i=0; i<pairSet.size(); i++) {
            str += pairSet.get(i);
            if(i != pairSet.size()-1)
                str +=",\n";
        }
        

        
        System.out.println(offset+str);
        
    }
        
        
        
        

    public void setRemovable(NodeList removable) {
        this.removable = removable;
    }
    
    
    
    public void setRemovable(Node var) {
        NodeList list = new NodeList();
        list.insertNode(var);
        this.removable = list;
    }

    
    public void setWithoutRemovable(){
        setRemovable(new NodeList());
    }
    
    public NodeList getAllVars() {
        return allVars;
    }


    public boolean marginalWithoutCommonChild(CombPair p) {
    
        if(p.isSinglePot() || !p.areMarginal())
            return false;
        
        //Relations in the rest of pairs
        RelationList rel = getAllRelations();
        for(int i=0; i<rel.size(); i++) {
            Relation r = rel.elementAt(i);
            if(!p.contains(r) && r.getVariables().intersection(p.getVars()).size()==2)
                return false;
        }
        
        //Rest of involved relations (e.g. utilities)
       
        for(int i=0; i<otherInvolvedPots.size(); i++) {
            Potential pot = otherInvolvedPots.elementAt(i);
            if(pot.getNodeList().intersection(p.getVars()).size()==2)
                return false;
        }
        
        
        
        //Rest of relations
        rel = this.notInvolvedRel;
            if(rel != null) {
            for(int i=0; i<rel.size(); i++) {
                Relation r = rel.elementAt(i);
                if(r.getVariables().intersection(p.getVars()).size()==2)
                    return false;
            }
        }
        return true;
    
    }    
    
    
    
    
    
    public CombPair getPair(int i) {
       return this.pairSet.elementAt(i);
    }    

    public void setSetDebugInfo(boolean setDebugInfo) {
        this.setDebugInfo = setDebugInfo;
    }

    public boolean isAllowSingletons() {
        return allowSingletons;
    }

    public void setAllowSingletons(boolean allowSinglePot) {
        this.allowSingletons = allowSinglePot;
    }

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }
        
    public int getSize(){
        return this.pairSet.size();
    }

    public boolean isCheckMarginalChild() {
        return checkMarginalChild;
    }

    public void setCheckMarginalChild(boolean checkMarginalChild) {
        this.checkMarginalChild = checkMarginalChild;
    }

    public void setNotInvolvedRel(RelationList notInvolvedRel) {
        this.notInvolvedRel = notInvolvedRel;
    }

    public boolean isCheckUnityPots() {
        return checkUnityPots;
    }

    public void setCheckUnityPots(boolean checkUnityPots) {
        this.checkUnityPots = checkUnityPots;
    }

    public void setSimetric(boolean simetric) {
        this.simetric = simetric;
    }
    
    
    
    public static int getHeuristicWithName(String name) {
    
        if(name.equals("MIN_SIZE_ONLY_PAIR")) return MIN_SIZE_ONLY_PAIR;
        if(name.equals("MIN_SIZE_MARG_ONLY_PAIR")) return MIN_SIZE_MARG_ONLY_PAIR;
        if(name.equals("MIN_WEIGHT_ONLY_PAIR")) return MIN_WEIGHT_ONLY_PAIR;
        if(name.equals("CANO_MORAL_ONLY_PAIR")) return CANO_MORAL_ONLY_PAIR;
        if(name.equals("MIN_SIZE")) return MIN_SIZE;
        if(name.equals("MIN_SIZE_MARG")) return MIN_SIZE_MARG;
        if(name.equals("MIN_WEIGHT")) return MIN_WEIGHT;
        if(name.equals("CANO_MORAL")) return CANO_MORAL;
        if(name.equals("MAX_POT_ONLY_PAIR")) return MAX_POT_ONLY_PAIR;
        if(name.equals("MAX_POT")) return MAX_POT;
        if(name.equals("DAMBROSIO_ONLY_PAIR")) return DAMBROSIO_ONLY_PAIR;
        if(name.equals("DAMBROSIO")) return DAMBROSIO;
        if(name.equals("MIN_TREESIZE_ONLY_PAIR")) return MIN_TREESIZE_ONLY_PAIR;
        if(name.equals("SIZE_MARG_WEIGHT_UTIL")) return SIZE_MARG_WEIGHT_UTIL;
        if(name.equals("MIN_TREESIZE")) return MIN_TREESIZE;

        return -1;
    
    
    }
    
    
    
}
