package TriesDS

import scala.collection.mutable.ArrayBuffer

case class MyTrieNode(var children:ArrayBuffer[MyTrieNode]= ArrayBuffer.fill(26)(null), var eow:Boolean=false )

object SampleTries extends App{

  var root = MyTrieNode()

  def insertWord(word:String):Unit={
    var curNode = root
    var idx = 0
    for(i<- 0 until word.length){
      idx = word.charAt(i) - 'a'
      if(curNode.children(idx) == null)
        curNode.children(idx)=MyTrieNode()
      curNode = curNode.children(idx)
    }
    curNode.eow = true
  }

  def searchWord(word:String):Boolean={
    var curNode = root
    var idx =0
    for(i <- 0 until word.length){
      idx = word.charAt(i) - 'a'
      if(curNode.children(idx) == null)
        return false
      curNode = curNode.children(idx)
    }
    curNode.eow == true
  }

  val words = ArrayBuffer("the","a","there","their","any","thee")
  for(w <- words) {
    insertWord(w)
    println("Inserted word ="+w)
  }

  println("Searching Word 'the' : "+searchWord("the"))
  println("Searching Word 'there' : "+searchWord("there"))
  println("Searching Word 'thor' : "+searchWord("thor"))
}
