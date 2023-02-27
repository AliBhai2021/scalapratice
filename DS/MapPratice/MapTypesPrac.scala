package DS.MapPratice

object MapTypesPrac extends App{

  // HashMap
  val hashMap = scala.collection.mutable.HashMap[String,Int]()
  println(hashMap.put("A",1))
  println(hashMap.put("B",2))
  println(hashMap.put("A",2))
  println(hashMap.put("C",3))
  println(hashMap.put("D",4))
  println(hashMap)
  println("hashMap.head : "+hashMap.head)
  println("hashMap.tail : "+hashMap.tail)
  println("hashMap.init : "+hashMap.init)
  println("hashMap.last : "+hashMap.last)


  // LinkedHashMap
  println(":::::::::::: LinkedHashMap ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::")
  val linkedhashMap = scala.collection.mutable.LinkedHashMap[String,Int]()
  println(linkedhashMap.put("A",1))
  println(linkedhashMap.put("B",2))
  println(linkedhashMap.put("A",2))   // insertion order not changed
  println(linkedhashMap.put("C",3))
  println(linkedhashMap.put("D",4))
  println(linkedhashMap)
  println("linkedhashMap.head : "+linkedhashMap.head)
  println("linkedhashMap.tail : "+linkedhashMap.tail)
  println("linkedhashMap.init : "+linkedhashMap.init)
  println("linkedhashMap.last : "+linkedhashMap.last)

  // ListMap
  println(":::::::::::: ListMap ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::")
  val listMap = scala.collection.mutable.ListMap[String,Int]()
  listMap.clear()
  println(listMap.put("A",1))
  println(listMap.put("B",2))
  //println(listMap.put("A",2))   // insertion order not follows
  println(listMap.put("C",3))
  println(listMap.put("D",4))
  println(listMap)
  println("ListMap.head : "+listMap.head)
  println("ListMap.tail : "+listMap.tail)
  println("ListMap.init : "+listMap.init)
  println("ListMap.last : "+listMap.last)

  // SortedMap
  println(":::::::::::: SortedMap ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::")
  val sortedMap = scala.collection.mutable.SortedMap[String,Int]()
  sortedMap.clear()
  println(sortedMap.put("A",1))
  println(sortedMap.put("B",2))
  println(sortedMap.put("A",2))   // insertion order not follows
  println(sortedMap.put("C",3))
  println(sortedMap.put("D",4))
  println(sortedMap)
  println("sortedMap.head : "+sortedMap.head)
  println("sortedMap.tail : "+sortedMap.tail)
  println("sortedMap.init : "+sortedMap.init)
  println("sortedMap.last : "+sortedMap.last)
}
