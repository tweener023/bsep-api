import React, { useState } from 'react';
import '../styles/AddGuitar.css';
import axios from 'axios';


const CreateGuitarForm = ({ user }) => {
    const [guitarData, setGuitarData] = useState({
        manufacturerOfGuitar: '',
        modelOfGuitar: '',
        yearOfProduction: '',
        price: '',
        stateOfGuitar: '',
        typeOfGuitar: '',
        typeOfMagnets: '',
        tuners: '',
        typeOfWood: '',
        description: ''
    });

    const [yearError, setYearError] = useState('');

    const handleChange = (e) => {
        const { name, value } = e.target;
        if (name === 'yearOfProduction') {
            if (!/^\d{4}$/.test(value)) {
                setYearError('Year must be a number with 4 digits');
            } else {
                setYearError('');
            }
        }
        setGuitarData({ ...guitarData, [name]: value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        
        const user = JSON.parse(localStorage.getItem('user'));
        const accessToken = user.accessToken;

        try {
            await axios.post(`https://localhost:443/api/horder/guitars/${user.id}/addGuitar`, guitarData, {
                headers: {
                    Authorization: `Bearer ${accessToken}`
                }
            });
            // You can perform further actions upon successful submission
            console.log('Guitar added successfully!');
        } catch (error) {
            console.error('Error adding guitar:', error);
        }
    };

    return (
        <div className="create-guitar-form-container">
            <h2>Create New Guitar</h2>
            <form onSubmit={handleSubmit}>
                <label htmlFor="manufacturerOfGuitar">Manufacturer:</label>
                <input type="text" id="manufacturerOfGuitar" name="manufacturerOfGuitar" value={guitarData.manufacturerOfGuitar} onChange={handleChange} required /><br /><br />

                <label htmlFor="modelOfGuitar">Model:</label>
                <input type="text" id="modelOfGuitar" name="modelOfGuitar" value={guitarData.modelOfGuitar} onChange={handleChange} required /><br /><br />

                <label htmlFor="yearOfProduction">Year of Production:</label>
                <input type="number" id="yearOfProduction" name="yearOfProduction" value={guitarData.yearOfProduction} onChange={handleChange} required />
                {yearError && <span className="error">{yearError}</span>}
                <br /><br />

                <label htmlFor="price">Price:</label>
                <input type="number" id="price" name="price" value={guitarData.price} onChange={handleChange} required /><br /><br />

                <label htmlFor="stateOfGuitar">State of Guitar:</label>
                <select id="stateOfGuitar" name="stateOfGuitar" value={guitarData.stateOfGuitar} onChange={handleChange} required>
                    <option value="">Select State</option>
                    <option value="POTREBNA_RESTAURACIJA">POTREBNA_RESTAURACIJA</option>
                    <option value="KORISCENO">KORISCENO</option>
                    <option value="DOBRO">DOBRO</option>
                    <option value="KAO_NOVO">KAO_NOVO</option>
                    <option value="NOVO">NOVO</option>
                </select><br /><br />

                <label htmlFor="typeOfGuitar">Type of Guitar:</label>
                <select id="typeOfGuitar" name="typeOfGuitar" value={guitarData.typeOfGuitar} onChange={handleChange} required>
                    <option value="">Select Type</option>
                    <option value="AKUSTICNA">AKUSTICNA</option>
                    <option value="ELEKTRICNA">ELEKTRICNA</option>
                    <option value="BASS">BASS</option>
                    <option value="HIBRID">HIBRID</option>
                </select><br /><br />

                <label htmlFor="typeOfMagnets">Type of Magnets:</label>
                <select id="typeOfMagnets" name="typeOfMagnets" value={guitarData.typeOfMagnets} onChange={handleChange} required>
                    <option value="">Select Type</option>
                    <option value="PIEZO">PIEZO</option>
                    <option value="SINGL_KOIL">SINGL_KOIL</option>
                    <option value="HAMBAKERI">HAMBAKERI</option>
                    <option value="HSS">HSS</option>
                    <option value="SSH">SSH</option>
                    <option value="DRUGO">DRUGO</option>
                </select><br /><br />

                <label htmlFor="tuners">Tuners:</label>
                <input type="text" id="tuners" name="tuners" value={guitarData.tuners} onChange={handleChange} required /><br /><br />

                <label htmlFor="typeOfWood">Type of Wood:</label>
                <input type="text" id="typeOfWood" name="typeOfWood" value={guitarData.typeOfWood} onChange={handleChange} required /><br /><br />

                <label htmlFor="description">Description:</label>
                <textarea id="description" name="description" value={guitarData.description} onChange={handleChange} required></textarea><br /><br />

                <button type="submit" disabled={!!yearError}>Create Guitar</button>

            </form>
        </div>
    );
};

export default CreateGuitarForm;
