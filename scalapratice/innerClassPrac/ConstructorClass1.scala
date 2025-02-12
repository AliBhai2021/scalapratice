package scalapratice.innerClassPrac

class TestA {
  def this(v1:String) {
    this()
    println(s"TESTA constructor is called : ${v1}")
  }
  println("........... TestA Called")

}


class TestB extends TestA {
  def this(v1:String) {
    this()
    println(s"TESTB constructor is called : ${v1}")
  }
  println("........... TestB Called")
}

// class TestE extends  TestC with TestD //--Note multiple inheritance not allowed on class, it achieved by trait

object ConstructorClass1 extends App{
  //val obj1 = new TestD // A <- C <- D
  val obj2 = new TestB("Lee")

  val obj3 = new TestX("ALI")

}


class TestZ(v1:String) {

  println(s"........... TestZ Called V1=${v1}")

}


class TestX(v1:String) extends TestZ(v1:String) {
  println(s"........... TestX Called v1 = ${v1}")
}