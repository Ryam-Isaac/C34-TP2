package TP2;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class Borne {
    private double banqueTotal;
    private double montantTemporaire;
    private String codeStationnement;
    private Transaction transactionCourante = null;
    private boolean active;

    private final double TARIF_QUARTIER_LATIN = 3.50;
    private final double TARIF_VILLE_EMARD = 2.75;

    public Borne() {
        this.banqueTotal = 0;
        this.montantTemporaire = 0;
        this.codeStationnement = "";
        this.transactionCourante = null;
        this.active = false;
    }

    public double getBanqueTotal() {
        return banqueTotal;
    }

    public double getMontantTemporaire() {
        return montantTemporaire;
    }

    public String getCodeStationnement() {
        return codeStationnement;
    }

    public Transaction getTransactionCourante() {
        return transactionCourante;
    }

    public boolean isActive() {
        return active;
    }

    public double getTARIF_QUARTIER_LATIN() {
        return TARIF_QUARTIER_LATIN;
    }

    public double getTARIF_VILLE_EMARD() {
        return TARIF_VILLE_EMARD;
    }

    public boolean validationCodeStationnement(String codeStationnement) {
        String regex1 = "G\\d{3}";
        String regex2 = "SQ\\d{3}";
        return codeStationnement.matches(regex1) || codeStationnement.matches(regex2);
    }

    public boolean validationHeurePayantStationnement(String codeStationnement) {
        LocalDateTime maintenant = LocalDateTime.now();
        DayOfWeek jourSemaine = maintenant.getDayOfWeek();
        int heure = maintenant.getHour();

        if (jourSemaine == DayOfWeek.SUNDAY) {
            return false;
        }

        if (codeStationnement.startsWith("G") && heure >= 9 && heure < 21) {
            return true;
        } else if (codeStationnement.startsWith("SQ") && heure >= 10 && heure < 18) {
            return true;
        }

        return false;
    }

    public void demarrerTransaction(String codeStationnement) {
        if (validationHeurePayantStationnement(codeStationnement) && validationCodeStationnement(codeStationnement)) {
            this.codeStationnement = codeStationnement;
            this.active = true;
            this.montantTemporaire = 0;
            this.transactionCourante = new Transaction("INCONNU", 0.0, LocalDateTime.now(), null);


        }
    }

    public boolean payerParCarte(CarteCredit carte, double montant) {
        if (!active || transactionCourante == null) return false;

        if (validationCarteCredit(carte) && carte.getSolde() >= montant) {
            carte.retirerArgent(montant);
            transactionCourante.setTypePaiement("CARTE");
            transactionCourante.setCoutTotal(montant);
            transactionCourante.setHeureFin(LocalDateTime.now());
            transactionCourante.setEspaceStationnement(codeStationnement);
            active = false;
            transactionCourante = null;
            codeStationnement = "";
            return true;
        }
        return false;
    }


    public void insererPiece(Piece piece) {
        montantTemporaire += piece.getValeur();
        if (transactionCourante != null) {
            transactionCourante.ajouterMontant(piece.getValeur());
        }
    }

    public boolean validationCarteCredit(CarteCredit carteCredit) {
        return carteCredit.getNumeroCredit().matches("\\d{16}");
    }

    public void confirmerTransaction() {
        if (transactionCourante != null && active) {
            transactionCourante.setCoutTotal(montantTemporaire);
            transactionCourante.setTypePaiement("PIECE");
            transactionCourante.setHeureFin(LocalDateTime.now());
            transactionCourante.setEspaceStationnement(codeStationnement);

            mettreAJourBanque();
            montantTemporaire = 0;
            active = false;
            transactionCourante = null;
            codeStationnement = "";
        }
    }

    public void annulerTransaction() {
        if (transactionCourante != null && active) {
            montantTemporaire = 0;
            transactionCourante = null;
            active = false;
            codeStationnement = "";
        }
    }

    private void mettreAJourBanque() {
        this.banqueTotal += this.montantTemporaire;
    }
}
