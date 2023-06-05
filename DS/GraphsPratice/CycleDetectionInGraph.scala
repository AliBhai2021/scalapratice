package GraphsPratice

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

object CycleDetectionInGraph extends App{

  def findCycleInGraph(startVertex:String, graph:List[(String,String)]):Boolean={
    val stack = mutable.LinkedHashSet[String]()
    var visited = ListBuffer[String]()
    var isCycleDetected = false
    visited += startVertex
    def dfs(vertex:String):Boolean={
      for(neighbourVertex <- graph.filter(_._1 == vertex).map(_._2) if !isCycleDetected){
        if(!visited.contains(neighbourVertex) || !stack.contains(neighbourVertex)){
          visited += neighbourVertex
          stack.add(neighbourVertex)
            dfs(neighbourVertex)
          stack.remove(neighbourVertex)
        }
          else{
          isCycleDetected = true;
          isCycleDetected
        }
      }
      isCycleDetected
    }

    dfs(startVertex)
  }

  val graph1 = List(("1","2"),("1","3"),("2","4"),("3","4"),("4","5"))
  println("Is Cycle Detected in Graph ?  : "+findCycleInGraph("1",graph1))

  val graph2 = graph1 ++ graph1.map(x=>(x._2,x._1))
  println("Is Cycle Detected in Graph ?  : "+findCycleInGraph("1",graph2))

}
