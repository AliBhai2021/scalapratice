package ArrayPratice

object FindMissingElements extends App{

  def findMissingMoreElementsInArray(arr:Array[Int]):Unit={
    var diff = 0 //6, 7, 9
    for(i <- 0 until arr.length){
      println("diff = "+diff)
      if(arr(i) - i != diff){
        while (diff < arr(i)-i) {
          println("Missing Number = " + (diff+i)) // (6+4),(7+6),(8+6)
          diff += 1
        }
      }
    }
  }

  val arr = Array(6,7,8,9,11,12,15,16,17,18,19)
  findMissingMoreElementsInArray(arr)
//------------------------------------------------------------------
  def findMissingOneElementsInArray(arr:Array[Int]):Unit= {
    var diff = arr(0) - 0
    for (i <- 0 until arr.length) {
      if (arr(i) - i != diff) {
        println("Missing Single number is :" + (i + diff))
        diff +=1
      }
    }
  }

  val arr2 = Array(3,4,5,6,7,9,10)
  findMissingOneElementsInArray(arr2)




}
