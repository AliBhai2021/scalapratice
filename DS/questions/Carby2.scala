package questions

object Carby2 extends App{

  def solution(matches: Array[String]): Array[String] = {
    val h = scala.collection.mutable.HashMap[String, Int]();
    for(i<- matches)
    {
      val team1= i.split(" ")(0)
      val team2 = i.split(" ")(2)
      val (t1,t2) = (i.split(" ")(1).split(":")(0),i.split(" ")(1).split(":")(1))
      println("team1 :"+team1 +"goals :"+t1)
      println("team2 :"+team2+"goals :"+t2)
      if(t1>t2)
        h.put(team1, h.getOrElse(team1,0)+3)
      else if(t1<t2)
        h.put(team2, h.getOrElse(team2,0)+3)
      else{
        h.put(team1, h.getOrElse(team1,0)+1)
        h.put(team2, h.getOrElse(team2,0)+1)
      }
    }
    h.foreach(println(_))
    h.toSeq.sortWith(_._2 > _._2). map(x=> s"${x._1} ${x._2}").toArray
  }


  val str = Array("Liverpool 3:2 PSG", "RedStar 0:0 Napoli", "PSG 6:1 RedStar", "Napoli 1:0 Liverpool")
  solution(str).foreach(print(_))

}
