public class Dame extends Piece {

    public Dame(String couleur) {
        super(couleur);
        if(couleur == BLANC)
            super.setSymbole(Dame_Blanc);
        else
            super.setSymbole(Dame_Noir);
    }

}