package com.brq.aviaoms.service;

import com.brq.aviaoms.json.request.AviaoRequest;
import org.springframework.validation.BindingResult;

public interface AviaoValidationService {

    Boolean isAviaoValid(AviaoRequest aviaoRequest, BindingResult bindingResult);
}
