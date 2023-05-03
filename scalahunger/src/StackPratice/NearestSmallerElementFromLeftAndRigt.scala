package StackPratice

import scala.collection.mutable

object NearestSmallerElementFromLeftAndRigt extends App{


  def findNearestSmallerLeftElement(arr:Array[Int]):Array[Int]= {
    val myStack = new mutable.Stack[Int]()
    for (i <- 0 until arr.length) {
      var currentElement = arr(i)
      while (myStack.nonEmpty && myStack.top >= arr(i))
        myStack.pop()
      //myStack.push(arr(i))
      if (myStack.isEmpty)
        arr(i) = -1
      else
        arr(i) = myStack.top
      myStack.push(currentElement)
    }
    arr
  }

     val input = Array(4,10,5,18,3,12,7)
    findNearestSmallerLeftElement(input).foreach(println(_))
    //val input2 = Array(3,10,5,1,15,10,7,6)
    //findNearestSmallerRightElement(input2).foreach(println(_))
//--------------------------------------------------------
    def findNearestSmallerRightElement(arr:Array[Int]):Array[Int]= {
      val myStack = new mutable.Stack[Int]()
      for (i <- arr.length-1 to 0 by -1) {
        var currentElement = arr(i)
        while (myStack.nonEmpty && myStack.top >= arr(i))
          myStack.pop()
        if (myStack.isEmpty)
          arr(i) = -1
        else
          arr(i) = myStack.top

        //println("Element pushing into Stack = "+currentElement)
        myStack.push(currentElement)
      }
      arr
    }

}
