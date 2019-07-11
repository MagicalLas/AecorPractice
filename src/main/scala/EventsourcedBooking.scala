class EventsourcedBooking[F[_]](implicit F: MonadActionReject[F, Option[BookingState], BookingEvent, BookingCommandRejection]) extends Booking[F] {

  import F._

  def place(client: ClientId): F[Unit] = ???
}
