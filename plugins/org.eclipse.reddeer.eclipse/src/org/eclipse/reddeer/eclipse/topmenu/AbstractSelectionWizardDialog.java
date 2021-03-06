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
package org.eclipse.reddeer.eclipse.topmenu;

import org.hamcrest.Matcher;
import org.eclipse.reddeer.core.matcher.WithTextMatcher;
import org.eclipse.reddeer.jface.wizard.WizardDialog;
import org.eclipse.reddeer.swt.api.Shell;
import org.eclipse.reddeer.workbench.api.TopMenuOpenable;

/**
 * Abstract class for wizard dialogs with selection page (such as NewWizard, ImportExportWizard)
 * @author rawagner
 *
 */
public abstract class AbstractSelectionWizardDialog extends WizardDialog implements TopMenuOpenable{
	
	protected Matcher<String> matcher;
	protected String[] wizardPath;
	
	/**
	 * Constructs new AbstractSelectionWizardDialog
	 * @param shellText wizard text when next is clicked
	 * @param wizardCategory wizard category in selection page
	 * @param wizardName wizard name in selection page
	 */
	public AbstractSelectionWizardDialog(String shellText, String wizardCategory, String wizardName) {
		this(shellText, new String[] {wizardCategory, wizardName});
	}
	
	/**
	 * Constructs new AbstractSelectionWizardDialog
	 * @param shellText wizard text when next is clicked
	 * @param wizardPath wizard path in selection page
	 */
	public AbstractSelectionWizardDialog(String shellText, String[] wizardPath) {
		super((Shell)null);
		this.matcher = new WithTextMatcher(shellText);
		this.wizardPath = wizardPath;
		isOpen();
	}
	
	@Override
	public Matcher<String> getShellMatcher() {
		return matcher;
	}
	
	@Override
	public String[] getMenuPath(){
		return wizardPath;
	}

}
