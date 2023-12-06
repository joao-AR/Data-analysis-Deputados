
import { Deputado } from './components/Deputado'
import './App.css'
import { useDeputadoData } from './hooks/DeputadoDataHook'
import Navbar from './components/navBar'

function App() {
  // const data:DeputadoData[] = [];
  // const {data} = useDeputadoData();
  return (
    <div className="container">
      <Navbar/>
      {/* <div>
        {
        data?.map(
          deputadoData => 
            <Deputado 
              id={deputadoData.id} 
              idPartido={deputadoData.idPartido} 
              nome={deputadoData.nome} 
              urlFoto={deputadoData.urlFoto} 
              email={deputadoData.email}/>
            )
        }
      </div> */}
    </div>
  )
}

export default App
