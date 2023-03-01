package ru.netology.patient.service.medical;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import ru.netology.patient.entity.BloodPressure;
import ru.netology.patient.entity.HealthInfo;
import ru.netology.patient.entity.PatientInfo;
import ru.netology.patient.repository.PatientInfoRepository;
import ru.netology.patient.service.alert.SendAlertService;

import java.math.BigDecimal;
import java.time.LocalDate;

class MedicalServiceImplTests {

    final PatientInfo patientInfoNormal = new PatientInfo("Иван", "Иванович", LocalDate.of(1, 1, 1),
            new HealthInfo(new BigDecimal(36.6), new BloodPressure(120, 80))
    );
    final PatientInfo patientInfoDeviation = new PatientInfo("Иван", "Иванович", LocalDate.of(1, 1, 1),
            new HealthInfo(new BigDecimal(39.6), new BloodPressure(150, 30))
    );
    final BloodPressure normalPressure = new BloodPressure(120, 80);
    final String ID = "ID";

    @Test
    public void checkTemperature_checkBloodPressure_with_normal_params() {
        PatientInfoRepository patientInfoRepository = Mockito.mock(PatientInfoRepository.class);
        Mockito.when(patientInfoRepository.getById(Mockito.any())).thenReturn(patientInfoNormal);
        SendAlertService service = Mockito.mock(SendAlertService.class);

        MedicalServiceImpl medicalService = new MedicalServiceImpl(patientInfoRepository, service);

        medicalService.checkTemperature(ID, new BigDecimal(36.6));
        medicalService.checkBloodPressure(ID, normalPressure);

        Mockito.verify(service, Mockito.never()).send(Mockito.any());
    }

    @Test
    public void checkTemperature_checkBloodPressure_with_deviation_params() {
        PatientInfoRepository patientInfoRepository = Mockito.mock(PatientInfoRepository.class);
        Mockito.when(patientInfoRepository.getById(Mockito.any())).thenReturn(patientInfoDeviation);
        SendAlertService service = Mockito.mock(SendAlertService.class);

        MedicalServiceImpl medicalService = new MedicalServiceImpl(patientInfoRepository, service);

        medicalService.checkTemperature(ID, new BigDecimal(36.6));
        medicalService.checkBloodPressure(ID, normalPressure);

        Mockito.verify(service, Mockito.times(2)).send(Mockito.any());
    }

    @Test
    public void checkBloodPressure_sendMessageArgument() {
        PatientInfoRepository patientInfoRepository = Mockito.mock(PatientInfoRepository.class);
        Mockito.when(patientInfoRepository.getById(Mockito.any())).thenReturn(patientInfoDeviation);
        SendAlertService service = Mockito.mock(SendAlertService.class);

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);

        MedicalService medicalService = new MedicalServiceImpl(patientInfoRepository, service);
        medicalService.checkBloodPressure(ID, normalPressure);

        Mockito.verify(patientInfoRepository).getById(captor.capture());
        Assertions.assertEquals(ID, captor.getValue());
    }
}