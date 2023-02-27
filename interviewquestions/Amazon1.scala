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

}
