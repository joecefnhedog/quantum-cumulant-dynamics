package who.ate.all.the.pies.operations

import who.ate.all.the.pies.expression.{Expr, Mult, Plus}

object Substitution {
  implicit class Substitute(expression: Expr) {
    def substitute(subExpr: Expr, replacement: Expr): Expr = {
      def substituteRec(expr: Expr): Expr = {
        println(expr)
        expr match {
          case s if s == subExpr => replacement
          case Mult(exprL, exprR) =>
            Mult(substituteRec(exprL), substituteRec(exprR))
          case Plus(terms) => Plus(terms.map(t => substituteRec(t)))
          case other => other
        }
      }

      substituteRec(expression)
    }

  }

}
