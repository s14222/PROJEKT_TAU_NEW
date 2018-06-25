package service;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import com.tau.account.model.Undead;
import com.tau.account.model.User;
import com.tau.account.service.interfaces.UndeadService;
import com.tau.account.service.interfaces.UserService;
import com.tau.account.service.interfaces.User_undeadService;
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

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/appconfig-root.xml"})
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

    @Autowired
    User_undeadService user_undeadService;

    @Test
    @DatabaseSetup(value = {"/fullData.xml"}) //wczytywanie danych z pliku
    @ExpectedDatabase(value = "/aaddUserData.xml", //oczekiwane dane wyjsciowe
            assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void addUserCheck() throws Exception {

        assertEquals(3, userService.findAll().size());  //oczekuje ze rozmiar wszystkich pobranych userow wynosi 3


        User u = new User();
        u.setUsername("LimChangkyun");
        u.setPassword("diction");

        userService.saveUser(u);

        assertEquals(4, userService.findAll().size());

    }

    @Test
    @DatabaseSetup(value = {"/fullData.xml"})
    @ExpectedDatabase(value = "/adeleteData.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void deleteUserCheck() throws Exception {

        assertEquals(3, userService.findAll().size());

        User user = userService.findById(5L);

        userService.delete(user);

        assertEquals(2, userService.findAll().size());

    }


    @Test
    @DatabaseSetup(value = {"/fullData.xml"})
    @ExpectedDatabase(value = "/aupdateData.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void updateUserCheck() throws Exception {


        User user = userService.findById(4L);

        user.setUsername("WonhoUlzzang");

        userService.update(user);

        assertEquals(userService.findById(4L).getUsername(), user.getUsername());


    }

    @Test
    @DatabaseSetup(value = {"/fullData.xml"})
    @ExpectedDatabase(value = "/fullData.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void getUserCheck() throws Exception {

        User user = userService.findById(4L);

        assertNotNull(user);
        //sprawdzamy czy ten pobrany wyzej user jest taki sam jak ten w bazie /czy nie jest nullem, czy poprawnie go pobiera/
        assertEquals(userService.findById(4L).getUsername(), user.getUsername());
    }


    @Test
    @DatabaseSetup(value = {"/fullData.xml"})
    @ExpectedDatabase(value = "/adisposeUndead.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void disposeUndeadCheck() throws Exception {

        //usuwanie powiazania usera z undeada, czyli usuwanie undeada
        User user = userService.findById(3L);

        assertNotNull(user);
        List<Undead> undead = undeadService.findByUserName(user.getUsername());
        assertEquals(3, undead.size());
        assertNotNull(undead);
        assertFalse(undead.isEmpty());

        user_undeadService.dispose(user.getId(), undead.get(0).getId() );

        List<Undead> undead2 = undeadService.findByUserName(user.getUsername());

        assertNotNull(undead2);
        assertEquals(2, undead2.size());



    }


}













  /*  @Test
    @DatabaseSetup(value = {"/fullData.xml"})
    @ExpectedDatabase(value = "/ausersundead.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void getUsersUndeads() throws Exception {


       user_undeadService.findUser_undeadBy(0L);

       assertNotNull(user);

      assertNotNull(user.getUndeadList());

        assertEquals(2, user.getUndeadList().size());
    }*/



