package codesignal

object NumberOfCommonCharacters extends App{

  import scala.collection.mutable
  def solution(s1: String, s2: String): Int = {
    var str1 = mutable.HashMap[Char,Int]()
    var str2 = mutable.HashMap[Char,Int]()

    for(i <- s1){
      str1.put(i, str1.getOrElse(i,0)+1)
    }
    for(i <- s2){
      str2.put(i, str2.getOrElse(i,0)+1)
    }
    println(str1.toList)
    println(str1.getOrElse('a',0))
    var count =0
    for(i <- s1.toCharArray.distinct){
      println(" i ="+i)
      if( math.min(str1.getOrElse(i,0) , str2.getOrElse(i,0))>0)
        count += math.min(str1.getOrElse(i,0) , str2.getOrElse(i,0))
    }
    count
  }

  println( "result :"+solution("haliama","ahammeda"))

  val x = 12215
  val z = x.toString
  var p1=0
  var p2=0
  for(i<- 0 until z.length/2){
    p1 += z(i).toInt
    p2 += z(z.length-i-1)

  }

  if(p1!=p2)
    println("false")
  else
    println("true")

}
