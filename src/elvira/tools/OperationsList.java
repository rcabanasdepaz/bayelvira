/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package elvira.tools;

import elvira.Node;

import java.util.HashSet;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author mgomez
 */
public class OperationsList {
   // List of operations
   ArrayList<Operation> operationsList;
   
   // Operation in execution
   Operation currentOperation;
   
   // Class constructor
   public OperationsList(){
      operationsList=new ArrayList<Operation>();
   }
   
   // Method for adding a new Operation
   public void addOperation(Node node){
      // Creates the new operation
      Operation operation=new Operation(node);
      
      // Add the operation to the list
      operationsList.add(operation);
      
      // This operation will be the current one
      currentOperation=operation;
   }
   
   // Method for adding a new size to current operation
   public void addSize(double size){
      currentOperation.addSize(size);
   }
   
   // Method for recovering the max size for the current operation
   public double getMaxSize(){
      return currentOperation.getMaxSize();
   }
   
    @Override
    public String toString() {
        String s = "";
        for(int i=0; i<operationsList.size(); i++) {
            s += "["+operationsList.get(i) +"]";
        }
        
        return s;
    }
   
   
   // Internal Operation class
   private class Operation{
      // Removed node in the operation
      Node node;
      
      // ArrayList with the sizes of the potentials reached
      // during the operation
      HashSet<Double> sizes;
      
      // Class constructor
      private Operation(Node node){
         this.node=node;
         sizes=new HashSet<Double>();
      }
      
      // Add a new size
      private void addSize(double size){
         sizes.add(size);
      }
      
      // Gets the max size for the operation
      private double getMaxSize(){
         double maxSize=0;
         
         for(Double size : sizes){
            if (size > maxSize){
               maxSize=size;
            }
         }
         
         return maxSize;
      }
      
      
    @Override
    public String toString() {
        String s = node+"->"+sizes;
        return s;
    }
   }
}
