package com.controller;

import com.controller.DAO.VisitorDAO;
import com.model.DeliveryOption;
import com.model.Visitor;
import com.util.exeptions.CustomException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VisitorLoginControllerTest {

    @Mock
    private VisitorDAO dao;

    @InjectMocks
    private VisitorLoginController controller = new VisitorLoginController();

    private Visitor visitor;

    private Set<DeliveryOption> set = new HashSet<>();

    @BeforeEach
    void setUp() throws CustomException {
        set.add(DeliveryOption.WAREHOUSE);
        visitor = new Visitor("user", "email@email.com", set);
        visitor.setPassword("$31$16$HaevUFUj5bfPQDFjzA9-DcwvICpfXOwQ12A2iwJHOdE");
    }

    @Test
    void validLogin() throws Exception {
        when(dao.getVisitor(any())).thenReturn(visitor);
        Assertions.assertEquals("user",controller.login("email@email.com", "3n$7Q14R0$*V").getUserName());
    }

    @Test
    void invalidLoginInvalidPassword() throws Exception {
        when(dao.getVisitor(any())).thenReturn(visitor);
                Exception exception = assertThrows(NullPointerException.class, () -> {
            controller.login("email@email.com", "invalidpassword").getUserName();
        });
    }
}