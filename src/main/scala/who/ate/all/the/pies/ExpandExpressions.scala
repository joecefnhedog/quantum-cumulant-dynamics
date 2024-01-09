package who.ate.all.the.pies

object ExpandExpressions {

  def simplify(expression:Expr): Expr = expression match {
    case Plus(expressions) => Plus(expressions.flatMap{
      case Plus(innerExpressions) => innerExpressions
      case term =>List(term)
    })
    case _ => expression
  }

  def expandFullMults(expression:Expr): Expr =
    _expandFullMults(expression, Const(0))


  private def _expandFullMults(cur: Expr, last: Expr): Expr =
    if (cur == last) cur
    else {
      val curExpanded = expandMults(cur)
      _expandFullMults(curExpanded, cur)
    }

  private def __expandMults(xs: List[Expr], ys: List[Expr]): List[Expr] = (xs, ys) match {
    case (x :: xsTail, y :: ysTail) => Mult(x :: (y :: ysTail)) :: __expandMults(xsTail, ys)
    case _ => Nil
  }

  private def expandMults(expr: Expr): Expr = expr match {
    case Mult(List(x)) => x
    case Mult(Plus(x :: xs) :: y :: ys) => Plus(__expandMults(x :: xs, y :: ys))
    case Mult(x :: Plus(ps) :: rest) => Plus(ps.map(e => Mult(x :: e :: rest)))
    case Mult(x :: rest) => Mult(x :: expandMults(Mult(rest)) :: Nil)
    case Plus(x) => Plus(x.map(expandMults))
  }

}
