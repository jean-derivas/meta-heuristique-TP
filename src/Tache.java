import java.util.ArrayList;
import java.util.List;

public class Tache {
    /* numero de la tache dans le job */
    int numero ;


    public ArrayList<MachineCout> coupleMachineCout;

    /**
     * Constructeur qui contient un numero de tache et un couple machine cout
     * @param numero
     * @param coupleMachineCout
     */
    public Tache(int numero, ArrayList<MachineCout> coupleMachineCout) {
        this.numero = numero;
        this.coupleMachineCout = coupleMachineCout;
    }

    /**
     * Constructeur qui contient qu'un numéro de tache. La liste des Machine cout sera donné plus tard avec addCouple
     * @param numero
     */
    public Tache(int numero) {
        this.numero = numero;
        this.coupleMachineCout = new ArrayList<>();
    }


    /**
     * Ajoute un couple machine cout à la liste de la tache
     * @param machineCout
     */
    public void addCouple(MachineCout machineCout){
        this.coupleMachineCout.add (machineCout);
    }


    @Override
    public String toString() {
        return ("\nTache numero : "+ this.numero +", Liste machine cout :" + this.coupleMachineCout);
    }



    public static void main(String[] args) {

    }
}
