package teste.selenium;

import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

class testFirefox {
	
	static WebDriver driver;
	private static Document doc;
	private static NodeList exp;
	private static NodeList rad;
	private static NodeList log;
	private static NodeList listaProcedimentos;
	

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\gabri\\eclipse-workspace\\selenium\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.get("https://www.calculadoraonline.com.br/basica");
		File inputFile = new File("C:\\Users\\gabri\\eclipse-workspace\\selenium\\Dados.xml");
		DocumentBuilderFactory dbFactory =	DocumentBuilderFactory.newInstance();

		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		doc = dBuilder.parse(inputFile);
		doc.getDocumentElement().normalize();
		listaProcedimentos =doc.getElementsByTagName("procedimento");
		
		
		Element procedimento = (Element)listaProcedimentos.item(0);
		exp = procedimento.getElementsByTagName("caso");
		
		Element procedimentorad = (Element)listaProcedimentos.item(1);
		rad = procedimentorad.getElementsByTagName("caso");
		
		Element procedimentolog = (Element)listaProcedimentos.item(2);
		log = procedimentolog.getElementsByTagName("caso");
		
	
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		driver.close();
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
		
	}

	@ParameterizedTest
	@ValueSource(strings = {"0","1","2","3","4"})
	void test(int codCaso) {
		//tryit.asp?filename=tryhow_custom_select
		JavascriptExecutor js = (JavascriptExecutor) driver;
		//js.executeScript("window.scrollBy(0,200);");
		js.executeScript("window.scrollBy(0,400)");
		
		Element casoexp = (Element) exp.item(codCaso);

		
		driver.findElement(By.id("b31")).click();
		driver.findElement(By.id("cx31_0")).sendKeys(casoexp.getElementsByTagName("base").item(0).getTextContent());
		driver.findElement(By.id("cx31_1")).sendKeys(casoexp.getElementsByTagName("expoente").item(0).getTextContent());
		driver.findElement(By.cssSelector("button.uk-button.uk-button-default")).click();
		String resultadoexp = driver.findElement(By.id("TIExp")).getAttribute("value");
		assertEquals(casoexp.getElementsByTagName("resultadoEsperado").item(0).getTextContent(), resultadoexp);
	}
	
	
	
		@ParameterizedTest
		@ValueSource(strings = {"0","1","2","3","4"})
		void test2(int codCaso) {
			//tryit.asp?filename=tryhow_custom_select
			JavascriptExecutor js = (JavascriptExecutor) driver;
			//js.executeScript("window.scrollBy(0,200);");
			js.executeScript("window.scrollBy(0,400)");
			
			Element casorad = (Element) rad.item(codCaso);

			driver.findElement(By.id("b23")).click();
			driver.findElement(By.id("cx23_0")).sendKeys(casorad.getElementsByTagName("radicando").item(0).getTextContent());
			driver.findElement(By.id("cx23_1")).sendKeys(casorad.getElementsByTagName("indice").item(0).getTextContent());
			driver.findElement(By.cssSelector("button.uk-button.uk-button-default")).click();
			String resultadorad = driver.findElement(By.id("TIExp")).getAttribute("value");
			assertEquals(casorad.getElementsByTagName("resultadoEsperado").item(0).getTextContent(), resultadorad);

	}
		
		@ParameterizedTest
		@ValueSource(strings = {"0","1","2","3","4"})
		void test3(int codCaso) {
			//tryit.asp?filename=tryhow_custom_select
			JavascriptExecutor js = (JavascriptExecutor) driver;
			//js.executeScript("window.scrollBy(0,200);");
			js.executeScript("window.scrollBy(0,400)");
			
			Element casolog = (Element) log.item(codCaso);

			driver.findElement(By.id("b8")).click();
			driver.findElement(By.id("cx8_0")).sendKeys(casolog.getElementsByTagName("logaritmando").item(0).getTextContent());
			driver.findElement(By.id("cx8_1")).sendKeys(casolog.getElementsByTagName("base").item(0).getTextContent());
			driver.findElement(By.cssSelector("button.uk-button.uk-button-default")).click();
			String resultadolog = driver.findElement(By.id("TIExp")).getAttribute("value");
			assertEquals(casolog.getElementsByTagName("resultadoEsperado").item(0).getTextContent(), resultadolog);

	}

}