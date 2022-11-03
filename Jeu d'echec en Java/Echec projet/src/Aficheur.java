

class Afficheur implements ValeursEchiquier {

    public void affichageMenu() {
        System.out.println("_________________\n"
                + "_________________\n"
                + "MENU\n"
                + "1. Jouer\n"
                + "2. Definir nom\n"
                + "3. Commandes et coups speciaux\n"
                + "4. Quitter\n");
    }




    public void affichagePartie(boolean nomDefini, String tour, Joueur j1, Joueur j2, Echiquier plateau) {
        System.out.println(plateau);

        if(!nomDefini)
            if(tour == BLANC)
                this.affichageTour("J1");
            else
                this.affichageTour("J2");
        else
        if(tour == BLANC)
            this.affichageTour(j1.getNom());
        else
            this.affichageTour(j2.getNom());

        System.out.print("\n Deplacement(ex : A2 A4) : ");
    }


    public void affichageVictoire(String victorieux) {
        System.out.println(
                "\n" +
                        "VICTOIRE DU "+ victorieux
                        );
    }


    public void affichageTour(String joueurActuel) {
        System.out.print("-*-*-*-*TOUR DU  "+ joueurActuel +"-*-*-*");
    }


    public void affichageEgalite() {
        System.out.println(

                        "///////////////////EGALITE/////////////\n"

        );
    }

    public void affichageCommandsEtCoupsSpeciaux() {
        System.out.println("\n\n"
                + "Commande pour abandonner : \"abandonner\"\n"
                + "Syntaxe du ROQUE : \"ROQUE \" + Coordonnees de la tour choisie\n");
    }

}