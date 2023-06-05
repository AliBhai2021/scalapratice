package GraphsPratice

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

object PrimsAlg extends App{

  def orderQueue(x:(String,Int))= -x._2

  def findMinCost(startVertex:String, graph:List[(String,Int,String)]):Int={
    val queue = mutable.PriorityQueue[(String,Int)](){
      Ordering.by(orderQueue)
    }

    queue.enqueue((startVertex,0))
    var minCost =0
    var visited =ListBuffer[String]()
    while(queue.nonEmpty){
      val currVertex = queue.dequeue()
      if(!visited.contains(currVertex._1)){
        visited+=currVertex._1
        minCost += currVertex._2
        for(neighbour <- graph.filter(_._1 == currVertex._1) if!visited.contains(neighbour._3))
          queue.enqueue((neighbour._3,neighbour._2))
      }
    }
    minCost
  }

  val graph1=List(("0",10,"1"),("0",15,"2"),("0",30,"3"),("1",4,"3"),("2",5,"3"))
  println("Minimum Cost  = "+findMinCost("0",graph1))
}
