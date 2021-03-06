/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.python.pydev.dltk.console.ui.internal.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.python.pydev.core.REF;
import org.python.pydev.dltk.console.ui.ScriptConsole;
import org.python.pydev.dltk.console.ui.ScriptConsoleUIConstants;
import org.python.pydev.plugin.PydevPlugin;

/**
 * This action will save the console session to a file chosen by the user.
 *
 * @author Fabio
 */
public class SaveConsoleSessionAction extends Action {
    
    private ScriptConsole console;

    public SaveConsoleSessionAction(ScriptConsole console, String text, String tooltip) {
        this.console = console;
        setText(text);
        setToolTipText(tooltip);
    }

    public void run() {
        IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();

        FileDialog dialog = new FileDialog(window.getShell(), SWT.SAVE);

        String file = dialog.open();

        if (file != null) {
            REF.writeStrToFile(console.getSession().toString(), file);
        }
    }

    public void update() {
        setEnabled(true);
    }

    public ImageDescriptor getImageDescriptor() {
        return PydevPlugin.getDefault().getImageDescriptor(ScriptConsoleUIConstants.SAVE_SESSION_ICON);
    }
}
