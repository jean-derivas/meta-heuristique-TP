public class Machine{

    private int numeroMachine ;
    public boolean disponible ;
    public int numTache ;
    public int numJob ;

    public Machine(int numeroMachine) {
        this.numeroMachine = numeroMachine;
        this.disponible = true;
        this.numTache = -1 ; // -1 veut dire qu'elle vient juste d'etre initialisé et n'a jamais servi
        this.numJob = -1 ;

    }

    public int getNumeroMachine() {
        return numeroMachine;
    }

    @Override
    public String toString() {
        return "numeroMachine = " + this.numeroMachine + " ; dispo = " + this.disponible + "\n";
    }
}
