package com.pms.medication_service.dto;

import java.time.LocalDate;
import java.util.List;

public record PrescriptionDTO(
        Integer placementId, // Placement ID, which will be used to get the medicationId
        List<RecommendMedicationDTO> recommendMedications
) {
}
