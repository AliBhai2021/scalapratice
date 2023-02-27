package codesignal

object TwoArraysSimalar extends  App{

    import scala.collection.mutable
    def solution(a: Array[Int], b: Array[Int]): Boolean = {
      var count=0
      var list = mutable.ListBuffer[Int]()
      for(i<- 0 until b.length ){
        if(a(i)!=b(i))
        {
          count +=1
          list +=i
        }
      }
      println(" Count : "+count)
      if(count !=0 && count !=2) {
        false
      }
      else if(count == 0)

        true
      else
      {
        println("ctrl in else block")
        println(list)
        if(a(list.head)==b(list.last) && a(list.last)==b(list.head))
          true
        else
          false

      }

    }
  println(solution(Array(1, 2,2),Array(2, 1, 1)))
  //println(solution(Array(1,2,3),Array(3,2,1)))
  //println(solution(Array(832, 998, 148, 570, 533, 561, 894, 147, 455, 279),
  //  Array(832, 570, 148, 998, 533, 561, 455, 147, 894, 279)))


}
