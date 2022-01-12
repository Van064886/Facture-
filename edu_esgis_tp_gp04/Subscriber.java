package edu_esgis_tp_gp04 ;
import java.util.Scanner ;
import java.util.Date ;
import java.text.SimpleDateFormat ;
import java.util.ArrayList ;

public class Subscriber{

    public static int cpt = 0 ;
    public static int copt = 0 ;

    private String codeRegion ;
    private String codeZone ;
    private String numeroAbonne ;
    private String nomAbonne ;
    private String prenomsAbonne ;
    private int telephoneAbonne ;
    private String nomBanque ;
    private int numeroCompteAbonne ;
    private double soldeAbonne ;
    private int calibreCompteur ;
    private int numeroCompteur ;
    private String numeroPolice ;
    private String nomOwner ;
    private String prenomsOwner ;
    private int telephoneOwner ;
    private String ville ;
    private String quartier ;
    private String maison ;
    private int numeroCarre ;
    private String typeAbonne ;
    private ArrayList<Releve> listeReleve = new ArrayList<Releve>();
 
    
    public Subscriber (String nomAbonne ,String prenomsAbonne,int telephoneAbonne, String nomBanque, int numeroCompteAbonne, double soldeAbonne, int calibreCompteur, int numeroCompteur,  String nomOwner, String prenomsOwner, int telephoneOwner, String ville,  String quartier, String maison, int numeroCarre, String codeRegion, String codeZone, String typeAbonne){
      
         this.nomAbonne = nomAbonne ;
         this.prenomsAbonne = prenomsAbonne ;
         this.telephoneAbonne = telephoneAbonne ;
         this.nomBanque = nomBanque ; 
         this.numeroCompteAbonne = numeroCompteAbonne ;
         this.soldeAbonne = soldeAbonne ;
         this.calibreCompteur = calibreCompteur ;
         this.numeroCompteur = numeroCompteur ;    
         this.nomOwner = nomOwner ;
         this.prenomsOwner = prenomsOwner ;
         this.telephoneOwner = telephoneOwner ;
         this.ville = ville ;
         this.quartier = quartier ;
         this.maison = maison ;
         this.numeroCarre = numeroCarre  ;
         this.codeRegion = codeRegion ;
         this.codeZone = codeZone ;
         this.numeroPolice = codeRegion + "" + codeZone + "" + compteur() ;
         this.numeroAbonne = "SUB" + compteurNum() ;
         this.typeAbonne = typeAbonne ;

         //this.listeReleve.add(effectueRelever()) ;

    }

// Les accesseurs
    public String getNumeroAbonne(){
        return this.numeroAbonne ;
    }

    public void setNumeroAbonne(String numeroAbonne){
        this.numeroAbonne = numeroAbonne ;
    }

    public String getNomAbonne(){
        return this.nomAbonne ;
    }

    public void setNomAbonne(String nomAbonne){    
        this.nomAbonne = nomAbonne ;
    }

    public String getPrenomsAbonne(){
        return this.prenomsAbonne ;
    }

    public void setPrenomsAbonne(String prenomsAbonne){
        this.prenomsAbonne = prenomsAbonne ;
    }

    public int getTelephoneAbonne(){
        return this.telephoneAbonne ;
    }

    public void setTelephoneAbonne(int telephoneAbonne){
        this.telephoneAbonne = telephoneAbonne ;
    }

    public String getNomBanque(){
        return this.nomBanque ;
    }

    public void setNomBanque(String nomBanque){
        this.nomBanque = nomBanque ;
    }

    public int getNumeroCompteAbonne(){
        return this.numeroCompteAbonne ;
    }

    public void setNumeroCompteAbonne(int numeroCompteAbonne){
        this.numeroCompteAbonne = numeroCompteAbonne ;
    }

    public double getSoldeAbonne(){
        return this.soldeAbonne ;
    }

    public void setSoldeAbonne(double soldeAbonne){
        this.soldeAbonne = soldeAbonne ;
    }

    public int getCalibreCompteur(){
        return this.calibreCompteur ;
    }

