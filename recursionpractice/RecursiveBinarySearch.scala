package recursionpractice

object RecursiveBinarySearch extends App{

  val arr = Array(2,3,4,5,6,7,8,9)
  println(binarySearch(arr,9,0,arr.length))
  println("++++++++++++++++++++++++++++++++++++++++++")
  println(binarySearch(arr,1,0,arr.length))
  println(binarySearch(arr,10,0,arr.length))

  def binarySearch(a:Array[Int], key:Int, s:Int, e:Int):Boolean ={
    Thread.sleep(1000)
    s+(e-s)/2 match {
        case mid if mid <= 0 || mid >= a.length => false
        case mid if a(mid) < key =>
          binarySearch(a, key, mid+1, a.length)
        case mid if a(mid) > key =>
          binarySearch(a, key, 0, mid-1)
        case mid if a(mid) == key => true
      }

  }

}
