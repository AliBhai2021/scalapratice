object TOH extends App{

  def toh(disks :Int, src:String,helper:String,des:String):Unit={
    disks match {
      case x if x==1 =>
                      println(s"Transfer disk from ${src} to ${des}")
      case x if x ==0 => ""
      case x =>
        toh(disks-1,src,des,helper)
        println(s"Transfer disk from ${src} to ${des}")
        toh(disks-1,helper,src,des)
    }
  }

  toh(2,"A","B","c")
  println("::::::::::::::::::::::::::::::::::::::")
  toh(3,"A","B","C")

}
