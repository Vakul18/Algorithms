// { Driver Code Starts
//Initial Template for C#

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DriverCode
{

    class GFG
    {
        static void Main(string[] args)
        {
            int testcases;// Taking testcase as input
            testcases = Convert.ToInt32(Console.ReadLine());
            while (testcases-- > 0)// Looping through all testcases
            {
                var ip = Console.ReadLine().Trim().Split(' ');
                int V = int.Parse(ip[0]);
                int E = int.Parse(ip[1]);
                List<List<List<int>>> adj = new List<List<List<int>>>();
                for (int i = 0; i < V; i++)
                {
                    adj.Add(new List<List<int>>());
                }
                for (int i = 0; i < E; i++)
                {
                    ip = Console.ReadLine().Trim().Split(' ');
                    int u = int.Parse(ip[0]);
                    int v = int.Parse(ip[1]);
                    int w = int.Parse(ip[2]);

                    adj[u].Add(new List<int>() { v, w });
                    adj[v].Add(new List<int>() { u, w });
                }
                int S = Convert.ToInt32(Console.ReadLine());
                Solution obj = new Solution();
                var res = obj.dijkstra(V, ref adj, S);
                foreach (int i in res)
                {
                    Console.Write(i + " ");
                }
                Console.WriteLine();
            }
        }
    }
}
// } Driver Code Ends


//User function Template for C#

class Solution
{
    //Function to find the shortest distance of all the vertices
    //from the source vertex S.
    public List<int> dijkstra(int V, ref List<List<List<int>>> adj, int S)
    {
        //code here
        /*for(int u =0; u < adj.Count; u++)
        {
            for(int v =0; v < adj[u].Count; v++)
            {
                for(int w=0 ; w<adj[u][v].Count ; w++)
                {
                    Console.WriteLine("adj["+ u +","+ v +","+ w +"] = " + adj[u][v][w]);
                }
            }   
        }*/
        var res = new List<int>();
        
        var visited = new bool[V];
       
       
        for(int i = 0 ; i< V ; i++)
        {
            
            res.Add(Int32.MaxValue);
        }
        
        res[S] = 0;
        

        for(int i=0 ; i<V ; i++)
        {
            int u = MinDistV(visited,res);
            if(u == -1)
                continue;
            //Console.WriteLine("u : " + u);
            foreach(var e in adj[u])
            {
                int v = e[0];
                int w = e[1];
                
                if(res[v] > (res[u] + w))
                {
                    res[v] = res[u] + w;
                }
                
            }
            
            visited[u] = true;
        }
        
        
        
        
        
       /* while(pq.Count>0)
        {
            int u= pq.Dequeue();
            
        }*/
        return  res;
        
    }
    
    public int MinDistV(bool[] visited , List<int> res)
    {
        int minIndex = -1;
        int minDist = Int32.MaxValue;
        
        for(int i = 0 ; i<res.Count ; i++)
        {
            if(!visited[i] && minDist>res[i])
            {
                minIndex = i;
                minDist = res[i];
            }
        }
        
        return minIndex;
    }
    
}

