package com.brq.aviaoms.service.Impl;

import com.brq.aviaoms.domain.Aviao;
import com.brq.aviaoms.exception.NotFoundException;
import com.brq.aviaoms.json.request.AviaoRequest;
import com.brq.aviaoms.json.response.AviaoResponse;
import com.brq.aviaoms.messages.Message;
import com.brq.aviaoms.repository.AviaoRepository;
import com.brq.aviaoms.service.AviaoValidationService;
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
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class AviaoServiceImplTest {

    @InjectMocks
    private AviaoServiceImpl aviaoServiceImplMock;

    @Mock
    private AviaoRepository aviaoRepository;

    @Mock
    private AviaoValidationService aviaoValidationService;

    @Mock
    private AviaoResponse aviaoResponse;

    @Mock
    private List<AviaoResponse> listAviaoResponse;

    @Mock
    private List<Aviao> listAviao;

    @Mock
    private AviaoRequest aviaoRequest;

    @Mock
    private BindingResult bindingResult;

    @Mock
    private Aviao aviao;

    private UUID uuid;

    @BeforeEach
    private void setUp(){
        uuid = UUID.randomUUID();

        aviaoResponse = new AviaoResponse();
        aviaoResponse.setId(uuid);

        listAviaoResponse = new ArrayList<AviaoResponse>();
        listAviaoResponse.add(aviaoResponse);

        aviao = new Aviao();
        aviao.setId(uuid);

        listAviao = new ArrayList<Aviao>();
        listAviao.add(aviao);

        aviaoRequest = new AviaoRequest();
        aviaoRequest.setModelo("A320");

        bindingResult.addError(new ObjectError("Object", Message.DADOS_INVALIDOS));
    }

    @DisplayName("GET all Aviões")
    @Test
    void test_BuscarTodosAvioes(){
        when(aviaoRepository.findAll()).thenReturn(listAviao);

        List<AviaoResponse> test = aviaoServiceImplMock.buscarTodosAvioes();

        assertEquals(listAviaoResponse.get(0).getClass(), test.get(0).getClass());
        verify(aviaoRepository, atLeastOnce()).findAll();
    }

    @DisplayName("Get Avião By Id")
    @Test
    void test_BuscarAviaoPorId() {

        when(aviaoRepository.findById(ArgumentMatchers.any(UUID.class))).thenReturn(Optional.of(aviao));

        ResponseEntity<AviaoResponse> test = aviaoServiceImplMock.buscarAviaoPorId(uuid);

        assertEquals(aviaoResponse, test.getBody());
        verify(aviaoRepository, atLeastOnce()).findById(ArgumentMatchers.any(UUID.class));
    }

    @DisplayName("Get Avião By Id  Not Found")
    @Test
    void test_BuscarAviaoPorId_NotFound() {

        when(aviaoRepository.findById(ArgumentMatchers.any(UUID.class))).thenReturn(Optional.ofNullable(null));

        ResponseEntity<AviaoResponse> test = aviaoServiceImplMock.buscarAviaoPorId(uuid);

        assertEquals(HttpStatus.NOT_FOUND.value(), test.getStatusCode().value());
        assertEquals(null, test.getBody());
        verify(aviaoRepository, atLeastOnce()).findById(ArgumentMatchers.any(UUID.class));
    }

    @DisplayName("Create Avião")
    @Test
    void test_PostAviao() {

        when(aviaoValidationService.isAviaoValid(ArgumentMatchers.any(AviaoRequest.class), ArgumentMatchers.any(BindingResult.class))).thenReturn(true);
        when(aviaoRepository.save(ArgumentMatchers.any(Aviao.class))).thenReturn(aviao);

        ResponseEntity<AviaoResponse> test = aviaoServiceImplMock.postAviao(aviaoRequest, bindingResult);

        assertEquals(aviaoResponse, test.getBody());
        verify(aviaoValidationService, atLeastOnce()).isAviaoValid(ArgumentMatchers.any(AviaoRequest.class), ArgumentMatchers.any(BindingResult.class));
        verify(aviaoRepository, atLeastOnce()).save(ArgumentMatchers.any(Aviao.class));
    }

    @DisplayName("Update Avião")
    @Test
    void test_PutAviao() {

        when(aviaoValidationService.isAviaoValid(ArgumentMatchers.any(AviaoRequest.class), ArgumentMatchers.any(BindingResult.class))).thenReturn(true);
        when(aviaoRepository.findById(ArgumentMatchers.any(UUID.class))).thenReturn(Optional.of(aviao));
        when(aviaoRepository.save(ArgumentMatchers.any(Aviao.class))).thenReturn(aviao);

        ResponseEntity<AviaoResponse> test = aviaoServiceImplMock.putAviao(aviaoRequest, bindingResult, uuid);

        assertEquals(aviaoResponse, test.getBody());
        verify(aviaoValidationService, atLeastOnce()).isAviaoValid(ArgumentMatchers.any(AviaoRequest.class), ArgumentMatchers.any(BindingResult.class));
        verify(aviaoRepository, atLeastOnce()).findById(ArgumentMatchers.any(UUID.class));
        verify(aviaoRepository, atLeastOnce()).save(ArgumentMatchers.any(Aviao.class));
    }

    @DisplayName("Update Avião Not Found")
    @Test
    void test_PutAviao_NotFound() {

        when(aviaoValidationService.isAviaoValid(ArgumentMatchers.any(AviaoRequest.class), ArgumentMatchers.any(BindingResult.class))).thenReturn(true);
        when(aviaoRepository.findById(ArgumentMatchers.any(UUID.class))).thenReturn(Optional.ofNullable(null));

        ResponseEntity<AviaoResponse> test = aviaoServiceImplMock.putAviao(aviaoRequest, bindingResult, uuid);

        assertEquals(HttpStatus.NOT_FOUND.value(), test.getStatusCode().value());
        assertEquals(null, test.getBody());
        verify(aviaoValidationService, atLeastOnce()).isAviaoValid(ArgumentMatchers.any(AviaoRequest.class), ArgumentMatchers.any(BindingResult.class));
        verify(aviaoRepository, atLeastOnce()).findById(ArgumentMatchers.any(UUID.class));
    }

    @DisplayName("Delete By Id")
    @Test
    void test_DeleteAviaoById() {

        when(aviaoRepository.findById(ArgumentMatchers.any(UUID.class))).thenReturn(Optional.ofNullable(aviao));
        doNothing().when(aviaoRepository).delete(ArgumentMatchers.any(Aviao.class));

        ResponseEntity<AviaoResponse> test = aviaoServiceImplMock.deleteAviao(uuid);

        assertEquals(HttpStatus.OK.value(), test.getStatusCode().value());
        assertEquals(null, test.getBody());
        verify(aviaoRepository, atLeastOnce()).findById(ArgumentMatchers.any(UUID.class));
        verify(aviaoRepository, atLeastOnce()).delete(ArgumentMatchers.any(Aviao.class));
    }

    @DisplayName("Delete By Id Not Found")
    @Test
    void test_DeleteAviaoById_NotFound() {

        when(aviaoRepository.findById(ArgumentMatchers.any(UUID.class))).thenReturn(Optional.ofNullable(null));

        assertThrows(NotFoundException.class, () -> aviaoServiceImplMock.deleteAviao(uuid));

        verify(aviaoRepository, atLeastOnce()).findById(ArgumentMatchers.any(UUID.class));
    }

}
