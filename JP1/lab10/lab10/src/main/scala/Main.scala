object AkkaIntro {
  import akka.actor.{ActorSystem, Actor, ActorRef, Props}

  // ZADANIE POKAZANE
  // case class Start(aktor: ActorRef)
  // case object msg
  // class MyActor extends Actor {
  //   def receive: Receive = {
  //     case Start(aktor) => println("start")
  //     aktor ! msg
  //     context.become(stop)
  //     case msg => println("oderane")
  //     sender() ! msg
  //   }
  //   def stop: Receive = {
  //     case msg => 
  //     println("Koniec")
  //     context.system.terminate()
  //   }
  // }

  // // case object Piłeczka

  // // case class Graj35(przeciwnik: ActorRef)

  // @main
  // def main: Unit = {
  //   val system = ActorSystem("halloAkka")
  //   try {
  //     val a1 = system.actorOf(Props[MyActor](), "a1")
  //     val a2 = system.actorOf(Props[MyActor](), "a2")
  //     a1 ! Start(a2)
  //     println("enter")
  //     io.StdIn.readLine()
  //   } finally {
  //   system.terminate()
  //   }
  // }

// ZAD 1

  // case class Graj35(przeciwnik: ActorRef)
  // case object Piłeczka

  // class Gracz35 extends Actor {
  //   def receive: Receive = {
  //     case Graj35(aktor) => 
  //     println("ping")
  //     aktor ! Piłeczka
  //     context.become(stop)
  //     case Piłeczka => 
  //     println("odbijam - PONG")
  //     sender() ! Piłeczka
  //   }
  //   def stop: Receive = {
  //     case Piłeczka => println("ping")
  //     sender() ! Piłeczka
  //   }
  // }

  // ZAD2

  // case class Graj36(przeciwnik: ActorRef, maks: Int)
  // case object Piłeczka

  // class Gracz35 extends Actor {
  //   def receive: Receive = {
  //     case Graj36(aktor, maks) => 
  //       println("ping")
  //       aktor ! Piłeczka
  //       context.become(nextStep(maks - 1))
  //     case Piłeczka => 
  //       println("odbijam - PONG")
  //       sender() ! Piłeczka
  //       context.become(nextStep2)
  //   }
  //   def nextStep(msgCount: Int): Receive = {
  //     case Piłeczka if (msgCount > 0) =>
  //       println("ping")
  //       context.become(nextStep(msgCount - 1 ))
  //       sender() ! Piłeczka
  //     case Piłeczka if (msgCount <= 0) => 
  //       context.system.terminate()
  //   }
  //   def nextStep2: Receive = {
  //     case Piłeczka =>
  //       println("odbijam - PONG")
  //       sender() ! Piłeczka
  //   } 
  // }

  // ZAD3 

  // case class Graj35(przeciwnik: ActorRef, atacker: ActorRef)
  // case object Piłeczka

  // class Gracz35 extends Actor {
  //   def receive: Receive = {
  //     case Graj35(aktor, aktor2) => 
  //       println(self.path.name)
  //       aktor ! Graj35(aktor2, self)
  //       context.become(stop1(aktor))
  //   }
  //   def stop1(x: ActorRef): Receive = {
  //     case Graj35(aktor, aktor2) => println(self.path.name)
  //       x ! Piłeczka
  //     case Piłeczka => 
  //       println(self.path.name)
  //       x ! Piłeczka
  //   }

  // }

  // ZAD 4

  case class Graj38(zawodnicy: List[ActorRef], n: Int)
  case class Piłeczka38(zawodnicy: List[ActorRef], n: Int)

  class Gracz38 extends Actor {
    def receive: Receive = {
      case Graj38(z, n) => z(n) ! Piłeczka38(z, n)
      case Piłeczka38(z, n) =>
        println(self.path.name)

        if n > z.size - 2 then 
          z(0) ! Piłeczka38(z, 0)

        else z(n + 1) ! Piłeczka38(z, n + 1)
    }
  }



@main
def main: Unit = {
  val system = ActorSystem("halloAkka")

  val liczbaGraczy = 4

  val listaGraczy = (1 to liczbaGraczy).toList.map(n => system.actorOf(Props[Gracz38](), s"a$n")) 

  listaGraczy.head ! Graj38(listaGraczy, 0)
}

//   @main
//   def main: Unit = {
//     val system = ActorSystem("halloAkka")
//     try {
//       val a0 = system.actorOf(Props[Gracz38](), "a0")
//       a1 ! Graj38(a2)
//       println("enter")
//       io.StdIn.readLine()
//     } finally {
//       system.terminate()
//     }
//   }
// }
}