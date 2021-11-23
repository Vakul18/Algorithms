/*
7
2
5
World
Asia
Africa
China
India
SouthAfrica
Egypt
1 China 9
1 India 9
3 Asia 9
2 India 9
2 Asia 9
*/

/* IMPORTANT: Multiple classes and nested static classes are supported */


//uncomment this if you want to read input.
//imports for BufferedReader
import java.io.BufferedReader;
import java.io.InputStreamReader;


//import for Scanner and other utility classes
import java.util.*;


// Warning: Printing unwanted or ill-formatted data to output will cause the test cases to fail

class TestClass {
    public static void main(String args[]) throws Exception {
        /* Sample code to perform I/O:
         * Use either of these methods for input

        //BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String name = br.readLine();                // Reading input from STDIN
        System.out.println("Hi, " + name + ".");    // Writing output to STDOUT

        //Scanner
        Scanner s = new Scanner(System.in);
        String name = s.nextLine();                 // Reading input from STDIN
        System.out.println("Hi, " + name + ".");    // Writing output to STDOUT

        */

        // Write your code here

        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int m = s.nextInt();
        int q = s.nextInt();

        Tree t = new TestClass().new Tree(m);
        s.nextLine();
        for (int i = 0; i < n; i++) {
            String name = s.nextLine();
            t.addNode(name);
        }

        for (int i = 0; i < q; i++) {
            String op = s.nextLine();

            String[] arr = op.split(" ");
            int opTy = Integer.parseInt(arr[0]);
            //System.out.println(opTy + "; opty");
            String nodeName = arr[1];
            //System.out.println(nodeName + "; node name");
            int uid = Integer.parseInt(arr[2]);
            //System.out.println(uid + "; uid");
            if (opTy == 1) {
                System.out.println(t.lock(nodeName, uid));
            } else if (opTy == 2) {
                System.out.println(t.unlock(nodeName, uid));
            } else if (opTy == 3) {
                System.out.println(t.upgrade(nodeName, uid));
            }
        }






    }

    class Node {
        public Node parent;
        public String name;
        public ArrayList < Node > child;
        public boolean isLocked;
        public boolean isLockable;
        public int lockedBy;
        public HashMap < String, Integer > descLocked;

        public Node(String name) {
            this.child = new ArrayList < Node > ();
            this.isLocked = false;
            this.isLockable = true;
            this.descLocked = new HashMap < String, Integer > ();
            this.name = name;
            this.parent = null;
            this.lockedBy = -1;
        }
    }

    class Tree {
        Node root = null;
        Node last = null;
        int m = -1;
        LinkedHashMap < String, Node > nodes = new LinkedHashMap < String, Node > ();

        public Tree(int m) {
            this.m = m;
        }

        public void addNode(String name) {
            Node n = new Node(name);
            nodes.put(name, n);
            if (root == null) {
                root = n;
                last = n;
                return;
            }

            n.parent = last;
            last.child.add(n);
            if (last.child.size() == m) {
                last = last.child.get(0);
            }
        }

        public boolean lock(String name, int uid) {
            Node n = nodes.get(name);

            if (n.isLocked || !n.isLockable) {
                return false;
            }

            Node parent = n.parent;
            while (parent != null) {
                if (parent.isLocked) {
                    return false;
                }
                parent = parent.parent;
            }

            n.lockedBy = uid;
            n.isLocked = true;

            parent = n.parent;
            while (parent != null) {
                parent.isLockable = false;
                parent.descLocked.put(name, uid);
                parent = parent.parent;
            }

            return true;


        }

        public boolean unlock(String name, int uid) {
            Node n = nodes.get(name);

            if (!(n.isLocked && (n.lockedBy == uid))) {
                return false;
            }

            Node parent = n.parent;
            while (parent != null) {
                parent.descLocked.remove(name);
                if (parent.descLocked.size() == 0) {
                    parent.isLockable = true;
                }
                parent = parent.parent;
            }

            n.isLocked = true;
            n.lockedBy = -1;
            
            return true;

        }

        public boolean upgrade(String name, int uid) {
            Node n = nodes.get(name);
            if (n.isLocked) {
                return false;
            }

            for (Map.Entry < String, Integer > entry: n.descLocked.entrySet()) {
                String key = entry.getKey();
                int value = entry.getValue();
                if (value != uid) {
                    return false;
                }
            }

            Node parent = n.parent;
            while (parent != null) {
                if (parent.isLocked) {
                    return false;
                }
                parent = parent.parent;
            }
            
            HashMap < String, Integer > clone = (HashMap < String, Integer >)n.descLocked.clone();
            for (Map.Entry < String, Integer > entry: clone.entrySet()) {
                String key = entry.getKey();
                int value = entry.getValue();
                unlock(key,value);
            }
           

            lock(n.name, uid);

            return true;

        }

    }


}



