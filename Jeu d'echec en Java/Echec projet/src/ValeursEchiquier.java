import java.awt.Color;

public interface ValeursEchiquier {

    // permet le placemet de nos pieces, en attribuant une couleur et une forme
    public static final String BLANC  = "BLANC"; // couleur
    public static final String  NOIR = "NOIR";
    public static final String  Roi_Blanc = "Rb"; //forme
    public static final String  Dame_Blanc= "D";
    public static final String 	Tour_Blanc = "T";
    public static final String Four_Blanc ="F";
    public static final String Cavalier_Blanc ="C";
    public static final String Pion_Blanc = "P";
    public static final String Roi_Noir = "R";
    public static final String Dame_Noir = "D";
    public static final String Four_Noir = "F";
    public static final String Cavalier_Noir = "C";
    public static final String Pion_Noir = "P";
    public static final String Tour_Noir = "T";


    public static final Piece[] tabPieceB = { // initalisation de la position des piecesB dns le tableau (plateau)

            new Pion(BLANC), new Pion(BLANC),new Pion(BLANC), new Pion(BLANC),
            new Pion(BLANC), new Pion(BLANC),new Pion(BLANC), new Pion(BLANC),
            new Tour(BLANC), new Cavalier(BLANC), new Fou(BLANC),new Dame(BLANC),
            new Roi(BLANC), new Fou(BLANC), new Cavalier(BLANC), new Tour(BLANC)};

    public static final Piece[] tabPieceN = { // initalisation de la position des piecesN dns le tableau (plateau)
            new Tour(NOIR), new Cavalier(NOIR), new Fou(NOIR),new Dame(NOIR),
            new Roi(NOIR),new Fou(NOIR), new Cavalier(NOIR), new Tour(NOIR),
            new Pion(NOIR), new Pion(NOIR),new Pion(NOIR), new Pion(NOIR),
            new Pion(NOIR),new Pion(NOIR), new Pion(NOIR), new Pion(NOIR)};


}