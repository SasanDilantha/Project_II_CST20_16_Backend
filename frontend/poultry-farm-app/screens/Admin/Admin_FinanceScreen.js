import React, { useState } from 'react';
import { View, Text, TextInput, StyleSheet, FlatList, TouchableOpacity } from 'react-native';

const FinanceScreen = () => {
  const [transactions, setTransactions] = useState([]);
  const [amount, setAmount] = useState('');
  const [description, setDescription] = useState('');
  const [type, setType] = useState('income'); // 'income' or 'expense'

  const addTransaction = () => {
    if (amount && description) {
      setTransactions([...transactions, { id: Date.now().toString(), amount, description, type }]);
      setAmount('');
      setDescription('');
    } else {
      alert('Please fill out all fields');
    }
  };

  return (
    <View style={styles.container}>
      {/* <Text style={styles.title}>Finance</Text> */}
      <View style={styles.cardContainer}>
        <View style={[styles.card, styles.incomeCard]}>
          <Text style={styles.cardTitle}>Income</Text>
          <Text style={styles.cardAmount}>
            ${transactions.filter(t => t.type === 'income').reduce((acc, t) => acc + parseFloat(t.amount), 0).toFixed(2)}
          </Text>
        </View>
        <View style={[styles.card, styles.expenseCard]}>
          <Text style={styles.cardTitle}>Expenses</Text>
          <Text style={styles.cardAmount}>
            ${transactions.filter(t => t.type === 'expense').reduce((acc, t) => acc + parseFloat(t.amount), 0).toFixed(2)}
          </Text>
        </View>
      </View>
      <View style={styles.inputContainer}>
        <TextInput
          style={styles.input}
          placeholder="Amount"
          keyboardType="numeric"
          value={amount}
          onChangeText={setAmount}
        />
        <TextInput
          style={styles.input}
          placeholder="Description"
          value={description}
          onChangeText={setDescription}
        />
        <View style={styles.buttonRow}>
          <TouchableOpacity
            style={[styles.typeButton, type === 'income' && styles.selectedButton]}
            onPress={() => setType('income')}
          >
            <Text style={styles.buttonText}>Income</Text>
          </TouchableOpacity>
          <TouchableOpacity
            style={[styles.typeButton, type === 'expense' && styles.selectedButton]}
            onPress={() => setType('expense')}
          >
            <Text style={styles.buttonText}>Expense</Text>
          </TouchableOpacity>
        </View>
        <TouchableOpacity style={styles.addButton} onPress={addTransaction}>
          <Text style={styles.addButtonText}>Add Transaction</Text>
        </TouchableOpacity>
      </View>
      <FlatList
        data={transactions}
        keyExtractor={(item) => item.id}
        renderItem={({ item }) => (
          <View style={[styles.transaction, item.type === 'income' ? styles.incomeTransaction : styles.expenseTransaction]}>
            <Text style={styles.transactionText}>{item.description}</Text>
            <Text style={styles.transactionAmount}>${item.amount}</Text>
          </View>
        )}
        style={styles.transactionList}
      />
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    padding: 16,
    backgroundColor: '#f0f4f7',
  },
  title: {
    fontSize: 24,
    fontWeight: 'bold',
    color: '#333',
    marginBottom: 16,
    textAlign: 'center',
  },
  cardContainer: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    marginBottom: 16,
  },
  card: {
    flex: 1,
    padding: 16,
    borderRadius: 8,
    alignItems: 'center',
    marginHorizontal: 8,
    shadowColor: '#000',
    shadowOpacity: 0.1,
    shadowOffset: { width: 0, height: 2 },
    shadowRadius: 4,
    elevation: 3,
  },
  incomeCard: {
    backgroundColor: '#e0f7e9',
  },
  expenseCard: {
    backgroundColor: '#ffe6e1',
  },
  cardTitle: {
    fontSize: 18,
    fontWeight: 'bold',
    marginBottom: 8,
  },
  cardAmount: {
    fontSize: 24,
    fontWeight: 'bold',
  },
  inputContainer: {
    marginBottom: 16,
  },
  input: {
    height: 40,
    borderColor: '#ccc',
    borderWidth: 1,
    borderRadius: 8,
    marginBottom: 8,
    paddingLeft: 8,
    backgroundColor: '#fff',
  },
  buttonRow: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    marginBottom: 16,
  },
  typeButton: {
    flex: 1,
    padding: 12,
    borderRadius: 8,
    alignItems: 'center',
    marginHorizontal: 8,
    backgroundColor: '#ccc',
  },
  selectedButton: {
    backgroundColor: '#4CAF50',
  },
  buttonText: {
    color: '#fff',
    fontSize: 16,
    fontWeight: 'bold',
  },
  addButton: {
    backgroundColor: '#4CAF50',
    padding: 16,
    borderRadius: 8,
    alignItems: 'center',
  },
  addButtonText: {
    color: '#fff',
    fontSize: 16,
    fontWeight: 'bold',
  },
  transactionList: {
    marginTop: 16,
  },
  transaction: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    padding: 16,
    borderRadius: 8,
    marginBottom: 8,
    shadowColor: '#000',
    shadowOpacity: 0.1,
    shadowOffset: { width: 0, height: 2 },
    shadowRadius: 4,
    elevation: 3,
  },
  incomeTransaction: {
    backgroundColor: '#e0f7e9',
  },
  expenseTransaction: {
    backgroundColor: '#ffe6e1',
  },
  transactionText: {
    fontSize: 16,
    color: '#555',
  },
  transactionAmount: {
    fontSize: 16,
    fontWeight: 'bold',
  },
});

export default FinanceScreen;
