package tech.murilo.stringsconcatbuilder;

import java.util.Random;

public enum LoremIpsum {
	
	LOREM_01(LoremIpsumText.LOREM_TEXT_01),
	LOREM_02(LoremIpsumText.LOREM_TEXT_02),
	LOREM_03(LoremIpsumText.LOREM_TEXT_03),
	LOREM_04(LoremIpsumText.LOREM_TEXT_04),
	LOREM_05(LoremIpsumText.LOREM_TEXT_05);
	
	private String text;
	
	private LoremIpsum(String text) {
		this.text = text;
	}
	
	public String getText() {
		return text;
	}
	
	public static LoremIpsum random() {
		var index = new Random().ints(0, values().length - 1)
			      .findFirst()
			      .getAsInt();
		
		return values()[index];
	}
}
