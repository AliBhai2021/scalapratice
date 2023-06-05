package LinkedListPratice

object LinkedListWithOptionNext extends App{

  val obj = new LL
  obj.printList
  obj.addFirst(10)
  obj.addFirst(20)
  obj.addFirst(30)
  obj.printList
  obj.addLast(40)
  obj.addLast(50)
  obj.addLast(60)
  obj.printList
  obj.reverseList
  obj.printList
  obj.removeLast
  obj.printList
  obj.removeFirst
  obj.printList

}

case class Node(data:Int, var next:Option[Node])

class LL{
  var head:Option[Node]=None

  def addFirst(element:Int):Unit={
    head match {
      case None => head = Some(Node(element,None))
      case _ => head = Some(Node(element,head))
    }
  }
  def addLast(element:Int):Unit={
    head match {
      case None => head = Some(Node(element,None))
      case _ =>
        var currentNode = head
        while(currentNode.get.next.nonEmpty)
          currentNode = currentNode.get.next
        currentNode.get.next = Some(Node(element,None))
    }
  }

  def printList :Unit={
    head match {
      case None => println(" List is Empty")
      case _=>
        var currentNode = head
        while(currentNode.nonEmpty) {
          print(" "+currentNode.get.data)
          currentNode = currentNode.get.next
        }
        println("")
    }
  }

  def removeFirst:Unit={
    head match {
      case None => println("List is Empty")
      case _ => head = head.get.next
    }
  }
  def removeLast:Unit={
    head match {
      case None => println("List is Empty")
      case node if node.get.next.isEmpty=> head = None
      case _ =>
        var currentNode = head
        while(currentNode.get.next.get.next.nonEmpty)
          currentNode = currentNode.get.next
        currentNode.get.next = None
    }
  }

  def reverseList:Unit={
    head match {
      case None => println("List is Empty")
      //case node if node.get.next.isEmpty => node
      case node =>
        println("Head          ==> "+head)
        var  previousNode = head
        var currentNode = head.get.next
        println("previousNode  ==> "+previousNode)
        println("currentNode   ==> "+currentNode)

        while(currentNode.nonEmpty){
          println("Loop currentNode ::> "+currentNode)
          var nextNode = currentNode.get.next
          println("nextNode ==> "+nextNode)

          currentNode.get.next = previousNode
          // shift window for loop
          previousNode = currentNode
          currentNode = nextNode
        }
        println("After Loop ::>"+head.get.data)
        head.get.next = None
        println("Head ==> "+head)
        println("previousNode ==> "+previousNode)
        head = previousNode
    }
  }

}


