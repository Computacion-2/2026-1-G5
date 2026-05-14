import Animal from './Animal';
import './App.css'

const App = () => {
  const list = ['SoC','React','Rest'];
  const animals = ['Perro','Gato','Pato'];

  const onClick = () => {
    alert("Click");

  }

  const mapAnimals = (animal) => {
    return <Animal name={animal} raza="any"></Animal>
  }

  const animalsJsx = animals.map(mapAnimals);

  console.log(animalsJsx)

  return (
    <div>
      <h1 className="App">
        Hello
      </h1>
      <h2 className="App">
        World
      </h2>
      <ul>
        {
          list.map(name => 
            <li>
              {name}
            </li>
          )
        }
      </ul>
      {animalsJsx}
      <button onClick={onClick}>Click</button>
    </div>
  )
}

export default App
