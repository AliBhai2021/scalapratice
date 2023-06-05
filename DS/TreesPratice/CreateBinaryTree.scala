package TreesPratice


trait BinaryTree
case class BranchNode(lbt:BinaryTree,value:Int,rbt:BinaryTree) extends BinaryTree
object Leaf extends BinaryTree

object CreateBinaryTree extends App{


  def createCompleteBinaryTree(value: Int, depth: Int): BinaryTree = {
    if(depth == 0) Leaf
    else BranchNode(createCompleteBinaryTree(2 * value,
      depth - 1),value,  createCompleteBinaryTree(2 * value + 1,
      depth -1))
  }


  def createAutoBST(arr:Array[Int],indx:Int=0, bt:BinaryTree=Leaf):BinaryTree={
    println("indx :"+indx+ " element ="+arr(indx)+ "  BT="+bt)
    indx match {
      case x if x < arr.length-1 => createAutoBST(arr,indx+1,addNode(arr(indx),bt)) // using addNode fun()
      case _ => bt
    }
  }

  def createBinaryTreeUsingArrayEments(arr:Array[Int]):BinaryTree={
    arr match {
      case elements if elements.isEmpty => Leaf
      //case element :: elements =>
      case elements =>
        val half = elements.tail.length/2
        BranchNode(
          createBinaryTreeUsingArrayEments(elements.tail.take(half))
          ,elements.head
          ,createBinaryTreeUsingArrayEments(elements.tail.drop(half))
        )
    }
  }

  def addNode(element:Int,bt:BinaryTree):BinaryTree= {
    bt match {
      case Leaf => BranchNode(Leaf, element, Leaf)
      case BranchNode(l, v, r) if element < v => BranchNode(addNode(element, l), v, r)
      case BranchNode(l, v, r) if element > v => BranchNode(l, v, addNode(element, r))
      //case BranchNode(l,v,r) if v == element => BranchNode(l,v,l)
      //case _ => Leaf
    }
  }

  type Dictionary = BinaryTree
  def empty: Dictionary = Leaf
  def insert(key: Int, dict: Dictionary): Dictionary = {
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
  }


  val arr = Array(1,2,3,4,5,6,7)
  val bt=createBinaryTreeUsingArrayEments(arr)
  println(bt)


  //---------------------------------------------------------------
  val myCompleteBinTree = createCompleteBinaryTree(1, 3);
  println(myCompleteBinTree)
  println(insert(10,myCompleteBinTree))

  val myCompleteBinTree2 = createCompleteBinaryTree(2, 3);
  println(myCompleteBinTree2)
  println(insert(11,myCompleteBinTree2))


}
