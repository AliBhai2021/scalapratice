object RatInMazePath extends App{

  val arr = Array(Array(0,0,1,1),
                  Array(0,1,0,0),
                  Array(0,0,0,0),
                  Array(0,0,1,0))
  val row = arr.length-1
  val col = arr(0).length-1
  println("Size = "+row+"X"+col +" ")
  var results=List("")

  println(findPath(arr,row,col))

  def findPath(arr: Array[Array[Int]], row:Int=0, col:Int=0, res:String=""):List[String]={
    (row,col) match {
      case (x,y) if x == 0 && y == 0 => results = res::results; results
      case (x,y) if x < 0 || y < 0 =>Nil
      case (x,y) if x > arr.length-1 || y > arr.length-1 => Nil
      case (x,y) if arr(x)(y) ==1 || arr(x)(y) ==9 => Nil
      case (x,y) =>
        arr(x)(y)=9 // if remove ten oes infinite
        findPath(arr,row-1,col,res+"U")
        findPath(arr,row,col-1,res+"L")
        findPath(arr,row+1,col,res+"D")
        findPath(arr,row,col+1,res+"R")
        arr(x)(y)=0
        results



    }

  }

}
