package GraphsPratice

import scala.collection.mutable

object BridgeEdgeInGraph extends App{

  def findBridgeEdge(startVertex:String,graph:List[(String,String)]):Unit={

    var visitedDT = mutable.LinkedHashMap[String,Int]()
    var visited = mutable.LinkedHashSet[String]()
    var lowDT = mutable.LinkedHashMap[String,Int]()
    visited.add(startVertex)
    visitedDT.put(startVertex,1)
    lowDT.put(startVertex,1)
    var count= 1
    def dfs(vertex:String):Unit={
      for(neighbourVertex <- graph.filter(_._1 == vertex) ){
        if (!visited.contains(neighbourVertex._2)) {
          count += 1
          visited.add(neighbourVertex._2)
          lowDT.put(neighbourVertex._2, count)
          visitedDT.put(neighbourVertex._2, count)
          dfs(neighbourVertex._2)
          //lowDT.put(neighbourVertex._2, Math.min(lowDT.getOrElse(neighbourVertex._1,Int.MaxValue), lowDT.getOrElse(neighbourVertex._2,Int.MaxValue)))
          lowDT.put(neighbourVertex._1, Math.min(lowDT.getOrElse(neighbourVertex._1,Int.MaxValue), lowDT.getOrElse(neighbourVertex._2,Int.MaxValue)))
          if(visitedDT.getOrElse(neighbourVertex._1,Int.MaxValue)<lowDT.getOrElse(neighbourVertex._2,Int.MaxValue))
            println("Bridge Edge is : "+neighbourVertex)
        }
        else{
          lowDT.put(neighbourVertex._1, Math.min(lowDT.getOrElse(neighbourVertex._1,Int.MaxValue), visitedDT.getOrElse(neighbourVertex._2,Int.MaxValue) ))

        }
      }
    }

    dfs(startVertex)
    println("visited = "+visited)
    println("lowDT = "+lowDT)
    println("visitedDT = "+visitedDT)
  }

  val graph1 = List(("1","2"),("2","3"),("3","4"),("4","1"),("4","5"),("5","6"))
  findBridgeEdge("1",graph1)

}
