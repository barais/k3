/*
* generated by Xtext
*/
package insa.ui.labeling

import com.google.inject.Inject
import expArithm.Plus
import expArithm.Moins
import expArithm.Mult
import expArithm.Division

/**
 * Provides labels for a EObjects.
 * 
 * see http://www.eclipse.org/Xtext/documentation.html#labelProvider
 */
class ExpArithmLabelProvider extends org.eclipse.xtext.ui.label.DefaultEObjectLabelProvider {

	@Inject
	new(org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider delegate) {
		super(delegate);
	}

	def text(Plus sc) {"Plus"}
	def text(Moins sc) {"Minus"}
	def text(Mult sc) {"Mult"}
	def text(Division sc) {"Division"}
}