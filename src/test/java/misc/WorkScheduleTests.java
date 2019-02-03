package misc;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * @author Samuel Nuttall
 * <p>
 * Tests for the Queue class
 * <p>
 * The purpose of the class is to manage the schedule for the employees of a company. The time is divided in units of 1 hour and the hours are simply identified by integers
 * (Note that this is an unrealistic simplification). For each hour, the schedule stores the number employees which is needed at that time. It could for instance be that during week-days the need
 * is that 5 employees work at the same time, but less during nights and weekend. For each hour the schedule also stores the names of the employees who have been assigned to work at that hour.
 */
public class WorkScheduleTests {
    private static final Logger log = LoggerFactory.getLogger(WorkScheduleTests.class);

    //private WorkSchedule testSchedule;

    @BeforeAll
    public static void init() {
        log.info("WorkScheduleTests Tests initiating");
    }

    @AfterAll
    public static void done() {
        log.info("WorkScheduleTests Tests finished");
    }


    /**
     * Tests to see if the constructor initializes the hours as it states in the spec
     * Checks that there are at least the amount as given by the argument in the constructor
     */
    @Test
    @DisplayName("Test Hours Created By Constructor by using readSchedule()")
    public void testHoursCreated() {
        WorkSchedule testSchedule = new WorkSchedule(4);
        for (int i = 0; i < 4; i++) {
            WorkSchedule.Hour hour = testSchedule.readSchedule(i);
            assertEquals(hour.requiredNumber, 0);
        }

    }

    /**
     * Test that the hours do not exceed the number given by the constructor
     * (Should throw an error when accessing out of rang hours)
     */
    @Test
    @DisplayName("Test Out of Bounds Read Schedule")
    public void testOutOfBoundsReadSchedule() {
        WorkSchedule testSchedule = new WorkSchedule(4);
        assertThrows(RuntimeException.class, () -> {
            testSchedule.readSchedule(5);
        });
    }

    /**
     * Tests that working employees array is empty
     * We know from the spec that the WorkSchedule.Hour type is an array of strings and that no employees are
     * assigned to it so the expected output of readSchedule after WorkSchedule object creation would be an hour object with an
     * empty string array as its workingEmployees property
     */
    @Test
    @DisplayName("Test Workers Created By Constructor by using readSchedule()")
    public void testWorkersCreated() {
        WorkSchedule testSchedule = new WorkSchedule(4);
        String[] empty = new String[]{};
        for (int i = 0; i < 4; i++) {
            WorkSchedule.Hour hour = testSchedule.readSchedule(i);
            assertArrayEquals(hour.workingEmployees, empty);
        }
    }

    /**
     * Tests the required number is set using readSchedule to verify,
     * that you are able to reset the required number after setting it once
     * Tests that changing one of the required numbers for an hour works properly
     */
    @Test
    @DisplayName("Test valid input for setRequiredNumber()")
    public void testSetRequiredNumberValid() {
        WorkSchedule testSchedule = new WorkSchedule(4);
        assertAll(
                () -> {
                    // Test 1
                    testSchedule.setRequiredNumber(1, 0, 3);
                    for (int i = 0; i < 4; i++) {
                        WorkSchedule.Hour hour = testSchedule.readSchedule(i);
                        assertEquals(1, hour.requiredNumber);
                    }
                },
                () -> {
                    // Test 2
                    testSchedule.setRequiredNumber(5, 0, 3);
                    for (int i = 0; i < 4; i++) {
                        WorkSchedule.Hour hour = testSchedule.readSchedule(i);
                        assertEquals(5, hour.requiredNumber);
                    }
                },
                () -> {
                    // Test 3
                    testSchedule.setRequiredNumber(8, 1, 2);
                    WorkSchedule.Hour hour = testSchedule.readSchedule(1);
                    assertEquals(8, hour.requiredNumber);
                }
        );
    }

    /**
     * Tests that addWorkingPeriod returns true when there is there is less than the required number of required workers
     * already added for a given time slot
     */
    @Test
    @DisplayName("Test Adding Work Period returns True when with valid input")
    public void testAddWorkPeriodTrue() {
        WorkSchedule testSchedule = new WorkSchedule(4);
        testSchedule.setRequiredNumber(4, 0, 3);

        String employee1 = "Jimmy";
        int startTime = 0;
        int endTime = 1;
        assertTrue(testSchedule.addWorkingPeriod(employee1, startTime, endTime));
    }

