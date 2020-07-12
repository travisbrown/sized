package dev.travisbrown.sized

import scala.collection.immutable.IndexedSeqOps
import scala.compiletime.{constValue, error}
import scala.compiletime.ops.int._

trait SizedOps[+A, U[+x], N <: Int, C[+x, _ <: Int] <: IndexedSeqOps[x, U, U[x]]] {
  protected def coll: U[A]

  inline final def apply[X <: Int]: A = inline constValue[X] match {
    case x if x < 0 => error("Can't get element at a negative index")
    case x =>
      inline constValue[N] match {
        case n if x < n => unsizedApply(coll, x)
        case _ => error("Index out of bounds")
      }
  }

  inline final def safeHead: A = inline constValue[N] match {
    case n if n > 0 => unsizedHead(coll)
    case _ => error("Can't take head of empty collection")
  }

  inline final def safeLast: A = inline constValue[N] match {
    case n if n > 0 => unsizedLast(coll)
    case _ => error("Can't take head of empty collection")
  }

  inline final def sizedTail: C[A, N - 1] = inline constValue[N] match {
    case n if n > 0 => unsafeWrap(unsizedTail(coll))
    case _ => error("Can't take tail of empty collection")
  }

  inline final def sizedInit: C[A, N - 1] = inline constValue[N] match {
    case n if n > 0 => unsafeWrap(unsizedInit(coll))
    case _ => error("Can't take init of empty collection")
  }

  final def sizedAppended[B >: A](value: B): C[B, N + 1] = unsafeWrap(unsizedAppended(coll, value))
  final def sizedPrepended[B >: A](value: B): C[B, N + 1] = unsafeWrap(unsizedPrepended(coll, value))
  final def sizedConcat[B >: A, O <: Int](other: C[B, O]): C[B, N + O] = unsafeWrap(unsizedConcat(coll, other))

  inline final def sizedTake[X <: Int]: C[A, X] = inline constValue[X] match {
    case x if x < 0 => error("Can't take a negative number of elements")
    case x =>
      inline constValue[N] match {
        case n if x <= n => unsafeWrap(unsizedTake(coll, x))
        case _ => error("Can't take more elements than the size of the collection")
      }
  }

  inline final def sizedTakeRight[X <: Int]: C[A, X] = inline constValue[X] match {
    case x if x < 0 => error("Can't take a negative number of elements")
    case x =>
      inline constValue[N] match {
        case n if x <= n => unsafeWrap(unsizedTakeRight(coll, x))
        case _ => error("Can't take more elements than the size of the collection")
      }
  }

  inline final def sizedDrop[X <: Int]: C[A, N - X] = inline constValue[X] match {
    case x if x < 0 => error("Can't drop a negative number of elements")
    case x =>
      inline constValue[N] match {
        case n if x <= n => unsafeWrap(unsizedDrop(coll, x))
        case _ => error("Can't drop more elements than the size of the collection")
      }
  }

  inline final def sizedDropRight[X <: Int]: C[A, N - X] = inline constValue[X] match {
    case x if x < 0 => error("Can't drop a negative number of elements")
    case x =>
      inline constValue[N] match {
        case n if x <= n => unsafeWrap(unsizedDropRight(coll, x))
        case _ => error("Can't drop more elements than the size of the collection")
      }
  }

  protected[this] def unsafeWrap[A, N <: Int](value: U[A]): C[A, N]
  protected[this] def unsizedApply[A](coll: U[A], n: Int): A
  protected[this] def unsizedHead[A](coll: U[A]): A
  protected[this] def unsizedLast[A](coll: U[A]): A
  protected[this] def unsizedTail[A](coll: U[A]): U[A]
  protected[this] def unsizedInit[A](coll: U[A]): U[A]
  protected[this] def unsizedAppended[A, B >: A](coll: U[A], value: B): U[B]
  protected[this] def unsizedPrepended[A, B >: A](coll: U[A], value: B): U[B]
  protected[this] def unsizedConcat[A, B >: A](coll: U[A], other: IndexedSeqOps[B, U, U[B]]): U[B]
  protected[this] def unsizedTake[A](coll: U[A], n: Int): U[A]
  protected[this] def unsizedTakeRight[A](coll: U[A], n: Int): U[A]
  protected[this] def unsizedDrop[A](coll: U[A], n: Int): U[A]
  protected[this] def unsizedDropRight[A](coll: U[A], n: Int): U[A]
}

private[sized] trait SizedOpsHelpers[U[+x] <: IndexedSeq[x] with IndexedSeqOps[x, U, U[x]]] {
  // These methods are currently necessary to work around a Dotty bug.
  final protected[this] def unsizedApply[A](coll: U[A], n: Int): A = coll(n)
  final protected[this] def unsizedHead[A](coll: U[A]): A = coll.head
  final protected[this] def unsizedLast[A](coll: U[A]): A = coll.last
  final protected[this] def unsizedTail[A](coll: U[A]): U[A] = coll.tail
  final protected[this] def unsizedInit[A](coll: U[A]): U[A] = coll.init
  final protected[this] def unsizedAppended[A, B >: A](coll: U[A], value: B): U[B] = coll.appended(value)
  final protected[this] def unsizedPrepended[A, B >: A](coll: U[A], value: B): U[B] = coll.prepended(value)
  final protected[this] def unsizedConcat[A, B >: A](coll: U[A], other: IndexedSeqOps[B, U, U[B]]): U[B] = coll ++ other
  final protected[this] def unsizedTake[A](coll: U[A], n: Int): U[A] = coll.take(n)
  final protected[this] def unsizedTakeRight[A](coll: U[A], n: Int): U[A] = coll.takeRight(n)
  final protected[this] def unsizedDrop[A](coll: U[A], n: Int): U[A] = coll.drop(n)
  final protected[this] def unsizedDropRight[A](coll: U[A], n: Int): U[A] = coll.dropRight(n)
}
