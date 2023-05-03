package strings

import scala.collection.mutable

object FirstRepeatElement extends App{

  def findFirstRepeatElement[T](list: List[T],temp:mutable.LinkedHashSet[T]=mutable.LinkedHashSet[T]()):Option[T]=
    list match {
      case Nil => None
      case x :: xs if !temp.add(x) => Some(x)
      case x :: xs => findFirstRepeatElement(xs,temp)
    }

  println("findFirstRepeatElement :"+findFirstRepeatElement(List('a','b','c','d','c','e','f','a')))
  println("findFirstRepeatElement :"+findFirstRepeatElement(List(1,2,3,4,5,3,2,1,4)))


}
