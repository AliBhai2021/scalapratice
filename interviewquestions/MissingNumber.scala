package interviewquestions

object MissingNumber extends App{

  println(findMissingNumber(Array(9,6,4,2,3,5,7,0,1)))
  println(findMissingNumber(Array(0,1)))

  def findMissingNumber(nums: Array[Int]): Int = {
    var i=0
    var list:List[Int]= List()
    while(i<nums.length){
      var checkedValue = nums(i)
      if(nums(i)< nums.length && nums(i)!=nums(checkedValue))
        swap (nums, i , checkedValue)
      else
        i +=1
    }

    for(j <-0 until nums.length){
      if(nums(j) != j)
        return j
    }
    // else array has 0,1,2 => return 3
    nums.length
  }

  def swap(arr:Array[Int], a:Int, b:Int):Array[Int]={
    var temp = arr(a)
    arr(a) = arr(b)
    arr(b)= temp
    arr
  }
}
