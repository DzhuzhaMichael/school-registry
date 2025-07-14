import React, { useEffect, useState } from 'react';
import { getSchools, deactivateSchool } from '../services/api';
import { REGIONS, SCHOOL_TYPES } from '../constants/enums';

const SchoolTable = () => {
    const [schools, setSchools] = useState([]);

    const [filters, setFilters] = useState({
        region: '',
        type: '',
        active: '',
    });

    const [localFilters, setLocalFilters] = useState({
        region: '',
        type: '',
        active: '',
    });

    const fetchSchools = async (appliedFilters) => {
        try {
            const cleanFilters = Object.fromEntries(
                Object.entries(appliedFilters).filter(([_, v]) => v !== '')
            );
            const response = await getSchools(cleanFilters);
            setSchools(response.data);
        } catch (error) {
            console.error('Error fetching schools:', error);
        }
    };

    useEffect(() => {
        fetchSchools(filters);
    }, []);

    const handleSearch = () => {
        setFilters(localFilters);
        fetchSchools(localFilters);
    };

    const handleChange = (e) => {
        const { name, value } = e.target;
        setLocalFilters((prev) => ({ ...prev, [name]: value }));
    };

    const handleDeactivate = async (id) => {
        if (window.confirm('Ви дійсно бажаєте деактивувати школу?')) {
            try {
                await deactivateSchool(id);
                fetchSchools(filters);
            } catch (error) {
                console.error('Помилка при деактивації:', error);
            }
        }
    };

    return (
        <div>
            <div className="filter-group">
                <div className="filter-row">
                    <label>Область:</label>
                    <select
                        name="region"
                        value={localFilters.region}
                        onChange={handleChange}
                    >
                        <option value="">Усі</option>
                        {REGIONS.map((region) => (
                            <option key={region} value={region}>
                                {region}
                            </option>
                        ))}
                    </select>
                </div>

                <div className="filter-row">
                    <label>Тип:</label>
                    <select
                        name="type"
                        value={localFilters.type}
                        onChange={handleChange}
                    >
                        <option value="">Усі</option>
                        {SCHOOL_TYPES.map((type) => (
                            <option key={type} value={type}>
                                {type}
                            </option>
                        ))}
                    </select>
                </div>

                <div className="filter-row">
                    <label>Активність:</label>
                    <select
                        name="active"
                        value={localFilters.active}
                        onChange={handleChange}
                    >
                        <option value="">Усі</option>
                        <option value="true">Активні</option>
                        <option value="false">Неактивні</option>
                    </select>
                </div>

                <div className="button-group">
                    <button onClick={handleSearch}>Знайти</button>
                    <button onClick={() => window.location.href = '/create'}>
                        Додати школу
                    </button>
                </div>
            </div>

            <table border="1" cellPadding="8">
                <thead>
                <tr>
                    <th>Назва</th>
                    <th>ЄДРПОУ</th>
                    <th>Область</th>
                    <th>Тип</th>
                    <th>Активна</th>
                    <th>Дія</th>
                </tr>
                </thead>
                <tbody>
                {schools.length === 0 ? (
                    <tr>
                        <td colSpan="6" style={{ textAlign: 'center' }}>
                            Шкіл не знайдено
                        </td>
                    </tr>
                ) : (
                    schools.map((school) => (
                        <tr key={school.id}>
                            <td>{school.name}</td>
                            <td>{school.edrpou}</td>
                            <td>{school.region}</td>
                            <td>{school.type}</td>
                            <td>{school.active ? 'Так' : 'Ні'}</td>
                            <td>
                                {school.active && (
                                    <button onClick={() => handleDeactivate(school.id)}>
                                        Деактивувати школу
                                    </button>
                                )}
                            </td>
                        </tr>
                    ))
                )}
                </tbody>
            </table>
        </div>
    );
};

export default SchoolTable;