    /**
     * Tests that addWorkingPeriod returns false given invalid input
     */
    @Test
    @DisplayName("Test Adding Work Period returns False given invalid input")
    public void testAddWorkPeriodFalse() {
        WorkSchedule testSchedule = new WorkSchedule(4);
        testSchedule.setRequiredNumber(0, 0, 3);

        String employee = "Jimmy";
        int startTime = 0;
        int endTime = 1;
        assertFalse(testSchedule.addWorkingPeriod(employee, startTime, endTime));
    }


    /**
     * Tests that addWorkingPeriod adds all the employees by using readSchedule to get the employees
     * working at the same time
     */
    @Test
    @DisplayName("Test Workers at same time addWorkingPeriod()")
    public void testAddWorkingPeriodValid() {
        WorkSchedule testSchedule = new WorkSchedule(4);
        testSchedule.setRequiredNumber(4, 0, 3);

        String employee1 = "Jimmy";
        int startTime = 0;
        int endTime = 1;

        String employee2 = "James Cameron";
        int startTime2 = 0;
        int endTime2 = 1;

        String employee3 = "Samuel L.Jackson";
        int startTime3 = 0;
        int endTime3 = 1;

        Set<String> expectedEmployees = new HashSet<>();
        expectedEmployees.add(employee1);
        expectedEmployees.add(employee2);
        expectedEmployees.add(employee3);

        testSchedule.addWorkingPeriod(employee1, startTime, endTime);
        testSchedule.addWorkingPeriod(employee2, startTime2, endTime2);
        testSchedule.addWorkingPeriod(employee3, startTime3, endTime3);

        WorkSchedule.Hour hour = testSchedule.readSchedule(0);
        Set<String> actualEmployees = new HashSet<>();
        String[] employees = hour.workingEmployees;
        for (int i = 0; i < employees.length; i++) {
            actualEmployees.add(employees[i]);
        }
        assertEquals(expectedEmployees, actualEmployees);
    }

    /**
     * Tests workers at different time slots
     * <p>
     * Test fails because time start and end times are obviously interpreted differently by the
     * implementation
     */
    @Test
    @DisplayName("Test Workers at different times using addWorkingPeriod()")
    public void testAddWorkingPeriodDifferentTimes() {
        WorkSchedule testSchedule = new WorkSchedule(4);
        testSchedule.setRequiredNumber(4, 0, 3);

        String employee1 = "Jimmy";
        int startTime = 0;
        int endTime = 1;

        String employee2 = "James Cameron";
        int startTime2 = 0;
        int endTime2 = 1;

        String employee3 = "Samuel L.Jackson";
        int startTime3 = 1;
        int endTime3 = 2;

        testSchedule.addWorkingPeriod(employee1, startTime, endTime);
        testSchedule.addWorkingPeriod(employee2, startTime2, endTime2);
        testSchedule.addWorkingPeriod(employee3, startTime3, endTime3);

        //Should be one since it mirrors the start and end time of when they were added.
        assertEquals(2, testSchedule.workingEmployees(0, 1).length);

    }


    /**
     * Tests Next Incomplete
     *
     * Tests fails, No comments for this one, it is just straight trash no one would use anything remotely like
     * this, if a place used software like this it would be time to rethink life and its about time I
     * stop wasting mine writing this
     */
    @Test
    @DisplayName("Test Next Incomplete")
    public void testNextIncomplete() {
        WorkSchedule testSchedule = new WorkSchedule(4);
        testSchedule.setRequiredNumber(2, 0, 3);

        String employee1 = "Jimmy";
        int startTime = 0;
        int endTime = 1;

        String employee2 = "James Cameron";
        int startTime2 = 0;
        int endTime2 = 1;

        String employee3 = "Samuel L.Jackson";
        int startTime3 = 2;
        int endTime3 = 3;

        testSchedule.addWorkingPeriod(employee1, startTime, endTime);
        testSchedule.addWorkingPeriod(employee2, startTime2, endTime2);
        testSchedule.addWorkingPeriod(employee3, startTime3, endTime3);

        assertEquals(2, testSchedule.nextIncomplete(0));

    }
}
