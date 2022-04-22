// { Driver Code Starts
/*package whatever //do not write package name here */

import java.io.*;
import java.util.*;
import java.math.*;

class GFG {
	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int t = Integer.parseInt(sc.next());
		while(t-- > 0)
		{
		    int n = Integer.parseInt(sc.next());
		    int k = Integer.parseInt(sc.next());
		    
		    String[] words = new String[n];
		    
		    for(int i=0;i<n;i++)
		    {
		        words[i] = sc.next();
		    }
		    
		    Solution ob = new Solution();
		  //  System.out.println(T.findOrder(words,k));
		    String order = ob.findOrder(words,n,k);
		    if(order.length() == 0){
		        System.out.println(0);
		        continue;
		    }
		    String temp[] = new String[n];
		    for(int i=0;i<n;i++)
		        temp[i] = words[i];
		    
		    Arrays.sort(temp, new Comparator<String>(){
		    
		      @Override
                public int compare(String a, String b) {
                    int index1 = 0;
                    int index2 = 0;
                    for(int i = 0; i < Math.min(a.length(), b.length()) 
                                        && index1 == index2; i++) {
                        index1 = order.indexOf(a.charAt(i));
                        index2 = order.indexOf(b.charAt(i));
                    }
                
                    if(index1 == index2 && a.length() != b.length()) 
                    {
                        if(a.length() < b.length())
                            return -1;
                        else
                            return 1;
                    }
                
                    if(index1 < index2)
                        return -1;
                    else
                        return 1;
                        
                }
		    });
		    
		    int flag = 1;
		    for(int i=0;i<n;i++)
		    {
		        if(!words[i].equals(temp[i]))
	            {
	                flag = 0;
	                break;
	            }
		    }
		    
		    System.out.println(flag);
		}
	}
	
}
// } Driver Code Ends


//User function Template for Java

class Solution
{
    public String findOrder(String [] dict, int N, int K)
    {
        // Write your code here
        Graph g = new Graph();
        for(int i = 0 ; i<N-1 ;i++)
        {
            String first = dict[i];
            String second = dict[i+1];
            for(int j  = 0 ; j<first.length() && j< second.length() ; j++)
            {
                if(first.charAt(j)!=second.charAt(j))
                {
                    g.AddEdge(first.charAt(j)-'a',second.charAt(j)-'a');
                    break;
                }
            }
        }
        
        String s =  g.Topo();
       //System.out.println(s);
        return s;
        
    }
    
}

class Graph
{
    int V = 26;
    ArrayList<ArrayList<Integer>> adj;
    boolean[] mark;
     
    public Graph()
    {
        mark = new boolean[V];
        adj = new ArrayList<ArrayList<Integer>>();
        for(int i = 0 ; i<V;i++)
        {
            adj.add(new ArrayList<Integer>());    
            
        }
        
    }
    
    public void AddEdge(int u , int v)
    {
        //System.out.println(u + "--->" + v);
        adj.get(u).add(v);
        mark[u] = true;
        mark[v] = true;
    }
    
    public String Topo()
    {
        boolean[] visited =new  boolean[V];
        Stack<Integer> st = new Stack<>();
        
        for(int i = 0 ; i<V ; i++)
        {
            if(!mark[i])
                continue;
            
            dfs(visited,i,st);
        }
        
        String res = "";
        while(!st.isEmpty())
        {
            int u  = st.pop();
            res  = res + (char)(u + 'a');
        }
            
        return res;
        
    }
    
    private void dfs(boolean[] visited, int u , Stack<Integer> st)
    {
        if(visited[u])
            return;
        visited[u] = true;
        ArrayList<Integer> l = adj.get(u);
        for(int i = 0 ; i< l.size(); i++)
        {
            dfs(visited,l.get(i),st);
        }
        st.push(u);
    }
    
    
}
