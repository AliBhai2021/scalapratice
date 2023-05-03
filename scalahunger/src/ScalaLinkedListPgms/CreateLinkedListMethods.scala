package ScalaLinkedListPgms

case class MyLinkedListNode[T](data:T, var next:MyLinkedListNode[T]=null)
class CreateLinkedListMethods[T] {
  //var head:MyLinkedListNode[T] = null

  def addFirst(element:T, head:MyLinkedListNode[T]):MyLinkedListNode[T]={
    head match {
      case node if node == null =>  MyLinkedListNode[T](element,null)
      case nodes => MyLinkedListNode[T](element,head)
    }
  }

  def addLast(element:T, head:MyLinkedListNode[T]):MyLinkedListNode[T]={
    head match {
      case null => MyLinkedListNode(element)
      case nodes =>
        var currentNode = nodes
        while (currentNode.next != null) {
          currentNode = currentNode.next
        }
        currentNode.next = MyLinkedListNode(element)
        nodes
    }
  }

  def removeFirst(head:MyLinkedListNode[T]):MyLinkedListNode[T]={
    head match {
      case node if node ==null  => node
      case nodes => nodes.next
    }
  }

  def removeLast(head:MyLinkedListNode[T]):MyLinkedListNode[T]={
    head match {
      case node if node ==null || node.next == null => null
      case nodes =>
        var currentNode = nodes
        while (currentNode.next.next != null) {
          currentNode = currentNode.next
        }
        currentNode.next=null
        nodes
    }
  }

  def removeElement(element:T, head:MyLinkedListNode[T]):MyLinkedListNode[T]={
    head match {
      case node if node == null => node
      case node if node.next == null && node.data != element => node
      case node if node.data == element => node.next
      case nodes  if nodes.next.data != element=>
           removeElement(element,nodes.next)
            nodes
      case nodes if(nodes.next.data == element) =>
          nodes.next = nodes.next.next
          nodes
    }
  }

  def printList(head:MyLinkedListNode[T]):Unit={
    head match {
      case null => println("List is Empty")
      case nodes =>
        var currentNode = head
        while(currentNode != null){
          print(" "+currentNode.data)
          currentNode = currentNode.next
        }
        println("")
    }
  }

}
