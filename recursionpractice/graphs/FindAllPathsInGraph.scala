package graphs

import scala.collection.mutable.ListBuffer

object FindAllPathsInGraph extends App {

  def findAllPaths(graph:List[(String,String)],start:String,target:String):Unit={
    var visited = ListBuffer[String]()
    def findpaths(src:String, result:String=""):Unit=
      src match {
        case vertex if vertex == target => println("Path = "+result+vertex)
        case vertex =>
                for(i<-graph.groupBy(_._1)(vertex).map(_._2)){
                  if(!visited.contains(vertex)){
                    visited += vertex
                    findpaths(i,result+vertex)
                    visited -= vertex
                  }

                }
      }
    findpaths(start)
  }

    val graph = List(("a","b"),("a","c"),("b","d"),("c","e"),("d","f"),("d","e"),("e","f"),("f","g"),
                   ("b","a"),("c","a"),("d","b"),("e","c"),("f","d"),("e","d"),("f","e"),("g","f"))
    findAllPaths(graph,"a","f")

  val  graph2 = List(("1","0"),("0","2"),("2","1"),("0","3"),("3","5"),("5","4"),("4","3"))
  val gp = graph2 ++ graph2.map(x=> (x._2,x._1))
  findAllPaths(gp,"2","5")

}
