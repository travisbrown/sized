package dev.travisbrown.sized

import scala.annotation.unchecked.uncheckedVariance
import scala.collection.immutable.{IndexedSeqOps, StrictOptimizedSeqOps}
import scala.collection.mutable.Builder

trait SizedIndexedSeqOps[+A, U[+x] <: IndexedSeq[x] with IndexedSeqOps[x, U, U[x]], N <: Int, C[+x, _ <: Int] <: IndexedSeqOps[
  x,
  U,
  U[x]
]] extends IndexedSeqOps[A, U, U[A]]
    with StrictOptimizedSeqOps[A, U, U[A]]
    with SizedOps[A, U, N, C]
    with SizedOpsHelpers[U] {
  override def iterableFactory: SizedIndexedSeqFactory[U @uncheckedVariance, C]

  final protected def fromSpecific(coll: IterableOnce[A @uncheckedVariance]): U[A] = coll.iterator.to(iterableFactory)
  final protected def newSpecificBuilder: Builder[A @uncheckedVariance, U[A]] = iterableFactory.newBuilder[A]
  final def toIterable: Iterable[A] = coll.toIterable
  final def apply(i: Int): A = coll(i)
  final def length: Int = coll.length

  final def unsized: U[A] = coll

  final def sizedMap[B](f: A => B): C[B, N] = unsafeWrap(coll.map(f))
  final def sizedReverse: C[A, N] = unsafeWrap(coll.reverse)

  override def toString: String = s"Sized${coll.toString}"

  final protected[this] def unsafeWrap[A, N <: Int](value: U[A]): C[A, N] =
    iterableFactory.unsafeWrap(value.to(iterableFactory))
}
