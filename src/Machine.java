
/** Classe contenant la représentation d'une machine et de son état
 *  Elle contient notamment le numéro de la machine, son état de disponibilité,
 *  le numéro de la tâche et du job à laquelle elle est attribuée
 */
public class Machine{

    private int numeroMachine ;
    public boolean disponible ;
    public int numTache ;
    public int numJob ;

    /** Constructeur de la classe
     * A l'origine, une machine est disponible, et n'est affectée à aucune tâche d'aucun job (-1)
     * @param numeroMachine
     */
    public Machine(int numeroMachine) {
        this.numeroMachine = numeroMachine;
        this.disponible = true;
        this.numTache = -1 ; // -1 veut dire qu'elle vient juste d'etre initialisé et n'a jamais servi
        this.numJob = -1 ;

    }

    /** Getter du numéro de la machine (ne doit pas pouvoir être modifié)
     *
     * @return le numéro de la machine
     */
    public int getNumeroMachine() {
        return numeroMachine;
    }

    @Override
    public String toString() {
        return "numeroMachine = " + this.numeroMachine
                + " ; dispo = " + this.disponible
                + " ; numTache = "+numTache
                +" ; numJob = "+numJob+"\n";
    }
}
