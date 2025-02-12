package DS.ArraysPrac

object ArrayMetods extends App{

  val arr = Array(1 to 10)
  arr.foreach(println(_)) //Range 1 to 10
  println("")
  val letters = Array('a' to 'j')
  letters.foreach(println(_))
  println("")
  val number1to10 = (1 to 5).toArray
  number1to10.foreach(print(_))
  println("")

  //val lttr = (('a' to 'j').toArray) ++ ('f' to 'l').toArray //not working
  val letters2 = ('a' to 'j').toArray
  val letters3 = ('f' to 'l').toArray
  val letters4 = letters2 ++ letters3
  letters4.foreach(print(_))
  println(" ")
  val arr1 = Array(1,2,3,4)
  val arr2 = Array(5,6,7,8)
  val arr3 = arr1 ++ arr2
  arr3.foreach(print(_))
  println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::")

  val array1 = Array.ofDim[Int](2,2)
  array1.foreach(println(_))
  for(i <- 0 until 2; j<- 0 until 2)
    array1(i)(j) = 3

  for(i <- 0 until 2; j<- 0 until 2)
    println(array1(i)(j))

}
