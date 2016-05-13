/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package elvira.tools.idiagram;

import elvira.Relation;
import elvira.RelationList;
import elvira.parser.ParseException;
import elvira.potential.Potential;
import java.io.IOException;
import java.util.Vector;

/**
 *
 * @author rcabanas
 */
public class IDPotentialAnalyzer {
    
    
    
    public static long getMaxSize(RelationList r) {
        
        long max = 0;
        long size_i;
        for(int i=0; i<r.size(); i++) {
            size_i = ((Relation)(r.getRelations().get(i))).getValues().getSize();
            if( size_i> max)
                max = size_i;
        }
        
        return max;
    
    }
    
    
    
      public static long getMaxSize(Vector r) {
        
        long max = 0;
        long size_i;
        for(int i=0; i<r.size(); i++) {
            size_i = ((Relation)(r.get(i))).getValues().getSize();
            if( size_i> max)
                max = size_i;
        }
        
        return max;
    
    }
    
    
    public static long getMaxSizeCombined(Vector r) {
        
        long max = 0;
        long size_i;
        for(int i=0; i<r.size(); i++) {
            size_i = ((Relation)(r.get(i))).getValues().getSize() * ((Relation)(r.get(i))).getParents().size();
            if( size_i> max)
                max = size_i;
        }
        
        return max;
    
    }
    
    
}
