import java.util.Arrays;

public class Genomics {
    public static long[] goal;

    public static long optBottomUp(String strang, String[] dictionary)
    {
        // TODO
        goal = new long[strang.length()];

        for(int i=strang.length()-1;i>=0;i--){
            for(int j=0;j<dictionary.length;j++){
                if(dictionary[j].length()==strang.length()-i && strang.startsWith(dictionary[j],i)){
                    goal[i] = 1;
                }
            }
        }
        for(int i=strang.length()-1;i>=0;i--){
            for(int j=0;j<dictionary.length;j++){
                String temp = dictionary[j];
                if(i + dictionary[j].length()<strang.length() && strang.startsWith(temp,i)){
                    goal[i] = goal[i+dictionary[j].length()]+goal[i];
                }
            }
        }
        return goal[0];

    }

    public static void main(String[] args)
    {
        // for testing
        String strang = "CAGTCCAGTCAGTC";
        String[] dictionary = {"AGT","CA","CAG","GTC","TC","TCA","TCC"};
        long test = Genomics.optBottomUp(strang,dictionary);
        System.out.println(test);
        System.out.println(Arrays.toString(goal));
    }
}

