package scalacookbook.lession06objects

object CompanionWithParameters extends App{

  object Animal{
    def apply(str:String): Animal = {
      //val animal = new Animal
      val animal = new Animal(str)
      animal.name=str
      animal
    }
  }

//  class Animal{
//    var name = ""
//  }
//  println("Animal name is :: "+Animal("CAT").name)
//


  class Animal(animal:String){
    println("name : "+animal)
    var name = if(animal.isBlank) "Unkown" else animal
    println("name : "+name)

  }
  println("Animal name is :: "+Animal(" ").name)

}
