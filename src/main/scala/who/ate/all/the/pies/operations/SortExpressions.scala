package who.ate.all.the.pies.operations

import who.ate.all.the.pies.expression.Expr

object SortExpressions {

  implicit class Sort(expr: Expr) {
    def sort: Expr = {
      sortMults(expr)
    }

    private def sortMults(expr: Expr): Expr = expr match {
      case _       => expr
    }
  }

}
