import { useState } from 'react';
import './style.css'

const AnimalForm = ({onClick}) => {

    const [newAnimal, setNewAnimal] = useState({especie:'', raza:'',imgs:''})

    return (
        <div className="animalForm">
            <input 
                type="text" 
                placeholder='Especie' 
                onChange={(e) => setNewAnimal({...newAnimal, especie:e.target.value})} 
            />
            <input 
                type="text" 
                placeholder='Raza' 
                onChange={(e) => setNewAnimal({...newAnimal, raza:e.target.value})} 
            />
            <input 
                type="text" 
                placeholder='Img' 
                onChange={(e) => setNewAnimal({...newAnimal, imgs:e.target.value})} 
            />
            
            <input type="button" onClick={() => onClick(newAnimal)} value="Agregar" />
        </div>
    )
}

export default AnimalForm;