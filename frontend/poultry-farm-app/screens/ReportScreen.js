import React, { useState } from 'react';
import { View, Text, StyleSheet, ScrollView } from 'react-native';
import { Picker } from '@react-native-picker/picker';

const ReportScreen = () => {
  const [selectedFarm, setSelectedFarm] = useState('Happy Farm');
  
  const farms = [
    { 
      name: 'Happy Farm', 
      location: 'Green Valley', 
      chickens: 1000,
      dailyReport: {
        eggsCollected: 300,
        mortalityRate: 2,
        feedConsumed: 50,
        income: 450,
        expenses: 200,
      },
      monthlyReport: {
        eggsCollected: 9000,
        mortalityRate: 60,
        feedConsumed: 1500,
        income: 13500,
        expenses: 6000,
      },
      chickDetails: {
        totalChicks: 500,
        healthyChicks: 480,
        sickChicks: 20,
      },
      healthReport: {
        vaccinated: 480,
        notVaccinated: 20,
        diseases: 5,
      },
      productionRate: {
        dailyEggs: 300,
        monthlyEggs: 9000,
        eggPerChickenPerDay: 0.3,
      },
    },
    {
      name: 'Sunshine Farm',
      location: 'Sunny Valley',
      chickens: 1200,
      dailyReport: {
        eggsCollected: 350,
        mortalityRate: 1.5,
        feedConsumed: 55,
        income: 500,
        expenses: 220,
      },
      monthlyReport: {
        eggsCollected: 10500,
        mortalityRate: 45,
        feedConsumed: 1650,
        income: 15000,
        expenses: 6600,
      },
      chickDetails: {
        totalChicks: 600,
        healthyChicks: 580,
        sickChicks: 20,
      },
      healthReport: {
        vaccinated: 580,
        notVaccinated: 20,
        diseases: 3,
      },
      productionRate: {
        dailyEggs: 350,
        monthlyEggs: 10500,
        eggPerChickenPerDay: 0.3,
      },
    },
  ];

  const farmDetails = farms.find(farm => farm.name === selectedFarm);

  return (
    <ScrollView style={styles.container}>
      <Text style={styles.header}>Farm Management Report</Text>
      <Picker
        selectedValue={selectedFarm}
        onValueChange={(itemValue) => setSelectedFarm(itemValue)}
        style={styles.picker}
      >
        {farms.map(farm => (
          <Picker.Item key={farm.name} label={farm.name} value={farm.name} />
        ))}
      </Picker>

      {farmDetails && (
        <>
          <View style={styles.section}>
            <Text style={styles.title}>Farm Details</Text>
            <Text style={styles.detailText}>Name: {farmDetails.name}</Text>
            <Text style={styles.detailText}>Location: {farmDetails.location}</Text>
            <Text style={styles.detailText}>Total Chickens: {farmDetails.chickens}</Text>
          </View>

          <View style={styles.section}>
            <Text style={styles.title}>Chick Details</Text>
            <Text style={styles.detailText}>Total Chicks: {farmDetails.chickDetails.totalChicks}</Text>
            <Text style={styles.detailText}>Healthy Chicks: {farmDetails.chickDetails.healthyChicks}</Text>
            <Text style={styles.detailText}>Sick Chicks: {farmDetails.chickDetails.sickChicks}</Text>
          </View>

          <View style={styles.section}>
            <Text style={styles.title}>Health Report</Text>
            <Text style={styles.detailText}>Vaccinated: {farmDetails.healthReport.vaccinated}</Text>
            <Text style={styles.detailText}>Not Vaccinated: {farmDetails.healthReport.notVaccinated}</Text>
            <Text style={styles.detailText}>Diseases: {farmDetails.healthReport.diseases}</Text>
          </View>

          <View style={styles.section}>
            <Text style={styles.title}>Daily Report</Text>
            <Text style={styles.detailText}>Eggs Collected: {farmDetails.dailyReport.eggsCollected}</Text>
            <Text style={styles.detailText}>Mortality Rate: {farmDetails.dailyReport.mortalityRate}</Text>
            <Text style={styles.detailText}>Feed Consumed: {farmDetails.dailyReport.feedConsumed} kg</Text>
            <Text style={styles.incomeText}>Income: ${farmDetails.dailyReport.income}</Text>
            <Text style={styles.expenseText}>Expenses: ${farmDetails.dailyReport.expenses}</Text>
          </View>

          <View style={styles.section}>
            <Text style={styles.title}>Monthly Report</Text>
            <Text style={styles.detailText}>Eggs Collected: {farmDetails.monthlyReport.eggsCollected}</Text>
            <Text style={styles.detailText}>Mortality Rate: {farmDetails.monthlyReport.mortalityRate}</Text>
            <Text style={styles.detailText}>Feed Consumed: {farmDetails.monthlyReport.feedConsumed} kg</Text>
            <Text style={styles.incomeText}>Income: ${farmDetails.monthlyReport.income}</Text>
            <Text style={styles.expenseText}>Expenses: ${farmDetails.monthlyReport.expenses}</Text>
          </View>

          <View style={styles.section}>
            <Text style={styles.title}>Production Rate</Text>
            <Text style={styles.detailText}>Daily Eggs: {farmDetails.productionRate.dailyEggs}</Text>
            <Text style={styles.detailText}>Monthly Eggs: {farmDetails.productionRate.monthlyEggs}</Text>
            <Text style={styles.detailText}>Eggs per Chicken per Day: {farmDetails.productionRate.eggPerChickenPerDay}</Text>
          </View>
        </>
      )}
    </ScrollView>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#f0f4f7',
  },
  header: {
    fontSize: 26,
    fontWeight: 'bold',
    color: '#333',
    marginBottom: 24,
    marginTop: 16,
    textAlign: 'center',
  },
  picker: {
    marginHorizontal: 16,
    marginBottom: 24,
  },
  section: {
    backgroundColor: '#fff',
    padding: 16,
    borderRadius: 8,
    marginHorizontal: 16,
    marginBottom: 16,
    shadowColor: '#000',
    shadowOpacity: 0.1,
    shadowOffset: { width: 0, height: 2 },
    shadowRadius: 4,
    elevation: 3,
  },
  title: {
    fontSize: 22,
    fontWeight: 'bold',
    marginBottom: 8,
    color: '#4CAF50',
  },
  detailText: {
    fontSize: 18,
    color: '#555',
    marginBottom: 4,
  },
  incomeText: {
    fontSize: 18,
    color: '#4CAF50',
    marginBottom: 4,
    fontWeight: 'bold',
  },
  expenseText: {
    fontSize: 18,
    color: '#FF5722',
    marginBottom: 4,
    fontWeight: 'bold',
  },
});

export default ReportScreen;
