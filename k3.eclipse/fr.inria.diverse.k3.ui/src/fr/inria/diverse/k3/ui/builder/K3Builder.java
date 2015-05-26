package fr.inria.diverse.k3.ui.builder;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.osgi.framework.BundleException;

import fr.inria.diverse.commons.eclipse.pde.manifest.ManifestChanger;
import fr.inria.diverse.commons.eclipse.pde.manifest.ManifestChangerExportPackage;
//import javax.xml.parsers.ParserConfigurationException;
//import javax.xml.parsers.SAXParser;
//import javax.xml.parsers.SAXParserFactory;
//import org.xml.sax.SAXException;
//import org.xml.sax.SAXParseException;
//import org.xml.sax.helpers.DefaultHandler;


/**
 * This builder is for handling automated and incremental build of K3 elements that 
 * aren't already taken into account by the xTend annotation processor
 *
 */
public class K3Builder extends IncrementalProjectBuilder {

	class K3BuilderDeltaVisitor implements IResourceDeltaVisitor {
		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.core.resources.IResourceDeltaVisitor#visit(org.eclipse.core.resources.IResourceDelta)
		 */
		public boolean visit(IResourceDelta delta) throws CoreException {
			IResource resource = delta.getResource();
			switch (delta.getKind()) {
			case IResourceDelta.ADDED:
				// handle added resource
				//checkXML(resource);
				break;
			case IResourceDelta.REMOVED:
				// handle removed resource
				break;
			case IResourceDelta.CHANGED:
				// handle changed resource
				//checkXML(resource);
				new AspectMappingPropertiesChecker(K3Builder.this).checkK3AspectMappingPropertiesForGeneratedJava(resource);
				
				if (resource instanceof IFile && resource.getName().endsWith(".xtend")) {
					IProject project = resource.getProject();
					if(project.hasNature("org.eclipse.pde.PluginNature")){
						IFolder root = project.getFolder("xtend-gen");
						Set<String> packs = findPackage(root);
						updateManifest(project, packs);
					}
				}
				
				break;
			}
			//return true to continue visiting children.
			return true;
		}

		private void updateManifest(IProject project, Set<String> packs) {
			IFile manifest = project.getFile("META-INF/MANIFEST.MF");
			ManifestChanger changer = new ManifestChanger(manifest);
			ManifestChangerExportPackage updater = new ManifestChangerExportPackage(changer);
			
			for(String pack : packs){
				try {
					updater.add(pack);
					changer.commit();
				} catch (BundleException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (CoreException e) {
					e.printStackTrace();
				}
			}
		}
		
		/**
		 * Search for package in @resource.
		 * Return empty list if none.
		 * 
		 * @param resource Explored resource 
		 * @param folderName Name of the containing package
		 */
		private Set<String> findPackage(IFolder root) {
			Set<String> res = new HashSet<String>();
			
			try {
				for(IResource member : root.members()){
					if(member.getType() == IResource.FOLDER){
						Set<String> subRes = findPackageRec(member, member.getName());
						res.addAll(subRes);
					}
				}
			} catch (CoreException e) {e.printStackTrace();}
			
			return res;
		}

		/**
		 * Recursive search for package in @resource.
		 * Return empty list if none.
		 * 
		 * @param resource Explored resource 
		 * @param folderName Name of the containing package
		 */
		private Set<String> findPackageRec(IResource resource, String packageName) {
			
			Set<String> res = new HashSet<String>();
			
			if(resource.getType() == IResource.FOLDER){
				try {
					for(IResource member : ((IFolder)resource).members()){
						if(member.getType() == IResource.FOLDER){
							Set<String> subRes = findPackageRec(member, packageName+"."+member.getName());
							res.addAll(subRes);
						}
						else if(member.getType() == IResource.FILE && member.getFileExtension().equals("java")){
							res.add(packageName);
						}
					}
				} catch (CoreException e) {e.printStackTrace();}
			}
			
			return res;
		}
	}

