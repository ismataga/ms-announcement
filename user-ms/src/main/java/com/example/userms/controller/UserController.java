package com.example.userms.controller;

import com.example.userms.model.request.UserInfoClientRequest;
import com.example.userms.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/user-profiles")
public class UserController {

    private final UserService userDetailService;

    @GetMapping
    public String test(@RequestBody @Validated UserInfoClientRequest userInfoClientRequest) {
        return "Test User";
    }

//    @PostMapping("/user-info")
//    @ResponseStatus(HttpStatus.OK)
//    public void updateUserInfo(@RequestBody UserProfileFormRequest userProfileForm) {
//        userService.updateUserInfo(userProfileForm);
//    }
//
//    @PostMapping(path = "/email/subscribe/{unsubscribe}")
//    @ResponseStatus(code = HttpStatus.OK)
//    public void unsubscribe(@PathVariable Boolean unsubscribe) {
//        userService.unsubscribe(unsubscribe);
//    }
//
//    @ReCaptcha
//    @PostMapping("/photo")
//    public ResponseEntity<Void> saveUserPhoto(@RequestBody String photoStr) {
//        return ResponseEntity.created(URI.create(userService.saveUserPhoto(photoStr))).build();
//    }
//
//    @DeleteMapping("/photo")
//    @ResponseStatus(code = HttpStatus.OK)
//    public void deleteUserPhoto() {
//        userService.deleteUserPhoto();
//    }
//
//    @PatchMapping("/lang/{langKey}")
//    @ResponseStatus(code = HttpStatus.OK)
//    public void changeLang(@PathVariable @NotNull(message = "langKey must not be null") String langKey) {
//        userService.changeLang(langKey);
//    }
//
//    @PutMapping("/disable")
//    @ResponseStatus(code = HttpStatus.ACCEPTED)
//    public void closeAccount() {
//        userService.closeAccount();
//    }
//
//    @PutMapping("/password/change")
//    @ResponseStatus(HttpStatus.OK)
//    public void changePassword(@RequestBody PasswordChangeRequest pcRequest) {
//        userService.changePassword(pcRequest);
//    }
//
//    @PostMapping("/password/check")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void checkPassword(@RequestBody CheckPasswordRequest checkPasswordRequest) {
//        userService.checkPassword(checkPasswordRequest);
//    }
//
//    @PatchMapping("/invoice/{status}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void updateInvoiceStatus(@PathVariable("status") InvoiceType status) {
//        userService.changeStatus(status);
//    }
//
//    @GetMapping("/is-uat-user")
//    @ResponseStatus(HttpStatus.OK)
//    public UatUserResponse isUatUser() {
//        return userService.isUatUser();
//    }
//
//    @GetMapping("/invite/{username}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void checkUserInvite(@PathVariable @NotNull(message = "username must not be null") String username) {
//        userService.checkUserInvite(username);
//    }
//
//    @GetMapping("/confirm-types")
//    public ConfirmTypeResponse getConfirmTypes() {
//        return userService.getConfirmTypes();
//    }
//
//    @PostMapping(value = "/invite")
//    @ResponseStatus(HttpStatus.OK)
//    public InviteUserResponse inviteUsers(@RequestBody @Valid InviteUserRequest inviteUserRequest) {
//        return userService.inviteUsers(inviteUserRequest);
//    }
}
