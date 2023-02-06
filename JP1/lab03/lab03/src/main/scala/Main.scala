import scala.annotation.tailrec

@main
def wywolanie(): Unit = {
  // println(zad01("lalka"))
  // println(zad02(2137))
  // println(zad03(5))
  println(zad04(List(2,4,3,5), List(1,2,2,3,1,5)))
}

// def reverse(n: String): String = {
//   val c = n.reverse
//   c
// }

def zad01(n: String): String = {
  @tailrec
  def reverse(n: String, akumulator: String = "", len: Int = n.size - 1): String = {
    if(len < 0) akumulator
    else {
      reverse(n, akumulator.concat(n(len).toString), len-1)
    }
  }
  reverse(n)
}

def zad02(n: Int): Boolean = {
  @tailrec
    def czy(x:Int, y:Int=2): Boolean = {
    if (x % y == 0){
      if (x != y) {
        false
    } else {
      true
    }
    } else {
      czy(x,y+1)
    }
  }
  czy(n)
}

def zad03(n: Int): Int = {
  @tailrec
  def ciag(n: Int, akum: Int = 1, prev: Int = 2): Int = {
    if(n < 2){
      if(n == -1) {
        return 2
      }
      akum
    } else {
      ciag(n-1, akum+prev, akum)
    }
  }
  ciag(n-1)
}

def zad04(arr: List[Int], arr2: List[Int]): List[Int] = {
  @tailrec
  def rec(arr: List[Int], arr2: List[Int], acc: List[Int] = List()): List[Int] = {
    (arr, arr2) match {
      case (Nil, Nil) => acc.reverse
      case (Nil, y :: ogon2) => if (y != acc(0)) {rec(List(), ogon2, y :: acc)} else {rec(List(), ogon2, acc)}
      case (x :: ogon, Nil) => if (x != acc(0)) {rec(ogon, List(), x :: acc)} else {rec(ogon, List(), acc)}
      case (x :: ogon, y :: ogon2) => if (x < y) {
        if (acc.size == 0) {rec(ogon, arr2, x :: acc)}
        else {
          if (x != acc(0)) {rec(ogon, arr2, x :: acc)}
          else {rec(ogon, arr2, acc)}
        }
      }
      else {
        if (acc.size == 0) {rec(arr, ogon2, y :: acc)}
        else {
          if (y != acc(0)) {rec(arr, ogon2, y :: acc)}
          else {rec(arr, ogon2, acc)}
        }
      }
    }
    
  }
  rec(arr, arr2)
}