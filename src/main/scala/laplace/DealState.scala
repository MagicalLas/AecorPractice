package laplace

import aecor.data.Folded
import aecor.data.Folded.syntax._
import cats.Monoid
import enumeratum.{CirceEnum, Enum, EnumEntry}

import scala.collection.immutable

case class DealState(money: Money) {
  def handleEvent(e: DealEvent): Folded[DealState] = e match {
    case a: DealPlace => copy(money = a.money).next
    case _ => impossible
  }
}

case class Money(double: Double) extends AnyVal
object Money {
  implicit val monoid: Monoid[Money] = new Monoid[Money] {
    def empty: Money = Money(0)
    def combine(x: Money, y: Money): Money = Money(x.double + y.double)
  }
}

object DealState {
  def init(e: DealEvent): Folded[DealState] = e match {
    case e: DealPlace =>
      DealState(e.money).next
    case _ => impossible
  }
}


sealed trait DealStatus extends EnumEntry

object DealStatus extends Enum[DealStatus] with CirceEnum[DealStatus] {

  case object Good extends DealStatus

  case object Bad extends DealStatus

  override def values: immutable.IndexedSeq[DealStatus] = findValues
}