package Operation;

import java.time.LocalDate;
import java.util.UUID;

public abstract class Operation {
    protected String numero;
    protected LocalDate date;
    protected double montant;

    public Operation(double montant){
        this.numero = UUID.randomUUID().toString();
        this.date = LocalDate.now();
        this.montant = montant;
    }

    public String getNumero() {
        return numero;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getMontant() {
        return montant;
    }
}
