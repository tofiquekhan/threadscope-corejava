package myproject.threadscope.core;

class ThreadScope extends ThreadLocal<String> {
@Override
protected String initialValue() {
	// TODO Auto-generated method stub
	return "No data in this Scope";
}
}

class A {
	void m1() {
		System.out.println("m1() : Thread1 Scope : " + Thread1.threadScope.get());
		System.out.println("m1() : Thread2 Scope : " + Thread2.threadScope.get());
	}

	void m2() {
		System.out.println("m2() : Thread2 Scope : " + Thread2.threadScope.get());
		System.out.println("m2() : Thread1 Scope : "+ Thread1.threadScope.get());
	}
}

class Thread1 extends Thread {
	static ThreadScope threadScope = new ThreadScope();
	A a;

	Thread1(A a) {
		this.a = a;
	}

	public void run() {
		threadScope.set("AAA");
		a.m1();
	}
}

class Thread2 extends Thread {
	static ThreadScope threadScope = new ThreadScope();
	A a;

	public Thread2(A a) {
		this.a = a;
	}

	@Override
	public void run() {
		threadScope.set("BBB");
		a.m2();
	}
}

public class Test {
	public static void main(String[] args) {
		A a = new A();
		Thread1 t1 = new Thread1(a);
		Thread2 t2 = new Thread2(a);
		
		t1.start();
		t2.start();
	}
}
