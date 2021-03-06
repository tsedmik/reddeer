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
package org.eclipse.reddeer.junit.test.internal.requirement;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.eclipse.reddeer.junit.requirement.CustomConfiguration;
import org.eclipse.reddeer.junit.requirement.Requirement;
import org.eclipse.reddeer.junit.test.internal.requirement.TestCustomServerRequirement.CustomServerAnnotation;

public class TestCustomServerRequirement implements Requirement<CustomServerAnnotation>, CustomConfiguration<TestCustomServerConfiguration> {

	private TestCustomServerConfiguration config;
	
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.TYPE)
	public @interface CustomServerAnnotation {
	}
	
	public boolean canFulfill() {
		return true;
	}

	public void fulfill() {
	}
	
	@Override
	public void setDeclaration(CustomServerAnnotation declaration) {
	}

	@Override
	public void cleanUp() {
		// TODO Auto-generated method stub
	}

	@Override
	public Class<TestCustomServerConfiguration> getConfigurationClass() {
		return TestCustomServerConfiguration.class;
	}

	@Override
	public void setConfiguration(TestCustomServerConfiguration configuration) {
		this.config = configuration;
	}
	
	public TestCustomServerConfiguration getConfig() {
		return this.config;
	}

}
