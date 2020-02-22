package dev.travisbrown.sized

import scala.language.experimental.macros

sealed class Pair[A <: Int, B <: Int] {
  type Lt <: Boolean
  type LtEq <: Boolean
  type Diff <: Int
  type Sum <: Int
}

object Pair {
  type Lt[A <: Int, B <: Int] = Pair[A, B] { type Lt = true }
  type LtEq[A <: Int, B <: Int] = Pair[A, B] { type LtEq = true }

  val singleton: Pair[Nothing, Nothing] = new Pair[Nothing, Nothing]

  implicit def materialize[A <: Int, B <: Int, Lt0 <: Boolean, LtEq0 <: Boolean, Diff0 <: Int, Sum0 <: Int]: Pair[A, B] {
    type Lt = Lt0
    type LtEq = LtEq0
    type Diff = Diff0
    type Sum = Sum0
  } = macro PairMacros.materialize[A, B]
}
