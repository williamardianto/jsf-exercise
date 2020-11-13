package com.example.view;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@ViewScoped
@Named
public class Timer implements Serializable {

	private int sliderValue = 7;
	private Integer progress;

	public int getSliderValue() {
		return sliderValue;
	}

	public void setSliderValue(int sliderValue) {
		this.sliderValue = sliderValue;
	}

	public Integer getProgress() {
		this.progress = updateProgress(this.progress);
		return (int) (100.0 * this.progress / this.sliderValue);
	}

	public void setProgress(Integer progress) {
		this.progress = progress;
	}

	public void reset() {
		this.progress = null;
	}

	public void onComplete() {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Progress Completed"));
	}

	public void sliderOnChange() {
		System.out.println(this.sliderValue);
	}

	private Integer updateProgress(Integer p) {
		if (p == null) {
			p = 0;
		} else {
			p = p + 1;
		}
		return p;
	}
}
