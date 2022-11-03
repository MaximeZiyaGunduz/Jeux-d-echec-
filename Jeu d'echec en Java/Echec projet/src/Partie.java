import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.*;
import java.util.Scanner;

public class Partie implements ValeursEchiquier {
    private String difficulte;
    private String tour;
    private boolean nomDefini;
    private Echiquier plateau;
    private PartieMat partieMat;
    private Joueur j1;
    private Joueur j2;
    private Afficheur afficheur;
    private Scanner scan;
    private int compteTour;

    public void initPartie() {
        this.j1 = new Joueur(BLANC, 4, 7);
        this.j2 = new Joueur(NOIR, 4, 0);
        this.plateau = new Echiquier();
        this.partieMat = new PartieMat();
        this.afficheur = new Afficheur();
        this.tour = BLANC;
        this.nomDefini = false;
        this.compteTour=0;
    }

    public boolean mouvement(String move) {
        //CONVERTION COORDONNE EN VARIABLE UTILISABLE PAR LA MACHINE//
        int x1 = ((int)move.charAt(0))-65,
                y1 = ((int)move.charAt(1))*-1+56,
                x2 = ((int)move.charAt(3))-65,
                y2 = ((int)move.charAt(4))*-1+56;

        //TEST COORDONNES CORRECTS//
        if(x1 < 0 || x1 >7 || x2 < 0 || x2 >7 || y1 < 0 || y1 >7 || y2 < 0 || y2 >7) {
            System.out.println("Coordonnees incorrects");
            return false;
        }


        //TEST CASE VIDE//
        try {
            System.out.println("Pion � deplace : "+ this.plateau.getCadriage().get(y1*8+x1).getPiece().getSymbole());
        } catch(Exception E) {
            System.out.println("La case "+move.charAt(0)+""+move.charAt(1)+" est vide");
            return false;
        }

        if(plateau.getCadriage().get(y1*8+x1).getPiece().getCouleur() != tour) {
            System.out.println("Pion de mauvaise couleur, tour des "+tour);
            return false;
        }
        //TEST MOUVEMENT POSSIBLE//
        if(!this.plateau.testerMouvement(tour, x1, y1, x2, y2))
            return false;

        //TEST LEGALITE PAT/MAT DU MOUVEMENT//
        if(plateau.getCadriage().get(y1*8+x1).getPiece() instanceof Roi) {
            if(this.partieMat.testMat(plateau, x2, y2, tour)) {
                System.out.println("\nVous etes en Mat ou le movement souhaite mettrait en danger votre roi");
                return false;
            }
        }
        else {
            Piece testTemp = plateau.getCadriage().get(y2*8+x2).getPiece();
            plateau.getCadriage().get(y2*8+x2).setPiece(plateau.getCadriage().get(y1*8+x1).getPiece());
            plateau.getCadriage().get(y1*8+x1).supprimerPiece();
            if(this.getJoueurActuel() == j1) {
                if(this.partieMat.testMat(plateau, j1.getAbscisseCoord(),
                        j1.getOrdonneCoord(), tour)) {
                    plateau.getCadriage().get(y1*8+x1).setPiece(plateau.getCadriage().get(y2*8+x2).getPiece());
                    plateau.getCadriage().get(y2*8+x2).setPiece(testTemp);

                    System.out.println("\nVous etes en Mat ou le movement souhaite mettrait en danger votre roi");
                    return false;
                }
            }


            else
            if(this.partieMat.testMat(plateau, j2.getAbscisseCoord(),
                    j2.getOrdonneCoord(), tour)) {
                plateau.getCadriage().get(y1*8+x1).setPiece(plateau.getCadriage().get(y2*8+x2).getPiece());
                plateau.getCadriage().get(y2*8+x2).setPiece(testTemp);
                System.out.println("\nVous etes en Mat ou le movement souhaite mettrait en danger votre roi");
                return false;
            }

            plateau.getCadriage().get(y1*8+x1).setPiece(plateau.getCadriage().get(y2*8+x2).getPiece());
            plateau.getCadriage().get(y2*8+x2).setPiece(testTemp);
        }



        //AFFICHAGE PIECE DETRUITE//
        if( plateau.getCadriage().get(y2*8+x2).getPiece() != null)
            System.out.println("Piece detruite : "+ plateau.getCadriage().get(y2*8+x2).getPiece().getSymbole());


        //SI MOUVEMENT ROI, MODIFICATION COORDONNE ROI POUR JOUEUR//
        if( plateau.getCadriage().get(y1*8+x1).getPiece() instanceof Roi) {
            if(this.getJoueurActuel() == j1) {
                this.j1.setAbscisseCoord(x2);
                this.j1.setOrdonneCoord(x2);
                this.j1.setPositioniInitial(false);
            }

            else{
                this.j2.setAbscisseCoord(x2);
                this.j2.setOrdonneCoord(x2);
                this.j2.setPositioniInitial(false);
            }
        }

        //DEPLACEMENT DE LA PIECE A BOUGER//
        plateau.getCadriage().get(y2*8+x2).setPiece(plateau.getCadriage().get(y1*8+x1).getPiece());
        plateau.getCadriage().get(y1*8+x1).supprimerPiece();
        ///////////////////////////////////

        //TEST PROMOTION//
        if(plateau.getCadriage().get(y2*8+x2).getPiece() instanceof Pion && (y2 == 0 || y2 == 7))
            this.plateau.Promotion(x2, y2, tour, scan);
        return true;
    }

