import java.awt.Color;
import java.util.List;

public class Tour extends Piece {

    public void rock() {
    }

    public Tour(String couleur) {
        super(couleur);
        if(couleur == BLANC)
            super.setSymbole(Tour_Blanc);
        else
            super.setSymbole(Tour_Noir);
    }




}