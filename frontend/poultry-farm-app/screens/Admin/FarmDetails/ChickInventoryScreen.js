import React from 'react';
import { View, Text, StyleSheet, FlatList } from 'react-native';

const chicks = [
  { id: '1', age: '1 week', health: 'Healthy', vaccinated: 'Yes', supplier: 'Supplier A', cost: '$2.00', block: 'Block A', breed: 'Breed X' },
  { id: '2', age: '2 weeks', health: 'Healthy', vaccinated: 'Yes', supplier: 'Supplier B', cost: '$2.50', block: 'Block B', breed: 'Breed Y' },
  { id: '3', age: '3 weeks', health: 'Sick', vaccinated: 'No', supplier: 'Supplier A', cost: '$2.00', block: 'Block C', breed: 'Breed Z' },
  { id: '4', age: '4 weeks', health: 'Healthy', vaccinated: 'Yes', supplier: 'Supplier C', cost: '$3.00', block: 'Block A', breed: 'Breed X' },
  { id: '5', age: '5 weeks', health: 'Healthy', vaccinated: 'Yes', supplier: 'Supplier B', cost: '$2.50', block: 'Block B', breed: 'Breed Y' },
];

const ChickInventoryScreen = () => {
  return (
    <View style={styles.container}>
      <Text style={styles.title}>Chick Inventory</Text>
      <FlatList
        data={chicks}
        keyExtractor={(item) => item.id}
        renderItem={({ item }) => (
          <View style={styles.card}>
            <Text style={styles.cardText}>Age: {item.age}</Text>
            <Text style={styles.cardText}>Health: {item.health}</Text>
            <Text style={styles.cardText}>Vaccinated: {item.vaccinated}</Text>
            <Text style={styles.cardText}>Supplier: {item.supplier}</Text>
            <Text style={styles.cardText}>Cost: {item.cost}</Text>
            <Text style={styles.cardText}>Block: {item.block}</Text>
            <Text style={styles.cardText}>Breed: {item.breed}</Text>
          </View>
        )}
      />
    </View>
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
  cardText: {
    fontSize: 16,
    color: '#555',
    marginBottom: 4,
  },
});

export default ChickInventoryScreen;
