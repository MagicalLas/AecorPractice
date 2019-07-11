trait MonadActionReject[F[_], S, E, R] extends MonadAction[F, S, E] {
  def reject[A](r: R): F[A]
}