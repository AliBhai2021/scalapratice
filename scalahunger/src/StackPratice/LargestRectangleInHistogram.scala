package StackPratice

import scala.collection.mutable

object LargestRectangleInHistogram extends App{

  def findPreviousSmallerElementsPositions(arr:Array[Int]):Array[Int]={
    val mystack = new mutable.Stack[Int]()
    val ps = new Array[Int](arr.length)
    for( i <- 0 until arr.length){
      var currentValue =arr(i)
      while (mystack.nonEmpty && arr(mystack.top) >= arr(i))
        mystack.pop()

      if(mystack.isEmpty)
        ps(i)= -1
      else
      ps(i)=mystack.top
        mystack.push(i)
    }
    ps

  }


  def findNextSmallerElementsPositions(arr:Array[Int]):Array[Int]={
    val mystack = new mutable.Stack[Int]()
    val ns = new Array[Int](arr.length)
    for (i <- arr.length-1 to 0 by -1) {
      while (mystack.nonEmpty && mystack.top >=0 && arr(mystack.top) >= arr(i))
        mystack.pop()

      if(mystack.isEmpty)
        ns(i)= 9 // -1
      else
        ns(i)=mystack.top
      mystack.push(i)
    }
    ns
  }

  def findLargetRectangleInHistogram(input:Array[Int]):Int={
    val ps = findPreviousSmallerElementsPositions(input)
    val ns = findNextSmallerElementsPositions(input)
    var maxArea = 0
    for(i<- 0 until input.length){
      var currentArea = (ns(i) - ps(i) -1 )*input(i)
      maxArea = Math.max(maxArea,currentArea)
    }
    maxArea
  }
  // driver Program ::

  //val input = Array(4,10,5,18,3,12,7)
  val input = Array(4,2,1,5,6,3,2,4,2)

  println("MAX-Rectangle : "+findLargetRectangleInHistogram(input))
}
