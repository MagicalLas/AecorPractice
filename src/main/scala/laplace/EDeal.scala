package laplace

import aecor.MonadActionReject
import aecor.data.{EitherK, EventsourcedBehavior}
import cats.Monad
import aecor.MonadActionReject
import aecor.data._
import cats.Monad

import DealState._


class EDeal[F[_]]
(implicit F: MonadActionReject[F, Option[DealState], DealEvent, DealCommandRejection]) extends Deal[F] {

  import F._

  def place(money: Money): F[Unit] = reject(DealErrorDefault)
}

object EDeal {

  def behavior[F[_] : Monad]: EBDeal[
    EitherK[Deal, DealCommandRejection, ?[_]],
    F,
    Option[DealState],
    DealEvent
    ] = EventsourcedBehavior
    .optionalRejectable(
      new EDeal(),
      DealState.init,
      _.handleEvent(_))
}