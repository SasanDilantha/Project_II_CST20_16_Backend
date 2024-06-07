import React from 'react';
import { View, Text, StyleSheet, FlatList } from 'react-native';
import { useTheme } from '../../theme/ThemeContext';

const NotificationScreen = () => {
  const { theme } = useTheme();

  const notifications = [
    { id: '1', message: 'Daily egg collection completed.' },
    { id: '2', message: 'New batch of feed delivered.' },
    { id: '3', message: 'Vaccination due for chickens.' },
  ];

  return (
    <View style={[styles.container, { backgroundColor: theme.background }]}>
      {/* <Text style={[styles.title, { color: theme.text }]}>Notifications</Text> */}
      <FlatList
        data={notifications}
        keyExtractor={(item) => item.id}
        renderItem={({ item }) => (
          <View style={[styles.notification, { backgroundColor: theme.secondary }]}>
            <Text style={[styles.notificationText, { color: theme.text }]}>{item.message}</Text>
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
  },
  title: {
    fontSize: 26,
    fontWeight: 'bold',
    marginBottom: 16,
    textAlign: 'center',
  },
  notification: {
    padding: 16,
    borderRadius: 8,
    marginBottom: 8,
    shadowColor: '#000',
    shadowOpacity: 0.1,
    shadowOffset: { width: 0, height: 2 },
    shadowRadius: 4,
    elevation: 2,
  },
  notificationText: {
    fontSize: 16,
  },
});

export default NotificationScreen;
