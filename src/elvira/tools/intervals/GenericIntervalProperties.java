/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elvira.tools.intervals;

import elvira.Configuration;
import elvira.NodeList;
import elvira.potential.IPotentialInterval;
import elvira.potential.Potential;
import elvira.potential.PotentialIntervalTable;
import java.math.BigDecimal;

/**
 *
 * @author rcabanas
 */
public class GenericIntervalProperties {

    public static boolean isProper(Potential pot) {

        if(!(pot instanceof IPotentialInterval)){
            System.out.println("ERROR: isProper on a invalid potential");
            System.exit(1);
        }
            
        try{
        
        if(pot.getTail().size()==0)
            System.out.println();
            
        Configuration confTail = new Configuration(pot.getTail());
        Configuration confHead;

        for (int y = 0; y < confTail.possibleValues(); y++) {
            confHead = new Configuration(pot.getHead());

            double sum_li = 0, sum_ui = 0;
            for (int x = 0; x < confHead.possibleValues(); x++) {

                Configuration conf;
        //        if(pot.getTail().size()>0)
                    conf = new Configuration(confHead, confTail, new NodeList(pot.getVariables()));
      //          else 
       //             conf = confHead;
                
                
                
                
                double li = ((IPotentialInterval)pot).getMinValue(conf);
                double ui = ((IPotentialInterval)pot).getMaxValue(conf);

          //      System.out.println(li);

                sum_li += li;
                sum_ui += ui;

                confHead.nextConfiguration();
            }

            if ((sum_li > 1.00001 || sum_ui < 0.9999)) {
                return false;
            }
            confTail.nextConfiguration();
        }

        }catch(Exception e) {
            System.out.print(e);
            e.printStackTrace();
            System.exit(0);
        }
        return true;
    }
    
    public static boolean isReachable(Potential pot) {

        if(!(pot instanceof IPotentialInterval)){
            System.out.println("ERROR: isReachable on a invalid potential");
            System.exit(1);
        }
            
        
        Configuration confTail = new Configuration(pot.getTail());
        Configuration confHead, confHead2;

        for (int y = 0; y < confTail.possibleValues(); y++) {
            confHead = new Configuration(pot.getHead());

            for (int x = 0; x < confHead.possibleValues(); x++) {

                Configuration conf = new Configuration(confTail, confHead, new NodeList(pot.getVariables()));

        
                double li = ((IPotentialInterval)pot).getMinValue(conf);
                double ui = ((IPotentialInterval)pot).getMaxValue(conf);

                double sum_lj = 0, sum_uj = 0;
                confHead2 = new Configuration(pot.getHead());
                for (int x2 = 0; x2 < confHead2.possibleValues(); x2++) {

                    Configuration conf2 = new Configuration(confHead2, confTail, new NodeList(pot.getVariables()));

                    if(!confHead.equals(confHead2)) {
                        double lj = ((IPotentialInterval)pot).getMinValue(conf2);
                        double uj = ((IPotentialInterval)pot).getMaxValue(conf2);

                        sum_lj += lj;
                        sum_uj += uj;
                    }
                    confHead2.nextConfiguration();

                }

                if ((li + sum_uj < 1 || ui + sum_lj > 1)) {
                    return false;
                }
                confHead.nextConfiguration();
            }

            confTail.nextConfiguration();
        }

        return true;
    }

    
    

