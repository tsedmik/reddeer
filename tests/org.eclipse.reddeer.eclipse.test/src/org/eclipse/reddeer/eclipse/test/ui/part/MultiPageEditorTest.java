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
package org.eclipse.reddeer.eclipse.test.ui.part;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.eclipse.reddeer.eclipse.jdt.ui.packageview.PackageExplorerPart;
import org.eclipse.reddeer.eclipse.test.Activator;
import org.eclipse.reddeer.eclipse.ui.part.MultiPageEditorPart;
import org.eclipse.reddeer.eclipse.ui.wizards.datatransfer.ExternalProjectImportWizardDialog;
import org.eclipse.reddeer.eclipse.ui.wizards.datatransfer.WizardProjectsImportPage;
import org.eclipse.reddeer.junit.runner.RedDeerSuite;
import org.eclipse.reddeer.swt.impl.styledtext.DefaultStyledText;
import org.eclipse.reddeer.swt.impl.tree.DefaultTreeItem;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(RedDeerSuite.class)
public class MultiPageEditorTest {

	public static final File ZIP_FILE = new File(Activator.getTestResourcesLocation(MultiPageEditorTest.class), "xml-project.zip");
	
	protected static final String PROJECT_NAME = "xml-project";
	
	protected static final String FILE_NAME = "file.xml";
	
	private static MultiPageEditorPart editor;
	
	@BeforeClass
	public static void importProject(){
		ExternalProjectImportWizardDialog wizard  = new ExternalProjectImportWizardDialog();
		wizard.open();

		WizardProjectsImportPage wizardPage = new WizardProjectsImportPage();
		wizardPage.setArchiveFile(ZIP_FILE.getAbsolutePath());
		wizardPage.selectProjects(PROJECT_NAME);

		wizard.finish();
		
		PackageExplorerPart explorer = new PackageExplorerPart();
		explorer.open();
		explorer.getProject(PROJECT_NAME).getProjectItem(FILE_NAME).open();
		
		editor = new MultiPageEditorPart(FILE_NAME);
	}
	
	@AfterClass
	public static void cleanProject(){
		PackageExplorerPart explorer = new PackageExplorerPart();
		explorer.open();
		explorer.getProject(PROJECT_NAME).delete(true);
	}
	
	@Test
	public void selectPage(){
		editor.selectPage("Design");
		new DefaultTreeItem("a", "b1");
		
		// loose focus
		new PackageExplorerPart().open();
		
		editor.selectPage("Source");
		assertTrue(new DefaultStyledText().getText().contains("</a>"));
	}
}
