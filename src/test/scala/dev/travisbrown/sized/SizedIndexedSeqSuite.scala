package dev.travisbrown.sized

import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.scalacheck.ScalaCheckDrivenPropertyChecks
import scala.collection.immutable.ArraySeq

class SizedIndexedSeqSuite extends AnyFunSuite with ScalaCheckDrivenPropertyChecks {
  test("SizedVector.wrap should only succeed if the input has the right length") {
    assert(SizedVector.wrap[4](Vector(1, 2, 3, 4)).map(_.unsized) === Some(Vector(1, 2, 3, 4)))

    forAll { (wrapped: Vector[String]) =>
      val expected = if (wrapped.length == 1) Some(wrapped) else None
      assert(SizedVector.wrap[1](wrapped).map(_.unsized) === expected)
    }
  }

  test("SizedArraySeq.wrap should only succeed if the input has the right length") {
    assert(SizedArraySeq.wrap[4](ArraySeq(1, 2, 3, 4)).map(_.unsized) === Some(ArraySeq(1, 2, 3, 4)))

    forAll { (wrapped: ArraySeq[String]) =>
      val expected = if (wrapped.length == 1) Some(wrapped) else None
      assert(SizedArraySeq.wrap[1](wrapped).map(_.unsized) === expected)
    }
  }

  test("sizedEmpty should return an empty collection") {
    val e0: SizedVector[String, 0] = SizedVector.sizedEmpty[String]
    val e1: SizedArraySeq[String, 0] = SizedArraySeq.sizedEmpty[String]

    assert(e0.unsized === Vector.empty[String])
    assert(e1.unsized === ArraySeq.empty[String])
  }

  // Sorry I got lazy.
  test("sized methods should work") {
    val xs0: SizedVector[String, 0] = SizedVector.sizedEmpty[String]
    val xs1: SizedVector[String, 3] = SizedVector("a", "b", "c")

    assert(xs1[2] === xs1(2))
    assert(xs1.safeHead === xs1.head)
    assert(xs1.safeLast === xs1.last)
    assert(xs1.sizedTail.unsized === xs1.tail)
    assert(xs1.sizedInit.unsized === xs1.init)

    val result0: SizedVector[String, 3] = xs0.sizedConcat(xs1)
    val result1: SizedVector[String, 6] = xs1.sizedConcat(xs1)
    val result2: SizedVector[String, 2] = xs1.sizedTake[2]
    val result3: SizedVector[String, 2] = xs1.sizedDrop[1]
    val result4: SizedVector[String, 1] = xs1.sizedTakeRight[1]
    val result5: SizedVector[String, 1] = xs1.sizedDropRight[2]
    val result6: SizedVector[Int, 3] = xs1.sizedMap(_.length)
    val result7: SizedVector[String, 4] = xs1.sizedAppended("z")
    val result8: SizedVector[String, 4] = xs1.sizedPrepended("z")
    val result9: SizedVector[String, 3] = xs1.sizedReverse

    assert(result0.unsized === (xs0 ++ xs1))
    assert(result1.unsized === (xs1 ++ xs1))
    assert(result2.unsized === xs1.take(2))
    assert(result3.unsized === xs1.drop(1))
    assert(result4.unsized === xs1.takeRight(1))
    assert(result5.unsized === xs1.dropRight(2))
    assert(result6.unsized === xs1.map(_.length))
    assert(result7.unsized === (xs1 :+ "z"))
    assert(result8.unsized === ("z" +: xs1))
    assert(result9.unsized === xs1.reverse)
  }

  test("Sized apply methods should work") {
    val xs0: SizedVector[Int, 0] = SizedVector()
    val xs1: SizedVector[Int, 1] = SizedVector(1)
    val xs2: SizedVector[Int, 2] = SizedVector(1, 1)
    val xs3: SizedVector[Int, 3] = SizedVector(1, 1, 1)
    val xs4: SizedVector[Int, 4] = SizedVector(1, 1, 1, 1)
    val xs5: SizedVector[Int, 5] = SizedVector(1, 1, 1, 1, 1)
    val xs6: SizedVector[Int, 6] = SizedVector(1, 1, 1, 1, 1, 1)
    val xs7: SizedVector[Int, 7] = SizedVector(1, 1, 1, 1, 1, 1, 1)
    val xs8: SizedVector[Int, 8] = SizedVector(1, 1, 1, 1, 1, 1, 1, 1)
    val xs9: SizedVector[Int, 9] = SizedVector(1, 1, 1, 1, 1, 1, 1, 1, 1)
    val xs10: SizedVector[Int, 10] = SizedVector(1, 1, 1, 1, 1, 1, 1, 1, 1, 1)
    val xs11: SizedVector[Int, 11] = SizedVector(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1)
    val xs12: SizedVector[Int, 12] = SizedVector(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1)
    val xs13: SizedVector[Int, 13] = SizedVector(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1)
    val xs14: SizedVector[Int, 14] = SizedVector(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1)
    val xs15: SizedVector[Int, 15] = SizedVector(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1)
    val xs16: SizedVector[Int, 16] = SizedVector(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1)
    val xs17: SizedVector[Int, 17] = SizedVector(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1)
    val xs18: SizedVector[Int, 18] = SizedVector(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1)
    val xs19: SizedVector[Int, 19] = SizedVector(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1)
    val xs20: SizedVector[Int, 20] = SizedVector(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1)
    val xs21: SizedVector[Int, 21] = SizedVector(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1)
    val xs22: SizedVector[Int, 22] = SizedVector(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1)

    assert(xs0.unsized === Vector.fill(0)(1))
    assert(xs1.unsized === Vector.fill(1)(1))
    assert(xs2.unsized === Vector.fill(2)(1))
    assert(xs3.unsized === Vector.fill(3)(1))
    assert(xs4.unsized === Vector.fill(4)(1))
    assert(xs5.unsized === Vector.fill(5)(1))
    assert(xs6.unsized === Vector.fill(6)(1))
    assert(xs7.unsized === Vector.fill(7)(1))
    assert(xs8.unsized === Vector.fill(8)(1))
    assert(xs9.unsized === Vector.fill(9)(1))
    assert(xs10.unsized === Vector.fill(10)(1))
    assert(xs11.unsized === Vector.fill(11)(1))
    assert(xs12.unsized === Vector.fill(12)(1))
    assert(xs13.unsized === Vector.fill(13)(1))
    assert(xs14.unsized === Vector.fill(14)(1))
    assert(xs15.unsized === Vector.fill(15)(1))
    assert(xs16.unsized === Vector.fill(16)(1))
    assert(xs17.unsized === Vector.fill(17)(1))
    assert(xs18.unsized === Vector.fill(18)(1))
    assert(xs19.unsized === Vector.fill(19)(1))
    assert(xs20.unsized === Vector.fill(20)(1))
    assert(xs21.unsized === Vector.fill(21)(1))
    assert(xs22.unsized === Vector.fill(22)(1))
  }
}
