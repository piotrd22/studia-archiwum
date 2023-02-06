import scala.annotation.tailrec

@main
def wywolaj(): Unit = {
  // val lista = List(1,2,3,4,5,5,5,5)
  // println(oczyść(lista))
  // val lista = List('a', 'a', 'b', 'c', 'c', 'c', 'a', 'a', 'b', 'd')
  // println(skompresuj(lista))
  val l = List(1, 2, 3, 5)
  println(isOrdered(l)(_ <= _))
  // val l = List(1, 3, 5)
  // println(applyForAll(l)((n) => n + 3))
}

def oczyść[A](l: List[A]): List[A] = {
  @tailrec
  def oko[A](l: List[A], acc: List[A]): List[A] = {
    l match {
      case Nil => acc.reverse
      case x :: ogon => if (acc.exists(y => y == x)) {oko(ogon, acc)} else {oko(ogon, x :: acc)} 
    }
  }
  oko(l, List())
}


def skompresuj[A](l: List[A]): List[(A, Int)] = {
    @tailrec
    def comp[A](l: List[A], acc: List[(A, Int)], index: Int): List[(A, Int)] = {
      l match {
        case Nil => acc.reverse
        case x :: ogon => if (acc.size == 0) {
          comp(ogon, (x, index+1) :: acc, index = index + 1)
          } else {
            if (x == acc(0)(0)) {
              comp(ogon, (x, index+1) :: acc.drop(1), index = index + 1)
            } else {
              comp(ogon, (x,1) :: acc, 1)
            }
          }
      }
    }
    comp(l, List(), 0)
}

def isOrdered[A](l: List[A])(leq: (A, A) => Boolean): Boolean = {
    @tailrec
    def is[A](l: List[A], acc: Boolean)(leq: (A, A) => Boolean): Boolean = {
      l match {
        case x :: Nil => true
        case x :: ogon => if (leq(x,ogon(0))) {is(ogon, true)(leq)} else {false}
      }
    }
    is(l,true)(leq)
}

def applyForAll[A, B](l: List[A])(f: A => B): List[B] = {
  @tailrec
    def afa[A,B](l: List[A], acc: List[B])(f: A => B): List[B] = {
      l match {
        case Nil => acc.reverse
        case x :: ogon => afa(ogon, f(x) :: acc)(f)
      }
    }
    afa(l,List())(f)
}

