// def obramuj(napis: String): String = {
//   var napisSplit = napis.split("\n")
//   var max = napisSplit(0).length()
//   var wynik=""

//   for (i <- 0 to (napisSplit.length-1) )
//     if(max<napisSplit(i).length()) {
//       max = napisSplit(i).length()
//     }

//   wynik+="X"*(max+4)+"\n"
//   for (i <- 0 to (napisSplit.length-1)) {
//     wynik +="X "+napisSplit(i)+" "*(max-napisSplit(i).length())+" x\n"
//     wynik += "X"*(max+4)+"\n"
//   }
//   wynik
// }

// @main 
// def zad01: Unit = {
//   var argument="""
// Ala
// ma
// kota i psa
//   """

//   var wynik=obramuj(argument)

//   println(wynik)

//   argument="Ala\nma\nkota i psa"
//   wynik=obramuj(argument)
//   println(wynik)
// }


def obramuj(napis: String): String = {
  val napisSplit = napis.split('\n')
  val max = napisSplit.map(x => x.length).max

  List(
    "X" * (max + 4),
    "X" + " " * (max + 2) + "X",
    napisSplit.map(c => "X " + c + " " * (max - c.length) + " X").mkString("\n"),
    "X" + " " * (max + 2) + "X",
    "X" * (max + 4)
  ).mkString("\n")
}

@main
def zad01() = {
  var argument = "Ala\nma\nkota i psa."

  var wynik = obramuj(argument)
  println(wynik)

  argument = """
Ala
ma
kota i psa.
  """

  wynik = obramuj(argument)

  println(wynik)
}


def zad02(x: String, y: String): String = {
  val nazwa = ('A'to'Z').toArray
  val nazwalen = nazwa.size
  val slowo = x.toUpperCase
  val klucz = y.toUpperCase
  val dlugosc = slowo.size


  def szyfr(x: String, y: String, odp: String = "", index: Int = 0): String = {

    val litera = y(index)
    val literaszyfru = x(index)
    val indexlitery = nazwa.indexOf(litera)
    val indexszyfru = nazwa.indexOf(literaszyfru)
    val ogon = y.tail
    val ogonszyfru = x.tail
    var d = 0

    if (ogonszyfru.size == 0) {
      if (indexszyfru + indexlitery < nazwa.size) {
          d = indexszyfru
      } else {
          d = indexszyfru - nazwa.size
      }

      val dodaj = (nazwa(d+indexlitery)).toString
      val odpowiedz = odp.concat(dodaj)
      odpowiedz
    }
    else {
      if (indexszyfru + indexlitery < nazwa.size) {
          d = indexszyfru
      } else {
          d = indexszyfru - nazwa.size
      }

      val dodaj = (nazwa(d+indexlitery)).toString
      val odpowiedz = odp.concat(dodaj)
      szyfr(ogonszyfru, ogon, odpowiedz)
    }
  }


  if (klucz.size > dlugosc) {
    szyfr(slowo, klucz)
  } else {
    val newklucz = klucz.concat(klucz)
    zad02(slowo, newklucz)
  }
}


@main
def odp1() = {
  val wynik = zad02("zaszyfrujmnie", "katar")
  println(wynik)
}