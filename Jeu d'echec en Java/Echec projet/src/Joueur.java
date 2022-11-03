public class Joueur implements ValeursEchiquier {
    private String CouleurPions;
    private String Nom;
    private int abscisseCoord;
    private int ordonneCoord;
    private boolean positionInitial;

    public void abandonnerPartie() {
        System.out.println("FIN PARTIE");
        return;
    }

    String getNom() {
        return this.Nom;
    }

    void setNom(String value) {
        this.Nom = value;
    }

    public Joueur(String couleur, int abscisseCoord, int ordonneCoord) {
        this.CouleurPions = couleur;
        this.Nom = new String();
        this.positionInitial = true;
        this.abscisseCoord = abscisseCoord;
        this.ordonneCoord = ordonneCoord;
    }

    int getAbscisseCoord() {
        return this.abscisseCoord;
    }

    void setAbscisseCoord(int value) {
        this.abscisseCoord = value;
    }

    int getOrdonneCoord() {
        return this.ordonneCoord;
    }

    void setOrdonneCoord(int value) {
        this.ordonneCoord = value;
    }

    boolean getPositionInitial() {
        return this.positionInitial;
    }

    void setPositioniInitial(boolean positionInitiale) {
        this.positionInitial = positionInitiale;
    }
}
