package edu_esgis_tp_gp04 ;

import java.util.Date ;
import java.text.SimpleDateFormat ;
import java.io.FileWriter ;
import java.io.IOException ;

public class Facture{

    private String numeroFacture ;
    private String dateEmission ;
    private int montant ;
    private String deadline ;
    private int consommation ;
    private Subscriber abonne ;
    private String statut ;
    private String datePaiement ;

    public static int cptr = 0 ;

    public Facture(Subscriber abonne){
        //Gestion des dates
        Date today = new Date() ;
        Date dateDeadline = new Date(today.getTime() + (1000*60*60*24*15)) ;
        SimpleDateFormat date =  new SimpleDateFormat("dd/MM/yy") ;
        SimpleDateFormat datenumFact =  new SimpleDateFormat("yyyyMMdd") ;

        this.numeroFacture = datenumFact.format(today) +""+ compteur() ;
        this.dateEmission = "" + date.format(today) ;
        this.deadline = "" + date.format(dateDeadline) ;
        this.abonne = abonne ;
        this.statut = "Impayer" ;

        int length = abonne.getListeReleve().size() ;
        this.consommation = Math.abs(abonne.getListeReleve().get(length - 1).getIndexReleve() - abonne.getListeReleve().get(length - 2).getIndexReleve()) ;
        

        if (this.abonne.getCalibreCompteur() == 15){
            this.montant = calcPrixTranche(this.abonne) + 402 ;
            Test.moovTest(this.numeroFacture) ;
            insert() ;
        }else if (this.abonne.getCalibreCompteur() == 20){
            this.montant = calcPrixTranche(this.abonne) + 402 ;
            Test.moovTest(this.numeroFacture) ;
            insert() ;
        }else if (this.abonne.getCalibreCompteur() == 30){
            this.montant = calcPrixTranche(this.abonne) + 676 ;
            Test.moovTest(this.numeroFacture) ;
            insert() ;
        }else if (this.abonne.getCalibreCompteur() == 40){
            this.montant = calcPrixTranche(this.abonne) + 931 ;
            Test.moovTest(this.numeroFacture) ;
            insert() ;
        }else if (this.abonne.getCalibreCompteur() == 60){
            this.montant = calcPrixTranche(this.abonne) + 1323 ;
            Test.moovTest(this.numeroFacture) ;
            insert() ;
        }else if (abonne.getCalibreCompteur() == 80){
            this.montant = calcPrixTranche(this.abonne) + 3602 ;
            Test.moovTest(this.numeroFacture) ;
            insert() ;
        }else if (abonne.getCalibreCompteur() == 100){
            this.montant = calcPrixTranche(this.abonne) + 3902 ;
            Test.moovTest(this.numeroFacture) ;
            insert() ;
        }else{
            System.out.println("Le calibre du compteur renseigne a la creation de l'abonne n'est pas conforme aux calibres compteurs de la SONEB.") ;
        }


    }

//les accesseurs
    public String getNumeroFacture(){
        return this.numeroFacture ;
    }

     public void setNumeroFacture(String numeroFacture){
        this.numeroFacture = numeroFacture ;
    }

    public String getDateEmission(){
        return this.dateEmission ;
    }

     public void setDateEmission(String dateEmission){
        this.dateEmission = dateEmission ;
    }

    public int getMontant(){
        return this.montant ;
    }

     public void setMontant(int montant){
        this.montant = montant ;
    }

    public String getDeadline(){
        return this.deadline ;
    }

    public void setDeadline(String deadline){
        this.deadline = deadline ;
    }

    public int getConsommation(){
        return this.consommation ;
    }

    public void setConsommation(int consommation){
        this.consommation = consommation ;
    }

    public Subscriber getAbonne(){
        return this.abonne ;
    }

    public void setAbonne(Subscriber abonne){
        this.abonne = abonne ;
    }

    public String getStatut(){
        return this.statut ;
    }

    public void setStatut(String statut){
        this.statut = statut ;
    }

    public String getDatePaiement(){
        return this.datePaiement ;
    }

