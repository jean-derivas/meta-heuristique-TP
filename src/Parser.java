import java.io.*;
import java.util.*;

public class Parser {

    /**
     * toParse marche bien même pour plusieurs machines affectables à une tache (en tout cas le printf marche)
     * @param filename
     * @return
     */
    public static InfoParse toParse(String filename){
        try{
            System.out.println("blah");
            BufferedReader buff=new BufferedReader(new FileReader(filename));
            System.out.println("blah");
            String ligne;
            int nbjobs = 0;
            int nbmachines=0;
            if ((ligne=buff.readLine()) !=null){
                StringTokenizer st = new StringTokenizer(ligne,"\t");
                nbjobs= Integer.parseInt(st.nextToken());
                nbmachines= Integer.parseInt(st.nextToken());
            }
            ArrayList<Job> jobs = new ArrayList<>();
            for(int i=0;i<nbjobs;i++) {
                //System.out.println(ligne);
                if ((ligne = buff.readLine()) != null) {
                    Job job = new Job();
                    job.setNumJob(i);
                    StringTokenizer st = new StringTokenizer(ligne, "\t");
                    int nbtache = Integer.parseInt(st.nextToken());
                    for (int j = 0; j < nbtache; j++) {
                        Tache tache = new Tache(j, i);
                        int nbmachopj = Integer.parseInt(st.nextToken());
                        for (int k = 0; k < nbmachopj; k++) {
                            int mach = Integer.parseInt(st.nextToken());
                            int cout = Integer.parseInt(st.nextToken());
                            MachineCout machine = new MachineCout(mach, cout);
                            tache.addCouple(machine);
                        }
                        job.addTache(tache);
                    }
                    jobs.add(job);
                }
            }
            // Affichage en cas de débug
            //System.out.println(nbjobs);
            //System.out.println(jobs);
            buff.close();



            return new InfoParse(jobs, nbmachines);
        }
        catch (Exception e){

            System.err.println("Erreur dans toParse");
            return null ;
        }
    }

    public static void main(String[] args) {
        toParse("dateset2.txt");
    }


}
