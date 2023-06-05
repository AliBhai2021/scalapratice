package recursion

object LongestCommonSubsequence extends App{

  def findLengthLongestCommonSubsequence(str1:String, str2:String):Int={
    def findLCS(s1:Int,s2:Int):Int={
      (s1,s2) match {
        case (x,y) if x<0 || y<0 => 0
        case (x,y) if str1(x) == str2(y) =>  1+(findLCS(s1-1,s2-1))
        case _ => Math.max(findLCS(s1-1,s2),findLCS(s1,s2-1))
      }
    }
    findLCS(str1.length-1,str2.length-1)
  }
  val x = "STONE"
  val y = "LONGEST"
  println("\n findLengthLongestCommonSubsequence : "+findLengthLongestCommonSubsequence(x,y))
//----------------------------------------------------------------------------------------------------------------------
  val commonStrMatrix = Array.ofDim[Int](x.length,y.length)
  def findLongestCommonSubsequence(str1:String, str2:String ):Int={

    def findLCS(s1:Int,s2:Int):Int={
      (s1,s2) match {
        case (x,y) if x<0 || y<0 => 0
        case (x,y) if str1(x) == str2(y) =>
          commonStrMatrix(x)(y)=1+findLCS(s1-1,s2-1)
          commonStrMatrix(x)(y)
        case (x,y) =>
          commonStrMatrix(x)(y)= Math.max(findLCS(s1-1,s2),findLCS(s1,s2-1));
          commonStrMatrix(x)(y)
      }
    }
    findLCS(str1.length-1,str2.length-1)
  }
  println("\n findLongestCommonSubsequence : "+findLongestCommonSubsequence(x,y))

  //printing matricx
  for(i <- 0 until commonStrMatrix.length){
    for(j <- 0 until commonStrMatrix(0).length)
      print(" "+commonStrMatrix(i)(j))
    println(" ")
  }

  var row=x.length-1
  var col=y.length-1
  var result=""
  println(row+", "+col)
  while(row >0 && col >0){
    if(commonStrMatrix(row)(col-1) != commonStrMatrix(row)(col)){
      print(y(col))
      result = y(col) +result
      row -=1
      col -=1
    }
    else {
      col -=1
    }
  }

  println("\n Longest Common String :"+result)

}
