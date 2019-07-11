import cats.Monad

trait MonadAction[F[_], S, E] extends Monad[F] {
  def read: F[S]
  def append(es: E, other: E*): F[Unit]
  def reset: F[Unit]
}
