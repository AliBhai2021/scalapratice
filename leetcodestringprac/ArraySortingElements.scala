package leetcodestringprac

object ArraySortingElements extends App{

  var array = Array(2,0,1,3,0,2)

  for ( i<- 0 until array.length)
  {
    println("i = "+i +" ")
    for( j<- 0 until array.length-1)
    {
      //println("j = "+j+" ")
      if(array(j) > array(j+1))
      {
        var temp = array(j)
        array(j)=array(j+1)
        array(j+1)= temp

      }

    }
    array.foreach(print(_))

    println()

  }
  array.foreach(println(_))

}
