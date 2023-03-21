object WaysToClimbSteps extends App{
  var result=0
  def climbSteps(target:Int,arr:Array[Int],s:String=""):Int={
    //println("taret :"+target)
    target match {
      case x if x == 0 =>
                          result +=1;
                          println("steps :"+s)
                          result
      case x if x <0 => 0
      case x =>
        for(step <- arr )
          climbSteps(x-step,arr,s+step)
        result

    }
  }

  println(climbSteps(7,Array(2,4,5,8))) //2
}
