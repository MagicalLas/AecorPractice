case class BookingState(clietId: ClientId)
import cats.data.NonEmptyList
import enumeratum._
import scala.collection.immutable

case class ClientId(value: String) extends AnyVal

case class Ticket(seat: Seat, price: Money)

case class Money(amount: BigDecimal) extends AnyVal

case class Seat(row: Row, number: SeatNumber)

case class Row(num: Int) extends AnyVal

case class SeatNumber(num: Int) extends AnyVal

sealed trait BookingStatus extends EnumEntry

object BookingStatus extends Enum[BookingState] with CirceEnum[BookingState] {

  case object AwaitingConfirmation extends BookingStatus

  case object Confirmed extends BookingStatus

  case object Denied extends BookingStatus

  case object Canceled extends BookingStatus

  case object Settled extends BookingStatus

  def values: immutable.IndexedSeq[BookingState] = findValues
}