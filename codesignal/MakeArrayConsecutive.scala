package codesignal

// missing numbers in array
object MakeArrayConsecutive extends App {

  def solution(statues: Array[Int]): Int = {

    for (i <- 0 until statues.length) {

      var flag=false
      for (j <- 0 until statues.length - i - 1) {
        if (statues(j) > statues(j + 1)) {
          val temp = statues(j)
          statues(j) = statues(j + 1)
          statues(j + 1) = temp
          flag=true
        }

      }
      //if(flag==false)
    }

    statues.foreach(println(_))
    println("length : "+statues.length)
    println("element : "+statues(statues.length-1))
    println("element_1: "+statues(0))
    println("pos : "+(statues.length-1))

     statues(statues.length - 1) - (statues.length - 1)- statues(0)

  }

  println(solution(Array(6,2,3,8)))
}