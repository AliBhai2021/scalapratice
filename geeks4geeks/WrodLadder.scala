package geeks4geeks

import org.apache.parquet.schema.Types.ListBuilder

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

object WrodLadder extends App{

  val worslist =List("hot","dot","dog","lot","log","cog")

  val abc = "efgrhi"
  val obj = new Solution
  println(obj.ladderLength("hot","cog",worslist))
  println(obj.ladderLength("hot","dot",worslist))

  //Input: Dictionary = {POON, PLEE, SAME, POIE, PLEA, PLIE, POIN}, start = TOON, target = PLEA
  //Explanation: TOON – POON – POIN – POIE – PLIE – PLEE – PLEA
  //Input: Dictionary = {ABCD, EBAD, EBCD, XYZA}, start = ABCV, target = EBAD
  //Explanation: ABCV – ABCD – EBCD – EBAD
  val list2= List("POON","PLEE","SAME","POIE","PLEA","PLIE","POIN")
  println(obj.ladderLength("TOON","PLEA",list2))
}

class Solution {
  def ladderLength(beginWord: String, endWord: String, wordList: List[String]): Int = {
    val q = new mutable.Queue[String]()
    q.enqueue(beginWord)
    var level = 1
    val words = new mutable.HashSet[String]()

    for (word <- wordList) {
      words.add(word)
    }
    words.remove(beginWord)
    while ( {
      !q.isEmpty
    }) {
      val sz = q.size
      for (k <- 0 until sz) {
        val curr = q.dequeue()
        if (curr == endWord) return level
        for (i <- 0 until curr.length) {
         for(ch <- 'a' to 'z'){
           val str = curr.substring(0, i) + ch + curr.substring(i + 1)
           if (words.remove(str)) q.enqueue(str)
         }
        }
      }
      level += 1
    }
    0
  }
}