	class K3BuilderResourceVisitor implements IResourceVisitor {
		public boolean visit(IResource resource) {
			new AspectMappingPropertiesChecker(K3Builder.this).checkK3AspectMappingPropertiesForGeneratedJava(resource);
			//return true to continue visiting children.
			return true;
		}
	}

//	class XMLErrorHandler extends DefaultHandler {
//		
//		private IFile file;
//
//		public XMLErrorHandler(IFile file) {
//			this.file = file;
//		}
//
//		private void addMarker(SAXParseException e, int severity) {
//			K3Builder.this.addMarker(file, e.getMessage(), e
//					.getLineNumber(), severity);
//		}
//
//		public void error(SAXParseException exception) throws SAXException {
//			addMarker(exception, IMarker.SEVERITY_ERROR);
//		}
//
//		public void fatalError(SAXParseException exception) throws SAXException {
//			addMarker(exception, IMarker.SEVERITY_ERROR);
//		}
//
//		public void warning(SAXParseException exception) throws SAXException {
//			addMarker(exception, IMarker.SEVERITY_WARNING);
//		}
//	}

	public static final String BUILDER_ID = "fr.inria.diverse.k3.ui.k3Builder";

	private static final String MARKER_TYPE = "fr.inria.diverse.k3.ui.k3Problem";

//	private SAXParserFactory parserFactory;

	
	/**
	 * convenient method for marking file associated with this builder
	 * @param file
	 * @param message
	 * @param lineNumber
	 * @param severity
	 */
	public void addMarker(IFile file, String message, int lineNumber,
			int severity) {
		try {
			IMarker marker = file.createMarker(MARKER_TYPE);
			marker.setAttribute(IMarker.MESSAGE, message);
			marker.setAttribute(IMarker.SEVERITY, severity);
			if (lineNumber == -1) {
				lineNumber = 1;
			}
			marker.setAttribute(IMarker.LINE_NUMBER, lineNumber);
		} catch (CoreException e) {
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.internal.events.InternalBuilder#build(int,
	 *      java.util.Map, org.eclipse.core.runtime.IProgressMonitor)
	 */
	protected IProject[] build(int kind, Map args, IProgressMonitor monitor)
			throws CoreException {
		if (kind == FULL_BUILD) {
			fullBuild(monitor);
		} else {
			IResourceDelta delta = getDelta(getProject());
			if (delta == null) {
				fullBuild(monitor);
			} else {
				incrementalBuild(delta, monitor);
			}
		}
		return null;
	}

	protected void clean(IProgressMonitor monitor) throws CoreException {
		// delete markers set and files created
		getProject().deleteMarkers(MARKER_TYPE, true, IResource.DEPTH_INFINITE);
		
		// delete properties file
		IFile mappingPropertyFile = getProject().getFile("/META-INF/xtend-gen/"+getProject().getName()+".k3_aspect_mapping.properties");
		if(mappingPropertyFile.exists()){
			mappingPropertyFile.delete(true, monitor);
		}
		
	}
	
	/**
	 * convenient method for removing marks associated with this builder
	 * @param file
	 * @param message
	 * @param lineNumber
	 * @param severity
	 */
	public void deleteMarkers(IFile file) {
		try {
			file.deleteMarkers(MARKER_TYPE, false, IResource.DEPTH_ZERO);
		} catch (CoreException ce) {
		}
	}

	protected void fullBuild(final IProgressMonitor monitor)
			throws CoreException {
		try {
			getProject().accept(new K3BuilderResourceVisitor());
		} catch (CoreException e) {
		}
	}
/*
	private SAXParser getParser() throws ParserConfigurationException,
			SAXException {
		if (parserFactory == null) {
			parserFactory = SAXParserFactory.newInstance();
		}
		return parserFactory.newSAXParser();
	}*/

	protected void incrementalBuild(IResourceDelta delta,
			IProgressMonitor monitor) throws CoreException {
		// the visitor does the work.
		delta.accept(new K3BuilderDeltaVisitor());
	}
}
