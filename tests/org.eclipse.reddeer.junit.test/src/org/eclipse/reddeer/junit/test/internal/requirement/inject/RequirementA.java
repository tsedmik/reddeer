/*******************************************************************************
 * Copyright (c) 2017 Red Hat, Inc and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Red Hat Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.reddeer.junit.test.internal.requirement.inject;

import java.lang.annotation.Annotation;

import org.eclipse.reddeer.junit.requirement.PropertyConfiguration;
import org.eclipse.reddeer.junit.requirement.Requirement;

public class RequirementA implements Requirement<Annotation>, PropertyConfiguration {

	private String a;
	
	public boolean canFulfill() {
		return false;
	}

	public void fulfill() {
	}
	
	public void setA(String a) {
		this.a = a;
	}
	
	public String getA() {
		return a;
	}
	
	@Override
	public void setDeclaration(Annotation declaration) {
	}

	@Override
	public void cleanUp() {
		// TODO Auto-generated method stub
		
	}
}
