package TriesDS

import scala.collection.mutable.ArrayBuffer

object WordBreakProblem extends App{

  var root=MyTrieNode()

  def insert(word:String):Unit={
    var curNode=root

    for(i <- 0 until word.length){
      val idx = word.charAt(i) - 'a'
      if(curNode.children(idx) == null)
        curNode.children(idx) = MyTrieNode()
      curNode = curNode.children(idx)
    }
    curNode.eow=true
    println("curNode = "+curNode)
  }

  def search(word:String):Boolean={
    var curNode = root
    for(i <- 0 until word.length){
      val idx = word.charAt(i) - 'a'
      if(curNode.children(idx)==null)
        return false
      curNode = curNode.children(idx)
    }
    curNode.eow == true
  }
  //=========================================================
  def findSentanceBreakWords(sentance:String):Boolean={
    sentance match {
      case w if w.isEmpty => true
      case w =>
        for(i<- 1 to sentance.length){
          if(search(sentance.substring(0,i)) && findSentanceBreakWords(sentance.substring(i))){
            println("Word = "+w)
            return true
          }
        }

        false
    }
  }

  //=====================================================================
  def startWith(word:String):Boolean={
    var curNode = root
    for(i <- 0 until word.length){
      val idx = word.charAt(i) - 'a'
      if(curNode.children(idx)==null)
        return false
      curNode = curNode.children(idx)
    }
    true
  }



  val words = ArrayBuffer("i","like","sam", "samsung", "mobile")
  for(w <- words)
    insert(w)
  println("root = "+root)
  val key = "ilikesamsung"
  println(" wordBreakPblm : "+findSentanceBreakWords(key))

  println("startWith 'samsun' = "+startWith("samsun"))
  println("startWith 'samsun' = "+startWith("samsuns"))
}
