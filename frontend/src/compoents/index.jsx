import { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import emblem from '../assets/emblem.png';
import axios from 'axios';
export default function Index() {
    const [selectedOption, setSelectedOption] = useState('');
    const [houses, setHouses] = useState([]);
    const navigate = useNavigate();
    const handleOptionChange = (event) => {
        const selectedValue = event.target.value;
        setSelectedOption(event.target.value);
        navigate(`/${selectedValue}`);
    };
    useEffect(() => {
        axios.get('http://localhost:8080/houses')
        .then(response => {
            setHouses(response.data);
        })
        .catch(error => {
            console.error(error);
        });
      }, []);
    

    
    return (
        <div className="dropdown-page">
            <div className="dropdown-main">
                <img src={emblem} />
                <select className="dropdown" value={selectedOption} onChange={handleOptionChange}>
                    <option value="">-- Select Your House --</option>
                    {houses && houses.map((option, index) => (
                        <option key={index} value={option}>
                            {option}
                        </option>
                    ))}
                </select>
            </div>
        </div>
    )
}