/*
package LinkedListPratice

object LinkedListPra extends App {

  trait LinkedListNodes
  case class Node(var data:Int, var next:LinkedListNodes) extends LinkedListNodes{
    this.data=data
    this.next = next
  }
  case object EmptyNode extends LinkedListNodes

  class LL{
    var head : Option[LinkedListNodes] = None

    def addFirst(element:Int):Unit={
      head match {
        case EmptyNode => head = Some(Node(element,EmptyNode))
        case Node(data,next) =>  head = Some(Node(element,head.get))
      }
    }

/*    def addLast(element:Int):Unit={
      head match {
        case EmptyNode => head = Node(element,EmptyNode)
        case node:Node =>
          var currentNode:LinkedListNodes= node
          while(currentNode.next != EmptyNode){
            println("node : "+node)
            println("currentNode.next :: "+ currentNode.next)
            currentNode = Node(currentNode.data,currentNode.next)
          }
          currentNode.next = Node(element,EmptyNode)
      }
    }*/
  }

  val list = new LL
  list.addFirst(10)
  list.addFirst(20)
  //list.addLast(30)

  println(list.head)

}
*/
