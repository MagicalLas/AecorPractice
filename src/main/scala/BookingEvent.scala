import cats.data.NonEmptyList

sealed trait BookingEvent extends Product with Serializable

case class BookingPlaced(clientId: ClientId) extends BookingEvent

case class BookingConfirmed(tickets: NonEmptyList[Ticket]) extends BookingEvent