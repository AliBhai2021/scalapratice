package codesignal

object AlmostIncreasingSequence extends App{

  def solution(sequence: Array[Int]): Boolean = {

    var count = 0
    for (i <- 0 until sequence.length-1 if count<2) {

      if (sequence(i) >= sequence(i+1) ){
        //if(i>0 )
        if(i>0 && sequence(i-1) >= sequence(i+1)) //logic for => Array(1, 2, 3, 4, 99, 5, 6),Array(10, 1, 2, 3, 4, 5)
          sequence(i+1) = sequence(i)
        count += 1
      }
    }
    (count <= 1)
  }



  println(solution(Array(1,3,2,1)))
  //println(solution3(Array(1, 2, 3, 4, 99, 5, 6))) //true
  //println(solution3(Array(1, 2, 5, 3, 5))) //
  //println(solution2(Array(1, 2, 3, 4, 5, 3, 5, 6))) //false
  //println(solution2(Array(1,2,1,2)))
  //println(solution(Array(10, 1, 2, 3, 4, 5, 10,20,10)))
  //println(solution2(Array(40, 50, 60, 10, 20, 30))) // false
  //println(solution(Array(10, 1, 2, 3, 4, 5)))

}
