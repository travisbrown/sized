package dev.travisbrown.sized

import scala.reflect.macros.whitebox.Context

class PairMacros(val c: Context) {
  import c.universe._

  final def materialize[A <: Int: WeakTypeTag, B <: Int: WeakTypeTag]: Tree =
    (c.weakTypeOf[A].dealias, c.weakTypeOf[B].dealias) match {
      case (aType @ ConstantType(Constant(a: Int)), bType @ ConstantType(Constant(b: Int))) =>
        q"""
          _root_.dev.travisbrown.sized.Pair.singleton.asInstanceOf[
            _root_.dev.travisbrown.sized.Pair[$aType, $bType] {
              type Lt = ${Constant(a < b)}
              type LtEq = ${Constant(a <= b)}
              type Diff = ${Constant(b - a)}
              type Sum = ${Constant(a + b)}
            }
          ]
        """
    }
}
