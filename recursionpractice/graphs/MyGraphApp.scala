package graphs

trait MyGraph[V] {
  def vertices: List[V]
  def edges: List[(V,V)]
  def addEdge(a: V, b: V): MyGraph[V]
  def neighbors(x: V): List[V]
}
object MyGraph {
  def apply[V](adjacencyList: Map[V, List[V]]): MyGraph[V] =
    new MyDirectedGraph(adjacencyList)
  def apply[V](): MyGraph[V] = new MyDirectedGraph(Map[V,List[V]]())
}

class MyDirectedGraph[V](adjacencyList: Map[V, List[V]])extends MyGraph[V] {
  override def vertices: List[V] = adjacencyList.keys.toList
  override def edges: List[(V, V)] = adjacencyList.flatMap {
    case (v, neighbors) => neighbors.map(x =>(v,x))
  }.toList
  override def addEdge(a: V, b: V): MyDirectedGraph[V] = {
    val aNeighbors = b +: neighbors(a)
    new MyDirectedGraph(adjacencyList + (a -> aNeighbors))
  }
  override def neighbors(x: V): List[V] =  adjacencyList.getOrElse(x, Nil)
}
class MyUndirectedGraph[V](adjacencyList: Map[V, List[V]]) extends MyDirectedGraph[V](adjacencyList) {
  override def addEdge(a: V, b: V): MyUndirectedGraph[V] = {
    val aNeighbors = b +: neighbors(a)
    val bNeighbors = a +: neighbors(b)
    new MyUndirectedGraph(adjacencyList + (a -> aNeighbors,
      b -> bNeighbors))
  }
}
case class MyWeightedEdge[V](dest: V, weight: Int)

class MyWeightedGraph[V](adjacencyList: Map[V,List[MyWeightedEdge[V]]]) extends MyGraph[V] {
  override def vertices: List[V] = adjacencyList.keys.toList
  override def edges: List[(V, V)] = adjacencyList.flatMap {
    case (v, edgeList) => edgeList.map(e => v -> e.dest)
  }.toList
  def addEdge(a: V, weightedEdge: MyWeightedEdge[V]): MyWeightedGraph[V] = {
    val aNeighbors = weightedEdge +: adjacencyList.getOrElse(a, Nil)
    new MyWeightedGraph(adjacencyList + (a -> aNeighbors))
  }
  override def addEdge(a: V, b: V): MyWeightedGraph[V] =  addEdge(a, new MyWeightedEdge(b, weight = 0))
  override def neighbors(x: V): List[V] = adjacencyList.getOrElse(x, Nil).map(_.dest)
  def neighborsWithWeight(x: V): List[MyWeightedEdge[V]] = adjacencyList.getOrElse(x, Nil).toList
}

object MyGraphApp {
  def main(args: Array[String]): Unit = {
    val myGraph = MyGraph[String]()
      .addEdge("San Francisco", "Hong Kong")
      .addEdge("Hong Kong", "Kathmandu")
      .addEdge("Kathmandu", "San Francisco")
      .addEdge("Kathmandu", "Bangkok")
      .addEdge("Bangkok", "San Francisco")
      .addEdge("Pokhara", "Bangkok")
    println(myGraph.vertices)
    println(myGraph.neighbors("Kathmandu"))
  }
}
//
////---------------------------------------------------------------------
//class MyWeightedGraph[V](adjacencyList: Map[V,
//  List[MyWeightedEdge[V]]]) extends MyGraph[V] {
//  // Methods implementation here
//}
//case class MyWeightedEdge[V](dest: V, weight: Int)
//trait MyGraph[V] {
//  def vertices: List[V]
//  def edges: List[(V,V)]
//  def addEdge(a: V, b: V): MyGraph[V]
//  def neighbors(x: V): List[V]
//}
//object MyTravelGraph {
//  val travelGraph = new MyWeightedGraph(Map("San Francisco"
//    -> Nil))
//    .addEdge("San Francisco", MyWeightedEdge("Vancouver",
//      348))
//    .addEdge("San Francisco", MyWeightedEdge("Los Angeles",
//      200))
//    .addEdge("San Francisco", MyWeightedEdge("Guangzhou",
//      680))
//    .addEdge("San Francisco", MyWeightedEdge("Hong Kong",
//      530))
//    .addEdge("San Francisco", MyWeightedEdge("Dubai", 800))
//    .addEdge("San Francisco", MyWeightedEdge("Shanghai", 382))
//    .addEdge("San Francisco", MyWeightedEdge("New York", 500))
//    .addEdge("San Francisco", MyWeightedEdge("Istanbul", 499))
//    .addEdge("San Francisco", MyWeightedEdge("Delhi", 1547))
//    .addEdge("Vancouver", MyWeightedEdge("Guangzhou", 686))
//    .addEdge("Los Angeles", MyWeightedEdge("Guangzhou", 500))
//    .addEdge("Shanghai", MyWeightedEdge("Bangkok", 160))
//    .addEdge("New York", MyWeightedEdge("Doha", 800))
//    .addEdge("Bangkok", MyWeightedEdge("Kunming", 145))
//    .addEdge("Guangzhou", MyWeightedEdge("Kathmandu", 214))
//    .addEdge("Hong Kong", MyWeightedEdge("Kathmandu", 400))
//    .addEdge("Kunming", MyWeightedEdge("Kathmandu", 105))
//    .addEdge("Doha", MyWeightedEdge("Kathmandu", 1287))
//    .addEdge("Istanbul", MyWeightedEdge("Kathmandu", 300))
//    .addEdge("Delhi", MyWeightedEdge("Kathmandu", 300))
//    .addEdge("Kathmandu", MyWeightedEdge("Delhi", 300))
//}
//
//object DijkstraAlg {
//  import scala.util.Try
//  import MyTravelGraph._
//  case class ShortestStep(parents: Map[String, String],
//                          unVisited: Set[String],
//                          distances: Map[String, Int]) {
//    def findMin(): Option[(String, Int)] =
//      Try(unVisited.minBy(x => distances(x))).toOption.map(x =>
//        (x, distances(x)))
//  }
//  object MyTravelDijkstraShortestPathApp {
//    def main(args: Array[String]): Unit = {
//      val spResult = findShortestPath(ShortestStep(Map(),
//        travelGraph.vertices.toSet, distancesMap))
//      println(spResult.distances)
//      travelGraph.vertices.foreach(x =>
//        println(extractShortestPaths(x,
//          spResult.parents).reverse))
//    }
//
//    val distancesMap = travelGraph.vertices.map(_ ->
//      Int.MaxValue).toMap + ("San Francisco" -> 0)
//
//    def findShortestPath(step: ShortestStep): ShortestStep = {
//      step.findMin().map {
//        case (x, currentDist) => {
//          val newDists = travelGraph.neighborsWithWeight(x)
//            .collect {
//              case MyWeightedEdge(y, w) if step.distances
//                .get(y).exists(_ > currentDist + w) =>
//                y -> (currentDist + w)
//            }
//          val newParents = newDists.map {
//            case (y, _) => y -> x
//          }
//          findShortestPath(ShortestStep(step.parents ++
//            newParents, step.unVisited - x, step.distances ++
//            newDists))
//        }
//      }.getOrElse(step)
//    }
//
//    def extractShortestPaths(vertex: String, parents:
//    Map[String, String]): List[String] = parents.get(vertex)
//      .map(x => vertex +: extractShortestPaths(x, parents))
//      .getOrElse(List(vertex))
//  }


