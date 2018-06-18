package org.imogene.epicam.client.i18n;
import com.google.gwt.core.client.GWT;

public class NLS {

	private static EpicamTranslations constants = (EpicamTranslations) GWT
			.create(EpicamTranslations.class);

	public static EpicamTranslations constants() {
		return constants;
	}

}
