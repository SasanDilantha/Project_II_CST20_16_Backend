import React, { useState } from 'react';
import { View, Text, TextInput, StyleSheet, ScrollView, Modal, Alert, TouchableOpacity } from 'react-native';
import Icon from 'react-native-vector-icons/MaterialCommunityIcons';
import { Picker } from '@react-native-picker/picker';
import { useTheme } from '../../theme/ThemeContext';

const UserManagementScreen = () => {
  const { theme } = useTheme();
  const [selectedFarm, setSelectedFarm] = useState('');
  const [showEmployees, setShowEmployees] = useState(false);
  const [employees, setEmployees] = useState([
    { id: '1', name: 'John Doe', position: 'Farm Manager', email: 'john@example.com', phone: '1234567890', address: '123 Farm Road', farm: 'Happy Farm', works: 'Supervising', salary: 5000, role: 'Farm Manager' },
    { id: '2', name: 'Jane Smith', position: 'Farm Worker', email: 'jane@example.com', phone: '0987654321', address: '456 Farm Lane', farm: 'Happy Farm', works: 'Feeding', salary: 3000, role: 'Farm Employee' },
    { id: '3', name: 'Sam Green', position: 'Veterinarian', email: 'sam@example.com', phone: '1112223333', address: '789 Farm Street', farm: 'Sunshine Farm', works: 'Animal Health', salary: 4500, role: 'Veterinarian' },
    { id: '4', name: 'Emily White', position: 'Farm Worker', email: 'emily@example.com', phone: '4445556666', address: '101 Farm Blvd', farm: 'Sunshine Farm', works: 'Maintenance', salary: 2800, role: 'Farm Employee' },
    { id: '5', name: 'Michael Brown', position: 'Accountant', email: 'michael@example.com', phone: '7778889999', address: '102 Farm Rd', farm: 'Happy Farm', works: 'Finance', salary: 4000, role: 'Accountant' },
    { id: '6', name: 'Linda Blue', position: 'Farm Worker', email: 'linda@example.com', phone: '1122334455', address: '103 Farm St', farm: 'Happy Farm', works: 'Harvesting', salary: 2900, role: 'Farm Employee' },
  ]);
  const [modalVisible, setModalVisible] = useState(false);
  const [deleteModalVisible, setDeleteModalVisible] = useState(false);
  const [isEditing, setIsEditing] = useState(false);
  const [editEmployeeId, setEditEmployeeId] = useState(null);
  const [employeeToDelete, setEmployeeToDelete] = useState(null);
  const [newEmployee, setNewEmployee] = useState({
    name: '',
    position: '',
    email: '',
    phone: '',
    address: '',
    farm: '',
    works: '',
    salary: '',
    role: 'Farm Employee', // Default role
  });

  const handleAddEmployee = () => {
    if (
      !newEmployee.name ||
      !newEmployee.position ||
      !newEmployee.email ||
      !newEmployee.phone ||
      !newEmployee.address ||
      !newEmployee.farm ||
      !newEmployee.works ||
      !newEmployee.salary
    ) {
      Alert.alert('Error', 'Please fill in all fields');
      return;
    }
    if (isEditing) {
      setEmployees(employees.map(employee => employee.id === editEmployeeId ? { ...newEmployee, id: editEmployeeId } : employee));
      setIsEditing(false);
      setEditEmployeeId(null);
    } else {
      setEmployees([...employees, { ...newEmployee, id: Date.now().toString() }]);
    }
    setNewEmployee({
      name: '',
      position: '',
      email: '',
      phone: '',
      address: '',
      farm: selectedFarm,
      works: '',
      salary: '',
      role: 'Farm Employee',
    });
    setModalVisible(false);
  };

  const handleEditEmployee = (employee) => {
    setNewEmployee(employee);
    setEditEmployeeId(employee.id);
    setIsEditing(true);
    setModalVisible(true);
  };

  const handleDeleteEmployee = () => {
    setEmployees(employees.filter(employee => employee.id !== employeeToDelete.id));
    setDeleteModalVisible(false);
  };

  const confirmDeleteEmployee = (employee) => {
    setEmployeeToDelete(employee);
    setDeleteModalVisible(true);
  };

  const filteredEmployees = employees.filter(employee => employee.farm === selectedFarm);
  const employeeCount = filteredEmployees.length;

  return (
    <ScrollView style={[styles.container, { backgroundColor: theme.background }]}>
      {/* <Text style={[styles.title, { color: theme.text }]}>User Management</Text> */}

      <Picker
        selectedValue={selectedFarm}
        onValueChange={(itemValue) => {
          setSelectedFarm(itemValue);
          setShowEmployees(false);
        }}
        style={[styles.picker, { color: theme.text }]}
      >
        <Picker.Item label="Select Farm" value="" />
        <Picker.Item label="Happy Farm" value="Happy Farm" />
        <Picker.Item label="Sunshine Farm" value="Sunshine Farm" />
      </Picker>

      {selectedFarm ? (
        <>
          {!showEmployees && (
            <View style={[styles.summaryCard, { backgroundColor: theme.secondary }]}>
              <Text style={[styles.summaryText, { color: theme.text }]}>Farm: {selectedFarm}</Text>
              <Text style={[styles.summaryText, { color: theme.text }]}>Total Employees: {employeeCount}</Text>
              
              <TouchableOpacity style={[styles.button, { backgroundColor: theme.primary }]} onPress={() => setShowEmployees(true)}>
                <Icon name="account-multiple" size={24} color="#fff" />
                <Text style={styles.buttonText}>View Current Employees</Text>
              </TouchableOpacity>
              
              <TouchableOpacity style={[styles.button, { backgroundColor: theme.primary }]} onPress={() => setModalVisible(true)}>
                <Icon name="account-plus" size={24} color="#fff" />
                <Text style={styles.buttonText}>Add Employee</Text>
              </TouchableOpacity>
            </View>
          )}
        </>
      ) : (
        <Text style={[styles.selectFarmText, { color: theme.text }]}>Please select a farm to manage employees.</Text>
      )}

      <Modal
        animationType="slide"
        transparent={true}
        visible={modalVisible}
        onRequestClose={() => setModalVisible(false)}
      >
        <View style={styles.modalContainer}>
          <View style={[styles.modalView, { backgroundColor: theme.secondary }]}>
            <Text style={[styles.modalTitle, { color: theme.text }]}>{isEditing ? 'Edit Employee' : 'Add New Employee'}</Text>
            <TextInput
              style={[styles.input, { backgroundColor: theme.background, color: theme.text }]}
              placeholder="Name"
              placeholderTextColor={theme.text}
              value={newEmployee.name}
              onChangeText={(text) => setNewEmployee({ ...newEmployee, name: text })}
            />
            <TextInput
              style={[styles.input, { backgroundColor: theme.background, color: theme.text }]}
              placeholder="Position"
              placeholderTextColor={theme.text}
              value={newEmployee.position}
              onChangeText={(text) => setNewEmployee({ ...newEmployee, position: text })}
            />
            <TextInput
              style={[styles.input, { backgroundColor: theme.background, color: theme.text }]}
              placeholder="Email"
              placeholderTextColor={theme.text}
              value={newEmployee.email}
              onChangeText={(text) => setNewEmployee({ ...newEmployee, email: text })}
              keyboardType="email-address"
            />
            <TextInput
              style={[styles.input, { backgroundColor: theme.background, color: theme.text }]}
              placeholder="Phone"
              placeholderTextColor={theme.text}
              value={newEmployee.phone}
              onChangeText={(text) => setNewEmployee({ ...newEmployee, phone: text })}
              keyboardType="phone-pad"
            />
            <TextInput
              style={[styles.input, { backgroundColor: theme.background, color: theme.text }]}
              placeholder="Address"
              placeholderTextColor={theme.text}
              value={newEmployee.address}
              onChangeText={(text) => setNewEmployee({ ...newEmployee, address: text })}
            />
            <TextInput
              style={[styles.input, { backgroundColor: theme.background, color: theme.text }]}
              placeholder="Works"
              placeholderTextColor={theme.text}
              value={newEmployee.works}
              onChangeText={(text) => setNewEmployee({ ...newEmployee, works: text })}
            />
            <TextInput
              style={[styles.input, { backgroundColor: theme.background, color: theme.text }]}
              placeholder="Salary"
              placeholderTextColor={theme.text}
              value={newEmployee.salary}
              onChangeText={(text) => setNewEmployee({ ...newEmployee, salary: text })}
              keyboardType="numeric"
            />
            <View style={styles.modalButtons}>
              <TouchableOpacity style={[styles.modalButton, styles.cancelButton]} onPress={() => setModalVisible(false)}>
                <Text style={styles.buttonText}>Cancel</Text>
              </TouchableOpacity>
              <TouchableOpacity style={[styles.modalButton, styles.saveButton, { backgroundColor: theme.primary }]} onPress={handleAddEmployee}>
                <Text style={styles.buttonText}>{isEditing ? "Save Changes" : "Add Employee"}</Text>
              </TouchableOpacity>
            </View>
          </View>
        </View>
      </Modal>

      <Modal
        animationType="slide"
        transparent={true}
        visible={deleteModalVisible}
        onRequestClose={() => setDeleteModalVisible(false)}
      >
        <View style={styles.modalContainer}>
          <View style={[styles.modalView, { backgroundColor: theme.secondary }]}>
            <Text style={[styles.modalTitle, { color: theme.text }]}>Are you sure?</Text>
            <Text style={{ color: theme.text }}>Do you really want to delete {employeeToDelete?.name}?</Text>
            <View style={styles.modalButtons}>
              <TouchableOpacity style={[styles.modalButton, styles.cancelButton]} onPress={() => setDeleteModalVisible(false)}>
                <Text style={styles.buttonText}>No</Text>
              </TouchableOpacity>
              <TouchableOpacity style={[styles.modalButton, styles.deleteButton, { backgroundColor: theme.primary }]} onPress={handleDeleteEmployee}>
                <Text style={styles.buttonText}>Yes</Text>
              </TouchableOpacity>
            </View>
          </View>
        </View>
      </Modal>

      {showEmployees && (
        <View style={styles.section}>
          <Text style={[styles.sectionTitle, { color: theme.text }]}>Employees at {selectedFarm}</Text>
          {filteredEmployees.map((employee) => (
            <View key={employee.id} style={[styles.card, { backgroundColor: theme.secondary }]}>
              <Icon name="account" size={24} color={theme.primary} style={styles.icon} />
              <View style={styles.cardContent}>
                <Text style={[styles.cardText, { color: theme.text }]}>Name: {employee.name}</Text>
                <Text style={[styles.cardText, { color: theme.text }]}>Position: {employee.position}</Text>
                <Text style={[styles.cardText, { color: theme.text }]}>Email: {employee.email}</Text>
                <Text style={[styles.cardText, { color: theme.text }]}>Phone: {employee.phone}</Text>
                <Text style={[styles.cardText, { color: theme.text }]}>Address: {employee.address}</Text>
                <Text style={[styles.cardText, { color: theme.text }]}>Farm: {employee.farm}</Text>
                <Text style={[styles.cardText, { color: theme.text }]}>Works: {employee.works}</Text>
                <Text style={[styles.cardText, { color: theme.text }]}>Salary: ${employee.salary}</Text>
                <Text style={[styles.cardText, { color: theme.text }]}>Role: {employee.role}</Text>
              </View>
              <TouchableOpacity onPress={() => handleEditEmployee(employee)}>
                <Icon name="pencil" size={24} color={theme.primary} />
              </TouchableOpacity>
              <TouchableOpacity onPress={() => confirmDeleteEmployee(employee)}>
                <Icon name="delete" size={24} color="#FF5722" style={styles.deleteIcon} />
              </TouchableOpacity>
            </View>
          ))}
        </View>
      )}
    </ScrollView>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
  title: {
    fontSize: 26,
    fontWeight: 'bold',
    marginBottom: 16,
    textAlign: 'center',
    marginTop: 16,
  },
  picker: {
    marginHorizontal: 16,
    marginBottom: 24,
  },
  button: {
    flexDirection: 'row',
    alignItems: 'center',
    padding: 12,
    borderRadius: 8,
    marginHorizontal: 16,
    marginBottom: 16,
  },
  buttonText: {
    color: '#fff',
    fontSize: 16,
    fontWeight: 'bold',
    marginLeft: 8,
  },
  input: {
    width: '100%',
    height: 40,
    borderColor: '#ccc',
    borderWidth: 1,
    borderRadius: 5,
    marginBottom: 16,
    paddingLeft: 8,
  },
  modalContainer: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: 'rgba(0,0,0,0.5)',
  },
  modalView: {
    width: '90%',
    borderRadius: 20,
    padding: 35,
    alignItems: 'center',
    shadowColor: '#000',
    shadowOffset: {
      width: 0,
      height: 2,
    },
    shadowOpacity: 0.25,
    shadowRadius: 4,
    elevation: 5,
  },
  modalTitle: {
    fontSize: 20,
    fontWeight: 'bold',
    marginBottom: 20,
  },
  modalButtons: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    width: '100%',
    marginTop: 20,
  },
  modalButton: {
    flex: 1,
    padding: 10,
    borderRadius: 8,
    alignItems: 'center',
    marginHorizontal: 5,
  },
  cancelButton: {
    backgroundColor: '#757575',
  },
  saveButton: {},
  deleteButton: {},
  section: {
    paddingHorizontal: 16,
    paddingBottom: 16,
  },
  sectionTitle: {
    fontSize: 22,
    fontWeight: 'bold',
    marginBottom: 16,
  },
  summaryCard: {
    padding: 16,
    borderRadius: 8,
    marginHorizontal: 16,
    marginBottom: 16,
    elevation: 3,
  },
  summaryText: {
    fontSize: 18,
    marginBottom: 4,
  },
  card: {
    flexDirection: 'row',
    padding: 16,
    borderRadius: 8,
    marginBottom: 16,
    alignItems: 'center',
    shadowColor: '#000',
    shadowOpacity: 0.1,
    shadowOffset: { width: 0, height: 2 },
    shadowRadius: 4,
    elevation: 3,
  },
  icon: {
    marginRight: 16,
  },
  cardContent: {
    flex: 1,
  },
  cardText: {
    fontSize: 16,
    marginBottom: 4,
  },
  deleteIcon: {
    marginLeft: 8,
  },
  selectFarmText: {
    textAlign: 'center',
    fontSize: 16,
    marginTop: 16,
  },
});

export default UserManagementScreen;
