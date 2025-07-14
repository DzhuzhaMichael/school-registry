import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import SchoolTable from './components/SchoolTable';
import SchoolForm from './components/SchoolForm';
import './App.css';

function App() {
    return (
        <Router>
            <div className="App">
                <h1>Реєстр шкіл</h1>
                <Routes>
                    <Route path="/" element={<SchoolTable />} />
                    <Route path="/create" element={<SchoolForm />} />
                </Routes>
            </div>
        </Router>
    );
}

export default App;
