/* IMPORTANT: Multiple classes and nested static classes are supported */

/*
 * uncomment this if you want to read input.
//imports for BufferedReader
import java.io.BufferedReader;
import java.io.InputStreamReader;

//import for Scanner and other utility classes
import java.util.*;
*/

// Warning: Printing unwanted or ill-formatted data to output will cause the test cases to fail
import java.util.*;

import javax.lang.model.util.ElementScanner6;

class TestClass {
    public static void main(String args[] ) throws Exception {
        /* Sample code to perform I/O:
         * Use either of these methods for input

        //BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String name = br.readLine();                // Reading input from STDIN
        System.out.println("Hi, " + name + ".");    // Writing output to STDOUT
*/
        //Scanner
        Scanner sc = new Scanner(System.in);
                      // Reading input from STDIN
       HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
       int nV =  sc.nextInt();
        
       for(int i = 0 ;i<nV ; i++)
       {
           int x = sc.nextInt();
           map.put(x,i);
       }

       int nE = sc.nextInt();
       int[][] arr = new int[nV][nV];
       for(int[] a : arr)
       {
           Arrays.fill(a,-1);
       }
       for(int i = 0; i< nE ; i++)
       {
            int x = sc.nextInt();
            int y = sc.nextInt();
            

            arr[map.get(x)][map.get(y)] = 1;
       }

       int source = sc.nextInt();
       int star = sc.nextInt();
        ArrayList<Integer> l = new ArrayList<Integer>();
        
        for(Integer v : map.keySet())
        {
            if(v!=star)
            {
                int val = arrVal(v,star,arr,map);
                if(val!=-1)
                {
                    l.add(v);
                }
            }
            
        }
         ArrayList<Integer> l1 = new ArrayList<Integer>();
        for(Integer v : l)
        {
            HashSet<Integer> clone =  new HashSet<Integer>(map.keySet());
            HashSet<Integer> clone1 =  (HashSet<Integer>)clone.clone();
             clone1.remove(source);
            clone1.remove(v);
            int retVal = shortP(source,v,arr,map,clone1);
            if(retVal > -1)
            {
                l1.add(v);
            }
        }
          
          for (Integer i : l1) {
        System.out.print(i + " ");
      }
       

        
        
       

    }

    public static int shortP(int vs,int vd, int[][] arr,HashMap<Integer,Integer> map,HashSet<Integer> set)
    {
        int minVal = arrVal(vs,vd,arr, map);
          
        for(Integer v : set)
        {
            int val = arrVal(vs,v,arr,map);
            if(val!=-1)
            {
                HashSet<Integer> clone = (HashSet<Integer>)set.clone();
                clone.remove(v);
                minVal = min(minVal, (shortP(v,vd,arr,map,clone) + val));
            }
        }

        return minVal;

    }

    public static int arrVal(int v1,int v2, int[][] arr, HashMap<Integer,Integer> map)
    {
        return arr[map.get(v1)][map.get(v2)];
    }

    public static int min(int a , int b)
    {
        if(a==-1)
        {
            return b;
        }
        else if(b==-1)
        {
            return a;
        }
        else
        {
            if(a>b)
            {
                return b;
            }
            else
            {
                return a;
            }
        }
        
    }
}



***********************************
4
2
5
7
9
4
2 9
7 2
7 9
9 5
7
9


2 7 