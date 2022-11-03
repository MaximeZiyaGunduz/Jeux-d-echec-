public class Pion extends Piece {

    public Pion(String couleur) {
        super(couleur);
        if(couleur == BLANC)
            super.setSymbole(Pion_Blanc);
        else
            super.setSymbole(Pion_Noir);
    }
}