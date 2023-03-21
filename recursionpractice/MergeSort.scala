import scala.collection.mutable.ArrayBuffer

object MergeSort extends App{

  def mergeSort(arr:Array[Int], low:Int,high:Int):Array[Int]={
    if(low < high){
      val mid = low+(high-low)/2
      mergeSort(arr,low,mid)
      mergeSort(arr,mid+1,high)
      merge(arr,low,mid,high)
  }
    else
      {
        println("MergeSort Else block ::::::::::::::: low ="+low+"  high ="+high)
        arr
      }
  }
  def merge(arr:Array[Int],low:Int,mid:Int,high:Int):Array[Int]={
    println("Merge Part ++++++++++++++++++++++++++++++++++++++++++++++++++ low ="+low+" mid ="+mid+"  high ="+high)
    val n1 = mid-low+1
    val n2=high-mid

    val rightArray = new Array[Int](n1)
    val leftArray = new Array[Int](n2)

    for(i<- 0 until n1)
      rightArray(i)=arr(i+low)
    for(i<- 0 until n2)
      leftArray(i)=arr(i+mid+1)
    println("   rightArray :"); rightArray.foreach(println(_))
    println("   leftArray  :"); leftArray.foreach(println(_))
    var i=0;
    var j=0;
    var k=low

    while(i<n1 && j<n2){
      if(rightArray(i)>leftArray(j)){
        arr(k)=leftArray(j)
        println(s"      arr(${k}) ="+arr(k))
        j+=1;k+=1;
      }
      else{
        arr(k)=rightArray(i)
        println(s"      arr(${k}) ="+arr(k))
        i+=1;k+=1;
      }
    }
    while(i<n1){
      arr(k)=rightArray(i)
      println(s"      arr(${k}) ="+arr(k))
      i+=1;k+=1;
    }
    while(j<n2){
      arr(k)=leftArray(i)
      println(s"      arr(${k}) ="+arr(k))
      j+=1;k+1;
    }
    arr
  }

  val input= Array(5,4,3,2,1)
  mergeSort(input,0,input.length-1).foreach(println(_))

}
