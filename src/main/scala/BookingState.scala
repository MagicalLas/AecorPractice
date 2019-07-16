import aecor.data.Folded
import aecor.data.Folded.syntax._
import cats.data.NonEmptyList
import enumeratum._

import scala.collection.immutable

case class BookingState(clietId: ClientId,
                        seats: NonEmptyList[Seat],
                        tickets: Option[NonEmptyList[Ticket]],
                        status: BookingStatus) {

  def handleEvent(e: BookingEvent): Folded[BookingState] = e match {
    case _: BookingPlaced => impossible
    case e: BookingConfirmed => copy(
      tickets = Some(e.tickets),
      status = BookingStatus.Confirmed
    ).next
  }
}


case class ClientId(value: String) extends AnyVal

case class Ticket(seat: Seat, price: Money)

case class Money(amount: BigDecimal) extends AnyVal

case class Seat(row: Row, number: SeatNumber)

case class Row(num: Int) extends AnyVal

case class SeatNumber(num: Int) extends AnyVal


object BookingState {

  def init(e: BookingEvent): Folded[BookingState] = e match {
    case e: BookingPlaced =>
      BookingState(e.clientId, e.seats, None, BookingStatus.AwaitingConfirmation).next
    case _ => impossible
  }
}


sealed trait BookingStatus extends EnumEntry

object BookingStatus extends Enum[BookingStatus] with CirceEnum[BookingStatus] {

  case object AwaitingConfirmation extends BookingStatus

  case object Confirmed extends BookingStatus

  case object Denied extends BookingStatus

  case object Canceled extends BookingStatus

  case object Settled extends BookingStatus

  def values: immutable.IndexedSeq[BookingStatus] = findValues
}
