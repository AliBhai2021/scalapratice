package scalacookbook.lession01strings

object ScalaStringPrac extends App{

  def toLower(ch:Char):Char = (ch.toByte+32).toChar

  println(toLower('A'))
  println('A'.toByte)
  println('A'+1)
  println('a'+1)
  println("A"+1) // note String gives String
  println("A".charAt(0).toByte)

  def main(args:String)={


  }

}
