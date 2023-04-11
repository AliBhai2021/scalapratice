package linkedlistpratice

object LLPratice extends App{

  import scala.util.control.Breaks

  case class LinkedList[T]() {
    var head: Node[T] = null;

    def push(data: T) = {
      head match {
        case null => head = new Node(data, null)

        case _ => {
          var last: Node[T] = head;

          while (last.next != null) {
            last = last.next;
          }

          last.next = new Node[T](data, null);
        }
      }
    }

    def append(data:T) = {
      push(data);
    }

    def prepend(data:T): Unit = {
      val tempHead:Node[T]  = new Node[T](data,head);
      head = tempHead;
    }
    def print() = {
      if (head != null) {
        head.printList();
      }
      println();
    }

    def delete(deleteItem: T) = {
      var previousNode: Node[T] = head
      var currentNode: Node[T] = head

      val loopBreak = new Breaks;

      loopBreak.breakable {
        while (currentNode != null) {
          if (currentNode.data.equals(deleteItem)) {
            if (currentNode.equals(previousNode)) {
              head = currentNode.next
            } else {
              previousNode.next = currentNode.next;

            }
            loopBreak.break();
          } else {
            previousNode = currentNode;
            currentNode = currentNode.next;
          }
        }
      }
    }

    def reverse(): Unit = {
      var previous:Node[T] = null;
      var current:Node[T] = head;
      var next:Node[T] = null;

      while (current != null) {
        next = current.next;
        current.next = previous;
        previous = current;
        current = next;
      }
      head = previous;
    }

    def getDataByIndex(index: Int): T = {
      var currentNode = head
      var currentIndex = 0;

      while (!currentIndex.equals(index)) {
        currentNode = currentNode.next
        currentIndex += 1;
      }

      currentNode.data;
    }
  }

  sealed case class Node[T](var data: T, var next: Node[T]) {
    def getData: T = this.data

    def getNext: Node[T] = this.next;

    def printList(): Unit = {
      print(data)

      if (next != null) {
        print(",")
        next.printList();
      }

    }

  }

  }
