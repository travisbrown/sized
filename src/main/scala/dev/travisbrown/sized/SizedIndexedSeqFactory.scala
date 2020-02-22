package dev.travisbrown.sized

import scala.collection.StrictOptimizedSeqFactory
import scala.collection.immutable.IndexedSeqOps

trait SizedIndexedSeqFactory[U[+x] <: IndexedSeq[x] with IndexedSeqOps[x, U, U[x]], C[+_, _ <: Int]]
    extends StrictOptimizedSeqFactory[U] {
  private[sized] def unsafeWrap[A, N <: Int](value: U[A]): C[A, N]

  final def sizedEmpty[A]: C[A, 0] = unsafeWrap(empty)

  final def wrap[N <: Int]: PartiallyAppliedWrap[N] = new PartiallyAppliedWrap[N]

  final class PartiallyAppliedWrap[N <: Int] {
    def apply[A](value: U[A])(implicit N: ValueOf[N]): Option[C[A, N]] =
      if (value.length == N.value) Some(unsafeWrap(value)) else None
  }

  private[this] def unsafeApply[A, N <: Int](values: A*): C[A, N] = unsafeWrap {
    val builder = newBuilder[A]
    builder ++= values
    builder.result()
  }

  // format: off
  final def apply[A](): C[A, 0] = sizedEmpty
  final def apply[A](a0: A): C[A, 1] = unsafeApply(a0)
  final def apply[A](a0: A, a1: A): C[A, 2] = unsafeApply(a0, a1)
  final def apply[A](a0: A, a1: A, a2: A): C[A, 3] = unsafeApply(a0, a1, a2)
  final def apply[A](a0: A, a1: A, a2: A, a3: A): C[A, 4] = unsafeApply(a0, a1, a2, a3)
  final def apply[A](a0: A, a1: A, a2: A, a3: A, a4: A): C[A, 5] = unsafeApply(a0, a1, a2, a3, a4)
  final def apply[A](a0: A, a1: A, a2: A, a3: A, a4: A, a5: A): C[A, 6] = unsafeApply(a0, a1, a2, a3, a4, a5)
  final def apply[A](a0: A, a1: A, a2: A, a3: A, a4: A, a5: A, a6: A): C[A, 7] = unsafeApply(a0, a1, a2, a3, a4, a5, a6)
  final def apply[A](a0: A, a1: A, a2: A, a3: A, a4: A, a5: A, a6: A, a7: A): C[A, 8] = unsafeApply(a0, a1, a2, a3, a4, a5, a6, a7)
  final def apply[A](a0: A, a1: A, a2: A, a3: A, a4: A, a5: A, a6: A, a7: A, a8: A): C[A, 9] = unsafeApply(a0, a1, a2, a3, a4, a5, a6, a7, a8)
  final def apply[A](a0: A, a1: A, a2: A, a3: A, a4: A, a5: A, a6: A, a7: A, a8: A, a9: A): C[A, 10] = unsafeApply(a0, a1, a2, a3, a4, a5, a6, a7, a8, a9)
  final def apply[A](a0: A, a1: A, a2: A, a3: A, a4: A, a5: A, a6: A, a7: A, a8: A, a9: A, a10: A): C[A, 11] = unsafeApply(a0, a1, a2, a3, a4, a5, a6, a7, a8, a9, a10)
  final def apply[A](a0: A, a1: A, a2: A, a3: A, a4: A, a5: A, a6: A, a7: A, a8: A, a9: A, a10: A, a11: A): C[A, 12] = unsafeApply(a0, a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11)
  final def apply[A](a0: A, a1: A, a2: A, a3: A, a4: A, a5: A, a6: A, a7: A, a8: A, a9: A, a10: A, a11: A, a12: A): C[A, 13] = unsafeApply(a0, a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12)
  final def apply[A](a0: A, a1: A, a2: A, a3: A, a4: A, a5: A, a6: A, a7: A, a8: A, a9: A, a10: A, a11: A, a12: A, a13: A): C[A, 14] = unsafeApply(a0, a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13)
  final def apply[A](a0: A, a1: A, a2: A, a3: A, a4: A, a5: A, a6: A, a7: A, a8: A, a9: A, a10: A, a11: A, a12: A, a13: A, a14: A): C[A, 15] = unsafeApply(a0, a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14)
  final def apply[A]( a0: A, a1: A, a2: A, a3: A, a4: A, a5: A, a6: A, a7: A, a8: A, a9: A, a10: A, a11: A, a12: A, a13: A, a14: A, a15: A): C[A, 16] = unsafeApply(a0, a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15)
  final def apply[A]( a0: A, a1: A, a2: A, a3: A, a4: A, a5: A, a6: A, a7: A, a8: A, a9: A, a10: A, a11: A, a12: A, a13: A, a14: A, a15: A, a16: A): C[A, 17] = unsafeApply(a0, a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15, a16)
  final def apply[A]( a0: A, a1: A, a2: A, a3: A, a4: A, a5: A, a6: A, a7: A, a8: A, a9: A, a10: A, a11: A, a12: A, a13: A, a14: A, a15: A, a16: A, a17: A): C[A, 18] = unsafeApply(a0, a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15, a16, a17)
  final def apply[A]( a0: A, a1: A, a2: A, a3: A, a4: A, a5: A, a6: A, a7: A, a8: A, a9: A, a10: A, a11: A, a12: A, a13: A, a14: A, a15: A, a16: A, a17: A, a18: A): C[A, 19] = unsafeApply(a0, a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15, a16, a17, a18)
  final def apply[A]( a0: A, a1: A, a2: A, a3: A, a4: A, a5: A, a6: A, a7: A, a8: A, a9: A, a10: A, a11: A, a12: A, a13: A, a14: A, a15: A, a16: A, a17: A, a18: A, a19: A): C[A, 20] = unsafeApply(a0, a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15, a16, a17, a18, a19)
  final def apply[A]( a0: A, a1: A, a2: A, a3: A, a4: A, a5: A, a6: A, a7: A, a8: A, a9: A, a10: A, a11: A, a12: A, a13: A, a14: A, a15: A, a16: A, a17: A, a18: A, a19: A, a20: A): C[A, 21] = unsafeApply(a0, a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15, a16, a17, a18, a19, a20)
  final def apply[A](a0: A, a1: A, a2: A, a3: A, a4: A, a5: A, a6: A, a7: A, a8: A, a9: A, a10: A, a11: A, a12: A, a13: A, a14: A, a15: A, a16: A, a17: A, a18: A, a19: A, a20: A, a21: A): C[A, 22] = unsafeApply(a0, a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15, a16, a17, a18, a19, a20, a21)
  // format: on
}
