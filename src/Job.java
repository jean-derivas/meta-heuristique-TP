import java.util.ArrayList;

/** Classe qui représente les travaux (jobs) à effectuer
 *  Elle contient notamment le numéro du job, et la liste des tâches de ce job
 */
public class Job {
    //numéro du job
    private int numJob ;

    //Un job contient une liste de taches
    public ArrayList<Tache> lesTaches ;

    /**
     * Constructeur qui initialise à la liste vide
     */
    public Job() {
        this.lesTaches = new ArrayList<>();
    }

    public void setNumJob(int numJob){ this.numJob=numJob;}

    public int getNumJob(){return this.numJob;}

    @Override
    public String toString() {
        String resu = "Taches du job " + numJob + ":";
        for(Tache tache:lesTaches){
            resu+=tache;
        }
        return resu;
    }
}
