package scalaz
package syntax

/** Wraps a value `self` and provides methods related to `Index` */
trait IndexV[F[_],A] extends SyntaxV[F[A]] {
  implicit def F: Index[F]
  ////
  def index(n: Int): Option[A] = F.index(self, n)
  def indexOr(default: => A, n: Int): A = F.indexOr(self, default, n)
  ////
}

trait ToIndexSyntax  {
  implicit def ToIndexV[F[_],A](v: F[A])(implicit F0: Index[F]) =
    new IndexV[F,A] { def self = v; implicit def F: Index[F] = F0 }
  implicit def ToIndexVFromBin[F[_, _], X, A](v: F[X, A])(implicit F0: Index[({type f[a] = F[X, a]})#f]) =
    new IndexV[({type f[a] = F[X, a]})#f,A] { def self = v; implicit def F: Index[({type f[a] = F[X, a]})#f] = F0 }
  implicit def ToIndexVFromBinT[F[_, _[_], _], G[_], X, A](v: F[X, G, A])(implicit F0: Index[({type f[a] = F[X, G, a]})#f]) =
    new IndexV[({type f[a] = F[X, G, a]})#f,A] { def self = v; implicit def F: Index[({type f[a] = F[X, G, a]})#f] = F0 }
  implicit def ToIndexVFromBinTId[F[_, _[_], _], X, A](v: F[X, Id, A])(implicit F0: Index[({type f[a] = F[X, Id, a]})#f]) =
    new IndexV[({type f[a] = F[X, Id, a]})#f,A] { def self = v; implicit def F: Index[({type f[a] = F[X, Id, a]})#f] = F0 }

  ////

  ////
}

trait IndexSyntax[F[_]]  {
  implicit def ToIndexV[A](v: F[A])(implicit F0: Index[F]): IndexV[F, A] = new IndexV[F,A] { def self = v; implicit def F: Index[F] = F0 }

  ////

  ////
}