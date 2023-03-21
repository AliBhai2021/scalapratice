object DecimalToBinary extends App{

  def decimalToBinary(number:Int,res:String=""):String={
    number match {
      case x if x == 0 => res
      case x =>
        decimalToBinary(x/2,  x%2+res)
    }
  }

  println(decimalToBinary(5))
  println(decimalToBinary(10))
  println(decimalToBinary(7))
  println(decimalToBinary(8))

}
