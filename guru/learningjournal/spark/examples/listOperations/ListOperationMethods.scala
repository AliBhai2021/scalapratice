package guru.learningjournal.spark.examples.listOperations

object ListOperationMethods extends App{

  val list =List("a","b","c","d","e","f")

  println(list.head)
  println(list.init)
  println(list.tail)
  println(list.last)

  println(list.lastOption)
  println(list.headOption)

  //println(list(-1))

}
