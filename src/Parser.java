import java.io.*;
import java.util.*;

/** Classe qui permet de récupérer les données à partir des fichiers de tests
 * Elle contient une unique méthode toParse qui renvoie les données dans un InfoParse
 */
public class Parser {

    /** Méthode qui permet de générer les données à partir d'un fichier passé en paramètre
     * @param filename
     * @return les données
     */
    public static InfoParse toParse(String filename){
        try{
            BufferedReader buff=new BufferedReader(new FileReader(filename));
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
                        job.lesTaches.add(tache);
                    }
                    jobs.add(job);
                }
            }
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
