
/** Classe contenant les couples Machine-Cout des tâches
 *  Elle contient notamment le numéro de la machine et le coût pour effectuer la tâche avec cette machine
 */
public class MachineCout {

    public int numeroMachine ;
    public int coutMachine ;

    /** Constructeur de la classe
     *
     * @param numeroMachine
     * @param coutMachine
     */
    public MachineCout(int numeroMachine, int coutMachine) {
        this.numeroMachine = numeroMachine;
        this.coutMachine = coutMachine;
    }


    @Override
    public String toString() {
        return("(numMachine,coutMachine)="+"("+numeroMachine+","+coutMachine+")");
    }
}
