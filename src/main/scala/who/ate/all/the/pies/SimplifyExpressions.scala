package who.ate.all.the.pies

object SimplifyExpressions {
  implicit class Simplify(expression: Expr) {
    def simplify: Expr = expression match {
      case Plus(expressions) =>
        Plus(expressions.flatMap {
          case Plus(innerExpressions) => innerExpressions
          case term                   => List(term)
        })
      case _ => expression
    }
  }
}
