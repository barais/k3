package fr.inria.diverse.k3.sle.processors

import com.google.inject.Inject

import fr.inria.diverse.k3.sle.metamodel.k3sle.ModelTypingSpace

import java.util.List

import org.apache.log4j.Logger

import org.eclipse.xtext.resource.DerivedStateAwareResource

import org.eclipse.xtext.util.internal.Stopwatches

import org.eclipse.xtext.xbase.jvmmodel.JvmModelAssociator

import static org.eclipse.xtext.util.internal.Stopwatches.*

class K3SLEDerivedStateComputer extends JvmModelAssociator
{
	List<K3SLEProcessor> processors = newArrayList

	static final Logger log = Logger.getLogger(K3SLEDerivedStateComputer)

	// FIXME: Because Guice's Multibinders aren't available,
	//         quick & dirty solution
	@Inject
	new(ModelLoader l, ASTCompleter c, ASTValidator v) {
		processors += l
		processors += c
		processors += v
	}

	override discardDerivedState(DerivedStateAwareResource resource) {
		super.discardDerivedState(resource)

		// Post-inferring processors
		val root = resource.contents.head as ModelTypingSpace
		processors.forEach[postProcess(root)]

		// Print stop watches metrics
		log.debug(Stopwatches.getPrintableStopwatchData)
	}

	override installDerivedState(DerivedStateAwareResource resource, boolean preLinkingPhase) {
		// Activate stop watches before anything happens
		Stopwatches.enabled = true

		// Pre-inferring processors
		val root = resource.contents.head as ModelTypingSpace
		processors.forEach[preProcess(root)]

		super.installDerivedState(resource, preLinkingPhase)
	}
}