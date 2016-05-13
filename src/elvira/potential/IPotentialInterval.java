/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package elvira.potential;

import elvira.Configuration;

/**
 *
 * @author Rafael Cabañas rcabanas@decsai.ugr.es
 */
public interface IPotentialInterval {

        public void setMaxValue(Configuration conf, double value);
        public void setMinValue(Configuration conf, double value);
        public double getMaxValue(Configuration conf);
        public double getMinValue(Configuration conf);
        
        public boolean isProper(); 
        public boolean isReachable();
        public void makeReachable();
        public double getHigherMax();
        public double getLowerMin();
        
        public boolean isIncluded(IPotentialInterval ipot);
        public boolean isIncluded(IPotentialInterval ipot, double error);

}
