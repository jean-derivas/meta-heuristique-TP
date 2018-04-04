import java.util.List;

public class Job {
    public List<Tache> lesTaches ;

    public Job(List<Tache> lesTaches) {
        this.lesTaches = lesTaches;
    }

    public Job() {
        this.lesTaches = null;
    }


    public void addTache(Tache tache){
        this.lesTaches.add(tache);
    }

    @Override
    public String toString() {
        return("Taches: "+lesTaches);
    }
}
