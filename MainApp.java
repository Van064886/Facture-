/*Programme écrit par : ABIBOU Sidikoth
                        ATCHAOUE Giovanni
                        ATADE Vanique
                        BOUKO Georgino
                        DANNON Patrick

Groupe : 01
Groupe de travail n°04
L2IRT, année académique: 2020-2021 */

import edu_esgis_tp_gp04.* ;
import java.util.Scanner ;
import java.util.Date ;
import java.text.SimpleDateFormat ;
import java.util.ArrayList ;
import java.util.InputMismatchException ;
import java.io.File ;
import java.io.IOException ;


public class MainApp{

    public static ArrayList<Subscriber> dataBase = new ArrayList<Subscriber>() ;
    public static ArrayList<Facture> dataBill = new ArrayList<Facture>() ;

    public static void main (String[] args) throws Exception { 
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    
        Menu() ;
         
    }

//******************************************************************************************************************************************
//Méthode qui affiche le menu
    public static void afficheMenu(){                                     
        System.out.println("******************************* Menu Principal ********************************\n> Creer un nouveau abonne..........................................  -->Taper a\n> Modifier les informations d'un abonne............................  -->Taper z\n> Emettre une facture..............................................  -->Taper e\n> Effectuer un releve..............................................  -->Taper r\n> Verifier le statut d'une facture ................................  -->Taper t\n> Payer une facture en instance....................................  -->Taper p\n> Verifier le solde et le nombre de facture en instance d'un abonne  -->Taper o\n> Quitter le programme.............................................  -->Taper q\n*******************************************************************************") ;
    }

// Méthode pour les choix du menu
    public static void choixMenu(){
        String valide ;
         String in;
         Scanner scanff = new Scanner(System.in) ;

        in = scanff.nextLine() ;

        switch (in){
            case "a" :
                createSubscriber() ;
                Menu() ;
                break;

            case "z" :
                //Subscriber abon = new Subscriber("1", "2", 3, "4", 5, 40, 40, 40, "9", "10", 11, "12", "13", "14", 15, "A", "004", "A1");
                //Facture obj = new Facture(abon) ;
                //System.out.println(obj.getConsommation()) ;
                //dataBase.add(abon) ;
                if(dataBase.size() < 1){
                    System.out.println("\nVous ve pouvez pas effectuer cette action car aucun abonne n'a encore ete cree !\n") ;
                    Menu() ;
                }else{
                    System.out.println(dataBase.get(0).getNumeroPolice()) ;
                    modifyData();
                    Menu() ;
                }
                break;
            
            case "e" :
                boolean b = true ;
                 if(dataBase.size() < 1){
                    System.out.println("\nVous ve pouvez pas effectuer cette action car aucun abonne n'a encore ete cree !\n") ;
                    
                    Menu() ;
                }else{
                System.out.println("**********************Facture**********************\n\n\tEntrer le numero de Police (NPA) de l'abonne:\n");
                String entrer ;
                Scanner sccanfff = new Scanner(System.in) ;
                entrer = sccanfff.nextLine() ;
                    for(Subscriber element : dataBase){
                        if ( entrer.equals(element.getNumeroPolice()) ){
                            //element.getListeReleve().add(element.effectueRelever());
                            while(element.getListeReleve().size() < 2){
                                        callRelever() ;
                            }
                            
                            Facture facture = new Facture(element) ;
                            dataBill.add(facture) ;
                            if ( (facture.getAbonne().getCalibreCompteur() == 15) || (facture.getAbonne().getCalibreCompteur() == 20) || (facture.getAbonne().getCalibreCompteur() == 30) || (facture.getAbonne().getCalibreCompteur() == 40) || (facture.getAbonne().getCalibreCompteur() == 60) || (facture.getAbonne().getCalibreCompteur() == 80) || (facture.getAbonne().getCalibreCompteur() == 100) ){
                                facture.emettreFacture() ;
                            }else{
                                System.out.println("La facture ne peut etre emise\n") ;
                            }
                        }else{
                            System.out.println("Cet abonne n'n'existe pas dans la base de donnee\n") ;
                        }

                    }
                    Menu() ;
                }
                
                break;
            
            case "r" :
                if(dataBase.size() < 1){
                    System.out.println("\nVous ve pouvez pas effectuer cette action car aucun abonne n'est encore enregistre dans la base de donnee !\n") ;
                    Menu() ;
                }else{
                    callRelever() ;
                    Menu() ;
                }
                break;

            case "t" :
                if(dataBill.size() < 1){
                    System.out.println("\nVous ve pouvez pas effectuer cette action car aucune facture n'est encore emise !\n") ;
                    Menu() ;
                }else{
                    System.out.println("**********************Verification du Statut de facture**********************\n\n\tEntrer le numero de Police (NPA) de l'abonne:\n");
                    
                    String ent ;
                    Scanner entr = new Scanner(System.in) ;
                    ent = entr.nextLine() ;
                        for(Facture element : dataBill){
                            if ( ent.equals(element.getAbonne().getNumeroPolice()) ){
                                verifyStatus(element) ;
                            }
                        }
                    Menu() ;
                }
                break;

            case "p" :
                 if(dataBill.size() < 1){
                    System.out.println("\nVous ve pouvez pas effectuer cette action car aucune facture n'est encore emise !\n") ;
                    Menu() ;
                }else{
                    System.out.println("**********************Payer une facture en instance**********************\n\n\tEntrer le numero de Facture de l'abonne:\n");
                    
                    String entt ;
                    Scanner enttr = new Scanner(System.in) ;
                    entt = enttr.nextLine() ;
                        for(Facture element : dataBill){
                            if ( entt.equals(element.getNumeroFacture()) ){
                                if(element.getAbonne().getSoldeAbonne() < element.getMontant()){
                                    System.out.println("\nVotre solde est insuffisant la transaction a echouer ! Merci de bien vouloir le recharger.\n") ;
                                }else{
                                    Date today = new Date() ;
                                    SimpleDateFormat date =  new SimpleDateFormat("dd/MM/yy") ;
                                    element.getAbonne().setSoldeAbonne(element.getAbonne().getSoldeAbonne() - element.getMontant()) ;
                                    for(Subscriber el : dataBase){
                                        if(element.getAbonne().getNumeroPolice().equals(el.getNumeroPolice())){
                                            el.setSoldeAbonne(element.getAbonne().getSoldeAbonne() - element.getMontant()) ;
                                        }
                                    }
                                    element.setStatut("Payer") ;
                                    element.setDatePaiement("" + date.format(today)) ;
                                    System.out.println ("Numero de facture :" + element.getNumeroFacture() + "\nMontant de la facture :\t" +element.getMontant() + "\nDate de paiement :\t" + element.getDatePaiement() + "\nStatut :\t" + element.getStatut() ) ;

                                }
                            }else{
                                System.out.println("\n\tCe numero de facture ne se trouve pas dans notre base de donnee.\n") ;
                            }
                        }
                    Menu() ;
                }
                break;

            case "o" :
                if(dataBase.size() < 1){
                    System.out.println("\nVous ve pouvez pas effectuer cette action car aucun abonne n'est encore enregistre dans la base de donnee !\n") ;
                    Menu() ;
                }else{
                    System.out.println("**********************Solde et Facture(s) en instence(s) de paiement**********************\n\n\tEntrer le numero de Police (NPA) de l'abonne:\n");
                    String etr ;
                    Scanner et = new Scanner(System.in) ;
                    etr = et.nextLine() ;
                    verifySoldFact(etr) ;
                    Menu() ;
                }
                break;

            case "q" :
                boolean a = true ;
                while (a){
                    System.out.println("Voulez vous vraiment quitter le programme ? (Entrer y pour <<oui>> et n pour <<non>>)");
                    valide = scanff.nextLine();
                    if (valide.equalsIgnoreCase("n")){
                        a = false ;
                        Menu() ;
                    }else if (valide.equalsIgnoreCase("y")){
                        a = false ;

                        if (dataBill.size()  > 0){
                            try{
                                for(Facture elt : dataBill){
                                    String way = System.getProperty("user.dir") ;
                                    File file = new File (way + "\\" + elt.getNumeroFacture() + ".txt") ;
                                    if(file.delete()){
                                        System.out.println("Le fichier " + file.getName() + " a ete supprimer avec succes.") ;
                                    }else{
                                        System.out.println("La suppression du fichier " + file.getName() + " a echoue!") ;
                                    }
                                }
                            }catch(Exception e){
                                e.printStackTrace() ;
                            }
                        }

                        System.out.println("Fin du programme") ;
                    }else {
                        System.out.println("Entrer un caractere entre y et n !") ;
                    }
                }
                
                break;

            default :
                System.out.println("\n\tAttention vueillez choisir un des caracteres definis dans le menu !\n\tLes informations entrees sont sensibles a la casse !\n");
                Menu() ;
                break;
        }

    }

// Métode pour créer l'abonné
    public static void createSubscriber(){
        Scanner s = new Scanner(System.in) ;
        Scanner sc = new Scanner(System.in) ;
        Scanner sca = new Scanner(System.in) ;
        Scanner scan = new Scanner(System.in) ;
        Scanner scann = new Scanner(System.in) ;
        Scanner scanne = new Scanner(System.in) ;
        Scanner scanner = new Scanner(System.in) ;
        Scanner ss = new Scanner(System.in) ;
        Scanner scsc = new Scanner(System.in) ;
        Scanner scasca = new Scanner(System.in) ;
        Scanner scanscan = new Scanner(System.in) ;
        Scanner scannscann = new Scanner(System.in) ;
        Scanner scannescanne = new Scanner(System.in) ;
        Scanner scannerscanner = new Scanner(System.in) ;
        Scanner sss = new Scanner(System.in) ;
        Scanner ssss = new Scanner(System.in) ;

        String codedRegion ;
        String codedZone ;
        String nomdAbonne ;
        String prenomsdAbonne ;
        int telephonedAbonne ;
        String nomdBanque ;
        int numerodCompteAbonne ;
        double soldedAbonne ;
        int calibredCompteur ;
        int numerodCompteur ;
        String nomdOwner ;
        String prenomsdOwner ;
        int telephonedOwner ;
        String villed ;
        String quartierd ;
        String maisond ;
        int numerodCarre ;
        String typedAbonne ;

        System.out.print("*************Creation d'un nouvelle abonne*************\nEntrer le nom de l'abonne : ") ;
        nomdAbonne = s.nextLine() ;

        System.out.print("Entrer le prenom(s) de l'abonne : ") ;
        prenomsdAbonne = ss.nextLine() ;

        System.out.print("Entrer le numero de telephone de l'abonne : ") ;
        telephonedAbonne = sc.nextInt() ;

        System.out.print("Entrer le nom de banque de l'abonne : ") ;
        nomdBanque = scsc.nextLine() ;

        System.out.print("Entrer le numero de compte de l'abonne : ") ;
        numerodCompteAbonne = sca.nextInt() ;

        System.out.print("Entrer le solde de l'abonne : ") ;
        soldedAbonne = scasca.nextDouble() ;

        System.out.println("\n\t\t\t\t\t\t\t\t\t\t\t\t\t ______________________\n\t\t\t\t\t\t\t\t\t\t\t\t\t|Calibre Compteur SONEB|\n\t\t\t\t\t\t\t\t\t\t\t\t\t|----------------------|\n\t\t\t\t\t\t\t\t\t\t\t\t\t|15|20|30|40|60|80|100 |\n\t\t\t\t\t\t\t\t\t\t\t\t\t|______________________| \n\n") ;
        System.out.print("Entrer le calibre du compteur de l'abonne : ") ;
        calibredCompteur = scan.nextInt() ;

        System.out.print("Entrer le numero de compteur de l'abonne : ") ;
        numerodCompteur = scanscan.nextInt() ;

        System.out.print("Entrer le nom du proprietaire de l'abonne : ") ;
        nomdOwner = scann.nextLine() ;

        System.out.print("Entrer le(s) prenom(s) du proprietaire de l'abonne : ") ;
        prenomsdOwner = scannscann.nextLine() ;

        System.out.print("Entrer le numero de telephone du proprietaire de l'abonne : ") ;
        telephonedOwner = scanne.nextInt() ;

        System.out.print("Entrer la ville de l'abonne : ") ;
        villed = scannescanne.nextLine() ;

        System.out.print("Entrer le quartier de l'abonne : ") ;
        quartierd = scanner.nextLine() ;

        System.out.print("Entrer la maison de l'abonne : ") ;
        maisond = scannerscanner.nextLine() ;

        System.out.print("Entrer le numero de carre de l'abonne : ") ;
        numerodCarre = scanner.nextInt() ;

        System.out.println("\n\t\t\t\t\t--------------------------------------------------------\n\t\t\t\t\t|                      Region CODE                     |\n\t\t\t\t\t|------------------------------------------------------|\n\t\t\t\t\t|Region 1: A | Region 4: D | Region 7: G | Region 10: J|\n\t\t\t\t\t|Region 2: B | Region 5: E | Region 8: H | Region 11: K|\n\t\t\t\t\t|Region 3: C | Region 6: F | Region 9: I | Region 12: L|\n\t\t\t\t\t-------------------------------------------------------\n\n") ;
        System.out.print("Entrer le code region de l'abonne (ce code est une lettre majuscule entre A et L): ") ;
         codedRegion = sss.nextLine() ;

        System.out.println("\n\t\t\t\t\t------------------------------------------------------------------------\n\t\t\t\t\t|                           Zone CODE ZONE                             |\n\t\t\t\t\t|----------------------------------------------------------------------|\n\t\t\t\t\t|Zone 1: 001 | Zone: 5 005 | Zone 9: 009  | Zone 13: 013 | Zone 17: 017|\n\t\t\t\t\t|Zone 2: 002 | Zone: 6 006 | Zone 10: 010 | Zone 14: 014 | Zone 18: 018|\n\t\t\t\t\t|Zone 3: 003 | Zone: 7 007 | Zone 11: 011 | Zone 15: 015 | Zone 19: 019|\n\t\t\t\t\t|Zone 4: 004 | Zone: 8 008 | Zone 12: 012 | Zone 16: 016 | Zone 20: 020|\n\t\t\t\t\t------------------------------------------------------------------------\n\n") ;
        System.out.print("Entrer le code zone de l'abonne : ") ;
        codedZone = ssss.nextLine() ;

        System.out.println("\n\t\t\t\t\t\t\t\t\t\t -----------------------------\n\t\t\t\t\t\t\t\t\t\t|Types Abonnes Codes Associe|\n\t\t\t\t\t\t\t\t\t\t|---------------------------|\n\t\t\t\t\t\t\t\t\t\t|Abonnes Standard: A1       |\n\t\t\t\t\t\t\t\t\t\t|Abonnes employes: A2       |\n\t\t\t\t\t\t\t\t\t\t|Abonnes EASY: A3           |\n\t\t\t\t\t\t\t\t\t\t|Abonnes Entreprises: A4    |\n\t\t\t\t\t\t\t\t\t\t-----------------------------\n\n") ;
        System.out.print("Entrer le type de l'abonne : ") ;
        typedAbonne = ssss.nextLine() ;

        //instenciation de l'abonné
        Subscriber abonne = new  Subscriber( nomdAbonne , prenomsdAbonne, telephonedAbonne,  nomdBanque,  numerodCompteAbonne,  soldedAbonne, calibredCompteur, numerodCompteur, nomdOwner, prenomsdOwner, telephonedOwner, villed, quartierd, maisond, numerodCarre, codedRegion, codedZone, typedAbonne) ;
        dataBase.add(abonne) ;
        for(int i = 0 ; i < dataBase.size(); i++ ){
            System.out.println("\n\n**********************Iformation abonne**********************\n\nCode Region :\t" + dataBase.get(i).getCodeRegion() + "\ncode Zone:\t" + dataBase.get(i).getCodeZone() + "\nnumero Abonne:\t" + dataBase.get(i).getNumeroAbonne() + "\nnom Abonne:\t" + dataBase.get(i).getNomAbonne() + "\nprenoms Abonne:\t" + dataBase.get(i).getPrenomsAbonne() + "\ntelephone Abonne:\t" + dataBase.get(i).getTelephoneAbonne() + "\nnom Banque:\t" + dataBase.get(i).getNomBanque() + "\nnumero Compte Abonne:\t" + dataBase.get(i).getNumeroCompteAbonne() + "\nsolde Abonne:\t" + dataBase.get(i).getSoldeAbonne() + "\ncalibre Compteur:\t" + dataBase.get(i).getCalibreCompteur() + "\nnumero Compteur:\t" + dataBase.get(i).getNumeroCompteur() + "\nnumero Police:\t" + dataBase.get(i).getNumeroPolice() + "\nnom Owner:\t" + dataBase.get(i).getNomOwner() + "\nprenoms Owner:\t" + dataBase.get(i).getPrenomsOwner() + "\ntelephone Owner:\t" + dataBase.get(i).getTelephoneOwner() + "\nville:\t" + dataBase.get(i).getVille() + "\nquartier:\t" + dataBase.get(i).getQuartier() + "\nmaison:\t" + dataBase.get(i).getMaison() + "\nnumero Carre:\t" + dataBase.get(i).getNumeroCarre() + "\ntype Abonne:\t"+dataBase.get(i).getTypeAbonne() + "\n");
            
        }


    }



//Méthode pour modifier informations abonnées
    public static void modifyData(){

        System.out.println("**********************Modification Informations Abonne**********************\n\n\tEntrer le numero de Police (NPA) de l'abonne:\n");
        String valid ;
        int infoInt  ;
        Double infoDouble ;
        String infoString ;
        Scanner scanfff = new Scanner(System.in) ;
        valid = scanfff.nextLine() ;
        for(int i = 0 ; i < dataBase.size(); i++ ){
            if ( valid.equals(dataBase.get(i).getNumeroPolice()) ){
                //System.out.println("C'est ok" + dataBase.get(i).getNumeroPolice()) ;
                //Affichage informations abonne avant modification
                boolean  continuation= true;
                while(continuation){
                    System.out.println("\n**********************Informations Abonne**********************\n\nCode Region :\t" + dataBase.get(i).getCodeRegion() + "\ncode Zone:\t" + dataBase.get(i).getCodeZone() + "\nnumero Abonne:\t" + dataBase.get(i).getNumeroAbonne() + "\nnom Abonne:\t" + dataBase.get(i).getNomAbonne() + "\nprenoms Abonne:\t" + dataBase.get(i).getPrenomsAbonne() + "\ntelephone Abonne:\t" + dataBase.get(i).getTelephoneAbonne() + "\nnom Banque:\t" + dataBase.get(i).getNomBanque() + "\nnumero Compte Abonne:\t" + dataBase.get(i).getNumeroCompteAbonne() + "\nsolde Abonne:\t" + dataBase.get(i).getSoldeAbonne() + "\ncalibre Compteur:\t" + dataBase.get(i).getCalibreCompteur() + "\nnumero Compteur:\t" + dataBase.get(i).getNumeroCompteur() + "\nnumero Police:\t" + dataBase.get(i).getNumeroPolice() + "\nnom Owner:\t" + dataBase.get(i).getNomOwner() + "\nprenoms Owner:\t" + dataBase.get(i).getPrenomsOwner() + "\ntelephone Owner:\t" + dataBase.get(i).getTelephoneOwner() + "\nville:\t" + dataBase.get(i).getVille() + "\nquartier:\t" + dataBase.get(i).getQuartier() + "\nmaison:\t" + dataBase.get(i).getMaison() + "\nnumero Carre:\t" + dataBase.get(i).getNumeroCarre() + "\ntype Abonne:\t"+dataBase.get(i).getTypeAbonne() + "\n");
                    System.out.println("\n**********************Guide de modification**********************\n\n>CodeRegion :       -->Tapez 1\n>CodeZone:            -->Tapez 2\n>NumeroAbonne:       -->Tapez 3\n>NomAbonne:           -->Tapez 4\n>PrenomsAbonne:      -->Tapez 5\n>TelephoneAbonne:     -->Tapez 6\n>NomBanque:          -->Tapez 7\n>NumeroCompteAbonne:  -->Tapez 8\n>SoldeAbonne:        -->Tapez 9\n>CalibreCompteur:     -->Tapez 10\n>NumeroCompteur:     -->Tapez 11\n>NumeroPolice:        -->Tapez 12\n>NomOwner:           -->Tapez 13\n>PrenomsOwner:        -->Tapez 14\n>TelephoneOwner:     -->Tapez 15\n>Ville:               -->Tapez 16\n>Quartier:           -->Tapez 17\n>Maison:              -->Tapez 18\n>NumeroCarre:        -->Tapez 19\n>Type Abonne:         -->Tapez 20\n>Retour au menu principal-->Tapez 21\n\n") ;
                    valid = scanfff.nextLine() ;
                    
                    //Modifcation prorement dite des données
                    if (valid.equalsIgnoreCase("1")){
                        System.out.println("\n\t\t\t\t\t--------------------------------------------------------\n\t\t\t\t\t|                      Region CODE                     |\n\t\t\t\t\t|------------------------------------------------------|\n\t\t\t\t\t|Region 1: A | Region 4: D | Region 7: G | Region 10: J|\n\t\t\t\t\t|Region 2: B | Region 5: E | Region 8: H | Region 11: K|\n\t\t\t\t\t|Region 3: C | Region 6: F | Region 9: I | Region 12: L|\n\t\t\t\t\t-------------------------------------------------------\n\n") ;
                        System.out.print("Entrer la nouvelle donnee");
                        infoString = scanfff.nextLine() ;
                         dataBase.get(i).setCodeRegion(infoString);
                    }else if (valid.equalsIgnoreCase("2")){
                        System.out.println("\n\t\t\t\t\t------------------------------------------------------------------------\n\t\t\t\t\t|                           Zone CODE ZONE                             |\n\t\t\t\t\t|----------------------------------------------------------------------|\n\t\t\t\t\t|Zone 1: 001 | Zone: 5 005 | Zone 9: 009  | Zone 13: 013 | Zone 17: 017|\n\t\t\t\t\t|Zone 2: 002 | Zone: 6 006 | Zone 10: 010 | Zone 14: 014 | Zone 18: 018|\n\t\t\t\t\t|Zone 3: 003 | Zone: 7 007 | Zone 11: 011 | Zone 15: 015 | Zone 19: 019|\n\t\t\t\t\t|Zone 4: 004 | Zone: 8 008 | Zone 12: 012 | Zone 16: 016 | Zone 20: 020|\n\t\t\t\t\t------------------------------------------------------------------------\n\n") ;
                        System.out.print("Entrer la nouvelle donnee");
                        infoString = scanfff.nextLine() ;
                         dataBase.get(i).setCodeZone(infoString);
                    }else if (valid.equalsIgnoreCase("3")){
                        System.out.print("Entrer la nouvelle donnee");
                        infoString = scanfff.nextLine() ;
                         dataBase.get(i).setNumeroAbonne(infoString);
                    }else if (valid.equalsIgnoreCase("4")){
                        System.out.print("Entrer la nouvelle donnee");
                        infoString = scanfff.nextLine() ;
                         dataBase.get(i).setNomAbonne(infoString);
                    }else if (valid.equalsIgnoreCase("5")){
                        System.out.print("Entrer la nouvelle donnee");
                        infoString = scanfff.nextLine() ;
                         dataBase.get(i).setPrenomsAbonne(infoString);
                    }else if (valid.equalsIgnoreCase("6")){
                        System.out.print("Entrer la nouvelle donnee");
                        infoInt = scanfff.nextInt() ;
                         dataBase.get(i).setTelephoneAbonne(infoInt);
                    }else if (valid.equalsIgnoreCase("7")){
                        System.out.print("Entrer la nouvelle donnee");
                        infoString = scanfff.nextLine() ;
                         dataBase.get(i).setNomBanque(infoString);
                    }else if (valid.equalsIgnoreCase("8")){
                        System.out.print("Entrer la nouvelle donnee");
                        infoInt = scanfff.nextInt() ;
                         dataBase.get(i).setNumeroCompteAbonne(infoInt);
                    }else if (valid.equalsIgnoreCase("9")){
                        System.out.print("Entrer la nouvelle donnee");
                        infoDouble = scanfff.nextDouble() ;
                         dataBase.get(i).setSoldeAbonne(infoDouble);
                    }else if (valid.equalsIgnoreCase("10")){
                        System.out.println("\n\t\t\t\t\t\t\t\t\t\t\t\t\t______________________\n\t\t\t\t\t\t\t\t\t\t\t\t\t|Calibre Compteur SONEB|\n\t\t\t\t\t\t\t\t\t\t\t\t\t|----------------------|\n\t\t\t\t\t\t\t\t\t\t\t\t\t|15|20|30|40|60|80|100 |\n\t\t\t\t\t\t\t\t\t\t\t\t\t|______________________| \n\n") ;
                        System.out.print("Entrer la nouvelle donnee");
                        infoInt = scanfff.nextInt() ;
                         dataBase.get(i).setCalibreCompteur(infoInt);
                    }else if (valid.equalsIgnoreCase("11")){
                        System.out.print("Entrer la nouvelle donnee");
                        infoInt = scanfff.nextInt() ;
                         dataBase.get(i).setNumeroCompteur(infoInt);
                    }else if (valid.equalsIgnoreCase("12")){
                        System.out.print("Entrer la nouvelle donnee");
                        infoString = scanfff.nextLine() ;
                         dataBase.get(i).setNumeroPolice(infoString);
                    }else if (valid.equalsIgnoreCase("13")){
                        System.out.print("Entrer la nouvelle donnee");
                        infoString = scanfff.nextLine() ;
                         dataBase.get(i).setNomOwner(infoString);
                    }else if (valid.equalsIgnoreCase("14")){
                        System.out.print("Entrer la nouvelle donnee");
                        infoString = scanfff.nextLine() ;
                         dataBase.get(i).setPrenomsOwner(infoString);
                    }else if (valid.equalsIgnoreCase("15")){
                        System.out.print("Entrer la nouvelle donnee");
                        infoInt = scanfff.nextInt() ;
                         dataBase.get(i).setTelephoneOwner(infoInt);
                    }else if (valid.equalsIgnoreCase("16")){
                        System.out.print("Entrer la nouvelle donnee");
                        infoString = scanfff.nextLine() ;
                         dataBase.get(i).setVille(infoString);
                    }else if (valid.equalsIgnoreCase("17")){
                        System.out.print("Entrer la nouvelle donnee");
                        infoString = scanfff.nextLine() ;
                         dataBase.get(i).setQuartier(infoString);
                    }else if (valid.equalsIgnoreCase("18")){
                        System.out.print("Entrer la nouvelle donnee");
                        infoString = scanfff.nextLine() ;
                         dataBase.get(i).setMaison(infoString);
                    }else if (valid.equalsIgnoreCase("19")){
                        System.out.print("Entrer la nouvelle donnee");
                        infoInt = scanfff.nextInt() ;
                         dataBase.get(i).setNumeroCarre(infoInt);
                    }else if (valid.equalsIgnoreCase("20")){
                        System.out.println("\n\t\t\t\t\t\t\t\t\t\t-----------------------------\n\t\t\t\t\t\t\t\t\t\t|Types Abonnes Codes Associe|\n\t\t\t\t\t\t\t\t\t\t|---------------------------|\n\t\t\t\t\t\t\t\t\t\t|Abonnes Standard: A1       |\n\t\t\t\t\t\t\t\t\t\t|Abonnes employes: A2       |\n\t\t\t\t\t\t\t\t\t\t|Abonnes EASY: A3           |\n\t\t\t\t\t\t\t\t\t\t|Abonnes Entreprises: A4    |\n\t\t\t\t\t\t\t\t\t\t-----------------------------\n\n") ;
                        System.out.print("Entrer la nouvelle donnee");
                        infoString = scanfff.nextLine() ;
                        dataBase.get(i).setTypeAbonne(infoString) ;
                    }else if (valid.equalsIgnoreCase("21")){
                        continuation = false ;
                    }
                    
                    
                }



            }
        }
    
    
    }
    
// Méthode pour instruction relever a effecyuer    
    public static void callRelever(){

        System.out.println("**********************Faire un relever**********************\n\n\tEntrer le numero de Police (NPA) de l'abonne:\n");
        String entrer ;
                
        Scanner sccanfff = new Scanner(System.in) ;
        entrer = sccanfff.nextLine() ;
        for(Subscriber element : dataBase){
            if ( entrer.equals(element.getNumeroPolice()) ){
                element.getListeReleve().add(element.effectueRelever());
            }

        }
    }

// Méthode pour veririfier statut d'une facture
    public static void verifyStatus(Facture facture){
        System.out.println ("Numero de facture :" + facture.getNumeroFacture() + "\nMontant de la facture :\t" +facture.getMontant() + "\nDate limite :\t" + facture.getDeadline() + "\nStatut :\t" + facture.getStatut() + "\n\n") ;
    }

//Méthode pour veririfier le solde d'un abonné et le nombre de facture en intence de paiement
    public static void verifySoldFact(String numPolAbon){
       if (dataBase.size() < 1){
           System.out.println("Cette acttion n'est pas possible car aucun abonne n'est encore creer") ;
           Menu() ;
       }else{
           int cpt = 0 ;
           for(Facture elment : dataBill){
               if(numPolAbon.equals(elment.getAbonne().getNumeroPolice())){
                   cpt++ ;
               }
           }
           for (Subscriber element : dataBase){
               if(numPolAbon.equals(element.getNumeroPolice())){
                   System.out.println("\n\nSolde actuel :\t" + element.getSoldeAbonne() + "\nNombre de facture en instence de paiement:\t" + cpt + "\n\n") ;
               }
           }
       }
    }

//méthode pour le menu
    public static void Menu(){
        afficheMenu();
        try{
            choixMenu();
        }catch(InputMismatchException e){
            System.out.println("\nEntrer des informations conformes a la realite!\n\n") ;
            Menu() ;
        }catch(Exception e){
            System.out.println("\nUne erreure c'est produite dans le programme !\n\n") ;
            Menu() ;
        }
        
    }



}



