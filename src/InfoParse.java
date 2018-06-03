import java.util.ArrayList;

/** Classe contenant les données du problème
 *  Elle contient notamment l'ensemble des jobs et de leurs tâches, et le nb de machines total
 */
public class InfoParse {

    public ArrayList<Job> jobs ;
    public int nbMachine ;

    public InfoParse(ArrayList<Job> jobs, int nbMachine) {
        this.jobs = jobs;
        this.nbMachine=nbMachine;
    }

    /** Méthode pour réinitialiser les données
     *  Elle remet les dates de fin et l'état de chaque tâche à 0
     */
    public void reinitialiserParse(){
        for(Job job: jobs){
            for(Tache tache: job.lesTaches){
                tache.dateFin=0;
                tache.etat=0;
            }
        }
    }
}
