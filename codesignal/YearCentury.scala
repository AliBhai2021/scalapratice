package codesignal

object YearCentury extends App{

  def findCentury(year:Int):Int=
    year match {
      case x if(x%100==0) => x/100
      case x => (x/100)+1
    }

  println(s"century = ${findCentury(1905)}")
  println(s"century = ${findCentury(1700)}")
  println(s"century = ${findCentury(1705)}")



}
