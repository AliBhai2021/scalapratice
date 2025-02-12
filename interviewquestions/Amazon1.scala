package interviewquestions

object Amazon1 extends App{

  import scala.io._

  val input = "aaaaabbbbcccddeff" //readLine().toString
  val strlength = input.length()
  var result=""
  var  ptr =1
  var count=if (input.nonEmpty) 1 else 0
  var newLetter= if (input.nonEmpty) input(0) else ""
  while (strlength > ptr){
    //println(ptr + " "+strlength+" "+newLetter+" "+result)
    if(input(ptr-1) == input(ptr) ){
      count +=1
    }
    else{
      result = s"${result+newLetter+count}"
      count =1
      newLetter=input(ptr)
    }
    ptr +=1
  }

  println(s"${result+newLetter+count}")
//--------------------------------------------------------------------------------------
  val abc = sample("aaaaabbbbcccddeff")
  def sample(input:String):Unit={
    val data = input.map(x=> (x,1))
    val data2 = data.groupBy(_._1).mapValues{x=> x.map(_._2).sum}
    println(data)
    println(data2)
    println(data2.toList)

  }

  println("::::::::::::::::::::SIMPLE LOGIC LinkedHashMap ::::::::::::::::::::::::::::::::::::")
  val temp = scala.collection.mutable.LinkedHashMap[Char,Int]()
  for(c <- "aaaaabbbbcccddeffaa"){
    temp.put(c, temp.getOrElse(c,0)+1)
  }
  print(temp.map{case(k,v) => k+""+v}.mkString(""))  // finalized logic
  println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::")

  println("::::::::::::::::::::SIMPLE LOGIC HashMap ::::::::::::::::::::::::::::::::::::")
  val temp3 = scala.collection.mutable.HashMap[Char,Int]()
  for(c <- "aaaaabbbbcccddeff"){
    temp3.put(c, temp3.getOrElse(c,0)+1)
  }
  print(temp3.map{case(k,v) => k+""+v}.mkString(""))  // finalized logic
  println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::")

  println(temp.mkString(""))
  println(temp.toList.mkString(""))
  //println(temp.toList.flatten(x=>x._1+x._2.toChar).mkString(""))
  print(temp.map{case(k,v) => k+""+v}.mkString(""))  // finalized logic

  println("")
  var temp2 = scala.collection.mutable.LinkedHashMap[Char,Int]()
  var myvar:Char = ' '
  for(c <- "aaaaabbbbcccddeffaaabcc"){
    if(myvar == ' ') {
      myvar =c
      temp2.put(c, temp2.getOrElse(c,0)+1)
    }

    if(myvar == c) {
      temp2.put(c, temp2.getOrElse(c,0)+1)
    } else {
      print(temp2.map { case (k, v) => k + "" + v }.mkString(""))
      temp2.clear()
      temp2.put(c, temp2.getOrElse(c, 0) + 1)
      myvar = c
    }
  }


}
