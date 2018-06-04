
import java.util.*;

/** Classe qui contient les métaheuristiques par voisinage pour résoudre le problème
 * Elle contient aussi le main avec lequel on effectue les tests de performance
 */
public class Metaheuristique {


    /*************************************
     * HEURISTIQUE NUMERO 1: DETERMINISTE
     *************************************/

    /**Cette méthode va permuter les éléments situés à indice1 et indice 2 (permutation tâches)
     * Elle print des erreurs (indice négatif ou trop grand) mais ne throw pas et ne bloque pas
     * En cas de bug elle renverra la liste en entrée
     * @param liste
     * @param indice1
     * @param indice2
     * @return la liste avec la permutation effectuée
     */
    public static ArrayList permuterElementListe(ArrayList<Integer> liste, int indice1, int indice2){
        ArrayList<Integer> temp = new ArrayList<>(liste) ;
        // cas d'erreurs
        if(indice1<0||indice2<0){
            System.err.println("Indice négatif");
        }
        else if(indice1>liste.size()-1 || indice2>liste.size()-1) {
            System.err.println("indice trop grand");
        }

        // cas classique
        else {
            int save = (int) temp.get(indice1);
            temp.set(indice1, temp.get(indice2));
            temp.set(indice2, save);
        }
        return temp ;
    }

    /** Cette méthode génère toutes les permutations possibles à partir d'une liste d'OS
     * Elle fait pour cela appel à permuterElementListe, qui effectue les permutations unitaires
     * @param liste est une sous liste d'OS
     * @return une liste contenant toutes les listes permutées
     */
    public static ArrayList genererPermutationsListe(ArrayList<Integer> liste){
        ArrayList<ArrayList<Integer>> resultat = new ArrayList<>() ;
        resultat.add(liste);
        int listeSize = liste.size() ;
        for (int i = 0; i<listeSize ; i++) {
            for(int j=i+1 ; j<listeSize ; j++){
                resultat.add(permuterElementListe(liste,i,j));
            }
        }
        return resultat ;
    }

    /** Méthode qui découpe la liste en sous listes de nombres tous différents
     * L'algorithme parcourt la liste et dès qu'on trouve un nombre pour la deuxième fois on coupe et on recommence
     * @param liste est une liste d'OS
     * @return la liste contenant les sous listes
     */
    public static ArrayList decouperListe(ArrayList liste){
        ArrayList<ArrayList<Integer>> resultat = new ArrayList<>() ;
        ArrayList<Integer> temp = new ArrayList<>() ;
        int listeSize = liste.size() ;
        for(int i=0 ; i<listeSize ; i++){
            int val = (int) liste.get(i) ;
            // si dans temp il n'y a pas déjà une valeur
            if (!temp.contains(val)){
                temp.add(val);
            }
            else {
                resultat.add(temp);
                temp = new ArrayList<>() ;
                temp.add(val);
            }
        }
        resultat.add(temp);
        return resultat ; // Retourne arraylist de arraylist d'entier
    }

    /**Méthode qui permet de récupérer l'indice de la tâche d'un job donné, dans le vecteur MA
     *
     * @param MA : le vecteur qui contient la liste des machines attribuées, ordonnées par job puis par tâche
     * @param parse : contient les valeurs du problème initial (le rendre statique?)
     * @param numJob : numéro du job
     * @param numTache : numéro de la tâche du job
     * @return l'indice de la tâche de ce job
     */
    public static int getIndiceMachineMA(ArrayList<Integer> MA, InfoParse parse, int numJob, int numTache){
        int indiceMachine = 0;
        for(int i=0;i<numJob;i++){
            indiceMachine+=parse.jobs.get(i).lesTaches.size();
        }
        indiceMachine+=numTache;
        return indiceMachine;
    }

