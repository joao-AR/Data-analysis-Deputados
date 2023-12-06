import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import { Deputado } from './components/Deputado'
import './App.css'
import { DeputadoData } from './interface/DeputadoData'
import { useDeputadoData } from './hooks/DeputadoDataHook'

function App() {
  // const data:DeputadoData[] = [];
  const {data} = useDeputadoData();
  return (
    <div className="container">
      <div>
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
      </div>
    </div>
  )
}

export default App
