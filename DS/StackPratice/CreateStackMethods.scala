package StackPratice

case class MyLinkedListNode(data:Int, next:MyLinkedListNode=null)

class CreateStackMethods {
  var head :MyLinkedListNode = null

  def pushElement(element: Int):Unit ={
    head match {
      case node if node == null => head = MyLinkedListNode(element)
      case nodes => head=MyLinkedListNode(element,nodes)
    }
  }

  def  popElement():Int={
    head match {
      case node if node == null => -1
      case nodes =>
      var pop = nodes.data
        println("POP Element = "+pop)
      head = head.next

      pop
    }
  }

  def peek:Option[Int]= {
    head match {
      case node if node == null => None
      case nodes => Some(nodes.data)
    }
  }


}

