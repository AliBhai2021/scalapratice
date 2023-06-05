package questions

object MinimumPlatforms extends App{

  def findMinimumPlatforms(a:Array[Int],d:Array[Int]):Int={
    var count=0
    var maxPlatform=0
    var arrival=0;
    var depature=0;
    while(arrival < a.length){
      if(a(arrival) <= d(depature)){
        count +=1
        maxPlatform = Math.max(maxPlatform,count)
        arrival +=1
      }
      else {//if(a(arrival) > d(depature)){
        count -=1
        depature +=1
      }
    }
    maxPlatform
  }

  val arrival = Array(900,940,950,1100,1500,1800)
  val departure = Array(910,1200,1120,1130,1900,2000)

  println("Min Platforms : "+findMinimumPlatforms(arrival,departure))

}
