package TP2;

import java.time.Month;
import java.time.Year;

public class CarteCredit {

    private Year anneeExpiration;
    private Month moisExpiration;
    private double solde;
    private String numeroCredit;


    public CarteCredit( Year anneeExpiration,Month moisExpiration,double solde,String numeroCredit){
        this.anneeExpiration= anneeExpiration;
        this.moisExpiration = moisExpiration;
        this.solde = solde;
        this.numeroCredit = numeroCredit;
    }


    public Year getAnneeExpiration() {
        return anneeExpiration;
    }

    public Month getMoisExpiration() {
        return moisExpiration;
    }

    public double getSolde() {
        return solde;
    }

    public String getNumeroCredit() {
        return numeroCredit;
    }


    public double ajouterArgent (double montantAjouter ){
        return solde+=montantAjouter;
    }

    public double retirerArgent (double montantRetirer){
        return solde-=montantRetirer;
    }


}