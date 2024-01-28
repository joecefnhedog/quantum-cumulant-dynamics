package who.ate.all.the.pies.expression

case class Plus(expressions: List[Expr]) extends Expr {
//  override def toString: String =
//    expressions.map(_.toString).mkString("+")
}
object Plus {
  def apply(expressions: Expr*): Plus = Plus(expressions.toList)
}
