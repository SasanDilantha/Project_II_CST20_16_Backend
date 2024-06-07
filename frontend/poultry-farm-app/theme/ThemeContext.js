// theme/ThemeContext.js
import React, { createContext, useState, useContext } from 'react';
import { Appearance } from 'react-native';

const ThemeContext = createContext();

const lightTheme = {
  mode: 'light',
  background: '#ffffff',
  text: '#000000',
  primary: '#4CAF50',
  secondary: '#8BC34A',
};

const darkTheme = {
  mode: 'dark',
  background: '#121212',
  text: '#ffffff',
  primary: '#4CAF50',
  secondary: '#8BC34A',
};

export const ThemeProvider = ({ children }) => {
  const colorScheme = Appearance.getColorScheme();
  const defaultTheme = colorScheme === 'dark' ? darkTheme : lightTheme;
  
  const [theme, setTheme] = useState(defaultTheme);

  const toggleTheme = () => {
    setTheme((prevTheme) => (prevTheme.mode === 'light' ? darkTheme : lightTheme));
  };

  return (
    <ThemeContext.Provider value={{ theme, toggleTheme }}>
      {children}
    </ThemeContext.Provider>
  );
};

export const useTheme = () => {
  const context = useContext(ThemeContext);
  if (!context) {
    throw new Error('useTheme must be used within a ThemeProvider');
  }
  return context;
};
