case class BookingState(clietId: ClientId)

case class ClientId(value: String) extends AnyVal

case class Ticket(seat: Seat, price: Money)

case class Money(amount: BigDecimal) extends AnyVal

case class Seat(row: Row, number: SeatNumber)

case class Row(num: Int) extends AnyVal

case class SeatNumber(num: Int) extends AnyVal
