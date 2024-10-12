package com.pms.notification_service.kafka.MonitoringDTO;

public record MonitoringResponse(
        Double weightGain,
        Double mortalityRate,
        Double feedConversionRatio,
        Double predictedWeightBasedOnFeedInFlock,
        Double predictedWeightBasedOnADGPerBird
) {
}
