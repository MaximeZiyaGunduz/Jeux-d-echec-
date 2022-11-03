public class PartieMat{
    public boolean testCheckMate(Echiquier plat, int x, int y, String tour) {
        if(!this.testMat(plat, x, y, tour))
            return false;
        //PRENDS CHAQUES PIECES ET TESTE CHAQUES MOUVEMENTS POSSIBLE,
        //SI UN MOUVEMENT EST REALISABLE SANS QUE testMat() == true,
        //RETURN FALSE,
        //SINON, RETURN TRUE LORSQUE TOUT L'ECHIQUIER A ETE TESTER
        for(int i = 0; i<8;i++)
            for(int j = 0; j<8;j++) {

                if(plat.getCadriage().get(i*8+j).getPiece() != null && plat.getCadriage().get(i*8+j).getPiece().getCouleur() == tour)
                    for(int i2 = 0; i2<8; i2++)
                        for(int j2 = 0; j2<8; j2++) {
                            if(plat.testerMouvement(tour, j, i, j2, i2)) {
                                if(plat.getCadriage().get(i*8+j).getPiece() instanceof Roi) {
                                    if(!this.testMat(plat, j2, i2, tour)) {
                                        return false;
                                    }
                                }
                                else {
                                    Piece testTemp = plat.getCadriage().get(i2*8+j2).getPiece();
                                    plat.getCadriage().get(i2*8+j2).setPiece(plat.getCadriage().get(i*8+j).getPiece());
                                    plat.getCadriage().get(i*8+j).supprimerPiece();
                                    if(!this.testMat(plat, x, y, tour)) {
                                        plat.getCadriage().get(i*8+j).setPiece(plat.getCadriage().get(i2*8+j2).getPiece());
                                        plat.getCadriage().get(i2*8+j2).setPiece(testTemp);
                                        System.out.println(i2+"la"+j2);
                                        return false;
                                    }
                                    plat.getCadriage().get(i*8+j).setPiece(plat.getCadriage().get(i2*8+j2).getPiece());
                                    plat.getCadriage().get(i2*8+j2).setPiece(testTemp);
                                }

                            }

                        }
            }
        return true;
    }

    public boolean testPat(Echiquier plat, int x, int y, String tour) {
        //MAT SANS LE PREMIER TEST//
        for(int i = 0; i<8;i++)
            for(int j = 0; j<8;j++) {
                if(plat.getCadriage().get(i*8+j).getPiece() != null && plat.getCadriage().get(i*8+j).getPiece().getCouleur() == tour)
                    // On parcours le plateau et on regarde pour chaque piece restante si un deplacement est possible si tout les pieces presente ne peuvent plus bouger alors c'est un PAT
                    for(int i2 = 0; i2<8; i2++)
                        for(int j2 = 0; j2<8; j2++) {

                            if(plat.testerMouvement(tour, j, i, j2, i2)) {
                                if(plat.getCadriage().get(i*8+j).getPiece() instanceof Roi) {
                                    if(!this.testMat(plat, j2, i2, tour)) {
                                        return false;
                                    }
                                }
                                else {
                                    Piece testTemp = plat.getCadriage().get(i2*8+j2).getPiece();
                                    plat.getCadriage().get(i2*8+j2).setPiece(plat.getCadriage().get(i*8+j).getPiece());
                                    plat.getCadriage().get(i*8+j).supprimerPiece();
                                    if(!this.testMat(plat, x, y, tour)) {
                                        plat.getCadriage().get(i*8+j).setPiece(plat.getCadriage().get(i2*8+j2).getPiece());
                                        plat.getCadriage().get(i2*8+j2).setPiece(testTemp);
                                        return false;
                                    }
                                    plat.getCadriage().get(i*8+j).setPiece(plat.getCadriage().get(i2*8+j2).getPiece());
                                    plat.getCadriage().get(i2*8+j2).setPiece(testTemp);
                                }

                            }

                        }
            }
        return true;
    }

    public boolean testMat(Echiquier plat, int x, int y, String tour) {
        if(testDangerRoiCauseCavalier(plat,x,y,tour))
            return true;
        if(testDangerRoiCauseDiagonales(plat,x,y,tour))
            return true;
        if(testDangerRoiCauseAxes(plat,x,y,tour))
            return true;
        return false;
    }

    public boolean testDangerRoiCauseCavalier(Echiquier plat, int x, int y, String tour) {
        Piece test;
        try{
            //Possibilite Cavalier Droite Haut//
            test = plat.getCadriage().get((y+1)*8+x+2).getPiece();
            if( test instanceof Cavalier && test.getCouleur() != tour)
                return true;
        }catch(Exception e) {}

        try{
            //Possibilite Cavalier Droite Bas//
            test = plat.getCadriage().get((y-1)*8+x+2).getPiece();
            if( test instanceof Cavalier && test.getCouleur() != tour)
                return true;
        }catch(Exception e) {}

        try {
            //Possibilite Cavalier Gauche Haut//
            test = plat.getCadriage().get((y+1)*8+x-2).getPiece();
            if(test instanceof Cavalier && test.getCouleur() != tour)
                return true;
        }catch(Exception e) {}

        try {
            //Possibilite Cavalier Gauche Bas//
            test = plat.getCadriage().get((y-1)*8+x-2).getPiece();
            if(test instanceof Cavalier && test.getCouleur() != tour)
                return true;
        }catch(Exception e) {}

        try {
            //Possibilite Cavalier Bas Gauche//
            test = plat.getCadriage().get((y+2)*8+x-1).getPiece();
            if(test instanceof Cavalier && test.getCouleur() != tour)
                return true;
        }catch(Exception e) {}

        try {
            //Possibilite Cavalier Bas Droite//
            test = plat.getCadriage().get((y+2)*8+x+1).getPiece();
            if(test instanceof Cavalier && test.getCouleur() != tour)
                return true;
        }catch(Exception e) {}
        try {

            //Possibilite Cavalier Haut Gauche//
            test = plat.getCadriage().get((y-2)*8+x-1).getPiece();
            if(test instanceof Cavalier && test.getCouleur() != tour)
                return true;
        }catch(Exception e) {}

        try {
            //Possibilite Cavalier Haut Droite//
            test = plat.getCadriage().get((y-2)*8+x+1).getPiece();
            if(test instanceof Cavalier && test.getCouleur() != tour)
                return true;
        }catch(Exception e) {}
        return false;
    }

    public boolean testDangerRoiCauseDiagonales(Echiquier plat, int x, int y, String tour) {
        int y1;
        int x1;
        boolean champLibre;
        Piece test = null;
        for( y1 = y+1, x1 = x+1, champLibre = false; !champLibre && y1<8 && x1<8; y1++, x1++) {
            test = plat.getCadriage().get(y1*8+x1).getPiece();
            if( (test instanceof Fou || test instanceof Dame )  && test.getCouleur() != tour) {
                return true;
            }
            else if(test != null){

                champLibre = true;}
        }

        for( y1 = y+1, x1 = x-1, champLibre = false; !champLibre && y1<8 && x1>-1; y1++, x1--) {
            test = plat.getCadriage().get(y1*8+x1).getPiece();
            if( (test instanceof Fou || test instanceof Dame )  && test.getCouleur() != tour)
                return true;
            else if(test != null)
                champLibre = true;
        }

        for( y1 = y-1, x1 = x-1, champLibre = false; !champLibre && y1>-1 && x1>-1; y1--, x1--) {
            test = plat.getCadriage().get(y1*8+x1).getPiece();
            if( (test instanceof Fou || test instanceof Dame )  && test.getCouleur() != tour)
                return true;
            else if(test != null)
                champLibre = true;
        }

        for( y1 = y-1, x1 = x+1, champLibre = false; !champLibre && y1>-1 && x1<8; y1--, x1++) {
            test = plat.getCadriage().get(y1*8+x1).getPiece();
            if( (test instanceof Fou || test instanceof Dame )  && test.getCouleur() != tour)
                return true;
            else if(test != null)
                champLibre = true;
        }
        return false;
    }

    public boolean testDangerRoiCauseAxes(Echiquier plat, int x, int y, String tour) {
        int y1;
        int x1;
        boolean champLibre;
        Piece test = null;
        for( y1 = y+1, x1 = x, champLibre = false; !champLibre && y1<8; y1++) {
            test = plat.getCadriage().get(y1*8+x1).getPiece();
            if( (test instanceof Tour || test instanceof Dame )  && test.getCouleur() != tour)
                return true;
            else if(test != null)
                champLibre = true;
        }

        for( y1 = y-1 , x1 = x, champLibre = false; !champLibre && y1>-1; y1--) {
            test = plat.getCadriage().get(y1*8+x1).getPiece();
            if( (test instanceof Tour || test instanceof Dame )  && test.getCouleur() != tour)
                return true;
            else if(test != null)
                champLibre = true;
        }

        for( y1 = y , x1 = x+1, champLibre = false; !champLibre && x1<8; x1++) {
            test = plat.getCadriage().get(y1*8+x1).getPiece();
            if( (test instanceof Tour || test instanceof Dame )  && test.getCouleur() != tour)
                return true;
            else if(test != null)
                champLibre = true;
        }

        for( y1 = y , x1 = x-1, champLibre = false; !champLibre && x1>-1; x1--) {
            test = plat.getCadriage().get(y1*8+x1).getPiece();
            if( (test instanceof Tour || test instanceof Dame )  && test.getCouleur() != tour)
                return true;
            else if(test != null)
                champLibre = true;
        }
        return false;
    }

    public boolean testDangerRoiCausePion(Echiquier plat, int x, int y, String tour) {
        Piece test;
        try{
            //Possibilite Cavalier Droite Haut//
            test = plat.getCadriage().get((y+1)*8+x+1).getPiece();
            if( test instanceof Pion && test.getCouleur() != tour)
                return true;
        }catch(Exception e) {}

        try{
            //Possibilite Cavalier Droite Haut//
            test = plat.getCadriage().get((y+1)*8+x-1).getPiece();
            if( test instanceof Pion && test.getCouleur() != tour)
                return true;
        }catch(Exception e) {}

        try{
            //Possibilite Cavalier Droite Haut//
            test = plat.getCadriage().get((y-1)*8+x+1).getPiece();
            if( test instanceof Pion && test.getCouleur() != tour)
                return true;
        }catch(Exception e) {}

        try{
            //Possibilite Cavalier Droite Haut//
            test = plat.getCadriage().get((y-1)*8+x-1).getPiece();
            if( test instanceof Pion && test.getCouleur() != tour)
                return true;
        }catch(Exception e) {}
        return false;
    }

}