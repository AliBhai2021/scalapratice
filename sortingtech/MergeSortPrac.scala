package sortingtech

object MergeSortPrac extends App{

    // Merges two subarrays of arr[].
    // First subarray is arr[l..m]
    // Second subarray is arr[m+1..r]
    def merge(arr:Array[Int], l:Int, m:Int, r:Int)
    {
      println(s"i = $l,  m = $m,  r = $r ")
      // Find sizes of two subarrays to be merged
      var n1 = m - l + 1;
      var n2 = r - m;

      /* Create temp arrays */
      var  L = new Array[Int](n1)
      var R = new Array[Int](n2)

      /*Copy data to temp arrays*/
      for (i <- 0 until  n1)
      L(i) = arr(l + i)
      for (j <- 0 until  n2)
      R(j) = arr(m + 1 + j)

      /* Merge the temp arrays */

      // Initial indexes of first and second subarrays
      var i = 0
      var j = 0

      // Initial index of merged subarray array
      var k = l;
      while (i < n1 && j < n2) {
        if (L(i) <= R(j)) {
          arr(k) = L(i);
          i+=1;
        }
        else {
          arr(k) = R(j)
          j+=1
        }
        k+=1
      }

      /* Copy remaining elements of L[] if any */
      while (i < n1) {
        arr(k) = L(i)
        i+=1
        k+=1
      }

      /* Copy remaining elements of R[] if any */
      while (j < n2) {
        arr(k) = R(j)
        j+=1
        k+=1
      }
      arr.foreach(print(_))
      println("")

    }

    // Main function that sorts arr[l..r] using
    // merge()
    def sort(arr:Array[Int], l:Int, r:Int)
    {
      if (l < r) {
        // Find the middle point
        val m = l + (r - l) / 2;

        // Sort first and second halves
        sort(arr, l, m);
        sort(arr, m + 1, r);

        // Merge the sorted halves
        merge(arr, l, m, r);
      }
      else
        println("skip")
    }

    /* A utility function to print array of size n */
    def printArray(arr:Array[Int])
    {
      val n = arr.length;
      for (i <- 0 until n)
      System.out.print(arr(i) + " ");
      System.out.println();
    }

    // Driver code

      var arr:Array[Int] = Array(12, 11, 13, 5, 6, 7 )

      System.out.println("Given Array");
      printArray(arr);

      sort(arr, 0, arr.length - 1);

      System.out.println("\nSorted array");
      printArray(arr);


}
