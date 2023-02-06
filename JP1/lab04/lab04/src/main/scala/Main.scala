import scala.annotation.tailrec

@main
def wywolaj(): Unit = {
  // val lista = List(Some(4.0), Some(6.0), Some(-2.0))
  // println(sumuj(lista))
  // val lista1 = List(2.0, -1.6, 3.2, 5.4, -8.4)
  // val lista2 = List(3.3, -3.1, 3.2, -4.1, -0.4, 5.5)
  // println(maksimum(lista1, lista2))
  // val lista = List(2, 1, 4, 1, 3, 3, 1, 2)
  // println(usun(lista, 1))
  val lista = List(1, 3, 5, 6, 7)
  println(divide(lista))
}

def sumuj(l: List[Option[Double]]): Option[Double] = {
  @tailrec
  def sum(l: List[Option[Double]], acc: Double): Option[Double] = {
    l match {
      case Nil => if(acc > 0) {Some(acc)} else {None}
      case Some(x) :: ogon => if(x>0) {sum(ogon, acc+x)} else {sum(ogon,acc)} 
      case _ :: ogon => sum(ogon, acc)
    }
  }
  sum(l,0.0)
}
// _ w pattern matchingu to cokolwiek


def maksimum(l1: List[Double], l2: List[Double]): List[Double] = {
    @tailrec
    def max(l1: List[Double], l2: List[Double], acc: List[Double]): List[Double] = {
      (l1, l2) match {
        case (Nil, Nil) => acc.reverse
        case (Nil, y :: ogon2) => max(List(), ogon2, y :: acc)
        case (x :: ogon, Nil) => max(ogon, List(), x :: acc)
        case (x :: ogon, y :: ogon2) => if(x > y) {max(ogon, ogon2, x :: acc)} else {max(ogon,ogon2, y :: acc)}
      }
    }

    max(l1, l2, List())
}


def usun[A](l: List[A], el: A): List[A] = {
  @tailrec
  def usu[A](l: List[A], el: A, acc: List[A]): List[A] = {
    l match {
      case Nil => acc.reverse
      case x :: ogon => if(x == el) {usu(ogon, el, acc)} else {usu(ogon, el, x :: acc)}
    }
  }
  usu(l,el,List())
}

def divide[A](l: List[A]): (List[A], List[A]) = {
    @tailrec
    def dvd[A](l: List[A], acc1: List[A], acc2: List[A], index: Int = 0): (List[A], List[A]) = {
      l match {
        case Nil => (acc1.reverse, acc2.reverse)
        case x :: ogon => if(index % 2 == 0) {dvd(ogon, x :: acc1, acc2, index = index + 1)} else {dvd(ogon, acc1, x :: acc2, index= index + 1)}
      }
    }
    dvd(l, List(), List())
}

def zad_5: Unit = {

  type Pred[A] = A => Boolean

  def and[A](p: Pred[A], q: Pred[A]): Pred[A] = {
    a => p(a) && q(a)
  }

  def or[A](p: Pred[A], q: Pred[A]): Pred[A] = {
    a => p(a) || q(a)
  }

  def not[A](p: Pred[A], q: Pred[A]): Pred[A] = {
    a => !p(a) && !q(a)
  }

  def imp[A](p: Pred[A], q: Pred[A]): Pred[A] = {
    a => !p(a) || q(a)
  }

}
