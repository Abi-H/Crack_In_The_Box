package algorithm_detector;

import java.lang.reflect.*;
import static java.lang.System.out;

public class Reflection {

	public static void main(String[] args) throws NoSuchMethodException, SecurityException {

		Class<Algorithm> c = Algorithm.class;
		
		out.println(c.getPackage());
		out.print("\npublic class " + c.getSimpleName() + " implements ");
		
		processInterface(c);
		processConstructor(c);
		processFields(c);
		out.println("\n");
		processMethods(c);

		out.println("}");
	}

	private static void processMethods(Class<Algorithm> c) {
		Method[] methods = c.getDeclaredMethods();

		for (int i = 0; i < methods.length; i++) {
			String replacement = methods[i].toGenericString();

			if (replacement.contains("java.lang.")) {
				replacement = editString(replacement, "java.lang.", "");
			}

			if (replacement.contains("algorithm_detector")) {
				replacement = editString(replacement, "algorithm_detector.Algorithm.", "");
			}

			out.println("\t" + replacement);
		}
	}

	private static void processFields(Class<Algorithm> c) {
		Field[] fields = c.getDeclaredFields();

		for (int i = 0; i < fields.length; i++) {
			String replacement = fields[i].toGenericString();

			if (replacement.contains("algorithm_detector")) {
				replacement = editString(replacement, "algorithm_detector.Algorithm.", "");
			}

			if (replacement.contains("java.lang.String")) {
				replacement = editString(replacement, "java.lang.String", "");
			}

			out.println("\t" + replacement);
		}
	}

	private static void processConstructor(Class<Algorithm> c) throws NoSuchMethodException, SecurityException {
		Constructor<Algorithm> constructor = c.getConstructor(String.class, int.class, String.class);
		String replacement = constructor.toGenericString();

		if (replacement.contains("algorithm_detector")) {
			replacement = editString(replacement, " algorithm_detector.", " ");
		}

		if (replacement.contains("java.lang.String")) {
			replacement = editString(replacement, "java.lang.", "");
		}

		out.println("\n\t" + replacement + "\n");
	}

	private static void processInterface(Class<Algorithm> c) {
		@SuppressWarnings("rawtypes")
		Class[] interfaces = c.getInterfaces();
		String replacement = interfaces[0].toGenericString();
		
		if (replacement.contains("interface")) {
			replacement = editString(replacement, "public abstract interface algorithm_detector.", "");
		}
		
		out.print(replacement + " {\n");
	}

	private static String editString(String originalString, String match, String replacement) {
		return originalString.replaceAll(match, replacement);
	}

}
