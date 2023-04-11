object Smaple extends App{
  val  array = Array('a','b','c')

  val r1 = array.charAt(1)-0
  println("a = "+r1)
  generateSeq("123")
  def generateSeq(upString:String, str:String=""):Unit={
    val digit = upString.charAt(0)
    println("digit :"+digit)
    if(upString.isEmpty) {
      println(str)
      return
    }
    for(i <- (digit-1)*3 until digit*3){
      println(s"i = ${i}    a =" +'a'.toInt)
      var ch = ('a' + i)//.toChar
      println("ch :"+ch)
      generateSeq(upString.substring(1),str ++ch.toString)

    }
  }
  }