/*
Quelle information voulez vous modifiez:\n\n\n


">CodeRegion :       -->Tapez 1\n>CodeZone:            -->Tapez 2\n>NumeroAbonne:       -->Tapez 3\n>NomAbonne:           -->Tapez 4\n>PrenomsAbonne:      -->Tapez 5\n>TelephoneAbonne:     -->Tapez 6\n>NomBanque:          -->Tapez 7\n>NumeroCompteAbonne:  -->Tapez 8\n>SoldeAbonne:        -->Tapez 9\n>CalibreCompteur:     -->Tapez 10\n>NumeroCompteur:     -->Tapez 11\n>NumeroPolice:        -->Tapez 12\n>NomOwner:           -->Tapez 13\n>PrenomsOwner:        -->Tapez 14\n>TelephoneOwner:     -->Tapez 15\n>Ville:               -->Tapez 16\n>Quartier:           -->Tapez 17\n>Maison:              -->Tapez 18\n>NumeroCarre:        -->Tapez 19\n>Type Abonne:         -->Tapez 20\n>Retour au menu principal-->Tapez 21\n\n"

*/






/*
                 "CodeRegion :\t" + dataBase.get(i).getCodeRegion() + "\ncodeZone:\t" + dataBase.get(i).getCodeZone() + "\nnumeroAbonne:\t" + dataBase.get(i).getNumeroAbonne() + "\nnomAbonne:\t" + dataBase.get(i).getNomAbonne() + "\nprenomsAbonne:\t" + dataBase.get(i).getPrenomsAbonne() + "\ntelephoneAbonne:\t" + dataBase.get(i).getTelephoneAbonne() + "\nnomBanque:\t" + dataBase.get(i).getNomBanque() + "\nnumeroCompteAbonne:\t" + dataBase.get(i).getNumeroCompteAbonne() + "\nsoldeAbonne:\t" + dataBase.get(i).getSoldeAbonne() + "\ncalibreCompteur:\t" + dataBase.get(i).getCalibreCompteur() + "\nnumeroCompteur:\t" + dataBase.get(i).getNumeroCompteur() + "\nnumeroPolice:\t" + dataBase.get(i).getNumeroPolice() + "\nnomOwner:\t" + dataBase.get(i).getNomOwner() + "\nprenomsOwner:\t" + dataBase.get(i).getPrenomsOwner() + "\ntelephoneOwner:\t" + dataBase.get(i).getTelephoneOwner() + "\nville:\t" + dataBase.get(i).getVille() + "\nquartier:\t" + dataBase.get(i).getQuartier() + "\nmaison:\t" + dataBase.get(i).getMaison() + "\nnumeroCarre:\t" + dataBase.get(i).getNumeroCarre() + "\n"
                 */


