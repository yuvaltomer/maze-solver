

/** 
 * Implementation of the Union-Find ADT. 
 */ 
public class UnionFind { 
 
   private int up[]; 
   private int weight[]; 
   int numSets; 
 
 
   /** 
    * Constructor - initializes up and weight arrays. 
    * 
    * @param numElements initial number of singleton sets. 
    */ 
   public UnionFind (int numElements) { 
		up = new int[numElements];
      weight = new int[numElements];
      for(int i = 0; i < numElements; i++) {
         up[i] = -1;
         weight[i] = 1;
      }
   } 
 
   /** 
    * Unites two sets using weigthed union. 
    * 
    * @param i representative of first set. 
    * @param j representative of second set. 
	* If i or j are not representative elements - throw an IllegalArgumentException
    */ 
   public void union (int i, int j) { 
		if(up[i] != -1 || up[j] != -1)
         throw new IllegalArgumentException();
      if(j == i)  return;
      int totalWeight = weight[i] + weight[j];
      if(weight[i] < weight[j]) {
         up[i] = j;
         weight[j] = totalWeight;
         return;
      }
      up[j] = i;
      weight[i] = totalWeight;
   } 
 
   /** 
    * Finds the set representative, and applies path compression. 
    * 
    * @param i the element to search. 
    * @return the representative of the group that contains i. 
    */ 
   public int find (int i) { 
		int root = i;
      while(up[root] != -1)
         root = up[root];
      if(i != root) {
         int parent = up[i];
         while(parent != root) {
            up[i] = root;
            i = parent;
            parent = up[parent];
         }
      }
      return root;
   } 
 
   /** 
    * Find the current number of sets. 
    * 
    * @return the number of set. 
    */ 
   public int getNumSets() { 
		int sum = 0;
      for(int i = 0; i < up.length; i++) {
         if(up[i] == -1)
            sum++;
      }
		return sum;
   } 
 
   /** 
    * Prints the contents of the up array. 
    */ 
   private void debugPrintUp() { 
 
      System.out.print ("up:     "); 
      for (int i = 1; i < up.length; i++) 
         System.out.print (up[i] + " "); 
      System.out.println (""); 
   } 
 
   /** 
    * Prints the results of running find on all elements. 
    */ 
   private void debugPrintFind() { 
 
      System.out.print ("find:   "); 
      for (int i = 1; i < up.length; i++) 
         System.out.print (find (i) + " "); 
      System.out.println (""); 
   } 
 
   /** 
    * Prints the contents of the weight array. 
    */ 
   private void debugPrintWeight() { 
 
      System.out.print ("weight: "); 
      for (int i = 1; i < weight.length; i++) 
         System.out.print (weight[i] + " "); 
      System.out.println (""); 
   } 
 
   /** 
    * Various tests for the Union-Find functionality. 
    * 
    * @param args command line arguments - not used. 
    */ 
   public static void main (String[] args) { 
 
      UnionFind uf = new UnionFind (10); 
 
      uf.debugPrintUp(); 
      uf.debugPrintFind(); 
      uf.debugPrintWeight(); 
      System.out.println ("Number of sets: " + uf.getNumSets()); 
      System.out.println (""); 
 
      uf.union (2, 1); 
      uf.union (3, 2); 
      uf.union (4, 2); 
      uf.union (5, 2); 
 
      uf.debugPrintUp(); 
      uf.debugPrintFind(); 
      uf.debugPrintWeight(); 
      System.out.println ("Number of sets: " + uf.getNumSets()); 
      System.out.println(); 
 
      uf.union (6, 7); 
      uf.union (8, 9); 
      uf.union (6, 8); 
 
      uf.debugPrintUp(); 
      uf.debugPrintFind(); 
      uf.debugPrintWeight(); 
      System.out.println ("Number of sets: " + uf.getNumSets()); 
      System.out.println(); 
 
      uf.find (8); 
 
      uf.debugPrintUp(); 
      uf.debugPrintFind(); 
      uf.debugPrintWeight(); 
      System.out.println ("Number of sets: " + uf.getNumSets()); 
      System.out.println(); 
   } 
} 
