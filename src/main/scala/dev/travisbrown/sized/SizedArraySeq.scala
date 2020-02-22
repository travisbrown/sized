package dev.travisbrown.sized

import scala.collection.SeqFactory.Delegate
import scala.collection.immutable.ArraySeq

final class SizedArraySeq[+A, N <: Int] private (protected val coll: ArraySeq[A])
    extends SizedIndexedSeqOps[A, ArraySeq, N, SizedArraySeq] {
  def iterableFactory: SizedIndexedSeqFactory[ArraySeq, SizedArraySeq] = SizedArraySeq
}

object SizedArraySeq extends Delegate(ArraySeq.untagged) with SizedIndexedSeqFactory[ArraySeq, SizedArraySeq] {
  private[sized] def unsafeWrap[A, N <: Int](value: ArraySeq[A]): SizedArraySeq[A, N] = new SizedArraySeq[A, N](value)
}