/*
System.out.print("********************************************************************************************************\n********************************#SOCIETE NATIONALE DES EAUX DU BANGOSSE#********************************\n*****************************************#SOCIETE ANONIME#**********************************************\n******************************#04 BP: 905 BANGOSSE CITY, TEL: 80-11-66-44#******************************\n********************************************************************************************************\n\n\n\t\tNUMERO DE FACTURE:\t" + this.numeroFacture +"\n\t\tDATE DE FACTURE:\t"     + this.dateEmission +"\n\t\tMOIS:\t"   + this.dateEmission +"\n\t\tPERIODE DE CONSOMMATION:\t Du "    + this.abonne.getListeReleve().get(this.abonne.getListeReleve().size() - 2).getDate() + " au " this.abonne.getListeReleve().get(this.abonne.getListeReleve().size() - 1).getDate() "\n\t\tDATE LIMITE DE PAIEMENT:\t"     + this.deadline +"\n\n________________________\nINFORMATIONS DE L'ABONNE:\n------------------------------------------------------------------------------------------------------\nNumero Abonne:\t     + this.abonne.getNumeroAbonne() +"\nNom et Prenoms:\t    + this.abonne.getNomAbonne() + ""+ this.abonne.getPrenomsAbonne() +"\nTelephone:\t    + this.abonne.getTelephoneAbonne() +"\n------------------------------------------------------------------------------------------------------\n\n__________________________\nINFORMATION DE BRANCHEMENT:\n------------------------------------------------------------------------------------------------------\nNumero Police:\t    + this.abonne.getNumeroPolice +"\nNom et Prenoms Proprietaire:\t   + this.abonne.getNomOwner() + "" + this.abonne.getPrenomsOwner() +"\nAdresse:\t    +this.abonne.getVille() + ", " + this.abonne.getQuartier()+ ", c/ "+ this.abonne.getNumeroCarre() +"\nNumero Compteur:\t    +this.abonne.getNumeroCompteur() +"\nCalibre:\t    + this.abonne.getNumeroCalibre() +"\n------------------------------------------------------------------------------------------------------\n\n__________________________\nINFORMATION DE FACTURATION:\n------------------------------------------------------------------------------------------------------\nType Abonne:    + this.abonne.getTypeAbonne() +"\nINDEX:\n\tAncien:\t   + this.abonne.getListeReleve().get(this.abonne.getListeReleve().size() - 2).getIndexReleve() +"\n\tNouveau:\t   + this.abonne.getListeReleve().get(this.abonne.getListeReleve().size() - 1).getIndexReleve() +"\nNet a Payer:\t   + this.montant +"\n") ;

"CONSOMMATION:\n\tTotale:\t "+ this.consommation
    "\n\tTranche 1:\t   + this.consommation +"\n\tTranche 2:\t   + this.consommation +"\n\tTranche 3:\t    + this.consommation +"\n\n"

MONTANT:\t    + calcPrixTranche(this.abonne) +"\n
LOCATION:\t   +........+"\n
TOTAL HT:\t   +........+"\n
TVA:\t     +........+"\n
STATUT:\t   +this.statut +"\n
TOTAL DU:\t    + this.montant +"\n
------------------------------------------------------------------------------------------------------\n\n\n


******************************#Votre Satisfaction Notre Raison d'existee#******************************\n\n\n



*/





