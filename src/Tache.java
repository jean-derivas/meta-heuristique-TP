import java.util.List;

public class Tache {
    int numero ;
    public List<MachineCout> coupleMachineCout;


    public Tache(int numero, List<MachineCout> coupleMachineCout) {
        this.numero = numero;
        this.coupleMachineCout = coupleMachineCout;
    }

    public Tache(int numero) {
        this.numero = numero;
        this.coupleMachineCout = null;
    }



    public void addCouple(MachineCout machineCout){
        this.coupleMachineCout.add(machineCout);
    }


    @Override
    public String toString() {
        return "Tache numero : "+ this.numero +"\nListe machine cout :" + this.coupleMachineCout;
    }



    public static void main(String[] args) {
    }
}
