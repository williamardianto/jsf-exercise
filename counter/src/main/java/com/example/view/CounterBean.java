package com.example.view;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

@ViewScoped
@Named
public class CounterBean implements Serializable {
	private int count = 0;
	
	public void addCount() {
		this.count = this.count + 1;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
