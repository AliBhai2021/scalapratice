package questions

object MissingPositiveNumber extends App{

  println("firstMissingPositive : "+firstMissingPositive(Array(3,4,-1,1,-2)))


  def firstMissingPositive(nums: Array[Int]): Int = {
    var i=0
    while(i<nums.length){
      var correctvalue = nums(i)-1
      if(nums(i)>0 && nums(i) <= nums.length && nums(i) != nums(correctvalue))
        swap(nums,i,correctvalue)
      else
        i +=1
    }
    nums.foreach(print(_))
    println(" ")
    for(j <- 0 until nums.length){
      if(nums(j) != j+1)
        return j+1
    }

    nums.length+1
  }

  def swap(arr:Array[Int], a:Int, b:Int):Array[Int]={
    var temp = arr(a)
    arr(a) = arr(b)
    arr(b)=temp
    arr
  }

}
