package interviewquestions

import scala.collection.mutable.ArrayBuffer

object carby extends App{

  def solution(n: Int): Boolean = {

    val arr = ArrayBuffer[Int]() //(if(n<10) 10 else n/2)//(n+5)
    println("arr length "+arr.length)
    //var i=0
    var result:Boolean=false
    def fibonaccifun(z: Int): Int = {
      def fibonacci(z: Int, f1: Int, f2: Int): Int = z match {
        case 0 => f1
        case r if(f1<=n) =>
          arr += f1
          for (x <- 0 until arr.length) {
            if (arr(x) == n)
              result = true
            else
              for (j <- 1 until arr.length - 1) {
                if (arr(x) + arr(j) == n)
                  result = true
              }
          }
            fibonacci(z - 1, f2, f1 + f2)
        case _=> 0

      }
          fibonacci(z, 0, 1)
      }

    fibonaccifun( if(n<5) 5 else n)
   // var result:Boolean=false
    arr.foreach(println(_))
/*
    for(x<- 0 until arr.length){
      if(arr(x)==n)
           result = true
      else
        for(j<-1 until arr.length-1){
          if(arr(x)+arr(j)==n)
            result= true
        }
    }*/

    result
  }

 println( solution(12))

}
