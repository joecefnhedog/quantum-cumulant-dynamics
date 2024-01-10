package who.ate.all.the.pies

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import who.ate.all.the.pies.ExpandExpressions._
import who.ate.all.the.pies.SimplifyExpressions._

class ExpandExpressionsSpec extends AnyFlatSpec with Matchers {

  val testExprPlus1 = Plus(List(Const(1), SymbolType(A, 2), SymbolType(A, 3)))
  val testExprPlus2 = Plus(List(Const(4), SymbolType(A, 5), SymbolType(A, 6)))


  Mult(List(testExprPlus1, testExprPlus2)).expandFullMults.simplify shouldBe Plus(
    List(
      Mult(List(Const(1.0), Const(4.0))),
      Mult(List(Const(1.0), SymbolType(A, 5))),
      Mult(List(Const(1.0), SymbolType(A, 6))),
      Mult(List(SymbolType(A, 2), Const(4.0))),
      Mult(List(SymbolType(A, 2), SymbolType(A, 5))),
      Mult(List(SymbolType(A, 2), SymbolType(A, 6))),
      Mult(List(SymbolType(A, 3), Const(4.0))),
      Mult(List(SymbolType(A, 3), SymbolType(A, 5))),
      Mult(List(SymbolType(A, 3), SymbolType(A, 6)))
    )
  )

}
