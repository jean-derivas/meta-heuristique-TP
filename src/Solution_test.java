import java.util.ArrayList;

public class Solution_test {

    public ArrayList<Integer> OS ;
    public ArrayList<Integer> MA ;
    public int temps;

    public Solution_test(ArrayList<Integer> OS, ArrayList<Integer> MA) {
        this.OS = OS;
        this.MA = MA;
    }

    public Solution_test() {
        this.OS = new ArrayList<>();
        this.MA = new ArrayList<>();
    }


    /**
     * Genere la solution intelligente (void a changer)
     */
    public static Solution_test genererSolution1(InfoParse info){
        int i, j, size;
        Solution_test solution = new Solution_test();
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

            System.out.println("Temps:"+ temps);
            System.out.println("Successeurs"+successeur);
            System.out.println("Affectes"+listeTacheAffecte);
            // Etat des machines
            System.out.println(listeMachine);
            System.out.println("-------------------------");

            try {
                Thread.sleep(100) ;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

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
                    //System.out.println("on est dans la boucle du successeur");
                    int numJob = tache.getNumJob();
                    int numTache = tache.getNumeroTache();
                    // si pas dans les noeuds affectés et tache précédente finie
                    if (tache.getNumeroTache()==0 || (tache.etat == 0 && info.jobs.get(numJob).lesTaches.get(numTache-1).etat==2)) {
                        // on récupère l'index de la première machine dispo pour la tache
                        int machine = tache.MachineDispo(listeMachine);
                        //System.out.println("Tache: "+tache);
                        System.out.print("Tache: "+tache);
                        // on n'affecte que dans le cas où on a bel et bien une machine dispo pour la tache
                        if(machine!=-1){
                            Machine m = listeMachine.get(tache.coupleMachineCout.get(machine).numeroMachine-1);
                            System.out.println("Num machine: "+m.getNumeroMachine());
                            // on affecte la tache à la machine, et on la rend indisponible
                            System.out.println("Machine "+m.getNumeroMachine()+" affectee!");
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
                //System.out.println("Liste tache affectees :"+listeTacheAffecte);
                //System.out.println("Size list: "+listeTacheAffecte.size());
                Tache tache = listeTacheAffecte.get(i);
                //System.out.println("Tache: "+tache);
                if(tache.dateFin==temps) {
                    tache.etat = 2;
                    for (Machine m : listeMachine) {
                        //System.out.println("Machine: "+m);
                        if (m.numTache == tache.getNumeroTache() && m.numJob == tache.getNumJob()) {
                            m.disponible = true;
                            m.numJob=-1;
                            m.numTache=-1;
                            System.out.println("Machine "+m.getNumeroMachine()+" liberee!");
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

    @Override
    public String toString() {
        String OS = "Liste OS: " + this.OS + "\n";
        String MA = "Liste MA: " + this.MA + "\n";
        String temps = "Temps: " + this.temps + "\n";
        return OS+MA+temps;
    }

    public int genererTemps(ArrayList OS, ArrayList MA){
        int temps = 0 ;
        /*while(!termine){
            If(une machine est disponible && qu’elle peut réaliser une tache d’un job)
            Affecter machine au job
            temps ++
        }*/
        return temps ;
    }

    public static void main(String[] args) {
        InfoParse parse = Parser.toParse("dataset1.txt");
        Solution_test solution = genererSolution1(parse);
        System.out.println(solution);
    }
}

