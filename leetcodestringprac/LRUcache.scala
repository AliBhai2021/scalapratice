package leetcodestringprac

object LRUcache  extends App{

  var l = List(2,1)
  println(l)

  l= l ::: List(3)
  println(l)


  var list :List[Int]=Nil
  val arr = Array(1,2,1,1,3,2,4)
  val cs =2
  var w = 0
  val h = scala.collection.mutable.HashMap[Int,Int]()
  for(i <- 0 until arr.length) {
    if (w < cs ) {
      if (h.getOrElse(arr(i), -1) < 0) {
        h.put(arr(i), arr(i))
        list ::= arr(i)
        println(list)
        w +=1
      }
    }
    else {
      if(h.getOrElse(arr(i), -1) <0) {
        list= List(arr(i)) ::: list.init
        println(list)
      }
      else{
        if(list.last == arr(i)){
          list= List(arr(i)) ::: list.init
        }
        println(list)
      }
    }
  }
}
