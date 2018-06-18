package org.imogene.epicam.domain.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class LocalizedText implements Serializable {

	private static final long serialVersionUID = -1449491140352552795L;

	private String francais;
	private String english;

	public String getFrancais() {
		return francais;
	}

	public void setFrancais(String francais) {
		this.francais = francais;
	}
	public String getEnglish() {
		return english;
	}

	public void setEnglish(String english) {
		this.english = english;
	}

}
