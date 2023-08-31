package DS.BT

object BinaryTreeOperations extends App{
  import CreateBinaryTreeUsingArrayOfInput._
  val binaryTree = createCompleteBinaryTree(1,3)

  def countNodes(bt:BinaryTree):Int={
    bt match {
      case bt if bt == Leaf => 0
      case Node(lt,v,rt)=> countNodes(lt) + countNodes(rt) + 1
    }
  }
  println("Number of Nodes : "+countNodes(binaryTree))

  def heightOfTree(bt:BinaryTree):Int={
    bt match {
      case bt if bt == Leaf => 0
      case Node(lt,v,rt)=> Math.max(heightOfTree(lt),heightOfTree(rt))+1
    }
  }
  println("Height of Tree : "+heightOfTree(binaryTree))

  def SumOfNodes(bt:BinaryTree):Int={
    bt match {
      case bt if bt == Leaf => 0
      case Node(lt,v,rt)=> SumOfNodes(lt) + SumOfNodes(rt) + v
    }
  }
  println("Sum of Nodes : "+SumOfNodes(binaryTree))

  def preOrderTraversalOfBT(bt:BinaryTree):Unit={
    bt match {
      case Leaf => " "
      case Node(lt,v,rt) => print(" "+v)
        preOrderTraversalOfBT(lt)
        preOrderTraversalOfBT(rt)

    }
  }
  println("PreOrder Traversal of BT : ");preOrderTraversalOfBT(binaryTree)

  println("")
  def inOrderTraversalOfBT(bt:BinaryTree):Unit={
    bt match {
      case Leaf => " "
      case Node(lt,v,rt) =>
        inOrderTraversalOfBT(lt)
        print(" "+v)
        inOrderTraversalOfBT(rt)

    }
  }
  println("InOrder Traversal of BT : ");inOrderTraversalOfBT(binaryTree)

  println("")
  def postOrderTraversalOfBT(bt:BinaryTree):Unit={
    bt match {
      case Leaf => " "
      case Node(lt,v,rt) =>
        postOrderTraversalOfBT(lt)
        postOrderTraversalOfBT(rt)
        print(" "+v)

    }
  }
  println("PostOrder Traversal of BT : ");postOrderTraversalOfBT(binaryTree)

  def diameterOfBinaryTree(bt:BinaryTree):Int={
    bt match {
      case Leaf => 0
      case Node(lt,v,rt) =>
        val d1 = diameterOfBinaryTree(lt)
        val d2 = diameterOfBinaryTree(rt)
        val d3 = heightOfTree(lt)+heightOfTree(rt)+1
        Math.max(d3, Math.max(d1,d2))
    }
  }
  println("")
  println("Diameter Of Binary Tree : "+diameterOfBinaryTree(binaryTree))
}
