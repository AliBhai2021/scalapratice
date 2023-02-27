package scalacookbook.lessiontraits

object TriatsPratice1 extends App{

  trait Notifier {
    val notificationMessage: String
    def printNotification(): Unit = {
      System.out.println(notificationMessage)
    }
    def clear()
  }
//notificationMessage in trait and class
  class NotifierImpl(val notificationMessage: String) extends Notifier {
    override def clear(): Unit = System.out.println("cleared")
  }

  val obj = new NotifierImpl("Lee")
  println(obj.printNotification())


}
