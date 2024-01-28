package who.ate.all.the.pies.operations

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import who.ate.all.the.pies.expression.{Const, Mult, Plus, SymbolType}
import who.ate.all.the.pies.operations.ExpandExpressions._
import who.ate.all.the.pies.operations.SimplifyExpressions._

class ExpandExpressionsSpec extends AnyFlatSpec with Matchers {

  "expandFullMults" should "expand out an expression" in {

    val testExprPlus1 =
      Plus(List(Const(9), SymbolType('A, 2), SymbolType('A, 3)))
    val testExprPlus2 =
      Plus(List(Const(4), SymbolType('A, 5), SymbolType('A, 6)))

    Mult(
      testExprPlus1,
      testExprPlus2
    ).expandFullMults.simplify shouldBe Plus(
      Mult(Const(9.0), Const(4.0)),
      Mult(Const(9.0), SymbolType('A, 5)),
      Mult(Const(9.0), SymbolType('A, 6)),
      Mult(SymbolType('A, 2), Const(4.0)),
      Mult(SymbolType('A, 2), SymbolType('A, 5)),
      Mult(SymbolType('A, 2), SymbolType('A, 6)),
      Mult(SymbolType('A, 3), Const(4.0)),
      Mult(SymbolType('A, 3), SymbolType('A, 5)),
      Mult(SymbolType('A, 3), SymbolType('A, 6))
    )
  }

  it should "expand plus terms nested within two mults" in {

    val test2 = Plus(
      List(
        Mult(
          Mult(
            Const(-1.0),
            Plus(
              List(
                Mult(SymbolType(Symbol("P"), 1), SymbolType(Symbol("P"), 1)),
                Mult(SymbolType(Symbol("Q"), 1), SymbolType(Symbol("Q"), 1))
              )
            )
          ),
          SymbolType(Symbol("Q"), 1)
        )
      )
    )

    val test2Exp = Plus(
      List(
        Mult(
          Const(-1.0),
          Mult(
            SymbolType(Symbol("P"), 1),
            Mult(SymbolType(Symbol("P"), 1), SymbolType(Symbol("Q"), 1))
          )
        ),
        Mult(
          Const(-1.0),
          Mult(
            SymbolType(Symbol("Q"), 1),
            Mult(SymbolType(Symbol("Q"), 1), SymbolType(Symbol("Q"), 1))
          )
        )
      )
    )

    test2.expandFullMults.simplify shouldBe test2Exp

  }

  it should "expand mult terms to be of the correct form" in {
    val test3Expr = Mult(
      Mult(Const(-1.0), SymbolType(Symbol("Q"), 1)),
      Mult(SymbolType(Symbol("P"), 1), SymbolType(Symbol("P"), 1))
    )

    val test3ns = Mult(
      Const(-1.0),
      Mult(
        SymbolType(Symbol("Q"), 1),
        Mult(SymbolType(Symbol("P"), 1), SymbolType(Symbol("P"), 1))
      )
    )

    test3Expr.expandFullMults.simplify shouldBe test3ns
  }
}
