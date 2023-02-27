package codesignal

object PolygonPixels extends App{

  def solution(n: Int): Int = {

    var res =1
    for(i <- 1 to n-1){
      res += i*4
    }
    res

  }

  println(solution(2))

  // 1->1; 2->5; 3->13; 4->25;

}
