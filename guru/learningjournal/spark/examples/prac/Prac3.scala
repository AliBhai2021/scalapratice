package guru.learningjournal.spark.examples.prac

object Prac3 {

  def main (args:Array[String]): Unit ={
    val list = Seq("1,Joseph,23,Mumbai-MH",
      "2,Jayjeet,33,Bangalore-KA",
      "3,Ram,45,Bangalore-KA",
      "4,Ramesh,51,Kolkata-WB",
      "5,Abraham,52,Kolkata-WB",
      "3,Ram,45,Bangalore-KA",
      "6,Ashraf,17,Ap-kadapa")

    for(i<- list.indices){
      for(m <- i+1 until list.length){
        if(list(i).contentEquals(list(m))) {println("duplicate values"+" "+list(i))}
      }
    }
      var i  = 0
      var j=0
      var dup = false


      while(i < list.length && !dup){
        j = i+1
        while (j < list.length && !dup) {
          if (list(i).contentEquals(list(j))) {
           dup = true
          println(list(j))
          }
          j += 1
        }
        i +=1
      }
      println(dup)

   }

  }
