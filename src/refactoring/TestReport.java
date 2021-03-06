package refactoring;

import junit.framework.TestCase;
import java.util.List;
import java.util.Collection;

public class TestReport extends TestCase {

	public TestReport(String name) { 
		super(name); 
	}
	
	public void testEmptyReport() throws Exception {
		SchedulePersistence.deleteAll();
		Report report = new Report();
		StringBuffer buffer = new StringBuffer();
		report.write(buffer);
		assertEquals("Number of scheduled offerings: 0\n", buffer.toString());
	}
	
	public void testReport() throws Exception {
		SchedulePersistence.deleteAll();
		Course cs101 = CoursePersistence.create("CS101", 3);
		CoursePersistence.update(cs101);
		Offering off1 = OfferingPersistence.create(cs101, "M10");
		OfferingPersistence.update(off1);
		Offering off2 = OfferingPersistence.create(cs101, "T9");
	    OfferingPersistence.update(off2);
		Schedule s = SchedulePersistence.create("Bob");
		s.offerings.add(off1);
		s.offerings.add(off2);
		SchedulePersistence.update(s);
		Schedule s2 = SchedulePersistence.create("Alice");
		s2.offerings.add(off1);
		SchedulePersistence.update(s2);
		Report report = new Report();
		StringBuffer buffer = new StringBuffer();
		report.write(buffer);
		String result = buffer.toString();
		String valid1 = "CS101 M10\n\tAlice\n\tBob\n" + "CS101 T9\n\tBob\n" + "Number of scheduled offerings: 2\n";
		String valid2 = "CS101 T9\n\tBob\n" + "CS101 M10\n\tBob\n\tAlice\n" + "Number of scheduled offerings: 2\n";
		assertTrue(result.equals(valid1) || result.equals(valid2));
	}
}