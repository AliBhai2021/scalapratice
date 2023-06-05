package TreesPratice

object IsSymmetricTree extends App{

  def isSymmetricTree(bt:BinaryTree):Boolean={
    bt match {
      case Leaf  => true
      case BranchNode(lbt, value, rbt) =>
        //helper(lbt,rbt)
        true
    }
  }

/*  def helper(lbt:BinaryTree,rbt:BinaryTree):Boolean={
    (lbt,rbt) match {
      case (lb,rb) if lb == Leaf && rb == Leaf => true
      case (lb,rb) if lb == Leaf || rb == Leaf || lb.value != rb.value => false
      case (lb,rb) =>
        helper(lbt.lbt, rbt.rbt) && helper(lbt.rbt, rbt.lbt)
    }
  }*/

}
