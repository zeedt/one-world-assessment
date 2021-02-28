package com.zeed.one.world.assessment.services.impl;

import com.zeed.one.world.assessment.model.*;
import com.zeed.one.world.assessment.entities.User;
import com.zeed.one.world.assessment.repository.UserRepository;
import com.zeed.one.world.assessment.services.EmailService;
import com.zeed.one.world.assessment.services.UserService;
import com.zeed.one.world.assessment.util.GeneralUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    @Value("${service.base.url:http://localhost:8080/user/}")
    private String serviceBaseUrl;

    @Override
    public UserApiModel createUser(UserCreationApiModel userCreationApiModel) {
        Optional<User> optionalUser = userRepository.findByEmail(userCreationApiModel.getEmail());
        if (optionalUser.isPresent())
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Email already exists");

        User user = GeneralUtil.convertCreateUserApiModelToEntity(userCreationApiModel);
        userRepository.save(user);
        emailService.sendNotification(user.getEmail(), "Welcome to One World Accuracy", composeEmailContent(user.getApprovalCode(), user.getId(), user.getFirstName()));
        return GeneralUtil.convertUserEntityToApiModel(user);
    }

    @Override
    public UserApiModel updateUser(UserUpdateApiModel userUpdateApiModel, String id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User with ID %s not found", id));
        User user = optionalUser.get();

        if (user.isDeleted())
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, String.format("User with ID %s already deactivated", id));

        user.setTitle(StringUtils.isEmpty(userUpdateApiModel.getTitle()) ? user.getTitle() : userUpdateApiModel.getTitle());
        user.setFirstName(StringUtils.isEmpty(userUpdateApiModel.getFirstName()) ? user.getFirstName() : userUpdateApiModel.getFirstName());
        user.setLastName(StringUtils.isEmpty(userUpdateApiModel.getLastName()) ? user.getLastName() : userUpdateApiModel.getLastName());
        user.setMobileNumber(StringUtils.isEmpty(userUpdateApiModel.getMobileNumber()) ? user.getMobileNumber() : userUpdateApiModel.getMobileNumber());
        user.setEmail(StringUtils.isEmpty(userUpdateApiModel.getEmail()) ? user.getEmail() : userUpdateApiModel.getEmail());
        userRepository.save(user);
        return GeneralUtil.convertUserEntityToApiModel(user);
    }

    @Override
    public UserSearchResponseModel getUser(UserSearchApiModel searchApiModel) {
        return getPagedRequestByAndParameters(searchApiModel);
    }

    @Override
    public void deleteUser(String id) {
        if (userRepository.updateDeleteStatus(LocalDateTime.now(), id) == 0)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to locate record or User already deactivated");

    }

    private UserSearchResponseModel getPagedRequestByAndParameters(UserSearchApiModel searchModel) {

        int pageNo = searchModel.getPageNo() == null || searchModel.getPageNo()-1 < 0 ? 0 : searchModel.getPageNo()-1;
        int pageSize = searchModel.getPageSize() == null || searchModel.getPageSize() < 1 ? 15 : searchModel.getPageSize();

        Page<User> userPage = userRepository.findAll((root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.and(composePredicateBasedOnParams(searchModel,
                criteriaBuilder, root).toArray(new Predicate[0])), PageRequest.of(pageNo, pageSize, Sort.Direction.DESC, "id"));

        return convertUserPageToUserApiModel(userPage);

    }

    private UserSearchResponseModel convertUserPageToUserApiModel(Page<User> userPage) {
        List<User> users = userPage.getContent();
        List<UserApiModel> userApiModels = users.stream()
                .map(GeneralUtil::convertUserEntityToApiModel).collect(Collectors.toList());
        UserSearchResponseModel userSearchResponseModel = new UserSearchResponseModel();
        userSearchResponseModel.setPageNo(userPage.getNumber()+1);
        userSearchResponseModel.setPageSize(userPage.getSize());
        userSearchResponseModel.setUserApiModels(userApiModels);
        userSearchResponseModel.setTotalPageNumber(userPage.getTotalPages());
        userSearchResponseModel.setTotalElements(userPage.getTotalElements());
        return userSearchResponseModel;
    }


    private List<Predicate> composePredicateBasedOnParams(UserSearchApiModel searchModel, CriteriaBuilder criteriaBuilder, Root root) {

        List<Predicate> predicateList = new ArrayList<>();
        if (!StringUtils.isEmpty(searchModel.getId()))
            predicateList.add(criteriaBuilder.equal(root.get("id"),searchModel.getId()));
        if (!StringUtils.isEmpty(searchModel.getEmail()))
            predicateList.add(criteriaBuilder.equal(criteriaBuilder.lower(root.get("email")),searchModel.getEmail().toLowerCase()));
        if (searchModel.isVerified() != null)
            predicateList.add(criteriaBuilder.equal(root.get("verified"),searchModel.isVerified()));
        if (searchModel.getRegisteredDate() != null)  {
            LocalDate date = searchModel.getRegisteredDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            predicateList.add(criteriaBuilder.between(root.get("dateRegistered"),date.atStartOfDay(), date.atTime(23,59,59)));
        }

        return predicateList;

    }

    private String composeEmailContent(String approvalCode, String id, String firstName) {
        StringBuilder stringBuilder = new StringBuilder("<html>")
                .append("<p>Hi " + firstName + ",</p>")
                .append(String.format("Welcome to One World Accuracy. Kindly click <a href='%s%s/%s'>here</a> to activate your account.", serviceBaseUrl, id, approvalCode))
                .append("</html>");

        return stringBuilder.toString();
    }

}
