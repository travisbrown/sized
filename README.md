# Sized collections for Scala 2.13 and Dotty

[![Build status](https://img.shields.io/travis/travisbrown/sized/master.svg)](https://travis-ci.org/travisbrown/sized)
[![Coverage status](https://img.shields.io/codecov/c/github/travisbrown/sized/master.svg)](https://codecov.io/github/travisbrown/sized)
[![Maven Central](https://img.shields.io/maven-central/v/dev.travisbrown/sized-core_2.13.svg)](https://maven-badges.herokuapp.com/maven-central/dev.travisbrown/sized-core_2.13)

## What is it?

This library provides a statically-sized wrapper for Scala 2.13's `IndexedSeq`:

```scala
scala> import dev.travisbrown.sized._

scala> val xs = SizedVector(1, 2, 3, 4, 5)
val xs: dev.travisbrown.sized.SizedVector[Int, 5] = SizedVector(1, 2, 3, 4, 5)

scala> xs.sizedReverse.sizedTail.sizedMap(_.toString)
val res0: dev.travisbrown.sized.SizedVector[String, 4] = SizedVector(4, 3, 2, 1)

scala> xs[4]
val res1: Int = 5

scala> xs.sizedConcat(xs.sizedReverse)
val res2: dev.travisbrown.sized.SizedVector[Int, 10] = SizedVector(1, 2, 3, 4, 5, 5, 4, 3, 2, 1)
```

All ordinary collections methods are available and return the underlying unsized type:

```scala
scala> xs.toVector
val res3: Vector[Int] = Vector(1, 2, 3, 4, 5)

scala> xs.take(100)
val res4: Vector[Int] = Vector(1, 2, 3, 4, 5)

scala> xs.map(_ * 1000)
val res5: Vector[Int] = Vector(1000, 2000, 3000, 4000, 5000)
```

Accessing out-of-bounds elements fails at compile time:

```scala
scala> xs[-1]
1 |xs[-1]
  |^^^^^^
  |Can't get element at a negative index

scala> xs[10]
1 |xs[10]
  |^^^^^^
  |Index out of bounds

scala> xs.sizedDrop[5].safeHead
1 |xs.sizedDrop[5].safeHead
  |^^^^^^^^^^^^^^^^^^^^^^^^
  |Can't take head of empty collection
```

This is all done without any macros (or custom type classes) on Dotty. Scala 2.13 requires a
(fairly minimal) `Pair` type class with a macro-powered materializer. Neither version has any
non-standard-library dependencies.

## Why?

I've been wanting an excuse to experiment with implementing a collection type with Scala 2.13's
[new collection library][scala-2.13-collections], and also an excuse to spend some time playing with
the [new type-level singleton operations][dotty-singleton-ops] in Dotty.

## Other questions

> What about Scala 2.12?

Nope.

> What about Scala.js?

PRs are open.

> What about other collection types?

PRs are open.

> Why not just use Shapeless's sized collections?

`Sized` in [Shapeless][shapeless] is great but I want to represent lengths with `Int` singletons, not `Nat`.

> Why not just use Refined?

`Size[Equal[N]]` in [Refined][refined] is also great, but not having support for collections in `refineMV`
means it can feel a little awkward to work with.

## Participation

This project supports the [Scala code of conduct][code-of-conduct] and wants
all of its channels (Gitter, GitHub, etc.) to be welcoming environments for everyone.

## License

This project is licensed under the **[Apache License, Version 2.0][apache]** (the
"License"); you may not use this software except in compliance with the License.

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

[apache]: http://www.apache.org/licenses/LICENSE-2.0
[code-of-conduct]: https://www.scala-lang.org/conduct/
[dotty-singleton-ops]: https://github.com/lampepfl/dotty/pull/7628
[refined]: https://github.com/fthomas/refined
[scala-2.13-collections]: https://www.scala-lang.org/blog/2018/06/13/scala-213-collections.html
[shapeless]: https://github.com/milessabin/shapeless