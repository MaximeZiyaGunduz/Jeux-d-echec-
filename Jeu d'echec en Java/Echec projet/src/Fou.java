

public class Fou extends Piece {

    public Fou(String couleur) {
        super(couleur);
        if(couleur == BLANC)
            super.setSymbole(Four_Blanc);
        else
            super.setSymbole(Four_Noir);
    }
}