    public void setDatePaiement(String datePaiement){
        this.datePaiement = datePaiement ;
    }

// *************************************** Méthodes propres à la classe***************************************
    public void emettreFacture(){
        
        System.out.print("******************************************************************************************************\n*******************************#SOCIETE NATIONALE DES EAUX DU BANGOSSE#*******************************\n****************************************#SOCIETE ANONIME#*********************************************\n*****************************#04 BP: 905 BANGOSSE CITY, TEL: 80-11-66-44#*****************************\n******************************************************************************************************\n\n\n\t\tNUMERO DE FACTURE:\t" + this.numeroFacture +"\n\t\tDATE DE FACTURE:\t"     + this.dateEmission +"\n\t\tMOIS:\t"   + this.dateEmission +"\n\t\tPERIODE DE CONSOMMATION:\t Du "    + this.abonne.getListeReleve().get(this.abonne.getListeReleve().size() - 2).getDate() + " au " + this.abonne.getListeReleve().get(this.abonne.getListeReleve().size() - 1).getDate() + "\n\t\tDATE LIMITE DE PAIEMENT:\t"     + this.deadline +"\n\n________________________\nINFORMATIONS DE L'ABONNE:\n------------------------------------------------------------------------------------------------------\nNumero Abonne:\t "    + this.abonne.getNumeroAbonne() + "\nNom et Prenoms:\t " + this.abonne.getNomAbonne() + "" + this.abonne.getPrenomsAbonne() +"\nTelephone:\t " + this.abonne.getTelephoneAbonne() + "\n------------------------------------------------------------------------------------------------------\n\n__________________________\nINFORMATION DE BRANCHEMENT:\n------------------------------------------------------------------------------------------------------\nNumero Police:\t" + this.abonne.getNumeroPolice() +"\nNom et Prenoms Proprietaire:\t"  + this.abonne.getNomOwner() + "" + this.abonne.getPrenomsOwner() +"\nAdresse:\t" +this.abonne.getVille() + ", " + this.abonne.getQuartier()+ ", c/ "+ this.abonne.getNumeroCarre() +"\nNumero Compteur:\t" +this.abonne.getNumeroCompteur() +"\nCalibre:\t" + this.abonne.getCalibreCompteur() +"\n------------------------------------------------------------------------------------------------------\n\n__________________________\nINFORMATION DE FACTURATION:\n------------------------------------------------------------------------------------------------------\nType Abonne:" + this.abonne.getTypeAbonne() +"\nINDEX:\n\tAncien:\t "  + this.abonne.getListeReleve().get(this.abonne.getListeReleve().size() - 2).getIndexReleve() +"\n\tNouveau:\t " + this.abonne.getListeReleve().get(this.abonne.getListeReleve().size() - 1).getIndexReleve() +"\nNet a Payer:\t" + this.montant +"\n" + "CONSOMMATION:\n\tTotale:\t "+ this.consommation) ;
        
        if (this.consommation >= 0 || this.consommation <= 5 ){//Tranche 1
            System.out.println("\n\tTranche 1:\t" + this.consommation +"\t\n\tTranche 2:\t \n\tTranche 3:\t\n\n") ;
        }else if(this.consommation <= 50){//Tranche 2
            System.out.println("\n\tTranche 1:\t\n\tTranche 2:\t" + this.consommation +"\t \n\tTranche 3:\t\n\n") ;
        }else{//Tranche 3
            System.out.println("\n\tTranche 1:\t\n\tTranche 2:\t \n\tTranche 3:\t" + this.consommation +"\n\n") ;
        }
        
        System.out.println("MONTANT:\t "  + calcPrixTranche(this.abonne) / (1.18) +"\n") ;

        if (this.abonne.getCalibreCompteur() == 15){
            System.out.println("LOCATION:\t402\n") ;
            System.out.println("TOTAL HT:\t" + ( (calcPrixTranche(this.abonne) / (1.18)) + 402 )+ "\n") ;
        }else if (this.abonne.getCalibreCompteur() == 20){
            System.out.println("LOCATION:\t402\n") ;
            System.out.println("TOTAL HT:\t" + ( (calcPrixTranche(this.abonne) / (1.18)) + 402 )+ "\n") ;
        }else if (this.abonne.getCalibreCompteur() == 30){
            System.out.println("LOCATION:\t676\n") ;
            System.out.println("TOTAL HT:\t" + ( (calcPrixTranche(this.abonne) / (1.18)) + 676 )+ "\n") ;
        }else if (this.abonne.getCalibreCompteur() == 40){
            System.out.println("LOCATION:\t931\n") ;
            System.out.println("TOTAL HT:\t" + ( (calcPrixTranche(this.abonne) / (1.18)) + 931 )+ "\n") ;
        }else if (this.abonne.getCalibreCompteur() == 60){
            System.out.println("LOCATION:\t1323\n") ;
            System.out.println("TOTAL HT:\t" + ( (calcPrixTranche(this.abonne) / (1.18)) + 1323 )+ "\n") ;
        }else if (abonne.getCalibreCompteur() == 80){
            System.out.println("LOCATION:\t3602\n") ;
            System.out.println("TOTAL HT:\t" + ( (calcPrixTranche(this.abonne) / (1.18)) + 3602 )+ "\n") ;
        }else if (abonne.getCalibreCompteur() == 100){
            System.out.println("LOCATION:\t3902\n") ;
            System.out.println("TOTAL HT:\t" + ( (calcPrixTranche(this.abonne) / (1.18)) + 3902 )+ "\n") ;
        }
        System.out.println("TVA:\t " + ( (calcPrixTranche(this.abonne) / (1.18)) * 0.18 ) + "\n") ;
        System.out.println("STATUT:\t" +this.statut +"\n") ;
        System.out.println("TOTAL DU:\t" + this.montant +"\n------------------------------------------------------------------------------------------------------\n\n\n******************************#Votre Satisfaction Notre Raison d'existee#******************************\n\n\n") ;


    }

