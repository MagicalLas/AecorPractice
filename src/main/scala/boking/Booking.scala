package boking

import aecor.MonadAction
import aecor.data.ActionT
import cats.Monad
import cats.data.NonEmptyList

trait Booking[F[_]] {
  def place(client: ClientId, seats: NonEmptyList[Seat]): F[Unit]

  def confirm(ticket: NonEmptyList[Ticket]): F[Unit]
}

