import React from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import SplashScreen from './screens/SplashScreen';
import LoginScreen from './screens/LoginScreen';
import AdminDashboard from './screens/AdminDashboard';
import FarmDetailsScreen from './screens/FarmDetailsScreen';
import FarmDetailScreen from './screens/FarmDetails/FarmDetailScreen';
import BlockDetailsScreen from './screens/FarmDetails/BlockDetailsScreen';
import MonitoringScreen from './screens/FarmDetails/MonitoringScreen';
import ChickInventoryScreen from './screens/FarmDetails/ChickInventoryScreen';
import FeedInventoryScreen from './screens/FarmDetails/FeedInventoryScreen';
import MedicalInventoryScreen from './screens/FarmDetails/MedicalInventoryScreen';
import FinanceScreen from './screens/FinanceScreen';
import ReportScreen from './screens/ReportScreen';
import NotificationScreen from './screens/NotificationScreen';
import UserManagementScreen from './screens/UserManagementScreen';

const Stack = createNativeStackNavigator();

export default function App() {
  return (
    <NavigationContainer>
      <Stack.Navigator initialRouteName="Splash">
        <Stack.Screen name="Splash" component={SplashScreen} options={{ headerShown: false }} />
        <Stack.Screen name="Login" component={LoginScreen} options={{ headerShown: false }} />
        <Stack.Screen name="AdminDashboard" component={AdminDashboard} options={{ headerShown: false }} />
        <Stack.Screen name="FarmDetails" component={FarmDetailsScreen} options={{ title: 'Farm Details' }} />
        <Stack.Screen name="FarmDetail" component={FarmDetailScreen} options={{ title: 'Farm Detail' }} />
        <Stack.Screen name="BlockDetails" component={BlockDetailsScreen} options={{ title: 'Block Details' }} />
        <Stack.Screen name="Monitoring" component={MonitoringScreen} options={{ title: 'Monitoring' }} />
        <Stack.Screen name="ChickInventory" component={ChickInventoryScreen} options={{ title: 'Chick Inventory' }} />
        <Stack.Screen name="FeedInventory" component={FeedInventoryScreen} options={{ title: 'Feed Inventory' }} />
        <Stack.Screen name="MedicalInventory" component={MedicalInventoryScreen} options={{ title: 'Medical Inventory' }} />
        <Stack.Screen name="Finance" component={FinanceScreen} options={{ title: 'Finance' }} />
        <Stack.Screen name="Report" component={ReportScreen} options={{ title: 'Report' }} />
        <Stack.Screen name="Notification" component={NotificationScreen} options={{ title: 'Notification' }} />
        <Stack.Screen name="UserManagement" component={UserManagementScreen} options={{ title: 'User Management' }} />
      </Stack.Navigator>
    </NavigationContainer>
  );
}
