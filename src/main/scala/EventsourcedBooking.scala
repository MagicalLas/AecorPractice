import BookingStatus._
import BookingState._
import aecor.MonadActionReject
import cats.Monoid
import cats.data.NonEmptyList
import cats.syntax.all._


class EventsourcedBooking[F[_]](implicit F: MonadActionReject[F, Option[BookingState], BookingEvent, BookingCommandRejection]) extends Booking[F] {

  import F._

  val ignore: F[Unit] = F.unit

  implicit val tempMoneyMonoid: Monoid[Money] = new Monoid[Money] {
    override def empty: Money = ???

    override def combine(x: Money, y: Money): Money = ???
  }

  def place(client: ClientId, seats: NonEmptyList[Seat]): F[Unit] =
    read.flatMap {
      case Some(_) => reject(BookingAlreadyExists)
      case None => append(BookingPlaced(client, seats))
      case _ => reject(BookingErrorDefault)
    }

  def confirm(tickets: NonEmptyList[Ticket]): F[Unit] = state.flatMap {
    case AwaitingConfirmation => append(BookingConfirmed(tickets)) >>
      whenA(tickets.foldMap(_.price).amount <= 0)(append(BookingSettled))
    case Denied => reject(BookingErrorDefault)
    case Canceled => reject(BookingErrorDefault)
  }

  def state: F[BookingStatus] = read.flatMap {
    case Some(s) => pure(s.status)
    case _ => reject(BookingErrorDefault)
  }

}
