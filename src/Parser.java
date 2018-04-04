import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Parser {


    public static void main(String[] args) {
        try{
            InputStream flux=new FileInputStream("test.txt");
            InputStreamReader lecture=new InputStreamReader(flux);
            BufferedReader buff=new BufferedReader(lecture);
            String ligne;
            while ((ligne=buff.readLine())!=null){
                System.out.println(ligne);
            }
            buff.close();
        }
        catch (Exception e){


        }

    }

}
