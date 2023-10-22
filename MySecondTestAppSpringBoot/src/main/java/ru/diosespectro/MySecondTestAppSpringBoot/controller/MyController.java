package ru.diosespectro.MySecondTestAppSpringBoot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.diosespectro.MySecondTestAppSpringBoot.exception.UnsupportedCodeException;
import ru.diosespectro.MySecondTestAppSpringBoot.exception.ValidationFailedException;
import ru.diosespectro.MySecondTestAppSpringBoot.model.*;
import ru.diosespectro.MySecondTestAppSpringBoot.service.ModifyRequestService;
import ru.diosespectro.MySecondTestAppSpringBoot.service.ModifyResponseService;
import ru.diosespectro.MySecondTestAppSpringBoot.service.ValidationService;
import ru.diosespectro.MySecondTestAppSpringBoot.util.DateTimeUtil;

import javax.validation.Valid;
import java.util.Date;

@Slf4j
@RestController
public class MyController {

    private final ValidationService validationService;
    private final ModifyResponseService modifyResponseService;
    private final ModifyRequestService modifyRequestService;
    private final ModifyRequestService modifySourceRequestService;

    @Autowired
    public MyController(ValidationService validationService,
                        @Qualifier("ModifySystemTimeResponseService") ModifyResponseService modifyResponseService,
                        @Qualifier("ModifySystemNameRequestService") ModifyRequestService modifyRequestService,
                        @Qualifier("ModifySourceRequestService") ModifyRequestService modifySourceRequestService) {
        this.validationService = validationService;
        this.modifyResponseService = modifyResponseService;
        this.modifyRequestService = modifyRequestService;
        this.modifySourceRequestService = modifySourceRequestService;
    }

    @PostMapping(value = "/feedback")
    public ResponseEntity<Response> feedback(@Valid @RequestBody Request request, BindingResult bindingResult) {
        request.setSystemTime(DateTimeUtil.getCustomFormat().format(new Date()));

        log.info("request: {}", request);

        Response response = Response.builder()
                .uid(request.getUid())
                .operationUid(request.getOperationUid())
                .systemTime(DateTimeUtil.getCustomFormat().format(new Date()))
                .code(Codes.SUCCESS)
                .errorCode(ErrorCodes.EMPTY)
                .errorMessage(ErrorMessages.EMPTY)
                .build();

        log.info("response: {}", response);

        modifyResponseService.modify(response);

        try {
            validationService.isValid(bindingResult);
        } catch (ValidationFailedException e) {
            return retErrorResponse(response, bindingResult, ErrorCodes.VALIDATION_EXCEPTION, ErrorMessages.VALIDATION, HttpStatus.BAD_REQUEST);
        } catch (UnsupportedCodeException e) {
            return retErrorResponse(response, bindingResult, ErrorCodes.UNSUPPORTED_EXCEPTION, ErrorMessages.UNSUPPORTED, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return retErrorResponse(response, bindingResult, ErrorCodes.UNKNOWN_EXCEPTION, ErrorMessages.UNKNOWN, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        modifyResponseService.modify(response);
        modifySourceRequestService.modify(request);
        modifyRequestService.modify(request);

        return new ResponseEntity<>(modifyResponseService.modify(response), HttpStatus.OK);
    }

    ResponseEntity<Response> retErrorResponse(Response response, BindingResult bindingResult, ErrorCodes errorCode,
                                    ErrorMessages errorMessage, HttpStatus httpStatus) {
        response.setCode(Codes.FAILED);
        response.setErrorCode(errorCode);
        response.setErrorMessage(errorMessage);

        log.error("error response: {} {}", response, bindingResult.getFieldError().getDefaultMessage());
        return new ResponseEntity<>(response, httpStatus);
    }
}