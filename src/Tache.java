import java.util.ArrayList;

public class Tache {
    /* numeroTache de la tache dans le job */
    private int numeroTache;

    /* numero du job auquel appartient cette tache*/
    private int numJob ;

    public boolean termine ;

    public ArrayList<MachineCout> coupleMachineCout;

    /**
     * Constructeur qui contient un numeroTache de tache et un couple machine cout
     * @param numeroTache
     * @param coupleMachineCout
     */
    public Tache(int numeroTache, int numJob ,ArrayList<MachineCout> coupleMachineCout) {
        this.numeroTache = numeroTache;
        this.numJob=numJob;
        this.termine = false ;
        this.coupleMachineCout = coupleMachineCout;
    }

    /**
     * Constructeur qui contient qu'un numéro de tache. La liste des Machine cout sera donné plus tard avec addCouple
     * @param numeroTache
     */
    public Tache(int numeroTache, int numJob ) {
        this.numeroTache = numeroTache;
        this.termine = false ;
        this.numJob=numJob;
        this.coupleMachineCout = new ArrayList<>();
    }


    /**
     * Ajoute un couple machine cout à la liste de la tache
     * @param machineCout
     */
    public void addCouple(MachineCout machineCout){
        this.coupleMachineCout.add (machineCout);
    }


    @Override
    public String toString() {
        return ("\nTache numeroTache : "+ this.numeroTache +", Liste machine cout :" + this.coupleMachineCout);
    }



    public static void main(String[] args) {

    }
}
