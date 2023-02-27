package interviewquestions

import scala.util.{Failure, Success}

object ReturnPratice extends App{

  val list = List(1,2,3,4,5,6,7,8,9,10)

  println(returnmethod1(list))
  println(":::::::::::::::::::::::::::::::::::::::::::")
  println(returnmethods1(list))

  def returnmethod1(nums: List[Int]):Int= {
    var res: Either[Int, Int] = Right(0)
    for (i <- nums) {
      i match {
        case 2 => res = Right(2)
        case _ => res = Left(1)
      }

    }
    res match {
      case Right(x) => x
      case Left(x)=> x
    }
  }

  def returnmethods1(nums: List[Int]):Int= {

    def m1(arr: List[Int], x: Int):Int = {
    arr(x) match {
      case x if(x==20) => 4
      case x if(x > arr.length)=> m1(arr, x+1)
      case _ => 0
    }

    }

    m1(nums, 0)
  }
}
