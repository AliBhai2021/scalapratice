package DS.BT

import scala.collection.mutable.ListBuffer

trait BinaryTree
case class Node(lt:BinaryTree, value:Int, rt:BinaryTree) extends BinaryTree
case object Leaf extends BinaryTree

object CreateBinaryTree extends App{

  var index = -1
  def createBinaryTreeUsingArrayWithNegitiveValue(array:Array[Int]):BinaryTree={
    index +=1
    index match {
      case idx if idx > array.length => Leaf
      case idx if array(idx) == -1 => Leaf
      case idx =>
         Node(createBinaryTreeUsingArrayWithNegitiveValue(array), array(idx), createBinaryTreeUsingArrayWithNegitiveValue(array))
    }
  }
  val array = Array(1,2,4,-1,-1,5,-1,-1,3,-1,6,-1,-1)
  val bt1 = createBinaryTreeUsingArrayWithNegitiveValue(array)
  println("createBinaryTreeUsingArrayWithNegitiveValue : "+bt1)

  def createBinaryTreeUsingHalfArray(array:Array[Int]):BinaryTree={
    array match {
      case  arr if arr.isEmpty => Leaf
      case arr =>
        val half = arr.tail.length/2
        Node(createBinaryTreeUsingHalfArray(arr.tail.take(half)), arr.head, createBinaryTreeUsingHalfArray(arr.tail.drop(half)))
    }
  }
  val array2 = Array(1,2,3,4,5,6,7)
  println("createBinaryTreeUsingHalfArray :"+createBinaryTreeUsingHalfArray(array2))
  println("createBinaryTreeUsingHalfArray :"+createBinaryTreeUsingHalfArray( Array(1,2,3,4,5,6,7,8,9)))

  def createCompleteBinaryTree(startValue:Int, depth:Int):BinaryTree={
    depth match {
      case dp if dp == 0 => Leaf
      case dp => Node(createCompleteBinaryTree(startValue*2, dp-1),startValue, createCompleteBinaryTree(startValue*2 +1, dp-1))
    }
  }
  println("createCompleteBinaryTree :"+createCompleteBinaryTree(1,3))


}
