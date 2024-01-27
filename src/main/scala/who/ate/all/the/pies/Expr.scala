package who.ate.all.the.pies

trait Expr {
  def :*(other: Expr): Mult = Mult(this, other)
}

