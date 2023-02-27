package codesignal

import scala.collection.mutable.ArrayBuffer

object ArrayOfArray extends App{

  val arr = Array(Array(1, 1, 1, 0),Array(0, 5, 0, 1),Array(2, 1, 3, 10))

  def solution(matrix: Array[Array[Int]]): Int = {
    var count =0
    for(i <- 0 until matrix(0).length){
      var flag = true
      for(j<- 0 until matrix.length if flag) {

        if(matrix(j)(i) != 0)
          count += matrix(j)(i) // (1 + 1+5+1 + 1)
        else
          flag = false
      }
    }
    count
  }


  println(solution(arr))
}
