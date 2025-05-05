package TP2;

public class Piece {

    private int valeur;

    public Piece(int valeur) {
        if(valeur == 25){
            this.valeur = 25;
        }
        else if (valeur == 100){
            this.valeur =100;
        }
        else if (valeur ==200){
            this.valeur =200;
        }


    }
    public int getValeur() {
        return valeur;
    }

}