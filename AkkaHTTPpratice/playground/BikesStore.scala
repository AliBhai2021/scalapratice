package AkkaHTTPpratice.playground

import akka.actor.{Actor, ActorLogging, ActorSystem, Props}
import akka.stream.ActorMaterializer
import akka.pattern.ask
import akka.util.Timeout

import scala.util.{Failure, Success}

object BikesStore extends App {

  implicit val system = ActorSystem("Bikes-Store")
  implicit val materialze = ActorMaterializer()
  import system.dispatcher
  case class Bike(name:String,model:Int)
  object BikeModels{
    case class AddBike(bike:Bike)
    case class SellBike(bike:Bike)
    object GetAllBikes
    case class GetBike(id:Int)
  }

  class BikesStore extends Actor with ActorLogging{
    import BikeModels._
    var bikelist:Map[Int,Bike] =Map()
    var id = 1
    override def receive:Receive={
      case AddBike(bike:Bike) =>
        log.info("Adding Bike....")
        bikelist=bikelist+(id ->bike)
        sender() ! bikelist(id)
        id +=1
      case SellBike(bike:Bike)=>
      case GetBike(id) =>
        sender()! bikelist.get(id)
      case GetAllBikes =>
        log.info("Preparing List...")
        sender() ! bikelist.values.toList
    }
  }

  import scala.concurrent.Future
  import scala.concurrent.duration._
  implicit val defaultTimeout = Timeout(2 seconds)
  import BikeModels._
  val bike1 = Bike("honda",2001)
  val bike2 = Bike("xl",2002)
  val bike3 = Bike("TVS",2022)
  val obj1 = system.actorOf(Props[BikesStore],"BikesStoreActor")
  val res1 =obj1 ? AddBike(bike1)
      res1.onComplete(x=>
      println(x)
      )
  val res2 = obj1 ? AddBike(bike2)
      res2.onComplete{
        case Success(value) => println(value)
        case Failure(exception) => println(exception)
      }
 val res3= obj1 ? GetAllBikes
    res3.onComplete(x=>println(x))

  val res4 = obj1 ? AddBike(bike3)
      res4.onComplete(x=>println(x))

  val res5 = obj1 ? GetAllBikes
  res5.onComplete(x=>println(x))


}