    /** Méthode qui change la machine attribuée à une tâche d'un job dans MA (permutation machine)
     *
     * @param MA : vecteur qui contient la liste des machines attribuées
     * @param parse : contient les valeurs du problème initial
     * @param numTache : numéro de la tâche sur laquelle la permutation est effectuée
     * @return liste des MA possibles pour une tache donnée
     */
    public static ArrayList permuterMachine(ArrayList<Integer> MA, InfoParse parse, int numTache, int numJob){
        int numMachine, numTemp, i, nbMachines;
        // liste à retourner
        ArrayList<ArrayList<Integer>> temp = new ArrayList<>();
        // on récupère le numéro de machine pour la tâche, dans MA
        numMachine = MA.get(getIndiceMachineMA(MA, parse, numJob, numTache));
        // on détermine le nombre de machines possibles pour la tâche
        nbMachines = parse.jobs.get(numJob).lesTaches.get(numTache).coupleMachineCout.size();
        for(i=0; i<nbMachines;i++){
            // on récupère le numéro de la machine
            numTemp=parse.jobs.get(numJob).lesTaches.get(numTache).coupleMachineCout.get(i).numeroMachine;
            // si ce n'est pas le numéro actuel, on permute et on ajoute le ma permuté à temp
            if(numMachine!=numTemp){
                ArrayList<Integer> maTemp = new ArrayList<>(MA);
                maTemp.set(getIndiceMachineMA(MA, parse, numJob, numTache), numTemp);
                temp.add(maTemp);
            }
        }
        return temp;
    }

    /** Méthode qui génère toutes les permutations possibles pour les machines d'une sous liste de tâches
     *
     * @param os : la sous-liste des jobs pour lesquels on permute
     * @param ma : la liste des machines attribuées
     * @param tab : la liste du numéro de tâche pour chaque job
     * @param parse : contient les valeurs du problème initial
     * @return la liste de tous les ma possibles
     */
    public static ArrayList<ArrayList<Integer>> genererPermutationsMachines(ArrayList<Integer> os, ArrayList<Integer> ma,
                                                                            Integer[] tab, InfoParse parse){
        ArrayList<ArrayList<Integer>> temp = new ArrayList<>();
        temp.add(ma);
        for(int job: os){
            // on ajoute à la liste toutes les permutations possibles pour le job (ma modifiés)
            ArrayList<ArrayList<Integer>> listePermutes = permuterMachine(ma, parse, tab[job],job);
            if (listePermutes!=null) {
                temp.addAll(permuterMachine(ma, parse, tab[job],job));
            }
        }
        return temp;
    }

    /** Métaheuristique qui génère le voisinage en effectuant des permutations unitaires de tâches et de machines
     * Elle est déterministe, cad qu'elle génère toutes les combinaisons de permutations unitaires possibles, et choisit la meilleure
     * Elle génère un voisinage en effectuant les permutations sur une sous liste de OS
     * @param parse : les données du problème
     * @return la meilleure solution trouvée par la métaheuristique
     */
    public static Solution heuristiqueMA(InfoParse parse){
        int i, j, size, meilleur;
        meilleur=0;
        // tableau qui conserve le num de la dernière tâche en cours pour chaque job
        // il est initialisé à 0
        Integer tabTaches[] = new Integer[parse.jobs.size()];
        for(i=0;i<parse.jobs.size();i++){
            tabTaches[i]=0;
        }
        // on génère la solution initiale, puis on la découpe en sous listes
        Solution solution = Solution.genererSolution(parse);
        ArrayList<ArrayList<Integer>> os = decouperListe(solution.OS);
        // on garde en mémoire la liste des machines sur laquelle on évalue
        ArrayList<Integer> ma = new ArrayList<>(solution.MA);
        // on effectue un traitement pour chaque sous liste
        size=os.size();
        for(i=0;i<size;i++){
            // on génère toutes les permutations de tâches possibles
            ArrayList<ArrayList<Integer>> permutationsTaches = genererPermutationsListe(os.get(i));
            // on génère toutes les permutations de machines possibles
            ArrayList<ArrayList<Integer>> permutationsMachines = genererPermutationsMachines(os.get(i),ma,tabTaches,parse);
            // on détermine la meilleure liste avec permutations de la sous liste testée
            meilleur = Integer.MAX_VALUE;
            ArrayList<Integer> listeMeilleure = new ArrayList<>();
            ArrayList<Integer> listeMachinesMeilleure = new ArrayList<>();
            // on parcourt toutes les listes de permutations de taches
            for(ArrayList<Integer> liste: permutationsTaches){
                // temp permet de reconstituer un os avec la sous liste permutée
                ArrayList<Integer> temp = new ArrayList<>();
                for(j=0;j<size;j++) {
                    if (i == j) {
                        temp.addAll(liste);
                    } else {
                        temp.addAll(os.get(j));
                    }
                }
                // on détermine le meilleur pour chaque permutation de machines
                for (ArrayList<Integer> listemachine: permutationsMachines){
                    int cout=Solution.genererTemps(parse, temp, listemachine);
                    if(cout<meilleur){
                        meilleur=cout;
                        listeMeilleure = temp;
                        listeMachinesMeilleure = listemachine;
                    }
                }
            }
            // on continue à évaluer avec les listes modifiées
            os=decouperListe(listeMeilleure);
            ma=listeMachinesMeilleure;
            // on met à jour le tableau des tâches
            for(int job: os.get(i)){
                tabTaches[job]++;
            }
        }
        ArrayList<Integer> temp = new ArrayList<>();
        for(ArrayList<Integer> liste: os){
            temp.addAll(liste);
        }
        solution.OS = temp;
        solution.temps=meilleur;
        solution.MA=ma;
        return solution;
    }


