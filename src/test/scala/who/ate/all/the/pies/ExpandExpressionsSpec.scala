package who.ate.all.the.pies

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import who.ate.all.the.pies.ExpandExpressions._
import who.ate.all.the.pies.SimplifyExpressions._

class ExpandExpressionsSpec extends AnyFlatSpec with Matchers {

  val testExprPlus1 = Plus(List(Const(9), SymbolType('A, 2), SymbolType('A, 3)))
  val testExprPlus2 = Plus(List(Const(4), SymbolType('A, 5), SymbolType('A, 6)))

  Mult(
    testExprPlus1, testExprPlus2
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
