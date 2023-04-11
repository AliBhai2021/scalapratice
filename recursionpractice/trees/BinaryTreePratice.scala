package trees

object BinaryTreePratice extends App {

  trait BinaryTree
  case class BranchNode(lbt:BinaryTree, v:Int, rbt:BinaryTree) extends BinaryTree
  case object Leaf extends BinaryTree


  def CreateBinaryTree(arr :Array[Int]):BinaryTree={
    arr match {
      case x if x.isEmpty => Leaf
      case x => BranchNode(
        CreateBinaryTree(x.tail.take(x.tail.length/2 ))
        ,x.head
        ,CreateBinaryTree(x.tail.drop(x.tail.length/2)))
    }
  }

  def addNode(element:Int,bt:BinaryTree):BinaryTree={
    bt match {
      case Leaf => BranchNode( Leaf,element, Leaf)
      case BranchNode(l,v,r) if element < v =>BranchNode(addNode(element,l),v,r)
      case BranchNode(l,v,r) if element > v =>BranchNode(l,v,addNode(element,r))
      //case BranchNode(l,v,r) if v == element => BranchNode(l,v,l)
      //case _ => Leaf
    }

  }

  val arr = Array(1,2,3,4,5,6,7)
  val bt=CreateBinaryTree(arr)
  println(bt)

  val  addnew = addNode(8,bt)
  println(" New Node Added = "+addnew)

  val  addnew2 = addNode(9,addnew)
  println(" New Node Added = "+addnew2)

  //FindNodes Leaf NonLeaf  Degree  Height
  def calculate(bt:BinaryTree, res:Int=0):Int={
/*    val nonLeafCount = bt match{
                        case BranchNode(l,v,r) if l !=Leaf || r != Leaf => 1+calculate(l)+calculate(r)
                        case _ => 0
                      }
    println(" nonLeafCount = "+nonLeafCount)
    val leafCount = bt match{
      case BranchNode(l,v,r) if l == Leaf && r == Leaf => res+1
      case BranchNode(l,v,r)  => calculate(l)+calculate(r)
      case _ => res
    }
    println( "leafCount = "+leafCount)*/
    bt match{
      case BranchNode(l,v,r) if l !=Leaf || r != Leaf =>
        val left=1+calculate(l)
        val right=1+calculate(r)
        if(left>right)
          left
        else
          right
      case _ => 0
    }
    //println(" height = "+height)
/*    val degree = bt match{
      case BranchNode(l,v,r) if l !=Leaf || r != Leaf || r == Leaf || l ==Leaf =>
        val left=1+calculate(l)
        val right=1+calculate(r)
            Math.max(left,right)
      case _ => 0
    }
    println(" degree = "+degree)*/


  }

  println(calculate(bt))
}
