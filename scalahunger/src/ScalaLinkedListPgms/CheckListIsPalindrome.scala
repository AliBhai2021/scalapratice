package ScalaLinkedListPgms

class CheckListIsPalindrome[T] extends ReverseTheLinkedList[T] {

    def checkListIsPalindrome(head:MyLinkedListNode[T]): Boolean = {
      head match {
        case node if node == null => true
        case node if node.next == null || node.next.next == null => true
        case nodes =>

          var secondHalf = reverseLinkedListUsingRecursion(findMiddle(head)) // break list into half and reverse the second half list
          var firstHalf = head
          println(" secondHalf = " + secondHalf)
          println(" firstHalf  = " + firstHalf)
          while (secondHalf != null) {
            println(secondHalf.data + " " + firstHalf.data)
            if (secondHalf.data != firstHalf.data)
              return false

            secondHalf = secondHalf.next
            firstHalf = firstHalf.next
          }
          return true
      }
    }

    def findMiddle(head:MyLinkedListNode[T]): MyLinkedListNode[T] = {
      var slow = head
      var fast = head
      while (fast.next != null && fast.next.next != null) {
        fast = fast.next.next
        slow = slow.next
      }
      //slow
      slow.next
    }

}
