import java.util.ArrayList;

public class MachineCout {

    public int numeroMachine ;
    public int coutMachine ;

    public MachineCout(int numeroMachine, int coutMachine) {
        this.numeroMachine = numeroMachine;
        this.coutMachine = coutMachine;
    }


    @Override
    public String toString() {
        return("(numMachine,coutMachine)="+"("+numeroMachine+","+coutMachine+")");
    }
}
