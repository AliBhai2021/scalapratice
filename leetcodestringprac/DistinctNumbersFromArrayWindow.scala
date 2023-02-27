package leetcodestringprac

object DistinctNumbersFromArrayWindow extends App{

  val array = Array(1,1,1,2,3,4,4,5,5)
  val ws = 3
  // Method #1
  var result = new Array[Int](array.length-ws)

  for(i <- 0 until array.length-ws){
    val disinctnum = for(j <- i to i+ws-1)yield (array(j))
    result(i)=disinctnum.toSet.size
  }
  println(result.toList)

  // method #2
  var result2 = new Array[Int](array.length-ws)
  for(i <- 0 until array.length-ws){
    val h = scala.collection.mutable.HashMap[Int, Int]();
    for(j <- i to i+ws-1)
      h.put(array(j), h.getOrElse(array(j),0)+1)
      println(h)
    result2(i)=h.keys.size
  }
  println(result2.toList)



}
