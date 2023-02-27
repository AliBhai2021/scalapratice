package interviewquestions

object MissingPositiveNumber extends App{

  println(firstMissingPositive(Array(3,4,-1,1)))
  println(firstMissingPositive(Array(1,2,4,5)))
  println(firstMissingPositive(Array(7,8,9,11,12)))
  println(firstMissingPositive(Array(1)))
  println(firstMissingPositive(Array(1,2,3,4,5,6,7,8)))

  def firstMissingPositive(nums: Array[Int]): Int = {
    var i=0
    while(i<nums.length){
      var correctvalue = nums(i)-1

      if(nums(i)>0 && nums(i) <= nums.length && nums(i) != nums(correctvalue))
        swap(nums,i,correctvalue)
      else
        i +=1
    }

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
