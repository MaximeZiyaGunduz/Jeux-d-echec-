import java.util.List;
//verifie bien que les corde y1 et X1 de depart et les cordonnee y2 et x2 d'arriv� sont bien corrrect
public class VerifMouvement implements ValeursEchiquier {
    public boolean testCoord(String tour, List<Case> cadriage, Piece pieceTeste, int x1, int y1, int x2, int y2) {
        if(     x1 < 0 || x1 > 7 ||
                x2 < 0 || x2 > 7 ||
                y1 < 0|| y1 > 7 ||
                y2 < 0 || y2 > 7 ||
                (y1==y2 && x1==x2) ||
                pieceTeste == null) {
            return false;
        }
        try {
            if( cadriage.get(y1*8+x1).getPiece().getCouleur()==
                    cadriage.get(y2*8+x2).getPiece().getCouleur())
                return false;
        }

        catch(NullPointerException e) {

        }
        return true;
    }

    public VerifMouvement() {
    }

    public boolean testGeneral(String tour, List<Case> cadriage, int x1, int y1, int x2, int y2) {   // test generale de toute les pieces qui verifie qi le mouvement est correct

        //x1 et y1 = piece � bouger
        Piece pieceTeste = cadriage.get(y1*8+x1).getPiece();            //x2 et y2 = destination

        if(!testCoord(tour, cadriage, pieceTeste,x1,y1,x2,y2))
            return false;
        if(pieceTeste instanceof Cavalier)
            return this.testMouvementCavalier(x1, y1, x2, y2);
        if(pieceTeste instanceof Fou)
            return this.testMouvementFou(cadriage,x1, y1, x2, y2);
        if(pieceTeste instanceof Tour)
            return this.testMouvementTour(cadriage,x1, y1, x2, y2);
        if(pieceTeste instanceof Dame)
            return this.testMouvementDame(cadriage,x1, y1, x2, y2);
        if(pieceTeste instanceof Roi)
            return this.testMouvementRoi(cadriage,x1, y1, x2, y2);
        if(pieceTeste instanceof Pion)
            return this.testMouvementPion(cadriage,x1, y1, x2, y2);
        return true;
    }

    public boolean testMouvementCavalier(int x1, int y1, int x2, int y2) {
        if(x2 == x1+2 && (y2 == y1+1 || y2 == y1-1))
            return true;
        else if(x2 == x1-2 && (y2 == y1+1 || y2 == y1-1))
            return true;
        else if(y2 == y1+2 && (x2 == x1+1 || x2 == x1-1))
            return true;
        else if(y2 == y1-2 && (x2 == x1+1 || x2 == x1-1))
            return true;
        return false;
    }
    /*methode testMouvementFou sert a v�rifier la conformit� du deplace d'un fou
     * retourne true ou false si son deplacement est correct, le mouvement du fou est diagonale
     *
     *
     */

    public boolean testMouvementFou(List<Case> cadriage, int x1, int y1, int x2, int y2) {
        if(x2>x1 && y2>y1)
            for(int y = y1+1, x = x1+1; y<8 && x<8; y++, x++)
                if(x == x2 && y == y2)
                    return true;
                else if(cadriage.get(y*8+x).getPiece()!=null)
                    return false;

        if(x2<x1 && y2>y1)
            for(int y = y1+1, x = x1-1; y<8 && x>-1; y++, x--){
                if(x == x2 && y == y2)
                    return true;
                else if(cadriage.get(y*8+x).getPiece()!=null)
                    return false;
            }
        if(x2<x1 && y2<y1)
            for(int y = y1-1, x = x1-1; y>-1 && x>-1; y--, x--){
                if(x == x2 && y == y2)
                    return true;
                else if(cadriage.get(y*8+x).getPiece()!=null)
                    return false;
            }

        if(x2>x1 && y2<y1)
            for(int y = y1-1, x = x1+1; y>-1 && x<8; y--, x++)
                if(x == x2 && y == y2)
                    return true;
                else if(cadriage.get(y*8+x).getPiece()!=null)
                    return false;
        return false;
    }
    // a chaque deplacement la tour ne peut aller que dasn une seule direction,
    // soit elle va vers la droite soit elle va vers la gauche, sois vers le bas ou vers le haut
    //si une piece se trouve sur ca trajectoir ell reste bloquer
    public boolean testMouvementTour(List<Case> cadriage, int x1, int y1, int x2, int y2) {
        if(y2 == y1) {
            if(x2>x1)
                for(int x = x1+1; x<8; x++){
                    if(x == x2)
                        return true;
                    else if(cadriage.get(y1*8+x).getPiece()!=null)
                        return false;
                }

            else
                for(int x = x1-1; x>-1; x--) {
                    if(x == x2)
                        return true;
                    else if(cadriage.get(y1*8+x).getPiece()!=null)
                        return false;
                }
        }

        else if(x2 == x1)
            if(y2<y1) {
                for(int y = y1-1; y>-1; y--)
                    if(y == y2)
                        return true;
                    else if(cadriage.get(y*8+x1).getPiece()!=null)
                        return false;
            }
            else{
                for(int y = y1+1; y<8; y++) {
                    if(y == y2)
                        return true;
                    else if(cadriage.get(y*8+x1).getPiece()!=null)
                        return false;
                }
            }
        return false;
    }

    public boolean testMouvementDame(List<Case> cadriage, int x1, int y1, int x2, int y2) {
        if(this.testMouvementFou(cadriage, x1, y1, x2, y2) || this.testMouvementTour(cadriage, x1, y1, x2, y2))
            return true;
        return false;
    }
    //le deplacement est valid les position d lig et de col ou de col et de lig soit different
    public boolean testMouvementRoi(List<Case> cadriage, int x1, int y1, int x2, int y2) {
        if((x2 <= x1+1 && x2 >= x1-1) && (y2 <= y1+1 && y2 >= y1-1))
            return true;
        return false;
    }
    //le pion se deplacement en diagonal, lors de sa premier partie il peut se deplacer d'une case,
    // ou de deux case, faut que la case d'arriver soit vide inoccuper
    // sd pendant le pion peut attaquer uniqement les pion qui se trouve a sa diagonale
    // on fais le verif puour les pion blanc et noir
    public boolean testMouvementPion(List<Case> cadriage, int x1, int y1, int x2, int y2) {
        if(cadriage.get(y1*8+x1).getPiece().getCouleur()==BLANC) {
            if(x2 == x1 && y2 == y1-1 && cadriage.get(y2*8+x1).getPiece()==null)
                return true;
            else if(x2 == x1 && y2 == y1-2 && cadriage.get((y1-1)*8+x1).getPiece()==null
                    && cadriage.get((y1-2)*8+x1).getPiece()==null)
                return true;
            else if((x2 == x1+1 || x2 == x1-1) && y2 == y1-1 && cadriage.get(y2*8+x2).getPiece()!=null)
                return true;
        }


        else if(cadriage.get(y1*8+x1).getPiece().getCouleur()==NOIR) {
            if(x2 == x1 && y2 == y1+1 && cadriage.get(y2*8+x1).getPiece()==null)
                return true;
            else if(x2 == x1 && y2 == y1+2 && cadriage.get((y1+1)*8+x1).getPiece()==null
                    && cadriage.get((y1+2)*8+x1).getPiece()==null)
                return true;
            else if((x2 == x1+1 || x2 == x1-1) && y2 == y1+1 && cadriage.get(y2*8+x2).getPiece()!=null)
                return true;
        }
        return false;
    }



}
