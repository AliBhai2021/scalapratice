package trees

object CreateBinarySearchTree extends App{

  trait BinaryTree
  case class Branch(lb:BinaryTree,v:Int,rb:BinaryTree) extends BinaryTree
  case object Leaf extends BinaryTree

  def addNode(element:Int,bt:BinaryTree):BinaryTree= {
    bt match {
      case Leaf => Branch(Leaf, element, Leaf)
      case Branch(l, v, r) if element < v => Branch(addNode(element, l), v, r)
      case Branch(l, v, r) if element > v => Branch(l, v, addNode(element, r))

    }
  }

  def createBST(arr:Array[Int],indx:Int=0, bt:BinaryTree=Leaf):BinaryTree={
    println("indx :"+indx+ " element ="+arr(indx)+ "  BT="+bt)
    indx match {
      case x if x < arr.length-1 => createBST(arr,indx+1,addNode(arr(indx),bt))
      case _ => bt
    }
  }

  val arr = Array(9,15,5,20,16,8,12,3,6)
  val newbt = createBST(arr)
  println(newbt)
  println(addNode(11,newbt))
  // drawback of BST creation is no control on branch increasing height
  // It goes to imbalanced tree structures based on input, here we don't have ctrl.
}
