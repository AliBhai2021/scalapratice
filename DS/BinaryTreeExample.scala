package DS

object BinaryTreeExample extends App{

  trait BinaryTree
  case class Node(left:BinaryTree,value:Int,right: BinaryTree) extends BinaryTree
  case object Leaf extends BinaryTree

  def createBinaryTreee(arr:List[Int]):BinaryTree={
    arr match {
      case Nil => Leaf
      case x:: ls => Node(createBinaryTreee(ls.take(ls.length/2)),x,createBinaryTreee(ls.drop(ls.length/2)))
    }
  }

  def sizeNonLeafNodes (bt:BinaryTree):Int={
    bt match {
      case Leaf => 0
      case Node(l,_,r)=> 1+(sizeNonLeafNodes(l)+sizeNonLeafNodes(r))
    }
  }

  def depthOfBT(bt:BinaryTree):Int={
    bt match {
      case Leaf => 0
      case Node(l,_,r) =>  1+(depthOfBT(l) max depthOfBT(r))
    }
  }

  def depthOfBTtoNode(bt:BinaryTree,nodeValue : Int):Int={
    bt match {
      case Leaf => 0
      case Node(_,x,_) if nodeValue ==x => 0
      case Node(l,_,r) =>  1+(depthOfBT(l) min depthOfBT(r))
    }
  }

  val list = List(11,20,13,24,5,36,17)
  val b1 = createBinaryTreee(list)
  println(b1)

  println("size : "+sizeNonLeafNodes(b1))
  println("dept :"+ depthOfBT(b1))
  println("depthOfBTtoNode :"+ depthOfBTtoNode(b1,5))
}
