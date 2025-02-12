package DS.graphs

object BFS extends App{

  def calcSuccessorSet(vertex: String, graph:List[(String, String)]): List[String] = graph match {
    case Nil => Nil
    case x :: xs if (vertex == x._1) => x._2 ::
      calcSuccessorSet(vertex, xs)
    case _ :: xs => calcSuccessorSet(vertex, xs)
  }
  def traverseBreadthFirst(start: String,graph: List[(String, String)]): List[String] = {
    def breadthFirst(vertices: List[String], visited:List[String]): List[String] =
      vertices match {
        case Nil => visited
        case x :: xs if visited.contains(x) =>   breadthFirst(xs, visited)
        case x :: xs => breadthFirst(xs ++ calcSuccessorSet(x, graph), x :: visited)
    }
    val result = breadthFirst(List(start), List())
    result.reverse
  }
  def traverseDepthFirst(start: String, graph: List[(String, String)]): List[String] = {
    def depthFirst(vertices: List[String], visited:List[String]): List[String] = vertices match {
      case Nil => visited
      case x :: xs if(visited.contains(x)) => depthFirst(xs,  visited)
      case x :: xs => depthFirst(calcSuccessorSet(x, graph),  x :: visited)
    }
    val result = depthFirst(List(start), List())
    result.reverse
  }


}
