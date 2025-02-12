package DS.BT

object BT {

  sealed trait BinaryTree[+A]
  case object Leaf extends BinaryTree[Nothing]
  case class Branch[A](value: A, left: BinaryTree[A],right: BinaryTree[A]) extends BinaryTree[A]

    def main(args: Array[String]): Unit = {
      val myList = List(1,2,3,4,5,6)
      val myBinTree = createTree(myList)
      println(myBinTree)
      println(size(myBinTree))
      println(depth(myBinTree))
      println("depthOfNode :" +depthOfNode(myBinTree,3))
      println("findDepth :"+findDepth(myBinTree,6))
    }
    def createTree[A](list: List[A]): BinaryTree[A] =
      list match {
        case Nil => Leaf
        case x :: xs => {
          val halfLength = xs.length / 2
          Branch(x, createTree(xs.take(halfLength)), createTree(xs.drop(halfLength)))
        }
      }
    def size[A](binTree: BinaryTree[A]): Int = binTree match {
      case Leaf => 0
      case Branch(_, leftBranch, rightBranch) => 1 +  size(leftBranch) + size(rightBranch)
    }
    def depth[A](binTree: BinaryTree[A]): Int = binTree match {
      case Leaf => 0
      case Branch(_, leftBranch, rightBranch) => 1 +
        (depth(leftBranch) max depth(rightBranch))
    }

  def depthOfNode[A](binTree: BinaryTree[A], node:Int, depth:Int=0): Int = binTree match {
    case Leaf => 0
    case Branch(x, leftBranch, rightBranch) => if(x==node) depth+1 else
    //case Branch(_, leftBranch, rightBranch) =>
      (depthOfNode(leftBranch,node,depth+1) min depthOfNode(rightBranch,node,depth+1))
  }

  def findDepth[A](binTree: BinaryTree[A],node:Int): Int = {
    var c = 0
    binTree match {
      case Leaf => c
      case Branch(value, leftBranch, rightBranch) if value == node =>
        println("case1 : "+value +"c :"+c)
        c
      case Branch(value, leftBranch, rightBranch) if findDepth(leftBranch,node) >= 0=>
        println("case2 : "+value +"c :"+c)
        c +=1
      case Branch(value, leftBranch, rightBranch) if findDepth(rightBranch,node) >= 0=>
        println("case3 : "+value +"c :"+c)
        c +=1
      case Branch(value, leftBranch, rightBranch)  =>
        println("case4 : "+value +"c :"+c)
        c+=1
    }
    c
  }

}
