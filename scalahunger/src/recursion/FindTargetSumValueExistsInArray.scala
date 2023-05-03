package recursion

object FindTargetSumValueExistsInArray extends App{
  var count =0
  var exists : Boolean=false
  var temp = scala.collection.mutable.LinkedHashSet[Int]()
  def findTaretSum(arr:Array[Int],targetSum:Int, elements:String=""):Boolean={
    count +=1
   // println(count+"  targetSum ="+targetSum)
    targetSum match {
      case x if targetSum == 0 => exists=true; println("elements ="+elements);exists
      case x if x <0 => false
      case x if !exists && temp.add(x) =>
        for(num <- arr if targetSum-num >=0)
          findTaretSum(arr,targetSum-num, elements+num)
        exists
      case _ => false
    }
  }

  println(findTaretSum(Array(4,5,6),21))
  temp.clear()
  temp.foreach(println(_))
  exists =false
  count=0
  println("::::::::::::::::::::::::::::::::::::::::")
  println(findTaretSum(Array(5,5,5,5),100))

  temp.clear()
  temp.foreach(println(_))
  exists =false
  count=0
  println("::::::::::::::::::::::::::::::::::::::::")
  println(findTaretSum(Array(1,2,3),4))


}