package interviewquestions

import scala.collection.mutable

object Apple2 extends App{
  //Expression Notation
  println("FinalResult : "+calculate("2+0-2-4+4+1+9*5/2%7"))
  def calculate(input:String):Int={
    var i=0
    var result=0
    while(i < input.length){
      input(i) match {
        case '+' =>
          i =i+1
          result = result+(input(i).toInt-48)
        case '-' =>
          i =i+1
          result = result-(input(i).toInt-48)
        case '*' =>
          i =i+1
          result = result*(input(i).toInt-48)
        case '/' =>
          i =i+1
          result = result/(input(i).toInt-48)
        case '%' =>
          i =i+1
          result = result%(input(i).toInt-48)
        case _=>
          result = input(i).toInt-48
      }
      i=i+1
    }
    result
  }

}
