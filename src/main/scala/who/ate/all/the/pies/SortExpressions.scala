package who.ate.all.the.pies

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
