import React from 'react';
import { View, Text, StyleSheet, TouchableOpacity } from 'react-native';
import Icon from 'react-native-vector-icons/MaterialCommunityIcons';

const FarmDetailScreen = ({ route, navigation }) => {
  const { farm } = route.params;

  return (
    <View style={styles.container}>
      <View style={styles.detailCard}>
        <Text style={styles.title}>{farm.name}</Text>
        <Text style={styles.detailText}>Location: {farm.location}</Text>
        <Text style={styles.detailText}>Chickens: {farm.chickens}</Text>
        <Text style={styles.detailText}>Eggs per day: {farm.eggsPerDay}</Text>
      </View>
      <View style={styles.cardContainer}>
        <TouchableOpacity style={styles.card} onPress={() => navigation.navigate('BlockDetails')}>
          <Icon name="view-dashboard-outline" size={30} color="#4CAF50" style={styles.icon} />
          <Text style={styles.cardText}>Block Details</Text>
        </TouchableOpacity>
        <TouchableOpacity style={styles.card} onPress={() => navigation.navigate('Monitoring')}>
          <Icon name="monitor" size={30} color="#4CAF50" style={styles.icon} />
          <Text style={styles.cardText}>Monitoring</Text>
        </TouchableOpacity>
        <TouchableOpacity style={styles.card} onPress={() => navigation.navigate('ChickInventory')}>
          <Icon name="chicken" size={30} color="#4CAF50" style={styles.icon} />
          <Text style={styles.cardText}>Chick Inventory</Text>
        </TouchableOpacity>
        <TouchableOpacity style={styles.card} onPress={() => navigation.navigate('FeedInventory')}>
          <Icon name="food-variant" size={30} color="#4CAF50" style={styles.icon} />
          <Text style={styles.cardText}>Feed Inventory</Text>
        </TouchableOpacity>
        <TouchableOpacity style={styles.card} onPress={() => navigation.navigate('MedicalInventory')}>
          <Icon name="medical-bag" size={30} color="#4CAF50" style={styles.icon} />
          <Text style={styles.cardText}>Medical Inventory</Text>
        </TouchableOpacity>
      </View>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    padding: 16,
    backgroundColor: '#f8f8f8',
  },
  detailCard: {
    backgroundColor: '#fff',
    padding: 16,
    borderRadius: 8,
    marginBottom: 16,
    elevation: 3,
  },
  title: {
    fontSize: 24,
    fontWeight: 'bold',
    marginBottom: 8,
    textAlign: 'center',
    color: '#333',
  },
  detailText: {
    fontSize: 18,
    color: '#555',
    marginBottom: 4,
  },
  cardContainer: {
    flexDirection: 'row',
    flexWrap: 'wrap',
    justifyContent: 'space-between',
  },
  card: {
    backgroundColor: '#fff',
    padding: 16,
    borderRadius: 8,
    width: '48%',
    marginBottom: 16,
    elevation: 2,
    alignItems: 'center',
  },
  icon: {
    marginBottom: 8,
  },
  cardText: {
    fontSize: 18,
    textAlign: 'center',
  },
});

export default FarmDetailScreen;
