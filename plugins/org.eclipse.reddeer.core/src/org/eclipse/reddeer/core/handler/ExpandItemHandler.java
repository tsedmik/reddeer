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
package org.eclipse.reddeer.core.handler;


import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.ExpandItem;
import org.eclipse.reddeer.common.logging.Logger;
import org.eclipse.reddeer.common.util.Display;
import org.eclipse.reddeer.common.util.ResultRunnable;
import org.eclipse.reddeer.common.wait.AbstractWait;
import org.eclipse.reddeer.common.wait.TimePeriod;

/**
 * Contains methods for handling UI operations on {@link ExpandItem} widgets.
 * 
 * @author Vlado Pakan
 *
 */
public class ExpandItemHandler extends ItemHandler{
	
	private static final Logger logger = Logger.getLogger(ExpandItemHandler.class);
	private static ExpandItemHandler instance;
	
	/**
	 * Gets instance of ExpandItemHandler.
	 * 
	 * @return instance of ExpandItemHandler
	 */
	public static ExpandItemHandler getInstance(){
		if(instance == null){
			instance = new ExpandItemHandler();
		}
		return instance;
	}

	/**
	 * Expands specified {@link ExpandItem} and wait for specified time period.
	 * 
	 * @param timePeriod time period to wait for after expanding
	 * @param expandItem expand item to handle
	 */
	public void expand(TimePeriod timePeriod, final ExpandItem expandItem) {
		ControlHandler.getInstance().checkModalShells(getParent(expandItem));
		logger.debug("Expand Expand Bar Item " + getText(expandItem));
		if (!isExpanded(expandItem)) {
			notifyExpandBar(createEventForExpandBar(SWT.Expand, expandItem),
					expandItem);
			Display.syncExec(new Runnable() {
				@Override
				public void run() {
					expandItem.setExpanded(true);
				}
			});
			AbstractWait.sleep(timePeriod);
			logger.info("Expand Bar Item " + getText(expandItem)
					+ " has been expanded");
		} else {
			logger.debug("Expand Bar Item " + getText(expandItem)
					+ " is already expanded. No action performed");
		}
	}

	/**
	 * Collapses specified {@link ExpandItem}.
	 * 
	 * @param expandItem expand item to handle
	 */
	public void collapse(final ExpandItem expandItem) {
		ControlHandler.getInstance().checkModalShells(getParent(expandItem));
		logger.debug("Collapse Expand Bar Item " + getText(expandItem));
		if (isExpanded(expandItem)) {
			Display.syncExec(new Runnable() {
				@Override
				public void run() {
					expandItem.setExpanded(false);
				}
			});
			notifyExpandBar(createEventForExpandBar(SWT.Collapse, expandItem),
					expandItem);
			logger.info("Expand Bar Item " + getText(expandItem)
					+ " has been collapsed");
		} else {
			logger.debug("Expand Bar Item " + getText(expandItem)
					+ " is already collapsed. No action performed");
		}
	}

	/**
	 * Notifies listeners of specified {@link ExpandItem} about specified event.
	 * Field for event type in specified event has to be set properly.
	 * 
	 * @param event event to notify about
	 * @param expandBarItem expand bar item to handle
	 */
	private void notifyExpandBar(final Event event,final ExpandItem expandItem) {
		Display.syncExec(new Runnable() {
			public void run() {
				expandItem.getParent().notifyListeners(event.type, event);
			}
		});
	}
	
	/**
	 * Creates the event for specified {@link ExpandItem} with specified type
	 * and empty details.
	 * 
	 * @param type type of the event
	 * @param expandBarItem expand bar item to handle
	 * @return event for specified expand bar
	 */
	private Event createEventForExpandBar(int type,
			ExpandItem expandItem) {
		return createEventForExpandBar(type, SWT.NONE, expandItem);
	}

