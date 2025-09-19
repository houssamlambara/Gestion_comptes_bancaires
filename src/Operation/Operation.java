package Operation;

import java.time.LocalDate;
import java.util.UUID;

public abstract class Operation {
    protected UUID numero;
    protected LocalDate date;
    protected double montant;

    public Operation(double montant){
        this.numero = UUID.randomUUID();
        this.date = LocalDate.now();
        this.montant = montant;
    }

    public UUID getNumero() {
        return numero;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }
}
