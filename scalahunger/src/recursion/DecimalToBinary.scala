package recursion

object DecimalToBinary extends App{

  def convertToBinary(num:Int,result:String=""):Unit={
    num match {
      case number if number == 0 => println(result)
      case number =>

        convertToBinary(number/2, number%2 +result)
    }
  }
  convertToBinary(5)
  convertToBinary(4)
  convertToBinary(10)

}
