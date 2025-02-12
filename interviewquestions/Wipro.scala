package interviewquestions

import org.apache.commons.collections.list.LazyList

import scala.annotation.tailrec


object Wipro extends App{

  //::::::::::::::::::::::::::::::::::: WORD COUNT :::::::::::::::::::::::::::::::::::::
  val ddata =  //List("Anish is working on BigData Technologies","Hello Anish","BigData")
  scala.io.Source.fromFile("data/result.csv").getLines()
  val ddata2 = ddata.flatMap(_.split(",")).map(x => (x,1))
  val ddata3 = ddata2.toList.groupBy(_._1).mapValues(x=> {x.map(_._2).sum})
  println("ddata3 :"+ddata3)
  ddata3.foreach(println(_))
  println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::")
  val list = List("Anish is working on BigData Technologies","Hello Anish","BigData")
  val words = list.flatMap(line => line.split(" "))
  val keyData = words.map(word => (word,1))
  val groupedData = keyData.groupBy(_._1)
  val result = groupedData.mapValues(list=>{list.map(_._2).sum })
  result.foreach(println)
  println("Words :"+words)
  println("KeyData :"+keyData)
  println("GroupedData :"+groupedData)

/*
  val text = sc.textFile("mytextfile.txt")
  val counts = text.flatMap(line => line.split(" ")).map(word => (word,1)).reduceByKey(_+_)
  counts.collect
  */

  println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::")
  val name = "ali ahammed"
  val reverse_name =  for(i <- name.length until 0 by -1) yield name(i-1)
  println("reverse_name :"+reverse_name.mkString(""))

  val reversemetos2 = for(a <- "lee") yield {println("a :"+a);a}
  println("reversemetos2 :"+ reversemetos2.mkString(""))
  println(reverseString("lee"))
  def reverseString( str:String,s:String=""):String={
    //println("str :"+str +" s ="+s)
    str match {
      case strl if strl.isEmpty => s
      case strl =>
        reverseString(strl.substring(0,str.length-1),s+s"${strl.substring(str.length-1)}" )

    }
  }

  println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::")

  def fibanacci(x:Int):Int={
    @tailrec
    def fib(n:Int,f1:Int,f2:Int):Int= n match {
      case 0 => f1
      case _ =>
        println("f1 = "+f1+"  f2 :"+f2)
        fib(n-1,f2, f1+f2)
    }
    fib(x,0,1)
  }
  println(fibanacci(9))

  //val fib: Stream[BigInt] = BigInt(0) #:: BigInt(1) #:: fib.zip(fib.tail).map(p => p._1 + p._2)
  //def fib4(n: Int) = fib(n)

  println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++")
  def fibonacciInfinite(f1:Int=0,f2:Int=1) : Stream[Int]= f1 #:: fibonacciInfinite(f2,f1+f2)
  println("Infinite fibonacci_series :"+fibonacciInfinite())  //:Stream(0, ?)
  println("nth fibonacci_series number :"+fibonacciInfinite()(10)) //55
  println("Finite fibonacci_series :"+fibonacciInfinite().take(10+1).mkString(",")) //0,1,1,2,3,5,8,13,21,34

  val fibvalue = fibonacciInfinite()(10)//(0,1)(10)
  println("fib "+fibvalue)

  val fibvalue2 = fibonacciInfinite(8,13)(4)
  println("fib "+fibvalue2)

 /* val fib2 = fibonacciInfinite()//(0,1)
  println("fib2 :"+fib2(99999) +"    try-to-print:"+fib2.take(5).toList)
  fib2.take(5).foreach(print(_))*/

  def fibonacci_optimized(n: Int): List[Int] = {
    require(n >= 0, "The number of terms must be non-negative.")

    var fibs = List(0, 1)
    for (i <- 2 until n) {
      fibs = fibs :+ (fibs(i - 1) + fibs(i - 2))
    }
    fibs.take(n)
  }
}
