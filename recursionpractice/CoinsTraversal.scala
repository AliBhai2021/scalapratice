object CoinsTraversal extends App{

  val coins = Array(1,2,3)
  val sumValue = 4

  var temp = scala.collection.mutable.LinkedHashSet[Int]()
  def findCoins(sumValue:Int,coins:Array[Int],c:String=""):Unit={
    sumValue match {
      case x if x <0 => ""
      case x if x == 0 =>
        println(" coins :"+c   )
      case x if temp.add(x) => //excluding repeated scenarios
                    for( i<- coins){
                      findCoins(sumValue-i,coins,c+i)
                    }
      case _ => "" //excluded repeated scenarios
    }
  }

  findCoins(sumValue,coins)

  println("::::::::::::::::::::::: findCoinsWithAll :::::::::::::::::::")
  def findCoinsWithAll(sumValue:Int,coins:Array[Int],c:String=""):Unit={
    sumValue match {
      case x if x <0 => ""
      case x if x == 0 =>
        println(" coins :"+c   )
      case x =>
        for( i<- coins){
          findCoinsWithAll(sumValue-i,coins,c+i)
        }
    }
  }
  println("::::::::::::::::::::::::::::::::::::::::::")
  findCoinsWithAll(sumValue,coins)
}
