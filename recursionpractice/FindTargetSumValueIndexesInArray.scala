object FindTargetSumValueIndexesInArray extends App {

  def findTargetIndexSum(arr:Array[Int], target:Int):String={
    var temp = scala.collection.mutable.LinkedHashMap[Int,Int]()
    var result=""
    for(i<- 0 until arr.length  if(result.isEmpty)){
      println(i)
      if(temp.get(arr(i))==None){
        temp.put(target-arr(i),i)
      }
      else{
        result= temp.get(arr(i)).get+","+i
      }
    }
    println(temp)
    println(result)
    result
  }


  val arr = Array(5,2,4,7,8)
  println(findTargetIndexSum(arr,6))
  println(":::::::::::::::::::::::::::::::::::")
  println(findTargetIndexSumWitTwoLoops(arr,6))

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
