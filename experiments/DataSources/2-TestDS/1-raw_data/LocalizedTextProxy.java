package org.imogene.epicam.shared.proxy;

import org.imogene.epicam.domain.entity.LocalizedText;

import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.google.web.bindery.requestfactory.shared.ValueProxy;

@ProxyFor(value = LocalizedText.class)
public interface LocalizedTextProxy extends ValueProxy {

	public String getFrancais();

	public void setFrancais(String francais);

	public String getEnglish();

	public void setEnglish(String english);

}
