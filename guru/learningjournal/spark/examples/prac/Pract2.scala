package guru.learningjournal.spark.examples.prac

import scala.io._
object  Pract2 {

  def isPrime(i:Int): Boolean ={
    if(i<=1)
      false
    else if(i == 2)
      true
    else
      !(2 until i).exists(n => i % n == 0)
  }

  def main(args:Array[String]): Unit = {
    val data = Source.fromFile("data/table.txt")
    val data1 = data.getLines().toSeq
    //println("Reading text data"+"\n")
    //data1.foreach(println)

    for(i <- 1 until data1.length){
     val a =data1(i).split(",")
      val b = a(2).toInt
      if(isPrime(b)){
        println(data1(i))
      }
    }
    /*println("\n"+"sorted Data from text File")

    for(line <-   data1.tail){
      val lines = line.split(",").toSeq
      if(isPrime(lines(2).toInt)){
         li +=line+"\n"
        println(line)
      }
    }
    try{
    val file = new File("data/result.csv")
    val writer =  new PrintWriter(file)
    writer.write(data1.head+"\n"+li)
    writer.close()}
    catch {
      case _:IOException => println("Not created file")
    }
    data.close()*/
  }
}