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
package org.eclipse.reddeer.workbench.impl.editor;

import org.eclipse.ui.IEditorPart;
import org.hamcrest.Matcher;
import org.eclipse.reddeer.workbench.api.Editor;

/**
 * Represents general editor with basic operations implemented.
 * 
 * @author rhopp
 * @author rawagner
 */
public class DefaultEditor extends AbstractEditor implements Editor {

	/**
	 * Initialize editor.
	 */
	public DefaultEditor() {
		super();
	}
	
	public DefaultEditor(IEditorPart editorPart){
		super(editorPart);
	}

	/**
	 * Initialize editor with given title and activate it.
	 * 
	 * @param title
	 *            Title of editor to initialize and activate
	 */
	public DefaultEditor(final String title) {
		super(title);
	}

	/**
	 * Initialize editor with given title matcher.
	 * 
	 * @param titleMatcher
	 *            Title matcher of editor to initialize and activate
	 */
	public DefaultEditor(final Matcher<String> titleMatcher) {
		super(titleMatcher);
	}

}
