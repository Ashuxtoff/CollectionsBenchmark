package helpClasses;

public class TwoTuple<A extends Comparable<A>, B> implements Comparable<TwoTuple<A, B>>{

	private A first;
	private B second;

	public A getFirst() {return first;}
	public B getSecond() {return second;}
	public void setFirst(A a) {first = a;}
	public void setSecond(B b) {second = b;}

	
	public TwoTuple(A a, B b) { first = a; second = b; }

	public String toString() {
		return "(" + first + ", " + second + ")";
	}

	@Override
	public int compareTo(TwoTuple<A, B> other) {
		return this.first.compareTo(other.first);
	}
}