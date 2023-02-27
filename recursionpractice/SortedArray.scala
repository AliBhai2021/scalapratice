package recursionpractice

import scala.collection.mutable.ArrayBuffer

object SortedArray extends App{

  val arr = Array(1,3,2,5,4,6)
  val arr2 = Array(1,2,3,4,5,6)
  println(" is Array sorted :")
  println(sortArray(arr))
  println(sortArray(arr2))
  println(sortArray(Array(1,2,3,4,5,6)))

  def sortArray(a:Array[Int], index:Int=0):Boolean= {
    index match {
      case x if x == a.length-1 => true
      case _ =>
        a(index) < a(index + 1) && sortArray(a, index + 1)
    }
  }
    println(" find target in Array :")
    println(find(arr2,3))
    println(find(arr2,6))
    println(find(arr2,8))

    def find(a:Array[Int], target:Int, index:Int=0):Boolean={
      index match {
        case x if x == a.length => false
        case _ =>
          a(index) == target || find(a,target,index+1)
      }
    }

  println("find IndexOfTarget :")
  println(findIndexOfTarget(arr2,3))
  println(findIndexOfTarget(arr2,8))
  println(findIndexOfTarget(arr2,6))

  def findIndexOfTarget(a:Array[Int], target:Int, index:Int=0):Int={
    index match {
      case x if x == a.length => -1
      case x if a(x) == target => x
      case _ =>
          findIndexOfTarget(a,target,index+1)
    }
  }

 // var arrayBuffer = ArrayBuffer[Int]()
  println("findIndexListOfTarget :")
  println(findIndexListOfTarget(Array(1,2,3,1,2,4,5,1),1))
  println(findIndexListOfTarget(Array(1,2,3,1,2,4,5,1,2,2,2),2))
  println(findIndexListOfTarget(arr2,10))

  def findIndexListOfTarget(a:Array[Int], target:Int, index:Int=0, arrayBuffer:ArrayBuffer[Int]= ArrayBuffer()):ArrayBuffer[Int]={
    index match {
      case x if x == a.length => arrayBuffer
      case x if a(x) == target =>
        findIndexListOfTarget(a,target,index+1, arrayBuffer += x)
      case _ =>
        findIndexListOfTarget(a,target,index+1, arrayBuffer)
    }
  }



}
