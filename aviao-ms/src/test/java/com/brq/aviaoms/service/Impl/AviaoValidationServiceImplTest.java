package com.brq.aviaoms.service.Impl;

import com.brq.aviaoms.json.request.AviaoRequest;
import com.brq.aviaoms.messages.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class AviaoValidationServiceImplTest {

    @InjectMocks
    private AviaoValidationServiceImpl aviaoValidationServiceImplMock;

    @Mock
    private AviaoRequest aviaoRequest;

    @Mock
    private BindingResult bindingResult;

    private Double altitudeMaxima;
    private Double velocidadeMaxima;
    private Double capacidadeMaximaVoo;

    @BeforeEach
    public void setUp(){
        altitudeMaxima = 40000D;
        velocidadeMaxima = 825D;
        capacidadeMaximaVoo = 16D;

        aviaoRequest = new AviaoRequest();
        aviaoRequest.setModelo("A320");
        aviaoRequest.setAltitudeMaxima(altitudeMaxima);
        aviaoRequest.setVelocidadeMaxima(velocidadeMaxima);
        aviaoRequest.setCapacidadeMaximaVoo(capacidadeMaximaVoo);

        bindingResult.addError(new ObjectError("Object", Message.DADOS_INVALIDOS));
    }

    @DisplayName("Avião sem erro de Validação")
    @Test
    void test_Validate_Aviao_Without_Error() {
        Boolean test = aviaoValidationServiceImplMock.isAviaoValid(aviaoRequest, bindingResult);
        assertEquals(true, test);
    }

    @DisplayName("Avião com erro de altitudeMaxima")
    @Test
    void test_Validate_Aviao_AltitudeMaxima() {
        altitudeMaxima = 250000.000D;
        aviaoRequest.setAltitudeMaxima(altitudeMaxima);
        Boolean test = aviaoValidationServiceImplMock.isAviaoValid(aviaoRequest, bindingResult);
        assertEquals(false, test);
    }

    @DisplayName("Avião com erro de velocidadeMaxima")
    @Test
    void test_Validate_Aviao_VelocidadeMaxima() {
        velocidadeMaxima = 250000.000D;
        aviaoRequest.setVelocidadeMaxima(velocidadeMaxima);
        Boolean test = aviaoValidationServiceImplMock.isAviaoValid(aviaoRequest, bindingResult);
        assertEquals(false, test);
    }

    @DisplayName("Avião com erro de capacidadeMaximaVoo")
    @Test
    void test_Validate_Aviao_CapacidadeMaximaVoo() {
        capacidadeMaximaVoo = 16000000.00D;
        aviaoRequest.setCapacidadeMaximaVoo(capacidadeMaximaVoo);
        Boolean test = aviaoValidationServiceImplMock.isAviaoValid(aviaoRequest, bindingResult);
        assertEquals(false, test);
    }
}
