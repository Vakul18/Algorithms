// { Driver Code Starts
// Initial Template for Java

import java.io.*;
import java.util.*;
import java.lang.*;

class GFG
{
    public static void main(String args[])throws IOException
    {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(in.readLine().trim());
		while(t-->0){
		    String A[] = in.readLine().trim().split(" ");
		    int N = Integer.parseInt(A[0]);
		    A = in.readLine().trim().split(" ");
		    
		    Solution ob = new Solution();
		    System.out.println(ob.isCircle(N, A));
		}
    }
}// } Driver Code Ends


// User function Template for Java

class Solution
{
    static int isCircle(int N, String A[])
    {
        Graph g = new Graph();
        for(int i = 0 ;i <N ; i++)
        {
            int u = A[i].charAt(0) - 'a';
            int v = A[i].charAt(A[i].length() - 1) - 'a';
            g.AddEdge(u,v);
        }
        
        if(!g.CheckIn())
            return 0;
            
        if(g.IsSc())
            return 1;
            
        return 0;
    }
    
   
}

 class Graph
    {
        int V = 26;
        int[] in = new int[26];
        
        ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>(); 
        public Graph()
        {
            for(int i = 0 ; i<V; i++)
            {
                adj.add(new ArrayList<Integer>());
            }
            
        }
        
        public void AddEdge(int u , int v)
        {
            adj.get(u).add(v);
            in[v]++;
        }
        
        public Graph Transpose()
        {
            Graph gr = new Graph();
            for(int i = 0 ; i< V ; i++)
            {
                for(int j = 0 ; j< adj.get(i).size() ; j++)
                {
                    int v = adj.get(i).get(j);
                    gr.AddEdge(v,i);
                }
            }
            return gr;
        }
        
        public boolean  CheckIn()
        {
            for(int i = 0 ; i<26 ; i++)
            {
                if(adj.get(i).size()!=in[i])
                    return false;
            }
            return true;
        }
        
        public boolean IsSc()
        {
            boolean[] visited = new boolean[26];
            int n = 0;
            for(int i = 0 ;i<26 ; i++)
            {
                if(adj.get(i).size()>0)
                {
                    n = i;
                    break;
                }
            }
            
            dfs(visited,n);
            
            for(int i = 0 ;i<26 ; i++)
            {
                if(adj.get(i).size()>0 && !visited[i])
                {
                    return false;
                }
            }
            
            Graph gr = Transpose();
            boolean[] visited1 = new boolean[26];
            gr.dfs(visited1,n);
            
            for(int i = 0 ;i<26 ; i++)
            {
                if(adj.get(i).size()>0 && !visited1[i])
                {
                    return false;
                }
            }
            
            return true;
        }
        
        public void dfs(boolean[] visited,int n)
        {
            if(visited[n])
                return;
            visited[n] = true;
            for(int i = 0 ; i< adj.get(n).size(); i++)
            {
                dfs(visited,adj.get(n).get(i));
            }
        }
    }
