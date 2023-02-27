package interviewquestions

import org.apache.commons.collections.list.LazyList

import scala.annotation.tailrec


object Wipro extends App{

  //::::::::::::::::::::::::::::::::::: WORD COUNT :::::::::::::::::::::::::::::::::::::
  val ddata =  //List("Anish is working on BigData Technologies","Hello Anish","BigData")
  scala.io.Source.fromFile("data/result.csv").getLines()
  val ddata2 = ddata.flatMap(_.split(",")).map(x => (x,1))
  val ddata3 = ddata2.toList.groupBy(_._1).mapValues(x=> {x.map(_._2).sum})
  println(ddata3)
  ddata3.foreach(println(_))
  println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::")
  val list = List("Anish is working on BigData Technologies","Hello Anish","BigData")
  val words = list.flatMap(line => line.split(" "))
  val keyData = words.map(word => (word,1))
  val groupedData = keyData.groupBy(_._1)
  val result = groupedData.mapValues(list=>{list.map(_._2).sum })
  result.foreach(println)

/*
  val text = sc.textFile("mytextfile.txt")
  val counts = text.flatMap(line => line.split(" ")).map(word => (word,1)).reduceByKey(_+_)
  counts.collect
  */

  println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::")
  val name = "ali ahammed"
  val reverse_name =  for(i <- name.length until 0 by -1) yield name(i-1)
  println("reverse_name :"+reverse_name.mkString(""))

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
        println("f1 = "+f1)
        fib(n-1,f2, f1+f2)
    }

    fib(x,0,1)
  }

  println(fibanacci(10))

  //val fib: Stream[BigInt] = BigInt(0) #:: BigInt(1) #:: fib.zip(fib.tail).map(p => p._1 + p._2)
  //def fib4(n: Int) = fib(n)

  println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++")
  def fibInfity(f1:Int,f2:Int) : Stream[Int]= f1 #:: fibInfity(f2,f1+f2)
  val fibvalue = fibInfity(0,1)(10)
  println("fib "+fibvalue)
}
