import React from 'react';
import { View, Text } from 'react-native';
import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';
import MaterialIcons from 'react-native-vector-icons/MaterialIcons';
import { TailwindProvider } from 'tailwindcss-react-native';
import FarmDetailsScreen from './FarmDetailsScreen';
import FinanceScreen from './FinanceScreen';
import ReportScreen from './ReportScreen';
import UserManagementScreen from './UserManagementScreen';
import NotificationScreen from './NotificationScreen';

const Tab = createBottomTabNavigator();

const NotificationIconWithDot = ({ color, size }) => (
  <View className="relative">
    <MaterialIcons name="notifications" size={size} color={color} />
    <View className="absolute right-[-8px] top-[-4px] bg-red-500 rounded-full w-3 h-3 flex items-center justify-center">
      <Text className="text-white text-[8px] font-bold">•</Text>
    </View>
  </View>
);

const AdminDashboard = () => {
  return (
    <TailwindProvider>
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
              <View className={`justify-center items-center ${focused ? 'bg-green-500 rounded-full p-2 scale-110' : ''}`}>
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
    </TailwindProvider>
  );
};

export default AdminDashboard;
