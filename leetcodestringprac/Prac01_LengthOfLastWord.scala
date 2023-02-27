package leetcodestringprac

import java.time.LocalTime

object Prac01_LengthOfLastWord extends App{

  val input = scala.io.StdIn.readLine()
  println("start : "+LocalTime.now())
  //val data = input.split(" ").map(_.length).last
  val data = input.split(" ").last.length
  println("end : "+LocalTime.now())
  println(data)


  // Method :2

  println(lengthOfLastWord(input))
  println("end : "+LocalTime.now())

  def lengthOfLastWord(s:String):Int= {
    println("start : "+LocalTime.now())
    var count = 0;
    val n = s.length
    var i = 0;
    while(i<n){
      if(s(i)!=' '){/// end of string
                    println(s(i))
                    count +=1
                    i+=1
      }else{
              /// current char is a space
              while(i<n && s(i)==' ')       // check for more spaces
                i+=1

              if(i==n)
                count   /// end of string
              else
                count = 0

      }
    }

    count
  }


}
