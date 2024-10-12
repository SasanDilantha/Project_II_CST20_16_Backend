package com.pms.monitoring_service.services;


import com.pms.monitoring_service.clients.ChickClient;

import com.pms.monitoring_service.dto.MonitoringResponse;
import com.pms.monitoring_service.dto.SensorData;

import com.pms.monitoring_service.kafka.MonitoringNotifications;
import com.pms.monitoring_service.kafka.NotificationProducer;
import com.pms.monitoring_service.model.GrowthMonitoring;
import com.pms.monitoring_service.model.WeightRecord;
import com.pms.monitoring_service.repository.GrowthMonitoringRepository;
import com.pms.monitoring_service.repository.WeightRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.OptionalDouble;

@Service
@RequiredArgsConstructor
public class MonitoringService {

    private final WeightRecordRepository weightRecordRepository;
    private final GrowthMonitoringRepository growthMonitoringRepository;
    private final MethodInMonitoring methode;

    private final IoTSensorDataService ioTSensorDataService;
    private final ChickClient chickClient;
    private final NotificationProducer notificationProducer;

    public LocalDateTime getChickStorageById(Integer placementId) {
        LocalDateTime initDate = chickClient.getChickStorageById(placementId);
        return (initDate != null) ? initDate : LocalDateTime.now();
    }

    public void testNotify(MonitoringNotifications request) {
        System.out.println("Sending test notification: service" );
        notificationProducer.sendNotification(request);
    }

    // Runs every 3 days
    @Scheduled(fixedRate = 3 * 24 * 60 * 60 * 1000) // 3 days in milliseconds
    private void createNewGrowthMonitoringRecord() {
        // Fetch all placement IDs or related data (depending on how you track the chickens)
        List<Integer> placementIds = weightRecordRepository.findDistinctPlacementIds(); // Add this method to the repository

        for (Integer placementId : placementIds) {
            // Fetch weight records from the last 3 days for this placement
            List<WeightRecord> recentRecords = weightRecordRepository
                    .findByPlacementIdAndDateAfter(placementId, LocalDate.now().minusDays(3));

            if (!recentRecords.isEmpty()) {
                // Calculate weight gain for each placement
                Double weightGain = methode.calculateWeightGain(placementId, weightRecordRepository);

                // Calculate feed conversion ratio
                double totalFeedConsumption = recentRecords.stream()
                        .mapToDouble(WeightRecord::getFeedConsumption)
                        .sum();
                double fcr = totalFeedConsumption / weightGain;

                // Create a new GrowthMonitoring entity with the new data
                GrowthMonitoring newGrowthMonitoring = GrowthMonitoring.builder()
                        .recordDate(LocalDate.now())
                        .weightGain(weightGain)
                        .feedConversionRatio(fcr) // Calculate this based on feed consumption
                        .weightRecords(recentRecords)
                        .build();

                // Save the new GrowthMonitoring record
                growthMonitoringRepository.save(newGrowthMonitoring);

                // Send a notification least 3 days chick summery record
                String notificationType = "Growth-Monitoring";
                MonitoringResponse monitoringResponse = getLatestGrowthMonitoringRecord(placementId);
                notificationProducer.sendNotification(
                        new MonitoringNotifications(
                                notificationType,
                                null,
                                monitoringResponse,
                                null,
                                "all"
                        )
                );
            }
        }
    }

    /**
     * Create method for the send data for the monitoring in front end
     * @param placementId The placement ID
     * @return weight gain, feed conversion ratio, mortality rate, predicted weight based on feed in flock &  predicted weight based on ADG per bird
     * this method also use reporting, if have any custom reporting, set field in table
     */
    // Get the latest growth monitoring record for a placement
    public MonitoringResponse getLatestGrowthMonitoringRecord(Integer placementId) {
        //get Inventory id
        Integer inventoryId = chickClient.getInventoryIdByPlacementId(placementId);
        // Find the latest growth monitoring record for the given placement ID
        GrowthMonitoring latestGrowthMonitoring = growthMonitoringRepository.findLatestByPlacementId(placementId, inventoryId);

        // weightGain,
        Double weightGain = latestGrowthMonitoring.getWeightGain();

        // feedConversionRatio,
        Double feedConversionRatio = latestGrowthMonitoring.getFeedConversionRatio();

        // mortalityRate,
        Double mortalityRate = methode.calculateMortalityRate(placementId, chickClient);

        // predictedWeightBasedOnFeedInFlock,
        Double totalFeedConsumed = methode.calculateTotalFeedConsumed(placementId, weightRecordRepository);
        Double predictedWeightBasedOnFeedInFlock = (totalFeedConsumed / feedConversionRatio) * (1 - (mortalityRate / 100));

        // predictedWeightBasedOnADGPerBird
        double initWeight = (double) chickClient.getInitWeight(placementId);
        Double averageDailyGain = methode.calculateAverageDailyGain(placementId, weightRecordRepository);
        Double predictedWeightBasedOnADGPerBird = initWeight + (averageDailyGain * 30); // Assuming 30 days



        return new MonitoringResponse(
                weightGain,
                feedConversionRatio,
                mortalityRate,
                predictedWeightBasedOnFeedInFlock,
                predictedWeightBasedOnADGPerBird
        );
    }

    // Method to retrieve sensor data
    public List<SensorData> getSensorData() {
        return ioTSensorDataService.getSensorData();
    }


}
