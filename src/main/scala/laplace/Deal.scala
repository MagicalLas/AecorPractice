package laplace

trait Deal[F[_]] {
  def place(money: Money): F[Unit]
}
