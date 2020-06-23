package ru.javawebinar.topjava.service;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertThrows;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest extends TestCase {

    static {
        // Only for postgres driver logging
        // It uses java.util.logging and logged via jul-to-slf4j bridge
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;

    @Autowired
    private MealRepository repository;

    @Test
    public void get() {
        Meal meal = service.get(MEAL_ID_1, USER_ID);
        assertEquals(meal, MEAL_1);
    }

    @Test
    public void testDelete() {
        service.delete(MEAL_ID_1, USER_ID);
        assertNull(repository.get(MEAL_ID_1, USER_ID));
    }

    @Test
    public void getBetweenInclusive() {
        List<Meal> mealsBetweenInclusive = service.getBetweenInclusive(LocalDate.of(2020, Month.JANUARY, 31),
                LocalDate.of(2020, Month.JANUARY, 31), ADMIN_ID);
        List<Meal> MEALS_BETWEEN_INCLUSIVE = Arrays.asList(MEAL_7, MEAL_6, MEAL_5, MEAL_4);
        assertEquals(MEALS_BETWEEN_INCLUSIVE, mealsBetweenInclusive);
    }

    @Test
    public void testGetAll() {
        List<Meal> all_meals = service.getAll(USER_ID);
        List<Meal> ALL_MEALS = Arrays.asList(MEAL_3, MEAL_2, MEAL_1);
        assertEquals(ALL_MEALS, all_meals);
    }

    @Test
    public void testUpdate() {
        service.update(UPDATED_MEAL_7, ADMIN_ID);
        Meal updatedMeal = service.get(UPDATED_MEAL_ID_7, ADMIN_ID);
        assertEquals(updatedMeal, UPDATED_MEAL_7);
    }

    @Test
    public void deleteSomeonesMeal() {
        assertThrows(NotFoundException.class, () -> service.delete(MEAL_ID_1, ADMIN_ID));
    }

    @Test
    public void getSomeonesMeal() {
        assertThrows(NotFoundException.class, () -> service.get(MEAL_ID_1, ADMIN_ID));
    }

    @Test
    public void updateSomeonesMeal() {
        assertThrows(NotFoundException.class, () -> service.update(UPDATED_MEAL_7, USER_ID));
    }

}