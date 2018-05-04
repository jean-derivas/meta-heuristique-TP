import java.util.ArrayList;

public class Solution {

    public ArrayList<Integer> OS ;
    public ArrayList<Integer> MA ;

    public Solution(ArrayList<Integer> OS, ArrayList<Integer> MA) {
        this.OS = OS;
        this.MA = MA;
    }

    public Solution() {
        this.OS = new ArrayList<>();
        this.MA = new ArrayList<>();
    }


    /**
     * Genere la solution intelligente (void a changer)
     */
    public static void genererSolution1(InfoParse info){

        // modélise le temps
        int temps = 0 ;

        // condition de terminaison
        boolean termine = false ;


        // liste des machines à mettre à jour à chaque affectation
        ArrayList<Machine> listeMachine = new ArrayList<>();

        // crée autant de machines qu'il en existe
        for (int i = 0; i < info.nbMachine ; i++) {
            listeMachine.add(new Machine(i));
        }

        ArrayList<Tache> listeTacheAffecte = new ArrayList<>();


        ArrayList<Tache> successeur = new ArrayList<>() ;
        for(Job job : info.jobs){
            successeur.add(job.lesTaches.get(0));
        }




        while(!termine) {
            // si une machine est dispo on essaie de l'affecter
            if (machinedispo) {

                // pour chaque tache dans successeur
                for (Tache tache : successeur) {

                    // si pas dans les noeuds affectés
                    if (tache.etat == 0) {
                        if(pasmachinedispopourmonsuccesseur){

                        }
                        int numJob = tache.getNumJob();
                        int numTache = tache.getNumeroTache();
                        // on affecte la tache actuelle
                        tache.etat=1;
                        // on retire la tache désormais affectée des successeurs
                        successeur.remove(tache);
                        // on l'ajoute dans la liste des taches affectées
                        listeTacheAffecte.add(tache);
                        // on ajoute le successeur de cette tache à la liste des successeurs
                        successeur.add(info.jobs.get(numJob).lesTaches.get(numTache+1));
                    }

                    // si dernier noeud du job
                    if(info.jobs.get(tache.getNumJob()).getNbTaches()==tache.getNumeroTache()){
                        if(info.jobs.)
                    }




                }
                temps++;
            }
        }
    }
}

