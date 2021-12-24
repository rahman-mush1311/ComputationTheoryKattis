import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.*;

class Edge{
    int startVertex;
    String symbol;
    int endVertex;

    public Edge(int start, String sym, int end){
        this.startVertex = start;
        this.symbol = sym;
        this.endVertex=end;
    }
}
class nfaSimulator {
    public static void main(String args[]) {
        try {
            Scanner reader = new Scanner(System.in);
            int node = reader.nextInt();
            int noOfTran = reader.nextInt();

            ArrayList<Edge> edgeList = new ArrayList<>();

            for (int i = 0; i < noOfTran; i++) {
                int s = reader.nextInt();
                String a = reader.next();
                int t = reader.nextInt();
                reader.nextLine();
                edgeList.add(new Edge(s, a, t));
            }

            int noOfFinal = reader.nextInt();
            ArrayList<Integer> finalState = new ArrayList<>();

            for (int i = 0; i < noOfFinal; i++) {
                int f = reader.nextInt();
                finalState.add(f);
            }

            int noOfInput = reader.nextInt();
            reader.nextLine();

            Queue<Integer> q = new LinkedList<>();

            int ss = 0;
            q.add(0);
            int isEps = 0;

            for (int i = 0; i < noOfInput; i++) {
                String regularLang = reader.nextLine(); //taking input for checking expression

                for (int l = 0; l < regularLang.length(); l++) {  //checking for every input string
                    char symbolRead = regularLang.charAt(l);
                    int queueSize = q.size();
                    for (int qe = 0; qe < queueSize; qe++) { //looping through the queue size and taking every node of the queue
                        ss = q.poll();
                        for (int j = 0; j < edgeList.size(); j++) { //looping through the edge for every possible transition
                            Edge currentList = edgeList.get(j);
                            int s = currentList.startVertex;
                            String a = currentList.symbol;
                            int f = currentList.endVertex;
                            if (a.equals("eps"))
                                isEps = 1;
                            if (ss == s) {    //if the start state & symbol matches than we add the final state to the queue;
                                if (a.toLowerCase().charAt(0) == symbolRead) {
                                    q.add(f);
                                }
                                if (a.equals("eps")) {
                                    q.add(f);
                                }
                            }
                        }
                    }
                }
                Queue<Integer> q1 = new LinkedList<>();
                //for epsilon input checking after consuming all the elements
                if (isEps == 1) {
                    for (Integer e : q) {
                        q1.add(e);
                    }
                    int q1Size = q1.size();
                    for (int qe = 0; qe < q1Size; qe++) {
                        int newQueueElement = q1.poll();
                        for (int j = 0; j < edgeList.size(); j++) { //looping through the edge for every possible transition
                            Edge currentList = edgeList.get(j);
                            int s = currentList.startVertex;
                            String a = currentList.symbol;
                            int f = currentList.endVertex;

                            if (newQueueElement == s) {
                                if (a.equals("eps")) {
                                    q1.add(f);
                                }
                            } else {
                                q1.add(s);
                            }
                        }
                    }
                    q.clear();
                    for (Integer e : q1) {
                        q.add(e);
                    }
                }
                int queSize = q.size();
                int flag = 0;
                int newElement = -1;

                accepted_output:
                for (int qe = 0; qe < queSize; qe++) {
                    if(!q.isEmpty()) {
                        newElement = q.poll();
                    }
                    for (int finals = 0; finals < noOfFinal; finals++) {
                        if (newElement == finalState.get(finals)) {
                            if (!q.isEmpty()) {
                                q.clear();
                            }
                            q.add(0);
                            System.out.println("accept");
                            flag = 1;
                            break accepted_output;
                        }
                    }
                }
                if (q.isEmpty()) {
                    q.add(0);
                }
                if (flag == 0) {
                    System.out.println("reject");
                    if (!q.isEmpty()) {
                        q.clear();
                        q.add(0);
                    }
                }

            }
        }catch (Exception e){

        }
    }
}

