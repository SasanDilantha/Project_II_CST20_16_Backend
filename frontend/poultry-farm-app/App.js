import React, { Suspense, lazy } from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import { Text } from 'react-native';

// Dynamic imports for screens
const SplashScreen = lazy(() => import('./screens/Auth/SplashScreen'));
const LoginScreen = lazy(() => import('./screens/Auth/LoginScreen'));
const AdminDashboard = lazy(() => import('./screens/Admin/Admin_Dashboard'));
const FarmDetailsScreen = lazy(() => import('./screens/Admin/Admin_FarmDetailsScreen'));
const FarmDetailScreen = lazy(() => import('./screens/Admin/FarmDetails/FarmDetailScreen'));
const BlockDetailsScreen = lazy(() => import('./screens/Admin/FarmDetails/BlockDetailsScreen'));
const MonitoringScreen = lazy(() => import('./screens/Admin/FarmDetails/MonitoringScreen'));
const ChickInventoryScreen = lazy(() => import('./screens/Admin/FarmDetails/ChickInventoryScreen'));
const FeedInventoryScreen = lazy(() => import('./screens/Admin/FarmDetails/FeedInventoryScreen'));
const MedicalInventoryScreen = lazy(() => import('./screens/Admin/FarmDetails/MedicalInventoryScreen'));
const FinanceScreen = lazy(() => import('./screens/Admin/Admin_FinanceScreen'));
const ReportScreen = lazy(() => import('./screens/Admin/Admin_ReportScreen'));
const NotificationScreen = lazy(() => import('./screens/Admin/Admin_NotificationScreen'));
const UserManagementScreen = lazy(() => import('./screens/Admin/Admin_UserManagementScreen'));

const Stack = createNativeStackNavigator();

const App = () => {
  return (
    <NavigationContainer>
      <Suspense fallback={<Text>Loading...</Text>}>
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
      </Suspense>
    </NavigationContainer>
  );
};

export default App;
