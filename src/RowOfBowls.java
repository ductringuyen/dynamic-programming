import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class RowOfBowls {

    public RowOfBowls() {}
    public int table[][];
    public Stack<Integer> moves;
    int player1Score[][];


    public int maxGain(int[] values)
    {
        // TODO
        table = new int[values.length][values.length];
        //base case
        for(int i=0;i<values.length;i++){
            for(int j=0;j<values.length;j++){
                if(j ==i+1){
                    table[i][j] = Math.max(values[i],values[j]);
                }else if(i==j){
                    table[i][j] = values[i];
                }
            }
        }


        //dynamic case
        for(int j=0;j<values.length;j++){
            for(int i=values.length-1;i>=0;i--){
                if(table[i][j] == 0 && i < j){
                    int x = values[i]+Math.min(table[i+2][j],table[i+1][j-1]);
                    int y = values[j]+Math.min(table[i+1][j-1],table[i][j-2]);
                    table[i][j] = Math.max(x,y);
                }
            }
        }


        //summe
        int sum=0;
        for(int i : values){
            sum += i;
        }
        return 2*table[0][values.length-1] -sum;


    }

    public int maxGainRecursive(int[] values,int player,int first, int last, int p1Score,int p2Score){
        if(first == last){
            return p1Score-(p2Score+values[last]);
        }

        //case1:first
        //Beispielspiele
        if(player == 1){
            p1Score += values[first];
        }else{
            p2Score += values[first];
        }
        int dif1 = maxGainRecursive(values,-player,first+1,last,p1Score,p2Score);

        //Zufallsspiele
        if(player == 1){
            p1Score -= values[first];
        }else{
            p2Score -= values[first];
        }

        //case2:last
        //Beispielspiele
        if(player ==1){
            p1Score += values[last];
        }else{
            p2Score += values[last];
        }
        int dif2 = maxGainRecursive(values,-player,first,last-1,p1Score,p2Score);

        //Zufallsspiele
        if(player ==1){
            p1Score -= values[last];
        }else{
            p2Score -= values[last];
        }

        if(dif1>=dif2){
            return (player==1) ? dif1 : dif2;
        }else{
            return (player==1) ? dif2 : dif1;
        }
    }

    public int maxGainRecursive(int[] values)
    {
        // TODO

        return maxGainRecursive(values,1,0,values.length-1,0,0);


    }


    public Iterable<Integer> optimalSequence()
    {
        List<Integer> opS = new LinkedList<>();
        int i=0;
        int j= table.length - 1;
        while(i+2<=j){
            if(player1Score[i][j] == table[i][0] + Math.min(player1Score[i+2][j],player1Score[i+1][j-1])){
                opS.add(i);
                if(player1Score[i + 1][j - 1] < player1Score[i + 2][j]){
                    i= i+2;
                    opS.add(i-1);
                }else{
                    i=i+1;
                    j=j-1;
                    opS.add(j+1);
                }
            }else{
                opS.add(j);
                if(player1Score[i+1][j-1]<player1Score[i][j-2]){
                    i=i+1;
                    j=j-1;
                    opS.add(i-1);
                }else{
                    j=j-2;
                    opS.add(j+1);
                }
            }
        }
        if(player1Score[i+1][j] == player1Score[i][j]){
            opS.add(i);
            opS.add(j);
        }else{
            opS.add(i);
            opS.add(j);
        }
        return opS;
    }


    public static void main(String[] args)
    {
        // for testing
    }
}

