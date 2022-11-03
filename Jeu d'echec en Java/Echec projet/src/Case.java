public class Case implements ValeursEchiquier {
    private String couleur; // couleur de la case
    private int x; // coordonnees x et y
    private int y;
    private Piece piece;  // une piece de type Piece

    public Case(int x, int y, String couleur) { // constructeur membre � membre
        this.x = x;
        this.y = y;
        this.couleur = couleur;
    }

    public Case(int x, int y, String couleur, Piece piece) {
        this.x = x;
        this.y = y;
        this.couleur = couleur;
        this.piece = piece;
    }

    String getCouleur() { // getter et setter
        return this.couleur;
    }

    void setCouleur(String value) {
        this.couleur = value;
    }

    int getY() {
        return this.y;
    }

    void setY(int value) {
        this.y = value;
    }

    int getX() {
        return this.x;
    }

    void setX(int value) {
        this.x = value;
    }

    Piece getPiece() {
        return this.piece;
    }

    void setPiece(Piece value) {
        this.piece = value;
    }

    void supprimerPiece() {
        this.piece = null;
    }
}