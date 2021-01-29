package ru.fbtw.tubes.utils;

public class Vector2<T extends Number> {
	private T x;
	private T y;

	public Vector2(T x, T y) {
		this.x = x;
		this.y = y;
	}

	public T getX() {
		return x;
	}

	public void setX(T x) {
		this.x = x;
	}

	public T getY() {
		return y;
	}

	public void setY(T y) {
		this.y = y;
	}
}