	/**
	 * Creates the event for specified {@link ExpandItem} with specified type
	 * and details.
	 * 
	 * @param type type of the event
	 * @param detail details of the event
	 * @param expandBarItem expand bar item to handle
	 * @return event for specified expand bar
	 */
	private Event createEventForExpandBar(int type, int detail,
			ExpandItem expandItem) {
		Event event = new Event();
		event.type = type;
		event.display = Display.getDisplay();
		event.time = (int) System.currentTimeMillis();
		event.item = expandItem;
		event.widget = getParent(expandItem);
		event.detail = detail;
		return event;
	}

	/**
	 * Expands specified {@link ExpandItem} under specified {@link ExpandBar}.
	 * 
	 * @param expandItem expand item to handle
	 * @param expandBar parent expand bar
	 */
	public void expand(final ExpandItem expandItem, final ExpandBar expandBar) {
		ControlHandler.getInstance().checkModalShells(getParent(expandItem));
		notifyExpandBar(createEventForExpandBar(SWT.Expand, expandItem, expandBar),
				expandBar);
		Display.syncExec(new Runnable() {
			@Override
			public void run() {
				expandItem.setExpanded(true);
			}
		});
	}

	/**
	 * Collapses specified {@link ExpandItem} under specified {@link ExpandBar}.
	 * 
	 * @param expandItem expand item to handle
	 * @param expandBar parent expand bar
	 */
	public void collapse(final ExpandItem expandItem, final ExpandBar expandBar) {
		ControlHandler.getInstance().checkModalShells(getParent(expandItem));
		Display.syncExec(new Runnable() {
			@Override
			public void run() {
				expandItem.setExpanded(false);
			}
		});
		notifyExpandBar(
				createEventForExpandBar(SWT.Collapse, expandItem, expandBar),
				expandBar);
	}

	private void notifyExpandBar(final Event event, final ExpandBar expandBar) {
		Display.syncExec(new Runnable() {
			public void run() {
				expandBar.notifyListeners(event.type, event);
			}
		});
	}

	private Event createEventForExpandBar(int type, ExpandItem expandItem,
			ExpandBar expandBar) {
		return createEventForExpandBar(type, SWT.NONE, expandItem, expandBar);
	}
	
	private Event createEventForExpandBar(int type, int detail,
			ExpandItem expandItem, ExpandBar expandBar) {
		Event event = new Event();
		event.type = type;
		event.display = Display.getDisplay();
		event.time = (int) System.currentTimeMillis();
		event.item = expandItem;
		event.widget = expandBar;
		event.detail = detail;
		return event;
	}

	/**
	 * Gets tool tip text of specified expand item.
	 * 
	 * @param item expand item to get tool tip
	 * @return tool tip text of specified item
	 */
	public String getToolTipText(final ExpandItem item) {
		String text = Display.syncExec(new ResultRunnable<String>() {
			@Override
			public String run() {
				return item.getParent().getToolTipText();
			}
		});
		return text;
	}

	/**
	 * Checks whether specified {@link ExpandItem} is expanded or not.
	 * 
	 * @param item item to handle
	 * @return true if expand item is expanded, false otherwise
	 */
	public boolean isExpanded(final ExpandItem item) {
		return Display.syncExec(new ResultRunnable<Boolean>() {

			@Override
			public Boolean run() {
				return item.getExpanded();
			}
		});
	}

	/**
	 * Gets parent {@link ExpandBar} of specified {@link ExpandItem}.
	 * 
	 * @param item to handle
	 * @return parent expand bar of specified item
	 */
	public ExpandBar getParent(final ExpandItem item) {
		return Display.syncExec(new ResultRunnable<ExpandBar>() {
			@Override
			public ExpandBar run() {
				return item.getParent();
			}
		});
	}
	
	/**
	 * Sets focus to expandbar
	 * @param expandItem to handle
	 */
	public void setFocus(ExpandItem expandItem) {
		ControlHandler.getInstance().checkModalShells(getParent(expandItem));
		ControlHandler.getInstance().setFocus(getParent(expandItem));
	}
}
