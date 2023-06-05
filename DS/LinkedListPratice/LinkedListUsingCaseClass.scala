package LinkedListPratice

case class MyLinkedListCaseClassNode(data:Int, var next:MyLinkedListCaseClassNode=null){
  var head : MyLinkedListCaseClassNode = null

  def addFrirst(element:Int):Unit={
    head match{
      case node if node == null => head = MyLinkedListCaseClassNode(element)
      case nodes => head= MyLinkedListCaseClassNode(element,head)
    }
  }

  def addLast(element:Int):Unit={
    head match {
      case node if node == null => head =MyLinkedListCaseClassNode(element)
      case nodes =>
        var currentNode = head
        while(currentNode.next != null){
          currentNode = currentNode.next
        }
        currentNode.next=MyLinkedListCaseClassNode(element)
    }
  }

  def reverseList:Unit={
    head match {
      case null => println("List is Empty")
      case node if node.next == null => node
      case nodes =>
        var previousNode = head
        var currentNode = head.next

        while( currentNode != null){
          var nextNode = currentNode.next
          currentNode.next = previousNode
          // loop for sliding window
          previousNode = currentNode
          currentNode = nextNode
        }
        head.next = null
        head = previousNode
    }
  }

  def removeFirst :Unit={
    head match{
      case node if node == null => println("List is Empty!")
      case nodes =>
        println("\n Removed Element is : "+head.data)
        head = head.next
    }
  }

  def removeLast:Unit={
    head match {
      case node if node == null => println("List is Empty!")
      case nodes =>
        var currentNode = head
        while(currentNode.next.next != null)
          currentNode = currentNode.next
        currentNode.next=null
    }
  }

  def removeElement(element:Int):Unit={
    head match {
      case node if node == null => println("List is Empty!")
      case nodes =>
        var previousNode = head
        var currentNode = head.next
        var elementFlag = false
        while(currentNode != null && currentNode.data != element ){
          previousNode = currentNode
          currentNode = currentNode.next
          if(currentNode != null && currentNode.data == element)
            elementFlag =true
        }
        if(elementFlag)
          previousNode.next = currentNode.next
    }
  }

  def printList:Unit={
    head match {
      case null => println("List is Empty")
      case nodes =>
        var currentNode = head
        while(currentNode != null){
          print(" "+currentNode.data)
          currentNode = currentNode.next
        }
    }
  }

}
object LinkedListUsingCaseClass extends App{

  val obj = MyLinkedListCaseClassNode(10)
  obj.addFrirst(20)
  obj.addLast(30)
  obj.printList
  obj.addLast(50)
}
