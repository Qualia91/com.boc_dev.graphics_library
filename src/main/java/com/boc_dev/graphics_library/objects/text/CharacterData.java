package com.boc_dev.graphics_library.objects.text;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class CharacterData {

	private static final int SPACE_ASCII = 64;

	// consts used in file
	private static final int PAD_TOP = 0;
	private static final int PAD_LEFT = 1;
	private static final int PAD_BOTTOM = 2;
	private static final int PAD_RIGHT = 3;
	private static final int DESIRED_PADDING = 3;
	private static final String SPLITTER = " ";
	private static final String NUMBER_SEPARATOR = ",";
	private final String textDescription;
	private final float base;

	// padding values from file
	private int[] padding = new int[4];
	private int paddingWidth;
	private int paddingHeight;
	private final HashMap<Integer, Character> characterAsciiMap = new HashMap<>();
	private BufferedReader reader;
	private final Map<String, String> values = new HashMap<>();
	private int spaceWidth;

	public CharacterData(String descriptor, InputStream is) {

		this.textDescription = descriptor;

		openFile(is);

		// load first line and get the padding values
		getPadding();

		// load next line and get image width
		processNextLine();
		int imageWidth = getValueOfVariable("scaleW");
		// get base. Used to normalise size of text to 1 from file input
		this.base = getValueOfVariable("base");

		// read next 2 lines and discard
		processNextLine();
		processNextLine();

		// now load character data
		while (processNextLine()) {
			Character c = loadCharacter(imageWidth);
			if (c != null) {
				characterAsciiMap.put(c.getId(), c);
			}
		}

		close();
	}

	public int getSpaceWidth() {
		return spaceWidth;
	}

	public Character getCharacter(int ascii) {
		return characterAsciiMap.get(ascii);
	}

	/**
	 * Loads all the data about one character in the texture atlas.
	 * The effects of padding are also removed from the data.
	 *
	 * @param imageSize
	 *            - the size of the texture atlas in pixels.
	 * @return The data about the character.
	 */
	private Character loadCharacter(int imageSize) {
		int id = getValueOfVariable("id");
		if (id == SPACE_ASCII) {
			this.spaceWidth = (getValueOfVariable("xadvance") - paddingWidth);
			return null;
		}
		float xTex = ((float)(getValueOfVariable("x") + (padding[PAD_LEFT] - DESIRED_PADDING))) / imageSize;
		float yTex = ((float)(getValueOfVariable("y") + (padding[PAD_TOP] - DESIRED_PADDING))) / imageSize;
		float width = getValueOfVariable("width") - (paddingWidth - (2 * DESIRED_PADDING));
		float height = getValueOfVariable("height") - ((paddingHeight) - (2 * DESIRED_PADDING));
		float xTexSize = width / imageSize;
		float yTexSize = height / imageSize;
		float xOff = (getValueOfVariable("xoffset") + padding[PAD_LEFT] - DESIRED_PADDING);
		float yOff = (getValueOfVariable("yoffset") + (padding[PAD_TOP] - DESIRED_PADDING));
		float xAdvance = (getValueOfVariable("xadvance") - paddingWidth);
		return new Character(id,
				xTex,
				yTex,
				xTexSize,
				yTexSize,
				xOff,
				yOff,
				width,
				height,
				xAdvance,
				base);
	}

	private void getPadding() {
		// load line
		processNextLine();

		// get padding values needed
		this.padding = getValuesOfVariable("padding");
		this.paddingWidth = padding[PAD_LEFT] + padding[PAD_RIGHT];
		this.paddingHeight = padding[PAD_TOP] + padding[PAD_BOTTOM];
	}

	/**
	 * Gets the {@code int} value of the variable with a certain name on the
	 * current line.
	 *
	 * @param variable
	 *            - the name of the variable.
	 * @return The value of the variable.
	 */
	private int getValueOfVariable(String variable) {
		return Integer.parseInt(values.get(variable));
	}

	/**
	 * Gets the array of ints associated with a variable on the current line.
	 *
	 * @param variable
	 *            - the name of the variable.
	 * @return The int array of values associated with the variable.
	 */
	private int[] getValuesOfVariable(String variable) {
		String[] numbers = values.get(variable).split(NUMBER_SEPARATOR);
		int[] actualValues = new int[numbers.length];
		for (int i = 0; i < actualValues.length; i++) {
			actualValues[i] = Integer.parseInt(numbers[i]);
		}
		return actualValues;
	}

	/**
	 * Read in the next line and store the variable values.
	 *
	 * @return {@code true} if the end of the file hasn't been reached.
	 */
	private boolean processNextLine() {
		values.clear();
		String line = null;
		try {
			line = reader.readLine();
		} catch (IOException e1) {
		}
		if (line == null) {
			return false;
		}
		for (String part : line.split(SPLITTER)) {
			String[] valuePairs = part.split("=");
			if (valuePairs.length == 2) {
				values.put(valuePairs[0], valuePairs[1]);
			}
		}
		return true;
	}

	/**
	 * Opens the font file, ready for reading
	 */
	private void openFile(InputStream is) {
		try {
			reader = new BufferedReader(new InputStreamReader(is));
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Couldn't read font meta file!");
		}
	}

	/**
	 * Closes the font file after finishing reading
	 */
	private void close() {
		try {
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getStringDescriptor() {
		return textDescription;
	}
}
