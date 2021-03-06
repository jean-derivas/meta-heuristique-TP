
import java.util.ArrayList;
import java.util.List;

/** Classe qui contient la représentation de la solution choisie
 * Elle contient notamment le vecteur OS (ordre des jobs), le vecteur MA (affectation machines), et le coût en temps
 * Elle contient également les fonctions permettant de générer une solution de départ (heuristique gloutonne),
 * et une fonction permettant de calculer le temps à partir des vecteurs OS et MA
 */
public class Solution {

    public ArrayList<Integer> OS ;
    public ArrayList<Integer> MA ;
    public int temps;

    public Solution() {
        this.OS = new ArrayList<>();
        this.MA = new ArrayList<>();
    }

    /** Heuristique gloutonne qui génère la solution de départ pour les métaheuristiques
     * Elle attribue à chaque tâche la première machine disponible
     * Elle construit les vecteurs MA et OS au fur et à mesure
     * Elle utilise un compteur de temps pour déterminer lorsqu'une tâche est terminée
     * @param info : les données du problème
     * @return la solution de départ
     */
    public static Solution genererSolution(InfoParse info){
        info.reinitialiserParse();
        int i, j, size;
        Solution solution = new Solution();
        ArrayList<ArrayList<Integer>> temp = new ArrayList<>();
        for(i=0; i<info.jobs.size(); i++){
            ArrayList<Integer> a = new ArrayList<>();
            temp.add(a);
        }
        // modélise le temps
        int temps = 0 ;

        // condition de terminaison
        boolean termine = false ;

        // liste des machines à mettre à jour à chaque affectation
        ArrayList<Machine> listeMachine = new ArrayList<>();

        // crée autant de machines qu'il en existe
        for (i = 0; i < info.nbMachine ; i++) {
            listeMachine.add(new Machine(i+1));
        }

        ArrayList<Tache> listeTacheAffecte = new ArrayList<>();

        ArrayList<Tache> successeur = new ArrayList<>() ;
        for(Job job : info.jobs){
            successeur.add(job.lesTaches.get(0));
        }

        while(!termine) {

            // on détermine si une machine est dispo
            boolean machinedispo=false;
            i=0;
            while(!machinedispo && i<listeMachine.size()){
                machinedispo = listeMachine.get(i).disponible;
                i++;
            }
            // si une machine est dispo on essaie de l'affecter
            if (machinedispo) {
                // i est ici l'index de la tache à laquelle on accede
                // j est notre compteur pour parcourir successeur independamment des modifications
                i=0;
                size = successeur.size();
                // pour chaque tache dans successeur
                for (j=0;j<size;j++) {
                    Tache tache = successeur.get(i);
                    int numJob = tache.getNumJob();
                    int numTache = tache.getNumeroTache();
                    // si pas dans les noeuds affectés et tache précédente finie
                    if (tache.getNumeroTache()==0 || (tache.etat == 0 && info.jobs.get(numJob).lesTaches.get(numTache-1).etat==2)) {
                        // on récupère l'index de la première machine dispo pour la tache
                        int machine = tache.MachineDispo(listeMachine);
                        // on n'affecte que dans le cas où on a bel et bien une machine dispo pour la tache
                        if(machine!=-1){
                            Machine m = listeMachine.get(tache.coupleMachineCout.get(machine).numeroMachine-1);
                            // on affecte la tache à la machine, et on la rend indisponible
                            m.disponible=false;
                            m.numTache=tache.getNumeroTache();
                            m.numJob=tache.getNumJob();
                            // on affecte la tache actuelle
                            tache.etat=1;
                            // on ajoute le numéro de job dans le vecteur OS
                            // et le numéro de machine dans le vecteur MA
                            solution.OS.add(numJob);
                            temp.get(numJob).add(m.getNumeroMachine());
                            // on initialise la date de fin de la tache avec duree tache+temps
                            tache.dateFin=tache.coupleMachineCout.get(machine).coutMachine+temps;
                            // on supprime la tache de la liste des successeurs
                            successeur.remove(tache);
                            // le prochain élément est désormais à la place de la tache que l'on vient de supprimer
                            // on retire 1 à l'indice pour s'assurer que quand il sera incrémenté, il reste à l'état actuel
                            i--;
                            // on l'ajoute dans la liste des taches affectées
                            listeTacheAffecte.add(tache);
                            // on ajoute le successeur de cette tache à la liste des successeurs
                            // ssi ce n'est pas la derniere tache du job
                            if(numTache <info.jobs.get(numJob).lesTaches.size()-1) {
                                successeur.add(info.jobs.get(numJob).lesTaches.get(numTache + 1));
                            }
                        }
                    }
                    i++;
                }
            }
            temps++;

            // on détermine si des taches en cours sont terminees, et si c'est le cas, on les supprime

            i=0;
            size = listeTacheAffecte.size();
            for (j=0;j<size;j++) {
                Tache tache = listeTacheAffecte.get(i);
                if(tache.dateFin==temps) {
                    tache.etat = 2;
                    for (Machine m : listeMachine) {
                        if (m.numTache == tache.getNumeroTache() && m.numJob == tache.getNumJob()) {
                            m.disponible = true;
                            m.numJob=-1;
                            m.numTache=-1;
                        }
                    }
                    listeTacheAffecte.remove(tache);
                    i--;
                }
                i++;
            }

            if(successeur.isEmpty()&& listeTacheAffecte.isEmpty()){
                termine = true;
            }
        }
        for(ArrayList<Integer> list : temp) {
            solution.MA.addAll(list);
        }
        solution.temps=temps;
        return solution;
    }

