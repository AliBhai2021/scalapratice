object SumOfNumber extends App{
  //Tail recursive
  def sumOfNumber(number:Int,sum:Int=0):Int={
    number match{
      case x if x ==0 => print("m1 :");sum
      case x => sumOfNumber(x-1,sum+x)
    }
  }

  println(sumOfNumber(10,0))
  println(sumOfNumber(10))
  // Recursive
  def sumOfNumber(number:Int):Int={
    number match{
      case x if x ==0 => print("m2 :");0
      case x => x+sumOfNumber(x-1)
    }
  }
  println(sumOfNumber(10))


}
