import java.util.ArrayList;

public class Tache {
    /* numeroTache de la tache dans le job */
    private int numeroTache;

    /* numero du job auquel appartient cette tache*/
    private int numJob ;
    public int etat; //0 pas fait, 1 en cours, 2 terminee
    public int dateFin ;

    public ArrayList<MachineCout> coupleMachineCout;

    /**
     * Constructeur qui contient un numeroTache de tache et un couple machine cout
     * @param numeroTache
     * @param coupleMachineCout
     */
    public Tache(int numeroTache, int numJob ,ArrayList<MachineCout> coupleMachineCout) {
        this.numeroTache = numeroTache;
        this.numJob=numJob;
        this.etat = 0 ;
        this.coupleMachineCout = coupleMachineCout;
    }

    /**
     *
     * @param machines liste de toutes les machines
     * @return index de la première machine disponible qui correspond, -1 si pas trouvé
     */
    public int MachineDispo(ArrayList<Machine> machines){
        int index = 0;
        for (MachineCout couple: coupleMachineCout) {
            //if(couple.numeroMachine==6) System.out.println("Probleme!!!: "+this);
            if(machines.get(couple.numeroMachine-1).disponible) return index;
            index++;
        }
        return -1 ; // on n'en a pas trouvé
    }


    public int indiceMachine(int num){
        int index =0;
        for (MachineCout couple: coupleMachineCout) {
            //if(couple.numeroMachine==6) System.out.println("Probleme!!!: "+this);
            if(couple.numeroMachine==num) return index;
            index++;
        }
        return -1;
    }

    /**
     * Constructeur qui contient qu'un numéro de tache. La liste des Machine cout sera donné plus tard avec addCouple
     * @param numeroTache
     */
    public Tache(int numeroTache, int numJob ) {
        this.numeroTache = numeroTache;
        this.etat = 0 ;
        this.numJob=numJob;
        this.coupleMachineCout = new ArrayList<>();
    }

    public int getNumJob() {
        return numJob;
    }

    public int getNumeroTache() {
        return numeroTache;
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
        return ("Job : " + this.numJob +  ", numeroTache : "+ this.numeroTache + ", Etat : " + this.etat + ", datefin : " + this.dateFin + ",  Liste machine cout :" + this.coupleMachineCout+"\n");
    }



    public static void main(String[] args) {

    }
}
