package com.zeed.one.world.assessment.services.impl;

import com.zeed.one.world.assessment.enums.Status;
import com.zeed.one.world.assessment.model.UserApiModel;
import com.zeed.one.world.assessment.model.UserCreationApiModel;
import com.zeed.one.world.assessment.model.UserUpdateApiModel;
import com.zeed.one.world.assessment.model.UserSearchResponseModel;
import com.zeed.one.world.assessment.repository.UserRepository;
import com.zeed.one.world.assessment.services.EmailService;
import com.zeed.one.world.assessment.util.TestUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @InjectMocks
    private final UserServiceImpl userServiceImpl = new UserServiceImpl();

    @Mock
    private final EmailService emailService = new EmailServiceImpl();

    @Mock
    private UserRepository userRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createUser() {
        UserCreationApiModel creationApiModel = TestUtil.getMockedUserCreationApiModel();
        Mockito.when(userRepository.findByEmail(Mockito.anyString()))
                .thenReturn(TestUtil.getMockedResponseForNotPresentUser());
        UserApiModel userApiModel = userServiceImpl.createUser(creationApiModel);
        assertNotNull("Returned user model cannot be null", userApiModel);
        assertEquals("Returned user email must be same as email passed", userApiModel.getEmail(), creationApiModel.getEmail());
        assertEquals("Returned user first name must be same as first name passed", userApiModel.getFirstName(), creationApiModel.getFirstName());
        assertEquals("Returned user last name must be same as last name passed", userApiModel.getLastName(), creationApiModel.getLastName());
        assertEquals("Returned user role must be same as role passed", userApiModel.getRole(), creationApiModel.getRole());
        assertNotNull("Registered date must not be null", userApiModel.getDateRegistered());
        assertEquals("User status must be REGISTERED", userApiModel.getStatus(), Status.REGISTERED);
        assertEquals("User verified status must be false", userApiModel.getVerified(), false);
    }

    @Test
    public void updateUser() {
        UserUpdateApiModel updateApiModel = TestUtil.getMockedUserUpdateApiModel();
        Mockito.when(userRepository.findById(Mockito.anyString()))
                .thenReturn(TestUtil.getMockedResponseForUserPresent());
        UserApiModel userApiModel = userServiceImpl.updateUser(updateApiModel, "ca2a3c19-2113-4d48-a7e3-27b66b95db5b");
        assertNotNull("Returned user model cannot be null", userApiModel);
        assertEquals("Returned user email must be same as email passed", userApiModel.getEmail(), updateApiModel.getEmail());
        assertEquals("Returned user first name must be same as first name passed", userApiModel.getFirstName(), updateApiModel.getFirstName());
        assertEquals("Returned user last name must be same as last name passed", userApiModel.getLastName(), updateApiModel.getLastName());
        assertEquals("Returned user title must be same as title passed", userApiModel.getLastName(), updateApiModel.getLastName());
    }

    @Test
    public void getUser() {
        Mockito.when(userRepository.findAll(Mockito.any(Specification.class), Mockito.any(Pageable.class))).thenReturn(TestUtil.getMockedPagedUser());
        UserSearchResponseModel userSearchApiModel = userServiceImpl.getUser(TestUtil.getMockedUserSearchApiRequest());
        assertNotNull("Returned user search response model cannot be null", userSearchApiModel);
        assertTrue("User records present", !CollectionUtils.isEmpty(userSearchApiModel.getUserApiModels()));
        assertTrue("2 User records present", userSearchApiModel.getUserApiModels().size() == 2);

    }

    @Test
    public void deleteUser() {
        Mockito.when(userRepository.updateDeleteStatus(Mockito.any(), Mockito.anyString()))
                .thenReturn(1);
        userServiceImpl.deleteUser("1ed1a2e9-98fb-4994-a22a-dfb1ee1149d7");
    }

    @Test
    public void verifyUser() {
        Mockito.when(userRepository.findByIdAndApprovalCode(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(TestUtil.getMockedResponseForUserPresent());
        String verifyResponse = userServiceImpl.verifyUser("1ed1a2e9-98fb-4994-a22a-dfb1ee1149d7", "(H2W178");

        assertEquals(verifyResponse, "Congratulations, Your account has been verified");

    }
}