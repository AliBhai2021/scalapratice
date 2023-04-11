package trees


object CompareBinaryTreeEqual {

  trait BinaryTree
  case class Branch( v:Int,lbt:BinaryTree, rbt:BinaryTree) extends BinaryTree
  case object Leaf extends BinaryTree

  def main(args: Array[String]): Unit = {
    val myList = List(1,2,3,4,5,6)
    val myBinTree = createTree(myList)
    val myBinTreeFlipped = flip(myBinTree)
    println(myBinTree)
    println(myBinTreeFlipped)
    println(flipEqual(myBinTree, myBinTreeFlipped))
  }
  def flipEqual(binTree1: BinaryTree, binTree2:BinaryTree): Boolean =
    (binTree1, binTree2) match {
    case (Leaf, Leaf) => true
    case (Branch(value1, leftBranch1, rightBranch1), Branch(value2, leftBranch2, rightBranch2))
      if value1 == value2 => //comparision start from  left Tree(1) and right Tree(2)
            flipEqual(leftBranch1,rightBranch2) && flipEqual(leftBranch2, rightBranch1)
    case _ => false
  }
  def createTree(list: List[Int]): BinaryTree =
    list match {
      case Nil => Leaf
      case x :: xs => {
        val halfLength = xs.length / 2
        Branch(x, createTree(xs.take(halfLength)),
          createTree(xs.drop(halfLength)))
      }
    }
  def flip(binTree: BinaryTree): BinaryTree =
    binTree match {
      case Leaf => Leaf
      case Branch(value, leftBranch, rightBranch) =>
        Branch(value, flip(rightBranch), flip(leftBranch))
    }

}
