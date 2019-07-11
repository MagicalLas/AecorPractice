sealed trait BookingEvent extends Product with Serializable

case class BookingPlaced(clientId: ClientId) extends BookingEvent