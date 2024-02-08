package hr.algebra.healthyapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import hr.algebra.healthyapp.dto.PrescriptionDto;
import hr.algebra.healthyapp.mapper.PrescriptionMapper;
import hr.algebra.healthyapp.service.PrescriptionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.charset.Charset;
import java.security.Principal;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PrescriptionControllerTest {

    @Mock
    private PrescriptionService prescriptionService;

    @Mock
    private PrescriptionMapper prescriptionMapper;

    @InjectMocks
    private PrescriptionController prescriptionController;

    private MockMvc mockMvc;

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @BeforeEach
    public void beforeAll() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(prescriptionController).build();
    }

    @Test
    void testGetPrescriptionsByDoctor() throws Exception {
        Principal mockPrincipal = Mockito.mock(Principal.class);
        Mockito.when(mockPrincipal.getName()).thenReturn("me");
        when(prescriptionService.getPrescriptionsByUser(anyString())).thenReturn(Collections.emptyList());

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/prescription")
                .principal(mockPrincipal)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(prescriptionService, times(1)).getPrescriptionsByUser(anyString());
    }

    @Test
    void testCreateAppointment() throws Exception {
        Principal mockPrincipal = Mockito.mock(Principal.class);
        Mockito.when(mockPrincipal.getName()).thenReturn("me");
        PrescriptionDto prescriptionDto = new PrescriptionDto();

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/prescription")
                .contentType(APPLICATION_JSON_UTF8)
                .content(asJsonString(prescriptionDto))
                .principal(mockPrincipal)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());

        verify(prescriptionService, times(1)).savePrescription(any(), anyString());
    }

    @Test
    void testCreateInvalidAppointment() throws Exception {
        Principal mockPrincipal = Mockito.mock(Principal.class);
        Mockito.when(mockPrincipal.getName()).thenReturn("me");

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/prescription")
                .contentType(APPLICATION_JSON_UTF8)
                .content(asJsonString(null))
                .principal(mockPrincipal)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is4xxClientError());

        verify(prescriptionService, times(0)).savePrescription(any(), anyString());
    }

    @Test
    void testUpdatePrescription() throws Exception {
        Principal mockPrincipal = Mockito.mock(Principal.class);
        Mockito.when(mockPrincipal.getName()).thenReturn("me");
        PrescriptionDto prescriptionDto = new PrescriptionDto();

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/prescription")
                .contentType(APPLICATION_JSON_UTF8)
                .content(asJsonString(prescriptionDto))
                .principal(mockPrincipal)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());

        verify(prescriptionService, times(1)).savePrescription(any(), anyString());
    }

    @Test
    void testDeletePrescription() throws Exception {
        Principal mockPrincipal = Mockito.mock(Principal.class);
        Mockito.when(mockPrincipal.getName()).thenReturn("me");
        Long prescriptionId = 1L;

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/prescription/{prescriptionId}", prescriptionId)
                .principal(mockPrincipal)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());

        verify(prescriptionService, times(1)).deletePrescription(prescriptionId);
    }

    @Test
    void testDeleteInvalidPrescription() throws Exception {
        Principal mockPrincipal = Mockito.mock(Principal.class);
        Mockito.when(mockPrincipal.getName()).thenReturn("me");
        Long prescriptionId = 1L;

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/prescription/{prescriptionId}", ";SHUTDOWN DATABASE")
                .principal(mockPrincipal)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is4xxClientError());

        verify(prescriptionService, times(0)).deletePrescription(prescriptionId);
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
