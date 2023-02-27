import akka.actor._
import scala.concurrent.duration._

object SiłaWyższa {
  case object Cyk
  case object Strzelać
  case class Przeciwnik(zamek: ActorRef)
}
class SiłaWyższa extends Actor {
  import SiłaWyższa._

  val zamekA = context.actorOf(Props[Zamek](), "zamekA")
  val zamekB = context.actorOf(Props[Zamek](), "zamekB")
  val zamki = context.actorSelection("/user/SilaWyzsza/zamek*") // fajne zapamietaj -- * jako regex

  def receive: Receive = zZamkami(zamki)

  def zZamkami(zamki: ActorSelection): Receive = {
    case Cyk =>
      zamekA ! Przeciwnik(zamekB)
      zamekB ! Przeciwnik(zamekA)

      context.become(poInicjalizacji(zamki))
  }

  def poInicjalizacji(zamki: ActorSelection): Receive = {
    case Cyk =>
      zamki ! Strzelać
  }

}

object Zamek {
  case object KulaWPłot
  case object Koniec
}

class Zamek extends Actor {
  import scala.util.Random
  import Zamek._

  val aktorzy = for {
    i <- (0 to 100).toList
  } yield {
    val o = context.actorOf(Props[Obrońca](), s"${self.path.name}_obronca_$i")
    context.watch(o) // trzeba dorzucic terminated itp. i zmieniac context z przefiltorwana lista zabitych XD
    o
  }

  def receive: Receive = {
    case SiłaWyższa.Przeciwnik(zamek) => 
      println(s"znam juz przciwnika ${zamek}")
      context.become(zObroncami(aktorzy, zamek))
  }

  def zObroncami(obroncy: List[ActorRef], przeciwnik: ActorRef): Receive = {
    case SiłaWyższa.Strzelać => 
      for {
        o <- obroncy
      } yield o ! Obrońca.Strzał(przeciwnik)

    case KulaWPłot => 
      obroncy(Random.nextInt(obroncy.length)) ! Obrońca.Bum(obroncy.length)

    case Terminated(dead) => 
      if (obroncy.filter(x => x != dead).length == 0) then self ! Koniec
      else context.become(zObroncami(obroncy.filter(x => x != dead), przeciwnik))

    case Koniec =>
      println(s"\n KONIEC WALKI ${przeciwnik.path.name} wygrał!")
      context.system.terminate()

    case cos =>
      println(s"z obroncami ${self.path.name} dostalem cos")
  }

}
object Obrońca {
  case class Bum(lObroncow: Int)
  case class Strzał(przecwinik: ActorRef)
}

class Obrońca extends Actor {
  import util.Random
  import Obrońca._

  def receive: Receive = {
    case Strzał(przeciwnik) => 
      przeciwnik ! Zamek.KulaWPłot
    
    case Bum(liczba) => 
      val losowa = Random.nextDouble <= liczba/(2.0 * 100)
      if losowa then self ! PoisonPill

    case cos =>
      println(s"cos tam obronca ${self.path.name} dostalem cos")
    

  }
}

@main
def bitwa: Unit = {
  val system = ActorSystem("Jabberwocky")
  import system.dispatcher

  // UWAGA: „nazwy”, tworzące ścieżkę do aktora muszą być zapisywane
  // z użyciem znaków znaków ASCII (a nie np. UTF8) – stąd „SilaWyzsza”
  val siłaWyższa = system.actorOf(Props[SiłaWyższa](), "SilaWyzsza")

  // Do „animacji” SiłyWyższej wykorzystamy „Planistę” (Scheduler)
  val pantaRhei = system.scheduler.scheduleWithFixedDelay(
    Duration.Zero,     // opóźnienie początkowe
    10.milliseconds, // odstęp pomiedzy kolejnymi komunikatami
    siłaWyższa,        // adresat „korespondencji”
    SiłaWyższa.Cyk     // komunikat
  )

  // Oczywiście zatrzymanie symulacji NIE MOŻE się odbyć tak, jak poniżej
  // Thread.sleep(3000)
  // val res = if pantaRhei.cancel()
  //   then "Udało się zakończyć „cykanie”"
  //   else "Coś poszło nie tak – dalej „cyka”"
  // println(res)
  // system.terminate()
}
