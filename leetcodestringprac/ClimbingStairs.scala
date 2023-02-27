package leetcodestringprac

object ClimbingStairs extends App{
  def climbStairs(n: Int): Int = {
    // n match {
    //     case x if x==0 | x==1 => 1
    //     case _ => climbStairs(n-1)+climbStairs(n-2)
    // }
    println("n : "+n)
    val arr=new Array[Int](n+1)
    println("arr size :"+arr.size+ "  "+arr.length)
    arr(0)=1
    arr(1)=1
    for(x <- 2 to n){
      arr(x)=arr(x-1)+arr(x-2)
    }
    arr(n)
  }


  println(climbStairs(2))

}
