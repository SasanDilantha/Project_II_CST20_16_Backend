import React from 'react';
import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';
import MaterialIcons from 'react-native-vector-icons/MaterialIcons';
import FarmDetailsScreen from './Admin_FarmDetailsScreen';
import FinanceScreen from './Admin_FinanceScreen';
import ReportScreen from './Admin_ReportScreen';
import UserManagementScreen from './Admin_UserManagementScreen';
import NotificationScreen from './Admin_NotificationScreen';
import { View, StyleSheet } from 'react-native';

const Tab = createBottomTabNavigator();

const AdminDashboard = () => {
  return (
    <Tab.Navigator
      screenOptions={({ route }) => ({
        tabBarIcon: ({ color, size, focused }) => {
          let iconName;

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
          }

          return (
            <View style={[styles.iconContainer, focused && styles.iconFocused]}>
              <MaterialIcons name={iconName} size={focused ? size * 1.2 : size} color={color} />
            </View>
          );
        },
        tabBarActiveTintColor: '#4CAF50',
        tabBarInactiveTintColor: 'gray',
        tabBarStyle: {
          backgroundColor: '#fff',
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
  iconContainer: {
    justifyContent: 'center',
    alignItems: 'center',
  },
  iconFocused: {
    transform: [{ scale: 1.2 }],
  },
});

export default AdminDashboard;
