package who.ate.all.the.pies

case class Plus(expressions: List[Expr]) extends Expr {
//  override def toString: String =
//    expressions.map(_.toString).mkString("+")
}

case class PlusSeq(elements: Expr*) extends Seq[Expr] with Expr {
  override def toString(): String = s"PlusSeq(${elements.mkString(",")})"

  override def length: Int = elements.length

  override def apply(idx: Int): Expr = elements(idx)

  override def iterator: Iterator[Expr] = elements.iterator

}