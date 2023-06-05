package StackPratice

object LargetAreaOfMatrix extends App{

  def findLargestAreaOfMatrix(arr:Array[Array[Int]]):Int={
    var currentRow= arr(0)
    var maxAns=LargestRectangleInHistogram.findLargetRectangleInHistogram(currentRow)
    for(i<- 0 until arr.length ){
      for(j<- 0 until arr(i).length){
        if(arr(i)(j)==1)
          currentRow(j) +=1
        else
          currentRow(j)=0
      }
      val curAns=   LargestRectangleInHistogram.findLargetRectangleInHistogram(currentRow)
      maxAns = Math.max(maxAns,curAns)

    }
    maxAns
  }

  //driver Program :::

  val arr = Array(
    Array(0,1,1,1,1,0),
    Array(1,1,1,1,0,1),
    Array(1,1,0,1,1,1),
    Array(1,1,1,1,1,0)
  )

  println("Result = "+findLargestAreaOfMatrix(arr))

}
