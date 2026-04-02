package CompagnieAerienne;

public class Sieges {
    private int numeroSiege;
    private boolean reserve;

    public Sieges(int numeroSiege) {
        this.numeroSiege = numeroSiege;
        this.reserve = false;
    }

    public int getNumeroSiege() { return numeroSiege; }
    public boolean isReserved()  { return reserve; }

    public void reserveSiege() { this.reserve = true;  }
    public void libereSiege()  { this.reserve = false; }

    @Override
    public String toString() {
        return "Siège " + numeroSiege + (reserve ? " [Réservé]" : " [Disponible]");
    }
}
