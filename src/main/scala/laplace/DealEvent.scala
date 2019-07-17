package laplace

sealed trait DealEvent extends Product with Serializable

case class DealPlace(money: Money) extends DealEvent
