import java.io.*;
import java.util.*;

public class Parser {

    public static void main(String[] args) {
        try{
            BufferedReader buff=new BufferedReader(new FileReader("dataset1.txt"));
            String ligne;
            int nbjobs = 0;
            int nbmachines=0;
            if ((ligne=buff.readLine()) !=null){
                StringTokenizer st = new StringTokenizer(ligne,"\t");
                nbjobs= Integer.parseInt(st.nextToken());
                nbmachines= Integer.parseInt(st.nextToken());
            }
            Job  jobs[] = new Job[nbjobs];
            for(int i=0;i<nbjobs;i++){
                System.out.println(ligne);
                if ((ligne=buff.readLine())!=null){
                    jobs[i]= new Job();
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
                        jobs[i].addTache(tache);
                    }
                }
            }
            System.out.println(nbjobs);
            for(int i=0;i<nbjobs;i++) {
                System.out.println(jobs[i]);
            }
            buff.close();

        }
        catch (Exception e){


        }

    }

}
