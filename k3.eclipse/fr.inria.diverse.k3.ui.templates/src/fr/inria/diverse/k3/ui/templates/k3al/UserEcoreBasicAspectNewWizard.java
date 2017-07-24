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


import org.eclipse.gemoc.commons.eclipse.pde.wizards.pages.pde.ui.BaseProjectWizardFields;
import org.eclipse.gemoc.commons.eclipse.pde.wizards.pages.pde.ui.templates.ITemplateSection;
import org.eclipse.gemoc.commons.eclipse.pde.wizards.pages.pde.ui.templates.NewProjectTemplateWizard;
import fr.inria.diverse.k3.ui.templates.K3TemplateMessages;

public class UserEcoreBasicAspectNewWizard extends NewProjectTemplateWizard {
	/**
	 * Constructor for HelloWorldNewWizard.
	 */
	public UserEcoreBasicAspectNewWizard() {
		super();
	}

	public void init(BaseProjectWizardFields data) {
		super.init(data);
		setWindowTitle(K3TemplateMessages.UserEcoreBasicAspectTemplate_wtitle);
	}

	/*
	 * @see NewExtensionTemplateWizard#createTemplateSections()
	 */
	public ITemplateSection[] createTemplateSections() {
		return new ITemplateSection[] {new UserEcoreBasicAspectTemplate(this)};
	}

	
	
	
}
