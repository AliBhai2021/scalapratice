package scalapratice.scalapatternmatch

trait ApplicationLee{
  def main(ars:Array[String]):Unit= println("Main")
  def lee:Unit = println("LEE")
}


object SSample extends ApplicationLee {

  println("Ello World")

  println("World!........")

  println("End")

  val obj1 = new E

  val arr = Array("ali","lee","ann")
  val res = new Array[String](arr.length+2)
  val maxstr = arr.maxBy(x=>x.length).length
  println("array MaxStr :"+maxstr)
  println("array Size :"+arr.size)
  println("array Size :"+res.size)

  //val abc = arr.map(x=>

  for(i <- 0 until res.length){
    if(i==0 || i== res.length-1)
      res(i) = List.fill(maxstr+2)("*").mkString("")
    else
      res(i) = s"*${arr(i-1)}*"
  }

  res.foreach(println(_))
}

class A {
  println("Hi")
  private[this] var count = 0
  def delayedInit1(x: => Unit) {
    x
    count += 1
    if (count==2) { println("There") }
  }
}
class B extends A with DelayedInit {
  private[this] var count = 0
  println("Hey")
  def delayedInit(x: => Unit) {
    x
    count += 1
    if (count==2) { println("There") }
  }
}
class C extends B { println("Ho") }
class D extends C { println("Ha") }
class E extends D { println("EE")}

