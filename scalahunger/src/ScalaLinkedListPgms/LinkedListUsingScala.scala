//package ScalaLinkedListPgms
//
//case class MyLinkedListNode(data:Int, var next:MyLinkedListNode=null)
//
//class MyLinkedList {
//  var head: MyLinkedListNode = null
//
//  def addFrirst(element: Int): Unit = {
//    head match {
//      case node if node == null => head = MyLinkedListNode(element)
//      case nodes => head = MyLinkedListNode(element, head)
//    }
//  }
//
//  def addLast(element: Int): Unit = {
//    head match {
//      case null => head = MyLinkedListNode(element)
//      case nodes =>
//        var currentNode = head
//        while (currentNode.next != null) {
//          currentNode = currentNode.next
//        }
//        currentNode.next = MyLinkedListNode(element)
//    }
//  }
//
//  def reverseList: Unit = {
//    head match {
//      case null => println("List is Empty")
//      case node if node.next == null => node
//      case nodes =>
//        var previousNode = head
//        var currentNode = head.next
//
//        while (currentNode != null) {
//          var nextNode = currentNode.next
//          currentNode.next = previousNode
//          // loop for sliding window
//          previousNode = currentNode
//          currentNode = nextNode
//        }
//        head.next = null
//        head = previousNode
//    }
//  }
//
//  def removeFirst: Unit = {
//    head match {
//      case node if node == null => println("List is Empty!")
//      case nodes =>
//        println("\n Removed Element is : " + head.data)
//        head = head.next
//    }
//  }
//
//  def removeLast: Unit = {
//    head match {
//      case node if node == null => println("List is Empty!")
//      case nodes =>
//        var currentNode = head
//        while (currentNode.next.next != null)
//          currentNode = currentNode.next
//        currentNode.next = null
//    }
//  }
//
//  def removeElement(element: Int): Unit = {
//    head match {
//      case node if node == null => println("List is Empty!")
//      case nodes =>
//        var previousNode = head
//        var currentNode = head.next
//        var elementFlag = false
//        while (currentNode != null && currentNode.data != element) {
//          previousNode = currentNode
//          currentNode = currentNode.next
//          if (currentNode != null && currentNode.data == element)
//            elementFlag = true
//        }
//        if (elementFlag)
//          previousNode.next = currentNode.next
//    }
//  }
//
//  def printList: Unit = {
//    head match {
//      case null => println("List is Empty")
//      case nodes =>
//        var currentNode = head
//        while (currentNode != null) {
//          print(" " + currentNode.data)
//          currentNode = currentNode.next
//        }
//    }
//  }
//
//  //======================================================================
//  def findMiddle: MyLinkedListNode = {
//    var slow = head
//    var fast = head
//    while (fast.next != null && fast.next.next != null) {
//      fast = fast.next.next
//      slow = slow.next
//    }
//    //slow
//    slow.next
//  }
//
//  def reverseGivenList(givenList: MyLinkedListNode): MyLinkedListNode = {
//    println("givenList = " + givenList)
//    givenList match {
//      case null => null //println("List is Empty")
//      case node if node.next == null => node //println("List have Single eleemt")
//      case nodes =>
//        var previousNode = givenList
//        var currentNode = givenList.next
//
//        while (currentNode != null) {
//          var nextNode = currentNode.next
//          currentNode.next = previousNode
//          // loop for sliding window
//          previousNode = currentNode
//          currentNode = nextNode
//        }
//        givenList.next = null
//        //givenList = previousNode
//        previousNode
//    }
//  }
//
//  def checkListIsPalindrome: Boolean = {
//    head match {
//      case node if node == null => true
//      case node if node.next == null || node.next.next == null => true
//      case nodes =>
//
//        var secondHalf = reverseGivenList(findMiddle) // break list into half and reverse the second half list
//        var firstHalf = head
//        println(" secondHalf = " + secondHalf)
//        println(" firstHalf  = " + firstHalf)
//        while (secondHalf != null) {
//          println(secondHalf.data + " " + firstHalf.data)
//          if (secondHalf.data != firstHalf.data)
//            return false
//
//          secondHalf = secondHalf.next
//          firstHalf = firstHalf.next
//        }
//        return true
//    }
//  }
//}
//
//object LinkedListUsingScala extends App{
//  val list = new MyLinkedList
//  list.addFrirst(10)//.addFrirst(80)
//  list.addFrirst(20)
//  list.addFrirst(30)
//  list.addFrirst(40)
//  list.printList
//  list.reverseList; println("")
//  list.printList
//
//  list.addLast(50)
//  list.addLast(60); println("")
//  list.printList
//
//  list.removeFirst
//  list.removeLast; println("")
//  list.printList
//
//  list.removeElement(40); println("");
//  list.printList
//
//  list.removeElement(50); println(""); println("HEAD :"+list.head);
//  list.removeElement(150); println("")
//  list.printList
//
//}
