package com.pms.monitoring_service.services;

import com.pms.monitoring_service.clients.ChickClient;
import com.pms.monitoring_service.model.WeightRecord;
import com.pms.monitoring_service.repository.WeightRecordRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.OptionalDouble;

@Service
public class MethodInMonitoring {

    public Double calculateWeightGain(Integer placementId, WeightRecordRepository weightRecordRepository) {
        // Fetch the weight records from the last 3 days (start date: 3 days ago, end date: today)
        LocalDate today = LocalDate.now();
        LocalDate threeDaysAgo = today.minusDays(3);

        // Get all weight records from the last 3 days and today
        List<WeightRecord> recentRecords = weightRecordRepository
                .findByPlacementIdAndDateBetween(placementId, threeDaysAgo, today);

        if (recentRecords.isEmpty()) {
            // No data available, return null or some default value
            return null;
        }

        // Find today's weight (most recent weight record)
        WeightRecord todayRecord = recentRecords.stream()
                .filter(record -> record.getDate().isEqual(today))
                .findFirst()
                .orElse(null);

        if (todayRecord == null) {
            // No weight record for today, return null or handle accordingly
            return null;
        }

        // Calculate the average weight of the previous 3 days
        OptionalDouble averageWeightLast3Days = recentRecords.stream()
                .filter(record -> !record.getDate().isEqual(today)) // Exclude today's record
                .mapToDouble(WeightRecord::getWeight)
                .average();

        if (averageWeightLast3Days.isPresent()) {
            // Calculate the weight gain as the difference between today's weight and the average of the previous 3 days
            return todayRecord.getWeight() - averageWeightLast3Days.getAsDouble();
        }

        return null;
    }

    // Function to calculate the total feed consumed for a specific placement
    public Double calculateTotalFeedConsumed(Integer placementId, WeightRecordRepository weightRecordRepository) {
        // Retrieve all weight records for the given placement
        List<WeightRecord> weightRecords = weightRecordRepository.findByPlacementId(placementId);

        // return Sum up the feedConsumption from each WeightRecord
        return weightRecords.stream()
                .mapToDouble(WeightRecord::getFeedConsumption)
                .sum();
    }

    // Function to calculate Average Daily Gain (ADG) for a specific placement
    public Double calculateAverageDailyGain(Integer placementId, WeightRecordRepository weightRecordRepository) {
        // Retrieve all weight records for the given placement, sorted by date
        List<WeightRecord> weightRecords = weightRecordRepository.findByPlacementId(placementId);

        // Check if there are enough records to calculate ADG
        if (weightRecords.size() < 2) {
            return null; // Not enough records to calculate ADG
        }

        // Sort weight records by date (ensure they are ordered chronologically)
        weightRecords.sort(Comparator.comparing(WeightRecord::getDate));

        // Get the initial weight (first record) and the final weight (last record)
        WeightRecord initialRecord = weightRecords.getFirst(); // First record (earliest)
        WeightRecord finalRecord = weightRecords.getLast(); // Last record (latest)

        // Calculate the number of days between the initial and final record
        long numberOfDays = java.time.temporal.ChronoUnit.DAYS.between(initialRecord.getDate(), finalRecord.getDate());

        // Check if there are any days between the records (to avoid division by zero)
        if (numberOfDays == 0) {
            return null; // Cannot calculate ADG if the records are from the same day
        }

        // return Calculate ADG using the formula
        return (finalRecord.getWeight() - initialRecord.getWeight()) / numberOfDays;
    }

    // Assuming you have access to initial bird count and current dead bird count from repositories or calculations
    public Double calculateMortalityRate(Integer placementId, ChickClient chickClient) {
        // Fetch initial bird count and dead bird count for the given placement ID
        var getMortalityData = chickClient.getMortalityDetailsById(placementId);
        Integer initialBirdCount = getMortalityData.initialCount();
        Integer deadBirdCount = getMortalityData.mortalityCount();


        // Check for division by zero
        if (initialBirdCount == 0) {
            return 0.0; // No birds placed, so no mortality rate
        }

        // return Mortality Rate calculation formula
        return (double) deadBirdCount / initialBirdCount * 100;
    }
}
