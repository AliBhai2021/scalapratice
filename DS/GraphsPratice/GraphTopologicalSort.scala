package GraphsPratice

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

object GraphTopologicalSort extends App{

  val stack = mutable.Stack[String]()
  var visited = mutable.LinkedHashSet[String]()
  def dfs(vertex:String,graph:List[(String,String)]):Unit={
    for(neighbourVertex <- graph.filter(_._1 == vertex).map(_._2) if !visited.contains(neighbourVertex)){
      visited.add(neighbourVertex)
      dfs(neighbourVertex,graph)
    }
    stack.push(vertex)
  }

  def findTopologicalSort(graph:List[(String,String)]):Unit={
    for(vertex <- graph.map(_._1).distinct if !visited.contains(vertex)) {
      visited.add(vertex)
      dfs(vertex,graph)
    }

    while(stack.nonEmpty){
      print(" "+stack.pop())
    }

  }

  val graph1 = List(("4","0"),("5","0"),("5","2"),("4","1"),("2","3"),("3","1"))
  findTopologicalSort(graph1)

}
