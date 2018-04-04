import java.util.ArrayList;
import java.util.List;

public class Tache {
    int numero ;
    public ArrayList<MachineCout> coupleMachineCout;


    public Tache(int numero, ArrayList<MachineCout> coupleMachineCout) {
        this.numero = numero;
        this.coupleMachineCout = coupleMachineCout;
    }

    public Tache(int numero) {
        this.numero = numero;
        this.coupleMachineCout = new ArrayList<>();
    }



    public void addCouple(MachineCout machineCout){
        this.coupleMachineCout.add (machineCout);
    }


    @Override
    public String toString() {
        return ("\nTache numero : "+ this.numero +", Liste machine cout :" + this.coupleMachineCout);
    }



    public static void main(String[] args) {
        Tache zeTache = new Tache(5) ;
        MachineCout couple = new MachineCout(1,10);
        zeTache.addCouple(couple);
        System.out.println(zeTache) ;
    }
}
