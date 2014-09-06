package component.testcase;

import static org.junit.Assert.*;

import org.junit.Test;

import component.Translator;

public class TestTranslator {

	@Test
	public void test() {
		try{
			Translator.main(null);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
