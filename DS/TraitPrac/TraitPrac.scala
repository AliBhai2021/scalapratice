package DS.TraitPrac

object TraitPrac extends App{

  trait Animal
  trait SomeTin

  case class Dog(name:String) extends Animal
  case class Cat(namse:String) extends Animal
  val s1 = Array(Dog("dog"),Cat("cat"))
  println("s1 :: "+s1.toString)
  println("class :: "+s1.getClass)
  println("s1(0) :: "+s1(0).getClass)
  println("s1(0) :: "+s1(1).getClass)

  s1(0) match {
    case x:Animal => println(s"${x} is a Animal")
    case _ => println("Nothing")
  }
//--------------------------------------------------------------------------
  case class Dog1(name:String) extends Animal with SomeTin
  case class Cat2(namse:String) extends SomeTin with Animal
  case class Dog2(name:String)
  val s2 :Animal = Dog1("dog1")
  //val s2 = Dog2("dog1")
  //val s2 :SomeTin = Dog1("dog1")

  s2 match {
    case x:SomeTin => println(s"${x} is a SomeTin")
    case x:Animal => println(s"${x} is a Animal")
    case _ => println("Nothing")
  }

  val s3: Animal=Cat2("cat2")

  s3 match {
    case x:SomeTin => println(s"${x} is a SomeTin")
    case x:Animal => println(s"${x} is a Animal")
    case _ => println("Nothing")
  }
//-----------------------------------------------------------------------------

}
