package who.ate.all.the.pies

case class Mult(expressions: List[Expr]) extends Expr {
  //  override def toString: String = expressions.map(_.toString).mkString(".")
}
object Mult {
  def apply(expressions: Expr*): Mult = Mult(expressions.toList)
}
