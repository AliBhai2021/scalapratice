package recursionpractice

import scala.collection.mutable.ListBuffer

object SubSeq extends App{

  //println(subSeq("","abc"))
  println(subSeqList("","abc"))
  //println(subSeqTrack("","abc",""))

  def subSeq(s:String,str:String):Unit={

    str match{
      case strl if strl.isEmpty => println(s)
      case strl =>
        subSeq(s+s"${strl(0)}",strl.substring(1)) // str ==> a-> ab -> abc then strl is empty
        subSeq(s,strl.substring(1)) // bc -> c
    }
  } // abc,ab,ac,a,bc,b,c

  def subSeqList(s:String,str:String):ListBuffer[String]={
    str match{
      case strl if strl.isEmpty => ListBuffer(s)
      case strl =>
        val left = subSeqList(s+s"${strl(0)}",strl.substring(1))
        val right = subSeqList(s,strl.substring(1))
        left ++ right
    }
  }

  def subSeqTrack(s:String,str:String, track:String):Unit={
    println(s"Track= $track :s=$s, str=$str")
    str match{
      case strl if strl.isEmpty => println(s)
      case strl =>
        subSeqTrack(s+s"${strl(0)}",strl.substring(1),"Left :")
        subSeqTrack(s,strl.substring(1),"Right :")
    }
  }
}
