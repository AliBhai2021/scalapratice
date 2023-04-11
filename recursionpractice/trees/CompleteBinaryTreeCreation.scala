package trees

object  CompleteBinaryTreeCreation {
  def main(args: Array[String]): Unit = {
    val myCompleteBinTree = createCompleteBinaryTree(1, 3);
    println(myCompleteBinTree)
  }

  trait BinaryTree
  case class BranchNode( lbt:BinaryTree, v:Int,rbt:BinaryTree) extends BinaryTree
  case object Leaf extends BinaryTree

  def createCompleteBinaryTree(value: Int, depth: Int): BinaryTree =
    if(depth == 0) Leaf
    else BranchNode(createCompleteBinaryTree(2 * value,
      depth - 1),value,  createCompleteBinaryTree(2 * value + 1,
      depth -1))

}
