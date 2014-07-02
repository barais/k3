package fr.inria.diverse.k3.ui.builder;

import java.io.IOException;
import java.util.Map;

//import javax.xml.parsers.ParserConfigurationException;
//import javax.xml.parsers.SAXParser;
//import javax.xml.parsers.SAXParserFactory;

import java.util.Map.Entry;
import java.util.Properties;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
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
				checkK3AspectMappingPropertiesForGeneratedJava(resource);
				break;
			}
			//return true to continue visiting children.
			return true;
		}
	}

	class K3BuilderResourceVisitor implements IResourceVisitor {
		public boolean visit(IResource resource) {
			checkK3AspectMappingPropertiesForGeneratedJava(resource);
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

	private void addMarker(IFile file, String message, int lineNumber,
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

	class JavaAspectFinderResourceVisitor implements IResourceVisitor {
		protected String searchedJavaFile;
		protected boolean result = false;
		public JavaAspectFinderResourceVisitor(String searchedJavaFile){
			this.searchedJavaFile = searchedJavaFile;
		}
		public boolean visit(IResource resource) {
			if(resource instanceof IFile && resource.getName().endsWith(".java")){
				if(resource.getProjectRelativePath().toString().contains(searchedJavaFile)){
					result = true;
					return false;
				}
			}
			//return true to continue visiting children.
			// continue only if not found
			return !result;
		}
		public boolean getSearchResult(){return result;}
	}
	
	void checkK3AspectMappingPropertiesForGeneratedJava(IResource resource) {
		
		// triggered when 
		if (resource instanceof IFile && resource.getName().endsWith(getProject().getName()+".k3_aspect_mapping.properties")) {
			IFile file = (IFile) resource;
			deleteMarkers(file);
			Properties properties = new Properties();
			try {
				// check that a java file exist for each of the mapping aspects
				properties.load(file.getContents());
				int mostProbableLine = 1;
				for (Entry<Object, Object> entrySet : properties.entrySet()) {
					mostProbableLine++;
					String[] values = entrySet.getValue().toString().split(", ");
					for (String value : values) {
						// search for a java class with that name in the project
						String searchedJavaFile = value.replaceAll("\\.", "/");
						JavaAspectFinderResourceVisitor finder = new JavaAspectFinderResourceVisitor(searchedJavaFile+".java");
						resource.getProject().accept(finder);
						
						if(!finder.getSearchResult()){
							addMarker(file, "Aspect "+value+" not found, clean project recommanded", mostProbableLine, IMarker.SEVERITY_WARNING);
						}
						
					}
				}
				
			} catch (IOException e) {
				
			} catch (CoreException e) {
				
			}
			
		}
	}

	private void deleteMarkers(IFile file) {
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
