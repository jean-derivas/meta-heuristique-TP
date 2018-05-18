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

            // on détermine si une machine est dispo
            boolean machinedispo=false;
            int i=0;
            while(!machinedispo && i<listeMachine.size()){
                machinedispo = listeMachine.get(i).disponible;
                i++;
            }

            // si une machine est dispo on essaie de l'affecter
            if (machinedispo) {
                // pour chaque tache dans successeur
                for (Tache tache : successeur) {

                    int numJob = tache.getNumJob();
                    int numTache = tache.getNumeroTache();

                    // si pas dans les noeuds affectés et tache précédente finie
                    if (tache.etat == 0 && info.jobs.get(numJob).lesTaches.get(numTache-1).etat==2) {

                        // on récupère la première machine dispo pour la tache
                        int machine = tache.MachineDispo(listeMachine);

                        // on n'affecte que dans le cas où on a bel et bien une machine dispo pour la tache
                        if(machine!=-1){
                            Machine m = listeMachine.get(machine);
                            // on affecte la tache à la machine, et on la rend indisponible
                            m.disponible=false;
                            m.numTache=tache.getNumeroTache();
                            m.numJob=tache.getNumJob();
                            // on affecte la tache actuelle
                            tache.etat=1;
                            // on initialise la date de fin de la tache avec duree tache+temps
                            tache.dateFin=tache.coupleMachineCout.get(machine).coutMachine+temps;
                            // on retire la tache désormais affectée des successeurs
                            successeur.remove(tache);
                            // on l'ajoute dans la liste des taches affectées
                            listeTacheAffecte.add(tache);
                            // on ajoute le successeur de cette tache à la liste des successeurs
                            // ssi ce n'est pas la derniere tache du job
                            if(numTache <info.jobs.get(numJob).lesTaches.size()-1)
                                successeur.add(info.jobs.get(numJob).lesTaches.get(numTache+1));
                        }
                    }

                    // si dernier noeud du job
                    if(info.jobs.get(tache.getNumJob()).getNbTaches()==tache.getNumeroTache()){
                    //    if(info.jobs.)
                    }

                }
            }
            temps++;

            for(Tache tache: listeTacheAffecte){
                if(tache.dateFin==temps)
                    tache.etat=2;
            }
        }
    }
}

