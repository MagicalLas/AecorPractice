package boking

import cats.data.NonEmptyList

sealed trait BookingEvent extends Product with Serializable

case class BookingPlaced(clientId: ClientId, seats: NonEmptyList[Seat]) extends BookingEvent

case class BookingConfirmed(tickets: NonEmptyList[Ticket]) extends BookingEvent

case object BookingSettled extends BookingEvent
