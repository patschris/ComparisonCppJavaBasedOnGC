public class A {
	public int i;
	public A(int i) {
		this.i=i;
	}
	void add() {
		i++;
	}
	void sub() {
		i--;
	}
	/* Sum of A's fields */
	int sum() {
		return i;
	}
}