package test.composition

import fr.inria.diverse.k3.al.annotationprocessor.Composition
import java.util.List
import org.junit.jupiter.api.Test

import static org.junit.jupiter.api.Assertions.*;

class TestComposition {
	@Test
	def void testComposition1() {
		val composed1 = new Composed1
		val composed2 = new Composed2
		val compose1 = new Compose

		composed1.attr1 = compose1
		assertEquals(compose1, composed1.attr1)

		composed2.attr2 = compose1
		assertEquals(compose1, composed2.attr2)
		assertNull(composed1.attr1)
	}

	@Test
	def void testComposition2() {
		val composed1 = new Composed1
		val composed2 = new Composed2
		val compose1 = new Compose
		val compose2 = new Compose

		composed1.attr1 = compose1
		assertEquals(compose1, composed1.attr1)
		composed2.attr2 = compose2
		assertEquals(compose2, composed2.attr2)

		composed2.attr2 = compose1
		assertEquals(compose1, composed2.attr2)
		assertNull(composed1.attr1)
		assertNull(compose2._kContainer)
	}
}


class Compose {
}

class Composed1 {
	@Composition
	public var Compose attr1

	@Composition
	public var Compose attr1b

	new() {
		attr1 = null
		attr1b = null
	}
}


class Composed3 {
//	@Composition
	public var List<Compose> attr1
}


class Composed2 {
	@Composition
	public var Compose attr2

	new() {
		attr2 = null
	}
}

