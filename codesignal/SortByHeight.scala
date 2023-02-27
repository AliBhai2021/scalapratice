package codesignal

object SortByHeight extends App{

  def solution(a: Array[Int]): Array[Int] = {
    for(i <- 0 until a.length ) {
      var k=0

      for (j <- 0 until a.length - i-1  if k == j) {
        println(" i ="+i +"  j= "+j+"  k ="+k + "  a(k) ="+a(k))
        if (a(k) != -1) {
          println("array size : "+a.length)
          //while (a(k + 1) == -1) {
          while (a.length-1>k && a(k + 1) == -1) {
            println("increment k value "+(k+1))
            k += 1
          }
          println(" k = "+k+"  j = "+j)
          if (a.length-1>k && a(j) > a(k + 1)) {
            println("swap "+a(j) +"  "+a(k+1))
            val temp = a(j)
            a(j) = a(k + 1)
            a(k + 1) = temp
          }
          k += 1
        }
        else {
          println("Escape from if block")
          k += 1
        }
      }
    }
    a

  }

  solution(Array(-1, 150, 190, 170, -1, -1, 160, 180)).foreach(println(_))
  //solution2(Array(4, 2, 9, 11, 2, 16)).foreach(println(_))
  //solution2(Array(4, 2, 9, 11, 2, 16)).foreach(println(_))
  //solution2(Array(-1, 11,9,2, -1, -1)).foreach(println(_))

}
