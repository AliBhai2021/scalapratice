package GraphsPratice

import scala.collection.mutable

object DijkstrasAlgorithm extends App{

  def orderQueue(x:(String,Int))= -x._2

  def findShortestPaths(startVertex:String, graph:List[(String,Int,String)]):mutable.LinkedHashMap[String,Int]= {

    val queue = mutable.PriorityQueue[(String, Int)]()
      {
        Ordering.by(orderQueue)
      }
    queue.enqueue((startVertex, 0))
    val vertexDistance = mutable.LinkedHashMap[String,Int]() //mutable.LinkedHashMap[String,Int]()
    vertexDistance.put(startVertex,0)
    val visited = mutable.LinkedHashSet[String]()

    while (queue.nonEmpty) {
      val currVertex = queue.dequeue()
      if(!visited.contains(currVertex._1))
      visited.add(currVertex._1)
      for (neighbourVertices <- graph.filter(_._1 == currVertex._1).map(x => (x._3, x._2))) {
        vertexDistance.put(neighbourVertices._1,
          Math.min(vertexDistance.getOrElse(neighbourVertices._1, Int.MaxValue),
            vertexDistance.getOrElse(currVertex._1,Int.MaxValue)+neighbourVertices._2))

        queue.enqueue((neighbourVertices._1,  vertexDistance.getOrElse(currVertex._1, Int.MaxValue)+neighbourVertices._2))
      }
    }
    println("Visited = "+visited)
    vertexDistance
  }

  val graph1=List(("0",2,"1"),("1",7,"3"),("3",1,"5"),("0",4,"2"),("2",3,"4"),("4",5,"5"),("1",1,"2"),("4",2,"3"))
  println("Shortest Paths: "+findShortestPaths("0",graph1))

}
