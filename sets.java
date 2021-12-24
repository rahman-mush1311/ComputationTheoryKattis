
/*
Online Java - IDE, Code Editor, Compiler

Online Java is a quick and easy tool that helps you to build, compile, test your programs online.
*/
import java.util.*; 
public class Main
{
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in); 
        
        ArrayList<String> list1 = new ArrayList<String>();
        ArrayList<String> list2 = new ArrayList<String>();
        ArrayList<String> unionList = new ArrayList<String>();
        ArrayList<String> intersectionList = new ArrayList<String>();
        
        //taking size input and string input of list1
        int sizeOfList1 =reader.nextInt();
        reader.nextLine();
        for (int i = 0; i < sizeOfList1; i++){
             list1.add(reader.nextLine());
        }
        //new list for union adding all the elements of list1
         unionList.addAll(list1);
      
        //taking input for 2nd list 
        int sizeOfList2 =reader.nextInt();
        reader.nextLine();
        if(sizeOfList2>0){
            for (int i = 0; i < sizeOfList2; i++){
                String element = reader.nextLine();
                list2.add(element);
                if(!list1.isEmpty()){
              //checking if that element already exsists add for intersection
                 if (list1.contains(element)) {
                   intersectionList.add(element);
            }
             //checking if that element already exsists skip for union
               for (int j = 0; j < sizeOfList1; j++){
                  if (!unionList.contains(element)) {
                    unionList.add(element);
                }
            }
           }
        }
        if(list1.isEmpty()){
            unionList.addAll(list2);
        }
    }
        //sorting for legographical order
        Collections.sort(unionList);
        Collections.sort(intersectionList);
        
        //printing the unionList
        if(!unionList.isEmpty()){
        for (int i = 0; i < unionList.size(); i++){
            System.out.print(unionList.get(i) + "\n");        
        }  
        System.out.print("\n"); 
        }
        //printing the intersectionList
        for (int j = 0; j < intersectionList.size(); j++){ 
            System.out.print(intersectionList.get(j) + "\n");        
        } 
        System.out.print("\n"); 
       //sorting for cartesian product    
       Collections.sort(list1);
       Collections.sort(list2);
       
       //printing the cartesian product 
       for(int i=0; i<sizeOfList1; i++){
         for(int j=0; j<sizeOfList2; j++){
            System.out.println(list1.get(i)+" "+list2.get(j) );
             
         }
       }
       
     System.out.print("\n"); 
       
       
    }
}
