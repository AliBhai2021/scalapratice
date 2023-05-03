package recursion

object CountTotalPathsInMatrixGrid extends App{

  def findAllPathGrids(row: Int, col: Int, path:String=""):Unit={
    (row,col) match {
      case (r,c) if r ==1 && c == 1 => println("Path = "+path)
      case (r,c) if r ==0 || c == 0 => ""
      case (r,c) =>
        findAllPathGrids(row-1,col,path+"U")
        findAllPathGrids(row,col-1,path+"L")
    }

  }

  findAllPathGrids(2,3)
  println(":::::::::::::::::::::::::")
  findAllPathGrids(4,3)

}
