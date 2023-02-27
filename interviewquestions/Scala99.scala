package interviewquestions

object Scala99 extends  App{

  val list = List(1, 1, 2, 3, 5, 8)

  println("list.last :"+list.last)
  println("list.head :"+list.head)
  println("list.tail :"+list.tail)
  println("list.init :"+list.init)
  println("list.init.last :"+list.init.last)
  println("list(0) :"+list(0))
  println("list.length :"+list.length)
  println("list.size :"+list.size)
  println("list.reverse :"+list.reverse)

  println("list==list :"+list==list)
  println("list==list.reverse :"+list==list.reverse)

  println("list :"+list)
  println("list.drop(3) :"+list.drop(3))
  println("list.dropRight(3) :"+list.dropRight(3))


  var list2 = List('a', 'a', 'a', 'a', 'b', 'c', 'c', 'a', 'a', 'd', 'e', 'e', 'e', 'e')
  var l1 :List[Char]= List()
  var l2 :List[Char]= List()
  var i=0
  while (i != list2.length-1){
    if(list2(i) != list2(i+1)) {
      l1 ::= list2(i)
      l2 :::= List(list2(i))
    }
    i +=1
  }

  println("l1 :"+ l1)
  println("l2 :"+l2)

  var listtest = List(1,2,3)
  println(listtest)
  listtest = listtest ::: List(4)
  println(listtest)

  listtest ::= 5
  println(listtest)

  listtest = 6 :: listtest
  println(listtest)

  //listtest = listtest + (7)
  println(listtest)


}
