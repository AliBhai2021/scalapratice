package TriesDS

object CountUniqueSequence extends App{

  var uroot=MyTrieNode()

  def insert(word:String):Unit={
    var curNode=uroot

    for(i <- 0 until word.length){
      val idx = word.charAt(i) - 'a'
      if(curNode.children(idx) == null)
        curNode.children(idx) = MyTrieNode()
      curNode = curNode.children(idx)
    }
    curNode.eow=true
    println("curNode = "+curNode)
  }

  //========================================================================

  //var count=0 // gives duplicate counts
  def countUniqueSequence(nodes:MyTrieNode):Int={
    nodes match {
      case ch if ch == null => 0
      case ch =>
        var count=0
        for(i <- 0 until 26){
          if(ch.children(i) != null)
            count += countUniqueSequence(ch.children(i))
        }
        count +=1;
        count
    }


  }

  val world = "ababa"
  for(i<- 0 until world.length)
    insert(world.substring(i))
  println("Root = "+uroot)
  println("Count nodes or no. of unique substrings  : "+countUniqueSequence(uroot))

}
