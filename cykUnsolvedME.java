import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class cyk {
    public static ArrayList<String>[][] modifyArraylist(ArrayList<String>[][] given, int inputLen) {
        ArrayList<String>[][] nextStates = given;
        int noOfindexes = inputLen;
        for (int i = 0; i < noOfindexes; i++) {
            for (int j = 0; j < noOfindexes; j++) {
                nextStates[i][j] = new ArrayList<String>();
                nextStates[i][j].add("0");
            }
        }
        return nextStates;
    }

    public static void main(String args[]) {

        Scanner reader = new Scanner(System.in);
        int node = reader.nextInt();
        reader.nextLine();

        Map<String, String> rules = new HashMap<String, String>();
        String startState = "";

        //inputing the rules
        for (int i = 0; i < node; i++) {
            String inputStrings = reader.nextLine();
            String[] words = inputStrings.split(" ");
            if (i == 0) {
                startState = words[0];
            }
            if (rules.containsKey(words[1])) {
                String val = rules.get(words[1]);
                words[0] += "|" + val;
            }
            rules.put(words[1], words[0]);
        }

        int noOfStrings = reader.nextInt();
        reader.nextLine();
        for (int in = 0; in < noOfStrings; in++) {
            String inputs = reader.nextLine();
            int inputLen = inputs.length();

            ArrayList<String>[][] nextStates = new ArrayList[inputLen+1][inputLen+1];
            nextStates = modifyArraylist(nextStates, (inputLen));

            for (int s = 0; s < inputLen; s++) {
                String ch = Character.toString(inputs.charAt(s));
                if (rules.containsKey(ch)) {
                    ArrayList<String> grammars = new ArrayList<>();
                    if (rules.containsKey(ch)) {
                        String ruleVal = rules.get(ch);
                        if (ruleVal.contains("|")) {
                            String[] wording = ruleVal.split("|");
                            for (int w = 0; w < wording.length; w++) {
                                if (wording[w].equals("|"))
                                    continue;
                                grammars.add(wording[w]);
                            }
                        } else
                            grammars.add(ruleVal);
                    }
                    nextStates[0][s] = grammars;
                }

            }//1st row sesh
       /* for(int i=0; i<inputLen;i++){
            System.out.print(nextStates[0][i]);
            System.out.print(" ");
        }*/
            for (int i = 1; i < inputLen; i++) {
                for (int j = 0; j < inputLen - i; j++) {
                    //System.out.println("kon idex er jonno kaj kortesi: "+i+" "+j);
                    ArrayList<String> states = new ArrayList<>();
                    for (int k = 0; k < i; k++) {
                        int Bsize = nextStates[k][j].size();
                        int Csize = nextStates[i - k - 1][j + k + 1].size();
                        String A = "";
                        // System.out.println("Kon kon indexes nicchi for B: "+k+" "+j+" "+Bsize);
                        //  System.out.println("Kon kon indexes nicchi for C: "+(i-k-1)+" "+(j+k+1)+" "+Csize);
                        for (int b = 0; b < Bsize; b++) {
                            String B = nextStates[k][j].get(b);
                            for (int c = 0; c < Csize; c++) {
                                String C = nextStates[i - k - 1][j + k + 1].get(c);
                                // System.out.println("String B & C: "+B+" "+C);
                                if ((B.equals("0")) || (C.equals("0")))
                                    A = "0";
                                else
                                    A = B + C;

                                if (rules.containsKey(A)) {
                                    String ruleVal = rules.get(A);
                                    if (ruleVal.contains("|")) {
                                        String[] wording = ruleVal.split("|");
                                        for (int w = 0; w < wording.length; w++) {
                                            if (wording[w].equals("|"))
                                                continue;
                                            states.add(wording[w]);
                                        }
                                    } else
                                        states.add(ruleVal);

                                }
                            }
                        }

                    }
                    if (!states.isEmpty()) {
                        nextStates[i][j] = states;
                    }

                }
            }//table sesh
            /* for (int i = 0; i < inputLen; i++) {
            for (int j = 0; j < inputLen - i; j++) {
                System.out.print(nextStates[i][j]);
                System.out.print(" ");
            }
            System.out.print("\n");
        }*/
            int f = nextStates[inputLen-1][0].size();
            int flag=0;
            for(int ff=0; ff<f; ff++){
                String finals = nextStates[inputLen-1][0].get(ff);
                if(finals.contains(startState)){
                    System.out.println("yes");
                    flag=1;
                    break;
                }
            }
            if(flag==0)
                System.out.println("no");
        }

    }
}
