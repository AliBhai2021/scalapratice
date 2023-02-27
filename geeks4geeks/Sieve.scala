package geeks4geeks

object Sieve extends App {
  def abc(s: Stream[Int], head: Int) = {
    val r = s filter {
      x => {
        if (x % head != 0) {
          println()
          println(s"${x} is not evenly divisible by ${head}")

          true
        }
        else {
          println()
          println(s"${x} is evenly divisible by ${head}. So Discard ${x}")
          false
        }
      }
    }
    r
  }
  def numberStream(g: Int): Stream[Int] = Stream.from(g)

  def sieve_of_Eratosthenes(stream: Stream[Int])  :Stream[Int] =
    stream.head #:: sieve_of_Eratosthenes(abc(stream.tail, stream.head))

  val no_of_primes = sieve_of_Eratosthenes(numberStream(2))
  (no_of_primes take 7) foreach {
    println(_)
  }
}

