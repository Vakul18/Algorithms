/*


1
10
88 78 46 95 84 98 55 3 68 42
3 7
8 7
7 5
4 6
7 9
10 1
1 7
2 7
1 4
6
26 9
50 7
79 10
40 1
77 5
50 3



56 59 57 55 57 59 

*/



import java.util.*;
import java.io.*;

public class HelloWorld {
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
            int[][] edges = new int[N-1][2];
            for(int i_edges = 0; i_edges < N-1; i_edges++)
            {
            	String[] arr_edges = br.readLine().split(" ");
            	for(int j_edges = 0; j_edges < arr_edges.length; j_edges++)
            	{
            		edges[i_edges][j_edges] = Integer.parseInt(arr_edges[j_edges]);
            	}
            }
            int Q = Integer.parseInt(br.readLine().trim());
            int[][] query = new int[Q][2];
            for(int i_query = 0; i_query < Q; i_query++)
            {
            	String[] arr_query = br.readLine().split(" ");
            	for(int j_query = 0; j_query < arr_query.length; j_query++)
            	{
            		query[i_query][j_query] = Integer.parseInt(arr_query[j_query]);
            	}
            }

            int[] out_ = solve(N, A, edges, Q, query);
            System.out.print(out_[0]);
            for(int i_out_ = 1; i_out_ < out_.length; i_out_++)
            {
            	System.out.print(" " + out_[i_out_]);
            }
            
            System.out.println();
            
         }

         wr.close();
         br.close();
    }
    
    static int[] solve(int N, int[] A, int[][] edges, int Q, int[][] query){
       // Write your code here
        int[] result = new int[Q];
        Tree tree = new HelloWorld().new Tree(A,N,edges);

        for(int i = 0 ;i<Q; i++)
        {
            int V = query[i][0];
            int X = query[i][1];
            result[i] = tree.query(V,X);
        }
        

        

        return result;
    
    }

    class Node
    {
        public int id;
        public int value;
        public ArrayList<Node> child = new ArrayList<Node>();

        public Node(int i,int v)
        {
            this.value =  v;
            this.id  = i;
        }
            
    }

    public class Tree
    {
        HashMap<Integer,Node> map = new HashMap<Integer,Node>();
        

        public Tree(int[] A , int N , int[][] edges)
        {
            for(int i = 0 ; i<N;i++)
            {
                Node n = new Node(i,A[i]);
                map.put(i,n);
            }

            for(int i = 0 ; i< edges.length ; i++)
            {
                int[] e = edges[i];
                int s = e[0]-1;
                int d = e[1]-1;
                
                Node ns = map.get(s);
                Node nd = map.get(d);
                ns.child.add(nd);
                nd.child.add(ns);
            }
            
        }

        public int query(int V, int X)
        {
          X--;
          LinkedHashSet<Integer> path = new LinkedHashSet<Integer>();
          
          path = findPath(map.get(X),path);
          
          
          int[] Vb = binary(V);
          //System.out.println("vb : " + V);
          int max = 0;
          for(Integer  i : path)
          { 
            Node n = map.get(i);    
            int[] nb = binary(n.value);
            //System.out.println("v : " + n.value);
            int c = common(Vb,nb);
            max = Math.max(c, max);
              
          }

          return max;
        }
        
        private LinkedHashSet<Integer> findPath(Node n,LinkedHashSet<Integer> path)
        {
           LinkedHashSet<Integer> cPath = (LinkedHashSet<Integer>)path.clone();
           cPath.add(n.id);
           if(n.id == 0)
           {
              return cPath;
           }
           else
           {
             for(Node c : n.child)
             {
               if(cPath.contains(c.id))
               {
                 continue;
               }
               else
               {
                 LinkedHashSet<Integer> tPath = findPath(c,cPath);
                 if(tPath!=null)
                 {
                   return tPath;
                 }
               }
             }
           }
           return null;
        }

        private int[] binary(int v)
        {
            int[] res = new int[62];
            int i = 61 ;
            //System.out.println(" v : " + v);
            while(v!=0 && i>=0)
            {
                int rem = v%2;
                res[i] = rem;
                v=v/2;
                i--;
            }
            //String bin = "";
            //for(int j = 0; j< res.length ; j++ )
            //{
              //  bin += res[j];
            //}
            //System.out.println(" bin : " + bin);
            return res;
        }

        private int common(int[] a1, int[] a2)
        {
            int res = 0;
            for(int i = 0 ; i<62 ; i++)
            {
                if(a1[i] == a2[i])
                {
                    res++;
                }
                else
                {
                    break;
                }
            }
            return res;
        }
    }
    
    
}
