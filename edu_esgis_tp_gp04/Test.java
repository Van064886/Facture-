
package edu_esgis_tp_gp04 ;

import java.io.File ;
import java.io.IOException ;


public class Test{


    public Test(){ }

    public static void moovTest(String numFacture){
        
        try{
            String way = System.getProperty("user.dir") ;
            File file = new File (way + "\\" + numFacture + ".txt") ;

            if (file.createNewFile()){
                System.out.println("Fichier creer") ;
            }else{
                System.out.println("Fichier non creer") ;
            }

        } catch(IOException e){
            e.printStackTrace()  ;
        }
        
    }

  /*  public static void insert (){
        try{
            FileWriter file = new FileWriter(way + "\\" + this.numeroFacture + ".txt") ;
            file.write () ;
            file.close() ;
        }catch(IOException e){
            e.printStackTrace()  ;
        }
    }*/

}