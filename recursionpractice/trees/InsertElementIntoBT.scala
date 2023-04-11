package trees

object InsertElementIntoBT {
  trait BinaryTree
  case class BranchNode(lbt:BinaryTree, v:Int, rbt:BinaryTree) extends BinaryTree
  case object Leaf extends BinaryTree
  type Dictionary = BinaryTree
  def empty: Dictionary = Leaf
  def insert(key: Int, dict: Dictionary): Dictionary =
    dict match {
      case Leaf => BranchNode( Leaf,key, Leaf)
      case BranchNode(lb,v, rb) if (key == v) =>
        println(s"key ${key} already present")
        dict
      case BranchNode(lb,v, rb) if (key < v) =>
        BranchNode( insert(key, lb),v, rb)
      case BranchNode(lb,v, rb) if (key > v) =>
        BranchNode(lb,v, insert(key, rb))
    }

  def createCompleteBinaryTree(value: Int, depth: Int): BinaryTree =
    if(depth == 0) Leaf
    else BranchNode(createCompleteBinaryTree(2 * value,
      depth - 1),value,  createCompleteBinaryTree(2 * value + 1,
      depth -1))

  def main(args: Array[String]): Unit = {
    val myCompleteBinTree = createCompleteBinaryTree(1, 3);
    println(myCompleteBinTree)
    println(insert(10,myCompleteBinTree))

    val myCompleteBinTree2 = createCompleteBinaryTree(2, 3);
    println(myCompleteBinTree2)
    println(insert(11,myCompleteBinTree2))


  }

}
