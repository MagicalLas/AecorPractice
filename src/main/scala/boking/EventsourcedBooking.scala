package boking

import aecor.MonadActionReject
import cats.data.NonEmptyList

class EventsourcedBooking[F[_]]
(implicit F: MonadActionReject[F, Option[BookingState], BookingEvent, BookingCommandRejection])
  extends Booking[F] {
  def place(client: ClientId, seats: NonEmptyList[Seat]): F[Unit] = ???

  def confirm(ticket: NonEmptyList[Ticket]): F[Unit] = ???
}