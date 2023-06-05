package ArrayPratice

import scala.collection.mutable

object FindDuplicateElemetsInArray extends App{

  def findDuplicateElemetsInUnSortedArray(arr:Array[Int]):Unit={
    val tempHash = mutable.HashSet[Int]()
    for( i<- arr){
      if(!tempHash.add(i))
        println("Duplicate element = "+i)
    }
  }

  val arr = Array(8,3,6,4,6,5,6,8,2,7)
  findDuplicateElemetsInUnSortedArray(arr)
  //------------------------------------------------------------------

  val arr2 = Array(3,6,8,8,10,12,15,15,15,20)
  findDuplicateElemetsInSortedArray(arr2)

  def findDuplicateElemetsInSortedArray(arr:Array[Int]):Unit = {
    for( i <- 0 until arr.length-2){
      if(arr(i+1)-arr(i) == 0)
        println("Found duplicate element = "+arr(i))
    }
  }

}
