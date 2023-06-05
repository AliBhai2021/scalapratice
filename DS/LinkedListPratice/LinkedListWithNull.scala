package LinkedListPratice
// 1. addFirst
// 2. addLast
// 3. removeFirst
// 4. removeLast
// 5. printList
// 6. ReverseList ( a.loop, b.recursive)
// 7. Check Palindrome in list
// 8. check cycle in list

case class MyLinkedListNode(data:Int, var next:MyLinkedListNode=null)

class LinkedListWithNullNode  {
  var head : MyLinkedListNode = null

  def addFrirst(element:Int):Unit={
    head match{
      case node if node == null => head = MyLinkedListNode(element)
      case nodes => head= MyLinkedListNode(element,head)
    }
  }

  def addLast(element:Int):Unit={
    head match {
      case node if node == null => head =MyLinkedListNode(element)
      case nodes =>
        var currentNode = nodes
        println(" currentNode : "+currentNode)
        while(currentNode.next != null){
          currentNode = currentNode.next
        }
        println("addLast() head = "+head)
        currentNode.next=MyLinkedListNode(element,null)
        println("addLast() currentNode = "+currentNode)
    }
  }

  def reverseList:Unit={
    head match {
      case null => println("List is Empty")
      case node if node.next == null => println("List have Single eleemt")
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

  def reverseListUsingRecursive(head:MyLinkedListNode):MyLinkedListNode={
    head match {
      case node if node == null || node.next == null => head
      case nodes =>
        val newHead = reverseListUsingRecursive(head.next)
        head.next.next = head
        head.next = null
        newHead
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

  def findMiddle:MyLinkedListNode={
    var slow = head
    var fast = head
    while(fast.next != null && fast.next.next != null){
      fast = fast.next.next
      slow = slow.next
    }
    //slow
    slow.next
  }
  def reverseGivenList(givenList:MyLinkedListNode):MyLinkedListNode={
    println("givenList = "+givenList)
    givenList match {
      case null => null //println("List is Empty")
      case node if node.next == null => node //println("List have Single eleemt")
      case nodes =>
        var previousNode = givenList
        var currentNode = givenList.next

        while( currentNode != null){
          var nextNode = currentNode.next
          currentNode.next = previousNode
          // loop for sliding window
          previousNode = currentNode
          currentNode = nextNode
        }
        givenList.next = null
        //givenList = previousNode
        previousNode
    }
  }

  def checkListIsPalindrome:Boolean={
    head match {
      case node if node == null => true
      case node if node.next == null || node.next.next == null => true
      case nodes =>

        var secondHalf = reverseGivenList(findMiddle) // break list into half and reverse the second half list
        var firstHalf = head
        println(" secondHalf = "+secondHalf)
        println(" firstHalf  = "+firstHalf)
        while(secondHalf != null){
          println(secondHalf.data +" "+ firstHalf.data)
          if(secondHalf.data != firstHalf.data)
            return false

          secondHalf = secondHalf.next
          firstHalf = firstHalf.next
        }
        return true
    }
  }

  def detectCycleInLinkedList:Boolean ={
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

}

object LinkedListWithNull extends App{

  val list = new LinkedListWithNullNode
  list.addLast(10)
  list.addLast(20)
  list.addLast(30)
  list.addLast(40)
  list.addLast(400)
//  list.addLast(40)
//  list.addFrirst(30)
//  list.addFrirst(20)
//  list.addFrirst(10)

  list.printList

  //list.removeFirst
  //list.removeLast; println("")
  //list.printList

  //list.removeElement(40); println("");
  //list.printList

  //list.removeElement(50); println(""); println("HEAD :"+list.head);
  //list.removeElement(150); println("")
  //list.printList
  println("\n Head = "+list.head)
  //val  middle = list.findMiddle
  //println("\n Middle List = "+ middle)

  //val reverseList = list.reverseGivenList(middle)
  //println("reverse Middle List = "+reverseList)
  println("\n checkListIsPalindrome : "+list.checkListIsPalindrome)
  println("detectCycleInLinkedList : "+list.detectCycleInLinkedList)
  println(" Head      = "+list.head)
  println(" Recursive = "+list.reverseListUsingRecursive(list.head))



}
