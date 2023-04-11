//package linkedlistpratice
//
//object LinkedListPratice extends App{
//
//  trait LinkedNode {
//    var head: Node = null;
//  }
//  case class Node(element: Int, next: Node) extends LinkedNode{
//    def getData:Int = this.element
//    def getNext: Node = this.next;
//    def printList(): Unit = {
//      print(element)
//
//      if (next != null) {
//        print(",")
//        next.printList();
//      }
//
//    }
//
//  }
//  case object EmptyNode extends LinkedNode
//
//  def addNode(ll:LinkedNode,element:Int):LinkedNode={
//    ll match {
//      case Node(x, n) if n != EmptyNode => addNode(n,element)
//      case Node(x,n)  => Node(x,Node(element,EmptyNode))
//    }
//  }
//
//  def createLinkedList(arr:Array[Int]):LinkedNode={
//    arr match {
//      case x if x.isEmpty => EmptyNode
//      case x =>
//        Node(arr.head,createLinkedList(arr.tail))
//    }
//  }
//
//  val ll =createLinkedList(Array(9,3,8,4,5,1,2))
//  println(ll)
//
//  println(addNode(ll,7))
//}
