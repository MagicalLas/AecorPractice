package laplace

sealed trait DealCommandRejection

case object DealErrorDefault extends DealCommandRejection