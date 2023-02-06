import scala.annotation.tailrec

@main
def wywolaj(): Unit = {
  // val l = List(0.0,1.0,2.2,3.3,4.4,5.5,6.6,7.7,8.8,9.8,-1.4,-4.5,-1.0)
  // println(subseq(l,4,7))
  // println(pairPosNeg(l))
  // val lista = Seq(1,2,3,4)
  // println(isOrdered(lista)(_<_))
  // val l = List(1,1,2,4,4,4,1,3)
  // println(deStutter(l))
  // val l = List(List(1,2,3), List(5,6,7), List(6,5,4))
  // println(remElems(l, 2))
  val l = List(1, 1, 2, 4, 4, 3, 4, 1, 3)
  println(freqMax(l))
}

def subseq[A](list: List[A], begIdx: Int, endIdx: Int): List[A] = {
    list.drop(begIdx).take(endIdx - begIdx)
}

def pairPosNeg(list: List[Double]): (List[Double], List[Double]) = {
    list.filter(_ != 0).partition(x => x > 0.0)
}

def isOrdered[A](seq: Seq[A])(leq:(A, A) => Boolean): Boolean = {
    seq.sliding(2).forall(a => if(leq(a(0), a(1))) {true} else {false})
}

def deStutter[A](list: List[A]): List[A] = {
    (list.foldLeft(List[A]())((arr, elem) => if(arr == Nil || arr(0) != elem) {elem :: arr} else {arr})).reverse
}

def remElems[A](list: List[A], k: Int): List[A] = {
    list.zipWithIndex.filter((x, y) => y != k).map((x,y) => x)
}

def freqMax[A](list: List[A]): (Set[A],Int) = {
    (list.filter(x => {list.count(_ == x) == list.map(x => list.count(_ == x)).max}).toSet, list.map(x => list.count(_ == x)).max)
}
