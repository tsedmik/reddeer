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
package org.eclipse.reddeer.swt.widgets;

import org.eclipse.swt.graphics.Image;
import org.hamcrest.Matcher;
import org.eclipse.reddeer.core.handler.ItemHandler;
import org.eclipse.reddeer.core.reference.ReferencedComposite;
import org.eclipse.reddeer.swt.api.Item;

/**
 * Abstract class for all item widgets
 * @author rawagner
 *
 * @param <T> extends swt item
 */
public abstract class AbstractItem<T extends org.eclipse.swt.widgets.Item> extends AbstractWidget<T> implements Item<T>{
	
	protected ItemHandler itemHandler = ItemHandler.getInstance();

	protected AbstractItem(Class<T> widgetClass, ReferencedComposite refComposite, int index, Matcher<?>... matchers) {
		super(widgetClass,refComposite,index,matchers);
	}
	
	protected AbstractItem(T swtWidget) {
		super(swtWidget);
	}
	
	public Image getImage(){
		return itemHandler.getImage(swtWidget);
	}
	
	public String getText(){
		return itemHandler.getText(swtWidget);
	}

}
