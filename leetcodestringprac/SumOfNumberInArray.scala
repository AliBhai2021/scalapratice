package leetcodestringprac

object SumOfNumberInArray extends App{
  def twoSum(nums: Array[Int], target: Int): Array[Int] = {
      val considerValues = nums //.filter(_<= target)
      considerValues.foreach(println(_))
      var result = Array[Int]()
      for(i <- 0 until considerValues.length-1)
        for(j <- i+1 until considerValues.length){
          if(considerValues(i)+considerValues(j)==target)
            result= Array(i,j)
        }
      result
  }

  println("TWO Sums :"+twoSum(Array(1,2,3,4,5), 5).toList)
}
