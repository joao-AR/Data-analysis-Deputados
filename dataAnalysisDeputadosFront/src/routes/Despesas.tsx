import { Barchart } from "../components/BarChar";
import Navbar from "../components/navBar";
import { useDespesaDeputadoData } from "../hooks/DespesaDeputadoDataHook";
import { useDespesaPartidoData } from "../hooks/DespesasPartidosDataHook";


export default function Despesas(){
    const {data:deputadoData} = useDespesaDeputadoData();
    const {data:partidoData} = useDespesaPartidoData(); 

    
    
    var numerosDeputados: number[] = []
    var nomesDeputados: string[] = []

    var numerosDeputadosMenor: number[] = []
    var nomesDeputadosMenor: string[] = []

    var numerosPartidos: number[] = []
    var nomesPartidos: string[] = []

    var numerosPartidosMenor: number[] = []
    var nomesPartidosMenor: string[] = []


    deputadoData?.sort((a, b) => b.totalGasto - a.totalGasto)
    partidoData?.sort((c, d) => d.total_liquido - c.total_liquido)
    

    

    deputadoData?.slice(0, 20).map(
        data => {
            numerosDeputados.push(data.totalGasto)
            nomesDeputados.push(data.nome)
        }
    )

    partidoData?.slice(0, 10).map(
        partidoData => {
            console.log(partidoData.total_liquido)
            numerosPartidos.push(partidoData.total_liquido)
            nomesPartidos.push(partidoData.sigla)
        }
    )
    
    deputadoData?.sort((a, b) => a.totalGasto - b.totalGasto)
    partidoData?.sort((a, b) => a.total_liquido - b.total_liquido)
    

    deputadoData?.slice(0, 20).map(
        data => {
            numerosDeputadosMenor.push(data.totalGasto)
            nomesDeputadosMenor.push(data.nome)
        }
    )

    partidoData?.slice(0, 10).map(
        partidoData => {
            numerosPartidosMenor.push(partidoData.total_liquido)
            nomesPartidosMenor.push(partidoData.sigla)
        }
    )
    

    return(
        <div style={{display:'flex', flexDirection:'column', justifyContent:'center', alignItems:'center', width:"100vw"}}>
            <Navbar/>
            <h1>Depesas 2019-2022</h1>
            <div style={ {display:'flex', alignContent:'center', justifyContent:'space-around', width:'100%' ,flexDirection:'row'}}>
                
                <div>
                    <h2>deputados</h2>
                    <div>
                        <h3>Deputados com maiores gastos</h3>
                        <Barchart numeros={numerosDeputados} labels={nomesDeputados}/>
                    </div>

                    <div>
                        <h3>Deputados com menores gastos</h3>
                        <Barchart numeros={numerosDeputadosMenor} labels={nomesDeputadosMenor}/>
                    </div>
                </div>

                <div>
                    <h2>Partidos</h2>
                    <div>
                        <h3>Partidos com maiores gastos</h3>
                        <Barchart numeros={numerosPartidos} labels={nomesPartidos}/>
                    </div>

                    <div>
                        <h3>Partidos com menores gastos</h3>
                        <Barchart numeros={numerosPartidosMenor} labels={nomesPartidosMenor}/>
                    </div>
                </div>

            </div>
            
        </div>
    )
}