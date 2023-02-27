package interviewquestions

object ClassTest extends App {

  val obj = new TestA
  obj.m1

  println("===================================")
  val obj2= new TestB
  obj2.m1
}

class TestA{
  println("Class A is called")
  def m1 = println("m1 is called")

  {
    println("block is called")
  }

}

class TestB extends TestA
