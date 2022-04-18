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
                List<List<int>> adj = new List<List<int>>();
                for (int i = 0; i < V; i++)
                {
                    adj.Add(new List<int>());
                }
                for (int i = 0; i < E; i++)
                {
                    ip = Console.ReadLine().Trim().Split(' ');
                    int u = int.Parse(ip[0]);
                    int v = int.Parse(ip[1]);
                    adj[u].Add(v);
                }
                Solution obj = new Solution();
                var res = obj.kosaraju(V, ref adj);
                Console.WriteLine(res);
            }
        }
    }
}// } Driver Code Ends


//User function Template for C#

class Solution
{
    //Function to find number of strongly connected components in the graph.
    public int kosaraju(int V, ref List<List<int>> adj)
    {
        //code here
        
        var st = new Stack<int>();
        var visited = new bool[V];
        var count = 0;
        
        for(int i = 0 ; i<V; i++)
        {
            if(visited[i])
            {
                continue;
            }
            dfsSt(i,visited,st,adj);
        }
        
        var visited1 = new bool[V];
        
        var revAdj = Reverse(adj);
        
        while(st.Count>0)
        {
            int u = st.Pop();
            if(visited1[u])
                continue;
            count++;
            dfs(u,visited1, revAdj);
        }
        
        return count;
        
    }
    
    public void dfsSt(int u, bool[] visited , Stack<int> st,  List<List<int>> adj)
    {
        if(visited[u])
            return;
        visited[u] = true;    
        foreach(int v in adj[u])
        {
            dfsSt(v,visited,st,adj);
        }
        
        st.Push(u);
    }
    
    public List<List<int>> Reverse(List<List<int>> adj)
    {
        var revAdj = new List<List<int>>();
        for(int i = 0 ; i<adj.Count;i++)
        {
            revAdj.Add(new List<int>());
        }
        
        for(int i = 0 ; i<adj.Count;i++)
        {
            for(int j = 0 ; j<adj[i].Count;j++)
            {
                revAdj[adj[i][j]].Add(i);
            }
        }
        
        return revAdj;
    }
    
    public void dfs(int u, bool[] visited, List<List<int>> adj)
    {
         if(visited[u])
            return;
        visited[u] = true;    
        foreach(int v in adj[u])
        {
            dfs(v,visited,adj);
        }
        
    }
}



