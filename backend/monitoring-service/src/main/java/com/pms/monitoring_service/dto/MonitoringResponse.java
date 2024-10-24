package com.pms.monitoring_service.dto;

public record MonitoringResponse(
        String farmName,
        Double weightGain,
        Double mortalityRate,
        Double feedConversionRatio,
        Double predictedWeightBasedOnFeedInFlock,
        Double predictedWeightBasedOnADGPerBird
) { }
                              