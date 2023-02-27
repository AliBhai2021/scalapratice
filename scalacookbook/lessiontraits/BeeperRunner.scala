package scalacookbook.lessiontraits

trait Beeper {
  def beep(times: Int): Unit = {
    1 to times foreach(i => System.out.println(s"Beep number: $i"))
  }
}

object BeeperRunner extends  Beeper {
  beep(5)
  val TIMES = 10
  def main (args: Array[String]): Unit = {
    val beeper = new Beeper {}
    beeper.beep(TIMES)
  }

}
