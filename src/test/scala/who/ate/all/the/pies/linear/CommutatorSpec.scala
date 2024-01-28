package who.ate.all.the.pies.linear

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import who.ate.all.the.pies.operations.ExpandExpressions.Expand
import who.ate.all.the.pies.operations.SimplifyExpressions.Simplify
import who.ate.all.the.pies.operations.Substitution.Substitute
import who.ate.all.the.pies.expression.{Const, Expr, Mult, Plus, SymbolType}

class CommutatorSpec extends AnyFlatSpec with Matchers {



  object EhrenfestTheorem {
    //d/dt ⟨A⟩ = ⟨[H,A]⟩
    def evolutionEquation(expectationValue: Expr, hamiltonian: Expr): Plus =
      Commutator(
        hamiltonian,
        expectationValue,
        SymbolType('i, 1) :* SymbolType('hbar, 1)
      ).expanded

  }

  val positionMomentumRelations = Commutator(
    SymbolType('Q, 1),
    SymbolType('P, 1),
    SymbolType('i, 1) :* SymbolType('hbar, 1)
  )

  val hamiltonian = Plus(
    SymbolType('P, 1) :* SymbolType('P, 1),
    SymbolType('Q, 1) :* SymbolType('Q, 1)
  )

  val ehren = EhrenfestTheorem.evolutionEquation(
    expectationValue = SymbolType('Q, 1),
    hamiltonian
  )

  val ehrenExpandedSimplified = ehren.expandFullMults.simplify

  val reOrderOps =
    ehrenExpandedSimplified.substitute(
      subExpr = SymbolType('Q, 1) :* SymbolType('P, 1),
      replacement = positionMomentumRelations.AB
    )

  println("ehren simplified")
  println(ehrenExpandedSimplified)


  println("ehren substituted")
  println(reOrderOps)

}
