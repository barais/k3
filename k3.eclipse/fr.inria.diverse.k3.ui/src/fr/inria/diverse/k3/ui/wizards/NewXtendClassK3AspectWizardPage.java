/*******************************************************************************
 * Copyright (c) 2017 Inria and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Inria - initial API and implementation
 *******************************************************************************/

package fr.inria.diverse.k3.ui.wizards;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.equinox.bidi.StructuredTextTypeHandlerFactory;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.ITypeHierarchy;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.ParameterizedType;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.search.IJavaSearchConstants;
import org.eclipse.jdt.core.search.IJavaSearchScope;
import org.eclipse.jdt.core.search.SearchEngine;
import org.eclipse.jdt.internal.corext.codemanipulation.StubUtility;
import org.eclipse.jdt.internal.corext.refactoring.StubTypeContext;
import org.eclipse.jdt.internal.corext.refactoring.TypeContextChecker;
import org.eclipse.jdt.internal.corext.util.JavaModelUtil;
import org.eclipse.jdt.internal.ui.JavaPlugin;
import org.eclipse.jdt.internal.ui.dialogs.FilteredTypesSelectionDialog;
import org.eclipse.jdt.internal.ui.dialogs.StatusInfo;
import org.eclipse.jdt.internal.ui.dialogs.TextFieldNavigationHandler;
import org.eclipse.jdt.internal.ui.refactoring.contentassist.CompletionContextRequestor;
import org.eclipse.jdt.internal.ui.refactoring.contentassist.ControlContentAssistHelper;
import org.eclipse.jdt.internal.ui.refactoring.contentassist.JavaTypeCompletionProcessor;
import org.eclipse.jdt.internal.ui.wizards.NewWizardMessages;
import org.eclipse.jdt.internal.ui.wizards.SuperInterfaceSelectionDialog;
import org.eclipse.jdt.internal.ui.wizards.dialogfields.DialogField;
import org.eclipse.jdt.internal.ui.wizards.dialogfields.IDialogFieldListener;
import org.eclipse.jdt.internal.ui.wizards.dialogfields.IListAdapter;
import org.eclipse.jdt.internal.ui.wizards.dialogfields.IStringButtonAdapter;
import org.eclipse.jdt.internal.ui.wizards.dialogfields.LayoutUtil;
import org.eclipse.jdt.internal.ui.wizards.dialogfields.ListDialogField;
import org.eclipse.jdt.internal.ui.wizards.dialogfields.SelectionButtonDialogField;
import org.eclipse.jdt.internal.ui.wizards.dialogfields.StringButtonDialogField;
import org.eclipse.jdt.ui.dialogs.TypeSelectionExtension;
import org.eclipse.jface.util.BidiUtils;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ISelectionStatusValidator;
import org.eclipse.xtend.ide.wizards.AbstractNewXtendElementWizardPage;
import org.eclipse.xtend.ide.wizards.XtendTypeCreatorUtil;

import fr.inria.diverse.k3.ui.Activator;

/**
 * @author Didier Vojtisek - Initial contribution and API
 */
public class NewXtendClassK3AspectWizardPage extends AbstractNewXtendElementWizardPage {

	public NewXtendClassK3AspectWizardPage() {
		super(CLASS_TYPE, NewXtendClassK3AspectWizard.TITLE);
		this.setTitle(NewXtendClassK3AspectWizard.TITLE);
		this.setDescription(Messages.XTEND_CLASS_K3_ASPECT_WIZARD_DESCRIPTION);
		
		
		AspectFieldsAdapter adapter = new AspectFieldsAdapter();
		
		fAspectClassDialogField= new StringButtonDialogField(adapter);
		fAspectClassDialogField.setDialogFieldListener(adapter);
		fAspectClassDialogField.setLabelText(Messages.ASPECT_DIALOG_LABEL);
		fAspectClassDialogField.setButtonLabel(Messages.ASPECT_DIALOG_BUTTON_LABEL);

	}

	private StringButtonDialogField fAspectClassDialogField;
	

	private StubTypeContext fAspectClassStubTypeContext;

	protected Button 		btnCheckBoxLimitToEMF;
	
	
	public void createControl(Composite parent) {
		Composite composite = createCommonControls(parent);

		createOptionControls(composite, COLS);
		createAspectClassControls(composite, COLS);
		createSuperClassControls(composite, COLS);
		createSuperInterfacesControls(composite, COLS);
		setControl(composite);
	}

	/**
	 * Creates the controls for the superclass name field. Expects a <code>GridLayout</code>
	 * with at least 3 columns.
	 *
	 * @param composite the parent composite
	 * @param nColumns number of columns to span
	 */
	protected void createAspectClassControls(Composite composite, int nColumns) {

		
		
		fAspectClassDialogField.doFillIntoGrid(composite, nColumns);
		Text text= fAspectClassDialogField.getTextControl(null);
		LayoutUtil.setWidthHint(text, getMaxFieldWidth());
		BidiUtils.applyBidiProcessing(text, StructuredTextTypeHandlerFactory.JAVA);

		JavaTypeCompletionProcessor aspectClassCompletionProcessor= new JavaTypeCompletionProcessor(false, false, true);
		aspectClassCompletionProcessor.setCompletionContextRequestor(new CompletionContextRequestor() {
			@Override
			public StubTypeContext getStubTypeContext() {
				return getAspectClassStubTypeContext();
			}
		});

		ControlContentAssistHelper.createTextContentAssistant(text, aspectClassCompletionProcessor);
		TextFieldNavigationHandler.install(text);
	}
	
