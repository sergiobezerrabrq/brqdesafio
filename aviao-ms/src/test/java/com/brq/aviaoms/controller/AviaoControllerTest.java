package com.brq.aviaoms.controller;

import com.brq.aviaoms.json.request.AviaoRequest;
import com.brq.aviaoms.json.response.AviaoResponse;
import com.brq.aviaoms.messages.Message;
import com.brq.aviaoms.service.AviaoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AviaoControllerTest {

    @InjectMocks
    private AviaoController aviaoControllerMock;

    @Mock
    private AviaoService aviaoService;

    @Mock
    private AviaoRequest aviaoRequest;

    @Mock
    private AviaoResponse aviaoResponse;

    @Mock
    private List<AviaoResponse> listAviaoResponse;

    @Mock
    private BindingResult bindingResult;

    private UUID uuid;
    private String modelo;
    private String fabricante;
    private String empresa;
    private String motor;
    private Integer qtdPassageiros;
    private Integer qtdPortasSaida;
    private Double altitudeMaxima;
    private Double velocidadeMaxima;
    private Double capacidadeMaximaVoo;


    @BeforeEach
    public void setUp(){
        aviaoRequest = new AviaoRequest();
        aviaoRequest.setModelo("A320");

        uuid = UUID.randomUUID();

        aviaoResponse = new AviaoResponse();
        aviaoResponse.setId(uuid);

        listAviaoResponse = new ArrayList<AviaoResponse>();
        listAviaoResponse.add(aviaoResponse);

        modelo = "A320";
        fabricante = "Airbus";
        empresa = "AirFrance";
        motor = "XYZ";
        qtdPassageiros = 160;
        qtdPortasSaida = 6;
        altitudeMaxima = 40000D;
        velocidadeMaxima = 825D;
        capacidadeMaximaVoo = 16D;

        bindingResult.addError(new ObjectError("Object", Message.DADOS_INVALIDOS));
    }

    @DisplayName("All Aviões")
    @Test
    void test_BuscarTodosAvioes(){

        when(aviaoService.buscarTodosAvioes()).thenReturn(listAviaoResponse);

        ResponseEntity<List<AviaoResponse>> test = aviaoControllerMock.buscarTodosAvioes();

        assertEquals(listAviaoResponse, test.getBody());
        verify(aviaoService, atLeastOnce()).buscarTodosAvioes();
    }

    @DisplayName("Aviões by Id")
    @Test
    void test_BuscarAviaoPorId(){

        when(aviaoService.buscarAviaoPorId(ArgumentMatchers.any(UUID.class))).thenReturn(ResponseEntity.status(HttpStatus.OK).body(aviaoResponse));

        ResponseEntity<AviaoResponse> test = aviaoControllerMock.buscarAviaoPorId(uuid);

        assertEquals(aviaoResponse, test.getBody());
        verify(aviaoService, atLeastOnce()).buscarAviaoPorId(ArgumentMatchers.any(UUID.class));
    }

    @DisplayName("Aviões by Filter")
    @Test
    void test_BuscarAviaoPorFiltro(){

        when(aviaoService.buscarAviaoPorFiltro(ArgumentMatchers.any(String.class),
                ArgumentMatchers.any(String.class),
                ArgumentMatchers.any(String.class),
                ArgumentMatchers.any(String.class),
                ArgumentMatchers.any(Integer.class),
                ArgumentMatchers.any(Integer.class),
                ArgumentMatchers.any(Double.class),
                ArgumentMatchers.any(Double.class),
                ArgumentMatchers.any(Double.class))).thenReturn(listAviaoResponse);

        ResponseEntity<List<AviaoResponse>> test = aviaoControllerMock.buscarAviaoPorFiltro(modelo, fabricante, empresa, motor, qtdPassageiros, qtdPortasSaida, altitudeMaxima, velocidadeMaxima, capacidadeMaximaVoo);

        assertEquals(listAviaoResponse, test.getBody());
        verify(aviaoService, atLeastOnce()).buscarAviaoPorFiltro(ArgumentMatchers.any(String.class),
                ArgumentMatchers.any(String.class),
                ArgumentMatchers.any(String.class),
                ArgumentMatchers.any(String.class),
                ArgumentMatchers.any(Integer.class),
                ArgumentMatchers.any(Integer.class),
                ArgumentMatchers.any(Double.class),
                ArgumentMatchers.any(Double.class),
                ArgumentMatchers.any(Double.class));
    }

    @DisplayName("Create Aviões")
    @Test
    void test_PostAviao() {

        when(aviaoService.postAviao(ArgumentMatchers.any(AviaoRequest.class), ArgumentMatchers.any(BindingResult.class)))
                .thenReturn(ResponseEntity.status(HttpStatus.CREATED).body(aviaoResponse));

        ResponseEntity<AviaoResponse> test = aviaoControllerMock.postAviao(aviaoRequest, bindingResult);

        assertEquals(aviaoResponse, test.getBody());
        verify(aviaoService, atLeastOnce()).postAviao(ArgumentMatchers.any(AviaoRequest.class), ArgumentMatchers.any(BindingResult.class));
    }

    @DisplayName("Update Aviões")
    @Test
    void test_PutAviao() {

        when(aviaoService.putAviao(ArgumentMatchers.any(AviaoRequest.class), ArgumentMatchers.any(BindingResult.class), ArgumentMatchers.any(UUID.class)))
                .thenReturn(ResponseEntity.status(HttpStatus.OK).body(aviaoResponse));

        ResponseEntity<AviaoResponse> test = aviaoControllerMock.putAviao(uuid, aviaoRequest, bindingResult);

        assertEquals(aviaoResponse, test.getBody());
        verify(aviaoService, atLeastOnce()).putAviao(ArgumentMatchers.any(AviaoRequest.class), ArgumentMatchers.any(BindingResult.class), ArgumentMatchers.any(UUID.class));
    }

    @DisplayName("Delete Aviões")
    @Test
    void test_DeleteAviao(){

        when(aviaoService.deleteAviao(ArgumentMatchers.any(UUID.class))).thenReturn(ResponseEntity.status(HttpStatus.OK).body(aviaoResponse));

        ResponseEntity<AviaoResponse> test = aviaoControllerMock.deleteAviao(uuid);

        assertEquals(aviaoResponse, test.getBody());
        verify(aviaoService, atLeastOnce()).deleteAviao(ArgumentMatchers.any(UUID.class));
    }
}
