import java.awt.Color;

public abstract class Piece implements ValeursEchiquier {
    private String couleur; //couleur de la piece
    private String symbole; // piece specifique (+ couleur)

    public Piece(String couleur) {  //constructeur
        this.couleur = couleur;
    }

    public String getCouleur() {  //getter + setter
        return this.couleur;
    }

    public void setCouleur(String value) {
        this.couleur = value;
    }

    public String getSymbole() {
        return this.symbole;
    }

    public void setSymbole(String symbole) {
        this.symbole = symbole;
    }
}
