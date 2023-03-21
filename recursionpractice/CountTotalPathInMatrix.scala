object CountTotalPathInMatrix extends App{
  // grid traveller

  def gridTraveller(m:Int,n:Int):Int={  //m & n are matrix size m-rows, n-columns
    (m,n) match {
      case (x,y) if x==0 || y==0 =>
        println("x ="+x+"   y="+y);   0
      case (x,y) if x ==1 && y==1 =>
        println("x ="+x+"   y="+y);   1
      case (x,y) =>
        println("x ="+x+"   y="+y)
        gridTraveller(x-1,y)+gridTraveller(x,y-1)
    }
  }
  println(gridTraveller(2,3)) //3
  println(gridTraveller(4,3)) //10


}
