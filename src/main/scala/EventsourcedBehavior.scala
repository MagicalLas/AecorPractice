package boking

import aecor.data.{ActionT, Folded}

final case class EventsourcedBehavior
[M[_[_]], F[_], S, E](
                       actions: M[ActionT[F, S, E, ?]],
                       create: S,
                       update: (S, E) => Folded[S])