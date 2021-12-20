package tech.murilo.stringsconcatbuilder;

public class TextGenerator {
	
	public static String generateParagraphsTextWithStringConcat(int paragraphCount) {
		String text = "";
		
		for (int i = 0; i < paragraphCount; i++) {
			text += LoremIpsum.random().getText();
		}
		
		return text;
	}
	
	public static String generateParagraphsTextWithStringBuilder(int paragraphCount) {
		var text = new StringBuilder();
		
		for (int i = 0; i < paragraphCount; i++) {
			text.append(LoremIpsum.random().getText());
		}
		
		return text.toString();
	}
	
	public static String generateParagraphsTextWithStringBuffer(int paragraphCount) {
		var text = new StringBuffer();
		
		for (int i = 0; i < paragraphCount; i++) {
			text.append(LoremIpsum.random().getText());
		}
		
		return text.toString();
	}

}
