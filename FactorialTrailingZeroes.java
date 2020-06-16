/* IMPORTANT: Multiple classes and nested static classes are supported */

/*
 * uncomment this if you want to read input.
//imports for BufferedReader*/
import java.io.BufferedReader;
import java.io.InputStreamReader;

//import for Scanner and other utility classes*/
import java.util.*;

import java.math.BigDecimal;
// Warning: Printing unwanted or ill-formatted data to output will cause the test cases to fail

class TestClass {
    public static void main(String args[] ) throws Exception {
        /* Sample code to perform I/O:
         * Use either of these methods for input
*/
        //BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String name = br.readLine();                // Reading input from STDIN
        int t = Integer.parseInt(name);
        
        for(int i = 0;i< t;i++)
        {
            int m  = Integer.parseInt(br.readLine());
            int start = m*5;
            int end = (m+1)*5;
            String op = "";
            System.out.println(5);
            for(int j=start;j<(end);j++)
            {
                op+= j + " ";
            }
            System.out.println(op);
            
            BigDecimal l = new BigDecimal("1");
            
            for(int k = 2;k<=150;k++)
            {
                l=l.multiply(new BigDecimal(k));
            }
            String x = l.toString();
            int count = 0;
            for(int h=x.length()-1;h>=0 && x.charAt(h)=='0';h--)
            {
                count++;
            }
            System.out.println("zeroes = " + count);
        }
        
        
        
           // Writing output to STDOUT
        
        
        
        //Scanner
       /* Scanner s = new Scanner(System.in);
        String name = s.nextLine();                 // Reading input from STDIN
        System.out.println("Hi, " + name + ".");    // Writing output to STDOUT
*/
        

        // Write your code here

    }
}
