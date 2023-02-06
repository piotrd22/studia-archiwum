@main
def wywolaj(): Unit = {
  // val lista = List(Some(5.4), Some(-2.0), Some(1.0), None, Some(2.6))
  // println(sumOpts(lista))
  // val lista = List(2, 1, 1, 5,1,6)
  // println(position(lista, 6))
  // val lista = List(1, 2, 1, 1, 5)
  // println(indices(lista, 1))
  // val lista = List(1, 2, 3, 4, 5)
  // println(swap(lista))
  // val lista = List('a','b','a','c','c','a')
  // println(freq(lista))
  val strefy: List[String] = java.util.TimeZone.getAvailableIDs.toList
  println(zad(strefy))
}

def sumOpts(l: List[Option[Double]]): Option[Double] = {
  if (l.filter(_ != None).size > 0) {
    Some(l.flatMap(x => x).sum)}
  else {None}
}

def position[A](l: List[A], el: A): Option[Int] = {
    if (l.contains(el)) {Some(l.indexOf(el))}
    else {None}
}

def indices[A](l: List[A], el: A): Set[Int] = {
    l.zipWithIndex.filter((x,y) => x == el).map((x,y) => y).toSet
}

def swap[A](l: List[A]): List[A] = {
   l.sliding(2,2).map(x => x.reverse).toList.flatten
}

def zad(l: List[String]): List[String] = {
  l.filter(_.contains("/")).map(x => x.split("/").map(_.trim).toList).flatten.sliding(2,2).toList.map(x => x.filter(y => x.indexOf(y) == 0 )).flatten.sorted.sortBy(_.size).distinct
}

def freq[A](l: List[A]): List[(A, Int)] = {
    l.groupBy(n => n).toList.map((x,y) => (x, y.size))
}