    public Partie() {
        initPartie();                                 //INIT PARTIR
        int choixMenu = 0;

        this.scan = new Scanner(System.in);
        while(choixMenu != 1) {                        //MENU GESTION AVANT LA PARTIE
            afficheur.affichageMenu();
            choixMenu = scan.nextInt();
            if(choixMenu == 2)                        //CHOIX POUR DEFINIR LES NOM DES JOUEURS
                definirNom();
            else if(choixMenu == 3){
                this.afficheur.affichageCommandsEtCoupsSpeciaux();
            }
            else if(choixMenu == 4) {                //CHOIX POUR QUITTER
                System.out.println("Fin du jeu");

            }
            else if(choixMenu == 5){
                sauvegarder(this.partieMat);
            }
            else if(choixMenu != 1)                    //CHOIX IMPOSSIBLES
                System.out.println("Choix errone");
        }

        //DEBUT DU JEU//
        int x;
        int y;
        do {
            if(!jouerTour())
                return;        //LE JOUEUR JOUE SON TOUR
            this.changerTour();        //LE TOUR CHANGE

            if(this.getJoueurActuel() == j2) {                    //VARIABLES NECESSAIRE POUR LES TESTS PAT MAT
                x = j2.getAbscisseCoord();
                y = j2.getOrdonneCoord();
            }

            else {
                x = j1.getAbscisseCoord();
                y = j1.getOrdonneCoord();
            }

            if(this.partieMat.testMat(plateau, this.getJoueurActuel().getAbscisseCoord(), this.getJoueurActuel().getOrdonneCoord(), tour))
                System.out.println("\nMAT !!");

        } while(!(this.partieMat.testCheckMate(this.plateau, x, y, tour) || this.partieMat.testPat(this.plateau, x, y, tour))) ;

        //DETERMINATION DU VAINQUEUR OU EGALITE//
        System.out.println(this.plateau);
        this.changerTour();
        if(this.partieMat.testPat(this.plateau, x, y, tour))
            this.afficheur.affichageEgalite();
        else
            this.afficheur.affichageVictoire(tour);
        scan.close();
    }

    public void definirNom() {
        String nom;
        System.out.print("Joueur 1 : ");
        nom = scan.next();
        this.j1.setNom(nom);
        System.out.print("Joueur 2 : ");
        nom = scan.next();
        this.j2.setNom(nom);
        this.nomDefini = true;
    }

    public void changerTour() {
        if(tour == BLANC)
            tour = NOIR;
        else
            tour = BLANC;
    }

    public boolean jouerTour() {
        this.afficheur.affichagePartie(nomDefini, tour, j1, j2, plateau);
        String move;
        boolean moveEffectue = false;
        scan = new Scanner(System.in);
        do {
            move = scan.nextLine();
            if(move.compareTo("abandonner") == 0) {
                System.out.println("Le joueur "+this.tour+" � abandonne");
                return false;
            }

            if(move.length() == 8) {
                if(    move.charAt(0) == 'R' &&
                        move.charAt(1) == 'O' &&
                        move.charAt(2) == 'Q' &&
                        move.charAt(3) == 'U' &&
                        move.charAt(4) == 'E' &&
                        this.getJoueurActuel().getPositionInitial()) {
                    moveEffectue=this.plateau.rock(move.charAt(6) +""+ move.charAt(7), this.getJoueurActuel());
                }
            }
            else if(move.length() == 5)
                moveEffectue = this.mouvement(move);
            if(!moveEffectue)
                System.out.print("Mouvement impossible\n\nReessayez : ");
        } while(!moveEffectue);
        return true;
    }

    public Joueur getJoueurActuel() {
        if(this.tour == NOIR)
            return this.j2;
        return this.j1;
    }
    public int compterNombreTour() {
        if(tour==BLANC || tour==NOIR) {
            compteTour ++;

        }
        return compteTour;
    }
    public void sauvegarder(Object partie) {

        try {
            File save = new File("sauvegarde.txt");  //creation d'un fichier sauvegarde
            if (!save.exists())       // verification
                save.createNewFile();

            FileOutputStream fileOut = new FileOutputStream("save.txt");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(partie);
            fileOut.close();
            objectOut.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
