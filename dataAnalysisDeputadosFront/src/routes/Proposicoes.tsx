import { Link } from "react-router-dom";
import { Barchart } from "../components/BarChar";
import Navbar from "../components/navBar";
import { useProposicaoDeputadoData } from "../hooks/ProposicaoDeputadoDataHook";
import { useProposicaoPartidoData } from "../hooks/ProposicaoPartidoDataHook";


export default function Proposicoes(){
    const {data:deputadoData} = useProposicaoDeputadoData();
    const {data:partidoData} = useProposicaoPartidoData(); 

    console.log("teste")
    console.log(deputadoData)
    var numerosDeputados: number[] = []
    var nomesDeputados: string[] = []

    var numerosDeputadosMenor: number[] = []
    var nomesDeputadosMenor: string[] = []

    var numerosPartidos: number[] = []
    var nomesPartidos: string[] = []

    var numerosPartidosMenor: number[] = []
    var nomesPartidosMenor: string[] = []

    deputadoData?.sort((a, b) => b.qtd - a.qtd)
    partidoData?.sort((c, d) => d.qtd - c.qtd)

    deputadoData?.slice(0, 10).map(
        data => {
            numerosDeputados.push(data.qtd)
            nomesDeputados.push(data.nome)
        }
    )

    partidoData?.slice(0, 10).map(
        partidoData => {
            numerosPartidos.push(partidoData.qtd)
            nomesPartidos.push(partidoData.sigla)
        }
    )
    
    deputadoData?.sort((a, b) => a.qtd - b.qtd)
    partidoData?.sort((a, b) => a.qtd - b.qtd)
    

    deputadoData?.slice(0, 10).map(
        data => {
            numerosDeputadosMenor.push(data.qtd)
            nomesDeputadosMenor.push(data.nome)
        }
    )

    partidoData?.slice(0, 10).map(
        partidoData => {
            numerosPartidosMenor.push(partidoData.qtd)
            nomesPartidosMenor.push(partidoData.sigla)
        }
    )
    

    return(
        <div style={{display:'flex', flexDirection:'column', justifyContent:'center', alignItems:'center', width:"100vw"}}>
            <Link to = "/">Home</Link>
            <h1>Depesas 2019-2022</h1>
            <div style={ {display:'flex', alignContent:'center', justifyContent:'space-around', width:'100%' ,flexDirection:'row'}}>
                
                <div>
                    <h2>deputados</h2>
                    <div>
                        <h3>Deputados com maiores numeros de proposições</h3>
                        <Barchart numeros={numerosDeputados} labels={nomesDeputados}/>
                    </div>

                    <div>
                        <h3>Deputados com menores numeros proposições</h3>
                        <Barchart numeros={numerosDeputadosMenor} labels={nomesDeputadosMenor}/>
                    </div>
                </div>

                <div>
                    <h2>Partidos</h2>
                    <div>
                        <h3>Partidos com menores numeros de proposições</h3>
                        <Barchart numeros={numerosPartidos} labels={nomesPartidos}/>
                    </div>

                    <div>
                        <h3>Partidos com menores numeros de proposições</h3>
                        <Barchart numeros={numerosPartidosMenor} labels={nomesPartidosMenor}/>
                    </div>
                </div>

            </div>
            
        </div>
    )
}