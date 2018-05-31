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
    public static ArrayList permuterElementListe(ArrayList liste, int indice1, int indice2){
        // cas d'erreurs
        if(indice1<0||indice2<0){
            System.err.println("Indice négatif");
        }
        else if(indice1>liste.size()-1 || indice2>liste.size()-1) {
            System.err.println("indice trop grand");
        }

        // cas classique
        else {
            int save = (int) liste.get(indice1);
            liste.set(indice1, liste.get(indice2));
            liste.set(indice2, save);
        }
        return liste ;
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
        return resultat ; // Retourne arraylist de arraylist d'entier
    }






    public static void main(String[] args) {



        /*
        Integer array[] = {0, 1, 2, 0, 4, 2, 3, 2, 5, 3, 1, 0, 5, 2, 5, 3, 1, 4, 0, 2, 5, 3, 0, 4, 1, 2, 5, 4, 1, 3, 5, 4, 1, 0, 3, 4} ;
        List<Integer> list = Arrays.asList(array);
        ArrayList<Integer> OS = new ArrayList<Integer>(list);
        System.out.println(OS);
        ArrayList<ArrayList<Integer>> resultat =  decouperListe(OS);
        System.out.println(resultat);*/
    }
}