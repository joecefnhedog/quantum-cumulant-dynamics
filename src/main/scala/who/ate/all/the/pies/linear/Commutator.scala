package who.ate.all.the.pies.linear

import who.ate.all.the.pies.expression.{Const, Expr, Plus}



case class Commutator(A: Expr, B: Expr, relation: Expr) { //[A,B]=relation

  def expanded: Plus = Plus(A :* B, Const(-1) :* B :* A) //AB-BA = [A,B]

  def AB = Plus(relation, B :* A)

  def BA = Plus(A :* B, Const(-1) :* relation)
}
