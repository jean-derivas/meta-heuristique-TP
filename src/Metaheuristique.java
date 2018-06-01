import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Metaheuristique {


    /**
     * permuterElementListe va permuter les éléments situés à indice1 et indice 2
     * Elle print des erreurs (indice négatif ou trop grand) mais ne throw pas et ne bloque pas
     * En cas de bug elle renverra la liste en entrée
     * @param liste
     * @param indice1
     * @param indice2
     * @return
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

    // methode qui va découper la liste en petits groupes de nombres qui ne se répètent pas comme ça
    // on peut faire les permutations qu'on veut à l'intérieur

    /**
     * méthode qui découpe la liste en petits groupes de liste avec des nombres qui ne se répètent pas (pour faciliter permutations=
     * l'algorithme parcourt la liste (OS derriere la tete), des qu'on trouve un nombre pour la deuxième fois on coupe et on recommence
     * /!\AU final c'est ptetre pas utile de découper mais peut etre plutot de noter l'indice de fin de chaque sous liste pour faire des
     * permutations localement directement sur la grosse liste
     * @param liste
     * @return
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


    // fonction qui decoupe Liste, détermine meilleure liste en modifiant une sous liste, puis modifie sous liste suivante
    // avec nouvelle liste
    public static Solution heuristique(InfoParse parse){
        int i, j, size, meilleur;
        meilleur=0;
        // on génère la solution initiale, puis on la découpe en sous listes
        Solution solution = Solution.genererSolution1(parse);
        ArrayList<ArrayList<Integer>> os = decouperListe(solution.OS);
        // on effectue un traitement pour chaque sous liste
        size=os.size();
        for(i=0;i<size;i++){
            ArrayList<ArrayList<Integer>> permutations = genererPermutationsListe(os.get(i));
            // on détermine la meilleure liste avec permutations de la sous liste testée
            meilleur = Integer.MAX_VALUE;
            ArrayList<Integer> listeMeilleure = new ArrayList<>();
            for(ArrayList<Integer> liste: permutations){
                ArrayList<Integer> temp = new ArrayList<>();
                for(j=0;j<size;j++) {
                    if (i == j) {
                        temp.addAll(liste);
                    } else {
                        temp.addAll(os.get(j));
                    }
                }
                int cout=Solution.genererTemps(parse, temp, solution.MA);
                //System.out.println("Cout: "+cout);
                if(cout<meilleur){
                    meilleur=cout;
                    listeMeilleure = temp;
                }
            }
            os=decouperListe(listeMeilleure);
        }
        ArrayList<Integer> temp = new ArrayList<>();
        for(ArrayList<Integer> liste: os){
            temp.addAll(liste);
        }
        solution.OS = temp;
        solution.temps=meilleur;
        return solution;
    }



    public static void main(String[] args) {

        /*Integer array[] = {0, 1, 2, 0, 4, 2, 3, 2, 5, 3, 1, 0, 5, 2, 5, 3, 1, 4, 0, 2, 5, 3, 0, 4, 1, 2, 5, 4, 1, 3, 5, 4, 1, 4, 3, 1,  4 ,2, 1};
        List<Integer> list = Arrays.asList(array);
        ArrayList<Integer> OS = new ArrayList<Integer>(list);
        System.out.println(OS);
        ArrayList<ArrayList<Integer>> resultat = decouperListe(OS);
        System.out.println(resultat);*/

        /*ArrayList<Integer> liste = new ArrayList<>() ;
        liste.add(1);
        liste.add(2);
        liste.add(3);
        liste.add(4);
        ArrayList<ArrayList<Integer>> resultat  ;
        resultat = genererPermutationsListe(liste);
        System.out.println(resultat);*/

        InfoParse parse = Parser.toParse("dataset1.txt");
        Solution solution = heuristique(parse);
        System.out.println(solution);

    }
}