import './animal.css'

const Animal = ({animal}) => {

    return (
        <div className="animalConteiner">
            <img src={animal.imgs} ></img>
            <h1>{animal.especie}</h1>
            <h1>{animal.raza}</h1>

        </div>

    )
}

export default Animal;