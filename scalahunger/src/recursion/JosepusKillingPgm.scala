package recursion

object JosepusKillingPgm extends App{

  def killingPattern(numOfPeoples:Int, frequency:Int):Int={
    numOfPeoples match {
      case peoples if peoples == 1 => 0
      case peoples =>
        println("(people = "+ (peoples - 1) +"  freq = "+frequency+") people = "+peoples)
        (killingPattern(peoples-1,frequency)+frequency) % peoples
    }
  }


  val safePosition = killingPattern(5,3)
  println(" safe Position = "+safePosition)

}
