package scalapratice.scalapatternmatch

object ScalaPatternMatchPRactice extends App{

  sealed class Animal
  case class Dog(color:String) extends Animal
  case class Cat(color:String) extends Animal

  val x:Animal = Dog("whiteDog")
  val y = Cat("whiteCat")
  x match {
    case Dog(c)=> println(s"Dog color is ${c}")
  }

  println(x.getClass)
  println(y.getClass)

}
