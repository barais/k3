/*******************************************************************************
 *  Copyright (c) 2000, 2017 IBM Corporation and others.
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 * 
 *  Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package fr.inria.diverse.k3.ui.templates.k3al;

import java.io.IOException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardPage;
import org.osgi.framework.BundleException;

import org.eclipse.gemoc.commons.eclipse.pde.classpath.ClasspathHelper;
import org.eclipse.gemoc.commons.eclipse.pde.manifest.ManifestChanger;
import org.eclipse.gemoc.commons.eclipse.pde.wizards.pages.pde.ui.BaseProjectWizardFields;
import org.eclipse.gemoc.commons.eclipse.pde.wizards.pages.pde.ui.templates.NewProjectTemplateWizard;
import org.eclipse.gemoc.commons.eclipse.core.resources.IFolderUtils;
import fr.inria.diverse.k3.ui.templates.Activator;
import fr.inria.diverse.k3.ui.templates.IHelpContextIds;
import fr.inria.diverse.k3.ui.templates.K3TemplateMessages;
import fr.inria.diverse.k3.ui.wizards.pages.NewK3ProjectWizardFields.KindsOfProject;

public class UserEcoreBasicAspectWithMelangeTemplate extends UserEcoreBasicAspectTemplate {

	public static final String KEY_MELANGE_FILE_NAME = "melangeFileName"; //$NON-NLS-1$
	public static final String MELANGE_FILE_NAME = "sample"; //$NON-NLS-1$
	public static final String KEY_METAMODEL_NAME = "metamodelName"; //$NON-NLS-1$
	public static final String METAMODEL_NAME = "MyMetamodel"; //$NON-NLS-1$
	
	/**
	 * Constructor for HelloWorldTemplate.
	 */
	public UserEcoreBasicAspectWithMelangeTemplate(NewProjectTemplateWizard hostWizard) {
		super(hostWizard);
	}

	@Override
	protected void createOptions() {
		super.createOptions();
		addOption(KEY_MELANGE_FILE_NAME, K3TemplateMessages.UserEcoreBasicAspectWithMelangeTemplate_melangeFileName, (String) null, 0);
		addOption(KEY_METAMODEL_NAME, K3TemplateMessages.UserEcoreBasicAspectWithMelangeTemplate_melangeMetamodelName, (String) null, 0);
		
	}
	/** 
	 * used to retrieve the template folder
	 */
	public String getSectionId() {
		return "userEcoreBasicAspectWithMelange"; //$NON-NLS-1$
	}

	@Override
	public void addPages(Wizard wizard) {
		WizardPage page = createPage(0, IHelpContextIds.TEMPLATE_ECORE_ASPECT_WITH_MELANGE);
		page.setTitle(K3TemplateMessages.UserEcoreBasicAspectWithMelangeTemplate_title);
		page.setDescription(K3TemplateMessages.UserEcoreBasicAspectWithMelangeTemplate_desc);
		wizard.addPage(page);
		markPagesAdded();
	}

	public boolean isDependentOnParentWizard() {
		return true;
	}

	protected void initializeFields(BaseProjectWizardFields data) {
		super.initializeFields(data);	
		if(_data.ecoreIFile != null){
			String ecoreBaseName = _data.ecoreIFile.getLocation().removeFileExtension().lastSegment();
			initializeOption(KEY_METAMODEL_NAME,Character.toUpperCase(ecoreBaseName.charAt(0)) + ecoreBaseName.substring(1));
			initializeOption(KEY_MELANGE_FILE_NAME,ecoreBaseName);
		}
		else{
			initializeOption(KEY_METAMODEL_NAME,METAMODEL_NAME);
			initializeOption(KEY_MELANGE_FILE_NAME,MELANGE_FILE_NAME);
		}
	}

	/* (non-Javadoc)
	 * @see fr.inria.diverse.k3.ui.templates.K3TemplateSection#generateFiles(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	protected void generateFiles(IProgressMonitor monitor) throws CoreException {		
		updateDataFromOptions();
		super.generateFiles(monitor);
		
		// now add the melange stuff
		
		// If this is a plugin
		if(_data.kindsOfProject == KindsOfProject.PLUGIN){
			ManifestChanger manifestChanger;
			try {
				manifestChanger = new ManifestChanger(project.getFile("META-INF/MANIFEST.MF"));
				manifestChanger.addPluginDependency(_data.ecoreIFile.getProject().getName(), "0.0.0", false, true);
				manifestChanger.addPluginDependency("fr.inria.diverse.melange.lib", "0.0.0", false, true);
				manifestChanger.commit();
				
				IFolderUtils.createFolder("src-gen", project, monitor);
				IJavaProject javaProject = (IJavaProject)project.getNature(JavaCore.NATURE_ID);
				ClasspathHelper.addEntry(project, JavaCore.newSourceEntry(javaProject.getPackageFragmentRoot(project.getFolder("src-gen")).getPath()), monitor);
			} catch (IOException | BundleException e) {
				Activator.logErrorMessage(e.getMessage(), e);
			}
		
		}

		
				 
	}

	
	public void updateDataFromOptions() {
		super.updateDataFromOptions();
		
	}
}
