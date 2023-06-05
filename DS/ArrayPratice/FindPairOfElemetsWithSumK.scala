package ArrayPratice

import scala.collection.mutable

object FindPairOfElemetsWithSumK extends App{

  def findPairOfElemetsWithSumKFromUnSortedArray(arr:Array[Int],K:Int):Unit={
    val tempHash = mutable.HashMap[Int,Int]()
    for(i<- arr){
      if(tempHash.get(K - i).isDefined)
        println("Pair = "+ i+","+ (K-i))
      tempHash.put(i, K-i)
    }
  }

  val arr = Array(6,3,8,10,16,7,5,2,9,14)
  findPairOfElemetsWithSumKFromUnSortedArray(arr,10)

  //==============================================================================
  def findPairOfElemetsWithSumKFormSortedArray(arr:Array[Int],K:Int):Unit= {
     var i = 0
     var j = arr.length-1
      while (i < j ){
        if(arr(i)+arr(j) == K) {
          println("Method#2 Pairs = "+ arr(i)+","+arr(j))
          i +=1; j -=1;
        } else if (arr(i)+arr(j)>K)
          j -=1
        else if (arr(i)+arr(j) < K)
          i +=1
      }
  }

  val arr2 = Array(2,3,5,6,7,8,9,10,14,16)
  findPairOfElemetsWithSumKFormSortedArray(arr2,10)

}
