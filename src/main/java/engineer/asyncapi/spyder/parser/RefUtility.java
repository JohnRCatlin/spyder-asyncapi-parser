package engineer.asyncapi.spyder.parser;

/**
 * 
 * @author johncatlin
 *
 */
class RefUtility {

	static final String ensureSafeLocalReference(final String ref) {
		return (ref.contains(":") || ref.startsWith("#") || ref.startsWith("/") || ref.indexOf(".") <= 0) ? ref
				: "./" + ref;
	}

	static final boolean isRelative(final String ref) {
		return (ref != null && ref.indexOf('.') == -1 && ref.indexOf('/') == -1);
	}

	private RefUtility() {
		// don't instantiate utility.
	}
}
