package recursion

object FindTargetSumValueIndexesInArray extends App {

  def findTargetIndexSum(arr:Array[Int], target:Int):String={
    var temp = scala.collection.mutable.LinkedHashMap[Int,Int]()
    var result=""
    for(i<- 0 until arr.length  if(result.isEmpty)){
      println(i)
      if(temp.get(arr(i))==None)
        temp.put(target-arr(i),i)
      else
        result= temp(arr(i))+","+i
    }
    println(temp)
    result
  }

  val arr = Array(5,2,7,8,4)
  println("Indexes : "+findTargetIndexSum(arr,6))
  println(":::::::::::::::::::::::::::::::::::")
  println("Indexes : "+findTargetIndexSumWitTwoLoops(arr,6))

/*  var tempstack = scala.collection.mutable.LinkedHashMap[Int,Int]()
  def findTargetIndexSumRecursive(arr:Array[Int], target:Int, result:String=""):String={
    arr match {
      case a if a.isEmpty => ""
      case a if  tempstack(a.head) => s"${tempstack(a.head)},"
    }
  }*/
  // time complecity O(n2)
  def findTargetIndexSumWitTwoLoops(arr:Array[Int], target:Int):String={
    var result=""
    for( i<- 0 until arr.length){
      for(j<- i until arr.length){
        println("loops "+i+j)
        if(arr(i)+arr(j) == target)
        {
          result=i+","+j
          println("solved")
          return result
        }

      }
    }
    result
  }

}