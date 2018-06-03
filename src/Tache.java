import java.util.ArrayList;

/** Classe qui contient la représentation d'une tâche d'un job
 * Elle contient notamment le numéro de la tâche, le numéro du job auquel elle appartient, son état et sa date de fin
 * Elle contient également la liste des machines (et leurs coûts) qui peuvent l'effectuer
 */
public class Tache {
    /* numeroTache de la tache dans le job */
    private int numeroTache;

    /* numero du job auquel appartient cette tache*/
    private int numJob ;
    public int etat; //0 pas fait, 1 en cours, 2 terminee
    public int dateFin ;

    public ArrayList<MachineCout> coupleMachineCout;

    /**Constructeur qui contient un numéro de tache et de job
     * La liste des Machine cout sera donné plus tard avec addCouple
     * @param numeroTache
     * @param numJob
     */
    public Tache(int numeroTache, int numJob ) {
        this.numeroTache = numeroTache;
        this.etat = 0 ;
        this.numJob=numJob;
        this.coupleMachineCout = new ArrayList<>();
    }

    /** Méthode qui détermine l'index de la première machine disponible qui puisse effectuer la tâche
     * @param machines liste de toutes les machines avec leurs données
     * @return index de la première machine disponible qui correspond, -1 si pas trouvé
     */
    public int MachineDispo(ArrayList<Machine> machines){
        int index = 0;
        for (MachineCout couple: coupleMachineCout) {
            if(machines.get(couple.numeroMachine-1).disponible) return index;
            index++;
        }
        return -1 ; // on n'en a pas trouvé
    }

    /** Méthode qui détermine la position de la machine de ce numéro dans la liste de MachineCout
     * @param num
     * @return index de la machine qui correspond, -1 si pas trouvé
     */
    public int indiceMachine(int num){
        int index =0;
        for (MachineCout couple: coupleMachineCout) {
            if(couple.numeroMachine==num) return index;
            index++;
        }
        return -1;
    }

    /** Getter de numJob (ne doit pas être modifié)
     * @return numJob
     */
    public int getNumJob() {
        return numJob;
    }

    /** Getter de numTache (ne doit pas être modifié)
     * @return numTache
     */
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
