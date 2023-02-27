package leetcodestringprac

object TargetNumberGame extends App{

  var a=1
  var b=2
  var n =12
  var target = new Array[Int](10)
  def findtarget(x:Int, win:Int, i:Int=1):Array[Int]={
    x match{
      case v if v <0 => target
      case _ =>
        target(i-1)= x
        findtarget(x-1-win, win,i+1 )
    }

  }

  findtarget(12,3).foreach(println(_))
  findtarget(11,3).foreach(println(_))

}
