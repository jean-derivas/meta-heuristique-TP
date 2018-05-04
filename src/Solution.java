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


        



        while(!termine){
            for (Tache tache: listeTache) {

            }
        }
    }
}
