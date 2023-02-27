package codesignal

object StringPalindrom extends App{

  def solution(inputString: String): Boolean = {
    println("string length = "+inputString.length)

    val x = for(str <- inputString.length until 0 by -1) yield inputString(str-1)
    (inputString == x.mkString(""))

  }

  println(solution("ababbbaba"))

  def method2(str:String):Boolean ={
    var i=0
    var j=str.length-1

    while(i<j){
      if(str(i)!= str(j))
        return false
      i +=1
      j -=1

    }
    true
  }
  println(method2("ababbbaba"))


}
