package dev.travisbrown.sized

import scala.collection.immutable.IndexedSeqOps

trait SizedOps[+A, U[+x] <: IndexedSeq[x] with IndexedSeqOps[x, U, U[x]], N <: Int, C[+x, _ <: Int] <: IndexedSeqOps[
  x,
  U,
  U[x]
]] {
  final def apply[X <: Int](implicit X: ValueOf[X], ev0: Pair.LtEq[0, X], ev1: Pair.Lt[X, N]): A = coll(X.value)
  final def safeHead(implicit ev: Pair.LtEq[1, N]): A = coll.head
  final def safeLast(implicit ev: Pair.LtEq[1, N]): A = coll.last
  final def sizedTail(implicit ev: Pair.LtEq[1, N]): C[A, ev.Diff] = unsafeWrap(coll.tail)
  final def sizedInit(implicit ev: Pair.LtEq[1, N]): C[A, ev.Diff] = unsafeWrap(coll.init)
  final def sizedAppended[B >: A](value: B)(implicit ev: Pair[1, N]): C[B, ev.Sum] = unsafeWrap(coll.appended(value))
  final def sizedPrepended[B >: A](value: B)(implicit ev: Pair[1, N]): C[B, ev.Sum] = unsafeWrap(coll.prepended(value))
  final def sizedConcat[B >: A, O <: Int](other: C[B, O])(implicit ev: Pair[N, O]): C[B, ev.Sum] = unsafeWrap(
    coll ++ other
  )

  final def sizedTake[X <: Int](implicit X: ValueOf[X], ev: Pair.LtEq[X, N]): C[A, X] = unsafeWrap(coll.take(X.value))
  final def sizedTakeRight[X <: Int](implicit X: ValueOf[X], ev: Pair.LtEq[X, N]): C[A, X] = unsafeWrap(
    coll.takeRight(X.value)
  )
  final def sizedDrop[X <: Int](implicit X: ValueOf[X], ev: Pair.LtEq[X, N]): C[A, ev.Diff] = unsafeWrap(
    coll.drop(X.value)
  )
  final def sizedDropRight[X <: Int](implicit X: ValueOf[X], ev: Pair.LtEq[X, N]): C[A, ev.Diff] = unsafeWrap(
    coll.dropRight(X.value)
  )

  protected def coll: U[A]
  protected[this] def unsafeWrap[A, N <: Int](value: U[A]): C[A, N]
}

private[sized] trait SizedOpsHelpers[U[+x] <: IndexedSeq[x] with IndexedSeqOps[x, U, U[x]]]
