package ScalaLinkedListPgms

object LinkedListDriver extends App {
  //var head :MyLinkedListNode[Int]= null
  val list = new CreateLinkedListMethods[Int] with ReverseTheLinkedList[Int]
  val obj1 = list.addFirst(10,null)
  val obj2 = list.addFirst(20,obj1)
  val obj3 = list.addFirst(30,obj2)
  val obj4 = list.addFirst(40,obj3)
  list.printList(obj4)
  println("obj4 = "+obj4)

 // val obj5 = list.removeLast(obj4)
 // list.printList(obj5)

  val obj6 = list.reverseLinkedListUsingRecursion(obj4)
  list.printList(obj6)


}
