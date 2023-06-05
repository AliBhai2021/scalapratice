package strings

object PrintCharactersCount extends App{

  val input = "aaaaabbbbcccddeff"
  val temp = scala.collection.mutable.LinkedHashMap[Char,Int]()
  for(c <- input){
    temp.put(c, temp.getOrElse(c,0)+1)
  }

  print(temp.map{case(k,v) => k+""+v}.mkString(""))  // finalized logic

}
