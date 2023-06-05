package ScalaLinkedListPgms

trait LinkedListCycleDetection[T] {

  def isCycleInLinkedListNodes(head:MyLinkedListNode[T]):Boolean ={
    head match {
      case node if node == null => false
      case nodes =>
        var slow = head
        var fast = head
        while (fast !=null && fast.next != null ){
          slow = slow.next
          fast = fast.next.next
          if (slow == fast)
            return true
        }
        false
    }
  }

  def findLinkedListCycleDetectionNode(head:MyLinkedListNode[T]):MyLinkedListNode[T] ={
    head match {
      case node if node == null => node
      case nodes =>
        var slow = head
        var fast = head
        while (fast !=null && fast.next != null ){
          slow = slow.next
          fast = fast.next.next
          if (slow == fast)
            return slow
        }
        null
    }
  }

  def findCycleBreakNode(head:MyLinkedListNode[T]):MyLinkedListNode[T]={
    var meetNode = findLinkedListCycleDetectionNode(head)
    var start = head
    while (start != meetNode){
      start = start.next
      meetNode = meetNode.next
    }
    start
  }

}
