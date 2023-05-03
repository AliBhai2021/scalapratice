package recursion

import scala.collection.mutable

object CoinTraversal extends App{ //FindTargetSumValueExistsInArray

  def coinTraversal(target:Int,coins:Array[Int],result:String=""):Unit={
    //println("target ="+target)
    target match {
      case balance if balance == 0 => println(result)
      case balance if balance <0 => ""
      //case balance if temp.add(balance)=>
      case balance =>
        for(coin <- coins if target >= coin)
        coinTraversal(target-coin,coins, result+coin)
    }
  }

  coinTraversal(4,Array(1,2,3))

}
