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
package org.eclipse.reddeer.eclipse.test.wst.jsdt.ui.wizards;

import static org.junit.Assert.assertTrue;

import org.eclipse.reddeer.eclipse.ui.navigator.resources.ProjectExplorer;
import org.eclipse.reddeer.eclipse.wst.jsdt.ui.wizards.JavaProjectWizard;
import org.eclipse.reddeer.eclipse.wst.jsdt.ui.wizards.JavaProjectWizardFirstPage;
import org.eclipse.reddeer.junit.runner.RedDeerSuite;
import org.eclipse.reddeer.requirements.cleanworkspace.CleanWorkspaceRequirement.CleanWorkspace;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(RedDeerSuite.class)
@CleanWorkspace
public class JavaProjectWizardFirstPageTest {

	public static String PROJECT_NAME = "testProject";

	@Test
	public void setName() {
		JavaProjectWizard dialog = new JavaProjectWizard();
		dialog.open();
		JavaProjectWizardFirstPage dialogPage = new JavaProjectWizardFirstPage();
		dialogPage.setName(PROJECT_NAME);
		dialog.finish();
		assertTrue("Project '" + PROJECT_NAME + "'not found", new ProjectExplorer().containsProject(PROJECT_NAME));
	}

}
