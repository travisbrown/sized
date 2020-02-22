package dev.travisbrown.sized

import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.scalacheck.ScalaCheckDrivenPropertyChecks

/**
 * Only for tests that won't pass on Dotty because of ScalaTest bugs.
 */
class SizedIndexedSeq213Suite extends AnyFunSuite with ScalaCheckDrivenPropertyChecks {
  test("Negative indexing should not compile") {
    val xs = SizedVector(1, 2, 3)
    assertCompiles("xs[2]")
    assertDoesNotCompile("xs[-1]")
  }

  test("Out-of-bounds indexing should not compile") {
    val xs = SizedVector(1, 2, 3)
    assertCompiles("xs[2]")
    assertDoesNotCompile("xs[3]")
  }

  test("safe head, last, tail, and init on an empty collection should not compile") {
    val xs0 = SizedVector.sizedEmpty[String]
    val xs1 = SizedVector("a", "b", "c")
    assertDoesNotCompile("xs0.safeHead")
    assertDoesNotCompile("xs0.safeLast")
    assertDoesNotCompile("xs0.sizedTail")
    assertDoesNotCompile("xs0.sizedInit")
    assertCompiles("xs1.safeHead")
    assertCompiles("xs1.safeLast")
    assertCompiles("xs1.sizedTail")
    assertCompiles("xs1.sizedInit")
  }

  test("Out-of-bounds take and drop should not compile") {
    val xs0 = SizedVector("a", "b", "c")
    val xs1 = SizedVector("a", "b", "c", "d")
    assertDoesNotCompile("xs0.sizedTake[4]")
    assertDoesNotCompile("xs0.sizedTakeRight[4]")
    assertDoesNotCompile("xs0.sizedDrop[4]")
    assertDoesNotCompile("xs0.sizedDropRight[4]")
    assertCompiles("xs1.sizedTake[4]")
    assertCompiles("xs1.sizedTakeRight[4]")
    assertCompiles("xs1.sizedDrop[4]")
    assertCompiles("xs1.sizedDropRight[4]")
  }
}
