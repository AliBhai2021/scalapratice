package linkedlistpratice

object LinkedListPratice extends App{

  case class LinkedList(){
    var head:Node=null
  }
  sealed class Node(var data:Int, var next:Node){
    def getData = this.data
    def getNext = this.next
    def printList():Unit ={
      print(data)
      if(next != null){
        print(",")
        next.printList();
      }
    }
  }


}
