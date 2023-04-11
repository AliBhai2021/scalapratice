package graphs

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

object DijkstraShortestPath extends App {

  def findSuccessor(visited:ListBuffer[String], distance:mutable.LinkedHashMap[String,Int]):mutable.LinkedHashMap[String,Int]=
    distance.filterNot(x=> visited.contains(x._1))

  def findDijkstraSP(graph: Map[String, List[(String, Int, String)]],start:String):Unit={

      def findSP(vertix:String, visited:ListBuffer[String], distance:mutable.LinkedHashMap[String,Int]):Unit= {
            println("vertix ="+vertix+"     visited ="+visited+"       distance ="+distance)
            vertix match {
              case x if x.isEmpty => println("visited ="+visited); println("distance ="+distance)
              case x  if distance.isEmpty  =>
                                              distance.put(x,0)
                                              findSP(vertix,visited,distance)
              case x if !visited.contains(x) =>
                      for (successorEdges <- graph(x)) {
                        distance.put(successorEdges._3,
                          Math.min(distance.getOrElse(successorEdges._3, Int.MaxValue), distance(x)+ successorEdges._2))
                      }
                      visited += x
                findSuccessor(visited,distance) match {
                  case adjcent if adjcent.isEmpty => findSP("",visited,distance)
                  case adjcent =>
                    findSP(adjcent.minBy(_._2)._1,visited,distance)
                }
            }
      }
      findSP(start,ListBuffer[String](),mutable.LinkedHashMap[String,Int]())

  }

  val graph = List( ("1",7,"2"),("1",9,"3"),("1",14,"6"), ("2",10,"3"),("2",15,"4"),("2",7,"1"),
                      ("3",10,"2"),("3",9,"1"),("3",11,"4"),("3",2,"6"),
                      ("4",15,"2"),("4",11,"3"),("4",6,"5"),
                       ("5",6,"4"),("5",9,"6"),  ("6",9,"5"),("6",14,"1"),("6",2,"3"))
  val gp = graph.groupBy(_._1)

  findDijkstraSP(gp,"1")

}
