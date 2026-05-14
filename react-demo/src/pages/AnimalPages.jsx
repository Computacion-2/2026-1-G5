import { useState } from "react";
import Animal from "../components/Animal/Animal";
import animals from "../components/Animal/animals";
import './animalPage.css'

const AnimalPage = () => {

    const [animalState, setAnimalState] = useState(animals)

    const addAnimal = () => {
        const a = {
            "especie":"caballo",
            "raza":"Raza caballo",
            "imgs":""
        }
        setAnimalState([...animalState, a])        
    }
    
    return (
        <>
            <h1>Animales</h1>
            <h2>Lista de mascotas</h2>
            <div className="animals">
                {
                    animalState.map( (a, i) =>
                        <Animal key={i} animal = {a} position = {0}></Animal>
                    )
                }

            </div>
            <button onClick={addAnimal}>Agregar Animal</button>
        </>
    )

}

export default AnimalPage;