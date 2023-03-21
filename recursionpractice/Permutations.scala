object Permutations extends App{

  def findPermutations(str:String,res:String=""):Unit={
    //println("str ="+str + "   res ="+res)
    str match {
      case x if x.isEmpty =>
        println(res)
      case x =>
        for(i<- 0 until str.length){
          val currentChar = str(i)
          //println("str.substring(0,i) ="+str.substring(0,i)+ "; str.substring(i+1)="+str.substring(i+1)+"   currentChar ="+currentChar)
          val newChar = str.substring(0,i)+str.substring(i+1) // skip any one middle char at a time
          //println("i ="+i+" Calling ::=> ("+newChar+","+res+currentChar+")")
          findPermutations(newChar,res+currentChar)
        }
    }
  }

  findPermutations("abc")

}
