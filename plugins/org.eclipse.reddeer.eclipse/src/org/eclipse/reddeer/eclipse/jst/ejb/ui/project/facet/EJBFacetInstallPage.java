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
package org.eclipse.reddeer.eclipse.jst.ejb.ui.project.facet;

import org.eclipse.reddeer.jface.wizard.WizardPage;
import org.eclipse.reddeer.swt.impl.button.CheckBox;

/**
 * Represents EJBFacetInstallPage.
 * @author rawagner
 *
 */
public class EJBFacetInstallPage extends WizardPage {
	
	/**
	 * Enables/disables generation of ejb-jar.xml.
	 * @param toggle toggle generation
	 */
	public void toggleGenerateEjbJarXml(final boolean toggle) {
		new CheckBox("Generate ejb-jar.xml deployment descriptor").toggle(toggle);
	}
	
	/**
	 * Checks if generation of ejb-jar.xml is enabled.
	 * @return true if generation of ejb-jar.xml is enabled, false otherwise
	 */
	public boolean isGenerateEjbJarXml() {
		return new CheckBox("Generate ejb-jar.xml deployment descriptor").isChecked();
	}

}
