package who.ate.all.the.pies

object SortExpressions {

  implicit class Sort(expr: Expr) {
    def sort: Expr = {
      sortMults(expr)
    }

    private def sortMults(expr: Expr): Expr = expr match {
      case Mult(terms) =>
        val (consts, nonConsts) = terms.partition {
          case Const(_) => true
          case _        => false
        }
        Mult(consts ++ nonConsts.map(sortMults))

      case Plus(x) => Plus(x.map(sortMults))
      case _       => expr
    }
  }

}
