import java.util.List;

public class Job {
    public List<Tache> lesTaches ;

    public Job(List<Tache> lesTaches) {
        this.lesTaches = lesTaches;
    }

    public Job() {
        this.lesTaches = null;
    }


    public void addTache(){

    }

    @Override
    public String toString() {
        return(lesTaches.toString());
    }
}
