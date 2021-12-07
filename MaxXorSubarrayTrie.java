
class Solution{

    static int maxSubarrayXOR(int N, int arr[]){
        // code here
        
        Trie tr = new Solution().new Trie();
        
        int curr_xor = 0;
        int max_xor = Integer.MIN_VALUE;
        
        for(int i = 0; i<N ; i++)
        {
            curr_xor = curr_xor ^ arr[i];
            tr.insert(curr_xor);
            max_xor = Math.max(max_xor,tr.query(curr_xor));
        }
        
        return max_xor;
        
    }
    
    class TrieNode
    {
        public int value;
        public TrieNode[] arr = new TrieNode[2];
        
        public TrieNode()
        {
            value = 0;
            arr[0] = null;
            arr[1] = null;
        }
        
    }
    
    class Trie
    {
        TrieNode root;
        int max_size = 32;
        
        public Trie()
        {
            root = new TrieNode();
            insert(0);
        }
        
        public void insert(int xorVal)
        {
            TrieNode temp = root;
            for(int i = max_size-1; i>=0;i--)
            {
                int val = (xorVal & (1<<i)) >= 1 ? 1 : 0;
                if(temp.arr[val]==null)
                {
                    temp.arr[val] = new TrieNode();
                }
                temp = temp.arr[val];
            }
            
            temp.value = xorVal;
        }
        
        public int query(int xorVal)
        {
            TrieNode temp = root;
            for(int i = max_size-1; i>=0;i--)
            {
                int val = (xorVal & (1<<i)) >= 1 ? 1 : 0;
                if(temp.arr[1-val]!=null)
                {
                    temp = temp.arr[1-val];
                }
                else
                {
                    temp = temp.arr[val];
                }
            }
            
            return temp.value^xorVal;
        }
        
        
    }
}
