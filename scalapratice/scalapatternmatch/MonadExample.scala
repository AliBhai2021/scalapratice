package scalapratice.scalapatternmatch

object MonadExample extends App{

  def demo(f1:Unit,f2:Unit):Unit=()
  val x={ println("hello") }
  demo(x,x)
  println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++")

  val f = (i: Int) => List(i - 1, i, i + 1)
  val list = List(5, 6, 7)
  println(list.flatMap(f))
  // prints List(4, 5, 6, 5, 6, 7, 6, 7, 8)
  println(list.map(f))

  val f2=(i:Int)=> i*(-1)
  println(list.map(f2))
  //println(list.flatMap(f2)) // Error: can't resolved overload metod

  val f3=(i:Int)=> List(i*(-1))
  println(list.flatMap(f3))
  println(list.flatMap(f3).flatMap(f))



}
