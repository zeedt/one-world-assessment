package com.zeed.one.world.assessment.services.impl;

import com.zeed.one.world.assessment.entities.User;
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
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @InjectMocks
    private final UserServiceImpl userServiceImpl = new UserServiceImpl();

    @Mock
    private final EmailService emailService = new EmailServiceImpl();

    @Mock
    private UserRepository userRepository;

    @Mock
    private JavaMailSender javaMailSender;

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

    @Test(expected = ResponseStatusException.class)
    public void testForUserAlreadyExistDuringCreation() {

        UserCreationApiModel creationApiModel = TestUtil.getMockedUserCreationApiModel();
        Mockito.when(userRepository.findByEmail(Mockito.anyString()))
                .thenReturn(TestUtil.getMockedResponseForUserPresent());
        userServiceImpl.createUser(creationApiModel);
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

    @Test(expected = ResponseStatusException.class)
    public void testForUserNotExistDuringUpdate() {

        UserUpdateApiModel updateApiModel = TestUtil.getMockedUserUpdateApiModel();
        Mockito.when(userRepository.findById(Mockito.anyString()))
                .thenReturn(TestUtil.getMockedResponseForNotPresentUser());
        userServiceImpl.updateUser(updateApiModel, "ba2a3c19-2113-4d48-a7e3-27b66b95db5b");
    }

    @Test(expected = ResponseStatusException.class)
    public void testForUserDeletedDuringUpdate() {
        UserUpdateApiModel updateApiModel = TestUtil.getMockedUserUpdateApiModel();
        User deletedUser = TestUtil.getMockedResponseForUserPresent().get();
        deletedUser.setDeleted(true);
        Mockito.when(userRepository.findById(Mockito.anyString()))
                .thenReturn(Optional.of(deletedUser));
        userServiceImpl.updateUser(updateApiModel, "ba2a3c19-2113-4d48-a7e3-27b66b95db5b");
    }

    @Test(expected = ResponseStatusException.class)
    public void testForUserWithNewEmailExistDuringUpdate() {
        UserUpdateApiModel updateApiModel = TestUtil.getMockedUserUpdateApiModel();
        User user = TestUtil.getMockedResponseForUserPresent().get();
        User existingUser = TestUtil.getMockedResponseForUserPresent().get();
        existingUser.setEmail("shola@www.com");
        Mockito.when(userRepository.findById(Mockito.anyString()))
                .thenReturn(Optional.of(user));
        Mockito.when(userRepository.findByEmail(Mockito.anyString()))
                .thenReturn(Optional.of(existingUser));

        userServiceImpl.updateUser(updateApiModel, "ba223c19-2113-4d48-a7e3-27b66b95db5b");
    }

    @Test
    public void getUser() {
        Mockito.when(userRepository.findAll(Mockito.any(Specification.class), Mockito.any(Pageable.class))).thenReturn(TestUtil.getMockedPagedUser());
        UserSearchResponseModel userSearchApiModel = userServiceImpl.getUser(TestUtil.getMockedUserSearchApiRequest());
        assertNotNull("Returned user search response model cannot be null", userSearchApiModel);
        assertFalse("User records present", CollectionUtils.isEmpty(userSearchApiModel.getUserApiModels()));
        assertTrue("2 User records present", userSearchApiModel.getUserApiModels().size() == 2);

    }

    @Test
    public void deleteUser() {
        Mockito.when(userRepository.findById(Mockito.anyString()))
                .thenReturn(TestUtil.getMockedResponseForUserPresent());
        userServiceImpl.deleteUser("1ed1a2e9-98fb-4994-a22a-dfb1ee1149d7");
    }

    @Test(expected = ResponseStatusException.class)
    public void testForUserAlreadyDeletedDuringDelete() {
        User deletedUser = TestUtil.getMockedResponseForUserPresent().get();
        deletedUser.setDeleted(true);
        Mockito.when(userRepository.findById(Mockito.anyString()))
                .thenReturn(Optional.of(deletedUser));
        userServiceImpl.deleteUser("fa2a3c19-2113-4d48-a7e3-27b66b95db5b");
    }

    @Test(expected = ResponseStatusException.class)
    public void testForUserNotFoundDuringDelete() {
        Mockito.when(userRepository.findById(Mockito.anyString()))
                .thenReturn(TestUtil.getMockedResponseForNotPresentUser());
        userServiceImpl.deleteUser("fa2a3c19-2113-4d48-a7e3-27b66b95db5b");
    }

    @Test
    public void verifyUser() {
        Mockito.when(userRepository.findByIdAndApprovalCode(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(TestUtil.getMockedResponseForUserPresent());
        String verifyResponse = userServiceImpl.verifyUser("1ed1a2e9-98fb-4994-a22a-dfb1ee1149d7", "(H2W178");
        assertEquals(verifyResponse, "Congratulations, Your account has been verified");
    }

    @Test
    public void testForUserNotFoundDuringVerification() {
        Mockito.when(userRepository.findByIdAndApprovalCode(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(TestUtil.getMockedResponseForNotPresentUser());
        String response = userServiceImpl.verifyUser("4a2716-2113-4d48-a7e3-27b66b95db5b", "0622E1");
        assertTrue(response.equals("Invalid verification link"));
    }

    @Test
    public void testForUserForAlreadyVerifiedDuringVerification() {
        User verifiedUser = TestUtil.getMockedResponseForUserPresent().get();
        verifiedUser.setVerified(true);
        Mockito.when(userRepository.findByIdAndApprovalCode(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(Optional.of(verifiedUser));
        String response = userServiceImpl.verifyUser("1a2a3c19-2113-4d48-a7e3-27b66b95db5b", "23EW12");
        assertTrue(response.equals("User already verified"));

    }
}