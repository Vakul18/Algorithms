import java.util.Date;

public class HelloWorld {
    public static void main(String[] args) {
        int[] arr = {2,1,3,5,4};
        int req = 9;
        qsort(arr, 0 , arr.length-1);
        
        String a = "";
        
        for(int i = 0 ; i< arr.length ; i++)
        {
          a = a + " , " + arr[i];
        }
        
        
        
        System.out.println("After sort : "+ a);
        int i = 0;
        int j = arr.length-1;
        
        boolean found = false;
        while(i<arr.length && j >=0)
        {
          int t = arr[i] + arr[j];
           if(t==req)
           {
             System.out.println(arr[i] + " , " +arr[j] );
             found = true;
             break;
           }
           else if(t<req)
           {
             i++;
           }
           else
           {
             j--;
           }
        }
        
        if(!found)
        {
          System.out.println( "No pair exists " );
        }
    }
    
    public static  void qsort(int[] arr, int l , int r)
    {
      //System.out.println(l + " , " +r );
       if(l>=arr.length || r<0 || l>r)
       {
         return;
       }
       
       
       int p = partition(arr,l,r);
       
       qsort(arr,l,p-1);
       qsort(arr,p+1,r);
       
    }
    
    public static int partition(int[] arr, int l , int r)
    {
      int i = l-1;
      
      for(int j = l ; j<r ; j++)
      {
        if(arr[j] <= arr[r])
        {
          i++;
          int t = arr[j];
          arr[j] = arr[i];
          arr[i] = t;
        }
      }
      
      i++;
      int t = arr[i];
      arr[i] = arr[r];
      arr[r] = t;
      return i;
      
    }
}





















