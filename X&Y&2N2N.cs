/*
3
2
7
4
0
3
8




*/

using System;
using System.IO;
using System.Linq;


public class HelloWorld
{
    static public void Main()
    {
        String line;
        line = Console.ReadLine();
        int T = Convert.ToInt32(line);

        for(int t_i = 0; t_i < T; t_i++)
        {
            line = Console.ReadLine();
            int N = Convert.ToInt32(line);
            line = Console.ReadLine();
            int K = Convert.ToInt32(line);

            int out_ = findSmallestX(N, K);
            Console.Out.WriteLine(out_);

        }
    }

    static int findSmallestX(int N, int K)
    {
        // You must complete the logic for the function that is provided
        // before compiling or submitting to avoid an error.
        // Write your code here
       
        int res = 1;
        for(int i = 0; i<N;i++)
        {
            res = res * 2;
        }
        int x = -1;
        bool found = false;
        int maxLim = res*2;
        for(int i = res ; i < (maxLim) ; i++)
        {
            int val = (i & (i+K)) & res;
            if(val == res)
            {
                found = true;
                x = i;
                break;
            }
           
        }
        
        if(found)
            return x;
        else
            return -1;

    }

}