    /**************************************
     * HEURISTIQUE NUMERO 2: STOCHASTIQUE
     **************************************/


    /**
     * Genere un nombre aleatoire entre minimum et maximum
     * @param minimum
     * @param maximum
     * @return un nombre aléatoire
     */
    public static Integer aleatoire(int minimum, int maximum){
        return minimum + (int)(Math.random() * ((maximum - minimum) + 1)) ;
    }

    /**
     * Permute un element d'une liste à "indiceAPermuter" avec un autre élément au hasard entre indiceMin et indiceMax
     * @param liste
     * @param indiceAPermuter
     * @param indiceMin
     * @param indiceMax
     * @return la liste modifiée
     */
    public static ArrayList genererPermutationAleatoire(ArrayList<Integer> liste,int indiceAPermuter, int indiceMin, int indiceMax){

        ArrayList<Integer> zeliste = (ArrayList<Integer>) liste.clone();
        int size = zeliste.size();

        if(indiceMin>indiceMax) {
            System.err.println("Indice minimum (" + indiceMin + ") plus grand que l'indice maximum (" + indiceMax + ")");

        }
        else if(indiceMin<0 || indiceMin>size-1|| indiceAPermuter <0 || indiceAPermuter >size-1 || indiceMax<0 || indiceMax>size-1 ){
            System.err.println("problème d'indice");
        }
        else {

            int aleatoire = aleatoire(indiceMin, indiceMax);
            zeliste = permuterElementListe(zeliste, indiceAPermuter, aleatoire);
        }
        return zeliste;
    }


    /**
     * Retourne un arrayList d'integer contenant l'indice précédent l'indiceAPermuter ayant meme valeur que la valeur
     * à l'indiceAPermuter et pareil pour l'indice suivant.
     * Exemple [1,2,4,5,6,4,6,4,1,2]
     * SI l'indice à permuter est 5 (donc valeur4) l'arrayList retourné sera [2,7]
     * @param liste
     * @param indiceAPermuter
     * @return
     */
    public static ArrayList<Integer> trouverElementIdentiqueVoisin(ArrayList<Integer> liste, int indiceAPermuter){
        ArrayList<Integer> zeliste = (ArrayList<Integer>) liste.clone();
        int valeurElement = zeliste.get(indiceAPermuter) ;
        int indice1 = indiceAPermuter, indice2 = indiceAPermuter ;
        ListIterator<Integer> it1 = zeliste.listIterator(indiceAPermuter+1);
        ListIterator<Integer> it2 = zeliste.listIterator(indiceAPermuter);
        boolean termine1=false, termine2=false ;

        while (it1.hasNext() && !termine1) {
            if(it1.next()==valeurElement){
                   termine1=true ;
            }
            indice1++ ;
        }

        while (it2.hasPrevious() && !termine2) {
            if(it2.previous()==valeurElement){
                termine2=true ;
            }
            indice2--;
        }
        ArrayList<Integer> resultat = new ArrayList<>();
        resultat.add(indice2) ;
        resultat.add(indice1) ;

        return resultat ;
    }

    /**
     * renvoie une permutation au hasard dans les contraintes de l'heuristique précédente
     * @param liste
     * @return
     */
    public static ArrayList<Integer> heuristiqueAleatoireUnitaireOS(ArrayList<Integer> liste){
        ArrayList<Integer> zeliste = (ArrayList<Integer>) liste.clone();
        int size = zeliste.size() ;
        int aleatoire = aleatoire(0,size-1);
        ArrayList<Integer> bornes = trouverElementIdentiqueVoisin(zeliste,aleatoire);
        ArrayList<Integer> resultat = genererPermutationAleatoire(zeliste,aleatoire,bornes.get(0),bornes.get(1));
        return resultat ;
    }


