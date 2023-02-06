object zadania extends App {
  def zad02(): Unit = {
    println("Podaj liczbe:")
    val liczba = io.StdIn.readInt()
    if (liczba % 2 == 0) {
      println("Parzysta")
    } else {
      println("Nieparzysta")
    }
  }

  def zad03(x:Int, y:Int): Unit = {
    if (y == 0) {
      println(x)
    } else {
      zad03(y,x%y)
    }
  }

  def zad04(x:Int, y:Int=2): Boolean = {
    if (x % y == 0){
      if (x != y) {
        false
    } else {
      true
    }
    } else {
      zad04(x,y+1)
    }
  }
  def zad05(x:Int): Unit = {
    def sum(a:Int, b:Int): Unit = {
      if (b > a) {
        println("Liczby nie da się rozłożyć na dwie l;iczby pierwsze")
      } else if ( zad04(b) == true) {
          if (zad04(a-b) == true) {
            println("Liczbę da się rozłożyc na sumę dwóch liczb pierwszych", b,  "+", a-b)
          } else {
            sum(a, b+1)
          }
      } else {
        sum(a, b+1)
      }
    }
    if (x % 2 != 0) {
      println("Proszę podawać liczby parzyste!")

    } else if (x <= 2) {
        println("Proszę podawać liczby większe od 2!")
    } else {
      sum(x,2)
    }
  }
  zad05(8)
}