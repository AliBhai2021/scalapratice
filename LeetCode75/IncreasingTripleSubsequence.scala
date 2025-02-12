package LeetCode75

object IncreasingTripleSubsequence extends App{
//  def increasingTriplet(nums: Array[Int]): Boolean = {
//    var count = 0
//    for(i<- 0 until nums.length-1){
//      if(nums(i) < nums(i+1)) count +=1
//      if(count == 2) return true
//    }
//    false
//
//  }
//
//  println("Result : "+increasingTriplet(Array(1,2,3,4,5)))
//  println("Result : "+increasingTriplet(Array(5,4,3,2,1)))
//  println("Result : "+increasingTriplet(Array(2,1,5,0,4,6)))
//  println("REsult : "+increasingTriplet(Array(20,100,10,12,5,13)))
//  println("Result : "+increasingTriplet(Array(6,7,1,2)))

  def increasingTriplet(nums: Array[Int]): Boolean = {
    var first = Int.MaxValue
    var second = Int.MaxValue

    for (num <- nums) {
      if (num <= first) {
        first = num
      } else if (num <= second) { // Changed to <= for correct handling
        second = num
      } else {
        return true
      }
    }
    false
  }

  //println("Result : "+increasingTriplet(Array(1,2,3,4,5)))
  //println("Result : "+increasingTriplet(Array(5,4,3,2,1)))
  println("Result : "+increasingTriplet(Array(2,1,5,0,4,6)))
 // println("REsult : "+increasingTriplet(Array(20,100,10,12,5,13)))
 // println("Result : "+increasingTriplet(Array(6,7,1,2)))
}
