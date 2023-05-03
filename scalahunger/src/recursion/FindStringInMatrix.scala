package recursion

object FindStringInMatrix extends App{
  val arr = Array(Array('m','o','n','e'),
                  Array('b','n','o','y'),
                  Array('c','e','f','g'),
                  Array('d','y','e','h'))
  val row = arr.length-1
  val col = arr(0).length-1
  println("Size = "+row+"X"+col +" ")
  var results=List("")
  val key="money"
  println(findString(arr,key))

  def findString(arr: Array[Array[Char]], key:String,srow:Int=0, scol:Int=0, res:String="", pos:String=""):Boolean={
    //println("row,col = "+srow+","+scol)
    (srow,scol) match {
      case (x,y) if res.toString == key => println("result = "+res +"     positions :"+pos); true
      case (x,y) if res.length>0  && res(res.length-1) != key(res.length-1) => false
      case (x,y) if x < 0 || y < 0 => false
      case (x,y) if x >= arr.length || y >= arr.length => false
      case (x,y) =>
          findString(arr,key,srow-1,scol,res+arr(x)(y),pos+s"(${x},${y})") ||
          findString(arr,key,srow+1,scol,res+arr(x)(y),pos+s"(${x},${y})") ||
          findString(arr,key,srow,scol-1,res+arr(x)(y),pos+s"(${x},${y})") ||
          findString(arr,key,srow,scol+1,res+arr(x)(y),pos+s"(${x},${y})")

    }

  }

}