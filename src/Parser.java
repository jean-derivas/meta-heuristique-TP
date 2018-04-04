import java.io.*;
import java.util.*;

public class Parser {


    public static void main(String[] args) {
        try{
            BufferedReader buff=new BufferedReader(new FileReader("dataset1.txt"));
            String ligne;
            int nbjobs = 0;
            int nbmachines;
            if ((ligne=buff.readLine()) !=null){
                StringTokenizer st = new StringTokenizer(ligne,"\t");
                nbjobs= Integer.parseInt(st.nextToken());
                nbmachines= Integer.parseInt(st.nextToken());
            }
            for(int i=0;i<nbjobs;i++){
                if ((ligne=buff.readLine())!=null){
                    StringTokenizer st = new StringTokenizer(ligne,"\t");
                    System.out.println(ligne);
                }
            }

            buff.close();
        }
        catch (Exception e){


        }

    }

}
