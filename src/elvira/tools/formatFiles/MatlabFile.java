/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package elvira.tools.formatFiles;

import java.io.IOException;

/**
 *
 * @author rcabanas
 */
public class MatlabFile {
    
    private String file;
    
    public MatlabFile(String file) throws IOException {
        this(file, false);
    
    }   
    
    public MatlabFile(String file, boolean append) throws IOException {
        
        this.file=file;
        PlainTextFile.initFile(file, append);
    
    }
    
    public void createArray(String nameArray) throws IOException {
        
        String line = nameArray+" = [];";
        //Writes data in matlab format
        PlainTextFile.println(file, line);
    
    }
    
    public void createCell(String nameArray) throws IOException {
        
        String line = nameArray+" = {};";
        //Writes data in matlab format
        PlainTextFile.println(file, line);
    
    }
    
    
    public void addData(String array, String data) throws IOException {
        String line = array+" = ["+array+"; "+data+"];";
        //Writes data in matlab format
        PlainTextFile.println(file, line);
    
    }
    
    public void addDataCell(String array, String data) throws IOException {
        String line = array+" = {"+array+"{:}, "+data+"};";
        //Writes data in matlab format
        PlainTextFile.println(file, line);
    
    }
    
    
   public void addLine(String line) throws IOException {

        //Writes data in matlab format
        PlainTextFile.println(file, line);
    
    }
    
    
    public static void main(String[] args) throws IOException {
        
        
       MatlabFile matf = new MatlabFile("prueba.m");
       
       matf.createArray("A");
       matf.createArray("A");
       matf.addData("A", "2, 3");
       matf.addData("A", "2.5, 13");
       matf.addData("A", "10, 12");
       matf.addData("B", "21, 31");
       matf.addData("B", "2.51, 18");
       matf.addData("B", "-0.5, 2");      
        
    }
    
    
    
}
