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
            System.out.println(nbjobs+" "+nbmachines);
            Job  jobs[] = new Job[nbjobs];
            for(int i=0;i<nbjobs;i++){
                System.out.println(ligne);
                if ((ligne=buff.readLine())!=null){
                    //jobs[i]= new Job();
                    StringTokenizer st = new StringTokenizer(ligne,"\t");
                    int nbtache = Integer.parseInt(st.nextToken());
                    System.out.println("nbtache: "+nbtache);
                    for (int j=0;j<nbtache;j++){
                        System.out.println("blah "+j);
                        Tache tache = new Tache(j);
                        System.out.println(tache.toString());
                        int nbmachopj=Integer.parseInt(st.nextToken());
                        System.out.println("nb machine op"+j+": "+nbmachopj);
                        for(int k=0;k<nbmachopj;k++){
                            int mach = Integer.parseInt(st.nextToken());
                            int cout = Integer.parseInt(st.nextToken());
                            System.out.println("machine:"+mach+" cout:"+cout);
                            MachineCout machine = new MachineCout(mach, cout);
                            System.out.println(machine);
                            tache.addCouple(machine);
                        }
                        System.out.println("blah "+j);
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
