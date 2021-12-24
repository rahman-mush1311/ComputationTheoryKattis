import java.util.*;

class dfaEdge{
    String startVertex;
    char symbol;
    String endVertex;
    int finalsMarking;

    public dfaEdge(String start, char sym, String end, int fm){
        this.startVertex = start;
        this.symbol = sym;
        this.endVertex=end;
        this.finalsMarking= fm;
    }
}

public class nfaDFA {

    public static int charToInt(String in) {
        if (in.length() == 3) {
            return 0;
        } else {
            char c = in.charAt(0);
            return c - 'a' + 1;
        }
    }

    public static char IntToChar(int in) {
        if (in == 0) {
            return 'E';
        } else {
            char c = (char) (in + 96);
            return c;
        }
    }

    public static ArrayList<Integer>[][] modifyArraylist(ArrayList<Integer>[][] given) {
        ArrayList<Integer>[][] nextStates = given;
        for (int i = 0; i < 105; i++) {
            for (int j = 0; j < 30; j++) {
                nextStates[i][j] = new ArrayList<Integer>();
                nextStates[i][j].add(-1);
            }
        }
        return nextStates;
    }

    public static void main(String[] args) {
        ArrayList<Integer>[][] nextStates = new ArrayList[105][30];
        nextStates = modifyArraylist(nextStates);

        Scanner reader = new Scanner(System.in);
        int node = reader.nextInt();
        int noOfTran = reader.nextInt();
        ArrayList<Integer> epsilonStates = new ArrayList<>();
        Integer[] alphabetMarked = new Integer[30];

        /*for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 27; j++) {
                System.out.print(nextStates[i][j]);
            }
            System.out.println("\n");
        }*/

        int noOfAlphabet = 0;
        for (int i = 0; i < noOfTran; i++) {
            int s = reader.nextInt();
            String a = reader.next();
            int t = reader.nextInt();
            reader.nextLine();
            int index = charToInt(a);
            if (nextStates[s][index].get(0) == -1) {
                nextStates[s][index].remove(0);
            }
            nextStates[s][index].add(t);
            if (a.equals("eps")) {
                if (alphabetMarked[0] == null) {
                    alphabetMarked[0] = 1;
                }
            } else if (alphabetMarked[index] == null) {
                alphabetMarked[index] = 1;
                noOfAlphabet++;
            }
        }

        /*System.out.println("Distinct alphabets: "+noOfAlphabet);
        for (int i = 0; i < node; i++) {
            for (int j = 0; j <=noOfAlphabet; j++) {
                System.out.print(nextStates[i][j]);
            }
            System.out.println("\n");
        }*/


        ArrayList<ArrayList<Integer>> rowEpsilon = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < node; i++) {
            ArrayList<Integer> collumEpsilon = new ArrayList<Integer>();
            if (nextStates[i][0].size() == 1) {
                //System.out.println("Elements for 1 in state: "+nextStates[i][0].get(0)+" "+i);
                if (nextStates[i][0].get(0) == -1) {
                    collumEpsilon.add(i);

                } else {
                    collumEpsilon.add(i);
                    collumEpsilon.add(nextStates[i][0].get(0));
                }
            } else {
                for (int l = 0; l < nextStates[i][0].size(); l++) {
                    // System.out.println("elements of epsilon list: " + nextStates[i][0].get(l));
                    collumEpsilon.add(nextStates[i][0].get(l));
                }

            }
            rowEpsilon.add(collumEpsilon);

        }

        int noOfFinal = reader.nextInt();
        Integer[] markedFinals = new Integer[105];

        //final states marking
        for (int i = 0; i < noOfFinal; i++) {
            int f = reader.nextInt();
            markedFinals[f] = 1;
        }
        Queue<String> q = new LinkedList<>();
        Integer[] tranStates = new Integer[105];

        //System.out.println("Epsilon states size: "+rowEpsilon[0].size());

    }

}