    public  static void makeReachable(Potential pot) {
        

        if(!(pot instanceof IPotentialInterval)){
            System.out.println("ERROR: makeReachable on a invalid potential");
            System.exit(1);
        }

     try{   
        
  /*      if(!((IPotentialInterval)pot).isProper()) {
            ((PotentialIntervalTable)pot).fixBounds(true);
                if(!((IPotentialInterval)pot).isProper()) {
                System.out.println("ERROR: trying to make reachable a non-proper potential");
                pot.print();
                System.exit(1);
            }
        }*/
        Configuration confTail = new Configuration(pot.getTail());
        Configuration confHead, confHead2;

        for (int y = 0; y < confTail.possibleValues(); y++) {
            confHead = new Configuration(pot.getHead());

            for (int x = 0; x < confHead.possibleValues(); x++) {

                Configuration conf = new Configuration(confHead, confTail, new NodeList(pot.getVariables()));

                double li = ((IPotentialInterval)pot).getMinValue(conf);
                double ui = ((IPotentialInterval)pot).getMaxValue(conf);

            //    conf.print();

                double sum_lj = 0, sum_uj = 0;
                confHead2 = new Configuration(pot.getHead());
                for (int x2 = 0; x2 < confHead2.possibleValues(); x2++) {

                    Configuration conf2 = new Configuration(confHead2, confTail, new NodeList(pot.getVariables()));
                    int j = conf2.getIndexInTable();

                    if (!confHead.equals(confHead2)) {
                        double lj = ((IPotentialInterval)pot).getMinValue(conf2);
                        double uj = ((IPotentialInterval)pot).getMaxValue(conf2);

                        sum_lj = BigDecimal.valueOf(sum_lj).add(BigDecimal.valueOf(lj)).doubleValue();
                        sum_uj = BigDecimal.valueOf(sum_uj).add(BigDecimal.valueOf(uj)).doubleValue();

                        /*
                        sum_lj += lj;
                        sum_uj += uj;*/
                    }
                    confHead2.nextConfiguration();

                }

                        
                      if(BigDecimal.valueOf(li).compareTo(BigDecimal.valueOf(1).subtract(BigDecimal.valueOf(sum_uj)))==-1)
                          li = BigDecimal.valueOf(1).subtract(BigDecimal.valueOf(sum_uj)).doubleValue();
                      //((IPotentialInterval)pot).setMinValue(conf, Math.max(li, BigDecimal.valueOf(1).subtract(BigDecimal.valueOf(sum_uj)).doubleValue()));

          //          ((IPotentialInterval)pot).setMinValue(conf, Math.max(li, 1 - sum_uj));
                        if(BigDecimal.valueOf(ui).compareTo(BigDecimal.valueOf(1).subtract(BigDecimal.valueOf(sum_lj)))==1)
                            ui = BigDecimal.valueOf(1).subtract(BigDecimal.valueOf(sum_lj)).doubleValue();
                     //  ((IPotentialInterval)pot).setMaxValue(conf, Math.min(ui, BigDecimal.valueOf(1).subtract(BigDecimal.valueOf(sum_lj)).doubleValue()));
 
                        double aux;
                        if(ui<li) {
                           aux = ui;
                           ui = li;
                           li = aux;
                        }
                        
                        ((IPotentialInterval)pot).setMaxValue(conf,ui);
                        ((IPotentialInterval)pot).setMinValue(conf,li);
                             
                        
                        
                   // ((IPotentialInterval)pot).setMaxValue(conf, Math.min(ui, 1.0 - sum_lj));
               

                    
                confHead.nextConfiguration();
            }

            confTail.nextConfiguration();
        }

     }catch(Exception e) {
                     System.out.print(e);
            e.printStackTrace();
            System.exit(0);
     }

    }

    /*
      public  static void makeReachable(Potential pot) {
        

        if(!(pot instanceof IPotentialInterval)){
            System.out.println("ERROR: makeReachable on a invalid potential");
            System.exit(1);
        }

     try{   
        

        Configuration confTail = new Configuration(pot.getTail());
        Configuration confHead, confHead2;

        for (int y = 0; y < confTail.possibleValues(); y++) {
            confHead = new Configuration(pot.getHead());

            for (int x = 0; x < confHead.possibleValues(); x++) {

                Configuration conf = new Configuration(confHead, confTail, new NodeList(pot.getVariables()));

                double li = ((IPotentialInterval)pot).getMinValue(conf);
                double ui = ((IPotentialInterval)pot).getMaxValue(conf);

            //    conf.print();

                double sum_lj = 0, sum_uj = 0;
                confHead2 = new Configuration(pot.getHead());
                for (int x2 = 0; x2 < confHead2.possibleValues(); x2++) {

                    Configuration conf2 = new Configuration(confHead2, confTail, new NodeList(pot.getVariables()));
                    int j = conf2.getIndexInTable();

                    if (!confHead.equals(confHead2)) {
                        double lj = ((IPotentialInterval)pot).getMinValue(conf2);
                        double uj = ((IPotentialInterval)pot).getMaxValue(conf2);

                        sum_lj += lj;
                        sum_uj += uj;
                    }
                    confHead2.nextConfiguration();

                }

  
                
           //     if((1.0 - sum_uj)-li > 0.00001)
                    ((IPotentialInterval)pot).setMinValue(conf, Math.max(li, 1 - sum_uj));
               
                
            //    if(ui - (1.0 - sum_lj) > 0.00001)
                    ((IPotentialInterval)pot).setMaxValue(conf, Math.min(ui, 1.0 - sum_lj));
               
                
                confHead.nextConfiguration();
            }

            confTail.nextConfiguration();
        }

     }catch(Exception e) {
                     System.out.print(e);
            e.printStackTrace();
            System.exit(0);
     }

    }

    
    */
    
    public static boolean consistentLimits(PotentialIntervalTable ipot) {
        
        Configuration conf = new Configuration(ipot.getListNonTransparents());
        for(int i=0; i<conf.possibleValues(); i++) {
            if(ipot.getMaxValue(conf)<ipot.getMinValue(conf))
                return false;
            
            conf.nextConfiguration();
        }
        return true;
    
    
    }
    
    

}
