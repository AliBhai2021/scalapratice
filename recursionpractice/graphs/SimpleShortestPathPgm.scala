package graphs

object SimpleShortestPathPgm extends App{

  def findAnsectors(vertix:String, graph:List[(String,Int,String)]):List[String]= {
    vertix match {
      case x if x.isEmpty => List()
      case x if !graph.contains(vertix) =>List()
      //case x => List(graph.edge.filter(_.src == vertix).sortBy(_.value).map(_.des).headOption.getOrElse(""))
      case x => List(graph.filter(_._1 ==vertix).sortBy(_._2).head._3)
    }
  }

//  def shortestPath(start:String,graph: List[(String,Int,String)]):List[String]={
//    def findSP(vertices:List[String], visited:List[String]):List[String]=
//      vertices match {
//          case vertix if vertix.isEmpty =>visited
//          case x :: xs if visited.contains(x) => findSP(xs,visited)
//          case x :: xs =>
//          findSP(xs ++ findAnsectors(x,graph), visited++List(x))
//    }
//
//    findSP(List(start),List())
//
//  }

  val gp = List(("0",4,"1"),("0",8,"7"),("1",8,"2"),("1",11,"7"),("2",2,"8"),("8",7,"7"),("8",6,"6"))
  //,Edge("1",4,"0"),Edge("7",8,"0"),Edge("2",8,"1"),Edge("7",11,"1"),Edge("8",2,"2"),Edge("7",7,"8"),Edge("6",6,"8")))
  println(findAnsectors("9",gp))
}
