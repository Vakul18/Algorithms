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
        bool check(int V, List<int> res, List<List<int>> adj)
        {
            if(V!=res.Count) return false;
            List<int> map = new List<int>();
            for (int i = 0; i < V+1; i++) map.Add(-1);
            for (int i = 0; i < V; i++)
            {
                map[res[i]] = i;
            }
            for (int i = 0; i < V; i++)
            {
                foreach (int v in adj[i])
                {
                    if (map[i] > map[v]) return false;
                }
            }
            return true;
        }
        static void Main(string[] args)
        {
            GFG g = new GFG();
            int testcases;// Taking testcase as input
            testcases = Convert.ToInt32(Console.ReadLine());
            while (testcases-- > 0)// Looping through all testcases
            {
                var ip = Console.ReadLine().Trim().Split(' ');
                int E = int.Parse(ip[0]);
                int V = int.Parse(ip[1]);
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
                var res = obj.topoSort(V, adj);
                Console.WriteLine(g.check(V, res, adj) ? 1 : 0);
            }
        }
    }
}
// } Driver Code Ends


//User function Template for C#

class Solution
{
    //Function to return list containing vertices in Topological order. 
    public List<int> topoSort(int V, List<List<int>> adj)
    {
        //code here
        bool[] visited = new bool[V];
        var res= new List<int>();
        var res1= new List<int>();
        for(int i = 0 ; i<V;i++)
        {
            dfs(i,adj,visited,res);
        }
        
        for(int i = V-1; i>=0 ; i--)
        {
            res1.Add(res[i]);    
        }
        
        
        return res1;
    }
    
    public void dfs(int u , List<List<int>> adj,bool[] visited,List<int> res )
    {
        if(visited[u])
            return;
        
        foreach(int v in adj[u])
        {
            dfs(v,adj,visited,res);    
        }
        
        res.Add(u);
        visited[u] = true;
        return;
    }
}
