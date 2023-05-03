object Sample extends App{

  val obj = new Test
  val arr= Array(1,2,3,4,5)
  arr.foreach(println(_))

  val call = obj.arrdef(arr)
  call.foreach(println(_))

  println("++++++++++++++++++++++")
  arr.foreach(println(_))

  val name = "Ali"
  val call2 = obj.chaneName(name)
  println(call2)
  println(name)
  println("++++++++++++++++++++++++++++")
  val element = Element(100,"Ali")
  println(element)
  val call3 = obj.elementChange(element)
  println(call3)
  println(element)

}

case class Element(id:Int,name:String)

class Test{
  def arrdef(sarr:Array[Int]):Array[Int]={
    val newarr = sarr
    newarr(3)=300;
    newarr
  }

  def chaneName(name:String):String= "Lee"

  def elementChange(element: Element):Element=Element(100,"Lee")
}