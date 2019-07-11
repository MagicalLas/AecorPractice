import BookingStatus._
import aecor.MonadActionReject
import cats.data.NonEmptyList
import cats.implicits._

class EventsourcedBooking[F[_]](implicit F: MonadActionReject[F, Option[BookingState], BookingEvent, BookingCommandRejection]) extends Booking[F] {

  import F._

  def place(client: ClientId): F[Unit] = read.flatMap {
    case Some(_) => reject(BookingAlreadyExists)
    case None => append(BookingPlaced(client))
    case _ => reject(BookingErrorDefault)
  }

  def confirm(tickets: NonEmptyList[Ticket]): F[Unit] = state.flatMap{
    case AwaitingConfirmation => append(BookingConfirmed(tickets))
    case Denied              => reject(BookingErrorDefault)
    case Canceled            => reject(BookingErrorDefault)
  }
  def state: F[BookingStatus] = read.flatMap {
    case Some(s) => pure(s.status)
    case _       => reject(BookingErrorDefault)
  }
}
