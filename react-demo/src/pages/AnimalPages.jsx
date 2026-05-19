import { useEffect, useState } from "react";
import Animal from "../components/AnimalCard/Animal";
import animals from "../components/AnimalCard/animals";
import './animalPage.css'
import AnimalForm from "../components/AnimalForm/AnimalForm";

const AnimalPage = () => {

    const [animalState, setAnimalState] = useState(animals)
    const [count, setCount] = useState(animalState.length)


    useEffect(()=>{
        console.log("page init")
        },
        []
    )
    
    
    const addAnimal = (a) => {
        setCount(count + 1)
        setCount(count + 1)
        

        setCount(prev => prev+1)
        setAnimalState([...animalState, a])        
    }
    
    return (
        <>
            <h1>Animales</h1>
            <h2>Lista de mascotas: {count}</h2>
            <div className="animals">
                {
                    animalState.map( (a, i) =>
                        <Animal key={i} animal = {a} position = {0}></Animal>
                    )
                }

            </div>
            <AnimalForm onClick = {addAnimal}></AnimalForm>
        </>
    )

}

export default AnimalPage;