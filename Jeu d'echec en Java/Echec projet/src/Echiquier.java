import java.util.*;
import java.util.List;
import java.util.Scanner;

public class Echiquier implements ValeursEchiquier {
    private List<Case> cadriage = new ArrayList<Case> ();
    public VerifMouvement  Verifmouvement;

    public void initialiser() {
        Case c;
        for(int y = 0; y<8; y++){
            for(int x = 0; x<8; x++){
                if(y<2) {                //INIT NOIRS
                    if(x%2==0){            //INIT PAR
                        if(y%2==0)            //COULEUR DE CASE
                            c = new Case(x,y,NOIR,tabPieceN[y*8+x]);
                        else
                            c = new Case(x,y,BLANC,tabPieceN[y*8+x]);
                    }
                    else{
                        if(y%2==1)
                            c = new Case(x,y,BLANC,tabPieceN[y*8+x]);
                        else
                            c = new Case(x,y,NOIR,tabPieceN[y*8+x]);
                    }
                }

                else if(y>5) {            //INIT BLANCS
                    if(x%2==0){            //INIT PAR
                        if(y%2==0)            //COULEUR DE CASE
                            c = new Case(x,y,NOIR,tabPieceB[(y-6)*8+x]);
                        else
                            c = new Case(x,y,BLANC,tabPieceB[(y-6)*8+x]);
                    }
                    else{
                        if(y%2==1)
                            c = new Case(x,y,BLANC,tabPieceB[(y-6)*8+x]);
                        else
                            c = new Case(x,y,NOIR,tabPieceB[(y-6)*8+x]);
                    }
                }

                else {                    //INIT CASE VIDE
                    if(x%2==0){            //INIT PAR
                        if(y%2==0)            //COULEUR DE CASE
                            c = new Case(x,y,NOIR,null);
                        else
                            c = new Case(x,y,BLANC,null);
                    }
                    else{
                        if(y%2==1)
                            c = new Case(x,y,BLANC,null);
                        else
                            c = new Case(x,y,NOIR,null);
                    }
                }
                cadriage.add(c);
            }
        }
    }

    public Echiquier() {
        initialiser();
        this.Verifmouvement = new VerifMouvement();
    }
    // permet  l'affichage avec les bordure remplis
    public String toString() {
        String s = "";
        for(int x = 0; x<8; x++) {
            s = s + "\n|";
            for(int y = 0; y<9; y++) {
                if(y==0)
                    s = s+"_"+((-8+x)*-1)+ "_|";
                else if(cadriage.get(x*8+y-1).getPiece() != null)
                    s = s+"_"+cadriage.get(x*8+y-1).getPiece().getSymbole()+"_|";
                else
                    s = s+"___|";
            }
        }
        s = s + "\n|";
        for(int y = 0; y<9; y++)
            s = s+"_"+((char)(y+64))+"_|";
        return s;
    }

    public List<Case> getCadriage() {
        return this.cadriage;
    }



    public boolean testerMouvement(String tour, int x1, int y1, int x2, int y2) {
        if(this.Verifmouvement.testGeneral(tour, cadriage, x1, y1, x2, y2))
            return true;
        return false;
    }
    //lorsque un pion arrive a la fin rang� adverse, la promotion permet le remplacement d'une piece  par une autre
    // soit par une Reine ou une tour ou un cavalier

    public void Promotion(int x, int y, String tour, Scanner scan) {
        String promotionChoisie;
        boolean promoReussie = false;
        do {
            System.out.print("\nChoisissez la promotion (ex : Reine, Tour, Fou, Cavalier) : \n");
            promotionChoisie = scan.next();
            if(promotionChoisie.compareTo("Reine") == 0) {
                this.cadriage.get(y*8+x).setPiece(new Dame(tour));
                promoReussie = true;
            }
            else if(promotionChoisie.compareTo("Fou") == 0) {
                this.cadriage.get(y*8+x).setPiece(new Fou(tour));
                promoReussie = true;
            }

            else if(promotionChoisie.compareTo("Tour") == 0) {
                this.cadriage.get(y*8+x).setPiece(new Tour(tour));
                promoReussie = true;
            }

            else if(promotionChoisie.compareTo("Reine") == 0) {
                this.cadriage.get(y*8+x).setPiece(new Cavalier(tour));
                promoReussie = true;
            }
            else
                System.out.println("Entree incorrecte, reessayez");

        }while(!promoReussie);
    }
    // est un deplacement special du roi et d'une tour, elle permet de mettre a l'abris le roi tout en centralissant une tour
    //permmet au roi de se deplacer horizontalement sans qu'il est deja effectuer un mouvement dans la partie

    public boolean rock(String move, Joueur j) {
        int x1 = ((int)move.charAt(0))-65,
                y1 = ((int)move.charAt(1))*-1+56;

        if(y1 != j.getOrdonneCoord() || !(this.cadriage.get(y1*8+x1).getPiece() instanceof Tour))
            return false;
        if(x1 > j.getAbscisseCoord()) {
            for(int x = j.getAbscisseCoord() + 1; x<x1 ; x++) {
                if(this.cadriage.get(y1*8+x).getPiece() != null) {
                    System.out.println("\nROQUE IMPOSSIBLE");
                    return false;
                }
            }
            this.cadriage.get(j.getOrdonneCoord()*8+j.getAbscisseCoord()+2).setPiece(this.cadriage.get(j.getOrdonneCoord()*8+j.getAbscisseCoord()).getPiece());
            this.cadriage.get(j.getOrdonneCoord()*8+j.getAbscisseCoord()).supprimerPiece();

            this.cadriage.get(j.getOrdonneCoord()*8+j.getAbscisseCoord()+1).setPiece(this.cadriage.get(y1*8+x1).getPiece());
            this.cadriage.get(y1*8+x1).supprimerPiece();
        }

        else {
            for(int x = j.getAbscisseCoord() - 1; x>x1 ; x--) {
                if(this.cadriage.get(y1*8+x).getPiece() != null) {
                    System.out.println("\nROQUE IMPOSSIBLE");
                    return false;
                }
            }
            this.cadriage.get(j.getOrdonneCoord()*8+j.getAbscisseCoord()-2).setPiece(this.cadriage.get(j.getOrdonneCoord()*8+j.getAbscisseCoord()).getPiece());
            this.cadriage.get(j.getOrdonneCoord()*8+j.getAbscisseCoord()).supprimerPiece();

            this.cadriage.get(j.getOrdonneCoord()*8+j.getAbscisseCoord()-1).setPiece(this.cadriage.get(y1*8+x1).getPiece());
            this.cadriage.get(y1*8+x1).supprimerPiece();

        }
        return true;
    }

}