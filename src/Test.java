import java.util.ArrayList;

public class Test {

    public static void main(String[] args) {

        ArrayList<Integer> sol = new ArrayList<>();
        ArrayList<ArrayList<Integer>> temp = new ArrayList<>();
        for(int i=0; i<3; i++){
            ArrayList<Integer> a = new ArrayList<>();
            temp.add(a);
        }

        temp.get(0).add(1);
        temp.get(0).add(2);
        temp.get(0).add(3);
        temp.get(2).add(4);
        temp.get(2).add(5);
        temp.get(2).add(6);
        temp.get(1).add(7);
        temp.get(1).add(8);
        temp.get(1).add(9);

        System.out.println(temp);
        for(ArrayList<Integer> list : temp)
            sol.addAll(list);
        System.out.println(sol);
    }

}
