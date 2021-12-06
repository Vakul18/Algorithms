/*
5
6
4 3 1 2 3
*/


using System;
using System.IO;
using System.Linq;
using System.Collections.Generic;


public class HelloWorld
{
    static public void Main()
    {
        String line;
        line = Console.ReadLine();
        int N = Convert.ToInt32(line);
        line = Console.ReadLine();
        int K = Convert.ToInt32(line);
        line = Console.ReadLine();
        int[] A = new int[N];
        A = line.Split().Select(str => int.Parse(str)).ToArray();

        /*int[][] out_ =*/ getGoodSubsequences(N, K, A);
        /*for(int i_out_ = 0; i_out_ < out_.Length; i_out_++)
        {
        	Console.Out.WriteLine(string.Join(" ", out_[i_out_].Select(x => x.ToString()).ToArray()));
        }*/
    }

    static void getGoodSubsequences(int N, int K, int[] A)
    {
        // You must complete the logic for the function that is provided
        // before compiling or submitting to avoid an error.
        // Write your code here

        Dictionary<int, List<int>> dic = new Dictionary<int, List<int>>();
        for(int i = 0 ;i < N-1 ; i++)
        {
            List<int> l  = new List<int>();
            for(int j = i+1 ; j<N; j++)
            {
                int v = A[i] - A[j];
                if( (v==-1) || (v==1))
                {
                    l.Add(j);
                }

            }

            dic.Add(i,l);
        }


        for(int i = 0; i<N ; i++)
        {
            string s = "";

            print(dic,ref K,i,s);
            if(K==0)
            {
                break;
            }
        }

    }

    static void print( Dictionary<int, List<int>> dic, ref int k, int i , string s)
    {
        if(string.IsNullOrEmpty(s))
        {
            s =  (Convert.ToString(i+1));
        }
        else
        {
             s= s + " " + (Convert.ToString(i+1));
        }
       
        Console.WriteLine(s);
        k--;
        if(dic.ContainsKey(i))
        {
            List<int> l  = dic[i];
            foreach(int j in l)
            {
                if(k==0)
                {
                    break;
                }

                print(dic,ref k,j,s);
            }
        }
    }


}