object Queen extends App{

  def validateHorizantal(row:Int,col:Int,board:Array[Array[Int]]):Boolean={
    col match {
      case x if x > board.length-1 => true
      case x if board(row)(col) == 1 => false
      case x => validateHorizantal(row,col+1,board)
    }
  }

  def validateVertical(row:Int,col:Int,board:Array[Array[Int]]):Boolean={
    //println(row+" "+col)
    row match {
      case x if x > board.length-1 => true
      case x if board(row)(col) == 1 => false
      case x => validateVertical(row+1,col,board)
    }
  }

  def validateUpperLeft(row:Int,col:Int,board:Array[Array[Int]]):Boolean={
    (row,col)match {
      case (x,y) if x < 0 || y < 0 => true
      case (x,y) if board(x)(y)==1=> false
      case (x,y)=> validateUpperLeft(x-1,y-1,board)
    }
  }
  def validateUpperRight(row:Int,col:Int,board:Array[Array[Int]]):Boolean={
    (row,col)match {
      case (x,y) if x < 0 || y>board.length-1=> true
      case (x,y) if board(x)(y)==1=> false
      case (x,y)=> validateUpperRight(x-1,y+1,board)
    }
  }

  def validateLowerRight(row:Int,col:Int,board:Array[Array[Int]]):Boolean={
    (row,col)match {
      case (x,y) if x>board.length-1 || y>board.length-1 => true
      case (x,y) if board(x)(y)==1=> false
      case (x,y)=> validateLowerRight(x+1,y+1,board)
    }
  }

  def validateLowerLeft(row:Int,col:Int,board:Array[Array[Int]]):Boolean={
    (row,col)match {
      case (x,y) if y <0 || x>board.length-1 => true
      case (x,y) if board(x)(y)==1=> false
      case (x,y)=> validateLowerLeft(x+1,y-1,board)
    }
  }

  def isSafe(row:Int,col:Int,board:Array[Array[Int]]):Boolean={
    (validateHorizantal(row,0,board) &&
      validateVertical(0,col,board) &&
      validateUpperLeft(row,col,board)  &&
      validateUpperRight(row,col,board) &&
      validateLowerRight(row,col,board) &&
      validateLowerLeft(row,col,board))
  }

/*  def saveBoard(board:Array[Array[Int]], allBoards:Set[List[String]]):Set[List[String]]= {
    var row = ""
    var newBoard = List[String]()

    for(i <- 0 until board.length) {
      row = "";
      for(j <- 0 until board.length) {
        if(board(i)(j) == 1)
          row += 'Q';
        else
          row += '.';
      }
      newBoard = newBoard ++ List(row)
    }
    allBoards ++ Set(newBoard)
  }*/


  def findPos(board:Array[Array[Int]],col:Int,allBoards:Set[List[String]])/*:Set[List[String]]*/:Unit={
    col match {
      case x if x == board.length =>
        println("\n--------------------------------------------------------------------------------- ")
        for(i<-board) {
          for (j <- i)
            print(j)
          println(" ")
        }
       // val res = saveBoard(board,allBoards )
       // res
      case x =>
        for(i<- 0 until board.length){
          if(isSafe(i,col,arr)){
            board(i)(col)=1
            findPos(board,col+1,allBoards)
            board(i)(col)=0
          }
        }
        //allBoards
    }
  }

  val arr = Array.ofDim[Int](4,4)
  val arr2 = Array(Array(0,0,0,0),
                  Array(0,0,0,0),
                  Array(0,0,0,0),
                  Array(0,0,0,0)
                  )
  //println(isSafe(0,2,arr))
  println("arr length :"+arr.length)
  var allBoards = Set[List[String]]()
  val result=findPos(arr,0,allBoards)
}
