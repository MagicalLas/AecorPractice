import cats.data.NonEmptyList

trait Booking[F[_]]{
  def place(client: ClientId): F[Unit]
  def confirm(ticket: NonEmptyList[Ticket]): F[Unit]
}
