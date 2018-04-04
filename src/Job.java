import java.util.ArrayList;
import java.util.List;

public class Job {
    public ArrayList<Tache> lesTaches ;

    public Job(ArrayList<Tache> lesTaches) {
        this.lesTaches = lesTaches;
    }

    public Job() {
        this.lesTaches = new ArrayList<>();
    }


    public void addTache(Tache tache){
        this.lesTaches.add(tache);
    }

    @Override
    public String toString() {
        return("Taches du job: "+lesTaches);
    }
}
