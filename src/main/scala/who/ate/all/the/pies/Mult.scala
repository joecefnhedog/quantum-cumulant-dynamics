package who.ate.all.the.pies

case class Mult(exprL: Expr, exprR: Expr) extends Expr {
  //  override def toString: String = expressions.map(_.toString).mkString(".")
}
