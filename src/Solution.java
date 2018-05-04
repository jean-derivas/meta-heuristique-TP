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

        ArrayList<Machine> listeMachine = new ArrayList<>();
        int temps = 0 ;
        for (int i = 0; i < info.nbMachine ; i++) {
            listeMachine.add(new Machine(i));
        }
        boolean termine = false ;

        while(!termine){
            for (Tache tache: listeTache) {

            }
        }

    }
}
