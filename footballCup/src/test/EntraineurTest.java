package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import data.Entraineur;

class EntraineurTest {
	private static Entraineur e1;
	private static Entraineur e2;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		e1 = new Entraineur();
		e2 = new Entraineur(4,18);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test() {
		assertTrue(0==0);
		assertEquals(0,e1.getExperience());
		assertEquals(0,e1.getMotivateur());
		assertNotEquals(1,e1.getExperience());
		assertNotEquals(1,e1.getMotivateur());
		assertEquals(4,e2.getExperience());
		assertEquals(18,e2.getMotivateur());
	}

}
