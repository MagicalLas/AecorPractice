package boking

import BookingStatus._
import BookingState._
import aecor.{MonadAction, MonadActionReject}
import aecor.data.{ActionT, EitherK, EventTag, EventsourcedBehavior, Folded, Tagging}
import aecor.encoding.{KeyDecoder, KeyEncoder}
import aecor.util.Clock
import cats.{Monad, Monoid}
import cats.data.{EitherT, NonEmptyList}
import cats.syntax.all._

class EventsourcedBooking[F[_] : Monad]
  extends Booking[
    EitherT[
      ActionT[F, Option[BookingState], BookingEvent, ?],
      BookingCommandRejection,
      ?]
    ] {

  def place(
             client: ClientId,
             seats: NonEmptyList[Seat]): EitherT[ActionT[F, Option[BookingState], BookingEvent, ?], BookingCommandRejection, Unit]

  def status: EitherT[ActionT[F, Option[BookingState], BookingEvent, ?], BookingCommandRejection, BookingStatus]
}