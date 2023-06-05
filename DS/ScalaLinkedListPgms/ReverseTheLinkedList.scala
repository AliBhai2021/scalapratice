package ScalaLinkedListPgms

//case class MyLinkedListNode[T](data:T, var next:MyLinkedListNode[T]=null)

trait ReverseTheLinkedList[T] {

  def reverseLinkedListUsingLoop(head:MyLinkedListNode[T]):MyLinkedListNode[T]={
    head match {
      case node if node == null || node.next == null => node
      case nodes =>
        var previousNode = nodes
        var currentNode = nodes.next

        while(currentNode != null){
          var nextNode = currentNode.next
          currentNode.next=previousNode
          // slide window on list
          previousNode = currentNode
          currentNode = nextNode
        }
        nodes.next = null
        println("previousNode ="+previousNode)
        println("currentNode ="+currentNode)
        previousNode
    }
  }

  def reverseLinkedListUsingRecursion(head:MyLinkedListNode[T]):MyLinkedListNode[T]={
    head match {
      case node if node == null || node.next == null =>
        node
      case nodes =>
        var newHead = reverseLinkedListUsingRecursion(nodes.next)

        nodes.next.next = nodes
        //println("After 2.......... nodes "+nodes) infinite loop
        nodes.next = null // discard remaining nodes
        newHead

    }
  }

}
