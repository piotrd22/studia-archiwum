@main
def main(): Unit = {
  val list = List(("Piotr", "Adamowski", 10, 18), ("Piotr", "Adamowski", 9, 15), ("Marek", "Piwo", 5, 10), ("Adam", "Warski", 4, 8), ("Adam", "Warski", 1, 2), 
  ("Andrzej", "Narcyz", 10, 5))
  println(zad2(list))
  // println(zad3(10))
  // val secret = List(1, 3, 2, 2, 4, 5)
  // val guess  = List(2, 1, 2, 4, 7, 2)
  // println(zad1(guess, secret))
}

def zad1(list: List[Int], secret: List[Int]): List[(String, Int)] = {
  val black = list.zip(secret).filter((x,y) => x == y).length
  val white1 = secret.filter(x => list.contains(x))
  val white2 = list.filter(x => secret.contains(x))
  if (white1.length > white2.length) {
    List(("Czarne", black), ("Białe", white2.length - black))
  } else List(("Czarne", black), ("Białe", white1.length - black))
}

def zad2(list: List[(String, String, Int, Int)]): List[(List[(String, Double)], Int)] = {
  val summing = list.groupBy(a => s"${a._1} ${a._2}").toList.map(             
        t => (
        t._1,t._2.foldLeft((0.0, 0.0))(
            (prev, curr) => (
            prev._1 + curr._3.toFloat / t._2.length,
            prev._2 + curr._4.toFloat / t._2.length
            )
        ).toList))

  summing.groupBy((x,y) => y.sum)
  .toList
  .map(x => x._2)
  .map(x => x.partition(y => y._2(0) < x(0)._2(0)))
  .map(x => List(x))
  .flatten
  .map(x => List(x._1, x._2))
  .flatten
  .filter(_.size != 0)
  .sortBy(x => x(0)._2.sum)
  .map(x => x.map(x => (x._1, x._2.sum)))
  .reverse
  .zipWithIndex
  .toList
  .map(x => (x._1, x._2 + 1))
} 

def zad3(n: Int): List[(Int, Int, Int)] = {
  for {
    a <- (1 to n).toList
    b <- (1 to n)
    c <- (1 to 2 * n)
    if (a*a + b*b == c*c)
    if (b > a)
  } yield (a,b,c)
}