package who.ate.all.the.pies

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import who.ate.all.the.pies.Substitution._

class SubstitutionSpec extends AnyFlatSpec with Matchers {
  // Assume your Expr types and case classes are defined appropriately

  "Substitute" should "replace occurrences in a simple expression" in {
    val expr = Mult(SymbolType('A, 2), Const(4.0))
    val subExpr = SymbolType('A, 2)
    val replacementExpr = Const(10.0)

    val result = expr.substitute(subExpr, replacementExpr)

    result shouldEqual Mult(Const(10.0), Const(4.0))
  }

  it should "replace a Mult occurrence in an expression" in {
    val expr = Plus(Mult(Const(9.0), Const(4.0)))
    val subExpr = Const(9.0) * Const(4.0)
    val replacementExpr = Plus(Mult(Const(18.0), Const(8.0)))
    val result = expr.substitute(subExpr, replacementExpr)

    result shouldEqual Plus(Plus(Mult(Const(18.0), Const(8.0))))

  }

  it should "replace a Mult occurrence in an expression with additional terms" in {
    val expr = Plus(Mult(Const(9.0), Const(4.0)), Mult(Const(2.1), Const(2.2)))
    val subExpr = Mult(Const(9.0), Const(4.0))
    val replacementExpr = Plus(Mult(Const(18.0), Const(8.0)))
    val result = expr.substitute(subExpr, replacementExpr)

    result shouldEqual Plus(
      Plus(Mult(Const(18.0), Const(8.0))),
      Mult(Const(2.1), Const(2.2))
    )

  }

  it should "replace a Mult occurrence in an expression with multiple Mults" in {
    val expr = Const(1) * Const(2) * Const(3)
    val subExpr = Const(1) * Const(2)
    val replacementExpr = Const(11) * Const(22)
    val result = expr.substitute(subExpr, replacementExpr)

    result shouldEqual Const(11) * Const(22) * Const(3)

  }



}
