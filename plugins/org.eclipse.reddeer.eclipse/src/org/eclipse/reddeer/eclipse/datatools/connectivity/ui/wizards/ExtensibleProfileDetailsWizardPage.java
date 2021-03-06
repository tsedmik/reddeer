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
package org.eclipse.reddeer.eclipse.datatools.connectivity.ui.wizards;

import org.eclipse.swt.widgets.Shell;
import org.eclipse.reddeer.common.exception.RedDeerException;
import org.eclipse.reddeer.common.exception.WaitTimeoutExpiredException;
import org.eclipse.reddeer.common.wait.WaitWhile;
import org.eclipse.reddeer.core.condition.WidgetIsFound;
import org.eclipse.reddeer.core.matcher.WithTextMatcher;
import org.eclipse.reddeer.jface.wizard.WizardPage;
import org.eclipse.reddeer.swt.condition.ShellIsAvailable;
import org.eclipse.reddeer.swt.impl.button.CheckBox;
import org.eclipse.reddeer.swt.impl.button.OkButton;
import org.eclipse.reddeer.swt.impl.combo.LabeledCombo;
import org.eclipse.reddeer.swt.impl.shell.DefaultShell;
import org.eclipse.reddeer.swt.impl.text.LabeledText;

/**
 * An abstract wizard page for database connection profile.
 * 
 * IMPORTANT: It is assumed that the appropriate driver definition has been already created.
 * 
 * @author apodhrad, mlabuda@redhat.com
 * 
 */
public abstract class ExtensibleProfileDetailsWizardPage extends WizardPage {

	public static final String LABEL_DRIVER = "Drivers:";

	/**
	 * Instantiates a new connection profile database page.
	 */
	protected ExtensibleProfileDetailsWizardPage() {
		super();
	}

	/**
	 * Tests database connection. Throws RedDeer exception
	 * if neither Sucess or Error shell are shown upon testing connection.
	 * 
	 * @return true if connection is 
	 * successful, false otherwise
	 */
	public boolean testConnection() {
		org.eclipse.reddeer.swt.api.Shell shell;
		try {
			shell = new DefaultShell("Success");
			new OkButton().click();
			new WaitWhile(new ShellIsAvailable(shell));
			return true;
		} catch (WaitTimeoutExpiredException ex) {
			WidgetIsFound widgetIsFound = new WidgetIsFound(Shell.class, new WithTextMatcher("Error"));
			if (widgetIsFound.test()) {
				new OkButton().click();
				new WaitWhile(new ShellIsAvailable(new DefaultShell((Shell) widgetIsFound.getWidget())));
				return false;
			}
			throw new RedDeerException("Something went wrong and nor Success nor Error shell were shown. " +
					" Cannot handle this exceptional situation.");
		}
	}
	
	/**
	 * Returns the driver name.
	 * 
	 * @return Driver name.
	 */
	public String getDriver() {
		return new LabeledCombo(LABEL_DRIVER).getText();
	}

	/**
	 * Sets the specified driver name.
	 * 
	 * @param driver
	 *            Driver name
	 */
	public void setDriver(String driver) {
		new LabeledCombo(LABEL_DRIVER).setSelection(driver);
	}

	/**
	 * Gets the database.
	 *
	 * @return the database 
	 */
	public String getDatabase() {
		return new LabeledText(getDatabaseLabel()).getText();
	}
	
	/**
	 * Sets a database.
	 * 
	 * @param database
	 *            Database
	 */
	public void setDatabase(String database) {
		new LabeledText(getDatabaseLabel()).setText(database);
	}

	/**
	 * Gets the hostname.
	 *
	 * @return the hostname
	 */
	public String getHostname() {
		return new LabeledText(getHostnameLabel()).getText();
	}
	
	/**
	 * Sets a given hostname.
	 * 
	 * @param hostname
	 *            Hostname
	 */
	public void setHostname(String hostname) {
		new LabeledText(getHostnameLabel()).setText(hostname);
	}

	/**
	 * Gets database URL.
	 * 
	 * @return URL URL of database
	 */
	public String getURL() {
		return new LabeledText(getURLLabel()).getText();
	}
	
	/**
	 * Sets database URL.
	 * @param URL URL of database
	 */
	public void setURL(String URL) {
		new LabeledText(getURLLabel()).setText(URL);
	}
	
	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	public String getUsername() {
		return new LabeledText(getUsernameLabel()).getText();
	}
	
	/**
	 * Sets a given user name.
	 * 
	 * @param userName
	 *            User name
	 */
	public void setUsername(String userName) {
		new LabeledText(getUsernameLabel()).setText(userName);
	}
	
	/**
	 * Gets database port.
	 *
	 * @return database port
	 */
	public String getPort() {
		return new LabeledText(getPortLabel()).getText();
	}
	
	/**
	 * Sets a given port.
	 * 
	 * @param port
	 *            Port
	 */
	public void setPort(String port) {
		new LabeledText(getPortLabel()).setText(port);
	}
	
	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return new LabeledText(getPasswordLabel()).getText();
	}
	
	/**
	 * Sets a given password.
	 * 
	 * @param password
	 *            Password
	 */
	public void setPassword(String password) {
		new LabeledText(getPasswordLabel()).setText(password);
	}
	
	/**
	 * Toggles save password checkbox.
	 * 
	 * @param toggle set to true to save password, false otherwise
	 */
	public void toggleSavePassword(boolean toggle) {
		new CheckBox(getSavePasswordLabel()).click(); 
	}
	
	/**
	 * Gets label for database hostname.
	 * 
	 * @return hostname label
	 */
	protected abstract String getHostnameLabel();
	
	/**
	 * Gets label for database.
	 * 
	 * @return database label
	 */
	protected abstract String getDatabaseLabel();
	
	/**
	 * Gets label for database URL.
	 * 
	 * @return label for database URL
	 */
	protected abstract String getURLLabel();
	
	/**
	 * Gets label for username.
	 * 
	 * @return label for username
	 */
	protected abstract String getUsernameLabel();
	
	/**
	 * Gets label for port.
	 * 
	 * @return label for port
	 */
	protected abstract String getPortLabel();
	
	/**
	 * Gets label for password.
	 * 
	 * @return label for password
	 */
	protected abstract String getPasswordLabel();
	
	/**
	 * Gets label for checkbox to save password.
	 * 
	 * @return label for checkbox to save password
	 */
	protected abstract String getSavePasswordLabel();
}
