package interviewquestions

object Apple extends App{
      //Find first repeated element\
      //scala.collection.mutable.LinkedHashSet[Int]()       => add()
      //scala.collection.mutable.LinkedHashMap[Int,Int]()   => put()
      //Difference between LinkedHashSet VS LinkedHashMap
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
      //--------------------------------------------------------------METHOD::2----------------------------
      import  scala.collection.mutable._
      def findFirstRepeatElement2(list: List[Int],temp:LinkedHashSet[Int]=LinkedHashSet[Int]()):Option[Int]= {
            list match {
                  case Nil => None
                  case x :: xs if !temp.add(x) => Some(x)
                  case x :: xs => findFirstRepeatElement2(xs,temp)
            }

      }
      println("findFirstRepeatElement2 :"+findFirstRepeatElement2(list))
      //--------------->Generic Function
      def findFirstRepeatElement3[T](list: List[T],temp:LinkedHashSet[T]=LinkedHashSet[T]()):Option[T]=
            list match {
                  case Nil => None
                  case x :: xs if !temp.add(x) => Some(x)
                  case x :: xs => findFirstRepeatElement3(xs,temp)
            }

      println("findFirstRepeatElement3 :"+findFirstRepeatElement3(List('a','b','c','d','c','e','f','a')))
      //===============================================================================================
      val temp = scala.collection.mutable.LinkedHashMap[Int,Int]()
      val x = for(i<-list if temp.put(i, temp.getOrElse(i, 0)+1) != None) yield (i) //INSERTION ORDER
      println(" x = "+x)
      println(" Repeated First Element  = "+x.head)
      println("temp = "+temp)

      println("put(100,11) returns ="+temp.put(100,11))
      println("put(100,21) returns ="+temp.put(100,21))
      println("put(100,31) returns ="+temp.put(100,31))
      println(temp)

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
      println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::")
      val temp2 = scala.collection.mutable.LinkedHashSet[Int]()
      println("temp2 : "+temp2.add(100))
      println("temp2 : "+temp2.add(100))
      println("temp2 : "+temp2.add(101))
      println("temp2 : "+temp2.add(102))
      println(temp2)


}
