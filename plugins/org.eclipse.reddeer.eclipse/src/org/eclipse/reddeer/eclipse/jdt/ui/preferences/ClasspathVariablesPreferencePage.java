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
package org.eclipse.reddeer.eclipse.jdt.ui.preferences;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.hamcrest.Matcher;
import org.eclipse.reddeer.common.wait.WaitWhile;
import org.eclipse.reddeer.core.matcher.WithTextMatcher;
import org.eclipse.reddeer.jface.preference.PreferencePage;
import org.eclipse.reddeer.swt.api.Table;
import org.eclipse.reddeer.swt.api.TableItem;
import org.eclipse.reddeer.swt.condition.ShellIsAvailable;
import org.eclipse.reddeer.swt.impl.button.OkButton;
import org.eclipse.reddeer.swt.impl.button.PushButton;
import org.eclipse.reddeer.swt.impl.shell.DefaultShell;
import org.eclipse.reddeer.swt.impl.table.DefaultTable;
import org.eclipse.reddeer.swt.impl.table.DefaultTableItem;
import org.eclipse.reddeer.swt.impl.text.LabeledText;
/**
 * Class represents Classpath Variables.
 * 
 * @author vlado pakan
 * @since 1.0
 */
public class ClasspathVariablesPreferencePage extends PreferencePage {
	/**
	 * Constructor for classpath variables preference in preference shell.
	 */
	public ClasspathVariablesPreferencePage() {
		super(new String[] {"Java", "Build Path", "Classpath Variables"});
	}
	
	/**
	 * Returns list with defined classpath variables.
	 *
	 * @return the variables
	 */
	public List<String> getVariables (){
		LinkedList<String> libraries = new LinkedList<String>();
		for (TableItem ti :  getClasspathVariablesTable().getItems()){
			libraries.addLast(ti.getText());
		}
		return libraries;
	}
	
	/**
	 * Selects classpath variable matching matcher .
	 *
	 * @param matcher the matcher
	 */
	public void selectVariable (Matcher<?> matcher){
		new DefaultTableItem(getClasspathVariablesTable(),matcher).select();
	}
	/**
	 * Returns classpath variable table
	 * @return
	 */
	private Table getClasspathVariablesTable () {
		return new DefaultTable();
	}	
	
	/**
	 * Adds new classpath variable.
	 *
	 * @param name the name
	 * @param value the value
	 * @param overwriteIfExists the overwrite if exists
	 * @return added classpath label
	 */
	public String addVariable(String name , String value , boolean overwriteIfExists){
		boolean externalJarExists = false;
		Iterator<TableItem> itTableItem = getClasspathVariablesTable().getItems().iterator();
		while (itTableItem.hasNext() && !externalJarExists) {
			TableItem tableItem = itTableItem.next();
			if (tableItem.getText().split(" - ")[0].equals(name)) {
				tableItem.select();
				externalJarExists = true;
			}
		}
		String variableSetShellTitle;
		String preferencesShellTitle = new DefaultShell().getText();
		if (externalJarExists && overwriteIfExists) {
			new PushButton("Edit...").click();	
			variableSetShellTitle = "Edit Variable Entry";
			new DefaultShell(variableSetShellTitle);	
			new LabeledText("Path:").setText(value);
		} else {
			new PushButton("New...").click();
			variableSetShellTitle = "New Variable Entry";
			new DefaultShell(variableSetShellTitle);		
			new LabeledText("Name:").setText(name);		
			new LabeledText("Path:").setText(value);
		}
		new OkButton().click();
		new WaitWhile(new ShellIsAvailable(variableSetShellTitle));
		new DefaultShell(preferencesShellTitle);
		return new DefaultTable().getSelectetItems().get(0).getText();
	}
	
	/**
	 * Removes classpath variable with label from classpath variables.
	 *
	 * @param label the label
	 */
	public void removeVariable(String label){
		selectVariable(new WithTextMatcher(label));
		new PushButton("Remove").click();
	}
}
