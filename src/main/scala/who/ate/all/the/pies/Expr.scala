package who.ate.all.the.pies

trait Expr

object Expr{
  implicit class ExprOps(expr: Expr) {
    def *(other: Expr): Mult = Mult(expr, other)
  }
}
