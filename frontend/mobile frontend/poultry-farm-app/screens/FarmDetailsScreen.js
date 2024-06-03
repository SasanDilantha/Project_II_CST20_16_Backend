import React from 'react';
import { View, Text, StyleSheet, ScrollView, TouchableOpacity } from 'react-native';
import Icon from 'react-native-vector-icons/MaterialCommunityIcons';

const farms = [
  { id: 1, name: 'Farm 1', location: 'Location 1', chickens: 150, eggsPerDay: 120 },
  { id: 2, name: 'Farm 2', location: 'Location 2', chickens: 200, eggsPerDay: 180 },
  { id: 3, name: 'Farm 3', location: 'Location 3', chickens: 250, eggsPerDay: 220 },
];

const FarmDetailsScreen = ({ navigation }) => {
  return (
    <ScrollView style={styles.container}>
      {farms.map((farm) => (
        <TouchableOpacity key={farm.id} style={styles.card} onPress={() => navigation.navigate('FarmDetail', { farm })}>
          <View style={styles.cardContent}>
            <Icon name="home" size={30} color="#4CAF50" style={styles.icon} />
            <View style={styles.textContainer}>
              <Text style={styles.title}>{farm.name}</Text>
              <Text>Location: {farm.location}</Text>
              <Text>Chickens: {farm.chickens}</Text>
              <Text>Eggs per day: {farm.eggsPerDay}</Text>
            </View>
          </View>
        </TouchableOpacity>
      ))}
      <TouchableOpacity style={styles.addButton} onPress={() => { /* Navigate to Add Farm Screen */ }}>
        <Icon name="plus-circle" size={50} color="#4CAF50" />
      </TouchableOpacity>
    </ScrollView>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    padding: 16,
    backgroundColor: '#f8f8f8',
  },
  card: {
    backgroundColor: '#fff',
    padding: 16,
    borderRadius: 8,
    marginBottom: 16,
    elevation: 2,
    flexDirection: 'row',
    alignItems: 'center',
  },
  cardContent: {
    flexDirection: 'row',
    alignItems: 'center',
  },
  icon: {
    marginRight: 16,
  },
  textContainer: {
    flex: 1,
  },
  title: {
    fontSize: 18,
    fontWeight: 'bold',
    marginBottom: 8,
  },
  addButton: {
    alignItems: 'center',
    justifyContent: 'center',
    marginTop: 16,
  },
});

export default FarmDetailsScreen;
