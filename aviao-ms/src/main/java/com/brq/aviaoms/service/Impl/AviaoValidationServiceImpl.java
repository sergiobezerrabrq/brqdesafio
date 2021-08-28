package com.brq.aviaoms.service.Impl;

import com.brq.aviaoms.json.request.AviaoRequest;
import com.brq.aviaoms.messages.Message;
import com.brq.aviaoms.service.AviaoValidationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.regex.Pattern;

@Service
@Slf4j
public class AviaoValidationServiceImpl implements AviaoValidationService {

    /**
     * Método responsável pela validação do Avião
     * @param aviaoRequest
     * @param bindingResult
     * @return Boolean
     */
    @Override
    public Boolean isAviaoValid(AviaoRequest aviaoRequest, BindingResult bindingResult) {
        log.info("Sevice validação Avião");
        boolean isAviaoValid = true;

        if(bindingResult.hasErrors()){
            isAviaoValid = false;
        }else if(!Pattern.matches("^[+]?\\d{1,5}(\\.\\d{1,2})?$", aviaoRequest.getAltitudeMaxima().toString())
                  || !Pattern.matches("^[+]?\\d{1,5}(\\.\\d{1,2})?$", aviaoRequest.getCapacidadeMaximaVoo().toString())
                  || !Pattern.matches("^[+]?\\d{1,5}(\\.\\d{1,2})?$", aviaoRequest.getVelocidadeMaxima().toString())){

            bindingResult.addError(new ObjectError("Object", Message.DADOS_INVALIDOS));
            isAviaoValid = false;
        }

        return isAviaoValid;
    }
}
