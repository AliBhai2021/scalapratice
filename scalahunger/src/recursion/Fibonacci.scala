package recursion

object Fibonacci extends App {

  // Using Recursive
  def fibonacci(number:Int):Int={
    number match {
      case x if x ==0 || x==1 => x
      case x =>
        fibonacci(x-1)+fibonacci(x-2)
    }
  }
  println("\n Recursive :"+fibonacci(9))

  // Using Tail Recursion
  def fibonacciTail(number:Int,f1:Int=0,f2:Int=1):Int={
    print(f1+" ")
    number match {
      case num if num == 0 =>  f1
      case num =>
        fibonacciTail(num-1,f2,f1+f2)
    }
  }
  println("fibonacciTail :"+fibonacciTail(9))


  // Using Streams
  def fibonacciInfinite(f1:Int=0,f2:Int=1):Stream[Int]={
    print(f1+" ")
    f1 #:: fibonacciInfinite(f2,f1+f2)
  }
  println("fibonacciInfinite :"+fibonacciInfinite()(9))

  //Sum of Numbers Using Tail Recursive
  def fibonacci(number: Int,res:Int=0):Int={
    number match {
      case x if x==0 => res
      case x =>
        print(x+" ")
        fibonacci(x-1,res+x)
    }
  }
  println("\n Tail Recursive :"+fibonacci(9,0))
}
