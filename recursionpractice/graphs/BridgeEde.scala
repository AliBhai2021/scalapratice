package graphs

import scala.collection.mutable.{LinkedHashMap, ListBuffer}

object BridgeEde extends App{
  var lowDistance=LinkedHashMap[String,Int]()
  var distance=LinkedHashMap[String,Int]()
  var count =0

  def successor(element:String,graph:List[(String,String)]):List[(String,String)]={
    graph.filter(_._1 == element)
  }

  def FindBrideEdge(gp:List[(String,String)],start:String):List[String]={
      def findBE(vertex:List[(String,String)],visited:List[String]=List(),parent:String=""):List[String]={
        println("vertex ="+vertex )
        vertex match { //x= Edge(src,des)
          case Nil => visited
          case x :: xs if x._2 == parent => visited
          case x::xs if visited.contains(x._2) =>
            lowDistance.put(x._2,Math.min(distance.getOrElse(x._1,0),lowDistance.getOrElse(x._2,0)))
            findBE(xs,visited)
          case x::xs =>

            count +=1
            if(x._1.isEmpty) {
              distance.put(x._2, count)
              lowDistance.put(x._2, count)
            } else {
                      distance.put(x._2, count)
                      lowDistance.put(x._2, count)
                    }
            val res = findBE(xs,findBE(successor(x._2,gp),x._2 ::visited,x._1),x._1)
            if(distance.getOrElse(x._1,Int.MaxValue)<lowDistance.getOrElse(x._2,Int.MaxValue))
              println(":::::::::::::::::Bridge Edge :::::::::::::::::::::::::: "+x)
            res
        }
      }
    findBE(List(("",start)),List()) // (parent,startNode)
  }



  val  graph = List(("1","2"),("2","3"),("3","4"),("4","1"),("4","5"),("5","6"),("6","7"))
  val gp = graph ++ graph.map(x=> (x._2,x._1))
  println(gp)
  println("RESULT = "+FindBrideEdge(gp,"1"))
  println("distance ="+distance)
  println("lowDistance ="+lowDistance)

}
