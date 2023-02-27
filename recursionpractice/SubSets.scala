package recursionpractice

import scala.collection.mutable.{ArrayBuffer, ListBuffer}

object SubSets extends App{

  println(findSubSets(List(3,4,1)))
  println(findSubSetsWithoutDuplicate(List(1,2,3,2)))
  println(List(3,2,5,6)(0))
  def findSubSets(arr:List[Int]):Unit= {
    var result = List(List[Int]())
    for (a <- arr) {
      println("a = "+a)

      for (i <- 0 until result.length) {
        result ++= List(result(i) ++ List(a)) // logic
      }
    }

    println("resArr =" + result)
  }

    def findSubSetsWithoutDuplicate(arr:List[Int]):Unit={
      var result = List(List[Int]())
      for((a,b) <- arr.zipWithIndex){
        val n = result.length
        val start = if(b>0 && arr.slice(0,b-1).contains(a)) 0 else n-1
        for(i<-start until n){
           val inner = List(result(i) ++ List(a))
          result ++= inner
        }
      }

      println("resArr =" + result)
  }

}
