package scalapratice.innerClassPrac

import scala.collection.mutable.ArrayBuffer

object InnerClassPrac extends App{
//The concept of a “class within a class” is different in Scala than in Java
  val obj = new Box
  obj.addThing("Pen")
  obj.addThing("campus")
  println(obj.list)

  obj.things.foreach(println(_))
  //------------------------------------
    //println( OuterObject.OuterObjectClass().x)
  println( new OuterObject.OuterObjectInnerClass().x)
  println(new OuterClass().OuterClassInnerObject.x)
}

class Box{
  case class Thing(name:String)
  var things = scala.collection.mutable.ArrayBuffer[Thing]()
  def addThing(name:String):Unit= things += Thing(name)
  def list:ArrayBuffer[Thing]= things
}

object OuterObject{
  class OuterObjectInnerClass{
    val x=10
  }
}

class OuterClass{
  object OuterClassInnerObject{
    val x =100
  }
}