trait Booking[F[_]]{
  def place(client: ClientId): F[Unit]
}
