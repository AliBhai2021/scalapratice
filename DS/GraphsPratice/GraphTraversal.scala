package GraphsPratice

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

object GraphTraversal extends App{

  //----------------- DFS ------------ Using recursion
  def DFSusingRecursion(startVertex:String,graph:List[(String,String)]):ListBuffer[String]={

    def dfs(vertices:List[String],visited:ListBuffer[String]):ListBuffer[String]={
      vertices match {
        case Nil => visited
        case vertex :: vertices if visited.contains(vertex)=> dfs(vertices,visited)
        case vertex :: vertices =>
          dfs(vertices ++ graph.filter(_._1 == vertex).map(_._2), visited+=vertex)
      }
    }
    dfs(List(startVertex),ListBuffer())
  }

  //---------------- DFS ----------- UsingLoop

  def DFSusingLoop(startVertex:String, graph:List[(String,String)]):ListBuffer[String]={
    var visited = ListBuffer[String]()
    visited += startVertex
    def dfs(vertex:String):Unit={
      for(neighbour <- graph.filter(_._1 == vertex).map(_._2)){
        if(!visited.contains(neighbour)) {
          visited += neighbour
          dfs(neighbour)
        }
      }
    }
    dfs(startVertex)
    visited

  }

  val graph = List(("1","0"),("1","2"),("2","0"),("0","3"),("3","4"),("3","5"),("5","4"))
  val graph1 = graph ++ graph.map(x => (x._2,x._1))
  println("Graph = "+graph1)

  println("DFS Using Recursive : "+DFSusingRecursion("0",graph1))
  println("DFS Using Loop : "+DFSusingLoop("0",graph1))


  //======================== BFS =============== Using Recursion

  def BFSusingRecursion(startVertex:String,graph:List[(String,String)]):ListBuffer[String]={
    def bfs(vertices:List[String], visited:ListBuffer[String]):ListBuffer[String]={
      vertices match {
        case Nil => visited
        case vertex :: vertices if visited.contains(vertex) => bfs(vertices,visited)
        case vertex :: vertices =>
          bfs(vertices ++ graph.filter(_._1 == vertex).map(_._2), visited += vertex)
      }
    }
    bfs(List(startVertex),ListBuffer())
  }

  def BFSusingLoop(startVertex:String, graph:List[(String,String)]):ListBuffer[String]={
    val queue = mutable.Queue[String]()
    var visited = ListBuffer[String]()
    queue.enqueue(startVertex)

    while(queue.nonEmpty) {
      var curVertex = queue.dequeue()
      if (!visited.contains(curVertex)) {
        visited += curVertex
        for (vertices <- graph.filter(_._1 == curVertex).map(_._2) if !visited.contains(vertices)) {
          queue.enqueue(vertices)
        }
      }
    }
    visited

  }

  println("BFS Using Recursive : "+BFSusingRecursion("1",graph1))
  println("BFS Using Loop : "+BFSusingLoop("1",graph1))

}
