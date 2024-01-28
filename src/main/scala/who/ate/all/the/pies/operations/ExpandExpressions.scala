package who.ate.all.the.pies.operations

import who.ate.all.the.pies.expression.{Const, Expr, Mult, Plus}

object ExpandExpressions {

  implicit class Expand(expression: Expr) {
    def expandFullMults: Expr =
      _expandFullMults(expression, Const(0))
  }

  private def _expandFullMults(cur: Expr, last: Expr): Expr =
    if (cur == last) cur
    else {
      val curExpanded = expand(cur)
      _expandFullMults(curExpanded, cur)
    }

  private def expand(expr: Expr): Expr = {
    expr match {
      case Mult(Plus(xs), expr) => Plus(xs.map(x => Mult(x, expr)))
      case Mult(expr, Plus(ys)) => Plus(ys.map(y => Mult( expr,y)))
      case Plus(expressions) => Plus(expressions.map(expand))
      case Mult(Mult(exprL, exprR),exprRChild) => Mult(exprL,Mult(exprR,exprRChild))
      case Mult(exprL, exprR) => Mult(expand(exprL), expand(exprR))
      case _ => expr
    }
  }

}

