/*
3
8
9 6 10 7 7 15 8 12
3
9
5 15 5 15 10 5 12 9 14
2
10
5 5 11 8 13 11 14 15 9 5
3
*/

import java.io.*;
import java.util.*;


public class TestClass {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter wr = new PrintWriter(System.out);
        int T = Integer.parseInt(br.readLine().trim());
        for(int t_i = 0; t_i < T; t_i++)
        {
            int N = Integer.parseInt(br.readLine().trim());
            String[] arr_A = br.readLine().split(" ");
            int[] A = new int[N];
            for(int i_A = 0; i_A < arr_A.length; i_A++)
            {
            	A[i_A] = Integer.parseInt(arr_A[i_A]);
            }
            int K = Integer.parseInt(br.readLine().trim());

            int out_ = minOperations(N, A, K);
            System.out.println(out_);
            
         }

         wr.close();
         br.close();
    }
    static int minOperations(int N, int[] A, int K){
       // Write your code here
        

        int c = 0;
        
        int[] A1 = new int[A.length];
        int[] A2 = new int[A.length];
        
        for(int i = 0 ; i<A.length ; i++)
        {
          A1[i] = A[i];
          A2[i] = A[i];
        }
        
        for(int i = A.length-1; i>= N-K ; i--)
        {
          //System.out.println("i = " + i  );
          int c1 = 0;
          int c2 = 0;
          for(int j=i ; j>=K ; j=j-K)
          {
             
             if(A1[j] < A1[j-K])
             {
               A1[j-K] = A1[j];
               c1++;
             }
          }
          
          for(int j= (i%K);j<N-K ; j=j+K)
          {
            //System.out.println("i,K,j = " + i + "," + K + ","+ j + "," );
            if(A2[j+K] < A2[j])
            {
              A2[j+K] = A2[j];
              c2++;
            }
          }
          //System.out.println("c1,c2 = " + c1 + "," + c2);
          if(c1>c2)
          {
            c = c+  c2;
          }
          else
          {
            c = c+  c1;
          }
          
        }
        
        
        return c;
        
    
    }
}