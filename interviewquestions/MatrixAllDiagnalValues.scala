package interviewquestions

// Java program to print matrix in diagonal order
object MatrixAllDiagnalValues {

  def printMatrixDiagonal(mat: Array[Array[Int]])={
    var cols = mat(0).length
    var rows = mat.length
    val total_diagnals = cols+rows-1

    for( d <- 1 to total_diagnals){

      var i = if(d<cols) 0 else d-cols
      var j = if(d<cols) cols-d else 0
      if (d<=cols)
      for( r <- 0 until d ){
        println(s"(${i+r},${j+r}) =${mat(i+r)(j+r)}")
      }
      else
        for( r <- 0 until total_diagnals-d ){
          println(s"(${i+r},${j+r}) =${mat(i+r)(j+r)}")
        }
    }
  }

  def printMatrixDiagonal2(mat: Array[Array[Int]])={
    var cols = mat(0).length
    var rows = mat.length
    val total_diagnals = cols+rows-1

    for( d <- 1 to total_diagnals){

      var i = if(d<cols) 0 else d-cols
      var j = if(d<cols) cols-d else 0
      if (d<=cols)
        for( r <- 0 until d ){
          print(s"${mat(i+r)(j+r)} ")
        }
      else
        for( r <- 0 until total_diagnals-d ){
          print(s"${mat(i+r)(j+r)} ")
        }
      println("")
    }
  }

  def printMatrixDiagonal3(mat: Array[Array[Int]])={
    var cols = mat(0).length
    var rows = mat.length
    val total_diagnals = cols+rows-1

    for( d <- 1 to total_diagnals){

      if(d <= cols) {
        var i = if (d % 2 == 0) 0 else d - 1
        var j = if (d % 2 == 0) d - 1 else 0
        for (r <- 0 until d) {
          if (d % 2 == 0)
            print(s"${mat(i + r)(j - r)} ")
          else
            print(s"${mat(i - r)(j + r)} ")
        }
      }
      else {

        var i = if (d % 2 == 0) d-cols else cols-1
        var j = if (d % 2 == 0) cols-1 else d-cols
        println(s"(i,j) = ${i},${j}, total_diagnals = ${total_diagnals}, d = ${d}")
        for (r <- 0 to total_diagnals-d) {
          if (d % 2 == 0)
            print(s"${mat(i + r)(j - r)} ")
          else
            print(s"${mat(i - r)(j + r)} ")
        }
      }
      println("")
    }
  }
  // Driver code
  def main(args: Array[String]): Unit = {
    val mat = Array(Array(1, 2, 3, 4), Array(5, 6, 7, 8), Array(9, 10, 11, 12),Array(13, 14, 15, 16))
    val n = 3
    println(mat(0).length)
    println(mat.length)
    printMatrixDiagonal3(mat)
  }
}

// This code is contributed by Anant Agarwal.