/*
System.out.print("

********************************************************************************************************\n
********************************#SOCIETE NATIONALE DES EAUX DU BANGOSSE#********************************\n
*****************************************#SOCIETE ANONIME#**********************************************\n
******************************#04 BP: 905 BANGOSSE CITY, TEL: 80-11-66-44#******************************\n
********************************************************************************************************\n\n\n


        \t\tNUMERO DE FACTURE:\t" + this.numeroFacture +"\n
        \t\tDATE DE FACTURE:\t"     + this.dateEmission +"\n
        \t\tMOIS:\t"   + this.dateEmission +"\n
        \t\tPERIODE DE CONSOMMATION:\t Du "    + this.abonne.getListeReleve().get(this.abonne.getListeReleve().size() - 2).getDate() + " au " this.abonne.getListeReleve().get(this.abonne.getListeReleve().size() - 1).getDate() "\n
        \t\tDATE LIMITE DE PAIEMENT:\t"     + this.deadline +"\n\n

________________________\n
INFORMATIONS DE L'ABONNE:\n
------------------------------------------------------------------------------------------------------\n
Numero Abonne:\t     + this.abonne.getNumeroAbonne() +"\n
Nom et Prenoms:\t    + this.abonne.getNomAbonne() + ""+ this.abonne.getPrenomsAbonne() +"\n
Telephone:\t    + this.abonne.getTelephoneAbonne() +"\n
------------------------------------------------------------------------------------------------------\n\n

__________________________\n
INFORMATION DE BRANCHEMENT:\n
------------------------------------------------------------------------------------------------------\n
Numero Police:\t    + this.abonne.getNumeroPolice +"\n
Nom et Prenoms Proprietaire:\t   + this.abonne.getNomOwner() + "" + this.abonne.getPrenomsOwner() +"\n
Adresse:\t    +this.abonne.getVille() + ", " + this.abonne.getQuartier()+ ", c/ "+ this.abonne.getNumeroCarre() +"\n
Numero Compteur:\t    +this.abonne.getNumeroCompteur() +"\n
Calibre:\t    + this.abonne.getNumeroCalibre() +"\n
------------------------------------------------------------------------------------------------------\n\n

__________________________\n
INFORMATION DE FACTURATION:\n
------------------------------------------------------------------------------------------------------\n
Type Abonne:    + this.abonne.getTypeAbonne() +"\n
INDEX:\n
    \tAncien:\t   + this.abonne.getListeReleve().get(this.abonne.getListeReleve().size() - 2).getIndexReleve() +"\n
    \tNouveau:\t   + this.abonne.getListeReleve().get(this.abonne.getListeReleve().size() - 1).getIndexReleve() +"\n
Net a Payer:\t   + this.montant +"\n

CONSOMMATION:\n
    \tTotale:\t     +........+"\n
    \tTranche 1:\t   +........+"\n
    \tTranche 2:\t   +........+"\n
    \tTranche 3:\t    +........+"\n\n

"MONTANT:\t "  + calcPrixTranche(this.abonne) +"\n"
"LOCATION:\t   +........+"\n"
"TOTAL HT:\t   +........+"\n"
"TVA:\t " +........+"\n"
"STATUT:\t" +this.statut +"\n"
"TOTAL DU:\t    + this.montant +"\n------------------------------------------------------------------------------------------------------\n\n\n******************************#Votre Satisfaction Notre Raison d'existee#******************************\n\n\n"



*/

