package GraphsPratice

import scala.collection.mutable

object BellmanFordAlg extends App{

  def findShortestPath(startVertex:String,graph:List[(String,Int,String)]):mutable.LinkedHashMap[String,Int]={
      val vertexDistance = mutable.LinkedHashMap[String,Int]()
      vertexDistance.put(startVertex,0)

      for(vertex <- graph.map(_._1).distinct){
        for(currVertex <- graph.filter(_._1 == vertex)){
          if(vertexDistance.getOrElse(currVertex._1, Int.MaxValue) != Int.MaxValue &&
              vertexDistance.getOrElse(currVertex._1,Int.MaxValue)+currVertex._2 < vertexDistance.getOrElse(currVertex._3, Int.MaxValue))
            vertexDistance.put(currVertex._3, vertexDistance.getOrElse(currVertex._1,Int.MaxValue)+currVertex._2)
        }
      }

      //------------ detecting negative sum outcome
      for(i <- 0 until graph.length){
        for(currVertex <- graph.filter(_._1 == graph(i)._1)){
          if(vertexDistance.getOrElse(currVertex._1, Int.MaxValue) != Int.MaxValue &&
            vertexDistance.getOrElse(currVertex._1,Int.MaxValue)+currVertex._2 < vertexDistance.getOrElse(currVertex._3, Int.MaxValue))
            println(("Negative weight is Detected "))

        }
      }
    vertexDistance
  }

  val graph = List(("0",2,"1"),("0",4,"2"),("1",-4,"2"),("2",2,"3"),("3",4,"4"),("4",-1,"1"))
  //val graph = List(("0",2,"1"),("0",4,"2"),("1",-40,"2"),("2",2,"3"),("3",4,"4"),("4",-1,"1"))
  println(findShortestPath("0",graph))

}
