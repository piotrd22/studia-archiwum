import akka.actor.{ActorSystem, Actor, ActorRef, Props}

case class Init(liczbaPracowników: Int)
case class Zlecenie(tekst: List[String])
case class Wykonaj(tekst: String)
case class Wynik(n: Int)
case object KoniecDanych

class Szef extends Actor {
  def receive: Receive = {
    case Init(n) => 
      val listOfWorkers = (0 to n).toList.map(x => context.actorOf(Props[Pracownik](), s"a$x"))
      context.become(nowe(listOfWorkers, 0, 0))
  }

  def nowe(list: List[ActorRef], acc: Int, licznik: Int): Receive = {
    case Zlecenie(tekst) if (tekst.size > 0) => 
      list(0) ! Wykonaj(tekst(0))
      context.become(nowe((list.tail :+ list.head), acc, licznik + 1))
      self ! Zlecenie(tekst.tail)
    case Zlecenie(tekst) if (tekst.size == 0 && licznik == 0) => 
      self ! KoniecDanych
    case Zlecenie(tekst) if (tekst.size == 0 && licznik > 0) => 
      self ! Zlecenie(tekst)
    case Wynik(n) => 
      context.become(nowe(list, acc + n, licznik - 1))
    case KoniecDanych => println(acc)
  }
}

class Pracownik extends Actor {
  def receive: Receive = {
    case Wykonaj(linijka) => 
      if (!linijka.isEmpty) {
        val p = linijka.split(" ").toList.size
        sender() ! Wynik(p)
      } else {
        sender() ! Wynik(0)
      }
  }
}

@main
def zadanie_39: Unit = {
  // poniższą listę napisów wyślij do „szefa” za pomocą komunikatu tu „Zlecenie”
  val lista = io.Source
      .fromResource("ogniem_i_mieczem.txt")
      .getLines
      .toList
  val system = ActorSystem("LICZENIE")
  val szefik = system.actorOf(Props[Szef](), "szefik")
  szefik ! Init(5)
  szefik ! Zlecenie(lista) 
}
