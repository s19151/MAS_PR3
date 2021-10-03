package io.github.s19151.MAS_PR3.models;

import javax.persistence.Basic;
import javax.persistence.Embeddable;

@Embeddable
public class Opinion {
	private String text;
	
	public Opinion() {}

	public Opinion(String text) {
		this.text = text;
	}
	
	@Basic
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	@Override
	public String toString() {
		return String.format(
				"%s: %s",
				getClass().getName(),
				getText()
			);
	}
}
