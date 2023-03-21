object BinarySearch extends App {

  def binarySearch(arr:Array[Int],key:Int):Boolean={
    def search(low: Int, high: Int):Boolean= {
      //for(i <- low to high)
       // print(arr(i)+" ")
       // println("")
      val mid = low + (high - low) / 2
      //println("mid ="+mid+"   Value :"+arr(mid)+ "  low="+low+"  High="+high)
      mid match {
        case x if arr(x) == key => true
        case x if low >= high => println("low :"+low+"   high :"+high); false
        case x if arr(x) < key => search(mid + 1, high)
        case x if arr(x) > key => search(low, mid)
      }
    }
    search(0,arr.length-1)
  }

  println(binarySearch(Array(1,2,3,4,5,6,7,8,9),9))
  println(binarySearch(Array(1,2,3,4,5,6,7,8,9),1))
  println(binarySearch(Array(1,2,3,4,5,6,7,8,9),10))
  println(binarySearch(Array(1,2,3,4,5,6,7,8,9),5))
  println(binarySearch(Array(1,2,3,4,5,6,7,8,9),4))
  println(binarySearch(Array(1,2,3,4,5,6,7,8,9),-5))
}
