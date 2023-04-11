package linkedlistpratice

object LLByRockJVM {
  trait DLList[+T] {
    def value: T // instead of "head"
    def prev: DLList[T]
    def next: DLList[T]
    def prepend[S >: T](element: S): DLList[S]
    def append[S >: T](element: S): DLList[S]

    def updatePrev[S >: T](newPrev: => DLList[S]): DLList[S]
    def updateNext[S >: T](newNext: => DLList[S]): DLList[S]
  }

  case object DLEmpty extends DLList[Nothing] {
    override def value = throw new NoSuchElementException("head of an empty list")
    override def prev = throw new NoSuchElementException("prev of an empty list")
    override def next = throw new NoSuchElementException("tail of an empty list")

    override def prepend[S >: Nothing](element: S) = new DLCons(element, DLEmpty, DLEmpty)
    override def append[S >: Nothing](element: S) = new DLCons(element, DLEmpty, DLEmpty)

    override def updatePrev[S >: Nothing](newPrev: => DLList[S]): DLList[S] = this
    override def updateNext[S >: Nothing](newNext: => DLList[S]): DLList[S] = this
  }

  class DLCons[+T](override val value: T, p: => DLList[T], n: => DLList[T]) extends DLList[T] {
    override lazy val prev: DLList[T] = p
    override lazy val next: DLList[T] = n

    override def updatePrev[S >: T](newPrev: => DLList[S]) = {
      lazy val result: DLCons[S] = new DLCons(value, newPrev, n.updatePrev(result))
      result
    }

    override def updateNext[S >: T](newTail: => DLList[S]) = {
      lazy val result: DLCons[S] = new DLCons(value, p.updateNext(result), newTail)
      result
    }

    def append[S >: T](element: S): DLList[S] = {
      lazy val result: DLList[S] = new DLCons(value, p.updateNext(result), n.append(element).updatePrev(result))
      result
    }

    def prepend[S >:T](element: S): DLList[S] = {
      lazy val result: DLList[S] = new DLCons(value, p.prepend(element).updateNext(result), n.updatePrev(result))
      result
    }
  }

  def main(args: Array[String]): Unit = {
    val list = DLEmpty.prepend(1).append(2).prepend(3).append(4)
    println(list.value) // 1
    println(list.next.value) // 2
    println(list.next.prev == list) // true
    println(list.prev.value) // 3
    println(list.prev.next == list) // true
    println(list.next.next.value) // 4
    println(list.next.next.prev.prev == list) // true
    println(list)
  }

}
