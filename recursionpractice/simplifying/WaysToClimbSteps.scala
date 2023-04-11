package simplifying

import scala.collection.mutable

object WaysToClimbSteps extends App{
  var result=0
  var count=0
  var check = mutable.LinkedHashMap[Int,Int]()
  def climbSteps(target:Int,arr:Array[Int],s:String=""):Int={
    target match {
      case x if x == 0 =>
                          result +=1; count +=1
                          println(count +" steps :"+s)
                          check
                          result
      case x if x <0 => 0
      case x =>
        for(step <- arr )
          climbSteps(x-step,arr,s+step)
        result

    }
  }

  println(climbSteps(7,Array(2,4,5,8,3))) //2
  println("::::::::::::::::::::::::::::::::::::::::::")
  count =0
  result=0
  println(climbSteps(10,Array(1,2,3,4,5,6,7,8,9)))
}
