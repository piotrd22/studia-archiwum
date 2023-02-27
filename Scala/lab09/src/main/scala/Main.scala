import scala.io.Source

@main
def main() = {
  // val dane1 = Source.fromFile("nazwiska.txt").getLines.toList
  // println(zad1(dane1))
  val dane2 = Source.fromFile("ogniem_i_mieczem.txt").getLines.toList
  println(zad2(40, dane2))
}

def zad1(list: List[String]): List[List[String]] = {
  val sorted = list.map(x => x.split(' ').toList)
  .map(x => List(s"${x(0)}", s"${x(0).distinct.length}", s"${x(1)}", s"${x(1).length}"))
  .sortBy(x => x(1).toInt)
  .reverse

  val max = sorted(0)(1)
  
  val maxsorted = sorted.filter(x => x(1).toInt == max.toInt)

  val minSurname = maxsorted.map(x => x(3)).min.toInt

  maxsorted.filter(x => x(3).toInt == minSurname).map(x => List(x(0), x(2)))
}

def zad2(max: Int, list: List[String]): String = { 
  val dane = list.map(x => x.filter(_.isLetter))
  .map(x => x.toLowerCase)
  .filter(x => !x.isEmpty)
  .map(x => x.toList)
  .flatten
  .groupBy(x => x)
  .transform((k,v) => v.size)
  .toList
  .sortBy((x,y) => y)
  .reverse

  val maximum = dane(0)(1)

  val changedByMax = dane.map((x,y) => (x, ((y.toFloat/maximum.toFloat) * max.toFloat).round))

  changedByMax.map((x,y) => s"""$x: ${"*" * y}""")
  .mkString("\n")

}