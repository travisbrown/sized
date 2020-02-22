package dev.travisbrown.sized

import scala.collection.SeqFactory.Delegate

final class SizedVector[+A, N <: Int] private (protected val coll: Vector[A])
    extends SizedIndexedSeqOps[A, Vector, N, SizedVector] {
  def iterableFactory: SizedIndexedSeqFactory[Vector, SizedVector] = SizedVector
}

object SizedVector extends Delegate(Vector) with SizedIndexedSeqFactory[Vector, SizedVector] {
  private[sized] def unsafeWrap[A, N <: Int](value: Vector[A]): SizedVector[A, N] = new SizedVector[A, N](value)
}
