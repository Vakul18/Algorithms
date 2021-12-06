/*
7 4
2 3 3 5 4 4 3
1 7
4 5
7 6
2 2

2
-1
-1
0

//

9 10
2 3 4 5 6 4 3 9 9
4 1
5 7
2 7
1 9
2 4
7 4
6 3
9 8
5 5
1 6

3
2
1
-1
-1
-1
1
1
0
-1

*/


import java.io.*;
import java.util.*;


public class TestClass {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter wr = new PrintWriter(System.out);
         String[] S = br.readLine().split(" ");
         int N = Integer.parseInt(S[0]);
         int Q = Integer.parseInt(S[1]);
         String[] arr_Arr = br.readLine().split(" ");
         int[] Arr = new int[N];
         for(int i_Arr = 0; i_Arr < arr_Arr.length; i_Arr++)
         {
         	Arr[i_Arr] = Integer.parseInt(arr_Arr[i_Arr]);
         }
         int[][] AB = new int[Q][2];
         for(int i_AB = 0; i_AB < Q; i_AB++)
         {
         	String[] arr_AB = br.readLine().split(" ");
         	for(int j_AB = 0; j_AB < arr_AB.length; j_AB++)
         	{
         		AB[i_AB][j_AB] = Integer.parseInt(arr_AB[j_AB]);
         	}
         }

         int[] out_ = collision_time(N, Q, Arr, AB);
         System.out.print(out_[0]);
         for(int i_out_ = 1; i_out_ < out_.length; i_out_++)
         {
         	System.out.print("\n" + out_[i_out_]);
         }

         wr.close();
         br.close();
    }
    static int[] collision_time(int N, int Q, int[] Arr, int[][] AB){
       // Write your code here
        int[] result = new int[Q] ;
        Graph g = new TestClass().new Graph();
        
        for(int i=0; i<N ; i++)
        {
          g.addEdge(i,Arr[i]-1);
        }
        
        for(int i = 0 ;i<Q;i++)
        {
          int A = AB[i][0];
          int B = AB[i][1];
          
          result[i] = g.getTime(A,B);
        }
        
        return result;
    
    }
    
    
    class Graph
    {
       HashMap<Integer,Integer> edges = new HashMap<Integer,Integer>();
       
       public void addEdge(int s , int d)
       {
           edges.put(s,d);  
       }
       
       public int getTime(int A, int B)
       {
        A--;
        B--;
        int t = 0;
        
        HashSet<Integer> aPath = new HashSet<Integer>();
        HashSet<Integer> bPath = new HashSet<Integer>();
        
        aPath.add(A);
        bPath.add(B);
        boolean aRept = false;
        boolean bRept = false;
        
        boolean aStuck = false;
        boolean bStuck = false;
        
        if(A==B)
          return t;
        
        while(!aRept && !bRept)
        {
          t++;
          //System.out.println("t : " + t);
          if(!aStuck)
          {
            if(!edges.containsKey(A))
            {
              aRept = true;
              aStuck = true;
            }
            else
            {
              int z = edges.get(A);
              if(z==A)
              {
                aRept = true;
                aStuck = true;
              }
              else 
              {
                if(aPath.contains(z))
                {
                  aRept = true;
                }
                A = z;
              }
            }
          }
          if(!bStuck)
          {
            if(!edges.containsKey(B))
            {
              bRept = true;
              bStuck = true;
            }
            else
            {
              int z = edges.get(B);
              if(z==B)
              {
                bRept = true;
                bStuck = true;
              }
              else
              {
                if(bPath.contains(z))
                {
                  bRept = true;
                }
                B = z;
              }
            }
          }
          if(A==B)
            return t;
        }
        
        
        return -1;
       
       }
       
       
    }
    
}































































