import aecor.MonadActionReject
import cats.implicits._

class EventsourcedBooking[F[_]](implicit F: MonadActionReject[F, Option[BookingState], BookingEvent, BookingCommandRejection]) extends Booking[F] {

  import F._

  def place(client: ClientId): F[Unit] = read.flatMap {
    case Some(_) => reject(BookingAlreadyExists)
    case None => append(BookingPlaced(client))
    case _ => reject(BookingErrorDefault)
  }
}
