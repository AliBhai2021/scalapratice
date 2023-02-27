package DS

object GraphVertixSucessors extends App{

  val myGraph = List(("a", "b"), ("a", "c"), ("b", "d"),("b", "e"), ("b", "c"), ("c", "e"), ("d", "f"))

  def sucessors(root:String,list:List[(String,String)]):List[String]={
       list.filter(_._1 == root).map(_._2)
  }

  println(" a :"+sucessors("a",myGraph))

  def traverseBreadthFirst(start: String,graph: List[(String, String)]): List[String] = {
    def breadthFirst(vertices: List[String], visited:List[String]): List[String] =
        vertices match {
          case Nil => visited
          case x :: xs if visited.contains(x) =>  breadthFirst(xs, visited)
          case x :: xs => breadthFirst(xs ++ sucessors(x, graph), x :: visited)
    }
    val result = breadthFirst(List(start), List())
    result.reverse
  }

  def traverseDepthFirst(start: String, graph:List[(String, String)]): List[String] = {
    def depthFirst(vertices: List[String], visited: List[String]): List[String] = vertices match {
      case Nil => visited
      case x :: xs if visited.contains(x) => depthFirst(xs,visited)
      case x :: xs => depthFirst(sucessors(x, graph), x :: visited)
    }
    val result = depthFirst(List(start), List())
    result.reverse
  }
}
