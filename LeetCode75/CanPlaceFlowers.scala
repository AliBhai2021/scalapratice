package LeetCode75

object CanPlaceFlowers extends App {

  def canPlaceFlowers(flowerbed: Array[Int], n: Int): Boolean = {
    var count = 0
    var i = 0
    while (i < flowerbed.length) {
      if (flowerbed(i) == 0 && (i == 0 || flowerbed(i - 1) == 0) &&
          (i == flowerbed.length - 1 || flowerbed(i + 1) == 0)) {
        flowerbed(i) = 1
        count += 1
        i += 1  // Skip the next spot to avoid adjacent planting
      }
      if (count >= n) return true
      i += 1
    }

    count >= n
  }
  println(canPlaceFlowers(Array(1,0,0,0,1), 1))
  println(canPlaceFlowers(Array(1,0,1,0,1), 1))
  println(canPlaceFlowers(Array(1,0,0,0,0,0,1), 2))

}
