import React, { useState } from 'react';
import { createSchool } from '../services/api';
import { useNavigate } from 'react-router-dom';
import { REGIONS, SCHOOL_TYPES } from '../constants/enums';

const SchoolForm = () => {
    const navigate = useNavigate();

    const [formData, setFormData] = useState({
        name: '',
        edrpou: '',
        region: '',
        type: '',
    });

    const [errors, setErrors] = useState(null);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData((prev) => ({ ...prev, [name]: value }));
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        if (formData.edrpou.length !== 8) {
            setErrors(['ЄДРПОУ має містити рівно 8 символів']);
            return;
        }
        try {
            await createSchool(formData);
            alert('Школу успішно створено!');
            navigate('/');
        } catch (err) {
            console.error(err);
            if (err.response?.data) {
                const data = err.response.data;
                if (typeof data === 'string') {
                    setErrors([data]);
                } else if (data.errors && Array.isArray(data.errors)) {
                    setErrors(data.errors);
                } else if (data.message) {
                    setErrors([data.message]);
                } else if (data.error) {
                    setErrors([data.error]);
                } else {
                    setErrors(['Невідома помилка']);
                }
            } else {
                setErrors(['Сервер недоступний або помилка з’єднання.']);
            }
        }
    };

    return (
        <div>
            <h2>Додати нову школу</h2>
            {errors && (
                <div style={{color: 'red', marginBottom: '1rem'}}>
                    {errors.map((err, idx) => (
                        <div key={idx}>{err}</div>
                    ))}
                </div>
            )}
            <form onSubmit={handleSubmit} className="form-group">
                <div className="form-row">
                    <label>
                        Назва:<span className="required-marker">*</span>
                    </label>
                    <input
                        name="name"
                        value={formData.name}
                        onChange={handleChange}
                        required
                    />
                </div>

                <div className="form-row">
                    <label>
                        ЄДРПОУ:<span className="required-marker">*</span>
                    </label>
                    <input
                        name="edrpou"
                        value={formData.edrpou}
                        onChange={handleChange}
                        required
                        minLength={8}
                        maxLength={8}
                    />
                </div>

                <div className="form-row">
                    <label>
                        Область:<span className="required-marker">*</span>
                    </label>
                    <select
                        name="region"
                        value={formData.region}
                        onChange={handleChange}
                        required
                    >
                        <option value="">Оберіть область</option>
                        {REGIONS.map((region) => (
                            <option key={region} value={region}>
                                {region}
                            </option>
                        ))}
                    </select>
                </div>

                <div className="form-row">
                    <label>
                        Тип:<span className="required-marker">*</span>
                    </label>
                    <select
                        name="type"
                        value={formData.type}
                        onChange={handleChange}
                        required
                    >
                        <option value="">Оберіть тип</option>
                        {SCHOOL_TYPES.map((type) => (
                            <option key={type} value={type}>
                                {type}
                            </option>
                        ))}
                    </select>
                </div>

                <div className="button-group">
                    <button type="submit">Створити</button>
                    <button type="button" onClick={() => navigate('/')}>
                        Назад
                    </button>
                </div>
            </form>
        </div>
    );
};

export default SchoolForm;
