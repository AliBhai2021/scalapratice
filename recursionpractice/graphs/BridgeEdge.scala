package graphs

import scala.collection.mutable.{LinkedHashMap, ListBuffer}

object BridgeEdge extends App {

  var lowDistance=LinkedHashMap[String,Int]()
  var distance=LinkedHashMap[String,Int]()
  var parent=LinkedHashMap[String,Int]()
  var count =0
  var exe=0

  def successor(element:String,graph:List[(String,String)]):List[String]={
    //println(s"findBE(successor._1},gp) ="+graph.filter(_._1 == element).map(_._2))
    graph.filter(_._1 == element).map(_._2)
  }

  def DFStraversal(start:String,graph:List[(String,String)]): List[String]={
    def dsf(vertex: List[String], visted: List[String]=List(),parent:String=""): List[String]={
      exe +=1
      //println("Execution ##################################################### "+exe)
      vertex match {
        case Nil => visted
        case x :: xs if visted.contains(x) && parent == x =>
          println(s"Parent ${parent} is Same ++++++++++++++++++++++++++++++++++++++++++++++++++ for :: "+x)
          visted//dsf(xs,visted)
        case x :: xs if visted.contains(x)=>
          //println(s"Parent ${parent} is not Same ++++++++++++++++++++++++++++++++++++++++++++++++++ for :: "+x)
          //if(!parent.isEmpty)
          lowDistance.put(parent, Math.min(lowDistance.getOrElse(parent,Int.MaxValue),distance.getOrElse(x,Int.MaxValue)) )
          //dsf(xs,visted)
          visted
        case x :: xs =>
            count +=1
            distance.put(x,count)
            lowDistance.put(x,count)
          //println(" lowDistance :"+lowDistance)
/*           val res1 = dsf(successor(x,graph), x :: visted)
            println("Res#1 BackTraking : "+x)
           val res2 = dsf(xs,res1)
          println("Res#1 BackTraking : "+x)
          res2*/
          //println("Res#1 In...BackTraking : "+x +"  ::::Parent ="+parent)
          val res = dsf(xs,dsf(successor(x,graph), x :: visted,x))
          println("Res#1 BackTraking : "+x +"  ::::Parent ="+parent+"    res ="+res)
          lowDistance.put(parent, Math.min(lowDistance.getOrElse(x,Int.MaxValue),lowDistance.getOrElse(parent,Int.MaxValue)) )
          if(distance.getOrElse(parent,0)<=lowDistance.getOrElse(x,0))
            println("Edge >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> "+(parent+","+x))
          res

      }
    }
    val result = dsf(List(start),List())
    result.reverse
  }

  val  graph = List(("1","0"),("0","2"),("2","1"),("0","3"),("3","5"),("5","4"),("4","3"))
  //val  graph = List(("1","2"),("2","3"),("3","4"),("4","1"),("4","5"),("5","6"),("6","7"),("7","8"),("8","9"))
  val gp = graph ++ graph.map(x=> (x._2,x._1))
  println(gp)
  println("RESULT = "+DFStraversal("0",gp))
  println("Distance : "+distance)
  println("LowDistance : "+lowDistance)

}
