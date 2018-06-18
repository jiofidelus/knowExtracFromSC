package org.imogene.epicam.shared.proxy;

import java.util.Date;
import java.util.List;

import org.imogene.epicam.domain.entity.Tutoriel;
import org.imogene.epicam.server.locator.TutorielLocator;
import org.imogene.web.shared.proxy.BinaryProxy;
import org.imogene.web.shared.proxy.ImogBeanProxy;

import org.imogene.web.shared.proxy.GeoFieldProxy;

import com.google.web.bindery.requestfactory.shared.ProxyFor;

/**
 * Tutoriel proxy 
 * @author Medes-IMPS
 */
@ProxyFor(value = Tutoriel.class, locator = TutorielLocator.class)
public interface TutorielProxy extends ImogBeanProxy {

	/* Description section fields */

	public String getReference();
	public void setReference(String value);

	public LocalizedTextProxy getNom();
	public void setNom(LocalizedTextProxy value);

	public LocalizedTextProxy getDescription();
	public void setDescription(LocalizedTextProxy value);

	public String getType();
	public void setType(String value);

	public BinaryProxy getAudioT();
	public void setAudioT(BinaryProxy value);

	public BinaryProxy getVideoT();
	public void setVideoT(BinaryProxy value);

	public BinaryProxy getTextT();
	public void setTextT(BinaryProxy value);

}
