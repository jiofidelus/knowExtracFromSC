package org.imogene.epicam.server.security;

import org.imogene.lib.common.entity.ImogActor;
import org.imogene.lib.common.security.AccessPolicy;
import org.imogene.lib.common.security.AccessPolicyFactory;

public class AccessPolicyFactoryImpl implements AccessPolicyFactory {

	@Override
	public AccessPolicy create(ImogActor actor) {
		return new AccessPolicyImpl(actor);
	}
}
