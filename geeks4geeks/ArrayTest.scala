package geeks4geeks

object ArrayTest extends App{

  val arr = Array(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16)
  val subArraySize =4
  val result = Array.ofDim[Int](arr.size/subArraySize,subArraySize)
  for(i<- 0 until arr.size/subArraySize){
    var count =i
    for(j <- 0 until subArraySize){
      result(i)(j)=arr(count)
      count = count+subArraySize
    }

  }
  arr.foreach(println(_))

  for(i<- 0 until arr.size/subArraySize){
    var count=0
    for(j <- 0 until subArraySize){
    print(" "+result(i)(j))
      count = count+result(i)(j)
    }
    print( "  "+count)
    println()
  }
}
