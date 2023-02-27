package interviewquestions

object FindAllDuplicates extends App{

  println(findDuplicates(Array(1,2,3,2,5,6,3))) //[4,3,2,7,8,2,3,1]
  println(findDuplicates(Array(1,4,2,2,3,3,2,7,8,1)))
  def findDuplicates(nums: Array[Int]): List[Int] = {
    var i=0
    var list:List[Int]= List()
    while(i<nums.length){

      var correctValue = nums(i)-1
      if(nums(i)!=nums(correctValue))
        swap (nums, i , correctValue)
      else
        i +=1
    }
    println("array elements : "+nums.toList)
    for(j <-0 until nums.length){
      if(nums(j) != j+1)
        list ::= nums(j)
    }
    list
  }

  def swap(arr:Array[Int], a:Int, b:Int):Array[Int]={
    var temp = arr(a)
    arr(a) = arr(b)
    arr(b)= temp
    arr
  }

}
