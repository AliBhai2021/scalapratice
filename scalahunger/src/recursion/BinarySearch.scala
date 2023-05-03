package recursion

object BinarySearch extends App {

  def serach(arr:Array[Int],key:Int):Boolean={

    def searchKey(key:Int, start:Int,end:Int):Boolean={
      start+(end-start)/2 match {
        case mid if mid <=0 || mid >=arr.length => false // to handle outofBound array
        case mid if arr(mid)== key => true
        case mid if arr(mid) > key => searchKey(key, start,mid-1)
        case mid if arr(mid) < key => searchKey(key,mid+1,end)
        //case _ => false
      }
    }
    searchKey(key,0,arr.length-1)
  }

  println("Search :"+serach(Array(1,2,3,4,5,6,7,8,9), 8))
  println("Search :"+serach(Array(1,2,3,4,5,6,7,8,9), 2))
  println("Search :"+serach(Array(1,2,3,4,5,6,7,8,9), -19))
  println("Search :"+serach(Array(1,2,3,4,5,6,7,8,9), 19))

}
