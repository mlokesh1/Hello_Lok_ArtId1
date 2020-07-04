package Hello_Lok_Package;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestReporter;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

@DisplayName("When running MathaUtilsTest") 
class MathUtilsTest {

	MathUtils mUtils;
	
	TestInfo testInfo;
	TestReporter testReporter;
	
	@BeforeAll
	static void beforeAllInit() {
		System.out.println("This needs to run before all");
	}
	
	@BeforeEach
	void init(TestInfo testInfo, TestReporter testReporter) {
		mUtils = new MathUtils();
		this.testInfo = testInfo;
		this.testReporter = testReporter;
		testReporter.publishEntry("Running" + testInfo.getDisplayName() + "with tag" + testInfo.getTags());
	}
	
	@AfterEach
	void cleanup() {
		System.out.println("...Cleaning Up...");
	}
	
	@Test
	@Tag("Math")		//used to selectively run the test methods
	@DisplayName("Testing add Method")     // this displayed instead of method name under run stats
	void testAdd() {
		//System.out.println("This Test Ran");
		//MathUtils mUtils = new MathUtils();      //commented since it initilized in  BeforeEach
		int expected = 2;
		int actual = mUtils.add(1, 1);
		assertEquals(expected, actual, "The add method should add two numbers");
	}

	
	@Nested				//runs this nested class containing several methods and groups under AddTest
	@DisplayName("and running Nested AddTest method")  
	class AddTest {

		@Test
		@DisplayName("and Testing add Method for + numbers")     
		void testAddPositive() {
			assertEquals(2, mUtils.add(1, 1), "should add two positive numbers");
		}

		@Test
		@DisplayName("and Testing add Method for - numbers")     
		void testAddNegative() {
			assertEquals(-2, mUtils.add(-1, -1), "should add two negative numbers");
		}
		
		//string "should..." always executes to displays when assert fail, 
		//but using lambda ()-> it executes only when fail hence its optimized if any methods are passed in string 
		@Test
		@DisplayName("and Testing add Method for - numbers")     
		void testAddNegativeUsing() {
			int expected = -3;
			int actuals = mUtils.add(-1, -1);
			assertEquals(expected, actuals, () -> "should add sum" + expected + "but returns" + actuals);
		}
	
	
	}
	
	
	
	@Test
	@DisplayName("Testing multiply Method")     // this displayed instead of method name under run stats
	void testMultiply() {
		//assertEquals(4, mUtils.multiply(2, 2), "should return right product");
		assertAll(
				() -> assertEquals(4, mUtils.multiply(2, 2)),
				() -> assertEquals(0, mUtils.multiply(2, 0)),
				() -> assertEquals(-2, mUtils.multiply(2, -1))
				);
	}
	
	
	@Test
	//@EnabledOnOs(OS.LINUX)						//if present this method run only on Linus
	void testDivide() {
		//boolean isServerUp false;
		//assumeTrue(isServerUp);	//if present runs below code only when 'isServerUp' is true
		//MathUtils mUtils = new MathUtils();      //commented since it initilized in  BeforeEach
		//assertThrows(NullPointerException.class, () -> mUtils.divide(1, 0), "Divide by zero should throw");
		assertThrows(ArithmeticException.class, () -> mUtils.divide(1, 0), "Divide by zero should throw");
		
	}

	
	@RepeatedTest(3)   					//this repeats the test testComputeCircleRadius 3 times
	@Tag("Circle")		//used to selectively run the test methods
	void testComputeCircleRadius() {
		//MathUtils mUtils = new MathUtils();      //commented since it initilized in  BeforeEach
		assertEquals(314.1592653589793, mUtils.computeCircleArea(10), "Should return right circle area");
	}

	@Test
	@Disabled									// this disables and not run this method
	@DisplayName("TDD method should not run")     // this displayed instead of method name under run stats
	void testDisabled() {
		fail("this test should be disabled");
	}
	
	
}
