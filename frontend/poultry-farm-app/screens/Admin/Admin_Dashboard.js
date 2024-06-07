import React, { useState, useEffect } from 'react';
import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';
import MaterialIcons from 'react-native-vector-icons/MaterialIcons';
import FarmDetailsScreen from './Admin_FarmDetailsScreen';
import FinanceScreen from './Admin_FinanceScreen';
import ReportScreen from './Admin_ReportScreen';
import UserManagementScreen from './Admin_UserManagementScreen';
import NotificationScreen from './Admin_NotificationScreen';
import { View, StyleSheet, Text, TouchableOpacity, Animated, Dimensions, Modal, Button } from 'react-native';
import { useTheme } from '../../theme/ThemeContext';

const Tab = createBottomTabNavigator();
const { width } = Dimensions.get('window');

const SidePanel = ({ visible, onClose }) => {
  const { theme, toggleTheme } = useTheme();
  const slideAnim = useState(new Animated.Value(width))[0];

  useEffect(() => {
    if (visible) {
      Animated.timing(slideAnim, {
        toValue: 0,
        duration: 300,
        useNativeDriver: true,
      }).start();
    } else {
      Animated.timing(slideAnim, {
        toValue: width,
        duration: 300,
        useNativeDriver: true,
      }).start();
    }
  }, [visible, slideAnim]);

  return (
    <Modal
      animationType="none"
      transparent={true}
      visible={visible}
      onRequestClose={onClose}
    >
      <View style={styles.modalContainer}>
        <Animated.View style={[styles.sidePanel, { transform: [{ translateX: slideAnim }], backgroundColor: theme.background }]}>
          <View style={styles.sidePanelHeader}>
            <Text style={[styles.sidePanelTitle, { color: theme.text }]}>Add New Item</Text>
            <TouchableOpacity onPress={onClose} style={styles.menuIconContainer}>
              <MaterialIcons name="menu" size={24} color={theme.primary} />
            </TouchableOpacity>
          </View>
          {/* Add your form or content here */}
          <View style={styles.themeToggleContainer}>
            <Button title="Toggle Theme" onPress={toggleTheme} color={theme.primary} />
          </View>
        </Animated.View>
      </View>
    </Modal>
  );
};

const AdminDashboard = () => {
  const { theme } = useTheme();
  const [isSidePanelVisible, setIsSidePanelVisible] = useState(false);

  const toggleSidePanel = () => {
    setIsSidePanelVisible(!isSidePanelVisible);
  };

  return (
    <View style={{ flex: 1 }}>
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
          tabBarActiveTintColor: theme.primary,
          tabBarInactiveTintColor: 'gray',
          tabBarStyle: {
            backgroundColor: theme.background,
          },
          headerStyle: {
            backgroundColor: theme.background,
          },
          headerTintColor: theme.text,
          headerRight: () => (
            <TouchableOpacity onPress={toggleSidePanel} style={styles.menuIconContainer}>
              <MaterialIcons name="menu" size={24} color={theme.primary} />
            </TouchableOpacity>
          ),
        })}
      >
        <Tab.Screen name="Finance" component={FinanceScreen} />
        <Tab.Screen name="Report" component={ReportScreen} />
        <Tab.Screen name="FarmDetails" component={FarmDetailsScreen} />
        <Tab.Screen name="UserManagement" component={UserManagementScreen} />
        <Tab.Screen name="Notification" component={NotificationScreen} />
      </Tab.Navigator>
      <SidePanel visible={isSidePanelVisible} onClose={toggleSidePanel} />
    </View>
  );
};

const styles = StyleSheet.create({
  modalContainer: {
    flex: 1,
    justifyContent: 'flex-end',
    alignItems: 'flex-end',
    backgroundColor: 'rgba(0, 0, 0, 0.5)',
  },
  iconContainer: {
    justifyContent: 'center',
    alignItems: 'center',
  },
  iconFocused: {
    transform: [{ scale: 1.2 }],
  },
  sidePanel: {
    position: 'absolute',
    top: 0,
    right: 0,
    bottom: 0,
    width: '80%',
    backgroundColor: '#fff',
    borderLeftWidth: 1,
    borderLeftColor: '#ddd',
    padding: 20,
  },
  sidePanelHeader: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
  },
  sidePanelTitle: {
    fontSize: 20,
    fontWeight: 'bold',
  },
  closeButton: {
    padding: 10,
    borderRadius: 5,
  },
  closeButtonText: {
    color: '#fff',
  },
  menuIconContainer: {
    padding: 10,
  },
  themeToggleContainer: {
    marginTop: 20,
  },
});

export default AdminDashboard;
