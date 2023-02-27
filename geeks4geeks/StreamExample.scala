package geeks4geeks

object StreamExample extends App{

  def numberStream(n: Int): Stream[Int] = Stream.from(n)
  println(numberStream(4))

  val result1 = Stream.from(4).take(5)
  println("result1 : " +result1)
  println("result1 : " +result1.toList)
  result1.foreach(println(_))

}
