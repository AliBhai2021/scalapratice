package StackPratice

object StackDriver extends App{

  val obj = new CreateStackMethods
  obj.pushElement(10)
  obj.pushElement(20)
  obj.pushElement(30)
  println(" obj = "+obj.head)
  obj.popElement()

  println("obj = "+obj.head)
}
