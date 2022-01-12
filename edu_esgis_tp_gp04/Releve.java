
package edu_esgis_tp_gp04 ;

public class Releve{

    private String date  ;
    private String nomAgent ;
    private int indexReleve ;

    public Releve(String date, String nomAgent, int indexReleve){
        this.date = date ;
        this.nomAgent = nomAgent ;
        this.indexReleve = indexReleve ;

    }

//les accesseurs
    public String getDate(){
        return this.date ;
    }

     public void setDate(String date){
        this.date = date ;
    }

    public String getNomAgent(){
        return this.nomAgent ;
    }

     public void setNomAgent(String nomAgent){
        this.nomAgent = nomAgent ;
    }

    public int getIndexReleve(){
        return this.indexReleve ;
    }

     public void setIndexReleve(int indexReleve){
        this.indexReleve = indexReleve ;
    }
    
}