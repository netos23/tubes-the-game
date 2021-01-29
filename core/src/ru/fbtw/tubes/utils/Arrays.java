package ru.fbtw.tubes.utils;

public class Arrays {
	public static <T> void moveLeft(T[] arr){
		T zeroEl = arr[0];
		System.arraycopy(arr, 1, arr, 0, arr.length - 1);
		arr[arr.length-1] = zeroEl;
	}

	public static <T> int indexOf(T[] arr, T o){
		for (int i = 0; i < arr.length; i++) {
			T target = arr[i];
			if (target.equals(o)) {
				return i;
			}
		}
		return -1;
	}

	public static <T> int countOfElements(T[] arr){
		int count = 0;
		for (T el : arr) {
			if(el != null){
				count++;
			}
		}

		return count;
	}
}


