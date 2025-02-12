package codesignal

import scala.collection.mutable.{ArrayBuffer, ListBuffer}

object FindLongestStringsInArray extends App{

  def solution(inputArray: Array[String]): Array[String] = {
    var arr = ListBuffer[(String,Int)]()
    var max = 0
    for(i<- 0 until inputArray.length){
      arr += ((inputArray(i),inputArray(i).size))
      max = if(max < inputArray(i).size) inputArray(i).size else max
    }
    arr.filter(_._2 == max).map(_._1).toArray
  }
  println("Longest String :")
  solution(Array("aba", "aa", "ad", "vcd", "aba")).foreach(println(_))

  def solution2(inputArray: Array[String]): Array[String] = {
    var arr = Map[String,Int]()
    var max = 0
    for(i<- 0 until inputArray.length){
      arr += ((inputArray(i),inputArray(i).size))
      max = if(max < inputArray(i).size) inputArray(i).size else max
    }
    arr.filter(_._2 == max).keys.toArray
  }
  println("Longest String :")
  solution2(Array("aba", "aa", "ad", "vcd", "aba")).foreach(println(_))


}
