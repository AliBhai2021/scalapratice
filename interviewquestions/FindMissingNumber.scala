package interviewquestions

import scala.collection.mutable.ArrayBuffer

object FindMissingNumber extends App{

  val inputarr = Array(3,2,7,1,6,5)
  println("Missing NUmber : "+sortNumber(inputarr).mkString(","))
  inputarr.foreach(println(_))
  def sortNumber(arr:Array[Int]):ArrayBuffer[Int] ={
    var i=0;
    while(i<arr.length){
      var correctValue = arr(i)-1
      println("arr(i)!=arr(correctValue) "+arr(i) +"  correctvalue "+correctValue)
      if(arr(i)<arr.length && arr(i)!=arr(correctValue))
        swap(arr,i,correctValue)
      else
        i +=1
    }

    //missing numbers
    var result = ArrayBuffer[Int]()
    for(j <- 0 until arr.length){
      if(arr(j) != j+1)
        result += j+1
    }
    result // for return (duplicate num, missing num) => return (arr(j), j+1)
  }

  def swap(arr:Array[Int], first:Int,second:Int):Array[Int]={
    var temp = arr(first)
    arr(first) = arr(second)
    arr(second)=temp
    arr
  }

}
