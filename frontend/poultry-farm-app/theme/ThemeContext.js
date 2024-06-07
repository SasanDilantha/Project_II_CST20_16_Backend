import React, { createContext, useState, useContext } from 'react';
import { Appearance } from 'react-native';

const ThemeContext = createContext();

const lightTheme = {
    mode: 'light',
    background: '#FFF4E6', // light peach background
    text: '#3A3A3A', // dark grey text
    primary: '#FF8C00', // vibrant orange for primary actions
    secondary: '#FFD700', // bright yellow for secondary actions
  };
  
  const darkTheme = {
    mode: 'dark',
    background: '#1E1E1E', // very dark grey background
    text: '#E0E0E0', // light grey text
    primary: '#FF8C00', // vibrant orange for primary actions
    secondary: '#FFD700', // bright yellow for secondary actions
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
