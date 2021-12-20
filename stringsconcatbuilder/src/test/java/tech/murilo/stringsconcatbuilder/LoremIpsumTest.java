package tech.murilo.stringsconcatbuilder;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class LoremIpsumTest {
	
	@Test
	public void loremIpsumAleatorio() {
		var lorems = Arrays.asList(
			LoremIpsum.LOREM_01,
			LoremIpsum.LOREM_02,
			LoremIpsum.LOREM_03,
			LoremIpsum.LOREM_04,
			LoremIpsum.LOREM_05
		);
		
		var lorem = LoremIpsum.random();

		assertTrue(lorems.contains(lorem));
	}

}
