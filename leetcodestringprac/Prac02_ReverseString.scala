package leetcodestringprac

import scala.collection.mutable

object Prac02_ReverseString extends App{

  val data = scala.io.StdIn.readLine()

  reverseString(data.toCharArray)
  println(" ::::::::reverseString2 ::::: "+reverseString2(data.toCharArray))

  def reverseString(s:Array[Char]) :Unit={
    val st = new mutable.Stack[Char]
    for(i <- 0 until s.length()){
      st.push(s(i))
    }
    st.foreach(print(_))
    for(i <- 0 until s.length()){
      s(i) = st.top
      st.pop();
    }
    println("st :::::::::::::::::::::"+s.toString)
  }



  def reverseString2(s:Array[Char]) :Unit= {
    val st = new mutable.Stack[Char]
    for (i <- 0 until s.length()) {
      st.push(s.charAt(i))
    }
    st.foreach(print(_))
    for (i <- 0 until s.length()) {
      s(i)=  st.top
      st.pop()
    }
  }
//*****************************************************************************************************
//              String Reverse
//*****************************************************************************************************
  def reverseString4(s: String)  = (for(i <- s.length until 0 by -1) yield s(i-1)).mkString
  println("reverseM2 : "+reverseString3("ahammed"))

  def reverseString3(s:String):String={
    var str = s.toCharArray
    var startPTR = 0
    var endPTR = str.length-1
    while (startPTR != endPTR && startPTR <= endPTR){
      val temp = str(startPTR)
      str(startPTR)=str(endPTR)
      str(endPTR)=temp
      startPTR +=1
      endPTR -=1
    }
    str.mkString("")
  }

  val str1 = "abcdef"
  println(reverseString3("abcdef"))

}