/*
"\n\t\t\t\t\t--------------------------------------------------------\n\t\t\t\t\t|                      Region CODE                     |\n\t\t\t\t\t|------------------------------------------------------|\n\t\t\t\t\t|Region 1: A | Region 4: D | Region 7: G | Region 10: J|\n\t\t\t\t\t|Region 2: B | Region 5: E | Region 8: H | Region 11: K|\n\t\t\t\t\t|Region 3: C | Region 6: F | Region 9: I | Region 12: L|\n\t\t\t\t\t-------------------------------------------------------\n\n"




"\n\t\t\t\t\t------------------------------------------------------------------------\n\t\t\t\t\t|                           Zone CODE ZONE                             |\n\t\t\t\t\t|----------------------------------------------------------------------|\n\t\t\t\t\t|Zone 1: 001 | Zone: 5 005 | Zone 9: 009  | Zone 13: 013 | Zone 17: 017|\n\t\t\t\t\t|Zone 2: 002 | Zone: 6 006 | Zone 10: 010 | Zone 14: 014 | Zone 18: 018|\n\t\t\t\t\t|Zone 3: 003 | Zone: 7 007 | Zone 11: 011 | Zone 15: 015 | Zone 19: 019|\n\t\t\t\t\t|Zone 4: 004 | Zone: 8 008 | Zone 12: 012 | Zone 16: 016 | Zone 20: 020|\n\t\t\t\t\t------------------------------------------------------------------------\n\n"



"\n\t\t\t\t\t\t\t\t\t\t\t\t\t______________________\n\t\t\t\t\t\t\t\t\t\t\t\t\t|Calibre Compteur SONEB|\n\t\t\t\t\t\t\t\t\t\t\t\t\t|----------------------|\n\t\t\t\t\t\t\t\t\t\t\t\t\t|15|20|30|40|60|80|100 |\n\t\t\t\t\t\t\t\t\t\t\t\t\t|______________________| \n\n" 



"\n\t\t\t\t\t\t\t\t\t\t -----------------------------\n\t\t\t\t\t\t\t\t\t\t|Types Abonnes Codes Associe|\n\t\t\t\t\t\t\t\t\t\t|---------------------------|\n\t\t\t\t\t\t\t\t\t\t|Abonnes Standard: A1       |\n\t\t\t\t\t\t\t\t\t\t|Abonnes employés: A2       |\n\t\t\t\t\t\t\t\t\t\t|Abonnes EASY: A3           |\n\t\t\t\t\t\t\t\t\t\t|Abonnes Entreprises: A4    |\n\t\t\t\t\t\t\t\t\t\t-----------------------------\n\n"


*/