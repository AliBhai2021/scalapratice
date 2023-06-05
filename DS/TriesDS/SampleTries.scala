package TriesDS

import scala.collection.mutable.ArrayBuffer

case class MyTrieNode(var children:ArrayBuffer[MyTrieNode]= ArrayBuffer.fill(26)(null), var eow:Boolean=false)

object SampleTries extends App{
  var root = MyTrieNode()

  def insert(word:String):Unit={
    var curNode=root

    for(i <- 0 until word.length){
      val idx = word.charAt(i) - 'a'
      if(curNode.children(idx) == null)
        curNode.children(idx) = MyTrieNode()
      curNode = curNode.children(idx)
    }
    curNode.eow=true
    //println("curNode = "+curNode)
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


  insert("acbe")
  println("root =" +root)
  println(" Search of word 'acbe' "+search("acbe"))
  println(" Search of word 'acbe' "+search("acb"))
}
