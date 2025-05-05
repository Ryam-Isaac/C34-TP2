package TP2;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.Year;
import java.util.Objects;

public class Transaction {

    private String typePaiement ;
    private double coutTotal ;
    private LocalDateTime heureDebut;
    private LocalDateTime heureFin;
    private String EspaceStationnement;

   public Transaction(String typePaiement, double coutTotal, LocalDateTime heureDebut, LocalDateTime heureFin){
       this.typePaiement = typePaiement;
       this.coutTotal =coutTotal;
       this.heureDebut = heureDebut;
       this.heureFin = heureFin;
   }

    public String getTypePaiement() {
        return typePaiement;
    }

    public double getCoutTotal() {
        return coutTotal;
    }

    public LocalDateTime getHeureDebut() {
        return heureDebut;
    }

    public LocalDateTime getHeureFin() {
        return heureFin;
    }

    public String getEspaceStationnement() {
        return EspaceStationnement;
    }
    public void setTypePaiement(String typePaiement) {
        this.typePaiement = typePaiement;
    }

    public void setCoutTotal(double coutTotal) {
        this.coutTotal = coutTotal;
    }

    public void setHeureFin(LocalDateTime heureFin) {
        this.heureFin = heureFin;
    }

    public void setEspaceStationnement(String espaceStationnement) {
        this.EspaceStationnement = espaceStationnement;
    }


    public void ajouterMontant(double montant) {
        // Implémente une logique selon tes besoins, ex. : cumul du montant
        this.coutTotal += montant;
    }



}
