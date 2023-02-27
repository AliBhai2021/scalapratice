package codesignal

import scala.collection.mutable

object ReverseInParentheses extends App{

  def solution(inputString: String): String = {
    var stack1 = new mutable.Stack[Char]()
    var queue1 = new mutable.Queue[Char]()
    var res = new mutable.Queue[Char]()

    var inner=0
    var ptr=0
    for(i <- inputString){
      if(inner==0 && i != '(')
      {
        res.enqueue(i)
      }
      else if (i != ')'){
        if(i=='(')
          inner +=1
        stack1.push(i)

      }
      else{
        if(i==')')
          inner -=1
        while (stack1.top !='('){
          queue1.enqueue(stack1.top)
          stack1.pop()
        }
        stack1.pop()

        if(stack1.nonEmpty) {
          while (queue1.nonEmpty) {
            stack1.push(queue1.head)
            queue1.dequeue()
          }
        }
        else{
          while (queue1.nonEmpty){
            res.enqueue(queue1.head)
            queue1.dequeue()
          }
        }

      }

    }
    res.mkString("")
  }
  //println(solution("(bar)"))
  //println(solution("ABC(DEF)(123)XYZ"))
  //println(solution("MNO(ABC)(XYZ)123(XYZ)")) //MNOCBAZYX123ZYX
  //println(solution("ABC(123(XYZ))")) //ABCXYZ321
  // println(solution("ali(abc(bar)(xyz))")) //alixyzbarcba
  //println(solution("MNO(ABC(EFJ(123)))")) ////MNOEFJ321CBA
  //println(solution("123(ABC(DEF(XYZ(980))))"))


  println(solution("MNO(ABC(EFJ(123)))XYZ"))
}

//MNO(ABC(EFJ(123)))
//MNO(ABC(EFJ321))
//MNO(ABC123JFE)
//MNOEFJ321CBA

//123(ABC(DEF(XYZ(980))))
//123(ABC(DEF(XYZ089)))
//123(ABC(DEF980ZYX)) --
//123(XYZ089FEDCBA)
//123ABCDEF980ZYX