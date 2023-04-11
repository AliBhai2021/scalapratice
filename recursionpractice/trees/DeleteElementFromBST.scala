package trees

object DeleteElementFromBST extends App{

  trait BinaryTree
  case class Branch(lbt:BinaryTree,v:Int,rbt:BinaryTree) extends BinaryTree
  case object Leaf extends BinaryTree

  def deleteElement(bt:BinaryTree, element:Int):BinaryTree= {
    bt match {
      case Branch(l, v, r) if v > element => Branch(deleteElement(l, element), v, r)
      case Branch(l, v, r) if v < element => Branch(l, v, deleteElement(r, element))
      case Branch(l, v, r) if v == element =>
        mergeBT(l, r)
      //case _ => bt
    }
  }
  def mergeBT(lbt:BinaryTree,rbt:BinaryTree):BinaryTree= {
    (lbt, rbt) match {
      case (Branch(l1, v1, r1), Branch(l2, v2, r2)) if v1 < v2 => Branch(mergeBT(lbt, l2), v2, r2)
      case (Branch(l1, v1, r1), Branch(l2, v2, r2)) if v1 > v2 => Branch(l1, v1, mergeBT(r1, rbt))
      case (Branch(l1, v1, r1), Leaf) => Branch(l1, v1, r1)
      case (Leaf, Branch(l1, v1, r1)) => Branch(l1, v1, r1)
      case (Leaf, Leaf) => Leaf
    }
  }
  def addElement(element:Int,bt:BinaryTree):BinaryTree={
    bt match {
      case Leaf => Branch(Leaf,element,Leaf)
      case Branch(l,v,r) if v < element => Branch(l,v,addElement(element,r))
      case Branch(l,v,r) if v > element => Branch(addElement(element,l),v,r)
      case _ => bt
    }
  }

  def createBST(arr:Array[Int], indx:Int=0, bt:BinaryTree=Leaf):BinaryTree={
     indx match {
       case x if x == arr.length => bt
       case x => createBST(arr,indx+1,addElement(arr(indx),bt))
     }
  }

  val arr = Array(2,4,7,3,6,1,8)
  val bt = createBST(arr)
  println(bt)
  val del=deleteElement(bt,7)
  println(del)
}
