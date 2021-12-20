package tech.murilo.stringsconcatbuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TextGeneratorTest {
	
	@Test
	public void cemParagrafosDeTextoComStringConcat() {
		var text = TextGenerator.generateParagraphsTextWithStringConcat(100);
		assertEquals(100, text.chars().filter(ch -> ch == '\n').count());
	}
	
	@Test
	public void cemParagrafosDeTextoComStringBuilder() {
		var text = TextGenerator.generateParagraphsTextWithStringBuilder(100);
		assertEquals(100, text.chars().filter(ch -> ch == '\n').count());
	}
	
	@Test
	public void milParagrafosDeTextoComStringConcat() {
		var text = TextGenerator.generateParagraphsTextWithStringConcat(1000);
		assertEquals(1000, text.chars().filter(ch -> ch == '\n').count());
	}
	
	@Test
	public void milParagrafosDeTextoComStringBuilder() {
		var text = TextGenerator.generateParagraphsTextWithStringBuilder(1000);
		assertEquals(1000, text.chars().filter(ch -> ch == '\n').count());
	}
	
	@Test
	public void dezMilParagrafosDeTextoComStringConcat() {
		var text = TextGenerator.generateParagraphsTextWithStringConcat(10000);
		assertEquals(10000, text.chars().filter(ch -> ch == '\n').count());
	}
	
	@Test
	public void dezMilParagrafosDeTextoComStringBuilder() {
		var text = TextGenerator.generateParagraphsTextWithStringBuilder(10000);
		assertEquals(10000, text.chars().filter(ch -> ch == '\n').count());
	}
	
	@Test
	public void dezMilParagrafosDeTextoComStringBuffer() {
		var text = TextGenerator.generateParagraphsTextWithStringBuffer(10000);
		assertEquals(10000, text.chars().filter(ch -> ch == '\n').count());
	}
}
