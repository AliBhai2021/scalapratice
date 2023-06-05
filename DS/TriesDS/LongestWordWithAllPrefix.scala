package TriesDS

object LongestWordWithAllPrefix extends App{
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

  //==================================================================
  var result=""
  var newChar= ""
  def findLongestWordWithAllPrefix(root:MyTrieNode, temp:StringBuilder):String={
        for(i<- 0 until 26) {
          if (root.children(i) != null && root.children(i).eow ) {
            temp.append((i + 'a').toChar)

            if (temp.length > result.length)
              result = temp.toString()
            findLongestWordWithAllPrefix(root.children(i), temp)
            temp.deleteCharAt(temp.length-1)
          }
        }
    result
    }




  val words = Array("a","ap","appl","banana","app","apply","apple")

  for(w <- words)
    insert(w)

  println("\n findLongestWordWithAllPrefix = "+findLongestWordWithAllPrefix(uroot,new StringBuilder))
  println(result)

}
