package org.imogene.epicam.shared.proxy;

import java.util.Date;
import java.util.List;

import org.imogene.epicam.domain.entity.Qualification;
import org.imogene.epicam.server.locator.QualificationLocator;
import org.imogene.web.shared.proxy.BinaryProxy;
import org.imogene.web.shared.proxy.ImogBeanProxy;

import org.imogene.web.shared.proxy.GeoFieldProxy;

import com.google.web.bindery.requestfactory.shared.ProxyFor;

/**
 * Qualification proxy 
 * @author Medes-IMPS
 */
@ProxyFor(value = Qualification.class, locator = QualificationLocator.class)
public interface QualificationProxy extends ImogBeanProxy {

	/* Description section fields */

	public String getCode();
	public void setCode(String value);

	public LocalizedTextProxy getNom();
	public void setNom(LocalizedTextProxy value);

	public LocalizedTextProxy getDescription();
	public void setDescription(LocalizedTextProxy value);

}
