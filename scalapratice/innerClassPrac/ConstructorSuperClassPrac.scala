package scalapratice.innerClassPrac

object ConstructorSuperClassPrac extends App{

  val obj= new Employee("Ali",Role("software"))
  val obj2= new Employee("Ali")
  val obj3= new Employee("Ali",Address("C1","S1"))
  //val obj4 = new Employee()

  val pobj = new Person("P1",Address("A1","A2"))
  println(pobj.address)


}

case class Address (city: String, state: String)
case class Role (role: String)
class Person (var name: String, var address: Address) {
  // no way for Employee auxiliary constructors to call this constructor
  //This is a bit of a trick question, because you can control the superclass constructor that’s called by the primary constructor in a subclass,
  // but you can’t control the superclass constructor that’s called by an auxiliary constructor in the subclass
  def this (name: String) {
    this(name, null)
    address = null
    println("called :Person => this (name: String) ")
  }
  override def toString = if (address == null) name else s"$name @ $address"
}
//------------------------------------------------------------------------
class Employee (name: String, address: Address,role: Role)  extends Person (name, address) {
  def this (name: String) {
    this(name, null, null)
    println(s"called :Employee => this (name: ${name}) ")
  }
  def this (name: String, role: Role) {
    this(name, null, role)
    println(s"called :Employee => this (name: ${name}, role: ${role}) ")
  }
  def this (name: String, address: Address) {
    this(name, address,null)
    println(s"called :Employee => this(name : ${name}, null, address: ${address}) ")
  }
  override def toString: String = super.toString
}
