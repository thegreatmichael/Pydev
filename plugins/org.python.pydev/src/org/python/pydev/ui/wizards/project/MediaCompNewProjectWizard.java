package org.python.pydev.ui.wizards.project;

import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.python.pydev.core.IInterpreterInfo;
import org.python.pydev.core.IInterpreterManager;
import org.python.pydev.plugin.PydevPlugin;
import org.python.pydev.plugin.preferences.PydevPrefs;

public class MediaCompNewProjectWizard extends
		NewProjectNameAndLocationWizardPage {

	public MediaCompNewProjectWizard(String pageName) {
		super(pageName);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String getProjectType()
	{
		return details.getJyVersion();
	}
	
	@Override
	public String getProjectInterpreter(){
		IInterpreterManager interpreterManager = PydevPlugin.getJythonInterpreterManager();
		IInterpreterInfo[] interpretersInfo = interpreterManager.getInterpreterInfos();
		return interpretersInfo[0].getName();
    }
	
	@Override
	public IPath getLocationPath() {
        return initialLocationFieldValue;
    }
	
	@Override
	protected final void createProjectLocationGroup(Composite parent) {}
	@Override
	protected void createProjectDetails(Composite parent) {}
	
	@Override
	public void createControl(Composite parent) {
        Composite composite = new Composite(parent, SWT.NULL);
        composite.setLayout(new GridLayout());
        composite.setLayoutData(new GridData(GridData.FILL_BOTH));
        composite.setFont(parent.getFont());


        createProjectNameGroup(composite);
        createProjectLocationGroup(composite);
        createProjectDetails(composite);
        
//        projectAsSrcFolder = new Button(composite , SWT.RADIO);
//        projectAsSrcFolder.setText("&Add project directory to the PYTHONPATH?");
//        
//        checkSrcFolder = new Button(composite , SWT.RADIO);
//        checkSrcFolder.setText("Cr&eate 'src' folder and add it to the PYTHONPATH?");
//        
//        noSrcFolder = new Button(composite , SWT.RADIO);
//        noSrcFolder.setText("Don't configure PYTHONPATH (to be done &manually later on)");
        
        IPreferenceStore preferences = PydevPrefs.getPreferences();
        int srcFolderCreate = preferences.getInt(IWizardNewProjectNameAndLocationPage.PYDEV_NEW_PROJECT_CREATE_PREFERENCES);
        switch (srcFolderCreate) {
            case PYDEV_NEW_PROJECT_CREATE_PROJECT_AS_SRC_FOLDER:
                projectAsSrcFolder.setSelection(true);
                break;
    
            case PYDEV_NEW_PROJECT_NO_PYTHONPATH:
                noSrcFolder.setSelection(true);
                break;
                
            default:
                //default is src folder...
                checkSrcFolder.setSelection(true);
        }
        
        preferences.setValue(IWizardNewProjectNameAndLocationPage.PYDEV_NEW_PROJECT_CREATE_PREFERENCES, 
                    PYDEV_NEW_PROJECT_CREATE_PROJECT_AS_SRC_FOLDER);
        
        validatePage();

        // Show description on opening
        setErrorMessage(null);
        setMessage(null);
        setControl(composite);
    }
}
