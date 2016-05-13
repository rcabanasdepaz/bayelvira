/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package elvira.tools.idiagram.pairtable;

import elvira.NodeList;
import elvira.Relation;
import elvira.RelationList;
import elvira.potential.Potential;
import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * @author Rafael Cabañas de Paz (rcabanas@decsai.ugr.es)
 */
public class CombPair {
    
  //  private Relation r1, r2;
    private ArrayList<Relation> relations;
    private NodeList vars;
    private NodeList removableVars;
    
    public CombPair(Relation r1) {
        
        initRelations();
        setR1(r1);
      //  setR2(null);
        
        
        vars = r1.getVariables().copy();
    }
    
    
    public CombPair(Relation r1, Relation r2) {
        
        initRelations();
        setR1(r1);
        setR2(r2);
        

        vars = r1.getVariables().copy();
        vars.join(r2.getVariables());

    }
    
    private void initRelations() {
        relations = new ArrayList<Relation>(2);
        relations.add(null);
        relations.add(null);
    }

    public void setR1(Relation r1) {
        this.relations.set(0, r1);
    }

    public void setR2(Relation r2) {
        this.relations.set(1, r2);
    }
    
    
    
    
    
    public boolean contains(Relation r) {
        if(isSinglePot())
            return r == getR1();
        return r==getR1() || r==getR2();
    }

    public boolean contains(Potential pot) {
        if(isSinglePot())
            return pot == getR1().getValues();
        return pot == getR1().getValues() || pot == getR2().getValues();
    }
    
    
    public boolean isEquivalent(Potential pot1, Potential pot2, boolean simetric) {
        if(isSinglePot())
            return false;
       
       boolean equivalent = false; 
       if(pot1 == getR1().getValues() && pot2 == getR2().getValues()){
           equivalent = true;
       }else if(simetric && pot2 == getR1().getValues() && pot1 == getR2().getValues()) {
           equivalent = true;
       }
       
       return equivalent;
    
    }
    
    public boolean isEquivalent(Relation rel1, Relation rel2, boolean simetric) {
        return isEquivalent(rel1.getValues(), rel2.getValues(), simetric);
    
    }
    
    
    public Relation getR1() {
        return relations.get(0);
    }

    public Relation getR2() {
        return relations.get(1);
    }

    public Potential getPot1(){
        return getR1().getValues();
    }
    public Potential getPot2(){
        return getR2().getValues();
    }    
    
    public NodeList getVars() {
        return vars;
    }
    
    public boolean isSinglePot() {
        return getR1()!=null && getR2()==null;
    }
    
    public boolean isPair() {
        return getR1()!=null && getR2()!=null;
    }
    
    @Override
    public String toString() {
        String str = "<";
        
        
        for(int i=2; i<getTupleSize(); i++)
            str+="<";
        
        str += getR1().getValues().toString2();
        if(isSinglePot())
            str+= ">";
        else
            str +="; "+getR2().getValues().toString2()+">";
        
        
        
        for(int i=2; i<getTupleSize(); i++)
            str+=";"+relations.get(i).getValues().toString2()+">";
        
        
        
        
        
        return str;
        
    }
    
    

    public NodeList getRemovableVars() {

        //COMPROBAR QUE ESTO NO AFECTE AL RESULTADO
        if(removableVars == null)
            removableVars = new NodeList();
        
        return removableVars;
    }

    public void setRemovableVars(NodeList removableVars) {
        this.removableVars = removableVars;
    }
    
    
    public Potential addition() {
        Potential pot = getR1().getValues();
        if(!isSinglePot()) {  
            
            pot.print();
            getPot2().print();
            pot =pot.addition(getR2().getValues());
            
 /*           Continuous u = new Continuous();
            u.setName("U");
            pot.getVariables().insertElementAt(u, 0);*/
          //  pot.setVariables(null);
        }
        return pot;
    }
    
    public boolean areMarginal() {
        
        if(getPot1().getVariables().size()==1 && getPot2().getVariables().size()==1)
            return true;
        return false;
    
    }
    
    
    public NodeList getHeadCombined() {
        NodeList head=null;
        if(getR1().getHead() == null)
            head = new NodeList();
        else
            head = getR1().getHead().copy();
        
        head.join(getR2().getHead());
        
        return head;
    
    }
    
    public NodeList getTailCombined() {
        NodeList head = getR1().getHead().copy();
        head.join(getR2().getHead());
        
        NodeList tail = getR1().getTail().copy();
        tail.join(getR2().getTail());
        tail = tail.difference(head);
        
        return tail;
    
    }
    
    
    public int getTupleSize() {
        int n=0;
        for(int i=0; i<relations.size(); i++){
            if(relations.get(i) != null)
                n++;
        }
        
        return n;
    
    }
    
    
    public boolean generatesUnityPot(NodeList removableVars) {
        if(isPair())
            return Potential.generatesUnityPot(getPot1(), getPot2(), removableVars);
        return Potential.generatesUnityPot(getPot1(), removableVars);
    
    }
    
    
    public long getSumNodes() {
        long num = getPot1().getNumberOfNodes();
        if(isPair())
            num += getPot2().getNumberOfNodes();
        
        return num;
        
    }
    
    
}