    /**Méthode qui génère le temps d'une solution à partir de ses vecteurs OS et MA
     * Elle utilise des mécanismes très similaires à ceux de l'heuristique gloutonne
     * Elle attribue à chaque tâche la machine correspondante dans le vecteur MA
     * Elle utilise un compteur de temps pour déterminer lorsqu'une tâche est terminée, et évaluer le coût en temps
     * @param info: données du problème
     * @param OS
     * @param MA
     * @return le coût en temps de la solution
     */
    public static int genererTemps(InfoParse info, ArrayList<Integer> OS, ArrayList<Integer> MA){
        info.reinitialiserParse();
        // i, j et size sont des compteurs
        // numOS est un indice de parcours de OS
        // numMA est un indice de parcours de MA
        int i, j, size, numOS, numMA;

        // indicesJob contient le numéro de la prochaine tâche à faire pour chaque job
        int indicesJob[] = new int[info.jobs.size()];
        for (i=0;i<info.jobs.size();i++){
            indicesJob[i]=0;
        }

        // temp : MA fractionné par job
        numMA=0;
        ArrayList<List<Integer>> temp = new ArrayList<>();
        for(i=0; i<info.jobs.size(); i++){
            List<Integer> a =  MA.subList(numMA, numMA+info.jobs.get(i).lesTaches.size());
            numMA+=info.jobs.get(i).lesTaches.size();
            temp.add(a);
        }

        // modélise le temps
        int temps = 0 ;

        // condition de terminaison
        boolean termine = false ;
        // condition d'incrémentation du temps
        boolean bloque;

        // liste des machines à mettre à jour à chaque affectation
        ArrayList<Machine> listeMachine = new ArrayList<>();
        ArrayList<Tache> listeTacheAffecte = new ArrayList<>();

        // crée autant de machines qu'il en existe
        for (i = 0; i < info.nbMachine ; i++) {
            listeMachine.add(new Machine(i+1));
        }

        numOS=0;
        while(!termine) {
            bloque = false;

            while(!bloque && numOS<OS.size()) {
                // on détermine si une machine est dispo
                boolean machinedispo = false;
                i = 0;
                while (!machinedispo && i < listeMachine.size()) {
                    machinedispo = listeMachine.get(i).disponible;
                    i++;
                }
                // si une machine est dispo on essaie de l'affecter
                if (machinedispo) {
                    // on récupère le job dans la liste des OS
                    Job job = info.jobs.get(OS.get(numOS));
                    // on détermine la prochaine tache à réaliser dans ce job
                    int numtache = indicesJob[job.getNumJob()];
                    // si on doit effectuer la première tache, on continue
                    // sinon, on s'assure que la tache précédente soit finie (etat=2)
                    if (numtache == 0 || job.lesTaches.get(numtache - 1).etat == 2) {
                        int numMachine = temp.get(job.getNumJob()).get(numtache);
                        if (listeMachine.get(numMachine-1).disponible) {
                            // on affecte la tache et on change son état et sa date de fin
                            Tache tache = job.lesTaches.get(numtache);
                            tache.etat = 1;
                            int indicemachine = tache.indiceMachine(numMachine);
                            tache.dateFin = tache.coupleMachineCout.get(indicemachine).coutMachine + temps;
                            listeTacheAffecte.add(tache);
                            //on modifie l'état de la machine en l'affectant à la tâche et en la rendant indisponible
                            listeMachine.get(numMachine-1).disponible = false;
                            listeMachine.get(numMachine-1).numTache = tache.getNumeroTache();
                            listeMachine.get(numMachine-1).numJob = tache.getNumJob();
                            // on incrémente l'indice de tache pour le job en cours
                            indicesJob[job.getNumJob()]++;
                            // on incrémente l'indice de OS
                            numOS++;
                        }
                        // on est bloqués si la machine de la tâche n'est pas disponible
                        else{
                            bloque = true;
                        }
                    }
                    // on est bloqués si la tâche précédente n'est pas terminée
                    else{
                        bloque = true;
                    }
                }
                // on est bloqués si aucune machine n'est disponible
                else{
                    bloque = true;
                }
            }
            temps++;

            // on détermine si des taches en cours sont terminees, et si c'est le cas, on les supprime

            i=0;
            size = listeTacheAffecte.size();
            for (j=0;j<size;j++) {
                Tache tache = listeTacheAffecte.get(i);
                if(tache.dateFin==temps) {
                    tache.etat = 2;
                    for (Machine m : listeMachine) {
                        if (m.numTache == tache.getNumeroTache() && m.numJob == tache.getNumJob()) {
                            m.disponible = true;
                            m.numJob=-1;
                            m.numTache=-1;
                        }
                    }
                    listeTacheAffecte.remove(tache);
                    i--;
                }
                i++;
            }

            // on considere que le programme est terminé quand on a fini de parcourir OS
            if(numOS>=OS.size() && listeTacheAffecte.isEmpty()){
                termine = true;
            }

        }
        return temps;
    }

    @Override
    public String toString() {
        String OS = "Liste OS: " + this.OS + "\n";
        String MA = "Liste MA: " + this.MA + "\n";
        String temps = "Temps: " + this.temps + "\n";
        return OS+MA+temps;
    }

    public static void main(String[] args) {
        InfoParse parse = Parser.toParse("dataset1.txt");
        Solution solution = Solution.genererSolution(parse);
        int temps = genererTemps(parse, solution.OS, solution.MA);
        System.out.println(solution);
        System.out.println(temps);
    }
}
