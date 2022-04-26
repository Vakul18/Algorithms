// { Driver Code Starts
//Initial Template for Java

import java.io.*;
import java.util.*;
class GfG
{
    public static void main(String args[])
        {
            Scanner sc = new Scanner(System.in);
            int t = sc.nextInt();
            while(t-->0)
                {
                    int n = sc.nextInt();
                    Solution ob = new Solution();
                    System.out.println(ob.minOperation(n));
                }
        }
}    // } Driver Code Ends


//User function Template for Java


class Solution
{
    public int minOperation(int n)
    {
        //code here.
        int[] a = new int[n+1];
        a[0] = 0;
        for(int i = 1 ; i<=n; i++)
        {
            int add = a[i-1]+1;
            if(i%2==0)
            {
                int mul = a[i/2]+1;
                a[i] = Math.min(add,mul);
            }
            else
            {
                a[i] = add;
            }
        }
        
        return a[n];
    }
}

/*
0 0
1 1
2 2
3 
*/
