import akka.actor._
import scala.concurrent.duration._
import javax.naming.InitialContext
import scala.collection.convert.impl.CodePointStringStepper
/*
  W konfiguracji projektu wykorzystana została wtyczka
  sbt-revolver. W związku z tym uruchamiamy program poleceniem

    reStart

  a zatrzymujemy pisząc (mimo przesuwających się komunikatów)

     reStop

  i naciskając klawisz ENTER. Jeśli czynności powyższe
  już wykonywaliśmy to możemy też przywołać poprzednie
  polecenia używając strzałek góra/dół na klawiaturze.
*/


case object Cyk
case object Strzelać
case class Init(zamek: ActorRef)
case object Strzel
case class Dead(a: Int)
case object KoniecDanych

class SiłaWyższa extends Actor {
  def receive: Receive = {
    case Cyk =>
      val zamek1 = context.actorOf(Props[Zamek](), "z1")
      val zamek2 = context.actorOf(Props[Zamek](), "z2")      // wysyłamy polacenie „Strzelać” do obu Zamków
      zamek1 ! Init(zamek1)
      zamek2 ! Init(zamek2)
      context.become(nextStep(zamek1, zamek2))
  }

  def nextStep(zamek1: ActorRef, zamek2: ActorRef): Receive = {
    case Cyk => 
      zamek1 ! Strzelać
      zamek2 ! Strzelać
  }
}

class Zamek extends Actor {
  def receive: Receive = {
    case Init(zamek) => 
      val obroncy = (0 to 100).toList.map(x => context.actorOf(Props[Obrońca](), s"o$x"))
      obroncy.foreach(x => x ! Init(zamek))
      obroncy.foreach(x => context.watch(x))
      context.become(nextStep(obroncy, zamek))
  }

  def nextStep(obroncy: List[ActorRef], zamek: ActorRef): Receive = {
    case Strzelać =>
      obroncy.foreach(x => x ! Strzelać)
    case Terminated(obronca) =>
      if (obroncy.size == 1) {
        self ! KoniecDanych
      } else {
          context.become(nextStep(obroncy.filter(x => x != obronca), zamek))
      }
    case Strzel => 
      val r = util.Random
      val deadniety = r.nextInt(obroncy.size)
      obroncy(deadniety) ! Dead(obroncy.size)
    
    case KoniecDanych => 
      println(s"Przegralismy: ${self.path.name} LIPA ZAJELI NASZ ZAMEK!!")
      context.system.terminate()
  }

}

class Obrońca extends Actor {
  def receive: Receive = {
    case Init(zamek) => 
      context.become(nextStep(zamek))
  }

  def nextStep(zamek: ActorRef): Receive = {
    case Strzelać => 
      zamek ! Strzel
    case Dead(a) => 
      val probability = a.toFloat / (2 * 100)
      val r = util.Random
      if (r.nextInt(101) < probability*100) {
        self ! PoisonPill
      }
  }
}

@main
def main: Unit = {
  val system = ActorSystem("Jabberwocky")
  import system.dispatcher

  // UWAGA: „nazwy”, tworzące ścieżkę do aktora muszą być zapisywane
  // z użyciem znaków znaków ASCII (a nie np. UTF8) – stąd „SilaWyzsza”
  val siłaWyższa = system.actorOf(Props[SiłaWyższa](), "SilaWyzsza")

  // Do „animacji” SiłyWyższej wykorzystamy „Planistę” (Scheduler)
  val pantaRhei = system.scheduler.scheduleWithFixedDelay(
    Duration.Zero,     // opóźnienie początkowe
    1.milliseconds, // odstęp pomiedzy kolejnymi komunikatami
    siłaWyższa,        // adresat „korespondencji”
    Cyk     // komunikat
  )

  // Oczywiście zatrzymanie symulacji NIE MOŻE się odbyć tak, jak poniżej
  // Thread.sleep(10000)
  // val res = if (pantaRhei.cancel()) {
  //   "Udało się zakończyć „cykanie”"
  // } else "Coś poszło nie tak – dalej „cyka”"
  // println(res)
  // system.terminate()
}
