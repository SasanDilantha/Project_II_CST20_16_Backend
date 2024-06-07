import React from 'react';
import { View, Text, StyleSheet, ScrollView } from 'react-native';

const monitoringData = [
  { 
    id: '1', 
    name: 'Block A', 
    feedConsumption: '50 kg', 
    waterConsumption: '100 liters', 
    temperature: '24°C', 
    humidity: '65%', 
    ammoniaLevel: '20 ppm' 
  },
  { 
    id: '2', 
    name: 'Block B', 
    feedConsumption: '70 kg', 
    waterConsumption: '150 liters', 
    temperature: '25°C', 
    humidity: '60%', 
    ammoniaLevel: '18 ppm' 
  },
  { 
    id: '3', 
    name: 'Block C', 
    feedConsumption: '90 kg', 
    waterConsumption: '200 liters', 
    temperature: '26°C', 
    humidity: '55%', 
    ammoniaLevel: '22 ppm' 
  },
];

const MonitoringScreen = () => {
  return (
    <ScrollView style={styles.container}>
      <Text style={styles.title}>Monitoring</Text>
      {monitoringData.map((block) => (
        <View key={block.id} style={styles.card}>
          <Text style={styles.cardTitle}>{block.name}</Text>
          <Text style={styles.detailText}>Feed Consumption: {block.feedConsumption}</Text>
          <Text style={styles.detailText}>Water Consumption: {block.waterConsumption}</Text>
          <Text style={styles.detailText}>Temperature: {block.temperature}</Text>
          <Text style={styles.detailText}>Humidity: {block.humidity}</Text>
          <Text style={styles.detailText}>Ammonia Level: {block.ammoniaLevel}</Text>
        </View>
      ))}
    </ScrollView>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    padding: 16,
    backgroundColor: '#f8f8f8',
  },
  title: {
    fontSize: 24,
    fontWeight: 'bold',
    marginBottom: 16,
    textAlign: 'center',
  },
  card: {
    backgroundColor: '#fff',
    padding: 16,
    borderRadius: 8,
    marginBottom: 16,
    elevation: 2,
  },
  cardTitle: {
    fontSize: 20,
    fontWeight: 'bold',
    marginBottom: 8,
  },
  detailText: {
    fontSize: 16,
    color: '#555',
    marginBottom: 4,
  },
});

export default MonitoringScreen;
