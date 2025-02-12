package LeetCode75

object ProductOfArrayExceptSelf extends App{

  def productExceptSelf(nums: Array[Int]): Array[Int] = {
    val n = nums.length
    val answer = Array.fill(n)(1)

    // Calculate left products
    var left = 1
    for (i <- nums.indices) {
      answer(i) *= left
      println(s"i = ${i}  --> ${answer.toList}")
      left *= nums(i)
    }
    println("+++++++++++++++++++++++++++++++++++++++++++")
    // Calculate right products
    var right = 1
    for (i <- (n - 1) to 0 by -1) {
      answer(i) *= right
      println(s"i = ${i}  --> ${answer.toList}")
      right *= nums(i)
    }

    answer
  }

  println("Result : "+productExceptSelf(Array(3,2,5,4)).toList)


  def productExceptSelf2(nums: Array[Int]): Array[Int] = {
    val n = nums.length
    val answer = Array.fill(n)(1)
    println("TEST CASE#2")
    // Calculate right products
    var right = 1
    for (i <- (n - 1) to 0 by -1) {
      answer(i) *= right
      println(s"i = ${i}  --> ${answer.toList}")
      right *= nums(i)
    }
    println("+++++++++++++++++++++++++++++++++++++++++++")
    // Calculate left products
    var left = 1
    for (i <- 0 until n) {
      answer(i) *= left
      println(s"i = ${i}  --> ${answer.toList}")
      left *= nums(i)
    }
    answer
  }
  println("Result : "+productExceptSelf2(Array(3,2,5,4)).toList)
}
