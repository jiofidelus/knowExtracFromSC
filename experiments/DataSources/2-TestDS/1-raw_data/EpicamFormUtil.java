package org.imogene.epicam.server.util;

import java.util.HashMap;
import java.util.Map;

import org.imogene.web.server.util.FormUtil;

/**
 * @author MEDES-IMPS
 */
public class EpicamFormUtil implements FormUtil {

	public String getActorClassName(String shortName) {

		Map<String, String> classesNames = new HashMap<String, String>();

		classesNames.put("PERS", "org.imogene.epicam.domain.entity.Personnel");
		classesNames.put("USR", "org.imogene.epicam.domain.entity.Utilisateur");

		return classesNames.get(shortName);
	}

}
