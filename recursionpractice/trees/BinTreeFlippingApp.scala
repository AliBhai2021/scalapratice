package trees

trait BinaryTree
case class Branch( lbt:BinaryTree, v:Int,rbt:BinaryTree) extends BinaryTree
case object Leaf extends BinaryTree

object BinTreeFlippingApp {
  def main(args: Array[String]): Unit = {
    val myList = List(1,2,3,4,5,6)
    val myBinTree = createTree(myList)
    println(myBinTree)
    println(flip(myBinTree))
  }
  def flip(binTree: BinaryTree): BinaryTree =
    binTree match {
      case Leaf => Leaf
      case Branch( leftBranch,value, rightBranch) =>
        Branch( flip(rightBranch),value, flip(leftBranch))
    }
  def createTree(list: List[Int]): BinaryTree =
    list match {
      case Nil => Leaf
      case x :: xs => {
        val halfLength = xs.length / 2
        Branch(createTree(xs.take(halfLength)),x,
          createTree(xs.drop(halfLength)))
      }
    }

}