    /** Métaheuristique qui génère le voisinage en effectuant des permutations aléatoires sur la liste d'OS
     *
     * @param listeOS
     * @param listeMA
     * @param parse : données du problème
     * @return la meilleure liste d'OS rencontrée
     */
    public static ArrayList<Integer> heuristiqueAleatoireOS(ArrayList<Integer> listeOS, ArrayList<Integer> listeMA, InfoParse parse){

        // clonage liste
        ArrayList<Integer> zeliste = (ArrayList<Integer>) listeOS.clone();

        //
        int scoreInitiale = Solution.genererTemps(parse,zeliste,listeMA);
        int scoreTemp = scoreInitiale ;

        // partie temps
        long tempsAttenteMax = 45*1000;
        long dateDebut = System.currentTimeMillis() ;
        long dateFin ;
        do{
            ArrayList<Integer> templist = heuristiqueAleatoireUnitaireOS(zeliste);
            int score = Solution.genererTemps(parse,templist,listeMA);
            if (score <= scoreTemp){
                zeliste= (ArrayList<Integer>) templist.clone();
                scoreTemp = score ;
            }
            if (score < scoreTemp){
                zeliste= (ArrayList<Integer>) templist.clone();
                scoreTemp = score ;
                dateDebut = System.currentTimeMillis() ;
            }

            dateFin = System.currentTimeMillis() ;
        } while (dateFin-dateDebut<tempsAttenteMax) ;

        System.out.println("Score init : " + scoreInitiale);
        System.out.println("Score final : " + scoreTemp);
        return zeliste ;
    }








    public static void main(String[] args) {
        /** Execution de la metaheuristique H2**/
        System.out.println("Brandimarte");

        System.out.println("Mk01");
        for (int i = 0; i <5 ; i++) {
            InfoParse parse = Parser.toParse("dataset/Brandimarte_Data/Mk01.fjs");
            Solution solutionGenerique = Solution.genererSolution(parse);
            heuristiqueAleatoireOS(solutionGenerique.OS,solutionGenerique.MA,parse);
        }

        System.out.println("Mk09");
        for (int i = 0; i <5 ; i++) {
            InfoParse parse = Parser.toParse("dataset/Brandimarte_Data/Mk09.fjs");
            Solution solutionGenerique = Solution.genererSolution(parse);
            heuristiqueAleatoireOS(solutionGenerique.OS,solutionGenerique.MA,parse);
        }

        System.out.println("*******************");


        System.out.println("Dauzere");
        System.out.println("01a");
        for (int i = 0; i <5 ; i++) {
            InfoParse parse = Parser.toParse("dataset/Dauzere_Data/01a.fjs");
            Solution solutionGenerique = Solution.genererSolution(parse);
            heuristiqueAleatoireOS(solutionGenerique.OS,solutionGenerique.MA,parse);
        }

        System.out.println("08a");
        for (int i = 0; i <5 ; i++) {
            InfoParse parse = Parser.toParse("dataset/Dauzere_Data/08a.fjs");
            Solution solutionGenerique = Solution.genererSolution(parse);
            heuristiqueAleatoireOS(solutionGenerique.OS,solutionGenerique.MA,parse);
        }

        System.out.println("14a");
        for (int i = 0; i <5 ; i++) {
            InfoParse parse = Parser.toParse("dataset/Dauzere_Data/14a.fjs");
            Solution solutionGenerique = Solution.genererSolution(parse);
            heuristiqueAleatoireOS(solutionGenerique.OS,solutionGenerique.MA,parse);
        }

        System.out.println("*******************");

        /** Execution de la metaheuristique H1 **/
        /*
        long startTime, endTime;

        InfoParse parse = Parser.toParse("dataset/Barnes/seti5cc.fjs");
        startTime = System.currentTimeMillis();
        Solution solutionGenerique = Solution.genererSolution(parse);
        endTime = System.currentTimeMillis();
        System.out.println("Solution générée de manière simple en un temps de: " + (endTime-startTime));
        System.out.println(solutionGenerique);

        startTime = System.currentTimeMillis();
        Solution solutionMachine = heuristiqueMA(parse);
        endTime = System.currentTimeMillis();
        System.out.println("Solution améliorée par permutations de taches et machines en un temps de: " + (endTime-startTime));
        System.out.println(solutionMachine);

        Solution solution = Solution.genererSolution(parse);
        startTime = System.currentTimeMillis();
        solution.OS = heuristiqueAleatoireOS(solution.OS, solution.MA, parse);
        endTime = System.currentTimeMillis();
        System.out.println("Solution améliorée random en un temps de: " + (endTime-startTime));
        System.out.println(solution);
        */

    }
}