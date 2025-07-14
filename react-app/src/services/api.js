import axios from 'axios';

const API_BASE = '/api/schools';

export const getSchools = (filters) => {
    return axios.get(API_BASE, { params: filters });
};

export const deactivateSchool = (id) => {
    return axios.patch(`${API_BASE}/${id}/deactivate`);
};

export const createSchool = (data) => {
    return axios.post(API_BASE, data);
};