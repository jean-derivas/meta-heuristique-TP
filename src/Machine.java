public class Machine{

    private int numeroMachine ;
    public boolean disponible ;
    private int numTache ;
    private int numJob ;

    public Machine(int numeroMachine) {
        this.numeroMachine = numeroMachine;
        this.disponible = true;
        this.numTache = -1 ; // -1 veut dire qu'elle vient juste d'etre initialisé et n'a jamais servi
        this.numJob = -1 ;

    }
}
