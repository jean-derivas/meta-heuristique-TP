import java.util.ArrayList;
import java.util.Iterator;

public class Solution {

    public ArrayList<Integer> OS ;
    public ArrayList<Integer> MA ;
    public int temps;

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
    public static Solution genererSolution1(InfoParse info){

        Solution solution = new Solution();
        ArrayList<ArrayList<Integer>> temp = new ArrayList<>();
        for(int i=0; i<info.jobs.size(); i++){
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
            System.out.println("Machine dispo?: "+machinedispo);
            // si une machine est dispo on essaie de l'affecter
            if (machinedispo) {
                ArrayList<Integer> rmList = new ArrayList<>();
                ArrayList<Integer> addList = new ArrayList<>();
                // pour chaque tache dans successeur
                Iterator<Tache> itr = successeur.iterator();
               // for (Tache tache : successeur) {
                  while(itr.hasNext()){
                  Tache tache = itr.next();
                    System.out.println("on est dans la boucle du successeur");
                    int numJob = tache.getNumJob();
                    int numTache = tache.getNumeroTache();
                    // si pas dans les noeuds affectés et tache précédente finie
                    if (tache.getNumeroTache()==0 || (tache.etat == 0 && info.jobs.get(numJob).lesTaches.get(numTache).etat==2)) {
                        System.out.println("ça marche! je crois...");
                        // on récupère l'index de la première machine dispo pour la tache
                        int machine = tache.MachineDispo(listeMachine);
                        System.out.println("Tache: "+tache);
                        System.out.println("Num machine: "+machine);
                        // on n'affecte que dans le cas où on a bel et bien une machine dispo pour la tache
                        if(machine!=-1){
                            Machine m = listeMachine.get(tache.coupleMachineCout.get(machine).numeroMachine);
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
                            // on ajoute l'indice de la tache désormais affectée des successeurs dans la liste des indices à supprimer
                            //successeur.remove(tache);
                            rmList.add(successeur.indexOf(tache));
                            // on l'ajoute dans la liste des taches affectées
                            listeTacheAffecte.add(tache);
                            // on ajoute le successeur de cette tache à la liste des successeurs
                            // ssi ce n'est pas la derniere tache du job
                            if(numTache <info.jobs.get(numJob).lesTaches.size()-1) {
                                addList.add(successeur.indexOf(tache));
                            }
                        }
                    }
                }

                for(int index : addList){
                    Tache tache = successeur.get(index);
                    successeur.add(info.jobs.get(tache.getNumJob()).lesTaches.get(tache.getNumeroTache() + 1));
                }

                for(int index : rmList){
                    successeur.remove(index);
                }
            }
            temps++;
            System.out.println("Temps:"+ temps);
            System.out.println("Successeurs"+successeur);
            System.out.println("Affectes"+listeTacheAffecte);

            ArrayList<Integer> rmList = new ArrayList<>();

            for(Tache tache: listeTacheAffecte){
                if(tache.dateFin==temps) {
                    tache.etat = 2;
                    rmList.add(listeTacheAffecte.indexOf(tache));
                }
            }

            for(Integer index : rmList){
                listeTacheAffecte.remove(index);
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

    public static void main(String[] args) {
        InfoParse parse = Parser.toParse("dataset1.txt");
        Solution solution = genererSolution1(parse);
        System.out.println(solution);
    }
}

