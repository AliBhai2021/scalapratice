object Fibonacci extends App{

  // Using Recursive
  def fibonacci(number:Int):Int={
    number match {
      case x if x ==0 || x==1 => x
      case x =>
        print(x+" ")
        fibonacci(x-1)+fibonacci(x-2)
    }
  }
  println("\n Recursive :"+fibonacci(10))

  //Using Tail Recursive
  def fibonacci(number: Int,res:Int=0):Int={
    number match {
      case x if x==1 || x==0 => res+x
      case x =>
        print(x+" ")
        fibonacci(x-1,res+x)
    }
  }
  println("\n Tail Recursive :"+fibonacci(10,0))

  def fibonacciInfinite(f1:Int=0,f2:Int=1):Stream[Int]=f1 #:: fibonacciInfinite(f2,f1+f2)

  println("fibonacciInfinite :"+fibonacciInfinite()(10))

}