    //Méthode pour compteur
     public String compteur(){
        String numero ;
        cptr++ ;
        int length = (int) (Math.log10(cptr) + 1) ;
        if (length == 1){
            numero = "000"+ cptr  ;
        }else if (length == 2){
            numero = "00"+ cptr  ;
        }else if (length == 3){
            numero = "0"+ cptr  ;
        }else{
            numero = ""+ cptr  ;
        }
        return numero ;
    }

    //Méthode por calculer le prix par tranche
    public int calcPrixTranche(Subscriber abonne){
        int prix = 0 ;
        if(abonne.getTypeAbonne().equals("A1")){

            if (this.consommation >= 0 || this.consommation <= 5 ){//Tranche 1
                prix = this.consommation * 198 ;
            }else if(this.consommation <= 50){//Tranche 2
                prix = (this.consommation * 453) + (this.consommation * 453 * 18/100) ;
            }else{//Tranche 3
                prix = (this.consommation * 658) + (this.consommation * 658 * 18/100) ;
            }
             
        }else if(abonne.getTypeAbonne().equals("A2")){

            if (this.consommation <= 10){
                prix = 0 ;
            }else{

                if ((this.consommation-10) >= 0 || (this.consommation-10) <= 5 ){//Tranche 1
                prix = this.consommation * 198 ;
                }else if((this.consommation-10) <= 50){//Tranche 2
                prix = ((this.consommation-10) * 453) + ((this.consommation-10) * 453 * 18/100) ;
                }else{//Tranche 3
                prix = ((this.consommation-10) * 658) + ((this.consommation-10) * 658 * 18/100) ;
                }

            }

        }else if(abonne.getTypeAbonne().equals("A3")){

            prix = 10000 ;
             
        }else if(abonne.getTypeAbonne().equals("A4")){

            if (this.consommation >= 0 || this.consommation <= 15 ){//Tranche 1
                prix = (this.consommation * 498) + (this.consommation * 498 * 18/100) ;
            }else if(this.consommation <= 75){//Tranche 2
                prix = (this.consommation * 453) + (this.consommation * 395 * 18/100) ;
            }else{//Tranche 3
                prix = (this.consommation * 658) + (this.consommation * 150 * 18/100) ;
            }
             
        }
        return prix ;
    }
    //Méthode insertion des données dans le fichier
   public  void insert(){
        try{
            String way = System.getProperty("user.dir") ;
            FileWriter file = new FileWriter(way + "\\" + this.numeroFacture + ".txt") ;
            file.write ("******************************************************************************************************\n*******************************#SOCIETE NATIONALE DES EAUX DU BANGOSSE#*******************************\n****************************************#SOCIETE ANONIME#*********************************************\n*****************************#04 BP: 905 BANGOSSE CITY, TEL: 80-11-66-44#*****************************\n******************************************************************************************************\n\n\n\t\tNUMERO DE FACTURE:\t" + this.numeroFacture +"\n\t\tDATE DE FACTURE:\t"     + this.dateEmission +"\n\t\tMOIS:\t"   + this.dateEmission +"\n\t\tPERIODE DE CONSOMMATION:\t Du "    + this.abonne.getListeReleve().get(this.abonne.getListeReleve().size() - 2).getDate() + " au " + this.abonne.getListeReleve().get(this.abonne.getListeReleve().size() - 1).getDate() + "\n\t\tDATE LIMITE DE PAIEMENT:\t"     + this.deadline +"\n\n________________________\nINFORMATIONS DE L'ABONNE:\n------------------------------------------------------------------------------------------------------\nNumero Abonne:\t "    + this.abonne.getNumeroAbonne() + "\nNom et Prenoms:\t " + this.abonne.getNomAbonne() + "" + this.abonne.getPrenomsAbonne() +"\nTelephone:\t " + this.abonne.getTelephoneAbonne() + "\n------------------------------------------------------------------------------------------------------\n\n__________________________\nINFORMATION DE BRANCHEMENT:\n------------------------------------------------------------------------------------------------------\nNumero Police:\t" + this.abonne.getNumeroPolice() +"\nNom et Prenoms Proprietaire:\t"  + this.abonne.getNomOwner() + "" + this.abonne.getPrenomsOwner() +"\nAdresse:\t" +this.abonne.getVille() + ", " + this.abonne.getQuartier()+ ", c/ "+ this.abonne.getNumeroCarre() +"\nNumero Compteur:\t" +this.abonne.getNumeroCompteur() +"\nCalibre:\t" + this.abonne.getCalibreCompteur() +"\n------------------------------------------------------------------------------------------------------\n\n__________________________\nINFORMATION DE FACTURATION:\n------------------------------------------------------------------------------------------------------\nType Abonne:" + this.abonne.getTypeAbonne() +"\nINDEX:\n\tAncien:\t "  + this.abonne.getListeReleve().get(this.abonne.getListeReleve().size() - 2).getIndexReleve() +"\n\tNouveau:\t " + this.abonne.getListeReleve().get(this.abonne.getListeReleve().size() - 1).getIndexReleve() +"\nNet a Payer:\t" + this.montant +"\n" + "CONSOMMATION:\n\tTotale:\t "+ this.consommation) ;
            if (this.consommation >= 0 || this.consommation <= 5 ){//Tranche 1
                file.write("\n\tTranche 1:\t" + this.consommation +"\t\n\tTranche 2:\t \n\tTranche 3:\t\n\n") ;
            }else if(this.consommation <= 50){//Tranche 2
                file.write("\n\tTranche 1:\t\n\tTranche 2:\t" + this.consommation +"\t \n\tTranche 3:\t\n\n") ;
            }else{//Tranche 3
                file.write("\n\tTranche 1:\t\n\tTranche 2:\t \n\tTranche 3:\t" + this.consommation +"\n\n") ;
            }
        
            file.write("MONTANT:\t "  + calcPrixTranche(this.abonne) / (1.18) +"\n") ;

            if (this.abonne.getCalibreCompteur() == 15){
                file.write("LOCATION:\t402\n") ;
                file.write("TOTAL HT:\t" + ( (calcPrixTranche(this.abonne) / (1.18)) + 402 )+ "\n") ;
            }else if (this.abonne.getCalibreCompteur() == 20){
                file.write("LOCATION:\t402\n") ;
                file.write("TOTAL HT:\t" + ( (calcPrixTranche(this.abonne) / (1.18)) + 402 )+ "\n") ;
            }else if (this.abonne.getCalibreCompteur() == 30){
                file.write("LOCATION:\t676\n") ;
                file.write("TOTAL HT:\t" + ( (calcPrixTranche(this.abonne) / (1.18)) + 676 )+ "\n") ;
            }else if (this.abonne.getCalibreCompteur() == 40){
                file.write("LOCATION:\t931\n") ;
                file.write("TOTAL HT:\t" + ( (calcPrixTranche(this.abonne) / (1.18)) + 931 )+ "\n") ;
            }else if (this.abonne.getCalibreCompteur() == 60){
                file.write("LOCATION:\t1323\n") ;
                file.write("TOTAL HT:\t" + ( (calcPrixTranche(this.abonne) / (1.18)) + 1323 )+ "\n") ;
            }else if (abonne.getCalibreCompteur() == 80){
                file.write("LOCATION:\t3602\n") ;
                file.write("TOTAL HT:\t" + ( (calcPrixTranche(this.abonne) / (1.18)) + 3602 )+ "\n") ;
            }else if (abonne.getCalibreCompteur() == 100){
                file.write("LOCATION:\t3902\n") ;
                file.write("TOTAL HT:\t" + ( (calcPrixTranche(this.abonne) / (1.18)) + 3902 )+ "\n") ;
            }
            file.write("TVA:\t " + ( (calcPrixTranche(this.abonne) / (1.18)) * 0.18 ) + "\n") ;
            file.write("STATUT:\t" +this.statut +"\n") ;
            file.write("TOTAL DU:\t" + this.montant +"\n------------------------------------------------------------------------------------------------------\n\n\n******************************#Votre Satisfaction Notre Raison d'existee#******************************\n\n\n") ;

            file.close() ;
        }catch(IOException e){
            e.printStackTrace()  ;
        }
    }



}