	protected void createOptionControls(Composite composite, int nColumns){
		btnCheckBoxLimitToEMF = new Button(composite, SWT.CHECK);
		btnCheckBoxLimitToEMF.setText("Limit Aspectized classes to EMF Interfaces");
		btnCheckBoxLimitToEMF.setSelection(true);
		GridData gd= new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gd.horizontalSpan= nColumns;
		btnCheckBoxLimitToEMF.setLayoutData(gd);
		
	}
	
	protected void init(IStructuredSelection selection) {
		super.init(selection);
		if(getTypeName() == null || getTypeName().isEmpty()){
			// force to a better name
			setTypeName("MyAspect", true);
		}
	}
	
	private StubTypeContext getAspectClassStubTypeContext() {
		if (fAspectClassStubTypeContext == null) {
			/* String typeName;
			if (fCurrType != null) {
				typeName= getTypeName();
			} else {
				typeName= JavaTypeCompletionProcessor.DUMMY_CLASS_NAME;
			}
			fAspectClassStubTypeContext= TypeContextChecker.createSuperClassStubTypeContext(typeName, getEnclosingType(), getPackageFragment());
			*/
			fAspectClassStubTypeContext = new StubTypeContext(null, null, null);
		}
		return fAspectClassStubTypeContext;
	}
	
	
	@Override
	protected void doStatusUpdate() {
		IStatus[] status= new IStatus[] {
			fContainerStatus,
			fPackageStatus,
			fTypeNameStatus,
			fSuperClassStatus,
			fSuperInterfacesStatus
		};
		updateStatus(status);
	}

	
	
	@Override
	protected String getPackageDeclaration(String lineSperator) {
		return XtendTypeCreatorUtil.createPackageDeclaration(getTypeName(), getPackageFragment(), getSuperClass(), getSuperInterfaces(), lineSperator);
	}

	@Override
	protected String getTypeContent(String indentation, String lineSperator) {
		return XtendK3CreatorUtil.createAspectClassContent(getTypeName(),getAspectizedClassName(), getSuperClass(), getSuperInterfaces(), indentation, lineSperator);
	}

	@Override
	protected String getElementCreationErrorMessage() {
		return Messages.ERROR_CREATING_CLASS;
	}
	
	
	/**
	 * Returns the type name entered into the type input field.
	 *
	 * @return the type name
	 */
	public String getAspectizedClassName() {
		return fAspectClassDialogField.getText();
	}
	
	/**
	 * Adapter for fields specific to this wizard
	 *
	 */
	private class AspectFieldsAdapter implements IStringButtonAdapter, IDialogFieldListener {

		// -------- IStringButtonAdapter
		public void changeControlPressed(DialogField field) {
			if (field == fAspectClassDialogField) {
				IType type= chooseAspectClass();
				if (type != null) {
					fAspectClassDialogField.setText(SuperInterfaceSelectionDialog.getNameWithTypeParameters(type));
				}
			}
		}

		// -------- IDialogFieldListener
		public void dialogFieldChanged(DialogField field) {
			if (field == fAspectClassDialogField) {
				//fAspectClassStatus= aspectClassChanged();
				//fieldName= SUPER;
			}
		}

	}
	
	/**
	 * Opens a selection dialog that allows to select a super class.
	 *
	 * @return returns the selected type or <code>null</code> if the dialog has been canceled.
	 * The caller typically sets the result to the super class input field.
	 * 	<p>
	 * Clients can override this method if they want to offer a different dialog.
	 * </p>
	 *
	 * @since 3.2
	 */
	protected IType chooseAspectClass() {
		IJavaProject project= getJavaProject();
		if (project == null) {
			return null;
		}

		IJavaElement[] elements= new IJavaElement[] { project };
		IJavaSearchScope scope= SearchEngine.createJavaSearchScope(elements);

		
		TypeSelectionExtension selectionExtention = new TypeSelectionExtension(){
			public ISelectionStatusValidator getSelectionValidator() {
				return new ISelectionStatusValidator(){

					@Override
					public IStatus validate(Object[] selection) {
						// if must restrict to EMF EObject Interfaces
						if(btnCheckBoxLimitToEMF.getSelection()){
							if(selection[0] instanceof IType){
								IType iType = (IType)selection[0];
								try {
									ITypeHierarchy iTypeHierarchy = iType.newSupertypeHierarchy(null);
									for(IType interfac : iTypeHierarchy.getAllInterfaces()){
										if(interfac.getFullyQualifiedName().equals("org.eclipse.emf.ecore.EObject")){
											return Status.OK_STATUS;
										}
									}
									// not found, raise a warning
									return new Status(IStatus.ERROR, JavaPlugin.getPluginId(), IStatus.OK, "Not an EMF EObject", null);
								} catch (JavaModelException e) {
									Activator.logErrorMessage(e.getMessage(), e);
									return Status.OK_STATUS;
								}
							}
						}
						return Status.OK_STATUS;
					}
				};
			}
		};
		FilteredTypesSelectionDialog dialog;
		// if must restrict to EMF EObject Interfaces
		if(btnCheckBoxLimitToEMF.getSelection()){
			dialog= new FilteredTypesSelectionDialog(getShell(), false,
					getWizard().getContainer(), scope, IJavaSearchConstants.INTERFACE, selectionExtention);
		}
		else{
			dialog= new FilteredTypesSelectionDialog(getShell(), false,
					getWizard().getContainer(), scope, IJavaSearchConstants.CLASS_AND_INTERFACE, selectionExtention);
		}
		dialog.setTitle(Messages.NewTypeWizardPage_AspectClassDialog_title);
		dialog.setMessage(Messages.NewTypeWizardPage_AspectClassDialog_message);
		dialog.setInitialPattern(getAspectizedClassName());

		if (dialog.open() == Window.OK) {
			return (IType) dialog.getFirstResult();
		}
		return null;
	}
	
}