    public void setCalibreCompteur(int calibreCompteur){
        this.calibreCompteur = calibreCompteur ;
    }

    public int getNumeroCompteur(){
        return this.numeroCompteur ;
    }

    public void setNumeroCompteur(int numeroCompteur){
        this.numeroCompteur = numeroCompteur ;
    }

    public String getNumeroPolice(){
        return this.numeroPolice ;
    }

    public void setNumeroPolice(String numeroPolice){
        this.numeroPolice = numeroPolice ;
    }

    public String getNomOwner(){
        return this.nomOwner ;
    }

    public void setNomOwner(String nomOwner){
        this.nomOwner = nomOwner ;
    }

    public String getPrenomsOwner(){
        return this.prenomsOwner ;
    }

    public void setPrenomsOwner(String prenomsOwner){
        this.prenomsOwner = prenomsOwner ;
    }

    public int getTelephoneOwner(){
        return this.telephoneOwner ;
    }

    public void setTelephoneOwner(int telephoneOwner){
        this.telephoneOwner = telephoneOwner ;
    }

    public String getVille(){
        return this.ville ;
    }

    public void setVille(String ville){
        this.ville = ville ;
    }

    public String getQuartier(){
        return this.quartier ;
    }

    public void setQuartier(String quartier){
        this.quartier = quartier ;
    }

    public String getMaison(){
        return this.maison ;
    }

    public void setMaison(String maison){
        this.maison = maison ;
    }

    public int getNumeroCarre(){
        return this.numeroCarre ;
    }

     public void setNumeroCarre(int numeroCarre){
        this.numeroCarre = numeroCarre ;
    }

    public ArrayList<Releve> getListeReleve(){
        return this.listeReleve ;
    }

    public void setListeReleve(ArrayList<Releve> listeReleve){
        this.listeReleve = listeReleve ;
    }

    public String getCodeRegion(){
        return this.codeRegion ;
    }

    public void setCodeRegion(String codeRegion){
        this.codeRegion = codeRegion ;
    }

    public String getCodeZone(){
        return this.codeZone ;
    }

    public void setCodeZone(String codeZone){
        this.codeZone = codeZone ;
    }

    public String getTypeAbonne(){
        return this.typeAbonne ;
    }

    public void setTypeAbonne(String typeAbonne){
        this.typeAbonne = typeAbonne ;
    }


// *************************************** Méthodes propres à la classe ***************************************


    // Méthode faire un relevé
    public  Releve effectueRelever(){
         Scanner scanf = new Scanner(System.in) ;
         String nomdAgent ;
         String date ;
         int indexdReleve ;
         Releve newReleve ;

         System.out.print("******************Releve******************\nEntrer la date au format [jour/mois/annee] : ") ;
         date = scanf.nextLine() ;
         System.out.print("Entrer votre nom d'agent : ") ;
         nomdAgent = scanf.nextLine() ;
         System.out.print("Entrer l'index du compteur : ") ;
         indexdReleve = scanf.nextInt() ;
         newReleve = new Releve(date, nomdAgent, indexdReleve) ;
        return newReleve ;

    }

    //Méthode compteur
    public String compteur(){
        String numero ;
        cpt++ ;
        int length = (int) (Math.log10(cpt) + 1) ;
        if(length == 1){
            numero = "0000"+ cpt  ;
        }else if (length == 2){
            numero = "000"+ cpt  ;
        }else if (length == 3){
            numero = "00"+ cpt  ;
        }else if (length == 4){
            numero = "0"+ cpt  ;
        }else{
            numero = ""+ cpt  ;
        }
        return numero ;
    }

    //Méthode compteur num abonné
    public String compteurNum(){
        String numero ;
        copt++ ;
        int length = (int) (Math.log10(cpt) + 1) ;
        if(length == 1){
            numero = "0000"+ copt  ;
        }else if (length == 2){
            numero = "000"+ copt  ;
        }else if (length == 3){
            numero = "00"+ copt  ;
        }else if (length == 4){
            numero = "0"+ copt  ;
        }else{
            numero = ""+ copt  ;
        }
        return numero ;
    }


    
}