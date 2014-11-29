package xml.testcase;

import org.junit.Test;

import xml.XMLBeautifier;

public class TestXMLBeautifier {

	@Test
	public void test() {
		XMLBeautifier b = new XMLBeautifier();
		System.out.println(b.beautifier("<?xml version=\"1.0\" encoding=\"UTF-8\"?> <!--Anagrafica del clienti del mercato--> <anagrafica> <testata> <nomemercato id=\"007\">Mercato di test</nomemercato> <data>Giovedi 18 dicembre 2003 16.05.29</data> </testata> <record> <codice_cliente>5</codice_cliente> <rag_soc>Miami American Cafe</rag_soc> <codice_fiscale>IT07654930130</codice_fiscale> <indirizzo tipo=\"casa\">Viale Carlo Espinasse 5, Como</indirizzo> <num_prodotti>13</num_prodotti> </record> <record> <codice_cliente>302</codice_cliente> <rag_soc>Filiberto Gilardi</rag_soc> <codice_fiscale>IT87654770157</codice_fiscale> <indirizzo tipo=\"ufficio\">Via Biancospini 20, Messina</indirizzo> <num_prodotti>8</num_prodotti> </record> <record> <codice_cliente>1302</codice_cliente> <rag_soc>Eidon</rag_soc> <codice_fiscale>IT887511231</codice_fiscale> <indirizzo tipo=\"ufficio\">Via Bassini 17/2, Milano</indirizzo> <num_prodotti>18</num_prodotti> </record> <record> <codice_cliente>202</codice_cliente> <rag_soc>SkillNet</rag_soc> <codice_fiscale>IT887642131</codice_fiscale> <indirizzo tipo=\"ufficio\">Via Chiasserini 11A, Milano</indirizzo> <num_prodotti>24</num_prodotti> </record> <record> <codice_cliente>12</codice_cliente> <rag_soc>Eidon</rag_soc> <codice_fiscale>IT04835710965</codice_fiscale> <indirizzo tipo=\"casa\">Via Cignoli 17/2, Roma</indirizzo> <num_prodotti>1112</num_prodotti> </record> <record> <codice_cliente>5</codice_cliente> <rag_soc>Miami American Cafe</rag_soc> <codice_fiscale>IT07654930130</codice_fiscale> <indirizzo tipo=\"casa\">Viale Carlo Espinasse 5, Como</indirizzo> <num_prodotti>13</num_prodotti> </record> <record> <codice_cliente>302</codice_cliente> <rag_soc>Filiberto Gilardi</rag_soc> <codice_fiscale>IT87654770157</codice_fiscale> <indirizzo tipo=\"ufficio\">Via Biancospini 20, Messina</indirizzo> <num_prodotti>8</num_prodotti> </record> <record> <codice_cliente>1302</codice_cliente> <rag_soc>Eidon</rag_soc> <codice_fiscale>IT887511231</codice_fiscale> <indirizzo tipo=\"ufficio\">Via Bassini 17/2, Milano</indirizzo> <num_prodotti>18</num_prodotti> </record> <record> <codice_cliente>202</codice_cliente> <rag_soc>SkillNet</rag_soc> <codice_fiscale>IT887642131</codice_fiscale> <indirizzo tipo=\"ufficio\">Via Chiasserini 11A, Milano</indirizzo> <num_prodotti>24</num_prodotti> </record> <record> <codice_cliente>202</codice_cliente> <rag_soc>SkillNet</rag_soc> <codice_fiscale>IT887642131</codice_fiscale> <indirizzo tipo=\"ufficio\">Via Chiasserini 11A, Milano</indirizzo> <num_prodotti>24</num_prodotti> </record> <record> <codice_cliente>12</codice_cliente> <rag_soc>Eidon</rag_soc> <codice_fiscale>IT04835710965</codice_fiscale> <indirizzo tipo=\"casa\">Via Cignoli 17/2, Roma</indirizzo> <num_prodotti>1112</num_prodotti> </record> </anagrafica> "));
		
	}

}
