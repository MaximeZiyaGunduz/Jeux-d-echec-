public class Roi extends Piece {
    public Roi(String couleur) {
        super(couleur);
        if(couleur == BLANC)
            super.setSymbole(Roi_Blanc);
        else
            super.setSymbole(Roi_Noir);
    }
}
