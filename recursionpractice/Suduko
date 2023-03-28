object SudoCode extends App{

  def isSafe(board: Array[Array[Int]], row: Int, col: Int, num: Int):Boolean={
    val sr = 3*(row/3)
    val sc = 3*(col/3)
    validateRow(board,row,0,num) && validateCol(arr,0,col,num) && validateGrid(arr,row,col,num,sr,sc)
  }
  def validateRow(board: Array[Array[Int]], row: Int, col: Int, num: Int):Boolean={
    col match {
      case x if x == arr.length-1 => true
      case x if board(row)(col) == num => false
      case x => validateRow(arr,row,col+1,num)
    }
  }

  def validateCol(board: Array[Array[Int]], row: Int, col: Int, num: Int):Boolean={
    row match {
      case x if x == arr.length-1 => true
      case x if board(row)(col) == num => false
      case x => validateCol(arr,row+1,col,num)
    }
  }

  def validateGrid(board: Array[Array[Int]], row: Int, col: Int, num: Int, sr:Int,sc:Int):Boolean={
    for(i <- sr until sr+3){
      for(j<- sc until sc+3)
        if(board(i)(j)==num)
          return false
    }
    return true
  }



  def fillSudo(arr:Array[Array[Int]],row:Int=0,col:Int=0):Boolean={
    row match {
      case x if row == arr.length => printSudo(arr); true
      case x if arr(row)(col) != 0 => true
      case x =>
        var nrow = 0
        var ncol = 0
         if(col == arr.length-1){
           nrow= row+1
           ncol =0
         }
        else {
           nrow = row
           ncol = col+1
         }
      //fill the places
        for(i<- 1 to 9){
          if(isSafe(arr,row,col,i)){
            arr(row)(col)=i
              if(fillSudo(arr,nrow,ncol))
                  return true
              else
                arr(row)(col)=0
          }
        }
        false
    }
  }
  def printSudo(arr:Array[Array[Int]]):Unit={
    println("::::::::::::::::::::::::::::::::")
    for(i <- 0 until arr.length) {
      for (j <- 0 until arr(0).length) {
        if((j+1)%3 == 0 )
          print(arr(i)(j)+"|")
        else
          print(arr(i)(j)+"")
      }
      if((i+1)%3 ==0)
      println("\n------------")
      else
        println("")
    }

  }

  var arr = Array.ofDim[Int](9,9)
  println("row X col =("+arr.length+" X "+arr(0).length+")")
  fillSudo(arr)
  printSudo(arr)
}
