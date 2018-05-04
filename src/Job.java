import java.util.ArrayList;

public class Job {
    /* numéro du job */
    private int numJob ;

    private int indexTacheAffecte ;


    /**
     * Un job contient une liste de taches
     */
    public ArrayList<Tache> lesTaches ;

    /**
     * Constructeur en donnant liste de taches
     * @param lesTaches
     */
    public Job(ArrayList<Tache> lesTaches) {
        this.lesTaches = lesTaches;
    }

    /**
     * Constructeur qui initialise à la liste vide
     */
    public Job() {
        this.lesTaches = new ArrayList<>();
    }


    /**
     * Ajoute tache
     * @param tache
     */
    public void addTache(Tache tache){
        this.lesTaches.add(tache);
    }

    public void setNumJob(int numJob){ this.numJob=numJob;}

    public int getNumJob(){return this.numJob;}

    @Override
    public String toString() {
        return("Taches du job " + numJob + ": "+lesTaches+"\n");
    }
}
