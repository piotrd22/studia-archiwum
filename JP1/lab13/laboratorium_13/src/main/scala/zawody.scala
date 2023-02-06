package jp1.akka.lab13

// „Interfejs użytkownika” wymaga pewnych dodatkowych elementów:
import scala.concurrent.ExecutionContext
import scala.util.control.Breaks._
import scala.io.StdIn

import akka.actor.{ActorSystem, Props}

import model.*

@main
def zawody: Unit = {

  val system = ActorSystem("system")
  val organizator = system.actorOf(Props[Organizator](), "organizator")

  // Interfejs „organizatora”:
  implicit val ec: ExecutionContext = ExecutionContext.global

  println("Witaj w naszym małym sumulatorze konkursu. " + "\n" +
    "Aby rozpocząć wpisz 'start', aby zatrzymać wpisz 'stop'. Zacznij od zaczęcia eliminacji komendą 'eliminacje'. " + "\n" +
    "Po ich rozsztrygnięciu możeśz zobaczyć wyniki wpisująć 'wyniki'. " + "\n" +
    "Następnie wpisz 'finał', aby rozpocząć finał i zakończ również wynikami i stopem")

  breakable {
    while (true) {
      StdIn.readLine("polecenie: ") match {
        case "start" =>
          // początek zawodów
          organizator ! Organizator.Start
        case "eliminacje" =>
          organizator ! Organizator.Eliminacje
          // polecenie rozegrania rundy eliminacyjnej
        case "finał" =>
          organizator ! Organizator.Finał
          // polecenie rozegrania rundy finałowej
          // wymaga zamknięcia Rundy eliminacyjnej i utworzenie
          // Rundy finałowej, zawierającej najlepszych 20.
          // zawodników z Rundy eliminacyjnej
        case "wyniki" =>
          organizator ! Organizator.Wyniki
          // żądanie rankingów (lub rankingu finałowego)
        case "stop" =>
          organizator ! Organizator.Stop
          break()
        case _ =>
      }
    }
  }

}
