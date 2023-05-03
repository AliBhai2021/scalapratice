package recursion

import scala.collection.mutable

object MathExpressionEvaluation extends App{
    val numStack = new mutable.Stack[Int]()
    val opStack = new mutable.Stack[Char]()
  def hasPrecedence(token:Char,stackOPtop:Char):Boolean={
    (token,stackOPtop) match {
      case (x,y) if  y == '(' || y == ')'=> false
      case (x,y) if  (x =='*' || x =='/') && (y =='+' || y =='-') => false
      case _ => true
    }
  }

  def applyOperation(op1:Int,op:Char,op2:Int):Int={
    op match {
      case '+' => op1 + op2
      case '-' => op2 - op1
      case '*' => op1 * op2
      case '/' => op2 / op1
      case _ => op2
    }
  }


def mathExpressionEvaluation(mathString:String):Unit= {
  numStack.clear();opStack.clear()
  def expEvaluate(exp: String): Unit = {
     println("@@@@@@@@@@@@ exp = "+exp+" stack ="+numStack+"  opStack ="+opStack)
    exp match {
      case token if token.isEmpty =>
        while (!opStack.isEmpty)
          numStack.push(applyOperation(numStack.pop(), opStack.pop(), numStack.pop()))
        println("result = "+numStack)

      case token if token(0) == '(' =>
        opStack.push(token(0))
        expEvaluate(exp.substring(1))

      case token if token(0) == ')' =>
        while (opStack.top != '(') {
          numStack.push(applyOperation(numStack.pop(), opStack.pop(), numStack.pop()))
        }
       opStack.pop()
        expEvaluate(exp.substring(1))

      case token if token(0).isDigit =>
        numStack.push(token(0) - 48)
        expEvaluate(exp.substring(1))

      case token if token(0) == '+' || token(0) == '-' || token(0) == '*' || token(0) == '/' =>
         if(!opStack.isEmpty && hasPrecedence(token(0), opStack.top))
            numStack.push(applyOperation(numStack.pop(), opStack.pop(), numStack.pop()))
        opStack.push(token(0))
        expEvaluate(exp.substring(1))
    }
  }
  expEvaluate(mathString)
}

  mathExpressionEvaluation("1+(2+(3+4)+5)/7-1")

}
