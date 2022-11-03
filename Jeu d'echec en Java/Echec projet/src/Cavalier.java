public class Cavalier extends Piece {

    public Cavalier(String couleur) {
        super(couleur);
        if(couleur == BLANC)
            super.setSymbole(Cavalier_Blanc);
        else
            super.setSymbole(Cavalier_Noir);
    }
}