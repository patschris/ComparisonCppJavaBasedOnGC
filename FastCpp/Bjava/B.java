public class B extends A {
	public int j;
	B(int j) {
		super(j/2+1);
		this.j = j;
	}
	void add() {
		super.add();
		j++;
	}
	void sub() {
		super.sub();
		j--;
	}
	/* Sum of B's fields */
	int sum() {
		return (i+j);
	}
}