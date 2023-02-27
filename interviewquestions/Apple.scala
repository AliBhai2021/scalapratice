package interviewquestions

object Apple extends App{
      //Find first repeated element
      val list= List(1,3,5,2,4,6,5,3,11,12,13)
      println("list(0) = "+list(0))

      def findFirstRepeatElement(list: List[Int]):Int= {
            import scala.util.control.Breaks._
            val temp2 = scala.collection.mutable.LinkedHashSet[Int]()
            var duplicateElement = -1
            breakable {
                  for (i <- list) {
                        if (!temp2.add(i)) {
                              duplicateElement = i
                              break()
                        }
                  }
            }
            duplicateElement
      }

      println("findFirstRepeatElement :"+findFirstRepeatElement(list))
      //===============================================================================================
      val temp = scala.collection.mutable.LinkedHashMap[Int,Int]()
      val x = for(i<-list if temp.put(i, temp.getOrElse(i, 0)+1) != None) yield (i) //INSERTION ORDER
      println(" x = "+x)
      println(" Repeated First Element  = "+x.head)
      println("temp = "+temp)

      println("put returns ="+temp.put(100,1))
      println("put returns ="+temp.put(100,2))
      println("put returns ="+temp.put(100,3))
      var flag = true
      temp.clear()
      val logic2 = for(i<-list if temp.put(i,0).nonEmpty && flag) yield {
            flag = false
            i}
      println("logic2 = "+logic2)

      temp.clear()

      val logic3 = for(i<-list if temp.put(i,0).nonEmpty && flag) yield {
            flag = false
            i}
      println("logic3 = "+logic3)

      val temp2 = scala.collection.mutable.LinkedHashSet[Int]()
      println("temp2 : "+temp2.add(100))
      println("temp2 : "+temp2.add(100))


}
