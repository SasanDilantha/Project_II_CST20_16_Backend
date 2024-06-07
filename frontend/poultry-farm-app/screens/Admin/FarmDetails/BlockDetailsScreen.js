import React from 'react';
import { View, Text, StyleSheet, ScrollView } from 'react-native';

const blockDetails = [
  { 
    id: '1', 
    name: 'Block A', 
    chickens: 150, 
    eggsPerDay: 120, 
    mortalityRate: '2%', 
    healthStatus: 'Good' 
  },
  { 
    id: '2', 
    name: 'Block B', 
    chickens: 200, 
    eggsPerDay: 180, 
    mortalityRate: '1.5%', 
    healthStatus: 'Good' 
  },
  { 
    id: '3', 
    name: 'Block C', 
    chickens: 250, 
    eggsPerDay: 220, 
    mortalityRate: '2.5%', 
    healthStatus: 'Fair' 
  },
];

const BlockDetailsScreen = () => {
  return (
    <ScrollView style={styles.container}>
      <Text style={styles.title}>Block Details</Text>
      {blockDetails.map((block) => (
        <View key={block.id} style={styles.card}>
          <Text style={styles.cardTitle}>{block.name}</Text>
          <Text style={styles.detailText}>Chickens: {block.chickens}</Text>
          <Text style={styles.detailText}>Eggs per day: {block.eggsPerDay}</Text>
          <Text style={styles.detailText}>Mortality Rate: {block.mortalityRate}</Text>
          <Text style={styles.detailText}>Health Status: {block.healthStatus}</Text>
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

export default BlockDetailsScreen;
