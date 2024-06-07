import React from 'react';
import { View, Text, StyleSheet } from 'react-native';
import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';
import MaterialIcons from 'react-native-vector-icons/MaterialIcons';
import FarmDetailsScreen from './FarmDetailsScreen';
import FinanceScreen from './FinanceScreen';
import ReportScreen from './ReportScreen';
import UserManagementScreen from './UserManagementScreen';
import NotificationScreen from './NotificationScreen';

const Tab = createBottomTabNavigator();

const NotificationIconWithDot = ({ color, size }) => (
  <View style={styles.notificationIconContainer}>
    <MaterialIcons name="notifications" size={size} color={color} />
    <View style={styles.notificationDot}>
      <Text style={styles.notificationDotText}>•</Text>
    </View>
  </View>
);

const AdminDashboard = () => {
  return (
    <Tab.Navigator
      screenOptions={({ route }) => ({
        tabBarIcon: ({ color, size, focused }) => {
          let iconName;
          let IconComponent = MaterialIcons;

          if (route.name === 'FarmDetails') {
            iconName = 'home';
          } else if (route.name === 'Finance') {
            iconName = 'attach-money';
          } else if (route.name === 'Report') {
            iconName = 'bar-chart';
          } else if (route.name === 'UserManagement') {
            iconName = 'person';
          } else if (route.name === 'Notification') {
            iconName = 'notifications';
            IconComponent = NotificationIconWithDot;
          }

          return (
            <View style={[styles.iconContainer, focused && styles.iconFocused]}>
              <IconComponent name={iconName} size={focused ? size * 1.2 : size} color={color} />
            </View>
          );
        },
        tabBarActiveTintColor: '#4CAF50',
        tabBarInactiveTintColor: 'gray',
        tabBarStyle: {
          backgroundColor: '#ffffff',
        },
      })}
    >
      <Tab.Screen name="Finance" component={FinanceScreen} />
      <Tab.Screen name="Report" component={ReportScreen} />
      <Tab.Screen name="FarmDetails" component={FarmDetailsScreen} />
      <Tab.Screen name="UserManagement" component={UserManagementScreen} />
      <Tab.Screen name="Notification" component={NotificationScreen} />
    </Tab.Navigator>
  );
};

const styles = StyleSheet.create({
  notificationIconContainer: {
    position: 'relative',
  },
  notificationDot: {
    position: 'absolute',
    right: -8,
    top: -4,
    backgroundColor: '#FF0000',
    borderRadius: 50,
    width: 12,
    height: 12,
    alignItems: 'center',
    justifyContent: 'center',
  },
  notificationDotText: {
    color: '#FFFFFF',
    fontSize: 8,
    fontWeight: 'bold',
  },
  iconContainer: {
    justifyContent: 'center',
    alignItems: 'center',
  },
  iconFocused: {
    backgroundColor: '#4CAF50',
    borderRadius: 50,
    padding: 8,
    transform: [{ scale: 1.1 }],
  },
});

export default AdminDashboard;
