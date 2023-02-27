package guru.learningjournal.spark.examples.prac

object prac4 {
  def main(args:Array[String]): Unit ={
    println(stepRequired("capture","capsule"))
    println(stepRequired("hari","malar"))
    println(stepRequired("ashraf","ashraf12"))
    println(findMaximum(6))
  }

  def findMaximum(i:Int) :Int={
    val x = i/2
    (x+1)*(i-x+1)
  }

  def stepRequired(str1:String, str2:String):Int={
    var count =0
    for(i <- 0 until str1.length){
      if(str2.indexOf(str1.charAt(i)) >= 0){
        count +=1
      }
    }
    str1.length+str2.length - count*2
  }
}
