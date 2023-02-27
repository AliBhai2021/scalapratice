package geeks4geeks

object ComputerFactory extends App{

  val obj = ComFactory("PC","10","20","30")
  println(obj)
}

//Via Class and Trait implementation
/*
trait Computer {
  def ram:String
  def hdd:String
  def cpu:String

  override def toString: String = "RAM = " + ram + ",  HDD = " + hdd + ", CPU =" + cpu
}
class PC(ram:String,hdd:String,cpu:String) extends Computer
class Server(ram:String,hdd:String,cpu:String) extends Computer
/*
class PC(ram:String,hdd:String,cpu:String) extends Computer{
  def ram:String = ram
  def hdd:String = hdd
  def cpu:String = cpu
}

class Server(ram:String,hdd:String,cpu:String) extends Computer{
  def ram:String = ram
  def hdd:String = hdd
  def cpu:String = cpu
}
*/
*/

// Via CaseClass and Trait implementation
trait Computer{
  //  abstract class Computer {
  def ram:String
  def hdd:String
  def cpu:String

  override def toString: String = "RAM = "+ram + ",  HDD = "+hdd+", CPU ="+cpu
}
case class PC(ram:String,hdd:String,cpu:String) extends Computer
case class Server(ram:String,hdd:String,cpu:String) extends Computer

object ComFactory{
  def apply(compType:String, ram:String, hdd:String, cpu:String)=
    compType match {
    case "PC"=> new PC(ram,hdd,cpu)
    case "Server" => new Server(ram,hdd,cpu)
  }
}
