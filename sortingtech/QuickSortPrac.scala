package sortingtech

object QuickSortPrac extends App {


  def quickSortPartition(arr:Array[Int],low:Int,high:Int):Int={

      val pivot = arr(high) // arr(low)
      println(s"pivot value = $pivot  high= $high")
      var i = low-1

      for( j<- low until high){
        if(arr(j) < pivot){
          i+=1
          swap(arr,i,j)
        }
      }
    swap(arr,i+1,high)
    i+1
  }

  def swap(arr:Array[Int], i:Int, j:Int)
  {
    val temp = arr(i)
    arr(i) = arr(j)
    arr(j) = temp
  }
  def quickSort(arr:Array[Int],low:Int,high:Int):Int={
    if(low<high){
      val pi=quickSortPartition(arr,low,high)
      println("pi ="+pi)
      quickSort(arr,low,pi-1)
      quickSort(arr,pi+1,high)
    }
    1
  }

  val array=Array(10,80,30,90,40,50,70)
  array.foreach(print(_))
  println("array length :"+array.length)
  println(" ")
  quickSort(array,0,array.length-1)
  array.foreach(println(_))
}
