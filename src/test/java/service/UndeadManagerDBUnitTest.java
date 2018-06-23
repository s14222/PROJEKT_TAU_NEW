package service;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.tau.account.model.User;
import com.tau.account.service.interfaces.UndeadService;
import com.tau.account.service.interfaces.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/appconfig-data.xml"})
@Rollback
@Transactional(transactionManager = "transactionManager")
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class
})
public class UndeadManagerDBUnitTest {

    @Autowired
    UndeadService undeadService;

    @Autowired
    UserService userService;

    @Test
    @DatabaseSetup(value = {"/fullData.xml"}) //wczytywanie danych z pliku
    @ExpectedDatabase(value = "/aaddUserData.xml", //oczekiwane dane wyjsciowe
            assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void addUserCheck() throws Exception {

        assertEquals(3, userService.findAll().size());  //oczekuje ze rozmiar wszystkich pobranych userow wynosi 3


        User u = new User();
        u.setUsername("LimChangkyun");
        u.setPassword("diction");
        u.setPesel("95122113440");

        userService.save(u);

        assertEquals(4, userService.findAll().size());

    }

    @Test
    @DatabaseSetup(value = {"/fullData.xml"})
    @ExpectedDatabase(value = "/adeleteData.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void deleteUserCheck() throws Exception {

        assertEquals(3, userService.findAll().size());

        User user = userService.findByPesel("85043021547");

        userService.delete(user);

        assertEquals(2, userService.findAll().size());

    }


    @Test
    @DatabaseSetup(value = {"/fullData.xml"})
    @ExpectedDatabase(value = "/aupdateData.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void updateUserCheck() throws Exception {


        User user = userService.findByPesel("43012144859");

        user.setUsername("WonhoUlzzang");

        userService.update(user);

        assertEquals(userService.findByPesel("43012144859").getUsername(), user.getUsername());


    }

    @Test
    @DatabaseSetup(value = {"/fullData.xml"})
    @ExpectedDatabase(value = "/fullData.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void getUserCheck() throws Exception {

        User user = userService.findByPesel("12043021547");

        assertNotNull(user);
        //sprawdzamy czy ten pobrany wyzej user jest taki sam jak ten w bazie /czy nie jest nullem, czy poprawnie go pobiera/
        assertEquals(userService.findByPesel("12043021547").getUsername(), user.getUsername());
    }


    @Test
    @DatabaseSetup(value = {"/fullData.xml"})
    @ExpectedDatabase(value = "/adisposeUndead.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void disposeUndeadCheck() throws Exception {

        //usuwanie powiazania usera z undeada, czyli usuwanie undeada
        User user = userService.findByPesel("12043021547");

//        assertEquals(2, user.getUndeadList().size());
//
//        Undead undead = user.getUndeadList().get(0);
//        //undeadManager.disposeUndead(user, undead);
//
//        assertEquals(1, user.getUndeadList().size());

    }

    @Test
    @DatabaseSetup(value = {"/fullData.xml"})
    @ExpectedDatabase(value = "/aundeadsList.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void getUsersUndeads() throws Exception {

//        User user = undeadManager.findUserByPesel("12043021547");
//
//        assertNotNull(user);
//
//        assertNotNull(user.getUndeadList());
//
//        assertEquals(2, user.getUndeadList().size());
    }


}
