/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package elvira.tools.formatFiles;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rcabanas
 */
public class PlainTextFile {

    /**
     * @param args the command line arguments
     */
    
    
    public static void initFile(String file, boolean append) throws IOException {
       
       FileWriter outFile;
       PrintWriter out;
       outFile = new FileWriter(file,append);
       out = new PrintWriter(outFile);
       out.close();
    
    }
    
    public static void initFile(String file) throws IOException {
        initFile(file, false);
        
    }   
    public static void println(String file, String line) throws IOException {
       
        FileWriter outFile;
       PrintWriter out;
       outFile = new FileWriter(file, true);
       out = new PrintWriter(outFile);
       out.println(line);
       out.close();
        
    
    }
    
    public static void print(String file, String line) throws IOException {
       
        FileWriter outFile;
       PrintWriter out;
       outFile = new FileWriter(file, true);
       out = new PrintWriter(outFile);
       out.print(line);
       out.close();
        
    
    }
    
    public static String readLastLine(String file) throws FileNotFoundException, IOException {
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        

        
        String s, lastLine="";
        
        
       
        while((s=br.readLine()) != null){
            lastLine = s;
        }
        
        
        fr.close();
        
        return lastLine;
    
    }
    
    
    
    public static boolean exists(String file) throws IOException {
    
        boolean exists = true;
        try {
            FileReader fr = new FileReader(file);
            fr.close();
        } catch (FileNotFoundException ex) {
            
            exists = false;
        }
        
        
        return exists;
    
    }
    
    
        public static String readFirstLine(String file) throws FileNotFoundException, IOException {
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        
        String s = br.readLine();
        
        

        fr.close();
        
        return s;
    
    }
    
    public static void main(String[] args) throws IOException {
        
/*        if(PlainTextFile.exists("pruebasLog2.txt")) { 
               System.out.println(PlainTextFile.readLastLine("pruebasLog2.txt"));
        }else{
            System.out.println("pruebasLog2.txt no existe");
        }
       System.out.println(PlainTextFile.readFirstLine("pruebasLog.txt"));
        
        
        PlainTextFile.initFile("pruebasLog.txt");
        PlainTextFile.println("pruebasLog.txt", "Linea1");
        
       PlainTextFile.initFile("pruebasLog.txt");
       PlainTextFile.println("pruebasLog.txt", "Linea2");
       PlainTextFile.println("pruebasLog.txt", "Linea3");
       
       System.out.println(PlainTextFile.readLastLine("pruebasLog.txt"));
       System.out.println(PlainTextFile.readFirstLine("pruebasLog.txt"));
       
       */
        
        
        System.out.println(readLastLine("ids/ijar_mejorado/log.txt"));
        
    }
}
