package GraphsPratice

import scala.collection.mutable.ListBuffer

object FindAllPaths extends  App {

  def findAllPaths(start:String,end:String,graph:List[(String,String)]):Unit={
    var visited=ListBuffer[String]()
    visited +=start
    def dfs(vertex:String):Unit={
      vertex match {
        case currVertex if currVertex == end => println(visited)
        case currVertex =>
          for(neighbour <- graph.filter(_._1 == currVertex).map(_._2) if !visited.contains(neighbour)){
            visited += neighbour
            dfs(neighbour)
            visited -= neighbour
          }
      }

    }
    dfs(start)

  }

  val graph = List(("1","0"),("1","2"),("2","0"),("0","3"),("3","4"),("3","5"),("5","4"))
  val graph1 = graph ++ graph.map(x => (x._2,x._1))
  println("Graph = "+graph1)
  println("findAll Paths Using Loop : "+findAllPaths("1","4",graph))


  def findAllPathsUsingRecursion(startVertex:String, endVertex:String,graph:List[(String,String)]):ListBuffer[String]={

    def dfs(vertices:List[String],visited:ListBuffer[String]):ListBuffer[String]={
      vertices match {
        case Nil => visited
        case vertex :: vertices if vertex == endVertex => println(visited); visited
        case vertex :: vertices  if visited.contains(vertex)=> dfs(vertices,visited)
        case vertex :: vertices =>
        dfs(vertices, dfs(graph.filter(_._1 == vertex).map(_._2), visited += vertex) -= vertex)
      }
    }
    dfs(List(startVertex), ListBuffer())
  }

  println("find All Paths Using Recursion : "+findAllPathsUsingRecursion("1","4",graph))

}
