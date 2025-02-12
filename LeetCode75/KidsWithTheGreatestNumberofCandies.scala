package LeetCode75

object KidsWithTheGreatestNumberofCandies extends App{
  def kidsWithCandies(candies: Array[Int], extraCandies: Int): List[Boolean] = {
    var maxcandies = candies.max
    println(maxcandies)
    val result = new Array[Boolean](candies.length)
    println("result array properties :"+result.length+"  "+result.size)
//    for (i <- candies.indices){
//      if(candies(i)+extraCandies >= maxcandies)
//        result(i)=true
//      else
//        result(i)=false
//    }
//    result.toList
    candies.map(candy => candy + extraCandies >= maxcandies).toList
  }

  println(kidsWithCandies( Array(2,3,5,1,3), 3))
  println(kidsWithCandies(Array(10,1,10),2))

}
