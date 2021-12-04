/*56 59 55 60 60 57


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
        Tree tree = new TestClass().new Tree(A,N,edges);

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
        public Node parent;
        public int value;
        public ArrayList<Integer> child = new ArrayList<Integer>();

        public Node(int v)
        {
            this.value =  v;
            parent = null;
        }
            
    }

    public class Tree
    {
        HashMap<Integer,Node> map = new HashMap<Integer,Node>();

        public Tree(int[] A , int N , int[][] edges)
        {
            for(int i = 0 ; i<N;i++)
            {
                Node n = new Node(A[i]);
                map.put(i,n);
            }

            for(int i = 0 ; i< edges.length ; i++)
            {
                int[] e = edges[i];
                int s = e[0]-1;
                int d = e[1]-1;
                if(d==0)
                {
                    d = s;
                    s = 0;
                }
                Node ns = map.get(s);
                Node nd = map.get(d);

                nd.parent = ns;
                ns.child.add(d);
            }
        }

        public int query(int V, int X)
        {
            int[] Vb = binary(V);
            //System.out.println("vb : " + V);
            Node n = map.get((X-1));
            int max = 0;
            while(n!= null)
            {
                int[] nb = binary(n.value);
                //System.out.println("v : " + n.value);
                int c = common(Vb,nb);
                max = Math.max(c, max);
                n= n.parent;
            }

            return max;
        }

        private int[] binary(int v)
        {
            int[] res = new int[62];
            int i = 61 ;
            System.out.println(" v : " + v);
            while(v!=0 && i>=0)
            {
                int rem = v%2;
                res[i] = rem;
                v=v/2;
                i--;
            }
            String bin = "";
            for(int j = 0; j< res.length ; j++ )
            {
                bin += res[j];
            }
            System.out.println(" bin : " + bin);
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


