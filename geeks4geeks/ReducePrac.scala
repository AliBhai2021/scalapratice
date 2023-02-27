package geeks4geeks

import java.time.{LocalDate, LocalTime}

object ReducePrac extends App{

  val list1 = (1 to 999999).toList
  println("Start  :"+LocalTime.now())
  println("Average of list1 : "+list1.sum/list1.length)
  println("end :"+LocalTime.now())
  println("::::::::::::::::::::::::::::::::::::::::::::::::::")
  println("Start  :"+LocalTime.now())
  println(list1.map(x=> (x,1)).reduce((a,b)=> (a._1+b._1, a._2+b._2)))
  println("end :"+LocalTime.now())
}
