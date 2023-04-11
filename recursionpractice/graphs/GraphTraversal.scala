package graphs

object GraphTraversal extends App{

  def successor(element:String,graph:List[(String,String)]):List[String]={
    println(s"findBE(successor._1},gp) ="+graph.filter(_._1 == element).map(_._2))
    graph.filter(_._1 == element).map(_._2)
  }

  def BFStraversal(start:String,graph:List[(String,String)]): List[String]={
    def bsf(vertex: List[String], visted: List[String]=List()): List[String]={
      vertex match {
        case Nil => visted
        case x :: xs if visted.contains(x)=> bsf(xs,visted)
        case x :: xs => bsf(xs ++ successor(x,graph), x :: visted)
      }
    }
    val result = bsf(List(start),List())
    result.reverse
  }

  def DFStraversal(start:String,graph:List[(String,String)]): List[String]={
    def dsf(vertex: List[String], visted: List[String]=List()): List[String]={
      vertex match {
        case Nil => visted
        case x :: xs if visted.contains(x)=> dsf(xs,visted)
        case x :: xs => dsf(xs,dsf(successor(x,graph), x :: visted))
      }
    }
    val result = dsf(List(start),List())
    result.reverse
  }

  val list = List(("a", "b"), ("a", "c"), ("b", "d"),("b", "e"), ("b", "c"), ("c", "e"), ("d", "f"))
  println("DFS = "+DFStraversal("a",list))

  println("BFS = "+BFStraversal("a",list))

}
