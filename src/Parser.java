import java.io.*;
import java.util.*;

public class Parser {


    public static ArrayList<Job> toParse(String filename){
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
            for(int i=0;i<nbjobs;i++){
                System.out.println(ligne);
                if ((ligne=buff.readLine())!=null){
                    Job job = new Job() ;
                    StringTokenizer st = new StringTokenizer(ligne,"\t");
                    int nbtache = Integer.parseInt(st.nextToken());
                    for (int j=0;j<nbtache;j++){
                        Tache tache = new Tache(j);
                        int nbmachopj=Integer.parseInt(st.nextToken());
                        for(int k=0;k<nbmachopj;k++){
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
            System.out.println(nbjobs);
            System.out.println(jobs);
            buff.close();
            return jobs ;
        }
        catch (Exception e){
            System.err.println("Erreur dans toParse");
            return null ;
        }
    }

    public static void main(String[] args) {
        toParse("dataset1.txt");
    }


}
