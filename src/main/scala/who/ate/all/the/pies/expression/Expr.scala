package who.ate.all.the.pies.expression

trait Expr {
  def :*(other: Expr): Mult = Mult(this, other)
}

